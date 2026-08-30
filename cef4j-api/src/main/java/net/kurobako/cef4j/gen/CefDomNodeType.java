// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-api
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * DOM node types.
 *
 * <p>Definition generated from cef_types.h
 *
 * <pre>typedef enum {
 *   DOM_NODE_TYPE_UNSUPPORTED = 0,
 *   DOM_NODE_TYPE_ELEMENT = 1,
 *   DOM_NODE_TYPE_ATTRIBUTE = 2,
 *   DOM_NODE_TYPE_TEXT = 3,
 *   DOM_NODE_TYPE_CDATA_SECTION = 4,
 *   ...
 * } cef_dom_node_type_t;</pre>
 *
 * <p>Possible values: {@link Kind#UNSUPPORTED}, {@link Kind#ELEMENT}, {@link Kind#ATTRIBUTE}, {@link Kind#TEXT},
 * {@link Kind#CDATA_SECTION}, {@link Kind#PROCESSING_INSTRUCTIONS}, {@link Kind#COMMENT}, {@link Kind#DOCUMENT},
 * {@link Kind#DOCUMENT_TYPE}, {@link Kind#DOCUMENT_FRAGMENT}, {@link Kind#NUM_VALUES}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__types_8h.html">cef_types.h</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-api -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
@NullableBoundary("generated external ABI")
public final class CefDomNodeType implements CefEnum<CefDomNodeType> {

    /** Known constants for {@link CefDomNodeType}. */
    public enum Kind {
        UNSUPPORTED(0, "0", "DOM_NODE_TYPE_UNSUPPORTED"),
        ELEMENT(1, "1", "DOM_NODE_TYPE_ELEMENT"),
        ATTRIBUTE(2, "2", "DOM_NODE_TYPE_ATTRIBUTE"),
        TEXT(3, "3", "DOM_NODE_TYPE_TEXT"),
        CDATA_SECTION(4, "4", "DOM_NODE_TYPE_CDATA_SECTION"),
        PROCESSING_INSTRUCTIONS(5, "5", "DOM_NODE_TYPE_PROCESSING_INSTRUCTIONS"),
        COMMENT(6, "6", "DOM_NODE_TYPE_COMMENT"),
        DOCUMENT(7, "7", "DOM_NODE_TYPE_DOCUMENT"),
        DOCUMENT_TYPE(8, "8", "DOM_NODE_TYPE_DOCUMENT_TYPE"),
        DOCUMENT_FRAGMENT(9, "9", "DOM_NODE_TYPE_DOCUMENT_FRAGMENT"),
        NUM_VALUES(10, "10", "DOM_NODE_TYPE_NUM_VALUES");

        private static final Kind[] VALUES = Kind.values();

        /** The underlying C enum numeric value. */
        public final long value;

        /** The original C expression/literal (e.g. {@code "1 << 3"}), or the numeric string. */
        public final String expr;

        /** The C constant name (e.g., {@code "cef_dom_node_type_t"}). */
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

    private CefDomNodeType(long value) {
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
    public static CefDomNodeType of(long v) {
        return new CefDomNodeType(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefDomNodeType of(Kind k) {
        return new CefDomNodeType(k.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefDomNodeType)) return false;
        return this.value == ((CefDomNodeType) obj).value;
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
