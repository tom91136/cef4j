// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
// -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.processing.Generated;

/**
 * Class used for managing cookies. The methods of this class may be called on any thread unless otherwise indicated.
 *
 * <p>Definition generated from cef_cookie_capi.h
 *
 * <pre>typedef struct _cef_cookie_manager_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_cookie_manager_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__cookie_8h.html">cef_cookie.h:50</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
public interface CefCookieManager extends CefLibraryObject {

    /**
     * Visit all cookies on the UI thread. The returned cookies are ordered by longest path, then by earliest creation
     * date. Returns {@code false} if cookies cannot be accessed.
     *
     * <p>Definition generated from cef_cookie_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* visit_all_cookies)(struct _cef_cookie_manager_t* self, struct _cef_cookie_visitor_t* visitor);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__cookie_8h.html">cef_cookie.h:69</a>
     */
    boolean visitAllCookies(@Nullable CefCookieVisitor visitor);

    /**
     * Visit a subset of cookies on the UI thread. The results are filtered by the given url scheme, host, domain and
     * path. If {@code includeHttpOnly} is {@code true} HTTP-only cookies will also be included in the results. The
     * returned cookies are ordered by longest path, then by earliest creation date. Returns {@code false} if cookies
     * cannot be accessed.
     *
     * <p>Definition generated from cef_cookie_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* visit_url_cookies)(struct _cef_cookie_manager_t* self, const cef_string_t* url, int includeHttpOnly, struct _cef_cookie_visitor_t* visitor);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__cookie_8h.html">cef_cookie.h:77</a>
     */
    boolean visitUrlCookies(@Nullable String url, boolean includeHttpOnly, @Nullable CefCookieVisitor visitor);

    /**
     * Sets a cookie given a valid URL and explicit user-provided cookie attributes. This function expects each
     * attribute to be well-formed. It will check for disallowed characters (e.g. the ';' character is disallowed within
     * the cookie value attribute) and fail without setting the cookie if such characters are found. If {@code callback}
     * is non-{@code null} it will be executed asnychronously on the UI thread after the cookie has been set. Returns
     * {@code false} if an invalid URL is specified or if cookies cannot be accessed.
     *
     * <p>Definition generated from cef_cookie_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* set_cookie)(struct _cef_cookie_manager_t* self, const cef_string_t* url, const struct _cef_cookie_t* cookie, struct _cef_set_cookie_callback_t* callback);
     * </pre>
     *
     * @param callback may be null
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__cookie_8h.html">cef_cookie.h:89</a>
     */
    boolean setCookie(@Nullable String url, @Nonnull CefCookie cookie, @Nullable CefSetCookieCallback callback);

    /**
     * Delete all cookies that match the specified parameters. If both {@code url} and {@code cookie_name} values are
     * specified all host and domain cookies matching both will be deleted. If only {@code url} is specified all host
     * cookies (but not domain cookies) irrespective of path will be deleted. If {@code url} is empty all cookies for
     * all hosts and domains will be deleted. If {@code callback} is non-{@code null} it will be executed asnychronously
     * on the UI thread after the cookies have been deleted. Returns {@code false} if a non-empty invalid URL is
     * specified or if cookies cannot be accessed. Cookies can alternately be deleted using the Visit*Cookies() methods.
     *
     * <p>Definition generated from cef_cookie_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* delete_cookies)(struct _cef_cookie_manager_t* self, const cef_string_t* url, const cef_string_t* cookie_name, struct _cef_delete_cookies_callback_t* callback);
     * </pre>
     *
     * @param url may be null
     * @param cookieName may be null
     * @param callback may be null
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__cookie_8h.html">cef_cookie.h:103</a>
     */
    boolean deleteCookies(
            @Nullable String url, @Nullable String cookieName, @Nullable CefDeleteCookiesCallback callback);

    /**
     * Flush the backing store (if any) to disk. If {@code callback} is non-{@code null} it will be executed
     * asnychronously on the UI thread after the flush is complete. Returns {@code false} if cookies cannot be accessed.
     *
     * <p>Definition generated from cef_cookie_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* flush_store)(struct _cef_cookie_manager_t* self, struct _cef_completion_callback_t* callback);
     * </pre>
     *
     * @param callback may be null
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__cookie_8h.html">cef_cookie.h:120</a>
     */
    boolean flushStore(@Nullable CefCompletionCallback callback);
    /**
     * Returns the global cookie manager. By default data will be stored at cef_settings_t.cache_path if specified or in
     * memory otherwise. If {@code callback} is non-{@code null} it will be executed asnychronously on the UI thread
     * after the manager's storage has been initialized. Using this method is equivalent to calling
     * CefRequestContext.getGlobalContext()->GetDefaultCookieManager().
     *
     * <p>Definition generated from cef_cookie_capi.h
     *
     * <pre>
     * CEF_EXPORT cef_cookie_manager_t* cef_cookie_manager_get_global_manager(struct _cef_completion_callback_t* callback);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__cookie_8h.html">cef_cookie.h:57</a>
     */
    static Optional<CefCookieManager> getGlobalManager(@Nullable CefCompletionCallback callback) {
        return Optional.ofNullable(NativePeer.getGlobalManager0(callback));
    }

    final class NativePeer implements CefCookieManager, AutoCloseable {
        private final long nativePtr;
        private final java.lang.ref.Cleaner.Cleanable cleanable;
        private volatile boolean closed;

        NativePeer(long ptr) {
            this.nativePtr = ptr;
            this.cleanable = net.kurobako.cef4j.NativeCleaner.INSTANCE.register(this, new Release(ptr));
        }

        @Override
        public void close() {
            closed = true;
            cleanable.clean();
        }

        @Override
        public boolean isClosed() {
            return closed;
        }

        private void checkNotClosed() {
            if (closed) throw new IllegalStateException("CefCookieManager has been closed");
        }

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefCookieManager.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefCookieManager 0x{}", Long.toHexString(ptr));
                release0(ptr);
            }
        }

        private static native void release0(long ptr);

        @Override
        public boolean visitAllCookies(@Nullable CefCookieVisitor visitor) {
            checkNotClosed();
            return visitAllCookies0(nativePtr, visitor);
        }

        @Override
        public boolean visitUrlCookies(
                @Nullable String url, boolean includeHttpOnly, @Nullable CefCookieVisitor visitor) {
            checkNotClosed();
            return visitUrlCookies0(nativePtr, url, includeHttpOnly, visitor);
        }

        @Override
        public boolean setCookie(
                @Nullable String url, @Nonnull CefCookie cookie, @Nullable CefSetCookieCallback callback) {
            checkNotClosed();
            return setCookie0(nativePtr, url, cookie, callback);
        }

        @Override
        public boolean deleteCookies(
                @Nullable String url, @Nullable String cookieName, @Nullable CefDeleteCookiesCallback callback) {
            checkNotClosed();
            return deleteCookies0(nativePtr, url, cookieName, callback);
        }

        @Override
        public boolean flushStore(@Nullable CefCompletionCallback callback) {
            checkNotClosed();
            return flushStore0(nativePtr, callback);
        }

        private static native boolean visitAllCookies0(long self, CefCookieVisitor visitor);

        private static native boolean visitUrlCookies0(
                long self, String url, boolean includeHttpOnly, CefCookieVisitor visitor);

        private static native boolean setCookie0(
                long self, String url, CefCookie cookie, CefSetCookieCallback callback);

        private static native boolean deleteCookies0(
                long self, String url, String cookieName, CefDeleteCookiesCallback callback);

        private static native boolean flushStore0(long self, CefCompletionCallback callback);

        static native CefCookieManager getGlobalManager0(CefCompletionCallback callback);

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof NativePeer)) return false;
            return this.nativePtr == ((NativePeer) obj).nativePtr;
        }

        @Override
        public int hashCode() {
            return Long.hashCode(nativePtr);
        }

        @Override
        public String toString() {
            return "CefCookieManager{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
