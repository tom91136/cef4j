// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * XML node types.
 *
 * <p>Definition generated from cef_types.h
 *
 * <pre>typedef enum {
 *   XML_NODE_UNSUPPORTED = 0,
 *   XML_NODE_PROCESSING_INSTRUCTION = 1,
 *   XML_NODE_DOCUMENT_TYPE = 2,
 *   XML_NODE_ELEMENT_START = 3,
 *   XML_NODE_ELEMENT_END = 4,
 *   ...
 * } cef_xml_node_type_t;</pre>
 *
 * <p>Possible values: {@link Kind#UNSUPPORTED}, {@link Kind#PROCESSING_INSTRUCTION}, {@link Kind#DOCUMENT_TYPE},
 * {@link Kind#ELEMENT_START}, {@link Kind#ELEMENT_END}, {@link Kind#ATTRIBUTE}, {@link Kind#TEXT}, {@link Kind#CDATA},
 * {@link Kind#ENTITY_REFERENCE}, {@link Kind#WHITESPACE}, {@link Kind#COMMENT}, {@link Kind#NUM_VALUES}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types_8h.html">cef_types.h</a>
 */
public final class CefXmlNodeType implements CefEnum<CefXmlNodeType> {

    /** Known constants for {@link CefXmlNodeType}. */
    public enum Kind {
        UNSUPPORTED(0, "0", "XML_NODE_UNSUPPORTED"),
        PROCESSING_INSTRUCTION(1, "1", "XML_NODE_PROCESSING_INSTRUCTION"),
        DOCUMENT_TYPE(2, "2", "XML_NODE_DOCUMENT_TYPE"),
        ELEMENT_START(3, "3", "XML_NODE_ELEMENT_START"),
        ELEMENT_END(4, "4", "XML_NODE_ELEMENT_END"),
        ATTRIBUTE(5, "5", "XML_NODE_ATTRIBUTE"),
        TEXT(6, "6", "XML_NODE_TEXT"),
        CDATA(7, "7", "XML_NODE_CDATA"),
        ENTITY_REFERENCE(8, "8", "XML_NODE_ENTITY_REFERENCE"),
        WHITESPACE(9, "9", "XML_NODE_WHITESPACE"),
        COMMENT(10, "10", "XML_NODE_COMMENT"),
        NUM_VALUES(11, "11", "XML_NODE_NUM_VALUES");

        private static final Kind[] VALUES = Kind.values();

        /** The underlying C enum numeric value. */
        public final long value;

        /** The original C expression (e.g., {@code "1 << 3"}), or the numeric string. */
        public final String expr;

        /** The C constant name (e.g., {@code "cef_xml_node_type_t"}). */
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

    /** The underlying C enum numeric value. May not correspond to any known {@link Kind}. */
    public final long value;

    private CefXmlNodeType(long value) {
        this.value = value;
    }

    @Override
    public long value() {
        return value;
    }

    @Override
    public String expr() {
        return kind().map(k -> k.expr).orElse(String.valueOf(value));
    }

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

    /** Returns an instance for the given raw value. No data is lost — unknown or composite values are preserved. */
    public static CefXmlNodeType of(long v) {
        return new CefXmlNodeType(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefXmlNodeType of(Kind k) {
        return new CefXmlNodeType(k.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefXmlNodeType)) return false;
        return this.value == ((CefXmlNodeType) obj).value;
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
