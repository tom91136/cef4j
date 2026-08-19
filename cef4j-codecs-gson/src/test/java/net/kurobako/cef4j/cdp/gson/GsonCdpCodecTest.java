package net.kurobako.cef4j.cdp.gson;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.google.gson.JsonParser;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import net.kurobako.cef4j.cdp.CdpClient;
import net.kurobako.cef4j.cdp.CdpSchema;
import net.kurobako.cef4j.cdp.CdpSubscription;
import net.kurobako.cef4j.cdp.CdpTransport;
import net.kurobako.cef4j.cdp.CdpVersionMismatchException;
import net.kurobako.cef4j.cdp.generated.Runtime;
import org.junit.jupiter.api.Test;

final class GsonCdpCodecTest {
    @Test
    void typedCallUsesCodecNeutralTransport() {
        FakeTransport transport = new FakeTransport(
                "{\"result\":{\"type\":\"string\",\"value\":\"hello\"}}".getBytes(StandardCharsets.UTF_8));
        CdpClient client = new CdpClient(transport, new GsonCdpCodec());
        Runtime.EvaluateResult result = client.domains()
                .runtime()
                .evaluate(new Runtime.EvaluateRequest("'hello'").returnByValue(true))
                .toCompletableFuture()
                .join();

        assertThat(transport.method).isEqualTo("Runtime.evaluate");
        assertThat(JsonParser.parseString(new String(transport.params, StandardCharsets.UTF_8)))
                .isEqualTo(JsonParser.parseString("{\"expression\":\"'hello'\",\"returnByValue\":true}"));
        Runtime.RemoteObject remoteObject = result.result();
        assertThat(remoteObject.type()).isEqualTo(Runtime.RemoteObject.TypeValues.STRING);
        assertThat(remoteObject.value()).isEqualTo(Optional.of("hello"));
    }

    @Test
    void exactVersionCheckUsesOnlyTheExistingTransport() {
        String response = "{\"product\":\"Chrome/" + CdpSchema.chromiumVersion()
                + "\",\"protocolVersion\":\"1.3\",\"revision\":\"r\","
                + "\"userAgent\":\"ua\",\"jsVersion\":\"v8\"}";
        FakeTransport transport = new FakeTransport(response.getBytes(StandardCharsets.UTF_8));
        CdpClient client = new CdpClient(transport, new GsonCdpCodec());

        assertThat(CdpSchema.requireExactVersion(client)
                        .toCompletableFuture()
                        .join()
                        .product())
                .endsWith(CdpSchema.chromiumVersion());
        assertThat(transport.method).isEqualTo("Browser.getVersion");
        assertThat(transport.params).isNull();
    }

    @Test
    void exactVersionCheckRejectsMismatch() {
        FakeTransport transport =
                new FakeTransport("{\"product\":\"Chrome/145.0.0.0\"}".getBytes(StandardCharsets.UTF_8));
        CdpClient client = new CdpClient(transport, new GsonCdpCodec());
        assertThatThrownBy(() -> CdpSchema.requireExactVersion(client)
                        .toCompletableFuture()
                        .join())
                .isInstanceOf(CompletionException.class)
                .hasCauseInstanceOf(CdpVersionMismatchException.class);
    }

    @Test
    void typedEventsDecodeNestedValuesAndCanBeUnsubscribed() {
        FakeTransport transport = new FakeTransport(new byte[0]);
        CdpClient client = new CdpClient(transport, new GsonCdpCodec());
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
        private final byte[] response;

        @Nullable
        private String method;

        @Nullable
        private byte[] params;

        @Nullable
        private String subscribedMethod;

        @Nullable
        private Consumer<byte[]> eventHandler;

        private boolean subscriptionClosed;

        private FakeTransport(byte[] response) {
            this.response = response;
        }

        @Override
        public CompletionStage<byte[]> execute(String method, @Nullable byte[] params) {
            this.method = method;
            this.params = params;
            return CompletableFuture.completedFuture(response);
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
