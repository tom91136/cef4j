package net.kurobako.cef4j.ipc.transport;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.newsclub.net.unix.AFUNIXSocket;
import org.newsclub.net.unix.AFUNIXSocketAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Unix-domain stream socket transport. Frames use a four-byte network-order length prefix. */
public final class UdsTransport implements CefTransport {
    private static final Logger LOG = LoggerFactory.getLogger(UdsTransport.class);
    private static final int MAX_FRAME_SIZE = 64 * 1024 * 1024;
    private static final AtomicInteger INSTANCE = new AtomicInteger();

    private final String endpoint;
    private final Socket socket;
    private final DataInputStream input;
    private final DataOutputStream output;
    private final Object sendLock = new Object();
    private final Object receiveLock = new Object();
    private final ArrayDeque<byte[]> pending = new ArrayDeque<>();
    private final Thread reader;

    @Nullable
    private volatile Consumer<ByteBuffer> receiveHandler;

    @Nullable
    private volatile Runnable disconnectHandler;

    private volatile boolean closed;
    private volatile boolean disconnected;
    private final AtomicBoolean disconnectNotified = new AtomicBoolean();

    @Nonnull
    public static UdsTransport connect(@Nonnull String endpoint) throws CefTransportException {
        Path path = pathOf(endpoint);
        AFUNIXSocket socket = null;
        try {
            socket = AFUNIXSocket.newInstance();
            socket.connect(AFUNIXSocketAddress.of(path));
            return new UdsTransport(endpoint(path), socket);
        } catch (IOException e) {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException closeFailure) {
                    e.addSuppressed(closeFailure);
                }
            }
            throw new CefTransportException(endpoint + ": connect failed", e);
        }
    }

    static UdsTransport accepted(@Nonnull Path path, @Nonnull Socket socket) throws IOException {
        return new UdsTransport(endpoint(path), socket);
    }

    private UdsTransport(String endpoint, Socket socket) throws IOException {
        this.endpoint = endpoint;
        this.socket = socket;
        this.input = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        this.output = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
        this.reader = new Thread(this::readLoop, "uds-reader-" + INSTANCE.incrementAndGet());
        reader.setDaemon(true);
        reader.start();
    }

    @Nonnull
    public String endpoint() {
        return endpoint;
    }

    @Override
    public void send(@Nonnull ByteBuffer frame) throws CefTransportException {
        if (closed) throw new CefTransportException(endpoint + ": send on closed transport");
        if (disconnected) throw new CefTransportException(endpoint + ": peer disconnected");
        byte[] copy = new byte[frame.remaining()];
        frame.get(copy);
        if (copy.length > MAX_FRAME_SIZE) throw new CefTransportException(endpoint + ": frame is too large");
        synchronized (sendLock) {
            try {
                output.writeInt(copy.length);
                output.write(copy);
                output.flush();
            } catch (IOException e) {
                markDisconnected();
                throw new CefTransportException(endpoint + ": send failed", e);
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
    public void close() {
        if (closed) return;
        closed = true;
        try {
            socket.close();
        } catch (IOException e) {
            LOG.debug("close on {} failed", endpoint, e);
        }
    }

    private void readLoop() {
        try {
            while (!closed) {
                int length = input.readInt();
                if (length < 0 || length > MAX_FRAME_SIZE) throw new IOException("invalid frame length " + length);
                byte[] frame = new byte[length];
                input.readFully(frame);
                synchronized (receiveLock) {
                    Consumer<ByteBuffer> handler = receiveHandler;
                    if (handler == null) pending.add(frame);
                    else dispatch(handler, frame);
                }
            }
        } catch (EOFException e) {
            markDisconnected();
        } catch (IOException e) {
            if (!closed) LOG.debug("reader on {} failed", endpoint, e);
            markDisconnected();
        }
    }

    private void dispatch(Consumer<ByteBuffer> handler, byte[] frame) {
        try {
            handler.accept(ByteBuffer.wrap(frame));
        } catch (RuntimeException e) {
            LOG.warn("receive handler on {} threw", endpoint, e);
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
            } catch (RuntimeException e) {
                LOG.warn("disconnect handler on {} threw", endpoint, e);
            }
        }
    }

    static Path pathOf(String endpoint) {
        String value = endpoint.startsWith("unix://") ? endpoint.substring("unix://".length()) : endpoint;
        return Paths.get(value);
    }

    static String endpoint(Path path) {
        return "unix://" + path.toAbsolutePath();
    }
}
