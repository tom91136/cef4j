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

@Timeout(600)
class V8EvalIntegrationTest {

    private static final RuntimeServerTestEnvironment RUNTIME = RuntimeServerTestEnvironment.require();

    @Test
    void evaluatesArithmetic() throws Exception {
        EvaluateJavascriptResponse resp = runEval("1 + 2 + 3");
        assertThat(resp.errorMessage()).isEmpty();
        assertThat(resp.valueKind()).as("integer kind").isEqualTo(2);
        assertThat(resp.intValue()).isEqualTo(6);
    }

    @Test
    void evaluatesStringExpression() throws Exception {
        EvaluateJavascriptResponse resp = runEval("'hel' + 'lo'");
        assertThat(resp.errorMessage()).isEmpty();
        assertThat(resp.valueKind()).as("string kind").isEqualTo(4);
        assertThat(resp.stringValue()).isEqualTo("hello");
    }

    @Test
    void evaluatesBoolean() throws Exception {
        EvaluateJavascriptResponse resp = runEval("3 > 2");
        assertThat(resp.errorMessage()).isEmpty();
        assertThat(resp.valueKind()).as("boolean kind").isEqualTo(1);
        assertThat(resp.boolValue()).isTrue();
    }

    @Test
    void surfacesSyntaxErrorAsErrorKind() throws Exception {
        EvaluateJavascriptResponse resp = runEval("this is not valid js !!!");
        assertThat(resp.valueKind()).as("error kind").isEqualTo(5);
        assertThat(resp.errorMessage()).isNotEmpty();
    }

    @Test
    void evaluatesObjectAsJsonStringified() throws Exception {
        EvaluateJavascriptResponse resp = runEval("({a: 1, b: 'two', c: [3, 4, 5]})");
        assertThat(resp.errorMessage()).isEmpty();
        assertThat(resp.valueKind()).isEqualTo(4);
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

            EvaluateJavascriptResponse evalResp = session.request(
                            new EvaluateJavascriptRequest(
                                    frame.handle(), "({greeting: 'hello world', count: 42})", true),
                            EvaluateJavascriptResponse.DECODER)
                    .get(10, TimeUnit.SECONDS);
            assertThat(evalResp.errorMessage()).isEmpty();
            assertThat(evalResp.valueHandle()).isPositive();

            net.kurobako.cef4j.ipc.protocol.gen.V8GetPropertyResponse propResp = session.request(
                            new net.kurobako.cef4j.ipc.protocol.gen.V8GetPropertyRequest(
                                    frame.handle(), evalResp.valueHandle(), "greeting"),
                            net.kurobako.cef4j.ipc.protocol.gen.V8GetPropertyResponse.DECODER)
                    .get(10, TimeUnit.SECONDS);
            assertThat(propResp.errorMessage()).isEmpty();
            assertThat(propResp.valueKind()).as("property string kind").isEqualTo(4);
            assertThat(propResp.stringValue()).isEqualTo("hello world");

            net.kurobako.cef4j.ipc.protocol.gen.V8GetPropertyResponse countResp = session.request(
                            new net.kurobako.cef4j.ipc.protocol.gen.V8GetPropertyRequest(
                                    frame.handle(), evalResp.valueHandle(), "count"),
                            net.kurobako.cef4j.ipc.protocol.gen.V8GetPropertyResponse.DECODER)
                    .get(10, TimeUnit.SECONDS);
            assertThat(countResp.valueKind()).isEqualTo(2);
            assertThat(countResp.intValue()).isEqualTo(42);

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
            assertThat(goneResp.valueKind()).as("released handle error kind").isEqualTo(5);
            assertThat(goneResp.errorMessage()).contains("v8 handle gone");
        }
    }

    @Test
    void executesJsFunctionFromJvmHandle() throws Exception {
        try (RuntimeServerProcess server = RUNTIME.spawn();
                CefTransport transport = server.connect();
                CefSession session = new CefSessionImpl(transport, Duration.ofSeconds(30))) {

            net.kurobako.cef4j.ipc.protocol.gen.Frame frame = setupFrame(session);

            EvaluateJavascriptResponse evalResp = session.request(
                            new EvaluateJavascriptRequest(frame.handle(), "(function(a, b) { return a + b; })", true),
                            EvaluateJavascriptResponse.DECODER)
                    .get(10, TimeUnit.SECONDS);
            assertThat(evalResp.errorMessage()).isEmpty();
            assertThat(evalResp.valueHandle()).isPositive();

            net.kurobako.cef4j.ipc.protocol.gen.V8ExecuteFunctionResponse callResp = session.request(
                            new net.kurobako.cef4j.ipc.protocol.gen.V8ExecuteFunctionRequest(
                                    frame.handle(), evalResp.valueHandle(), "[3, 4]"),
                            net.kurobako.cef4j.ipc.protocol.gen.V8ExecuteFunctionResponse.DECODER)
                    .get(10, TimeUnit.SECONDS);
            assertThat(callResp.errorMessage()).isEmpty();
            assertThat(callResp.valueKind()).as("call integer kind").isEqualTo(2);
            assertThat(callResp.intValue()).isEqualTo(7);

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
