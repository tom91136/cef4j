// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-types
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * Enumerates the various representations of the ordering of audio channels. Must be kept synchronized with
 * media::ChannelLayout from Chromium. See media\base\channel_layout.h
 *
 * <p>Definition generated from cef_types.h
 *
 * <pre>typedef enum {
 *   CEF_CHANNEL_LAYOUT_NONE = 0,
 *   CEF_CHANNEL_LAYOUT_UNSUPPORTED = 1,
 *   CEF_CHANNEL_LAYOUT_MONO = 2,
 *   CEF_CHANNEL_LAYOUT_STEREO = 3,
 *   CEF_CHANNEL_LAYOUT_2_1 = 4,
 *   ...
 * } cef_channel_layout_t;</pre>
 *
 * <p>Possible values: {@link Kind#LAYOUT_NONE}, {@link Kind#LAYOUT_UNSUPPORTED}, {@link Kind#LAYOUT_MONO},
 * {@link Kind#LAYOUT_STEREO}, {@link Kind#LAYOUT_2_1}, {@link Kind#LAYOUT_SURROUND}, {@link Kind#LAYOUT_4_0},
 * {@link Kind#LAYOUT_2_2}, {@link Kind#LAYOUT_QUAD}, {@link Kind#LAYOUT_5_0}, {@link Kind#LAYOUT_5_1},
 * {@link Kind#LAYOUT_5_0_BACK}, {@link Kind#LAYOUT_5_1_BACK}, {@link Kind#LAYOUT_7_0}, {@link Kind#LAYOUT_7_1},
 * {@link Kind#LAYOUT_7_1_WIDE}, {@link Kind#LAYOUT_STEREO_DOWNMIX}, {@link Kind#LAYOUT_2POINT1},
 * {@link Kind#LAYOUT_3_1}, {@link Kind#LAYOUT_4_1}, {@link Kind#LAYOUT_6_0}, {@link Kind#LAYOUT_6_0_FRONT},
 * {@link Kind#LAYOUT_HEXAGONAL}, {@link Kind#LAYOUT_6_1}, {@link Kind#LAYOUT_6_1_BACK}, {@link Kind#LAYOUT_6_1_FRONT},
 * {@link Kind#LAYOUT_7_0_FRONT}, {@link Kind#LAYOUT_7_1_WIDE_BACK}, {@link Kind#LAYOUT_OCTAGONAL},
 * {@link Kind#LAYOUT_DISCRETE}, {@link Kind#LAYOUT_STEREO_AND_KEYBOARD_MIC}, {@link Kind#LAYOUT_4_1_QUAD_SIDE},
 * {@link Kind#LAYOUT_BITSTREAM}, {@link Kind#LAYOUT_5_1_4_DOWNMIX}, {@link Kind#LAYOUT_1_1},
 * {@link Kind#LAYOUT_3_1_BACK}, {@link Kind#LAYOUT_5_1_4}, {@link Kind#LAYOUT_7_1_4}, {@link Kind#NUM_VALUES}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__types_8h.html">cef_types.h</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-types -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@NullableBoundary("generated external ABI")
public final class CefChannelLayout implements CefEnum<CefChannelLayout> {

    /** Known constants for {@link CefChannelLayout}. */
    public enum Kind {
        LAYOUT_NONE(new EnumConstant(0, "0", "CEF_CHANNEL_LAYOUT_NONE")),
        LAYOUT_UNSUPPORTED(new EnumConstant(1, "1", "CEF_CHANNEL_LAYOUT_UNSUPPORTED")),
        /** Front C */
        LAYOUT_MONO(new EnumConstant(2, "2", "CEF_CHANNEL_LAYOUT_MONO")),
        /** Front L, Front R */
        LAYOUT_STEREO(new EnumConstant(3, "3", "CEF_CHANNEL_LAYOUT_STEREO")),
        /** Front L, Front R, Back C */
        LAYOUT_2_1(new EnumConstant(4, "4", "CEF_CHANNEL_LAYOUT_2_1")),
        /** Front L, Front R, Front C */
        LAYOUT_SURROUND(new EnumConstant(5, "5", "CEF_CHANNEL_LAYOUT_SURROUND")),
        /** Front L, Front R, Front C, Back C */
        LAYOUT_4_0(new EnumConstant(6, "6", "CEF_CHANNEL_LAYOUT_4_0")),
        /** Front L, Front R, Side L, Side R */
        LAYOUT_2_2(new EnumConstant(7, "7", "CEF_CHANNEL_LAYOUT_2_2")),
        /** Front L, Front R, Back L, Back R */
        LAYOUT_QUAD(new EnumConstant(8, "8", "CEF_CHANNEL_LAYOUT_QUAD")),
        /** Front L, Front R, Front C, Side L, Side R */
        LAYOUT_5_0(new EnumConstant(9, "9", "CEF_CHANNEL_LAYOUT_5_0")),
        /** Front L, Front R, Front C, LFE, Side L, Side R */
        LAYOUT_5_1(new EnumConstant(10, "10", "CEF_CHANNEL_LAYOUT_5_1")),
        /** Front L, Front R, Front C, Back L, Back R */
        LAYOUT_5_0_BACK(new EnumConstant(11, "11", "CEF_CHANNEL_LAYOUT_5_0_BACK")),
        /** Front L, Front R, Front C, LFE, Back L, Back R */
        LAYOUT_5_1_BACK(new EnumConstant(12, "12", "CEF_CHANNEL_LAYOUT_5_1_BACK")),
        /** Front L, Front R, Front C, Back L, Back R, Side L, Side R */
        LAYOUT_7_0(new EnumConstant(13, "13", "CEF_CHANNEL_LAYOUT_7_0")),
        /** Front L, Front R, Front C, LFE, Back L, Back R, Side L, Side R */
        LAYOUT_7_1(new EnumConstant(14, "14", "CEF_CHANNEL_LAYOUT_7_1")),
        /** Front L, Front R, Front C, LFE, Front LofC, Front RofC, Side L, Side R */
        LAYOUT_7_1_WIDE(new EnumConstant(15, "15", "CEF_CHANNEL_LAYOUT_7_1_WIDE")),
        /** Front L, Front R */
        LAYOUT_STEREO_DOWNMIX(new EnumConstant(16, "16", "CEF_CHANNEL_LAYOUT_STEREO_DOWNMIX")),
        /** Front L, Front R, LFE */
        LAYOUT_2POINT1(new EnumConstant(17, "17", "CEF_CHANNEL_LAYOUT_2POINT1")),
        /** Front L, Front R, Front C, LFE */
        LAYOUT_3_1(new EnumConstant(18, "18", "CEF_CHANNEL_LAYOUT_3_1")),
        /** Front L, Front R, Front C, LFE, Back C */
        LAYOUT_4_1(new EnumConstant(19, "19", "CEF_CHANNEL_LAYOUT_4_1")),
        /** Front L, Front R, Front C, Back C, Side L, Side R */
        LAYOUT_6_0(new EnumConstant(20, "20", "CEF_CHANNEL_LAYOUT_6_0")),
        /** Front L, Front R, Front LofC, Front RofC, Side L, Side R */
        LAYOUT_6_0_FRONT(new EnumConstant(21, "21", "CEF_CHANNEL_LAYOUT_6_0_FRONT")),
        /** Front L, Front R, Front C, Back L, Back R, Back C */
        LAYOUT_HEXAGONAL(new EnumConstant(22, "22", "CEF_CHANNEL_LAYOUT_HEXAGONAL")),
        /** Front L, Front R, Front C, LFE, Back C, Side L, Side R */
        LAYOUT_6_1(new EnumConstant(23, "23", "CEF_CHANNEL_LAYOUT_6_1")),
        /** Front L, Front R, Front C, LFE, Back L, Back R, Back C */
        LAYOUT_6_1_BACK(new EnumConstant(24, "24", "CEF_CHANNEL_LAYOUT_6_1_BACK")),
        /** Front L, Front R, LFE, Front LofC, Front RofC, Side L, Side R */
        LAYOUT_6_1_FRONT(new EnumConstant(25, "25", "CEF_CHANNEL_LAYOUT_6_1_FRONT")),
        /** Front L, Front R, Front C, Front LofC, Front RofC, Side L, Side R */
        LAYOUT_7_0_FRONT(new EnumConstant(26, "26", "CEF_CHANNEL_LAYOUT_7_0_FRONT")),
        /** Front L, Front R, Front C, LFE, Back L, Back R, Front LofC, Front RofC */
        LAYOUT_7_1_WIDE_BACK(new EnumConstant(27, "27", "CEF_CHANNEL_LAYOUT_7_1_WIDE_BACK")),
        /** Front L, Front R, Front C, Back L, Back R, Back C, Side L, Side R */
        LAYOUT_OCTAGONAL(new EnumConstant(28, "28", "CEF_CHANNEL_LAYOUT_OCTAGONAL")),
        /** Channels are not explicitly mapped to speakers. */
        LAYOUT_DISCRETE(new EnumConstant(29, "29", "CEF_CHANNEL_LAYOUT_DISCRETE")),
        /**
         * Deprecated, but keeping the enum value for UMA consistency. Front L, Front R, Front C. Front C contains the
         * keyboard mic audio. This layout is only intended for input for WebRTC. The Front C channel is stripped away
         * in the WebRTC audio input pipeline and never seen outside of that.
         */
        LAYOUT_STEREO_AND_KEYBOARD_MIC(new EnumConstant(30, "30", "CEF_CHANNEL_LAYOUT_STEREO_AND_KEYBOARD_MIC")),
        /** Front L, Front R, LFE, Side L, Side R */
        LAYOUT_4_1_QUAD_SIDE(new EnumConstant(31, "31", "CEF_CHANNEL_LAYOUT_4_1_QUAD_SIDE")),
        /**
         * Actual channel layout is specified in the bitstream and the actual channel count is unknown at Chromium media
         * pipeline level (useful for audio pass-through mode).
         */
        LAYOUT_BITSTREAM(new EnumConstant(32, "32", "CEF_CHANNEL_LAYOUT_BITSTREAM")),
        /**
         * Front L, Front R, Front C, LFE, Side L, Side R, Front Height L, Front Height R, Rear Height L, Rear Height R
         * Will be represented as six channels (5.1) due to eight channel limit kMaxConcurrentChannels
         */
        LAYOUT_5_1_4_DOWNMIX(new EnumConstant(33, "33", "CEF_CHANNEL_LAYOUT_5_1_4_DOWNMIX")),
        /** Front C, LFE */
        LAYOUT_1_1(new EnumConstant(34, "34", "CEF_CHANNEL_LAYOUT_1_1")),
        /** Front L, Front R, LFE, Back C */
        LAYOUT_3_1_BACK(new EnumConstant(35, "35", "CEF_CHANNEL_LAYOUT_3_1_BACK")),
        /** Front L, Front R, Front C, LFE, Side L, Side R, Top Front L, Top Front R, Top Back L, Top Back R */
        LAYOUT_5_1_4(new EnumConstant(36, "36", "CEF_CHANNEL_LAYOUT_5_1_4")),
        /**
         * Front L, Front R, Front C, LFE, Back L, Back R, Side L, Side R, Top Front L, Top Front R, Top Back L, Top
         * Back R
         */
        LAYOUT_7_1_4(new EnumConstant(37, "37", "CEF_CHANNEL_LAYOUT_7_1_4")),
        NUM_VALUES(new EnumConstant(38, "38", "CEF_CHANNEL_NUM_VALUES"));

        private static final Kind[] VALUES = Kind.values();

        private final EnumConstant constant;

        Kind(EnumConstant constant) {
            this.constant = constant;
        }

        /** The underlying C enum numeric value. */
        public long value() {
            return constant.value();
        }

        /** The original C expression/literal (e.g. {@code "1 << 3"}), or the numeric string. */
        public String expr() {
            return constant.expr();
        }

        /** The C constant name (e.g., {@code "cef_channel_layout_t"}). */
        public String cName() {
            return constant.name();
        }

        @Override
        public String toString() {
            return cName() + "(expr=" + expr() + ", value=" + value() + ")";
        }
    }

    /** The underlying C enum numeric value. This may not correspond to any known {@link Kind}. */
    public final long value;

    private CefChannelLayout(long value) {
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
        return kind().map(Kind::expr).orElse(String.valueOf(value));
    }

    /** {@inheritDoc} */
    @Override
    public String name() {
        return kind().map(Kind::cName).orElse("UNKNOWN(" + value + ")");
    }

    /**
     * Returns the {@link Kind} matching this value, or empty for unknown/composite values. Use this for exhaustive
     * switch over known constants.
     */
    public java.util.Optional<Kind> kind() {
        for (Kind k : Kind.VALUES) {
            if (k.value() == value) return java.util.Optional.of(k);
        }
        return java.util.Optional.empty();
    }

    /** Returns an instance for the given raw value, use {@link #kind} to resolve to a concrete enum. */
    public static CefChannelLayout of(long v) {
        return new CefChannelLayout(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefChannelLayout of(Kind k) {
        return new CefChannelLayout(k.value());
    }

    @Override
    public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefChannelLayout)) return false;
        return this.value == ((CefChannelLayout) obj).value;
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
