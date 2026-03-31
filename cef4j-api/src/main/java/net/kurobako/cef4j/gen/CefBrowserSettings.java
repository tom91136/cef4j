// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Browser initialization settings. Specify NULL or 0 to get the recommended default values. The consequences of using
 * custom values may not be well tested. Many of these and other settings can also configured using command- line
 * switches.
 */
public final class CefBrowserSettings {

    public final long size;
    public final int windowlessFrameRate;
    public final int standardFontFamily;
    public final int fixedFontFamily;
    public final int serifFontFamily;
    public final int sansSerifFontFamily;
    public final int cursiveFontFamily;
    public final int fantasyFontFamily;
    public final int defaultFontSize;
    public final int defaultFixedFontSize;
    public final int minimumFontSize;
    public final int minimumLogicalFontSize;
    public final int defaultEncoding;
    public final CefState remoteFonts;
    public final CefState javascript;
    public final CefState javascriptCloseWindows;
    public final CefState javascriptAccessClipboard;
    public final CefState javascriptDomPaste;
    public final CefState imageLoading;
    public final CefState imageShrinkStandaloneToFit;
    public final CefState textAreaResize;
    public final CefState tabToLinks;
    public final CefState localStorage;
    public final CefState databasesDeprecated;
    public final CefState webgl;
    public final int backgroundColor;
    public final CefState chromeStatusBubble;
    public final CefState chromeZoomBubble;
    public final CefState axViewportCollapse;

