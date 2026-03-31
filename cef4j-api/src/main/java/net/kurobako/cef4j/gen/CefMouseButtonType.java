// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Mouse button types. */
public enum CefMouseButtonType {
    MBT_LEFT(0L),
    MBT_MIDDLE(1L),
    MBT_RIGHT(2L),
    UNKNOWN(-1L);

    public final long value;

    CefMouseButtonType(long v) {
        this.value = v;
    }

    public static CefMouseButtonType fromLong(long v) {
        for (CefMouseButtonType e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
