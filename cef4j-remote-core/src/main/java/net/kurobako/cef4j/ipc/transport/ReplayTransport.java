package net.kurobako.cef4j.ipc.transport;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Standalone {@link CefTransport} that replays a {@link MessageLog} produced by a previous run under
 * {@link RecordingTransport}. No network or peer is involved.
 *
 * <p>Two-phase use:
 *
 * <ol>
 *   <li>{@link #onReceive} stores the handler.
 *   <li>{@link #start} drains the recorded {@code INBOUND} frames in order through that handler.
 * </ol>
 *
 * The split exists because callers built on top of this transport (e.g. {@code CefSessionImpl}) install their own
 * {@code onReceive} in their constructor; user-level subscriptions register only afterwards. Eagerly dispatching on
 * {@code onReceive} would race with that wiring.
 *
 * <p>Recorded {@code OUTBOUND} frames are exposed via {@link #recordedOutbound()} so callers can assert that the
 * subject under test produced the same sends; frames passed to {@link #send} on this replay transport are captured in
 * {@link #actualOutbound()} for comparison.
 */
public final class ReplayTransport implements CefTransport {

    private final List<byte[]> inbound;
    private final List<byte[]> recordedOutbound;
    private final List<byte[]> actualOutbound = new ArrayList<>();
    private final Object outboundLock = new Object();

    @Nullable
    private volatile Consumer<ByteBuffer> handler;

    private volatile boolean started = false;
    private volatile boolean closed = false;

    public static ReplayTransport fromFile(@Nonnull Path file) throws IOException {
        try (MessageLog.Reader r = MessageLog.reader(file)) {
            return fromReader(r);
        }
    }

    public static ReplayTransport fromReader(@Nonnull MessageLog.Reader reader) throws IOException {
        List<byte[]> in = new ArrayList<>();
        List<byte[]> out = new ArrayList<>();
        MessageLog.Entry e;
        while ((e = reader.next().orElse(null)) != null) {
            switch (e.direction) {
                case INBOUND:
                    in.add(e.payload);
                    break;
                case OUTBOUND:
                    out.add(e.payload);
                    break;
                default:
                    throw new IOException("unexpected direction: " + e.direction);
            }
        }
        return new ReplayTransport(in, out);
    }

    private ReplayTransport(List<byte[]> inbound, List<byte[]> recordedOutbound) {
        this.inbound = inbound;
        this.recordedOutbound = recordedOutbound;
    }

    /** The sequence of {@code OUTBOUND} frames as they appeared in the recording. */
    public List<byte[]> recordedOutbound() {
        return Collections.unmodifiableList(recordedOutbound);
    }

    /** The sequence of frames passed to {@link #send} on this replay instance. */
    public List<byte[]> actualOutbound() {
        synchronized (outboundLock) {
            return new ArrayList<>(actualOutbound);
        }
    }

    @Override
    public void send(@Nonnull ByteBuffer frame) throws CefTransportException {
        if (closed) throw new CefTransportException("replay transport closed");
        byte[] copy = new byte[frame.remaining()];
        frame.get(copy);
        synchronized (outboundLock) {
            actualOutbound.add(copy);
        }
    }

    @Override
    public void onReceive(@Nonnull Consumer<ByteBuffer> h) {
        this.handler = h;
    }

    /**
     * Drain the recorded inbound frames through the registered {@link #onReceive} handler. Callers register all
     * handlers (including session-layer subscriptions) before invoking this; doing the work in two phases avoids a race
     * where events arrive before the consumer can subscribe. Idempotent.
     */
    public void start() {
        if (started) return;
        started = true;
        Consumer<ByteBuffer> h = handler;
        if (h == null) return;
        for (byte[] payload : inbound) h.accept(ByteBuffer.wrap(payload));
    }

    @Override
    public void onDisconnect(@Nonnull Runnable r) {
        // Replay never raises a disconnect; no peer exists.
    }

    @Override
    public boolean isConnected() {
        return !closed;
    }

    @Override
    public void close() {
        closed = true;
    }
}
