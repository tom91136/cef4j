// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** XML node types. */
public enum CefXmlNodeType {
    XML_NODE_UNSUPPORTED(0L),
    XML_NODE_PROCESSING_INSTRUCTION(1L),
    XML_NODE_DOCUMENT_TYPE(2L),
    XML_NODE_ELEMENT_START(3L),
    XML_NODE_ELEMENT_END(4L),
    XML_NODE_ATTRIBUTE(5L),
    XML_NODE_TEXT(6L),
    XML_NODE_CDATA(7L),
    XML_NODE_ENTITY_REFERENCE(8L),
    XML_NODE_WHITESPACE(9L),
    XML_NODE_COMMENT(10L),
    XML_NODE_NUM_VALUES(11L),
    UNKNOWN(-1L);

    public final long value;

    CefXmlNodeType(long v) {
        this.value = v;
    }

    public static CefXmlNodeType fromLong(long v) {
        for (CefXmlNodeType e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
