// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Supported event bit flags. */
public enum CefEventFlags {
    EVENTFLAG_NONE(0L),
    EVENTFLAG_CAPS_LOCK_ON(1L),
    EVENTFLAG_SHIFT_DOWN(2L),
    EVENTFLAG_CONTROL_DOWN(4L),
    EVENTFLAG_ALT_DOWN(8L),
    EVENTFLAG_LEFT_MOUSE_BUTTON(16L),
    EVENTFLAG_MIDDLE_MOUSE_BUTTON(32L),
    EVENTFLAG_RIGHT_MOUSE_BUTTON(64L),
    /** Mac OS-X command key. */
    EVENTFLAG_COMMAND_DOWN(128L),
    EVENTFLAG_NUM_LOCK_ON(256L),
    EVENTFLAG_IS_KEY_PAD(512L),
    EVENTFLAG_IS_LEFT(1024L),
    EVENTFLAG_IS_RIGHT(2048L),
    EVENTFLAG_ALTGR_DOWN(4096L),
    EVENTFLAG_IS_REPEAT(8192L),
    EVENTFLAG_PRECISION_SCROLLING_DELTA(16384L),
    EVENTFLAG_SCROLL_BY_PAGE(32768L),
    UNKNOWN(-1L);

    public final long value;

    CefEventFlags(long v) {
        this.value = v;
    }

    public static CefEventFlags fromLong(long v) {
        for (CefEventFlags e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
