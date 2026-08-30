package net.kurobako.cef4j.webdriver;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URI;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Embeddable W3C WebDriver Classic HTTP remote end. */
public final class WebDriverServer implements AutoCloseable {
    public static final String ELEMENT_KEY = "element-6066-11e4-a52e-4f735466cecf";
    private static final Logger LOG = LoggerFactory.getLogger(WebDriverServer.class);
    private static final int DEFAULT_BACKLOG = 32;
    private static final int MAX_REQUEST_BYTES = 1024 * 1024;
    private static final Duration DEFAULT_COMMAND_TIMEOUT = Duration.ofSeconds(60);

    private static final Set<String> STANDARD_CAPABILITIES = Set.of(
            "acceptInsecureCerts",
            "browserName",
            "browserVersion",
            "pageLoadStrategy",
            "platformName",
            "proxy",
            "setWindowRect",
            "strictFileInteractability",
            "timeouts",
            "unhandledPromptBehavior",
            "webSocketUrl");

    private final HttpServer server;

    @Nullable
    private final ExecutorService ownedExecutor;

    private final AutomationBackendFactory backendFactory;
    private final WebDriverJsonCodec jsonCodec;
    private final Duration commandTimeout;
    private final Map<String, ActiveSession> sessions = new ConcurrentHashMap<>();
    private final Object creationLock = new Object();
    private final AtomicBoolean closed = new AtomicBoolean();
    private volatile boolean creatingSession;

    /** Starts a loopback-only endpoint on an ephemeral port. */
    @Nonnull
    public static WebDriverServer start(@Nonnull AutomationBackendFactory backendFactory) throws IOException {
        return start(backendFactory, WebDriverJsonCodec.installed());
    }

    /** Starts a loopback-only endpoint with an explicitly selected JSON codec. */
    @Nonnull
    public static WebDriverServer start(
            @Nonnull AutomationBackendFactory backendFactory, @Nonnull WebDriverJsonCodec jsonCodec)
            throws IOException {
        return start(
                new InetSocketAddress(InetAddress.getLoopbackAddress(), 0),
                backendFactory,
                DEFAULT_COMMAND_TIMEOUT,
                jsonCodec);
    }

    /** Starts an endpoint at the explicitly supplied address. Callers own the security implications of non-loopback. */
    @Nonnull
    public static WebDriverServer start(
            @Nonnull InetSocketAddress bindAddress,
            @Nonnull AutomationBackendFactory backendFactory,
            @Nonnull Duration commandTimeout)
            throws IOException {
        return start(bindAddress, backendFactory, commandTimeout, WebDriverJsonCodec.installed());
    }

    /** Starts an endpoint with an explicitly selected JSON codec. */
    @Nonnull
    public static WebDriverServer start(
            @Nonnull InetSocketAddress bindAddress,
            @Nonnull AutomationBackendFactory backendFactory,
            @Nonnull Duration commandTimeout,
            @Nonnull WebDriverJsonCodec jsonCodec)
            throws IOException {
        Objects.requireNonNull(bindAddress, "bindAddress");
        Objects.requireNonNull(backendFactory, "backendFactory");
        Objects.requireNonNull(commandTimeout, "commandTimeout");
        Objects.requireNonNull(jsonCodec, "jsonCodec");
        if (commandTimeout.isZero() || commandTimeout.isNegative()) {
            throw new IllegalArgumentException("commandTimeout must be positive");
        }
        ExecutorService workers = Executors.newFixedThreadPool(
                Math.max(2, Runtime.getRuntime().availableProcessors()), daemonThreads("cef4j-webdriver-http"));
        try {
            return start(bindAddress, backendFactory, commandTimeout, jsonCodec, workers, workers);
        } catch (IOException | RuntimeException failure) {
            workers.shutdownNow();
            throw failure;
        }
    }

    @Nonnull
    public static WebDriverServer start(
            @Nonnull InetSocketAddress bindAddress,
            @Nonnull AutomationBackendFactory backendFactory,
            @Nonnull Duration commandTimeout,
            @Nonnull WebDriverJsonCodec jsonCodec,
            @Nonnull Executor executor)
            throws IOException {
        return start(bindAddress, backendFactory, commandTimeout, jsonCodec, executor, null);
    }

