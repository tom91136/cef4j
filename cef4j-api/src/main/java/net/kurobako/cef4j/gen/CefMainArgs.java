// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
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
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types__linux_8h.html">internal/cef_types_linux.h:77</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public interface CefMainArgs {


    public interface Mutable {}

}
