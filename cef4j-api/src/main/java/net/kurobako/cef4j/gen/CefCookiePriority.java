// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Cookie priority values. */
public enum CefCookiePriority {
    CEF_COOKIE_PRIORITY_LOW(-1L),
    CEF_COOKIE_PRIORITY_MEDIUM(0L),
    CEF_COOKIE_PRIORITY_HIGH(1L),
    UNKNOWN(-1L);

    public final long value;

    CefCookiePriority(long v) {
        this.value = v;
    }

    public static CefCookiePriority fromLong(long v) {
        for (CefCookiePriority e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
