package net.kurobako.cef4j.ipc.protocol.process;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import net.kurobako.cef4j.ipc.protocol.gen.EvaluateJavascriptRequest;
import net.kurobako.cef4j.ipc.protocol.gen.EvaluateJavascriptResponse;
import net.kurobako.cef4j.ipc.protocol.gen.JsFunctionCallEvent;
import net.kurobako.cef4j.ipc.protocol.gen.LifeSpanHandlerOnAfterCreatedEvent;
import net.kurobako.cef4j.ipc.protocol.gen.RegisterJsFunctionRequest;
import net.kurobako.cef4j.ipc.protocol.gen.RegisterJsFunctionResponse;
import net.kurobako.cef4j.ipc.protocol.gen.V8ContextCreatedEvent;
import net.kurobako.cef4j.ipc.session.CefSession;
import net.kurobako.cef4j.ipc.session.CefSessionImpl;
import net.kurobako.cef4j.ipc.session.JvmCallbackTable;
import net.kurobako.cef4j.ipc.session.RemoteHandle;
import net.kurobako.cef4j.ipc.session.process.RuntimeServerProcess;
import net.kurobako.cef4j.ipc.transport.CefTransport;
import net.kurobako.cef4j.test.RuntimeServerTestEnvironment;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

@Timeout(600)
class JsFunctionRegistrationTest {

    private static final RuntimeServerTestEnvironment RUNTIME = RuntimeServerTestEnvironment.require();

    @FunctionalInterface
    interface JsFunctionCallback {
        void invoke(String argsJson);
    }

    @Test
    void registeredJsFunctionFiresOnInvocation() throws Exception {
        try (RuntimeServerProcess server = RUNTIME.spawn();
                CefTransport transport = server.connect();
                CefSession session = new CefSessionImpl(transport, Duration.ofSeconds(30))) {

            JvmCallbackTable<JsFunctionCallback> jsCallbacks = new JvmCallbackTable<>();
            session.on(JsFunctionCallEvent.MESSAGE_ID, JsFunctionCallEvent.DECODER, ev -> {
                JsFunctionCallback cb = jsCallbacks.lookup(ev.callbackId());
                if (cb != null) cb.invoke(ev.argsJson());
            });

            LinkedBlockingQueue<RemoteHandle> browsers = new LinkedBlockingQueue<>();
            session.onLatest(
                    LifeSpanHandlerOnAfterCreatedEvent.MESSAGE_ID,
                    LifeSpanHandlerOnAfterCreatedEvent.DECODER,
                    ev -> browsers.offer(ev.browser()));
            LinkedBlockingQueue<V8ContextCreatedEvent> contexts = new LinkedBlockingQueue<>();
            session.on(V8ContextCreatedEvent.MESSAGE_ID, V8ContextCreatedEvent.DECODER, contexts::offer);
            RemoteHandle browser = browsers.poll(20, TimeUnit.SECONDS);
            assertThat(browser).isNotNull();
            assertThat(contexts.poll(15, TimeUnit.SECONDS)).isNotNull();
            net.kurobako.cef4j.ipc.protocol.gen.Browser facade =
                    new net.kurobako.cef4j.ipc.protocol.gen.Browser(session, browser);
            net.kurobako.cef4j.ipc.protocol.gen.Frame frame =
                    facade.getMainFrame().get(5, TimeUnit.SECONDS);

            CompletableFuture<String> received = new CompletableFuture<>();
            int callbackId = jsCallbacks.register(argsJson -> {
                if (!received.isDone()) received.complete(argsJson);
            });
            session.request(
                            new RegisterJsFunctionRequest(frame.handle(), "javaTalk", callbackId),
                            RegisterJsFunctionResponse.DECODER)
                    .get(5, TimeUnit.SECONDS);

            EvaluateJavascriptResponse evalResp = session.request(
                            new EvaluateJavascriptRequest(frame.handle(), "window.javaTalk('hi', 42, true)", false),
                            EvaluateJavascriptResponse.DECODER)
                    .get(5, TimeUnit.SECONDS);
            assertThat(evalResp.errorMessage()).isEmpty();

            String observed = received.get(10, TimeUnit.SECONDS);
            assertThat(observed).isEqualTo("[\"hi\",42,true]");
        }
    }
}
