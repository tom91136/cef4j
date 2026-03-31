// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Structure representing IME composition underline information. This is a thin wrapper around Blink's
 * WebCompositionUnderline class and should be kept in sync with that.
 */
public final class CefCompositionUnderline {

    public final long size;
    public final CefRange range;
    public final int color;
    public final int backgroundColor;
    public final int thick;
    public final CefCompositionUnderlineStyle style;

    public CefCompositionUnderline(
            long size, CefRange range, int color, int backgroundColor, int thick, CefCompositionUnderlineStyle style) {
        this.size = size;
        this.range = range;
        this.color = color;
        this.backgroundColor = backgroundColor;
        this.thick = thick;
        this.style = style;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefCompositionUnderline)) return false;
        CefCompositionUnderline other = (CefCompositionUnderline) obj;
        return this.size == other.size
                && java.util.Objects.equals(this.range, other.range)
                && this.color == other.color
                && this.backgroundColor == other.backgroundColor
                && this.thick == other.thick
                && java.util.Objects.equals(this.style, other.style);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(size, range, color, backgroundColor, thick, style);
    }

    @Override
    public String toString() {
        return "CefCompositionUnderline{" + "size=" + size + ", " + "range=" + range + ", " + "color=" + color + ", "
                + "backgroundColor=" + backgroundColor + ", " + "thick=" + thick + ", " + "style=" + style + "}";
    }
}
