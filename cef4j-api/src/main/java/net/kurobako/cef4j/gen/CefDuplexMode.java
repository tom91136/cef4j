// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Print job duplex mode values. */
public enum CefDuplexMode {
    DUPLEX_MODE_UNKNOWN(-1L),
    DUPLEX_MODE_SIMPLEX(0L),
    DUPLEX_MODE_LONG_EDGE(1L),
    DUPLEX_MODE_SHORT_EDGE(2L),
    DUPLEX_MODE_NUM_VALUES(3L),
    UNKNOWN(-1L);

    public final long value;

    CefDuplexMode(long v) {
        this.value = v;
    }

    public static CefDuplexMode fromLong(long v) {
        for (CefDuplexMode e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
