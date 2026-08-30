// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-api
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.Nullable;
import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * Settings used when initializing a CefBoxLayout.
 *
 * <p>Definition generated from internal/cef_types.h
 *
 * <pre>typedef struct _cef_box_layout_settings_t {
 *   size_t size;
 *   int horizontal;
 *   int inside_border_horizontal_spacing;
 *   int inside_border_vertical_spacing;
 *   cef_insets_t* inside_border_insets;
 *   int between_child_spacing;
 *   cef_axis_alignment_t main_axis_alignment;
 *   cef_axis_alignment_t cross_axis_alignment;
 *   int minimum_cross_axis_size;
 *   int default_flex;
 * } cef_box_layout_settings_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__types_8h.html">internal/cef_types.h:3132</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-api -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
@NullableBoundary("generated external ABI")
public final class CefBoxLayoutSettings {

    // Native struct size, populated by the JNI layer with sizeof(struct) as required by CEF. Not user-modifiable.
    @SuppressWarnings({"FieldMayBeFinal", "CanBeFinal"})
    private volatile long size = -1;

    /** If {@code true} (1) the layout will be horizontal, otherwise the layout will be vertical. */
    public final int horizontal;
    /** Adds additional horizontal space between the child view area and the host view border. */
    public final int insideBorderHorizontalSpacing;
    /** Adds additional vertical space between the child view area and the host view border. */
    public final int insideBorderVerticalSpacing;
    /** Adds additional space around the child view area. */
    public final @Nullable CefInsets insideBorderInsets;
    /** Adds additional space between child views. */
    public final int betweenChildSpacing;
    /** Specifies where along the main axis the child views should be laid out. */
    public final @Nullable CefAxisAlignment mainAxisAlignment;
    /** Specifies where along the cross axis the child views should be laid out. */
    public final @Nullable CefAxisAlignment crossAxisAlignment;
    /** Minimum cross axis size. */
    public final int minimumCrossAxisSize;
    /**
     * Default flex for views when none is specified via CefBoxLayout methods. Using the preferred size as the basis,
     * free space along the main axis is distributed to views in the ratio of their flex weights. Similarly, if the
     * views will overflow the parent, space is subtracted in these ratios. A flex of 0 means this view is not resized.
     * Flex values must not be negative.
     */
    public final int defaultFlex;

    public CefBoxLayoutSettings(
            int horizontal,
            int insideBorderHorizontalSpacing,
            int insideBorderVerticalSpacing,
            @Nullable CefInsets insideBorderInsets,
            int betweenChildSpacing,
            @Nullable CefAxisAlignment mainAxisAlignment,
            @Nullable CefAxisAlignment crossAxisAlignment,
            int minimumCrossAxisSize,
            int defaultFlex) {
        this.horizontal = horizontal;
        this.insideBorderHorizontalSpacing = insideBorderHorizontalSpacing;
        this.insideBorderVerticalSpacing = insideBorderVerticalSpacing;
        this.insideBorderInsets = insideBorderInsets;
        this.betweenChildSpacing = betweenChildSpacing;
        this.mainAxisAlignment = mainAxisAlignment;
        this.crossAxisAlignment = crossAxisAlignment;
        this.minimumCrossAxisSize = minimumCrossAxisSize;
        this.defaultFlex = defaultFlex;
    }

