// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Class used to implement browser process callbacks. The methods of this class will be called on the browser process main thread unless otherwise indicated.
 * <p>Definition generated from cef_browser_process_handler_capi.h
 * <pre>typedef struct _cef_browser_process_handler_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_browser_process_handler_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__browser__process__handler_8h.html">cef_browser_process_handler.h:48</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused", "NullableForbidden"})
public interface CefBrowserProcessHandler extends CefClientHandler {

    /**
     * Provides an opportunity to register custom preferences prior to global and request context initialization.
     * <p>
     * If {@code type} is {@link net.kurobako.cef4j.gen.CefPreferencesType.Kind#GLOBAL} the registered preferences can be accessed via net.kurobako.cef4j.gen.CefPreferenceManager.getGlobalPreferences() after OnContextInitialized is called. Global preferences are registered a single time at application startup. See related cef_settings_t.cache_path configuration.
     * <p>
     * If {@code type} is {@link net.kurobako.cef4j.gen.CefPreferencesType.Kind#REQUEST_CONTEXT} the preferences can be accessed via the CefRequestContext after {@link net.kurobako.cef4j.gen.CefRequestContextHandler#onRequestContextInitialized(CefRequestContext)} is called. Request context preferences are registered each time a new CefRequestContext is created. It is intended but not required that all request contexts have the same registered preferences. See related cef_request_context_settings_t.cache_path configuration.
     * <p>
     * Do not keep a reference to the {@code registrar} object. This method is called on the browser process UI thread.
     * <p>Definition generated from cef_browser_process_handler_capi.h
     * <pre>void (CEF_CALLBACK* on_register_custom_preferences)(struct _cef_browser_process_handler_t* self, cef_preferences_type_t type, struct _cef_preference_registrar_t* registrar);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__browser__process__handler_8h.html">cef_browser_process_handler.h:56</a>
     */
    default void onRegisterCustomPreferences(@Nonnull CefPreferencesType type, @Nullable CefPreferenceRegistrar registrar) {
    }

    /**
     * Called on the browser process UI thread immediately after the CEF context has been initialized.
     * <p>Definition generated from cef_browser_process_handler_capi.h
     * <pre>void (CEF_CALLBACK* on_context_initialized)(struct _cef_browser_process_handler_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__browser__process__handler_8h.html">cef_browser_process_handler.h:82</a>
     */
    default void onContextInitialized() {
    }

    /**
     * Called before a child process is launched. Will be called on the browser process UI thread when launching a render process and on the browser process IO thread when launching a GPU process. Provides an opportunity to modify the child process command line. Do not keep a reference to {@code command_line} outside of this method.
     * <p>Definition generated from cef_browser_process_handler_capi.h
     * <pre>void (CEF_CALLBACK* on_before_child_process_launch)(struct _cef_browser_process_handler_t* self, struct _cef_command_line_t* command_line);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__browser__process__handler_8h.html">cef_browser_process_handler.h:89</a>
     */
    default void onBeforeChildProcessLaunch(@Nullable CefCommandLine commandLine) {
    }

    /**
     * Implement this method to provide app-specific behavior when an already running app is relaunched with the same CefSettings.root_cache_path value. For example, activate an existing app window or create a new app window. {@code command_line} will be read-only. Do not keep a reference to {@code command_line} outside of this method. Return {@code true} if the relaunch is handled or {@code false} for default relaunch behavior. Default behavior will create a new default styled Chrome window.
     * <p>
     * To avoid cache corruption only a single app instance is allowed to run for a given CefSettings.root_cache_path value. On relaunch the app checks a process singleton lock and then forwards the new launch arguments to the already running app process before exiting early. Client apps should therefore check the CefInitialize() return value for early exit before proceeding.
     * <p>
     * This method will be called on the browser process UI thread.
     * <p>Definition generated from cef_browser_process_handler_capi.h
     * <pre>int (CEF_CALLBACK* on_already_running_app_relaunch)(struct _cef_browser_process_handler_t* self, struct _cef_command_line_t* command_line, const cef_string_t* current_directory);</pre>
     *
     * @param currentDirectory may be null
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__browser__process__handler_8h.html">cef_browser_process_handler.h:100</a>
     */
    default boolean onAlreadyRunningAppRelaunch(@Nullable CefCommandLine commandLine, @Nullable String currentDirectory) {
        return false;
    }

