// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Supported JavaScript dialog types. */
public enum CefJsdialogType {
    JSDIALOGTYPE_ALERT(0L),
    JSDIALOGTYPE_CONFIRM(1L),
    JSDIALOGTYPE_PROMPT(2L),
    JSDIALOGTYPE_NUM_VALUES(3L),
    UNKNOWN(-1L);

    public final long value;

    CefJsdialogType(long v) {
        this.value = v;
    }

    public static CefJsdialogType fromLong(long v) {
        for (CefJsdialogType e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
