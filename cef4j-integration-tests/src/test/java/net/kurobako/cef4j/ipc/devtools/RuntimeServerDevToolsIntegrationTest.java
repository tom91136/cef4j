package net.kurobako.cef4j.ipc.devtools;

import static org.assertj.core.api.Assertions.assertThat;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.time.Duration;
import java.util.Base64;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalLong;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import net.kurobako.cef4j.cdp.CdpClient;
import net.kurobako.cef4j.cdp.CdpSubscription;
import net.kurobako.cef4j.cdp.generated.DOM;
import net.kurobako.cef4j.cdp.generated.Fetch;
import net.kurobako.cef4j.cdp.generated.Input;
import net.kurobako.cef4j.cdp.generated.Network;
import net.kurobako.cef4j.cdp.generated.Page;
import net.kurobako.cef4j.cdp.generated.Runtime;
import net.kurobako.cef4j.cdp.gson.GsonCdpCodec;
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
import net.kurobako.cef4j.test.RuntimeServerTestEnvironment;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/** Exercises generated CDP commands and callbacks against a real packaged CEF runtime. */
@Timeout(120)
class RuntimeServerDevToolsIntegrationTest {
    private static final Duration EVENT_TIMEOUT = Duration.ofSeconds(20);
    private static Path serverBinary;
    private static Path cefResources;

    @BeforeAll
    static void resolveBinary() {
        RuntimeServerTestEnvironment environment = RuntimeServerTestEnvironment.require();
        serverBinary = environment.binary();
        cefResources = environment.resources();
    }

