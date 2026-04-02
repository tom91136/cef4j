package net.kurobako.cef4j.osr.jfx;

import javafx.scene.paint.Color;

/**
 * Extracts scrollbar appearance from the active JavaFX theme and generates equivalent {@code ::-webkit-scrollbar} CSS
 * for use in CEF OSR pages.
 *
 * <p>This is a best-effort approximation - JavaFX themes use layered backgrounds and derived colors that don't map 1:1
 * to WebKit scrollbar pseudo-elements.
 */
final class ScrollbarTheme {

    private ScrollbarTheme() {}

    /**
     * Generate WebKit scrollbar CSS that approximates the current JavaFX theme.
     *
     * <p>Reads the scene's resolved {@code -fx-base} color to derive scrollbar colors. Falls back to Modena defaults if
     * no scene is available.
     */
    static String generateCss(javafx.scene.Scene scene) {
        // Modena defaults
        Color base = Color.web("#ececec");

        // Try to resolve -fx-base from the scene root, which reflects the active theme
        if (scene != null && scene.getRoot() != null) {
            try {
                scene.getRoot().applyCss();
                // -fx-base is exposed as a background fill on the root
                javafx.scene.layout.Background bg = null;
                if (scene.getRoot() instanceof javafx.scene.layout.Region) {
                    bg = ((javafx.scene.layout.Region) scene.getRoot()).getBackground();
                }
                if (bg != null && !bg.getFills().isEmpty()) {
                    javafx.scene.paint.Paint p = bg.getFills().get(0).getFill();
                    if (p instanceof Color) {
                        base = (Color) p;
                    }
                }
            } catch (Exception ignored) {
            }
        }

        Color track = base.deriveColor(0, 1, 0.97, 1); // slightly lighter than base
        Color thumb = base.deriveColor(0, 1, 0.78, 1); // darker for contrast
        Color thumbHover = base.deriveColor(0, 1, 0.68, 1);
        Color thumbActive = base.deriveColor(0, 1, 0.58, 1);

        int width = 14;
        int radius = 7;

        return String.join(
                "\n",
                "::-webkit-scrollbar {",
                "  width: " + width + "px;",
                "  height: " + width + "px;",
                "  background: " + toCssColor(track) + ";",
                "}",
                "::-webkit-scrollbar-track {",
                "  background: " + toCssColor(track) + ";",
                "}",
                "::-webkit-scrollbar-thumb {",
                "  background: " + toCssColor(thumb) + ";",
                "  border-radius: " + radius + "px;",
                "  border: 2px solid " + toCssColor(track) + ";",
                "}",
                "::-webkit-scrollbar-thumb:hover {",
                "  background: " + toCssColor(thumbHover) + ";",
                "}",
                "::-webkit-scrollbar-thumb:active {",
                "  background: " + toCssColor(thumbActive) + ";",
                "}",
                "::-webkit-scrollbar-corner {",
                "  background: " + toCssColor(track) + ";",
                "}");
    }

    /** JavaScript snippet that injects a {@code <style>} element into the page head. */
    static String injectScript(String css) {
        String escaped = css.replace("\\", "\\\\").replace("'", "\\'").replace("\n", "\\n");
        return "(function(){"
                + "if(document.getElementById('_cef4j_scrollbar'))return;"
                + "var s=document.createElement('style');"
                + "s.id='_cef4j_scrollbar';"
                + "s.textContent='" + escaped + "';"
                + "(document.head||document.documentElement).appendChild(s);"
                + "})();";
    }

    private static String toCssColor(Color c) {
        return String.format(
                "rgba(%d,%d,%d,%.2f)",
                Math.round(c.getRed() * 255),
                Math.round(c.getGreen() * 255),
                Math.round(c.getBlue() * 255),
                c.getOpacity());
    }
}
