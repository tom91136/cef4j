package net.kurobako.cef4j.ipc.frame;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.kurobako.cef4j.ipc.session.CefSession;
import net.kurobako.cef4j.ipc.session.RemoteHandle;

/** Portable raw-frame fallback that receives complete BGRA snapshots inside IPC events. */
@SuppressWarnings("deprecation")
public final class InlineFrameTransport implements FrameTransport {
    private final WebSocketFrameTransport delegate;

    @Nonnull
    public static InlineFrameTransport bind(@Nonnull CefSession session, @Nonnull RemoteHandle browser) {
        return new InlineFrameTransport(WebSocketFrameTransport.bind(session, browser));
    }

    @Nonnull
    public static InlineFrameTransport bindAll(@Nonnull CefSession session) {
        return new InlineFrameTransport(WebSocketFrameTransport.bindAll(session));
    }

    private InlineFrameTransport(WebSocketFrameTransport delegate) {
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
