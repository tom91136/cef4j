// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Storage types. */
public enum CefStorageType {
    ST_LOCALSTORAGE(0L),
    ST_SESSIONSTORAGE(1L),
    UNKNOWN(-1L);

    public final long value;

    CefStorageType(long v) {
        this.value = v;
    }

    public static CefStorageType fromLong(long v) {
        for (CefStorageType e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
