// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** DOM event category flags. */
public enum CefDomEventCategory {
    DOM_EVENT_CATEGORY_UNKNOWN(0L),
    DOM_EVENT_CATEGORY_UI(1L),
    DOM_EVENT_CATEGORY_MOUSE(2L),
    DOM_EVENT_CATEGORY_MUTATION(4L),
    DOM_EVENT_CATEGORY_KEYBOARD(8L),
    DOM_EVENT_CATEGORY_TEXT(16L),
    DOM_EVENT_CATEGORY_COMPOSITION(32L),
    DOM_EVENT_CATEGORY_DRAG(64L),
    DOM_EVENT_CATEGORY_CLIPBOARD(128L),
    DOM_EVENT_CATEGORY_MESSAGE(256L),
    DOM_EVENT_CATEGORY_WHEEL(512L),
    DOM_EVENT_CATEGORY_BEFORE_TEXT_INSERTED(1024L),
    DOM_EVENT_CATEGORY_OVERFLOW(2048L),
    DOM_EVENT_CATEGORY_PAGE_TRANSITION(4096L),
    DOM_EVENT_CATEGORY_POPSTATE(8192L),
    DOM_EVENT_CATEGORY_PROGRESS(16384L),
    DOM_EVENT_CATEGORY_XMLHTTPREQUEST_PROGRESS(32768L),
    UNKNOWN(-1L);

    public final long value;

    CefDomEventCategory(long v) {
        this.value = v;
    }

    public static CefDomEventCategory fromLong(long v) {
        for (CefDomEventCategory e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
