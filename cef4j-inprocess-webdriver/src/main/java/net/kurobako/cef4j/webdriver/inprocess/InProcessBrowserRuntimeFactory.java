package net.kurobako.cef4j.webdriver.inprocess;

import java.util.concurrent.CompletableFuture;

/** Creates an owned in-process browser without transferring ownership of the global {@code Cef} lifecycle. */
@FunctionalInterface
public interface InProcessBrowserRuntimeFactory {
    CompletableFuture<? extends InProcessBrowserRuntime> create();
}
