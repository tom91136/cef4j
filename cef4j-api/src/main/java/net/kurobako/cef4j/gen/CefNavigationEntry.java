// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.util.Optional;

/**
 * Class used to represent an entry in navigation history.
 *
 * <p>Definition generated from cef_navigation_entry_capi.h
 *
 * <pre>typedef struct _cef_navigation_entry_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_navigation_entry_t;</pre>
 *
 * @see <a
 *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__navigation__entry_8h.html">cef_navigation_entry.h:44</a>
 */
public interface CefNavigationEntry extends CefLibraryObject {

    /**
     * Returns {@code true} if this object is valid. Do not call any other methods if this function returns
     * {@code false}.
     *
     * <p>Definition generated from cef_navigation_entry_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_valid)(struct _cef_navigation_entry_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__download__item_8h.html">cef_download_item.h:49</a>
     */
    boolean isValid();

    /**
     * Returns the URL.
     *
     * <p>Definition generated from cef_navigation_entry_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_url)(struct _cef_navigation_entry_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__download__item_8h.html">cef_download_item.h:143</a>
     */
    Optional<String> getUrl();

    /**
     * Returns a display-friendly version of the URL.
     *
     * <p>Definition generated from cef_navigation_entry_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_display_url)(struct _cef_navigation_entry_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__navigation__entry_8h.html">cef_navigation_entry.h:66</a>
     */
    Optional<String> getDisplayUrl();

    /**
     * Returns the original URL before any redirections.
     *
     * <p>Definition generated from cef_navigation_entry_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_original_url)(struct _cef_navigation_entry_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__download__item_8h.html">cef_download_item.h:149</a>
     */
    Optional<String> getOriginalUrl();

    /**
     * Returns the title of an HTML document.
     *
     * <p>Definition generated from cef_navigation_entry_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_title)(struct _cef_navigation_entry_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__dom_8h.html">cef_dom.h:99</a>
     */
    Optional<String> getTitle();

    /**
     * Returns the transition type which indicates what the user did to move to this page from the previous page.
     *
     * <p>Definition generated from cef_navigation_entry_capi.h
     *
     * <pre>cef_transition_type_t (CEF_CALLBACK* get_transition_type)(struct _cef_navigation_entry_t* self);</pre>
     *
     * @return the result, or {@code TT_EXPLICIT} for default handling
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__navigation__entry_8h.html">cef_navigation_entry.h:85</a>
     */
    CefTransitionType getTransitionType();

    /**
     * Returns {@code true} if this navigation includes post data.
     *
     * <p>Definition generated from cef_navigation_entry_capi.h
     *
     * <pre>int (CEF_CALLBACK* has_post_data)(struct _cef_navigation_entry_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__navigation__entry_8h.html">cef_navigation_entry.h:92</a>
     */
    boolean hasPostData();

    /**
     * Returns the time for the last known successful navigation completion. A navigation may be completed more than
     * once if the page is reloaded. May be 0 if the navigation has not yet completed.
     *
     * <p>Definition generated from cef_navigation_entry_capi.h
     *
     * <pre>cef_basetime_t* (CEF_CALLBACK* get_completion_time)(struct _cef_navigation_entry_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__navigation__entry_8h.html">cef_navigation_entry.h:98</a>
     */
    CefBasetime getCompletionTime();

    /**
     * Returns the HTTP status code for the last known successful navigation response. May be 0 if the response has not
     * yet been received or if the navigation has not yet completed.
     *
     * <p>Definition generated from cef_navigation_entry_capi.h
     *
     * <pre>int (CEF_CALLBACK* get_http_status_code)(struct _cef_navigation_entry_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__navigation__entry_8h.html">cef_navigation_entry.h:106</a>
     */
    int getHttpStatusCode();

    /**
     * Returns the SSL information for this navigation entry.
     *
     * <p>Definition generated from cef_navigation_entry_capi.h
     *
     * <pre>cef_sslstatus_t* (CEF_CALLBACK* get_sslstatus)(struct _cef_navigation_entry_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__navigation__entry_8h.html">cef_navigation_entry.h:114</a>
     */
    Optional<CefSslStatus> getSslStatus();

    final class NativePeer implements CefNavigationEntry, AutoCloseable {
        private final long nativePtr;
        private final java.lang.ref.Cleaner.Cleanable cleanable;

        NativePeer(long ptr) {
            this.nativePtr = ptr;
            this.cleanable = net.kurobako.cef4j.NativeCleaner.INSTANCE.register(this, new Release(ptr));
        }

        @Override
        public void close() {
            cleanable.clean();
        }

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefNavigationEntry.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefNavigationEntry 0x{}", Long.toHexString(ptr));
                N_Release(ptr);
            }
        }

        private static native void N_Release(long ptr);

        @Override
        public boolean isValid() {
            return N_IsValid(nativePtr);
        }

        @Override
        public Optional<String> getUrl() {
            return Optional.ofNullable(N_GetUrl(nativePtr));
        }

        @Override
        public Optional<String> getDisplayUrl() {
            return Optional.ofNullable(N_GetDisplayUrl(nativePtr));
        }

        @Override
        public Optional<String> getOriginalUrl() {
            return Optional.ofNullable(N_GetOriginalUrl(nativePtr));
        }

        @Override
        public Optional<String> getTitle() {
            return Optional.ofNullable(N_GetTitle(nativePtr));
        }

        @Override
        public CefTransitionType getTransitionType() {
            return N_GetTransitionType(nativePtr);
        }

        @Override
        public boolean hasPostData() {
            return N_HasPostData(nativePtr);
        }

        @Override
        public CefBasetime getCompletionTime() {
            return N_GetCompletionTime(nativePtr);
        }

        @Override
        public int getHttpStatusCode() {
            return N_GetHttpStatusCode(nativePtr);
        }

        @Override
        public Optional<CefSslStatus> getSslStatus() {
            return Optional.ofNullable(N_GetSslStatus(nativePtr));
        }

        private static native boolean N_IsValid(long self);

        private static native String N_GetUrl(long self);

        private static native String N_GetDisplayUrl(long self);

        private static native String N_GetOriginalUrl(long self);

        private static native String N_GetTitle(long self);

        private static native CefTransitionType N_GetTransitionType(long self);

        private static native boolean N_HasPostData(long self);

        private static native CefBasetime N_GetCompletionTime(long self);

        private static native int N_GetHttpStatusCode(long self);

        private static native CefSslStatus N_GetSslStatus(long self);

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
            return "CefNavigationEntry{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
