// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;

/**
 * Supported context menu edit state bit flags. These constants match their equivalents in Chromium's ContextMenuDataEditFlags and should not be renumbered.
 * <p>Definition generated from cef_types.h
 * <pre>typedef enum {
 *   CM_EDITFLAG_NONE = 0,
 *   CM_EDITFLAG_CAN_UNDO = 1 &lt;&lt; 0,
 *   CM_EDITFLAG_CAN_REDO = 1 &lt;&lt; 1,
 *   CM_EDITFLAG_CAN_CUT = 1 &lt;&lt; 2,
 *   CM_EDITFLAG_CAN_COPY = 1 &lt;&lt; 3,
 *   ...
 * } cef_context_menu_edit_state_flags_t;</pre>
 * <p>Possible values: {@link Kind#NONE}, {@link Kind#CAN_UNDO}, {@link Kind#CAN_REDO}, {@link Kind#CAN_CUT}, {@link Kind#CAN_COPY}, {@link Kind#CAN_PASTE}, {@link Kind#CAN_DELETE}, {@link Kind#CAN_SELECT_ALL}, {@link Kind#CAN_TRANSLATE}, {@link Kind#CAN_EDIT_RICHLY}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__types_8h.html">cef_types.h</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused", "NullableForbidden"})
public final class CefContextMenuEditStateFlags implements CefEnum<CefContextMenuEditStateFlags> {

    /** Known constants for {@link CefContextMenuEditStateFlags}. */
    public enum Kind {
        NONE(0, "0", "CM_EDITFLAG_NONE"),
        CAN_UNDO(1 << 0, "1 << 0", "CM_EDITFLAG_CAN_UNDO"),
        CAN_REDO(1 << 1, "1 << 1", "CM_EDITFLAG_CAN_REDO"),
        CAN_CUT(1 << 2, "1 << 2", "CM_EDITFLAG_CAN_CUT"),
        CAN_COPY(1 << 3, "1 << 3", "CM_EDITFLAG_CAN_COPY"),
        CAN_PASTE(1 << 4, "1 << 4", "CM_EDITFLAG_CAN_PASTE"),
        CAN_DELETE(1 << 5, "1 << 5", "CM_EDITFLAG_CAN_DELETE"),
        CAN_SELECT_ALL(1 << 6, "1 << 6", "CM_EDITFLAG_CAN_SELECT_ALL"),
        CAN_TRANSLATE(1 << 7, "1 << 7", "CM_EDITFLAG_CAN_TRANSLATE"),
        CAN_EDIT_RICHLY(1 << 8, "1 << 8", "CM_EDITFLAG_CAN_EDIT_RICHLY");

        private static final Kind[] VALUES = Kind.values();

        /** The underlying C enum numeric value. */
        public final long value;

        /** The original C expression/literal (e.g. {@code "1 << 3"}), or the numeric string. */
        public final String expr;

        /** The C constant name (e.g., {@code "cef_context_menu_edit_state_flags_t"}). */
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

    private CefContextMenuEditStateFlags(long value) {
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
    public static CefContextMenuEditStateFlags of(long v) {
        return new CefContextMenuEditStateFlags(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefContextMenuEditStateFlags of(Kind k) {
        return new CefContextMenuEditStateFlags(k.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefContextMenuEditStateFlags)) return false;
        return this.value == ((CefContextMenuEditStateFlags) obj).value;
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
