// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.nio.ByteBuffer;
import java.util.Optional;
import javax.annotation.Nonnull;

/**
 * Container for a single image represented at different scale factors. All image representations should be the same
 * size in density independent pixel (DIP) units. For example, if the image at scale factor 1.0 is 100x100 pixels then
 * the image at scale factor 2.0 should be 200x200 pixels -- both images will display with a DIP size of 100x100 units.
 * The methods of this class can be called on any browser process thread.
 *
 * <p>Definition generated from cef_image_capi.h
 *
 * <pre>typedef struct _cef_image_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_image_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__image_8h.html">cef_image.h:44</a>
 */
public interface CefImage extends CefLibraryObject {

    /**
     * Returns {@code true} if this Image is empty.
     *
     * <p>Definition generated from cef_image_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_empty)(struct _cef_image_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__image_8h.html">cef_image.h:62</a>
     */
    boolean isEmpty();

    /**
     * Returns {@code true} if this object is pointing to the same handle as {@code that} object.
     *
     * <p>Definition generated from cef_image_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_same)(struct _cef_image_t* self, struct _cef_image_t* that);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__dom_8h.html">cef_dom.h:208</a>
     */
    boolean isSame(@Nonnull CefImage that);

    /**
     * Add a bitmap image representation for {@code scale_factor}. Only 32-bit RGBA/BGRA formats are supported.
     * {@code pixel_width} and {@code pixel_height} are the bitmap representation size in pixel coordinates.
     * {@code pixel_data} is the array of pixel data and should be {@code pixel_width} x {@code pixel_height} x 4 bytes
     * in size. {@code color_type} and {@code alpha_type} values specify the pixel format.
     *
     * <p>Definition generated from cef_image_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* add_bitmap)(struct _cef_image_t* self, float scale_factor, int pixel_width, int pixel_height, cef_color_type_t color_type, cef_alpha_type_t alpha_type, const void* pixel_data, size_t pixel_data_size);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__image_8h.html">cef_image.h:75</a>
     */
    boolean addBitmap(
            float scaleFactor,
            int pixelWidth,
            int pixelHeight,
            @Nonnull CefColorType colorType,
            @Nonnull CefAlphaType alphaType,
            @Nonnull ByteBuffer pixelData);

    /**
     * Add a PNG image representation for {@code scale_factor}. {@code png_data} is the image data of size
     * {@code png_data_size}. Any alpha transparency in the PNG data will be maintained.
     *
     * <p>Definition generated from cef_image_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* add_png)(struct _cef_image_t* self, float scale_factor, const void* png_data, size_t png_data_size);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__image_8h.html">cef_image.h:91</a>
     */
    boolean addPng(float scaleFactor, @Nonnull ByteBuffer pngData);

    /**
     * Create a JPEG image representation for {@code scale_factor}. {@code jpeg_data} is the image data of size
     * {@code jpeg_data_size}. The JPEG format does not support transparency so the alpha byte will be set to 0xFF for
     * all pixels.
     *
     * <p>Definition generated from cef_image_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* add_jpeg)(struct _cef_image_t* self, float scale_factor, const void* jpeg_data, size_t jpeg_data_size);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__image_8h.html">cef_image.h:101</a>
     */
    boolean addJpeg(float scaleFactor, @Nonnull ByteBuffer jpegData);

    /**
     * Returns the image width in density independent pixel (DIP) units.
     *
     * <p>Definition generated from cef_image_capi.h
     *
     * <pre>size_t (CEF_CALLBACK* get_width)(struct _cef_image_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__image_8h.html">cef_image.h:111</a>
     */
    long getWidth();

    /**
     * Returns the image height in density independent pixel (DIP) units.
     *
     * <p>Definition generated from cef_image_capi.h
     *
     * <pre>size_t (CEF_CALLBACK* get_height)(struct _cef_image_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__image_8h.html">cef_image.h:117</a>
     */
    long getHeight();

    /**
     * Returns {@code true} if this image contains a representation for {@code scale_factor}.
     *
     * <p>Definition generated from cef_image_capi.h
     *
     * <pre>int (CEF_CALLBACK* has_representation)(struct _cef_image_t* self, float scale_factor);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__image_8h.html">cef_image.h:123</a>
     */
    boolean hasRepresentation(float scaleFactor);

