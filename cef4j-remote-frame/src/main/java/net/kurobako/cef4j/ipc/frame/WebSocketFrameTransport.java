package net.kurobako.cef4j.ipc.frame;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.kurobako.cef4j.ipc.session.CefSession;
import net.kurobako.cef4j.ipc.session.RemoteHandle;

/** @deprecated use {@link InlineFrameTransport}. */
@Deprecated
public final class WebSocketFrameTransport implements FrameTransport {
    private final InlineFrameTransport delegate;

    @Nonnull
    public static WebSocketFrameTransport bind(@Nonnull CefSession session, @Nonnull RemoteHandle browser) {
        return new WebSocketFrameTransport(InlineFrameTransport.bind(session, browser));
    }

    @Nonnull
    public static WebSocketFrameTransport bindAll(@Nonnull CefSession session) {
        return new WebSocketFrameTransport(InlineFrameTransport.bindAll(session));
    }

    private WebSocketFrameTransport(InlineFrameTransport delegate) {
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
