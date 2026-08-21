package net.kurobako.cef4j.ipc.protocol.process;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.file.Path;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import net.kurobako.cef4j.ipc.protocol.gen.Browser;
import net.kurobako.cef4j.ipc.protocol.gen.CefStringVisitor;
import net.kurobako.cef4j.ipc.protocol.gen.Frame;
import net.kurobako.cef4j.ipc.protocol.gen.LifeSpanHandlerOnAfterCreatedEvent;
import net.kurobako.cef4j.ipc.session.CefSession;
import net.kurobako.cef4j.ipc.session.CefSessionImpl;
import net.kurobako.cef4j.ipc.session.JvmCallbackTable;
import net.kurobako.cef4j.ipc.session.RemoteHandle;
import net.kurobako.cef4j.ipc.session.process.RuntimeServerProcess;
import net.kurobako.cef4j.ipc.transport.ZmqTransport;
import net.kurobako.cef4j.test.RuntimeServerTestEnvironment;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

/**
 * Validates the visitor-callback round trip — JVM provides the callback object, server invokes it. Pattern:
 *
 * <ol>
 *   <li>JVM registers a {@link Consumer Consumer&lt;String&gt;} in a {@link JvmCallbackTable}, gets back an int32 id.
 *   <li>JVM calls {@code frame.getSource(new RemoteHandle(callbackId))} — the int32 visitor handle is the JVM callback
 *       id, NOT a server-side facade handle.
 *   <li>Runtime server's hand-written dispatcher case for {@code FrameGetSourceRequest} synthesises a
 *       {@link net.kurobako.cef4j.ipc.session.RemoteHandle JvmStringVisitor} bound to that callbackId and passes it to
 *       {@code cef_frame_t::get_source}.
 *   <li>CEF retrieves the page source asynchronously, then calls {@code visitor->visit(text)} on the server.
 *   <li>Runtime server-side {@code visit()} encodes a {@link StringVisitorCallbackEvent} carrying {@code callbackId} +
 *       {@code text} and ships it as {@code Kind::Event}.
 *   <li>JVM dispatches by {@code callbackId} into the Java callback. Test asserts the expected text arrived.
 * </ol>
 *
 * <p>This is the inverse of the facade-handle pattern: facade handles flow server→JVM, visitor handles flow JVM→server.
 * With this end-to-end pattern proven, follow-up codegen can generate the JVM-side {@code CefStringVisitor} interface,
 * the {@code RegisterStringVisitor} entry point, and the server-side synthetic for every visitor type CEF defines.
 */
@Timeout(240)
class VisitorCallbackIntegrationTest {

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

    @Test
    void getSourceVisitorRoundTrip() throws Exception {
        try (RuntimeServerProcess server = spawnServerWithEnv();
                ZmqTransport transport = ZmqTransport.connect(server.endpoint());
                CefSession session = new CefSessionImpl(transport, Duration.ofSeconds(30))) {

            // Wire visitor routing once per session via the codegen-emitted server. CefStringVisitor.route()
            // subscribes the matching XxxCallbackEvent and dispatches by callbackId into the supplied table.
            JvmCallbackTable<CefStringVisitor> visitors = new JvmCallbackTable<>();
            CefStringVisitor.route(session, visitors);

            CompletableFuture<RemoteHandle> handleFuture = new CompletableFuture<>();
            session.onLatest(
                    LifeSpanHandlerOnAfterCreatedEvent.MESSAGE_ID, LifeSpanHandlerOnAfterCreatedEvent.DECODER, ev -> {
                        if (!handleFuture.isDone()) handleFuture.complete(ev.browser());
                    });

            RemoteHandle browserHandle = handleFuture.get(20, TimeUnit.SECONDS);
            Browser browser = new Browser(session, browserHandle);

            // Navigate to a deterministic page so getSource returns predictable HTML.
            String dataUrl = "data:text/html,<html><body>RMI-callback-marker-9341</body></html>";
            Frame mainFrame = browser.getMainFrame().get(5, TimeUnit.SECONDS);
            mainFrame.loadUrl(dataUrl).get(5, TimeUnit.SECONDS);

            // CEF can expose the target URL before its renderer has committed the document. Windows CI
            // reliably observed an empty first source in that window, so retry the complete visitor
            // round-trip rather than treating URL visibility as document readiness.
            String observed = "";
            long deadline = System.nanoTime() + Duration.ofSeconds(20).toNanos();
            while (System.nanoTime() < deadline && !observed.contains("RMI-callback-marker-9341")) {
                CompletableFuture<String> sourceFuture = new CompletableFuture<>();
                CefStringVisitor visitor = text -> {
                    if (!sourceFuture.isDone()) sourceFuture.complete(text);
                };
                int callbackId = visitors.register(visitor);
                try {
                    Frame currentFrame = browser.getMainFrame().get(5, TimeUnit.SECONDS);
                    currentFrame.getSource(new RemoteHandle(callbackId)).get(5, TimeUnit.SECONDS);
                    try {
                        observed = sourceFuture.get(5, TimeUnit.SECONDS);
                    } catch (TimeoutException ignored) {
                        observed = "";
                    }
                } finally {
                    // Belt-and-braces: in real code the visitor's first call would auto-release; tests cleanup
                    // explicitly to keep the table tidy regardless of whether visit() fired.
                    visitors.release(callbackId);
                }
                if (!observed.contains("RMI-callback-marker-9341")) Thread.sleep(100);
            }
            assertThat(observed).contains("RMI-callback-marker-9341");
        }
    }
}
