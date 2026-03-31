// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import javax.annotation.Nonnull;

/** Implement this interface to provide handler implementations. */
public interface CefClient {

    /** Return the handler for audio rendering events. */
    default long getAudioHandler() {
        return 0L;
    }

    /** Return the handler for commands. If no handler is provided the default implementation will be used. */
    default long getCommandHandler() {
        return 0L;
    }

    /** Return the handler for context menus. If no handler is provided the default implementation will be used. */
    default long getContextMenuHandler() {
        return 0L;
    }

    /** Return the handler for dialogs. If no handler is provided the default implementation will be used. */
    default long getDialogHandler() {
        return 0L;
    }

    /** Return the handler for browser display state events. */
    default long getDisplayHandler() {
        return 0L;
    }

    /** Return the handler for download events. If no handler is returned downloads will not be allowed. */
    default long getDownloadHandler() {
        return 0L;
    }

    /** Return the handler for drag events. */
    default long getDragHandler() {
        return 0L;
    }

    /** Return the handler for find result events. */
    default long getFindHandler() {
        return 0L;
    }

    /** Return the handler for focus events. */
    default long getFocusHandler() {
        return 0L;
    }

    /**
     * Return the handler for events related to CefFrame lifespan. This method will be called once during CefBrowser
     * creation and the result will be cached for performance reasons.
     */
    default long getFrameHandler() {
        return 0L;
    }

    /** Return the handler for permission requests. */
    default long getPermissionHandler() {
        return 0L;
    }

    default long getJsdialogHandler() {
        return 0L;
    }

    /** Return the handler for keyboard events. */
    default long getKeyboardHandler() {
        return 0L;
    }

    /** Return the handler for browser life span events. */
    default long getLifeSpanHandler() {
        return 0L;
    }

    /** Return the handler for browser load status events. */
    default long getLoadHandler() {
        return 0L;
    }

    /**
     * Return the handler for printing on Linux. If a print handler is not provided then printing will not be supported
     * on the Linux platform.
     */
    default long getPrintHandler() {
        return 0L;
    }

    /** Return the handler for off-screen rendering events. */
    default long getRenderHandler() {
        return 0L;
    }

    /** Return the handler for browser request events. */
    default long getRequestHandler() {
        return 0L;
    }

    /**
     * Called when a new message is received from a different process. Return true if the message was handled or false
     * otherwise. It is safe to keep a reference to |message| outside of this callback.
     */
    default boolean onProcessMessageReceived(
            long browser, long frame, @Nonnull CefProcessId sourceProcess, long message) {
        return false;
    }
}
