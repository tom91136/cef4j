// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;

/**
 * Result codes for {@link net.kurobako.cef4j.gen.CefMediaRouter#createRoute(CefMediaSource, CefMediaSink, CefMediaRouteCreateCallback)}. Should be kept in sync with Chromium's media_router::mojom::RouteRequestResultCode type.
 * <p>Definition generated from cef_types.h
 * <pre>typedef enum {
 *   CEF_MRCR_UNKNOWN_ERROR = 0,
 *   CEF_MRCR_OK = 1,
 *   CEF_MRCR_TIMED_OUT = 2,
 *   CEF_MRCR_ROUTE_NOT_FOUND = 3,
 *   CEF_MRCR_SINK_NOT_FOUND = 4,
 *   ...
 * } cef_media_route_create_result_t;</pre>
 * <p>Possible values: {@link Kind#UNKNOWN_ERROR}, {@link Kind#OK}, {@link Kind#TIMED_OUT}, {@link Kind#ROUTE_NOT_FOUND}, {@link Kind#SINK_NOT_FOUND}, {@link Kind#INVALID_ORIGIN}, {@link Kind#OFF_THE_RECORD_MISMATCH_DEPRECATED}, {@link Kind#NO_SUPPORTED_PROVIDER}, {@link Kind#CANCELLED}, {@link Kind#ROUTE_ALREADY_EXISTS}, {@link Kind#DESKTOP_PICKER_FAILED}, {@link Kind#ROUTE_ALREADY_TERMINATED}, {@link Kind#REDUNDANT_REQUEST}, {@link Kind#USER_NOT_ALLOWED}, {@link Kind#NOTIFICATION_DISABLED}, {@link Kind#NUM_VALUES}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types_8h.html">cef_types.h</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public final class CefMediaRouteCreateResult implements CefEnum<CefMediaRouteCreateResult> {

    /** Known constants for {@link CefMediaRouteCreateResult}. */
    public enum Kind {
        UNKNOWN_ERROR(0, "0", "CEF_MRCR_UNKNOWN_ERROR"),
        OK(1, "1", "CEF_MRCR_OK"),
        TIMED_OUT(2, "2", "CEF_MRCR_TIMED_OUT"),
        ROUTE_NOT_FOUND(3, "3", "CEF_MRCR_ROUTE_NOT_FOUND"),
        SINK_NOT_FOUND(4, "4", "CEF_MRCR_SINK_NOT_FOUND"),
        INVALID_ORIGIN(5, "5", "CEF_MRCR_INVALID_ORIGIN"),
        OFF_THE_RECORD_MISMATCH_DEPRECATED(6, "6", "CEF_MRCR_OFF_THE_RECORD_MISMATCH_DEPRECATED"),
        NO_SUPPORTED_PROVIDER(7, "7", "CEF_MRCR_NO_SUPPORTED_PROVIDER"),
        CANCELLED(8, "8", "CEF_MRCR_CANCELLED"),
        ROUTE_ALREADY_EXISTS(9, "9", "CEF_MRCR_ROUTE_ALREADY_EXISTS"),
        DESKTOP_PICKER_FAILED(10, "10", "CEF_MRCR_DESKTOP_PICKER_FAILED"),
        ROUTE_ALREADY_TERMINATED(11, "11", "CEF_MRCR_ROUTE_ALREADY_TERMINATED"),
        REDUNDANT_REQUEST(12, "12", "CEF_MRCR_REDUNDANT_REQUEST"),
        USER_NOT_ALLOWED(13, "13", "CEF_MRCR_USER_NOT_ALLOWED"),
        NOTIFICATION_DISABLED(14, "14", "CEF_MRCR_NOTIFICATION_DISABLED"),
        NUM_VALUES(15, "15", "CEF_MRCR_NUM_VALUES");

        private static final Kind[] VALUES = Kind.values();

        /** The underlying C enum numeric value. */
        public final long value;

        /** The original C expression/literal (e.g. {@code "1 << 3"}), or the numeric string. */
        public final String expr;

        /** The C constant name (e.g., {@code "cef_media_route_create_result_t"}). */
        public final String name;

        Kind(long value, String expr, String name) {
            this.value = value;
            this.expr = expr;
            this.name = name;
        }

        @Override
        public String toString() {
            return name + "(expr=" + expr + ", value=" + value + ")";
        }
    }

    /** The underlying C enum numeric value. This may not correspond to any known {@link Kind}. */
    public final long value;

    private CefMediaRouteCreateResult(long value) {
        this.value = value;
    }

    /** {@inheritDoc} */
    @Override
    public long value() {
        return value;
    }

    /** {@inheritDoc} */
    @Override
    public String expr() {
        return kind().map(k -> k.expr).orElse(String.valueOf(value));
    }

    /** {@inheritDoc} */
    @Override
    public String name() {
        return kind().map(k -> k.name).orElse("UNKNOWN(" + value + ")");
    }

    /**
     * Returns the {@link Kind} matching this value, or empty for unknown/composite values.
     * Use this for exhaustive switch over known constants.
     */
    public java.util.Optional<Kind> kind() {
        for (Kind k : Kind.VALUES) {
            if (k.value == value) return java.util.Optional.of(k);
        }
        return java.util.Optional.empty();
    }

    /** Returns an instance for the given raw value, use {@link #kind} to resolve to a concrete enum. */
    public static CefMediaRouteCreateResult of(long v) {
        return new CefMediaRouteCreateResult(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefMediaRouteCreateResult of(Kind k) {
        return new CefMediaRouteCreateResult(k.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefMediaRouteCreateResult)) return false;
        return this.value == ((CefMediaRouteCreateResult) obj).value;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(value);
    }

    @Override
    public String toString() {
        return kind().map(Kind::toString).orElse("UNKNOWN(value=" + value + ")");
    }
}