    /**
     * Called from any thread when work has been scheduled for the browser process main (UI) thread. This callback is used in combination with cef_settings_t.external_message_pump and CefDoMessageLoopWork() in cases where the CEF message loop must be integrated into an existing application message loop (see additional comments and warnings on CefDoMessageLoopWork). This callback should schedule a CefDoMessageLoopWork() call to happen on the main (UI) thread. {@code delay_ms} is the requested delay in milliseconds. If {@code delay_ms} is &lt;= 0 then the call should happen reasonably soon. If {@code delay_ms} is > 0 then the call should be scheduled to happen after the specified delay and any currently pending scheduled call should be cancelled.
     * <p>Definition generated from cef_browser_process_handler_capi.h
     * <pre>void (CEF_CALLBACK* on_schedule_message_pump_work)(struct _cef_browser_process_handler_t* self, int64_t delay_ms);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__browser__process__handler_8h.html">cef_browser_process_handler.h:125</a>
     */
    default void onScheduleMessagePumpWork(long delayMs) {
    }

    /**
     * Return the default client for use with a newly created browser window (CefBrowser object). If null is returned the CefBrowser will be unmanaged (no callbacks will be executed for that CefBrowser) and application shutdown will be blocked until the browser window is closed manually. This method is currently only used with Chrome style when creating new browser windows via Chrome UI.
     * <p>Definition generated from cef_browser_process_handler_capi.h
     * <pre>cef_client_t* (CEF_CALLBACK* get_default_client)(struct _cef_browser_process_handler_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__browser__process__handler_8h.html">cef_browser_process_handler.h:141</a>
     */
    default Optional<CefClient> getDefaultClient() {
        return Optional.empty();
    }

    default Optional<CefRequestContextHandler> getDefaultRequestContextHandler() {
        return Optional.empty();
    }
    /**
     * Composite that fans callbacks out to every registered delegate. {@code void} methods invoke all
     * delegates in order; {@code boolean} methods short-circuit on the first {@code true}; handler-returning
     * {@code Optional}s collect every non-empty delegate and wrap them in the handler's own {@code Delegating}
     * wrapper; other {@code Optional}s pick the first non-empty; any other return type yields the first
     * delegate's value.
     */
    class Delegating implements CefBrowserProcessHandler {
        private final java.util.List<CefBrowserProcessHandler> delegates;

        public Delegating(java.util.List<CefBrowserProcessHandler> delegates) {
            this.delegates = java.util.List.copyOf(delegates);
        }

        @Override
        public void onRegisterCustomPreferences(@Nonnull CefPreferencesType type, @Nullable CefPreferenceRegistrar registrar) {
            for (CefBrowserProcessHandler d : delegates) d.onRegisterCustomPreferences(type, registrar);
        }

        @Override
        public void onContextInitialized() {
            for (CefBrowserProcessHandler d : delegates) d.onContextInitialized();
        }

        @Override
        public void onBeforeChildProcessLaunch(@Nullable CefCommandLine commandLine) {
            for (CefBrowserProcessHandler d : delegates) d.onBeforeChildProcessLaunch(commandLine);
        }

        @Override
        public boolean onAlreadyRunningAppRelaunch(@Nullable CefCommandLine commandLine, @Nullable String currentDirectory) {
            for (CefBrowserProcessHandler d : delegates) {
                if (d.onAlreadyRunningAppRelaunch(commandLine, currentDirectory)) return true;
            }
            if (!delegates.isEmpty()) return false;
            return false;
        }

        @Override
        public void onScheduleMessagePumpWork(long delayMs) {
            for (CefBrowserProcessHandler d : delegates) d.onScheduleMessagePumpWork(delayMs);
        }

        @Override
        public Optional<CefClient> getDefaultClient() {
            java.util.ArrayList<CefClient> collected = new java.util.ArrayList<>();
            for (CefBrowserProcessHandler d : delegates) d.getDefaultClient().ifPresent(collected::add);
            return collected.isEmpty()
                    ? Optional.empty()
                    : Optional.of(new CefClient.Delegating(collected));
        }

        @Override
        public Optional<CefRequestContextHandler> getDefaultRequestContextHandler() {
            java.util.ArrayList<CefRequestContextHandler> collected = new java.util.ArrayList<>();
            for (CefBrowserProcessHandler d : delegates) d.getDefaultRequestContextHandler().ifPresent(collected::add);
            return collected.isEmpty()
                    ? Optional.empty()
                    : Optional.of(new CefRequestContextHandler.Delegating(collected));
        }
    }

}