    /**
     * Removes the representation for {@code scale_factor}. Returns {@code true} on success.
     *
     * <p>Definition generated from cef_image_capi.h
     *
     * <pre>int (CEF_CALLBACK* remove_representation)(struct _cef_image_t* self, float scale_factor);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__image_8h.html">cef_image.h:129</a>
     */
    boolean removeRepresentation(float scaleFactor);

    /**
     * Returns information for the representation that most closely matches {@code scale_factor}.
     * {@code actual_scale_factor} is the actual scale factor for the representation. {@code pixel_width} and
     * {@code pixel_height} are the representation size in pixel coordinates. Returns {@code true} on success.
     *
     * <p>Definition generated from cef_image_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* get_representation_info)(struct _cef_image_t* self, float scale_factor, float* actual_scale_factor, int* pixel_width, int* pixel_height);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__image_8h.html">cef_image.h:135</a>
     */
    boolean getRepresentationInfo(float scaleFactor, float[] actualScaleFactor, int[] pixelWidth, int[] pixelHeight);

    /**
     * Returns the bitmap representation that most closely matches {@code scale_factor}. Only 32-bit RGBA/BGRA formats
     * are supported. {@code color_type} and {@code alpha_type} values specify the desired output pixel format.
     * {@code pixel_width} and {@code pixel_height} are the output representation size in pixel coordinates. Returns a
     * CefBinaryValue containing the pixel data on success or {@code null} on failure.
     *
     * <p>Definition generated from cef_image_capi.h
     *
     * <pre>
     * cef_binary_value_t* (CEF_CALLBACK* get_as_bitmap)(struct _cef_image_t* self, float scale_factor, cef_color_type_t color_type, cef_alpha_type_t alpha_type, int* pixel_width, int* pixel_height);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__image_8h.html">cef_image.h:147</a>
     */
    Optional<CefBinaryValue> getAsBitmap(
            float scaleFactor,
            @Nonnull CefColorType colorType,
            @Nonnull CefAlphaType alphaType,
            int[] pixelWidth,
            int[] pixelHeight);

    /**
     * Returns the PNG representation that most closely matches {@code scale_factor}. If {@code with_transparency} is
     * {@code true} any alpha transparency in the image will be represented in the resulting PNG data.
     * {@code pixel_width} and {@code pixel_height} are the output representation size in pixel coordinates. Returns a
     * CefBinaryValue containing the PNG image data on success or {@code null} on failure.
     *
     * <p>Definition generated from cef_image_capi.h
     *
     * <pre>
     * cef_binary_value_t* (CEF_CALLBACK* get_as_png)(struct _cef_image_t* self, float scale_factor, int with_transparency, int* pixel_width, int* pixel_height);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__image_8h.html">cef_image.h:162</a>
     */
    Optional<CefBinaryValue> getAsPng(float scaleFactor, boolean withTransparency, int[] pixelWidth, int[] pixelHeight);

    /**
     * Returns the JPEG representation that most closely matches {@code scale_factor}. {@code quality} determines the
     * compression level with 0 == lowest and 100 == highest. The JPEG format does not support alpha transparency and
     * the alpha channel, if any, will be discarded. {@code pixel_width} and {@code pixel_height} are the output
     * representation size in pixel coordinates. Returns a CefBinaryValue containing the JPEG image data on success or
     * {@code null} on failure.
     *
     * <p>Definition generated from cef_image_capi.h
     *
     * <pre>
     * cef_binary_value_t* (CEF_CALLBACK* get_as_jpeg)(struct _cef_image_t* self, float scale_factor, int quality, int* pixel_width, int* pixel_height);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__image_8h.html">cef_image.h:176</a>
     */
    Optional<CefBinaryValue> getAsJpeg(float scaleFactor, int quality, int[] pixelWidth, int[] pixelHeight);
    /**
     * Create a new backing store with allocated memory of {@code byte_length} bytes. The memory is uninitialized. This
     * method must be called on a thread with a valid V8 isolate. The returned object can safely be passed to other
     * threads. Returns {@code null} on failure.
     *
     * <p>Definition generated from cef_image_capi.h
     *
     * <pre>CEF_EXPORT cef_image_t* cef_image_create(void);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:445</a>
     */
    static Optional<CefImage> create() {
        return Optional.ofNullable(NativePeer.N_Create());
    }

