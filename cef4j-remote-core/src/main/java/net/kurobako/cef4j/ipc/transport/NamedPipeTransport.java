package net.kurobako.cef4j.ipc.transport;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Windows named-pipe transport using only standard Java file APIs. */
public final class NamedPipeTransport implements CefTransport {
    private static final Logger LOG = LoggerFactory.getLogger(NamedPipeTransport.class);
    private static final int MAX_FRAME_SIZE = 64 * 1024 * 1024;
    private static final AtomicInteger INSTANCE = new AtomicInteger();
    private static final Executor CLOSE_EXECUTOR =
            Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors(), task -> {
                Thread thread = new Thread(task, "named-pipe-closer-" + INSTANCE.incrementAndGet());
                thread.setDaemon(true);
                return thread;
            });

    private final String endpoint;
    private final Closeable pipe;
    private final DataInputStream input;
    private final DataOutputStream output;
    private final Object ioLock = new Object();
    private final Object receiveLock = new Object();
    private final Executor closeExecutor;
    private final ArrayDeque<byte[]> pending = new ArrayDeque<>();
    private final AtomicBoolean disconnectNotified = new AtomicBoolean();

    @Nullable
    private volatile Consumer<ByteBuffer> receiveHandler;

    @Nullable
    private volatile Runnable disconnectHandler;

    private volatile boolean closed;
    private volatile boolean disconnected;

    @Nonnull
    public static NamedPipeTransport connect(@Nonnull String endpoint) throws CefTransportException {
        return connect(endpoint, CLOSE_EXECUTOR);
    }

    @Nonnull
    public static NamedPipeTransport connect(@Nonnull String endpoint, @Nonnull Executor closeExecutor)
            throws CefTransportException {
        Objects.requireNonNull(closeExecutor, "closeExecutor");
        if (!System.getProperty("os.name", "").toLowerCase(Locale.ROOT).contains("win")) {
            throw new CefTransportException("Windows named pipes are unavailable on " + System.getProperty("os.name"));
        }
        String path = pathOf(endpoint);
        RandomAccessFile pipe = null;
        try {
            pipe = new RandomAccessFile(path, "rw");
            FileDescriptor descriptor = pipe.getFD();
            return new NamedPipeTransport(
                    endpointOf(path),
                    pipe,
                    new FileInputStream(descriptor),
                    new FileOutputStream(descriptor),
                    closeExecutor);
        } catch (IOException failure) {
            closeResource(endpoint, pipe);
            throw new CefTransportException(endpoint + ": connect failed", failure);
        }
    }

    NamedPipeTransport(String endpoint, Closeable pipe, InputStream inputPipe, OutputStream outputPipe) {
        this(endpoint, pipe, inputPipe, outputPipe, CLOSE_EXECUTOR);
    }

    NamedPipeTransport(
            String endpoint, Closeable pipe, InputStream inputPipe, OutputStream outputPipe, Executor closeExecutor) {
        this.endpoint = endpoint;
        this.pipe = pipe;
        this.input = new DataInputStream(new BufferedInputStream(inputPipe));
        this.output = new DataOutputStream(new BufferedOutputStream(outputPipe));
        this.closeExecutor = Objects.requireNonNull(closeExecutor, "closeExecutor");
        Thread reader = new Thread(this::readLoop, "named-pipe-reader-" + INSTANCE.incrementAndGet());
        reader.setDaemon(true);
        reader.start();
    }

    @Override
    public void send(@Nonnull ByteBuffer frame) throws CefTransportException {
        if (closed || disconnected) throw new CefTransportException(endpoint + ": pipe is disconnected");
        byte[] bytes = new byte[frame.remaining()];
        frame.get(bytes);
        if (bytes.length > MAX_FRAME_SIZE) throw new CefTransportException(endpoint + ": frame is too large");
        synchronized (ioLock) {
            try {
                output.writeInt(bytes.length);
                output.write(bytes);
                output.flush();
            } catch (IOException failure) {
                markDisconnected();
                throw new CefTransportException(endpoint + ": send failed", failure);
            }
        }
    }

    @Override
    public void onReceive(@Nonnull Consumer<ByteBuffer> handler) {
        synchronized (receiveLock) {
            receiveHandler = handler;
            byte[] frame;
            while ((frame = pending.poll()) != null) dispatch(handler, frame);
        }
    }

    @Override
    public void onDisconnect(@Nonnull Runnable handler) {
        disconnectHandler = handler;
        fireDisconnectIfReady();
    }

    @Override
    public boolean isConnected() {
        return !closed && !disconnected;
    }

    @Override
    public boolean isRuntimeServerClient() {
        return true;
    }

    @Override
    public void close() {
        if (closed) return;
        closed = true;
        // XXX: A synchronous Windows ReadFile can block close on the same handle; remove when this transport uses
        // overlapped I/O or separate read/write handles.
        closeAsync(endpoint, pipe, closeExecutor);
    }

    static void closeAsync(String endpoint, Closeable resource, Executor executor) {
        Runnable close = () -> closeResource(endpoint, resource);
        try {
            executor.execute(close);
        } catch (RejectedExecutionException rejection) {
            LOG.debug("close executor rejected work for {}; using the default executor", endpoint, rejection);
            CLOSE_EXECUTOR.execute(close);
        }
    }

    private static void closeResource(String endpoint, @Nullable Closeable resource) {
        if (resource == null) return;
        try {
            resource.close();
        } catch (IOException failure) {
            LOG.debug("close on {} failed", endpoint, failure);
        }
    }

    private void readLoop() {
        try {
            while (!closed) {
                byte[] frame = null;
                // XXX: Windows serializes synchronous ReadFile and WriteFile on one handle; remove with overlapped I/O.
                synchronized (ioLock) {
                    if (input.available() >= Integer.BYTES) {
                        int length = input.readInt();
                        if (length < 0 || length > MAX_FRAME_SIZE) {
                            throw new IOException("invalid frame length " + length);
                        }
                        frame = new byte[length];
                        input.readFully(frame);
                    }
                }
                if (frame == null) {
                    Thread.sleep(2L);
                    continue;
                }
                synchronized (receiveLock) {
                    Consumer<ByteBuffer> handler = receiveHandler;
                    if (handler == null) pending.add(frame);
                    else dispatch(handler, frame);
                }
            }
        } catch (InterruptedException failure) {
            Thread.currentThread().interrupt();
            markDisconnected();
        } catch (EOFException failure) {
            markDisconnected();
        } catch (IOException failure) {
            if (!closed) LOG.debug("reader on {} failed", endpoint, failure);
            markDisconnected();
        }
    }

    private void dispatch(Consumer<ByteBuffer> handler, byte[] frame) {
        try {
            handler.accept(ByteBuffer.wrap(frame));
        } catch (RuntimeException failure) {
            LOG.warn("receive handler on {} threw", endpoint, failure);
        }
    }

    private void markDisconnected() {
        if (closed || disconnected) return;
        disconnected = true;
        fireDisconnectIfReady();
    }

    private void fireDisconnectIfReady() {
        Runnable handler = disconnectHandler;
        if (disconnected && !closed && handler != null && disconnectNotified.compareAndSet(false, true)) {
            try {
                handler.run();
            } catch (RuntimeException failure) {
                LOG.warn("disconnect handler on {} threw", endpoint, failure);
            }
        }
    }

    static String pathOf(String endpoint) throws CefTransportException {
        String name = endpoint.startsWith("pipe://") ? endpoint.substring("pipe://".length()) : endpoint;
        if (name.startsWith("\\\\.\\pipe\\")) return name;
        if (!name.matches("[A-Za-z0-9._-]+")) throw new CefTransportException("invalid named-pipe endpoint");
        return "\\\\.\\pipe\\" + name;
    }

    private static String endpointOf(String path) {
        String prefix = "\\\\.\\pipe\\";
        return path.startsWith(prefix) ? "pipe://" + path.substring(prefix.length()) : path;
    }
}
