// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import javax.annotation.Nonnull;

/**
 * Container for a single image represented at different scale factors. All image representations should be the same
 * size in density independent pixel (DIP) units. For example, if the image at scale factor 1.0 is 100x100 pixels then
 * the image at scale factor 2.0 should be 200x200 pixels -- both images will display with a DIP size of 100x100 units.
 * The methods of this class can be called on any browser process thread.
 */
public interface CefImage {

    /** Returns true if this Image is empty. */
    boolean isEmpty();

    /** Returns true if this object is pointing to the same handle as |that| object. */
    boolean isSame(long that);

    /**
     * Add a bitmap image representation for |scale_factor|. Only 32-bit RGBA/BGRA formats are supported. |pixel_width|
     * and |pixel_height| are the bitmap representation size in pixel coordinates. |pixel_data| is the array of pixel
     * data and should be |pixel_width| x |pixel_height| x 4 bytes in size. |color_type| and |alpha_type| values specify
     * the pixel format.
     */
    boolean addBitmap(
            float scaleFactor,
            int pixelWidth,
            int pixelHeight,
            @Nonnull CefColorType colorType,
            @Nonnull CefAlphaType alphaType,
            long pixelData,
            long pixelDataSize);

    int addPng(float scaleFactor, long pngData, long pngDataSize);

    int addJpeg(float scaleFactor, long jpegData, long jpegDataSize);

    /** Returns the image width in density independent pixel (DIP) units. */
    long getWidth();

    /** Returns the image height in density independent pixel (DIP) units. */
    long getHeight();

    /** Returns true if this image contains a representation for |scale_factor|. */
    boolean hasRepresentation(float scaleFactor);

    /** Removes the representation for |scale_factor|. Returns true on success. */
    boolean removeRepresentation(float scaleFactor);

    /**
     * Returns information for the representation that most closely matches |scale_factor|. |actual_scale_factor| is the
     * actual scale factor for the representation. |pixel_width| and |pixel_height| are the representation size in pixel
     * coordinates. Returns true on success.
     */
    boolean getRepresentationInfo(float scaleFactor, long actualScaleFactor, int[] pixelWidth, int[] pixelHeight);

    /**
     * Returns the bitmap representation that most closely matches |scale_factor|. Only 32-bit RGBA/BGRA formats are
     * supported. |color_type| and |alpha_type| values specify the desired output pixel format. |pixel_width| and
     * |pixel_height| are the output representation size in pixel coordinates. Returns a CefBinaryValue containing the
     * pixel data on success or NULL on failure.
     */
    long getAsBitmap(
            float scaleFactor,
            @Nonnull CefColorType colorType,
            @Nonnull CefAlphaType alphaType,
            int[] pixelWidth,
            int[] pixelHeight);

    long getAsPng(float scaleFactor, int withTransparency, int[] pixelWidth, int[] pixelHeight);

    long getAsJpeg(float scaleFactor, int quality, int[] pixelWidth, int[] pixelHeight);

    static class NativePeer implements CefImage {
        private volatile long nativePtr;

        @Override
        public boolean isEmpty() {
            return N_IsEmpty(nativePtr);
        }

        @Override
        public boolean isSame(long that) {
            return N_IsSame(nativePtr, that);
        }

        @Override
        public boolean addBitmap(
                float scaleFactor,
                int pixelWidth,
                int pixelHeight,
                CefColorType colorType,
                CefAlphaType alphaType,
                long pixelData,
                long pixelDataSize) {
            return N_AddBitmap(
                    nativePtr, scaleFactor, pixelWidth, pixelHeight, colorType, alphaType, pixelData, pixelDataSize);
        }

        @Override
        public int addPng(float scaleFactor, long pngData, long pngDataSize) {
            return N_AddPng(nativePtr, scaleFactor, pngData, pngDataSize);
        }

        @Override
        public int addJpeg(float scaleFactor, long jpegData, long jpegDataSize) {
            return N_AddJpeg(nativePtr, scaleFactor, jpegData, jpegDataSize);
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
                float scaleFactor, long actualScaleFactor, int[] pixelWidth, int[] pixelHeight) {
            return N_GetRepresentationInfo(nativePtr, scaleFactor, actualScaleFactor, pixelWidth, pixelHeight);
        }

        @Override
        public long getAsBitmap(
                float scaleFactor,
                CefColorType colorType,
                CefAlphaType alphaType,
                int[] pixelWidth,
                int[] pixelHeight) {
            return N_GetAsBitmap(nativePtr, scaleFactor, colorType, alphaType, pixelWidth, pixelHeight);
        }

        @Override
        public long getAsPng(float scaleFactor, int withTransparency, int[] pixelWidth, int[] pixelHeight) {
            return N_GetAsPng(nativePtr, scaleFactor, withTransparency, pixelWidth, pixelHeight);
        }

        @Override
        public long getAsJpeg(float scaleFactor, int quality, int[] pixelWidth, int[] pixelHeight) {
            return N_GetAsJpeg(nativePtr, scaleFactor, quality, pixelWidth, pixelHeight);
        }

        private native boolean N_IsEmpty(long self);

        private native boolean N_IsSame(long self, long that);

        private native boolean N_AddBitmap(
                long self,
                float scaleFactor,
                int pixelWidth,
                int pixelHeight,
                CefColorType colorType,
                CefAlphaType alphaType,
                long pixelData,
                long pixelDataSize);

        private native int N_AddPng(long self, float scaleFactor, long pngData, long pngDataSize);

        private native int N_AddJpeg(long self, float scaleFactor, long jpegData, long jpegDataSize);

        private native long N_GetWidth(long self);

        private native long N_GetHeight(long self);

        private native boolean N_HasRepresentation(long self, float scaleFactor);

        private native boolean N_RemoveRepresentation(long self, float scaleFactor);

        private native boolean N_GetRepresentationInfo(
                long self, float scaleFactor, long actualScaleFactor, int[] pixelWidth, int[] pixelHeight);

        private native long N_GetAsBitmap(
                long self,
                float scaleFactor,
                CefColorType colorType,
                CefAlphaType alphaType,
                int[] pixelWidth,
                int[] pixelHeight);

        private native long N_GetAsPng(
                long self, float scaleFactor, int withTransparency, int[] pixelWidth, int[] pixelHeight);

        private native long N_GetAsJpeg(long self, float scaleFactor, int quality, int[] pixelWidth, int[] pixelHeight);

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
