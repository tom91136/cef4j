package net.kurobako.cef4j.webdriver;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import net.kurobako.cef4j.cdp.CdpClient;
import net.kurobako.cef4j.cdp.CdpObject;
import net.kurobako.cef4j.cdp.generated.Browser;
import net.kurobako.cef4j.cdp.generated.DOM;
import net.kurobako.cef4j.cdp.generated.Input;
import net.kurobako.cef4j.cdp.generated.Network;
import net.kurobako.cef4j.cdp.generated.Page;
import net.kurobako.cef4j.cdp.generated.Runtime;
import org.jspecify.annotations.Nullable;

/** Shared CDP-backed automation implementation, independent of in-process or remote CEF lifecycle. */
public final class CdpAutomationBackend implements AutomationBackend {
    /** Whether an evaluation returns the value itself or the reference of the value, which the caller releases. */
    private enum ResultMode {
        VALUE,
        REFERENCE
    }

    private static final AtomicInteger POLL_THREAD_IDS = new AtomicInteger();
    private static final Executor POLL_EXECUTOR =
            Executors.newFixedThreadPool(java.lang.Runtime.getRuntime().availableProcessors(), task -> {
                final Thread thread = new Thread(task, "cef4j-webdriver-poll-" + POLL_THREAD_IDS.incrementAndGet());
                thread.setDaemon(true);
                return thread;
            });

    private final JsonCdpBrowser cdp;
    private final CdpClient client;
    private final JsonObject capabilities;
    private final AtomicBoolean closed = new AtomicBoolean();
    private final ConcurrentHashMap<String, DOM.BackendNodeId> elements = new ConcurrentHashMap<>();
    private final Runtime.Client runtime;
    private final DOM.Client dom;
    private final Page.Client page;
    private final Input.Client input;
    private final Network.Client network;
    private final Executor pollExecutor;

    private CdpAutomationBackend(
            JsonCdpBrowser cdp, CdpClient client, Browser.GetVersionResult version, Executor pollExecutor) {
        this.cdp = cdp;
        this.client = client;
        runtime = client.domains().runtime();
        dom = client.domains().dom();
        page = client.domains().page();
        input = client.domains().input();
        network = client.domains().network();
        this.pollExecutor = pollExecutor;
        capabilities = new JsonObject();
        capabilities.addProperty("browserName", "cef4j");
        capabilities.addProperty("browserVersion", version.product());
        capabilities.addProperty(
                "platformName", System.getProperty("os.name", "unknown").toLowerCase(Locale.ROOT));
        capabilities.addProperty("acceptInsecureCerts", false);
        capabilities.addProperty("pageLoadStrategy", "normal");
        capabilities.addProperty("setWindowRect", false);
        final JsonObject cef4j = new JsonObject();
        cef4j.addProperty("protocolVersion", version.protocolVersion());
        cef4j.addProperty("revision", version.revision());
        capabilities.add("cef4j:devtools", cef4j);
    }

    /** Queries browser metadata and creates the common WebDriver command backend. */
    public static CompletableFuture<CdpAutomationBackend> create(JsonCdpBrowser cdp) {
        return create(cdp, POLL_EXECUTOR);
    }

    public static CompletableFuture<CdpAutomationBackend> create(JsonCdpBrowser cdp, Executor pollExecutor) {
        Objects.requireNonNull(cdp, "cdp");
        Objects.requireNonNull(pollExecutor, "pollExecutor");
        final CdpClient client = new CdpClient(cdp, cdp.jsonCodec().cdpCodec());
        return client.domains()
                .browser()
                .getVersion()
                .thenApply(version -> new CdpAutomationBackend(cdp, client, version, pollExecutor))
                .whenComplete((backend, failure) -> {
                    if (failure != null) cdp.close();
                })
                .toCompletableFuture();
    }

    @Override
    public JsonObject capabilities() {
        return capabilities.deepCopy();
    }

    @Override
    public CompletableFuture<Void> navigate(String url) {
        Objects.requireNonNull(url, "url");
        elements.clear();
        return cdp.loadUrl(url).thenCompose(ignored -> awaitNotLoading());
    }

    @Override
    public CompletableFuture<String> currentUrl() {
        return evaluateValue("window.location.href").thenApply(JsonElement::string);
    }

    @Override
    public CompletableFuture<String> title() {
        return evaluateValue("document.title").thenApply(JsonElement::string);
    }

