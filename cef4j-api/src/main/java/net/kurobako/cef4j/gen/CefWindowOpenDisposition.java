// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * The manner in which a link click should be opened. These constants match their equivalents in Chromium's
 * window_open_disposition.h and should not be renumbered.
 */
public enum CefWindowOpenDisposition {
    CEF_WOD_UNKNOWN(0L),
    /** Current tab. This is the default in most cases. */
    CEF_WOD_CURRENT_TAB(1L),
    /** Indicates that only one tab with the url should exist in the same window. */
    CEF_WOD_SINGLETON_TAB(2L),
    /** Shift key + Middle mouse button or meta/ctrl key while clicking. */
    CEF_WOD_NEW_FOREGROUND_TAB(3L),
    /** Middle mouse button or meta/ctrl key while clicking. */
    CEF_WOD_NEW_BACKGROUND_TAB(4L),
    /** New popup window. */
    CEF_WOD_NEW_POPUP(5L),
    /** Shift key while clicking. */
    CEF_WOD_NEW_WINDOW(6L),
    /** Alt key while clicking. */
    CEF_WOD_SAVE_TO_DISK(7L),
    /** New off-the-record (incognito) window. */
    CEF_WOD_OFF_THE_RECORD(8L),
    /** Special case error condition from the renderer. */
    CEF_WOD_IGNORE_ACTION(9L),
    /**
     * Activates an existing tab containing the url, rather than navigating. This is similar to SINGLETON_TAB, but
     * searches across all windows from the current profile and anonymity (instead of just the current one); closes the
     * current tab on switching if the current tab was the NTP with no session history; and behaves like CURRENT_TAB
     * instead of NEW_FOREGROUND_TAB when no existing tab is found.
     */
    CEF_WOD_SWITCH_TO_TAB(10L),
    /** Creates a new document picture-in-picture window showing a child WebView. */
    CEF_WOD_NEW_PICTURE_IN_PICTURE(11L),
    CEF_WOD_NUM_VALUES(12L),
    UNKNOWN(-1L);

    public final long value;

    CefWindowOpenDisposition(long v) {
        this.value = v;
    }

    public static CefWindowOpenDisposition fromLong(long v) {
        for (CefWindowOpenDisposition e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
