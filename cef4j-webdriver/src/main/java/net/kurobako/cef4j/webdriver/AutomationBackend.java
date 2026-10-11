package net.kurobako.cef4j.webdriver;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/** Browser operations consumed by the W3C HTTP layer, independent of CEF, CDP, or IPC. */
public interface AutomationBackend extends AutoCloseable {

    /** Actual capabilities of this browser instance, returned from the new-session command. */
    JsonObject capabilities();

    CompletableFuture<Void> navigate(String url);

    CompletableFuture<String> currentUrl();

    CompletableFuture<String> title();

    CompletableFuture<String> pageSource();

    CompletableFuture<JsonElement> executeScript(String script, JsonArray arguments);

    /** Complete PNG image bytes. */
    CompletableFuture<byte[]> screenshot();

    default CompletableFuture<String> findElement(String using, String value, Optional<String> parentElement) {
        return unsupported("find element");
    }

    default CompletableFuture<java.util.List<String>> findElements(
            String using, String value, Optional<String> parentElement) {
        return unsupported("find elements");
    }

    default CompletableFuture<String> activeElement() {
        return unsupported("active element");
    }

    default CompletableFuture<String> elementTagName(String elementId) {
        return unsupported("element tag name");
    }

    default CompletableFuture<String> elementText(String elementId) {
        return unsupported("element text");
    }

    default CompletableFuture<JsonElement> elementAttribute(String elementId, String name) {
        return unsupported("element attribute");
    }

    default CompletableFuture<JsonElement> elementProperty(String elementId, String name) {
        return unsupported("element property");
    }

    default CompletableFuture<String> elementCssValue(String elementId, String name) {
        return unsupported("element CSS value");
    }

    default CompletableFuture<JsonObject> elementRect(String elementId) {
        return unsupported("element rect");
    }

    default CompletableFuture<Boolean> elementDisplayed(String elementId) {
        return unsupported("element displayed state");
    }

    default CompletableFuture<Boolean> elementEnabled(String elementId) {
        return unsupported("element enabled state");
    }

    default CompletableFuture<Boolean> elementSelected(String elementId) {
        return unsupported("element selected state");
    }

    default CompletableFuture<Void> elementClick(String elementId) {
        return unsupported("element click");
    }

    default CompletableFuture<Void> elementClear(String elementId) {
        return unsupported("element clear");
    }

    default CompletableFuture<Void> elementSendKeys(String elementId, String text) {
        return unsupported("element send keys");
    }

    default CompletableFuture<Void> back() {
        return unsupported("back");
    }

    default CompletableFuture<Void> forward() {
        return unsupported("forward");
    }

    default CompletableFuture<Void> refresh() {
        return unsupported("refresh");
    }

    default CompletableFuture<JsonArray> cookies() {
        return unsupported("get cookies");
    }

    default CompletableFuture<JsonElement> cookie(String name) {
        return unsupported("get cookie");
    }

    default CompletableFuture<Void> addCookie(JsonObject cookie) {
        return unsupported("add cookie");
    }

    default CompletableFuture<Void> deleteCookie(String name) {
        return unsupported("delete cookie");
    }

    default CompletableFuture<Void> deleteAllCookies() {
        return unsupported("delete all cookies");
    }

    default void cancelPendingCommands(Throwable failure) {}

    static <T> CompletableFuture<T> unsupported(String command) {
        CompletableFuture<T> result = new CompletableFuture<>();
        result.completeExceptionally(
                new WebDriverException(WebDriverError.UNSUPPORTED_OPERATION, command + " is not supported"));
        return result;
    }

    @Override
    void close();
}
