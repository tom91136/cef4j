// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen.views;

import javax.annotation.processing.Generated;
import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.kurobako.cef4j.gen.CefInsets;
import net.kurobako.cef4j.gen.CefLibraryObject;
import net.kurobako.cef4j.gen.CefPoint;
import net.kurobako.cef4j.gen.CefRect;
import net.kurobako.cef4j.gen.CefSize;

/**
 * A ScrollView will show horizontal and/or vertical scrollbars when necessary based on the size of the attached content view. Methods must be called on the browser process UI thread unless otherwise indicated.
 * <p>Definition generated from views/cef_scroll_view_capi.h
 * <pre>typedef struct _cef_scroll_view_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_scroll_view_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__scroll__view_8h.html">views/cef_scroll_view.h:43</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public interface CefScrollView extends CefView {

    /**
     * Set the content View. The content View must have a specified size (e.g. via {@link net.kurobako.cef4j.gen.views.CefView#setBounds(CefRect)} or {@link net.kurobako.cef4j.gen.views.CefViewDelegate#getPreferredSize(CefView)}).
     * <p>Definition generated from views/cef_scroll_view_capi.h
     * <pre>void (CEF_CALLBACK* set_content_view)(struct _cef_scroll_view_t* self, struct _cef_view_t* view);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__scroll__view_8h.html">views/cef_scroll_view.h:58</a>
     */
    void setContentView(@Nullable CefView view);

    /**
     * Returns the content View.
     * <p>Definition generated from views/cef_scroll_view_capi.h
     * <pre>cef_view_t* (CEF_CALLBACK* get_content_view)(struct _cef_scroll_view_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__scroll__view_8h.html">views/cef_scroll_view.h:65</a>
     */
    Optional<CefView> getContentView();

    /**
     * Returns the visible region of the content View.
     * <p>Definition generated from views/cef_scroll_view_capi.h
     * <pre>cef_rect_t* (CEF_CALLBACK* get_visible_content_rect)(struct _cef_scroll_view_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__scroll__view_8h.html">views/cef_scroll_view.h:71</a>
     */
    CefRect getVisibleContentRect();

    /**
     * Returns {@code true} if the horizontal scrollbar is currently showing.
     * <p>Definition generated from views/cef_scroll_view_capi.h
     * <pre>int (CEF_CALLBACK* has_horizontal_scrollbar)(struct _cef_scroll_view_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__scroll__view_8h.html">views/cef_scroll_view.h:77</a>
     */
    boolean hasHorizontalScrollbar();

    /**
     * Returns the height of the horizontal scrollbar.
     * <p>Definition generated from views/cef_scroll_view_capi.h
     * <pre>int (CEF_CALLBACK* get_horizontal_scrollbar_height)(struct _cef_scroll_view_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__scroll__view_8h.html">views/cef_scroll_view.h:83</a>
     */
    int getHorizontalScrollbarHeight();

    /**
     * Returns {@code true} if the vertical scrollbar is currently showing.
     * <p>Definition generated from views/cef_scroll_view_capi.h
     * <pre>int (CEF_CALLBACK* has_vertical_scrollbar)(struct _cef_scroll_view_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__scroll__view_8h.html">views/cef_scroll_view.h:89</a>
     */
    boolean hasVerticalScrollbar();

    /**
     * Returns the width of the vertical scrollbar.
     * <p>Definition generated from views/cef_scroll_view_capi.h
     * <pre>int (CEF_CALLBACK* get_vertical_scrollbar_width)(struct _cef_scroll_view_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__scroll__view_8h.html">views/cef_scroll_view.h:95</a>
     */
    int getVerticalScrollbarWidth();
    /**
     * Create a new ScrollView.
     * <p>Definition generated from views/cef_scroll_view_capi.h
     * <pre>CEF_EXPORT cef_scroll_view_t* cef_scroll_view_create(struct _cef_view_delegate_t* delegate);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__scroll__view_8h.html">views/cef_scroll_view.h:51</a>
     */
    static Optional<CefScrollView> create(@Nullable CefViewDelegate delegate) {
      return Optional.ofNullable(NativePeer.create0(delegate));
  }

    final class NativePeer implements CefScrollView, AutoCloseable {
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
            if (closed) throw new IllegalStateException("CefScrollView has been closed");
        }

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefScrollView.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefScrollView 0x{}", Long.toHexString(ptr));
                release0(ptr);
            }
        }

        private static native void release0(long ptr);

        @Override
      public void setContentView(@Nullable CefView view) {
          checkNotClosed();
            CefLibraryObject.requireOpen(view, "CefView");
          setContentView0(nativePtr, view);
      }

        @Override
      public Optional<CefView> getContentView() {
          checkNotClosed();
          return Optional.ofNullable(getContentView0(nativePtr));
      }

        @Override
      public CefRect getVisibleContentRect() {
          checkNotClosed();
          return getVisibleContentRect0(nativePtr);
      }

        @Override
      public boolean hasHorizontalScrollbar() {
          checkNotClosed();
          return hasHorizontalScrollbar0(nativePtr);
      }

        @Override
      public int getHorizontalScrollbarHeight() {
          checkNotClosed();
          return getHorizontalScrollbarHeight0(nativePtr);
      }

        @Override
      public boolean hasVerticalScrollbar() {
          checkNotClosed();
          return hasVerticalScrollbar0(nativePtr);
      }

        @Override
      public int getVerticalScrollbarWidth() {
          checkNotClosed();
          return getVerticalScrollbarWidth0(nativePtr);
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

        static native void setContentView0(long self, @Nullable CefView view);

        static native CefView getContentView0(long self);

        static native CefRect getVisibleContentRect0(long self);

        static native boolean hasHorizontalScrollbar0(long self);

        static native int getHorizontalScrollbarHeight0(long self);

        static native boolean hasVerticalScrollbar0(long self);

        static native int getVerticalScrollbarWidth0(long self);

        static native CefScrollView create0(@Nullable CefViewDelegate delegate);

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
            return "CefScrollView{0x" + Long.toHexString(nativePtr) + "}";
        }
    }

}
