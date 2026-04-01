// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.nio.ByteBuffer;
import javax.annotation.Nonnull;

/**
 * Implemented by the client to observe MediaRouter events and registered via
 * {@link CefMediaRouter#addObserver(CefMediaObserver)}. The methods of this class will be called on the browser process
 * UI thread.
 *
 * <p>Definition generated from cef_media_router_capi.h
 *
 * <pre>typedef struct _cef_media_observer_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_media_observer_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__media__router_8h.html">cef_media_router.h:116</a>
 */
public interface CefMediaObserver extends CefClientHandler {

    /**
     * The list of available media sinks has changed or {@link CefMediaRouter#notifyCurrentSinks()} was called.
     *
     * <p>Definition generated from cef_media_router_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* on_sinks)(struct _cef_media_observer_t* self, size_t sinksCount, struct _cef_media_sink_t* const* sinks);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__media__router_8h.html">cef_media_router.h:126</a>
     */
    default void onSinks(long sinkscount, @Nonnull CefMediaSink[] sinks) {}

    /**
     * The list of available media routes has changed or {@link CefMediaRouter#notifyCurrentRoutes()} was called.
     *
     * <p>Definition generated from cef_media_router_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* on_routes)(struct _cef_media_observer_t* self, size_t routesCount, struct _cef_media_route_t* const* routes);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__media__router_8h.html">cef_media_router.h:133</a>
     */
    default void onRoutes(long routescount, @Nonnull CefMediaRoute[] routes) {}

    /**
     * The connection state of {@code route} has changed.
     *
     * <p>Definition generated from cef_media_router_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* on_route_state_changed)(struct _cef_media_observer_t* self, struct _cef_media_route_t* route, cef_media_route_connection_state_t state);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__media__router_8h.html">cef_media_router.h:141</a>
     */
    default void onRouteStateChanged(@Nonnull CefMediaRoute route, @Nonnull CefMediaRouteConnectionState state) {}

    /**
     * A message was received over {@code route}. {@code message} is only valid for the scope of this callback and
     * should be copied if necessary.
     *
     * <p>Definition generated from cef_media_router_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* on_route_message_received)(struct _cef_media_observer_t* self, struct _cef_media_route_t* route, const void* message, size_t message_size);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__media__router_8h.html">cef_media_router.h:148</a>
     */
    default void onRouteMessageReceived(@Nonnull CefMediaRoute route, @Nonnull ByteBuffer message) {}
}
