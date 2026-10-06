package net.kurobako.cef4j.webdriver.inprocess;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.CompletableFuture;
import net.kurobako.cef4j.cdp.CdpCodec;
import net.kurobako.cef4j.webdriver.JsonObject;
import net.kurobako.cef4j.webdriver.WebDriverJsonCodec;
import org.junit.jupiter.api.Test;

class InProcessCefAutomationBackendFactoryTest {
    @Test
    void cancellationReachesRuntimeCreation() {
        CompletableFuture<InProcessBrowserRuntime> runtime = new CompletableFuture<>();
        InProcessCefAutomationBackendFactory factory =
                new InProcessCefAutomationBackendFactory(() -> runtime, new WebDriverJsonCodec(new UnusedCodec()));

        factory.create(new JsonObject()).cancel(true);

        assertThat(runtime).isCancelled();
    }

    private static final class UnusedCodec implements CdpCodec {
        @Override
        public byte[] encode(Object value) {
            throw new AssertionError("unexpected encode");
        }

        @Override
        public Object decode(byte[] json) {
            throw new AssertionError("unexpected decode");
        }
    }
}
