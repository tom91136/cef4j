// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Navigation types. */
public enum CefNavigationType {
    NAVIGATION_LINK_CLICKED(0L),
    NAVIGATION_FORM_SUBMITTED(1L),
    NAVIGATION_BACK_FORWARD(2L),
    NAVIGATION_RELOAD(3L),
    NAVIGATION_FORM_RESUBMITTED(4L),
    NAVIGATION_OTHER(5L),
    NAVIGATION_NUM_VALUES(6L),
    UNKNOWN(-1L);

    public final long value;

    CefNavigationType(long v) {
        this.value = v;
    }

    public static CefNavigationType fromLong(long v) {
        for (CefNavigationType e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