    static List<RuntimeCase> transports() {
        return List.of(
                new RuntimeCase("zmq + mmap", "zmq", "tcp://127.0.0.1:0", "mmap"),
                new RuntimeCase("websocket + inline", "websocket", "ws://127.0.0.1:0/cef4j", "inline"),
                new RuntimeCase("platform-local + inline", "local", localEndpoint(), "inline"));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("transports")
    void generatedCdpDrivesNavigationInspectionInputCallbacksAndInterception(RuntimeCase runtime) throws Exception {
        String run = UUID.randomUUID().toString();
        try (FixtureSite site = FixtureSite.start();
                RuntimeServerProcess server = startServer(runtime);
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
            DevToolsSession devTools = DevToolsSession.attach(
                            session, browser.handle(), browser.getHost().get(5, TimeUnit.SECONDS), new GsonCdpCodec())
                    .get(10, TimeUnit.SECONDS);
            CdpClient cdp = new CdpClient(devTools, new GsonCdpCodec());
            try {
                exerciseGeneratedContract(cdp, site, run);
            } finally {
                devTools.closeAsync().toCompletableFuture().get(10, TimeUnit.SECONDS);
            }
            assertThat(server.transport()).isEqualTo(runtime.transport);
        }
    }

    @SuppressWarnings("try") // Named resources exist solely to unregister typed CDP callbacks on scope exit.
    private static void exerciseGeneratedContract(CdpClient cdp, FixtureSite site, String run) throws Exception {
        Page.Client page = cdp.domains().page();
        Network.Client network = cdp.domains().network();
        Runtime.Client runtime = cdp.domains().runtime();
        DOM.Client dom = cdp.domains().dOM();
        Input.Client input = cdp.domains().input();
        Fetch.Client fetch = cdp.domains().fetch();

        EventProbe<Page.FrameNavigatedEvent> frames = new EventProbe<>("Page.frameNavigated");
        EventProbe<Page.LoadEventFiredEvent> loads = new EventProbe<>("Page.loadEventFired");
        EventProbe<Network.RequestWillBeSentEvent> requests = new EventProbe<>("Network.requestWillBeSent");
        EventProbe<Network.ResponseReceivedEvent> responses = new EventProbe<>("Network.responseReceived");
        EventProbe<Runtime.ConsoleAPICalledEvent> console = new EventProbe<>("Runtime.consoleAPICalled");
        EventProbe<Runtime.ExceptionThrownEvent> exceptions = new EventProbe<>("Runtime.exceptionThrown");
        AtomicInteger consoleDeliveries = new AtomicInteger();

        CdpSubscription consoleSubscription = runtime.onConsoleAPICalled(event -> {
            consoleDeliveries.incrementAndGet();
            console.add(event);
        });
        try (CdpSubscription ignoredFrames = page.onFrameNavigated(frames::add);
                CdpSubscription ignoredLoads = page.onLoadEventFired(loads::add);
                CdpSubscription ignoredRequests = network.onRequestWillBeSent(requests::add);
                CdpSubscription ignoredResponses = network.onResponseReceived(responses::add);
                CdpSubscription ignoredExceptions = runtime.onExceptionThrown(exceptions::add)) {
            get(page.enable());
            get(network.enable());
            get(runtime.enable());
            get(dom.enable());

            URI pageUri = site.uri("/cdp/automation.html?run=" + run);
            Page.NavigateResult navigation = get(page.navigate(pageUri.toString()));
            assertThat(navigation.errorText()).isEmpty();
            assertThat(navigation.frameId().value()).isNotBlank();

            Page.FrameNavigatedEvent navigated = frames.await(
                    event -> event.frame() != null
                            && pageUri.toString().equals(event.frame().url()),
                    EVENT_TIMEOUT);
            assertThat(navigated.frame().loaderId().value()).isNotBlank();
            loads.await(event -> true, EVENT_TIMEOUT);

            Network.RequestWillBeSentEvent documentRequest =
                    requests.await(event -> requestUrl(event).equals(pageUri.toString()), EVENT_TIMEOUT);
            assertThat(documentRequest.type()).hasValue(Network.ResourceType.DOCUMENT);
            Network.ResponseReceivedEvent documentResponse =
                    responses.await(event -> responseUrl(event).equals(pageUri.toString()), EVENT_TIMEOUT);
            assertThat(documentResponse.response().status()).isEqualTo(200L);
            assertThat(documentResponse.response().mimeType()).isEqualTo("text/html");
            requests.await(event -> requestUrl(event).contains("/cdp/automation.js"), EVENT_TIMEOUT);
            requests.await(event -> requestUrl(event).contains("/cdp/pixel.png"), EVENT_TIMEOUT);

            Runtime.ConsoleAPICalledEvent ready = console.await(
                    event -> consoleContains(event, "cef4j-fixture-ready") && consoleContains(event, run),
                    EVENT_TIMEOUT);
            assertThat(ready.type()).isEqualTo(Runtime.ConsoleAPICalledEvent.TypeValues.LOG);

            DOM.Node document = get(dom.getDocument(new DOM.GetDocumentRequest().depth(2L)));
            DOM.NodeId rootId = document.nodeId();
            DOM.NodeId inputId = query(dom, rootId, "#name-input");
            DOM.NodeId buttonId = query(dom, rootId, "#action-button");
            List<String> buttonAttributes = get(dom.getAttributes(buttonId));
            assertThat(buttonAttributes).contains("id", "action-button", "data-action", "increment");

            get(dom.focus(new DOM.FocusRequest().nodeId(inputId)));
            get(input.insertText("typed through CDP"));
            assertThat(remoteValue(evaluate(runtime, "document.querySelector('#name-input').value", true, false)))
                    .isEqualTo("typed through CDP");
            assertThat(remoteValue(
                            evaluate(runtime, "document.querySelector('#typed-output').textContent", true, false)))
                    .isEqualTo("typed through CDP");

            DOM.BoxModel model = get(dom.getBoxModel(new DOM.GetBoxModelRequest().nodeId(buttonId)));
            double[] center = quadCenter(model.content());
            dispatchMouse(input, "mouseMoved", center[0], center[1], "none", 0L, 0L);
            dispatchMouse(input, "mousePressed", center[0], center[1], "left", 1L, 1L);
            dispatchMouse(input, "mouseReleased", center[0], center[1], "left", 1L, 0L);

            Runtime.ConsoleAPICalledEvent clicked = console.await(
                    event -> consoleContains(event, "cef4j-fixture-click") && consoleContains(event, run),
                    EVENT_TIMEOUT);
            assertThat(clicked.args()).hasSizeGreaterThanOrEqualTo(3);
            requests.await(event -> requestUrl(event).contains("/cdp/api/click?run=" + run), EVENT_TIMEOUT);
            Object clickState = remoteValue(evaluate(
                    runtime,
                    "(async()=>{while(!window.fixtureState.clickResponse) await new Promise(r=>setTimeout(r,10));"
                            + "return window.fixtureState})()",
                    true,
                    true));
            assertThat(Objects.requireNonNull(clickState).toString()).contains("clicked-1", "typed through CDP");

            exerciseFetchInterception(fetch, runtime, site, run);

            Object delayed = remoteValue(evaluate(runtime, "window.delayedFixtureValue()", true, true));
            assertThat(Objects.requireNonNull(delayed).toString()).contains("answer=42", run);
            evaluate(runtime, "setTimeout(()=>window.throwFixtureError(), 0); 'scheduled'", true, false);
            Runtime.ExceptionThrownEvent thrown =
                    exceptions.await(event -> event.exceptionDetails().text().contains("Uncaught"), EVENT_TIMEOUT);
            assertThat(thrown.exceptionDetails().exception().isPresent()).isTrue();

            String screenshot = get(page.captureScreenshot(
                    Optional.of(Page.CaptureScreenshotFormatValues.PNG),
                    OptionalLong.empty(),
                    Optional.empty(),
                    Optional.of(true),
                    Optional.empty(),
                    Optional.empty()));
            assertThat(Base64.getDecoder().decode(screenshot)).startsWith(0x89, 0x50, 0x4e, 0x47);

            consoleSubscription.close();
            int deliveriesBeforeUnsubscribe = consoleDeliveries.get();
            evaluate(runtime, "console.log('cef4j-after-unsubscribe', '" + run + "')", true, false);
            evaluate(runtime, "1", true, false); // Response ordering barrier for the preceding console event.
            assertThat(consoleDeliveries.get()).isEqualTo(deliveriesBeforeUnsubscribe);
        } finally {
            consoleSubscription.close();
        }
    }

    @SuppressWarnings("try") // The named resource unregisters the generated Fetch callback on scope exit.
    private static void exerciseFetchInterception(
            Fetch.Client fetch, Runtime.Client runtime, FixtureSite site, String run) throws Exception {
        EventProbe<Fetch.RequestPausedEvent> paused = new EventProbe<>("Fetch.requestPaused");
        AtomicReference<Throwable> callbackFailure = new AtomicReference<>();
        List<CompletableFuture<?>> callbackActions = new CopyOnWriteArrayList<>();
        String continuedUrl = site.uri("/cdp/api/continued?run=" + run).toString();
        String mockUrl = site.uri("/cdp/api/mock?run=" + run).toString();
        String mockBody = Base64.getEncoder()
                .encodeToString(("{\"source\":\"cdp-mock\",\"run\":\"" + run + "\"}").getBytes(StandardCharsets.UTF_8));

        try (CdpSubscription ignored = fetch.onRequestPaused(event -> {
            paused.add(event);
            Network.Request request = event.request();
            if (request == null || event.requestId() == null) return;
            CompletableFuture<?> action;
            if (continuedUrl.equals(request.url())) {
                action = fetch.continueRequest(event.requestId()).toCompletableFuture();
            } else if (mockUrl.equals(request.url())) {
                action = fetch.fulfillRequest(
                                event.requestId(),
                                200L,
                                Optional.of(List.of(
                                        new Fetch.HeaderEntry()
                                                .name("Content-Type")
                                                .value("application/json; charset=utf-8"),
                                        new Fetch.HeaderEntry()
                                                .name("X-Cef4j-Source")
                                                .value("generated-cdp"))),
                                Optional.empty(),
                                Optional.of(mockBody),
                                Optional.empty())
                        .toCompletableFuture();
            } else {
                return;
            }
            CompletableFuture<?> observed = action.whenComplete((value, failure) -> {
                if (failure != null) callbackFailure.compareAndSet(null, failure);
            });
            callbackActions.add(observed);
        })) {
            get(fetch.enable(
                    Optional.of(List.of(new Fetch.RequestPattern()
                            .urlPattern(site.origin() + "/cdp/api/*")
                            .requestStage(Fetch.RequestStage.REQUEST))),
                    Optional.empty()));

            Object continued = remoteValue(evaluate(runtime, "window.loadContinuedResource()", true, true));
            paused.await(
                    event -> event.request() != null
                            && continuedUrl.equals(event.request().url()),
                    EVENT_TIMEOUT);
            assertThat(Objects.requireNonNull(continued).toString()).contains("fixture-server", run);
            assertThat(site.sawPath("/cdp/api/continued", run)).isTrue();

            Object mocked = remoteValue(evaluate(runtime, "window.loadMockResource()", true, true));
            paused.await(
                    event -> event.request() != null
                            && mockUrl.equals(event.request().url()),
                    EVENT_TIMEOUT);
            assertThat(Objects.requireNonNull(mocked).toString()).contains("cdp-mock", run);
            assertThat(site.sawPath("/cdp/api/mock", run)).isFalse();
            for (CompletableFuture<?> callbackAction : callbackActions) {
                callbackAction.get(20, TimeUnit.SECONDS);
            }
            assertThat(callbackFailure.get()).isNull();
        } finally {
            get(fetch.disable());
        }
    }

    private static Runtime.EvaluateResult evaluate(
            Runtime.Client runtime, String expression, boolean returnByValue, boolean awaitPromise) throws Exception {
        return get(runtime.evaluate(new Runtime.EvaluateRequest(expression)
                .returnByValue(returnByValue)
                .awaitPromise(awaitPromise)));
    }

    @Nullable
    private static Object remoteValue(Runtime.EvaluateResult result) {
        assertThat(result.exceptionDetails()).isEmpty();
        return result.result().value().orElse(null);
    }

    private static DOM.NodeId query(DOM.Client dom, DOM.NodeId rootId, String selector) throws Exception {
        DOM.NodeId nodeId = get(dom.querySelector(new DOM.QuerySelectorRequest(rootId, selector)));
        assertThat(nodeId.value()).as(selector).isPositive();
        return nodeId;
    }

    private static double[] quadCenter(List<Double> quad) {
        assertThat(quad).hasSize(8);
        return new double[] {
            (quad.get(0) + quad.get(2) + quad.get(4) + quad.get(6)) / 4,
            (quad.get(1) + quad.get(3) + quad.get(5) + quad.get(7)) / 4
        };
    }

    private static void dispatchMouse(
            Input.Client input, String type, double x, double y, String button, long clickCount, long buttons)
            throws Exception {
        Input.DispatchMouseEventRequest request =
                new Input.DispatchMouseEventRequest(Input.DispatchMouseEventTypeValues.of(type), x, y).buttons(buttons);
        if (!"none".equals(button)) {
            request.button(Input.MouseButton.of(button));
        }
        if (clickCount > 0) {
            request.clickCount(clickCount);
        }
        get(input.dispatchMouseEvent(request));
    }

    private static boolean consoleContains(Runtime.ConsoleAPICalledEvent event, Object value) {
        return event.args().stream()
                .anyMatch(arg -> arg.value().map(v -> Objects.equals(v, value)).orElse(false));
    }

    private static String requestUrl(Network.RequestWillBeSentEvent event) {
        Network.Request request = event.request();
        return request == null || request.url() == null ? "" : request.url();
    }

    private static String responseUrl(Network.ResponseReceivedEvent event) {
        Network.Response response = event.response();
        return response == null || response.url() == null ? "" : response.url();
    }

    private static <T> T get(java.util.concurrent.CompletionStage<T> stage) throws Exception {
        return stage.toCompletableFuture().get(20, TimeUnit.SECONDS);
    }

    private static RuntimeServerProcess startServer(RuntimeCase runtime) throws IOException {
        return RuntimeServerProcess.spawn(
                serverBinary,
                runtime.transport,
                runtime.endpoint,
                runtime.frameTransport,
                Duration.ofSeconds(30),
                net.kurobako.cef4j.ipc.frame.RemoteCefBrowserBackend.runtimeEnvironment(cefResources));
    }

    private static String localEndpoint() {
        return isWindows() ? "pipe://cef4j-cdp-" + Long.toUnsignedString(System.nanoTime()) : "tcp://127.0.0.1:0";
    }

    private static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase(Locale.ROOT).contains("win");
    }

