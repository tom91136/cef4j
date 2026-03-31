// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Show states supported by CefWindowDelegate::GetInitialShowState. */
public enum CefShowState {
    CEF_SHOW_STATE_NORMAL(0L),
    CEF_SHOW_STATE_MINIMIZED(1L),
    CEF_SHOW_STATE_MAXIMIZED(2L),
    CEF_SHOW_STATE_FULLSCREEN(3L),
    CEF_SHOW_STATE_HIDDEN(4L),
    CEF_SHOW_STATE_NUM_VALUES(5L),
    UNKNOWN(-1L);

    public final long value;

    CefShowState(long v) {
        this.value = v;
    }

    public static CefShowState fromLong(long v) {
        for (CefShowState e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
