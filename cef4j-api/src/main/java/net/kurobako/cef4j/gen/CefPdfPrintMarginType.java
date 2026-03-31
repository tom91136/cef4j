// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Margin type for PDF printing. */
public enum CefPdfPrintMarginType {

    /** Default margins of 1cm (~0.4 inches). */
    PDF_PRINT_MARGIN_DEFAULT(0L),
    /** No margins. */
    PDF_PRINT_MARGIN_NONE(1L),
    /** Custom margins using the |margin_*| values from cef_pdf_print_settings_t. */
    PDF_PRINT_MARGIN_CUSTOM(2L),
    UNKNOWN(-1L);

    public final long value;

    CefPdfPrintMarginType(long v) {
        this.value = v;
    }

    public static CefPdfPrintMarginType fromLong(long v) {
        for (CefPdfPrintMarginType e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
