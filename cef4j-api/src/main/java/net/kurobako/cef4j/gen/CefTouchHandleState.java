// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

public final class CefTouchHandleState {

    // Native struct size — set by JNI, not user-modifiable.
    @SuppressWarnings("FieldMayBeFinal")
    private volatile long size = -1;

    public final int touchHandleId;
    public final int flags;
    public final int enabled;
    public final CefHorizontalAlignment orientation;
    public final int mirrorVertical;
    public final int mirrorHorizontal;
    public final CefPoint origin;
    public final float alpha;

    public CefTouchHandleState(
            int touchHandleId,
            int flags,
            int enabled,
            CefHorizontalAlignment orientation,
            int mirrorVertical,
            int mirrorHorizontal,
            CefPoint origin,
            float alpha) {
        this.touchHandleId = touchHandleId;
        this.flags = flags;
        this.enabled = enabled;
        this.orientation = orientation;
        this.mirrorVertical = mirrorVertical;
        this.mirrorHorizontal = mirrorHorizontal;
        this.origin = origin;
        this.alpha = alpha;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefTouchHandleState)) return false;
        CefTouchHandleState other = (CefTouchHandleState) obj;
        return this.touchHandleId == other.touchHandleId
                && this.flags == other.flags
                && this.enabled == other.enabled
                && java.util.Objects.equals(this.orientation, other.orientation)
                && this.mirrorVertical == other.mirrorVertical
                && this.mirrorHorizontal == other.mirrorHorizontal
                && java.util.Objects.equals(this.origin, other.origin)
                && this.alpha == other.alpha;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(
                touchHandleId, flags, enabled, orientation, mirrorVertical, mirrorHorizontal, origin, alpha);
    }

    @Override
    public String toString() {
        return "CefTouchHandleState{" + "size=" + (size == -1 ? "pending" : Long.toString(size)) + ", "
                + "touchHandleId=" + touchHandleId + ", " + "flags=" + flags + ", " + "enabled=" + enabled + ", "
                + "orientation=" + orientation + ", " + "mirrorVertical=" + mirrorVertical + ", " + "mirrorHorizontal="
                + mirrorHorizontal + ", " + "origin=" + origin + ", " + "alpha=" + alpha + "}";
    }
}
