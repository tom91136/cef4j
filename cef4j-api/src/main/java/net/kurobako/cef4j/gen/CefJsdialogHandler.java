// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Implement this interface to handle events related to JavaScript dialogs. The methods of this class will be called on
 * the UI thread.
 */
public interface CefJsdialogHandler {

    default int onJsdialog(
            long browser,
            @Nonnull String originUrl,
            @Nonnull CefJsdialogType dialogType,
            @Nonnull String messageText,
            @Nonnull String defaultPromptText,
            long callback,
            int[] suppressMessage) {
        return 0;
    }

    /**
     * Called to run a dialog asking the user if they want to leave a page. Return false to use the default dialog
     * implementation. Return true if the application will use a custom dialog or if the callback has been executed
     * immediately. Custom dialogs may be either modal or modeless. If a custom dialog is used the application must
     * execute |callback| once the custom dialog is dismissed.
     *
     * @param messageText may be null
     */
    default boolean onBeforeUnloadDialog(long browser, @Nullable String messageText, boolean isReload, long callback) {
        return false;
    }

    /**
     * Called to cancel any pending dialogs and reset any saved dialog state. Will be called due to events like page
     * navigation irregardless of whether any dialogs are currently pending.
     */
    default void onResetDialogState(long browser) {}

    /** Called when the dialog is closed. */
    default void onDialogClosed(long browser) {}
}
