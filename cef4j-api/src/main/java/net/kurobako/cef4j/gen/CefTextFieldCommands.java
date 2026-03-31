// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Represents commands available to TextField. Should be kept in sync with Chromium's views::TextField::MenuCommands
 * type.
 */
public enum CefTextFieldCommands {
    CEF_TFC_UNKNOWN(0L),
    CEF_TFC_CUT(1L),
    CEF_TFC_COPY(2L),
    CEF_TFC_PASTE(3L),
    CEF_TFC_SELECT_ALL(4L),
    CEF_TFC_SELECT_WORD(5L),
    CEF_TFC_UNDO(6L),
    CEF_TFC_DELETE(7L),
    CEF_TFC_NUM_VALUES(8L),
    UNKNOWN(-1L);

    public final long value;

    CefTextFieldCommands(long v) {
        this.value = v;
    }

    public static CefTextFieldCommands fromLong(long v) {
        for (CefTextFieldCommands e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
