// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.util.List;
import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Class used to represent a browser. When used in the browser process the methods of this class may be called on any
 * thread unless otherwise indicated in the comments. When used in the render process the methods of this class may only
 * be called on the main thread.
 *
 * <p>Definition generated from cef_browser_capi.h
 *
 * <pre>typedef struct _cef_browser_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_browser_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:55</a>
 */
public interface CefBrowser extends CefLibraryObject {

    /**
     * True if this object is currently valid. This will return {@code false} after
     * {@link CefLifeSpanHandler#onBeforeClose(CefBrowser)} is called.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_valid)(struct _cef_browser_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:64</a>
     */
    boolean isValid();

    /**
     * Returns the browser host object. This method can only be called in the browser process.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>cef_browser_host_t* (CEF_CALLBACK* get_host)(struct _cef_browser_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:71</a>
     */
    Optional<CefBrowserHost> getHost();

    /**
     * Returns {@code true} if the browser can navigate backwards.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>int (CEF_CALLBACK* can_go_back)(struct _cef_browser_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:78</a>
     */
    boolean canGoBack();

    /**
     * Navigate backwards.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>void (CEF_CALLBACK* go_back)(struct _cef_browser_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:84</a>
     */
    void goBack();

    /**
     * Returns {@code true} if the browser can navigate forwards.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>int (CEF_CALLBACK* can_go_forward)(struct _cef_browser_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:90</a>
     */
    boolean canGoForward();

    /**
     * Navigate forwards.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>void (CEF_CALLBACK* go_forward)(struct _cef_browser_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:96</a>
     */
    void goForward();

    /**
     * Returns {@code true} if the browser is currently loading.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_loading)(struct _cef_browser_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:102</a>
     */
    boolean isLoading();

    /**
     * Reload the current page.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>void (CEF_CALLBACK* reload)(struct _cef_browser_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:108</a>
     */
    void reload();

    /**
     * Reload the current page ignoring any cached data.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>void (CEF_CALLBACK* reload_ignore_cache)(struct _cef_browser_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:114</a>
     */
    void reloadIgnoreCache();

    /**
     * Stop loading the page.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>void (CEF_CALLBACK* stop_load)(struct _cef_browser_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:120</a>
     */
    void stopLoad();

    /**
     * Returns the globally unique identifier for this browser. This value is also used as the tabId for extension APIs.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>int (CEF_CALLBACK* get_identifier)(struct _cef_browser_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:126</a>
     */
    int getIdentifier();

    /**
     * Returns {@code true} if this object is pointing to the same handle as {@code that} object.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_same)(struct _cef_browser_t* self, struct _cef_browser_t* that);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:133</a>
     */
    boolean isSame(@Nullable CefBrowser that);

    /**
     * Returns {@code true} if the browser is a popup.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_popup)(struct _cef_browser_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:140</a>
     */
    boolean isPopup();

    /**
     * Returns {@code true} if a document has been loaded in the browser.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>int (CEF_CALLBACK* has_document)(struct _cef_browser_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:146</a>
     */
    boolean hasDocument();

    /**
     * Returns the main (top-level) frame for the browser. In the browser process this will return a valid object until
     * after {@link CefLifeSpanHandler#onBeforeClose(CefBrowser)} is called. In the renderer process this will return
     * {@code null} if the main frame is hosted in a different renderer process (e.g. for cross-origin sub-frames). The
     * main frame object will change during cross-origin navigation or re-navigation after renderer process termination
     * (due to crashes, etc).
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>cef_frame_t* (CEF_CALLBACK* get_main_frame)(struct _cef_browser_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:152</a>
     */
    Optional<CefFrame> getMainFrame();

    /**
     * Returns the focused frame for the browser.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>cef_frame_t* (CEF_CALLBACK* get_focused_frame)(struct _cef_browser_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:164</a>
     */
    Optional<CefFrame> getFocusedFrame();

    /**
     * Returns the frame with the specified identifier, or {@code null} if not found.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>
     * cef_frame_t* (CEF_CALLBACK* get_frame_by_identifier)(struct _cef_browser_t* self, const cef_string_t* identifier);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:170</a>
     */
    Optional<CefFrame> getFrameByIdentifier(@Nullable String identifier);

    /**
     * Returns the frame with the specified name, or {@code null} if not found.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>cef_frame_t* (CEF_CALLBACK* get_frame_by_name)(struct _cef_browser_t* self, const cef_string_t* name);</pre>
     *
     * @param name may be null
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:177</a>
     */
    Optional<CefFrame> getFrameByName(@Nullable String name);

    /**
     * Returns the number of frames that currently exist.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>size_t (CEF_CALLBACK* get_frame_count)(struct _cef_browser_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:183</a>
     */
    long getFrameCount();

