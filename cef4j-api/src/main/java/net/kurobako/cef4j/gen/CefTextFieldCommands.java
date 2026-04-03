// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
// -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;

/**
 * Represents commands available to TextField. Should be kept in sync with Chromium's views::TextField::MenuCommands
 * type.
 *
 * <p>Definition generated from cef_types.h
 *
 * <pre>typedef enum {
 *   CEF_TFC_UNKNOWN = 0,
 *   CEF_TFC_CUT = 1,
 *   CEF_TFC_COPY = 2,
 *   CEF_TFC_PASTE = 3,
 *   CEF_TFC_SELECT_ALL = 4,
 *   ...
 * } cef_text_field_commands_t;</pre>
 *
 * <p>Possible values: {@link Kind#UNKNOWN}, {@link Kind#CUT}, {@link Kind#COPY}, {@link Kind#PASTE},
 * {@link Kind#SELECT_ALL}, {@link Kind#SELECT_WORD}, {@link Kind#UNDO}, {@link Kind#DELETE}, {@link Kind#NUM_VALUES}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types_8h.html">cef_types.h</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
public final class CefTextFieldCommands implements CefEnum<CefTextFieldCommands> {

    /** Known constants for {@link CefTextFieldCommands}. */
    public enum Kind {
        UNKNOWN(0, "0", "CEF_TFC_UNKNOWN"),
        CUT(1, "1", "CEF_TFC_CUT"),
        COPY(2, "2", "CEF_TFC_COPY"),
        PASTE(3, "3", "CEF_TFC_PASTE"),
        SELECT_ALL(4, "4", "CEF_TFC_SELECT_ALL"),
        SELECT_WORD(5, "5", "CEF_TFC_SELECT_WORD"),
        UNDO(6, "6", "CEF_TFC_UNDO"),
        DELETE(7, "7", "CEF_TFC_DELETE"),
        NUM_VALUES(8, "8", "CEF_TFC_NUM_VALUES");

        private static final Kind[] VALUES = Kind.values();

        /** The underlying C enum numeric value. */
        public final long value;

        /** The original C expression/literal (e.g. {@code "1 << 3"}), or the numeric string. */
        public final String expr;

        /** The C constant name (e.g., {@code "cef_text_field_commands_t"}). */
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

    private CefTextFieldCommands(long value) {
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
    public static CefTextFieldCommands of(long v) {
        return new CefTextFieldCommands(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefTextFieldCommands of(Kind k) {
        return new CefTextFieldCommands(k.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefTextFieldCommands)) return false;
        return this.value == ((CefTextFieldCommands) obj).value;
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