    @Override
    public CompletableFuture<String> pageSource() {
        return dom.getDocument(0L, null)
                .thenCompose(root -> {
                    final Map<String, Object> params = new LinkedHashMap<>();
                    params.put("nodeId", CdpObject.json(root.nodeId()));
                    return client.call("DOM.getOuterHTML", params, result ->
                            (String) Objects.requireNonNull(result.get("outerHTML")));
                })
                .toCompletableFuture();
    }

    @Override
    public CompletableFuture<JsonElement> executeScript(String script, JsonArray arguments) {
        Objects.requireNonNull(script, "script");
        Objects.requireNonNull(arguments, "arguments");
        return remoteObject("globalThis").thenCompose(globalObject -> {
            CompletableFuture<List<Runtime.CallArgument>> callArguments =
                    CompletableFuture.completedFuture(new ArrayList<>());
            final List<String> borrowedObjects = new ArrayList<>();
            for (JsonElement argument : arguments) {
                callArguments = callArguments.thenCompose(
                        built -> toCallArgument(argument, borrowedObjects).thenApply(converted -> {
                            built.add(converted);
                            return built;
                        }));
            }
            return callArguments
                    .thenCompose(converted -> callFunctionOnValue(
                            globalObject,
                            "function(){return (function(){" + script + "\n}).apply(null,arguments);}",
                            converted))
                    .whenComplete((ignored, failure) -> {
                        releaseObject(globalObject);
                        borrowedObjects.forEach(this::releaseObject);
                    })
                    .thenApply(this::readEvaluationResult);
        });
    }

    private CompletableFuture<Runtime.CallArgument> toCallArgument(JsonElement argument, List<String> borrowedObjects) {
        final Optional<JsonElement> element = argument.isObject()
                ? argument.asObject().get(WebDriverServer.ELEMENT_KEY).filter(JsonElement::isPrimitive)
                : Optional.empty();
        return element.map(reference -> resolveElement(reference.string()).thenApply(objectId -> {
                    borrowedObjects.add(objectId);
                    return new Runtime.CallArgument().objectId(new Runtime.RemoteObjectId(objectId));
                }))
                .orElseGet(() -> CompletableFuture.completedFuture(
                        // CallArgument.value(null) omits the member, which CDP reads as undefined rather than null
                        WebDriverJsonCodec.fromJsonElement(argument)
                                .map(value -> new Runtime.CallArgument().value(value))
                                .orElseGet(
                                        () -> Runtime.CallArgument.fromMap(Collections.singletonMap("value", null)))));
    }

    @Override
    public CompletableFuture<byte[]> screenshot() {
        final Map<String, Object> params = new LinkedHashMap<>();
        params.put("format", CdpObject.json("png"));
        params.put("captureBeyondViewport", true);
        return client.call("Page.captureScreenshot", params, result ->
                        (String) java.util.Objects.requireNonNull(result.get("data")))
                .thenApply(Base64.getDecoder()::decode)
                .toCompletableFuture();
    }

    @Override
    public CompletableFuture<String> findElement(String using, String value) {
        return findElements(using, value).thenApply(found -> {
            if (found.isEmpty()) throw failure(WebDriverError.NO_SUCH_ELEMENT, "unable to locate element: " + value);
            return found.get(0);
        });
    }

    @Override
    public CompletableFuture<String> findElement(String using, String value, String parentElement) {
        return findElements(using, value, Objects.requireNonNull(parentElement, "parentElement"))
                .thenApply(found -> {
                    if (found.isEmpty())
                        throw failure(WebDriverError.NO_SUCH_ELEMENT, "unable to locate element: " + value);
                    return found.get(0);
                });
    }

    @Override
    public CompletableFuture<List<String>> findElements(String using, String value) {
        return findElementsImpl(using, value, null);
    }

    @Override
    public CompletableFuture<List<String>> findElements(String using, String value, String parentElement) {
        return findElementsImpl(using, value, Objects.requireNonNull(parentElement, "parentElement"));
    }

    private CompletableFuture<List<String>> findElementsImpl(
            String using, String value, @Nullable String parentElement) {
        validateLocator(using);
        final CompletableFuture<String> root =
                parentElement != null ? resolveElement(parentElement) : remoteObject("document");
        return root.thenCompose(
                        objectId -> callFunctionOnReference(objectId, FIND_ELEMENTS, callArguments(using, value))
                                .whenComplete((ignored, failure) -> releaseObject(objectId)))
                .thenCompose(CdpAutomationBackend::remoteObjectId)
                .thenCompose(this::readElementArray);
    }