    private static WebDriverServer start(
            InetSocketAddress bindAddress,
            AutomationBackendFactory backendFactory,
            Duration commandTimeout,
            WebDriverJsonCodec jsonCodec,
            Executor executor,
            @Nullable ExecutorService ownedExecutor)
            throws IOException {
        Objects.requireNonNull(bindAddress, "bindAddress");
        Objects.requireNonNull(backendFactory, "backendFactory");
        Objects.requireNonNull(commandTimeout, "commandTimeout");
        Objects.requireNonNull(jsonCodec, "jsonCodec");
        Objects.requireNonNull(executor, "executor");
        if (commandTimeout.isZero() || commandTimeout.isNegative()) {
            throw new IllegalArgumentException("commandTimeout must be positive");
        }
        HttpServer http = HttpServer.create(bindAddress, DEFAULT_BACKLOG);
        WebDriverServer result = new WebDriverServer(http, ownedExecutor, backendFactory, commandTimeout, jsonCodec);
        try {
            http.createContext("/", result::handle);
            http.setExecutor(executor);
            http.start();
        } catch (RuntimeException failure) {
            http.stop(0);
            throw failure;
        }
        return result;
    }

    private WebDriverServer(
            HttpServer server,
            @Nullable ExecutorService ownedExecutor,
            AutomationBackendFactory backendFactory,
            Duration commandTimeout,
            WebDriverJsonCodec jsonCodec) {
        this.server = server;
        this.ownedExecutor = ownedExecutor;
        this.backendFactory = backendFactory;
        this.commandTimeout = commandTimeout;
        this.jsonCodec = jsonCodec;
    }

    /** Base URI consumed by Selenium's RemoteWebDriver. */
    @Nonnull
    public URI endpoint() {
        InetSocketAddress address = server.getAddress();
        String host = address.getAddress().getHostAddress();
        if (host.indexOf(':') >= 0) host = "[" + host + "]";
        return URI.create("http://" + host + ":" + address.getPort());
    }

    private void handle(HttpExchange exchange) {
        try {
            route(exchange);
        } catch (WebDriverException e) {
            sendError(
                    exchange,
                    e.error(),
                    Objects.requireNonNull(e.getMessage(), e.error().code()));
        } catch (RuntimeException e) {
            LOG.warn("unhandled WebDriver request failure", e);
            sendError(exchange, WebDriverError.UNKNOWN_ERROR, "internal WebDriver server error");
        } catch (IOException e) {
            LOG.debug("WebDriver connection closed while handling request: {}", e.toString());
        } finally {
            exchange.close();
        }
    }

    private void route(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();
        WebDriverRoute.Match route = WebDriverRoute.match(method, path)
                .orElseThrow(() -> error(WebDriverError.UNKNOWN_COMMAND, "unknown command: " + method + " " + path));
        if (route.command() == WebDriverRoute.Command.STATUS) {
            JsonObject status = new JsonObject();
            status.addProperty("ready", !closed.get() && !creatingSession && sessions.isEmpty());
            status.addProperty("message", sessions.isEmpty() ? "cef4j WebDriver is ready" : "a session is active");
            sendSuccess(exchange, status);
            return;
        }
        if (route.command() == WebDriverRoute.Command.NEW_SESSION) {
            createSession(exchange, parseObjectBody(exchange));
            return;
        }
        String sessionId = route.parameter("session");
        ActiveSession session = sessions.get(sessionId);
        if (session == null) {
            throw error(WebDriverError.INVALID_SESSION_ID, "unknown session: " + sessionId);
        }
        if (route.command() == WebDriverRoute.Command.DELETE_SESSION) {
            sessions.remove(session.id, session);
            session.close();
            sendSuccess(exchange, JsonNull.INSTANCE);
            return;
        }
        routeSessionCommand(exchange, route, session);
    }

