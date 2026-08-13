package net.kurobako.cef4j.webdriver;

import java.util.concurrent.CompletableFuture;
import javax.annotation.Nonnull;
import net.kurobako.cef4j.cdp.CdpTransport;

/** Browser lifecycle and Chrome DevTools channel consumed by the shared WebDriver implementation. */
public interface CdpBrowser extends CdpTransport {

    /** Navigates the main frame using the hosting CEF API. */
    @Nonnull
    CompletableFuture<Void> loadUrl(@Nonnull String url);

    @Nonnull
    CompletableFuture<Boolean> canGoBack();

    @Nonnull
    CompletableFuture<Void> goBack();

    @Nonnull
    CompletableFuture<Boolean> canGoForward();

    @Nonnull
    CompletableFuture<Void> goForward();

    /** Returns CEF's current main-frame loading state. */
    @Nonnull
    CompletableFuture<Boolean> loading();

    @Override
    void close();
}
