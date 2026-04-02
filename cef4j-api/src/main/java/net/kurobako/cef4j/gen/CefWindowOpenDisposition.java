// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * The manner in which a link click should be opened. These constants match their equivalents in Chromium's
 * window_open_disposition.h and should not be renumbered.
 *
 * <p>Definition generated from cef_types.h
 *
 * <pre>typedef enum {
 *   CEF_WOD_UNKNOWN = 0,
 *   CEF_WOD_CURRENT_TAB = 1,
 *   CEF_WOD_SINGLETON_TAB = 2,
 *   CEF_WOD_NEW_FOREGROUND_TAB = 3,
 *   CEF_WOD_NEW_BACKGROUND_TAB = 4,
 *   ...
 * } cef_window_open_disposition_t;</pre>
 *
 * <p>Possible values: {@link Kind#UNKNOWN}, {@link Kind#CURRENT_TAB}, {@link Kind#SINGLETON_TAB},
 * {@link Kind#NEW_FOREGROUND_TAB}, {@link Kind#NEW_BACKGROUND_TAB}, {@link Kind#NEW_POPUP}, {@link Kind#NEW_WINDOW},
 * {@link Kind#SAVE_TO_DISK}, {@link Kind#OFF_THE_RECORD}, {@link Kind#IGNORE_ACTION}, {@link Kind#SWITCH_TO_TAB},
 * {@link Kind#NEW_PICTURE_IN_PICTURE}, {@link Kind#NUM_VALUES}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types_8h.html">cef_types.h</a>
 */
public final class CefWindowOpenDisposition implements CefEnum<CefWindowOpenDisposition> {

    /** Known constants for {@link CefWindowOpenDisposition}. */
    public enum Kind {
        UNKNOWN(0, "0", "CEF_WOD_UNKNOWN"),
        /** Current tab. This is the default in most cases. */
        CURRENT_TAB(1, "1", "CEF_WOD_CURRENT_TAB"),
        /** Indicates that only one tab with the url should exist in the same window. */
        SINGLETON_TAB(2, "2", "CEF_WOD_SINGLETON_TAB"),
        /** Shift key + Middle mouse button or meta/ctrl key while clicking. */
        NEW_FOREGROUND_TAB(3, "3", "CEF_WOD_NEW_FOREGROUND_TAB"),
        /** Middle mouse button or meta/ctrl key while clicking. */
        NEW_BACKGROUND_TAB(4, "4", "CEF_WOD_NEW_BACKGROUND_TAB"),
        /** New popup window. */
        NEW_POPUP(5, "5", "CEF_WOD_NEW_POPUP"),
        /** Shift key while clicking. */
        NEW_WINDOW(6, "6", "CEF_WOD_NEW_WINDOW"),
        /** Alt key while clicking. */
        SAVE_TO_DISK(7, "7", "CEF_WOD_SAVE_TO_DISK"),
        /** New off-the-record (incognito) window. */
        OFF_THE_RECORD(8, "8", "CEF_WOD_OFF_THE_RECORD"),
        /** Special case error condition from the renderer. */
        IGNORE_ACTION(9, "9", "CEF_WOD_IGNORE_ACTION"),
        /**
         * Activates an existing tab containing the url, rather than navigating. This is similar to SINGLETON_TAB, but
         * searches across all windows from the current profile and anonymity (instead of just the current one); closes
         * the current tab on switching if the current tab was the NTP with no session history; and behaves like
         * CURRENT_TAB instead of NEW_FOREGROUND_TAB when no existing tab is found.
         */
        SWITCH_TO_TAB(10, "10", "CEF_WOD_SWITCH_TO_TAB"),
        /** Creates a new document picture-in-picture window showing a child WebView. */
        NEW_PICTURE_IN_PICTURE(11, "11", "CEF_WOD_NEW_PICTURE_IN_PICTURE"),
        NUM_VALUES(12, "12", "CEF_WOD_NUM_VALUES");

        private static final Kind[] VALUES = Kind.values();

        /** The underlying C enum numeric value. */
        public final long value;

        /** The original C expression/literal (e.g. {@code "1 << 3"}), or the numeric string. */
        public final String expr;

        /** The C constant name (e.g., {@code "cef_window_open_disposition_t"}). */
        public final String name;

        Kind(long value, String expr, String name) {
            this.value = value;
            this.expr = expr;
            this.name = name;
        }

        @Override
        public String toString() {
            return name + "(expr=" + expr + ", value=" + value + ")";
        }
    }

    /** The underlying C enum numeric value. This may not correspond to any known {@link Kind}. */
    public final long value;

    private CefWindowOpenDisposition(long value) {
        this.value = value;
    }

    /** {@inheritDoc} */
    @Override
    public long value() {
        return value;
    }

    /** {@inheritDoc} */
    @Override
    public String expr() {
        return kind().map(k -> k.expr).orElse(String.valueOf(value));
    }

    /** {@inheritDoc} */
    @Override
    public String name() {
        return kind().map(k -> k.name).orElse("UNKNOWN(" + value + ")");
    }

    /**
     * Returns the {@link Kind} matching this value, or empty for unknown/composite values. Use this for exhaustive
     * switch over known constants.
     */
    public java.util.Optional<Kind> kind() {
        for (Kind k : Kind.VALUES) {
            if (k.value == value) return java.util.Optional.of(k);
        }
        return java.util.Optional.empty();
    }

    /** Returns an instance for the given raw value, use {@link #kind} to resolve to a concrete enum. */
    public static CefWindowOpenDisposition of(long v) {
        return new CefWindowOpenDisposition(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefWindowOpenDisposition of(Kind k) {
        return new CefWindowOpenDisposition(k.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefWindowOpenDisposition)) return false;
        return this.value == ((CefWindowOpenDisposition) obj).value;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(value);
    }

    @Override
    public String toString() {
        return kind().map(Kind::toString).orElse("UNKNOWN(value=" + value + ")");
    }
}