    private void createSession(HttpExchange exchange, JsonObject body) throws IOException {
        List<JsonObject> candidates = matchCapabilities(body);
        synchronized (creationLock) {
            if (closed.get() || creatingSession || !sessions.isEmpty()) {
                throw error(WebDriverError.SESSION_NOT_CREATED, "this endpoint already owns an active session");
            }
            creatingSession = true;
        }

        AutomationBackend backend = null;
        try {
            JsonObject requested = null;
            WebDriverException mismatch = null;
            for (JsonObject candidate : candidates) {
                CompletableFuture<? extends AutomationBackend> creation;
                try {
                    creation = Objects.requireNonNull(
                            backendFactory.create(candidate), "backend factory returned null future");
                } catch (RuntimeException failure) {
                    throw new WebDriverException(
                            WebDriverError.SESSION_NOT_CREATED,
                            "backend factory failed: " + describe(failure),
                            failure);
                }
                try {
                    backend = await(creation, WebDriverError.SESSION_NOT_CREATED, commandTimeout, false, null);
                } catch (RuntimeException failure) {
                    CompletableFuture<?> cleanup = creation.whenComplete((lateBackend, ignored) -> {
                        if (lateBackend != null) closeQuietly(lateBackend);
                    });
                    cleanup.isDone();
                    throw failure;
                }
                JsonObject actual = normalizeActualCapabilities(candidate, backend.capabilities());
                if (matchesRequestedCapabilities(candidate, actual)) {
                    requested = candidate;
                    break;
                }
                closeQuietly(backend);
                backend = null;
                mismatch = error(WebDriverError.SESSION_NOT_CREATED, "backend does not satisfy requested capabilities");
            }
            if (backend == null || requested == null) throw Objects.requireNonNull(mismatch);
            if (closed.get()) {
                backend.close();
                throw error(WebDriverError.SESSION_NOT_CREATED, "WebDriver server closed during session creation");
            }
            String id = UUID.randomUUID().toString();
            JsonObject actual = normalizeActualCapabilities(requested, backend.capabilities());
            ActiveSession active = new ActiveSession(id, backend, actual.object("timeouts"));
            sessions.put(id, active);
            backend = null;
            if (closed.get() && sessions.remove(id, active)) {
                active.close();
                throw error(WebDriverError.SESSION_NOT_CREATED, "WebDriver server closed during session creation");
            }

            JsonObject value = new JsonObject();
            value.addProperty("sessionId", id);
            value.add("capabilities", actual);
            sendSuccess(exchange, value);
        } finally {
            synchronized (creationLock) {
                creatingSession = false;
            }
            if (backend != null) closeQuietly(backend);
        }
    }

