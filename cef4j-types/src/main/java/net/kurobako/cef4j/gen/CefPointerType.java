// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-types
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * The device type that caused the event.
 *
 * <p>Definition generated from cef_types.h
 *
 * <pre>typedef enum {
 *   CEF_POINTER_TYPE_TOUCH = 0,
 *   CEF_POINTER_TYPE_MOUSE = 1,
 *   CEF_POINTER_TYPE_PEN = 2,
 *   CEF_POINTER_TYPE_ERASER = 3,
 *   CEF_POINTER_TYPE_UNKNOWN = 4
 * } cef_pointer_type_t;</pre>
 *
 * <p>Possible values: {@link Kind#TOUCH}, {@link Kind#MOUSE}, {@link Kind#PEN}, {@link Kind#ERASER},
 * {@link Kind#UNKNOWN}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__types_8h.html">cef_types.h</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-types -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@NullableBoundary("generated external ABI")
public final class CefPointerType implements CefEnum<CefPointerType> {

    /** Known constants for {@link CefPointerType}. */
    public enum Kind {
        TOUCH(new EnumConstant(0, "0", "CEF_POINTER_TYPE_TOUCH")),
        MOUSE(new EnumConstant(1, "1", "CEF_POINTER_TYPE_MOUSE")),
        PEN(new EnumConstant(2, "2", "CEF_POINTER_TYPE_PEN")),
        ERASER(new EnumConstant(3, "3", "CEF_POINTER_TYPE_ERASER")),
        UNKNOWN(new EnumConstant(4, "4", "CEF_POINTER_TYPE_UNKNOWN"));

        private static final Kind[] VALUES = Kind.values();

        private final EnumConstant constant;

        Kind(EnumConstant constant) {
            this.constant = constant;
        }

        /** The underlying C enum numeric value. */
        public long value() {
            return constant.value();
        }

        /** The original C expression/literal (e.g. {@code "1 << 3"}), or the numeric string. */
        public String expr() {
            return constant.expr();
        }

        /** The C constant name (e.g., {@code "cef_pointer_type_t"}). */
        public String cName() {
            return constant.name();
        }

        @Override
        public String toString() {
            return cName() + "(expr=" + expr() + ", value=" + value() + ")";
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
        return kind().map(Kind::expr).orElse(String.valueOf(value));
    }

    /** {@inheritDoc} */
    @Override
    public String name() {
        return kind().map(Kind::cName).orElse("UNKNOWN(" + value + ")");
    }

    /**
     * Returns the {@link Kind} matching this value, or empty for unknown/composite values. Use this for exhaustive
     * switch over known constants.
     */
    public java.util.Optional<Kind> kind() {
        for (Kind k : Kind.VALUES) {
            if (k.value() == value) return java.util.Optional.of(k);
        }
        return java.util.Optional.empty();
    }

    /** Returns an instance for the given raw value, use {@link #kind} to resolve to a concrete enum. */
    public static CefPointerType of(long v) {
        return new CefPointerType(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefPointerType of(Kind k) {
        return new CefPointerType(k.value());
    }

    @Override
    public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
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
