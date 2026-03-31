// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** DOM node types. */
public enum CefDomNodeType {
    DOM_NODE_TYPE_UNSUPPORTED(0L),
    DOM_NODE_TYPE_ELEMENT(1L),
    DOM_NODE_TYPE_ATTRIBUTE(2L),
    DOM_NODE_TYPE_TEXT(3L),
    DOM_NODE_TYPE_CDATA_SECTION(4L),
    DOM_NODE_TYPE_PROCESSING_INSTRUCTIONS(5L),
    DOM_NODE_TYPE_COMMENT(6L),
    DOM_NODE_TYPE_DOCUMENT(7L),
    DOM_NODE_TYPE_DOCUMENT_TYPE(8L),
    DOM_NODE_TYPE_DOCUMENT_FRAGMENT(9L),
    DOM_NODE_TYPE_NUM_VALUES(10L),
    UNKNOWN(-1L);

    public final long value;

    CefDomNodeType(long v) {
        this.value = v;
    }

    public static CefDomNodeType fromLong(long v) {
        for (CefDomNodeType e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
