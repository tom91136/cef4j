// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

public enum CefSchemeOptions {
    CEF_SCHEME_OPTION_NONE(0L),
    CEF_SCHEME_OPTION_STANDARD(1L),
    CEF_SCHEME_OPTION_LOCAL(2L),
    CEF_SCHEME_OPTION_DISPLAY_ISOLATED(4L),
    CEF_SCHEME_OPTION_SECURE(8L),
    CEF_SCHEME_OPTION_CORS_ENABLED(16L),
    CEF_SCHEME_OPTION_CSP_BYPASSING(32L),
    CEF_SCHEME_OPTION_FETCH_ENABLED(64L),
    UNKNOWN(-1L);

    public final long value;

    CefSchemeOptions(long v) {
        this.value = v;
    }

    public static CefSchemeOptions fromLong(long v) {
        for (CefSchemeOptions e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
