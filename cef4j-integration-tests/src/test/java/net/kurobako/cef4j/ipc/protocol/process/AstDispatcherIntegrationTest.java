package net.kurobako.cef4j.ipc.protocol.process;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import net.kurobako.cef4j.ipc.protocol.gen.Browser;
import net.kurobako.cef4j.ipc.protocol.gen.BrowserSettings;
import net.kurobako.cef4j.ipc.protocol.gen.CefLifeSpanHandler;
import net.kurobako.cef4j.ipc.protocol.gen.CreateBrowserRequest;
import net.kurobako.cef4j.ipc.protocol.gen.CreateBrowserResponse;
import net.kurobako.cef4j.ipc.protocol.gen.Frame;
import net.kurobako.cef4j.ipc.protocol.gen.LifeSpanHandlerOnAfterCreatedEvent;
import net.kurobako.cef4j.ipc.session.CefSession;
import net.kurobako.cef4j.ipc.session.CefSessionImpl;
import net.kurobako.cef4j.ipc.session.RemoteHandle;
import net.kurobako.cef4j.ipc.session.process.RuntimeServerProcess;
import net.kurobako.cef4j.ipc.transport.ZmqTransport;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

/**
 * Validates the AST-derived dispatcher path end-to-end. Unlike {@link RemoteHandleIntegrationTest}, which exercises
 * hand-written Slice C messages (whose server-side dispatch is also hand-written), this test calls a method whose
 * Request/Response classes and server-side switch case are entirely codegen-emitted from the CEF AST.
 *
 * <p>{@code cef_browser_t::is_valid()} is a no-arg, primitive-returning method — the simplest shape supported by the
 * first-pass {@code CppDispatcherEmitter}. A non-zero result confirms: AST-derived wire codecs round-trip correctly,
 * the JVM-side {@link Browser} facade dispatches through {@link CefSession}, and the server's generated dispatcher
 * decoded, retained the handle, posted the call onto the CEF UI thread, and encoded the response.
 */
@Timeout(60)
class AstDispatcherIntegrationTest {

    private static Path serverBinary;
    private static Path cefResources;

