// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Specifies the button display state. */
public enum CefButtonState {
    CEF_BUTTON_STATE_NORMAL(0L),
    CEF_BUTTON_STATE_HOVERED(1L),
    CEF_BUTTON_STATE_PRESSED(2L),
    CEF_BUTTON_STATE_DISABLED(3L),
    CEF_BUTTON_STATE_NUM_VALUES(4L),
    UNKNOWN(-1L);

    public final long value;

    CefButtonState(long v) {
        this.value = v;
    }

    public static CefButtonState fromLong(long v) {
        for (CefButtonState e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
