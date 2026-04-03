// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
// -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;

/**
 * Structure representing CefExecuteProcess arguments.
 *
 * <p>Definition generated from cef_types_linux.h
 *
 * <pre>typedef struct _cef_main_args_t {
 *   int argc;
 *   char** argv;
 * } cef_main_args_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types__linux_8h.html">cef_types_linux.h:77</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
public final class CefMainArgs {

    public final int argc;
    public final long argv;

    public CefMainArgs(int argc, long argv) {
        this.argc = argc;
        this.argv = argv;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefMainArgs)) return false;
        CefMainArgs other = (CefMainArgs) obj;
        return this.argc == other.argc && java.util.Objects.equals(this.argv, other.argv);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(argc, argv);
    }

    @Override
    public String toString() {
        return "CefMainArgs{" + "argc=" + argc + ", " + "argv=" + argv + "}";
    }
}
