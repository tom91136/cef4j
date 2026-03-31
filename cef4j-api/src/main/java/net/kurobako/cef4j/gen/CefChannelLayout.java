// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Enumerates the various representations of the ordering of audio channels. Must be kept synchronized with
 * media::ChannelLayout from Chromium. See media\base\channel_layout.h
 */
public enum CefChannelLayout {
    CEF_CHANNEL_LAYOUT_NONE(0L),
    CEF_CHANNEL_LAYOUT_UNSUPPORTED(1L),
    /** Front C */
    CEF_CHANNEL_LAYOUT_MONO(2L),
    /** Front L, Front R */
    CEF_CHANNEL_LAYOUT_STEREO(3L),
    /** Front L, Front R, Back C */
    CEF_CHANNEL_LAYOUT_2_1(4L),
    /** Front L, Front R, Front C */
    CEF_CHANNEL_LAYOUT_SURROUND(5L),
    /** Front L, Front R, Front C, Back C */
    CEF_CHANNEL_LAYOUT_4_0(6L),
    /** Front L, Front R, Side L, Side R */
    CEF_CHANNEL_LAYOUT_2_2(7L),
    /** Front L, Front R, Back L, Back R */
    CEF_CHANNEL_LAYOUT_QUAD(8L),
    /** Front L, Front R, Front C, Side L, Side R */
    CEF_CHANNEL_LAYOUT_5_0(9L),
    /** Front L, Front R, Front C, LFE, Side L, Side R */
    CEF_CHANNEL_LAYOUT_5_1(10L),
    /** Front L, Front R, Front C, Back L, Back R */
    CEF_CHANNEL_LAYOUT_5_0_BACK(11L),
    /** Front L, Front R, Front C, LFE, Back L, Back R */
    CEF_CHANNEL_LAYOUT_5_1_BACK(12L),
    /** Front L, Front R, Front C, Back L, Back R, Side L, Side R */
    CEF_CHANNEL_LAYOUT_7_0(13L),
    /** Front L, Front R, Front C, LFE, Back L, Back R, Side L, Side R */
    CEF_CHANNEL_LAYOUT_7_1(14L),
    /** Front L, Front R, Front C, LFE, Front LofC, Front RofC, Side L, Side R */
    CEF_CHANNEL_LAYOUT_7_1_WIDE(15L),
    /** Front L, Front R */
    CEF_CHANNEL_LAYOUT_STEREO_DOWNMIX(16L),
    /** Front L, Front R, LFE */
    CEF_CHANNEL_LAYOUT_2POINT1(17L),
    /** Front L, Front R, Front C, LFE */
    CEF_CHANNEL_LAYOUT_3_1(18L),
    /** Front L, Front R, Front C, LFE, Back C */
    CEF_CHANNEL_LAYOUT_4_1(19L),
    /** Front L, Front R, Front C, Back C, Side L, Side R */
    CEF_CHANNEL_LAYOUT_6_0(20L),
    /** Front L, Front R, Front LofC, Front RofC, Side L, Side R */
    CEF_CHANNEL_LAYOUT_6_0_FRONT(21L),
    /** Front L, Front R, Front C, Back L, Back R, Back C */
    CEF_CHANNEL_LAYOUT_HEXAGONAL(22L),
    /** Front L, Front R, Front C, LFE, Back C, Side L, Side R */
    CEF_CHANNEL_LAYOUT_6_1(23L),
    /** Front L, Front R, Front C, LFE, Back L, Back R, Back C */
    CEF_CHANNEL_LAYOUT_6_1_BACK(24L),
    /** Front L, Front R, LFE, Front LofC, Front RofC, Side L, Side R */
    CEF_CHANNEL_LAYOUT_6_1_FRONT(25L),
    /** Front L, Front R, Front C, Front LofC, Front RofC, Side L, Side R */
    CEF_CHANNEL_LAYOUT_7_0_FRONT(26L),
    /** Front L, Front R, Front C, LFE, Back L, Back R, Front LofC, Front RofC */
    CEF_CHANNEL_LAYOUT_7_1_WIDE_BACK(27L),
    /** Front L, Front R, Front C, Back L, Back R, Back C, Side L, Side R */
    CEF_CHANNEL_LAYOUT_OCTAGONAL(28L),
    /** Channels are not explicitly mapped to speakers. */
    CEF_CHANNEL_LAYOUT_DISCRETE(29L),
    /**
     * Deprecated, but keeping the enum value for UMA consistency. Front L, Front R, Front C. Front C contains the
     * keyboard mic audio. This layout is only intended for input for WebRTC. The Front C channel is stripped away in
     * the WebRTC audio input pipeline and never seen outside of that.
     */
    CEF_CHANNEL_LAYOUT_STEREO_AND_KEYBOARD_MIC(30L),
    /** Front L, Front R, LFE, Side L, Side R */
    CEF_CHANNEL_LAYOUT_4_1_QUAD_SIDE(31L),
    /**
     * Actual channel layout is specified in the bitstream and the actual channel count is unknown at Chromium media
     * pipeline level (useful for audio pass-through mode).
     */
    CEF_CHANNEL_LAYOUT_BITSTREAM(32L),
    /**
     * Front L, Front R, Front C, LFE, Side L, Side R, Front Height L, Front Height R, Rear Height L, Rear Height R Will
     * be represented as six channels (5.1) due to eight channel limit kMaxConcurrentChannels
     */
    CEF_CHANNEL_LAYOUT_5_1_4_DOWNMIX(33L),
    /** Front C, LFE */
    CEF_CHANNEL_LAYOUT_1_1(34L),
    /** Front L, Front R, LFE, Back C */
    CEF_CHANNEL_LAYOUT_3_1_BACK(35L),
    CEF_CHANNEL_NUM_VALUES(36L),
    UNKNOWN(-1L);

    public final long value;

    CefChannelLayout(long v) {
        this.value = v;
    }

    public static CefChannelLayout fromLong(long v) {
        for (CefChannelLayout e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
