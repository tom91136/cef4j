package net.kurobako.cef4j;

import net.kurobako.cef4j.gen.*;

/**
 * Hub for handler registration and browser creation.
 *
 * <p>Each CefClient can manage multiple browsers. Register handlers before or after browser creation - CEF queries the
 * client for handlers on each callback, so late registration works.
 *
 * <p>Handler registration replaces any previously registered handler of the same type (one handler per type per
 * client).
 *
 * <p>When a handler is registered, a native wrapper struct is created immediately. The getter methods return the native
 * pointer (as long) so the generated client.cpp trampoline can pass it directly to CEF.
 */
public class CefClient {

    // Java handler references - prevent GC while native wrappers hold them
    private volatile CefLifeSpanHandler lifeSpanHandler;
    private volatile CefLoadHandler loadHandler;
    private volatile CefDisplayHandler displayHandler;
    private volatile CefRenderHandler renderHandler;
    private volatile CefRequestHandler requestHandler;
    private volatile CefContextMenuHandler contextMenuHandler;
    private volatile CefDialogHandler dialogHandler;
    private volatile CefDownloadHandler downloadHandler;
    private volatile CefDragHandler dragHandler;
    private volatile CefFindHandler findHandler;
    private volatile CefFocusHandler focusHandler;
    private volatile CefFrameHandler frameHandler;
    private volatile CefKeyboardHandler keyboardHandler;
    private volatile CefJsdialogHandler jsDialogHandler;
    private volatile CefPermissionHandler permissionHandler;
    private volatile CefPrintHandler printHandler;
    private volatile CefAudioHandler audioHandler;
    private volatile CefCommandHandler commandHandler;

    // Native handler wrapper pointers (created eagerly on addXxxHandler)
    private volatile long lifeSpanHandlerPtr;
    private volatile long loadHandlerPtr;
    private volatile long displayHandlerPtr;
    private volatile long renderHandlerPtr;
    private volatile long requestHandlerPtr;
    private volatile long contextMenuHandlerPtr;
    private volatile long dialogHandlerPtr;
    private volatile long downloadHandlerPtr;
    private volatile long dragHandlerPtr;
    private volatile long findHandlerPtr;
    private volatile long focusHandlerPtr;
    private volatile long frameHandlerPtr;
    private volatile long keyboardHandlerPtr;
    private volatile long jsDialogHandlerPtr;
    private volatile long permissionHandlerPtr;
    private volatile long printHandlerPtr;
    private volatile long audioHandlerPtr;
    private volatile long commandHandlerPtr;

    CefClient() {}

    // --- Handler registration ---

    public CefClient addLifeSpanHandler(CefLifeSpanHandler handler) {
        this.lifeSpanHandler = handler;
        this.lifeSpanHandlerPtr = handler != null ? N_CreateLifeSpanHandler(handler) : 0;
        return this;
    }

    public CefClient addLoadHandler(CefLoadHandler handler) {
        this.loadHandler = handler;
        this.loadHandlerPtr = handler != null ? N_CreateLoadHandler(handler) : 0;
        return this;
    }

    public CefClient addDisplayHandler(CefDisplayHandler handler) {
        this.displayHandler = handler;
        this.displayHandlerPtr = handler != null ? N_CreateDisplayHandler(handler) : 0;
        return this;
    }

    public CefClient addRenderHandler(CefRenderHandler handler) {
        this.renderHandler = handler;
        this.renderHandlerPtr = handler != null ? N_CreateRenderHandler(handler) : 0;
        return this;
    }

    public CefClient addRequestHandler(CefRequestHandler handler) {
        this.requestHandler = handler;
        this.requestHandlerPtr = handler != null ? N_CreateRequestHandler(handler) : 0;
        return this;
    }

    public CefClient addContextMenuHandler(CefContextMenuHandler handler) {
        this.contextMenuHandler = handler;
        this.contextMenuHandlerPtr = handler != null ? N_CreateContextMenuHandler(handler) : 0;
        return this;
    }

    public CefClient addDialogHandler(CefDialogHandler handler) {
        this.dialogHandler = handler;
        this.dialogHandlerPtr = handler != null ? N_CreateDialogHandler(handler) : 0;
        return this;
    }

    public CefClient addDownloadHandler(CefDownloadHandler handler) {
        this.downloadHandler = handler;
        this.downloadHandlerPtr = handler != null ? N_CreateDownloadHandler(handler) : 0;
        return this;
    }

    public CefClient addDragHandler(CefDragHandler handler) {
        this.dragHandler = handler;
        this.dragHandlerPtr = handler != null ? N_CreateDragHandler(handler) : 0;
        return this;
    }

    public CefClient addFindHandler(CefFindHandler handler) {
        this.findHandler = handler;
        this.findHandlerPtr = handler != null ? N_CreateFindHandler(handler) : 0;
        return this;
    }

    public CefClient addFocusHandler(CefFocusHandler handler) {
        this.focusHandler = handler;
        this.focusHandlerPtr = handler != null ? N_CreateFocusHandler(handler) : 0;
        return this;
    }

