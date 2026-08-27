package net.kurobako.cef4j.ipc.protocol.process;

import static org.assertj.core.api.Assertions.assertThat;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
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
import net.kurobako.cef4j.ipc.session.Envelope;
import net.kurobako.cef4j.ipc.session.RemoteHandle;
import net.kurobako.cef4j.ipc.session.process.RuntimeServerProcess;
import net.kurobako.cef4j.ipc.transport.CefTransport;
import net.kurobako.cef4j.test.RuntimeServerTestEnvironment;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.api.io.TempDir;
import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

/** End-to-end runtime-server lifecycle and transport coverage. */
@Timeout(600)
class RuntimeServerIntegrationTest {

    private static final RuntimeServerTestEnvironment RUNTIME = RuntimeServerTestEnvironment.require();

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
        return RUNTIME.spawn(transport, endpoint, "shared-file", Duration.ofSeconds(30));
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
    void udsTransportBootstrapsRuntimeServerSession(@TempDir Path tmp) throws Exception {
        Assumptions.assumeFalse(isWindows(), "Unix-domain sockets are not the Windows local transport");
        Assumptions.assumeTrue(optionalUdsClientAvailable(), "optional junixsocket client is not on this classpath");
        Path socket = tmp.resolve("ipc.sock");
        try (RuntimeServerProcess server = startServerWithEnv("uds", "unix://" + socket);
                CefTransport transport = server.connect();
                CefSession session = new CefSessionImpl(transport, Duration.ofSeconds(30))) {
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
                CefSession session = new CefSessionImpl(transport, Duration.ofSeconds(30))) {
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
    @EnabledOnOs(OS.WINDOWS)
    void namedPipeRuntimeConnectionReportsProcessExit() throws Exception {
        String endpoint = "pipe://cef4j-disconnect-" + Long.toUnsignedString(System.nanoTime());
        try (RuntimeServerProcess server = startServerWithEnv("local", endpoint);
                CefTransport transport = server.connect();
                CefSession session = new CefSessionImpl(transport, Duration.ofSeconds(30))) {
            CountDownLatch closed = new CountDownLatch(1);
            session.onClose(closed::countDown);
            server.kill();
            assertThat(closed.await(20, TimeUnit.SECONDS)).isTrue();
            assertThat(transport.isConnected()).isFalse();
        }
    }

    @Test
    void webSocketTransportBootstrapsRuntimeServerSession() throws Exception {
        try (RuntimeServerProcess server = startServerWithEnv("websocket", "ws://127.0.0.1:0/cef4j");
                CefTransport transport = server.connect();
                CefSession session = new CefSessionImpl(transport, Duration.ofSeconds(30))) {
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
    void zmqRuntimeRejectsASecondDealerPeer() throws Exception {
        try (RuntimeServerProcess server = startServerWithEnv();
                CefTransport transport = server.connect();
                CefSession session = new CefSessionImpl(transport, Duration.ofSeconds(30));
                ZContext context = new ZContext()) {
            ZMQ.Socket secondPeer = context.createSocket(SocketType.DEALER);
            secondPeer.setLinger(0);
            secondPeer.setReceiveTimeOut(1_000);
            secondPeer.connect(server.endpoint());
            ByteBuffer ready = ByteBuffer.allocate(Envelope.HEADER_SIZE).order(ByteOrder.LITTLE_ENDIAN);
            Envelope.writeHeader(ready, Envelope.Kind.REQUEST, 0, 0, 0, 0);
            secondPeer.send(ready.array(), 0);
            assertThat(secondPeer.recv(0)).isNull();

            assertThat(session.request(
                                    new CreateBrowserRequest(
                                            "about:blank",
                                            BrowserSettings.builder().build()),
                                    CreateBrowserResponse.DECODER)
                            .get(20, TimeUnit.SECONDS))
                    .isNotNull();
        }
    }

    @Test
    void loadUrlRoundTripsThroughRuntimeServer() throws Exception {
        HttpServer fixture = startFixture();
        try (RuntimeServerProcess server = startServerWithEnv();
                CefTransport transport = server.connect();
                CefSession session = new CefSessionImpl(transport, Duration.ofSeconds(30))) {
            String url = "http://127.0.0.1:" + fixture.getAddress().getPort() + "/hello";

            CompletableFuture<RemoteHandle> browserHandle = new CompletableFuture<>();
            session.onLatest(
                    LifeSpanHandlerOnAfterCreatedEvent.MESSAGE_ID, LifeSpanHandlerOnAfterCreatedEvent.DECODER, ev -> {
                        if (!browserHandle.isDone()) browserHandle.complete(ev.browser());
                    });

            CountDownLatch sawOurUrl = new CountDownLatch(1);
            int[] capturedStatus = {-1};
            CopyOnWriteArrayList<CompletableFuture<Void>> urlChecks = new CopyOnWriteArrayList<>();
            CefLoadHandler.register(session, new CefLoadHandler() {
                @Override
                public void onLoadEnd(RemoteHandle browser, RemoteHandle frame, int httpStatusCode) {
                    urlChecks.add(new Frame(session, frame).getUrl().thenAccept(loaded -> {
                        if (httpStatusCode == 200 && (loaded.equals(url) || loaded.startsWith(url))) {
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
        HttpServer fixture = startFixture();
        try (RuntimeServerProcess server = startServerWithEnv();
                CefTransport transport = server.connect();
                CefSession session = new CefSessionImpl(transport, Duration.ofSeconds(30))) {
            String url = "http://127.0.0.1:" + fixture.getAddress().getPort() + "/hello";

            CompletableFuture<RemoteHandle> browserHandle = new CompletableFuture<>();
            session.onLatest(
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
        try (net.kurobako.cef4j.ipc.transport.CefTransport transport = server.connect();
                CefSession session = new CefSessionImpl(transport, Duration.ofSeconds(60))) {

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

            server.kill();

            org.assertj.core.api.Assertions.assertThatThrownBy(() -> fut.get(20, TimeUnit.SECONDS))
                    .as("pending request should fail once the server is gone")
                    .isInstanceOf(java.util.concurrent.ExecutionException.class)
                    .hasCauseInstanceOf(net.kurobako.cef4j.ipc.transport.CefTransportException.class);
        } finally {
            server.close();
        }
    }
}
