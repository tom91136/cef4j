// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen.views;

import javax.annotation.processing.Generated;
import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.kurobako.cef4j.gen.CefBoxLayoutSettings;
import net.kurobako.cef4j.gen.CefInsets;
import net.kurobako.cef4j.gen.CefLibraryObject;
import net.kurobako.cef4j.gen.CefPoint;
import net.kurobako.cef4j.gen.CefRect;
import net.kurobako.cef4j.gen.CefSize;

/**
 * A Panel is a container in the views hierarchy that can contain other Views as children. Methods must be called on the browser process UI thread unless otherwise indicated.
 * <p>Definition generated from views/cef_panel_capi.h
 * <pre>typedef struct _cef_panel_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_panel_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__panel_8h.html">views/cef_panel.h:49</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public interface CefPanel extends CefView {

    /**
     * Returns this Panel as a Window or {@code null} if this is not a Window.
     * <p>Definition generated from views/cef_panel_capi.h
     * <pre>cef_window_t* (CEF_CALLBACK* as_window)(struct _cef_panel_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__panel_8h.html">views/cef_panel.h:63</a>
     */
    Optional<CefWindow> asWindow();

    /**
     * Set this Panel's Layout to FillLayout and return the FillLayout object.
     * <p>Definition generated from views/cef_panel_capi.h
     * <pre>cef_fill_layout_t* (CEF_CALLBACK* set_to_fill_layout)(struct _cef_panel_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__panel_8h.html">views/cef_panel.h:69</a>
     */
    Optional<CefFillLayout> setToFillLayout();

    /**
     * Set this Panel's Layout to BoxLayout and return the BoxLayout object.
     * <p>Definition generated from views/cef_panel_capi.h
     * <pre>cef_box_layout_t* (CEF_CALLBACK* set_to_box_layout)(struct _cef_panel_t* self, const cef_box_layout_settings_t* settings);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__panel_8h.html">views/cef_panel.h:75</a>
     */
    Optional<CefBoxLayout> setToBoxLayout(@Nonnull CefBoxLayoutSettings settings);

    /**
     * Get the Layout.
     * <p>Definition generated from views/cef_panel_capi.h
     * <pre>cef_layout_t* (CEF_CALLBACK* get_layout)(struct _cef_panel_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__panel_8h.html">views/cef_panel.h:82</a>
     */
    Optional<CefLayout> getLayout();

    /**
     * Lay out the child Views (set their bounds based on sizing heuristics specific to the current Layout).
     * <p>Definition generated from views/cef_panel_capi.h
     * <pre>void (CEF_CALLBACK* layout)(struct _cef_panel_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__panel_8h.html">views/cef_panel.h:88</a>
     */
    void layout();

    /**
     * Add a child View.
     * <p>Definition generated from views/cef_panel_capi.h
     * <pre>void (CEF_CALLBACK* add_child_view)(struct _cef_panel_t* self, struct _cef_view_t* view);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__panel_8h.html">views/cef_panel.h:95</a>
     */
    void addChildView(@Nullable CefView view);

    /**
     * Add a child View at the specified {@code index}. If {@code index} matches the result of GetChildCount() then the View will be added at the end.
     * <p>Definition generated from views/cef_panel_capi.h
     * <pre>void (CEF_CALLBACK* add_child_view_at)(struct _cef_panel_t* self, struct _cef_view_t* view, int index);</pre>
     *
     * @param index zero-based index
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__panel_8h.html">views/cef_panel.h:101</a>
     */
    void addChildViewAt(@Nullable CefView view, int index);

    /**
     * Move the child View to the specified {@code index}. A negative value for {@code index} will move the View to the end.
     * <p>Definition generated from views/cef_panel_capi.h
     * <pre>void (CEF_CALLBACK* reorder_child_view)(struct _cef_panel_t* self, struct _cef_view_t* view, int index);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__panel_8h.html">views/cef_panel.h:108</a>
     */
    void reorderChildView(@Nullable CefView view, int index);

    /**
     * Remove a child View. The View can then be added to another Panel.
     * <p>Definition generated from views/cef_panel_capi.h
     * <pre>void (CEF_CALLBACK* remove_child_view)(struct _cef_panel_t* self, struct _cef_view_t* view);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__panel_8h.html">views/cef_panel.h:115</a>
     */
    void removeChildView(@Nullable CefView view);

    /**
     * Remove all child Views. The removed Views will be deleted if the client holds no references to them.
     * <p>Definition generated from views/cef_panel_capi.h
     * <pre>void (CEF_CALLBACK* remove_all_child_views)(struct _cef_panel_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__panel_8h.html">views/cef_panel.h:121</a>
     */
    void removeAllChildViews();

    /**
     * Returns the number of child Views.
     * <p>Definition generated from views/cef_panel_capi.h
     * <pre>size_t (CEF_CALLBACK* get_child_view_count)(struct _cef_panel_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__panel_8h.html">views/cef_panel.h:128</a>
     */
    long getChildViewCount();
    /**
     * Create a new Panel.
     * <p>Definition generated from views/cef_panel_capi.h
     * <pre>CEF_EXPORT cef_panel_t* cef_panel_create(struct _cef_panel_delegate_t* delegate);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__panel_8h.html">views/cef_panel.h:57</a>
     */
    static Optional<CefPanel> create(@Nullable CefPanelDelegate delegate) {
      return Optional.ofNullable(NativePeer.create0(delegate));
  }

    final class NativePeer implements CefPanel, AutoCloseable {
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
            if (closed) throw new IllegalStateException("CefPanel has been closed");
        }

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefPanel.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefPanel 0x{}", Long.toHexString(ptr));
                release0(ptr);
            }
        }

        private static native void release0(long ptr);

        @Override
      public Optional<CefWindow> asWindow() {
          checkNotClosed();
          return Optional.ofNullable(asWindow0(nativePtr));
      }

        @Override
      public Optional<CefFillLayout> setToFillLayout() {
          checkNotClosed();
          return Optional.ofNullable(setToFillLayout0(nativePtr));
      }

        @Override
      public Optional<CefBoxLayout> setToBoxLayout(@Nonnull CefBoxLayoutSettings settings) {
          checkNotClosed();
          return Optional.ofNullable(setToBoxLayout0(nativePtr, settings));
      }

        @Override
      public Optional<CefLayout> getLayout() {
          checkNotClosed();
          return Optional.ofNullable(getLayout0(nativePtr));
      }

        @Override
      public void layout() {
          checkNotClosed();
          layout0(nativePtr);
      }

        @Override
      public void addChildView(@Nullable CefView view) {
          checkNotClosed();
            CefLibraryObject.requireOpen(view, "CefView");
          addChildView0(nativePtr, view);
      }

        @Override
      public void addChildViewAt(@Nullable CefView view, int index) {
          checkNotClosed();
            CefLibraryObject.requireOpen(view, "CefView");
          addChildViewAt0(nativePtr, view, index);
      }

        @Override
      public void reorderChildView(@Nullable CefView view, int index) {
          checkNotClosed();
            CefLibraryObject.requireOpen(view, "CefView");
          reorderChildView0(nativePtr, view, index);
      }

        @Override
      public void removeChildView(@Nullable CefView view) {
          checkNotClosed();
            CefLibraryObject.requireOpen(view, "CefView");
          removeChildView0(nativePtr, view);
      }

        @Override
      public void removeAllChildViews() {
          checkNotClosed();
          removeAllChildViews0(nativePtr);
      }

        @Override
      public long getChildViewCount() {
          checkNotClosed();
          return getChildViewCount0(nativePtr);
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

        static native CefWindow asWindow0(long self);

        static native CefFillLayout setToFillLayout0(long self);

        static native CefBoxLayout setToBoxLayout0(long self, CefBoxLayoutSettings settings);

        static native CefLayout getLayout0(long self);

        static native void layout0(long self);

        static native void addChildView0(long self, CefView view);

        static native void addChildViewAt0(long self, CefView view, int index);

        static native void reorderChildView0(long self, CefView view, int index);

        static native void removeChildView0(long self, CefView view);

        static native void removeAllChildViews0(long self);

        static native long getChildViewCount0(long self);

        static native CefPanel create0(CefPanelDelegate delegate);

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
            return "CefPanel{0x" + Long.toHexString(nativePtr) + "}";
        }
    }

}
