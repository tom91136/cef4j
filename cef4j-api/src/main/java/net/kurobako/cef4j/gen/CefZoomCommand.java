// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Specifies the zoom commands supported by CefBrowserHost::Zoom. */
public enum CefZoomCommand {
    CEF_ZOOM_COMMAND_OUT(0L),
    CEF_ZOOM_COMMAND_RESET(1L),
    CEF_ZOOM_COMMAND_IN(2L),
    UNKNOWN(-1L);

    public final long value;

    CefZoomCommand(long v) {
        this.value = v;
    }

    public static CefZoomCommand fromLong(long v) {
        for (CefZoomCommand e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