    final class NativePeer implements CefImage, AutoCloseable {
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

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefImage.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefImage 0x{}", Long.toHexString(ptr));
                N_Release(ptr);
            }
        }

        private static native void N_Release(long ptr);

        @Override
        public boolean isEmpty() {
            return N_IsEmpty(nativePtr);
        }

        @Override
        public boolean isSame(@Nonnull CefImage that) {
            return N_IsSame(nativePtr, that);
        }

        @Override
        public boolean addBitmap(
                float scaleFactor,
                int pixelWidth,
                int pixelHeight,
                @Nonnull CefColorType colorType,
                @Nonnull CefAlphaType alphaType,
                @Nonnull ByteBuffer pixelData) {
            return N_AddBitmap(nativePtr, scaleFactor, pixelWidth, pixelHeight, colorType, alphaType, pixelData);
        }

        @Override
        public boolean addPng(float scaleFactor, @Nonnull ByteBuffer pngData) {
            return N_AddPng(nativePtr, scaleFactor, pngData);
        }

        @Override
        public boolean addJpeg(float scaleFactor, @Nonnull ByteBuffer jpegData) {
            return N_AddJpeg(nativePtr, scaleFactor, jpegData);
        }

        @Override
        public long getWidth() {
            return N_GetWidth(nativePtr);
        }

        @Override
        public long getHeight() {
            return N_GetHeight(nativePtr);
        }

        @Override
        public boolean hasRepresentation(float scaleFactor) {
            return N_HasRepresentation(nativePtr, scaleFactor);
        }

        @Override
        public boolean removeRepresentation(float scaleFactor) {
            return N_RemoveRepresentation(nativePtr, scaleFactor);
        }

        @Override
        public boolean getRepresentationInfo(
                float scaleFactor, float[] actualScaleFactor, int[] pixelWidth, int[] pixelHeight) {
            return N_GetRepresentationInfo(nativePtr, scaleFactor, actualScaleFactor, pixelWidth, pixelHeight);
        }

        @Override
        public Optional<CefBinaryValue> getAsBitmap(
                float scaleFactor,
                @Nonnull CefColorType colorType,
                @Nonnull CefAlphaType alphaType,
                int[] pixelWidth,
                int[] pixelHeight) {
            return Optional.ofNullable(
                    N_GetAsBitmap(nativePtr, scaleFactor, colorType, alphaType, pixelWidth, pixelHeight));
        }

        @Override
        public Optional<CefBinaryValue> getAsPng(
                float scaleFactor, boolean withTransparency, int[] pixelWidth, int[] pixelHeight) {
            return Optional.ofNullable(N_GetAsPng(nativePtr, scaleFactor, withTransparency, pixelWidth, pixelHeight));
        }

        @Override
        public Optional<CefBinaryValue> getAsJpeg(float scaleFactor, int quality, int[] pixelWidth, int[] pixelHeight) {
            return Optional.ofNullable(N_GetAsJpeg(nativePtr, scaleFactor, quality, pixelWidth, pixelHeight));
        }

        private static native boolean N_IsEmpty(long self);

        private static native boolean N_IsSame(long self, CefImage that);

        private static native boolean N_AddBitmap(
                long self,
                float scaleFactor,
                int pixelWidth,
                int pixelHeight,
                CefColorType colorType,
                CefAlphaType alphaType,
                ByteBuffer pixelData);

        private static native boolean N_AddPng(long self, float scaleFactor, ByteBuffer pngData);

        private static native boolean N_AddJpeg(long self, float scaleFactor, ByteBuffer jpegData);

        private static native long N_GetWidth(long self);

        private static native long N_GetHeight(long self);

        private static native boolean N_HasRepresentation(long self, float scaleFactor);

        private static native boolean N_RemoveRepresentation(long self, float scaleFactor);

        private static native boolean N_GetRepresentationInfo(
                long self, float scaleFactor, float[] actualScaleFactor, int[] pixelWidth, int[] pixelHeight);

        private static native CefBinaryValue N_GetAsBitmap(
                long self,
                float scaleFactor,
                CefColorType colorType,
                CefAlphaType alphaType,
                int[] pixelWidth,
                int[] pixelHeight);

        private static native CefBinaryValue N_GetAsPng(
                long self, float scaleFactor, boolean withTransparency, int[] pixelWidth, int[] pixelHeight);

        private static native CefBinaryValue N_GetAsJpeg(
                long self, float scaleFactor, int quality, int[] pixelWidth, int[] pixelHeight);

        static native CefImage N_Create();

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
            return "CefImage{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
