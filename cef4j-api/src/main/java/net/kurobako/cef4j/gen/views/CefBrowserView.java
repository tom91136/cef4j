// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen.views;

import javax.annotation.processing.Generated;
import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.kurobako.cef4j.gen.CefBrowser;
import net.kurobako.cef4j.gen.CefBrowserSettings;
import net.kurobako.cef4j.gen.CefClient;
import net.kurobako.cef4j.gen.CefDictionaryValue;
import net.kurobako.cef4j.gen.CefInsets;
import net.kurobako.cef4j.gen.CefLibraryObject;
import net.kurobako.cef4j.gen.CefPoint;
import net.kurobako.cef4j.gen.CefRect;
import net.kurobako.cef4j.gen.CefRequestContext;
import net.kurobako.cef4j.gen.CefRuntimeStyle;
import net.kurobako.cef4j.gen.CefSize;

/**
 * A View hosting a CefBrowser instance. Methods must be called on the browser process UI thread unless otherwise indicated.
 * <p>Definition generated from views/cef_browser_view_capi.h
 * <pre>typedef struct _cef_browser_view_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_browser_view_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__browser__view_8h.html">views/cef_browser_view.h:45</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused", "NullableForbidden"})
public interface CefBrowserView extends CefView {

    /**
     * Returns the CefBrowser hosted by this BrowserView. Will return {@code null} if the browser has not yet been created or has already been destroyed.
     * <p>Definition generated from views/cef_browser_view_capi.h
     * <pre>cef_browser_t* (CEF_CALLBACK* get_browser)(struct _cef_browser_view_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__browser__view_8h.html">views/cef_browser_view.h:76</a>
     */
    Optional<CefBrowser> getBrowser();

    /**
     * Returns the Chrome toolbar associated with this BrowserView. Only supported when using Chrome style. The CefBrowserViewDelegate:: GetChromeToolbarType() method must return a value other than {@link net.kurobako.cef4j.gen.CefChromeToolbarType.Kind#NONE} and the toolbar will not be available until after this BrowserView is added to a CefWindow and {@link net.kurobako.cef4j.gen.views.CefViewDelegate#onWindowChanged(CefView, boolean)} has been called.
     * <p>Definition generated from views/cef_browser_view_capi.h
     * <pre>cef_view_t* (CEF_CALLBACK* get_chrome_toolbar)(struct _cef_browser_view_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__browser__view_8h.html">views/cef_browser_view.h:83</a>
     */
    Optional<CefView> getChromeToolbar();

    /**
     * Sets whether normal priority accelerators are first forwarded to the web content (`keydown` event handler) or CefKeyboardHandler. Normal priority accelerators can be registered via {@link net.kurobako.cef4j.gen.views.CefWindow#setAccelerator(int, int, boolean, boolean, boolean, boolean)} (with {@code high_priority}={@code false}) or internally for standard accelerators supported by Chrome style. If {@code prefer_accelerators} is {@code true} then the matching accelerator will be triggered immediately (calling {@link net.kurobako.cef4j.gen.views.CefWindowDelegate#onAccelerator(CefWindow, int)} or {@link net.kurobako.cef4j.gen.CefCommandHandler#onChromeCommand(CefBrowser, int, CefWindowOpenDisposition)} respectively) and the event will not be forwarded to the web content or CefKeyboardHandler first. If {@code prefer_accelerators} is {@code false} then the matching accelerator will only be triggered if the event is not handled by web content (`keydown` event handler that calls `event.preventDefault()`) or by CefKeyboardHandler. The default value is {@code false}.
     * <p>Definition generated from views/cef_browser_view_capi.h
     * <pre>void (CEF_CALLBACK* set_prefer_accelerators)(struct _cef_browser_view_t* self, int prefer_accelerators);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__browser__view_8h.html">views/cef_browser_view.h:94</a>
     */
    void setPreferAccelerators(boolean preferAccelerators);

    /**
     * Returns the runtime style for this BrowserView (ALLOY or CHROME). See cef_runtime_style_t documentation for details.
     * <p>Definition generated from views/cef_browser_view_capi.h
     * <pre>cef_runtime_style_t (CEF_CALLBACK* get_runtime_style)(struct _cef_browser_view_t* self);</pre>
     *
     * @return the result, or {@code CEF_RUNTIME_STYLE_DEFAULT} for default handling
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__browser__view_8h.html">views/cef_browser_view.h:111</a>
     */
    CefRuntimeStyle getRuntimeStyle();
    /**
     * Create a new BrowserView. The underlying CefBrowser will not be created until this view is added to the views hierarchy. The optional {@code extra_info} parameter provides an opportunity to specify extra information specific to the created browser that will be passed to {@link net.kurobako.cef4j.gen.CefRenderProcessHandler#onBrowserCreated(CefBrowser, CefDictionaryValue)} in the render process.
     * <p>Definition generated from views/cef_browser_view_capi.h
     * <pre>CEF_EXPORT cef_browser_view_t* cef_browser_view_create(struct _cef_client_t* client, const cef_string_t* url, const struct _cef_browser_settings_t* settings, struct _cef_dictionary_value_t* extra_info, struct _cef_request_context_t* request_context, struct _cef_browser_view_delegate_t* delegate);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__browser__view_8h.html">views/cef_browser_view.h:52</a>
     */
    static Optional<CefBrowserView> create(@Nullable CefClient client, @Nullable String url, @Nonnull CefBrowserSettings settings, @Nullable CefDictionaryValue extraInfo, @Nullable CefRequestContext requestContext, @Nullable CefBrowserViewDelegate delegate) {
      return Optional.ofNullable(NativePeer.create0(client, url, settings, extraInfo, requestContext, delegate));
  }

    /**
     * Returns the BrowserView associated with {@code browser}.
     * <p>Definition generated from views/cef_browser_view_capi.h
     * <pre>CEF_EXPORT cef_browser_view_t* cef_browser_view_get_for_browser(struct _cef_browser_t* browser);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__browser__view_8h.html">views/cef_browser_view.h:70</a>
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

        @Override
      public Optional<CefBrowserView> asBrowserView() {
          checkNotClosed();
          return Optional.ofNullable(CefView.NativePeer.asBrowserView0(nativePtr));
      }

        @Override
      public Optional<CefButton> asButton() {
          checkNotClosed();
          return Optional.ofNullable(CefView.NativePeer.asButton0(nativePtr));
      }

        @Override
      public Optional<CefPanel> asPanel() {
          checkNotClosed();
          return Optional.ofNullable(CefView.NativePeer.asPanel0(nativePtr));
      }

        @Override
      public Optional<CefScrollView> asScrollView() {
          checkNotClosed();
          return Optional.ofNullable(CefView.NativePeer.asScrollView0(nativePtr));
      }

        @Override
      public Optional<CefTextfield> asTextfield() {
          checkNotClosed();
          return Optional.ofNullable(CefView.NativePeer.asTextfield0(nativePtr));
      }

        @Override
      public Optional<String> getTypeString() {
          checkNotClosed();
          return Optional.ofNullable(CefView.NativePeer.getTypeString0(nativePtr));
      }

        @Override
      public Optional<String> cefToString(boolean includeChildren) {
          checkNotClosed();
          return Optional.ofNullable(CefView.NativePeer.cefToString0(nativePtr, includeChildren));
      }

        @Override
      public boolean isValid() {
          checkNotClosed();
          return CefView.NativePeer.isValid0(nativePtr);
      }

        @Override
      public boolean isAttached() {
          checkNotClosed();
          return CefView.NativePeer.isAttached0(nativePtr);
      }

        @Override
      public boolean isSame(@Nullable CefView that) {
          checkNotClosed();
            CefLibraryObject.requireOpen(that, "CefView");
          return CefView.NativePeer.isSame0(nativePtr, that);
      }

        @Override
      public Optional<CefViewDelegate> getDelegate() {
          checkNotClosed();
          return Optional.ofNullable(CefView.NativePeer.getDelegate0(nativePtr));
      }

        @Override
      public Optional<CefWindow> getWindow() {
          checkNotClosed();
          return Optional.ofNullable(CefView.NativePeer.getWindow0(nativePtr));
      }

        @Override
      public int getId() {
          checkNotClosed();
          return CefView.NativePeer.getId0(nativePtr);
      }

        @Override
      public void setId(int id) {
          checkNotClosed();
          CefView.NativePeer.setId0(nativePtr, id);
      }

        @Override
      public int getGroupId() {
          checkNotClosed();
          return CefView.NativePeer.getGroupId0(nativePtr);
      }

        @Override
      public void setGroupId(int groupId) {
          checkNotClosed();
          CefView.NativePeer.setGroupId0(nativePtr, groupId);
      }

        @Override
      public Optional<CefView> getParentView() {
          checkNotClosed();
          return Optional.ofNullable(CefView.NativePeer.getParentView0(nativePtr));
      }

        @Override
      public Optional<CefView> getViewForId(int id) {
          checkNotClosed();
          return Optional.ofNullable(CefView.NativePeer.getViewForId0(nativePtr, id));
      }

        @Override
      public void setBounds(@Nonnull CefRect bounds) {
          checkNotClosed();
          CefView.NativePeer.setBounds0(nativePtr, bounds);
      }

        @Override
      public CefRect getBounds() {
          checkNotClosed();
          return CefView.NativePeer.getBounds0(nativePtr);
      }

        @Override
      public CefRect getBoundsInScreen() {
          checkNotClosed();
          return CefView.NativePeer.getBoundsInScreen0(nativePtr);
      }

        @Override
      public void setSize(@Nonnull CefSize size) {
          checkNotClosed();
          CefView.NativePeer.setSize0(nativePtr, size);
      }

        @Override
      public CefSize getSize() {
          checkNotClosed();
          return CefView.NativePeer.getSize0(nativePtr);
      }

        @Override
      public void setPosition(@Nonnull CefPoint position) {
          checkNotClosed();
          CefView.NativePeer.setPosition0(nativePtr, position);
      }

        @Override
      public CefPoint getPosition() {
          checkNotClosed();
          return CefView.NativePeer.getPosition0(nativePtr);
      }

        @Override
      public void setInsets(@Nonnull CefInsets insets) {
          checkNotClosed();
          CefView.NativePeer.setInsets0(nativePtr, insets);
      }

        @Override
      public CefInsets getInsets() {
          checkNotClosed();
          return CefView.NativePeer.getInsets0(nativePtr);
      }

        @Override
      public CefSize getPreferredSize() {
          checkNotClosed();
          return CefView.NativePeer.getPreferredSize0(nativePtr);
      }

        @Override
      public void sizeToPreferredSize() {
          checkNotClosed();
          CefView.NativePeer.sizeToPreferredSize0(nativePtr);
      }

        @Override
      public CefSize getMinimumSize() {
          checkNotClosed();
          return CefView.NativePeer.getMinimumSize0(nativePtr);
      }

        @Override
      public CefSize getMaximumSize() {
          checkNotClosed();
          return CefView.NativePeer.getMaximumSize0(nativePtr);
      }

        @Override
      public int getHeightForWidth(int width) {
          checkNotClosed();
          return CefView.NativePeer.getHeightForWidth0(nativePtr, width);
      }

        @Override
      public void invalidateLayout() {
          checkNotClosed();
          CefView.NativePeer.invalidateLayout0(nativePtr);
      }

        @Override
      public void setVisible(boolean visible) {
          checkNotClosed();
          CefView.NativePeer.setVisible0(nativePtr, visible);
      }

        @Override
      public boolean isVisible() {
          checkNotClosed();
          return CefView.NativePeer.isVisible0(nativePtr);
      }

        @Override
      public boolean isDrawn() {
          checkNotClosed();
          return CefView.NativePeer.isDrawn0(nativePtr);
      }

        @Override
      public void setEnabled(boolean enabled) {
          checkNotClosed();
          CefView.NativePeer.setEnabled0(nativePtr, enabled);
      }

        @Override
      public boolean isEnabled() {
          checkNotClosed();
          return CefView.NativePeer.isEnabled0(nativePtr);
      }

        @Override
      public void setFocusable(boolean focusable) {
          checkNotClosed();
          CefView.NativePeer.setFocusable0(nativePtr, focusable);
      }

        @Override
      public boolean isFocusable() {
          checkNotClosed();
          return CefView.NativePeer.isFocusable0(nativePtr);
      }

        @Override
      public boolean isAccessibilityFocusable() {
          checkNotClosed();
          return CefView.NativePeer.isAccessibilityFocusable0(nativePtr);
      }

        @Override
      public boolean hasFocus() {
          checkNotClosed();
          return CefView.NativePeer.hasFocus0(nativePtr);
      }

        @Override
      public void requestFocus() {
          checkNotClosed();
          CefView.NativePeer.requestFocus0(nativePtr);
      }

        @Override
      public void setBackgroundColor(int color) {
          checkNotClosed();
          CefView.NativePeer.setBackgroundColor0(nativePtr, color);
      }

        @Override
      public int getBackgroundColor() {
          checkNotClosed();
          return CefView.NativePeer.getBackgroundColor0(nativePtr);
      }

        @Override
      public int getThemeColor(int colorId) {
          checkNotClosed();
          return CefView.NativePeer.getThemeColor0(nativePtr, colorId);
      }

        @Override
      public boolean convertPointToScreen(@Nonnull CefPoint.Mutable point) {
          checkNotClosed();
          return CefView.NativePeer.convertPointToScreen0(nativePtr, point);
      }

        @Override
      public boolean convertPointFromScreen(@Nonnull CefPoint.Mutable point) {
          checkNotClosed();
          return CefView.NativePeer.convertPointFromScreen0(nativePtr, point);
      }

        @Override
      public boolean convertPointToWindow(@Nonnull CefPoint.Mutable point) {
          checkNotClosed();
          return CefView.NativePeer.convertPointToWindow0(nativePtr, point);
      }

        @Override
      public boolean convertPointFromWindow(@Nonnull CefPoint.Mutable point) {
          checkNotClosed();
          return CefView.NativePeer.convertPointFromWindow0(nativePtr, point);
      }

        @Override
      public boolean convertPointToView(@Nullable CefView view, @Nonnull CefPoint.Mutable point) {
          checkNotClosed();
            CefLibraryObject.requireOpen(view, "CefView");
          return CefView.NativePeer.convertPointToView0(nativePtr, view, point);
      }

        @Override
      public boolean convertPointFromView(@Nullable CefView view, @Nonnull CefPoint.Mutable point) {
          checkNotClosed();
            CefLibraryObject.requireOpen(view, "CefView");
          return CefView.NativePeer.convertPointFromView0(nativePtr, view, point);
      }

        static native CefBrowser getBrowser0(long self);

        static native CefView getChromeToolbar0(long self);

        static native void setPreferAccelerators0(long self, boolean preferAccelerators);

        static native CefRuntimeStyle getRuntimeStyle0(long self);

        static native CefBrowserView create0(@Nullable CefClient client, @Nullable String url, @Nonnull CefBrowserSettings settings, @Nullable CefDictionaryValue extraInfo, @Nullable CefRequestContext requestContext, @Nullable CefBrowserViewDelegate delegate);
        static native CefBrowserView getForBrowser0(@Nullable CefBrowser browser);

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
