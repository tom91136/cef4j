// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Implement this interface to handle events related to browser requests. The methods of this class will be called on the thread indicated.
 * <p>Definition generated from cef_request_handler_capi.h
 * <pre>typedef struct _cef_request_handler_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_request_handler_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request__handler_8h.html">cef_request_handler.h:68</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public interface CefRequestHandler extends CefClientHandler {

    /**
     * Called on the UI thread before browser navigation. Return {@code true} to cancel the navigation or {@code false} to allow the navigation to proceed. The {@code request} object cannot be modified in this callback. {@link net.kurobako.cef4j.gen.CefLoadHandler#onLoadingStateChange(CefBrowser, boolean, boolean, boolean)} will be called twice in all cases. If the navigation is allowed {@link net.kurobako.cef4j.gen.CefLoadHandler#onLoadStart(CefBrowser, CefFrame, CefTransitionType)} and {@link net.kurobako.cef4j.gen.CefLoadHandler#onLoadEnd(CefBrowser, CefFrame, int)} will be called. If the navigation is canceled {@link net.kurobako.cef4j.gen.CefLoadHandler#onLoadError(CefBrowser, CefFrame, CefErrorCode, String, String)} will be called with an {@code errorCode} value of {@code ERR_ABORTED}. The {@code user_gesture} value will be {@code true} if the browser navigated via explicit user gesture (e.g. clicking a link) or {@code false} if it navigated automatically (e.g. via the DomContentLoaded event).
     * <p>Definition generated from cef_request_handler_capi.h
     * <pre>int (CEF_CALLBACK* on_before_browse)(struct _cef_request_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, struct _cef_request_t* request, int user_gesture, int is_redirect);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request__handler_8h.html">cef_request_handler.h:79</a>
     */
    default boolean onBeforeBrowse(@Nullable CefBrowser browser, @Nullable CefFrame frame, @Nullable CefRequest request, boolean userGesture, boolean isRedirect) {
        return false;
    }

    /**
     * Called on the UI thread before OnBeforeBrowse in certain limited cases where navigating a new or different browser might be desirable. This includes user-initiated navigation that might open in a special way (e.g. links clicked via middle-click or ctrl + left-click) and certain types of cross-origin navigation initiated from the renderer process (e.g. navigating the top-level frame to/from a file URL). The {@code browser} and {@code frame} values represent the source of the navigation. The {@code target_disposition} value indicates where the user intended to navigate the browser based on standard Chromium behaviors (e.g. current tab, new tab, etc). The {@code user_gesture} value will be {@code true} if the browser navigated via explicit user gesture (e.g. clicking a link) or {@code false} if it navigated automatically (e.g. via the DomContentLoaded event). Return {@code true} to cancel the navigation or {@code false} to allow the navigation to proceed in the source browser's top-level frame.
     * <p>Definition generated from cef_request_handler_capi.h
     * <pre>int (CEF_CALLBACK* on_open_urlfrom_tab)(struct _cef_request_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, const cef_string_t* target_url, cef_window_open_disposition_t target_disposition, int user_gesture);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request__handler_8h.html">cef_request_handler.h:100</a>
     */
    default boolean onOpenUrlFromTab(@Nullable CefBrowser browser, @Nullable CefFrame frame, @Nullable String targetUrl, @Nonnull CefWindowOpenDisposition targetDisposition, boolean userGesture) {
        return false;
    }

    /**
     * Called on the browser process IO thread before a resource request is initiated. The {@code browser} and {@code frame} values represent the source of the request. {@code request} represents the request contents and cannot be modified in this callback. {@code is_navigation} will be {@code true} if the resource request is a navigation. {@code is_download} will be {@code true} if the resource request is a download. {@code request_initiator} is the origin (scheme + domain) of the page that initiated the request. Set {@code disable_default_handling} to {@code true} to disable default handling of the request, in which case it will need to be handled via {@link net.kurobako.cef4j.gen.CefResourceRequestHandler#getResourceHandler(CefBrowser, CefFrame, CefRequest)} or it will be canceled. To allow the resource load to proceed with default handling return {@code null}. To specify a handler for the resource return a CefResourceRequestHandler object. If this callback returns {@code null} the same method will be called on the associated CefRequestContextHandler, if any.
     * <p>Definition generated from cef_request_handler_capi.h
     * <pre>cef_resource_request_handler_t* (CEF_CALLBACK* get_resource_request_handler)(struct _cef_request_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, struct _cef_request_t* request, int is_navigation, int is_download, const cef_string_t* request_initiator, int* disable_default_handling);</pre>
     *
     * @param requestInitiator may be null
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request__handler_8h.html">cef_request_handler.h:125</a>
     */
    default Optional<CefResourceRequestHandler> getResourceRequestHandler(@Nullable CefBrowser browser, @Nullable CefFrame frame, @Nullable CefRequest request, boolean isNavigation, boolean isDownload, @Nullable String requestInitiator, int[] disableDefaultHandling) {
        return Optional.empty();
    }

    /**
     * Called on the IO thread when the browser needs credentials from the user. {@code origin_url} is the origin making this authentication request. {@code isProxy} indicates whether the host is a proxy server. {@code host} contains the hostname and {@code port} contains the port number. {@code realm} is the realm of the challenge and may be empty. {@code scheme} is the authentication scheme used, such as "basic" or "digest", and will be empty if the source of the request is an FTP server. Return {@code true} to continue the request and call net.kurobako.cef4j.gen.CefAuthCallback.continue() either in this method or at a later time when the authentication information is available. Return {@code false} to cancel the request immediately.
     * <p>Definition generated from cef_request_handler_capi.h
     * <pre>int (CEF_CALLBACK* get_auth_credentials)(struct _cef_request_handler_t* self, struct _cef_browser_t* browser, const cef_string_t* origin_url, int isProxy, const cef_string_t* host, int port, const cef_string_t* realm, const cef_string_t* scheme, struct _cef_auth_callback_t* callback);</pre>
     *
     * @param realm may be null
     * @param scheme may be null
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request__handler_8h.html">cef_request_handler.h:152</a>
     */
    default boolean getAuthCredentials(@Nullable CefBrowser browser, @Nullable String originUrl, boolean isProxy, @Nullable String host, int port, @Nullable String realm, @Nullable String scheme, @Nullable CefAuthCallback callback) {
        return false;
    }

    /**
     * Called on the UI thread to handle requests for URLs with an invalid SSL certificate. Return {@code true} and call CefCallback methods either in this method or at a later time to continue or cancel the request. Return {@code false} to cancel the request immediately. If cef_settings_t.ignore_certificate_errors is set all invalid certificates will be accepted without calling this method.
     * <p>Definition generated from cef_request_handler_capi.h
     * <pre>int (CEF_CALLBACK* on_certificate_error)(struct _cef_request_handler_t* self, struct _cef_browser_t* browser, cef_errorcode_t cert_error, const cef_string_t* request_url, struct _cef_sslinfo_t* ssl_info, struct _cef_callback_t* callback);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request__handler_8h.html">cef_request_handler.h:176</a>
     */
    default boolean onCertificateError(@Nullable CefBrowser browser, @Nonnull CefErrorCode certError, @Nullable String requestUrl, @Nullable CefSslInfo sslInfo, @Nullable CefCallback callback) {
        return false;
    }

    /**
     * Called on the UI thread when a client certificate is being requested for authentication. Return {@code false} to use the default behavior.  If the {@code certificates} list is not empty the default behavior will be to display a dialog for certificate selection. If the {@code certificates} list is empty then the default behavior will be not to show a dialog and it will continue without using any certificate. Return {@code true} and call {@link net.kurobako.cef4j.gen.CefSelectClientCertificateCallback#select(CefX509Certificate)} either in this method or at a later time to select a certificate. Do not call Select or call it with {@code null} to continue without using any certificate. {@code isProxy} indicates whether the host is an HTTPS proxy or the origin server. {@code host} and {@code port} contains the hostname and port of the SSL server. {@code certificates} is the list of certificates to choose from; this list has already been pruned by Chromium so that it only contains certificates from issuers that the server trusts.
     * <p>Definition generated from cef_request_handler_capi.h
     * <pre>int (CEF_CALLBACK* on_select_client_certificate)(struct _cef_request_handler_t* self, struct _cef_browser_t* browser, int isProxy, const cef_string_t* host, int port, size_t certificatesCount, struct _cef_x509_certificate_t* const* certificates, struct _cef_select_client_certificate_callback_t* callback);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request__handler_8h.html">cef_request_handler.h:193</a>
     */
    default boolean onSelectClientCertificate(@Nullable CefBrowser browser, boolean isProxy, @Nullable String host, int port, long certificatesCount, @Nullable CefX509Certificate[] certificates, @Nullable CefSelectClientCertificateCallback callback) {
        return false;
    }

    /**
     * Called on the browser process UI thread when the render view associated with {@code browser} is ready to receive/handle IPC messages in the render process.
     * <p>Definition generated from cef_request_handler_capi.h
     * <pre>void (CEF_CALLBACK* on_render_view_ready)(struct _cef_request_handler_t* self, struct _cef_browser_t* browser);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request__handler_8h.html">cef_request_handler.h:220</a>
     */
    default void onRenderViewReady(@Nullable CefBrowser browser) {
    }

    /**
     * Called on the browser process UI thread when the render process is unresponsive as indicated by a lack of input event processing for at least 15 seconds. Return {@code false} for the default behavior which is to continue waiting with Alloy style or display of the "Page unresponsive" dialog with Chrome style. Return {@code true} and don't execute the callback to continue waiting without display of the Chrome style dialog. Return {@code true} and call {@link net.kurobako.cef4j.gen.CefUnresponsiveProcessCallback#cefWait()} either in this method or at a later time to reset the wait timer. In cases where you continue waiting there may be another call to this method if the process remains unresponsive. Return {@code true} and call {@link net.kurobako.cef4j.gen.CefUnresponsiveProcessCallback#terminate()} either in this method or at a later time to terminate the unresponsive process, resulting in a call to OnRenderProcessTerminated. OnRenderProcessResponsive will be called if the process becomes responsive after this method is called. This functionality depends on the hang monitor which can be disabled by passing the `--disable-hang-monitor` command-line flag.
     * <p>Definition generated from cef_request_handler_capi.h
     * <pre>int (CEF_CALLBACK* on_render_process_unresponsive)(struct _cef_request_handler_t* self, struct _cef_browser_t* browser, struct _cef_unresponsive_process_callback_t* callback);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request__handler_8h.html">cef_request_handler.h:228</a>
     */
    default boolean onRenderProcessUnresponsive(@Nullable CefBrowser browser, @Nullable CefUnresponsiveProcessCallback callback) {
        return false;
    }

    /**
     * Called on the browser process UI thread when the render process becomes responsive after previously being unresponsive. See documentation on OnRenderProcessUnresponsive.
     * <p>Definition generated from cef_request_handler_capi.h
     * <pre>void (CEF_CALLBACK* on_render_process_responsive)(struct _cef_request_handler_t* self, struct _cef_browser_t* browser);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request__handler_8h.html">cef_request_handler.h:253</a>
     */
    default void onRenderProcessResponsive(@Nullable CefBrowser browser) {
    }

    /**
     * Called on the browser process UI thread when the render process terminates unexpectedly. {@code status} indicates how the process terminated. {@code error_code} and {@code error_string} represent the error that would be displayed in Chrome's "Aw, Snap!" view. Possible {@code error_code} values include cef_resultcode_t non-normal exit values and platform-specific crash values (for example, a Posix signal or Windows hardware exception).
     * <p>Definition generated from cef_request_handler_capi.h
     * <pre>void (CEF_CALLBACK* on_render_process_terminated)(struct _cef_request_handler_t* self, struct _cef_browser_t* browser, cef_termination_status_t status, int error_code, const cef_string_t* error_string);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request__handler_8h.html">cef_request_handler.h:261</a>
     */
    default void onRenderProcessTerminated(@Nullable CefBrowser browser, @Nonnull CefTerminationStatus status, int errorCode, @Nullable String errorString) {
    }

    /**
     * Called on the browser process UI thread when the window.document object of the main frame has been created.
     * <p>Definition generated from cef_request_handler_capi.h
     * <pre>void (CEF_CALLBACK* on_document_available_in_main_frame)(struct _cef_request_handler_t* self, struct _cef_browser_t* browser);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request__handler_8h.html">cef_request_handler.h:275</a>
     */
    default void onDocumentAvailableInMainFrame(@Nullable CefBrowser browser) {
    }
    /**
     * Composite that fans callbacks out to every registered delegate. {@code void} methods invoke all
     * delegates in order; {@code boolean} methods short-circuit on the first {@code true}; handler-returning
     * {@code Optional}s collect every non-empty delegate and wrap them in the handler's own {@code Delegating}
     * wrapper; other {@code Optional}s pick the first non-empty; any other return type yields the first
     * delegate's value.
     */
    class Delegating implements CefRequestHandler {
        private final java.util.List<CefRequestHandler> delegates;

        public Delegating(java.util.List<CefRequestHandler> delegates) {
            this.delegates = java.util.List.copyOf(delegates);
        }

        @Override
        public boolean onBeforeBrowse(@Nullable CefBrowser browser, @Nullable CefFrame frame, @Nullable CefRequest request, boolean userGesture, boolean isRedirect) {
            for (CefRequestHandler d : delegates) {
                if (d.onBeforeBrowse(browser, frame, request, userGesture, isRedirect)) return true;
            }
            if (!delegates.isEmpty()) return false;
            return false;
        }

        @Override
        public boolean onOpenUrlFromTab(@Nullable CefBrowser browser, @Nullable CefFrame frame, @Nullable String targetUrl, @Nonnull CefWindowOpenDisposition targetDisposition, boolean userGesture) {
            for (CefRequestHandler d : delegates) {
                if (d.onOpenUrlFromTab(browser, frame, targetUrl, targetDisposition, userGesture)) return true;
            }
            if (!delegates.isEmpty()) return false;
            return false;
        }

        @Override
        public Optional<CefResourceRequestHandler> getResourceRequestHandler(@Nullable CefBrowser browser, @Nullable CefFrame frame, @Nullable CefRequest request, boolean isNavigation, boolean isDownload, @Nullable String requestInitiator, int[] disableDefaultHandling) {
            java.util.ArrayList<CefResourceRequestHandler> collected = new java.util.ArrayList<>();
            for (CefRequestHandler d : delegates) d.getResourceRequestHandler(browser, frame, request, isNavigation, isDownload, requestInitiator, disableDefaultHandling).ifPresent(collected::add);
            return collected.isEmpty()
                    ? Optional.empty()
                    : Optional.of(new CefResourceRequestHandler.Delegating(collected));
        }

        @Override
        public boolean getAuthCredentials(@Nullable CefBrowser browser, @Nullable String originUrl, boolean isProxy, @Nullable String host, int port, @Nullable String realm, @Nullable String scheme, @Nullable CefAuthCallback callback) {
            for (CefRequestHandler d : delegates) {
                if (d.getAuthCredentials(browser, originUrl, isProxy, host, port, realm, scheme, callback)) return true;
            }
            if (!delegates.isEmpty()) return false;
            return false;
        }

        @Override
        public boolean onCertificateError(@Nullable CefBrowser browser, @Nonnull CefErrorCode certError, @Nullable String requestUrl, @Nullable CefSslInfo sslInfo, @Nullable CefCallback callback) {
            for (CefRequestHandler d : delegates) {
                if (d.onCertificateError(browser, certError, requestUrl, sslInfo, callback)) return true;
            }
            if (!delegates.isEmpty()) return false;
            return false;
        }

        @Override
        public boolean onSelectClientCertificate(@Nullable CefBrowser browser, boolean isProxy, @Nullable String host, int port, long certificatesCount, @Nullable CefX509Certificate[] certificates, @Nullable CefSelectClientCertificateCallback callback) {
            for (CefRequestHandler d : delegates) {
                if (d.onSelectClientCertificate(browser, isProxy, host, port, certificatesCount, certificates, callback)) return true;
            }
            if (!delegates.isEmpty()) return false;
            return false;
        }

        @Override
        public void onRenderViewReady(@Nullable CefBrowser browser) {
            for (CefRequestHandler d : delegates) d.onRenderViewReady(browser);
        }

        @Override
        public boolean onRenderProcessUnresponsive(@Nullable CefBrowser browser, @Nullable CefUnresponsiveProcessCallback callback) {
            for (CefRequestHandler d : delegates) {
                if (d.onRenderProcessUnresponsive(browser, callback)) return true;
            }
            if (!delegates.isEmpty()) return false;
            return false;
        }

        @Override
        public void onRenderProcessResponsive(@Nullable CefBrowser browser) {
            for (CefRequestHandler d : delegates) d.onRenderProcessResponsive(browser);
        }

        @Override
        public void onRenderProcessTerminated(@Nullable CefBrowser browser, @Nonnull CefTerminationStatus status, int errorCode, @Nullable String errorString) {
            for (CefRequestHandler d : delegates) d.onRenderProcessTerminated(browser, status, errorCode, errorString);
        }

        @Override
        public void onDocumentAvailableInMainFrame(@Nullable CefBrowser browser) {
            for (CefRequestHandler d : delegates) d.onDocumentAvailableInMainFrame(browser);
        }
    }

}
