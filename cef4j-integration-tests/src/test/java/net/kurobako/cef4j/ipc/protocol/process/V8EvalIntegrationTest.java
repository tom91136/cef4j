package net.kurobako.cef4j.ipc.protocol.process;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import net.kurobako.cef4j.ipc.protocol.gen.EvaluateJavascriptRequest;
import net.kurobako.cef4j.ipc.protocol.gen.EvaluateJavascriptResponse;
import net.kurobako.cef4j.ipc.protocol.gen.LifeSpanHandlerOnAfterCreatedEvent;
import net.kurobako.cef4j.ipc.protocol.gen.V8ContextCreatedEvent;
import net.kurobako.cef4j.ipc.session.CefSession;
import net.kurobako.cef4j.ipc.session.CefSessionImpl;
import net.kurobako.cef4j.ipc.session.JsResult;
import net.kurobako.cef4j.ipc.session.RemoteHandle;
import net.kurobako.cef4j.ipc.session.process.RuntimeServerProcess;
import net.kurobako.cef4j.ipc.transport.CefTransport;
import net.kurobako.cef4j.test.RuntimeServerTestEnvironment;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

/**
 * V8 evaluation round-trip from JVM through the renderer process and back. Path:
 *
 * <ol>
 *   <li>JVM sends {@link EvaluateJavascriptRequest}{frame, code} as {@code Kind::Request}.
 *   <li>Browser-process dispatcher relays via {@code cef_process_message("v8_eval_req")} to renderer with
 *       {@code [corrId, code]} args.
 *   <li>Renderer's {@code render_process_handler::on_process_message_received} evaluates in the frame's V8 context,
 *       packs the result into {@code "v8_eval_resp"} args, and sends back to PID_BROWSER.
 *   <li>Browser-side {@code Client::on_process_message_received} decodes the args and emits {@code Kind::Response} with
 *       the original corrId and an {@link EvaluateJavascriptResponse}.
 * </ol>
 *
 * <p>This is the foundation for full V8 RMI. Method dispatch on V8 values, function callbacks (cef_v8handler_t), and
 * DOM access all layer onto the same wire shape — JVM owns the frame handle, renderer does the V8 work, results flow
 * back through process messages with corrId tracking.
 */
@Timeout(600)
class V8EvalIntegrationTest {

    private static final RuntimeServerTestEnvironment RUNTIME = RuntimeServerTestEnvironment.require();

    @Test
    void evaluatesArithmetic() throws Exception {
        EvaluateJavascriptResponse resp = runEval("1 + 2 + 3");
        assertThat(resp.errorMessage()).isEmpty();
        assertThat(resp.valueKind()).isEqualTo(2); // int
        assertThat(resp.intValue()).isEqualTo(6);
    }

    @Test
    void evaluatesStringExpression() throws Exception {
        EvaluateJavascriptResponse resp = runEval("'hel' + 'lo'");
        assertThat(resp.errorMessage()).isEmpty();
        assertThat(resp.valueKind()).isEqualTo(4); // string
        assertThat(resp.stringValue()).isEqualTo("hello");
    }

    @Test
    void evaluatesBoolean() throws Exception {
        EvaluateJavascriptResponse resp = runEval("3 > 2");
        assertThat(resp.errorMessage()).isEmpty();
        assertThat(resp.valueKind()).isEqualTo(1); // bool
        assertThat(resp.boolValue()).isTrue();
    }

    @Test
    void surfacesSyntaxErrorAsErrorKind() throws Exception {
        EvaluateJavascriptResponse resp = runEval("this is not valid js !!!");
        assertThat(resp.valueKind()).isEqualTo(5); // error
        assertThat(resp.errorMessage()).isNotEmpty();
    }

    @Test
    void evaluatesObjectAsJsonStringified() throws Exception {
        // Objects come back as a JSON.stringify'd string in the string slot — caller knows from
        // valueKind=4 that they got a string and can JSON.parse to reconstruct.
        EvaluateJavascriptResponse resp = runEval("({a: 1, b: 'two', c: [3, 4, 5]})");
        assertThat(resp.errorMessage()).isEmpty();
        assertThat(resp.valueKind()).isEqualTo(4);
        // Order of keys in JSON.stringify is insertion order — matches the literal.
        assertThat(resp.stringValue()).isEqualTo("{\"a\":1,\"b\":\"two\",\"c\":[3,4,5]}");
    }

    @Test
    void evaluatesArrayAsJsonStringified() throws Exception {
        EvaluateJavascriptResponse resp = runEval("[1, 'two', true, null]");
        assertThat(resp.errorMessage()).isEmpty();
        assertThat(resp.valueKind()).isEqualTo(4);
        assertThat(resp.stringValue()).isEqualTo("[1,\"two\",true,null]");
    }

