// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Result codes for CefMediaRouter::CreateRoute. Should be kept in sync with Chromium's
 * media_router::mojom::RouteRequestResultCode type.
 */
public enum CefMediaRouteCreateResult {
    CEF_MRCR_UNKNOWN_ERROR(0L),
    CEF_MRCR_OK(1L),
    CEF_MRCR_TIMED_OUT(2L),
    CEF_MRCR_ROUTE_NOT_FOUND(3L),
    CEF_MRCR_SINK_NOT_FOUND(4L),
    CEF_MRCR_INVALID_ORIGIN(5L),
    CEF_MRCR_OFF_THE_RECORD_MISMATCH_DEPRECATED(6L),
    CEF_MRCR_NO_SUPPORTED_PROVIDER(7L),
    CEF_MRCR_CANCELLED(8L),
    CEF_MRCR_ROUTE_ALREADY_EXISTS(9L),
    CEF_MRCR_DESKTOP_PICKER_FAILED(10L),
    CEF_MRCR_ROUTE_ALREADY_TERMINATED(11L),
    CEF_MRCR_REDUNDANT_REQUEST(12L),
    CEF_MRCR_USER_NOT_ALLOWED(13L),
    CEF_MRCR_NOTIFICATION_DISABLED(14L),
    CEF_MRCR_NUM_VALUES(15L),
    UNKNOWN(-1L);

    public final long value;

    CefMediaRouteCreateResult(long v) {
        this.value = v;
    }

    public static CefMediaRouteCreateResult fromLong(long v) {
        for (CefMediaRouteCreateResult e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
