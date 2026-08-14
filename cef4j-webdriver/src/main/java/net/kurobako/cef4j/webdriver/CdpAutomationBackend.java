package net.kurobako.cef4j.webdriver;

import java.util.Base64;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/** Shared CDP-backed automation implementation, independent of in-process or remote CEF lifecycle. */
public final class CdpAutomationBackend implements AutomationBackend {
    private final JsonCdpBrowser cdp;
    private final JsonObject capabilities;
    private final AtomicBoolean closed = new AtomicBoolean();
    private final ConcurrentHashMap<String, Integer> elements = new ConcurrentHashMap<>();

    private CdpAutomationBackend(JsonCdpBrowser cdp, JsonObject version) {
        this.cdp = cdp;
        capabilities = new JsonObject();
        capabilities.addProperty("browserName", "cef4j");
        capabilities.addProperty("browserVersion", stringValue(version, "product", "unknown"));
        capabilities.addProperty(
                "platformName", System.getProperty("os.name", "unknown").toLowerCase(Locale.ROOT));
        capabilities.addProperty("acceptInsecureCerts", false);
        capabilities.addProperty("pageLoadStrategy", "normal");
        capabilities.addProperty("setWindowRect", false);
        JsonObject cef4j = new JsonObject();
        cef4j.addProperty("protocolVersion", stringValue(version, "protocolVersion", "unknown"));
        cef4j.addProperty("revision", stringValue(version, "revision", "unknown"));
        capabilities.add("cef4j:devtools", cef4j);
    }

    /** Queries browser metadata and creates the common WebDriver command backend. */
    @Nonnull
    public static CompletableFuture<CdpAutomationBackend> create(@Nonnull JsonCdpBrowser cdp) {
        Objects.requireNonNull(cdp, "cdp");
        return cdp.send("Browser.getVersion", null)
                .thenApply(version -> new CdpAutomationBackend(cdp, version))
                .whenComplete((backend, failure) -> {
                    if (failure != null) cdp.close();
                });
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
        return cdp.loadUrl(url)
                .thenCompose(ignored -> awaitNotLoading(System.nanoTime() + TimeUnit.SECONDS.toNanos(30)));
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
        JsonObject documentParams = new JsonObject();
        documentParams.addProperty("depth", 0);
        return cdp.send("DOM.getDocument", documentParams)
                .thenCompose(document -> {
                    JsonObject params = new JsonObject();
                    params.addProperty(
                            "nodeId", document.object("root").get("nodeId").intValue());
                    return cdp.send("DOM.getOuterHTML", params);
                })
                .thenApply(result -> result.get("outerHTML").string());
    }

    @Override
    @Nonnull
    public CompletableFuture<JsonElement> executeScript(@Nonnull String script, @Nonnull JsonArray arguments) {
        Objects.requireNonNull(script, "script");
        Objects.requireNonNull(arguments, "arguments");
        return remoteObject("globalThis").thenCompose(globalObject -> {
            CompletableFuture<JsonArray> callArguments = CompletableFuture.completedFuture(new JsonArray());
            List<String> borrowedObjects = new java.util.ArrayList<>();
            for (JsonElement argument : arguments) {
                callArguments = callArguments.thenCompose(
                        built -> toCallArgument(argument, borrowedObjects).thenApply(converted -> {
                            built.add(converted);
                            return built;
                        }));
            }
            return callArguments
                    .thenCompose(converted -> {
                        JsonObject params = new JsonObject();
                        params.addProperty("objectId", globalObject);
                        params.addProperty(
                                "functionDeclaration",
                                "function(){return (function(){" + script + "\n}).apply(null,arguments);}");
                        params.addProperty("returnByValue", true);
                        params.addProperty("awaitPromise", true);
                        params.add("arguments", converted);
                        return cdp.send("Runtime.callFunctionOn", params);
                    })
                    .whenComplete((ignored, failure) -> {
                        releaseObject(globalObject);
                        borrowedObjects.forEach(this::releaseObject);
                    })
                    .thenApply(this::readEvaluationResult);
        });
    }

