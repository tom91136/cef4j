// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/** Class representing print settings. */
public interface CefPrintSettings {

    /** Returns true if this object is valid. Do not call any other methods if this function returns false. */
    boolean isValid();

    /** Returns true if the values of this object are read-only. Some APIs may expose read-only objects. */
    boolean isReadOnly();

    /** Set the page orientation. */
    void setOrientation(boolean landscape);

    /** Returns true if the orientation is landscape. */
    boolean isLandscape();

    /**
     * Set the printer printable area in device units. Some platforms already provide flipped area. Set
     * |landscape_needs_flip| to false on those platforms to avoid double flipping.
     */
    void setPrinterPrintableArea(
            @Nonnull CefSize physicalSizeDeviceUnits,
            @Nonnull CefRect printableAreaDeviceUnits,
            boolean landscapeNeedsFlip);

    /**
     * Set the device name.
     *
     * @param name may be null
     */
    void setDeviceName(@Nullable String name);

    /** Get the device name. */
    Optional<String> getDeviceName();

    void setDpi(int dpi);

    int getDpi();

    /** Set the page ranges. */
    void setPageRanges(long rangesCount, @Nonnull CefRange[] ranges);

    /** Returns the number of page ranges that currently exist. */
    long getPageRangesCount();

    /**
     * Retrieve the page ranges.
     *
     * <p>The size of {@code ranges} is determined by {@code GetPageRangesCount()}.
     */
    void getPageRanges(long rangesCount, @Nonnull CefMutableRange ranges);

    /** Set whether only the selection will be printed. */
    void setSelectionOnly(boolean selectionOnly);

    /** Returns true if only the selection will be printed. */
    boolean isSelectionOnly();

    /** Set whether pages will be collated. */
    void setCollate(boolean collate);

    /** Returns true if pages will be collated. */
    boolean willCollate();

    /** Set the color model. */
    void setColorModel(@Nonnull CefColorModel model);

    /**
     * Get the color model.
     *
     * @return the result, or {@code COLOR_MODEL_UNKNOWN} for default handling
     */
    CefColorModel getColorModel();

    /** Set the number of copies. */
    void setCopies(int copies);

    /** Get the number of copies. */
    int getCopies();

    /** Set the duplex mode. */
    void setDuplexMode(@Nonnull CefDuplexMode mode);

    /**
     * Get the duplex mode.
     *
     * @return the result, or {@code DUPLEX_MODE_UNKNOWN} for default handling
     */
    CefDuplexMode getDuplexMode();

    static class NativePeer implements CefPrintSettings {
        private volatile long nativePtr;

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
                CefSize physicalSizeDeviceUnits, CefRect printableAreaDeviceUnits, boolean landscapeNeedsFlip) {
            N_SetPrinterPrintableArea(nativePtr, physicalSizeDeviceUnits, printableAreaDeviceUnits, landscapeNeedsFlip);
        }

        @Override
        public void setDeviceName(String name) {
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
        public void setPageRanges(long rangesCount, CefRange[] ranges) {
            N_SetPageRanges(nativePtr, rangesCount, ranges);
        }

        @Override
        public long getPageRangesCount() {
            return N_GetPageRangesCount(nativePtr);
        }

        @Override
        public void getPageRanges(long rangesCount, CefMutableRange ranges) {
            N_GetPageRanges(nativePtr, rangesCount, ranges);
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
        public void setColorModel(CefColorModel model) {
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
        public void setDuplexMode(CefDuplexMode mode) {
            N_SetDuplexMode(nativePtr, mode);
        }

        @Override
        public CefDuplexMode getDuplexMode() {
            return N_GetDuplexMode(nativePtr);
        }

        private native boolean N_IsValid(long self);

        private native boolean N_IsReadOnly(long self);

        private native void N_SetOrientation(long self, boolean landscape);

        private native boolean N_IsLandscape(long self);

        private native void N_SetPrinterPrintableArea(
                long self,
                CefSize physicalSizeDeviceUnits,
                CefRect printableAreaDeviceUnits,
                boolean landscapeNeedsFlip);

        private native void N_SetDeviceName(long self, String name);

        private native String N_GetDeviceName(long self);

        private native void N_SetDpi(long self, int dpi);

        private native int N_GetDpi(long self);

        private native void N_SetPageRanges(long self, long rangesCount, CefRange[] ranges);

        private native long N_GetPageRangesCount(long self);

        private native void N_GetPageRanges(long self, long rangesCount, CefMutableRange ranges);

        private native void N_SetSelectionOnly(long self, boolean selectionOnly);

        private native boolean N_IsSelectionOnly(long self);

        private native void N_SetCollate(long self, boolean collate);

        private native boolean N_WillCollate(long self);

        private native void N_SetColorModel(long self, CefColorModel model);

        private native CefColorModel N_GetColorModel(long self);

        private native void N_SetCopies(long self, int copies);

        private native int N_GetCopies(long self);

        private native void N_SetDuplexMode(long self, CefDuplexMode mode);

        private native CefDuplexMode N_GetDuplexMode(long self);

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
