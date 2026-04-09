// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import javax.annotation.Nullable;

/**
 * Callback interface for {@link net.kurobako.cef4j.gen.CefMediaSink#getDeviceInfo(CefMediaSinkDeviceInfoCallback)}. The methods of this class will be called on the browser process UI thread.
 * <p>Definition generated from cef_media_router_capi.h
 * <pre>typedef struct _cef_media_sink_device_info_callback_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_media_sink_device_info_callback_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__media__router_8h.html">cef_media_router.h:275</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public interface CefMediaSinkDeviceInfoCallback extends CefClientHandler {

    /**
     * Method that will be executed asyncronously once device information has been retrieved.
     * <p>Definition generated from cef_media_router_capi.h
     * <pre>void (CEF_CALLBACK* on_media_sink_device_info)(struct _cef_media_sink_device_info_callback_t* self, const struct _cef_media_sink_device_info_t* device_info);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__media__router_8h.html">cef_media_router.h:282</a>
     */
    default void onMediaSinkDeviceInfo(@Nullable NativePointer deviceInfo) {
    }
}
