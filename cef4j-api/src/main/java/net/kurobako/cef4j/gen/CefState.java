// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Represents the state of a setting. */
public enum CefState {

    /** Use the default state for the setting. */
    STATE_DEFAULT(0L),
    /** Enable or allow the setting. */
    STATE_ENABLED(1L),
    /** Disable or disallow the setting. */
    STATE_DISABLED(2L),
    UNKNOWN(-1L);

    public final long value;

    CefState(long v) {
        this.value = v;
    }

    public static CefState fromLong(long v) {
        for (CefState e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
