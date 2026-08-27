package net.kurobako.cef4j.ipc.protocol.process;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import net.kurobako.cef4j.ipc.protocol.gen.EvaluateJavascriptRequest;
import net.kurobako.cef4j.ipc.protocol.gen.EvaluateJavascriptResponse;
import net.kurobako.cef4j.ipc.protocol.gen.LifeSpanHandlerOnAfterCreatedEvent;
import net.kurobako.cef4j.ipc.protocol.gen.V8ContextCreatedEvent;
import net.kurobako.cef4j.ipc.protocol.gen.V8Value;
import net.kurobako.cef4j.ipc.session.CefSession;
import net.kurobako.cef4j.ipc.session.CefSessionImpl;
import net.kurobako.cef4j.ipc.session.RemoteHandle;
import net.kurobako.cef4j.ipc.session.process.RuntimeServerProcess;
import net.kurobako.cef4j.ipc.transport.CefTransport;
import net.kurobako.cef4j.test.RuntimeServerTestEnvironment;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

@Timeout(600)
class CodegenV8DispatchTest {

    private static final RuntimeServerTestEnvironment RUNTIME = RuntimeServerTestEnvironment.require();

    private static V8Value evalToHandle(CefSession session, RemoteHandle frame, String code) throws Exception {
        EvaluateJavascriptResponse resp = session.request(
                        new EvaluateJavascriptRequest(frame, code, true), EvaluateJavascriptResponse.DECODER)
                .get(5, TimeUnit.SECONDS);
        assertThat(resp.errorMessage()).isEmpty();
        assertThat(resp.valueHandle()).isNotZero();
        return new V8Value(session, frame, new RemoteHandle(resp.valueHandle()));
    }

    @Test
    void codegenIsStringMatchesEvalKind() throws Exception {
        try (RuntimeServerProcess server = RUNTIME.spawn();
                CefTransport transport = server.connect();
                CefSession session = new CefSessionImpl(transport, Duration.ofSeconds(30))) {

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

            V8Value obj = evalToHandle(session, frame.handle(), "({a: 1, b: 'two'})");
            int isStringObj = obj.isString().get(5, TimeUnit.SECONDS);
            assertThat(isStringObj).isZero();

            V8Value fn = evalToHandle(session, frame.handle(), "(function add(a, b) { return a + b; })");
            int isFn = fn.isFunction().get(5, TimeUnit.SECONDS);
            assertThat(isFn).isEqualTo(1);
            int isObj = fn.isObject().get(5, TimeUnit.SECONDS);
            assertThat(isObj).isEqualTo(1);
        }
    }

    @Test
    void codegenDrillsIntoPropertiesAndReadsLeafValues() throws Exception {
        try (RuntimeServerProcess server = RUNTIME.spawn();
                CefTransport transport = server.connect();
                CefSession session = new CefSessionImpl(transport, Duration.ofSeconds(30))) {

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

            V8Value obj = evalToHandle(session, frame.handle(), "({greeting: 'hello world', count: 42})");

            int hasGreeting = obj.hasValueBykey("greeting").get(5, TimeUnit.SECONDS);
            assertThat(hasGreeting).isEqualTo(1);

            V8Value greeting = obj.getValueBykey("greeting").get(5, TimeUnit.SECONDS);
            assertThat(greeting.frame().id()).isEqualTo(frame.handle().id());

            int isStr = greeting.isString().get(5, TimeUnit.SECONDS);
            assertThat(isStr).isEqualTo(1);
            String text = greeting.getStringValue().get(5, TimeUnit.SECONDS);
            assertThat(text).isEqualTo("hello world");

            V8Value count = obj.getValueBykey("count").get(5, TimeUnit.SECONDS);
            int countV = count.getIntValue().get(5, TimeUnit.SECONDS);
            assertThat(countV).isEqualTo(42);

            V8Value arr = evalToHandle(session, frame.handle(), "(['a','b','c'])");
            int len = arr.getArrayLength().get(5, TimeUnit.SECONDS);
            assertThat(len).isEqualTo(3);
        }
    }

    @Test
    void rendererReleaseHandleDropsRendererTableEntry() throws Exception {
        try (RuntimeServerProcess server = RUNTIME.spawn();
                CefTransport transport = server.connect();
                CefSession session = new CefSessionImpl(transport, Duration.ofSeconds(30))) {

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

            V8Value obj = evalToHandle(session, frame.handle(), "({a: 1})");
            assertThat(obj.isObject().get(5, TimeUnit.SECONDS)).isEqualTo(1);

            obj.releaseHandle().get(5, TimeUnit.SECONDS);

            try {
                obj.isObject().get(5, TimeUnit.SECONDS);
                throw new AssertionError("expected CefRemoteException after releaseHandle");
            } catch (java.util.concurrent.ExecutionException ex) {
                assertThat(ex.getCause()).isInstanceOf(net.kurobako.cef4j.ipc.session.CefRemoteException.class);
            }
        }
    }
}
