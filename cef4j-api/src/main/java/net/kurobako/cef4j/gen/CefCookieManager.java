// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Class used for managing cookies. The methods of this class may be called on any thread unless otherwise indicated.
 */
public interface CefCookieManager {

    /**
     * Visit all cookies on the UI thread. The returned cookies are ordered by longest path, then by earliest creation
     * date. Returns false if cookies cannot be accessed.
     */
    boolean visitAllCookies(long visitor);

    /**
     * Visit a subset of cookies on the UI thread. The results are filtered by the given url scheme, host, domain and
     * path. If |includeHttpOnly| is true HTTP-only cookies will also be included in the results. The returned cookies
     * are ordered by longest path, then by earliest creation date. Returns false if cookies cannot be accessed.
     */
    boolean visitUrlCookies(@Nonnull String url, boolean includeHttpOnly, long visitor);

    /**
     * Sets a cookie given a valid URL and explicit user-provided cookie attributes. This function expects each
     * attribute to be well-formed. It will check for disallowed characters (e.g. the ';' character is disallowed within
     * the cookie value attribute) and fail without setting the cookie if such characters are found. If |callback| is
     * non-NULL it will be executed asnychronously on the UI thread after the cookie has been set. Returns false if an
     * invalid URL is specified or if cookies cannot be accessed.
     *
     * @param callback may be null
     */
    boolean setCookie(@Nonnull String url, long cookie, long callback);

    /**
     * Delete all cookies that match the specified parameters. If both |url| and |cookie_name| values are specified all
     * host and domain cookies matching both will be deleted. If only |url| is specified all host cookies (but not
     * domain cookies) irrespective of path will be deleted. If |url| is empty all cookies for all hosts and domains
     * will be deleted. If |callback| is non-NULL it will be executed asnychronously on the UI thread after the cookies
     * have been deleted. Returns false if a non-empty invalid URL is specified or if cookies cannot be accessed.
     * Cookies can alternately be deleted using the Visit*Cookies() methods.
     *
     * @param url may be null
     * @param cookieName may be null
     * @param callback may be null
     */
    boolean deleteCookies(@Nullable String url, @Nullable String cookieName, long callback);

    /**
     * Flush the backing store (if any) to disk. If |callback| is non-NULL it will be executed asnychronously on the UI
     * thread after the flush is complete. Returns false if cookies cannot be accessed.
     *
     * @param callback may be null
     */
    boolean flushStore(long callback);

    static class NativePeer implements CefCookieManager {
        private volatile long nativePtr;

        @Override
        public boolean visitAllCookies(long visitor) {
            return N_VisitAllCookies(nativePtr, visitor);
        }

        @Override
        public boolean visitUrlCookies(String url, boolean includeHttpOnly, long visitor) {
            return N_VisitUrlCookies(nativePtr, url, includeHttpOnly, visitor);
        }

        @Override
        public boolean setCookie(String url, long cookie, long callback) {
            return N_SetCookie(nativePtr, url, cookie, callback);
        }

        @Override
        public boolean deleteCookies(String url, String cookieName, long callback) {
            return N_DeleteCookies(nativePtr, url, cookieName, callback);
        }

        @Override
        public boolean flushStore(long callback) {
            return N_FlushStore(nativePtr, callback);
        }

        private native boolean N_VisitAllCookies(long self, long visitor);

        private native boolean N_VisitUrlCookies(long self, String url, boolean includeHttpOnly, long visitor);

        private native boolean N_SetCookie(long self, String url, long cookie, long callback);

        private native boolean N_DeleteCookies(long self, String url, String cookieName, long callback);

        private native boolean N_FlushStore(long self, long callback);

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
