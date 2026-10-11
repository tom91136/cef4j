package net.kurobako.cef4j.webdriver;

import java.util.concurrent.CompletableFuture;

/** Creates one isolated browser backend for a successfully matched new-session capability set. */
@FunctionalInterface
public interface AutomationBackendFactory {

    CompletableFuture<? extends AutomationBackend> create(JsonObject requestedCapabilities);
}
