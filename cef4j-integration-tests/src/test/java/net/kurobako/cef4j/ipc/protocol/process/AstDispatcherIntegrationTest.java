package net.kurobako.cef4j.ipc.protocol.process;

import static org.assertj.core.api.Assertions.assertThat;

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
import net.kurobako.cef4j.ipc.transport.CefTransport;
import net.kurobako.cef4j.test.RuntimeServerTestEnvironment;
import net.kurobako.cef4j.test.TestDeadline;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

@Timeout(600)
class AstDispatcherIntegrationTest {

    private static final RuntimeServerTestEnvironment RUNTIME = RuntimeServerTestEnvironment.require();

    @Test
    void browserIsValidThroughAstDispatcher() throws Exception {
        try (RuntimeServerProcess server = RUNTIME.spawn();
                CefTransport transport = server.connect();
                CefSession session = new CefSessionImpl(transport, Duration.ofSeconds(30))) {

            CompletableFuture<RemoteHandle> handleFuture = new CompletableFuture<>();
            session.onLatest(
                    LifeSpanHandlerOnAfterCreatedEvent.MESSAGE_ID, LifeSpanHandlerOnAfterCreatedEvent.DECODER, ev -> {
                        if (!handleFuture.isDone()) handleFuture.complete(ev.browser());
                    });

            RemoteHandle handle = handleFuture.get(20, TimeUnit.SECONDS);
            assertThat(handle.isNull()).isFalse();

            Browser browser = new Browser(session, handle);

            Integer valid = browser.isValid().get(5, TimeUnit.SECONDS);
            assertThat(valid).isNotNull();
            assertThat(valid).isNotZero();
        }
    }

    @Test
    void cefLifeSpanHandlerOnAfterCreatedFires() throws Exception {
        try (RuntimeServerProcess server = RUNTIME.spawn();
                CefTransport transport = server.connect();
                CefSession session = new CefSessionImpl(transport, Duration.ofSeconds(30))) {

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
        try (RuntimeServerProcess server = RUNTIME.spawn();
                CefTransport transport = server.connect();
                CefSession session = new CefSessionImpl(transport, Duration.ofSeconds(30))) {

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

            int validBefore = frame.isValid().get(5, TimeUnit.SECONDS);
            assertThat(validBefore).isNotZero();

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
        try (RuntimeServerProcess server = RUNTIME.spawn();
                CefTransport transport = server.connect();
                CefSession session = new CefSessionImpl(transport, Duration.ofSeconds(30))) {

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
        try (RuntimeServerProcess server = RUNTIME.spawn();
                CefTransport transport = server.connect();
                CefSession session = new CefSessionImpl(transport, Duration.ofSeconds(30))) {

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

            String url = TestDeadline.after(Duration.ofSeconds(20))
                    .poll(
                            () -> {
                                try (Frame current = browser.getMainFrame().get(5, TimeUnit.SECONDS)) {
                                    return current.getUrl().get(5, TimeUnit.SECONDS);
                                }
                            },
                            candidate -> candidate.startsWith("data:text/html"),
                            Duration.ofMillis(100),
                            "frame URL update");
            assertThat(url).startsWith("data:text/html");
        }
    }

    @Test
    void twoConcurrentBrowsersHaveIndependentState() throws Exception {
        try (RuntimeServerProcess server = RUNTIME.spawn();
                CefTransport transport = server.connect();
                CefSession session = new CefSessionImpl(transport, Duration.ofSeconds(30))) {

            LinkedBlockingQueue<RemoteHandle> handles = new LinkedBlockingQueue<>();
            session.onLatest(
                    LifeSpanHandlerOnAfterCreatedEvent.MESSAGE_ID,
                    LifeSpanHandlerOnAfterCreatedEvent.DECODER,
                    ev -> handles.offer(ev.browser()));

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

            String urlOnA = pollFrameUrl(browserA, "data:text/html,<html><body>A");
            String urlOnB = pollFrameUrl(browserB, "data:text/html,<html><body>B");
            assertThat(urlOnA).contains("A</body></html>");
            assertThat(urlOnB).contains("B</body></html>");
            assertThat(urlOnA).doesNotContain("B</body>");
            assertThat(urlOnB).doesNotContain("A</body>");
        }
    }

    private static String pollFrameUrl(Browser browser, String expectedPrefix) throws Exception {
        return TestDeadline.after(Duration.ofSeconds(20))
                .poll(
                        () -> {
                            try (Frame current = browser.getMainFrame().get(5, TimeUnit.SECONDS)) {
                                return current.getUrl().get(5, TimeUnit.SECONDS);
                            }
                        },
                        url -> url.startsWith(expectedPrefix),
                        Duration.ofMillis(100),
                        "frame URL update");
    }

    @Test
    void createBrowserMintsAdditionalHandle() throws Exception {
        try (RuntimeServerProcess server = RUNTIME.spawn();
                CefTransport transport = server.connect();
                CefSession session = new CefSessionImpl(transport, Duration.ofSeconds(30))) {

            LinkedBlockingQueue<RemoteHandle> handles = new LinkedBlockingQueue<>();
            session.onLatest(
                    LifeSpanHandlerOnAfterCreatedEvent.MESSAGE_ID,
                    LifeSpanHandlerOnAfterCreatedEvent.DECODER,
                    ev -> handles.offer(ev.browser()));

            RemoteHandle bootstrap = handles.poll(20, TimeUnit.SECONDS);
            assertThat(bootstrap).isNotNull();

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
