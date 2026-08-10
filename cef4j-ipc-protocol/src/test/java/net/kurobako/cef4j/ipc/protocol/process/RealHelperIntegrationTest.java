package net.kurobako.cef4j.ipc.protocol.process;

import static org.assertj.core.api.Assertions.assertThat;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import net.kurobako.cef4j.ipc.protocol.gen.Browser;
import net.kurobako.cef4j.ipc.protocol.gen.CefDisplayHandler;
import net.kurobako.cef4j.ipc.protocol.gen.CefLoadHandler;
import net.kurobako.cef4j.ipc.protocol.gen.Frame;
import net.kurobako.cef4j.ipc.protocol.gen.LifeSpanHandlerOnAfterCreatedEvent;
import net.kurobako.cef4j.ipc.protocol.gen.ReleaseHandleResponse;
import net.kurobako.cef4j.ipc.session.CefSession;
import net.kurobako.cef4j.ipc.session.CefSessionImpl;
import net.kurobako.cef4j.ipc.session.RemoteHandle;
import net.kurobako.cef4j.ipc.session.process.HelperProcess;
import net.kurobako.cef4j.ipc.transport.ZmqTransport;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

/**
 * End-to-end through the real C++ {@code cef4j-helper} executable: spawn the helper, fetch a URL served by a local
 * {@link HttpServer}, observe {@link OnLoadEndEvent} arriving back over the IPC channel.
 *
 * <p>Skipped if the helper binary hasn't been built. On Linux, requires xvfb (CEF's GPU process initialises X even in
 * windowless mode); run via {@code xvfb-run --auto-servernum ./mvnw -pl cef4j-ipc-session -am test}.
 */
@Timeout(60)
class RealHelperIntegrationTest {

    private static Path helperBinary;
    private static Path cefResources;

    @BeforeAll
    static void resolveBinary() {
        String bin = System.getProperty("cef4j.ipc.helper.binary");
        String res = System.getProperty("cef4j.ipc.cef.resources");
        Assumptions.assumeTrue(bin != null, "cef4j.ipc.helper.binary system property not set");
        Assumptions.assumeTrue(res != null, "cef4j.ipc.cef.resources system property not set");
        helperBinary = Path.of(bin);
        cefResources = Path.of(res);
        Assumptions.assumeTrue(Files.isExecutable(helperBinary), "helper binary not built at " + helperBinary);
        Assumptions.assumeTrue(Files.isDirectory(cefResources), "CEF resources dir missing at " + cefResources);
    }

