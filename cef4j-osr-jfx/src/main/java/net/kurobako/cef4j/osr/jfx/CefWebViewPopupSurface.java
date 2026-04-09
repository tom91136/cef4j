package net.kurobako.cef4j.osr.jfx;

import java.nio.IntBuffer;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelBuffer;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.stage.Window;
import net.kurobako.cef4j.gen.CefMouseButtonType;
import net.kurobako.cef4j.gen.CefMouseEvent;

final class CefWebViewPopupSurface {
    private final CefWebView view;
    private javafx.stage.Popup popup;
    private ImageView imageView;
    private IntBuffer pixelBuf;
    private PixelBuffer<IntBuffer> pixelBuffer;
    private int width;
    private int height;

    CefWebViewPopupSurface(CefWebView view) {
        this.view = view;
    }

    void show() {
        hide();
        imageView = new ImageView();
        imageView.setPreserveRatio(false);
        imageView.setSmooth(false);
        imageView.setOnMousePressed(e -> forwardMouseClick(e, false));
        imageView.setOnMouseReleased(e -> forwardMouseClick(e, true));
        imageView.setOnMouseMoved(this::forwardMouseMove);
        imageView.setOnMouseDragged(this::forwardMouseMove);
        imageView.setOnScroll(this::forwardScroll);
        popup = new javafx.stage.Popup();
        popup.getContent().add(imageView);
        popup.setAutoFix(false);
        popup.setAutoHide(false);
    }

    void hide() {
        if (popup != null) {
            popup.hide();
            popup = null;
        }
        imageView = null;
        pixelBuf = null;
        pixelBuffer = null;
        width = 0;
        height = 0;
    }

    void blit(int[] pixels, int width, int height) {
        if (popup == null || imageView == null) return;
        var rect = view.popupRect;
        if (rect == null) return;
        if (pixelBuffer == null || this.width != width || this.height != height) {
            this.width = width;
            this.height = height;
            pixelBuf = IntBuffer.allocate(width * height);
            pixelBuffer = new PixelBuffer<>(width, height, pixelBuf, PixelFormat.getIntArgbPreInstance());
            imageView.setImage(new WritableImage(pixelBuffer));
        }
        System.arraycopy(pixels, 0, pixelBuf.array(), 0, width * height);
        pixelBuffer.updateBuffer(pb -> null);
        double scale = view.currentScaleFactor(view.currentScreen());
        imageView.setFitWidth(width / scale);
        imageView.setFitHeight(height / scale);
        Point2D screen = view.localToScreen(rect.x, rect.y);
        Window window = view.getScene() != null ? view.getScene().getWindow() : null;
        if (screen != null && window != null) {
            if (!popup.isShowing()) {
                popup.show(window, screen.getX(), screen.getY());
            } else {
                popup.setAnchorX(screen.getX());
                popup.setAnchorY(screen.getY());
            }
        }
    }

    boolean containsScreenPoint(double screenX, double screenY) {
        if (popup == null || !popup.isShowing() || imageView == null) return false;
        var bounds = imageView.localToScreen(imageView.getBoundsInLocal());
        return bounds != null && bounds.contains(screenX, screenY);
    }

    private void forwardMouseClick(MouseEvent e, boolean mouseUp) {
        var rect = view.popupRect;
        if (rect == null) return;
        int viewX = (int) e.getX() + rect.x;
        int viewY = (int) e.getY() + rect.y;
        view.runWithBrowserHost(
                false,
                host -> host.sendMouseClickEvent(
                        new CefMouseEvent(viewX, viewY, CefWebView.mouseModifiers(e)),
                        CefMouseButtonType.of(CefWebView.cefButton(e)),
                        mouseUp,
                        e.getClickCount()));
    }

    private void forwardMouseMove(MouseEvent e) {
        var rect = view.popupRect;
        if (rect == null) return;
        int viewX = (int) e.getX() + rect.x;
        int viewY = (int) e.getY() + rect.y;
        view.runWithBrowserHost(
                false,
                host -> host.sendMouseMoveEvent(new CefMouseEvent(viewX, viewY, CefWebView.mouseModifiers(e)), false));
    }

    private void forwardScroll(ScrollEvent e) {
        var rect = view.popupRect;
        if (rect == null) return;
        view.runWithBrowserHost(
                false,
                host -> host.sendMouseWheelEvent(
                        new CefMouseEvent(
                                (int) e.getX() + rect.x,
                                (int) e.getY() + rect.y,
                                CefWebView.baseModifiers(
                                        e.isShiftDown(), e.isControlDown(), e.isAltDown(), e.isMetaDown())),
                        (int) e.getDeltaX(),
                        (int) e.getDeltaY()));
    }
}
