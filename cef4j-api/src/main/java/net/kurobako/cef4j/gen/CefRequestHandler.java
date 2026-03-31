// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Implement this interface to handle events related to browser requests. The methods of this class will be called on
 * the thread indicated.
 */
public interface CefRequestHandler {

    /**
     * Called on the UI thread before browser navigation. Return true to cancel the navigation or false to allow the
     * navigation to proceed. The |request| object cannot be modified in this callback.
     * CefLoadHandler::OnLoadingStateChange will be called twice in all cases. If the navigation is allowed
     * CefLoadHandler::OnLoadStart and CefLoadHandler::OnLoadEnd will be called. If the navigation is canceled
     * CefLoadHandler::OnLoadError will be called with an |errorCode| value of ERR_ABORTED. The |user_gesture| value
     * will be true if the browser navigated via explicit user gesture (e.g. clicking a link) or false if it navigated
     * automatically (e.g. via the DomContentLoaded event).
     */
    default boolean onBeforeBrowse(long browser, long frame, long request, boolean userGesture, boolean isRedirect) {
        return false;
    }

    default int onOpenUrlfromTab(
            long browser,
            long frame,
            @Nonnull String targetUrl,
            @Nonnull CefWindowOpenDisposition targetDisposition,
            int userGesture) {
        return 0;
    }

    /**
     * Called on the IO thread when the browser needs credentials from the user. |isProxy| indicates whether the host is
     * a proxy server. |host| contains the hostname and |port| contains the port number. Return true to continue the
     * request and call CefAuthCallback::Continue() when the authentication information is available. If the request has
     * an associated browser/frame then returning false will result in a call to GetAuthCredentials on the
     * CefRequestHandler associated with that browser, if any. Otherwise, returning false will cancel the request
     * immediately. This method will only be called for requests initiated from the browser process.
     *
     * @param realm may be null
     */
    default boolean getAuthCredentials(
            long browser,
            @Nonnull String originUrl,
            boolean isProxy,
            @Nonnull String host,
            int port,
            @Nullable String realm,
            @Nonnull String scheme,
            long callback) {
        return false;
    }

    /**
     * Called on the UI thread to handle requests for URLs with an invalid SSL certificate. Return true and call
     * CefCallback methods either in this method or at a later time to continue or cancel the request. Return false to
     * cancel the request immediately. If cef_settings_t.ignore_certificate_errors is set all invalid certificates will
     * be accepted without calling this method.
     */
    default boolean onCertificateError(
            long browser, @Nonnull CefErrorcode certError, @Nonnull String requestUrl, long sslInfo, long callback) {
        return false;
    }

    /**
     * Called on the UI thread when a client certificate is being requested for authentication. Return false to use the
     * default behavior. If the |certificates| list is not empty the default behavior will be to display a dialog for
     * certificate selection. If the |certificates| list is empty then the default behavior will be not to show a dialog
     * and it will continue without using any certificate. Return true and call
     * CefSelectClientCertificateCallback::Select either in this method or at a later time to select a certificate. Do
     * not call Select or call it with NULL to continue without using any certificate. |isProxy| indicates whether the
     * host is an HTTPS proxy or the origin server. |host| and |port| contains the hostname and port of the SSL server.
     * |certificates| is the list of certificates to choose from; this list has already been pruned by Chromium so that
     * it only contains certificates from issuers that the server trusts.
     */
    default boolean onSelectClientCertificate(
            long browser,
            boolean isProxy,
            @Nonnull String host,
            int port,
            long certificatesCount,
            long certificates,
            long callback) {
        return false;
    }

    /**
     * Called on the browser process UI thread when the render view associated with |browser| is ready to receive/handle
     * IPC messages in the render process.
     */
    default void onRenderViewReady(long browser) {}

    /**
     * Called on the browser process UI thread when the render process is unresponsive as indicated by a lack of input
     * event processing for at least 15 seconds. Return false for the default behavior which is to continue waiting with
     * Alloy style or display of the "Page unresponsive" dialog with Chrome style. Return true and don't execute the
     * callback to continue waiting without display of the Chrome style dialog. Return true and call
     * CefUnresponsiveProcessCallback::Wait either in this method or at a later time to reset the wait timer. In cases
     * where you continue waiting there may be another call to this method if the process remains unresponsive. Return
     * true and call CefUnresponsiveProcessCallback::Terminate either in this method or at a later time to terminate the
     * unresponsive process, resulting in a call to OnRenderProcessTerminated. OnRenderProcessResponsive will be called
     * if the process becomes responsive after this method is called. This functionality depends on the hang monitor
     * which can be disabled by passing the `--disable-hang-monitor` command-line flag.
     */
    default boolean onRenderProcessUnresponsive(long browser, long callback) {
        return false;
    }

    /**
     * Called on the browser process UI thread when the render process becomes responsive after previously being
     * unresponsive. See documentation on OnRenderProcessUnresponsive.
     */
    default void onRenderProcessResponsive(long browser) {}

    /**
     * Called on the browser process UI thread when the render process terminates unexpectedly. |status| indicates how
     * the process terminated. |error_code| and |error_string| represent the error that would be displayed in Chrome's
     * "Aw, Snap!" view. Possible |error_code| values include cef_resultcode_t non-normal exit values and
     * platform-specific crash values (for example, a Posix signal or Windows hardware exception).
     */
    default void onRenderProcessTerminated(
            long browser, @Nonnull CefTerminationStatus status, int errorCode, @Nonnull String errorString) {}

    /** Called on the browser process UI thread when the window.document object of the main frame has been created. */
    default void onDocumentAvailableInMainFrame(long browser) {}
}
