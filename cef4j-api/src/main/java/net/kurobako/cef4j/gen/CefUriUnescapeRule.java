// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;

/**
 * URI unescape rules passed to CefURIDecode().
 * <p>Definition generated from cef_types.h
 * <pre>typedef enum {
 *   UU_NONE = 0,
 *   UU_NORMAL = 1 &lt;&lt; 0,
 *   UU_SPACES = 1 &lt;&lt; 1,
 *   UU_PATH_SEPARATORS = 1 &lt;&lt; 2,
 *   UU_URL_SPECIAL_CHARS_EXCEPT_PATH_SEPARATORS = 1 &lt;&lt; 3,
 *   ...
 * } cef_uri_unescape_rule_t;</pre>
 * <p>Possible values: {@link Kind#NONE}, {@link Kind#NORMAL}, {@link Kind#SPACES}, {@link Kind#PATH_SEPARATORS}, {@link Kind#URL_SPECIAL_CHARS_EXCEPT_PATH_SEPARATORS}, {@link Kind#REPLACE_PLUS_WITH_SPACE}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__types_8h.html">cef_types.h</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public final class CefUriUnescapeRule implements CefEnum<CefUriUnescapeRule> {

    /** Known constants for {@link CefUriUnescapeRule}. */
    public enum Kind {
        /** Don't unescape anything at all.  */
        NONE(0, "0", "UU_NONE"),
        /** Don't unescape anything special, but all normal unescaping will happen. This is a placeholder and can't be combined with other flags (since it's just the absence of them). All other unescape rules imply "normal" in addition to their special meaning. Things like escaped letters, digits, and most symbols will get unescaped with this mode.  */
        NORMAL(1 << 0, "1 << 0", "UU_NORMAL"),
        /** Convert %20 to spaces. In some places where we're showing URLs, we may want this. In places where the URL may be copied and pasted out, then you wouldn't want this since it might not be interpreted in one piece by other applications.  */
        SPACES(1 << 1, "1 << 1", "UU_SPACES"),
        /** Unescapes '/' and '\\'. If these characters were unescaped, the resulting URL won't be the same as the source one. Moreover, they are dangerous to unescape in strings that will be used as file paths or names. This value should only be used when slashes don't have special meaning, like data URLs.  */
        PATH_SEPARATORS(1 << 2, "1 << 2", "UU_PATH_SEPARATORS"),
        /** Unescapes various characters that will change the meaning of URLs, including '%', '+', '&amp;', '#'. Does not unescape path separators. If these characters were unescaped, the resulting URL won't be the same as the source one. This flag is used when generating final output like filenames for URLs where we won't be interpreting as a URL and want to do as much unescaping as possible.  */
        URL_SPECIAL_CHARS_EXCEPT_PATH_SEPARATORS(1 << 3, "1 << 3", "UU_URL_SPECIAL_CHARS_EXCEPT_PATH_SEPARATORS"),
        /** URL queries use "+" for space. This flag controls that replacement.  */
        REPLACE_PLUS_WITH_SPACE(1 << 4, "1 << 4", "UU_REPLACE_PLUS_WITH_SPACE");

        private static final Kind[] VALUES = Kind.values();

        /** The underlying C enum numeric value. */
        public final long value;

        /** The original C expression/literal (e.g. {@code "1 << 3"}), or the numeric string. */
        public final String expr;

        /** The C constant name (e.g., {@code "cef_uri_unescape_rule_t"}). */
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

    private CefUriUnescapeRule(long value) {
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
     * Returns the {@link Kind} matching this value, or empty for unknown/composite values.
     * Use this for exhaustive switch over known constants.
     */
    public java.util.Optional<Kind> kind() {
        for (Kind k : Kind.VALUES) {
            if (k.value == value) return java.util.Optional.of(k);
        }
        return java.util.Optional.empty();
    }

    /** Returns an instance for the given raw value, use {@link #kind} to resolve to a concrete enum. */
    public static CefUriUnescapeRule of(long v) {
        return new CefUriUnescapeRule(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefUriUnescapeRule of(Kind k) {
        return new CefUriUnescapeRule(k.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefUriUnescapeRule)) return false;
        return this.value == ((CefUriUnescapeRule) obj).value;
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
