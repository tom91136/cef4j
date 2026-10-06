package net.kurobako.cef4j.webdriver.remote;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nullable;
import net.kurobako.cef4j.cdp.CdpSubscription;
import net.kurobako.cef4j.cdp.CdpTransport;
import net.kurobako.cef4j.remote.RemoteBrowserRuntime;
import net.kurobako.cef4j.remote.RemoteBrowserRuntimeFactory;
import net.kurobako.cef4j.webdriver.JsonElement;
import net.kurobako.cef4j.webdriver.WebDriverJsonCodec;
import org.junit.jupiter.api.Test;

class RemoteCefAutomationBackendFactoryTest {
    @Test
    void cancellationReachesRuntimeCreation() {
        CompletableFuture<RemoteBrowserRuntime> runtime = new CompletableFuture<>();
        RemoteBrowserRuntimeFactory runtimes = () -> runtime;
        RemoteCefAutomationBackendFactory factory = new RemoteCefAutomationBackendFactory(
                runtimes,
                (session, browser, host) -> CompletableFuture.failedFuture(new AssertionError("unexpected attach")),
                new UnusedJsonCodec());

        factory.create(new net.kurobako.cef4j.webdriver.JsonObject()).cancel(true);

        assertThat(runtime).isCancelled();
    }

    @Test
    void closesRuntimeWhenDevToolsDetachNeverAcknowledges() {
        AtomicBoolean runtimeClosed = new AtomicBoolean();
        long started = System.nanoTime();

        RemoteCefAutomationBackendFactory.closeDevToolsThenRuntime(
                new NeverClosingDevTools(), new TestRuntime(runtimeClosed), Duration.ofMillis(50), ignored -> {});

        assertThat(runtimeClosed).isTrue();
        assertThat(Duration.ofNanos(System.nanoTime() - started)).isLessThan(Duration.ofSeconds(2));
    }

    private static final class NeverClosingDevTools implements CdpTransport {
        @Override
        public CompletableFuture<byte[]> execute(String method, @Nullable byte[] params) {
            throw new UnsupportedOperationException();
        }

        @Override
        public CdpSubscription subscribe(String method, java.util.function.Consumer<byte[]> handler) {
            throw new UnsupportedOperationException();
        }

        @Override
        public CompletableFuture<Void> closeAsync() {
            return new CompletableFuture<>();
        }
    }

    private static final class UnusedJsonCodec implements WebDriverJsonCodec {
        @Override
        public JsonElement decode(byte[] json) {
            throw new AssertionError("unexpected decode");
        }

        @Override
        public byte[] encode(JsonElement value) {
            throw new AssertionError("unexpected encode");
        }
    }

    private static final class TestRuntime implements RemoteBrowserRuntime {
        private final AtomicBoolean closed;

        private TestRuntime(AtomicBoolean closed) {
            this.closed = closed;
        }

        @Override
        public net.kurobako.cef4j.ipc.session.CefSession session() {
            throw new UnsupportedOperationException();
        }

        @Override
        public net.kurobako.cef4j.ipc.session.RemoteHandle browser() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void close() {
            closed.set(true);
        }
    }
}
