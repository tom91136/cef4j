// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Class representing print settings.
 * <p>Definition generated from cef_print_settings_capi.h
 * <pre>typedef struct _cef_print_settings_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_print_settings_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__print__settings_8h.html">cef_print_settings.h:45</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused", "NullableForbidden"})
public interface CefPrintSettings extends CefLibraryObject {

    /**
     * Returns {@code true} if this object is valid. Do not call any other methods if this function returns {@code false}.
     * <p>Definition generated from cef_print_settings_capi.h
     * <pre>int (CEF_CALLBACK* is_valid)(struct _cef_print_settings_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__print__settings_8h.html">cef_print_settings.h:61</a>
     */
    boolean isValid();

    /**
     * Returns {@code true} if the values of this object are read-only. Some APIs may expose read-only objects.
     * <p>Definition generated from cef_print_settings_capi.h
     * <pre>int (CEF_CALLBACK* is_read_only)(struct _cef_print_settings_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__print__settings_8h.html">cef_print_settings.h:68</a>
     */
    boolean isReadOnly();

    /**
     * Set the page orientation.
     * <p>Definition generated from cef_print_settings_capi.h
     * <pre>void (CEF_CALLBACK* set_orientation)(struct _cef_print_settings_t* self, int landscape);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__print__settings_8h.html">cef_print_settings.h:75</a>
     */
    void setOrientation(boolean landscape);

    /**
     * Returns {@code true} if the orientation is landscape.
     * <p>Definition generated from cef_print_settings_capi.h
     * <pre>int (CEF_CALLBACK* is_landscape)(struct _cef_print_settings_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__print__settings_8h.html">cef_print_settings.h:81</a>
     */
    boolean isLandscape();

    /**
     * Set the printer printable area in device units. Some platforms already provide flipped area. Set {@code landscape_needs_flip} to {@code false} on those platforms to avoid double flipping.
     * <p>Definition generated from cef_print_settings_capi.h
     * <pre>void (CEF_CALLBACK* set_printer_printable_area)(struct _cef_print_settings_t* self, const cef_size_t* physical_size_device_units, const cef_rect_t* printable_area_device_units, int landscape_needs_flip);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__print__settings_8h.html">cef_print_settings.h:87</a>
     */
    void setPrinterPrintableArea(@Nonnull CefSize physicalSizeDeviceUnits, @Nonnull CefRect printableAreaDeviceUnits, boolean landscapeNeedsFlip);

    /**
     * Set the device name.
     * <p>Definition generated from cef_print_settings_capi.h
     * <pre>void (CEF_CALLBACK* set_device_name)(struct _cef_print_settings_t* self, const cef_string_t* name);</pre>
     *
     * @param name may be null
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__print__settings_8h.html">cef_print_settings.h:98</a>
     */
    void setDeviceName(@Nullable String name);

    /**
     * Get the device name.
     * <p>Definition generated from cef_print_settings_capi.h
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_device_name)(struct _cef_print_settings_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__print__settings_8h.html">cef_print_settings.h:104</a>
     */
    Optional<String> getDeviceName();

    /**
     * Set the DPI (dots per inch).
     * <p>Definition generated from cef_print_settings_capi.h
     * <pre>void (CEF_CALLBACK* set_dpi)(struct _cef_print_settings_t* self, int dpi);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__print__settings_8h.html">cef_print_settings.h:110</a>
     */
    void setDpi(int dpi);

    /**
     * Get the DPI (dots per inch).
     * <p>Definition generated from cef_print_settings_capi.h
     * <pre>int (CEF_CALLBACK* get_dpi)(struct _cef_print_settings_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__print__settings_8h.html">cef_print_settings.h:116</a>
     */
    int getDpi();

    /**
     * Set the page ranges.
     * <p>Definition generated from cef_print_settings_capi.h
     * <pre>void (CEF_CALLBACK* set_page_ranges)(struct _cef_print_settings_t* self, size_t rangesCount, cef_range_t const* ranges);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__print__settings_8h.html">cef_print_settings.h:122</a>
     */
    void setPageRanges(long rangesCount, @Nonnull CefRange[] ranges);

    /**
     * Returns the number of page ranges that currently exist.
     * <p>Definition generated from cef_print_settings_capi.h
     * <pre>size_t (CEF_CALLBACK* get_page_ranges_count)(struct _cef_print_settings_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__print__settings_8h.html">cef_print_settings.h:128</a>
     */
    long getPageRangesCount();

