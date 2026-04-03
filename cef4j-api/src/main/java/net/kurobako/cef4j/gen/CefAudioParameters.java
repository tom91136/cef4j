// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
// -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;

/**
 * Structure representing the audio parameters for setting up the audio handler.
 *
 * <p>Definition generated from internal/cef_types.h
 *
 * <pre>typedef struct _cef_audio_parameters_t {
 *   size_t size;
 *   cef_channel_layout_t channel_layout;
 *   int sample_rate;
 *   int frames_per_buffer;
 * } cef_audio_parameters_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types_8h.html">internal/cef_types.h:3522</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
public final class CefAudioParameters {

    // Native struct size, populated by the JNI layer with sizeof(struct) as required by CEF. Not user-modifiable.
    @SuppressWarnings("FieldMayBeFinal")
    private volatile long size = -1;

    /** Layout of the audio channels */
    public final CefChannelLayout channelLayout;
    /** Sample rate */
    public final int sampleRate;
    /** Number of frames per buffer */
    public final int framesPerBuffer;

    public CefAudioParameters(CefChannelLayout channelLayout, int sampleRate, int framesPerBuffer) {
        this.channelLayout = channelLayout;
        this.sampleRate = sampleRate;
        this.framesPerBuffer = framesPerBuffer;
    }

    /** Create a mutable copy of this instance. */
    public Mutable toMutable() {
        return new Mutable(this.channelLayout, this.sampleRate, this.framesPerBuffer);
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

    /**
     * Mutable variant of {@link CefAudioParameters}. Structure representing the audio parameters for setting up the
     * audio handler.
     *
     * <p>Definition generated from internal/cef_types.h
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types_8h.html">internal/cef_types.h:3522</a>
     */
    public static final class Mutable {

        // Native struct size, populated by the JNI layer with sizeof(struct) as required by CEF. Not user-modifiable.
        @SuppressWarnings("FieldMayBeFinal")
        private volatile long size = -1;

        /** Layout of the audio channels */
        public CefChannelLayout channelLayout;
        /** Sample rate */
        public int sampleRate;
        /** Number of frames per buffer */
        public int framesPerBuffer;

        public Mutable() {}

        public Mutable(CefChannelLayout channelLayout, int sampleRate, int framesPerBuffer) {
            this.channelLayout = channelLayout;
            this.sampleRate = sampleRate;
            this.framesPerBuffer = framesPerBuffer;
        }

        /** Create an immutable snapshot of this instance. */
        public CefAudioParameters toImmutable() {
            return new CefAudioParameters(this.channelLayout, this.sampleRate, this.framesPerBuffer);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Mutable)) return false;
            Mutable other = (Mutable) obj;
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
            return "CefAudioParameters.Mutable{" + "size=" + (size == -1 ? "pending" : Long.toString(size)) + ", "
                    + "channelLayout=" + channelLayout + ", " + "sampleRate=" + sampleRate + ", " + "framesPerBuffer="
                    + framesPerBuffer + "}";
        }
    }
}
