// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import javax.annotation.Nonnull;

/** Implement this interface to handle audio events. */
public interface CefAudioHandler {

    /**
     * Called on the UI thread to allow configuration of audio stream parameters. Return true to proceed with audio
     * stream capture, or false to cancel it. All members of |params| can optionally be configured here, but they are
     * also pre-filled with some sensible defaults.
     */
    default boolean getAudioParameters(long browser, long params) {
        return false;
    }

    /**
     * Called on a browser audio capture thread when the browser starts streaming audio. OnAudioStreamStopped will
     * always be called after OnAudioStreamStarted; both methods may be called multiple times for the same browser.
     * |params| contains the audio parameters like sample rate and channel layout. |channels| is the number of channels.
     */
    default void onAudioStreamStarted(long browser, long params, int channels) {}

    /**
     * Called on the audio stream thread when a PCM packet is received for the stream. |data| is an array representing
     * the raw PCM data as a floating point type, i.e. 4-byte value(s). |frames| is the number of frames in the PCM
     * packet. |pts| is the presentation timestamp (in milliseconds since the Unix Epoch) and represents the time at
     * which the decompressed packet should be presented to the user. Based on |frames| and the |channel_layout| value
     * passed to OnAudioStreamStarted you can calculate the size of the |data| array in bytes.
     */
    default void onAudioStreamPacket(long browser, long data, int frames, long pts) {}

    /**
     * Called on the UI thread when the stream has stopped. OnAudioSteamStopped will always be called after
     * OnAudioStreamStarted; both methods may be called multiple times for the same stream.
     */
    default void onAudioStreamStopped(long browser) {}

    /**
     * Called on the UI or audio stream thread when an error occurred. During the stream creation phase this callback
     * will be called on the UI thread while in the capturing phase it will be called on the audio stream thread. The
     * stream will be stopped immediately.
     */
    default void onAudioStreamError(long browser, @Nonnull String message) {}
}
