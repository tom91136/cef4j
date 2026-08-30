// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-api
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.Nullable;
import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;

@Generated("./mvnw generate-sources -pl cef4j-api -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
@NullableBoundary("generated external ABI")
public final class CefTouchHandleState {

    // Native struct size, populated by the JNI layer with sizeof(struct) as required by CEF. Not user-modifiable.
    @SuppressWarnings({"FieldMayBeFinal", "CanBeFinal"})
    private volatile long size = -1;

    /** Touch handle id. Increments for each new touch handle. */
    public final int touchHandleId;
    /** Combination of cef_touch_handle_state_flags_t values indicating what state is set. */
    public final int flags;
    /**
     * Enabled state. Only set if {@code flags} contains
     * {@link net.kurobako.cef4j.gen.CefTouchHandleStateFlags.Kind#ENABLED}.
     */
    public final int enabled;
    /**
     * Orientation state. Only set if {@code flags} contains
     * {@link net.kurobako.cef4j.gen.CefTouchHandleStateFlags.Kind#ORIENTATION}.
     */
    public final @Nullable CefHorizontalAlignment orientation;

    public final int mirrorVertical;
    public final int mirrorHorizontal;
    /**
     * Origin state. Only set if {@code flags} contains
     * {@link net.kurobako.cef4j.gen.CefTouchHandleStateFlags.Kind#ORIGIN}.
     */
    public final @Nullable CefPoint origin;
    /**
     * Alpha state. Only set if {@code flags} contains
     * {@link net.kurobako.cef4j.gen.CefTouchHandleStateFlags.Kind#ALPHA}.
     */
    public final float alpha;

    public CefTouchHandleState(
            int touchHandleId,
            int flags,
            int enabled,
            @Nullable CefHorizontalAlignment orientation,
            int mirrorVertical,
            int mirrorHorizontal,
            @Nullable CefPoint origin,
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

    /** Create a mutable copy of this instance. */
    public Mutable toMutable() {
        return new Mutable(
                this.touchHandleId,
                this.flags,
                this.enabled,
                this.orientation,
                this.mirrorVertical,
                this.mirrorHorizontal,
                this.origin,
                this.alpha);
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

    /** Mutable variant of {@link CefTouchHandleState}. */
    public static final class Mutable {

        // Native struct size, populated by the JNI layer with sizeof(struct) as required by CEF. Not user-modifiable.
        @SuppressWarnings({"FieldMayBeFinal", "CanBeFinal"})
        private volatile long size = -1;

        /** Touch handle id. Increments for each new touch handle. */
        public int touchHandleId;
        /** Combination of cef_touch_handle_state_flags_t values indicating what state is set. */
        public int flags;
        /**
         * Enabled state. Only set if {@code flags} contains
         * {@link net.kurobako.cef4j.gen.CefTouchHandleStateFlags.Kind#ENABLED}.
         */
        public int enabled;
        /**
         * Orientation state. Only set if {@code flags} contains
         * {@link net.kurobako.cef4j.gen.CefTouchHandleStateFlags.Kind#ORIENTATION}.
         */
        public @Nullable CefHorizontalAlignment orientation;

        public int mirrorVertical;
        public int mirrorHorizontal;
        /**
         * Origin state. Only set if {@code flags} contains
         * {@link net.kurobako.cef4j.gen.CefTouchHandleStateFlags.Kind#ORIGIN}.
         */
        public @Nullable CefPoint origin;
        /**
         * Alpha state. Only set if {@code flags} contains
         * {@link net.kurobako.cef4j.gen.CefTouchHandleStateFlags.Kind#ALPHA}.
         */
        public float alpha;

        public Mutable() {}

        public Mutable(
                int touchHandleId,
                int flags,
                int enabled,
                @Nullable CefHorizontalAlignment orientation,
                int mirrorVertical,
                int mirrorHorizontal,
                @Nullable CefPoint origin,
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

        /** Create an immutable snapshot of this instance. */
        public CefTouchHandleState toImmutable() {
            return new CefTouchHandleState(
                    this.touchHandleId,
                    this.flags,
                    this.enabled,
                    this.orientation,
                    this.mirrorVertical,
                    this.mirrorHorizontal,
                    this.origin,
                    this.alpha);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Mutable)) return false;
            Mutable other = (Mutable) obj;
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
            return "CefTouchHandleState.Mutable{" + "size=" + (size == -1 ? "pending" : Long.toString(size)) + ", "
                    + "touchHandleId=" + touchHandleId + ", " + "flags=" + flags + ", " + "enabled=" + enabled + ", "
                    + "orientation=" + orientation + ", " + "mirrorVertical=" + mirrorVertical + ", "
                    + "mirrorHorizontal=" + mirrorHorizontal + ", " + "origin=" + origin + ", " + "alpha=" + alpha
                    + "}";
        }
    }
}
