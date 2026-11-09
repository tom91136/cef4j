// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-types
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * DOM form control types. Should be kept in sync with Chromium's blink::mojom::FormControlType type.
 *
 * <p>Definition generated from cef_types.h
 *
 * <pre>typedef enum {
 *   DOM_FORM_CONTROL_TYPE_UNSUPPORTED = 0,
 *   DOM_FORM_CONTROL_TYPE_BUTTON_BUTTON = 1,
 *   DOM_FORM_CONTROL_TYPE_BUTTON_SUBMIT = 2,
 *   DOM_FORM_CONTROL_TYPE_BUTTON_RESET = 3,
 *   DOM_FORM_CONTROL_TYPE_BUTTON_POPOVER = 4,
 *   ...
 * } cef_dom_form_control_type_t;</pre>
 *
 * <p>Possible values: {@link Kind#UNSUPPORTED}, {@link Kind#BUTTON_BUTTON}, {@link Kind#BUTTON_SUBMIT},
 * {@link Kind#BUTTON_RESET}, {@link Kind#BUTTON_POPOVER}, {@link Kind#FIELDSET}, {@link Kind#INPUT_BUTTON},
 * {@link Kind#INPUT_CHECKBOX}, {@link Kind#INPUT_COLOR}, {@link Kind#INPUT_DATE}, {@link Kind#INPUT_DATETIME_LOCAL},
 * {@link Kind#INPUT_EMAIL}, {@link Kind#INPUT_FILE}, {@link Kind#INPUT_HIDDEN}, {@link Kind#INPUT_IMAGE},
 * {@link Kind#INPUT_MONTH}, {@link Kind#INPUT_NUMBER}, {@link Kind#INPUT_PASSWORD}, {@link Kind#INPUT_RADIO},
 * {@link Kind#INPUT_RANGE}, {@link Kind#INPUT_RESET}, {@link Kind#INPUT_SEARCH}, {@link Kind#INPUT_SUBMIT},
 * {@link Kind#INPUT_TELEPHONE}, {@link Kind#INPUT_TEXT}, {@link Kind#INPUT_TIME}, {@link Kind#INPUT_URL},
 * {@link Kind#INPUT_WEEK}, {@link Kind#OUTPUT}, {@link Kind#SELECT_ONE}, {@link Kind#SELECT_MULTIPLE},
 * {@link Kind#TEXT_AREA}, {@link Kind#NUM_VALUES}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__types_8h.html">cef_types.h</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-types -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@NullableBoundary("generated external ABI")
public final class CefDomFormControlType implements CefEnum<CefDomFormControlType> {

    /** Known constants for {@link CefDomFormControlType}. */
    public enum Kind {
        UNSUPPORTED(new EnumConstant(0, "0", "DOM_FORM_CONTROL_TYPE_UNSUPPORTED")),
        BUTTON_BUTTON(new EnumConstant(1, "1", "DOM_FORM_CONTROL_TYPE_BUTTON_BUTTON")),
        BUTTON_SUBMIT(new EnumConstant(2, "2", "DOM_FORM_CONTROL_TYPE_BUTTON_SUBMIT")),
        BUTTON_RESET(new EnumConstant(3, "3", "DOM_FORM_CONTROL_TYPE_BUTTON_RESET")),
        BUTTON_POPOVER(new EnumConstant(4, "4", "DOM_FORM_CONTROL_TYPE_BUTTON_POPOVER")),
        FIELDSET(new EnumConstant(5, "5", "DOM_FORM_CONTROL_TYPE_FIELDSET")),
        INPUT_BUTTON(new EnumConstant(6, "6", "DOM_FORM_CONTROL_TYPE_INPUT_BUTTON")),
        INPUT_CHECKBOX(new EnumConstant(7, "7", "DOM_FORM_CONTROL_TYPE_INPUT_CHECKBOX")),
        INPUT_COLOR(new EnumConstant(8, "8", "DOM_FORM_CONTROL_TYPE_INPUT_COLOR")),
        INPUT_DATE(new EnumConstant(9, "9", "DOM_FORM_CONTROL_TYPE_INPUT_DATE")),
        INPUT_DATETIME_LOCAL(new EnumConstant(10, "10", "DOM_FORM_CONTROL_TYPE_INPUT_DATETIME_LOCAL")),
        INPUT_EMAIL(new EnumConstant(11, "11", "DOM_FORM_CONTROL_TYPE_INPUT_EMAIL")),
        INPUT_FILE(new EnumConstant(12, "12", "DOM_FORM_CONTROL_TYPE_INPUT_FILE")),
        INPUT_HIDDEN(new EnumConstant(13, "13", "DOM_FORM_CONTROL_TYPE_INPUT_HIDDEN")),
        INPUT_IMAGE(new EnumConstant(14, "14", "DOM_FORM_CONTROL_TYPE_INPUT_IMAGE")),
        INPUT_MONTH(new EnumConstant(15, "15", "DOM_FORM_CONTROL_TYPE_INPUT_MONTH")),
        INPUT_NUMBER(new EnumConstant(16, "16", "DOM_FORM_CONTROL_TYPE_INPUT_NUMBER")),
        INPUT_PASSWORD(new EnumConstant(17, "17", "DOM_FORM_CONTROL_TYPE_INPUT_PASSWORD")),
        INPUT_RADIO(new EnumConstant(18, "18", "DOM_FORM_CONTROL_TYPE_INPUT_RADIO")),
        INPUT_RANGE(new EnumConstant(19, "19", "DOM_FORM_CONTROL_TYPE_INPUT_RANGE")),
        INPUT_RESET(new EnumConstant(20, "20", "DOM_FORM_CONTROL_TYPE_INPUT_RESET")),
        INPUT_SEARCH(new EnumConstant(21, "21", "DOM_FORM_CONTROL_TYPE_INPUT_SEARCH")),
        INPUT_SUBMIT(new EnumConstant(22, "22", "DOM_FORM_CONTROL_TYPE_INPUT_SUBMIT")),
        INPUT_TELEPHONE(new EnumConstant(23, "23", "DOM_FORM_CONTROL_TYPE_INPUT_TELEPHONE")),
        INPUT_TEXT(new EnumConstant(24, "24", "DOM_FORM_CONTROL_TYPE_INPUT_TEXT")),
        INPUT_TIME(new EnumConstant(25, "25", "DOM_FORM_CONTROL_TYPE_INPUT_TIME")),
        INPUT_URL(new EnumConstant(26, "26", "DOM_FORM_CONTROL_TYPE_INPUT_URL")),
        INPUT_WEEK(new EnumConstant(27, "27", "DOM_FORM_CONTROL_TYPE_INPUT_WEEK")),
        OUTPUT(new EnumConstant(28, "28", "DOM_FORM_CONTROL_TYPE_OUTPUT")),
        SELECT_ONE(new EnumConstant(29, "29", "DOM_FORM_CONTROL_TYPE_SELECT_ONE")),
        SELECT_MULTIPLE(new EnumConstant(30, "30", "DOM_FORM_CONTROL_TYPE_SELECT_MULTIPLE")),
        TEXT_AREA(new EnumConstant(31, "31", "DOM_FORM_CONTROL_TYPE_TEXT_AREA")),
        NUM_VALUES(new EnumConstant(32, "32", "DOM_FORM_CONTROL_TYPE_NUM_VALUES"));

        private static final Kind[] VALUES = Kind.values();

        private final EnumConstant constant;

        Kind(EnumConstant constant) {
            this.constant = constant;
        }

        /** The underlying C enum numeric value. */
        public long value() {
            return constant.value();
        }

        /** The original C expression/literal (e.g. {@code "1 << 3"}), or the numeric string. */
        public String expr() {
            return constant.expr();
        }

        /** The C constant name (e.g., {@code "cef_dom_form_control_type_t"}). */
        public String cName() {
            return constant.name();
        }

        @Override
        public String toString() {
            return cName() + "(expr=" + expr() + ", value=" + value() + ")";
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
        return kind().map(Kind::expr).orElse(String.valueOf(value));
    }

    /** {@inheritDoc} */
    @Override
    public String name() {
        return kind().map(Kind::cName).orElse("UNKNOWN(" + value + ")");
    }

    /**
     * Returns the {@link Kind} matching this value, or empty for unknown/composite values. Use this for exhaustive
     * switch over known constants.
     */
    public java.util.Optional<Kind> kind() {
        for (Kind k : Kind.VALUES) {
            if (k.value() == value) return java.util.Optional.of(k);
        }
        return java.util.Optional.empty();
    }

    /** Returns an instance for the given raw value, use {@link #kind} to resolve to a concrete enum. */
    public static CefDomFormControlType of(long v) {
        return new CefDomFormControlType(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefDomFormControlType of(Kind k) {
        return new CefDomFormControlType(k.value());
    }

    @Override
    public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
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
