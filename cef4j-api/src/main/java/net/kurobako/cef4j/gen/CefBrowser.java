// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import java.util.List;
import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Class used to represent a browser. When used in the browser process the methods of this class may be called on any thread unless otherwise indicated in the comments. When used in the render process the methods of this class may only be called on the main thread.
 * <p>Definition generated from cef_browser_capi.h
 * <pre>typedef struct _cef_browser_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_browser_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:55</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public interface CefBrowser extends CefLibraryObject {

    /**
     * True if this object is currently valid. This will return {@code false} after {@link net.kurobako.cef4j.gen.CefLifeSpanHandler#onBeforeClose(CefBrowser)} is called.
     * <p>Definition generated from cef_browser_capi.h
     * <pre>int (CEF_CALLBACK* is_valid)(struct _cef_browser_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:64</a>
     */
    boolean isValid();

    /**
     * Returns the browser host object. This method can only be called in the browser process.
     * <p>Definition generated from cef_browser_capi.h
     * <pre>cef_browser_host_t* (CEF_CALLBACK* get_host)(struct _cef_browser_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:71</a>
     */
    Optional<CefBrowserHost> getHost();

    /**
     * Returns {@code true} if the browser can navigate backwards.
     * <p>Definition generated from cef_browser_capi.h
     * <pre>int (CEF_CALLBACK* can_go_back)(struct _cef_browser_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:78</a>
     */
    boolean canGoBack();

    /**
     * Navigate backwards.
     * <p>Definition generated from cef_browser_capi.h
     * <pre>void (CEF_CALLBACK* go_back)(struct _cef_browser_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:84</a>
     */
    void goBack();

    /**
     * Returns {@code true} if the browser can navigate forwards.
     * <p>Definition generated from cef_browser_capi.h
     * <pre>int (CEF_CALLBACK* can_go_forward)(struct _cef_browser_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:90</a>
     */
    boolean canGoForward();

    /**
     * Navigate forwards.
     * <p>Definition generated from cef_browser_capi.h
     * <pre>void (CEF_CALLBACK* go_forward)(struct _cef_browser_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:96</a>
     */
    void goForward();

    /**
     * Returns {@code true} if the browser is currently loading.
     * <p>Definition generated from cef_browser_capi.h
     * <pre>int (CEF_CALLBACK* is_loading)(struct _cef_browser_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:102</a>
     */
    boolean isLoading();

    /**
     * Reload the current page.
     * <p>Definition generated from cef_browser_capi.h
     * <pre>void (CEF_CALLBACK* reload)(struct _cef_browser_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:108</a>
     */
    void reload();

    /**
     * Reload the current page ignoring any cached data.
     * <p>Definition generated from cef_browser_capi.h
     * <pre>void (CEF_CALLBACK* reload_ignore_cache)(struct _cef_browser_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:114</a>
     */
    void reloadIgnoreCache();

    /**
     * Stop loading the page.
     * <p>Definition generated from cef_browser_capi.h
     * <pre>void (CEF_CALLBACK* stop_load)(struct _cef_browser_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:120</a>
     */
    void stopLoad();

    /**
     * Returns the globally unique identifier for this browser. This value is also used as the tabId for extension APIs.
     * <p>Definition generated from cef_browser_capi.h
     * <pre>int (CEF_CALLBACK* get_identifier)(struct _cef_browser_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:126</a>
     */
    int getIdentifier();

    /**
     * Returns {@code true} if this object is pointing to the same handle as {@code that} object.
     * <p>Definition generated from cef_browser_capi.h
     * <pre>int (CEF_CALLBACK* is_same)(struct _cef_browser_t* self, struct _cef_browser_t* that);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:133</a>
     */
    boolean isSame(@Nullable CefBrowser that);

    /**
     * Returns {@code true} if the browser is a popup.
     * <p>Definition generated from cef_browser_capi.h
     * <pre>int (CEF_CALLBACK* is_popup)(struct _cef_browser_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:140</a>
     */
    boolean isPopup();

    /**
     * Returns {@code true} if a document has been loaded in the browser.
     * <p>Definition generated from cef_browser_capi.h
     * <pre>int (CEF_CALLBACK* has_document)(struct _cef_browser_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:146</a>
     */
    boolean hasDocument();

    /**
     * Returns the main (top-level) frame for the browser. In the browser process this will return a valid object until after {@link net.kurobako.cef4j.gen.CefLifeSpanHandler#onBeforeClose(CefBrowser)} is called. In the renderer process this will return {@code null} if the main frame is hosted in a different renderer process (e.g. for cross-origin sub-frames). The main frame object will change during cross-origin navigation or re-navigation after renderer process termination (due to crashes, etc).
     * <p>Definition generated from cef_browser_capi.h
     * <pre>cef_frame_t* (CEF_CALLBACK* get_main_frame)(struct _cef_browser_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:152</a>
     */
    Optional<CefFrame> getMainFrame();

    /**
     * Returns the focused frame for the browser.
     * <p>Definition generated from cef_browser_capi.h
     * <pre>cef_frame_t* (CEF_CALLBACK* get_focused_frame)(struct _cef_browser_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:164</a>
     */
    Optional<CefFrame> getFocusedFrame();

    /**
     * Returns the frame with the specified identifier, or {@code null} if not found.
     * <p>Definition generated from cef_browser_capi.h
     * <pre>cef_frame_t* (CEF_CALLBACK* get_frame_by_identifier)(struct _cef_browser_t* self, const cef_string_t* identifier);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:170</a>
     */
    Optional<CefFrame> getFrameByIdentifier(@Nullable String identifier);

    /**
     * Returns the frame with the specified name, or {@code null} if not found.
     * <p>Definition generated from cef_browser_capi.h
     * <pre>cef_frame_t* (CEF_CALLBACK* get_frame_by_name)(struct _cef_browser_t* self, const cef_string_t* name);</pre>
     *
     * @param name may be null
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:177</a>
     */
    Optional<CefFrame> getFrameByName(@Nullable String name);

    /**
     * Returns the number of frames that currently exist.
     * <p>Definition generated from cef_browser_capi.h
     * <pre>size_t (CEF_CALLBACK* get_frame_count)(struct _cef_browser_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:183</a>
     */
    long getFrameCount();

    /**
     * Returns the identifiers of all existing frames.
     * <p>Definition generated from cef_browser_capi.h
     * <pre>void (CEF_CALLBACK* get_frame_identifiers)(struct _cef_browser_t* self, cef_string_list_t identifiers);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:189</a>
     */
    void getFrameIdentifiers(@Nonnull List<String> identifiers);

    /**
     * Returns the names of all existing frames.
     * <p>Definition generated from cef_browser_capi.h
     * <pre>void (CEF_CALLBACK* get_frame_names)(struct _cef_browser_t* self, cef_string_list_t names);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:195</a>
     */
    void getFrameNames(@Nonnull List<String> names);
    final class NativePeer implements CefBrowser, AutoCloseable {
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
            if (closed) throw new IllegalStateException("CefBrowser has been closed");
        }

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefBrowser.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefBrowser 0x{}", Long.toHexString(ptr));
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
      public Optional<CefBrowserHost> getHost() {
          checkNotClosed();
          return Optional.ofNullable(getHost0(nativePtr));
      }

        @Override
      public boolean canGoBack() {
          checkNotClosed();
          return canGoBack0(nativePtr);
      }

        @Override
      public void goBack() {
          checkNotClosed();
          goBack0(nativePtr);
      }

        @Override
      public boolean canGoForward() {
          checkNotClosed();
          return canGoForward0(nativePtr);
      }

        @Override
      public void goForward() {
          checkNotClosed();
          goForward0(nativePtr);
      }

        @Override
      public boolean isLoading() {
          checkNotClosed();
          return isLoading0(nativePtr);
      }

        @Override
      public void reload() {
          checkNotClosed();
          reload0(nativePtr);
      }

        @Override
      public void reloadIgnoreCache() {
          checkNotClosed();
          reloadIgnoreCache0(nativePtr);
      }

        @Override
      public void stopLoad() {
          checkNotClosed();
          stopLoad0(nativePtr);
      }

        @Override
      public int getIdentifier() {
          checkNotClosed();
          return getIdentifier0(nativePtr);
      }

        @Override
      public boolean isSame(@Nullable CefBrowser that) {
          checkNotClosed();
            CefLibraryObject.requireOpen(that, "CefBrowser");
          return isSame0(nativePtr, that);
      }

        @Override
      public boolean isPopup() {
          checkNotClosed();
          return isPopup0(nativePtr);
      }

        @Override
      public boolean hasDocument() {
          checkNotClosed();
          return hasDocument0(nativePtr);
      }

        @Override
      public Optional<CefFrame> getMainFrame() {
          checkNotClosed();
          return Optional.ofNullable(getMainFrame0(nativePtr));
      }

        @Override
      public Optional<CefFrame> getFocusedFrame() {
          checkNotClosed();
          return Optional.ofNullable(getFocusedFrame0(nativePtr));
      }

        @Override
      public Optional<CefFrame> getFrameByIdentifier(@Nullable String identifier) {
          checkNotClosed();
          return Optional.ofNullable(getFrameByIdentifier0(nativePtr, identifier));
      }

        @Override
      public Optional<CefFrame> getFrameByName(@Nullable String name) {
          checkNotClosed();
          return Optional.ofNullable(getFrameByName0(nativePtr, name));
      }

        @Override
      public long getFrameCount() {
          checkNotClosed();
          return getFrameCount0(nativePtr);
      }

        @Override
      public void getFrameIdentifiers(@Nonnull List<String> identifiers) {
          checkNotClosed();
          getFrameIdentifiers0(nativePtr, identifiers);
      }

        @Override
      public void getFrameNames(@Nonnull List<String> names) {
          checkNotClosed();
          getFrameNames0(nativePtr, names);
      }


        static native boolean isValid0(long self);

        static native CefBrowserHost getHost0(long self);

        static native boolean canGoBack0(long self);

        static native void goBack0(long self);

        static native boolean canGoForward0(long self);

        static native void goForward0(long self);

        static native boolean isLoading0(long self);

        static native void reload0(long self);

        static native void reloadIgnoreCache0(long self);

        static native void stopLoad0(long self);

        static native int getIdentifier0(long self);

        static native boolean isSame0(long self, CefBrowser that);

        static native boolean isPopup0(long self);

        static native boolean hasDocument0(long self);

        static native CefFrame getMainFrame0(long self);

        static native CefFrame getFocusedFrame0(long self);

        static native CefFrame getFrameByIdentifier0(long self, String identifier);

        static native CefFrame getFrameByName0(long self, String name);

        static native long getFrameCount0(long self);

        static native void getFrameIdentifiers0(long self, List<String> identifiers);

        static native void getFrameNames0(long self, List<String> names);


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
            return "CefBrowser{0x" + Long.toHexString(nativePtr) + "}";
        }
    }

}