    @Override
    public CompletableFuture<String> activeElement() {
        return remoteObject("document.activeElement").thenCompose(this::registerObject);
    }

    @Override
    public CompletableFuture<String> elementTagName(String elementId) {
        return callElementValue(elementId, "function(){return this.tagName.toLowerCase();}", List.of())
                .thenApply(JsonElement::string);
    }

    @Override
    public CompletableFuture<String> elementText(String elementId) {
        return callElementValue(
                        elementId,
                        "function(){return this.innerText === undefined ? (this.textContent || '') : this.innerText;}",
                        List.of())
                .thenApply(JsonElement::string);
    }

    @Override
    public CompletableFuture<JsonElement> elementAttribute(String elementId, String name) {
        return callElementValue(elementId, "function(n){return this.getAttribute(n);}", callArguments(name));
    }

    @Override
    public CompletableFuture<JsonElement> elementProperty(String elementId, String name) {
        return callElementValue(elementId, "function(n){return this[n];}", callArguments(name));
    }

    @Override
    public CompletableFuture<String> elementCssValue(String elementId, String name) {
        return callElementValue(
                        elementId,
                        "function(n){return getComputedStyle(this).getPropertyValue(n);}",
                        callArguments(name))
                .thenApply(JsonElement::string);
    }

    @Override
    public CompletableFuture<JsonObject> elementRect(String elementId) {
        return callElementValue(
                        elementId,
                        "function(){const r=this.getBoundingClientRect();return {x:r.x,y:r.y,width:r.width,height:r.height};}",
                        List.of())
                .thenApply(JsonElement::asObject);
    }

    @Override
    public CompletableFuture<Boolean> elementDisplayed(String elementId) {
        return callElementValue(elementId, DISPLAYED, List.of()).thenApply(JsonElement::booleanValue);
    }

    @Override
    public CompletableFuture<Boolean> elementEnabled(String elementId) {
        return callElementValue(elementId, "function(){return !this.disabled;}", List.of())
                .thenApply(JsonElement::booleanValue);
    }

    @Override
    public CompletableFuture<Boolean> elementSelected(String elementId) {
        return callElementValue(
                        elementId,
                        "function(){return this.tagName==='OPTION' ? this.selected : !!this.checked;}",
                        List.of())
                .thenApply(JsonElement::booleanValue);
    }

    @Override
    public CompletableFuture<Void> elementClick(String elementId) {
        final DOM.BackendNodeId backendNodeId = requireElementId(elementId);
        return resolveElement(elementId)
                .thenCompose(objectId -> {
                    final Map<String, Object> scrollParams = new LinkedHashMap<>();
                    scrollParams.put("backendNodeId", CdpObject.json(backendNodeId.value()));
                    return client.call("DOM.scrollIntoViewIfNeeded", scrollParams)
                            .toCompletableFuture()
                            .whenComplete((ignored, failure) -> releaseObject(objectId));
                })
                .thenCompose(ignored -> elementDisplayed(elementId))
                .thenCompose(displayed -> {
                    if (!displayed) {
                        throw failure(WebDriverError.ELEMENT_NOT_INTERACTABLE, "element is not displayed");
                    }
                    return elementEnabled(elementId);
                })
                .thenCompose(enabled -> {
                    if (!enabled) throw failure(WebDriverError.ELEMENT_NOT_INTERACTABLE, "element is disabled");
                    return elementRect(elementId);
                })
                .thenCompose(rect -> {
                    final double x = member(rect, "x").doubleValue()
                            + member(rect, "width").doubleValue() / 2;
                    final double y = member(rect, "y").doubleValue()
                            + member(rect, "height").doubleValue() / 2;
                    final List<Runtime.CallArgument> point =
                            List.of(new Runtime.CallArgument().value(x), new Runtime.CallArgument().value(y));
                    return callElementValue(
                                    elementId,
                                    "function(x,y){const hit=document.elementFromPoint(x,y);return !!hit&&(hit===this||this.contains(hit));}",
                                    point)
                            .thenCompose(hit -> {
                                if (!hit.booleanValue()) {
                                    throw failure(
                                            WebDriverError.ELEMENT_CLICK_INTERCEPTED,
                                            "another element obscures the click point");
                                }
                                return dispatchMouse("mouseMoved", x, y, "none", 0, 0)
                                        .thenCompose(ignored -> dispatchMouse("mousePressed", x, y, "left", 1, 1))
                                        .thenCompose(ignored -> dispatchMouse("mouseReleased", x, y, "left", 1, 0));
                            });
                });
    }

