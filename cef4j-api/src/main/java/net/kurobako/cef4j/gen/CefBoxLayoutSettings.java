// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Settings used when initializing a CefBoxLayout.
 *
 * <p>Definition generated from cef_types.h
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
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types_8h.html">cef_types.h:3117</a>
 */
public final class CefBoxLayoutSettings {

    // Native struct size — set by JNI, not user-modifiable.
    @SuppressWarnings("FieldMayBeFinal")
    private volatile long size = -1;

    public final int horizontal;
    public final int insideBorderHorizontalSpacing;
    public final int insideBorderVerticalSpacing;
    public final CefInsets insideBorderInsets;
    public final int betweenChildSpacing;
    public final CefAxisAlignment mainAxisAlignment;
    public final CefAxisAlignment crossAxisAlignment;
    public final int minimumCrossAxisSize;
    public final int defaultFlex;

    public CefBoxLayoutSettings(
            int horizontal,
            int insideBorderHorizontalSpacing,
            int insideBorderVerticalSpacing,
            CefInsets insideBorderInsets,
            int betweenChildSpacing,
            CefAxisAlignment mainAxisAlignment,
            CefAxisAlignment crossAxisAlignment,
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
}
