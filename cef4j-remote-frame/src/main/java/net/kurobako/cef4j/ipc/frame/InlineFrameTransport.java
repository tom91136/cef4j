package net.kurobako.cef4j.ipc.frame;

import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.kurobako.cef4j.ipc.protocol.gen.InlinePaintEvent;
import net.kurobako.cef4j.ipc.session.CefSession;
import net.kurobako.cef4j.ipc.session.CefSession.HandlerRegistration;
import net.kurobako.cef4j.ipc.session.RemoteHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Portable raw-frame fallback that receives complete BGRA snapshots inside IPC events. */
public final class InlineFrameTransport implements FrameTransport {
    private static final Logger LOG = LoggerFactory.getLogger(InlineFrameTransport.class);
    private static final int BYTES_PER_PIXEL = 4;

    @Nullable
    private final RemoteHandle browser;

    private final AtomicInteger sequence = new AtomicInteger();

    @Nullable
    private volatile FrameConsumer consumer;

    @Nullable
    private HandlerRegistration registration;

    private volatile boolean closed;

    @Nonnull
    public static InlineFrameTransport bind(@Nonnull CefSession session, @Nonnull RemoteHandle browser) {
        InlineFrameTransport transport = new InlineFrameTransport(browser);
        transport.subscribe(session);
        return transport;
    }

    @Nonnull
    public static InlineFrameTransport bindAll(@Nonnull CefSession session) {
        InlineFrameTransport transport = new InlineFrameTransport(null);
        transport.subscribe(session);
        return transport;
    }

    private InlineFrameTransport(@Nullable RemoteHandle browser) {
        this.browser = browser;
    }

    private void subscribe(CefSession session) {
        registration = session.onLatest(InlinePaintEvent.MESSAGE_ID, InlinePaintEvent.DECODER, this::onPaint);
    }

    private void onPaint(InlinePaintEvent event) {
        if (closed || (browser != null && browser.id() != event.browser().id())) return;
        FrameConsumer current = consumer;
        if (current == null) return;
        long expectedBytes = (long) event.width() * event.height() * BYTES_PER_PIXEL;
        byte[] pixels = event.pixels();
        if (event.width() <= 0
                || event.height() <= 0
                || expectedBytes > Integer.MAX_VALUE
                || pixels.length != expectedBytes) {
            LOG.warn(
                    "dropping malformed inline frame {}x{} with {} bytes for browser={}",
                    event.width(),
                    event.height(),
                    pixels.length,
                    event.browser().id());
            return;
        }
        // Coalescing may skip intermediate paints, so the latest event's dirty rectangle cannot safely describe all
        // changes since the last delivered frame. The payload is a complete snapshot; advertise a full-frame update.
        Rect dirty = new Rect(0, 0, event.width(), event.height());
        FrameMetadata metadata = new FrameMetadata(
                sequence.incrementAndGet(),
                event.frameSequence(),
                System.nanoTime(),
                PixelFormat.BGRA,
                Collections.singletonList(dirty));
        try {
            current.accept(
                    event.width(), event.height(), ByteBuffer.wrap(pixels).asReadOnlyBuffer(), metadata);
        } catch (RuntimeException e) {
            LOG.warn(
                    "frame consumer threw for browser={} wire sequence={}",
                    event.browser().id(),
                    event.frameSequence(),
                    e);
        }
    }

    @Override
    public void onFrame(@Nullable FrameConsumer consumer) {
        this.consumer = consumer;
    }

    @Override
    public void close() {
        if (closed) return;
        closed = true;
        consumer = null;
        HandlerRegistration current = registration;
        if (current != null) current.unregister();
        registration = null;
    }
}
