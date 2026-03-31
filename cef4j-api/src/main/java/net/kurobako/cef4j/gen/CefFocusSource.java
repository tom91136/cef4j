// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Focus sources. */
public enum CefFocusSource {

    /** The source is explicit navigation via the API (LoadURL(), etc). */
    FOCUS_SOURCE_NAVIGATION(0L),
    /** The source is a system-generated focus event. */
    FOCUS_SOURCE_SYSTEM(1L),
    FOCUS_SOURCE_NUM_VALUES(2L),
    UNKNOWN(-1L);

    public final long value;

    CefFocusSource(long v) {
        this.value = v;
    }

    public static CefFocusSource fromLong(long v) {
        for (CefFocusSource e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