    @Test
    void jsResultExposesTypedAccessors() throws Exception {
        // Wrap the wire response in JsResult — the ergonomic JVM-side typed accessor.
        EvaluateJavascriptResponse resp = runEval("Math.PI");
        JsResult result = JsResult.fromWire(
                resp.valueKind(),
                resp.boolValue(),
                resp.intValue(),
                resp.doubleValue(),
                resp.stringValue(),
                resp.errorMessage());
        assertThat(result.isDouble()).isTrue();
        assertThat(result.asDouble()).isCloseTo(Math.PI, org.assertj.core.data.Offset.offset(1e-12));
    }

    @Test
    void retainsObjectAsV8HandleAndDrillsIntoProperty() throws Exception {
        try (RuntimeServerProcess server = RUNTIME.spawn();
                CefTransport transport = server.connect();
                CefSession session = new CefSessionImpl(transport, Duration.ofSeconds(30))) {

            net.kurobako.cef4j.ipc.protocol.gen.Frame frame = setupFrame(session);

            // Eval with retainHandle=true: complex value comes back as a renderer-side V8 handle id.
            EvaluateJavascriptResponse evalResp = session.request(
                            new EvaluateJavascriptRequest(
                                    frame.handle(), "({greeting: 'hello world', count: 42})", true),
                            EvaluateJavascriptResponse.DECODER)
                    .get(10, TimeUnit.SECONDS);
            assertThat(evalResp.errorMessage()).isEmpty();
            assertThat(evalResp.valueHandle()).isPositive();

            // Drill: get the "greeting" property from that V8 handle.
            net.kurobako.cef4j.ipc.protocol.gen.V8GetPropertyResponse propResp = session.request(
                            new net.kurobako.cef4j.ipc.protocol.gen.V8GetPropertyRequest(
                                    frame.handle(), evalResp.valueHandle(), "greeting"),
                            net.kurobako.cef4j.ipc.protocol.gen.V8GetPropertyResponse.DECODER)
                    .get(10, TimeUnit.SECONDS);
            assertThat(propResp.errorMessage()).isEmpty();
            assertThat(propResp.valueKind()).isEqualTo(4); // string
            assertThat(propResp.stringValue()).isEqualTo("hello world");

            // Numeric property comes back as int.
            net.kurobako.cef4j.ipc.protocol.gen.V8GetPropertyResponse countResp = session.request(
                            new net.kurobako.cef4j.ipc.protocol.gen.V8GetPropertyRequest(
                                    frame.handle(), evalResp.valueHandle(), "count"),
                            net.kurobako.cef4j.ipc.protocol.gen.V8GetPropertyResponse.DECODER)
                    .get(10, TimeUnit.SECONDS);
            assertThat(countResp.valueKind()).isEqualTo(2);
            assertThat(countResp.intValue()).isEqualTo(42);

            // Release the handle. Subsequent property reads should fail with a "v8 handle gone" error.
            session.request(
                            new net.kurobako.cef4j.ipc.protocol.gen.V8ReleaseHandleRequest(
                                    frame.handle(), evalResp.valueHandle()),
                            net.kurobako.cef4j.ipc.protocol.gen.V8ReleaseHandleResponse.DECODER)
                    .get(5, TimeUnit.SECONDS);
            net.kurobako.cef4j.ipc.protocol.gen.V8GetPropertyResponse goneResp = session.request(
                            new net.kurobako.cef4j.ipc.protocol.gen.V8GetPropertyRequest(
                                    frame.handle(), evalResp.valueHandle(), "greeting"),
                            net.kurobako.cef4j.ipc.protocol.gen.V8GetPropertyResponse.DECODER)
                    .get(10, TimeUnit.SECONDS);
            assertThat(goneResp.valueKind()).isEqualTo(5); // error
            assertThat(goneResp.errorMessage()).contains("v8 handle gone");
        }
    }

