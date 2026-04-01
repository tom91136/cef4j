// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Implement this interface to handle events related to browser requests. The methods of this class will be called on
 * the thread indicated.
 *
 * <p>Definition generated from cef_request_handler_capi.h
 *
 * <pre>typedef struct _cef_request_handler_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_request_handler_t;</pre>
 *
 * @see <a
 *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request__handler_8h.html">cef_request_handler.h:68</a>
 */
public interface CefRequestHandler extends CefClientHandler {

    /**
     * Called on the UI thread before browser navigation. Return {@code true} to cancel the navigation or {@code false}
     * to allow the navigation to proceed. The {@code request} object cannot be modified in this callback.
     * {@link CefLoadHandler#onLoadingStateChange(CefBrowser, boolean, boolean, boolean)} will be called twice in all
     * cases. If the navigation is allowed {@link CefLoadHandler#onLoadStart(CefBrowser, CefFrame, CefTransitionType)}
     * and {@link CefLoadHandler#onLoadEnd(CefBrowser, CefFrame, int)} will be called. If the navigation is canceled
     * {@link CefLoadHandler#onLoadError(CefBrowser, CefFrame, CefErrorCode, String, String)} will be called with an
     * {@code errorCode} value of {@code ERR_ABORTED}. The {@code user_gesture} value will be {@code true} if the
     * browser navigated via explicit user gesture (e.g. clicking a link) or {@code false} if it navigated automatically
     * (e.g. via the DomContentLoaded event).
     *
     * <p>Definition generated from cef_request_handler_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* on_before_browse)(struct _cef_request_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, struct _cef_request_t* request, int user_gesture, int is_redirect);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request__handler_8h.html">cef_request_handler.h:79</a>
     */
    default boolean onBeforeBrowse(
            @Nonnull CefBrowser browser,
            @Nonnull CefFrame frame,
            @Nonnull CefRequest request,
            boolean userGesture,
            boolean isRedirect) {
        return false;
    }

    /**
     * Called on the UI thread before OnBeforeBrowse in certain limited cases where navigating a new or different
     * browser might be desirable. This includes user-initiated navigation that might open in a special way (e.g. links
     * clicked via middle-click or ctrl + left-click) and certain types of cross-origin navigation initiated from the
     * renderer process (e.g. navigating the top-level frame to/from a file URL). The {@code browser} and {@code frame}
     * values represent the source of the navigation. The {@code target_disposition} value indicates where the user
     * intended to navigate the browser based on standard Chromium behaviors (e.g. current tab, new tab, etc). The
     * {@code user_gesture} value will be {@code true} if the browser navigated via explicit user gesture (e.g. clicking
     * a link) or {@code false} if it navigated automatically (e.g. via the DomContentLoaded event). Return {@code true}
     * to cancel the navigation or {@code false} to allow the navigation to proceed in the source browser's top-level
     * frame.
     *
     * <p>Definition generated from cef_request_handler_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* on_open_urlfrom_tab)(struct _cef_request_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, const cef_string_t* target_url, cef_window_open_disposition_t target_disposition, int user_gesture);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request__handler_8h.html">cef_request_handler.h:100</a>
     */
    default boolean onOpenUrlFromTab(
            @Nonnull CefBrowser browser,
            @Nonnull CefFrame frame,
            @Nonnull String targetUrl,
            @Nonnull CefWindowOpenDisposition targetDisposition,
            boolean userGesture) {
        return false;
    }

    /**
     * Called on the IO thread when the browser needs credentials from the user. {@code isProxy} indicates whether the
     * host is a proxy server. {@code host} contains the hostname and {@code port} contains the port number. Return
     * {@code true} to continue the request and call CefAuthCallback.continue() when the authentication information is
     * available. If the request has an associated browser/frame then returning {@code false} will result in a call to
     * GetAuthCredentials on the CefRequestHandler associated with that browser, if any. Otherwise, returning
     * {@code false} will cancel the request immediately. This method will only be called for requests initiated from
     * the browser process.
     *
     * <p>Definition generated from cef_request_handler_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* get_auth_credentials)(struct _cef_request_handler_t* self, struct _cef_browser_t* browser, const cef_string_t* origin_url, int isProxy, const cef_string_t* host, int port, const cef_string_t* realm, const cef_string_t* scheme, struct _cef_auth_callback_t* callback);
     * </pre>
     *
     * @param realm may be null
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__urlrequest_8h.html">cef_urlrequest.h:178</a>
     */
    default boolean getAuthCredentials(
            @Nonnull CefBrowser browser,
            @Nonnull String originUrl,
            boolean isproxy,
            @Nonnull String host,
            int port,
            @Nullable String realm,
            @Nonnull String scheme,
            @Nonnull CefAuthCallback callback) {
        return false;
    }

    /**
     * Called on the UI thread to handle requests for URLs with an invalid SSL certificate. Return {@code true} and call
     * CefCallback methods either in this method or at a later time to continue or cancel the request. Return
     * {@code false} to cancel the request immediately. If cef_settings_t.ignore_certificate_errors is set all invalid
     * certificates will be accepted without calling this method.
     *
     * <p>Definition generated from cef_request_handler_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* on_certificate_error)(struct _cef_request_handler_t* self, struct _cef_browser_t* browser, cef_errorcode_t cert_error, const cef_string_t* request_url, struct _cef_sslinfo_t* ssl_info, struct _cef_callback_t* callback);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request__handler_8h.html">cef_request_handler.h:176</a>
     */
    default boolean onCertificateError(
            @Nonnull CefBrowser browser,
            @Nonnull CefErrorCode certError,
            @Nonnull String requestUrl,
            @Nonnull CefSslInfo sslInfo,
            @Nonnull CefCallback callback) {
        return false;
    }

