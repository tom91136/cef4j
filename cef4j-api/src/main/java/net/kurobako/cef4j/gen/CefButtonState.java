// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;

/**
 * Specifies the button display state.
 * <p>Definition generated from cef_types.h
 * <pre>typedef enum {
 *   CEF_BUTTON_STATE_NORMAL = 0,
 *   CEF_BUTTON_STATE_HOVERED = 1,
 *   CEF_BUTTON_STATE_PRESSED = 2,
 *   CEF_BUTTON_STATE_DISABLED = 3,
 *   CEF_BUTTON_STATE_NUM_VALUES = 4
 * } cef_button_state_t;</pre>
 * <p>Possible values: {@link Kind#NORMAL}, {@link Kind#HOVERED}, {@link Kind#PRESSED}, {@link Kind#DISABLED}, {@link Kind#NUM_VALUES}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__types_8h.html">cef_types.h</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public final class CefButtonState implements CefEnum<CefButtonState> {

    /** Known constants for {@link CefButtonState}. */
    public enum Kind {
        NORMAL(0, "0", "CEF_BUTTON_STATE_NORMAL"),
        HOVERED(1, "1", "CEF_BUTTON_STATE_HOVERED"),
        PRESSED(2, "2", "CEF_BUTTON_STATE_PRESSED"),
        DISABLED(3, "3", "CEF_BUTTON_STATE_DISABLED"),
        NUM_VALUES(4, "4", "CEF_BUTTON_STATE_NUM_VALUES");

        private static final Kind[] VALUES = Kind.values();

        /** The underlying C enum numeric value. */
        public final long value;

        /** The original C expression/literal (e.g. {@code "1 << 3"}), or the numeric string. */
        public final String expr;

        /** The C constant name (e.g., {@code "cef_button_state_t"}). */
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

    private CefButtonState(long value) {
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
    public static CefButtonState of(long v) {
        return new CefButtonState(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefButtonState of(Kind k) {
        return new CefButtonState(k.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefButtonState)) return false;
        return this.value == ((CefButtonState) obj).value;
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