    @Test
    void executesJsFunctionFromJvmHandle() throws Exception {
        try (RuntimeServerProcess server = RUNTIME.spawn();
                CefTransport transport = server.connect();
                CefSession session = new CefSessionImpl(transport, Duration.ofSeconds(30))) {

            net.kurobako.cef4j.ipc.protocol.gen.Frame frame = setupFrame(session);

            // Eval a JS function and retain it as a handle. The function takes (a, b) and returns a + b.
            EvaluateJavascriptResponse evalResp = session.request(
                            new EvaluateJavascriptRequest(frame.handle(), "(function(a, b) { return a + b; })", true),
                            EvaluateJavascriptResponse.DECODER)
                    .get(10, TimeUnit.SECONDS);
            assertThat(evalResp.errorMessage()).isEmpty();
            assertThat(evalResp.valueHandle()).isPositive();

            // Call it from JVM with [3, 4] → 7.
            net.kurobako.cef4j.ipc.protocol.gen.V8ExecuteFunctionResponse callResp = session.request(
                            new net.kurobako.cef4j.ipc.protocol.gen.V8ExecuteFunctionRequest(
                                    frame.handle(), evalResp.valueHandle(), "[3, 4]"),
                            net.kurobako.cef4j.ipc.protocol.gen.V8ExecuteFunctionResponse.DECODER)
                    .get(10, TimeUnit.SECONDS);
            assertThat(callResp.errorMessage()).isEmpty();
            assertThat(callResp.valueKind()).isEqualTo(2); // int
            assertThat(callResp.intValue()).isEqualTo(7);

            // Call again with strings — JS '+' concatenates → "hello world".
            net.kurobako.cef4j.ipc.protocol.gen.V8ExecuteFunctionResponse stringResp = session.request(
                            new net.kurobako.cef4j.ipc.protocol.gen.V8ExecuteFunctionRequest(
                                    frame.handle(), evalResp.valueHandle(), "[\"hello \", \"world\"]"),
                            net.kurobako.cef4j.ipc.protocol.gen.V8ExecuteFunctionResponse.DECODER)
                    .get(10, TimeUnit.SECONDS);
            assertThat(stringResp.valueKind()).isEqualTo(4);
            assertThat(stringResp.stringValue()).isEqualTo("hello world");
        }
    }

    @Test
    void primitiveValuesDoNotPopulateHandleEvenWhenRetainRequested() throws Exception {
        try (RuntimeServerProcess server = RUNTIME.spawn();
                CefTransport transport = server.connect();
                CefSession session = new CefSessionImpl(transport, Duration.ofSeconds(30))) {

            net.kurobako.cef4j.ipc.protocol.gen.Frame frame = setupFrame(session);

            // Even with retainHandle=true, primitives return inline — no handle slot needed.
            EvaluateJavascriptResponse resp = session.request(
                            new EvaluateJavascriptRequest(frame.handle(), "1 + 2", true),
                            EvaluateJavascriptResponse.DECODER)
                    .get(10, TimeUnit.SECONDS);
            assertThat(resp.valueKind()).isEqualTo(2);
            assertThat(resp.intValue()).isEqualTo(3);
            assertThat(resp.valueHandle()).isZero();
        }
    }

    private net.kurobako.cef4j.ipc.protocol.gen.Frame setupFrame(CefSession session) throws Exception {
        LinkedBlockingQueue<RemoteHandle> browsers = new LinkedBlockingQueue<>();
        session.onLatest(
                LifeSpanHandlerOnAfterCreatedEvent.MESSAGE_ID,
                LifeSpanHandlerOnAfterCreatedEvent.DECODER,
                ev -> browsers.offer(ev.browser()));
        LinkedBlockingQueue<V8ContextCreatedEvent> contexts = new LinkedBlockingQueue<>();
        // Browser and renderer startup can complete before this test registers its observers on a fast machine. Both
        // events describe current session state, so replay the latest value instead of losing a startup edge and
        // waiting until the session times out.
        session.onLatest(V8ContextCreatedEvent.MESSAGE_ID, V8ContextCreatedEvent.DECODER, contexts::offer);
        RemoteHandle browser = browsers.poll(45, TimeUnit.SECONDS);
        assertThat(browser).isNotNull();
        V8ContextCreatedEvent ctx = contexts.poll(15, TimeUnit.SECONDS);
        assertThat(ctx).isNotNull();
        net.kurobako.cef4j.ipc.protocol.gen.Browser facade =
                new net.kurobako.cef4j.ipc.protocol.gen.Browser(session, browser);
        return facade.getMainFrame().get(5, TimeUnit.SECONDS);
    }

    private EvaluateJavascriptResponse runEval(String code) throws Exception {
        try (RuntimeServerProcess server = RUNTIME.spawn();
                CefTransport transport = server.connect();
                CefSession session = new CefSessionImpl(transport, Duration.ofSeconds(30))) {
            net.kurobako.cef4j.ipc.protocol.gen.Frame frame = setupFrame(session);
            return session.request(
                            new EvaluateJavascriptRequest(frame.handle(), code, false),
                            EvaluateJavascriptResponse.DECODER)
                    .get(10, TimeUnit.SECONDS);
        }
    }
}
