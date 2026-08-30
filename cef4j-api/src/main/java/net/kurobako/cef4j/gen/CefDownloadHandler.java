// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-api
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.Nullable;
import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * Class used to handle file downloads. The methods of this class will called on the browser process UI thread.
 *
 * <p>Definition generated from cef_download_handler_capi.h
 *
 * <pre>typedef struct _cef_download_handler_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_download_handler_t;</pre>
 *
 * @see <a
 *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__download__handler_8h.html">cef_download_handler.h:86</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-api -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
@NullableBoundary("generated external ABI")
public interface CefDownloadHandler extends CefClientHandler {

    /**
     * Called before a download begins in response to a user-initiated action (e.g. alt + link click or link click that
     * returns a `Content-Disposition: attachment` response from the server). {@code url} is the target download URL and
     * {@code request_method} is the target method (GET, POST, etc). Return {@code true} to proceed with the download or
     * {@code false} to cancel the download.
     *
     * <p>Definition generated from cef_download_handler_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* can_download)(struct _cef_download_handler_t* self, struct _cef_browser_t* browser, const cef_string_t* url, const cef_string_t* request_method);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__download__handler_8h.html">cef_download_handler.h:93</a>
     */
    default boolean canDownload(@Nullable CefBrowser browser, @Nullable String url, @Nullable String requestMethod) {
        return false;
    }

    /**
     * Called before a download begins. {@code suggested_name} is the suggested name for the download file. Return
     * {@code true} and execute {@code callback} either asynchronously or in this method to continue or cancel the
     * download. Return {@code false} to proceed with default handling (cancel with Alloy style, download shelf with
     * Chrome style). Do not keep a reference to {@code download_item} outside of this method.
     *
     * <p>Definition generated from cef_download_handler_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* on_before_download)(struct _cef_download_handler_t* self, struct _cef_browser_t* browser, struct _cef_download_item_t* download_item, const cef_string_t* suggested_name, struct _cef_before_download_callback_t* callback);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__download__handler_8h.html">cef_download_handler.h:107</a>
     */
    default boolean onBeforeDownload(
            @Nullable CefBrowser browser,
            @Nullable CefDownloadItem downloadItem,
            @Nullable String suggestedName,
            @Nullable CefBeforeDownloadCallback callback) {
        return false;
    }

    /**
     * Called when a download's status or progress information has been updated. This may be called multiple times
     * before and after OnBeforeDownload(). Execute {@code callback} either asynchronously or in this method to cancel
     * the download if desired. Do not keep a reference to {@code download_item} outside of this method.
     *
     * <p>Definition generated from cef_download_handler_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* on_download_updated)(struct _cef_download_handler_t* self, struct _cef_browser_t* browser, struct _cef_download_item_t* download_item, struct _cef_download_item_callback_t* callback);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__download__handler_8h.html">cef_download_handler.h:123</a>
     */
    default void onDownloadUpdated(
            @Nullable CefBrowser browser,
            @Nullable CefDownloadItem downloadItem,
            @Nullable CefDownloadItemCallback callback) {}
    /**
     * Composite that fans callbacks out to every registered delegate. {@code void} methods invoke all delegates in
     * order; {@code boolean} methods short-circuit on the first {@code true}; handler-returning {@code Optional}s
     * collect every non-empty delegate and wrap them in the handler's own {@code Delegating} wrapper; other
     * {@code Optional}s pick the first non-empty; any other return type yields the first delegate's value.
     */
    class Delegating implements CefDownloadHandler {
        private final java.util.List<CefDownloadHandler> delegates;

        public Delegating(java.util.List<CefDownloadHandler> delegates) {
            this.delegates = java.util.List.copyOf(delegates);
        }

        @Override
        public boolean canDownload(@Nullable CefBrowser browser, @Nullable String url, @Nullable String requestMethod) {
            for (CefDownloadHandler d : delegates) {
                if (d.canDownload(browser, url, requestMethod)) return true;
            }
            if (!delegates.isEmpty()) return false;
            return false;
        }

        @Override
        public boolean onBeforeDownload(
                @Nullable CefBrowser browser,
                @Nullable CefDownloadItem downloadItem,
                @Nullable String suggestedName,
                @Nullable CefBeforeDownloadCallback callback) {
            for (CefDownloadHandler d : delegates) {
                if (d.onBeforeDownload(browser, downloadItem, suggestedName, callback)) return true;
            }
            if (!delegates.isEmpty()) return false;
            return false;
        }

        @Override
        public void onDownloadUpdated(
                @Nullable CefBrowser browser,
                @Nullable CefDownloadItem downloadItem,
                @Nullable CefDownloadItemCallback callback) {
            for (CefDownloadHandler d : delegates) d.onDownloadUpdated(browser, downloadItem, callback);
        }
    }
}
