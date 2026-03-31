// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Key event types. */
public enum CefKeyEventType {

    /** Notification that a key transitioned from "up" to "down". */
    KEYEVENT_RAWKEYDOWN(0L),
    /**
     * Notification that a key was pressed. This does not necessarily correspond to a character depending on the key and
     * language. Use KEYEVENT_CHAR for character input.
     */
    KEYEVENT_KEYDOWN(1L),
    /** Notification that a key was released. */
    KEYEVENT_KEYUP(2L),
    /**
     * Notification that a character was typed. Use this for text input. Key down events may generate 0, 1, or more than
     * one character event depending on the key, locale, and operating system.
     */
    KEYEVENT_CHAR(3L),
    UNKNOWN(-1L);

    public final long value;

    CefKeyEventType(long v) {
        this.value = v;
    }

    public static CefKeyEventType fromLong(long v) {
        for (CefKeyEventType e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
