// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-types
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * Print job color mode values.
 *
 * <p>Definition generated from cef_types.h
 *
 * <pre>typedef enum {
 *   COLOR_MODEL_UNKNOWN = 0,
 *   COLOR_MODEL_GRAY = 1,
 *   COLOR_MODEL_COLOR = 2,
 *   COLOR_MODEL_CMYK = 3,
 *   COLOR_MODEL_CMY = 4,
 *   ...
 * } cef_color_model_t;</pre>
 *
 * <p>Possible values: {@link Kind#UNKNOWN}, {@link Kind#GRAY}, {@link Kind#COLOR}, {@link Kind#CMYK}, {@link Kind#CMY},
 * {@link Kind#KCMY}, {@link Kind#CMY_K}, {@link Kind#BLACK}, {@link Kind#GRAYSCALE}, {@link Kind#RGB},
 * {@link Kind#RGB16}, {@link Kind#RGBA}, {@link Kind#COLORMODE_COLOR}, {@link Kind#COLORMODE_MONOCHROME},
 * {@link Kind#HP_COLOR_COLOR}, {@link Kind#HP_COLOR_BLACK}, {@link Kind#PRINTOUTMODE_NORMAL},
 * {@link Kind#PRINTOUTMODE_NORMAL_GRAY}, {@link Kind#PROCESSCOLORMODEL_CMYK}, {@link Kind#PROCESSCOLORMODEL_GREYSCALE},
 * {@link Kind#PROCESSCOLORMODEL_RGB}, {@link Kind#NUM_VALUES}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__types_8h.html">cef_types.h</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-types -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@NullableBoundary("generated external ABI")
public final class CefColorModel implements CefEnum<CefColorModel> {

    /** Known constants for {@link CefColorModel}. */
    public enum Kind {
        UNKNOWN(new EnumConstant(0, "0", "COLOR_MODEL_UNKNOWN")),
        GRAY(new EnumConstant(1, "1", "COLOR_MODEL_GRAY")),
        COLOR(new EnumConstant(2, "2", "COLOR_MODEL_COLOR")),
        CMYK(new EnumConstant(3, "3", "COLOR_MODEL_CMYK")),
        CMY(new EnumConstant(4, "4", "COLOR_MODEL_CMY")),
        KCMY(new EnumConstant(5, "5", "COLOR_MODEL_KCMY")),
        CMY_K(new EnumConstant(6, "6", "COLOR_MODEL_CMY_K")),
        BLACK(new EnumConstant(7, "7", "COLOR_MODEL_BLACK")),
        GRAYSCALE(new EnumConstant(8, "8", "COLOR_MODEL_GRAYSCALE")),
        RGB(new EnumConstant(9, "9", "COLOR_MODEL_RGB")),
        RGB16(new EnumConstant(10, "10", "COLOR_MODEL_RGB16")),
        RGBA(new EnumConstant(11, "11", "COLOR_MODEL_RGBA")),
        COLORMODE_COLOR(new EnumConstant(12, "12", "COLOR_MODEL_COLORMODE_COLOR")),
        COLORMODE_MONOCHROME(new EnumConstant(13, "13", "COLOR_MODEL_COLORMODE_MONOCHROME")),
        HP_COLOR_COLOR(new EnumConstant(14, "14", "COLOR_MODEL_HP_COLOR_COLOR")),
        HP_COLOR_BLACK(new EnumConstant(15, "15", "COLOR_MODEL_HP_COLOR_BLACK")),
        PRINTOUTMODE_NORMAL(new EnumConstant(16, "16", "COLOR_MODEL_PRINTOUTMODE_NORMAL")),
        PRINTOUTMODE_NORMAL_GRAY(new EnumConstant(17, "17", "COLOR_MODEL_PRINTOUTMODE_NORMAL_GRAY")),
        PROCESSCOLORMODEL_CMYK(new EnumConstant(18, "18", "COLOR_MODEL_PROCESSCOLORMODEL_CMYK")),
        PROCESSCOLORMODEL_GREYSCALE(new EnumConstant(19, "19", "COLOR_MODEL_PROCESSCOLORMODEL_GREYSCALE")),
        PROCESSCOLORMODEL_RGB(new EnumConstant(20, "20", "COLOR_MODEL_PROCESSCOLORMODEL_RGB")),
        NUM_VALUES(new EnumConstant(21, "21", "COLOR_MODEL_NUM_VALUES"));

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

        /** The C constant name (e.g., {@code "cef_color_model_t"}). */
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

    private CefColorModel(long value) {
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
    public static CefColorModel of(long v) {
        return new CefColorModel(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefColorModel of(Kind k) {
        return new CefColorModel(k.value());
    }

    @Override
    public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefColorModel)) return false;
        return this.value == ((CefColorModel) obj).value;
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
