// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Structure containing shared texture information for the OnAcceleratedPaint callback. Resources will be released to
 * the underlying pool for reuse when the callback returns from client code.
 */
public final class CefAcceleratedPaintInfo {

    public final long size;
    public final int planeCount;
    public final long modifier;
    public final CefColorType format;
    public final CefAcceleratedPaintInfoCommon extra;

    public CefAcceleratedPaintInfo(
            long size, int planeCount, long modifier, CefColorType format, CefAcceleratedPaintInfoCommon extra) {
        this.size = size;
        this.planeCount = planeCount;
        this.modifier = modifier;
        this.format = format;
        this.extra = extra;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefAcceleratedPaintInfo)) return false;
        CefAcceleratedPaintInfo other = (CefAcceleratedPaintInfo) obj;
        return this.size == other.size
                && this.planeCount == other.planeCount
                && this.modifier == other.modifier
                && java.util.Objects.equals(this.format, other.format)
                && java.util.Objects.equals(this.extra, other.extra);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(size, planeCount, modifier, format, extra);
    }

    @Override
    public String toString() {
        return "CefAcceleratedPaintInfo{" + "size=" + size + ", " + "planeCount=" + planeCount + ", " + "modifier="
                + modifier + ", " + "format=" + format + ", " + "extra=" + extra + "}";
    }
}
