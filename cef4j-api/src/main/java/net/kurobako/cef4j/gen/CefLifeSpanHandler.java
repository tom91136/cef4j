// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Implement this interface to handle events related to browser life span. The methods of this class will be called on the UI thread unless otherwise indicated.
 * <p>Definition generated from cef_life_span_handler_capi.h
 * <pre>typedef struct _cef_life_span_handler_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_life_span_handler_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__life__span__handler_8h.html">cef_life_span_handler.h:46</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public interface CefLifeSpanHandler extends CefClientHandler {

    /**
     * Called on the UI thread before a new popup browser is created. The {@code browser} and {@code frame} values represent the source of the popup request (opener browser and frame). The {@code popup_id} value uniquely identifies the popup in the context of the opener browser. The {@code target_url} and {@code target_frame_name} values indicate where the popup browser should navigate and may be empty if not specified with the request. The {@code target_disposition} value indicates where the user intended to open the popup (e.g. current tab, new tab, etc). The {@code user_gesture} value will be {@code true} if the popup was opened via explicit user gesture (e.g. clicking a link) or {@code false} if the popup opened automatically (e.g. via the DomContentLoaded event). The {@code popupFeatures} structure contains additional information about the requested popup window. To allow creation of the popup browser optionally modify {@code windowInfo}, {@code client}, {@code settings} and {@code no_javascript_access} and return {@code false}. To cancel creation of the popup browser return {@code true}. The {@code client} and {@code settings} values will default to the source browser's values. If the {@code no_javascript_access} value is set to {@code false} the new browser will not be scriptable and may not be hosted in the same renderer process as the source browser. Any modifications to {@code windowInfo} will be ignored if the parent browser is wrapped in a CefBrowserView. The {@code extra_info} parameter provides an opportunity to specify extra information specific to the created popup browser that will be passed to {@link net.kurobako.cef4j.gen.CefRenderProcessHandler#onBrowserCreated(CefBrowser, CefDictionaryValue)} in the render process.
     * <p>
     * If popup browser creation succeeds then OnAfterCreated will be called for the new popup browser. If popup browser creation fails, and if the opener browser has not yet been destroyed, then OnBeforePopupAborted will be called for the opener browser. See OnBeforePopupAborted documentation for additional details.
     * <p>
     * A default popup window is created if this method returns {@code false} without setting a parent window handle via CefWindowInfo (for native-hosted popups), or without implementing {@link net.kurobako.cef4j.gen.views.CefBrowserViewDelegate#onPopupBrowserViewCreated(CefBrowserView, CefBrowserView, boolean)} (for Views-hosted popups). The default popup window type depends on the parent browser configuration:
     * <ul>
     * <li>Views-hosted parent: Creates a Views-hosted popup window.</li>
     * <li>Native-hosted Alloy style parent: Creates a native popup window.</li>
     * <li>Native-hosted Chrome style parent: Creates a Chrome UI popup window by</li>
     * </ul>
     * default; set CefSettings.use_views_default_popup to {@code true} to instead create a Views-hosted popup window.
     * <p>Definition generated from cef_life_span_handler_capi.h
     * <pre>int (CEF_CALLBACK* on_before_popup)(struct _cef_life_span_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, int popup_id, const cef_string_t* target_url, const cef_string_t* target_frame_name, cef_window_open_disposition_t target_disposition, int user_gesture, const cef_popup_features_t* popupFeatures, struct _cef_window_info_t* windowInfo, struct _cef_client_t** client, struct _cef_browser_settings_t* settings, struct _cef_dictionary_value_t** extra_info, int* no_javascript_access);</pre>
     *
     * @param targetUrl may be null
     * @param targetFrameName may be null
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__life__span__handler_8h.html">cef_life_span_handler.h:56</a>
     */
    default boolean onBeforePopup(@Nullable CefBrowser browser, @Nullable CefFrame frame, int popupId, @Nullable String targetUrl, @Nullable String targetFrameName, @Nonnull CefWindowOpenDisposition targetDisposition, boolean userGesture, @Nullable CefPopupFeatures popupFeatures, @Nonnull CefWindowInfo.Mutable windowInfo, @Nullable AtomicReference<CefClient> client, @Nonnull CefBrowserSettings.Mutable settings, @Nullable AtomicReference<CefDictionaryValue> extraInfo, int[] noJavascriptAccess) {
        return false;
    }

    /**
     * Called on the UI thread if a new popup browser is aborted. This only occurs if the popup is allowed in OnBeforePopup and creation fails before OnAfterCreated is called for the new popup browser. The {@code browser} value is the source of the popup request (opener browser). The {@code popup_id} value uniquely identifies the popup in the context of the opener browser, and is the same value that was passed to OnBeforePopup.
     * <p>
     * Any client state associated with pending popups should be cleared in OnBeforePopupAborted, OnAfterCreated of the popup browser, or OnBeforeClose of the opener browser. OnBeforeClose of the opener browser may be called before this method in cases where the opener is closing during popup creation, in which case net.kurobako.cef4j.gen.CefBrowserHost.isValid() will return {@code false} in this method.
     * <p>Definition generated from cef_life_span_handler_capi.h
     * <pre>void (CEF_CALLBACK* on_before_popup_aborted)(struct _cef_life_span_handler_t* self, struct _cef_browser_t* browser, int popup_id);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__life__span__handler_8h.html">cef_life_span_handler.h:116</a>
     */
    default void onBeforePopupAborted(@Nullable CefBrowser browser, int popupId) {
    }

    /**
     * Called on the UI thread before a new DevTools popup browser is created. The {@code browser} value represents the source of the popup request. Optionally modify {@code windowInfo}, {@code client}, {@code settings} and {@code extra_info} values. The {@code client}, {@code settings} and {@code extra_info} values will default to the source browser's values. Any modifications to {@code windowInfo} will be ignored if the parent browser is Views-hosted (wrapped in a CefBrowserView).
     * <p>
     * The {@code extra_info} parameter provides an opportunity to specify extra information specific to the created popup browser that will be passed to {@link net.kurobako.cef4j.gen.CefRenderProcessHandler#onBrowserCreated(CefBrowser, CefDictionaryValue)} in the render process. The existing {@code extra_info} object, if any, will be read-only but may be replaced with a new object.
     * <p>
     * Views-hosted source browsers will create Views-hosted DevTools popups unless {@code use_default_window} is set to to {@code true}. DevTools popups can be blocked by returning {@code true} from {@link net.kurobako.cef4j.gen.CefCommandHandler#onChromeCommand(CefBrowser, int, CefWindowOpenDisposition)} for IDC_DEV_TOOLS. Only used with Chrome style.
     * <p>Definition generated from cef_life_span_handler_capi.h
     * <pre>void (CEF_CALLBACK* on_before_dev_tools_popup)(struct _cef_life_span_handler_t* self, struct _cef_browser_t* browser, struct _cef_window_info_t* windowInfo, struct _cef_client_t** client, struct _cef_browser_settings_t* settings, struct _cef_dictionary_value_t** extra_info, int* use_default_window);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__life__span__handler_8h.html">cef_life_span_handler.h:135</a>
     */
    default void onBeforeDevToolsPopup(@Nullable CefBrowser browser, @Nonnull CefWindowInfo.Mutable windowInfo, @Nullable AtomicReference<CefClient> client, @Nonnull CefBrowserSettings.Mutable settings, @Nullable AtomicReference<CefDictionaryValue> extraInfo, int[] useDefaultWindow) {
    }

    /**
     * Called after a new browser is created. It is now safe to begin performing actions with {@code browser}. CefFrameHandler callbacks related to initial main frame creation will arrive before this callback. See CefFrameHandler documentation for additional usage information.
     * <p>Definition generated from cef_life_span_handler_capi.h
     * <pre>void (CEF_CALLBACK* on_after_created)(struct _cef_life_span_handler_t* self, struct _cef_browser_t* browser);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__life__span__handler_8h.html">cef_life_span_handler.h:162</a>
     */
    default void onAfterCreated(@Nullable CefBrowser browser) {
    }

    /**
     * Called when an Alloy style browser is ready to be closed, meaning that the close has already been initiated and that JavaScript unload handlers have already executed or should be ignored. This may result directly from a call to CefBrowserHost::[Try]CloseBrowser() or indirectly if the browser's top-level parent window was created by CEF and the user attempts to close that window (by clicking the 'X', for example). DoClose() will not be called if the browser's host window/view has already been destroyed (via parent window/view hierarchy tear-down, for example), as it is no longer possible to customize the close behavior at that point.
     * <p>
     * An application should handle top-level parent window close notifications by calling {@link net.kurobako.cef4j.gen.CefBrowserHost#tryCloseBrowser()} or {@link net.kurobako.cef4j.gen.CefBrowserHost#closeBrowser(boolean)}({@code false}) instead of allowing the window to close immediately (see the examples below). This gives CEF an opportunity to process JavaScript unload handlers and optionally cancel the close before DoClose() is called.
     * <p>
     * When windowed rendering is enabled CEF will create an internal child window/view to host the browser. In that case returning {@code false} from DoClose() will send the standard close notification to the browser's top-level parent window (e.g. WM_CLOSE on Windows, performClose: on OS X, "delete_event" on Linux or {@link net.kurobako.cef4j.gen.views.CefWindowDelegate#canClose(CefWindow)} callback from Views).
     * <p>
     * When windowed rendering is disabled there is no internal window/view and returning {@code false} from DoClose() will cause the browser object to be destroyed immediately.
     * <p>
     * If the browser's top-level parent window requires a non-standard close notification then send that notification from DoClose() and return {@code true}. You are still required to complete the browser close as soon as possible (either by calling [Try]CloseBrowser() or by proceeding with window/view hierarchy tear-down), otherwise the browser will be left in a partially closed state that interferes with proper functioning. Top-level windows created on the browser process UI thread can alternately call {@link net.kurobako.cef4j.gen.CefBrowserHost#isReadyToBeClosed()} in the close handler to check close status instead of relying on custom DoClose() handling. See documentation on that method for additional details.
     * <p>
     * The {@link net.kurobako.cef4j.gen.CefLifeSpanHandler#onBeforeClose(CefBrowser)} method will be called after DoClose() (if DoClose() is called) and immediately before the browser object is destroyed. The application should only exit after OnBeforeClose() has been called for all existing browsers.
     * <p>
     * The below examples describe what should happen during window close when the browser is parented to an application-provided top-level window.
     * <p>
     * Example 1: Using {@link net.kurobako.cef4j.gen.CefBrowserHost#tryCloseBrowser()}. This is recommended for clients using standard close handling and windows created on the browser process UI thread. 1.  User clicks the window close button which sends a close notification to the application's top-level window. 2.  Application's top-level window receives the close notification and calls TryCloseBrowser() (similar to calling CloseBrowser({@code false})). TryCloseBrowser() returns {@code false} so the client cancels the window close. 3.  JavaScript 'onbeforeunload' handler executes and shows the close confirmation dialog (which can be overridden via CefJSDialogHandler.onBeforeUnloadDialog()). 4.  User approves the close. 5.  JavaScript 'onunload' handler executes. 6.  Application's DoClose() handler is called and returns {@code false} by default. 7.  CEF sends a close notification to the application's top-level window (because DoClose() returned {@code false}). 8.  Application's top-level window receives the close notification and calls TryCloseBrowser(). TryCloseBrowser() returns {@code true} so the client allows the window close. 9.  Application's top-level window is destroyed, triggering destruction of the child browser window. 10. Application's OnBeforeClose() handler is called and the browser object is destroyed. 11. Application exits by calling CefQuitMessageLoop() if no other browsers exist.
     * <p>
     * Example 2: Using {@link net.kurobako.cef4j.gen.CefBrowserHost#closeBrowser(boolean)}({@code false}) and implementing the DoClose() callback. This is recommended for clients using non-standard close handling or windows that were not created on the browser process UI thread. 1.  User clicks the window close button which sends a close notification to the application's top-level window. 2.  Application's top-level window receives the close notification and: A. Calls {@link net.kurobako.cef4j.gen.CefBrowserHost#closeBrowser(boolean)}({@code false}). B. Cancels the window close. 3.  JavaScript 'onbeforeunload' handler executes and shows the close confirmation dialog (which can be overridden via CefJSDialogHandler.onBeforeUnloadDialog()). 4.  User approves the close. 5.  JavaScript 'onunload' handler executes. 6.  Application's DoClose() handler is called. Application will: A. Set a flag to indicate that the next top-level window close attempt will be allowed. B. Return {@code false}. 7.  CEF sends a close notification to the application's top-level window (because DoClose() returned {@code false}). 8.  Application's top-level window receives the close notification and allows the window to close based on the flag from #6A. 9.  Application's top-level window is destroyed, triggering destruction of the child browser window. 10. Application's OnBeforeClose() handler is called and the browser object is destroyed. 11. Application exits by calling CefQuitMessageLoop() if no other browsers exist.
     * <p>Definition generated from cef_life_span_handler_capi.h
     * <pre>int (CEF_CALLBACK* do_close)(struct _cef_life_span_handler_t* self, struct _cef_browser_t* browser);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__life__span__handler_8h.html">cef_life_span_handler.h:171</a>
     */
    default boolean doClose(@Nullable CefBrowser browser) {
        return false;
    }

    /**
     * Called just before a browser is destroyed. Release all references to the browser object and do not attempt to execute any methods on the browser object (other than IsValid, GetIdentifier or IsSame) after this callback returns. CefFrameHandler callbacks related to final main frame destruction, and OnBeforePopupAborted callbacks for any pending popups, will arrive after this callback and {@link net.kurobako.cef4j.gen.CefBrowser#isValid()} will return {@code false} at that time. Any in-progress network requests associated with {@code browser} will be aborted when the browser is destroyed, and CefResourceRequestHandler callbacks related to those requests may still arrive on the IO thread after this callback. See CefFrameHandler and DoClose() documentation for additional usage information.
     * <p>Definition generated from cef_life_span_handler_capi.h
     * <pre>void (CEF_CALLBACK* on_before_close)(struct _cef_life_span_handler_t* self, struct _cef_browser_t* browser);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__life__span__handler_8h.html">cef_life_span_handler.h:279</a>
     */
    default void onBeforeClose(@Nullable CefBrowser browser) {
    }
    /**
     * Composite that fans callbacks out to every registered delegate. {@code void} methods invoke all
     * delegates in order; {@code boolean} methods short-circuit on the first {@code true}; handler-returning
     * {@code Optional}s collect every non-empty delegate and wrap them in the handler's own {@code Delegating}
     * wrapper; other {@code Optional}s pick the first non-empty; any other return type yields the first
     * delegate's value.
     */
    class Delegating implements CefLifeSpanHandler {
        private final java.util.List<CefLifeSpanHandler> delegates;

        public Delegating(java.util.List<CefLifeSpanHandler> delegates) {
            this.delegates = java.util.List.copyOf(delegates);
        }

        @Override
        public boolean onBeforePopup(@Nullable CefBrowser browser, @Nullable CefFrame frame, int popupId, @Nullable String targetUrl, @Nullable String targetFrameName, @Nonnull CefWindowOpenDisposition targetDisposition, boolean userGesture, @Nullable CefPopupFeatures popupFeatures, @Nonnull CefWindowInfo.Mutable windowInfo, @Nullable AtomicReference<CefClient> client, @Nonnull CefBrowserSettings.Mutable settings, @Nullable AtomicReference<CefDictionaryValue> extraInfo, int[] noJavascriptAccess) {
            for (CefLifeSpanHandler d : delegates) {
                if (d.onBeforePopup(browser, frame, popupId, targetUrl, targetFrameName, targetDisposition, userGesture, popupFeatures, windowInfo, client, settings, extraInfo, noJavascriptAccess)) return true;
            }
            if (!delegates.isEmpty()) return false;
            return false;
        }

        @Override
        public void onBeforePopupAborted(@Nullable CefBrowser browser, int popupId) {
            for (CefLifeSpanHandler d : delegates) d.onBeforePopupAborted(browser, popupId);
        }

        @Override
        public void onBeforeDevToolsPopup(@Nullable CefBrowser browser, @Nonnull CefWindowInfo.Mutable windowInfo, @Nullable AtomicReference<CefClient> client, @Nonnull CefBrowserSettings.Mutable settings, @Nullable AtomicReference<CefDictionaryValue> extraInfo, int[] useDefaultWindow) {
            for (CefLifeSpanHandler d : delegates) d.onBeforeDevToolsPopup(browser, windowInfo, client, settings, extraInfo, useDefaultWindow);
        }

        @Override
        public void onAfterCreated(@Nullable CefBrowser browser) {
            for (CefLifeSpanHandler d : delegates) d.onAfterCreated(browser);
        }

        @Override
        public boolean doClose(@Nullable CefBrowser browser) {
            for (CefLifeSpanHandler d : delegates) {
                if (d.doClose(browser)) return true;
            }
            if (!delegates.isEmpty()) return false;
            return false;
        }

        @Override
        public void onBeforeClose(@Nullable CefBrowser browser) {
            for (CefLifeSpanHandler d : delegates) d.onBeforeClose(browser);
        }
    }

}
