// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Chrome toolbar types. */
public enum CefChromeToolbarType {
    CEF_CTT_UNKNOWN(0L),
    CEF_CTT_NONE(1L),
    CEF_CTT_NORMAL(2L),
    CEF_CTT_LOCATION(3L),
    CEF_CTT_NUM_VALUES(4L),
    UNKNOWN(-1L);

    public final long value;

    CefChromeToolbarType(long v) {
        this.value = v;
    }

    public static CefChromeToolbarType fromLong(long v) {
        for (CefChromeToolbarType e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
