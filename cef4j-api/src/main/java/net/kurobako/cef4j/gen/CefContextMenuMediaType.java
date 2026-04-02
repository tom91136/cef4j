// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Supported context menu media types. These constants match their equivalents in Chromium's ContextMenuDataMediaType
 * and should not be renumbered.
 *
 * <p>Definition generated from cef_types.h
 *
 * <pre>typedef enum {
 *   CM_MEDIATYPE_NONE = 0,
 *   CM_MEDIATYPE_IMAGE = 1,
 *   CM_MEDIATYPE_VIDEO = 2,
 *   CM_MEDIATYPE_AUDIO = 3,
 *   CM_MEDIATYPE_CANVAS = 4,
 *   ...
 * } cef_context_menu_media_type_t;</pre>
 *
 * <p>Possible values: {@link Kind#NONE}, {@link Kind#IMAGE}, {@link Kind#VIDEO}, {@link Kind#AUDIO},
 * {@link Kind#CANVAS}, {@link Kind#FILE}, {@link Kind#PLUGIN}, {@link Kind#NUM_VALUES}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types_8h.html">cef_types.h</a>
 */
public final class CefContextMenuMediaType implements CefEnum<CefContextMenuMediaType> {

    /** Known constants for {@link CefContextMenuMediaType}. */
    public enum Kind {
        /** No special node is in context. */
        NONE(0, "0", "CM_MEDIATYPE_NONE"),
        /** An image node is selected. */
        IMAGE(1, "1", "CM_MEDIATYPE_IMAGE"),
        /** A video node is selected. */
        VIDEO(2, "2", "CM_MEDIATYPE_VIDEO"),
        /** An audio node is selected. */
        AUDIO(3, "3", "CM_MEDIATYPE_AUDIO"),
        /** An canvas node is selected. */
        CANVAS(4, "4", "CM_MEDIATYPE_CANVAS"),
        /** A file node is selected. */
        FILE(5, "5", "CM_MEDIATYPE_FILE"),
        /** A plugin node is selected. */
        PLUGIN(6, "6", "CM_MEDIATYPE_PLUGIN"),
        NUM_VALUES(7, "7", "CM_MEDIATYPE_NUM_VALUES");

        private static final Kind[] VALUES = Kind.values();

        /** The underlying C enum numeric value. */
        public final long value;

        /** The original C expression/literal (e.g. {@code "1 << 3"}), or the numeric string. */
        public final String expr;

        /** The C constant name (e.g., {@code "cef_context_menu_media_type_t"}). */
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

    private CefContextMenuMediaType(long value) {
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
    public static CefContextMenuMediaType of(long v) {
        return new CefContextMenuMediaType(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefContextMenuMediaType of(Kind k) {
        return new CefContextMenuMediaType(k.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefContextMenuMediaType)) return false;
        return this.value == ((CefContextMenuMediaType) obj).value;
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
