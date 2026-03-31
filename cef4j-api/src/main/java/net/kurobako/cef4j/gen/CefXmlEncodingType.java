// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Supported XML encoding types. The parser supports ASCII, ISO-8859-1, and UTF16 (LE and BE) by default. All other
 * types must be translated to UTF8 before being passed to the parser. If a BOM is detected and the correct decoder is
 * available then that decoder will be used automatically.
 */
public enum CefXmlEncodingType {
    XML_ENCODING_NONE(0L),
    XML_ENCODING_UTF8(1L),
    XML_ENCODING_UTF16LE(2L),
    XML_ENCODING_UTF16BE(3L),
    XML_ENCODING_ASCII(4L),
    XML_ENCODING_NUM_VALUES(5L),
    UNKNOWN(-1L);

    public final long value;

    CefXmlEncodingType(long v) {
        this.value = v;
    }

    public static CefXmlEncodingType fromLong(long v) {
        for (CefXmlEncodingType e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
