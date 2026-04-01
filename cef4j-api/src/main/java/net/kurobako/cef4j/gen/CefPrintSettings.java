// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Class representing print settings.
 *
 * <p>Definition generated from cef_print_settings_capi.h
 *
 * <pre>typedef struct _cef_print_settings_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_print_settings_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__print__settings_8h.html">cef_print_settings.h:45</a>
 */
public interface CefPrintSettings extends CefLibraryObject {

    /**
     * Returns {@code true} if this object is valid. Do not call any other methods if this function returns
     * {@code false}.
     *
     * <p>Definition generated from cef_print_settings_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_valid)(struct _cef_print_settings_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__download__item_8h.html">cef_download_item.h:49</a>
     */
    boolean isValid();

    /**
     * Returns {@code true} if the values of this object are read-only. Some APIs may expose read-only objects.
     *
     * <p>Definition generated from cef_print_settings_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_read_only)(struct _cef_print_settings_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__print__settings_8h.html">cef_print_settings.h:68</a>
     */
    boolean isReadOnly();

    /**
     * Set the page orientation.
     *
     * <p>Definition generated from cef_print_settings_capi.h
     *
     * <pre>void (CEF_CALLBACK* set_orientation)(struct _cef_print_settings_t* self, int landscape);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__print__settings_8h.html">cef_print_settings.h:75</a>
     */
    void setOrientation(boolean landscape);

    /**
     * Returns {@code true} if the orientation is landscape.
     *
     * <p>Definition generated from cef_print_settings_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_landscape)(struct _cef_print_settings_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__print__settings_8h.html">cef_print_settings.h:81</a>
     */
    boolean isLandscape();

    /**
     * Set the printer printable area in device units. Some platforms already provide flipped area. Set
     * {@code landscape_needs_flip} to {@code false} on those platforms to avoid double flipping.
     *
     * <p>Definition generated from cef_print_settings_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* set_printer_printable_area)(struct _cef_print_settings_t* self, const cef_size_t* physical_size_device_units, const cef_rect_t* printable_area_device_units, int landscape_needs_flip);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__print__settings_8h.html">cef_print_settings.h:87</a>
     */
    void setPrinterPrintableArea(
            @Nonnull CefSize physicalSizeDeviceUnits,
            @Nonnull CefRect printableAreaDeviceUnits,
            boolean landscapeNeedsFlip);

    /**
     * Set the device name.
     *
     * <p>Definition generated from cef_print_settings_capi.h
     *
     * <pre>void (CEF_CALLBACK* set_device_name)(struct _cef_print_settings_t* self, const cef_string_t* name);</pre>
     *
     * @param name may be null
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__print__settings_8h.html">cef_print_settings.h:98</a>
     */
    void setDeviceName(@Nullable String name);

    /**
     * Get the device name.
     *
     * <p>Definition generated from cef_print_settings_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_device_name)(struct _cef_print_settings_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__print__settings_8h.html">cef_print_settings.h:104</a>
     */
    Optional<String> getDeviceName();

    /**
     * Set the DPI (dots per inch).
     *
     * <p>Definition generated from cef_print_settings_capi.h
     *
     * <pre>void (CEF_CALLBACK* set_dpi)(struct _cef_print_settings_t* self, int dpi);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__print__settings_8h.html">cef_print_settings.h:110</a>
     */
    void setDpi(int dpi);

    /**
     * Get the DPI (dots per inch).
     *
     * <p>Definition generated from cef_print_settings_capi.h
     *
     * <pre>int (CEF_CALLBACK* get_dpi)(struct _cef_print_settings_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__print__settings_8h.html">cef_print_settings.h:116</a>
     */
    int getDpi();

    /**
     * Set the page ranges.
     *
     * <p>Definition generated from cef_print_settings_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* set_page_ranges)(struct _cef_print_settings_t* self, size_t rangesCount, cef_range_t const* ranges);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__print__settings_8h.html">cef_print_settings.h:122</a>
     */
    void setPageRanges(long rangescount, @Nonnull CefRange[] ranges);

    /**
     * Returns the number of page ranges that currently exist.
     *
     * <p>Definition generated from cef_print_settings_capi.h
     *
     * <pre>size_t (CEF_CALLBACK* get_page_ranges_count)(struct _cef_print_settings_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__print__settings_8h.html">cef_print_settings.h:128</a>
     */
    long getPageRangesCount();

