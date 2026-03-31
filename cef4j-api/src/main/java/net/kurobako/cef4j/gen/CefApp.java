// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import javax.annotation.Nullable;

/**
 * Implement this interface to provide handler implementations. Methods will be called by the process and/or thread
 * indicated.
 */
public interface CefApp {

    /**
     * Provides an opportunity to view and/or modify command-line arguments before processing by CEF and Chromium. The
     * |process_type| value will be empty for the browser process. Do not keep a reference to the CefCommandLine object
     * passed to this method. The cef_settings_t.command_line_args_disabled value can be used to start with an empty
     * command-line object. Any values specified in CefSettings that equate to command-line arguments will be set before
     * this method is called. Be cautious when using this method to modify command-line arguments for non-browser
     * processes as this may result in undefined behavior including crashes.
     *
     * @param processType may be null
     */
    default void onBeforeCommandLineProcessing(@Nullable String processType, long commandLine) {}

    /**
     * Provides an opportunity to register custom schemes. Do not keep a reference to the |registrar| object. This
     * method is called on the main thread for each process and the registered schemes should be the same across all
     * processes.
     */
    default void onRegisterCustomSchemes(long registrar) {}
}
