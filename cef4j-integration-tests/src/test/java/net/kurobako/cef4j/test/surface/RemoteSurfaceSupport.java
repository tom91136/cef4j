package net.kurobako.cef4j.test.surface;

import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
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
import net.kurobako.cef4j.test.backend.BrowserSession;

final class RemoteSurfaceSupport {
    private RemoteSurfaceSupport() {}

    static boolean available() {
        String binary = System.getProperty("cef4j.runtime.server.binary");
        String resources = System.getProperty("cef4j.runtime.server.resources");
        if (binary == null || resources == null) return false;
        if (!Files.isExecutable(Paths.get(binary)) || !Files.isDirectory(Paths.get(resources))) return false;
        String os = System.getProperty("os.name", "").toLowerCase(java.util.Locale.ROOT);
        return !os.contains("linux") || System.getenv("DISPLAY") != null || System.getenv("WAYLAND_DISPLAY") != null;
    }

    static RuntimeFixture open(Duration timeout) throws Exception {
        java.nio.file.Path binary = Paths.get(System.getProperty("cef4j.runtime.server.binary"));
        java.nio.file.Path resources = Paths.get(System.getProperty("cef4j.runtime.server.resources"));
        RuntimeServerProcess server = RemoteCefBrowserBackend.launchServer(binary, resources, timeout);
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
                // Continue releasing independently owned resources.
            }
            try {
                transport.close();
            } catch (RuntimeException ignored) {
                // Continue releasing independently owned resources.
            }
            try {
                server.close();
            } catch (RuntimeException ignored) {
                // Best-effort teardown in a disposable test process.
            }
        }
    }

    static final class FrameProbe {
        private final LinkedBlockingQueue<BrowserSession.PaintInfo> paints = new LinkedBlockingQueue<>();

        FrameTransport bind(CefSession session) {
            return new ProbedFrameTransport(SharedFileFrameTransport.bindAll(session), this);
        }

        BrowserSession.PaintInfo await(int width, int height, Duration timeout) throws InterruptedException {
            long deadline = System.nanoTime() + timeout.toNanos();
            BrowserSession.PaintInfo last = null;
            while (System.nanoTime() < deadline) {
                long remaining = Math.max(1L, deadline - System.nanoTime());
                BrowserSession.PaintInfo next = paints.poll(remaining, TimeUnit.NANOSECONDS);
                if (next != null) last = next;
                if (next != null && next.width == width && next.height == height) return next;
            }
            throw new InterruptedException("no " + width + "x" + height + " surface paint within " + timeout
                    + (last == null ? "" : "; last was " + last.width + "x" + last.height));
        }

        private void accept(int width, int height, ByteBuffer pixels) {
            paints.offer(new BrowserSession.PaintInfo(width, height, pixels.remaining()));
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