    /**
     * Retrieve the page ranges.
     *
     * <p>The C API exposes this as a two-pass pattern: first call {@link #getPageRangesCount()} to obtain the count,
     * then allocate and populate the array/collection. This method performs both steps and returns the result directly.
     *
     * <p>Definition generated from cef_print_settings_capi.h
     *
     * <pre>const cef_range_t** (CEF_CALLBACK* get_page_ranges)(struct _cef_print_settings_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__print__settings_8h.html">cef_print_settings.h:134</a>
     */
    List<CefRange> getPageRanges();

    /**
     * Set whether only the selection will be printed.
     *
     * <p>Definition generated from cef_print_settings_capi.h
     *
     * <pre>void (CEF_CALLBACK* set_selection_only)(struct _cef_print_settings_t* self, int selection_only);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__print__settings_8h.html">cef_print_settings.h:140</a>
     */
    void setSelectionOnly(boolean selectionOnly);

    /**
     * Returns {@code true} if only the selection will be printed.
     *
     * <p>Definition generated from cef_print_settings_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_selection_only)(struct _cef_print_settings_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__print__settings_8h.html">cef_print_settings.h:146</a>
     */
    boolean isSelectionOnly();

    /**
     * Set whether pages will be collated.
     *
     * <p>Definition generated from cef_print_settings_capi.h
     *
     * <pre>void (CEF_CALLBACK* set_collate)(struct _cef_print_settings_t* self, int collate);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__print__settings_8h.html">cef_print_settings.h:152</a>
     */
    void setCollate(boolean collate);

    /**
     * Returns {@code true} if pages will be collated.
     *
     * <p>Definition generated from cef_print_settings_capi.h
     *
     * <pre>int (CEF_CALLBACK* will_collate)(struct _cef_print_settings_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__print__settings_8h.html">cef_print_settings.h:158</a>
     */
    boolean willCollate();

    /**
     * Set the color model.
     *
     * <p>Definition generated from cef_print_settings_capi.h
     *
     * <pre>void (CEF_CALLBACK* set_color_model)(struct _cef_print_settings_t* self, cef_color_model_t model);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__print__settings_8h.html">cef_print_settings.h:164</a>
     */
    void setColorModel(@Nonnull CefColorModel model);

    /**
     * Get the color model.
     *
     * <p>Definition generated from cef_print_settings_capi.h
     *
     * <pre>cef_color_model_t (CEF_CALLBACK* get_color_model)(struct _cef_print_settings_t* self);</pre>
     *
     * @return the result, or {@code COLOR_MODEL_UNKNOWN} for default handling
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__print__settings_8h.html">cef_print_settings.h:170</a>
     */
    CefColorModel getColorModel();

    /**
     * Set the number of copies.
     *
     * <p>Definition generated from cef_print_settings_capi.h
     *
     * <pre>void (CEF_CALLBACK* set_copies)(struct _cef_print_settings_t* self, int copies);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__print__settings_8h.html">cef_print_settings.h:176</a>
     */
    void setCopies(int copies);

    /**
     * Get the number of copies.
     *
     * <p>Definition generated from cef_print_settings_capi.h
     *
     * <pre>int (CEF_CALLBACK* get_copies)(struct _cef_print_settings_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__print__settings_8h.html">cef_print_settings.h:182</a>
     */
    int getCopies();

    /**
     * Set the duplex mode.
     *
     * <p>Definition generated from cef_print_settings_capi.h
     *
     * <pre>void (CEF_CALLBACK* set_duplex_mode)(struct _cef_print_settings_t* self, cef_duplex_mode_t mode);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__print__settings_8h.html">cef_print_settings.h:188</a>
     */
    void setDuplexMode(@Nonnull CefDuplexMode mode);

    /**
     * Get the duplex mode.
     *
     * <p>Definition generated from cef_print_settings_capi.h
     *
     * <pre>cef_duplex_mode_t (CEF_CALLBACK* get_duplex_mode)(struct _cef_print_settings_t* self);</pre>
     *
     * @return the result, or {@code DUPLEX_MODE_UNKNOWN} for default handling
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__print__settings_8h.html">cef_print_settings.h:194</a>
     */
    CefDuplexMode getDuplexMode();
    /**
     * Create a new backing store with allocated memory of {@code byte_length} bytes. The memory is uninitialized. This
     * method must be called on a thread with a valid V8 isolate. The returned object can safely be passed to other
     * threads. Returns {@code null} on failure.
     *
     * <p>Definition generated from cef_print_settings_capi.h
     *
     * <pre>CEF_EXPORT cef_print_settings_t* cef_print_settings_create(void);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:445</a>
     */
    static Optional<CefPrintSettings> create() {
        return Optional.ofNullable(NativePeer.N_Create());
    }

    final class NativePeer implements CefPrintSettings, AutoCloseable {
        private final long nativePtr;
        private final java.lang.ref.Cleaner.Cleanable cleanable;

