package net.kurobako.cef4j.ipc.frame;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import javax.annotation.Nonnull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Non-blocking bridge from raw paints to a codec. Only the newest unencoded snapshot is retained, keeping CEF and IPC
 * reader threads independent from codec latency. Encoded frames are never silently dropped by this class.
 */
public final class EncodedFramePipeline implements AutoCloseable {
    private static final Logger LOG = LoggerFactory.getLogger(EncodedFramePipeline.class);

    private final FrameCodec codec;
    private final Consumer<EncodedFrame> consumer;
    private final ExecutorService worker;
    private final AtomicReference<RawFrame> latest = new AtomicReference<>();
    private final AtomicBoolean scheduled = new AtomicBoolean();
    private final AtomicBoolean closed = new AtomicBoolean();

    public EncodedFramePipeline(@Nonnull FrameCodec codec, @Nonnull Consumer<EncodedFrame> consumer) {
        this.codec = Objects.requireNonNull(codec, "codec");
        this.consumer = Objects.requireNonNull(consumer, "consumer");
        this.worker = Executors.newSingleThreadExecutor(r -> {
            Thread thread =
                    new Thread(r, "cef4j-frame-codec-" + codec.descriptor().id());
            thread.setDaemon(true);
            return thread;
        });
    }

    /** Callback suitable for {@link FrameTransport#onRawFrame}. Copies because encoding is asynchronous. */
    public void submit(@Nonnull RawFrame frame) {
        if (closed.get()) return;
        latest.set(frame.snapshot());
        if (scheduled.compareAndSet(false, true)) worker.execute(this::drain);
    }

    public void requestKeyFrame() {
        codec.requestKeyFrame();
    }

    private void drain() {
        try {
            while (!closed.get()) {
                RawFrame frame = latest.getAndSet(null);
                if (frame == null) break;
                try {
                    consumer.accept(codec.encode(frame));
                } catch (IOException | RuntimeException failure) {
                    LOG.warn(
                            "frame codec {} failed for sequence={}",
                            codec.descriptor().id(),
                            frame.metadata().sourceSequence(),
                            failure);
                    codec.reset(CodecResetReason.DEPENDENCY_LOST);
                }
            }
        } finally {
            scheduled.set(false);
            if (!closed.get() && latest.get() != null && scheduled.compareAndSet(false, true))
                worker.execute(this::drain);
        }
    }

    @Override
    public void close() {
        if (!closed.compareAndSet(false, true)) return;
        latest.set(null);
        worker.shutdownNow();
        codec.close();
    }
}
