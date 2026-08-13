package net.kurobako.cef4j.webdriver;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URI;
import java.time.Duration;
import java.util.Base64;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
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
    private static final int DEFAULT_WORKERS = 4;
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
    private final ExecutorService executor;
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
        HttpServer http = HttpServer.create(bindAddress, DEFAULT_BACKLOG);
        ExecutorService workers = Executors.newFixedThreadPool(DEFAULT_WORKERS, daemonThreads("cef4j-webdriver-http"));
        WebDriverServer result = new WebDriverServer(http, workers, backendFactory, commandTimeout, jsonCodec);
        http.createContext("/", result::handle);
        http.setExecutor(workers);
        http.start();
        return result;
    }

    private WebDriverServer(
            HttpServer server,
            ExecutorService executor,
            AutomationBackendFactory backendFactory,
            Duration commandTimeout,
            WebDriverJsonCodec jsonCodec) {
        this.server = server;
        this.executor = executor;
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
        if ("GET".equals(method) && "/status".equals(path)) {
            JsonObject status = new JsonObject();
            status.addProperty("ready", !closed.get() && !creatingSession && sessions.isEmpty());
            status.addProperty("message", sessions.isEmpty() ? "cef4j WebDriver is ready" : "a session is active");
            sendSuccess(exchange, status);
            return;
        }
        if ("POST".equals(method) && "/session".equals(path)) {
            createSession(exchange, parseObjectBody(exchange));
            return;
        }

        String[] parts = pathParts(path);
        if (parts.length < 2 || !"session".equals(parts[0])) {
            throw error(WebDriverError.UNKNOWN_COMMAND, "unknown command: " + method + " " + path);
        }
        ActiveSession session = sessions.get(parts[1]);
        if (session == null) {
            throw error(WebDriverError.INVALID_SESSION_ID, "unknown session: " + parts[1]);
        }
        if (parts.length == 2 && "DELETE".equals(method)) {
            sessions.remove(session.id, session);
            session.close();
            sendSuccess(exchange, JsonNull.INSTANCE);
            return;
        }
        routeSessionCommand(exchange, method, path, parts, session);
    }

    private void createSession(HttpExchange exchange, JsonObject body) throws IOException {
        JsonObject requested = matchCapabilities(body);
        synchronized (creationLock) {
            if (closed.get() || creatingSession || !sessions.isEmpty()) {
                throw error(WebDriverError.SESSION_NOT_CREATED, "this endpoint already owns an active session");
            }
            creatingSession = true;
        }

        AutomationBackend backend = null;
        CompletableFuture<? extends AutomationBackend> creation =
                Objects.requireNonNull(backendFactory.create(requested), "backend factory returned null future");
        try {
            try {
                backend = await(creation, WebDriverError.SESSION_NOT_CREATED, commandTimeout);
            } catch (RuntimeException failure) {
                CompletableFuture<?> cleanup = creation.whenComplete((lateBackend, ignored) -> {
                    if (lateBackend != null) closeQuietly(lateBackend);
                });
                cleanup.isDone();
                throw failure;
            }
            if (closed.get()) {
                backend.close();
                throw error(WebDriverError.SESSION_NOT_CREATED, "WebDriver server closed during session creation");
            }
            String id = UUID.randomUUID().toString();
            JsonObject actual = normalizeActualCapabilities(requested, backend.capabilities());
            ActiveSession active = new ActiveSession(id, backend, actual.object("timeouts"));
            sessions.put(id, active);
            backend = null; // ownership transferred
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

    private void routeSessionCommand(
            HttpExchange exchange, String method, String path, String[] parts, ActiveSession session)
            throws IOException {
        AutomationBackend backend = session.backend;
        if (parts.length == 3 && "url".equals(parts[2])) {
            if ("GET".equals(method)) {
                sendSuccess(exchange, string(command(session, backend::currentUrl)));
                return;
            }
            if ("POST".equals(method)) {
                JsonObject body = parseObjectBody(exchange);
                String url = requiredString(body, "url");
                command(session, "pageLoad", () -> backend.navigate(url));
                sendSuccess(exchange, JsonNull.INSTANCE);
                return;
            }
        }
        if (parts.length == 3 && "GET".equals(method) && "title".equals(parts[2])) {
            sendSuccess(exchange, string(command(session, backend::title)));
            return;
        }
        if (parts.length == 3 && "GET".equals(method) && "source".equals(parts[2])) {
            sendSuccess(exchange, string(command(session, backend::pageSource)));
            return;
        }
        if (parts.length == 3 && "GET".equals(method) && "screenshot".equals(parts[2])) {
            byte[] png = command(session, backend::screenshot);
            sendSuccess(exchange, string(Base64.getEncoder().encodeToString(png)));
            return;
        }
        if (parts.length == 4 && "POST".equals(method) && "execute".equals(parts[2]) && "sync".equals(parts[3])) {
            JsonObject body = parseObjectBody(exchange);
            String script = requiredString(body, "script");
            JsonArray arguments = requiredArray(body, "args");
            sendSuccess(
                    exchange, command(session, "script", () -> backend.executeScript(script, arguments.deepCopy())));
            return;
        }
        if (parts.length == 3 && "POST".equals(method)) {
            boolean handled = true;
            switch (parts[2]) {
                case "back":
                    parseObjectBody(exchange);
                    command(session, "pageLoad", backend::back);
                    break;
                case "forward":
                    parseObjectBody(exchange);
                    command(session, "pageLoad", backend::forward);
                    break;
                case "refresh":
                    parseObjectBody(exchange);
                    command(session, "pageLoad", backend::refresh);
                    break;
                case "timeouts":
                    JsonObject timeouts = parseObjectBody(exchange);
                    validateTimeouts(timeouts);
                    session.updateTimeouts(timeouts);
                    break;
                case "cookie":
                    JsonObject body = parseObjectBody(exchange);
                    command(
                            session,
                            () -> backend.addCookie(
                                    requiredObject(body, "cookie").deepCopy()));
                    break;
                default:
                    handled = false;
                    break;
            }
            if (handled) {
                sendSuccess(exchange, JsonNull.INSTANCE);
                return;
            }
        }
        if (parts.length == 3 && "GET".equals(method) && "timeouts".equals(parts[2])) {
            sendSuccess(exchange, session.timeouts());
            return;
        }
        if (parts.length == 3 && "cookie".equals(parts[2])) {
            if ("GET".equals(method)) {
                sendSuccess(exchange, command(session, backend::cookies));
                return;
            }
            if ("DELETE".equals(method)) {
                command(session, backend::deleteAllCookies);
                sendSuccess(exchange, JsonNull.INSTANCE);
                return;
            }
        }
        if (parts.length == 4 && "cookie".equals(parts[2])) {
            if ("GET".equals(method)) {
                sendSuccess(exchange, command(session, () -> backend.cookie(parts[3])));
                return;
            }
            if ("DELETE".equals(method)) {
                command(session, () -> backend.deleteCookie(parts[3]));
                sendSuccess(exchange, JsonNull.INSTANCE);
                return;
            }
        }
        if (parts.length == 3 && "POST".equals(method) && "element".equals(parts[2])) {
            JsonObject body = parseObjectBody(exchange);
            String id = findElementWithImplicitWait(
                    session, backend, requiredString(body, "using"), requiredString(body, "value"), null);
            sendSuccess(exchange, elementReference(id));
            return;
        }
        if (parts.length == 3 && "POST".equals(method) && "elements".equals(parts[2])) {
            JsonObject body = parseObjectBody(exchange);
            java.util.List<String> ids = findElementsWithImplicitWait(
                    session, backend, requiredString(body, "using"), requiredString(body, "value"), null);
            sendSuccess(exchange, elementReferences(ids));
            return;
        }
        if (parts.length == 4 && "GET".equals(method) && "element".equals(parts[2]) && "active".equals(parts[3])) {
            sendSuccess(exchange, elementReference(command(session, backend::activeElement)));
            return;
        }
        if (parts.length >= 4 && "element".equals(parts[2])) {
            routeElementCommand(exchange, method, path, parts, session, backend);
            return;
        }
        throw error(WebDriverError.UNKNOWN_COMMAND, "unknown command: " + method + " " + path);
    }

    private void routeElementCommand(
            HttpExchange exchange,
            String method,
            String path,
            String[] parts,
            ActiveSession session,
            AutomationBackend backend)
            throws IOException {
        String elementId = parts[3];
        if (parts.length == 5 && "POST".equals(method) && ("element".equals(parts[4]) || "elements".equals(parts[4]))) {
            JsonObject body = parseObjectBody(exchange);
            String using = requiredString(body, "using");
            String value = requiredString(body, "value");
            if ("element".equals(parts[4])) {
                sendSuccess(
                        exchange,
                        elementReference(findElementWithImplicitWait(session, backend, using, value, elementId)));
            } else {
                sendSuccess(
                        exchange,
                        elementReferences(findElementsWithImplicitWait(session, backend, using, value, elementId)));
            }
            return;
        }
        if (parts.length == 5 && "GET".equals(method)) {
            JsonElement value;
            switch (parts[4]) {
                case "name":
                    value = string(command(session, () -> backend.elementTagName(elementId)));
                    break;
                case "text":
                    value = string(command(session, () -> backend.elementText(elementId)));
                    break;
                case "rect":
                    value = command(session, () -> backend.elementRect(elementId));
                    break;
                case "displayed":
                    value = booleanValue(command(session, () -> backend.elementDisplayed(elementId)));
                    break;
                case "enabled":
                    value = booleanValue(command(session, () -> backend.elementEnabled(elementId)));
                    break;
                case "selected":
                    value = booleanValue(command(session, () -> backend.elementSelected(elementId)));
                    break;
                default:
                    throw error(WebDriverError.UNKNOWN_COMMAND, "unknown command: " + method + " " + path);
            }
            sendSuccess(exchange, value);
            return;
        }
        if (parts.length == 5 && "POST".equals(method)) {
            switch (parts[4]) {
                case "click":
                    parseObjectBody(exchange);
                    command(session, () -> backend.elementClick(elementId));
                    break;
                case "clear":
                    parseObjectBody(exchange);
                    command(session, () -> backend.elementClear(elementId));
                    break;
                case "value":
                    JsonObject body = parseObjectBody(exchange);
                    command(session, () -> backend.elementSendKeys(elementId, requiredString(body, "text")));
                    break;
                default:
                    throw error(WebDriverError.UNKNOWN_COMMAND, "unknown command: " + method + " " + path);
            }
            sendSuccess(exchange, JsonNull.INSTANCE);
            return;
        }
        if (parts.length == 6 && "GET".equals(method)) {
            JsonElement value;
            switch (parts[4]) {
                case "attribute":
                    value = command(session, () -> backend.elementAttribute(elementId, parts[5]));
                    break;
                case "property":
                    value = command(session, () -> backend.elementProperty(elementId, parts[5]));
                    break;
                case "css":
                    value = string(command(session, () -> backend.elementCssValue(elementId, parts[5])));
                    break;
                default:
                    throw error(WebDriverError.UNKNOWN_COMMAND, "unknown command: " + method + " " + path);
            }
            sendSuccess(exchange, value);
            return;
        }
        throw error(WebDriverError.UNKNOWN_COMMAND, "unknown command: " + method + " " + path);
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
            ActiveSession session, AutomationBackend backend, String using, String value, @Nullable String parent) {
        java.util.List<String> found = findElementsWithImplicitWait(session, backend, using, value, parent);
        if (found.isEmpty()) throw error(WebDriverError.NO_SUCH_ELEMENT, "unable to locate element: " + value);
        return found.get(0);
    }

    private java.util.List<String> findElementsWithImplicitWait(
            ActiveSession session, AutomationBackend backend, String using, String value, @Nullable String parent) {
        long timeoutMillis = session.timeoutMillis("implicit");
        long deadline = System.nanoTime() + TimeUnit.MILLISECONDS.toNanos(timeoutMillis);
        do {
            java.util.List<String> found = command(session, () -> backend.findElements(using, value, parent));
            if (!found.isEmpty() || System.nanoTime() >= deadline) return found;
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
            try {
                return await(operation.get(), WebDriverError.UNKNOWN_ERROR, timeout);
            } catch (WebDriverException failure) {
                if (failure.error() == WebDriverError.TIMEOUT && sessions.remove(session.id, session)) session.close();
                throw failure;
            }
        }
    }

    private static <T> T await(CompletableFuture<? extends T> future, WebDriverError fallback, Duration timeout) {
        Objects.requireNonNull(future, "backend returned null future");
        try {
            return future.get(timeout.toMillis(), TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            throw new WebDriverException(WebDriverError.TIMEOUT, "command exceeded " + timeout, e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new WebDriverException(WebDriverError.UNKNOWN_ERROR, "command interrupted", e);
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

    private static JsonObject matchCapabilities(JsonObject body) {
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
            if (matchesCef4j(merged)) return merged;
        }
        throw error(WebDriverError.SESSION_NOT_CREATED, "no requested capability set matches cef4j");
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
            if (!value.isPrimitive()
                    || !value.asPrimitive().isNumber()
                    || value.doubleValue() < 0
                    || value.doubleValue() > Long.MAX_VALUE
                    || value.doubleValue() != Math.rint(value.doubleValue())) {
                throw error(
                        WebDriverError.INVALID_ARGUMENT, "timeout must be a non-negative integer: " + entry.getKey());
            }
        }
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
            for (Map.Entry<String, JsonElement> entry :
                    suppliedTimeouts.asObject().entrySet())
                timeouts.add(entry.getKey(), entry.getValue().deepCopy());
        }
        result.add("timeouts", timeouts);
        return result;
    }

    private static String[] pathParts(String path) {
        if (path.length() < 2 || path.charAt(0) != '/' || path.endsWith("/")) return new String[0];
        return path.substring(1).split("/");
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
            // Client disconnected before the error could be returned.
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
        executor.shutdownNow();
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
            if (closed.compareAndSet(false, true)) closeQuietly(backend);
        }
    }
}
