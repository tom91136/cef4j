package net.kurobako.cef4j.ipc.frame;

import java.nio.ByteBuffer;
import javax.annotation.Nonnull;

/**
 * Per-browser pixel-frame channel. Decouples the OSR pixel pipeline from the IPC control plane: the helper writes
 * frames to a backing store (mmap'd shm today, possibly hardware-accelerated tomorrow) and signals readiness via a
 * control-plane event; the {@code FrameTransport} implementation translates that into a typed callback handing the
 * caller a read-only view of the pixels.
 *
 * <p><b>Threading contract (locked).</b> {@code FrameConsumer.accept} fires on a transport-owned thread — for
 * {@link MmapFrameTransport} that's the {@code CefSession}'s IO reader thread, the same one that delivered the
 * underlying {@code OsrPaintEvent}. The transport never marshals onto the JFX, Swing, or caller thread; UI consumers
 * are responsible for hopping to their own thread (e.g. {@code Platform.runLater}) inside the callback. This keeps
 * non-UI consumers (server-side renderers, headless tests) from paying an FX-pump dependency. Implementations are free
 * to use any internal worker thread but must not call back on the consumer's calling thread or any thread the caller
 * hasn't already opted into.
 *
 * <p><b>Buffer lifetime.</b> {@code pixels} is a read-only view of memory the transport owns; it is valid for the
 * duration of the callback only. Subsequent paints may overwrite the same backing store. Consumers that retain pixels
 * past the callback must {@link ByteBuffer#duplicate} into their own buffer first.
 *
 * <p>Single subscriber: {@link #onFrame} replaces any prior consumer rather than fanning out, mirroring
 * {@code CefTransport.onReceive}'s contract. If multiple parties want frames, build a fan-out atop a single
 * {@code FrameTransport}.
 *
 * <p>Lifetime: closing the transport unsubscribes from the underlying control channel and releases mapped buffers;
 * subsequent paint events are silently dropped. Idempotent.
 */
public interface FrameTransport extends AutoCloseable {

    /** Replace the current frame consumer. Pass {@code null} to disable callbacks without closing the transport. */
    void onFrame(FrameConsumer consumer);

    @Override
    void close();

    @FunctionalInterface
    interface FrameConsumer {
        /**
         * Fires once per paint on a transport-owned thread (see {@link FrameTransport} javadoc). Pixel buffer is valid
         * only for the duration of this call.
         */
        void accept(int width, int height, @Nonnull ByteBuffer pixels, @Nonnull FrameMetadata meta);
    }
}
