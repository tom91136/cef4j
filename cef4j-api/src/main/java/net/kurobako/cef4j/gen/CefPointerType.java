// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;

/**
 * The device type that caused the event.
 * <p>Definition generated from cef_types.h
 * <pre>typedef enum {
 *   CEF_POINTER_TYPE_TOUCH = 0,
 *   CEF_POINTER_TYPE_MOUSE = 1,
 *   CEF_POINTER_TYPE_PEN = 2,
 *   CEF_POINTER_TYPE_ERASER = 3,
 *   CEF_POINTER_TYPE_UNKNOWN = 4
 * } cef_pointer_type_t;</pre>
 * <p>Possible values: {@link Kind#TOUCH}, {@link Kind#MOUSE}, {@link Kind#PEN}, {@link Kind#ERASER}, {@link Kind#UNKNOWN}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types_8h.html">cef_types.h</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public final class CefPointerType implements CefEnum<CefPointerType> {

    /** Known constants for {@link CefPointerType}. */
    public enum Kind {
        TOUCH(0, "0", "CEF_POINTER_TYPE_TOUCH"),
        MOUSE(1, "1", "CEF_POINTER_TYPE_MOUSE"),
        PEN(2, "2", "CEF_POINTER_TYPE_PEN"),
        ERASER(3, "3", "CEF_POINTER_TYPE_ERASER"),
        UNKNOWN(4, "4", "CEF_POINTER_TYPE_UNKNOWN");

        private static final Kind[] VALUES = Kind.values();

        /** The underlying C enum numeric value. */
        public final long value;

        /** The original C expression/literal (e.g. {@code "1 << 3"}), or the numeric string. */
        public final String expr;

        /** The C constant name (e.g., {@code "cef_pointer_type_t"}). */
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

    private CefPointerType(long value) {
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
     * Returns the {@link Kind} matching this value, or empty for unknown/composite values.
     * Use this for exhaustive switch over known constants.
     */
    public java.util.Optional<Kind> kind() {
        for (Kind k : Kind.VALUES) {
            if (k.value == value) return java.util.Optional.of(k);
        }
        return java.util.Optional.empty();
    }

    /** Returns an instance for the given raw value, use {@link #kind} to resolve to a concrete enum. */
    public static CefPointerType of(long v) {
        return new CefPointerType(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefPointerType of(Kind k) {
        return new CefPointerType(k.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefPointerType)) return false;
        return this.value == ((CefPointerType) obj).value;
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