    /**
     * Retrieve the page ranges.
     * <p><b>The C API exposes this as a two-pass pattern: first call {@link #getPageRangesCount()} to obtain
     * the count, then allocate and populate the array/collection. This method performs both steps and returns the
     * result directly.</b>
     * <p>Definition generated from cef_print_settings_capi.h
     * <pre>const cef_range_t** (CEF_CALLBACK* get_page_ranges)(struct _cef_print_settings_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__print__settings_8h.html">cef_print_settings.h:134</a>
     */
    List<CefRange> getPageRanges();

    /**
     * Set whether only the selection will be printed.
     * <p>Definition generated from cef_print_settings_capi.h
     * <pre>void (CEF_CALLBACK* set_selection_only)(struct _cef_print_settings_t* self, int selection_only);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__print__settings_8h.html">cef_print_settings.h:140</a>
     */
    void setSelectionOnly(boolean selectionOnly);

    /**
     * Returns {@code true} if only the selection will be printed.
     * <p>Definition generated from cef_print_settings_capi.h
     * <pre>int (CEF_CALLBACK* is_selection_only)(struct _cef_print_settings_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__print__settings_8h.html">cef_print_settings.h:146</a>
     */
    boolean isSelectionOnly();

    /**
     * Set whether pages will be collated.
     * <p>Definition generated from cef_print_settings_capi.h
     * <pre>void (CEF_CALLBACK* set_collate)(struct _cef_print_settings_t* self, int collate);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__print__settings_8h.html">cef_print_settings.h:152</a>
     */
    void setCollate(boolean collate);

    /**
     * Returns {@code true} if pages will be collated.
     * <p>Definition generated from cef_print_settings_capi.h
     * <pre>int (CEF_CALLBACK* will_collate)(struct _cef_print_settings_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__print__settings_8h.html">cef_print_settings.h:158</a>
     */
    boolean willCollate();

    /**
     * Set the color model.
     * <p>Definition generated from cef_print_settings_capi.h
     * <pre>void (CEF_CALLBACK* set_color_model)(struct _cef_print_settings_t* self, cef_color_model_t model);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__print__settings_8h.html">cef_print_settings.h:164</a>
     */
    void setColorModel(@Nonnull CefColorModel model);

    /**
     * Get the color model.
     * <p>Definition generated from cef_print_settings_capi.h
     * <pre>cef_color_model_t (CEF_CALLBACK* get_color_model)(struct _cef_print_settings_t* self);</pre>
     *
     * @return the result, or {@code COLOR_MODEL_UNKNOWN} for default handling
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__print__settings_8h.html">cef_print_settings.h:170</a>
     */
    CefColorModel getColorModel();

    /**
     * Set the number of copies.
     * <p>Definition generated from cef_print_settings_capi.h
     * <pre>void (CEF_CALLBACK* set_copies)(struct _cef_print_settings_t* self, int copies);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__print__settings_8h.html">cef_print_settings.h:176</a>
     */
    void setCopies(int copies);

    /**
     * Get the number of copies.
     * <p>Definition generated from cef_print_settings_capi.h
     * <pre>int (CEF_CALLBACK* get_copies)(struct _cef_print_settings_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__print__settings_8h.html">cef_print_settings.h:182</a>
     */
    int getCopies();

    /**
     * Set the duplex mode.
     * <p>Definition generated from cef_print_settings_capi.h
     * <pre>void (CEF_CALLBACK* set_duplex_mode)(struct _cef_print_settings_t* self, cef_duplex_mode_t mode);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__print__settings_8h.html">cef_print_settings.h:188</a>
     */
    void setDuplexMode(@Nonnull CefDuplexMode mode);

    /**
     * Get the duplex mode.
     * <p>Definition generated from cef_print_settings_capi.h
     * <pre>cef_duplex_mode_t (CEF_CALLBACK* get_duplex_mode)(struct _cef_print_settings_t* self);</pre>
     *
     * @return the result, or {@code DUPLEX_MODE_UNKNOWN} for default handling
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__print__settings_8h.html">cef_print_settings.h:194</a>
     */
    CefDuplexMode getDuplexMode();
    /**
     * Create a new CefPrintSettings object.
     * <p>Definition generated from cef_print_settings_capi.h
     * <pre>CEF_EXPORT cef_print_settings_t* cef_print_settings_create(void);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__print__settings_8h.html">cef_print_settings.h:55</a>
     */
    static Optional<CefPrintSettings> create() {
      return Optional.ofNullable(NativePeer.create0());
  }

    final class NativePeer implements CefPrintSettings, AutoCloseable {
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
            if (closed) throw new IllegalStateException("CefPrintSettings has been closed");
        }

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefPrintSettings.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefPrintSettings 0x{}", Long.toHexString(ptr));
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
      public boolean isReadOnly() {
          checkNotClosed();
          return isReadOnly0(nativePtr);
      }

