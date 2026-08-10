package net.kurobako.cef4j.ipc.protocol.process;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import net.kurobako.cef4j.ipc.protocol.gen.Browser;
import net.kurobako.cef4j.ipc.protocol.gen.CefStringVisitor;
import net.kurobako.cef4j.ipc.protocol.gen.Frame;
import net.kurobako.cef4j.ipc.protocol.gen.LifeSpanHandlerOnAfterCreatedEvent;
import net.kurobako.cef4j.ipc.session.CefSession;
import net.kurobako.cef4j.ipc.session.CefSessionImpl;
import net.kurobako.cef4j.ipc.session.JvmCallbackTable;
import net.kurobako.cef4j.ipc.session.RemoteHandle;
import net.kurobako.cef4j.ipc.session.process.HelperProcess;
import net.kurobako.cef4j.ipc.transport.ZmqTransport;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

/**
 * Validates the visitor-callback round trip — JVM provides the callback object, helper invokes it. Pattern:
 *
 * <ol>
 *   <li>JVM registers a {@link Consumer Consumer&lt;String&gt;} in a {@link JvmCallbackTable}, gets back an int32 id.
 *   <li>JVM calls {@code frame.getSource(new RemoteHandle(callbackId))} — the int32 visitor handle is the JVM callback
 *       id, NOT a helper-side facade handle.
 *   <li>Helper's hand-written dispatcher case for {@code FrameGetSourceRequest} synthesises a
 *       {@link net.kurobako.cef4j.ipc.session.RemoteHandle JvmStringVisitor} bound to that callbackId and passes it to
 *       {@code cef_frame_t::get_source}.
 *   <li>CEF retrieves the page source asynchronously, then calls {@code visitor->visit(text)} on the helper.
 *   <li>Helper-side {@code visit()} encodes a {@link StringVisitorCallbackEvent} carrying {@code callbackId} +
 *       {@code text} and ships it as {@code Kind::Event}.
 *   <li>JVM dispatches by {@code callbackId} into the Java callback. Test asserts the expected text arrived.
 * </ol>
 *
 * <p>This is the inverse of the facade-handle pattern: facade handles flow helper→JVM, visitor handles flow JVM→helper.
 * With this end-to-end pattern proven, follow-up codegen can generate the JVM-side {@code CefStringVisitor} interface,
 * the {@code RegisterStringVisitor} entry point, and the helper-side synthetic for every visitor type CEF defines.
 */
@Timeout(60)
class VisitorCallbackIntegrationTest {

    private static Path helperBinary;
    private static Path cefResources;

    @BeforeAll
    static void resolveBinary() {
        String bin = System.getProperty("cef4j.ipc.helper.binary");
        String res = System.getProperty("cef4j.ipc.cef.resources");
        Assumptions.assumeTrue(bin != null, "cef4j.ipc.helper.binary system property not set");
        Assumptions.assumeTrue(res != null, "cef4j.ipc.cef.resources system property not set");
        helperBinary = Paths.get(bin);
        cefResources = Paths.get(res);
        Assumptions.assumeTrue(Files.isExecutable(helperBinary), "helper binary not built at " + helperBinary);
        Assumptions.assumeTrue(Files.isDirectory(cefResources), "CEF resources dir missing at " + cefResources);
    }

    private static HelperProcess spawnHelperWithEnv() throws IOException {
        Path tmpDir = Files.createTempDirectory("cef4j-helper-launcher");
        Path script = tmpDir.resolve("helper-launch.sh");
        String content = "#!/bin/sh\n"
                + "export CEF_RESOURCES_DIR=\"" + cefResources + "\"\n"
                + "export LD_LIBRARY_PATH=\"" + cefResources + ":${LD_LIBRARY_PATH:-}\"\n"
                + "exec \"" + helperBinary + "\" \"$@\"\n";
        Files.writeString(script, content);
        script.toFile().setExecutable(true);
        return HelperProcess.spawn(script, "tcp://127.0.0.1:0", Duration.ofSeconds(30));
    }

    @Test
    void getSourceVisitorRoundTrip() throws Exception {
        try (HelperProcess helper = spawnHelperWithEnv();
                ZmqTransport transport = ZmqTransport.connect(helper.endpoint());
                CefSession session = new CefSessionImpl(transport, Duration.ofSeconds(10))) {

            // Wire visitor routing once per session via the codegen-emitted helper. CefStringVisitor.route()
            // subscribes the matching XxxCallbackEvent and dispatches by callbackId into the supplied table.
            JvmCallbackTable<CefStringVisitor> visitors = new JvmCallbackTable<>();
            CefStringVisitor.route(session, visitors);

            CompletableFuture<RemoteHandle> handleFuture = new CompletableFuture<>();
            session.on(
                    LifeSpanHandlerOnAfterCreatedEvent.MESSAGE_ID, LifeSpanHandlerOnAfterCreatedEvent.DECODER, ev -> {
                        if (!handleFuture.isDone()) handleFuture.complete(ev.browser());
                    });

            RemoteHandle browserHandle = handleFuture.get(20, TimeUnit.SECONDS);
            Browser browser = new Browser(session, browserHandle);

            // Navigate to a deterministic page so getSource returns predictable HTML.
            String dataUrl = "data:text/html,<html><body>RMI-callback-marker-9341</body></html>";
            Frame mainFrame = browser.getMainFrame().get(5, TimeUnit.SECONDS);
            mainFrame.loadUrl(dataUrl).get(5, TimeUnit.SECONDS);

            // Poll until the navigation is committed (CEF re-mints the main frame across loads).
            String currentUrl = "";
            long deadline = System.nanoTime() + Duration.ofSeconds(15).toNanos();
            while (System.nanoTime() < deadline) {
                Frame current = browser.getMainFrame().get(5, TimeUnit.SECONDS);
                currentUrl = current.getUrl().get(5, TimeUnit.SECONDS);
                if (currentUrl.startsWith("data:text/html")) break;
                Thread.sleep(100);
            }
            assertThat(currentUrl).startsWith("data:text/html");

            // Register a one-shot visitor: completes a future when CEF delivers the page source.
            CompletableFuture<String> sourceFuture = new CompletableFuture<>();
            CefStringVisitor visitor = text -> {
                if (!sourceFuture.isDone()) sourceFuture.complete(text);
            };
            int callbackId = visitors.register(visitor);
            try {
                Frame currentFrame = browser.getMainFrame().get(5, TimeUnit.SECONDS);
                currentFrame.getSource(new RemoteHandle(callbackId)).get(5, TimeUnit.SECONDS);

                String observed = sourceFuture.get(15, TimeUnit.SECONDS);
                assertThat(observed).contains("RMI-callback-marker-9341");
            } finally {
                // Belt-and-braces: in real code the visitor's first call would auto-release; tests cleanup
                // explicitly to keep the table tidy regardless of whether visit() fired.
                visitors.release(callbackId);
            }
        }
    }
}
