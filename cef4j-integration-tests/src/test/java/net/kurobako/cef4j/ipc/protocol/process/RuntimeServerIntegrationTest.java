package net.kurobako.cef4j.ipc.protocol.process;

import static org.assertj.core.api.Assertions.assertThat;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import net.kurobako.cef4j.ipc.protocol.gen.Browser;
import net.kurobako.cef4j.ipc.protocol.gen.BrowserSettings;
import net.kurobako.cef4j.ipc.protocol.gen.CefDisplayHandler;
import net.kurobako.cef4j.ipc.protocol.gen.CefLoadHandler;
import net.kurobako.cef4j.ipc.protocol.gen.CreateBrowserRequest;
import net.kurobako.cef4j.ipc.protocol.gen.CreateBrowserResponse;
import net.kurobako.cef4j.ipc.protocol.gen.Frame;
import net.kurobako.cef4j.ipc.protocol.gen.LifeSpanHandlerOnAfterCreatedEvent;
import net.kurobako.cef4j.ipc.protocol.gen.ReleaseHandleResponse;
import net.kurobako.cef4j.ipc.session.CefSession;
import net.kurobako.cef4j.ipc.session.CefSessionImpl;
import net.kurobako.cef4j.ipc.session.RemoteHandle;
import net.kurobako.cef4j.ipc.session.process.RuntimeServerProcess;
import net.kurobako.cef4j.ipc.transport.CefTransport;
import net.kurobako.cef4j.ipc.transport.ZmqTransport;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

/**
 * End-to-end through the native {@code cef4j-runtime-server}: start the server, fetch a URL served by a local
 * {@link HttpServer}, observe {@link OnLoadEndEvent} arriving back over the IPC channel.
 *
 * <p>Skipped if the server binary has not been built. On Linux, requires xvfb (CEF's GPU process initialises X even in
 * windowless mode); run via {@code xvfb-run --auto-servernum ./mvnw -pl cef4j-remote-core -am test}.
 */
@Timeout(60)
class RuntimeServerIntegrationTest {

    private static Path serverBinary;
    private static Path cefResources;

    @BeforeAll
    static void resolveBinary() {
        String bin = System.getProperty("cef4j.runtime.server.binary");
        String res = System.getProperty("cef4j.runtime.server.resources");
        Assumptions.assumeTrue(bin != null, "cef4j.runtime.server.binary system property not set");
        Assumptions.assumeTrue(res != null, "cef4j.runtime.server.resources system property not set");
        serverBinary = Path.of(bin);
        cefResources = Path.of(res);
        Assumptions.assumeTrue(Files.isExecutable(serverBinary), "runtime server binary not built at " + serverBinary);
        Assumptions.assumeTrue(Files.isDirectory(cefResources), "CEF resources dir missing at " + cefResources);
    }

