// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
// -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;

/**
 * Supported context menu type flags.
 *
 * <p>Definition generated from cef_types.h
 *
 * <pre>typedef enum {
 *   CM_TYPEFLAG_NONE = 0,
 *   CM_TYPEFLAG_PAGE = 1 &lt;&lt; 0,
 *   CM_TYPEFLAG_FRAME = 1 &lt;&lt; 1,
 *   CM_TYPEFLAG_LINK = 1 &lt;&lt; 2,
 *   CM_TYPEFLAG_MEDIA = 1 &lt;&lt; 3,
 *   ...
 * } cef_context_menu_type_flags_t;</pre>
 *
 * <p>Possible values: {@link Kind#NONE}, {@link Kind#PAGE}, {@link Kind#FRAME}, {@link Kind#LINK}, {@link Kind#MEDIA},
 * {@link Kind#SELECTION}, {@link Kind#EDITABLE}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types_8h.html">cef_types.h</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
public final class CefContextMenuTypeFlags implements CefEnum<CefContextMenuTypeFlags> {

    /** Known constants for {@link CefContextMenuTypeFlags}. */
    public enum Kind {
        /** No node is selected. */
        NONE(0, "0", "CM_TYPEFLAG_NONE"),
        /** The top page is selected. */
        PAGE(1 << 0, "1 << 0", "CM_TYPEFLAG_PAGE"),
        /** A subframe page is selected. */
        FRAME(1 << 1, "1 << 1", "CM_TYPEFLAG_FRAME"),
        /** A link is selected. */
        LINK(1 << 2, "1 << 2", "CM_TYPEFLAG_LINK"),
        /** A media node is selected. */
        MEDIA(1 << 3, "1 << 3", "CM_TYPEFLAG_MEDIA"),
        /** There is a textual or mixed selection that is selected. */
        SELECTION(1 << 4, "1 << 4", "CM_TYPEFLAG_SELECTION"),
        /** An editable element is selected. */
        EDITABLE(1 << 5, "1 << 5", "CM_TYPEFLAG_EDITABLE");

        private static final Kind[] VALUES = Kind.values();

        /** The underlying C enum numeric value. */
        public final long value;

        /** The original C expression/literal (e.g. {@code "1 << 3"}), or the numeric string. */
        public final String expr;

        /** The C constant name (e.g., {@code "cef_context_menu_type_flags_t"}). */
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

    private CefContextMenuTypeFlags(long value) {
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
    public static CefContextMenuTypeFlags of(long v) {
        return new CefContextMenuTypeFlags(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefContextMenuTypeFlags of(Kind k) {
        return new CefContextMenuTypeFlags(k.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefContextMenuTypeFlags)) return false;
        return this.value == ((CefContextMenuTypeFlags) obj).value;
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