    public CefClient addFrameHandler(CefFrameHandler handler) {
        this.frameHandler = handler;
        this.frameHandlerPtr = handler != null ? N_CreateFrameHandler(handler) : 0;
        return this;
    }

    public CefClient addKeyboardHandler(CefKeyboardHandler handler) {
        this.keyboardHandler = handler;
        this.keyboardHandlerPtr = handler != null ? N_CreateKeyboardHandler(handler) : 0;
        return this;
    }

    public CefClient addJsDialogHandler(CefJsdialogHandler handler) {
        this.jsDialogHandler = handler;
        this.jsDialogHandlerPtr = handler != null ? N_CreateJsdialogHandler(handler) : 0;
        return this;
    }

    public CefClient addPermissionHandler(CefPermissionHandler handler) {
        this.permissionHandler = handler;
        this.permissionHandlerPtr = handler != null ? N_CreatePermissionHandler(handler) : 0;
        return this;
    }

    public CefClient addPrintHandler(CefPrintHandler handler) {
        this.printHandler = handler;
        this.printHandlerPtr = handler != null ? N_CreatePrintHandler(handler) : 0;
        return this;
    }

    public CefClient addAudioHandler(CefAudioHandler handler) {
        this.audioHandler = handler;
        this.audioHandlerPtr = handler != null ? N_CreateAudioHandler(handler) : 0;
        return this;
    }

    public CefClient addCommandHandler(CefCommandHandler handler) {
        this.commandHandler = handler;
        this.commandHandlerPtr = handler != null ? N_CreateCommandHandler(handler) : 0;
        return this;
    }

    // --- Handler getters (called from native client.cpp - return native pointers) ---

    public long getLifeSpanHandler() {
        return lifeSpanHandlerPtr;
    }

    public long getLoadHandler() {
        return loadHandlerPtr;
    }

    public long getDisplayHandler() {
        return displayHandlerPtr;
    }

    public long getRenderHandler() {
        return renderHandlerPtr;
    }

    public long getRequestHandler() {
        return requestHandlerPtr;
    }

    public long getContextMenuHandler() {
        return contextMenuHandlerPtr;
    }

    public long getDialogHandler() {
        return dialogHandlerPtr;
    }

    public long getDownloadHandler() {
        return downloadHandlerPtr;
    }

    public long getDragHandler() {
        return dragHandlerPtr;
    }

    public long getFindHandler() {
        return findHandlerPtr;
    }

    public long getFocusHandler() {
        return focusHandlerPtr;
    }

    public long getFrameHandler() {
        return frameHandlerPtr;
    }

    public long getKeyboardHandler() {
        return keyboardHandlerPtr;
    }

    public long getJsdialogHandler() {
        return jsDialogHandlerPtr;
    }

    public long getPermissionHandler() {
        return permissionHandlerPtr;
    }

    public long getPrintHandler() {
        return printHandlerPtr;
    }

    public long getAudioHandler() {
        return audioHandlerPtr;
    }

    public long getCommandHandler() {
        return commandHandlerPtr;
    }

    // --- Browser creation ---

    /**
     * Create an offscreen browser that navigates to the given URL.
     *
     * @param url the initial URL to load
     * @return a new CefBrowserOsr instance
     */
    public CefBrowserOsr createBrowser(String url) {
        return new CefBrowserOsr(this, url, 0);
    }

    /**
     * Create an offscreen browser that navigates to the given URL with a specified frame rate.
     *
     * @param url the initial URL to load
     * @param frameRate the target frame rate for OSR rendering (0 for default 60fps)
     * @return a new CefBrowserOsr instance
     */
    public CefBrowserOsr createBrowser(String url, int frameRate) {
        return new CefBrowserOsr(this, url, frameRate);
    }

    // --- Native factory methods (implemented in cef_client_factories.cpp) ---

    private static native long N_CreateAudioHandler(CefAudioHandler handler);

    private static native long N_CreateCommandHandler(CefCommandHandler handler);

    private static native long N_CreateContextMenuHandler(CefContextMenuHandler handler);

    private static native long N_CreateDialogHandler(CefDialogHandler handler);

    private static native long N_CreateDisplayHandler(CefDisplayHandler handler);

    private static native long N_CreateDownloadHandler(CefDownloadHandler handler);

    private static native long N_CreateDragHandler(CefDragHandler handler);

    private static native long N_CreateFindHandler(CefFindHandler handler);

    private static native long N_CreateFocusHandler(CefFocusHandler handler);

    private static native long N_CreateFrameHandler(CefFrameHandler handler);

    private static native long N_CreateJsdialogHandler(CefJsdialogHandler handler);

    private static native long N_CreateKeyboardHandler(CefKeyboardHandler handler);

    private static native long N_CreateLifeSpanHandler(CefLifeSpanHandler handler);

    private static native long N_CreateLoadHandler(CefLoadHandler handler);

    private static native long N_CreatePermissionHandler(CefPermissionHandler handler);

    private static native long N_CreatePrintHandler(CefPrintHandler handler);

    private static native long N_CreateRenderHandler(CefRenderHandler handler);

    private static native long N_CreateRequestHandler(CefRequestHandler handler);
}
