// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import java.util.Optional;
import javax.annotation.Nullable;

/**
 * Implement this interface to provide handler implementations. Methods will be called by the process and/or thread indicated.
 * <p>Definition generated from cef_app_capi.h
 * <pre>typedef struct _cef_app_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_app_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__app_8h.html">cef_app.h:188</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public interface CefApp extends CefClientHandler {

    /**
     * Provides an opportunity to view and/or modify command-line arguments before processing by CEF and Chromium. The {@code process_type} value will be empty for the browser process. Do not keep a reference to the CefCommandLine object passed to this method. The cef_settings_t.command_line_args_disabled value can be used to start with an empty command-line object. Any values specified in CefSettings that equate to command-line arguments will be set before this method is called. Be cautious when using this method to modify command-line arguments for non-browser processes as this may result in undefined behavior including crashes.
     * <p>Definition generated from cef_app_capi.h
     * <pre>void (CEF_CALLBACK* on_before_command_line_processing)(struct _cef_app_t* self, const cef_string_t* process_type, struct _cef_command_line_t* command_line);</pre>
     *
     * @param processType may be null
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__app_8h.html">cef_app.h:195</a>
     */
    default void onBeforeCommandLineProcessing(@Nullable String processType, @Nullable CefCommandLine commandLine) {
    }

    /**
     * Provides an opportunity to register custom schemes. Do not keep a reference to the {@code registrar} object. This method is called on the main thread for each process and the registered schemes should be the same across all processes.
     * <p>Definition generated from cef_app_capi.h
     * <pre>void (CEF_CALLBACK* on_register_custom_schemes)(struct _cef_app_t* self, struct _cef_scheme_registrar_t* registrar);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__app_8h.html">cef_app.h:212</a>
     */
    default void onRegisterCustomSchemes(@Nullable CefSchemeRegistrar registrar) {
    }

    /**
     * Return the handler for resource bundle events. If no handler is returned resources will be loaded from pack files. This method is called by the browser and render processes on multiple threads.
     * <p>Definition generated from cef_app_capi.h
     * <pre>cef_resource_bundle_handler_t* (CEF_CALLBACK* get_resource_bundle_handler)(struct _cef_app_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__app_8h.html">cef_app.h:222</a>
     */
    default Optional<CefResourceBundleHandler> getResourceBundleHandler() {
        return Optional.empty();
    }

    /**
     * Return the handler for functionality specific to the browser process. This method is called on multiple threads in the browser process.
     * <p>Definition generated from cef_app_capi.h
     * <pre>cef_browser_process_handler_t* (CEF_CALLBACK* get_browser_process_handler)(struct _cef_app_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__app_8h.html">cef_app.h:232</a>
     */
    default Optional<CefBrowserProcessHandler> getBrowserProcessHandler() {
        return Optional.empty();
    }

    /**
     * Return the handler for functionality specific to the render process. This method is called on the render process main thread.
     * <p>Definition generated from cef_app_capi.h
     * <pre>cef_render_process_handler_t* (CEF_CALLBACK* get_render_process_handler)(struct _cef_app_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__app_8h.html">cef_app.h:241</a>
     */
    default Optional<CefRenderProcessHandler> getRenderProcessHandler() {
        return Optional.empty();
    }
    /**
     * Composite that fans callbacks out to every registered delegate. {@code void} methods invoke all
     * delegates in order; {@code boolean} methods short-circuit on the first {@code true}; handler-returning
     * {@code Optional}s collect every non-empty delegate and wrap them in the handler's own {@code Delegating}
     * wrapper; other {@code Optional}s pick the first non-empty; any other return type yields the first
     * delegate's value.
     */
    class Delegating implements CefApp {
        private final java.util.List<CefApp> delegates;

        public Delegating(java.util.List<CefApp> delegates) {
            this.delegates = java.util.List.copyOf(delegates);
        }

        @Override
        public void onBeforeCommandLineProcessing(@Nullable String processType, @Nullable CefCommandLine commandLine) {
            for (CefApp d : delegates) d.onBeforeCommandLineProcessing(processType, commandLine);
        }

        @Override
        public void onRegisterCustomSchemes(@Nullable CefSchemeRegistrar registrar) {
            for (CefApp d : delegates) d.onRegisterCustomSchemes(registrar);
        }

        @Override
        public Optional<CefResourceBundleHandler> getResourceBundleHandler() {
            java.util.ArrayList<CefResourceBundleHandler> collected = new java.util.ArrayList<>();
            for (CefApp d : delegates) d.getResourceBundleHandler().ifPresent(collected::add);
            return collected.isEmpty()
                    ? Optional.empty()
                    : Optional.of(new CefResourceBundleHandler.Delegating(collected));
        }

        @Override
        public Optional<CefBrowserProcessHandler> getBrowserProcessHandler() {
            java.util.ArrayList<CefBrowserProcessHandler> collected = new java.util.ArrayList<>();
            for (CefApp d : delegates) d.getBrowserProcessHandler().ifPresent(collected::add);
            return collected.isEmpty()
                    ? Optional.empty()
                    : Optional.of(new CefBrowserProcessHandler.Delegating(collected));
        }

        @Override
        public Optional<CefRenderProcessHandler> getRenderProcessHandler() {
            java.util.ArrayList<CefRenderProcessHandler> collected = new java.util.ArrayList<>();
            for (CefApp d : delegates) d.getRenderProcessHandler().ifPresent(collected::add);
            return collected.isEmpty()
                    ? Optional.empty()
                    : Optional.of(new CefRenderProcessHandler.Delegating(collected));
        }
    }

}
