// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Mutable variant of {@link CefBrowserSettings}. Browser initialization settings. Specify {@code null} or 0 to get the
 * recommended default values. The consequences of using custom values may not be well tested. Many of these and other
 * settings can also configured using command-line switches.
 *
 * <p>Definition generated from cef_types.h
 *
 * <pre>typedef struct _cef_browser_settings_t {
 *   size_t size;
 *   int windowless_frame_rate;
 *   cef_string_t* standard_font_family;
 *   cef_string_t* fixed_font_family;
 *   cef_string_t* serif_font_family;
 *   cef_string_t* sans_serif_font_family;
 *   cef_string_t* cursive_font_family;
 *   cef_string_t* fantasy_font_family;
 *   int default_font_size;
 *   int default_fixed_font_size;
 *   int minimum_font_size;
 *   int minimum_logical_font_size;
 *   cef_string_t* default_encoding;
 *   cef_state_t remote_fonts;
 *   cef_state_t javascript;
 *   cef_state_t javascript_close_windows;
 *   cef_state_t javascript_access_clipboard;
 *   cef_state_t javascript_dom_paste;
 *   cef_state_t image_loading;
 *   cef_state_t image_shrink_standalone_to_fit;
 *   cef_state_t text_area_resize;
 *   cef_state_t tab_to_links;
 *   cef_state_t local_storage;
 *   cef_state_t databases_deprecated;
 *   cef_state_t webgl;
 *   unsigned int background_color;
 *   cef_state_t chrome_status_bubble;
 *   cef_state_t chrome_zoom_bubble;
 *   cef_state_t ax_viewport_collapse;
 * } cef_browser_settings_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types_8h.html">cef_types.h:571</a>
 */
public final class CefMutableBrowserSettings {

    // Native struct size — set by JNI, not user-modifiable.
    @SuppressWarnings("FieldMayBeFinal")
    private volatile long size = -1;

    public int windowlessFrameRate;
    public String standardFontFamily;
    public String fixedFontFamily;
    public String serifFontFamily;
    public String sansSerifFontFamily;
    public String cursiveFontFamily;
    public String fantasyFontFamily;
    public int defaultFontSize;
    public int defaultFixedFontSize;
    public int minimumFontSize;
    public int minimumLogicalFontSize;
    public String defaultEncoding;
    public CefState remoteFonts;
    public CefState javascript;
    public CefState javascriptCloseWindows;
    public CefState javascriptAccessClipboard;
    public CefState javascriptDomPaste;
    public CefState imageLoading;
    public CefState imageShrinkStandaloneToFit;
    public CefState textAreaResize;
    public CefState tabToLinks;
    public CefState localStorage;
    public CefState databasesDeprecated;
    public CefState webgl;
    public int backgroundColor;
    public CefState chromeStatusBubble;
    public CefState chromeZoomBubble;
    public CefState axViewportCollapse;

    public CefMutableBrowserSettings() {}

    public CefMutableBrowserSettings(
            int windowlessFrameRate,
            String standardFontFamily,
            String fixedFontFamily,
            String serifFontFamily,
            String sansSerifFontFamily,
            String cursiveFontFamily,
            String fantasyFontFamily,
            int defaultFontSize,
            int defaultFixedFontSize,
            int minimumFontSize,
            int minimumLogicalFontSize,
            String defaultEncoding,
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

    /** Create an immutable snapshot of this instance. */
    public CefBrowserSettings toImmutable() {
        return new CefBrowserSettings(
                this.windowlessFrameRate,
                this.standardFontFamily,
                this.fixedFontFamily,
                this.serifFontFamily,
                this.sansSerifFontFamily,
                this.cursiveFontFamily,
                this.fantasyFontFamily,
                this.defaultFontSize,
                this.defaultFixedFontSize,
                this.minimumFontSize,
                this.minimumLogicalFontSize,
                this.defaultEncoding,
                this.remoteFonts,
                this.javascript,
                this.javascriptCloseWindows,
                this.javascriptAccessClipboard,
                this.javascriptDomPaste,
                this.imageLoading,
                this.imageShrinkStandaloneToFit,
                this.textAreaResize,
                this.tabToLinks,
                this.localStorage,
                this.databasesDeprecated,
                this.webgl,
                this.backgroundColor,
                this.chromeStatusBubble,
                this.chromeZoomBubble,
                this.axViewportCollapse);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefMutableBrowserSettings)) return false;
        CefMutableBrowserSettings other = (CefMutableBrowserSettings) obj;
        return this.windowlessFrameRate == other.windowlessFrameRate
                && java.util.Objects.equals(this.standardFontFamily, other.standardFontFamily)
                && java.util.Objects.equals(this.fixedFontFamily, other.fixedFontFamily)
                && java.util.Objects.equals(this.serifFontFamily, other.serifFontFamily)
                && java.util.Objects.equals(this.sansSerifFontFamily, other.sansSerifFontFamily)
                && java.util.Objects.equals(this.cursiveFontFamily, other.cursiveFontFamily)
                && java.util.Objects.equals(this.fantasyFontFamily, other.fantasyFontFamily)
                && this.defaultFontSize == other.defaultFontSize
                && this.defaultFixedFontSize == other.defaultFixedFontSize
                && this.minimumFontSize == other.minimumFontSize
                && this.minimumLogicalFontSize == other.minimumLogicalFontSize
                && java.util.Objects.equals(this.defaultEncoding, other.defaultEncoding)
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
        return "CefMutableBrowserSettings{" + "size=" + (size == -1 ? "pending" : Long.toString(size)) + ", "
                + "windowlessFrameRate=" + windowlessFrameRate + ", " + "standardFontFamily=" + standardFontFamily
                + ", " + "fixedFontFamily=" + fixedFontFamily + ", " + "serifFontFamily=" + serifFontFamily + ", "
                + "sansSerifFontFamily=" + sansSerifFontFamily + ", " + "cursiveFontFamily=" + cursiveFontFamily + ", "
                + "fantasyFontFamily=" + fantasyFontFamily + ", " + "defaultFontSize=" + defaultFontSize + ", "
                + "defaultFixedFontSize=" + defaultFixedFontSize + ", " + "minimumFontSize=" + minimumFontSize + ", "
                + "minimumLogicalFontSize=" + minimumLogicalFontSize + ", " + "defaultEncoding=" + defaultEncoding
                + ", " + "remoteFonts=" + remoteFonts + ", " + "javascript=" + javascript + ", "
                + "javascriptCloseWindows=" + javascriptCloseWindows + ", " + "javascriptAccessClipboard="
                + javascriptAccessClipboard + ", " + "javascriptDomPaste=" + javascriptDomPaste + ", " + "imageLoading="
                + imageLoading + ", " + "imageShrinkStandaloneToFit=" + imageShrinkStandaloneToFit + ", "
                + "textAreaResize=" + textAreaResize + ", " + "tabToLinks=" + tabToLinks + ", " + "localStorage="
                + localStorage + ", " + "databasesDeprecated=" + databasesDeprecated + ", " + "webgl=" + webgl + ", "
                + "backgroundColor=" + backgroundColor + ", " + "chromeStatusBubble=" + chromeStatusBubble + ", "
                + "chromeZoomBubble=" + chromeZoomBubble + ", " + "axViewportCollapse=" + axViewportCollapse + "}";
    }
}
