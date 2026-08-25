package net.kurobako.cef4j.webdriver.inprocess;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.CompletableFuture;
import net.kurobako.cef4j.webdriver.JsonElement;
import net.kurobako.cef4j.webdriver.JsonObject;
import net.kurobako.cef4j.webdriver.WebDriverJsonCodec;
import org.junit.jupiter.api.Test;

class InProcessCefAutomationBackendFactoryTest {
    @Test
    void cancellationReachesRuntimeCreation() {
        CompletableFuture<InProcessBrowserRuntime> runtime = new CompletableFuture<>();
        InProcessCefAutomationBackendFactory factory =
                new InProcessCefAutomationBackendFactory(() -> runtime, new UnusedJsonCodec());

        factory.create(new JsonObject()).cancel(true);

        assertThat(runtime).isCancelled();
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
}
