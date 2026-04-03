// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
// -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.processing.Generated;

/**
 * A View hosting a CefBrowser instance. Methods must be called on the browser process UI thread unless otherwise
 * indicated.
 *
 * <p>Definition generated from views/cef_browser_view_capi.h
 *
 * <pre>typedef struct _cef_browser_view_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_browser_view_t;</pre>
 *
 * @see <a
 *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser__view_8h.html">views/cef_browser_view.h:45</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
public interface CefBrowserView extends CefLibraryObject {

    /**
     * Returns the CefBrowser hosted by this BrowserView. Will return {@code null} if the browser has not yet been
     * created or has already been destroyed.
     *
     * <p>Definition generated from views/cef_browser_view_capi.h
     *
     * <pre>cef_browser_t* (CEF_CALLBACK* get_browser)(struct _cef_browser_view_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser__view_8h.html">views/cef_browser_view.h:76</a>
     */
    Optional<CefBrowser> getBrowser();

    /**
     * Returns the Chrome toolbar associated with this BrowserView. Only supported when using Chrome style. The
     * CefBrowserViewDelegate:: GetChromeToolbarType() method must return a value other than
     * {@link CefChromeToolbarType.Kind#NONE} and the toolbar will not be available until after this BrowserView is
     * added to a CefWindow and {@link CefViewDelegate#onWindowChanged(CefView, boolean)} has been called.
     *
     * <p>Definition generated from views/cef_browser_view_capi.h
     *
     * <pre>cef_view_t* (CEF_CALLBACK* get_chrome_toolbar)(struct _cef_browser_view_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser__view_8h.html">views/cef_browser_view.h:83</a>
     */
    Optional<CefView> getChromeToolbar();

    /**
     * Sets whether normal priority accelerators are first forwarded to the web content (`keydown` event handler) or
     * CefKeyboardHandler. Normal priority accelerators can be registered via {@link CefWindow#setAccelerator(int, int,
     * boolean, boolean, boolean, boolean)} (with {@code high_priority}={@code false}) or internally for standard
     * accelerators supported by Chrome style. If {@code prefer_accelerators} is {@code true} then the matching
     * accelerator will be triggered immediately (calling {@link CefWindowDelegate#onAccelerator(CefWindow, int)} or
     * {@link CefCommandHandler#onChromeCommand(CefBrowser, int, CefWindowOpenDisposition)} respectively) and the event
     * will not be forwarded to the web content or CefKeyboardHandler first. If {@code prefer_accelerators} is
     * {@code false} then the matching accelerator will only be triggered if the event is not handled by web content
     * (`keydown` event handler that calls `event.preventDefault()`) or by CefKeyboardHandler. The default value is
     * {@code false}.
     *
     * <p>Definition generated from views/cef_browser_view_capi.h
     *
     * <pre>void (CEF_CALLBACK* set_prefer_accelerators)(struct _cef_browser_view_t* self, int prefer_accelerators);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser__view_8h.html">views/cef_browser_view.h:94</a>
     */
    void setPreferAccelerators(boolean preferAccelerators);

    /**
     * Returns the runtime style for this BrowserView (ALLOY or CHROME). See cef_runtime_style_t documentation for
     * details.
     *
     * <p>Definition generated from views/cef_browser_view_capi.h
     *
     * <pre>cef_runtime_style_t (CEF_CALLBACK* get_runtime_style)(struct _cef_browser_view_t* self);</pre>
     *
     * @return the result, or {@code CEF_RUNTIME_STYLE_DEFAULT} for default handling
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser__view_8h.html">views/cef_browser_view.h:111</a>
     */
    CefRuntimeStyle getRuntimeStyle();
    /**
     * Create a new BrowserView. The underlying CefBrowser will not be created until this view is added to the views
     * hierarchy. The optional {@code extra_info} parameter provides an opportunity to specify extra information
     * specific to the created browser that will be passed to
     * {@link CefRenderProcessHandler#onBrowserCreated(CefBrowser, CefDictionaryValue)} in the render process.
     *
     * <p>Definition generated from views/cef_browser_view_capi.h
     *
     * <pre>
     * CEF_EXPORT cef_browser_view_t* cef_browser_view_create(struct _cef_client_t* client, const cef_string_t* url, const struct _cef_browser_settings_t* settings, struct _cef_dictionary_value_t* extra_info, struct _cef_request_context_t* request_context, struct _cef_browser_view_delegate_t* delegate);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser__view_8h.html">views/cef_browser_view.h:52</a>
     */
    static Optional<CefBrowserView> create(
            @Nullable CefClient client,
            @Nullable String url,
            @Nonnull CefBrowserSettings settings,
            @Nullable CefDictionaryValue extraInfo,
            @Nullable CefRequestContext requestContext,
            @Nullable CefBrowserViewDelegate delegate) {
        return Optional.ofNullable(NativePeer.create0(client, url, settings, extraInfo, requestContext, delegate));
    }

    /**
     * Returns the BrowserView associated with {@code browser}.
     *
     * <p>Definition generated from views/cef_browser_view_capi.h
     *
     * <pre>CEF_EXPORT cef_browser_view_t* cef_browser_view_get_for_browser(struct _cef_browser_t* browser);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser__view_8h.html">views/cef_browser_view.h:70</a>
     */
    static Optional<CefBrowserView> getForBrowser(@Nullable CefBrowser browser) {
        return Optional.ofNullable(NativePeer.getForBrowser0(browser));
    }

    final class NativePeer implements CefBrowserView, AutoCloseable {
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
            if (closed) throw new IllegalStateException("CefBrowserView has been closed");
        }

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefBrowserView.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefBrowserView 0x{}", Long.toHexString(ptr));
                release0(ptr);
            }
        }

        private static native void release0(long ptr);

        @Override
        public Optional<CefBrowser> getBrowser() {
            checkNotClosed();
            return Optional.ofNullable(getBrowser0(nativePtr));
        }

        @Override
        public Optional<CefView> getChromeToolbar() {
            checkNotClosed();
            return Optional.ofNullable(getChromeToolbar0(nativePtr));
        }

        @Override
        public void setPreferAccelerators(boolean preferAccelerators) {
            checkNotClosed();
            setPreferAccelerators0(nativePtr, preferAccelerators);
        }

        @Override
        public CefRuntimeStyle getRuntimeStyle() {
            checkNotClosed();
            return getRuntimeStyle0(nativePtr);
        }

        private static native CefBrowser getBrowser0(long self);

        private static native CefView getChromeToolbar0(long self);

        private static native void setPreferAccelerators0(long self, boolean preferAccelerators);

        private static native CefRuntimeStyle getRuntimeStyle0(long self);

        static native CefBrowserView create0(
                CefClient client,
                String url,
                CefBrowserSettings settings,
                CefDictionaryValue extraInfo,
                CefRequestContext requestContext,
                CefBrowserViewDelegate delegate);

        static native CefBrowserView getForBrowser0(CefBrowser browser);

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
            return "CefBrowserView{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