        NativePeer(long ptr) {
            this.nativePtr = ptr;
            this.cleanable = net.kurobako.cef4j.NativeCleaner.INSTANCE.register(this, new Release(ptr));
        }

        @Override
        public void close() {
            cleanable.clean();
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
                N_Release(ptr);
            }
        }

        private static native void N_Release(long ptr);

        @Override
        public boolean isValid() {
            return N_IsValid(nativePtr);
        }

        @Override
        public boolean isReadOnly() {
            return N_IsReadOnly(nativePtr);
        }

        @Override
        public void setOrientation(boolean landscape) {
            N_SetOrientation(nativePtr, landscape);
        }

        @Override
        public boolean isLandscape() {
            return N_IsLandscape(nativePtr);
        }

        @Override
        public void setPrinterPrintableArea(
                @Nonnull CefSize physicalSizeDeviceUnits,
                @Nonnull CefRect printableAreaDeviceUnits,
                boolean landscapeNeedsFlip) {
            N_SetPrinterPrintableArea(nativePtr, physicalSizeDeviceUnits, printableAreaDeviceUnits, landscapeNeedsFlip);
        }

        @Override
        public void setDeviceName(@Nullable String name) {
            N_SetDeviceName(nativePtr, name);
        }

        @Override
        public Optional<String> getDeviceName() {
            return Optional.ofNullable(N_GetDeviceName(nativePtr));
        }

        @Override
        public void setDpi(int dpi) {
            N_SetDpi(nativePtr, dpi);
        }

        @Override
        public int getDpi() {
            return N_GetDpi(nativePtr);
        }

        @Override
        public void setPageRanges(long rangescount, @Nonnull CefRange[] ranges) {
            N_SetPageRanges(nativePtr, rangescount, ranges);
        }

        @Override
        public long getPageRangesCount() {
            return N_GetPageRangesCount(nativePtr);
        }

        @Override
        public List<CefRange> getPageRanges() {
            return Arrays.asList(N_GetPageRanges(nativePtr));
        }

        @Override
        public void setSelectionOnly(boolean selectionOnly) {
            N_SetSelectionOnly(nativePtr, selectionOnly);
        }

        @Override
        public boolean isSelectionOnly() {
            return N_IsSelectionOnly(nativePtr);
        }

        @Override
        public void setCollate(boolean collate) {
            N_SetCollate(nativePtr, collate);
        }

        @Override
        public boolean willCollate() {
            return N_WillCollate(nativePtr);
        }

        @Override
        public void setColorModel(@Nonnull CefColorModel model) {
            N_SetColorModel(nativePtr, model);
        }

        @Override
        public CefColorModel getColorModel() {
            return N_GetColorModel(nativePtr);
        }

        @Override
        public void setCopies(int copies) {
            N_SetCopies(nativePtr, copies);
        }

        @Override
        public int getCopies() {
            return N_GetCopies(nativePtr);
        }

        @Override
        public void setDuplexMode(@Nonnull CefDuplexMode mode) {
            N_SetDuplexMode(nativePtr, mode);
        }

        @Override
        public CefDuplexMode getDuplexMode() {
            return N_GetDuplexMode(nativePtr);
        }

        private static native boolean N_IsValid(long self);

        private static native boolean N_IsReadOnly(long self);

        private static native void N_SetOrientation(long self, boolean landscape);

        private static native boolean N_IsLandscape(long self);

        private static native void N_SetPrinterPrintableArea(
                long self,
                CefSize physicalSizeDeviceUnits,
                CefRect printableAreaDeviceUnits,
                boolean landscapeNeedsFlip);

        private static native void N_SetDeviceName(long self, String name);

        private static native String N_GetDeviceName(long self);

        private static native void N_SetDpi(long self, int dpi);

        private static native int N_GetDpi(long self);

        private static native void N_SetPageRanges(long self, long rangescount, CefRange[] ranges);

        private static native long N_GetPageRangesCount(long self);

        private static native CefRange[] N_GetPageRanges(long self);

        private static native void N_SetSelectionOnly(long self, boolean selectionOnly);

        private static native boolean N_IsSelectionOnly(long self);

        private static native void N_SetCollate(long self, boolean collate);

        private static native boolean N_WillCollate(long self);

        private static native void N_SetColorModel(long self, CefColorModel model);

        private static native CefColorModel N_GetColorModel(long self);

        private static native void N_SetCopies(long self, int copies);

        private static native int N_GetCopies(long self);

        private static native void N_SetDuplexMode(long self, CefDuplexMode mode);

        private static native CefDuplexMode N_GetDuplexMode(long self);

        static native CefPrintSettings N_Create();

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
