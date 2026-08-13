package net.kurobako.cef4j.ipc.frame;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.kurobako.cef4j.ipc.session.CefSession;
import net.kurobako.cef4j.ipc.session.RemoteHandle;

/**
 * Cross-platform, file-backed shared-frame transport. The server and Java process map the same temporary file; frame
 * sequence validation prevents a producer wraparound from exposing torn pixels.
 */
@SuppressWarnings("deprecation")
public final class SharedFileFrameTransport implements FrameTransport {
    private final MmapFrameTransport delegate;

    @Nonnull
    public static SharedFileFrameTransport bind(@Nonnull CefSession session, @Nonnull RemoteHandle browser) {
        return new SharedFileFrameTransport(MmapFrameTransport.bind(session, browser));
    }

    @Nonnull
    public static SharedFileFrameTransport bindAll(@Nonnull CefSession session) {
        return new SharedFileFrameTransport(MmapFrameTransport.bindAll(session));
    }

    private SharedFileFrameTransport(MmapFrameTransport delegate) {
        this.delegate = delegate;
    }

    @Override
    public void onFrame(@Nullable FrameConsumer consumer) {
        delegate.onFrame(consumer);
    }

    @Override
    public void close() {
        delegate.close();
    }
}
