package net.kurobako.cef4j.ipc.protocol.process;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.file.Path;
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
import net.kurobako.cef4j.ipc.transport.ZmqTransport;
import net.kurobako.cef4j.test.RuntimeServerTestEnvironment;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

/**
 * End-to-end smoke test for the codegen V8Value facade. Exercises the renderer-dispatch path:
 *
 * <ol>
 *   <li>JVM evaluates JS via hand-written {@link EvaluateJavascriptRequest} with retainHandle=true; server returns a
 *       renderer-side V8 handle (via tables::v8Value).
 *   <li>JVM constructs a codegen-generated {@link V8Value} facade carrying (session, frame, handle).
 *   <li>JVM calls a facade method (e.g. isString, getStringValue). The Request ID is renderer-affinity, so the
 *       browser-side Dispatcher.h relays it via {@code cef4j_renderer_req}; the renderer-side RendererDispatcher.h
 *       decodes, looks up the receiver in tables::v8Value, enters the V8 context, calls the C-API method, ships the
 *       response back via {@code cef4j_renderer_resp}; the browser translates that to Kind::Response on the JVM corrId.
 * </ol>
 */
@Timeout(120)
class CodegenV8DispatchTest {

    private static Path serverBinary;
    private static Path cefResources;

    @BeforeAll
    static void resolveBinary() {
        RuntimeServerTestEnvironment environment = RuntimeServerTestEnvironment.require();
        serverBinary = environment.binary();
        cefResources = environment.resources();
    }

    private static RuntimeServerProcess spawnServerWithEnv() throws IOException {
        return spawnPackagedServer();
    }

    private static RuntimeServerProcess spawnPackagedServer() throws IOException {
        return RuntimeServerProcess.spawn(
                serverBinary,
                "zmq",
                "tcp://127.0.0.1:0",
                "shared-file",
                Duration.ofSeconds(30),
                net.kurobako.cef4j.ipc.frame.RemoteCefBrowserBackend.runtimeEnvironment(cefResources));
    }

    private static V8Value evalToHandle(CefSession session, RemoteHandle frame, String code) throws Exception {
        EvaluateJavascriptResponse resp = session.request(
                        new EvaluateJavascriptRequest(frame, code, /*retainHandle=*/ true),
                        EvaluateJavascriptResponse.DECODER)
                .get(5, TimeUnit.SECONDS);
        assertThat(resp.errorMessage()).isEmpty();
        assertThat(resp.valueHandle()).isNotZero();
        return new V8Value(session, frame, new RemoteHandle(resp.valueHandle()));
    }

    @Test
    void codegenIsStringMatchesEvalKind() throws Exception {
        try (RuntimeServerProcess server = spawnServerWithEnv();
                ZmqTransport transport = ZmqTransport.connect(server.endpoint());
                CefSession session = new CefSessionImpl(transport, Duration.ofSeconds(30))) {

            // Bootstrap: wait for an after-created browser + V8 context.
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

            // Returning an object — V8 handle is retained, V8Value.isString() expected to be 0.
            V8Value obj = evalToHandle(session, frame.handle(), "({a: 1, b: 'two'})");
            int isStringObj = obj.isString().get(5, TimeUnit.SECONDS);
            assertThat(isStringObj).isZero();

            // Returning a function — isFunction() should be 1, isObject() should be 1 too (functions are objects).
            V8Value fn = evalToHandle(session, frame.handle(), "(function add(a, b) { return a + b; })");
            int isFn = fn.isFunction().get(5, TimeUnit.SECONDS);
            assertThat(isFn).isEqualTo(1);
            int isObj = fn.isObject().get(5, TimeUnit.SECONDS);
            assertThat(isObj).isEqualTo(1);
        }
    }

    @Test
    void codegenDrillsIntoPropertiesAndReadsLeafValues() throws Exception {
        try (RuntimeServerProcess server = spawnServerWithEnv();
                ZmqTransport transport = ZmqTransport.connect(server.endpoint());
                CefSession session = new CefSessionImpl(transport, Duration.ofSeconds(30))) {

            // Same bootstrap dance as the first test.
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

            // Object with a nested string. Walk: obj.greeting -> string, then read string value.
            V8Value obj = evalToHandle(session, frame.handle(), "({greeting: 'hello world', count: 42})");

            // hasValueBykey returns 1 for an existing key.
            int hasGreeting = obj.hasValueBykey("greeting").get(5, TimeUnit.SECONDS);
            assertThat(hasGreeting).isEqualTo(1);

            // getValueBykey returns a child V8Value carrying the same frame.
            V8Value greeting = obj.getValueBykey("greeting").get(5, TimeUnit.SECONDS);
            assertThat(greeting.frame().id()).isEqualTo(frame.handle().id());

            // Confirm the child is a string and read its UTF-8 value.
            int isStr = greeting.isString().get(5, TimeUnit.SECONDS);
            assertThat(isStr).isEqualTo(1);
            String text = greeting.getStringValue().get(5, TimeUnit.SECONDS);
            assertThat(text).isEqualTo("hello world");

            // Numeric child — int value comes back via getIntValue.
            V8Value count = obj.getValueBykey("count").get(5, TimeUnit.SECONDS);
            int countV = count.getIntValue().get(5, TimeUnit.SECONDS);
            assertThat(countV).isEqualTo(42);

            // Array with three elements — getArrayLength reflects it.
            V8Value arr = evalToHandle(session, frame.handle(), "(['a','b','c'])");
            int len = arr.getArrayLength().get(5, TimeUnit.SECONDS);
            assertThat(len).isEqualTo(3);
        }
    }

    @Test
    void rendererReleaseHandleDropsRendererTableEntry() throws Exception {
        try (RuntimeServerProcess server = spawnServerWithEnv();
                ZmqTransport transport = ZmqTransport.connect(server.endpoint());
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
            // Sanity: the handle is live before release — isObject returns 1.
            assertThat(obj.isObject().get(5, TimeUnit.SECONDS)).isEqualTo(1);

            // releaseHandle is wired through RendererReleaseHandleRequest; the renderer-side server turns
            // it into gendisp::dispatchRelease, dropping the entry from tables::v8Value in the renderer.
            obj.releaseHandle().get(5, TimeUnit.SECONDS);

            // After release the server should report ReceiverGone — a follow-up codegen call sees no entry
            // in the renderer's table and the relay maps it to Kind::Error → CefRemoteException.
            try {
                obj.isObject().get(5, TimeUnit.SECONDS);
                throw new AssertionError("expected CefRemoteException after releaseHandle");
            } catch (java.util.concurrent.ExecutionException ex) {
                assertThat(ex.getCause()).isInstanceOf(net.kurobako.cef4j.ipc.session.CefRemoteException.class);
            }
        }
    }
}
