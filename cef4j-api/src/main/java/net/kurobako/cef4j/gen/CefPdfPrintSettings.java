// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;

/**
 * Structure representing PDF print settings. These values match the parameters supported by the DevTools Page.printToPDF function. See <a href="https://chromedevtools.github.io/devtools-protocol/tot/Page/#method-printToPDF">https://chromedevtools.github.io/devtools-protocol/tot/Page/#method-printToPDF</a>
 * <p>Definition generated from internal/cef_types.h
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
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types_8h.html">internal/cef_types.h:2856</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public final class CefPdfPrintSettings {

    // Native struct size, populated by the JNI layer with sizeof(struct) as required by CEF. Not user-modifiable.
    @SuppressWarnings({"FieldMayBeFinal", "CanBeFinal"})
    private volatile long size = -1;

        /**
         * Set to {@code true} (1) for landscape mode or {@code false} (0) for portrait mode. 
         */    public final int landscape;
        /**
         * Set to {@code true} (1) to print background graphics. 
         */    public final int printBackground;
        /**
         * The percentage to scale the PDF by before printing (e.g. .5 is 50%). If this value is less than or equal to zero the default value of 1.0 will be used. 
         */    public final double scale;
        /**
         * Output paper size in inches. If either of these values is less than or equal to zero then the default paper size (letter, 8.5 x 11 inches) will be used. 
         */    public final double paperWidth;
    public final double paperHeight;
        /**
         * Set to {@code true} (1) to prefer page size as defined by css. Defaults to {@code false} (0), in which case the content will be scaled to fit the paper size. 
         */    public final int preferCssPageSize;
        /**
         * Margin type. 
         */    public final CefPdfPrintMarginType marginType;
        /**
         * Margins in inches. Only used if {@code margin_type} is set to PDF_PRINT_MARGIN_CUSTOM. 
         */    public final double marginTop;
    public final double marginRight;
    public final double marginBottom;
    public final double marginLeft;
        /**
         * Paper ranges to print, one based, e.g., '1-5, 8, 11-13'. Pages are printed in the document order, not in the order specified, and no more than once. Defaults to empty string, which implies the entire document is printed. The page numbers are quietly capped to actual page count of the document, and ranges beyond the end of the document are ignored. If this results in no pages to print, an error is reported. It is an error to specify a range with start greater than end. 
         */    public final String pageRanges;
        /**
         * Set to {@code true} (1) to display the header and/or footer. Modify {@code header_template} and/or {@code footer_template} to customize the display. 
         */    public final int displayHeaderFooter;
        /**
         * HTML template for the print header. Only displayed if {@code display_header_footer} is {@code true} (1). Should be valid HTML markup with the following classes used to inject printing values into them:  - date: formatted print date - title: document title - url: document location - pageNumber: current page number - totalPages: total pages in the document  For example, "&lt;span class=title&gt;&lt;/span&gt;" would generate a span containing the title. 
         */    public final String headerTemplate;
        /**
         * HTML template for the print footer. Only displayed if {@code display_header_footer} is {@code true} (1). Uses the same format as {@code header_template}. 
         */    public final String footerTemplate;
        /**
         * Set to {@code true} (1) to generate tagged (accessible) PDF. 
         */    public final int generateTaggedPdf;
        /**
         * Set to {@code true} (1) to generate a document outline. 
         */    public final int generateDocumentOutline;

    public CefPdfPrintSettings(int landscape, int printBackground, double scale, double paperWidth, double paperHeight, int preferCssPageSize, CefPdfPrintMarginType marginType, double marginTop, double marginRight, double marginBottom, double marginLeft, String pageRanges, int displayHeaderFooter, String headerTemplate, String footerTemplate, int generateTaggedPdf, int generateDocumentOutline) {
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

    /** Create a mutable copy of this instance. */
    public Mutable toMutable() {
        return new Mutable(this.landscape, this.printBackground, this.scale, this.paperWidth, this.paperHeight, this.preferCssPageSize, this.marginType, this.marginTop, this.marginRight, this.marginBottom, this.marginLeft, this.pageRanges, this.displayHeaderFooter, this.headerTemplate, this.footerTemplate, this.generateTaggedPdf, this.generateDocumentOutline);
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
        return java.util.Objects.hash(landscape, printBackground, scale, paperWidth, paperHeight, preferCssPageSize, marginType, marginTop, marginRight, marginBottom, marginLeft, pageRanges, displayHeaderFooter, headerTemplate, footerTemplate, generateTaggedPdf, generateDocumentOutline);
    }

    @Override
    public String toString() {
        return "CefPdfPrintSettings{" + "size=" + (size == -1 ? "pending" : Long.toString(size)) + ", " + "landscape=" + landscape + ", " + "printBackground=" + printBackground + ", " + "scale=" + scale + ", " + "paperWidth=" + paperWidth + ", " + "paperHeight=" + paperHeight + ", " + "preferCssPageSize=" + preferCssPageSize + ", " + "marginType=" + marginType + ", " + "marginTop=" + marginTop + ", " + "marginRight=" + marginRight + ", " + "marginBottom=" + marginBottom + ", " + "marginLeft=" + marginLeft + ", " + "pageRanges=" + pageRanges + ", " + "displayHeaderFooter=" + displayHeaderFooter + ", " + "headerTemplate=" + headerTemplate + ", " + "footerTemplate=" + footerTemplate + ", " + "generateTaggedPdf=" + generateTaggedPdf + ", " + "generateDocumentOutline=" + generateDocumentOutline + "}";
    }

    /**
     * Mutable variant of {@link CefPdfPrintSettings}. Structure representing PDF print settings. These values match the parameters supported by the DevTools Page.printToPDF function. See <a href="https://chromedevtools.github.io/devtools-protocol/tot/Page/#method-printToPDF">https://chromedevtools.github.io/devtools-protocol/tot/Page/#method-printToPDF</a>
     * <p>Definition generated from internal/cef_types.h
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types_8h.html">internal/cef_types.h:2856</a>
     */
    public static final class Mutable {

    // Native struct size, populated by the JNI layer with sizeof(struct) as required by CEF. Not user-modifiable.
    @SuppressWarnings({"FieldMayBeFinal", "CanBeFinal"})
    private volatile long size = -1;

            /**
             * Set to {@code true} (1) for landscape mode or {@code false} (0) for portrait mode. 
             */        public int landscape;
            /**
             * Set to {@code true} (1) to print background graphics. 
             */        public int printBackground;
            /**
             * The percentage to scale the PDF by before printing (e.g. .5 is 50%). If this value is less than or equal to zero the default value of 1.0 will be used. 
             */        public double scale;
            /**
             * Output paper size in inches. If either of these values is less than or equal to zero then the default paper size (letter, 8.5 x 11 inches) will be used. 
             */        public double paperWidth;
        public double paperHeight;
            /**
             * Set to {@code true} (1) to prefer page size as defined by css. Defaults to {@code false} (0), in which case the content will be scaled to fit the paper size. 
             */        public int preferCssPageSize;
            /**
             * Margin type. 
             */        public CefPdfPrintMarginType marginType;
            /**
             * Margins in inches. Only used if {@code margin_type} is set to PDF_PRINT_MARGIN_CUSTOM. 
             */        public double marginTop;
        public double marginRight;
        public double marginBottom;
        public double marginLeft;
            /**
             * Paper ranges to print, one based, e.g., '1-5, 8, 11-13'. Pages are printed in the document order, not in the order specified, and no more than once. Defaults to empty string, which implies the entire document is printed. The page numbers are quietly capped to actual page count of the document, and ranges beyond the end of the document are ignored. If this results in no pages to print, an error is reported. It is an error to specify a range with start greater than end. 
             */        public String pageRanges;
            /**
             * Set to {@code true} (1) to display the header and/or footer. Modify {@code header_template} and/or {@code footer_template} to customize the display. 
             */        public int displayHeaderFooter;
            /**
             * HTML template for the print header. Only displayed if {@code display_header_footer} is {@code true} (1). Should be valid HTML markup with the following classes used to inject printing values into them:  - date: formatted print date - title: document title - url: document location - pageNumber: current page number - totalPages: total pages in the document  For example, "&lt;span class=title&gt;&lt;/span&gt;" would generate a span containing the title. 
             */        public String headerTemplate;
            /**
             * HTML template for the print footer. Only displayed if {@code display_header_footer} is {@code true} (1). Uses the same format as {@code header_template}. 
             */        public String footerTemplate;
            /**
             * Set to {@code true} (1) to generate tagged (accessible) PDF. 
             */        public int generateTaggedPdf;
            /**
             * Set to {@code true} (1) to generate a document outline. 
             */        public int generateDocumentOutline;

        public Mutable() {}

        public Mutable(int landscape, int printBackground, double scale, double paperWidth, double paperHeight, int preferCssPageSize, CefPdfPrintMarginType marginType, double marginTop, double marginRight, double marginBottom, double marginLeft, String pageRanges, int displayHeaderFooter, String headerTemplate, String footerTemplate, int generateTaggedPdf, int generateDocumentOutline) {
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

        /** Create an immutable snapshot of this instance. */
        public CefPdfPrintSettings toImmutable() {
            return new CefPdfPrintSettings(this.landscape, this.printBackground, this.scale, this.paperWidth, this.paperHeight, this.preferCssPageSize, this.marginType, this.marginTop, this.marginRight, this.marginBottom, this.marginLeft, this.pageRanges, this.displayHeaderFooter, this.headerTemplate, this.footerTemplate, this.generateTaggedPdf, this.generateDocumentOutline);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Mutable)) return false;
            Mutable other = (Mutable) obj;
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
            return java.util.Objects.hash(landscape, printBackground, scale, paperWidth, paperHeight, preferCssPageSize, marginType, marginTop, marginRight, marginBottom, marginLeft, pageRanges, displayHeaderFooter, headerTemplate, footerTemplate, generateTaggedPdf, generateDocumentOutline);
        }

        @Override
        public String toString() {
            return "CefPdfPrintSettings.Mutable{" + "size=" + (size == -1 ? "pending" : Long.toString(size)) + ", " + "landscape=" + landscape + ", " + "printBackground=" + printBackground + ", " + "scale=" + scale + ", " + "paperWidth=" + paperWidth + ", " + "paperHeight=" + paperHeight + ", " + "preferCssPageSize=" + preferCssPageSize + ", " + "marginType=" + marginType + ", " + "marginTop=" + marginTop + ", " + "marginRight=" + marginRight + ", " + "marginBottom=" + marginBottom + ", " + "marginLeft=" + marginLeft + ", " + "pageRanges=" + pageRanges + ", " + "displayHeaderFooter=" + displayHeaderFooter + ", " + "headerTemplate=" + headerTemplate + ", " + "footerTemplate=" + footerTemplate + ", " + "generateTaggedPdf=" + generateTaggedPdf + ", " + "generateDocumentOutline=" + generateDocumentOutline + "}";
        }
    }
}
