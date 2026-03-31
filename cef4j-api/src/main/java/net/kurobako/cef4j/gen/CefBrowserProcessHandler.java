// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Class used to implement browser process callbacks. The methods of this class will be called on the browser process
 * main thread unless otherwise indicated.
 */
public interface CefBrowserProcessHandler {

    /**
     * Provides an opportunity to register custom preferences prior to global and request context initialization. If
     * |type| is CEF_PREFERENCES_TYPE_GLOBAL the registered preferences can be accessed via
     * CefPreferenceManager::GetGlobalPreferences after OnContextInitialized is called. Global preferences are
     * registered a single time at application startup. See related cef_settings_t.cache_path configuration. If |type|
     * is CEF_PREFERENCES_TYPE_REQUEST_CONTEXT the preferences can be accessed via the CefRequestContext after
     * CefRequestContextHandler::OnRequestContextInitialized is called. Request context preferences are registered each
     * time a new CefRequestContext is created. It is intended but not required that all request contexts have the same
     * registered preferences. See related cef_request_context_settings_t.cache_path configuration. Do not keep a
     * reference to the |registrar| object. This method is called on the browser process UI thread.
     */
    default void onRegisterCustomPreferences(@Nonnull CefPreferencesType type, long registrar) {}

    /** Called on the browser process UI thread immediately after the CEF context has been initialized. */
    default void onContextInitialized() {}

    /**
     * Called before a child process is launched. Will be called on the browser process UI thread when launching a
     * render process and on the browser process IO thread when launching a GPU process. Provides an opportunity to
     * modify the child process command line. Do not keep a reference to |command_line| outside of this method.
     */
    default void onBeforeChildProcessLaunch(long commandLine) {}

    /**
     * Implement this method to provide app-specific behavior when an already running app is relaunched with the same
     * CefSettings.root_cache_path value. For example, activate an existing app window or create a new app window.
     * |command_line| will be read-only. Do not keep a reference to |command_line| outside of this method. Return true
     * if the relaunch is handled or false for default relaunch behavior. Default behavior will create a new default
     * styled Chrome window. To avoid cache corruption only a single app instance is allowed to run for a given
     * CefSettings.root_cache_path value. On relaunch the app checks a process singleton lock and then forwards the new
     * launch arguments to the already running app process before exiting early. Client apps should therefore check the
     * CefInitialize() return value for early exit before proceeding. This method will be called on the browser process
     * UI thread.
     *
     * @param currentDirectory may be null
     */
    default boolean onAlreadyRunningAppRelaunch(long commandLine, @Nullable String currentDirectory) {
        return false;
    }

    /**
     * Called from any thread when work has been scheduled for the browser process main (UI) thread. This callback is
     * used in combination with cef_settings_t.external_message_pump and CefDoMessageLoopWork() in cases where the CEF
     * message loop must be integrated into an existing application message loop (see additional comments and warnings
     * on CefDoMessageLoopWork). This callback should schedule a CefDoMessageLoopWork() call to happen on the main (UI)
     * thread. |delay_ms| is the requested delay in milliseconds. If |delay_ms| is <= 0 then the call should happen
     * reasonably soon. If |delay_ms| is > 0 then the call should be scheduled to happen after the specified delay and
     * any currently pending scheduled call should be cancelled.
     */
    default void onScheduleMessagePumpWork(long delayMs) {}

    /**
     * Return the default client for use with a newly created browser window (CefBrowser object). If null is returned
     * the CefBrowser will be unmanaged (no callbacks will be executed for that CefBrowser) and application shutdown
     * will be blocked until the browser window is closed manually. This method is currently only used with Chrome style
     * when creating new browser windows via Chrome UI.
     */
    default long getDefaultClient() {
        return 0L;
    }
}
