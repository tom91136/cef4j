package net.kurobako.cef4j.ipc.transport;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.function.Consumer;
import javax.annotation.Nonnull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Decorator that records every send and received frame to a {@link MessageLog}, then forwards to the underlying
 * transport. Closing this transport closes both the delegate and the log.
 *
 * <p>Direction in the log is recorded from this transport's perspective: {@code OUTBOUND} for things {@link #send}
 * passed in, {@code INBOUND} for frames delivered to the {@code onReceive} handler.
 */
public final class RecordingTransport implements CefTransport {

    private static final Logger LOG = LoggerFactory.getLogger(RecordingTransport.class);

    private final CefTransport delegate;
    private final MessageLog.Writer log;

    public RecordingTransport(@Nonnull CefTransport delegate, @Nonnull MessageLog.Writer log) {
        this.delegate = delegate;
        this.log = log;
    }

    @Override
    public void send(@Nonnull ByteBuffer frame) throws CefTransportException {
        ByteBuffer view = frame.duplicate();
        byte[] copy = new byte[view.remaining()];
        view.get(copy);
        try {
            log.append(MessageLog.Direction.OUTBOUND, System.nanoTime(), copy);
        } catch (IOException e) {
            throw new CefTransportException("recording outbound failed", e);
        }
        delegate.send(frame);
    }

    @Override
    public void onReceive(@Nonnull Consumer<ByteBuffer> handler) {
        delegate.onReceive(frame -> {
            ByteBuffer view = frame.duplicate();
            byte[] copy = new byte[view.remaining()];
            view.get(copy);
            try {
                log.append(MessageLog.Direction.INBOUND, System.nanoTime(), copy);
            } catch (IOException e) {
                // The recording is best-effort; a broken log must not stall live traffic.
                LOG.warn("recording inbound failed", e);
            }
            handler.accept(frame);
        });
    }

    @Override
    public void onDisconnect(@Nonnull Runnable handler) {
        delegate.onDisconnect(handler);
    }

    @Override
    public boolean isConnected() {
        return delegate.isConnected();
    }

    @Override
    public void close() {
        try {
            delegate.close();
        } finally {
            try {
                log.close();
            } catch (IOException e) {
                LOG.warn("failed to close MessageLog writer", e);
            }
        }
    }
}
