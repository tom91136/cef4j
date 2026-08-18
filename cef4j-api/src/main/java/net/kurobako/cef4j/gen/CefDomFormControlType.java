// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;

/**
 * DOM form control types. Should be kept in sync with Chromium's blink::mojom::FormControlType type.
 * <p>Definition generated from cef_types.h
 * <pre>typedef enum {
 *   DOM_FORM_CONTROL_TYPE_UNSUPPORTED = 0,
 *   DOM_FORM_CONTROL_TYPE_BUTTON_BUTTON = 1,
 *   DOM_FORM_CONTROL_TYPE_BUTTON_SUBMIT = 2,
 *   DOM_FORM_CONTROL_TYPE_BUTTON_RESET = 3,
 *   DOM_FORM_CONTROL_TYPE_BUTTON_POPOVER = 4,
 *   ...
 * } cef_dom_form_control_type_t;</pre>
 * <p>Possible values: {@link Kind#UNSUPPORTED}, {@link Kind#BUTTON_BUTTON}, {@link Kind#BUTTON_SUBMIT}, {@link Kind#BUTTON_RESET}, {@link Kind#BUTTON_POPOVER}, {@link Kind#FIELDSET}, {@link Kind#INPUT_BUTTON}, {@link Kind#INPUT_CHECKBOX}, {@link Kind#INPUT_COLOR}, {@link Kind#INPUT_DATE}, {@link Kind#INPUT_DATETIME_LOCAL}, {@link Kind#INPUT_EMAIL}, {@link Kind#INPUT_FILE}, {@link Kind#INPUT_HIDDEN}, {@link Kind#INPUT_IMAGE}, {@link Kind#INPUT_MONTH}, {@link Kind#INPUT_NUMBER}, {@link Kind#INPUT_PASSWORD}, {@link Kind#INPUT_RADIO}, {@link Kind#INPUT_RANGE}, {@link Kind#INPUT_RESET}, {@link Kind#INPUT_SEARCH}, {@link Kind#INPUT_SUBMIT}, {@link Kind#INPUT_TELEPHONE}, {@link Kind#INPUT_TEXT}, {@link Kind#INPUT_TIME}, {@link Kind#INPUT_URL}, {@link Kind#INPUT_WEEK}, {@link Kind#OUTPUT}, {@link Kind#SELECT_ONE}, {@link Kind#SELECT_MULTIPLE}, {@link Kind#TEXT_AREA}, {@link Kind#NUM_VALUES}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__types_8h.html">cef_types.h</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused", "NullableForbidden"})
public final class CefDomFormControlType implements CefEnum<CefDomFormControlType> {

    /** Known constants for {@link CefDomFormControlType}. */
    public enum Kind {
        UNSUPPORTED(0, "0", "DOM_FORM_CONTROL_TYPE_UNSUPPORTED"),
        BUTTON_BUTTON(1, "1", "DOM_FORM_CONTROL_TYPE_BUTTON_BUTTON"),
        BUTTON_SUBMIT(2, "2", "DOM_FORM_CONTROL_TYPE_BUTTON_SUBMIT"),
        BUTTON_RESET(3, "3", "DOM_FORM_CONTROL_TYPE_BUTTON_RESET"),
        BUTTON_POPOVER(4, "4", "DOM_FORM_CONTROL_TYPE_BUTTON_POPOVER"),
        FIELDSET(5, "5", "DOM_FORM_CONTROL_TYPE_FIELDSET"),
        INPUT_BUTTON(6, "6", "DOM_FORM_CONTROL_TYPE_INPUT_BUTTON"),
        INPUT_CHECKBOX(7, "7", "DOM_FORM_CONTROL_TYPE_INPUT_CHECKBOX"),
        INPUT_COLOR(8, "8", "DOM_FORM_CONTROL_TYPE_INPUT_COLOR"),
        INPUT_DATE(9, "9", "DOM_FORM_CONTROL_TYPE_INPUT_DATE"),
        INPUT_DATETIME_LOCAL(10, "10", "DOM_FORM_CONTROL_TYPE_INPUT_DATETIME_LOCAL"),
        INPUT_EMAIL(11, "11", "DOM_FORM_CONTROL_TYPE_INPUT_EMAIL"),
        INPUT_FILE(12, "12", "DOM_FORM_CONTROL_TYPE_INPUT_FILE"),
        INPUT_HIDDEN(13, "13", "DOM_FORM_CONTROL_TYPE_INPUT_HIDDEN"),
        INPUT_IMAGE(14, "14", "DOM_FORM_CONTROL_TYPE_INPUT_IMAGE"),
        INPUT_MONTH(15, "15", "DOM_FORM_CONTROL_TYPE_INPUT_MONTH"),
        INPUT_NUMBER(16, "16", "DOM_FORM_CONTROL_TYPE_INPUT_NUMBER"),
        INPUT_PASSWORD(17, "17", "DOM_FORM_CONTROL_TYPE_INPUT_PASSWORD"),
        INPUT_RADIO(18, "18", "DOM_FORM_CONTROL_TYPE_INPUT_RADIO"),
        INPUT_RANGE(19, "19", "DOM_FORM_CONTROL_TYPE_INPUT_RANGE"),
        INPUT_RESET(20, "20", "DOM_FORM_CONTROL_TYPE_INPUT_RESET"),
        INPUT_SEARCH(21, "21", "DOM_FORM_CONTROL_TYPE_INPUT_SEARCH"),
        INPUT_SUBMIT(22, "22", "DOM_FORM_CONTROL_TYPE_INPUT_SUBMIT"),
        INPUT_TELEPHONE(23, "23", "DOM_FORM_CONTROL_TYPE_INPUT_TELEPHONE"),
        INPUT_TEXT(24, "24", "DOM_FORM_CONTROL_TYPE_INPUT_TEXT"),
        INPUT_TIME(25, "25", "DOM_FORM_CONTROL_TYPE_INPUT_TIME"),
        INPUT_URL(26, "26", "DOM_FORM_CONTROL_TYPE_INPUT_URL"),
        INPUT_WEEK(27, "27", "DOM_FORM_CONTROL_TYPE_INPUT_WEEK"),
        OUTPUT(28, "28", "DOM_FORM_CONTROL_TYPE_OUTPUT"),
        SELECT_ONE(29, "29", "DOM_FORM_CONTROL_TYPE_SELECT_ONE"),
        SELECT_MULTIPLE(30, "30", "DOM_FORM_CONTROL_TYPE_SELECT_MULTIPLE"),
        TEXT_AREA(31, "31", "DOM_FORM_CONTROL_TYPE_TEXT_AREA"),
        NUM_VALUES(32, "32", "DOM_FORM_CONTROL_TYPE_NUM_VALUES");

        private static final Kind[] VALUES = Kind.values();

        /** The underlying C enum numeric value. */
        public final long value;

        /** The original C expression/literal (e.g. {@code "1 << 3"}), or the numeric string. */
        public final String expr;

        /** The C constant name (e.g., {@code "cef_dom_form_control_type_t"}). */
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

    private CefDomFormControlType(long value) {
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
    public static CefDomFormControlType of(long v) {
        return new CefDomFormControlType(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefDomFormControlType of(Kind k) {
        return new CefDomFormControlType(k.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefDomFormControlType)) return false;
        return this.value == ((CefDomFormControlType) obj).value;
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