    /**
     * Returns the identifiers of all existing frames.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>void (CEF_CALLBACK* get_frame_identifiers)(struct _cef_browser_t* self, cef_string_list_t identifiers);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:189</a>
     */
    void getFrameIdentifiers(@Nonnull List<String> identifiers);

    /**
     * Returns the names of all existing frames.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
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
        public void close() {
            closed = true;
            cleanable.clean();
        }

        @Override
        public boolean isClosed() {
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
                N_Release(ptr);
            }
        }

        private static native void N_Release(long ptr);

        @Override
        public boolean isValid() {
            checkNotClosed();
            return N_IsValid(nativePtr);
        }

        @Override
        public Optional<CefBrowserHost> getHost() {
            checkNotClosed();
            return Optional.ofNullable(N_GetHost(nativePtr));
        }

        @Override
        public boolean canGoBack() {
            checkNotClosed();
            return N_CanGoBack(nativePtr);
        }

        @Override
        public void goBack() {
            checkNotClosed();
            N_GoBack(nativePtr);
        }

        @Override
        public boolean canGoForward() {
            checkNotClosed();
            return N_CanGoForward(nativePtr);
        }

        @Override
        public void goForward() {
            checkNotClosed();
            N_GoForward(nativePtr);
        }

        @Override
        public boolean isLoading() {
            checkNotClosed();
            return N_IsLoading(nativePtr);
        }

        @Override
        public void reload() {
            checkNotClosed();
            N_Reload(nativePtr);
        }

        @Override
        public void reloadIgnoreCache() {
            checkNotClosed();
            N_ReloadIgnoreCache(nativePtr);
        }

        @Override
        public void stopLoad() {
            checkNotClosed();
            N_StopLoad(nativePtr);
        }

        @Override
        public int getIdentifier() {
            checkNotClosed();
            return N_GetIdentifier(nativePtr);
        }

        @Override
        public boolean isSame(@Nullable CefBrowser that) {
            checkNotClosed();
            CefLibraryObject.requireOpen(that, "CefBrowser");
            return N_IsSame(nativePtr, that);
        }

        @Override
        public boolean isPopup() {
            checkNotClosed();
            return N_IsPopup(nativePtr);
        }

        @Override
        public boolean hasDocument() {
            checkNotClosed();
            return N_HasDocument(nativePtr);
        }

        @Override
        public Optional<CefFrame> getMainFrame() {
            checkNotClosed();
            return Optional.ofNullable(N_GetMainFrame(nativePtr));
        }

        @Override
        public Optional<CefFrame> getFocusedFrame() {
            checkNotClosed();
            return Optional.ofNullable(N_GetFocusedFrame(nativePtr));
        }

        @Override
        public Optional<CefFrame> getFrameByIdentifier(@Nullable String identifier) {
            checkNotClosed();
            return Optional.ofNullable(N_GetFrameByIdentifier(nativePtr, identifier));
        }

        @Override
        public Optional<CefFrame> getFrameByName(@Nullable String name) {
            checkNotClosed();
            return Optional.ofNullable(N_GetFrameByName(nativePtr, name));
        }

        @Override
        public long getFrameCount() {
            checkNotClosed();
            return N_GetFrameCount(nativePtr);
        }

        @Override
        public void getFrameIdentifiers(@Nonnull List<String> identifiers) {
            checkNotClosed();
            N_GetFrameIdentifiers(nativePtr, identifiers);
        }

        @Override
        public void getFrameNames(@Nonnull List<String> names) {
            checkNotClosed();
            N_GetFrameNames(nativePtr, names);
        }

        private static native boolean N_IsValid(long self);

        private static native CefBrowserHost N_GetHost(long self);

        private static native boolean N_CanGoBack(long self);

        private static native void N_GoBack(long self);

        private static native boolean N_CanGoForward(long self);

        private static native void N_GoForward(long self);

        private static native boolean N_IsLoading(long self);

        private static native void N_Reload(long self);

        private static native void N_ReloadIgnoreCache(long self);

        private static native void N_StopLoad(long self);

        private static native int N_GetIdentifier(long self);

        private static native boolean N_IsSame(long self, CefBrowser that);

        private static native boolean N_IsPopup(long self);

        private static native boolean N_HasDocument(long self);

        private static native CefFrame N_GetMainFrame(long self);

        private static native CefFrame N_GetFocusedFrame(long self);

        private static native CefFrame N_GetFrameByIdentifier(long self, String identifier);

        private static native CefFrame N_GetFrameByName(long self, String name);

        private static native long N_GetFrameCount(long self);

        private static native void N_GetFrameIdentifiers(long self, List<String> identifiers);

        private static native void N_GetFrameNames(long self, List<String> names);

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
