// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-api
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen.mac;

import java.util.List;
import javax.annotation.Nullable;
import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * Structure representing CefExecuteProcess arguments.
 *
 * <p>Definition generated from internal/cef_types_mac.h
 *
 * <pre>typedef struct _cef_main_args_t {
 *   int argc;
 *   char** argv;
 * } cef_main_args_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__types__mac_8h.html">internal/cef_types_mac.h:83</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-api -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
@NullableBoundary("generated external ABI")
public final class CefMainArgs implements net.kurobako.cef4j.gen.CefMainArgs {

    public final int argc;
    public final @Nullable List<String> argv;

    public CefMainArgs(int argc, @Nullable List<String> argv) {
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

    /**
     * Mutable variant of {@link CefMainArgs}. Structure representing CefExecuteProcess arguments.
     *
     * <p>Definition generated from internal/cef_types_mac.h
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__types__mac_8h.html">internal/cef_types_mac.h:83</a>
     */
    public static final class Mutable implements net.kurobako.cef4j.gen.CefMainArgs.Mutable {

        public int argc;
        public @Nullable List<String> argv;

        public Mutable() {}

        public Mutable(int argc, @Nullable List<String> argv) {
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
            return this.argc == other.argc && java.util.Objects.equals(this.argv, other.argv);
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
