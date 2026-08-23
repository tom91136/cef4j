package net.kurobako.cef4j.ipc.frame;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.kurobako.cef4j.ipc.session.CefSession;
import net.kurobako.cef4j.ipc.session.RemoteHandle;

/** @deprecated use {@link SharedFileFrameTransport}. */
@Deprecated
public final class MmapFrameTransport implements FrameTransport {
    private final SharedFileFrameTransport delegate;

    @Nonnull
    public static MmapFrameTransport bind(@Nonnull CefSession session, @Nonnull RemoteHandle browser) {
        return new MmapFrameTransport(SharedFileFrameTransport.bind(session, browser));
    }

    @Nonnull
    public static MmapFrameTransport bindAll(@Nonnull CefSession session) {
        return new MmapFrameTransport(SharedFileFrameTransport.bindAll(session));
    }

    private MmapFrameTransport(SharedFileFrameTransport delegate) {
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
