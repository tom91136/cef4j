// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Class representing window information. */
public final class CefWindowInfo {

    public final long size;
    public final int windowName;
    public final CefRect bounds;
    public final long parentWindow;
    public final int windowlessRenderingEnabled;
    public final int sharedTextureEnabled;
    public final int externalBeginFrameEnabled;
    public final long window;
    public final CefRuntimeStyle runtimeStyle;

    public CefWindowInfo(
            long size,
            int windowName,
            CefRect bounds,
            long parentWindow,
            int windowlessRenderingEnabled,
            int sharedTextureEnabled,
            int externalBeginFrameEnabled,
            long window,
            CefRuntimeStyle runtimeStyle) {
        this.size = size;
        this.windowName = windowName;
        this.bounds = bounds;
        this.parentWindow = parentWindow;
        this.windowlessRenderingEnabled = windowlessRenderingEnabled;
        this.sharedTextureEnabled = sharedTextureEnabled;
        this.externalBeginFrameEnabled = externalBeginFrameEnabled;
        this.window = window;
        this.runtimeStyle = runtimeStyle;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefWindowInfo)) return false;
        CefWindowInfo other = (CefWindowInfo) obj;
        return this.size == other.size
                && this.windowName == other.windowName
                && java.util.Objects.equals(this.bounds, other.bounds)
                && this.parentWindow == other.parentWindow
                && this.windowlessRenderingEnabled == other.windowlessRenderingEnabled
                && this.sharedTextureEnabled == other.sharedTextureEnabled
                && this.externalBeginFrameEnabled == other.externalBeginFrameEnabled
                && this.window == other.window
                && java.util.Objects.equals(this.runtimeStyle, other.runtimeStyle);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(
                size,
                windowName,
                bounds,
                parentWindow,
                windowlessRenderingEnabled,
                sharedTextureEnabled,
                externalBeginFrameEnabled,
                window,
                runtimeStyle);
    }

    @Override
    public String toString() {
        return "CefWindowInfo{" + "size=" + size + ", " + "windowName=" + windowName + ", " + "bounds=" + bounds + ", "
                + "parentWindow=" + parentWindow + ", " + "windowlessRenderingEnabled=" + windowlessRenderingEnabled
                + ", " + "sharedTextureEnabled=" + sharedTextureEnabled + ", " + "externalBeginFrameEnabled="
                + externalBeginFrameEnabled + ", " + "window=" + window + ", " + "runtimeStyle=" + runtimeStyle + "}";
    }
}
