package net.kurobako.cef4j.osr.jfx;

import javafx.scene.paint.Color;

/**
 * Extracts scrollbar appearance from the active JavaFX theme and generates equivalent {@code ::-webkit-scrollbar} CSS
 * for use in CEF OSR pages.
 *
 * <p>This is a best-effort approximation: JavaFX themes use layered backgrounds and derived colours that don't map 1:1
 * to WebKit scrollbar pseudo-elements.
 */
final class ScrollbarTheme {
    private static final String STYLE_ELEMENT_ID = "_cef4j_scrollbar";
    private static final int SCROLLBAR_SIZE = 14;
    private static final int THUMB_RADIUS = 7;
    private static final Color DEFAULT_BASE = Color.web("#ececec");

    private ScrollbarTheme() {}

    /**
     * Generate WebKit scrollbar CSS that approximates the current JavaFX theme.
     *
     * <p>Reads the scene's resolved {@code -fx-base} color to derive scrollbar colours. Falls back to Modena defaults
     * if no scene is available.
     */
    static String generateCss(javafx.scene.Scene scene) {
        ScrollbarPalette palette = ScrollbarPalette.from(resolveBase(scene));

        return String.join(
                "\n",
                ":root {",
                "  color-scheme: " + (palette.dark ? "dark" : "light") + ";",
                "}",
                "::-webkit-scrollbar {",
                "  width: " + SCROLLBAR_SIZE + "px;",
                "  height: " + SCROLLBAR_SIZE + "px;",
                "  background: " + toCssColor(palette.track) + ";",
                "}",
                "::-webkit-scrollbar-track {",
                "  background: " + toCssColor(palette.track) + ";",
                "}",
                "::-webkit-scrollbar-thumb {",
                "  background: " + toCssColor(palette.thumb) + ";",
                "  min-height: 24px;",
                "  border-radius: " + THUMB_RADIUS + "px;",
                "  border: 2px solid " + toCssColor(palette.track) + ";",
                "  background-clip: padding-box;",
                "}",
                "::-webkit-scrollbar-thumb:hover {",
                "  background: " + toCssColor(palette.thumbHover) + ";",
                "}",
                "::-webkit-scrollbar-thumb:active {",
                "  background: " + toCssColor(palette.thumbActive) + ";",
                "}",
                "::-webkit-scrollbar-corner {",
                "  background: " + toCssColor(palette.track) + ";",
                "}");
    }

    /** JavaScript snippet that injects a {@code <style>} element into the page head. */
    static String injectScript(String css) {
        String escaped = css.replace("\\", "\\\\").replace("'", "\\'").replace("\n", "\\n");
        return "(function(){"
                + "function apply(doc){"
                + "if(!doc)return;"
                + "var root=doc.head||doc.documentElement;"
                + "if(!root)return;"
                + "var s=doc.getElementById('" + STYLE_ELEMENT_ID + "');"
                + "if(!s){s=doc.createElement('style');s.id='" + STYLE_ELEMENT_ID + "';root.appendChild(s);}"
                + "s.textContent='" + escaped + "';"
                + "var frames=doc.querySelectorAll('iframe,frame');"
                + "for(var i=0;i<frames.length;i++){"
                + "try{apply(frames[i].contentDocument);}catch(e){}"
                + "}"
                + "}"
                + "apply(document);"
                + "})();";
    }

    private static Color resolveBase(javafx.scene.Scene scene) {
        if (scene != null && scene.getRoot() != null) {
            try {
                scene.getRoot().applyCss();
                javafx.scene.layout.Background bg = null;
                if (scene.getRoot() instanceof javafx.scene.layout.Region) {
                    bg = ((javafx.scene.layout.Region) scene.getRoot()).getBackground();
                }
                if (bg != null && !bg.getFills().isEmpty()) {
                    javafx.scene.paint.Paint fill = bg.getFills().get(0).getFill();
                    if (fill instanceof Color) {
                        return (Color) fill;
                    }
                }
            } catch (Exception ignored) {
                // Scene graph may not be fully initialised; fall back to default.
            }
        }
        return DEFAULT_BASE;
    }

    private static String toCssColor(Color c) {
        return String.format(
                "rgba(%d,%d,%d,%.2f)",
                Math.round(c.getRed() * 255),
                Math.round(c.getGreen() * 255),
                Math.round(c.getBlue() * 255),
                c.getOpacity());
    }

    private static final class ScrollbarPalette {
        private final boolean dark;
        private final Color track;
        private final Color thumb;
        private final Color thumbHover;
        private final Color thumbActive;

        private ScrollbarPalette(boolean dark, Color track, Color thumb, Color thumbHover, Color thumbActive) {
            this.dark = dark;
            this.track = track;
            this.thumb = thumb;
            this.thumbHover = thumbHover;
            this.thumbActive = thumbActive;
        }

        private static ScrollbarPalette from(Color base) {
            boolean dark = luminance(base) < 0.5;
            Color track = mix(base, dark ? Color.WHITE : Color.BLACK, dark ? 0.08 : 0.05);
            Color thumb = mix(base, dark ? Color.WHITE : Color.BLACK, dark ? 0.32 : 0.22);
            Color thumbHover = mix(base, dark ? Color.WHITE : Color.BLACK, dark ? 0.42 : 0.32);
            Color thumbActive = mix(base, dark ? Color.WHITE : Color.BLACK, dark ? 0.52 : 0.42);
            return new ScrollbarPalette(dark, track, thumb, thumbHover, thumbActive);
        }
    }

    // ITU-R BT.709 luma coefficients for sRGB.
    private static double luminance(Color color) {
        return 0.2126 * color.getRed() + 0.7152 * color.getGreen() + 0.0722 * color.getBlue();
    }

    private static Color mix(Color a, Color b, double ratio) {
        double clamped = Math.max(0.0, Math.min(1.0, ratio));
        double inv = 1.0 - clamped;
        return new Color(
                a.getRed() * inv + b.getRed() * clamped,
                a.getGreen() * inv + b.getGreen() * clamped,
                a.getBlue() * inv + b.getBlue() * clamped,
                a.getOpacity() * inv + b.getOpacity() * clamped);
    }
}
