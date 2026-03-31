// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Specifies the gesture commands. */
public enum CefGestureCommand {
    CEF_GESTURE_COMMAND_BACK(0L),
    CEF_GESTURE_COMMAND_FORWARD(1L),
    UNKNOWN(-1L);

    public final long value;

    CefGestureCommand(long v) {
        this.value = v;
    }

    public static CefGestureCommand fromLong(long v) {
        for (CefGestureCommand e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
