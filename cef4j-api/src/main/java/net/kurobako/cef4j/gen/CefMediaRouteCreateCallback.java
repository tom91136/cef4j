// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
// -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.processing.Generated;

/**
 * Callback interface for {@link CefMediaRouter#createRoute(CefMediaSource, CefMediaSink, CefMediaRouteCreateCallback)}.
 * The methods of this class will be called on the browser process UI thread.
 *
 * <p>Definition generated from cef_media_router_capi.h
 *
 * <pre>typedef struct _cef_media_route_create_callback_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_media_route_create_callback_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__media__router_8h.html">cef_media_router.h:200</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
public interface CefMediaRouteCreateCallback extends CefClientHandler {

    /**
     * Method that will be executed when the route creation has finished. {@code result} will be
     * {@link CefMediaRouteCreateResult.Kind#OK} if the route creation succeeded. {@code error} will be a description of
     * the error if the route creation failed. {@code route} is the resulting route, or empty if the route creation
     * failed.
     *
     * <p>Definition generated from cef_media_router_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* on_media_route_create_finished)(struct _cef_media_route_create_callback_t* self, cef_media_route_create_result_t result, const cef_string_t* error, struct _cef_media_route_t* route);
     * </pre>
     *
     * @param error may be null
     * @param route may be null
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__media__router_8h.html">cef_media_router.h:209</a>
     */
    default void onMediaRouteCreateFinished(
            @Nonnull CefMediaRouteCreateResult result, @Nullable String error, @Nullable CefMediaRoute route) {}
}
