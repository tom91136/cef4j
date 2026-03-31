// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Cookie same site values. */
public enum CefCookieSameSite {
    CEF_COOKIE_SAME_SITE_UNSPECIFIED(0L),
    CEF_COOKIE_SAME_SITE_NO_RESTRICTION(1L),
    CEF_COOKIE_SAME_SITE_LAX_MODE(2L),
    CEF_COOKIE_SAME_SITE_STRICT_MODE(3L),
    CEF_COOKIE_SAME_SITE_NUM_VALUES(4L),
    UNKNOWN(-1L);

    public final long value;

    CefCookieSameSite(long v) {
        this.value = v;
    }

    public static CefCookieSameSite fromLong(long v) {
        for (CefCookieSameSite e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
