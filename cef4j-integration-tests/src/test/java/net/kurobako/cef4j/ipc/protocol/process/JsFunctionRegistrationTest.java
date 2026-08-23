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
import net.kurobako.cef4j.ipc.transport.ZmqTransport;
import net.kurobako.cef4j.test.RuntimeServerTestEnvironment;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

/**
 * JVM-registered global JS function: page calls {@code window.<name>(args)}, JVM observes the call. Path:
 *
 * <ol>
 *   <li>JVM mints a callbackId via {@link JvmCallbackTable}, sends {@link RegisterJsFunctionRequest}.
 *   <li>Browser-process relays via {@code js_register_func_req} process_message; renderer creates a
 *       {@code cef_v8_handler_t} synthetic and installs it as a global function in the frame's V8 context.
 *   <li>JS code in the page calls {@code window.<name>(args)}; the synthetic JSON-stringifies the args (via the live
 *       context's {@code JSON.stringify}) and ships them as {@code js_function_call} process_message → browser →
 *       {@code Kind::Event(JsFunctionCallEvent)} → JVM.
 *   <li>JVM dispatches by callbackId into the Java handler.
 * </ol>
 *
 * <p>Fire-and-forget for v1 — JS sees {@code undefined} as the return value. Sync return support would require blocking
 * the renderer's V8 thread on a process_message round-trip, deferred.
 */
@Timeout(600)
class JsFunctionRegistrationTest {

    private static final RuntimeServerTestEnvironment RUNTIME = RuntimeServerTestEnvironment.require();

    /** Functional callback: receives the JSON.stringify'd args array. */
    @FunctionalInterface
    interface JsFunctionCallback {
        void invoke(String argsJson);
    }

    @Test
    void registeredJsFunctionFiresOnInvocation() throws Exception {
        try (RuntimeServerProcess server = RUNTIME.spawn();
                ZmqTransport transport = ZmqTransport.connect(server.endpoint());
                CefSession session = new CefSessionImpl(transport, Duration.ofSeconds(30))) {

            // Wire JsFunctionCallEvent → callbackId → registered Java callback. Mirrors the visitor
            // codegen pattern from #77 (JvmCallbackTable holds Java handlers, event arrives with id).
            JvmCallbackTable<JsFunctionCallback> jsCallbacks = new JvmCallbackTable<>();
            session.on(JsFunctionCallEvent.MESSAGE_ID, JsFunctionCallEvent.DECODER, ev -> {
                JsFunctionCallback cb = jsCallbacks.lookup(ev.callbackId());
                if (cb != null) cb.invoke(ev.argsJson());
            });

            // Setup: bootstrap browser + V8 context.
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

            // Register: when the page calls window.javaTalk(...args), the renderer's synthetic V8 handler
            // ships args back as JsFunctionCallEvent with the callbackId we mint here.
            CompletableFuture<String> received = new CompletableFuture<>();
            int callbackId = jsCallbacks.register(argsJson -> {
                if (!received.isDone()) received.complete(argsJson);
            });
            session.request(
                            new RegisterJsFunctionRequest(frame.handle(), "javaTalk", callbackId),
                            RegisterJsFunctionResponse.DECODER)
                    .get(5, TimeUnit.SECONDS);

            // Trigger from JS: evaluate code that calls window.javaTalk('hi', 42, true). Eval returns
            // undefined (the function is fire-and-forget) but the call fires the JsFunctionCallEvent.
            EvaluateJavascriptResponse evalResp = session.request(
                            new EvaluateJavascriptRequest(
                                    frame.handle(), "window.javaTalk('hi', 42, true)", /*retainHandle=*/ false),
                            EvaluateJavascriptResponse.DECODER)
                    .get(5, TimeUnit.SECONDS);
            assertThat(evalResp.errorMessage()).isEmpty();

            String observed = received.get(10, TimeUnit.SECONDS);
            // Args were JSON.stringify'd as an array.
            assertThat(observed).isEqualTo("[\"hi\",42,true]");
        }
    }
}