    /**
     * Called on the UI thread when a client certificate is being requested for authentication. Return {@code false} to
     * use the default behavior. If the {@code certificates} list is not empty the default behavior will be to display a
     * dialog for certificate selection. If the {@code certificates} list is empty then the default behavior will be not
     * to show a dialog and it will continue without using any certificate. Return {@code true} and call
     * {@link CefSelectClientCertificateCallback#select(CefX509Certificate)} either in this method or at a later time to
     * select a certificate. Do not call Select or call it with {@code null} to continue without using any certificate.
     * {@code isProxy} indicates whether the host is an HTTPS proxy or the origin server. {@code host} and {@code port}
     * contains the hostname and port of the SSL server. {@code certificates} is the list of certificates to choose
     * from; this list has already been pruned by Chromium so that it only contains certificates from issuers that the
     * server trusts.
     *
     * <p>Definition generated from cef_request_handler_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* on_select_client_certificate)(struct _cef_request_handler_t* self, struct _cef_browser_t* browser, int isProxy, const cef_string_t* host, int port, size_t certificatesCount, struct _cef_x509_certificate_t* const* certificates, struct _cef_select_client_certificate_callback_t* callback);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request__handler_8h.html">cef_request_handler.h:193</a>
     */
    default boolean onSelectClientCertificate(
            @Nonnull CefBrowser browser,
            boolean isproxy,
            @Nonnull String host,
            int port,
            long certificatescount,
            @Nonnull CefX509Certificate[] certificates,
            @Nonnull CefSelectClientCertificateCallback callback) {
        return false;
    }

    /**
     * Called on the browser process UI thread when the render view associated with {@code browser} is ready to
     * receive/handle IPC messages in the render process.
     *
     * <p>Definition generated from cef_request_handler_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* on_render_view_ready)(struct _cef_request_handler_t* self, struct _cef_browser_t* browser);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request__handler_8h.html">cef_request_handler.h:220</a>
     */
    default void onRenderViewReady(@Nonnull CefBrowser browser) {}

    /**
     * Called on the browser process UI thread when the render process is unresponsive as indicated by a lack of input
     * event processing for at least 15 seconds. Return {@code false} for the default behavior which is to continue
     * waiting with Alloy style or display of the "Page unresponsive" dialog with Chrome style. Return {@code true} and
     * don't execute the callback to continue waiting without display of the Chrome style dialog. Return {@code true}
     * and call {@link CefUnresponsiveProcessCallback#cefWait()} either in this method or at a later time to reset the
     * wait timer. In cases where you continue waiting there may be another call to this method if the process remains
     * unresponsive. Return {@code true} and call {@link CefUnresponsiveProcessCallback#terminate()} either in this
     * method or at a later time to terminate the unresponsive process, resulting in a call to
     * OnRenderProcessTerminated. OnRenderProcessResponsive will be called if the process becomes responsive after this
     * method is called. This functionality depends on the hang monitor which can be disabled by passing the
     * `--disable-hang-monitor` command-line flag.
     *
     * <p>Definition generated from cef_request_handler_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* on_render_process_unresponsive)(struct _cef_request_handler_t* self, struct _cef_browser_t* browser, struct _cef_unresponsive_process_callback_t* callback);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request__handler_8h.html">cef_request_handler.h:228</a>
     */
    default boolean onRenderProcessUnresponsive(
            @Nonnull CefBrowser browser, @Nonnull CefUnresponsiveProcessCallback callback) {
        return false;
    }

    /**
     * Called on the browser process UI thread when the render process becomes responsive after previously being
     * unresponsive. See documentation on OnRenderProcessUnresponsive.
     *
     * <p>Definition generated from cef_request_handler_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* on_render_process_responsive)(struct _cef_request_handler_t* self, struct _cef_browser_t* browser);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request__handler_8h.html">cef_request_handler.h:253</a>
     */
    default void onRenderProcessResponsive(@Nonnull CefBrowser browser) {}

    /**
     * Called on the browser process UI thread when the render process terminates unexpectedly. {@code status} indicates
     * how the process terminated. {@code error_code} and {@code error_string} represent the error that would be
     * displayed in Chrome's "Aw, Snap!" view. Possible {@code error_code} values include cef_resultcode_t non-normal
     * exit values and platform-specific crash values (for example, a Posix signal or Windows hardware exception).
     *
     * <p>Definition generated from cef_request_handler_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* on_render_process_terminated)(struct _cef_request_handler_t* self, struct _cef_browser_t* browser, cef_termination_status_t status, int error_code, const cef_string_t* error_string);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request__handler_8h.html">cef_request_handler.h:261</a>
     */
    default void onRenderProcessTerminated(
            @Nonnull CefBrowser browser,
            @Nonnull CefTerminationStatus status,
            int errorCode,
            @Nonnull String errorString) {}

    /**
     * Called on the browser process UI thread when the window.document object of the main frame has been created.
     *
     * <p>Definition generated from cef_request_handler_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* on_document_available_in_main_frame)(struct _cef_request_handler_t* self, struct _cef_browser_t* browser);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request__handler_8h.html">cef_request_handler.h:275</a>
     */
    default void onDocumentAvailableInMainFrame(@Nonnull CefBrowser browser) {}
}
