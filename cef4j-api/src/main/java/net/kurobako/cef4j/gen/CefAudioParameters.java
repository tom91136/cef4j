// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Structure representing the audio parameters for setting up the audio handler.
 *
 * <p>Definition generated from cef_types.h
 *
 * <pre>typedef struct _cef_audio_parameters_t {
 *   size_t size;
 *   cef_channel_layout_t channel_layout;
 *   int sample_rate;
 *   int frames_per_buffer;
 * } cef_audio_parameters_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types_8h.html">cef_types.h:3522</a>
 */
public final class CefAudioParameters {

    // Native struct size — set by JNI, not user-modifiable.
    @SuppressWarnings("FieldMayBeFinal")
    private volatile long size = -1;

    public final CefChannelLayout channelLayout;
    public final int sampleRate;
    public final int framesPerBuffer;

    public CefAudioParameters(CefChannelLayout channelLayout, int sampleRate, int framesPerBuffer) {
        this.channelLayout = channelLayout;
        this.sampleRate = sampleRate;
        this.framesPerBuffer = framesPerBuffer;
    }

    /** Create a mutable copy of this instance. */
    public CefMutableAudioParameters toMutable() {
        return new CefMutableAudioParameters(this.channelLayout, this.sampleRate, this.framesPerBuffer);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefAudioParameters)) return false;
        CefAudioParameters other = (CefAudioParameters) obj;
        return java.util.Objects.equals(this.channelLayout, other.channelLayout)
                && this.sampleRate == other.sampleRate
                && this.framesPerBuffer == other.framesPerBuffer;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(channelLayout, sampleRate, framesPerBuffer);
    }

    @Override
    public String toString() {
        return "CefAudioParameters{" + "size=" + (size == -1 ? "pending" : Long.toString(size)) + ", "
                + "channelLayout=" + channelLayout + ", " + "sampleRate=" + sampleRate + ", " + "framesPerBuffer="
                + framesPerBuffer + "}";
    }
}
