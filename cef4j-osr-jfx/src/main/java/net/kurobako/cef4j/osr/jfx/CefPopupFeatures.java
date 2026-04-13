package net.kurobako.cef4j.osr.jfx;

/** JavaFX WebView-compatiable popup feature flags without depending on {@code javafx-web}. */
@SuppressWarnings("unused")
public final class CefPopupFeatures {
    private final boolean menu;
    private final boolean status;
    private final boolean toolbar;
    private final boolean resizable;

    public CefPopupFeatures(boolean menu, boolean status, boolean toolbar, boolean resizable) {
        this.menu = menu;
        this.status = status;
        this.toolbar = toolbar;
        this.resizable = resizable;
    }

    public boolean hasMenu() {
        return menu;
    }

    public boolean hasStatus() {
        return status;
    }

    public boolean hasToolbar() {
        return toolbar;
    }

    public boolean isResizable() {
        return resizable;
    }
}