    private static HttpServer startFixture() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress("127.0.0.1", 0), 0);
        server.createContext("/hello", ex -> respond(ex, 200, "<html><body>hello from fixture</body></html>"));
        server.start();
        return server;
    }

    private static void respond(HttpExchange ex, int status, String body) throws IOException {
        byte[] b = body.getBytes(StandardCharsets.UTF_8);
        ex.getResponseHeaders().add("Content-Type", "text/html");
        ex.sendResponseHeaders(status, b.length);
        try (OutputStream out = ex.getResponseBody()) {
            out.write(b);
        }
    }

    /**
     * {@link HelperProcess#spawn} doesn't take env vars; the helper needs {@code CEF_RESOURCES_DIR} (so it can find pak
     * files) and {@code LD_LIBRARY_PATH} (so it can dlopen {@code libcef.so}). We solve both with a tiny launcher
     * script that exports the env then execs the binary.
     */
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
    void loadUrlRoundTripsThroughRealHelper() throws Exception {
        // End-to-end through the AST pipeline: get the helper's initial browser handle, walk to its main
        // frame, dispatch Frame.loadUrl, observe CefLoadHandler.onLoadEnd via the typed handler API.
        HttpServer server = startFixture();
        try (HelperProcess helper = spawnHelperWithEnv();
                ZmqTransport transport = ZmqTransport.connect(helper.endpoint());
                CefSession session = new CefSessionImpl(transport, Duration.ofSeconds(20))) {
            String url = "http://127.0.0.1:" + server.getAddress().getPort() + "/hello";

            CompletableFuture<RemoteHandle> browserHandle = new CompletableFuture<>();
            session.on(
                    LifeSpanHandlerOnAfterCreatedEvent.MESSAGE_ID, LifeSpanHandlerOnAfterCreatedEvent.DECODER, ev -> {
                        if (!browserHandle.isDone()) browserHandle.complete(ev.browser());
                    });

            // Subscribe before navigating. about:blank also fires onLoadEnd; filter on URL via Frame.getUrl
            // (the AST event carries handles only).
            CountDownLatch sawOurUrl = new CountDownLatch(1);
            int[] capturedStatus = {-1};
            CefLoadHandler.register(session, new CefLoadHandler() {
                @Override
                public void onLoadEnd(RemoteHandle browser, RemoteHandle frame, int httpStatusCode) {
                    new Frame(session, frame).getUrl().thenAccept(loaded -> {
                        if (loaded.equals(url) || loaded.startsWith(url)) {
                            capturedStatus[0] = httpStatusCode;
                            sawOurUrl.countDown();
                        }
                    });
                }
            });

            Browser browser = new Browser(session, browserHandle.get(20, TimeUnit.SECONDS));
            Frame mainFrame = browser.getMainFrame().get(5, TimeUnit.SECONDS);
            mainFrame.loadUrl(url).get(5, TimeUnit.SECONDS);

            assertThat(sawOurUrl.await(30, TimeUnit.SECONDS))
                    .as("OnLoadEnd for %s should arrive within 30s", url)
                    .isTrue();
            assertThat(capturedStatus[0]).isEqualTo(200);
        } finally {
            server.stop(0);
        }
    }

    @Test
    void cefDisplayHandlerOnAddressChangeFiresOnNavigation() throws Exception {
        // Validates the generated `wireClient` chain end-to-end for a non-LoadHandler handler:
        // CefClient::get_display_handler returns the generated DisplayHandlerForwarder; CEF fires
        // on_address_change during navigation; the forwarder encodes a `DisplayHandlerOnAddressChangeEvent`
        // (browser, frame, url) and sends it; CefDisplayHandler.register decodes it back to typed callbacks.
        HttpServer server = startFixture();
        try (HelperProcess helper = spawnHelperWithEnv();
                ZmqTransport transport = ZmqTransport.connect(helper.endpoint());
                CefSession session = new CefSessionImpl(transport, Duration.ofSeconds(20))) {
            String url = "http://127.0.0.1:" + server.getAddress().getPort() + "/hello";

            CompletableFuture<RemoteHandle> browserHandle = new CompletableFuture<>();
            session.on(
                    LifeSpanHandlerOnAfterCreatedEvent.MESSAGE_ID, LifeSpanHandlerOnAfterCreatedEvent.DECODER, ev -> {
                        if (!browserHandle.isDone()) browserHandle.complete(ev.browser());
                    });

            CountDownLatch sawOurUrl = new CountDownLatch(1);
            CefDisplayHandler.register(session, new CefDisplayHandler() {
                @Override
                public void onAddressChange(RemoteHandle browser, RemoteHandle frame, String addr) {
                    if (addr.equals(url) || addr.startsWith(url)) {
                        sawOurUrl.countDown();
                    }
                }
            });

            Browser browser = new Browser(session, browserHandle.get(20, TimeUnit.SECONDS));
            Frame mainFrame = browser.getMainFrame().get(5, TimeUnit.SECONDS);
            mainFrame.loadUrl(url).get(5, TimeUnit.SECONDS);

            assertThat(sawOurUrl.await(30, TimeUnit.SECONDS))
                    .as("DisplayHandler.onAddressChange should fire for %s", url)
                    .isTrue();
        } finally {
            server.stop(0);
        }
    }

    @Test
    void killingHelperDisconnectsSessionCleanly() throws Exception {
        HelperProcess helper = spawnHelperWithEnv();
        try (ZmqTransport transport = ZmqTransport.connect(helper.endpoint());
                CefSession session = new CefSessionImpl(transport, Duration.ofSeconds(60))) {

            // Send a request with a messageId the helper does not handle. The helper drops it silently
            // (no ack), so the JVM-side future stays pending until the transport reports a disconnect.
            // Then we kill the helper; the session's onDisconnect handler must fail the pending future
            // with a CefTransportException.
            int unhandledMessageId = 9999;
            CompletableFuture<ReleaseHandleResponse> fut = session.request(
                    new net.kurobako.cef4j.ipc.session.CefMessageEncoder() {
                        @Override
                        public int messageId() {
                            return unhandledMessageId;
                        }

                        @Override
                        public int encodedSize() {
                            return 0;
                        }

                        @Override
                        public void encodeInto(java.nio.ByteBuffer dst) {}
                    },
                    ReleaseHandleResponse.DECODER);

            // Let the helper bind and the ZMTP heartbeat exchange establish.
            Thread.sleep(500);
            ProcessHandle.of(helper.pid()).ifPresent(ProcessHandle::destroyForcibly);

            org.assertj.core.api.Assertions.assertThatThrownBy(() -> fut.get(20, TimeUnit.SECONDS))
                    .as("pending request should fail once the helper is gone")
                    .isInstanceOf(java.util.concurrent.ExecutionException.class)
                    .hasCauseInstanceOf(net.kurobako.cef4j.ipc.transport.CefTransportException.class);
        } finally {
            helper.close();
        }
    }
}