    private void routeSessionCommand(HttpExchange exchange, WebDriverRoute.Match route, ActiveSession session)
            throws IOException {
        AutomationBackend backend = session.backend;
        switch (route.command()) {
            case CURRENT_URL:
                sendSuccess(exchange, string(command(session, backend::currentUrl)));
                return;
            case NAVIGATE:
                JsonObject body = parseObjectBody(exchange);
                String url = requiredString(body, "url");
                command(session, "pageLoad", () -> backend.navigate(url));
                sendSuccess(exchange, JsonNull.INSTANCE);
                return;
            case TITLE:
                sendSuccess(exchange, string(command(session, backend::title)));
                return;
            case SOURCE:
                sendSuccess(exchange, string(command(session, backend::pageSource)));
                return;
            case SCREENSHOT:
                byte[] png = command(session, backend::screenshot);
                sendSuccess(exchange, string(Base64.getEncoder().encodeToString(png)));
                return;
            case EXECUTE_SCRIPT:
                JsonObject scriptBody = parseObjectBody(exchange);
                String script = requiredString(scriptBody, "script");
                JsonArray arguments = requiredArray(scriptBody, "args");
                sendSuccess(
                        exchange,
                        command(session, "script", () -> backend.executeScript(script, arguments.deepCopy())));
                return;
            case BACK:
                parseObjectBody(exchange);
                command(session, "pageLoad", backend::back);
                sendSuccess(exchange, JsonNull.INSTANCE);
                return;
            case FORWARD:
                parseObjectBody(exchange);
                command(session, "pageLoad", backend::forward);
                sendSuccess(exchange, JsonNull.INSTANCE);
                return;
            case REFRESH:
                parseObjectBody(exchange);
                command(session, "pageLoad", backend::refresh);
                sendSuccess(exchange, JsonNull.INSTANCE);
                return;
            case GET_TIMEOUTS:
                sendSuccess(exchange, session.timeouts());
                return;
            case SET_TIMEOUTS:
                JsonObject timeouts = parseObjectBody(exchange);
                validateTimeouts(timeouts);
                session.updateTimeouts(timeouts);
                sendSuccess(exchange, JsonNull.INSTANCE);
                return;
            case GET_COOKIES:
                sendSuccess(exchange, command(session, backend::cookies));
                return;
            case ADD_COOKIE:
                JsonObject cookieBody = parseObjectBody(exchange);
                command(
                        session,
                        () -> backend.addCookie(
                                requiredObject(cookieBody, "cookie").deepCopy()));
                sendSuccess(exchange, JsonNull.INSTANCE);
                return;
            case DELETE_COOKIES:
                command(session, backend::deleteAllCookies);
                sendSuccess(exchange, JsonNull.INSTANCE);
                return;
            case GET_COOKIE:
                sendSuccess(exchange, command(session, () -> backend.cookie(route.parameter("name"))));
                return;
            case DELETE_COOKIE:
                command(session, () -> backend.deleteCookie(route.parameter("name")));
                sendSuccess(exchange, JsonNull.INSTANCE);
                return;
            case FIND_ELEMENT:
            case FIND_ELEMENTS:
                findElements(exchange, route.command(), session, backend, Optional.empty());
                return;
            case ACTIVE_ELEMENT:
                sendSuccess(exchange, elementReference(command(session, backend::activeElement)));
                return;
            case FIND_CHILD_ELEMENT:
            case FIND_CHILD_ELEMENTS:
                findElements(exchange, route.command(), session, backend, Optional.of(route.parameter("element")));
                return;
            case ELEMENT_NAME:
                sendSuccess(
                        exchange, string(command(session, () -> backend.elementTagName(route.parameter("element")))));
                return;
            case ELEMENT_TEXT:
                sendSuccess(exchange, string(command(session, () -> backend.elementText(route.parameter("element")))));
                return;
            case ELEMENT_RECT:
                sendSuccess(exchange, command(session, () -> backend.elementRect(route.parameter("element"))));
                return;
            case ELEMENT_DISPLAYED:
                sendSuccess(
                        exchange,
                        booleanValue(command(session, () -> backend.elementDisplayed(route.parameter("element")))));
                return;
            case ELEMENT_ENABLED:
                sendSuccess(
                        exchange,
                        booleanValue(command(session, () -> backend.elementEnabled(route.parameter("element")))));
                return;
            case ELEMENT_SELECTED:
                sendSuccess(
                        exchange,
                        booleanValue(command(session, () -> backend.elementSelected(route.parameter("element")))));
                return;
            case ELEMENT_CLICK:
                parseObjectBody(exchange);
                command(session, () -> backend.elementClick(route.parameter("element")));
                sendSuccess(exchange, JsonNull.INSTANCE);
                return;
            case ELEMENT_CLEAR:
                parseObjectBody(exchange);
                command(session, () -> backend.elementClear(route.parameter("element")));
                sendSuccess(exchange, JsonNull.INSTANCE);
                return;
            case ELEMENT_VALUE:
                JsonObject valueBody = parseObjectBody(exchange);
                command(
                        session,
                        () -> backend.elementSendKeys(route.parameter("element"), requiredString(valueBody, "text")));
                sendSuccess(exchange, JsonNull.INSTANCE);
                return;
            case ELEMENT_ATTRIBUTE:
                sendSuccess(
                        exchange,
                        command(
                                session,
                                () -> backend.elementAttribute(route.parameter("element"), route.parameter("name"))));
                return;
            case ELEMENT_PROPERTY:
                sendSuccess(
                        exchange,
                        command(
                                session,
                                () -> backend.elementProperty(route.parameter("element"), route.parameter("name"))));
                return;
            case ELEMENT_CSS:
                sendSuccess(
                        exchange,
                        string(command(
                                session,
                                () -> backend.elementCssValue(route.parameter("element"), route.parameter("name")))));
                return;
            default:
                throw new AssertionError("non-session command reached session dispatch: " + route.command());
        }
    }

    private void findElements(
            HttpExchange exchange,
            WebDriverRoute.Command route,
            ActiveSession session,
            AutomationBackend backend,
            Optional<String> parent)
            throws IOException {
        JsonObject body = parseObjectBody(exchange);
        String using = requiredString(body, "using");
        String value = requiredString(body, "value");
        if (route == WebDriverRoute.Command.FIND_ELEMENT || route == WebDriverRoute.Command.FIND_CHILD_ELEMENT) {
            sendSuccess(
                    exchange, elementReference(findElementWithImplicitWait(session, backend, using, value, parent)));
        } else {
            sendSuccess(
                    exchange, elementReferences(findElementsWithImplicitWait(session, backend, using, value, parent)));
        }
    }

    private static JsonObject elementReference(String id) {
        JsonObject reference = new JsonObject();
        reference.addProperty(ELEMENT_KEY, id);
        return reference;
    }

    private static JsonArray elementReferences(java.util.List<String> ids) {
        JsonArray references = new JsonArray();
        for (String id : ids) references.add(elementReference(id));
        return references;
    }

    private static JsonElement booleanValue(boolean value) {
        return new JsonPrimitive(value);
    }

    private String findElementWithImplicitWait(
            ActiveSession session, AutomationBackend backend, String using, String value, Optional<String> parent) {
        java.util.List<String> found = findElementsWithImplicitWait(session, backend, using, value, parent);
        if (found.isEmpty()) throw error(WebDriverError.NO_SUCH_ELEMENT, "unable to locate element: " + value);
        return found.get(0);
    }

