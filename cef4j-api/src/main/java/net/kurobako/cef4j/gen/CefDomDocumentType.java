// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** DOM document types. */
public enum CefDomDocumentType {
    DOM_DOCUMENT_TYPE_UNKNOWN(0L),
    DOM_DOCUMENT_TYPE_HTML(1L),
    DOM_DOCUMENT_TYPE_XHTML(2L),
    DOM_DOCUMENT_TYPE_PLUGIN(3L),
    DOM_DOCUMENT_TYPE_NUM_VALUES(4L),
    UNKNOWN(-1L);

    public final long value;

    CefDomDocumentType(long v) {
        this.value = v;
    }

    public static CefDomDocumentType fromLong(long v) {
        for (CefDomDocumentType e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
