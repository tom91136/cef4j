// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;

@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public final class CefAcceleratedPaintNativePixmapPlane {

    public final int stride;
    public final long offset;
    public final long size;
    public final int fd;

    public CefAcceleratedPaintNativePixmapPlane(int stride, long offset, long size, int fd) {
        this.stride = stride;
        this.offset = offset;
        this.size = size;
        this.fd = fd;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefAcceleratedPaintNativePixmapPlane)) return false;
        CefAcceleratedPaintNativePixmapPlane other = (CefAcceleratedPaintNativePixmapPlane) obj;
        return this.stride == other.stride
                    && this.offset == other.offset
                    && this.size == other.size
                    && this.fd == other.fd;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(stride, offset, size, fd);
    }

    @Override
    public String toString() {
        return "CefAcceleratedPaintNativePixmapPlane{" + "stride=" + stride + ", " + "offset=" + offset + ", " + "size=" + size + ", " + "fd=" + fd + "}";
    }
}
