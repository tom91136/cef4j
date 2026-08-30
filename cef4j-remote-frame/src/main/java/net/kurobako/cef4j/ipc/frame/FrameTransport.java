package net.kurobako.cef4j.ipc.frame;

import java.nio.ByteBuffer;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * Per-browser pixel-frame channel, independent of the storage or network mechanism used to deliver pixels.
 *
 * <p><b>Threading contract.</b> {@link FrameConsumer#accept} fires on a transport-owned thread. The transport never
 * marshals onto the JFX, Swing, or caller thread; UI consumers are responsible for hopping to their UI thread.
 *
 * <p><b>Buffer lifetime.</b> {@code pixels} is a read-only view of memory the transport owns and is valid only for the
 * duration of the callback. Consumers that retain pixels must copy them before the callback returns.
 *
 * <p>{@link #onFrame} replaces any prior consumer rather than fanning out. Closing unsubscribes from the underlying
 * channel and releases transport resources; closing is idempotent.
 */
@NullableBoundary("null consumers disable frame callbacks")
public interface FrameTransport extends AutoCloseable {

    /** Replace the current frame consumer. Pass {@code null} to disable callbacks without closing the transport. */
    void onFrame(@Nullable FrameConsumer consumer);

    /**
     * Replaces the current consumer with the richer raw-frame view used by codecs. The pixel view has the same
     * callback-only lifetime as {@link FrameConsumer}.
     */
    default void onRawFrame(@Nullable RawFrameConsumer consumer) {
        onFrame(
                consumer == null
                        ? null
                        : (width, height, pixels, metadata) ->
                                consumer.accept(new RawFrame(width, height, width * 4, pixels, metadata)));
    }

    @Override
    void close();

    @FunctionalInterface
    interface FrameConsumer {
        /** Fires once per delivered paint on a transport-owned thread. */
        void accept(int width, int height, @Nonnull ByteBuffer pixels, @Nonnull FrameMetadata meta);
    }

    @FunctionalInterface
    interface RawFrameConsumer {
        void accept(@Nonnull RawFrame frame);
    }
}
