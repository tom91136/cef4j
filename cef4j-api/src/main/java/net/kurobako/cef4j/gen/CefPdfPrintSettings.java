// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Structure representing PDF print settings. These values match the parameters supported by the DevTools
 * Page.printToPDF function. See https://chromedevtools.github.io/devtools-protocol/tot/Page/#method-printToPDF
 */
public final class CefPdfPrintSettings {

    public final long size;
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
    public final int pageRanges;
    public final int displayHeaderFooter;
    public final int headerTemplate;
    public final int footerTemplate;
    public final int generateTaggedPdf;
    public final int generateDocumentOutline;

    public CefPdfPrintSettings(
            long size,
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
            int pageRanges,
            int displayHeaderFooter,
            int headerTemplate,
            int footerTemplate,
            int generateTaggedPdf,
            int generateDocumentOutline) {
        this.size = size;
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
        return this.size == other.size
                && this.landscape == other.landscape
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
                && this.pageRanges == other.pageRanges
                && this.displayHeaderFooter == other.displayHeaderFooter
                && this.headerTemplate == other.headerTemplate
                && this.footerTemplate == other.footerTemplate
                && this.generateTaggedPdf == other.generateTaggedPdf
                && this.generateDocumentOutline == other.generateDocumentOutline;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(
                size,
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
        return "CefPdfPrintSettings{" + "size=" + size + ", " + "landscape=" + landscape + ", " + "printBackground="
                + printBackground + ", " + "scale=" + scale + ", " + "paperWidth=" + paperWidth + ", " + "paperHeight="
                + paperHeight + ", " + "preferCssPageSize=" + preferCssPageSize + ", " + "marginType=" + marginType
                + ", " + "marginTop=" + marginTop + ", " + "marginRight=" + marginRight + ", " + "marginBottom="
                + marginBottom + ", " + "marginLeft=" + marginLeft + ", " + "pageRanges=" + pageRanges + ", "
                + "displayHeaderFooter=" + displayHeaderFooter + ", " + "headerTemplate=" + headerTemplate + ", "
                + "footerTemplate=" + footerTemplate + ", " + "generateTaggedPdf=" + generateTaggedPdf + ", "
                + "generateDocumentOutline=" + generateDocumentOutline + "}";
    }
}
