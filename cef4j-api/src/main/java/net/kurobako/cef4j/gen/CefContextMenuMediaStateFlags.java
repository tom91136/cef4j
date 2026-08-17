// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;

/**
 * Supported context menu media state bit flags. These constants match their equivalents in Chromium's ContextMenuData::MediaFlags and should not be renumbered.
 * <p>Definition generated from cef_types.h
 * <pre>typedef enum {
 *   CM_MEDIAFLAG_NONE = 0,
 *   CM_MEDIAFLAG_IN_ERROR = 1 &lt;&lt; 0,
 *   CM_MEDIAFLAG_PAUSED = 1 &lt;&lt; 1,
 *   CM_MEDIAFLAG_MUTED = 1 &lt;&lt; 2,
 *   CM_MEDIAFLAG_LOOP = 1 &lt;&lt; 3,
 *   ...
 * } cef_context_menu_media_state_flags_t;</pre>
 * <p>Possible values: {@link Kind#NONE}, {@link Kind#IN_ERROR}, {@link Kind#PAUSED}, {@link Kind#MUTED}, {@link Kind#LOOP}, {@link Kind#CAN_SAVE}, {@link Kind#HAS_AUDIO}, {@link Kind#CAN_TOGGLE_CONTROLS}, {@link Kind#CONTROLS}, {@link Kind#CAN_PRINT}, {@link Kind#CAN_ROTATE}, {@link Kind#CAN_PICTURE_IN_PICTURE}, {@link Kind#PICTURE_IN_PICTURE}, {@link Kind#CAN_LOOP}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__types_8h.html">cef_types.h</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public final class CefContextMenuMediaStateFlags implements CefEnum<CefContextMenuMediaStateFlags> {

    /** Known constants for {@link CefContextMenuMediaStateFlags}. */
    public enum Kind {
        NONE(0, "0", "CM_MEDIAFLAG_NONE"),
        IN_ERROR(1 << 0, "1 << 0", "CM_MEDIAFLAG_IN_ERROR"),
        PAUSED(1 << 1, "1 << 1", "CM_MEDIAFLAG_PAUSED"),
        MUTED(1 << 2, "1 << 2", "CM_MEDIAFLAG_MUTED"),
        LOOP(1 << 3, "1 << 3", "CM_MEDIAFLAG_LOOP"),
        CAN_SAVE(1 << 4, "1 << 4", "CM_MEDIAFLAG_CAN_SAVE"),
        HAS_AUDIO(1 << 5, "1 << 5", "CM_MEDIAFLAG_HAS_AUDIO"),
        CAN_TOGGLE_CONTROLS(1 << 6, "1 << 6", "CM_MEDIAFLAG_CAN_TOGGLE_CONTROLS"),
        CONTROLS(1 << 7, "1 << 7", "CM_MEDIAFLAG_CONTROLS"),
        CAN_PRINT(1 << 8, "1 << 8", "CM_MEDIAFLAG_CAN_PRINT"),
        CAN_ROTATE(1 << 9, "1 << 9", "CM_MEDIAFLAG_CAN_ROTATE"),
        CAN_PICTURE_IN_PICTURE(1 << 10, "1 << 10", "CM_MEDIAFLAG_CAN_PICTURE_IN_PICTURE"),
        PICTURE_IN_PICTURE(1 << 11, "1 << 11", "CM_MEDIAFLAG_PICTURE_IN_PICTURE"),
        CAN_LOOP(1 << 12, "1 << 12", "CM_MEDIAFLAG_CAN_LOOP");

        private static final Kind[] VALUES = Kind.values();

        /** The underlying C enum numeric value. */
        public final long value;

        /** The original C expression/literal (e.g. {@code "1 << 3"}), or the numeric string. */
        public final String expr;

        /** The C constant name (e.g., {@code "cef_context_menu_media_state_flags_t"}). */
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

    private CefContextMenuMediaStateFlags(long value) {
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
    public static CefContextMenuMediaStateFlags of(long v) {
        return new CefContextMenuMediaStateFlags(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefContextMenuMediaStateFlags of(Kind k) {
        return new CefContextMenuMediaStateFlags(k.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefContextMenuMediaStateFlags)) return false;
        return this.value == ((CefContextMenuMediaStateFlags) obj).value;
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
