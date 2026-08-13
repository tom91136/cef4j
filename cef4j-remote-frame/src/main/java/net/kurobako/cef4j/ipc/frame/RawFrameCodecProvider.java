package net.kurobako.cef4j.ipc.frame;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nonnull;

/** Built-in lossless BGRA pass-through codec, useful for negotiation and custom transport integrations. */
public final class RawFrameCodecProvider implements FrameCodecProvider {
    private static final CodecDescriptor DESCRIPTOR =
            new CodecDescriptor("raw-bgra", "application/x-cef4j-bgra", false);

    @Override
    @Nonnull
    public CodecDescriptor descriptor() {
        return DESCRIPTOR;
    }

    @Override
    @Nonnull
    public FrameCodec newEncoder(@Nonnull Map<String, String> configuration) {
        return new FrameCodec() {
            @Override
            @Nonnull
            public CodecDescriptor descriptor() {
                return DESCRIPTOR;
            }

            @Override
            @Nonnull
            public EncodedFrame encode(@Nonnull RawFrame frame) {
                return new EncodedFrame(
                        DESCRIPTOR,
                        frame.metadata().sourceSequence(),
                        EncodedFrame.NO_BASE_SEQUENCE,
                        true,
                        frame.width(),
                        frame.height(),
                        frame.pixels());
            }
        };
    }

    @Override
    @Nonnull
    public FrameDecoder newDecoder(@Nonnull Map<String, String> configuration) {
        return new FrameDecoder() {
            private final AtomicInteger delivered = new AtomicInteger();

            @Override
            @Nonnull
            public CodecDescriptor descriptor() {
                return DESCRIPTOR;
            }

            @Override
            @Nonnull
            public RawFrame decode(@Nonnull EncodedFrame frame) {
                FrameMetadata metadata = new FrameMetadata(
                        delivered.incrementAndGet(),
                        frame.sequence(),
                        System.nanoTime(),
                        PixelFormat.BGRA,
                        Collections.singletonList(new Rect(0, 0, frame.width(), frame.height())));
                return new RawFrame(frame.width(), frame.height(), frame.width() * 4, frame.payload(), metadata);
            }
        };
    }
}
