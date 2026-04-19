// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import javax.annotation.Nullable;

/**
 * Structure representing IME composition underline information. This is a thin wrapper around Blink's WebCompositionUnderline class and should be kept in sync with that.
 * <p>Definition generated from internal/cef_types.h
 * <pre>typedef struct _cef_composition_underline_t {
 *   size_t size;
 *   cef_range_t* range;
 *   unsigned int color;
 *   unsigned int background_color;
 *   int thick;
 *   cef_composition_underline_style_t style;
 * } cef_composition_underline_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types_8h.html">internal/cef_types.h:3362</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public final class CefCompositionUnderline {

    // Native struct size, populated by the JNI layer with sizeof(struct) as required by CEF. Not user-modifiable.
    @SuppressWarnings({"FieldMayBeFinal", "CanBeFinal"})
    private volatile long size = -1;

        /**
         * Underline character range. 
         */    public final @Nullable CefRange range;
        /**
         * Text color. 
         */    public final int color;
        /**
         * Background color. 
         */    public final int backgroundColor;
        /**
         * Set to {@code true} (1) for thick underline. 
         */    public final int thick;
        /**
         * Style. 
         */    public final @Nullable CefCompositionUnderlineStyle style;

    public CefCompositionUnderline(@Nullable CefRange range, int color, int backgroundColor, int thick, @Nullable CefCompositionUnderlineStyle style) {
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
        return java.util.Objects.equals(this.range, other.range)
                    && this.color == other.color
                    && this.backgroundColor == other.backgroundColor
                    && this.thick == other.thick
                    && java.util.Objects.equals(this.style, other.style);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(range, color, backgroundColor, thick, style);
    }

    @Override
    public String toString() {
        return "CefCompositionUnderline{" + "size=" + (size == -1 ? "pending" : Long.toString(size)) + ", " + "range=" + range + ", " + "color=" + color + ", " + "backgroundColor=" + backgroundColor + ", " + "thick=" + thick + ", " + "style=" + style + "}";
    }
}
