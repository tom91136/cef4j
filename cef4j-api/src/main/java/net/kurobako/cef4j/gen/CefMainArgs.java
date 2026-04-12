// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import java.util.List;

/**
 * Structure representing CefExecuteProcess arguments.
 * <p>Definition generated from internal/cef_types_linux.h
 * <pre>typedef struct _cef_main_args_t {
 *   int argc;
 *   char** argv;
 * } cef_main_args_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types__linux_8h.html">internal/cef_types_linux.h:77</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public final class CefMainArgs {

    public final int argc;
    public final List<String> argv;

    public CefMainArgs(int argc, List<String> argv) {
        this.argc = argc;
        this.argv = argv;
    }

    /** Create a mutable copy of this instance. */
    public Mutable toMutable() {
        return new Mutable(this.argc, this.argv);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefMainArgs)) return false;
        CefMainArgs other = (CefMainArgs) obj;
        return this.argc == other.argc
                    && java.util.Objects.equals(this.argv, other.argv);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(argc, argv);
    }

    @Override
    public String toString() {
        return "CefMainArgs{" + "argc=" + argc + ", " + "argv=" + argv + "}";
    }

    /**
     * Mutable variant of {@link CefMainArgs}. Structure representing CefExecuteProcess arguments.
     * <p>Definition generated from internal/cef_types_linux.h
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types__linux_8h.html">internal/cef_types_linux.h:77</a>
     */
    public static final class Mutable {

        public int argc;
        public List<String> argv;

        public Mutable() {}

        public Mutable(int argc, List<String> argv) {
            this.argc = argc;
            this.argv = argv;
        }

        /** Create an immutable snapshot of this instance. */
        public CefMainArgs toImmutable() {
            return new CefMainArgs(this.argc, this.argv);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Mutable)) return false;
            Mutable other = (Mutable) obj;
            return this.argc == other.argc
                        && java.util.Objects.equals(this.argv, other.argv);
        }

        @Override
        public int hashCode() {
            return java.util.Objects.hash(argc, argv);
        }

        @Override
        public String toString() {
            return "CefMainArgs.Mutable{" + "argc=" + argc + ", " + "argv=" + argv + "}";
        }
    }
}
