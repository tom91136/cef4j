// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;

/**
 * V8 property attribute values.
 * <p>Definition generated from cef_types.h
 * <pre>typedef enum {
 *   V8_PROPERTY_ATTRIBUTE_NONE = 0,
 *   V8_PROPERTY_ATTRIBUTE_READONLY = 1 &lt;&lt; 0,
 *   V8_PROPERTY_ATTRIBUTE_DONTENUM = 1 &lt;&lt; 1,
 *   V8_PROPERTY_ATTRIBUTE_DONTDELETE = 1 &lt;&lt; 2
 * } cef_v8_propertyattribute_t;</pre>
 * <p>Possible values: {@link Kind#NONE}, {@link Kind#READONLY}, {@link Kind#DONTENUM}, {@link Kind#DONTDELETE}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__types_8h.html">cef_types.h</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public final class CefV8PropertyAttribute implements CefEnum<CefV8PropertyAttribute> {

    /** Known constants for {@link CefV8PropertyAttribute}. */
    public enum Kind {
        /** Writeable, Enumerable, Configurable  */
        NONE(0, "0", "V8_PROPERTY_ATTRIBUTE_NONE"),
        /** Not writeable  */
        READONLY(1 << 0, "1 << 0", "V8_PROPERTY_ATTRIBUTE_READONLY"),
        /** Not enumerable  */
        DONTENUM(1 << 1, "1 << 1", "V8_PROPERTY_ATTRIBUTE_DONTENUM"),
        /** Not configurable  */
        DONTDELETE(1 << 2, "1 << 2", "V8_PROPERTY_ATTRIBUTE_DONTDELETE");

        private static final Kind[] VALUES = Kind.values();

        /** The underlying C enum numeric value. */
        public final long value;

        /** The original C expression/literal (e.g. {@code "1 << 3"}), or the numeric string. */
        public final String expr;

        /** The C constant name (e.g., {@code "cef_v8_propertyattribute_t"}). */
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

    private CefV8PropertyAttribute(long value) {
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
    public static CefV8PropertyAttribute of(long v) {
        return new CefV8PropertyAttribute(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefV8PropertyAttribute of(Kind k) {
        return new CefV8PropertyAttribute(k.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefV8PropertyAttribute)) return false;
        return this.value == ((CefV8PropertyAttribute) obj).value;
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
