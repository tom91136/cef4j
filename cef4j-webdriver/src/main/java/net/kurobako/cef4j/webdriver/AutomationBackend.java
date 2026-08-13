package net.kurobako.cef4j.webdriver;

import java.util.concurrent.CompletableFuture;
import javax.annotation.Nonnull;

/** Browser operations consumed by the W3C HTTP layer, independent of CEF, CDP, or IPC. */
public interface AutomationBackend extends AutoCloseable {

    /** Actual capabilities of this browser instance, returned from the new-session command. */
    @Nonnull
    JsonObject capabilities();

    @Nonnull
    CompletableFuture<Void> navigate(@Nonnull String url);

    @Nonnull
    CompletableFuture<String> currentUrl();

    @Nonnull
    CompletableFuture<String> title();

    @Nonnull
    CompletableFuture<String> pageSource();

    @Nonnull
    CompletableFuture<JsonElement> executeScript(@Nonnull String script, @Nonnull JsonArray arguments);

    /** Complete PNG image bytes. */
    @Nonnull
    CompletableFuture<byte[]> screenshot();

    @Nonnull
    default CompletableFuture<String> findElement(
            @Nonnull String using, @Nonnull String value, @javax.annotation.Nullable String parentElement) {
        return unsupported("find element");
    }

    @Nonnull
    default CompletableFuture<java.util.List<String>> findElements(
            @Nonnull String using, @Nonnull String value, @javax.annotation.Nullable String parentElement) {
        return unsupported("find elements");
    }

    @Nonnull
    default CompletableFuture<String> activeElement() {
        return unsupported("active element");
    }

    @Nonnull
    default CompletableFuture<String> elementTagName(@Nonnull String elementId) {
        return unsupported("element tag name");
    }

    @Nonnull
    default CompletableFuture<String> elementText(@Nonnull String elementId) {
        return unsupported("element text");
    }

    @Nonnull
    default CompletableFuture<JsonElement> elementAttribute(@Nonnull String elementId, @Nonnull String name) {
        return unsupported("element attribute");
    }

    @Nonnull
    default CompletableFuture<JsonElement> elementProperty(@Nonnull String elementId, @Nonnull String name) {
        return unsupported("element property");
    }

    @Nonnull
    default CompletableFuture<String> elementCssValue(@Nonnull String elementId, @Nonnull String name) {
        return unsupported("element CSS value");
    }

    @Nonnull
    default CompletableFuture<JsonObject> elementRect(@Nonnull String elementId) {
        return unsupported("element rect");
    }

    @Nonnull
    default CompletableFuture<Boolean> elementDisplayed(@Nonnull String elementId) {
        return unsupported("element displayed state");
    }

    @Nonnull
    default CompletableFuture<Boolean> elementEnabled(@Nonnull String elementId) {
        return unsupported("element enabled state");
    }

    @Nonnull
    default CompletableFuture<Boolean> elementSelected(@Nonnull String elementId) {
        return unsupported("element selected state");
    }

    @Nonnull
    default CompletableFuture<Void> elementClick(@Nonnull String elementId) {
        return unsupported("element click");
    }

    @Nonnull
    default CompletableFuture<Void> elementClear(@Nonnull String elementId) {
        return unsupported("element clear");
    }

    @Nonnull
    default CompletableFuture<Void> elementSendKeys(@Nonnull String elementId, @Nonnull String text) {
        return unsupported("element send keys");
    }

    @Nonnull
    default CompletableFuture<Void> back() {
        return unsupported("back");
    }

    @Nonnull
    default CompletableFuture<Void> forward() {
        return unsupported("forward");
    }

    @Nonnull
    default CompletableFuture<Void> refresh() {
        return unsupported("refresh");
    }

    @Nonnull
    default CompletableFuture<JsonArray> cookies() {
        return unsupported("get cookies");
    }

    @Nonnull
    default CompletableFuture<JsonElement> cookie(@Nonnull String name) {
        return unsupported("get cookie");
    }

    @Nonnull
    default CompletableFuture<Void> addCookie(@Nonnull JsonObject cookie) {
        return unsupported("add cookie");
    }

    @Nonnull
    default CompletableFuture<Void> deleteCookie(@Nonnull String name) {
        return unsupported("delete cookie");
    }

    @Nonnull
    default CompletableFuture<Void> deleteAllCookies() {
        return unsupported("delete all cookies");
    }

    @Nonnull
    static <T> CompletableFuture<T> unsupported(@Nonnull String command) {
        CompletableFuture<T> result = new CompletableFuture<>();
        result.completeExceptionally(
                new WebDriverException(WebDriverError.UNSUPPORTED_OPERATION, command + " is not supported"));
        return result;
    }

    @Override
    void close();
}