    private java.util.List<String> findElementsWithImplicitWait(
            ActiveSession session, AutomationBackend backend, String using, String value, Optional<String> parent) {
        long timeoutMillis = session.timeoutMillis("implicit");
        long started = System.nanoTime();
        long timeoutNanos = TimeUnit.MILLISECONDS.toNanos(timeoutMillis);
        do {
            java.util.List<String> found = command(session, () -> backend.findElements(using, value, parent));
            if (!found.isEmpty() || System.nanoTime() - started >= timeoutNanos) return found;
            try {
                Thread.sleep(Math.min(50L, Math.max(1L, timeoutMillis)));
            } catch (InterruptedException failure) {
                Thread.currentThread().interrupt();
                throw new WebDriverException(WebDriverError.UNKNOWN_ERROR, "element search interrupted", failure);
            }
        } while (true);
    }

    private <T> T command(ActiveSession session, Supplier<? extends CompletableFuture<T>> operation) {
        return command(session, null, operation);
    }

    private <T> T command(
            ActiveSession session, @Nullable String timeoutName, Supplier<? extends CompletableFuture<T>> operation) {
        synchronized (session.commandLock) {
            if (session.closed.get() || sessions.get(session.id) != session) {
                throw error(WebDriverError.INVALID_SESSION_ID, "session is closed: " + session.id);
            }
            Duration timeout = timeoutName == null ? commandTimeout : session.timeout(timeoutName);
            return await(
                    operation.get(),
                    WebDriverError.UNKNOWN_ERROR,
                    timeout,
                    true,
                    session.backend::cancelPendingCommands);
        }
    }

