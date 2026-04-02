package net.kurobako.cef4j;

import javax.annotation.Nullable;
import net.kurobako.cef4j.gen.*;

/**
 * An off-screen rendered CEF browser.
 *
 * <p>Manages the lifecycle of an OSR browser: creation ({@link #createImmediately()}), frame-safe navigation
 * ({@link #loadURL(String)}, {@link #executeJavaScript(String, String, int)}), and cleanup ({@link #close(boolean)}).
 *
 * <p>For direct access to the full CEF browser and host APIs (input events, navigation, focus, etc.), use
 * {@link #getBrowser()} and {@link #getHost()}.
 *
 * <p>The browser is not valid until {@link #createImmediately()} is called and CEF fires onAfterCreated.
 */
public class CefBrowserOsr {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(CefBrowserOsr.class);

    private final CefClient client;
    private final String initialUrl;
    private final int frameRate;
    private volatile CefBrowser browser;
    private volatile CefBrowserHost host;

    CefBrowserOsr(CefClient client, String url, int frameRate) {
        this.client = client;
        this.initialUrl = url;
        this.frameRate = frameRate;
        log.debug("Browser created for URL: {} (frameRate={})", url, frameRate);
    }

    /**
     * Request synchronous browser creation. After this call returns, the browser may not yet be valid - wait for
     * onAfterCreated via the life span handler.
     */
    public void createImmediately() {
        log.debug("Creating browser synchronously for: {}", initialUrl);

        var windowInfo = new CefWindowInfo.Mutable();
        windowInfo.windowlessRenderingEnabled = 1;

        var settings = new CefBrowserSettings.Mutable();
        settings.windowlessFrameRate = frameRate > 0 ? frameRate : 60;

        browser = CefBrowserHost.createBrowserSync(
                        windowInfo.toImmutable(), client, initialUrl, settings.toImmutable(), null, null)
                .orElse(null);

        if (browser != null) {
            host = browser.getHost().orElse(null);
            log.info("Browser created");
        } else {
            log.error("createBrowserSync returned null");
        }
    }

    /**
     * Returns the underlying {@link CefBrowser}, or {@code null} if the browser has not been created or has been
     * closed.
     */
    @Nullable
    public CefBrowser getBrowser() {
        return browser;
    }

    /**
     * Returns the underlying {@link CefBrowserHost}, or {@code null} if the browser has not been created or has been
     * closed.
     */
    @Nullable
    public CefBrowserHost getHost() {
        return host;
    }

    /**
     * Load the given URL in the main frame. Handles frame lifecycle (close) automatically.
     *
     * @param url the URL to load
     */
    public void loadURL(String url) {
        if (browser == null) return;
        log.debug("Loading URL: {}", url);
        browser.getMainFrame().ifPresent(f -> {
            try {
                f.loadUrl(url);
            } finally {
                f.close();
            }
        });
    }

    /**
     * Execute a string of JavaScript code in the main frame. Handles frame lifecycle (close) automatically.
     *
     * @param code the JavaScript code to execute
     * @param url the URL to use for error reporting (may be empty)
     * @param line the base line number for error reporting
     */
    public void executeJavaScript(String code, String url, int line) {
        if (browser == null) return;
        browser.getMainFrame().ifPresent(f -> {
            try {
                f.executeJavaScript(code, url, line);
            } finally {
                f.close();
            }
        });
    }

    /**
     * Close the browser. If forceClose is true, the close is immediate. Otherwise, JavaScript onbeforeunload will be
     * respected.
     *
     * @param forceClose true to skip the unload handler
     */
    public void close(boolean forceClose) {
        log.info("Closing browser (force={})", forceClose);
        CefBrowserHost h = host;
        CefBrowser b = browser;
        host = null;
        browser = null;
        try {
            if (h != null) {
                h.closeBrowser(forceClose);
                h.close();
            }
        } finally {
            if (b != null) {
                b.close();
            }
        }
        log.debug("Browser closed");
    }
}