        @Override
      public void setOrientation(boolean landscape) {
          checkNotClosed();
          setOrientation0(nativePtr, landscape);
      }

        @Override
      public boolean isLandscape() {
          checkNotClosed();
          return isLandscape0(nativePtr);
      }

        @Override
      public void setPrinterPrintableArea(@Nonnull CefSize physicalSizeDeviceUnits, @Nonnull CefRect printableAreaDeviceUnits, boolean landscapeNeedsFlip) {
          checkNotClosed();
          setPrinterPrintableArea0(nativePtr, physicalSizeDeviceUnits, printableAreaDeviceUnits, landscapeNeedsFlip);
      }

        @Override
      public void setDeviceName(@Nullable String name) {
          checkNotClosed();
          setDeviceName0(nativePtr, name);
      }

        @Override
      public Optional<String> getDeviceName() {
          checkNotClosed();
          return Optional.ofNullable(getDeviceName0(nativePtr));
      }

        @Override
      public void setDpi(int dpi) {
          checkNotClosed();
          setDpi0(nativePtr, dpi);
      }

        @Override
      public int getDpi() {
          checkNotClosed();
          return getDpi0(nativePtr);
      }

        @Override
      public void setPageRanges(long rangesCount, @Nonnull CefRange[] ranges) {
          checkNotClosed();
          setPageRanges0(nativePtr, rangesCount, ranges);
      }

        @Override
      public long getPageRangesCount() {
          checkNotClosed();
          return getPageRangesCount0(nativePtr);
      }

        @Override
      public List<CefRange> getPageRanges() {
          checkNotClosed();
          return Arrays.asList(getPageRanges0(nativePtr));
      }

        @Override
      public void setSelectionOnly(boolean selectionOnly) {
          checkNotClosed();
          setSelectionOnly0(nativePtr, selectionOnly);
      }

        @Override
      public boolean isSelectionOnly() {
          checkNotClosed();
          return isSelectionOnly0(nativePtr);
      }

        @Override
      public void setCollate(boolean collate) {
          checkNotClosed();
          setCollate0(nativePtr, collate);
      }

        @Override
      public boolean willCollate() {
          checkNotClosed();
          return willCollate0(nativePtr);
      }

        @Override
      public void setColorModel(@Nonnull CefColorModel model) {
          checkNotClosed();
          setColorModel0(nativePtr, model);
      }

        @Override
      public CefColorModel getColorModel() {
          checkNotClosed();
          return getColorModel0(nativePtr);
      }

        @Override
      public void setCopies(int copies) {
          checkNotClosed();
          setCopies0(nativePtr, copies);
      }

        @Override
      public int getCopies() {
          checkNotClosed();
          return getCopies0(nativePtr);
      }

        @Override
      public void setDuplexMode(@Nonnull CefDuplexMode mode) {
          checkNotClosed();
          setDuplexMode0(nativePtr, mode);
      }

        @Override
      public CefDuplexMode getDuplexMode() {
          checkNotClosed();
          return getDuplexMode0(nativePtr);
      }


        static native boolean isValid0(long self);

        static native boolean isReadOnly0(long self);

        static native void setOrientation0(long self, boolean landscape);

        static native boolean isLandscape0(long self);

        static native void setPrinterPrintableArea0(long self, @Nonnull CefSize physicalSizeDeviceUnits, @Nonnull CefRect printableAreaDeviceUnits, boolean landscapeNeedsFlip);

        static native void setDeviceName0(long self, @Nullable String name);

        static native String getDeviceName0(long self);

        static native void setDpi0(long self, int dpi);

        static native int getDpi0(long self);

        static native void setPageRanges0(long self, long rangesCount, @Nonnull CefRange[] ranges);

        static native long getPageRangesCount0(long self);

        static native CefRange[] getPageRanges0(long self);

        static native void setSelectionOnly0(long self, boolean selectionOnly);

        static native boolean isSelectionOnly0(long self);

        static native void setCollate0(long self, boolean collate);

        static native boolean willCollate0(long self);

        static native void setColorModel0(long self, @Nonnull CefColorModel model);

        static native CefColorModel getColorModel0(long self);

        static native void setCopies0(long self, int copies);

        static native int getCopies0(long self);

        static native void setDuplexMode0(long self, @Nonnull CefDuplexMode mode);

        static native CefDuplexMode getDuplexMode0(long self);

        static native CefPrintSettings create0();

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
            return "CefPrintSettings{0x" + Long.toHexString(nativePtr) + "}";
        }
    }

}
