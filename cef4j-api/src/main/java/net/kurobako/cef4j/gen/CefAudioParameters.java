// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Structure representing the audio parameters for setting up the audio handler. */
public final class CefAudioParameters {

    public final long size;
    public final CefChannelLayout channelLayout;
    public final int sampleRate;
    public final int framesPerBuffer;

    public CefAudioParameters(long size, CefChannelLayout channelLayout, int sampleRate, int framesPerBuffer) {
        this.size = size;
        this.channelLayout = channelLayout;
        this.sampleRate = sampleRate;
        this.framesPerBuffer = framesPerBuffer;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefAudioParameters)) return false;
        CefAudioParameters other = (CefAudioParameters) obj;
        return this.size == other.size
                && java.util.Objects.equals(this.channelLayout, other.channelLayout)
                && this.sampleRate == other.sampleRate
                && this.framesPerBuffer == other.framesPerBuffer;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(size, channelLayout, sampleRate, framesPerBuffer);
    }

    @Override
    public String toString() {
        return "CefAudioParameters{" + "size=" + size + ", " + "channelLayout=" + channelLayout + ", " + "sampleRate="
                + sampleRate + ", " + "framesPerBuffer=" + framesPerBuffer + "}";
    }
}
