package net.kurobako.cef4j.ipc.frame;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import javax.annotation.Nonnull;

/** Validates delta dependencies before passing encoded frames to a stateful decoder. */
public final class EncodedFrameReceiver implements AutoCloseable {
    private final FrameDecoder decoder;
    private final Runnable keyFrameRequest;
    private long lastSequence = EncodedFrame.NO_BASE_SEQUENCE;
    private int width = -1;
    private int height = -1;

    public EncodedFrameReceiver(@Nonnull FrameDecoder decoder, @Nonnull Runnable keyFrameRequest) {
        this.decoder = Objects.requireNonNull(decoder, "decoder");
        this.keyFrameRequest = Objects.requireNonNull(keyFrameRequest, "keyFrameRequest");
    }

    /** Returns empty after a dependency gap; the caller should keep receiving until the requested key frame arrives. */
    @Nonnull
    public Optional<RawFrame> accept(@Nonnull EncodedFrame frame) throws IOException {
        Objects.requireNonNull(frame, "frame");
        CodecDescriptor expected = decoder.descriptor();
        if (!expected.id().equals(frame.codec().id())) {
            throw new IOException("received codec " + frame.codec().id() + " for decoder " + expected.id());
        }
        if (!frame.keyFrame() && frame.baseSequence() != lastSequence) {
            loseDependency();
            return Optional.empty();
        }
        if ((width != -1 && (width != frame.width() || height != frame.height())) && !frame.keyFrame()) {
            loseDependency();
            return Optional.empty();
        }
        if (width != -1 && (width != frame.width() || height != frame.height())) {
            decoder.reset(CodecResetReason.SOURCE_RESIZED);
        }
        RawFrame decoded = decoder.decode(frame);
        lastSequence = frame.sequence();
        width = frame.width();
        height = frame.height();
        return Optional.of(decoded);
    }

    /** Clears dependency state when a server generation or stream changes. */
    public void restart() {
        lastSequence = EncodedFrame.NO_BASE_SEQUENCE;
        width = -1;
        height = -1;
        decoder.reset(CodecResetReason.STREAM_RESTARTED);
        keyFrameRequest.run();
    }

    private void loseDependency() {
        lastSequence = EncodedFrame.NO_BASE_SEQUENCE;
        decoder.reset(CodecResetReason.DEPENDENCY_LOST);
        keyFrameRequest.run();
    }

    @Override
    public void close() {
        decoder.close();
    }
}
