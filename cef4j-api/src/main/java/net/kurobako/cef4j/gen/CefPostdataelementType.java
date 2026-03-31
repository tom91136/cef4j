// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Post data elements may represent either bytes or files. */
public enum CefPostdataelementType {
    PDE_TYPE_EMPTY(0L),
    PDE_TYPE_BYTES(1L),
    PDE_TYPE_FILE(2L),
    PDE_TYPE_NUM_VALUES(3L),
    UNKNOWN(-1L);

    public final long value;

    CefPostdataelementType(long v) {
        this.value = v;
    }

    public static CefPostdataelementType fromLong(long v) {
        for (CefPostdataelementType e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
