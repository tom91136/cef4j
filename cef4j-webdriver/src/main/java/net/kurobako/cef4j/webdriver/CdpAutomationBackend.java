package net.kurobako.cef4j.webdriver;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalLong;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import javax.annotation.Nonnull;
import net.kurobako.cef4j.cdp.CdpClient;
import net.kurobako.cef4j.cdp.CdpObject;
import net.kurobako.cef4j.cdp.generated.Browser;
import net.kurobako.cef4j.cdp.generated.DOM;
import net.kurobako.cef4j.cdp.generated.Input;
import net.kurobako.cef4j.cdp.generated.Network;
import net.kurobako.cef4j.cdp.generated.Page;
import net.kurobako.cef4j.cdp.generated.Runtime;

/** Shared CDP-backed automation implementation, independent of in-process or remote CEF lifecycle. */
public final class CdpAutomationBackend implements AutomationBackend {
    private static final AtomicInteger POLL_THREAD_IDS = new AtomicInteger();
    private static final Executor POLL_EXECUTOR =
            Executors.newFixedThreadPool(java.lang.Runtime.getRuntime().availableProcessors(), task -> {
                Thread thread = new Thread(task, "cef4j-webdriver-poll-" + POLL_THREAD_IDS.incrementAndGet());
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
        JsonObject cef4j = new JsonObject();
        cef4j.addProperty("protocolVersion", version.protocolVersion());
        cef4j.addProperty("revision", version.revision());
        capabilities.add("cef4j:devtools", cef4j);
    }

    /** Queries browser metadata and creates the common WebDriver command backend. */
    @Nonnull
    public static CompletableFuture<CdpAutomationBackend> create(@Nonnull JsonCdpBrowser cdp) {
        return create(cdp, POLL_EXECUTOR);
    }

    @Nonnull
    public static CompletableFuture<CdpAutomationBackend> create(
            @Nonnull JsonCdpBrowser cdp, @Nonnull Executor pollExecutor) {
        Objects.requireNonNull(cdp, "cdp");
        Objects.requireNonNull(pollExecutor, "pollExecutor");
        CdpClient client = new CdpClient(cdp, new WebDriverCdpCodec(cdp.jsonCodec()));
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
    @Nonnull
    public JsonObject capabilities() {
        return capabilities.deepCopy();
    }

    @Override
    @Nonnull
    public CompletableFuture<Void> navigate(@Nonnull String url) {
        Objects.requireNonNull(url, "url");
        elements.clear();
        return cdp.loadUrl(url).thenCompose(ignored -> awaitNotLoading());
    }

    @Override
    @Nonnull
    public CompletableFuture<String> currentUrl() {
        return evaluateValue("window.location.href").thenApply(JsonElement::string);
    }

    @Override
    @Nonnull
    public CompletableFuture<String> title() {
        return evaluateValue("document.title").thenApply(JsonElement::string);
    }

    @Override
    @Nonnull
    public CompletableFuture<String> pageSource() {
        return dom.getDocument(OptionalLong.of(0), Optional.empty())
                .thenCompose(root -> {
                    Map<String, Object> params = new LinkedHashMap<>();
                    params.put("nodeId", CdpObject.json(root.nodeId()));
                    return client.call("DOM.getOuterHTML", params, result ->
                            (String) Objects.requireNonNull(result.get("outerHTML")));
                })
                .toCompletableFuture();
    }

    @Override
    @Nonnull
    public CompletableFuture<JsonElement> executeScript(@Nonnull String script, @Nonnull JsonArray arguments) {
        Objects.requireNonNull(script, "script");
        Objects.requireNonNull(arguments, "arguments");
        return remoteObject("globalThis").thenCompose(globalObject -> {
            CompletableFuture<List<Runtime.CallArgument>> callArguments =
                    CompletableFuture.completedFuture(new ArrayList<>());
            List<String> borrowedObjects = new ArrayList<>();
            for (JsonElement argument : arguments) {
                callArguments = callArguments.thenCompose(
                        built -> toCallArgument(argument, borrowedObjects).thenApply(converted -> {
                            built.add(converted);
                            return built;
                        }));
            }
            return callArguments
                    .thenCompose(converted -> callFunctionOn(
                            globalObject,
                            "function(){return (function(){" + script + "\n}).apply(null,arguments);}",
                            converted,
                            true))
                    .whenComplete((ignored, failure) -> {
                        releaseObject(globalObject);
                        borrowedObjects.forEach(this::releaseObject);
                    })
                    .thenApply(this::readEvaluationResult);
        });
    }

    @SuppressWarnings("NullAway")
    private CompletableFuture<Runtime.CallArgument> toCallArgument(JsonElement argument, List<String> borrowedObjects) {
        if (argument.isObject()
                && argument.asObject().has(WebDriverServer.ELEMENT_KEY)
                && argument.asObject().get(WebDriverServer.ELEMENT_KEY).isPrimitive()) {
            String id = argument.asObject().get(WebDriverServer.ELEMENT_KEY).string();
            return resolveElement(id).thenApply(objectId -> {
                borrowedObjects.add(objectId);
                return new Runtime.CallArgument().objectId(new Runtime.RemoteObjectId(objectId));
            });
        }
        return CompletableFuture.completedFuture(
                new Runtime.CallArgument().value(WebDriverCdpCodec.fromJsonElement(argument)));
    }

    @Override
    @Nonnull
    public CompletableFuture<byte[]> screenshot() {
        return page.captureScreenshot(
                        Optional.of(Page.CaptureScreenshotFormatValues.PNG),
                        OptionalLong.empty(),
                        Optional.empty(),
                        Optional.of(true),
                        Optional.empty(),
                        Optional.empty())
                .thenApply(data -> Base64.getDecoder().decode(data))
                .toCompletableFuture();
    }

    @Override
    @Nonnull
    public CompletableFuture<String> findElement(String using, String value, Optional<String> parentElement) {
        return findElements(using, value, parentElement).thenApply(found -> {
            if (found.isEmpty()) throw failure(WebDriverError.NO_SUCH_ELEMENT, "unable to locate element: " + value);
            return found.get(0);
        });
    }

    @Override
    @Nonnull
    public CompletableFuture<List<String>> findElements(String using, String value, Optional<String> parentElement) {
        validateLocator(using);
        CompletableFuture<String> root =
                parentElement.map(this::resolveElement).orElseGet(() -> remoteObject("document"));
        return root.thenCompose(objectId -> callFunctionOn(objectId, FIND_ELEMENTS, callArguments(using, value), false)
                        .whenComplete((ignored, failure) -> releaseObject(objectId)))
                .thenCompose(CdpAutomationBackend::remoteObjectId)
                .thenCompose(this::readElementArray);
    }

    @Override
    @Nonnull
    public CompletableFuture<String> activeElement() {
        return remoteObject("document.activeElement").thenCompose(this::registerObject);
    }

    @Override
    @Nonnull
    public CompletableFuture<String> elementTagName(String elementId) {
        return callElementValue(elementId, "function(){return this.tagName.toLowerCase();}", List.of())
                .thenApply(JsonElement::string);
    }

    @Override
    @Nonnull
    public CompletableFuture<String> elementText(String elementId) {
        return callElementValue(
                        elementId,
                        "function(){return this.innerText === undefined ? (this.textContent || '') : this.innerText;}",
                        List.of())
                .thenApply(JsonElement::string);
    }

    @Override
    @Nonnull
    public CompletableFuture<JsonElement> elementAttribute(String elementId, String name) {
        return callElementValue(elementId, "function(n){return this.getAttribute(n);}", callArguments(name));
    }

    @Override
    @Nonnull
    public CompletableFuture<JsonElement> elementProperty(String elementId, String name) {
        return callElementValue(elementId, "function(n){return this[n];}", callArguments(name));
    }

    @Override
    @Nonnull
    public CompletableFuture<String> elementCssValue(String elementId, String name) {
        return callElementValue(
                        elementId,
                        "function(n){return getComputedStyle(this).getPropertyValue(n);}",
                        callArguments(name))
                .thenApply(JsonElement::string);
    }

    @Override
    @Nonnull
    public CompletableFuture<JsonObject> elementRect(String elementId) {
        return callElementValue(
                        elementId,
                        "function(){const r=this.getBoundingClientRect();return {x:r.x,y:r.y,width:r.width,height:r.height};}",
                        List.of())
                .thenApply(JsonElement::asObject);
    }

    @Override
    @Nonnull
    public CompletableFuture<Boolean> elementDisplayed(String elementId) {
        return callElementValue(elementId, DISPLAYED, List.of()).thenApply(JsonElement::booleanValue);
    }

    @Override
    @Nonnull
    public CompletableFuture<Boolean> elementEnabled(String elementId) {
        return callElementValue(elementId, "function(){return !this.disabled;}", List.of())
                .thenApply(JsonElement::booleanValue);
    }

    @Override
    @Nonnull
    public CompletableFuture<Boolean> elementSelected(String elementId) {
        return callElementValue(
                        elementId,
                        "function(){return this.tagName==='OPTION' ? this.selected : !!this.checked;}",
                        List.of())
                .thenApply(JsonElement::booleanValue);
    }

    @Override
    @Nonnull
    public CompletableFuture<Void> elementClick(String elementId) {
        DOM.BackendNodeId backendNodeId = requireElementId(elementId);
        return resolveElement(elementId)
                .thenCompose(objectId -> dom.scrollIntoViewIfNeeded(
                                Optional.empty(), Optional.of(backendNodeId), Optional.empty(), Optional.empty())
                        .whenComplete((ignored, failure) -> releaseObject(objectId)))
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
                    double x = rect.get("x").doubleValue() + rect.get("width").doubleValue() / 2;
                    double y = rect.get("y").doubleValue() + rect.get("height").doubleValue() / 2;
                    List<Runtime.CallArgument> point =
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
                                return dispatchMouse("mouseMoved", x, y, Input.MouseButton.NONE, 0, 0)
                                        .thenCompose(ignored ->
                                                dispatchMouse("mousePressed", x, y, Input.MouseButton.LEFT, 1, 1))
                                        .thenCompose(ignored ->
                                                dispatchMouse("mouseReleased", x, y, Input.MouseButton.LEFT, 1, 0));
                            });
                });
    }

    @Override
    @Nonnull
    public CompletableFuture<Void> elementClear(String elementId) {
        return focusElement(elementId)
                .thenCompose(ignored -> callElementValue(elementId, CLEAR_ELEMENT, List.of()))
                .thenApply(ignored -> null);
    }

    @Override
    @Nonnull
    public CompletableFuture<Void> elementSendKeys(String elementId, String text) {
        Objects.requireNonNull(text, "text");
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) >= '\ue000' && text.charAt(i) <= '\ue05d') {
                return failed(failure(
                        WebDriverError.UNSUPPORTED_OPERATION,
                        "special WebDriver keys are not implemented by this endpoint yet"));
            }
        }
        return focusElement(elementId)
                .thenCompose(ignored -> input.insertText(text).thenApply(v -> null));
    }

    @Override
    @Nonnull
    public CompletableFuture<Void> back() {
        elements.clear();
        return cdp.canGoBack()
                .thenCompose(
                        canGoBack -> canGoBack ? waitForLoad(cdp::goBack) : CompletableFuture.completedFuture(null));
    }

    @Override
    @Nonnull
    public CompletableFuture<Void> forward() {
        elements.clear();
        return cdp.canGoForward()
                .thenCompose(canGoForward ->
                        canGoForward ? waitForLoad(cdp::goForward) : CompletableFuture.completedFuture(null));
    }

    @Override
    @Nonnull
    public CompletableFuture<Void> refresh() {
        elements.clear();
        return waitForLoad(() -> page.reload().toCompletableFuture());
    }

    private CompletableFuture<Void> waitForLoad(Supplier<? extends CompletableFuture<?>> command) {
        return command.get().thenCompose(ignored -> awaitNotLoading());
    }

    @Override
    @Nonnull
    public CompletableFuture<JsonArray> cookies() {
        return currentUrl()
                .thenCompose(url -> network.getCookies(Optional.of(List.of(url))))
                .thenApply(this::readCookies);
    }

    private JsonArray readCookies(List<Network.Cookie> source) {
        JsonArray result = new JsonArray();
        for (Network.Cookie cookie : source) {
            JsonObject item = new JsonObject();
            item.addProperty("name", cookie.name());
            item.addProperty("value", cookie.value());
            item.addProperty("path", cookie.path());
            item.addProperty("domain", cookie.domain());
            item.addProperty("secure", cookie.secure());
            item.addProperty("httpOnly", cookie.httpOnly());
            if (cookie.sameSite().isPresent())
                item.addProperty("sameSite", cookie.sameSite().get().value());
            if (cookie.expires() >= 0) item.addProperty("expiry", (long) cookie.expires());
            result.add(item);
        }
        return result;
    }

    @Override
    @Nonnull
    public CompletableFuture<JsonElement> cookie(String name) {
        return cookies().thenApply(all -> {
            for (JsonElement item : all) {
                if (name.equals(item.asObject().get("name").string())) return item;
            }
            throw failure(WebDriverError.NO_SUCH_COOKIE, "no cookie named " + name);
        });
    }

    @Override
    @Nonnull
    public CompletableFuture<Void> addCookie(JsonObject cookie) {
        validateCookie(cookie);
        return currentUrl()
                .thenCompose(url -> {
                    Map<String, Object> params = new LinkedHashMap<>();
                    params.put("name", CdpObject.json(cookie.get("name").string()));
                    params.put("value", CdpObject.json(cookie.get("value").string()));
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
        requireCookieString(cookie, "name", true);
        requireCookieString(cookie, "value", true);
        requireCookieString(cookie, "domain", false);
        requireCookieString(cookie, "path", false);
        requireCookieBoolean(cookie, "secure");
        requireCookieBoolean(cookie, "httpOnly");
        validateCookieSameSite(cookie);
        validateCookieExpiry(cookie);
    }

    private static void requireCookieString(JsonObject cookie, String name, boolean required) {
        JsonElement value = cookie.get(name);
        if (value == null && !required) return;
        if (value == null || !value.isPrimitive() || !value.asPrimitive().isString()) {
            throw failure(WebDriverError.INVALID_ARGUMENT, "cookie " + name + " must be a string");
        }
    }

    private static void requireCookieBoolean(JsonObject cookie, String name) {
        JsonElement value = cookie.get(name);
        if (value != null && (!value.isPrimitive() || !value.asPrimitive().isBoolean())) {
            throw failure(WebDriverError.INVALID_ARGUMENT, "cookie " + name + " must be a boolean");
        }
    }

    private static void validateCookieSameSite(JsonObject cookie) {
        JsonElement value = cookie.get("sameSite");
        if (value == null) return;
        if (!value.isPrimitive() || !value.asPrimitive().isString()) {
            throw failure(WebDriverError.INVALID_ARGUMENT, "cookie sameSite must be a string");
        }
        String sameSite = value.string();
        if (!sameSite.equals("Strict") && !sameSite.equals("Lax") && !sameSite.equals("None")) {
            throw failure(WebDriverError.INVALID_ARGUMENT, "invalid cookie sameSite: " + sameSite);
        }
    }

    private static void validateCookieExpiry(JsonObject cookie) {
        JsonElement value = cookie.get("expiry");
        if (value == null) return;
        if (!value.isPrimitive() || !value.asPrimitive().isNumber()) {
            throw failure(WebDriverError.INVALID_ARGUMENT, "cookie expiry must be a non-negative integer");
        }
        try {
            if (new java.math.BigDecimal(value.string()).longValueExact() < 0) throw new ArithmeticException();
        } catch (ArithmeticException | NumberFormatException ignored) {
            throw failure(WebDriverError.INVALID_ARGUMENT, "cookie expiry must be a non-negative integer");
        }
    }

    private static Optional<String> optionalString(JsonObject source, String name) {
        return source.has(name) ? Optional.of(source.get(name).string()) : Optional.empty();
    }

    private static Optional<Boolean> optionalBoolean(JsonObject source, String name) {
        return source.has(name) ? Optional.of(source.get(name).booleanValue()) : Optional.empty();
    }

    private static Optional<Network.CookieSameSite> optionalSameSite(JsonObject source) {
        return source.has("sameSite")
                ? Optional.of(Network.CookieSameSite.of(source.get("sameSite").string()))
                : Optional.empty();
    }

    private static Optional<Network.TimeSinceEpoch> optionalExpiry(JsonObject source) {
        return source.has("expiry")
                ? Optional.of(new Network.TimeSinceEpoch(source.get("expiry").doubleValue()))
                : Optional.empty();
    }

    @Override
    @Nonnull
    public CompletableFuture<Void> deleteCookie(String name) {
        return currentUrl()
                .thenCompose(url -> {
                    Map<String, Object> params = new LinkedHashMap<>();
                    params.put("name", CdpObject.json(name));
                    params.put("url", CdpObject.json(url));
                    return client.<Void>call("Network.deleteCookies", params, result -> null);
                })
                .thenApply(ignored -> null);
    }

    @Override
    @Nonnull
    public CompletableFuture<Void> deleteAllCookies() {
        return network.clearBrowserCookies().<Void>thenApply(ignored -> null).toCompletableFuture();
    }

    private CompletableFuture<Void> focusElement(String elementId) {
        DOM.BackendNodeId backendNodeId = requireElementId(elementId);
        return resolveElement(elementId)
                .thenCompose(objectId -> dom.focus(Optional.empty(), Optional.of(backendNodeId), Optional.empty())
                        .whenComplete((ignored, failure) -> releaseObject(objectId)))
                .thenApply(ignored -> null);
    }

    private CompletableFuture<Void> dispatchMouse(
            String type, double x, double y, Input.MouseButton button, int clickCount, int buttons) {
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("type", CdpObject.json(type));
        params.put("x", x);
        params.put("y", y);
        params.put("button", CdpObject.json(button));
        params.put("buttons", buttons);
        params.put("clickCount", clickCount);
        return client.<Void>call("Input.dispatchMouseEvent", params, result -> null)
                .toCompletableFuture();
    }

    private DOM.BackendNodeId requireElementId(String id) {
        DOM.BackendNodeId backendNodeId = elements.get(id);
        if (backendNodeId == null) throw failure(WebDriverError.STALE_ELEMENT_REFERENCE, "unknown element: " + id);
        return backendNodeId;
    }

    private CompletableFuture<List<String>> readElementArray(String arrayObjectId) {
        return runtime.getProperties(
                        new Runtime.RemoteObjectId(arrayObjectId),
                        Optional.of(true),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty())
                .thenCompose(properties -> {
                    List<Runtime.PropertyDescriptor> entries = new ArrayList<>();
                    for (Runtime.PropertyDescriptor property : properties.result()) {
                        if (property.value().isPresent()
                                && property.value().get().objectId().isPresent()) {
                            entries.add(property);
                        }
                    }
                    entries.sort(Comparator.comparingInt(property -> Integer.parseInt(property.name())));
                    CompletableFuture<List<String>> result = CompletableFuture.completedFuture(new ArrayList<>());
                    for (Runtime.PropertyDescriptor entry : entries) {
                        String objectId = entry.value().get().objectId().get().value();
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

    private CompletableFuture<String> registerObject(String objectId) {
        return dom.describeNode(
                        Optional.empty(),
                        Optional.empty(),
                        Optional.of(new Runtime.RemoteObjectId(objectId)),
                        OptionalLong.empty(),
                        Optional.empty())
                .thenApply(description -> {
                    DOM.BackendNodeId backendNodeId = description.backendNodeId();
                    String id = UUID.randomUUID().toString();
                    elements.put(id, backendNodeId);
                    return id;
                })
                .whenComplete((ignored, failure) -> releaseObject(objectId))
                .toCompletableFuture();
    }

    @SuppressWarnings("FutureReturnValueIgnored")
    private CompletableFuture<String> resolveElement(String id) {
        DOM.BackendNodeId backendNodeId = elements.get(id);
        if (backendNodeId == null) {
            return failed(failure(WebDriverError.STALE_ELEMENT_REFERENCE, "unknown element: " + id));
        }
        CompletableFuture<String> result = new CompletableFuture<>();
        dom.resolveNode(Optional.empty(), Optional.of(backendNodeId), Optional.empty(), Optional.empty())
                .whenComplete((remoteObject, problem) -> {
                    if (problem != null) {
                        elements.remove(id);
                        result.completeExceptionally(failure(
                                WebDriverError.STALE_ELEMENT_REFERENCE, "element is no longer attached to the DOM"));
                        return;
                    }
                    Optional<Runtime.RemoteObjectId> objectId = remoteObject.objectId();
                    if (objectId.isEmpty()) {
                        elements.remove(id);
                        result.completeExceptionally(failure(
                                WebDriverError.STALE_ELEMENT_REFERENCE, "element is no longer attached to the DOM"));
                        return;
                    }
                    String resolved = objectId.get().value();
                    callFunctionOn(resolved, "function(){return this.isConnected;}", List.of(), true)
                            .whenComplete((connected, checkFailure) -> {
                                boolean attached = false;
                                if (checkFailure == null) {
                                    try {
                                        attached = readEvaluationResult(
                                                        connected.result(), connected.exceptionDetails())
                                                .booleanValue();
                                    } catch (RuntimeException ignored) {
                                        attached = false;
                                    }
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

    private CompletableFuture<JsonElement> callElementValue(
            String id, String function, List<Runtime.CallArgument> args) {
        return resolveElement(id)
                .thenCompose(objectId -> callFunctionOn(objectId, function, args, true)
                        .whenComplete((ignored, failure) -> releaseObject(objectId)))
                .thenApply(this::readEvaluationResult);
    }

    private CompletableFuture<String> remoteObject(String expression) {
        return evaluate(expression, false, false).thenCompose(CdpAutomationBackend::remoteObjectId);
    }

    private static CompletableFuture<String> remoteObjectId(Runtime.EvaluateResult response) {
        return remoteObjectId(response.result(), response.exceptionDetails());
    }

    private static CompletableFuture<String> remoteObjectId(Runtime.CallFunctionOnResult response) {
        return remoteObjectId(response.result(), response.exceptionDetails());
    }

    private static CompletableFuture<String> remoteObjectId(
            Runtime.RemoteObject result, Optional<Runtime.ExceptionDetails> exceptionDetails) {
        if (exceptionDetails.isPresent()) {
            return failed(failure(
                    WebDriverError.INVALID_SELECTOR, exceptionDetails.get().text()));
        }
        if (result.objectId().isEmpty()) {
            return failed(failure(WebDriverError.NO_SUCH_ELEMENT, "locator did not produce a DOM node"));
        }
        return CompletableFuture.completedFuture(result.objectId().get().value());
    }

    private static List<Runtime.CallArgument> callArguments(String... values) {
        List<Runtime.CallArgument> result = new ArrayList<>();
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

    private static <T> CompletableFuture<T> failed(Throwable failure) {
        CompletableFuture<T> result = new CompletableFuture<>();
        result.completeExceptionally(failure);
        return result;
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
        return evaluate(expression, true, true).thenApply(this::readEvaluationResult);
    }

    private CompletableFuture<Runtime.EvaluateResult> evaluate(
            String expression, boolean returnByValue, boolean awaitPromise) {
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("expression", CdpObject.json(expression));
        params.put("returnByValue", returnByValue);
        params.put("awaitPromise", awaitPromise);
        return client.call("Runtime.evaluate", params, Runtime.EvaluateResult::fromMap)
                .toCompletableFuture();
    }

    private CompletableFuture<Runtime.CallFunctionOnResult> callFunctionOn(
            String objectId, String function, List<Runtime.CallArgument> arguments, boolean returnByValue) {
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("functionDeclaration", CdpObject.json(function));
        params.put("objectId", CdpObject.json(objectId));
        params.put("arguments", CdpObject.json(arguments));
        params.put("returnByValue", returnByValue);
        params.put("awaitPromise", true);
        return client.call("Runtime.callFunctionOn", params, Runtime.CallFunctionOnResult::fromMap)
                .toCompletableFuture();
    }

    private CompletableFuture<Void> awaitNotLoading() {
        CompletableFuture<Void> result = new CompletableFuture<>();
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
        return readEvaluationResult(response.result(), response.exceptionDetails());
    }

    private JsonElement readEvaluationResult(Runtime.CallFunctionOnResult response) {
        return readEvaluationResult(response.result(), response.exceptionDetails());
    }

    private JsonElement readEvaluationResult(
            Runtime.RemoteObject result, Optional<Runtime.ExceptionDetails> exceptionDetails) {
        if (exceptionDetails.isPresent()) {
            Runtime.ExceptionDetails details = exceptionDetails.get();
            String text = details.text();
            if (details.exception().isPresent()) {
                Optional<String> description = details.exception().get().description();
                if (description.isPresent()) text = description.get();
            }
            throw failure(WebDriverError.JAVASCRIPT_ERROR, text);
        }
        Optional<Object> value = result.value();
        if (value.isPresent()) return WebDriverCdpCodec.toJsonElement(value.get());
        if ("undefined".equals(result.type().value())) return JsonNull.INSTANCE;
        Optional<String> description = result.description();
        return description.isEmpty() ? JsonNull.INSTANCE : cdp.jsonCodec().decode(description.get());
    }

    @Override
    public void cancelPendingCommands(@Nonnull Throwable failure) {
        client.cancelPending(failure);
    }

    @Override
    @SuppressWarnings("FutureReturnValueIgnored")
    public void close() {
        if (!closed.compareAndSet(false, true)) return;
        cdp.close();
    }
}
