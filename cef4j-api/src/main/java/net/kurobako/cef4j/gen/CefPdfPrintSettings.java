// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Structure representing PDF print settings. These values match the parameters supported by the DevTools
 * Page.printToPDF function. See <a
 * href="https://chromedevtools.github.io/devtools-protocol/tot/Page/#method-printToPDF">https://chromedevtools.github.io/devtools-protocol/tot/Page/#method-printToPDF</a>
 *
 * <p>Definition generated from cef_types.h
 *
 * <pre>typedef struct _cef_pdf_print_settings_t {
 *   size_t size;
 *   int landscape;
 *   int print_background;
 *   double scale;
 *   double paper_width;
 *   double paper_height;
 *   int prefer_css_page_size;
 *   cef_pdf_print_margin_type_t margin_type;
 *   double margin_top;
 *   double margin_right;
 *   double margin_bottom;
 *   double margin_left;
 *   cef_string_t* page_ranges;
 *   int display_header_footer;
 *   cef_string_t* header_template;
 *   cef_string_t* footer_template;
 *   int generate_tagged_pdf;
 *   int generate_document_outline;
 * } cef_pdf_print_settings_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types_8h.html">cef_types.h:2856</a>
 */
public final class CefPdfPrintSettings {

    // Native struct size — set by JNI, not user-modifiable.
    @SuppressWarnings("FieldMayBeFinal")
    private volatile long size = -1;

    public final int landscape;
    public final int printBackground;
    public final double scale;
    public final double paperWidth;
    public final double paperHeight;
    public final int preferCssPageSize;
    public final CefPdfPrintMarginType marginType;
    public final double marginTop;
    public final double marginRight;
    public final double marginBottom;
    public final double marginLeft;
    public final String pageRanges;
    public final int displayHeaderFooter;
    public final String headerTemplate;
    public final String footerTemplate;
    public final int generateTaggedPdf;
    public final int generateDocumentOutline;

    public CefPdfPrintSettings(
            int landscape,
            int printBackground,
            double scale,
            double paperWidth,
            double paperHeight,
            int preferCssPageSize,
            CefPdfPrintMarginType marginType,
            double marginTop,
            double marginRight,
            double marginBottom,
            double marginLeft,
            String pageRanges,
            int displayHeaderFooter,
            String headerTemplate,
            String footerTemplate,
            int generateTaggedPdf,
            int generateDocumentOutline) {
        this.landscape = landscape;
        this.printBackground = printBackground;
        this.scale = scale;
        this.paperWidth = paperWidth;
        this.paperHeight = paperHeight;
        this.preferCssPageSize = preferCssPageSize;
        this.marginType = marginType;
        this.marginTop = marginTop;
        this.marginRight = marginRight;
        this.marginBottom = marginBottom;
        this.marginLeft = marginLeft;
        this.pageRanges = pageRanges;
        this.displayHeaderFooter = displayHeaderFooter;
        this.headerTemplate = headerTemplate;
        this.footerTemplate = footerTemplate;
        this.generateTaggedPdf = generateTaggedPdf;
        this.generateDocumentOutline = generateDocumentOutline;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefPdfPrintSettings)) return false;
        CefPdfPrintSettings other = (CefPdfPrintSettings) obj;
        return this.landscape == other.landscape
                && this.printBackground == other.printBackground
                && this.scale == other.scale
                && this.paperWidth == other.paperWidth
                && this.paperHeight == other.paperHeight
                && this.preferCssPageSize == other.preferCssPageSize
                && java.util.Objects.equals(this.marginType, other.marginType)
                && this.marginTop == other.marginTop
                && this.marginRight == other.marginRight
                && this.marginBottom == other.marginBottom
                && this.marginLeft == other.marginLeft
                && java.util.Objects.equals(this.pageRanges, other.pageRanges)
                && this.displayHeaderFooter == other.displayHeaderFooter
                && java.util.Objects.equals(this.headerTemplate, other.headerTemplate)
                && java.util.Objects.equals(this.footerTemplate, other.footerTemplate)
                && this.generateTaggedPdf == other.generateTaggedPdf
                && this.generateDocumentOutline == other.generateDocumentOutline;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(
                landscape,
                printBackground,
                scale,
                paperWidth,
                paperHeight,
                preferCssPageSize,
                marginType,
                marginTop,
                marginRight,
                marginBottom,
                marginLeft,
                pageRanges,
                displayHeaderFooter,
                headerTemplate,
                footerTemplate,
                generateTaggedPdf,
                generateDocumentOutline);
    }

    @Override
    public String toString() {
        return "CefPdfPrintSettings{" + "size=" + (size == -1 ? "pending" : Long.toString(size)) + ", " + "landscape="
                + landscape + ", " + "printBackground=" + printBackground + ", " + "scale=" + scale + ", "
                + "paperWidth=" + paperWidth + ", " + "paperHeight=" + paperHeight + ", " + "preferCssPageSize="
                + preferCssPageSize + ", " + "marginType=" + marginType + ", " + "marginTop=" + marginTop + ", "
                + "marginRight=" + marginRight + ", " + "marginBottom=" + marginBottom + ", " + "marginLeft="
                + marginLeft + ", " + "pageRanges=" + pageRanges + ", " + "displayHeaderFooter=" + displayHeaderFooter
                + ", " + "headerTemplate=" + headerTemplate + ", " + "footerTemplate=" + footerTemplate + ", "
                + "generateTaggedPdf=" + generateTaggedPdf + ", " + "generateDocumentOutline=" + generateDocumentOutline
                + "}";
    }
}
