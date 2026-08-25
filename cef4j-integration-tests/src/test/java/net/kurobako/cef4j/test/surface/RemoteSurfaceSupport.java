package net.kurobako.cef4j.test.surface;

import java.nio.ByteBuffer;
import java.time.Duration;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.kurobako.cef4j.ipc.frame.FrameMetadata;
import net.kurobako.cef4j.ipc.frame.FrameTransport;
import net.kurobako.cef4j.ipc.frame.RemoteCefBrowserBackend;
import net.kurobako.cef4j.ipc.frame.SharedFileFrameTransport;
import net.kurobako.cef4j.ipc.session.CefSession;
import net.kurobako.cef4j.ipc.session.CefSessionImpl;
import net.kurobako.cef4j.ipc.session.process.RuntimeServerProcess;
import net.kurobako.cef4j.ipc.transport.ZmqTransport;
import net.kurobako.cef4j.test.RuntimeServerTestEnvironment;
import net.kurobako.cef4j.test.TestDeadline;
import net.kurobako.cef4j.test.backend.BrowserSession;

final class RemoteSurfaceSupport {
    private RemoteSurfaceSupport() {}

    static boolean available() {
        String os = System.getProperty("os.name", "").toLowerCase(java.util.Locale.ROOT);
        boolean display =
                !os.contains("linux") || System.getenv("DISPLAY") != null || System.getenv("WAYLAND_DISPLAY") != null;
        if (display) RuntimeServerTestEnvironment.require();
        return display;
    }

    static RuntimeFixture open(Duration timeout) throws Exception {
        RuntimeServerTestEnvironment environment = RuntimeServerTestEnvironment.require();
        RuntimeServerProcess server =
                RemoteCefBrowserBackend.launchServer(environment.binary(), environment.resources(), timeout);
        ZmqTransport transport = ZmqTransport.connect(server.endpoint());
        CefSession session = new CefSessionImpl(transport, timeout);
        return new RuntimeFixture(server, transport, session);
    }

    static final class RuntimeFixture implements AutoCloseable {
        final RuntimeServerProcess server;
        final ZmqTransport transport;
        final CefSession session;

        RuntimeFixture(RuntimeServerProcess server, ZmqTransport transport, CefSession session) {
            this.server = server;
            this.transport = transport;
            this.session = session;
        }

        @Override
        public void close() {
            try {
                session.close();
            } catch (Exception ignored) {
                // Cleanup continues with independently owned resources.
            }
            try {
                transport.close();
            } catch (RuntimeException ignored) {
                // Cleanup continues with independently owned resources.
            }
            try {
                server.close();
            } catch (RuntimeException ignored) {
                // Cleanup is best effort in the disposable test process.
            }
        }
    }

    static final class FrameProbe {
        private final ArrayBlockingQueue<BrowserSession.PaintInfo> paints = new ArrayBlockingQueue<>(1);

        FrameTransport bind(CefSession session) {
            return new ProbedFrameTransport(SharedFileFrameTransport.bindAll(session), this);
        }

        BrowserSession.PaintInfo await(int width, int height, Duration timeout)
                throws InterruptedException, TimeoutException {
            TestDeadline deadline = TestDeadline.after(timeout);
            BrowserSession.PaintInfo last = null;
            while (!deadline.isExpired()) {
                BrowserSession.PaintInfo next = paints.poll(deadline.remainingNanos(), TimeUnit.NANOSECONDS);
                if (next != null) last = next;
                if (next != null && next.width == width && next.height == height) return next;
            }
            throw new TimeoutException("no " + width + "x" + height + " surface paint within " + timeout
                    + (last == null ? "" : "; last was " + last.width + "x" + last.height));
        }

        private void accept(int width, int height, ByteBuffer pixels) {
            BrowserSession.PaintInfo latest = new BrowserSession.PaintInfo(width, height, pixels.remaining());
            while (!paints.offer(latest)) paints.poll();
        }
    }

    private static final class ProbedFrameTransport implements FrameTransport {
        private final FrameTransport delegate;
        private final FrameProbe probe;

        @Nullable
        private volatile FrameConsumer consumer;

        ProbedFrameTransport(FrameTransport delegate, FrameProbe probe) {
            this.delegate = delegate;
            this.probe = probe;
            delegate.onFrame(this::dispatch);
        }

        private void dispatch(int width, int height, @Nonnull ByteBuffer pixels, @Nonnull FrameMetadata metadata) {
            probe.accept(width, height, pixels);
            FrameConsumer current = consumer;
            if (current != null) current.accept(width, height, pixels, metadata);
        }

        @Override
        public void onFrame(@Nullable FrameConsumer consumer) {
            this.consumer = consumer;
        }

        @Override
        public void close() {
            consumer = null;
            delegate.close();
        }
    }
}
