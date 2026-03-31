// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Connection state for a MediaRoute object. Should be kept in sync with Chromium's
 * blink::mojom::PresentationConnectionState type.
 */
public enum CefMediaRouteConnectionState {
    CEF_MRCS_UNKNOWN(-1L),
    CEF_MRCS_CONNECTING(0L),
    CEF_MRCS_CONNECTED(1L),
    CEF_MRCS_CLOSED(2L),
    CEF_MRCS_TERMINATED(3L),
    CEF_MRCS_NUM_VALUES(4L),
    UNKNOWN(-1L);

    public final long value;

    CefMediaRouteConnectionState(long v) {
        this.value = v;
    }

    public static CefMediaRouteConnectionState fromLong(long v) {
        for (CefMediaRouteConnectionState e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
