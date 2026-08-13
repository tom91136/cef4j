package net.kurobako.cef4j.ipc.devtools;

import static org.assertj.core.api.Assertions.assertThat;

import com.google.gson.JsonObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import net.kurobako.cef4j.ipc.devtools.gson.DevToolsSession;
import net.kurobako.cef4j.ipc.protocol.gen.Browser;
import net.kurobako.cef4j.ipc.protocol.gen.BrowserSettings;
import net.kurobako.cef4j.ipc.protocol.gen.CreateBrowserRequest;
import net.kurobako.cef4j.ipc.protocol.gen.CreateBrowserResponse;
import net.kurobako.cef4j.ipc.protocol.gen.LifeSpanHandlerOnAfterCreatedEvent;
import net.kurobako.cef4j.ipc.session.CefSession;
import net.kurobako.cef4j.ipc.session.CefSessionImpl;
import net.kurobako.cef4j.ipc.session.RemoteHandle;
import net.kurobako.cef4j.ipc.session.process.RuntimeServerProcess;
import net.kurobako.cef4j.ipc.transport.CefTransport;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

/** Proves that raw CDP works through the CEF runtime server over a pluggable control transport. */
@Timeout(60)
class RuntimeServerDevToolsIntegrationTest {
    private static Path serverBinary;
    private static Path cefResources;

    @BeforeAll
    static void resolveBinary() {
        String binary = System.getProperty("cef4j.runtime.server.binary");
        String resources = System.getProperty("cef4j.runtime.server.resources");
        Assumptions.assumeTrue(binary != null, "cef4j.runtime.server.binary system property not set");
        Assumptions.assumeTrue(resources != null, "cef4j.runtime.server.resources system property not set");
        serverBinary = Path.of(binary);
        cefResources = Path.of(resources);
        Assumptions.assumeTrue(Files.isExecutable(serverBinary), "runtime server binary not built at " + serverBinary);
        Assumptions.assumeTrue(Files.isDirectory(cefResources), "CEF resources missing at " + cefResources);
    }

    @Test
    void evaluatesJavascriptOverZmqTransport() throws Exception {
        verifyTransport("zmq", "tcp://127.0.0.1:0", "mmap");
    }

    @Test
    void evaluatesJavascriptOverWebSocketTransport() throws Exception {
        verifyTransport("websocket", "ws://127.0.0.1:0/cef4j", "inline");
    }

    private static void verifyTransport(String transportName, String endpoint, String frameTransport) throws Exception {
        try (RuntimeServerProcess server = startServer(transportName, endpoint, frameTransport);
                CefTransport transport = server.connect();
                CefSession session = new CefSessionImpl(transport, Duration.ofSeconds(20))) {
            CompletableFuture<RemoteHandle> browserHandle = new CompletableFuture<>();
            session.onLatest(
                    LifeSpanHandlerOnAfterCreatedEvent.MESSAGE_ID,
                    LifeSpanHandlerOnAfterCreatedEvent.DECODER,
                    event -> browserHandle.complete(event.browser()));
            session.request(
                            new CreateBrowserRequest(
                                    "about:blank", BrowserSettings.builder().build()),
                            CreateBrowserResponse.DECODER)
                    .get(10, TimeUnit.SECONDS);

            Browser browser = new Browser(session, browserHandle.get(20, TimeUnit.SECONDS));
            try (DevToolsSession devTools = DevToolsSession.attach(
                            session, browser.handle(), browser.getHost().get(5, TimeUnit.SECONDS))
                    .get(10, TimeUnit.SECONDS)) {
                JsonObject params = new JsonObject();
                params.addProperty("expression", "6 * 7");
                params.addProperty("returnByValue", true);
                JsonObject result = devTools.send("Runtime.evaluate", params).get(10, TimeUnit.SECONDS);
                assertThat(result.getAsJsonObject("result").get("value").getAsInt())
                        .isEqualTo(42);
            }

            assertThat(server.transport()).isEqualTo(transportName);
        }
    }

    private static RuntimeServerProcess startServer(String transport, String endpoint, String frameTransport)
            throws IOException {
        return RuntimeServerProcess.spawn(
                serverBinary,
                transport,
                endpoint,
                frameTransport,
                Duration.ofSeconds(30),
                net.kurobako.cef4j.ipc.frame.RemoteCefBrowserBackend.runtimeEnvironment(cefResources));
    }
}
