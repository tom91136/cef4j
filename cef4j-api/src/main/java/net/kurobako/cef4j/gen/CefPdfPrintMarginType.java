// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Margin type for PDF printing.
 *
 * <p>Definition generated from cef_types.h
 *
 * <pre>typedef enum {
 *   PDF_PRINT_MARGIN_DEFAULT = 0,
 *   PDF_PRINT_MARGIN_NONE = 1,
 *   PDF_PRINT_MARGIN_CUSTOM = 2
 * } cef_pdf_print_margin_type_t;</pre>
 *
 * <p>Possible values: {@link Kind#DEFAULT}, {@link Kind#NONE}, {@link Kind#CUSTOM}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types_8h.html">cef_types.h</a>
 */
public final class CefPdfPrintMarginType implements CefEnum<CefPdfPrintMarginType> {

    /** Known constants for {@link CefPdfPrintMarginType}. */
    public enum Kind {
        /** Default margins of 1cm (~0.4 inches). */
        DEFAULT(0, "0", "PDF_PRINT_MARGIN_DEFAULT"),
        /** No margins. */
        NONE(1, "1", "PDF_PRINT_MARGIN_NONE"),
        /** Custom margins using the {@code margin_*} values from cef_pdf_print_settings_t. */
        CUSTOM(2, "2", "PDF_PRINT_MARGIN_CUSTOM");

        private static final Kind[] VALUES = Kind.values();

        /** The underlying C enum numeric value. */
        public final long value;

        /** The original C expression/literal (e.g. {@code "1 << 3"}), or the numeric string. */
        public final String expr;

        /** The C constant name (e.g., {@code "cef_pdf_print_margin_type_t"}). */
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

    private CefPdfPrintMarginType(long value) {
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
    public static CefPdfPrintMarginType of(long v) {
        return new CefPdfPrintMarginType(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefPdfPrintMarginType of(Kind k) {
        return new CefPdfPrintMarginType(k.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefPdfPrintMarginType)) return false;
        return this.value == ((CefPdfPrintMarginType) obj).value;
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
