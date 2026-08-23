package net.kurobako.cef4j.ipc.frame;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
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
    private final Object commandLock = new Object();
    private final AtomicReference<RawFrame> latest = new AtomicReference<>();
    private final AtomicReference<Thread> workerThread = new AtomicReference<>();
    private final AtomicBoolean scheduled = new AtomicBoolean();
    private final AtomicBoolean closed = new AtomicBoolean();

    public EncodedFramePipeline(@Nonnull FrameCodec codec, @Nonnull Consumer<EncodedFrame> consumer) {
        this.codec = Objects.requireNonNull(codec, "codec");
        this.consumer = Objects.requireNonNull(consumer, "consumer");
        this.worker = Executors.newSingleThreadExecutor(r -> {
            Thread thread =
                    new Thread(r, "cef4j-frame-codec-" + codec.descriptor().id());
            thread.setDaemon(true);
            workerThread.set(thread);
            return thread;
        });
    }

    /** Callback suitable for {@link FrameTransport#onRawFrame}. Copies because encoding is asynchronous. */
    public void submit(@Nonnull RawFrame frame) {
        RawFrame snapshot = frame.snapshot();
        synchronized (commandLock) {
            if (closed.get()) return;
            latest.set(snapshot);
            if (scheduled.compareAndSet(false, true)) worker.execute(this::drain);
        }
    }

    public void requestKeyFrame() {
        synchronized (commandLock) {
            if (closed.get()) return;
            worker.execute(codec::requestKeyFrame);
        }
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
            synchronized (commandLock) {
                if (!closed.get() && latest.get() != null && scheduled.compareAndSet(false, true)) {
                    worker.execute(this::drain);
                }
            }
        }
    }

    @Override
    public void close() {
        CountDownLatch codecClosed = new CountDownLatch(1);
        synchronized (commandLock) {
            if (!closed.compareAndSet(false, true)) return;
            latest.set(null);
            worker.execute(() -> {
                try {
                    codec.close();
                } finally {
                    codecClosed.countDown();
                }
            });
            worker.shutdown();
        }
        if (Thread.currentThread() == workerThread.get()) return;
        boolean interrupted = false;
        while (true) {
            try {
                codecClosed.await();
                break;
            } catch (InterruptedException ignored) {
                interrupted = true;
            }
        }
        while (true) {
            try {
                if (worker.awaitTermination(1, TimeUnit.DAYS)) break;
            } catch (InterruptedException ignored) {
                interrupted = true;
            }
        }
        if (interrupted) Thread.currentThread().interrupt();
    }
}
