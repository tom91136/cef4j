// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import java.nio.ByteBuffer;
import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Container for a single image represented at different scale factors. All image representations should be the same size in density independent pixel (DIP) units. For example, if the image at scale factor 1.0 is 100x100 pixels then the image at scale factor 2.0 should be 200x200 pixels -- both images will display with a DIP size of 100x100 units. The methods of this class can be called on any browser process thread.
 * <p>Definition generated from cef_image_capi.h
 * <pre>typedef struct _cef_image_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_image_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__image_8h.html">cef_image.h:44</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public interface CefImage extends CefLibraryObject {

    /**
     * Returns {@code true} if this Image is empty.
     * <p>Definition generated from cef_image_capi.h
     * <pre>int (CEF_CALLBACK* is_empty)(struct _cef_image_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__image_8h.html">cef_image.h:62</a>
     */
    boolean isEmpty();

    /**
     * Returns {@code true} if this Image and {@code that} Image share the same underlying storage. Will also return {@code true} if both images are empty.
     * <p>Definition generated from cef_image_capi.h
     * <pre>int (CEF_CALLBACK* is_same)(struct _cef_image_t* self, struct _cef_image_t* that);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__image_8h.html">cef_image.h:68</a>
     */
    boolean isSame(@Nullable CefImage that);

    /**
     * Add a bitmap image representation for {@code scale_factor}. Only 32-bit RGBA/BGRA formats are supported. {@code pixel_width} and {@code pixel_height} are the bitmap representation size in pixel coordinates. {@code pixel_data} is the array of pixel data and should be {@code pixel_width} x {@code pixel_height} x 4 bytes in size. {@code color_type} and {@code alpha_type} values specify the pixel format.
     * <p><b>The C API {@code void*} buffer parameter has been converted to {@link java.nio.ByteBuffer}; the hidden {@code pixelDataSize} parameter is derived from the buffer's capacity.</b>
     * <p>Definition generated from cef_image_capi.h
     * <pre>int (CEF_CALLBACK* add_bitmap)(struct _cef_image_t* self, float scale_factor, int pixel_width, int pixel_height, cef_color_type_t color_type, cef_alpha_type_t alpha_type, const void* pixel_data, size_t pixel_data_size);</pre>
     *
     * @param pixelData <b>a direct {@link java.nio.ByteBuffer} whose capacity is the buffer size. This buffer is not reference-counted; its lifetime is not predictable beyond the scope of this callback. Storing a reference to it is unsafe unless explicitly permitted by the CEF documentation and may lead to native crashes.</b>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__image_8h.html">cef_image.h:75</a>
     */
    boolean addBitmap(float scaleFactor, int pixelWidth, int pixelHeight, @Nonnull CefColorType colorType, @Nonnull CefAlphaType alphaType, @Nonnull ByteBuffer pixelData);

    /**
     * Add a PNG image representation for {@code scale_factor}. {@code png_data} is the image data of size {@code png_data_size}. Any alpha transparency in the PNG data will be maintained.
     * <p><b>The C API {@code void*} buffer parameter has been converted to {@link java.nio.ByteBuffer}; the hidden {@code pngDataSize} parameter is derived from the buffer's capacity.</b>
     * <p>Definition generated from cef_image_capi.h
     * <pre>int (CEF_CALLBACK* add_png)(struct _cef_image_t* self, float scale_factor, const void* png_data, size_t png_data_size);</pre>
     *
     * @param pngData <b>a direct {@link java.nio.ByteBuffer} whose capacity is the buffer size. This buffer is not reference-counted; its lifetime is not predictable beyond the scope of this callback. Storing a reference to it is unsafe unless explicitly permitted by the CEF documentation and may lead to native crashes.</b>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__image_8h.html">cef_image.h:91</a>
     */
    boolean addPng(float scaleFactor, @Nonnull ByteBuffer pngData);

    /**
     * Create a JPEG image representation for {@code scale_factor}. {@code jpeg_data} is the image data of size {@code jpeg_data_size}. The JPEG format does not support transparency so the alpha byte will be set to 0xFF for all pixels.
     * <p><b>The C API {@code void*} buffer parameter has been converted to {@link java.nio.ByteBuffer}; the hidden {@code jpegDataSize} parameter is derived from the buffer's capacity.</b>
     * <p>Definition generated from cef_image_capi.h
     * <pre>int (CEF_CALLBACK* add_jpeg)(struct _cef_image_t* self, float scale_factor, const void* jpeg_data, size_t jpeg_data_size);</pre>
     *
     * @param jpegData <b>a direct {@link java.nio.ByteBuffer} whose capacity is the buffer size. This buffer is not reference-counted; its lifetime is not predictable beyond the scope of this callback. Storing a reference to it is unsafe unless explicitly permitted by the CEF documentation and may lead to native crashes.</b>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__image_8h.html">cef_image.h:101</a>
     */
    boolean addJpeg(float scaleFactor, @Nonnull ByteBuffer jpegData);

    /**
     * Returns the image width in density independent pixel (DIP) units.
     * <p>Definition generated from cef_image_capi.h
     * <pre>size_t (CEF_CALLBACK* get_width)(struct _cef_image_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__image_8h.html">cef_image.h:111</a>
     */
    long getWidth();

    /**
     * Returns the image height in density independent pixel (DIP) units.
     * <p>Definition generated from cef_image_capi.h
     * <pre>size_t (CEF_CALLBACK* get_height)(struct _cef_image_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__image_8h.html">cef_image.h:117</a>
     */
    long getHeight();

    /**
     * Returns {@code true} if this image contains a representation for {@code scale_factor}.
     * <p>Definition generated from cef_image_capi.h
     * <pre>int (CEF_CALLBACK* has_representation)(struct _cef_image_t* self, float scale_factor);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__image_8h.html">cef_image.h:123</a>
     */
    boolean hasRepresentation(float scaleFactor);

    /**
     * Removes the representation for {@code scale_factor}. Returns {@code true} on success.
     * <p>Definition generated from cef_image_capi.h
     * <pre>int (CEF_CALLBACK* remove_representation)(struct _cef_image_t* self, float scale_factor);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__image_8h.html">cef_image.h:129</a>
     */
    boolean removeRepresentation(float scaleFactor);

    /**
     * Returns information for the representation that most closely matches {@code scale_factor}. {@code actual_scale_factor} is the actual scale factor for the representation. {@code pixel_width} and {@code pixel_height} are the representation size in pixel coordinates. Returns {@code true} on success.
     * <p>Definition generated from cef_image_capi.h
     * <pre>int (CEF_CALLBACK* get_representation_info)(struct _cef_image_t* self, float scale_factor, float* actual_scale_factor, int* pixel_width, int* pixel_height);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__image_8h.html">cef_image.h:135</a>
     */
    boolean getRepresentationInfo(float scaleFactor, float[] actualScaleFactor, int[] pixelWidth, int[] pixelHeight);

    /**
     * Returns the bitmap representation that most closely matches {@code scale_factor}. Only 32-bit RGBA/BGRA formats are supported. {@code color_type} and {@code alpha_type} values specify the desired output pixel format. {@code pixel_width} and {@code pixel_height} are the output representation size in pixel coordinates. Returns a CefBinaryValue containing the pixel data on success or {@code null} on failure.
     * <p>Definition generated from cef_image_capi.h
     * <pre>cef_binary_value_t* (CEF_CALLBACK* get_as_bitmap)(struct _cef_image_t* self, float scale_factor, cef_color_type_t color_type, cef_alpha_type_t alpha_type, int* pixel_width, int* pixel_height);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__image_8h.html">cef_image.h:147</a>
     */
    Optional<CefBinaryValue> getAsBitmap(float scaleFactor, @Nonnull CefColorType colorType, @Nonnull CefAlphaType alphaType, int[] pixelWidth, int[] pixelHeight);

    /**
     * Returns the PNG representation that most closely matches {@code scale_factor}. If {@code with_transparency} is {@code true} any alpha transparency in the image will be represented in the resulting PNG data. {@code pixel_width} and {@code pixel_height} are the output representation size in pixel coordinates. Returns a CefBinaryValue containing the PNG image data on success or {@code null} on failure.
     * <p>Definition generated from cef_image_capi.h
     * <pre>cef_binary_value_t* (CEF_CALLBACK* get_as_png)(struct _cef_image_t* self, float scale_factor, int with_transparency, int* pixel_width, int* pixel_height);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__image_8h.html">cef_image.h:162</a>
     */
    Optional<CefBinaryValue> getAsPng(float scaleFactor, boolean withTransparency, int[] pixelWidth, int[] pixelHeight);

    /**
     * Returns the JPEG representation that most closely matches {@code scale_factor}. {@code quality} determines the compression level with 0 == lowest and 100 == highest. The JPEG format does not support alpha transparency and the alpha channel, if any, will be discarded. {@code pixel_width} and {@code pixel_height} are the output representation size in pixel coordinates. Returns a CefBinaryValue containing the JPEG image data on success or {@code null} on failure.
     * <p>Definition generated from cef_image_capi.h
     * <pre>cef_binary_value_t* (CEF_CALLBACK* get_as_jpeg)(struct _cef_image_t* self, float scale_factor, int quality, int* pixel_width, int* pixel_height);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__image_8h.html">cef_image.h:176</a>
     */
    Optional<CefBinaryValue> getAsJpeg(float scaleFactor, int quality, int[] pixelWidth, int[] pixelHeight);
    /**
     * Create a new CefImage. It will initially be empty. Use the Add*() methods to add representations at different scale factors.
     * <p>Definition generated from cef_image_capi.h
     * <pre>CEF_EXPORT cef_image_t* cef_image_create(void);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__image_8h.html">cef_image.h:55</a>
     */
    static Optional<CefImage> create() {
      return Optional.ofNullable(NativePeer.create0());
  }

    final class NativePeer implements CefImage, AutoCloseable {
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
            if (closed) throw new IllegalStateException("CefImage has been closed");
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
                release0(ptr);
            }
        }

        private static native void release0(long ptr);

        @Override
      public boolean isEmpty() {
          checkNotClosed();
          return isEmpty0(nativePtr);
      }

        @Override
      public boolean isSame(@Nullable CefImage that) {
          checkNotClosed();
            CefLibraryObject.requireOpen(that, "CefImage");
          return isSame0(nativePtr, that);
      }

        @Override
      public boolean addBitmap(float scaleFactor, int pixelWidth, int pixelHeight, @Nonnull CefColorType colorType, @Nonnull CefAlphaType alphaType, @Nonnull ByteBuffer pixelData) {
          checkNotClosed();
          return addBitmap0(nativePtr, scaleFactor, pixelWidth, pixelHeight, colorType, alphaType, pixelData);
      }

        @Override
      public boolean addPng(float scaleFactor, @Nonnull ByteBuffer pngData) {
          checkNotClosed();
          return addPng0(nativePtr, scaleFactor, pngData);
      }

        @Override
      public boolean addJpeg(float scaleFactor, @Nonnull ByteBuffer jpegData) {
          checkNotClosed();
          return addJpeg0(nativePtr, scaleFactor, jpegData);
      }

        @Override
      public long getWidth() {
          checkNotClosed();
          return getWidth0(nativePtr);
      }

        @Override
      public long getHeight() {
          checkNotClosed();
          return getHeight0(nativePtr);
      }

        @Override
      public boolean hasRepresentation(float scaleFactor) {
          checkNotClosed();
          return hasRepresentation0(nativePtr, scaleFactor);
      }

        @Override
      public boolean removeRepresentation(float scaleFactor) {
          checkNotClosed();
          return removeRepresentation0(nativePtr, scaleFactor);
      }

        @Override
      public boolean getRepresentationInfo(float scaleFactor, float[] actualScaleFactor, int[] pixelWidth, int[] pixelHeight) {
          checkNotClosed();
          return getRepresentationInfo0(nativePtr, scaleFactor, actualScaleFactor, pixelWidth, pixelHeight);
      }

        @Override
      public Optional<CefBinaryValue> getAsBitmap(float scaleFactor, @Nonnull CefColorType colorType, @Nonnull CefAlphaType alphaType, int[] pixelWidth, int[] pixelHeight) {
          checkNotClosed();
          return Optional.ofNullable(getAsBitmap0(nativePtr, scaleFactor, colorType, alphaType, pixelWidth, pixelHeight));
      }

        @Override
      public Optional<CefBinaryValue> getAsPng(float scaleFactor, boolean withTransparency, int[] pixelWidth, int[] pixelHeight) {
          checkNotClosed();
          return Optional.ofNullable(getAsPng0(nativePtr, scaleFactor, withTransparency, pixelWidth, pixelHeight));
      }

        @Override
      public Optional<CefBinaryValue> getAsJpeg(float scaleFactor, int quality, int[] pixelWidth, int[] pixelHeight) {
          checkNotClosed();
          return Optional.ofNullable(getAsJpeg0(nativePtr, scaleFactor, quality, pixelWidth, pixelHeight));
      }


        static native boolean isEmpty0(long self);

        static native boolean isSame0(long self, CefImage that);

        static native boolean addBitmap0(long self, float scaleFactor, int pixelWidth, int pixelHeight, CefColorType colorType, CefAlphaType alphaType, ByteBuffer pixelData);

        static native boolean addPng0(long self, float scaleFactor, ByteBuffer pngData);

        static native boolean addJpeg0(long self, float scaleFactor, ByteBuffer jpegData);

        static native long getWidth0(long self);

        static native long getHeight0(long self);

        static native boolean hasRepresentation0(long self, float scaleFactor);

        static native boolean removeRepresentation0(long self, float scaleFactor);

        static native boolean getRepresentationInfo0(long self, float scaleFactor, float[] actualScaleFactor, int[] pixelWidth, int[] pixelHeight);

        static native CefBinaryValue getAsBitmap0(long self, float scaleFactor, CefColorType colorType, CefAlphaType alphaType, int[] pixelWidth, int[] pixelHeight);

        static native CefBinaryValue getAsPng0(long self, float scaleFactor, boolean withTransparency, int[] pixelWidth, int[] pixelHeight);

        static native CefBinaryValue getAsJpeg0(long self, float scaleFactor, int quality, int[] pixelWidth, int[] pixelHeight);

        static native CefImage create0();

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
