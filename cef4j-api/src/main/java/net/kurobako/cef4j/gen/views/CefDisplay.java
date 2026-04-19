// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen.views;

import javax.annotation.processing.Generated;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.annotation.Nonnull;
import net.kurobako.cef4j.gen.CefLibraryObject;
import net.kurobako.cef4j.gen.CefPoint;
import net.kurobako.cef4j.gen.CefRect;

/**
 * This class typically, but not always, corresponds to a physical display connected to the system. A fake Display may exist on a headless system, or a Display may correspond to a remote, virtual display. All size and position values are in density independent pixel (DIP) coordinates unless otherwise indicated. Methods must be called on the browser process UI thread unless otherwise indicated. For details on coordinate systems and usage see <a href="https://chromiumembedded.github.io/cef/general_usage#coordinate-systems">https://chromiumembedded.github.io/cef/general_usage#coordinate-systems</a>
 * <p>Definition generated from views/cef_display_capi.h
 * <pre>typedef struct _cef_display_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_display_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__display_8h.html">views/cef_display.h:45</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public interface CefDisplay extends CefLibraryObject {

    /**
     * Returns the ID (media source URN or URL) for this source.
     * <p>Definition generated from views/cef_display_capi.h
     * <pre>int64_t (CEF_CALLBACK* get_id)(struct _cef_display_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__media__router_8h.html">cef_media_router.h:299</a>
     */
    long getId();

    /**
     * Returns this Display's device pixel scale factor. This specifies how much the UI should be scaled when the actual output has more pixels than standard displays (which is around 100~120dpi). The potential return values differ by platform. Windowed browsers with 1.0 zoom will have a JavaScript `window.devicePixelRatio` value matching the associated Display's GetDeviceScaleFactor() value.
     * <p>Definition generated from views/cef_display_capi.h
     * <pre>float (CEF_CALLBACK* get_device_scale_factor)(struct _cef_display_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__display_8h.html">views/cef_display.h:131</a>
     */
    float getDeviceScaleFactor();

    /**
     * Convert {@code point} from DIP coordinates to pixel coordinates using this Display's device scale factor.
     * <p>Definition generated from views/cef_display_capi.h
     * <pre>void (CEF_CALLBACK* convert_point_to_pixels)(struct _cef_display_t* self, cef_point_t* point);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__display_8h.html">views/cef_display.h:142</a>
     */
    void convertPointToPixels(@Nonnull CefPoint.Mutable point);

    /**
     * Convert {@code point} from pixel coordinates to DIP coordinates using this Display's device scale factor.
     * <p>Definition generated from views/cef_display_capi.h
     * <pre>void (CEF_CALLBACK* convert_point_from_pixels)(struct _cef_display_t* self, cef_point_t* point);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__display_8h.html">views/cef_display.h:149</a>
     */
    void convertPointFromPixels(@Nonnull CefPoint.Mutable point);

    /**
     * Returns this Display's bounds in DIP screen coordinates. This is the full size of the display.
     * <p>Definition generated from views/cef_display_capi.h
     * <pre>cef_rect_t* (CEF_CALLBACK* get_bounds)(struct _cef_display_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__display_8h.html">views/cef_display.h:156</a>
     */
    CefRect getBounds();

    /**
     * Returns this Display's work area in DIP screen coordinates. This excludes areas of the display that are occupied with window manager toolbars, etc.
     * <p>Definition generated from views/cef_display_capi.h
     * <pre>cef_rect_t* (CEF_CALLBACK* get_work_area)(struct _cef_display_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__display_8h.html">views/cef_display.h:163</a>
     */
    CefRect getWorkArea();

    /**
     * Returns this Display's rotation in degrees.
     * <p>Definition generated from views/cef_display_capi.h
     * <pre>int (CEF_CALLBACK* get_rotation)(struct _cef_display_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__display_8h.html">views/cef_display.h:170</a>
     */
    int getRotation();
    /**
     * Returns the primary Display.
     * <p>Definition generated from views/cef_display_capi.h
     * <pre>CEF_EXPORT cef_display_t* cef_display_get_primary(void);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__display_8h.html">views/cef_display.h:59</a>
     */
    static Optional<CefDisplay> getPrimary() {
      return Optional.ofNullable(NativePeer.getPrimary0());
  }

    static Optional<CefDisplay> getNearestPoint(@Nonnull CefPoint point, int inputPixelCoords) {
      return Optional.ofNullable(NativePeer.getNearestPoint0(point, inputPixelCoords));
  }

    static Optional<CefDisplay> getMatchingBounds(@Nonnull CefRect bounds, int inputPixelCoords) {
      return Optional.ofNullable(NativePeer.getMatchingBounds0(bounds, inputPixelCoords));
  }

    /**
     * Returns the number of items in this menu.
     * <p>Definition generated from views/cef_display_capi.h
     * <pre>CEF_EXPORT size_t cef_display_get_count(void);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__menu__model_8h.html">cef_menu_model.h:74</a>
     */
    static long getCount() {
      return NativePeer.getCount0();
  }

    static List<CefDisplay> getAlls() {
      return Arrays.asList(NativePeer.getAlls0());
  }

    /**
     * Convert {@code point} from DIP screen coordinates to pixel screen coordinates. This method is only used on Windows.
     * <p>Definition generated from views/cef_display_capi.h
     * <pre>CEF_EXPORT cef_point_t* cef_display_convert_screen_point_to_pixels(const cef_point_t* point);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__display_8h.html">views/cef_display.h:97</a>
     */
    static CefPoint convertScreenPointToPixels(@Nonnull CefPoint point) {
      return NativePeer.convertScreenPointToPixels0(point);
  }

    /**
     * Convert {@code point} from pixel screen coordinates to DIP screen coordinates. This method is only used on Windows.
     * <p>Definition generated from views/cef_display_capi.h
     * <pre>CEF_EXPORT cef_point_t* cef_display_convert_screen_point_from_pixels(const cef_point_t* point);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__display_8h.html">views/cef_display.h:104</a>
     */
    static CefPoint convertScreenPointFromPixels(@Nonnull CefPoint point) {
      return NativePeer.convertScreenPointFromPixels0(point);
  }

    /**
     * Convert {@code rect} from DIP screen coordinates to pixel screen coordinates. This method is only used on Windows.
     * <p>Definition generated from views/cef_display_capi.h
     * <pre>CEF_EXPORT cef_rect_t* cef_display_convert_screen_rect_to_pixels(const cef_rect_t* rect);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__display_8h.html">views/cef_display.h:111</a>
     */
    static CefRect convertScreenRectToPixels(@Nonnull CefRect rect) {
      return NativePeer.convertScreenRectToPixels0(rect);
  }

    /**
     * Convert {@code rect} from pixel screen coordinates to DIP screen coordinates. This method is only used on Windows.
     * <p>Definition generated from views/cef_display_capi.h
     * <pre>CEF_EXPORT cef_rect_t* cef_display_convert_screen_rect_from_pixels(const cef_rect_t* rect);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__display_8h.html">views/cef_display.h:118</a>
     */
    static CefRect convertScreenRectFromPixels(@Nonnull CefRect rect) {
      return NativePeer.convertScreenRectFromPixels0(rect);
  }

    final class NativePeer implements CefDisplay, AutoCloseable {
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
            if (closed) throw new IllegalStateException("CefDisplay has been closed");
        }

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefDisplay.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefDisplay 0x{}", Long.toHexString(ptr));
                release0(ptr);
            }
        }

        private static native void release0(long ptr);

        @Override
      public long getId() {
          checkNotClosed();
          return getId0(nativePtr);
      }

        @Override
      public float getDeviceScaleFactor() {
          checkNotClosed();
          return getDeviceScaleFactor0(nativePtr);
      }

        @Override
      public void convertPointToPixels(@Nonnull CefPoint.Mutable point) {
          checkNotClosed();
          convertPointToPixels0(nativePtr, point);
      }

        @Override
      public void convertPointFromPixels(@Nonnull CefPoint.Mutable point) {
          checkNotClosed();
          convertPointFromPixels0(nativePtr, point);
      }

        @Override
      public CefRect getBounds() {
          checkNotClosed();
          return getBounds0(nativePtr);
      }

        @Override
      public CefRect getWorkArea() {
          checkNotClosed();
          return getWorkArea0(nativePtr);
      }

        @Override
      public int getRotation() {
          checkNotClosed();
          return getRotation0(nativePtr);
      }


        static native long getId0(long self);

        static native float getDeviceScaleFactor0(long self);

        static native void convertPointToPixels0(long self, @Nonnull CefPoint.Mutable point);

        static native void convertPointFromPixels0(long self, @Nonnull CefPoint.Mutable point);

        static native CefRect getBounds0(long self);

        static native CefRect getWorkArea0(long self);

        static native int getRotation0(long self);

        static native CefDisplay getPrimary0();
        static native CefDisplay getNearestPoint0(@Nonnull CefPoint point, int inputPixelCoords);
        static native CefDisplay getMatchingBounds0(@Nonnull CefRect bounds, int inputPixelCoords);
        static native long getCount0();
        static native CefDisplay[] getAlls0();
        static native CefPoint convertScreenPointToPixels0(@Nonnull CefPoint point);
        static native CefPoint convertScreenPointFromPixels0(@Nonnull CefPoint point);
        static native CefRect convertScreenRectToPixels0(@Nonnull CefRect rect);
        static native CefRect convertScreenRectFromPixels0(@Nonnull CefRect rect);

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
            return "CefDisplay{0x" + Long.toHexString(nativePtr) + "}";
        }
    }

}
