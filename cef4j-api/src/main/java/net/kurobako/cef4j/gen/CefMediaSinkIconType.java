// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Icon types for a MediaSink object. Should be kept in sync with Chromium's media_router::SinkIconType type. */
public enum CefMediaSinkIconType {
    CEF_MSIT_CAST(0L),
    CEF_MSIT_CAST_AUDIO_GROUP(1L),
    CEF_MSIT_CAST_AUDIO(2L),
    CEF_MSIT_MEETING(3L),
    CEF_MSIT_HANGOUT(4L),
    CEF_MSIT_EDUCATION(5L),
    CEF_MSIT_WIRED_DISPLAY(6L),
    CEF_MSIT_GENERIC(7L),
    CEF_MSIT_NUM_VALUES(8L),
    UNKNOWN(-1L);

    public final long value;

    CefMediaSinkIconType(long v) {
        this.value = v;
    }

    public static CefMediaSinkIconType fromLong(long v) {
        for (CefMediaSinkIconType e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
