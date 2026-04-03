// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
// -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;

/**
 * Transition type for a request. Made up of one source value and 0 or more qualifiers.
 *
 * <p>Definition generated from cef_types.h
 *
 * <pre>typedef enum {
 *   TT_LINK = 0,
 *   TT_EXPLICIT = 1,
 *   TT_AUTO_BOOKMARK = 2,
 *   TT_AUTO_SUBFRAME = 3,
 *   TT_MANUAL_SUBFRAME = 4,
 *   ...
 * } cef_transition_type_t;</pre>
 *
 * <p>Possible values: {@link Kind#LINK}, {@link Kind#EXPLICIT}, {@link Kind#AUTO_BOOKMARK}, {@link Kind#AUTO_SUBFRAME},
 * {@link Kind#MANUAL_SUBFRAME}, {@link Kind#GENERATED}, {@link Kind#AUTO_TOPLEVEL}, {@link Kind#FORM_SUBMIT},
 * {@link Kind#RELOAD}, {@link Kind#KEYWORD}, {@link Kind#KEYWORD_GENERATED}, {@link Kind#NUM_VALUES},
 * {@link Kind#SOURCE_MASK}, {@link Kind#BLOCKED_FLAG}, {@link Kind#FORWARD_BACK_FLAG}, {@link Kind#DIRECT_LOAD_FLAG},
 * {@link Kind#HOME_PAGE_FLAG}, {@link Kind#FROM_API_FLAG}, {@link Kind#CHAIN_START_FLAG}, {@link Kind#CHAIN_END_FLAG},
 * {@link Kind#CLIENT_REDIRECT_FLAG}, {@link Kind#SERVER_REDIRECT_FLAG}, {@link Kind#IS_REDIRECT_MASK},
 * {@link Kind#QUALIFIER_MASK}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types_8h.html">cef_types.h</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
public final class CefTransitionType implements CefEnum<CefTransitionType> {

    /** Known constants for {@link CefTransitionType}. */
    public enum Kind {
        /**
         * Source is a link click or the JavaScript window.open function. This is also the default value for requests
         * like sub-resource loads that are not navigations.
         */
        LINK(0, "0", "TT_LINK"),
        /**
         * Source is some other "explicit" navigation. This is the default value for navigations where the actual type
         * is unknown. See also TT_DIRECT_LOAD_FLAG.
         */
        EXPLICIT(1, "1", "TT_EXPLICIT"),
        /**
         * User got to this page through a suggestion in the UI (for example, via the destinations page). Chrome style
         * only.
         */
        AUTO_BOOKMARK(2, "2", "TT_AUTO_BOOKMARK"),
        /**
         * Source is a subframe navigation. This is any content that is automatically loaded in a non-toplevel frame.
         * For example, if a page consists of several frames containing ads, those ad URLs will have this transition
         * type. The user may not even realize the content in these pages is a separate frame, so may not care about the
         * URL.
         */
        AUTO_SUBFRAME(3, "3", "TT_AUTO_SUBFRAME"),
        /**
         * Source is a subframe navigation explicitly requested by the user that will generate new navigation entries in
         * the back/forward list. These are probably more important than frames that were automatically loaded in the
         * background because the user probably cares about the fact that this link was loaded.
         */
        MANUAL_SUBFRAME(4, "4", "TT_MANUAL_SUBFRAME"),
        /**
         * User got to this page by typing in the URL bar and selecting an entry that did not look like a URL. For
         * example, a match might have the URL of a Google search result page, but appear like "Search Google for ...".
         * These are not quite the same as EXPLICIT navigations because the user didn't type or see the destination URL.
         * Chrome style only. See also TT_KEYWORD.
         */
        GENERATED(5, "5", "TT_GENERATED"),
        /**
         * This is a toplevel navigation. This is any content that is automatically loaded in a toplevel frame. For
         * example, opening a tab to show the ASH screen saver, opening the devtools window, opening the NTP after the
         * safe browsing warning, opening web-based dialog boxes are examples of AUTO_TOPLEVEL navigations. Chrome style
         * only.
         */
        AUTO_TOPLEVEL(6, "6", "TT_AUTO_TOPLEVEL"),
        /**
         * Source is a form submission by the user. NOTE: In some situations submitting a form does not result in this
         * transition type. This can happen if the form uses a script to submit the contents.
         */
        FORM_SUBMIT(7, "7", "TT_FORM_SUBMIT"),
        /**
         * Source is a "reload" of the page via the Reload function or by re-visiting the same URL. NOTE: This is
         * distinct from the concept of whether a particular load uses "reload semantics" (i.e. bypasses cached data).
         */
        RELOAD(8, "8", "TT_RELOAD"),
        /**
         * The url was generated from a replaceable keyword other than the default search provider. If the user types a
         * keyword (which also applies to tab-to-search) in the omnibox this qualifier is applied to the transition type
         * of the generated url. TemplateURLModel then may generate an additional visit with a transition type of
         * TT_KEYWORD_GENERATED against the url 'http://' + keyword. For example, if you do a tab-to-search against
         * wikipedia the generated url has a transition qualifer of TT_KEYWORD, and TemplateURLModel generates a visit
         * for 'wikipedia.org' with a transition type of TT_KEYWORD_GENERATED. Chrome style only.
         */
        KEYWORD(9, "9", "TT_KEYWORD"),
        /**
         * Corresponds to a visit generated for a keyword. See description of TT_KEYWORD for more details. Chrome style
         * only.
         */
        KEYWORD_GENERATED(10, "10", "TT_KEYWORD_GENERATED"),
        NUM_VALUES(11, "11", "TT_NUM_VALUES"),
        /** General mask defining the bits used for the source values. */
        SOURCE_MASK(0xFF, "0xFF", "TT_SOURCE_MASK"),
        /**
         * Qualifiers. Any of the core values above can be augmented by one or more qualifiers. These qualifiers further
         * define the transition. Attempted to visit a URL but was blocked.
         */
        BLOCKED_FLAG(0x00800000, "0x00800000", "TT_BLOCKED_FLAG"),
        /**
         * Used the Forward or Back function to navigate among browsing history. Will be ORed to the transition type for
         * the original load.
         */
        FORWARD_BACK_FLAG(0x01000000, "0x01000000", "TT_FORWARD_BACK_FLAG"),
        /** Loaded a URL directly via CreateBrowser, LoadURL or LoadRequest. */
        DIRECT_LOAD_FLAG(0x02000000, "0x02000000", "TT_DIRECT_LOAD_FLAG"),
        /** User is navigating to the home page. Chrome style only. */
        HOME_PAGE_FLAG(0x04000000, "0x04000000", "TT_HOME_PAGE_FLAG"),
        /**
         * The transition originated from an external application; the exact definition of this is embedder dependent.
         * Chrome style only.
         */
        FROM_API_FLAG(0x08000000, "0x08000000", "TT_FROM_API_FLAG"),
        /** The beginning of a navigation chain. */
        CHAIN_START_FLAG(0x10000000, "0x10000000", "TT_CHAIN_START_FLAG"),
        /** The last transition in a redirect chain. */
        CHAIN_END_FLAG(0x20000000, "0x20000000", "TT_CHAIN_END_FLAG"),
        /** Redirects caused by JavaScript or a meta refresh tag on the page. */
        CLIENT_REDIRECT_FLAG(0x40000000, "0x40000000", "TT_CLIENT_REDIRECT_FLAG"),
        /** Redirects sent from the server by HTTP headers. */
        SERVER_REDIRECT_FLAG(0x80000000, "0x80000000", "TT_SERVER_REDIRECT_FLAG"),
        /** Used to test whether a transition involves a redirect. */
        IS_REDIRECT_MASK(0xC0000000, "0xC0000000", "TT_IS_REDIRECT_MASK"),
        /** General mask defining the bits used for the qualifiers. */
        QUALIFIER_MASK(0xFFFFFF00, "0xFFFFFF00", "TT_QUALIFIER_MASK");

        private static final Kind[] VALUES = Kind.values();

        /** The underlying C enum numeric value. */
        public final long value;

        /** The original C expression/literal (e.g. {@code "1 << 3"}), or the numeric string. */
        public final String expr;

        /** The C constant name (e.g., {@code "cef_transition_type_t"}). */
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

    private CefTransitionType(long value) {
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
    public static CefTransitionType of(long v) {
        return new CefTransitionType(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefTransitionType of(Kind k) {
        return new CefTransitionType(k.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefTransitionType)) return false;
        return this.value == ((CefTransitionType) obj).value;
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
