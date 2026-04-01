package net.kurobako.cef4j;

import net.kurobako.cef4j.gen.*;

/**
 * An off-screen rendered CEF browser.
 *
 * <p>This is the primary entry point for creating and interacting with a browser in OSR mode. There is no AWT/Swing
 * dependency - input events are dispatched as raw CEF structs.
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

        var windowInfo = new CefMutableWindowInfo();
        windowInfo.windowlessRenderingEnabled = 1;

        var settings = new CefMutableBrowserSettings();
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
     * Returns whether the underlying CEF browser object is valid.
     *
     * @return true if the browser is valid
     */
    public boolean isValid() {
        return browser != null;
    }

    /**
     * Get the browser identifier.
     *
     * @return the browser's unique identifier, or -1 if not valid
     */
    public int getIdentifier() {
        return browser != null ? browser.getIdentifier() : -1;
    }

    // --- Navigation ---

    /**
     * Load the given URL in the main frame.
     *
     * @param url the URL to load
     */
    public void loadURL(String url) {
        if (browser == null) return;
        log.debug("Loading URL: {}", url);
        browser.getMainFrame().ifPresent(f -> {
            f.loadUrl(url);
            f.close();
        });
    }

    /**
     * Execute a string of JavaScript code in the main frame.
     *
     * @param code the JavaScript code to execute
     * @param url the URL to use for error reporting (may be empty)
     * @param line the base line number for error reporting
     */
    public void executeJavaScript(String code, String url, int line) {
        if (browser == null) return;
        browser.getMainFrame().ifPresent(f -> {
            f.executeJavaScript(code, url, line);
            f.close();
        });
    }

    public boolean canGoBack() {
        return browser != null && browser.canGoBack();
    }

    public void goBack() {
        if (browser != null) browser.goBack();
    }

    public boolean canGoForward() {
        return browser != null && browser.canGoForward();
    }

    public void goForward() {
        if (browser != null) browser.goForward();
    }

    public void reload() {
        if (browser != null) browser.reload();
    }

    public void stopLoad() {
        if (browser != null) browser.stopLoad();
    }

    // --- OSR notifications ---

    /** Notify the browser that it was resized. The render handler's getViewRect will be queried for the new size. */
    public void wasResized() {
        if (host != null) host.wasResized();
    }

    /** Invalidate the browser's paint buffer. CEF will call onPaint. */
    public void invalidate() {
        if (host != null) host.invalidate(CefPaintElementType.of(CefPaintElementType.Kind.VIEW));
    }

    /**
     * Send a mouse click event.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @param modifiers CEF event modifier flags
     * @param buttonType 0=left, 1=middle, 2=right (maps to cef_mouse_button_type_t)
     * @param mouseUp true for mouse-up, false for mouse-down
     * @param clickCount number of clicks (1 for single, 2 for double)
     */
    public void sendMouseClickEvent(int x, int y, int modifiers, int buttonType, boolean mouseUp, int clickCount) {
        if (host != null) {
            host.sendMouseClickEvent(
                    new CefMouseEvent(x, y, modifiers), CefMouseButtonType.of(buttonType), mouseUp, clickCount);
        }
    }

    /**
     * Send a mouse move event.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @param modifiers CEF event modifier flags
     * @param mouseLeave true if the mouse is leaving the browser area
     */
    public void sendMouseMoveEvent(int x, int y, int modifiers, boolean mouseLeave) {
        if (host != null) {
            host.sendMouseMoveEvent(new CefMouseEvent(x, y, modifiers), mouseLeave);
        }
    }

    /**
     * Send a mouse wheel event.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @param modifiers CEF event modifier flags
     * @param deltaX horizontal scroll delta
     * @param deltaY vertical scroll delta
     */
    public void sendMouseWheelEvent(int x, int y, int modifiers, int deltaX, int deltaY) {
        if (host != null) {
            host.sendMouseWheelEvent(new CefMouseEvent(x, y, modifiers), deltaX, deltaY);
        }
    }

    /**
     * Send a key event.
     *
     * @param type 0=RAWKEYDOWN, 1=KEYDOWN, 2=KEYUP, 3=CHAR
     * @param modifiers CEF event modifier flags
     * @param windowsKeyCode the Windows virtual key code
     * @param nativeKeyCode the native (platform) key code
     * @param character the character generated by the keystroke
     * @param unmodifiedCharacter the unmodified character
     * @param isSystemKey true if this is a system key event
     */
    public void sendKeyEvent(
            int type,
            int modifiers,
            int windowsKeyCode,
            int nativeKeyCode,
            char character,
            char unmodifiedCharacter,
            boolean isSystemKey) {
        if (host != null) {
            host.sendKeyEvent(new CefKeyEvent(
                    CefKeyEventType.of(type),
                    modifiers,
                    windowsKeyCode,
                    nativeKeyCode,
                    isSystemKey ? 1 : 0,
                    character,
                    unmodifiedCharacter,
                    0));
        }
    }

    // --- Lifecycle ---

    /**
     * Close the browser. If forceClose is true, the close is immediate. Otherwise, JavaScript onbeforeunload will be
     * respected.
     *
     * @param forceClose true to skip the unload handler
     */
    public void close(boolean forceClose) {
        log.info("Closing browser (force={})", forceClose);
        if (host != null) {
            host.closeBrowser(forceClose);
        }
        if (host != null) {
            host.close();
            host = null;
        }
        if (browser != null) {
            browser.close();
            browser = null;
        }
        log.debug("Browser closed");
    }

    /**
     * Set focus on the browser.
     *
     * @param focus true to set focus, false to remove
     */
    public void setFocus(boolean focus) {
        if (host != null) host.setFocus(focus);
    }
}