    private static <T> T await(
            CompletableFuture<? extends T> future,
            WebDriverError fallback,
            Duration timeout,
            boolean cancelOnTimeout,
            @Nullable java.util.function.Consumer<Throwable> pendingCancellation) {
        Objects.requireNonNull(future, "backend returned null future");
        try {
            return future.get(timeout.toMillis(), TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            WebDriverException failure =
                    new WebDriverException(WebDriverError.TIMEOUT, "command exceeded " + timeout, e);
            if (cancelOnTimeout) future.cancel(true);
            if (pendingCancellation != null) pendingCancellation.accept(failure);
            throw failure;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new WebDriverException(WebDriverError.UNKNOWN_ERROR, "command interrupted", e);
        } catch (CancellationException e) {
            throw new WebDriverException(fallback, "backend command cancelled", e);
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            if (cause instanceof WebDriverException) throw (WebDriverException) cause;
            if (cause == null) cause = e;
            throw new WebDriverException(fallback, "backend command failed: " + describe(cause), cause);
        }
    }

    private static String describe(Throwable failure) {
        Throwable current = failure;
        String description = current.getClass().getSimpleName();
        while (current != null) {
            if (current.getMessage() != null && !current.getMessage().isBlank()) {
                description = current.getClass().getSimpleName() + ": " + current.getMessage();
            }
            current = current.getCause();
        }
        return description;
    }

    private static List<JsonObject> matchCapabilities(JsonObject body) {
        JsonElement capabilitiesElement = body.get("capabilities");
        if (capabilitiesElement == null || !capabilitiesElement.isObject()) {
            throw error(WebDriverError.INVALID_ARGUMENT, "capabilities must be a JSON object");
        }
        JsonObject capabilities = capabilitiesElement.asObject();
        JsonObject always = optionalObject(capabilities, "alwaysMatch", new JsonObject());
        JsonArray first = optionalArray(capabilities, "firstMatch");
        if (first == null) {
            first = new JsonArray();
            first.add(new JsonObject());
        }
        if (first.size() == 0) {
            throw error(WebDriverError.INVALID_ARGUMENT, "firstMatch must contain at least one entry");
        }
        validateCapabilityObject(always);
        List<JsonObject> matches = new ArrayList<>();
        for (JsonElement candidateElement : first) {
            if (!candidateElement.isObject()) {
                throw error(WebDriverError.INVALID_ARGUMENT, "firstMatch entries must be JSON objects");
            }
            JsonObject candidate = candidateElement.asObject();
            validateCapabilityObject(candidate);
            JsonObject merged = always.deepCopy();
            boolean conflict = false;
            for (Map.Entry<String, JsonElement> entry : candidate.entrySet()) {
                if (merged.has(entry.getKey())) {
                    conflict = true;
                    break;
                }
                merged.add(entry.getKey(), entry.getValue().deepCopy());
            }
            if (conflict) {
                throw error(
                        WebDriverError.INVALID_ARGUMENT, "alwaysMatch and firstMatch contain duplicate capabilities");
            }
            if (matchesCef4j(merged)) matches.add(merged);
        }
        if (matches.isEmpty())
            throw error(WebDriverError.SESSION_NOT_CREATED, "no requested capability set matches cef4j");
        return matches;
    }

    private static boolean matchesCef4j(JsonObject capabilities) {
        JsonElement browserName = capabilities.get("browserName");
        return browserName == null
                || (browserName.isPrimitive()
                        && browserName.asPrimitive().isString()
                        && "cef4j".equals(browserName.string()));
    }

    private static void validateCapabilityObject(JsonObject capabilities) {
        for (Map.Entry<String, JsonElement> entry : capabilities.entrySet()) {
            String name = entry.getKey();
            if (!STANDARD_CAPABILITIES.contains(name) && name.indexOf(':') < 1) {
                throw error(WebDriverError.INVALID_ARGUMENT, "unrecognized capability: " + name);
            }
        }
        requireOptionalString(capabilities, "browserName");
        requireOptionalString(capabilities, "browserVersion");
        requireOptionalString(capabilities, "platformName");
        requireOptionalBoolean(capabilities, "acceptInsecureCerts");
        requireOptionalBoolean(capabilities, "setWindowRect");
        requireOptionalBoolean(capabilities, "strictFileInteractability");
        requireOptionalBoolean(capabilities, "webSocketUrl");
        JsonElement proxy = capabilities.get("proxy");
        if (proxy != null && !proxy.isObject()) {
            throw error(WebDriverError.INVALID_ARGUMENT, "proxy must be a JSON object");
        }
        requireOptionalString(capabilities, "unhandledPromptBehavior");
        JsonElement strategy = capabilities.get("pageLoadStrategy");
        if (strategy != null) {
            if (!strategy.isPrimitive() || !strategy.asPrimitive().isString()) {
                throw error(WebDriverError.INVALID_ARGUMENT, "pageLoadStrategy must be a string");
            }
            String value = strategy.string();
            if (!"none".equals(value) && !"eager".equals(value) && !"normal".equals(value)) {
                throw error(WebDriverError.INVALID_ARGUMENT, "invalid pageLoadStrategy: " + value);
            }
        }
        JsonElement timeouts = capabilities.get("timeouts");
        if (timeouts != null) validateTimeouts(timeouts);
    }

    private static void validateTimeouts(JsonElement element) {
        if (!element.isObject()) {
            throw error(WebDriverError.INVALID_ARGUMENT, "timeouts must be a JSON object");
        }
        Set<String> known = new HashSet<>();
        known.add("implicit");
        known.add("pageLoad");
        known.add("script");
        for (Map.Entry<String, JsonElement> entry : element.asObject().entrySet()) {
            if (!known.contains(entry.getKey())) {
                throw error(WebDriverError.INVALID_ARGUMENT, "unknown timeout: " + entry.getKey());
            }
            JsonElement value = entry.getValue();
            if (!value.isPrimitive() || !value.asPrimitive().isNumber() || !isNonNegativeLong(value.asPrimitive())) {
                throw error(
                        WebDriverError.INVALID_ARGUMENT, "timeout must be a non-negative integer: " + entry.getKey());
            }
        }
    }

    private static boolean isNonNegativeLong(JsonPrimitive value) {
        try {
            return new BigDecimal(value.string()).longValueExact() >= 0;
        } catch (ArithmeticException | NumberFormatException ignored) {
            return false;
        }
    }

    private static boolean matchesRequestedCapabilities(JsonObject requested, JsonObject actual) {
        for (String name : List.of("browserName", "browserVersion", "platformName", "pageLoadStrategy")) {
            JsonElement expected = requested.get(name);
            if (expected != null && !samePrimitive(expected, actual.get(name))) return false;
        }
        for (String name : List.of("acceptInsecureCerts", "setWindowRect", "strictFileInteractability")) {
            JsonElement expected = requested.get(name);
            if (expected != null) {
                JsonElement supplied = actual.get(name);
                boolean actualValue = supplied != null && supplied.booleanValue();
                if (expected.booleanValue() != actualValue) return false;
            }
        }
        if (requested.has("proxy") || requested.has("unhandledPromptBehavior")) return false;
        JsonElement webSocketUrl = requested.get("webSocketUrl");
        return webSocketUrl == null || !webSocketUrl.booleanValue() || actual.has("webSocketUrl");
    }

    private static boolean samePrimitive(JsonElement expected, @Nullable JsonElement actual) {
        return actual != null && actual.isPrimitive() && expected.string().equals(actual.string());
    }

    private static JsonObject normalizeActualCapabilities(JsonObject requested, JsonObject supplied) {
        JsonObject result =
                Objects.requireNonNull(supplied, "backend capabilities").deepCopy();
        if (!result.has("browserName")) result.addProperty("browserName", "cef4j");
        if (!result.has("platformName")) {
            result.addProperty(
                    "platformName", System.getProperty("os.name", "unknown").toLowerCase(Locale.ROOT));
        }
        if (!result.has("acceptInsecureCerts")) result.addProperty("acceptInsecureCerts", false);
        if (!result.has("pageLoadStrategy")) {
            JsonElement requestedStrategy = requested.get("pageLoadStrategy");
            result.addProperty("pageLoadStrategy", requestedStrategy == null ? "normal" : requestedStrategy.string());
        }
        if (!result.has("setWindowRect")) result.addProperty("setWindowRect", false);
        JsonObject timeouts = ActiveSession.defaultTimeouts();
        JsonElement requestedTimeouts = requested.get("timeouts");
        if (requestedTimeouts != null) {
            for (Map.Entry<String, JsonElement> entry :
                    requestedTimeouts.asObject().entrySet())
                timeouts.add(entry.getKey(), entry.getValue().deepCopy());
        }
        JsonElement suppliedTimeouts = result.get("timeouts");
        if (suppliedTimeouts != null) {
            validateTimeouts(suppliedTimeouts);
            for (Map.Entry<String, JsonElement> entry :
                    suppliedTimeouts.asObject().entrySet())
                timeouts.add(entry.getKey(), entry.getValue().deepCopy());
        }
        result.add("timeouts", timeouts);
        return result;
    }

    private JsonObject parseObjectBody(HttpExchange exchange) throws IOException {
        byte[] bytes = readBody(exchange);
        try {
            JsonElement parsed = jsonCodec.decode(bytes);
            if (!parsed.isObject()) {
                throw error(WebDriverError.INVALID_ARGUMENT, "request body must be a JSON object");
            }
            return parsed.asObject();
        } catch (RuntimeException e) {
            throw new WebDriverException(WebDriverError.INVALID_ARGUMENT, "request body is not valid JSON", e);
        }
    }

    private static byte[] readBody(HttpExchange exchange) throws IOException {
        String lengthHeader = exchange.getRequestHeaders().getFirst("Content-Length");
        if (lengthHeader != null) {
            try {
                long length = Long.parseLong(lengthHeader);
                if (length < 0 || length > MAX_REQUEST_BYTES) {
                    throw error(
                            WebDriverError.INVALID_ARGUMENT, "request body exceeds " + MAX_REQUEST_BYTES + " bytes");
                }
            } catch (NumberFormatException e) {
                throw new WebDriverException(WebDriverError.INVALID_ARGUMENT, "invalid Content-Length", e);
            }
        }
        try (InputStream input = exchange.getRequestBody();
                ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[8192];
            int total = 0;
            int read;
            while ((read = input.read(buffer)) >= 0) {
                total += read;
                if (total > MAX_REQUEST_BYTES) {
                    throw error(
                            WebDriverError.INVALID_ARGUMENT, "request body exceeds " + MAX_REQUEST_BYTES + " bytes");
                }
                output.write(buffer, 0, read);
            }
            return output.toByteArray();
        }
    }

    private static String requiredString(JsonObject object, String name) {
        JsonElement value = object.get(name);
        if (value == null || !value.isPrimitive() || !value.asPrimitive().isString()) {
            throw error(WebDriverError.INVALID_ARGUMENT, name + " must be a string");
        }
        return value.string();
    }

    private static JsonArray requiredArray(JsonObject object, String name) {
        JsonElement value = object.get(name);
        if (value == null || !value.isArray()) {
            throw error(WebDriverError.INVALID_ARGUMENT, name + " must be an array");
        }
        return value.asArray();
    }

    private static JsonObject requiredObject(JsonObject object, String name) {
        JsonElement value = object.get(name);
        if (value == null || !value.isObject()) {
            throw error(WebDriverError.INVALID_ARGUMENT, name + " must be an object");
        }
        return value.asObject();
    }

    private static JsonObject optionalObject(JsonObject object, String name, JsonObject fallback) {
        JsonElement value = object.get(name);
        if (value == null) return fallback;
        if (!value.isObject()) throw error(WebDriverError.INVALID_ARGUMENT, name + " must be an object");
        return value.asObject();
    }

    @Nullable
    private static JsonArray optionalArray(JsonObject object, String name) {
        JsonElement value = object.get(name);
        if (value == null) return null;
        if (!value.isArray()) throw error(WebDriverError.INVALID_ARGUMENT, name + " must be an array");
        return value.asArray();
    }

    private static void requireOptionalString(JsonObject object, String name) {
        JsonElement value = object.get(name);
        if (value != null && (!value.isPrimitive() || !value.asPrimitive().isString())) {
            throw error(WebDriverError.INVALID_ARGUMENT, name + " must be a string");
        }
    }

    private static void requireOptionalBoolean(JsonObject object, String name) {
        JsonElement value = object.get(name);
        if (value != null && (!value.isPrimitive() || !value.asPrimitive().isBoolean())) {
            throw error(WebDriverError.INVALID_ARGUMENT, name + " must be a boolean");
        }
    }

    private static JsonElement string(String value) {
        return new JsonPrimitive(value);
    }

    private void sendSuccess(HttpExchange exchange, JsonElement value) throws IOException {
        JsonObject envelope = new JsonObject();
        envelope.add("value", value);
        sendJson(exchange, 200, envelope);
    }

    private void sendError(HttpExchange exchange, WebDriverError error, String message) {
        JsonObject value = new JsonObject();
        value.addProperty("error", error.code());
        value.addProperty("message", message);
        value.addProperty("stacktrace", "");
        JsonObject envelope = new JsonObject();
        envelope.add("value", value);
        try {
            sendJson(exchange, error.httpStatus(), envelope);
        } catch (IOException ignored) {
            LOG.debug("Failed to send WebDriver error response", ignored);
        }
    }

    private void sendJson(HttpExchange exchange, int status, JsonObject body) throws IOException {
        byte[] bytes = jsonCodec.encode(body);
        exchange.getResponseHeaders().set("Content-Type", "application/json; charset=utf-8");
        exchange.getResponseHeaders().set("Cache-Control", "no-store");
        exchange.sendResponseHeaders(status, bytes.length);
        exchange.getResponseBody().write(bytes);
    }

    private static WebDriverException error(WebDriverError error, String message) {
        return new WebDriverException(error, message);
    }

    private static ThreadFactory daemonThreads(String prefix) {
        AtomicInteger sequence = new AtomicInteger();
        return task -> {
            Thread thread = new Thread(task, prefix + "-" + sequence.incrementAndGet());
            thread.setDaemon(true);
            return thread;
        };
    }

    private static void closeQuietly(AutomationBackend backend) {
        try {
            backend.close();
        } catch (RuntimeException e) {
            LOG.debug("automation backend close failed: {}", e.toString());
        }
    }

    @Override
    public void close() {
        if (!closed.compareAndSet(false, true)) return;
        server.stop(0);
        for (ActiveSession session : sessions.values()) session.close();
        sessions.clear();
        if (ownedExecutor != null) ownedExecutor.shutdownNow();
    }

    private static final class ActiveSession implements AutoCloseable {
        private final String id;
        private final AutomationBackend backend;
        private final Object commandLock = new Object();
        private final AtomicBoolean closed = new AtomicBoolean();
        private final JsonObject timeouts;

        private ActiveSession(String id, AutomationBackend backend, JsonObject timeouts) {
            this.id = id;
            this.backend = backend;
            this.timeouts = timeouts.deepCopy();
        }

        private static JsonObject defaultTimeouts() {
            JsonObject result = new JsonObject();
            result.addProperty("implicit", 0);
            result.addProperty("pageLoad", 300000);
            result.addProperty("script", 30000);
            return result;
        }

        private Duration timeout(String name) {
            synchronized (commandLock) {
                return Duration.ofMillis(timeouts.get(name).longValue());
            }
        }

        private long timeoutMillis(String name) {
            synchronized (commandLock) {
                return timeouts.get(name).longValue();
            }
        }

        private void updateTimeouts(JsonObject updates) {
            synchronized (commandLock) {
                for (Map.Entry<String, JsonElement> entry : updates.entrySet())
                    timeouts.add(entry.getKey(), entry.getValue().deepCopy());
            }
        }

        private JsonObject timeouts() {
            synchronized (commandLock) {
                return timeouts.deepCopy();
            }
        }

        @Override
        public void close() {
            if (!closed.compareAndSet(false, true)) return;
            try {
                backend.cancelPendingCommands(new CancellationException("session closed"));
            } catch (RuntimeException failure) {
                LOG.debug("automation backend command cancellation failed: {}", failure.toString());
            }
            synchronized (commandLock) {
                closeQuietly(backend);
            }
        }
    }
}
