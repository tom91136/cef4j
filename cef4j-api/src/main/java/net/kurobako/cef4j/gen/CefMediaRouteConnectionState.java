// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;

/**
 * Connection state for a MediaRoute object. Should be kept in sync with Chromium's blink::mojom::PresentationConnectionState type.
 * <p>Definition generated from cef_types.h
 * <pre>typedef enum {
 *   CEF_MRCS_UNKNOWN = -1,
 *   CEF_MRCS_CONNECTING = 0,
 *   CEF_MRCS_CONNECTED = 1,
 *   CEF_MRCS_CLOSED = 2,
 *   CEF_MRCS_TERMINATED = 3,
 *   ...
 * } cef_media_route_connection_state_t;</pre>
 * <p>Possible values: {@link Kind#UNKNOWN}, {@link Kind#CONNECTING}, {@link Kind#CONNECTED}, {@link Kind#CLOSED}, {@link Kind#TERMINATED}, {@link Kind#NUM_VALUES}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__types_8h.html">cef_types.h</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public final class CefMediaRouteConnectionState implements CefEnum<CefMediaRouteConnectionState> {

    /** Known constants for {@link CefMediaRouteConnectionState}. */
    public enum Kind {
        UNKNOWN(-1, "- 1", "CEF_MRCS_UNKNOWN"),
        CONNECTING(0, "0", "CEF_MRCS_CONNECTING"),
        CONNECTED(1, "1", "CEF_MRCS_CONNECTED"),
        CLOSED(2, "2", "CEF_MRCS_CLOSED"),
        TERMINATED(3, "3", "CEF_MRCS_TERMINATED"),
        NUM_VALUES(4, "4", "CEF_MRCS_NUM_VALUES");

        private static final Kind[] VALUES = Kind.values();

        /** The underlying C enum numeric value. */
        public final long value;

        /** The original C expression/literal (e.g. {@code "1 << 3"}), or the numeric string. */
        public final String expr;

        /** The C constant name (e.g., {@code "cef_media_route_connection_state_t"}). */
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

    private CefMediaRouteConnectionState(long value) {
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
    public static CefMediaRouteConnectionState of(long v) {
        return new CefMediaRouteConnectionState(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefMediaRouteConnectionState of(Kind k) {
        return new CefMediaRouteConnectionState(k.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefMediaRouteConnectionState)) return false;
        return this.value == ((CefMediaRouteConnectionState) obj).value;
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