    private static final class RuntimeCase {
        private final String name;
        private final String transport;
        private final String endpoint;
        private final String frameTransport;

        private RuntimeCase(String name, String transport, String endpoint, String frameTransport) {
            this.name = name;
            this.transport = transport;
            this.endpoint = endpoint;
            this.frameTransport = frameTransport;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    private static final class EventProbe<T> {
        private final String name;
        private final BlockingQueue<T> pending = new LinkedBlockingQueue<>();
        private final List<T> history = new CopyOnWriteArrayList<>();

        private EventProbe(String name) {
            this.name = name;
        }

        private void add(T event) {
            history.add(event);
            pending.add(event);
        }

        private T await(Predicate<T> predicate, Duration timeout) throws InterruptedException {
            long deadline = System.nanoTime() + timeout.toNanos();
            while (System.nanoTime() < deadline) {
                for (T event : history) {
                    if (predicate.test(event)) return event;
                }
                long remaining = Math.max(1, deadline - System.nanoTime());
                if (pending.poll(remaining, TimeUnit.NANOSECONDS) == null) break;
            }
            throw new AssertionError(
                    "Timed out waiting for " + name + "; observed " + history.size() + " events: " + history);
        }
    }

    private static final class FixtureSite implements AutoCloseable {
        private static final byte[] PIXEL = Base64.getDecoder()
                .decode("iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAQAAAC1HAwCAAAAC0lEQVR42mP8/x8AAusB9Y9ZPWQAAAAASUVORK5CYII=");
        private final HttpServer server;
        private final ExecutorService executor;
        private final List<URI> requests = new CopyOnWriteArrayList<>();

        private FixtureSite(HttpServer server, ExecutorService executor) {
            this.server = server;
            this.executor = executor;
        }

        private static FixtureSite start() throws IOException {
            HttpServer server = HttpServer.create(new InetSocketAddress(InetAddress.getLoopbackAddress(), 0), 0);
            ExecutorService executor = Executors.newCachedThreadPool();
            FixtureSite site = new FixtureSite(server, executor);
            server.setExecutor(executor);
            server.createContext("/", site::handle);
            server.start();
            return site;
        }

        private void handle(HttpExchange exchange) throws IOException {
            requests.add(exchange.getRequestURI());
            String path = exchange.getRequestURI().getPath();
            if (path.equals("/cdp/automation.html")) {
                respond(exchange, 200, "text/html; charset=utf-8", resource("/cdp/automation.html"));
            } else if (path.equals("/cdp/automation.js")) {
                respond(exchange, 200, "text/javascript; charset=utf-8", resource("/cdp/automation.js"));
            } else if (path.equals("/cdp/pixel.png")) {
                respond(exchange, 200, "image/png", PIXEL);
            } else if (path.equals("/cdp/api/click")) {
                String count = queryValue(exchange.getRequestURI(), "count");
                respondJson(exchange, "{\"message\":\"clicked-" + count + "\",\"source\":\"fixture-server\"}");
            } else if (path.equals("/cdp/api/continued")) {
                respondJson(
                        exchange,
                        "{\"source\":\"fixture-server\",\"run\":\"" + queryValue(exchange.getRequestURI(), "run")
                                + "\"}");
            } else if (path.equals("/cdp/api/mock")) {
                respondJson(exchange, "{\"source\":\"unexpected-server-hit\"}");
            } else {
                respond(exchange, 404, "text/plain; charset=utf-8", "not found".getBytes(StandardCharsets.UTF_8));
            }
        }

        private String origin() {
            return "http://127.0.0.1:" + server.getAddress().getPort();
        }

        private URI uri(String path) {
            return URI.create(origin() + path);
        }

        private boolean sawPath(String path, String run) {
            return requests.stream().anyMatch(uri -> path.equals(uri.getPath()) && run.equals(queryValue(uri, "run")));
        }

        private static byte[] resource(String name) throws IOException {
            try (InputStream stream = RuntimeServerDevToolsIntegrationTest.class.getResourceAsStream(name)) {
                if (stream == null) throw new IOException("missing test resource " + name);
                return stream.readAllBytes();
            }
        }

        private static void respondJson(HttpExchange exchange, String body) throws IOException {
            respond(exchange, 200, "application/json; charset=utf-8", body.getBytes(StandardCharsets.UTF_8));
        }

        private static void respond(HttpExchange exchange, int status, String contentType, byte[] body)
                throws IOException {
            exchange.getResponseHeaders().set("Content-Type", contentType);
            exchange.getResponseHeaders().set("Cache-Control", "no-store");
            exchange.getResponseHeaders().set("X-Cef4j-Fixture", "generated-cdp");
            exchange.sendResponseHeaders(status, body.length);
            exchange.getResponseBody().write(body);
            exchange.close();
        }

        private static String queryValue(URI uri, String name) {
            String query = uri.getRawQuery();
            if (query == null) return "";
            int start = 0;
            while (start <= query.length()) {
                int end = query.indexOf('&', start);
                if (end < 0) end = query.length();
                String part = query.substring(start, end);
                int separator = part.indexOf('=');
                if (separator >= 0 && part.substring(0, separator).equals(name)) {
                    return java.net.URLDecoder.decode(part.substring(separator + 1), StandardCharsets.UTF_8);
                }
                if (end == query.length()) break;
                start = end + 1;
            }
            return "";
        }

        @Override
        public void close() {
            server.stop(0);
            executor.shutdownNow();
        }
    }
}