    @BeforeAll
    static void resolveBinary() {
        String bin = System.getProperty("cef4j.runtime.server.binary");
        String res = System.getProperty("cef4j.runtime.server.resources");
        Assumptions.assumeTrue(bin != null, "cef4j.runtime.server.binary system property not set");
        Assumptions.assumeTrue(res != null, "cef4j.runtime.server.resources system property not set");
        serverBinary = Paths.get(bin);
        cefResources = Paths.get(res);
        Assumptions.assumeTrue(Files.isExecutable(serverBinary), "server binary not built at " + serverBinary);
        Assumptions.assumeTrue(Files.isDirectory(cefResources), "CEF resources dir missing at " + cefResources);
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
    void browserIsValidThroughAstDispatcher() throws Exception {
        try (RuntimeServerProcess server = spawnServerWithEnv();
                ZmqTransport transport = ZmqTransport.connect(server.endpoint());
                CefSession session = new CefSessionImpl(transport, Duration.ofSeconds(10))) {

            CompletableFuture<RemoteHandle> handleFuture = new CompletableFuture<>();
            session.onLatest(
                    LifeSpanHandlerOnAfterCreatedEvent.MESSAGE_ID, LifeSpanHandlerOnAfterCreatedEvent.DECODER, ev -> {
                        if (!handleFuture.isDone()) handleFuture.complete(ev.browser());
                    });

            RemoteHandle handle = handleFuture.get(20, TimeUnit.SECONDS);
            assertThat(handle.isNull()).isFalse();

            Browser browser = new Browser(session, handle);

            // is_valid() returns int (non-zero == valid). The whole call path is generated: facade method →
            // BrowserIsValidRequest encoder → wire → server's Dispatcher.h case → cef_browser_t->is_valid →
            // BrowserIsValidResponse encoder → JVM decoder.
            Integer valid = browser.isValid().get(5, TimeUnit.SECONDS);
            assertThat(valid).isNotNull();
            assertThat(valid).isNotZero();
        }
    }

    @Test
    void cefLifeSpanHandlerOnAfterCreatedFires() throws Exception {
        // Validates the typed CefLifeSpanHandler interface end-to-end: the typed handler delivery and a raw
        // session.onLatest subscription on the same AST event class both receive the same handle. Browser creation
        // is a state announcement that may race session construction, so both subscriptions must use latest-event
        // replay rather than depending on process startup timing.
        try (RuntimeServerProcess server = spawnServerWithEnv();
                ZmqTransport transport = ZmqTransport.connect(server.endpoint());
                CefSession session = new CefSessionImpl(transport, Duration.ofSeconds(10))) {

            CompletableFuture<RemoteHandle> viaTyped = new CompletableFuture<>();
            CompletableFuture<RemoteHandle> viaRaw = new CompletableFuture<>();
            CefLifeSpanHandler.register(session, new CefLifeSpanHandler() {
                @Override
                public void onAfterCreated(RemoteHandle browser) {
                    if (!viaTyped.isDone()) viaTyped.complete(browser);
                }
            });
            session.onLatest(
                    LifeSpanHandlerOnAfterCreatedEvent.MESSAGE_ID, LifeSpanHandlerOnAfterCreatedEvent.DECODER, ev -> {
                        if (!viaRaw.isDone()) viaRaw.complete(ev.browser());
                    });

            assertThat(viaTyped.get(20, TimeUnit.SECONDS)).isEqualTo(viaRaw.get(20, TimeUnit.SECONDS));
        }
    }

    @Test
    void facadeCloseReleasesRuntimeServerHandle() throws Exception {
        // Validates the refcount lifecycle: Browser.getMainFrame() mints a frame handle, the JVM closes it
        // via Frame.close() which sends ReleaseHandleRequest{handle, kind="cef_frame_t"}. After the close
        // future completes the server has dropped its retain; calling Frame.getUrl() afterwards no longer
        // finds a receiver and the response comes back empty (default-decoded "").
        try (RuntimeServerProcess server = spawnServerWithEnv();
                ZmqTransport transport = ZmqTransport.connect(server.endpoint());
                CefSession session = new CefSessionImpl(transport, Duration.ofSeconds(10))) {

            CompletableFuture<RemoteHandle> handleFuture = new CompletableFuture<>();
            session.onLatest(
                    LifeSpanHandlerOnAfterCreatedEvent.MESSAGE_ID, LifeSpanHandlerOnAfterCreatedEvent.DECODER, ev -> {
                        if (!handleFuture.isDone()) handleFuture.complete(ev.browser());
                    });

            RemoteHandle browserHandle = handleFuture.get(20, TimeUnit.SECONDS);
            Browser browser = new Browser(session, browserHandle);
            net.kurobako.cef4j.ipc.protocol.gen.Frame frame =
                    browser.getMainFrame().get(5, TimeUnit.SECONDS);
            assertThat(frame.handle().id()).isPositive();

            // Before release: isValid() finds the receiver in tables::frame and returns the CEF non-zero result.
            int validBefore = frame.isValid().get(5, TimeUnit.SECONDS);
            assertThat(validBefore).isNotZero();

            // After releaseHandle: server has dropped the table entry. Dispatcher's null-receiver path now
            // sends Kind::Error(ReceiverGone), which the session translates into CefRemoteException — the
            // call fails fast instead of decoding a zero-default response.
            frame.releaseHandle().get(5, TimeUnit.SECONDS);
            try {
                frame.isValid().get(5, TimeUnit.SECONDS);
                org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown(
                        java.util.concurrent.ExecutionException.class);
            } catch (java.util.concurrent.ExecutionException e) {
                Throwable cause = java.util.Objects.requireNonNull(e.getCause());
                assertThat(cause).isInstanceOf(net.kurobako.cef4j.ipc.session.CefRemoteException.class);
                net.kurobako.cef4j.ipc.session.CefRemoteException remote =
                        (net.kurobako.cef4j.ipc.session.CefRemoteException) cause;
                assertThat(remote.code())
                        .isEqualTo(net.kurobako.cef4j.ipc.session.CefRemoteException.CODE_RECEIVER_GONE);
            }
        }
    }

    @Test
    void browserGetMainFrameReturnsTypedFrameFacade() throws Exception {
        // Exercises both the RemoteHandle-return dispatcher path AND the typed-wrapper return story:
        // `Browser.getMainFrame()` returns `CompletableFuture<Frame>`, not bare `RemoteHandle`. The wrapper
        // carries the same session so chained calls (e.g. `frame.getUrl()`) work without manual rewrapping.
        try (RuntimeServerProcess server = spawnServerWithEnv();
                ZmqTransport transport = ZmqTransport.connect(server.endpoint());
                CefSession session = new CefSessionImpl(transport, Duration.ofSeconds(10))) {

            CompletableFuture<RemoteHandle> handleFuture = new CompletableFuture<>();
            session.onLatest(
                    LifeSpanHandlerOnAfterCreatedEvent.MESSAGE_ID, LifeSpanHandlerOnAfterCreatedEvent.DECODER, ev -> {
                        if (!handleFuture.isDone()) handleFuture.complete(ev.browser());
                    });

            RemoteHandle browserHandle = handleFuture.get(20, TimeUnit.SECONDS);
            Browser browser = new Browser(session, browserHandle);

            net.kurobako.cef4j.ipc.protocol.gen.Frame frame =
                    browser.getMainFrame().get(5, TimeUnit.SECONDS);
            assertThat(frame).isNotNull();
            assertThat(frame.handle().isNull()).isFalse();
            assertThat(frame.handle().id()).isPositive();
        }
    }

    @Test
    void frameLoadUrlAndGetUrlRoundTrip() throws Exception {
        // Exercises the dispatcher's string-param path (loadUrl(url)) plus the string-return path (getUrl())
        // through real CEF. Uses a data: URI so loading is deterministic and offline. CefLoadHandler.onLoadEnd
        // signals when CEF has finished navigating; getUrl() afterward is expected to match what we asked for.
        try (RuntimeServerProcess server = spawnServerWithEnv();
                ZmqTransport transport = ZmqTransport.connect(server.endpoint());
                CefSession session = new CefSessionImpl(transport, Duration.ofSeconds(10))) {

            CompletableFuture<RemoteHandle> handleFuture = new CompletableFuture<>();
            session.onLatest(
                    LifeSpanHandlerOnAfterCreatedEvent.MESSAGE_ID, LifeSpanHandlerOnAfterCreatedEvent.DECODER, ev -> {
                        if (!handleFuture.isDone()) handleFuture.complete(ev.browser());
                    });

            String dataUrl = "data:text/html,<html><body>hi</body></html>";

            RemoteHandle browserHandle = handleFuture.get(20, TimeUnit.SECONDS);
            Browser browser = new Browser(session, browserHandle);
            Frame frame = browser.getMainFrame().get(5, TimeUnit.SECONDS);

            frame.loadUrl(dataUrl).get(5, TimeUnit.SECONDS);

            // The bootstrap's about:blank onLoadEnd races our data: load, so don't gate on the first event.
            // CEF can replace the main frame across navigations (cross-origin reuses the host but mints a new
            // frame), so re-fetch on each iteration rather than caching the frame from before loadUrl.
            String url = "";
            long deadline = System.nanoTime() + Duration.ofSeconds(20).toNanos();
            while (System.nanoTime() < deadline) {
                Frame current = browser.getMainFrame().get(5, TimeUnit.SECONDS);
                url = current.getUrl().get(5, TimeUnit.SECONDS);
                if (url.startsWith("data:text/html")) break;
                Thread.sleep(100);
            }
            assertThat(url).startsWith("data:text/html");
        }
    }

    @Test
    void twoConcurrentBrowsersHaveIndependentState() throws Exception {
        // Two live browsers in one server, each navigated to a distinct data: URL. Validates that
        // tables::browser dedupes by pointer (each receives a unique handle id) and that subsequent dispatch
        // calls route to the right receiver — getUrl() on browser A returns A's URL, not B's.
        try (RuntimeServerProcess server = spawnServerWithEnv();
                ZmqTransport transport = ZmqTransport.connect(server.endpoint());
                CefSession session = new CefSessionImpl(transport, Duration.ofSeconds(10))) {

            LinkedBlockingQueue<RemoteHandle> handles = new LinkedBlockingQueue<>();
            session.onLatest(
                    LifeSpanHandlerOnAfterCreatedEvent.MESSAGE_ID,
                    LifeSpanHandlerOnAfterCreatedEvent.DECODER,
                    ev -> handles.offer(ev.browser()));

            // Drain the bootstrap browser before issuing our two CreateBrowsers so we're matching events to
            // requests deterministically.
            RemoteHandle bootstrap = handles.poll(20, TimeUnit.SECONDS);
            assertThat(bootstrap).isNotNull();

            BrowserSettings settings = BrowserSettings.builder().build();
            String urlA = "data:text/html,<html><body>A</body></html>";
            String urlB = "data:text/html,<html><body>B</body></html>";
            session.request(new CreateBrowserRequest(urlA, settings), CreateBrowserResponse.DECODER)
                    .get(5, TimeUnit.SECONDS);
            session.request(new CreateBrowserRequest(urlB, settings), CreateBrowserResponse.DECODER)
                    .get(5, TimeUnit.SECONDS);

            RemoteHandle handleA = handles.poll(45, TimeUnit.SECONDS);
            RemoteHandle handleB = handles.poll(45, TimeUnit.SECONDS);
            assertThat(handleA).isNotNull();
            assertThat(handleB).isNotNull();
            assertThat(handleA).isNotEqualTo(handleB);

            Browser browserA = new Browser(session, handleA);
            Browser browserB = new Browser(session, handleB);

            // Poll each browser independently until its main frame's URL reflects what we asked for.
            String urlOnA = pollFrameUrl(browserA, "data:text/html,<html><body>A");
            String urlOnB = pollFrameUrl(browserB, "data:text/html,<html><body>B");
            assertThat(urlOnA).contains("A</body></html>");
            assertThat(urlOnB).contains("B</body></html>");
            // Cross-check: each browser saw its own URL, not the other's.
            assertThat(urlOnA).doesNotContain("B</body>");
            assertThat(urlOnB).doesNotContain("A</body>");
        }
    }

    private static String pollFrameUrl(Browser browser, String expectedPrefix) throws Exception {
        long deadline = System.nanoTime() + Duration.ofSeconds(20).toNanos();
        String url = "";
        while (System.nanoTime() < deadline) {
            Frame current = browser.getMainFrame().get(5, TimeUnit.SECONDS);
            url = current.getUrl().get(5, TimeUnit.SECONDS);
            if (url.startsWith(expectedPrefix)) return url;
            Thread.sleep(100);
        }
        return url;
    }

    @Test
    void createBrowserMintsAdditionalHandle() throws Exception {
        // Validates JVM-triggered browser creation: send a CreateBrowserRequest, expect a second
        // LifeSpanHandlerOnAfterCreatedEvent (the first is the server's bootstrap about:blank). The new
        // browser's handle differs from the bootstrap's, proving the server minted a fresh table entry.
        try (RuntimeServerProcess server = spawnServerWithEnv();
                ZmqTransport transport = ZmqTransport.connect(server.endpoint());
                CefSession session = new CefSessionImpl(transport, Duration.ofSeconds(10))) {

            LinkedBlockingQueue<RemoteHandle> handles = new LinkedBlockingQueue<>();
            session.onLatest(
                    LifeSpanHandlerOnAfterCreatedEvent.MESSAGE_ID,
                    LifeSpanHandlerOnAfterCreatedEvent.DECODER,
                    ev -> handles.offer(ev.browser()));

            RemoteHandle bootstrap = handles.poll(20, TimeUnit.SECONDS);
            assertThat(bootstrap).isNotNull();

            // Build a BrowserSettings via the generated builder (28-arg ctor would be unreadable). Sets a
            // non-default frame rate to prove the data struct rides through the wire — server-side decode →
            // cef_browser_settings_t.windowless_frame_rate.
            BrowserSettings settings = BrowserSettings.builder()
                    .windowlessFrameRate(60)
                    .defaultEncoding("UTF-8")
                    .javascript(1)
                    .build();

            session.request(new CreateBrowserRequest("about:blank", settings), CreateBrowserResponse.DECODER)
                    .get(5, TimeUnit.SECONDS);

            RemoteHandle created = handles.poll(20, TimeUnit.SECONDS);
            assertThat(created).isNotNull();
            assertThat(created).isNotEqualTo(bootstrap);
            assertThat(created.id()).isPositive();
        }
    }
}
