// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen.views;

import javax.annotation.processing.Generated;
import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.kurobako.cef4j.gen.CefDockingMode;
import net.kurobako.cef4j.gen.CefInsets;
import net.kurobako.cef4j.gen.CefLibraryObject;
import net.kurobako.cef4j.gen.CefPoint;
import net.kurobako.cef4j.gen.CefRect;
import net.kurobako.cef4j.gen.CefSize;

/**
 * Controller for an overlay that contains a contents View added via {@link net.kurobako.cef4j.gen.views.CefWindow#addOverlayView(CefView, CefDockingMode, boolean)}. Methods exposed by this controller should be called in preference to methods of the same name exposed by the contents View unless otherwise indicated. Methods must be called on the browser process UI thread unless otherwise indicated.
 * <p>Definition generated from views/cef_overlay_controller_capi.h
 * <pre>typedef struct _cef_overlay_controller_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_overlay_controller_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__overlay__controller_8h.html">views/cef_overlay_controller.h:46</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public interface CefOverlayController extends CefLibraryObject {

    /**
     * Returns {@code true} if this object is valid.
     * <p>Definition generated from views/cef_overlay_controller_capi.h
     * <pre>int (CEF_CALLBACK* is_valid)(struct _cef_overlay_controller_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__overlay__controller_8h.html">views/cef_overlay_controller.h:56</a>
     */
    boolean isValid();

    /**
     * Returns {@code true} if this object is the same as {@code that} object.
     * <p>Definition generated from views/cef_overlay_controller_capi.h
     * <pre>int (CEF_CALLBACK* is_same)(struct _cef_overlay_controller_t* self, struct _cef_overlay_controller_t* that);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__overlay__controller_8h.html">views/cef_overlay_controller.h:62</a>
     */
    boolean isSame(@Nullable CefOverlayController that);

    /**
     * Returns the contents View for this overlay.
     * <p>Definition generated from views/cef_overlay_controller_capi.h
     * <pre>cef_view_t* (CEF_CALLBACK* get_contents_view)(struct _cef_overlay_controller_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__overlay__controller_8h.html">views/cef_overlay_controller.h:68</a>
     */
    Optional<CefView> getContentsView();

    /**
     * Returns the top-level Window hosting this overlay. Use this method instead of calling GetWindow() on the contents View.
     * <p>Definition generated from views/cef_overlay_controller_capi.h
     * <pre>cef_window_t* (CEF_CALLBACK* get_window)(struct _cef_overlay_controller_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__overlay__controller_8h.html">views/cef_overlay_controller.h:74</a>
     */
    Optional<CefWindow> getWindow();

    /**
     * Returns the docking mode for this overlay.
     * <p>Definition generated from views/cef_overlay_controller_capi.h
     * <pre>cef_docking_mode_t (CEF_CALLBACK* get_docking_mode)(struct _cef_overlay_controller_t* self);</pre>
     *
     * @return the result, or {@code CEF_DOCKING_MODE_TOP_LEFT} for default handling
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__overlay__controller_8h.html">views/cef_overlay_controller.h:81</a>
     */
    CefDockingMode getDockingMode();

    /**
     * Destroy this overlay.
     * <p>Definition generated from views/cef_overlay_controller_capi.h
     * <pre>void (CEF_CALLBACK* destroy)(struct _cef_overlay_controller_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__overlay__controller_8h.html">views/cef_overlay_controller.h:87</a>
     */
    void destroy();

    /**
     * Sets the bounds (size and position) of this overlay. This will set the bounds of the contents View to match and trigger a re-layout if necessary. {@code bounds} is in parent coordinates and any insets configured on this overlay will be ignored. Use this method only for overlays created with a docking mode value of {@link net.kurobako.cef4j.gen.CefDockingMode.Kind#CUSTOM}. With other docking modes modify the insets of this overlay and/or layout of the contents View and call SizeToPreferredSize() instead to calculate the new size and re-position the overlay if necessary.
     * <p>Definition generated from views/cef_overlay_controller_capi.h
     * <pre>void (CEF_CALLBACK* set_bounds)(struct _cef_overlay_controller_t* self, const cef_rect_t* bounds);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__overlay__controller_8h.html">views/cef_overlay_controller.h:93</a>
     */
    void setBounds(@Nonnull CefRect bounds);

    /**
     * Returns the bounds (size and position) of this overlay in parent coordinates.
     * <p>Definition generated from views/cef_overlay_controller_capi.h
     * <pre>cef_rect_t* (CEF_CALLBACK* get_bounds)(struct _cef_overlay_controller_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__overlay__controller_8h.html">views/cef_overlay_controller.h:106</a>
     */
    CefRect getBounds();

    /**
     * Returns the bounds (size and position) of this overlay in DIP screen coordinates.
     * <p>Definition generated from views/cef_overlay_controller_capi.h
     * <pre>cef_rect_t* (CEF_CALLBACK* get_bounds_in_screen)(struct _cef_overlay_controller_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__overlay__controller_8h.html">views/cef_overlay_controller.h:113</a>
     */
    CefRect getBoundsInScreen();

    /**
     * Sets the size of this overlay without changing the position. This will set the size of the contents View to match and trigger a re-layout if necessary. {@code size} is in parent coordinates and any insets configured on this overlay will be ignored. Use this method only for overlays created with a docking mode value of {@link net.kurobako.cef4j.gen.CefDockingMode.Kind#CUSTOM}. With other docking modes modify the insets of this overlay and/or layout of the contents View and call SizeToPreferredSize() instead to calculate the new size and re-position the overlay if necessary.
     * <p>Definition generated from views/cef_overlay_controller_capi.h
     * <pre>void (CEF_CALLBACK* set_size)(struct _cef_overlay_controller_t* self, const cef_size_t* size);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__overlay__controller_8h.html">views/cef_overlay_controller.h:120</a>
     */
    void setSize(@Nonnull CefSize size);

    /**
     * Returns the size of this overlay in parent coordinates.
     * <p>Definition generated from views/cef_overlay_controller_capi.h
     * <pre>cef_size_t* (CEF_CALLBACK* get_size)(struct _cef_overlay_controller_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__overlay__controller_8h.html">views/cef_overlay_controller.h:133</a>
     */
    CefSize getSize();

    /**
     * Sets the position of this overlay without changing the size. {@code position} is in parent coordinates and any insets configured on this overlay will be ignored. Use this method only for overlays created with a docking mode value of {@link net.kurobako.cef4j.gen.CefDockingMode.Kind#CUSTOM}. With other docking modes modify the insets of this overlay and/or layout of the contents View and call SizeToPreferredSize() instead to calculate the new size and re-position the overlay if necessary.
     * <p>Definition generated from views/cef_overlay_controller_capi.h
     * <pre>void (CEF_CALLBACK* set_position)(struct _cef_overlay_controller_t* self, const cef_point_t* position);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__overlay__controller_8h.html">views/cef_overlay_controller.h:139</a>
     */
    void setPosition(@Nonnull CefPoint position);

    /**
     * Returns the position of this overlay in parent coordinates.
     * <p>Definition generated from views/cef_overlay_controller_capi.h
     * <pre>cef_point_t* (CEF_CALLBACK* get_position)(struct _cef_overlay_controller_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__overlay__controller_8h.html">views/cef_overlay_controller.h:151</a>
     */
    CefPoint getPosition();

    /**
     * Sets the insets for this overlay. {@code insets} is in parent coordinates. Use this method only for overlays created with a docking mode value other than {@link net.kurobako.cef4j.gen.CefDockingMode.Kind#CUSTOM}.
     * <p>Definition generated from views/cef_overlay_controller_capi.h
     * <pre>void (CEF_CALLBACK* set_insets)(struct _cef_overlay_controller_t* self, const cef_insets_t* insets);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__overlay__controller_8h.html">views/cef_overlay_controller.h:157</a>
     */
    void setInsets(@Nonnull CefInsets insets);

    /**
     * Returns the insets for this overlay in parent coordinates.
     * <p>Definition generated from views/cef_overlay_controller_capi.h
     * <pre>cef_insets_t* (CEF_CALLBACK* get_insets)(struct _cef_overlay_controller_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__overlay__controller_8h.html">views/cef_overlay_controller.h:165</a>
     */
    CefInsets getInsets();

    /**
     * Size this overlay to its preferred size and trigger a re-layout if necessary. The position of overlays created with a docking mode value of {@link net.kurobako.cef4j.gen.CefDockingMode.Kind#CUSTOM} will not be modified by calling this method. With other docking modes this method may re-position the overlay if necessary to accommodate the new size and any insets configured on the contents View.
     * <p>Definition generated from views/cef_overlay_controller_capi.h
     * <pre>void (CEF_CALLBACK* size_to_preferred_size)(struct _cef_overlay_controller_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__overlay__controller_8h.html">views/cef_overlay_controller.h:171</a>
     */
    void sizeToPreferredSize();

    /**
     * Sets whether this overlay is visible. Overlays are hidden by default. If this overlay is hidden then it and any child Views will not be drawn and, if any of those Views currently have focus, then focus will also be cleared. Painting is scheduled as needed.
     * <p>Definition generated from views/cef_overlay_controller_capi.h
     * <pre>void (CEF_CALLBACK* set_visible)(struct _cef_overlay_controller_t* self, int visible);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__overlay__controller_8h.html">views/cef_overlay_controller.h:182</a>
     */
    void setVisible(boolean visible);

    /**
     * Returns whether this overlay is visible. A View may be visible but still not drawn in a Window if any parent Views are hidden. Call IsDrawn() to determine whether this overlay and all parent Views are visible and will be drawn.
     * <p>Definition generated from views/cef_overlay_controller_capi.h
     * <pre>int (CEF_CALLBACK* is_visible)(struct _cef_overlay_controller_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__overlay__controller_8h.html">views/cef_overlay_controller.h:191</a>
     */
    boolean isVisible();

    /**
     * Returns whether this overlay is visible and drawn in a Window. A View is drawn if it and all parent Views are visible. To determine if the containing Window is visible to the user on-screen call IsVisible() on the Window.
     * <p>Definition generated from views/cef_overlay_controller_capi.h
     * <pre>int (CEF_CALLBACK* is_drawn)(struct _cef_overlay_controller_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__overlay__controller_8h.html">views/cef_overlay_controller.h:200</a>
     */
    boolean isDrawn();
    final class NativePeer implements CefOverlayController, AutoCloseable {
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
            if (closed) throw new IllegalStateException("CefOverlayController has been closed");
        }

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefOverlayController.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefOverlayController 0x{}", Long.toHexString(ptr));
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
      public boolean isSame(@Nullable CefOverlayController that) {
          checkNotClosed();
            CefLibraryObject.requireOpen(that, "CefOverlayController");
          return isSame0(nativePtr, that);
      }

        @Override
      public Optional<CefView> getContentsView() {
          checkNotClosed();
          return Optional.ofNullable(getContentsView0(nativePtr));
      }

        @Override
      public Optional<CefWindow> getWindow() {
          checkNotClosed();
          return Optional.ofNullable(getWindow0(nativePtr));
      }

        @Override
      public CefDockingMode getDockingMode() {
          checkNotClosed();
          return getDockingMode0(nativePtr);
      }

        @Override
      public void destroy() {
          checkNotClosed();
          destroy0(nativePtr);
      }

        @Override
      public void setBounds(@Nonnull CefRect bounds) {
          checkNotClosed();
          setBounds0(nativePtr, bounds);
      }

        @Override
      public CefRect getBounds() {
          checkNotClosed();
          return getBounds0(nativePtr);
      }

        @Override
      public CefRect getBoundsInScreen() {
          checkNotClosed();
          return getBoundsInScreen0(nativePtr);
      }

        @Override
      public void setSize(@Nonnull CefSize size) {
          checkNotClosed();
          setSize0(nativePtr, size);
      }

        @Override
      public CefSize getSize() {
          checkNotClosed();
          return getSize0(nativePtr);
      }

        @Override
      public void setPosition(@Nonnull CefPoint position) {
          checkNotClosed();
          setPosition0(nativePtr, position);
      }

        @Override
      public CefPoint getPosition() {
          checkNotClosed();
          return getPosition0(nativePtr);
      }

        @Override
      public void setInsets(@Nonnull CefInsets insets) {
          checkNotClosed();
          setInsets0(nativePtr, insets);
      }

        @Override
      public CefInsets getInsets() {
          checkNotClosed();
          return getInsets0(nativePtr);
      }

        @Override
      public void sizeToPreferredSize() {
          checkNotClosed();
          sizeToPreferredSize0(nativePtr);
      }

        @Override
      public void setVisible(boolean visible) {
          checkNotClosed();
          setVisible0(nativePtr, visible);
      }

        @Override
      public boolean isVisible() {
          checkNotClosed();
          return isVisible0(nativePtr);
      }

        @Override
      public boolean isDrawn() {
          checkNotClosed();
          return isDrawn0(nativePtr);
      }


        static native boolean isValid0(long self);

        static native boolean isSame0(long self, CefOverlayController that);

        static native CefView getContentsView0(long self);

        static native CefWindow getWindow0(long self);

        static native CefDockingMode getDockingMode0(long self);

        static native void destroy0(long self);

        static native void setBounds0(long self, CefRect bounds);

        static native CefRect getBounds0(long self);

        static native CefRect getBoundsInScreen0(long self);

        static native void setSize0(long self, CefSize size);

        static native CefSize getSize0(long self);

        static native void setPosition0(long self, CefPoint position);

        static native CefPoint getPosition0(long self);

        static native void setInsets0(long self, CefInsets insets);

        static native CefInsets getInsets0(long self);

        static native void sizeToPreferredSize0(long self);

        static native void setVisible0(long self, boolean visible);

        static native boolean isVisible0(long self);

        static native boolean isDrawn0(long self);


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
            return "CefOverlayController{0x" + Long.toHexString(nativePtr) + "}";
        }
    }

}