    @Override
    public CompletableFuture<Void> elementClear(String elementId) {
        return focusElement(elementId)
                .thenCompose(ignored -> callElementValue(elementId, CLEAR_ELEMENT, List.of()))
                .thenApply(ignored -> null);
    }

    @Override
    public CompletableFuture<Void> elementSendKeys(String elementId, String text) {
        Objects.requireNonNull(text, "text");
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) >= '\ue000' && text.charAt(i) <= '\ue05d') {
                return CompletableFuture.failedFuture(failure(
                        WebDriverError.UNSUPPORTED_OPERATION,
                        "special WebDriver keys are not implemented by this endpoint yet"));
            }
        }
        return focusElement(elementId)
                .thenCompose(ignored -> input.insertText(text).thenApply(v -> null));
    }

    @Override
    public CompletableFuture<Void> back() {
        elements.clear();
        return cdp.canGoBack()
                .thenCompose(
                        canGoBack -> canGoBack ? waitForLoad(cdp::goBack) : CompletableFuture.completedFuture(null));
    }

    @Override
    public CompletableFuture<Void> forward() {
        elements.clear();
        return cdp.canGoForward()
                .thenCompose(canGoForward ->
                        canGoForward ? waitForLoad(cdp::goForward) : CompletableFuture.completedFuture(null));
    }

    @Override
    public CompletableFuture<Void> refresh() {
        elements.clear();
        return waitForLoad(() -> page.reload().toCompletableFuture());
    }

    private CompletableFuture<Void> waitForLoad(Supplier<? extends CompletableFuture<?>> command) {
        return command.get().thenCompose(ignored -> awaitNotLoading());
    }

    @Override
    public CompletableFuture<JsonArray> cookies() {
        return currentUrl().thenCompose(url -> network.getCookies(List.of(url))).thenApply(this::readCookies);
    }

    private JsonArray readCookies(List<Network.Cookie> source) {
        final JsonArray result = new JsonArray();
        for (Network.Cookie cookie : source) {
            final JsonObject item = new JsonObject();
            item.addProperty("name", cookie.name());
            item.addProperty("value", cookie.value());
            item.addProperty("path", cookie.path());
            item.addProperty("domain", cookie.domain());
            item.addProperty("secure", cookie.secure());
            item.addProperty("httpOnly", cookie.httpOnly());
            cookie.sameSite().ifPresent(sameSite -> item.addProperty("sameSite", sameSite.value()));
            if (cookie.expires() >= 0) item.addProperty("expiry", (long) cookie.expires());
            result.add(item);
        }
        return result;
    }

    @Override
    public CompletableFuture<JsonElement> cookie(String name) {
        return cookies().thenApply(all -> {
            for (JsonElement item : all) {
                if (name.equals(member(item.asObject(), "name").string())) return item;
            }
            throw failure(WebDriverError.NO_SUCH_COOKIE, "no cookie named " + name);
        });
    }

    @Override
    public CompletableFuture<Void> addCookie(JsonObject cookie) {
        validateCookie(cookie);
        return currentUrl()
                .thenCompose(url -> {
                    final Map<String, Object> params = new LinkedHashMap<>();
                    params.put("name", CdpObject.json(member(cookie, "name").string()));
                    params.put("value", CdpObject.json(member(cookie, "value").string()));
                    params.put("url", CdpObject.json(url));
                    optionalString(cookie, "domain").ifPresent(value -> params.put("domain", CdpObject.json(value)));
                    optionalString(cookie, "path").ifPresent(value -> params.put("path", CdpObject.json(value)));
                    optionalBoolean(cookie, "secure").ifPresent(value -> params.put("secure", value));
                    optionalBoolean(cookie, "httpOnly").ifPresent(value -> params.put("httpOnly", value));
                    optionalSameSite(cookie).ifPresent(value -> params.put("sameSite", CdpObject.json(value)));
                    optionalExpiry(cookie).ifPresent(value -> params.put("expires", CdpObject.json(value)));
                    return client.call("Network.setCookie", params, result ->
                            (Boolean) Objects.requireNonNull(result.get("success")));
                })
                .thenApply(success -> {
                    if (!success) throw failure(WebDriverError.INVALID_ARGUMENT, "CEF rejected cookie");
                    return null;
                });
    }

    static void validateCookie(JsonObject cookie) {
        requireCookieString(cookie, "name");
        requireCookieString(cookie, "value");
        optionalCookieString(cookie, "domain");
        optionalCookieString(cookie, "path");
        requireCookieBoolean(cookie, "secure");
        requireCookieBoolean(cookie, "httpOnly");
        validateCookieSameSite(cookie);
        validateCookieExpiry(cookie);
    }

    private static void requireCookieString(JsonObject cookie, String name) {
        if (cookie.get(name)
                .filter(value -> value.isPrimitive() && value.asPrimitive().isString())
                .isEmpty()) {
            throw failure(WebDriverError.INVALID_ARGUMENT, "cookie " + name + " must be a string");
        }
    }

    /** A cookie member that is optional, but has to be a string when it is there. */
    private static void optionalCookieString(JsonObject cookie, String name) {
        if (!cookie.has(name)) return;
        requireCookieString(cookie, name);
    }

    private static void requireCookieBoolean(JsonObject cookie, String name) {
        cookie.get(name).ifPresent(value -> {
            if (!value.isPrimitive() || !value.asPrimitive().isBoolean()) {
                throw failure(WebDriverError.INVALID_ARGUMENT, "cookie " + name + " must be a boolean");
            }
        });
    }

    private static void validateCookieSameSite(JsonObject cookie) {
        cookie.get("sameSite").ifPresent(value -> {
            if (!value.isPrimitive() || !value.asPrimitive().isString()) {
                throw failure(WebDriverError.INVALID_ARGUMENT, "cookie sameSite must be a string");
            }
            final String sameSite = value.string();
            if (!sameSite.equals("Strict") && !sameSite.equals("Lax") && !sameSite.equals("None")) {
                throw failure(WebDriverError.INVALID_ARGUMENT, "invalid cookie sameSite: " + sameSite);
            }
        });
    }

    private static void validateCookieExpiry(JsonObject cookie) {
        cookie.get("expiry").ifPresent(value -> {
            if (!value.isPrimitive() || !value.asPrimitive().isNumber()) {
                throw failure(WebDriverError.INVALID_ARGUMENT, "cookie expiry must be a non-negative integer");
            }
            try {
                if (new java.math.BigDecimal(value.string()).longValueExact() < 0) throw new ArithmeticException();
            } catch (ArithmeticException | NumberFormatException invalid) {
                throw new WebDriverException(
                        WebDriverError.INVALID_ARGUMENT, "cookie expiry must be a non-negative integer", invalid);
            }
        });
    }

    private static JsonElement member(JsonObject source, String name) {
        return source.get(name).orElseThrow(() -> new NullPointerException(name));
    }

    private static Optional<String> optionalString(JsonObject source, String name) {
        return source.get(name).map(JsonElement::string);
    }

    private static Optional<Boolean> optionalBoolean(JsonObject source, String name) {
        return source.get(name).map(JsonElement::booleanValue);
    }

    private static Optional<Network.CookieSameSite> optionalSameSite(JsonObject source) {
        return source.get("sameSite").map(value -> Network.CookieSameSite.of(value.string()));
    }

    private static Optional<Network.TimeSinceEpoch> optionalExpiry(JsonObject source) {
        return source.get("expiry").map(value -> new Network.TimeSinceEpoch(value.doubleValue()));
    }

    @Override
    public CompletableFuture<Void> deleteCookie(String name) {
        return currentUrl()
                .thenCompose(url -> {
                    final Map<String, Object> params = new LinkedHashMap<>();
                    params.put("name", CdpObject.json(name));
                    params.put("url", CdpObject.json(url));
                    return client.call("Network.deleteCookies", params);
                })
                .thenApply(ignored -> null);
    }

    @Override
    public CompletableFuture<Void> deleteAllCookies() {
        return network.clearBrowserCookies().<Void>thenApply(ignored -> null).toCompletableFuture();
    }

    private CompletableFuture<Void> focusElement(String elementId) {
        final DOM.BackendNodeId backendNodeId = requireElementId(elementId);
        return resolveElement(elementId)
                .thenCompose(objectId -> dom.focus(null, backendNodeId, null)
                        .whenComplete((ignored, failure) -> releaseObject(objectId)))
                .thenApply(ignored -> null);
    }

    private CompletableFuture<Void> dispatchMouse(
            String type, double x, double y, String button, int clickCount, int buttons) {
        final Map<String, Object> params = new LinkedHashMap<>();
        params.put("type", CdpObject.json(type));
        params.put("x", x);
        params.put("y", y);
        params.put("button", CdpObject.json(button));
        params.put("buttons", buttons);
        params.put("clickCount", clickCount);
        return client.call("Input.dispatchMouseEvent", params).toCompletableFuture();
    }

    private DOM.BackendNodeId requireElementId(String id) {
        final DOM.BackendNodeId backendNodeId = elements.get(id);
        if (backendNodeId == null) throw failure(WebDriverError.STALE_ELEMENT_REFERENCE, "unknown element: " + id);
        return backendNodeId;
    }

    private CompletableFuture<List<String>> readElementArray(String arrayObjectId) {
        final Map<String, Object> params = new LinkedHashMap<>();
        params.put("objectId", CdpObject.json(arrayObjectId));
        params.put("ownProperties", true);
        return client.call("Runtime.getProperties", params, Runtime.GetPropertiesResult::fromMap)
                .thenCompose(properties -> {
                    final List<Runtime.PropertyDescriptor> entries = new ArrayList<>();
                    for (final Runtime.PropertyDescriptor property : properties.result()) {
                        if (property.value().isPresent()
                                && property.value().get().objectId().isPresent()
                                && isArrayIndex(property.name())) {
                            entries.add(property);
                        }
                    }
                    entries.sort(Comparator.comparingInt(property -> Integer.parseInt(property.name())));
                    CompletableFuture<List<String>> result = CompletableFuture.completedFuture(new ArrayList<>());
                    for (final Runtime.PropertyDescriptor entry : entries) {
                        final String objectId = entry.value()
                                .orElseThrow(() -> new IllegalStateException("property value missing"))
                                .objectId()
                                .orElseThrow(() -> new IllegalStateException("object id missing"))
                                .value();
                        result = result.thenCombine(registerObject(objectId), (ids, id) -> {
                            ids.add(id);
                            return ids;
                        });
                    }
                    return result;
                })
                .whenComplete((ignored, failure) -> releaseObject(arrayObjectId))
                .toCompletableFuture();
    }

    private static boolean isArrayIndex(String name) {
        if (name.isEmpty()) return false;
        for (int i = 0; i < name.length(); i++) {
            if (!Character.isDigit(name.charAt(i))) return false;
        }
        try {
            return Integer.parseInt(name) >= 0;
        } catch (NumberFormatException ignored) {
            return false;
        }
    }

    private CompletableFuture<String> registerObject(String objectId) {
        return dom.describeNode(null, null, new Runtime.RemoteObjectId(objectId), null, null)
                .thenApply(description -> {
                    final DOM.BackendNodeId backendNodeId = description.backendNodeId();
                    final String id = UUID.randomUUID().toString();
                    elements.put(id, backendNodeId);
                    return id;
                })
                .whenComplete((ignored, failure) -> releaseObject(objectId))
                .toCompletableFuture();
    }

    @SuppressWarnings("FutureReturnValueIgnored")
    private CompletableFuture<String> resolveElement(String id) {
        final DOM.BackendNodeId backendNodeId = elements.get(id);
        if (backendNodeId == null) {
            return CompletableFuture.failedFuture(
                    failure(WebDriverError.STALE_ELEMENT_REFERENCE, "unknown element: " + id));
        }
        final CompletableFuture<String> result = new CompletableFuture<>();
        dom.resolveNode(null, backendNodeId, null, null).whenComplete((remoteObject, problem) -> {
            if (problem != null) {
                failStaleElement(id, result);
                return;
            }
            final Optional<Runtime.RemoteObjectId> objectId = remoteObject.objectId();
            if (objectId.isEmpty()) {
                failStaleElement(id, result);
                return;
            }
            final String resolved = objectId.get().value();
            callFunctionOnValue(resolved, "function(){return this.isConnected;}", List.of())
                    .whenComplete((connected, checkFailure) -> {
                        boolean attached;
                        try {
                            attached = checkFailure == null
                                    && readEvaluationResult(
                                                    connected.result(),
                                                    connected.exceptionDetails().orElse(null))
                                            .booleanValue();
                        } catch (RuntimeException ignored) {
                            // A result that cannot be read is a value the element does not have, so the
                            // element is not attached rather than the check failing the command.
                            attached = false;
                        }
                        if (attached) {
                            result.complete(resolved);
                        } else {
                            elements.remove(id);
                            releaseObject(resolved);
                            result.completeExceptionally(failure(
                                    WebDriverError.STALE_ELEMENT_REFERENCE,
                                    "element is no longer attached to the DOM"));
                        }
                    });
        });
        return result;
    }

    private void failStaleElement(String id, CompletableFuture<String> result) {
        elements.remove(id);
        result.completeExceptionally(
                failure(WebDriverError.STALE_ELEMENT_REFERENCE, "element is no longer attached to the DOM"));
    }

    private CompletableFuture<JsonElement> callElementValue(
            String id, String function, List<Runtime.CallArgument> args) {
        return resolveElement(id)
                .thenCompose(objectId -> callFunctionOnValue(objectId, function, args)
                        .whenComplete((ignored, failure) -> releaseObject(objectId)))
                .thenApply(this::readEvaluationResult);
    }

    private CompletableFuture<String> remoteObject(String expression) {
        return evaluateReference(expression).thenCompose(CdpAutomationBackend::remoteObjectId);
    }

    private static CompletableFuture<String> remoteObjectId(Runtime.EvaluateResult response) {
        return remoteObjectId(response.result(), response.exceptionDetails().orElse(null));
    }

    private static CompletableFuture<String> remoteObjectId(Runtime.CallFunctionOnResult response) {
        return remoteObjectId(response.result(), response.exceptionDetails().orElse(null));
    }

    private static CompletableFuture<String> remoteObjectId(
            Runtime.RemoteObject result, Runtime.@Nullable ExceptionDetails exceptionDetails) {
        if (exceptionDetails != null) {
            return CompletableFuture.failedFuture(failure(WebDriverError.INVALID_SELECTOR, exceptionDetails.text()));
        }
        if (result.objectId().isEmpty()) {
            return CompletableFuture.failedFuture(
                    failure(WebDriverError.NO_SUCH_ELEMENT, "locator did not produce a DOM node"));
        }
        return CompletableFuture.completedFuture(result.objectId().get().value());
    }

    private static List<Runtime.CallArgument> callArguments(String... values) {
        final List<Runtime.CallArgument> result = new ArrayList<>();
        for (String value : values) result.add(new Runtime.CallArgument().value(value));
        return result;
    }

    @SuppressWarnings("FutureReturnValueIgnored")
    private void releaseObject(String objectId) {
        runtime.releaseObject(new Runtime.RemoteObjectId(objectId)).exceptionally(ignored -> null);
    }

    private static void validateLocator(String using) {
        if (!"css selector".equals(using)
                && !"link text".equals(using)
                && !"partial link text".equals(using)
                && !"tag name".equals(using)
                && !"xpath".equals(using)) {
            throw failure(WebDriverError.INVALID_ARGUMENT, "unsupported locator strategy: " + using);
        }
    }

    private static WebDriverException failure(WebDriverError error, String message) {
        return new WebDriverException(error, message);
    }

    private static final String FIND_ELEMENTS = "function(using,value){"
            + "if(using==='css selector')return Array.from(this.querySelectorAll(value));"
            + "if(using==='tag name')return Array.from(this.getElementsByTagName(value));"
            + "if(using==='link text'||using==='partial link text'){return Array.from(this.querySelectorAll('a')).filter(a=>using==='link text'?a.textContent.trim()===value:a.textContent.includes(value));}"
            + "if(using==='xpath'){const d=this.ownerDocument||this;const r=d.evaluate(value,this,null,XPathResult.ORDERED_NODE_SNAPSHOT_TYPE,null);const a=[];for(let i=0;i<r.snapshotLength;i++)a.push(r.snapshotItem(i));return a;}"
            + "return [];}";

    private static final String DISPLAYED = "function(){"
            + "if(!this.isConnected)return false;const s=getComputedStyle(this);"
            + "if(s.display==='none'||s.visibility==='hidden'||s.visibility==='collapse'||Number(s.opacity)===0)return false;"
            + "const r=this.getBoundingClientRect();return r.width>0&&r.height>0;}";

    private static final String CLEAR_ELEMENT = "function(){"
            + "if(this.isContentEditable){this.textContent='';}"
            + "else if('value' in this){this.value='';}"
            + "else{throw new Error('element is not editable');}"
            + "this.dispatchEvent(new Event('input',{bubbles:true,composed:true}));"
            + "this.dispatchEvent(new Event('change',{bubbles:true}));}";

    private CompletableFuture<JsonElement> evaluateValue(String expression) {
        return evaluate(expression, ResultMode.VALUE).thenApply(this::readEvaluationResult);
    }

    /** Evaluates an expression to the reference of its value, which the caller releases. */
    private CompletableFuture<Runtime.EvaluateResult> evaluateReference(String expression) {
        return evaluate(expression, ResultMode.REFERENCE);
    }

    private CompletableFuture<Runtime.EvaluateResult> evaluate(String expression, ResultMode mode) {
        final boolean value = mode == ResultMode.VALUE;
        final Map<String, Object> params = new LinkedHashMap<>();
        params.put("expression", CdpObject.json(expression));
        params.put("returnByValue", value);
        params.put("awaitPromise", value);
        return client.call("Runtime.evaluate", params, Runtime.EvaluateResult::fromMap)
                .toCompletableFuture();
    }

    private CompletableFuture<Runtime.CallFunctionOnResult> callFunctionOnValue(
            String objectId, String function, List<Runtime.CallArgument> arguments) {
        return callFunctionOn(objectId, function, arguments, ResultMode.VALUE);
    }

    private CompletableFuture<Runtime.CallFunctionOnResult> callFunctionOnReference(
            String objectId, String function, List<Runtime.CallArgument> arguments) {
        return callFunctionOn(objectId, function, arguments, ResultMode.REFERENCE);
    }

    private CompletableFuture<Runtime.CallFunctionOnResult> callFunctionOn(
            String objectId, String function, List<Runtime.CallArgument> arguments, ResultMode mode) {
        final Map<String, Object> params = new LinkedHashMap<>();
        params.put("functionDeclaration", CdpObject.json(function));
        params.put("objectId", CdpObject.json(objectId));
        params.put("arguments", CdpObject.json(arguments));
        params.put("returnByValue", mode == ResultMode.VALUE);
        params.put("awaitPromise", true);
        return client.call("Runtime.callFunctionOn", params, Runtime.CallFunctionOnResult::fromMap)
                .toCompletableFuture();
    }

    private CompletableFuture<Void> awaitNotLoading() {
        final CompletableFuture<Void> result = new CompletableFuture<>();
        pollLoading(result);
        return result;
    }

    @SuppressWarnings("FutureReturnValueIgnored")
    private void pollLoading(CompletableFuture<Void> result) {
        if (result.isDone()) return;
        CompletableFuture.runAsync(() -> {}, CompletableFuture.delayedExecutor(25, TimeUnit.MILLISECONDS, pollExecutor))
                .thenCompose(ignored -> cdp.loading())
                .whenComplete((loading, failure) -> {
                    if (result.isDone()) return;
                    if (failure != null) result.completeExceptionally(failure);
                    else if (!loading) result.complete(null);
                    else pollLoading(result);
                });
    }

    private JsonElement readEvaluationResult(Runtime.EvaluateResult response) {
        return readEvaluationResult(
                response.result(), response.exceptionDetails().orElse(null));
    }

    private JsonElement readEvaluationResult(Runtime.CallFunctionOnResult response) {
        return readEvaluationResult(
                response.result(), response.exceptionDetails().orElse(null));
    }

    private JsonElement readEvaluationResult(
            Runtime.RemoteObject result, Runtime.@Nullable ExceptionDetails exceptionDetails) {
        if (exceptionDetails != null) {
            final String text = exceptionDetails
                    .exception()
                    .flatMap(Runtime.RemoteObject::description)
                    .orElseGet(exceptionDetails::text);
            throw failure(WebDriverError.JAVASCRIPT_ERROR, text);
        }
        final Optional<Object> value = result.value();
        if (value.isPresent()) return WebDriverJsonCodec.toJsonElement(value.orElseThrow());
        if ("undefined".equals(result.type().value())) return JsonNull.INSTANCE;
        final Optional<String> description = result.description();
        return description.map(cdp.jsonCodec()::decode).orElse(JsonNull.INSTANCE);
    }

    @Override
    public void cancelPendingCommands(Throwable failure) {
        client.cancelPending(failure);
    }

    @Override
    @SuppressWarnings("FutureReturnValueIgnored")
    public void close() {
        if (!closed.compareAndSet(false, true)) return;
        cdp.close();
    }
}