    private CompletableFuture<JsonObject> toCallArgument(JsonElement argument, List<String> borrowedObjects) {
        if (argument.isObject()
                && argument.asObject().has(WebDriverServer.ELEMENT_KEY)
                && argument.asObject().get(WebDriverServer.ELEMENT_KEY).isPrimitive()) {
            String id = argument.asObject().get(WebDriverServer.ELEMENT_KEY).string();
            return resolveElement(id).thenApply(objectId -> {
                borrowedObjects.add(objectId);
                JsonObject converted = new JsonObject();
                converted.addProperty("objectId", objectId);
                return converted;
            });
        }
        JsonObject converted = new JsonObject();
        converted.add("value", argument.deepCopy());
        return CompletableFuture.completedFuture(converted);
    }

    @Override
    @Nonnull
    public CompletableFuture<byte[]> screenshot() {
        JsonObject params = new JsonObject();
        params.addProperty("format", "png");
        params.addProperty("fromSurface", true);
        return cdp.send("Page.captureScreenshot", params)
                .thenApply(
                        result -> Base64.getDecoder().decode(result.get("data").string()));
    }

    @Override
    @Nonnull
    public CompletableFuture<String> findElement(String using, String value, @Nullable String parentElement) {
        return findElements(using, value, parentElement).thenApply(found -> {
            if (found.isEmpty()) throw failure(WebDriverError.NO_SUCH_ELEMENT, "unable to locate element: " + value);
            return found.get(0);
        });
    }

