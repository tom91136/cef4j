package net.kurobako.cef4j.remote;

import java.util.concurrent.CompletableFuture;
import javax.annotation.Nonnull;

/** Creates an owned Remote CEF browser runtime independently of any UI or automation frontend. */
@FunctionalInterface
public interface RemoteBrowserRuntimeFactory {
    @Nonnull
    CompletableFuture<? extends RemoteBrowserRuntime> create();
}
