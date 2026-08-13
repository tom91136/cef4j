package net.kurobako.cef4j.webdriver;

import java.util.concurrent.CompletableFuture;
import javax.annotation.Nonnull;

/** Creates one isolated browser backend for a successfully matched new-session capability set. */
@FunctionalInterface
public interface AutomationBackendFactory {

    @Nonnull
    CompletableFuture<? extends AutomationBackend> create(@Nonnull JsonObject requestedCapabilities);
}
