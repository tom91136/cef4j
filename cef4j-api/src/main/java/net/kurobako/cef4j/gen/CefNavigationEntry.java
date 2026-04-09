// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import java.util.Optional;

/**
 * Class used to represent an entry in navigation history.
 * <p>Definition generated from cef_navigation_entry_capi.h
 * <pre>typedef struct _cef_navigation_entry_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_navigation_entry_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__navigation__entry_8h.html">cef_navigation_entry.h:44</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public interface CefNavigationEntry extends CefLibraryObject {

    /**
     * Returns {@code true} if this object is valid. Do not call any other methods if this function returns {@code false}.
     * <p>Definition generated from cef_navigation_entry_capi.h
     * <pre>int (CEF_CALLBACK* is_valid)(struct _cef_navigation_entry_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__navigation__entry_8h.html">cef_navigation_entry.h:52</a>
     */
    boolean isValid();

    /**
     * Returns the actual URL of the page. For some pages this may be data: URL or similar. Use GetDisplayURL() to return a display-friendly version.
     * <p>Definition generated from cef_navigation_entry_capi.h
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_url)(struct _cef_navigation_entry_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__navigation__entry_8h.html">cef_navigation_entry.h:59</a>
     */
    Optional<String> getUrl();

    /**
     * Returns a display-friendly version of the URL.
     * <p>Definition generated from cef_navigation_entry_capi.h
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_display_url)(struct _cef_navigation_entry_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__navigation__entry_8h.html">cef_navigation_entry.h:66</a>
     */
    Optional<String> getDisplayUrl();

    /**
     * Returns the original URL before any redirections.
     * <p>Definition generated from cef_navigation_entry_capi.h
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_original_url)(struct _cef_navigation_entry_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__download__item_8h.html">cef_download_item.h:149</a>
     */
    Optional<String> getOriginalUrl();

    /**
     * Returns the title set by the page. This value may be empty.
     * <p>Definition generated from cef_navigation_entry_capi.h
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_title)(struct _cef_navigation_entry_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__navigation__entry_8h.html">cef_navigation_entry.h:79</a>
     */
    Optional<String> getTitle();

    /**
     * Returns the transition type which indicates what the user did to move to this page from the previous page.
     * <p>Definition generated from cef_navigation_entry_capi.h
     * <pre>cef_transition_type_t (CEF_CALLBACK* get_transition_type)(struct _cef_navigation_entry_t* self);</pre>
     *
     * @return the result, or {@code TT_EXPLICIT} for default handling
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__navigation__entry_8h.html">cef_navigation_entry.h:85</a>
     */
    CefTransitionType getTransitionType();

    /**
     * Returns {@code true} if this navigation includes post data.
     * <p>Definition generated from cef_navigation_entry_capi.h
     * <pre>int (CEF_CALLBACK* has_post_data)(struct _cef_navigation_entry_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__navigation__entry_8h.html">cef_navigation_entry.h:92</a>
     */
    boolean hasPostData();

    /**
     * Returns the time for the last known successful navigation completion. A navigation may be completed more than once if the page is reloaded. May be 0 if the navigation has not yet completed.
     * <p>Definition generated from cef_navigation_entry_capi.h
     * <pre>cef_basetime_t* (CEF_CALLBACK* get_completion_time)(struct _cef_navigation_entry_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__navigation__entry_8h.html">cef_navigation_entry.h:98</a>
     */
    CefBasetime getCompletionTime();

    /**
     * Returns the HTTP status code for the last known successful navigation response. May be 0 if the response has not yet been received or if the navigation has not yet completed.
     * <p>Definition generated from cef_navigation_entry_capi.h
     * <pre>int (CEF_CALLBACK* get_http_status_code)(struct _cef_navigation_entry_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__navigation__entry_8h.html">cef_navigation_entry.h:106</a>
     */
    int getHttpStatusCode();

    /**
     * Returns the SSL information for this navigation entry.
     * <p>Definition generated from cef_navigation_entry_capi.h
     * <pre>cef_sslstatus_t* (CEF_CALLBACK* get_sslstatus)(struct _cef_navigation_entry_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__navigation__entry_8h.html">cef_navigation_entry.h:114</a>
     */
    Optional<CefSslStatus> getSslStatus();
    final class NativePeer implements CefNavigationEntry, AutoCloseable {
        private final long nativePtr;
        private final java.lang.ref.Cleaner.Cleanable cleanable;
        private volatile boolean closed;

        NativePeer(long ptr) {
            this.nativePtr = ptr;
            this.cleanable = net.kurobako.cef4j.NativeCleaner.INSTANCE.register(this, new Release(ptr));
        }

        @Override
        public void peerClose() {
            closed = true;
            cleanable.clean();
        }

        @Override
        public boolean peerIsClosed() {
            return closed;
        }

        private void checkNotClosed() {
            if (closed) throw new IllegalStateException("CefNavigationEntry has been closed");
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
                release0(ptr);
            }
        }

        private static native void release0(long ptr);

        @Override
      public boolean isValid() {
          checkNotClosed();
          return isValid0(nativePtr);
      }

        @Override
      public Optional<String> getUrl() {
          checkNotClosed();
          return Optional.ofNullable(getUrl0(nativePtr));
      }

        @Override
      public Optional<String> getDisplayUrl() {
          checkNotClosed();
          return Optional.ofNullable(getDisplayUrl0(nativePtr));
      }

        @Override
      public Optional<String> getOriginalUrl() {
          checkNotClosed();
          return Optional.ofNullable(getOriginalUrl0(nativePtr));
      }

        @Override
      public Optional<String> getTitle() {
          checkNotClosed();
          return Optional.ofNullable(getTitle0(nativePtr));
      }

        @Override
      public CefTransitionType getTransitionType() {
          checkNotClosed();
          return getTransitionType0(nativePtr);
      }

        @Override
      public boolean hasPostData() {
          checkNotClosed();
          return hasPostData0(nativePtr);
      }

        @Override
      public CefBasetime getCompletionTime() {
          checkNotClosed();
          return getCompletionTime0(nativePtr);
      }

        @Override
      public int getHttpStatusCode() {
          checkNotClosed();
          return getHttpStatusCode0(nativePtr);
      }

        @Override
      public Optional<CefSslStatus> getSslStatus() {
          checkNotClosed();
          return Optional.ofNullable(getSslStatus0(nativePtr));
      }


        static native boolean isValid0(long self);

        static native String getUrl0(long self);

        static native String getDisplayUrl0(long self);

        static native String getOriginalUrl0(long self);

        static native String getTitle0(long self);

        static native CefTransitionType getTransitionType0(long self);

        static native boolean hasPostData0(long self);

        static native CefBasetime getCompletionTime0(long self);

        static native int getHttpStatusCode0(long self);

        static native CefSslStatus getSslStatus0(long self);


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
