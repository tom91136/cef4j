// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
// -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.Nullable;
import javax.annotation.processing.Generated;

/**
 * Implement this interface to provide handler implementations. Methods will be called by the process and/or thread
 * indicated.
 *
 * <p>Definition generated from cef_app_capi.h
 *
 * <pre>typedef struct _cef_app_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_app_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__app_8h.html">cef_app.h:188</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
public interface CefApp extends CefClientHandler {

    /**
     * Provides an opportunity to view and/or modify command-line arguments before processing by CEF and Chromium. The
     * {@code process_type} value will be empty for the browser process. Do not keep a reference to the CefCommandLine
     * object passed to this method. The cef_settings_t.command_line_args_disabled value can be used to start with an
     * empty command-line object. Any values specified in CefSettings that equate to command-line arguments will be set
     * before this method is called. Be cautious when using this method to modify command-line arguments for non-browser
     * processes as this may result in undefined behavior including crashes.
     *
     * <p>Definition generated from cef_app_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* on_before_command_line_processing)(struct _cef_app_t* self, const cef_string_t* process_type, struct _cef_command_line_t* command_line);
     * </pre>
     *
     * @param processType may be null
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__app_8h.html">cef_app.h:195</a>
     */
    default void onBeforeCommandLineProcessing(@Nullable String processType, @Nullable CefCommandLine commandLine) {}

    /**
     * Provides an opportunity to register custom schemes. Do not keep a reference to the {@code registrar} object. This
     * method is called on the main thread for each process and the registered schemes should be the same across all
     * processes.
     *
     * <p>Definition generated from cef_app_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* on_register_custom_schemes)(struct _cef_app_t* self, struct _cef_scheme_registrar_t* registrar);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__app_8h.html">cef_app.h:212</a>
     */
    default void onRegisterCustomSchemes(@Nullable CefSchemeRegistrar registrar) {}
}
