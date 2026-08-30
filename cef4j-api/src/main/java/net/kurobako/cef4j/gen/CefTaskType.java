// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-api
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * Specifies the task type variants supported by CefTaskManager. Should be kept in sync with Chromium's
 * task_manager::Task::Type type.
 *
 * <p>Definition generated from cef_types.h
 *
 * <pre>typedef enum {
 *   CEF_TASK_TYPE_UNKNOWN = 0,
 *   CEF_TASK_TYPE_BROWSER = 1,
 *   CEF_TASK_TYPE_GPU = 2,
 *   CEF_TASK_TYPE_ZYGOTE = 3,
 *   CEF_TASK_TYPE_UTILITY = 4,
 *   ...
 * } cef_task_type_t;</pre>
 *
 * <p>Possible values: {@link Kind#UNKNOWN}, {@link Kind#BROWSER}, {@link Kind#GPU}, {@link Kind#ZYGOTE},
 * {@link Kind#UTILITY}, {@link Kind#RENDERER}, {@link Kind#EXTENSION}, {@link Kind#GUEST},
 * {@link Kind#PLUGIN_DEPRECATED}, {@link Kind#SANDBOX_HELPER}, {@link Kind#DEDICATED_WORKER},
 * {@link Kind#SHARED_WORKER}, {@link Kind#SERVICE_WORKER}, {@link Kind#NUM_VALUES}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__types_8h.html">cef_types.h</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-api -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
@NullableBoundary("generated external ABI")
public final class CefTaskType implements CefEnum<CefTaskType> {

    /** Known constants for {@link CefTaskType}. */
    public enum Kind {
        UNKNOWN(0, "0", "CEF_TASK_TYPE_UNKNOWN"),
        /** The main browser process. */
        BROWSER(1, "1", "CEF_TASK_TYPE_BROWSER"),
        /** A graphics process. */
        GPU(2, "2", "CEF_TASK_TYPE_GPU"),
        /** A Linux zygote process. */
        ZYGOTE(3, "3", "CEF_TASK_TYPE_ZYGOTE"),
        /** A browser utility process. */
        UTILITY(4, "4", "CEF_TASK_TYPE_UTILITY"),
        /** A normal WebContents renderer process. */
        RENDERER(5, "5", "CEF_TASK_TYPE_RENDERER"),
        /** An extension or app process. */
        EXTENSION(6, "6", "CEF_TASK_TYPE_EXTENSION"),
        /** A browser plugin guest process. */
        GUEST(7, "7", "CEF_TASK_TYPE_GUEST"),
        PLUGIN_DEPRECATED(8, "8", "CEF_TASK_TYPE_PLUGIN_DEPRECATED"),
        /** A sandbox helper process */
        SANDBOX_HELPER(9, "9", "CEF_TASK_TYPE_SANDBOX_HELPER"),
        /** A dedicated worker running on the renderer process. */
        DEDICATED_WORKER(10, "10", "CEF_TASK_TYPE_DEDICATED_WORKER"),
        /** A shared worker running on the renderer process. */
        SHARED_WORKER(11, "11", "CEF_TASK_TYPE_SHARED_WORKER"),
        /** A service worker running on the renderer process. */
        SERVICE_WORKER(12, "12", "CEF_TASK_TYPE_SERVICE_WORKER"),
        NUM_VALUES(13, "13", "CEF_TASK_TYPE_NUM_VALUES");

        private static final Kind[] VALUES = Kind.values();

        /** The underlying C enum numeric value. */
        public final long value;

        /** The original C expression/literal (e.g. {@code "1 << 3"}), or the numeric string. */
        public final String expr;

        /** The C constant name (e.g., {@code "cef_task_type_t"}). */
        public final String name;

        Kind(long value, String expr, String name) {
            this.value = value;
            this.expr = expr;
            this.name = name;
        }

        @Override
        public String toString() {
            return name + "(expr=" + expr + ", value=" + value + ")";
        }
    }

    /** The underlying C enum numeric value. This may not correspond to any known {@link Kind}. */
    public final long value;

    private CefTaskType(long value) {
        this.value = value;
    }

    /** {@inheritDoc} */
    @Override
    public long value() {
        return value;
    }

    /** {@inheritDoc} */
    @Override
    public String expr() {
        return kind().map(k -> k.expr).orElse(String.valueOf(value));
    }

    /** {@inheritDoc} */
    @Override
    public String name() {
        return kind().map(k -> k.name).orElse("UNKNOWN(" + value + ")");
    }

    /**
     * Returns the {@link Kind} matching this value, or empty for unknown/composite values. Use this for exhaustive
     * switch over known constants.
     */
    public java.util.Optional<Kind> kind() {
        for (Kind k : Kind.VALUES) {
            if (k.value == value) return java.util.Optional.of(k);
        }
        return java.util.Optional.empty();
    }

    /** Returns an instance for the given raw value, use {@link #kind} to resolve to a concrete enum. */
    public static CefTaskType of(long v) {
        return new CefTaskType(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefTaskType of(Kind k) {
        return new CefTaskType(k.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefTaskType)) return false;
        return this.value == ((CefTaskType) obj).value;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(value);
    }

    @Override
    public String toString() {
        return kind().map(Kind::toString).orElse("UNKNOWN(value=" + value + ")");
    }
}
