// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** URI unescape rules passed to CefURIDecode(). */
public enum CefUriUnescapeRule {

    /** Don't unescape anything at all. */
    UU_NONE(0L),
    /**
     * Don't unescape anything special, but all normal unescaping will happen. This is a placeholder and can't be
     * combined with other flags (since it's just the absence of them). All other unescape rules imply "normal" in
     * addition to their special meaning. Things like escaped letters, digits, and most symbols will get unescaped with
     * this mode.
     */
    UU_NORMAL(1L),
    /**
     * Convert %20 to spaces. In some places where we're showing URLs, we may want this. In places where the URL may be
     * copied and pasted out, then you wouldn't want this since it might not be interpreted in one piece by other
     * applications.
     */
    UU_SPACES(2L),
    /**
     * Unescapes '/' and '\\'. If these characters were unescaped, the resulting URL won't be the same as the source
     * one. Moreover, they are dangerous to unescape in strings that will be used as file paths or names. This value
     * should only be used when slashes don't have special meaning, like data URLs.
     */
    UU_PATH_SEPARATORS(4L),
    /**
     * Unescapes various characters that will change the meaning of URLs, including '%', '+', '&', '#'. Does not
     * unescape path separators. If these characters were unescaped, the resulting URL won't be the same as the source
     * one. This flag is used when generating final output like filenames for URLs where we won't be interpreting as a
     * URL and want to do as much unescaping as possible.
     */
    UU_URL_SPECIAL_CHARS_EXCEPT_PATH_SEPARATORS(8L),
    /** URL queries use "+" for space. This flag controls that replacement. */
    UU_REPLACE_PLUS_WITH_SPACE(16L),
    UNKNOWN(-1L);

    public final long value;

    CefUriUnescapeRule(long v) {
        this.value = v;
    }

    public static CefUriUnescapeRule fromLong(long v) {
        for (CefUriUnescapeRule e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
