package net.kurobako.cef4j.cdp.jackson;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import net.kurobako.cef4j.cdp.CdpClient;
import net.kurobako.cef4j.cdp.CdpSubscription;
import net.kurobako.cef4j.cdp.CdpTransport;
import net.kurobako.cef4j.cdp.generated.Runtime;
import org.junit.jupiter.api.Test;

final class JacksonCdpCodecTest {
    @Test
    void returnsOnlyJsonCompatibleJdkValues() {
        JacksonCdpCodec codec = new JacksonCdpCodec();
        Object decoded = codec.decode(codec.encode(Map.of("name", "cef4j", "values", List.of(1, true))));
        assertThat(decoded).isInstanceOf(Map.class);
        assertThat(((Map<?, ?>) decoded).get("values")).isInstanceOf(List.class);
    }

    @Test
    void typedEventsDecodeNestedValuesAndCanBeUnsubscribed() {
        FakeTransport transport = new FakeTransport();
        CdpClient client = new CdpClient(transport, new JacksonCdpCodec());
        List<Runtime.ConsoleAPICalledEvent> events = new ArrayList<>();

        CdpSubscription subscription = client.domains().runtime().onConsoleAPICalled(events::add);
        transport.emit("{\"type\":\"log\",\"args\":[{\"type\":\"string\",\"value\":\"ready\"}],"
                + "\"executionContextId\":7,\"timestamp\":12.5}");

        assertThat(transport.subscribedMethod).isEqualTo("Runtime.consoleAPICalled");
        assertThat(events).singleElement().satisfies(event -> {
            assertThat(event.type()).isEqualTo(Runtime.ConsoleAPICalledEvent.TypeValues.LOG);
            assertThat(event.executionContextId()).isEqualTo(new Runtime.ExecutionContextId(7));
            assertThat(event.args()).singleElement().satisfies(argument -> {
                assertThat(argument.type()).isEqualTo(Runtime.RemoteObject.TypeValues.STRING);
                assertThat(argument.value()).isEqualTo(Optional.of("ready"));
            });
        });

        subscription.close();
        assertThat(transport.subscriptionClosed).isTrue();
    }

    private static final class FakeTransport implements CdpTransport {
        @Nullable
        private String subscribedMethod;

        @Nullable
        private Consumer<byte[]> eventHandler;

        private boolean subscriptionClosed;

        @Override
        public CompletionStage<byte[]> execute(String method, @Nullable byte[] params) {
            return CompletableFuture.completedFuture(new byte[0]);
        }

        @Override
        public CdpSubscription subscribe(String method, Consumer<byte[]> handler) {
            subscribedMethod = method;
            eventHandler = handler;
            return () -> subscriptionClosed = true;
        }

        private void emit(String json) {
            Objects.requireNonNull(eventHandler).accept(json.getBytes(StandardCharsets.UTF_8));
        }
    }
}
