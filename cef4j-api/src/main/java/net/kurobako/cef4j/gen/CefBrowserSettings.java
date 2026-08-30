// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-api
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.Nullable;
import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * Browser initialization settings. Specify {@code null} or 0 to get the recommended default values. The consequences of
 * using custom values may not be well tested. Many of these and other settings can also configured using command-line
 * switches.
 *
 * <p>Definition generated from internal/cef_types.h
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
 * } cef_browser_settings_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__types_8h.html">internal/cef_types.h:571</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-api -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
@NullableBoundary("generated external ABI")
public final class CefBrowserSettings {

    // Native struct size, populated by the JNI layer with sizeof(struct) as required by CEF. Not user-modifiable.
    @SuppressWarnings({"FieldMayBeFinal", "CanBeFinal"})
    private volatile long size = -1;

    /**
     * The maximum rate in frames per second (fps) that
     * {@link net.kurobako.cef4j.gen.CefRenderHandler#onPaint(CefBrowser, CefPaintElementType, long, CefRect[],
     * java.nio.ByteBuffer, int, int)} will be called for a windowless browser. The actual fps may be lower if the
     * browser cannot generate frames at the requested rate. The minimum value is 1 and the default value is 30. This
     * value can also be changed dynamically via
     * {@link net.kurobako.cef4j.gen.CefBrowserHost#setWindowlessFrameRate(int)}.
     */
    public final int windowlessFrameRate;
    /**
     * Font settings.
     *
     * <p><i>values that map to WebPreferences settings</i>
     */
    public final @Nullable String standardFontFamily;
    /** <i>values that map to WebPreferences settings</i> */
    public final @Nullable String fixedFontFamily;
    /** <i>values that map to WebPreferences settings</i> */
    public final @Nullable String serifFontFamily;
    /** <i>values that map to WebPreferences settings</i> */
    public final @Nullable String sansSerifFontFamily;
    /** <i>values that map to WebPreferences settings</i> */
    public final @Nullable String cursiveFontFamily;
    /** <i>values that map to WebPreferences settings</i> */
    public final @Nullable String fantasyFontFamily;
    /** <i>values that map to WebPreferences settings</i> */
    public final int defaultFontSize;
    /** <i>values that map to WebPreferences settings</i> */
    public final int defaultFixedFontSize;
    /** <i>values that map to WebPreferences settings</i> */
    public final int minimumFontSize;
    /** <i>values that map to WebPreferences settings</i> */
    public final int minimumLogicalFontSize;
    /**
     * Default encoding for Web content. If empty "ISO-8859-1" will be used. Also configurable using the
     * "default-encoding" command-line switch.
     *
     * <p><i>values that map to WebPreferences settings</i>
     */
    public final @Nullable String defaultEncoding;
    /**
     * Controls the loading of fonts from remote sources. Also configurable using the "disable-remote-fonts"
     * command-line switch.
     *
     * <p><i>values that map to WebPreferences settings</i>
     */
    public final @Nullable CefState remoteFonts;
    /**
     * Controls whether JavaScript can be executed. Also configurable using the "disable-javascript" command-line
     * switch.
     *
     * <p><i>values that map to WebPreferences settings</i>
     */
    public final @Nullable CefState javascript;
    /**
     * Controls whether JavaScript can be used to close windows that were not opened via JavaScript. JavaScript can
     * still be used to close windows that were opened via JavaScript or that have no back/forward history. Also
     * configurable using the "disable-javascript-close-windows" command-line switch.
     *
     * <p><i>values that map to WebPreferences settings</i>
     */
    public final @Nullable CefState javascriptCloseWindows;
    /**
     * Controls whether JavaScript can access the clipboard. Also configurable using the
     * "disable-javascript-access-clipboard" command-line switch.
     *
     * <p><i>values that map to WebPreferences settings</i>
     */
    public final @Nullable CefState javascriptAccessClipboard;
    /**
     * Controls whether DOM pasting is supported in the editor via execCommand("paste"). The
     * {@code javascript_access_clipboard} setting must also be enabled. Also configurable using the
     * "disable-javascript-dom-paste" command-line switch.
     *
     * <p><i>values that map to WebPreferences settings</i>
     */
    public final @Nullable CefState javascriptDomPaste;
    /**
     * Controls whether image URLs will be loaded from the network. A cached image will still be rendered if requested.
     * Also configurable using the "disable-image-loading" command-line switch.
     *
     * <p><i>values that map to WebPreferences settings</i>
     */
    public final @Nullable CefState imageLoading;
    /**
     * Controls whether standalone images will be shrunk to fit the page. Also configurable using the
     * "image-shrink-standalone-to-fit" command-line switch.
     *
     * <p><i>values that map to WebPreferences settings</i>
     */
    public final @Nullable CefState imageShrinkStandaloneToFit;
    /**
     * Controls whether text areas can be resized. Also configurable using the "disable-text-area-resize" command-line
     * switch.
     *
     * <p><i>values that map to WebPreferences settings</i>
     */
    public final @Nullable CefState textAreaResize;
    /**
     * Controls whether the tab key can advance focus to links. Also configurable using the "disable-tab-to-links"
     * command-line switch.
     *
     * <p><i>values that map to WebPreferences settings</i>
     */
    public final @Nullable CefState tabToLinks;
    /**
     * Controls whether local storage can be used. Also configurable using the "disable-local-storage" command-line
     * switch.
     *
     * <p><i>values that map to WebPreferences settings</i>
     */
    public final @Nullable CefState localStorage;
    /**
     * Controls whether databases can be used. Also configurable using the "disable-databases" command-line switch.
     *
     * <p><i>values that map to WebPreferences settings</i>
     */
    public final @Nullable CefState databasesDeprecated;
    /**
     * Controls whether WebGL can be used. Note that WebGL requires hardware support and may not work on all systems
     * even when enabled. Also configurable using the "disable-webgl" command-line switch.
     *
     * <p><i>values that map to WebPreferences settings</i>
     */
    public final @Nullable CefState webgl;
    /**
     * Background color used for the browser before a document is loaded and when no document color is specified. The
     * alpha component must be either fully opaque (0xFF) or fully transparent (0x00). If the alpha component is fully
     * opaque then the RGB components will be used as the background color. If the alpha component is fully transparent
     * for a windowed browser then the CefSettings.background_color value will be used. If the alpha component is fully
     * transparent for a windowless (off-screen) browser then transparent painting will be enabled.
     */
    public final int backgroundColor;
    /**
     * Controls whether the Chrome status bubble will be used. Only supported with Chrome style. For details about the
     * status bubble see <a
     * href="https://www.chromium.org/user-experience/status-bubble/">https://www.chromium.org/user-experience/status-bubble/</a>
     */
    public final @Nullable CefState chromeStatusBubble;
    /** Controls whether the Chrome zoom bubble will be shown when zooming. Only supported with Chrome style. */
    public final @Nullable CefState chromeZoomBubble;

