// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import javax.annotation.Nonnull;

/**
 * Implement this interface to handle audio events.
 *
 * <p>Definition generated from cef_audio_handler_capi.h
 *
 * <pre>typedef struct _cef_audio_handler_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_audio_handler_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__audio__handler_8h.html">cef_audio_handler.h:44</a>
 */
public interface CefAudioHandler extends CefClientHandler {

    /**
     * Called on the UI thread to allow configuration of audio stream parameters. Return {@code true} to proceed with
     * audio stream capture, or {@code false} to cancel it. All members of {@code params} can optionally be configured
     * here, but they are also pre-filled with some sensible defaults.
     *
     * <p>Definition generated from cef_audio_handler_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* get_audio_parameters)(struct _cef_audio_handler_t* self, struct _cef_browser_t* browser, cef_audio_parameters_t* params);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__audio__handler_8h.html">cef_audio_handler.h:52</a>
     */
    default boolean getAudioParameters(@Nonnull CefBrowser browser, @Nonnull CefMutableAudioParameters params) {
        return false;
    }

    /**
     * Called on a browser audio capture thread when the browser starts streaming audio. OnAudioStreamStopped will
     * always be called after OnAudioStreamStarted; both methods may be called multiple times for the same browser.
     * {@code params} contains the audio parameters like sample rate and channel layout. {@code channels} is the number
     * of channels.
     *
     * <p>Definition generated from cef_audio_handler_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* on_audio_stream_started)(struct _cef_audio_handler_t* self, struct _cef_browser_t* browser, const cef_audio_parameters_t* params, int channels);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__audio__handler_8h.html">cef_audio_handler.h:64</a>
     */
    default void onAudioStreamStarted(@Nonnull CefBrowser browser, @Nonnull CefAudioParameters params, int channels) {}

    /**
     * Called on the audio stream thread when a PCM packet is received for the stream. {@code data} is an array
     * representing the raw PCM data as a floating point type, i.e. 4-byte value(s). {@code frames} is the number of
     * frames in the PCM packet. {@code pts} is the presentation timestamp (in milliseconds since the Unix Epoch) and
     * represents the time at which the decompressed packet should be presented to the user. Based on {@code frames} and
     * the {@code channel_layout} value passed to OnAudioStreamStarted you can calculate the size of the {@code data}
     * array in bytes.
     *
     * <p>Definition generated from cef_audio_handler_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* on_audio_stream_packet)(struct _cef_audio_handler_t* self, struct _cef_browser_t* browser, const float** data, int frames, int64_t pts);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__audio__handler_8h.html">cef_audio_handler.h:76</a>
     */
    default void onAudioStreamPacket(@Nonnull CefBrowser browser, @Nonnull NativePointer data, int frames, long pts) {}

    /**
     * Called on the UI thread when the stream has stopped. OnAudioSteamStopped will always be called after
     * OnAudioStreamStarted; both methods may be called multiple times for the same stream.
     *
     * <p>Definition generated from cef_audio_handler_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* on_audio_stream_stopped)(struct _cef_audio_handler_t* self, struct _cef_browser_t* browser);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__audio__handler_8h.html">cef_audio_handler.h:92</a>
     */
    default void onAudioStreamStopped(@Nonnull CefBrowser browser) {}

    /**
     * Called on the UI or audio stream thread when an error occurred. During the stream creation phase this callback
     * will be called on the UI thread while in the capturing phase it will be called on the audio stream thread. The
     * stream will be stopped immediately.
     *
     * <p>Definition generated from cef_audio_handler_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* on_audio_stream_error)(struct _cef_audio_handler_t* self, struct _cef_browser_t* browser, const cef_string_t* message);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__audio__handler_8h.html">cef_audio_handler.h:100</a>
     */
    default void onAudioStreamError(@Nonnull CefBrowser browser, @Nonnull String message) {}
}
