// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-api
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * Icon types for a MediaSink object. Should be kept in sync with Chromium's media_router::SinkIconType type.
 *
 * <p>Definition generated from cef_types.h
 *
 * <pre>typedef enum {
 *   CEF_MSIT_CAST = 0,
 *   CEF_MSIT_CAST_AUDIO_GROUP = 1,
 *   CEF_MSIT_CAST_AUDIO = 2,
 *   CEF_MSIT_MEETING = 3,
 *   CEF_MSIT_HANGOUT = 4,
 *   ...
 * } cef_media_sink_icon_type_t;</pre>
 *
 * <p>Possible values: {@link Kind#CAST}, {@link Kind#CAST_AUDIO_GROUP}, {@link Kind#CAST_AUDIO}, {@link Kind#MEETING},
 * {@link Kind#HANGOUT}, {@link Kind#EDUCATION}, {@link Kind#WIRED_DISPLAY}, {@link Kind#GENERIC},
 * {@link Kind#NUM_VALUES}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__types_8h.html">cef_types.h</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-api -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
@NullableBoundary("generated external ABI")
public final class CefMediaSinkIconType implements CefEnum<CefMediaSinkIconType> {

    /** Known constants for {@link CefMediaSinkIconType}. */
    public enum Kind {
        CAST(0, "0", "CEF_MSIT_CAST"),
        CAST_AUDIO_GROUP(1, "1", "CEF_MSIT_CAST_AUDIO_GROUP"),
        CAST_AUDIO(2, "2", "CEF_MSIT_CAST_AUDIO"),
        MEETING(3, "3", "CEF_MSIT_MEETING"),
        HANGOUT(4, "4", "CEF_MSIT_HANGOUT"),
        EDUCATION(5, "5", "CEF_MSIT_EDUCATION"),
        WIRED_DISPLAY(6, "6", "CEF_MSIT_WIRED_DISPLAY"),
        GENERIC(7, "7", "CEF_MSIT_GENERIC"),
        NUM_VALUES(8, "8", "CEF_MSIT_NUM_VALUES");

        private static final Kind[] VALUES = Kind.values();

        /** The underlying C enum numeric value. */
        public final long value;

        /** The original C expression/literal (e.g. {@code "1 << 3"}), or the numeric string. */
        public final String expr;

        /** The C constant name (e.g., {@code "cef_media_sink_icon_type_t"}). */
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

    private CefMediaSinkIconType(long value) {
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
     * Returns the {@link Kind} matching this value, or empty for unknown/composite values. Use this for exhaustive
     * switch over known constants.
     */
    public java.util.Optional<Kind> kind() {
        for (Kind k : Kind.VALUES) {
            if (k.value == value) return java.util.Optional.of(k);
        }
        return java.util.Optional.empty();
    }

    /** Returns an instance for the given raw value, use {@link #kind} to resolve to a concrete enum. */
    public static CefMediaSinkIconType of(long v) {
        return new CefMediaSinkIconType(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefMediaSinkIconType of(Kind k) {
        return new CefMediaSinkIconType(k.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefMediaSinkIconType)) return false;
        return this.value == ((CefMediaSinkIconType) obj).value;
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
