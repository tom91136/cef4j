// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;

/**
 * Structure representing CefExecuteProcess arguments.
 * <p>Definition generated from internal/cef_types_linux.h
 * <pre>typedef struct _cef_main_args_t {
 *   int argc;
 *   char** argv;
 * } cef_main_args_t;</pre>
 * Platform-specific implementations: {@link net.kurobako.cef4j.gen.linux.CefMainArgs}, {@link net.kurobako.cef4j.gen.mac.CefMainArgs}, {@link net.kurobako.cef4j.gen.win.CefMainArgs}.
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__types__linux_8h.html">internal/cef_types_linux.h:77</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused", "NullableForbidden"})
public interface CefMainArgs {


    public interface Mutable {}

}