    /** Create a mutable copy of this instance. */
    public Mutable toMutable() {
        return new Mutable(
                this.horizontal,
                this.insideBorderHorizontalSpacing,
                this.insideBorderVerticalSpacing,
                this.insideBorderInsets,
                this.betweenChildSpacing,
                this.mainAxisAlignment,
                this.crossAxisAlignment,
                this.minimumCrossAxisSize,
                this.defaultFlex);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefBoxLayoutSettings)) return false;
        CefBoxLayoutSettings other = (CefBoxLayoutSettings) obj;
        return this.horizontal == other.horizontal
                && this.insideBorderHorizontalSpacing == other.insideBorderHorizontalSpacing
                && this.insideBorderVerticalSpacing == other.insideBorderVerticalSpacing
                && java.util.Objects.equals(this.insideBorderInsets, other.insideBorderInsets)
                && this.betweenChildSpacing == other.betweenChildSpacing
                && java.util.Objects.equals(this.mainAxisAlignment, other.mainAxisAlignment)
                && java.util.Objects.equals(this.crossAxisAlignment, other.crossAxisAlignment)
                && this.minimumCrossAxisSize == other.minimumCrossAxisSize
                && this.defaultFlex == other.defaultFlex;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(
                horizontal,
                insideBorderHorizontalSpacing,
                insideBorderVerticalSpacing,
                insideBorderInsets,
                betweenChildSpacing,
                mainAxisAlignment,
                crossAxisAlignment,
                minimumCrossAxisSize,
                defaultFlex);
    }

    @Override
    public String toString() {
        return "CefBoxLayoutSettings{" + "size=" + (size == -1 ? "pending" : Long.toString(size)) + ", " + "horizontal="
                + horizontal + ", " + "insideBorderHorizontalSpacing=" + insideBorderHorizontalSpacing + ", "
                + "insideBorderVerticalSpacing=" + insideBorderVerticalSpacing + ", " + "insideBorderInsets="
                + insideBorderInsets + ", " + "betweenChildSpacing=" + betweenChildSpacing + ", " + "mainAxisAlignment="
                + mainAxisAlignment + ", " + "crossAxisAlignment=" + crossAxisAlignment + ", " + "minimumCrossAxisSize="
                + minimumCrossAxisSize + ", " + "defaultFlex=" + defaultFlex + "}";
    }

    /**
     * Mutable variant of {@link CefBoxLayoutSettings}. Settings used when initializing a CefBoxLayout.
     *
     * <p>Definition generated from internal/cef_types.h
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__types_8h.html">internal/cef_types.h:3132</a>
     */
    public static final class Mutable {

        // Native struct size, populated by the JNI layer with sizeof(struct) as required by CEF. Not user-modifiable.
        @SuppressWarnings({"FieldMayBeFinal", "CanBeFinal"})
        private volatile long size = -1;

        /** If {@code true} (1) the layout will be horizontal, otherwise the layout will be vertical. */
        public int horizontal;
        /** Adds additional horizontal space between the child view area and the host view border. */
        public int insideBorderHorizontalSpacing;
        /** Adds additional vertical space between the child view area and the host view border. */
        public int insideBorderVerticalSpacing;
        /** Adds additional space around the child view area. */
        public @Nullable CefInsets insideBorderInsets;
        /** Adds additional space between child views. */
        public int betweenChildSpacing;
        /** Specifies where along the main axis the child views should be laid out. */
        public @Nullable CefAxisAlignment mainAxisAlignment;
        /** Specifies where along the cross axis the child views should be laid out. */
        public @Nullable CefAxisAlignment crossAxisAlignment;
        /** Minimum cross axis size. */
        public int minimumCrossAxisSize;
        /**
         * Default flex for views when none is specified via CefBoxLayout methods. Using the preferred size as the
         * basis, free space along the main axis is distributed to views in the ratio of their flex weights. Similarly,
         * if the views will overflow the parent, space is subtracted in these ratios. A flex of 0 means this view is
         * not resized. Flex values must not be negative.
         */
        public int defaultFlex;

        public Mutable() {}

        public Mutable(
                int horizontal,
                int insideBorderHorizontalSpacing,
                int insideBorderVerticalSpacing,
                @Nullable CefInsets insideBorderInsets,
                int betweenChildSpacing,
                @Nullable CefAxisAlignment mainAxisAlignment,
                @Nullable CefAxisAlignment crossAxisAlignment,
                int minimumCrossAxisSize,
                int defaultFlex) {
            this.horizontal = horizontal;
            this.insideBorderHorizontalSpacing = insideBorderHorizontalSpacing;
            this.insideBorderVerticalSpacing = insideBorderVerticalSpacing;
            this.insideBorderInsets = insideBorderInsets;
            this.betweenChildSpacing = betweenChildSpacing;
            this.mainAxisAlignment = mainAxisAlignment;
            this.crossAxisAlignment = crossAxisAlignment;
            this.minimumCrossAxisSize = minimumCrossAxisSize;
            this.defaultFlex = defaultFlex;
        }

        /** Create an immutable snapshot of this instance. */
        public CefBoxLayoutSettings toImmutable() {
            return new CefBoxLayoutSettings(
                    this.horizontal,
                    this.insideBorderHorizontalSpacing,
                    this.insideBorderVerticalSpacing,
                    this.insideBorderInsets,
                    this.betweenChildSpacing,
                    this.mainAxisAlignment,
                    this.crossAxisAlignment,
                    this.minimumCrossAxisSize,
                    this.defaultFlex);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Mutable)) return false;
            Mutable other = (Mutable) obj;
            return this.horizontal == other.horizontal
                    && this.insideBorderHorizontalSpacing == other.insideBorderHorizontalSpacing
                    && this.insideBorderVerticalSpacing == other.insideBorderVerticalSpacing
                    && java.util.Objects.equals(this.insideBorderInsets, other.insideBorderInsets)
                    && this.betweenChildSpacing == other.betweenChildSpacing
                    && java.util.Objects.equals(this.mainAxisAlignment, other.mainAxisAlignment)
                    && java.util.Objects.equals(this.crossAxisAlignment, other.crossAxisAlignment)
                    && this.minimumCrossAxisSize == other.minimumCrossAxisSize
                    && this.defaultFlex == other.defaultFlex;
        }

        @Override
        public int hashCode() {
            return java.util.Objects.hash(
                    horizontal,
                    insideBorderHorizontalSpacing,
                    insideBorderVerticalSpacing,
                    insideBorderInsets,
                    betweenChildSpacing,
                    mainAxisAlignment,
                    crossAxisAlignment,
                    minimumCrossAxisSize,
                    defaultFlex);
        }

        @Override
        public String toString() {
            return "CefBoxLayoutSettings.Mutable{" + "size=" + (size == -1 ? "pending" : Long.toString(size)) + ", "
                    + "horizontal=" + horizontal + ", " + "insideBorderHorizontalSpacing="
                    + insideBorderHorizontalSpacing + ", " + "insideBorderVerticalSpacing="
                    + insideBorderVerticalSpacing + ", " + "insideBorderInsets=" + insideBorderInsets + ", "
                    + "betweenChildSpacing=" + betweenChildSpacing + ", " + "mainAxisAlignment=" + mainAxisAlignment
                    + ", " + "crossAxisAlignment=" + crossAxisAlignment + ", " + "minimumCrossAxisSize="
                    + minimumCrossAxisSize + ", " + "defaultFlex=" + defaultFlex + "}";
        }
    }
}
