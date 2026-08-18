// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen.win;

import javax.annotation.processing.Generated;

/**
 * Structure representing CefExecuteProcess arguments.
 * <p>Definition generated from internal/cef_types_win.h
 * <pre>typedef struct _cef_main_args_t {
 *   int64_t instance;
 * } cef_main_args_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__types__win_8h.html">internal/cef_types_win.h:64</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused", "NullableForbidden"})
public final class CefMainArgs implements net.kurobako.cef4j.gen.CefMainArgs {

    public final long instance;

    public CefMainArgs(long instance) {
        this.instance = instance;
    }

    /** Create a mutable copy of this instance. */
    public Mutable toMutable() {
        return new Mutable(this.instance);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefMainArgs)) return false;
        CefMainArgs other = (CefMainArgs) obj;
        return this.instance == other.instance;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(instance);
    }

    @Override
    public String toString() {
        return "CefMainArgs{" + "instance=" + instance + "}";
    }

    /**
     * Mutable variant of {@link CefMainArgs}. Structure representing CefExecuteProcess arguments.
     * <p>Definition generated from internal/cef_types_win.h
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__types__win_8h.html">internal/cef_types_win.h:64</a>
     */
    public static final class Mutable implements net.kurobako.cef4j.gen.CefMainArgs.Mutable {

        public long instance;

        public Mutable() {}

        public Mutable(long instance) {
            this.instance = instance;
        }

        /** Create an immutable snapshot of this instance. */
        public CefMainArgs toImmutable() {
            return new CefMainArgs(this.instance);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Mutable)) return false;
            Mutable other = (Mutable) obj;
            return this.instance == other.instance;
        }

        @Override
        public int hashCode() {
            return java.util.Objects.hash(instance);
        }

        @Override
        public String toString() {
            return "CefMainArgs.Mutable{" + "instance=" + instance + "}";
        }
    }
}
