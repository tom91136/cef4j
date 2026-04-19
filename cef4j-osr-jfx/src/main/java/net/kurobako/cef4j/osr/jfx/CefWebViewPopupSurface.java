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
import javax.annotation.Nullable;
import net.kurobako.cef4j.gen.CefMouseButtonType;
import net.kurobako.cef4j.gen.CefMouseEvent;

final class CefWebViewPopupSurface {
    private final CefWebView view;

    @Nullable
    private javafx.stage.Popup popup;

    @Nullable
    private ImageView imageView;

    @Nullable
    private IntBuffer pixelBuf;

    @Nullable
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
        javafx.stage.Popup currentPopup = popup;
        ImageView currentImageView = imageView;
        if (currentPopup == null || currentImageView == null) return;
        var rect = view.popupRect;
        if (rect == null) return;
        IntBuffer buf = pixelBuf;
        PixelBuffer<IntBuffer> pb = pixelBuffer;
        if (pb == null || buf == null || this.width != width || this.height != height) {
            this.width = width;
            this.height = height;
            buf = IntBuffer.allocate(width * height);
            pb = new PixelBuffer<>(width, height, buf, PixelFormat.getIntArgbPreInstance());
            pixelBuf = buf;
            pixelBuffer = pb;
            currentImageView.setImage(new WritableImage(pb));
        }
        System.arraycopy(pixels, 0, buf.array(), 0, width * height);
        pb.updateBuffer(ignored -> null);
        double scale = view.currentScaleFactor(view.currentScreen());
        currentImageView.setFitWidth(width / scale);
        currentImageView.setFitHeight(height / scale);
        Point2D screen = view.localToScreen(rect.x, rect.y);
        Window window = view.getScene() != null ? view.getScene().getWindow() : null;
        if (screen != null && window != null) {
            if (!currentPopup.isShowing()) {
                currentPopup.show(window, screen.getX(), screen.getY());
            } else {
                currentPopup.setAnchorX(screen.getX());
                currentPopup.setAnchorY(screen.getY());
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
