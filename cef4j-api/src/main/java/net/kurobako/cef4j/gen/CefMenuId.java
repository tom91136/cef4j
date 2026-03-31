// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Supported menu IDs. Non-English translations can be provided for the IDS_MENU_* strings in
 * CefResourceBundleHandler::GetLocalizedString().
 */
public enum CefMenuId {
    MENU_ID_BACK(100L),
    MENU_ID_FORWARD(101L),
    MENU_ID_RELOAD(102L),
    MENU_ID_RELOAD_NOCACHE(103L),
    MENU_ID_STOPLOAD(104L),
    MENU_ID_UNDO(110L),
    MENU_ID_REDO(111L),
    MENU_ID_CUT(112L),
    MENU_ID_COPY(113L),
    MENU_ID_PASTE(114L),
    MENU_ID_PASTE_MATCH_STYLE(115L),
    MENU_ID_DELETE(116L),
    MENU_ID_SELECT_ALL(117L),
    MENU_ID_FIND(130L),
    MENU_ID_PRINT(131L),
    MENU_ID_VIEW_SOURCE(132L),
    MENU_ID_SPELLCHECK_SUGGESTION_0(200L),
    MENU_ID_SPELLCHECK_SUGGESTION_1(201L),
    MENU_ID_SPELLCHECK_SUGGESTION_2(202L),
    MENU_ID_SPELLCHECK_SUGGESTION_3(203L),
    MENU_ID_SPELLCHECK_SUGGESTION_4(204L),
    MENU_ID_SPELLCHECK_SUGGESTION_LAST(204L),
    MENU_ID_NO_SPELLING_SUGGESTIONS(205L),
    MENU_ID_ADD_TO_DICTIONARY(206L),
    MENU_ID_CUSTOM_FIRST(220L),
    MENU_ID_CUSTOM_LAST(250L),
    MENU_ID_USER_FIRST(26500L),
    MENU_ID_USER_LAST(28500L),
    UNKNOWN(-1L);

    public final long value;

    CefMenuId(long v) {
        this.value = v;
    }

    public static CefMenuId fromLong(long v) {
        for (CefMenuId e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