    @Override
    @Nonnull
    public CompletableFuture<List<String>> findElements(String using, String value, @Nullable String parentElement) {
        validateLocator(using);
        CompletableFuture<String> root =
                parentElement == null ? remoteObject("document") : resolveElement(parentElement);
        return root.thenCompose(objectId -> cdp.send(
                                "Runtime.callFunctionOn",
                                callParams(objectId, FIND_ELEMENTS, arguments(using, value), false))
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
        return callElementValue(elementId, "function(){return this.tagName.toLowerCase();}", new JsonArray())
                .thenApply(JsonElement::string);
    }

    @Override
    @Nonnull
    public CompletableFuture<String> elementText(String elementId) {
        return callElementValue(
                        elementId,
                        "function(){return this.innerText === undefined ? (this.textContent || '') : this.innerText;}",
                        new JsonArray())
                .thenApply(JsonElement::string);
    }

    @Override
    @Nonnull
    public CompletableFuture<JsonElement> elementAttribute(String elementId, String name) {
        return callElementValue(elementId, "function(n){return this.getAttribute(n);}", arguments(name));
    }

    @Override
    @Nonnull
    public CompletableFuture<JsonElement> elementProperty(String elementId, String name) {
        return callElementValue(elementId, "function(n){return this[n];}", arguments(name));
    }

    @Override
    @Nonnull
    public CompletableFuture<String> elementCssValue(String elementId, String name) {
        return callElementValue(
                        elementId, "function(n){return getComputedStyle(this).getPropertyValue(n);}", arguments(name))
                .thenApply(JsonElement::string);
    }

    @Override
    @Nonnull
    public CompletableFuture<JsonObject> elementRect(String elementId) {
        return callElementValue(
                        elementId,
                        "function(){const r=this.getBoundingClientRect();return {x:r.x,y:r.y,width:r.width,height:r.height};}",
                        new JsonArray())
                .thenApply(JsonElement::asObject);
    }

    @Override
    @Nonnull
    public CompletableFuture<Boolean> elementDisplayed(String elementId) {
        return callElementValue(elementId, DISPLAYED, new JsonArray()).thenApply(JsonElement::booleanValue);
    }

    @Override
    @Nonnull
    public CompletableFuture<Boolean> elementEnabled(String elementId) {
        return callElementValue(elementId, "function(){return !this.disabled;}", new JsonArray())
                .thenApply(JsonElement::booleanValue);
    }

    @Override
    @Nonnull
    public CompletableFuture<Boolean> elementSelected(String elementId) {
        return callElementValue(
                        elementId,
                        "function(){return this.tagName==='OPTION' ? this.selected : !!this.checked;}",
                        new JsonArray())
                .thenApply(JsonElement::booleanValue);
    }

    @Override
    @Nonnull
    public CompletableFuture<Void> elementClick(String elementId) {
        Integer backendNodeId = requireElementId(elementId);
        JsonObject scroll = new JsonObject();
        scroll.addProperty("backendNodeId", backendNodeId);
        return resolveElement(elementId)
                .thenCompose(objectId -> cdp.send("DOM.scrollIntoViewIfNeeded", scroll)
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
                    JsonArray point = new JsonArray();
                    point.add(x);
                    point.add(y);
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
                                return dispatchMouse("mousePressed", x, y)
                                        .thenCompose(ignored -> dispatchMouse("mouseReleased", x, y));
                            });
                });
    }

    @Override
    @Nonnull
    public CompletableFuture<Void> elementClear(String elementId) {
        return focusElement(elementId)
                .thenCompose(ignored -> callElementValue(elementId, CLEAR_ELEMENT, new JsonArray()))
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
        JsonObject params = new JsonObject();
        params.addProperty("text", text);
        return focusElement(elementId)
                .thenCompose(ignored -> cdp.send("Input.insertText", params).thenApply(v -> null));
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
        return waitForLoad(() -> cdp.send("Page.reload", null));
    }

    private CompletableFuture<Void> waitForLoad(Supplier<? extends CompletableFuture<?>> command) {
        return command.get().thenCompose(ignored -> awaitNotLoading(System.nanoTime() + TimeUnit.SECONDS.toNanos(30)));
    }

    @Override
    @Nonnull
    public CompletableFuture<JsonArray> cookies() {
        return currentUrl()
                .thenCompose(url -> {
                    JsonObject params = new JsonObject();
                    JsonArray urls = new JsonArray();
                    urls.add(url);
                    params.add("urls", urls);
                    return cdp.send("Network.getCookies", params);
                })
                .thenApply(response -> {
                    JsonArray result = new JsonArray();
                    for (JsonElement item : response.array("cookies")) {
                        JsonObject source = item.asObject();
                        JsonObject cookie = new JsonObject();
                        copy(source, cookie, "name");
                        copy(source, cookie, "value");
                        copy(source, cookie, "path");
                        copy(source, cookie, "domain");
                        copy(source, cookie, "secure");
                        copy(source, cookie, "httpOnly");
                        copy(source, cookie, "sameSite");
                        if (source.has("expires") && source.get("expires").doubleValue() >= 0) {
                            cookie.addProperty(
                                    "expiry", (long) source.get("expires").doubleValue());
                        }
                        result.add(cookie);
                    }
                    return result;
                });
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
        if (!cookie.has("name") || !cookie.get("name").isPrimitive()) {
            return failed(failure(WebDriverError.INVALID_ARGUMENT, "cookie name must be a string"));
        }
        if (!cookie.has("value") || !cookie.get("value").isPrimitive()) {
            return failed(failure(WebDriverError.INVALID_ARGUMENT, "cookie value must be a string"));
        }
        return currentUrl().thenCompose(url -> {
            JsonObject params = new JsonObject();
            params.addProperty("name", cookie.get("name").string());
            params.addProperty("value", cookie.get("value").string());
            params.addProperty("url", url);
            copy(cookie, params, "domain");
            copy(cookie, params, "path");
            copy(cookie, params, "secure");
            copy(cookie, params, "httpOnly");
            copy(cookie, params, "sameSite");
            if (cookie.has("expiry")) params.add("expires", cookie.get("expiry").deepCopy());
            return cdp.send("Network.setCookie", params).thenApply(response -> {
                if (response.has("success") && !response.get("success").booleanValue()) {
                    throw failure(WebDriverError.INVALID_ARGUMENT, "CEF rejected cookie");
                }
                return null;
            });
        });
    }

    @Override
    @Nonnull
    public CompletableFuture<Void> deleteCookie(String name) {
        return currentUrl().thenCompose(url -> {
            JsonObject params = new JsonObject();
            params.addProperty("name", name);
            params.addProperty("url", url);
            return cdp.send("Network.deleteCookies", params).thenApply(ignored -> null);
        });
    }

    @Override
    @Nonnull
    public CompletableFuture<Void> deleteAllCookies() {
        return cdp.send("Network.clearBrowserCookies", null).thenApply(ignored -> null);
    }

    private static void copy(JsonObject source, JsonObject target, String name) {
        if (source.has(name)) target.add(name, source.get(name).deepCopy());
    }

    private CompletableFuture<Void> focusElement(String elementId) {
        int backendNodeId = requireElementId(elementId);
        JsonObject params = new JsonObject();
        params.addProperty("backendNodeId", backendNodeId);
        return resolveElement(elementId)
                .thenCompose(objectId ->
                        cdp.send("DOM.focus", params).whenComplete((ignored, failure) -> releaseObject(objectId)))
                .thenApply(ignored -> null);
    }

    private CompletableFuture<Void> dispatchMouse(String type, double x, double y) {
        JsonObject params = new JsonObject();
        params.addProperty("type", type);
        params.addProperty("x", x);
        params.addProperty("y", y);
        params.addProperty("button", "left");
        params.addProperty("clickCount", 1);
        return cdp.send("Input.dispatchMouseEvent", params).thenApply(ignored -> null);
    }

    private int requireElementId(String id) {
        Integer backendNodeId = elements.get(id);
        if (backendNodeId == null) throw failure(WebDriverError.STALE_ELEMENT_REFERENCE, "unknown element: " + id);
        return backendNodeId;
    }

    private CompletableFuture<List<String>> readElementArray(String arrayObjectId) {
        JsonObject params = new JsonObject();
        params.addProperty("objectId", arrayObjectId);
        params.addProperty("ownProperties", true);
        return cdp.send("Runtime.getProperties", params)
                .thenCompose(properties -> {
                    List<JsonObject> entries = new java.util.ArrayList<>();
                    for (JsonElement entry : properties.array("result")) {
                        JsonObject property = entry.asObject();
                        if (property.has("value") && property.object("value").has("objectId")) entries.add(property);
                    }
                    entries.sort(Comparator.comparingInt(
                            property -> Integer.parseInt(property.get("name").string())));
                    CompletableFuture<List<String>> result =
                            CompletableFuture.completedFuture(new java.util.ArrayList<>());
                    for (JsonObject entry : entries) {
                        String objectId = entry.object("value").get("objectId").string();
                        result = result.thenCombine(registerObject(objectId), (ids, id) -> {
                            ids.add(id);
                            return ids;
                        });
                    }
                    return result;
                })
                .whenComplete((ignored, failure) -> releaseObject(arrayObjectId));
    }

    private CompletableFuture<String> registerObject(String objectId) {
        JsonObject params = new JsonObject();
        params.addProperty("objectId", objectId);
        return cdp.send("DOM.describeNode", params)
                .thenApply(description -> {
                    int backendNodeId =
                            description.object("node").get("backendNodeId").intValue();
                    String id = UUID.randomUUID().toString();
                    elements.put(id, backendNodeId);
                    return id;
                })
                .whenComplete((ignored, failure) -> releaseObject(objectId));
    }

    @SuppressWarnings("FutureReturnValueIgnored")
    private CompletableFuture<String> resolveElement(String id) {
        Integer backendNodeId = elements.get(id);
        if (backendNodeId == null) {
            return failed(failure(WebDriverError.STALE_ELEMENT_REFERENCE, "unknown element: " + id));
        }
        JsonObject params = new JsonObject();
        params.addProperty("backendNodeId", backendNodeId);
        CompletableFuture<String> result = new CompletableFuture<>();
        cdp.send("DOM.resolveNode", params).whenComplete((response, problem) -> {
            if (problem != null) {
                elements.remove(id);
                result.completeExceptionally(
                        failure(WebDriverError.STALE_ELEMENT_REFERENCE, "element is no longer attached to the DOM"));
            } else {
                String objectId = response.object("object").get("objectId").string();
                cdp.send(
                                "Runtime.callFunctionOn",
                                callParams(objectId, "function(){return this.isConnected;}", new JsonArray(), true))
                        .whenComplete((connected, checkFailure) -> {
                            boolean attached = false;
                            if (checkFailure == null) {
                                try {
                                    attached = readEvaluationResult(connected).booleanValue();
                                } catch (RuntimeException ignored) {
                                    // Treat an execution-context loss as a stale node.
                                }
                            }
                            if (attached) {
                                result.complete(objectId);
                            } else {
                                elements.remove(id);
                                releaseObject(objectId);
                                result.completeExceptionally(failure(
                                        WebDriverError.STALE_ELEMENT_REFERENCE,
                                        "element is no longer attached to the DOM"));
                            }
                        });
            }
        });
        return result;
    }

    private CompletableFuture<JsonElement> callElementValue(String id, String function, JsonArray args) {
        return resolveElement(id)
                .thenCompose(objectId -> cdp.send("Runtime.callFunctionOn", callParams(objectId, function, args, true))
                        .whenComplete((ignored, failure) -> releaseObject(objectId)))
                .thenApply(this::readEvaluationResult);
    }

    private CompletableFuture<String> remoteObject(String expression) {
        JsonObject params = new JsonObject();
        params.addProperty("expression", expression);
        params.addProperty("returnByValue", false);
        return cdp.send("Runtime.evaluate", params).thenCompose(CdpAutomationBackend::remoteObjectId);
    }

    private static CompletableFuture<String> remoteObjectId(JsonObject response) {
        if (response.has("exceptionDetails")) {
            return failed(failure(
                    WebDriverError.INVALID_SELECTOR,
                    stringValue(response.object("exceptionDetails"), "text", "locator evaluation failed")));
        }
        JsonObject result = response.object("result");
        if (result == null || !result.has("objectId")) {
            return failed(failure(WebDriverError.NO_SUCH_ELEMENT, "locator did not produce a DOM node"));
        }
        return CompletableFuture.completedFuture(result.get("objectId").string());
    }

    private static JsonObject callParams(String objectId, String function, JsonArray args, boolean returnByValue) {
        JsonObject params = new JsonObject();
        params.addProperty("objectId", objectId);
        params.addProperty("functionDeclaration", function);
        params.addProperty("returnByValue", returnByValue);
        params.addProperty("awaitPromise", true);
        JsonArray callArguments = new JsonArray();
        for (JsonElement argument : args) {
            JsonObject callArgument = new JsonObject();
            callArgument.add("value", argument.deepCopy());
            callArguments.add(callArgument);
        }
        params.add("arguments", callArguments);
        return params;
    }

    private static JsonArray arguments(String... values) {
        JsonArray result = new JsonArray();
        for (String value : values) result.add(value);
        return result;
    }

    @SuppressWarnings("FutureReturnValueIgnored")
    private void releaseObject(String objectId) {
        JsonObject params = new JsonObject();
        params.addProperty("objectId", objectId);
        cdp.send("Runtime.releaseObject", params).exceptionally(ignored -> null);
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
        JsonObject params = new JsonObject();
        params.addProperty("expression", expression);
        params.addProperty("awaitPromise", true);
        params.addProperty("returnByValue", true);
        return cdp.send("Runtime.evaluate", params).thenApply(this::readEvaluationResult);
    }

    private CompletableFuture<Void> awaitNotLoading(long deadlineNanos) {
        return CompletableFuture.runAsync(() -> {}, CompletableFuture.delayedExecutor(25, TimeUnit.MILLISECONDS))
                .thenCompose(ignored -> cdp.loading())
                .thenCompose(loading -> {
                    if (!loading) return CompletableFuture.completedFuture(null);
                    if (System.nanoTime() >= deadlineNanos) {
                        CompletableFuture<Void> timeout = new CompletableFuture<>();
                        timeout.completeExceptionally(
                                failure(WebDriverError.TIMEOUT, "CEF did not finish loading the document"));
                        return timeout;
                    }
                    return awaitNotLoading(deadlineNanos);
                });
    }

    private JsonElement readEvaluationResult(JsonObject response) {
        if (response.has("exceptionDetails")) {
            JsonObject details = response.object("exceptionDetails");
            String text = stringValue(details, "text", "JavaScript execution failed");
            JsonObject exception =
                    details.has("exception") && details.get("exception").isObject()
                            ? details.object("exception")
                            : null;
            if (exception != null) text = stringValue(exception, "description", text);
            throw failure(WebDriverError.JAVASCRIPT_ERROR, text);
        }
        JsonObject remote = response.object("result");
        JsonElement value = remote.get("value");
        if (value != null) return value.deepCopy();
        if ("undefined".equals(stringValue(remote, "type", ""))) return JsonNull.INSTANCE;
        JsonElement description = remote.get("description");
        return description == null ? JsonNull.INSTANCE : cdp.jsonCodec().decode(description.string());
    }

    private static String stringValue(JsonObject object, String name, String fallback) {
        JsonElement value = object.get(name);
        return value != null && value.isPrimitive() ? value.string() : fallback;
    }

    private static WebDriverException failure(WebDriverError error, String message) {
        return new WebDriverException(error, message);
    }

    @Override
    @SuppressWarnings("FutureReturnValueIgnored")
    public void close() {
        if (!closed.compareAndSet(false, true)) return;
        cdp.close();
    }
}
