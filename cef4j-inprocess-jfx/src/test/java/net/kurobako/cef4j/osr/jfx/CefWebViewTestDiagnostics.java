package net.kurobako.cef4j.osr.jfx;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/** Failure-only diagnostics for the generated JavaFX/CEF compatibility tests. */
public final class CefWebViewTestDiagnostics {
    private CefWebViewTestDiagnostics() {}

    public static String describe(Object view, double x, double y) {
        if (!(view instanceof CefWebView)) return "stock JavaFX WebView";
        CefWebView cefView = (CefWebView) view;
        ImageView imageView = cefView.getChildrenUnmodifiable().stream()
                .filter(ImageView.class::isInstance)
                .map(ImageView.class::cast)
                .findFirst()
                .orElse(null);
        Image image = imageView == null ? null : imageView.getImage();
        String imageDetails = "none";
        if (image != null && image.getPixelReader() != null) {
            int imageX = Math.min((int) image.getWidth() - 1, Math.max(0, (int)
                    (x * image.getWidth() / Math.max(1, cefView.getWidth()))));
            int imageY = Math.min((int) image.getHeight() - 1, Math.max(0, (int)
                    (y * image.getHeight() / Math.max(1, cefView.getHeight()))));
            imageDetails = (int) image.getWidth() + "x" + (int) image.getHeight() + "@" + imageX + "," + imageY + "=0x"
                    + Integer.toHexString(image.getPixelReader().getArgb(imageX, imageY));
        }
        return "cefFramesPainted=" + cefView.framesPainted.sum() + ", browserPresent=" + (cefView.getBrowser() != null)
                + ", hostPresent=" + (cefView.getBrowserHost() != null) + ", imagePixel=" + imageDetails;
    }
}
