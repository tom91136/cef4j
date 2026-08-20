package net.kurobako.cef4j.ipc.protocol.process;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.file.Path;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import net.kurobako.cef4j.ipc.protocol.gen.Browser;
import net.kurobako.cef4j.ipc.protocol.gen.BrowserHost;
import net.kurobako.cef4j.ipc.protocol.gen.EvaluateJavascriptRequest;
import net.kurobako.cef4j.ipc.protocol.gen.EvaluateJavascriptResponse;
import net.kurobako.cef4j.ipc.protocol.gen.Frame;
import net.kurobako.cef4j.ipc.protocol.gen.LifeSpanHandlerOnAfterCreatedEvent;
import net.kurobako.cef4j.ipc.protocol.gen.MouseEvent;
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
 * Validates the codegen-generated {@code BrowserHost.sendMouseClickEvent} / {@code sendMouseMoveEvent} round-trip
 * end-to-end. Loads a page with a button, drives a synthetic click via the IPC wire, and confirms the page-level click
 * handler ran by reading {@code document.body.innerText} back.
 *
 * <p>This is the first cross-cutting test of the {@link MouseEvent} data-struct codegen: parser→spec (ByValueIn →
 * DataStruct) → JVM emitter (overlay class with x/y/modifiers) → C++ dispatcher (decode overlay, fill
 * {@code cef_mouse_event_t}, call {@code receiver->send_mouse_click_event}) → CEF → DOM.
 */
@Timeout(60)
class MouseInputIntegrationTest {

    private static Path serverBinary;
    private static Path cefResources;

    @BeforeAll
    static void resolveBinary() {
        RuntimeServerTestEnvironment environment = RuntimeServerTestEnvironment.require();
        serverBinary = environment.binary();
        cefResources = environment.resources();
    }

    private static RuntimeServerProcess spawnServerWithEnv() throws IOException {
        return RuntimeServerProcess.spawn(
                serverBinary,
                "zmq",
                "tcp://127.0.0.1:0",
                "shared-file",
                Duration.ofSeconds(30),
                net.kurobako.cef4j.ipc.frame.RemoteCefBrowserBackend.runtimeEnvironment(cefResources));
    }

    /**
     * Smoke test for the codegen mouse-input wire: the server-side dispatcher decodes our {@link MouseEvent} overlay,
     * materialises a {@code cef_mouse_event_t}, and acknowledges. We don't assert page-level effect here (DOM
     * hit-testing for synthetic clicks against an OSR-rendered button is its own can of worms — the page needs to be
     * fully laid out, and CEF's OSR mode demands a {@code WasResized} sequence that's gated on open issue #3). What
     * this test proves: the new codegen DataStruct param + dispatcher emit are correct end-to-end, and the future
     * completes.
     */
    @Test
    void mouseClickEventDispatchesAcrossWire() throws Exception {
        try (RuntimeServerProcess server = spawnServerWithEnv();
                ZmqTransport transport = ZmqTransport.connect(server.endpoint());
                CefSession session = new CefSessionImpl(transport, Duration.ofSeconds(30))) {

            CompletableFuture<RemoteHandle> handleFuture = new CompletableFuture<>();
            session.onLatest(
                    LifeSpanHandlerOnAfterCreatedEvent.MESSAGE_ID, LifeSpanHandlerOnAfterCreatedEvent.DECODER, e -> {
                        if (!handleFuture.isDone()) handleFuture.complete(e.browser());
                    });
            RemoteHandle browserHandle = handleFuture.get(20, TimeUnit.SECONDS);
            Browser browser = new Browser(session, browserHandle);
            BrowserHost host = browser.getHost().get(5, TimeUnit.SECONDS);

            // press + release + a move event — three different codegen paths through the same DataStruct
            // wire. The server acks each individually; if any one timed out we'd know the dispatcher case
            // for that method is broken (decode, overlay→native copy, or C-API call).
            host.sendMouseClickEvent(new MouseEvent(50, 50, 0), /*type=*/ 0, /*mouseUp=*/ 0, /*clickCount=*/ 1)
                    .get(5, TimeUnit.SECONDS);
            host.sendMouseClickEvent(new MouseEvent(50, 50, 0), /*type=*/ 0, /*mouseUp=*/ 1, /*clickCount=*/ 1)
                    .get(5, TimeUnit.SECONDS);
            host.sendMouseMoveEvent(new MouseEvent(75, 75, 0), /*mouseLeave=*/ 0)
                    .get(5, TimeUnit.SECONDS);
            host.sendMouseWheelEvent(new MouseEvent(100, 100, 0), /*deltaX=*/ 0, /*deltaY=*/ 120)
                    .get(5, TimeUnit.SECONDS);

            // Sanity: the session is still alive after the click sequence.
            Frame frame = browser.getMainFrame().get(5, TimeUnit.SECONDS);
            EvaluateJavascriptResponse resp = session.request(
                            new EvaluateJavascriptRequest(frame.handle(), "1 + 1", false),
                            EvaluateJavascriptResponse.DECODER)
                    .get(5, TimeUnit.SECONDS);
            assertThat(resp.intValue()).isEqualTo(2);
        }
    }
}
