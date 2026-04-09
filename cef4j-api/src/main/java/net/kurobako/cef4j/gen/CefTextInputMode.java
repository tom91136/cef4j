// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;

/**
 * Input mode of a virtual keyboard. These constants match their equivalents in Chromium's text_input_mode.h and should not be renumbered. See <a href="https://html.spec.whatwg.org/#input-modalities:-the-inputmode-attribute">https://html.spec.whatwg.org/#input-modalities:-the-inputmode-attribute</a>
 * <p>Definition generated from cef_types.h
 * <pre>typedef enum {
 *   CEF_TEXT_INPUT_MODE_DEFAULT = 0,
 *   CEF_TEXT_INPUT_MODE_NONE = 1,
 *   CEF_TEXT_INPUT_MODE_TEXT = 2,
 *   CEF_TEXT_INPUT_MODE_TEL = 3,
 *   CEF_TEXT_INPUT_MODE_URL = 4,
 *   ...
 * } cef_text_input_mode_t;</pre>
 * <p>Possible values: {@link Kind#DEFAULT}, {@link Kind#NONE}, {@link Kind#TEXT}, {@link Kind#TEL}, {@link Kind#URL}, {@link Kind#EMAIL}, {@link Kind#NUMERIC}, {@link Kind#DECIMAL}, {@link Kind#SEARCH}, {@link Kind#NUM_VALUES}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types_8h.html">cef_types.h</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public final class CefTextInputMode implements CefEnum<CefTextInputMode> {

    /** Known constants for {@link CefTextInputMode}. */
    public enum Kind {
        DEFAULT(0, "0", "CEF_TEXT_INPUT_MODE_DEFAULT"),
        NONE(1, "1", "CEF_TEXT_INPUT_MODE_NONE"),
        TEXT(2, "2", "CEF_TEXT_INPUT_MODE_TEXT"),
        TEL(3, "3", "CEF_TEXT_INPUT_MODE_TEL"),
        URL(4, "4", "CEF_TEXT_INPUT_MODE_URL"),
        EMAIL(5, "5", "CEF_TEXT_INPUT_MODE_EMAIL"),
        NUMERIC(6, "6", "CEF_TEXT_INPUT_MODE_NUMERIC"),
        DECIMAL(7, "7", "CEF_TEXT_INPUT_MODE_DECIMAL"),
        SEARCH(8, "8", "CEF_TEXT_INPUT_MODE_SEARCH"),
        NUM_VALUES(9, "9", "CEF_TEXT_INPUT_MODE_NUM_VALUES");

        private static final Kind[] VALUES = Kind.values();

        /** The underlying C enum numeric value. */
        public final long value;

        /** The original C expression/literal (e.g. {@code "1 << 3"}), or the numeric string. */
        public final String expr;

        /** The C constant name (e.g., {@code "cef_text_input_mode_t"}). */
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

    private CefTextInputMode(long value) {
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
    public static CefTextInputMode of(long v) {
        return new CefTextInputMode(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefTextInputMode of(Kind k) {
        return new CefTextInputMode(k.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefTextInputMode)) return false;
        return this.value == ((CefTextInputMode) obj).value;
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