    public CefBrowserSettings(
            int windowlessFrameRate,
            @Nullable String standardFontFamily,
            @Nullable String fixedFontFamily,
            @Nullable String serifFontFamily,
            @Nullable String sansSerifFontFamily,
            @Nullable String cursiveFontFamily,
            @Nullable String fantasyFontFamily,
            int defaultFontSize,
            int defaultFixedFontSize,
            int minimumFontSize,
            int minimumLogicalFontSize,
            @Nullable String defaultEncoding,
            @Nullable CefState remoteFonts,
            @Nullable CefState javascript,
            @Nullable CefState javascriptCloseWindows,
            @Nullable CefState javascriptAccessClipboard,
            @Nullable CefState javascriptDomPaste,
            @Nullable CefState imageLoading,
            @Nullable CefState imageShrinkStandaloneToFit,
            @Nullable CefState textAreaResize,
            @Nullable CefState tabToLinks,
            @Nullable CefState localStorage,
            @Nullable CefState databasesDeprecated,
            @Nullable CefState webgl,
            int backgroundColor,
            @Nullable CefState chromeStatusBubble,
            @Nullable CefState chromeZoomBubble) {
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
    }

    /** Create a mutable copy of this instance. */
    public Mutable toMutable() {
        return new Mutable(
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
                this.chromeZoomBubble);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefBrowserSettings)) return false;
        CefBrowserSettings other = (CefBrowserSettings) obj;
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
                && java.util.Objects.equals(this.chromeZoomBubble, other.chromeZoomBubble);
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
                chromeZoomBubble);
    }

    @Override
    public String toString() {
        return "CefBrowserSettings{" + "size=" + (size == -1 ? "pending" : Long.toString(size)) + ", "
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
                + "chromeZoomBubble=" + chromeZoomBubble + "}";
    }

    /**
     * Mutable variant of {@link CefBrowserSettings}. Browser initialization settings. Specify {@code null} or 0 to get
     * the recommended default values. The consequences of using custom values may not be well tested. Many of these and
     * other settings can also configured using command-line switches.
     *
     * <p>Definition generated from internal/cef_types.h
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__types_8h.html">internal/cef_types.h:571</a>
     */
    public static final class Mutable {

        // Native struct size, populated by the JNI layer with sizeof(struct) as required by CEF. Not user-modifiable.
        @SuppressWarnings({"FieldMayBeFinal", "CanBeFinal"})
        private volatile long size = -1;

        /**
         * The maximum rate in frames per second (fps) that
         * {@link net.kurobako.cef4j.gen.CefRenderHandler#onPaint(CefBrowser, CefPaintElementType, long, CefRect[],
         * java.nio.ByteBuffer, int, int)} will be called for a windowless browser. The actual fps may be lower if the
         * browser cannot generate frames at the requested rate. The minimum value is 1 and the default value is 30.
         * This value can also be changed dynamically via
         * {@link net.kurobako.cef4j.gen.CefBrowserHost#setWindowlessFrameRate(int)}.
         */
        public int windowlessFrameRate;
        /**
         * Font settings.
         *
         * <p><i>values that map to WebPreferences settings</i>
         */
        public @Nullable String standardFontFamily;
        /** <i>values that map to WebPreferences settings</i> */
        public @Nullable String fixedFontFamily;
        /** <i>values that map to WebPreferences settings</i> */
        public @Nullable String serifFontFamily;
        /** <i>values that map to WebPreferences settings</i> */
        public @Nullable String sansSerifFontFamily;
        /** <i>values that map to WebPreferences settings</i> */
        public @Nullable String cursiveFontFamily;
        /** <i>values that map to WebPreferences settings</i> */
        public @Nullable String fantasyFontFamily;
        /** <i>values that map to WebPreferences settings</i> */
        public int defaultFontSize;
        /** <i>values that map to WebPreferences settings</i> */
        public int defaultFixedFontSize;
        /** <i>values that map to WebPreferences settings</i> */
        public int minimumFontSize;
        /** <i>values that map to WebPreferences settings</i> */
        public int minimumLogicalFontSize;
        /**
         * Default encoding for Web content. If empty "ISO-8859-1" will be used. Also configurable using the
         * "default-encoding" command-line switch.
         *
         * <p><i>values that map to WebPreferences settings</i>
         */
        public @Nullable String defaultEncoding;
        /**
         * Controls the loading of fonts from remote sources. Also configurable using the "disable-remote-fonts"
         * command-line switch.
         *
         * <p><i>values that map to WebPreferences settings</i>
         */
        public @Nullable CefState remoteFonts;
        /**
         * Controls whether JavaScript can be executed. Also configurable using the "disable-javascript" command-line
         * switch.
         *
         * <p><i>values that map to WebPreferences settings</i>
         */
        public @Nullable CefState javascript;
        /**
         * Controls whether JavaScript can be used to close windows that were not opened via JavaScript. JavaScript can
         * still be used to close windows that were opened via JavaScript or that have no back/forward history. Also
         * configurable using the "disable-javascript-close-windows" command-line switch.
         *
         * <p><i>values that map to WebPreferences settings</i>
         */
        public @Nullable CefState javascriptCloseWindows;
        /**
         * Controls whether JavaScript can access the clipboard. Also configurable using the
         * "disable-javascript-access-clipboard" command-line switch.
         *
         * <p><i>values that map to WebPreferences settings</i>
         */
        public @Nullable CefState javascriptAccessClipboard;
        /**
         * Controls whether DOM pasting is supported in the editor via execCommand("paste"). The
         * {@code javascript_access_clipboard} setting must also be enabled. Also configurable using the
         * "disable-javascript-dom-paste" command-line switch.
         *
         * <p><i>values that map to WebPreferences settings</i>
         */
        public @Nullable CefState javascriptDomPaste;
        /**
         * Controls whether image URLs will be loaded from the network. A cached image will still be rendered if
         * requested. Also configurable using the "disable-image-loading" command-line switch.
         *
         * <p><i>values that map to WebPreferences settings</i>
         */
        public @Nullable CefState imageLoading;
        /**
         * Controls whether standalone images will be shrunk to fit the page. Also configurable using the
         * "image-shrink-standalone-to-fit" command-line switch.
         *
         * <p><i>values that map to WebPreferences settings</i>
         */
        public @Nullable CefState imageShrinkStandaloneToFit;
        /**
         * Controls whether text areas can be resized. Also configurable using the "disable-text-area-resize"
         * command-line switch.
         *
         * <p><i>values that map to WebPreferences settings</i>
         */
        public @Nullable CefState textAreaResize;
        /**
         * Controls whether the tab key can advance focus to links. Also configurable using the "disable-tab-to-links"
         * command-line switch.
         *
         * <p><i>values that map to WebPreferences settings</i>
         */
        public @Nullable CefState tabToLinks;
        /**
         * Controls whether local storage can be used. Also configurable using the "disable-local-storage" command-line
         * switch.
         *
         * <p><i>values that map to WebPreferences settings</i>
         */
        public @Nullable CefState localStorage;
        /**
         * Controls whether databases can be used. Also configurable using the "disable-databases" command-line switch.
         *
         * <p><i>values that map to WebPreferences settings</i>
         */
        public @Nullable CefState databasesDeprecated;
        /**
         * Controls whether WebGL can be used. Note that WebGL requires hardware support and may not work on all systems
         * even when enabled. Also configurable using the "disable-webgl" command-line switch.
         *
         * <p><i>values that map to WebPreferences settings</i>
         */
        public @Nullable CefState webgl;
        /**
         * Background color used for the browser before a document is loaded and when no document color is specified.
         * The alpha component must be either fully opaque (0xFF) or fully transparent (0x00). If the alpha component is
         * fully opaque then the RGB components will be used as the background color. If the alpha component is fully
         * transparent for a windowed browser then the CefSettings.background_color value will be used. If the alpha
         * component is fully transparent for a windowless (off-screen) browser then transparent painting will be
         * enabled.
         */
        public int backgroundColor;
        /**
         * Controls whether the Chrome status bubble will be used. Only supported with Chrome style. For details about
         * the status bubble see <a
         * href="https://www.chromium.org/user-experience/status-bubble/">https://www.chromium.org/user-experience/status-bubble/</a>
         */
        public @Nullable CefState chromeStatusBubble;
        /** Controls whether the Chrome zoom bubble will be shown when zooming. Only supported with Chrome style. */
        public @Nullable CefState chromeZoomBubble;

        public Mutable() {}

        public Mutable(
                int windowlessFrameRate,
                @Nullable String standardFontFamily,
                @Nullable String fixedFontFamily,
                @Nullable String serifFontFamily,
                @Nullable String sansSerifFontFamily,
                @Nullable String cursiveFontFamily,
                @Nullable String fantasyFontFamily,
                int defaultFontSize,
                int defaultFixedFontSize,
                int minimumFontSize,
                int minimumLogicalFontSize,
                @Nullable String defaultEncoding,
                @Nullable CefState remoteFonts,
                @Nullable CefState javascript,
                @Nullable CefState javascriptCloseWindows,
                @Nullable CefState javascriptAccessClipboard,
                @Nullable CefState javascriptDomPaste,
                @Nullable CefState imageLoading,
                @Nullable CefState imageShrinkStandaloneToFit,
                @Nullable CefState textAreaResize,
                @Nullable CefState tabToLinks,
                @Nullable CefState localStorage,
                @Nullable CefState databasesDeprecated,
                @Nullable CefState webgl,
                int backgroundColor,
                @Nullable CefState chromeStatusBubble,
                @Nullable CefState chromeZoomBubble) {
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
                    this.chromeZoomBubble);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Mutable)) return false;
            Mutable other = (Mutable) obj;
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
                    && java.util.Objects.equals(this.chromeZoomBubble, other.chromeZoomBubble);
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
                    chromeZoomBubble);
        }

        @Override
        public String toString() {
            return "CefBrowserSettings.Mutable{" + "size=" + (size == -1 ? "pending" : Long.toString(size)) + ", "
                    + "windowlessFrameRate=" + windowlessFrameRate + ", " + "standardFontFamily=" + standardFontFamily
                    + ", " + "fixedFontFamily=" + fixedFontFamily + ", " + "serifFontFamily=" + serifFontFamily + ", "
                    + "sansSerifFontFamily=" + sansSerifFontFamily + ", " + "cursiveFontFamily=" + cursiveFontFamily
                    + ", " + "fantasyFontFamily=" + fantasyFontFamily + ", " + "defaultFontSize=" + defaultFontSize
                    + ", " + "defaultFixedFontSize=" + defaultFixedFontSize + ", " + "minimumFontSize="
                    + minimumFontSize + ", " + "minimumLogicalFontSize=" + minimumLogicalFontSize + ", "
                    + "defaultEncoding=" + defaultEncoding + ", " + "remoteFonts=" + remoteFonts + ", " + "javascript="
                    + javascript + ", " + "javascriptCloseWindows=" + javascriptCloseWindows + ", "
                    + "javascriptAccessClipboard=" + javascriptAccessClipboard + ", " + "javascriptDomPaste="
                    + javascriptDomPaste + ", " + "imageLoading=" + imageLoading + ", " + "imageShrinkStandaloneToFit="
                    + imageShrinkStandaloneToFit + ", " + "textAreaResize=" + textAreaResize + ", " + "tabToLinks="
                    + tabToLinks + ", " + "localStorage=" + localStorage + ", " + "databasesDeprecated="
                    + databasesDeprecated + ", " + "webgl=" + webgl + ", " + "backgroundColor=" + backgroundColor + ", "
                    + "chromeStatusBubble=" + chromeStatusBubble + ", " + "chromeZoomBubble=" + chromeZoomBubble + "}";
        }
    }
}
