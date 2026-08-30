// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-api
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen.linux;

import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;

@Generated("./mvnw generate-sources -pl cef4j-api -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
@NullableBoundary("generated external ABI")
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
        return "CefAcceleratedPaintNativePixmapPlane{" + "stride=" + stride + ", " + "offset=" + offset + ", " + "size="
                + size + ", " + "fd=" + fd + "}";
    }
}
