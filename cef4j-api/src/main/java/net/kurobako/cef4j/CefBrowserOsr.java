package net.kurobako.cef4j;

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
    private volatile long browserPtr;
    private volatile long hostPtr;

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
        browserPtr = N_CreateBrowserSync(client, initialUrl, frameRate);
        log.info("Browser created (ptr=0x{})", Long.toHexString(browserPtr));
    }

    /**
     * Returns whether the underlying CEF browser object is valid.
     *
     * @return true if the browser is valid
     */
    public boolean isValid() {
        return browserPtr != 0;
    }

    /**
     * Get the browser identifier.
     *
     * @return the browser's unique identifier, or -1 if not valid
     */
    public int getIdentifier() {
        if (browserPtr == 0) return -1;
        return N_GetIdentifier(browserPtr);
    }

    // --- Navigation ---

    /**
     * Load the given URL in the main frame.
     *
     * @param url the URL to load
     */
    public void loadURL(String url) {
        if (browserPtr == 0) return;
        log.debug("Loading URL: {}", url);
        N_LoadURL(browserPtr, url);
    }

    /**
     * Execute a string of JavaScript code in the main frame.
     *
     * @param code the JavaScript code to execute
     * @param url the URL to use for error reporting (may be empty)
     * @param line the base line number for error reporting
     */
    public void executeJavaScript(String code, String url, int line) {
        if (browserPtr == 0) return;
        N_ExecuteJavaScript(browserPtr, code, url, line);
    }

    public boolean canGoBack() {
        return browserPtr != 0 && N_CanGoBack(browserPtr) != 0;
    }

    public void goBack() {
        if (browserPtr == 0) return;
        N_GoBack(browserPtr);
    }

    public boolean canGoForward() {
        return browserPtr != 0 && N_CanGoForward(browserPtr) != 0;
    }

    public void goForward() {
        if (browserPtr == 0) return;
        N_GoForward(browserPtr);
    }

    public void reload() {
        if (browserPtr == 0) return;
        N_Reload(browserPtr);
    }

    public void stopLoad() {
        if (browserPtr == 0) return;
        N_StopLoad(browserPtr);
    }

    // --- Host access ---

    /**
     * Get the host pointer for advanced operations (input dispatch, etc.). The host pointer is cached after the first
     * retrieval.
     *
     * @return the native host pointer, or 0 if the browser is not valid
     */
    public long getHostPtr() {
        if (hostPtr == 0 && browserPtr != 0) {
            hostPtr = N_GetHost(browserPtr);
        }
        return hostPtr;
    }

    // --- OSR notifications ---

    /** Notify the browser that it was resized. The render handler's getViewRect will be queried for the new size. */
    public void wasResized() {
        long host = getHostPtr();
        if (host != 0) N_WasResized(host);
    }

    /** Invalidate the browser's paint buffer. CEF will call onPaint. */
    public void invalidate() {
        long host = getHostPtr();
        if (host != 0) N_Invalidate(host);
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
        long host = getHostPtr();
        if (host != 0) {
            N_SendMouseClickEvent(host, x, y, modifiers, buttonType, mouseUp ? 1 : 0, clickCount);
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
        long host = getHostPtr();
        if (host != 0) {
            N_SendMouseMoveEvent(host, x, y, modifiers, mouseLeave ? 1 : 0);
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
        long host = getHostPtr();
        if (host != 0) {
            N_SendMouseWheelEvent(host, x, y, modifiers, deltaX, deltaY);
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
        long host = getHostPtr();
        if (host != 0) {
            N_SendKeyEvent(
                    host,
                    type,
                    modifiers,
                    windowsKeyCode,
                    nativeKeyCode,
                    character,
                    unmodifiedCharacter,
                    isSystemKey ? 1 : 0);
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
        long host = getHostPtr();
        if (host != 0) {
            N_CloseBrowser(host, forceClose ? 1 : 0);
        }
        browserPtr = 0;
        hostPtr = 0;
        log.debug("Browser closed");
    }

    /**
     * Set focus on the browser.
     *
     * @param focus true to set focus, false to remove
     */
    public void setFocus(boolean focus) {
        long host = getHostPtr();
        if (host != 0) N_SetFocus(host, focus ? 1 : 0);
    }

    // --- Native methods ---
    // These are implemented in the hand-written cef_browser_osr.cpp runtime file.

    private static native long N_CreateBrowserSync(CefClient client, String url, int frameRate);

    private static native int N_GetIdentifier(long browser);

    private static native void N_LoadURL(long browser, String url);

    private static native void N_ExecuteJavaScript(long browser, String code, String url, int line);

    private static native int N_CanGoBack(long browser);

    private static native void N_GoBack(long browser);

    private static native int N_CanGoForward(long browser);

    private static native void N_GoForward(long browser);

    private static native void N_Reload(long browser);

    private static native void N_StopLoad(long browser);

    private static native long N_GetHost(long browser);

    private static native void N_WasResized(long host);

    private static native void N_Invalidate(long host);

    private static native void N_SendMouseClickEvent(
            long host, int x, int y, int modifiers, int buttonType, int mouseUp, int clickCount);

    private static native void N_SendMouseMoveEvent(long host, int x, int y, int modifiers, int mouseLeave);

    private static native void N_SendMouseWheelEvent(long host, int x, int y, int modifiers, int deltaX, int deltaY);

    private static native void N_SendKeyEvent(
            long host,
            int type,
            int modifiers,
            int windowsKeyCode,
            int nativeKeyCode,
            char character,
            char unmodifiedCharacter,
            int isSystemKey);

    private static native void N_CloseBrowser(long host, int forceClose);

    private static native void N_SetFocus(long host, int focus);
}
