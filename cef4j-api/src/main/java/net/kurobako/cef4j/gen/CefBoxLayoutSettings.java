// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Settings used when initializing a CefBoxLayout. */
public final class CefBoxLayoutSettings {

    public final long size;
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
            long size,
            int horizontal,
            int insideBorderHorizontalSpacing,
            int insideBorderVerticalSpacing,
            CefInsets insideBorderInsets,
            int betweenChildSpacing,
            CefAxisAlignment mainAxisAlignment,
            CefAxisAlignment crossAxisAlignment,
            int minimumCrossAxisSize,
            int defaultFlex) {
        this.size = size;
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
        return this.size == other.size
                && this.horizontal == other.horizontal
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
                size,
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
        return "CefBoxLayoutSettings{" + "size=" + size + ", " + "horizontal=" + horizontal + ", "
                + "insideBorderHorizontalSpacing=" + insideBorderHorizontalSpacing + ", "
                + "insideBorderVerticalSpacing=" + insideBorderVerticalSpacing + ", " + "insideBorderInsets="
                + insideBorderInsets + ", " + "betweenChildSpacing=" + betweenChildSpacing + ", " + "mainAxisAlignment="
                + mainAxisAlignment + ", " + "crossAxisAlignment=" + crossAxisAlignment + ", " + "minimumCrossAxisSize="
                + minimumCrossAxisSize + ", " + "defaultFlex=" + defaultFlex + "}";
    }
}