    private static HttpServer startFixture() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(InetAddress.getLoopbackAddress(), 0), 0);
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

    private static RuntimeServerProcess startServerWithEnv() throws IOException {
        return startServerWithEnv("zmq", "tcp://127.0.0.1:0");
    }

    private static RuntimeServerProcess startServerWithEnv(String transport, String endpoint) throws IOException {
        return RuntimeServerProcess.spawn(
                serverBinary,
                transport,
                endpoint,
                "shared-file",
                Duration.ofSeconds(30),
                net.kurobako.cef4j.ipc.frame.RemoteCefBrowserBackend.runtimeEnvironment(cefResources));
    }

    private static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase(Locale.ROOT).contains("win");
    }

    private static boolean optionalUdsClientAvailable() {
        try {
            Class.forName(
                    "org.newsclub.net.unix.AFUNIXSocketAddress",
                    false,
                    RuntimeServerIntegrationTest.class.getClassLoader());
            return true;
        } catch (ClassNotFoundException unavailable) {
            return false;
        }
    }

    @Test
    void udsTransportBootstrapsRuntimeServerSession() throws Exception {
        Assumptions.assumeFalse(isWindows(), "Unix-domain sockets are not the Windows local transport");
        Assumptions.assumeTrue(optionalUdsClientAvailable(), "optional junixsocket client is not on this classpath");
        Path socket = Files.createTempDirectory("cef4j-runtime-server-uds-").resolve("ipc.sock");
        try (RuntimeServerProcess server = startServerWithEnv("uds", "unix://" + socket);
                CefTransport transport = server.connect();
                CefSession session = new CefSessionImpl(transport, Duration.ofSeconds(20))) {
            CreateBrowserResponse response = session.request(
                            new CreateBrowserRequest(
                                    "about:blank", BrowserSettings.builder().build()),
                            CreateBrowserResponse.DECODER)
                    .get(20, TimeUnit.SECONDS);
            assertThat(response).isNotNull();
            assertThat(server.transport()).isEqualTo("uds");
            assertThat(server.endpoint()).startsWith("unix://");
            assertThat(server.handshake().cefApiVersion()).isPositive();
            assertThat(server.handshake().capabilities()).contains("remote-cef-api", "devtools", "osr", "input");
        }
    }

    @Test
    void platformLocalTransportBootstrapsRuntimeServerSession() throws Exception {
        String endpoint =
                isWindows() ? "pipe://cef4j-test-" + Long.toUnsignedString(System.nanoTime()) : "tcp://127.0.0.1:0";
        try (RuntimeServerProcess server = startServerWithEnv("local", endpoint);
                CefTransport transport = server.connect();
                CefSession session = new CefSessionImpl(transport, Duration.ofSeconds(20))) {
            CreateBrowserResponse response = session.request(
                            new CreateBrowserRequest(
                                    "about:blank", BrowserSettings.builder().build()),
                            CreateBrowserResponse.DECODER)
                    .get(20, TimeUnit.SECONDS);
            assertThat(response).isNotNull();
            assertThat(server.transport()).isEqualTo("local");
            assertThat(server.endpoint()).startsWith(isWindows() ? "pipe://" : "tcp://");
        }
    }

    @Test
    void webSocketTransportBootstrapsRuntimeServerSession() throws Exception {
        try (RuntimeServerProcess server = startServerWithEnv("websocket", "ws://127.0.0.1:0/cef4j");
                CefTransport transport = server.connect();
                CefSession session = new CefSessionImpl(transport, Duration.ofSeconds(20))) {
            CreateBrowserResponse response = session.request(
                            new CreateBrowserRequest(
                                    "about:blank", BrowserSettings.builder().build()),
                            CreateBrowserResponse.DECODER)
                    .get(20, TimeUnit.SECONDS);
            assertThat(response).isNotNull();
            assertThat(server.transport()).isEqualTo("websocket");
            assertThat(server.endpoint()).startsWith("ws://127.0.0.1:").endsWith("/cef4j");
        }
    }

    @Test
    void loadUrlRoundTripsThroughRuntimeServer() throws Exception {
        // End-to-end through the AST pipeline: get the server's initial browser handle, walk to its main
        // frame, dispatch Frame.loadUrl, observe CefLoadHandler.onLoadEnd via the typed handler API.
        HttpServer fixture = startFixture();
        try (RuntimeServerProcess server = startServerWithEnv();
                ZmqTransport transport = ZmqTransport.connect(server.endpoint());
                CefSession session = new CefSessionImpl(transport, Duration.ofSeconds(20))) {
            String url = "http://127.0.0.1:" + fixture.getAddress().getPort() + "/hello";

            CompletableFuture<RemoteHandle> browserHandle = new CompletableFuture<>();
            session.on(
                    LifeSpanHandlerOnAfterCreatedEvent.MESSAGE_ID, LifeSpanHandlerOnAfterCreatedEvent.DECODER, ev -> {
                        if (!browserHandle.isDone()) browserHandle.complete(ev.browser());
                    });

            // Subscribe before navigating. about:blank also fires onLoadEnd; filter on URL via Frame.getUrl
            // (the AST event carries handles only).
            CountDownLatch sawOurUrl = new CountDownLatch(1);
            int[] capturedStatus = {-1};
            CopyOnWriteArrayList<CompletableFuture<Void>> urlChecks = new CopyOnWriteArrayList<>();
            CefLoadHandler.register(session, new CefLoadHandler() {
                @Override
                public void onLoadEnd(RemoteHandle browser, RemoteHandle frame, int httpStatusCode) {
                    urlChecks.add(new Frame(session, frame).getUrl().thenAccept(loaded -> {
                        if (loaded.equals(url) || loaded.startsWith(url)) {
                            capturedStatus[0] = httpStatusCode;
                            sawOurUrl.countDown();
                        }
                    }));
                }
            });

            Browser browser = new Browser(session, browserHandle.get(20, TimeUnit.SECONDS));
            Frame mainFrame = browser.getMainFrame().get(5, TimeUnit.SECONDS);
            mainFrame.loadUrl(url).get(5, TimeUnit.SECONDS);

            assertThat(sawOurUrl.await(30, TimeUnit.SECONDS))
                    .as("OnLoadEnd for %s should arrive within 30s", url)
                    .isTrue();
            for (CompletableFuture<Void> urlCheck : urlChecks) {
                urlCheck.get(5, TimeUnit.SECONDS);
            }
            assertThat(capturedStatus[0]).isEqualTo(200);
        } finally {
            fixture.stop(0);
        }
    }

    @Test
    void cefDisplayHandlerOnAddressChangeFiresOnNavigation() throws Exception {
        // Validates the generated `wireClient` chain end-to-end for a non-LoadHandler handler:
        // CefClient::get_display_handler returns the generated DisplayHandlerForwarder; CEF fires
        // on_address_change during navigation; the forwarder encodes a `DisplayHandlerOnAddressChangeEvent`
        // (browser, frame, url) and sends it; CefDisplayHandler.register decodes it back to typed callbacks.
        HttpServer fixture = startFixture();
        try (RuntimeServerProcess server = startServerWithEnv();
                ZmqTransport transport = ZmqTransport.connect(server.endpoint());
                CefSession session = new CefSessionImpl(transport, Duration.ofSeconds(20))) {
            String url = "http://127.0.0.1:" + fixture.getAddress().getPort() + "/hello";

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
            fixture.stop(0);
        }
    }

    @Test
    void killingRuntimeServerDisconnectsSessionCleanly() throws Exception {
        RuntimeServerProcess server = startServerWithEnv();
        try (ZmqTransport transport = ZmqTransport.connect(server.endpoint());
                CefSession session = new CefSessionImpl(transport, Duration.ofSeconds(60))) {

            // Send a request with a messageId the server does not handle. The server drops it silently
            // (no ack), so the JVM-side future stays pending until the transport reports a disconnect.
            // Then we kill the server; the session's onDisconnect handler must fail the pending future
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

            // Let the server bind and the ZMTP heartbeat exchange establish.
            Thread.sleep(500);
            ProcessHandle.of(server.pid()).ifPresent(ProcessHandle::destroyForcibly);

            org.assertj.core.api.Assertions.assertThatThrownBy(() -> fut.get(20, TimeUnit.SECONDS))
                    .as("pending request should fail once the server is gone")
                    .isInstanceOf(java.util.concurrent.ExecutionException.class)
                    .hasCauseInstanceOf(net.kurobako.cef4j.ipc.transport.CefTransportException.class);
        } finally {
            server.close();
        }
    }
}