    public CefBrowserSettings(
            long size,
            int windowlessFrameRate,
            int standardFontFamily,
            int fixedFontFamily,
            int serifFontFamily,
            int sansSerifFontFamily,
            int cursiveFontFamily,
            int fantasyFontFamily,
            int defaultFontSize,
            int defaultFixedFontSize,
            int minimumFontSize,
            int minimumLogicalFontSize,
            int defaultEncoding,
            CefState remoteFonts,
            CefState javascript,
            CefState javascriptCloseWindows,
            CefState javascriptAccessClipboard,
            CefState javascriptDomPaste,
            CefState imageLoading,
            CefState imageShrinkStandaloneToFit,
            CefState textAreaResize,
            CefState tabToLinks,
            CefState localStorage,
            CefState databasesDeprecated,
            CefState webgl,
            int backgroundColor,
            CefState chromeStatusBubble,
            CefState chromeZoomBubble,
            CefState axViewportCollapse) {
        this.size = size;
        this.windowlessFrameRate = windowlessFrameRate;
        this.standardFontFamily = standardFontFamily;
        this.fixedFontFamily = fixedFontFamily;
        this.serifFontFamily = serifFontFamily;
        this.sansSerifFontFamily = sansSerifFontFamily;
        this.cursiveFontFamily = cursiveFontFamily;
        this.fantasyFontFamily = fantasyFontFamily;
        this.defaultFontSize = defaultFontSize;
        this.defaultFixedFontSize = defaultFixedFontSize;
        this.minimumFontSize = minimumFontSize;
        this.minimumLogicalFontSize = minimumLogicalFontSize;
        this.defaultEncoding = defaultEncoding;
        this.remoteFonts = remoteFonts;
        this.javascript = javascript;
        this.javascriptCloseWindows = javascriptCloseWindows;
        this.javascriptAccessClipboard = javascriptAccessClipboard;
        this.javascriptDomPaste = javascriptDomPaste;
        this.imageLoading = imageLoading;
        this.imageShrinkStandaloneToFit = imageShrinkStandaloneToFit;
        this.textAreaResize = textAreaResize;
        this.tabToLinks = tabToLinks;
        this.localStorage = localStorage;
        this.databasesDeprecated = databasesDeprecated;
        this.webgl = webgl;
        this.backgroundColor = backgroundColor;
        this.chromeStatusBubble = chromeStatusBubble;
        this.chromeZoomBubble = chromeZoomBubble;
        this.axViewportCollapse = axViewportCollapse;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefBrowserSettings)) return false;
        CefBrowserSettings other = (CefBrowserSettings) obj;
        return this.size == other.size
                && this.windowlessFrameRate == other.windowlessFrameRate
                && this.standardFontFamily == other.standardFontFamily
                && this.fixedFontFamily == other.fixedFontFamily
                && this.serifFontFamily == other.serifFontFamily
                && this.sansSerifFontFamily == other.sansSerifFontFamily
                && this.cursiveFontFamily == other.cursiveFontFamily
                && this.fantasyFontFamily == other.fantasyFontFamily
                && this.defaultFontSize == other.defaultFontSize
                && this.defaultFixedFontSize == other.defaultFixedFontSize
                && this.minimumFontSize == other.minimumFontSize
                && this.minimumLogicalFontSize == other.minimumLogicalFontSize
                && this.defaultEncoding == other.defaultEncoding
                && java.util.Objects.equals(this.remoteFonts, other.remoteFonts)
                && java.util.Objects.equals(this.javascript, other.javascript)
                && java.util.Objects.equals(this.javascriptCloseWindows, other.javascriptCloseWindows)
                && java.util.Objects.equals(this.javascriptAccessClipboard, other.javascriptAccessClipboard)
                && java.util.Objects.equals(this.javascriptDomPaste, other.javascriptDomPaste)
                && java.util.Objects.equals(this.imageLoading, other.imageLoading)
                && java.util.Objects.equals(this.imageShrinkStandaloneToFit, other.imageShrinkStandaloneToFit)
                && java.util.Objects.equals(this.textAreaResize, other.textAreaResize)
                && java.util.Objects.equals(this.tabToLinks, other.tabToLinks)
                && java.util.Objects.equals(this.localStorage, other.localStorage)
                && java.util.Objects.equals(this.databasesDeprecated, other.databasesDeprecated)
                && java.util.Objects.equals(this.webgl, other.webgl)
                && this.backgroundColor == other.backgroundColor
                && java.util.Objects.equals(this.chromeStatusBubble, other.chromeStatusBubble)
                && java.util.Objects.equals(this.chromeZoomBubble, other.chromeZoomBubble)
                && java.util.Objects.equals(this.axViewportCollapse, other.axViewportCollapse);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(
                size,
                windowlessFrameRate,
                standardFontFamily,
                fixedFontFamily,
                serifFontFamily,
                sansSerifFontFamily,
                cursiveFontFamily,
                fantasyFontFamily,
                defaultFontSize,
                defaultFixedFontSize,
                minimumFontSize,
                minimumLogicalFontSize,
                defaultEncoding,
                remoteFonts,
                javascript,
                javascriptCloseWindows,
                javascriptAccessClipboard,
                javascriptDomPaste,
                imageLoading,
                imageShrinkStandaloneToFit,
                textAreaResize,
                tabToLinks,
                localStorage,
                databasesDeprecated,
                webgl,
                backgroundColor,
                chromeStatusBubble,
                chromeZoomBubble,
                axViewportCollapse);
    }

    @Override
    public String toString() {
        return "CefBrowserSettings{" + "size=" + size + ", " + "windowlessFrameRate=" + windowlessFrameRate + ", "
                + "standardFontFamily=" + standardFontFamily + ", " + "fixedFontFamily=" + fixedFontFamily + ", "
                + "serifFontFamily=" + serifFontFamily + ", " + "sansSerifFontFamily=" + sansSerifFontFamily + ", "
                + "cursiveFontFamily=" + cursiveFontFamily + ", " + "fantasyFontFamily=" + fantasyFontFamily + ", "
                + "defaultFontSize=" + defaultFontSize + ", " + "defaultFixedFontSize=" + defaultFixedFontSize + ", "
                + "minimumFontSize=" + minimumFontSize + ", " + "minimumLogicalFontSize=" + minimumLogicalFontSize
                + ", " + "defaultEncoding=" + defaultEncoding + ", " + "remoteFonts=" + remoteFonts + ", "
                + "javascript=" + javascript + ", " + "javascriptCloseWindows=" + javascriptCloseWindows + ", "
                + "javascriptAccessClipboard=" + javascriptAccessClipboard + ", " + "javascriptDomPaste="
                + javascriptDomPaste + ", " + "imageLoading=" + imageLoading + ", " + "imageShrinkStandaloneToFit="
                + imageShrinkStandaloneToFit + ", " + "textAreaResize=" + textAreaResize + ", " + "tabToLinks="
                + tabToLinks + ", " + "localStorage=" + localStorage + ", " + "databasesDeprecated="
                + databasesDeprecated + ", " + "webgl=" + webgl + ", " + "backgroundColor=" + backgroundColor + ", "
                + "chromeStatusBubble=" + chromeStatusBubble + ", " + "chromeZoomBubble=" + chromeZoomBubble + ", "
                + "axViewportCollapse=" + axViewportCollapse + "}";
    }
}
