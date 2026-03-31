// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Docking modes supported by CefWindow::AddOverlay. */
public enum CefDockingMode {
    CEF_DOCKING_MODE_TOP_LEFT(0L),
    CEF_DOCKING_MODE_TOP_RIGHT(1L),
    CEF_DOCKING_MODE_BOTTOM_LEFT(2L),
    CEF_DOCKING_MODE_BOTTOM_RIGHT(3L),
    CEF_DOCKING_MODE_CUSTOM(4L),
    CEF_DOCKING_MODE_NUM_VALUES(5L),
    UNKNOWN(-1L);

    public final long value;

    CefDockingMode(long v) {
        this.value = v;
    }

    public static CefDockingMode fromLong(long v) {
        for (CefDockingMode e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
