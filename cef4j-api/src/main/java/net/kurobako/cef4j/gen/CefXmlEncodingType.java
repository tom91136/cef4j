// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;

/**
 * Supported XML encoding types. The parser supports ASCII, ISO-8859-1, and UTF16 (LE and BE) by default. All other types must be translated to UTF8 before being passed to the parser. If a BOM is detected and the correct decoder is available then that decoder will be used automatically.
 * <p>Definition generated from cef_types.h
 * <pre>typedef enum {
 *   XML_ENCODING_NONE = 0,
 *   XML_ENCODING_UTF8 = 1,
 *   XML_ENCODING_UTF16LE = 2,
 *   XML_ENCODING_UTF16BE = 3,
 *   XML_ENCODING_ASCII = 4,
 *   ...
 * } cef_xml_encoding_type_t;</pre>
 * <p>Possible values: {@link Kind#NONE}, {@link Kind#UTF8}, {@link Kind#UTF16LE}, {@link Kind#UTF16BE}, {@link Kind#ASCII}, {@link Kind#NUM_VALUES}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__types_8h.html">cef_types.h</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused", "NullableForbidden"})
public final class CefXmlEncodingType implements CefEnum<CefXmlEncodingType> {

    /** Known constants for {@link CefXmlEncodingType}. */
    public enum Kind {
        NONE(0, "0", "XML_ENCODING_NONE"),
        UTF8(1, "1", "XML_ENCODING_UTF8"),
        UTF16LE(2, "2", "XML_ENCODING_UTF16LE"),
        UTF16BE(3, "3", "XML_ENCODING_UTF16BE"),
        ASCII(4, "4", "XML_ENCODING_ASCII"),
        NUM_VALUES(5, "5", "XML_ENCODING_NUM_VALUES");

        private static final Kind[] VALUES = Kind.values();

        /** The underlying C enum numeric value. */
        public final long value;

        /** The original C expression/literal (e.g. {@code "1 << 3"}), or the numeric string. */
        public final String expr;

        /** The C constant name (e.g., {@code "cef_xml_encoding_type_t"}). */
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

    private CefXmlEncodingType(long value) {
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
    public static CefXmlEncodingType of(long v) {
        return new CefXmlEncodingType(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefXmlEncodingType of(Kind k) {
        return new CefXmlEncodingType(k.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefXmlEncodingType)) return false;
        return this.value == ((CefXmlEncodingType) obj).value;
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
