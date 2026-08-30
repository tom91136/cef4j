// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-api
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import java.nio.ByteBuffer;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * Implemented by the client to observe MediaRouter events and registered via
 * {@link net.kurobako.cef4j.gen.CefMediaRouter#addObserver(CefMediaObserver)}. The methods of this class will be called
 * on the browser process UI thread.
 *
 * <p>Definition generated from cef_media_router_capi.h
 *
 * <pre>typedef struct _cef_media_observer_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_media_observer_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__media__router_8h.html">cef_media_router.h:116</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-api -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
@NullableBoundary("generated external ABI")
public interface CefMediaObserver extends CefClientHandler {

    /**
     * The list of available media sinks has changed or
     * {@link net.kurobako.cef4j.gen.CefMediaRouter#notifyCurrentSinks()} was called.
     *
     * <p>Definition generated from cef_media_router_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* on_sinks)(struct _cef_media_observer_t* self, size_t sinksCount, struct _cef_media_sink_t* const* sinks);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__media__router_8h.html">cef_media_router.h:126</a>
     */
    default void onSinks(long sinksCount, @Nullable CefMediaSink[] sinks) {}

    /**
     * The list of available media routes has changed or
     * {@link net.kurobako.cef4j.gen.CefMediaRouter#notifyCurrentRoutes()} was called.
     *
     * <p>Definition generated from cef_media_router_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* on_routes)(struct _cef_media_observer_t* self, size_t routesCount, struct _cef_media_route_t* const* routes);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__media__router_8h.html">cef_media_router.h:133</a>
     */
    default void onRoutes(long routesCount, @Nullable CefMediaRoute[] routes) {}

    /**
     * The connection state of {@code route} has changed.
     *
     * <p>Definition generated from cef_media_router_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* on_route_state_changed)(struct _cef_media_observer_t* self, struct _cef_media_route_t* route, cef_media_route_connection_state_t state);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__media__router_8h.html">cef_media_router.h:141</a>
     */
    default void onRouteStateChanged(@Nullable CefMediaRoute route, @Nonnull CefMediaRouteConnectionState state) {}

    /**
     * A message was received over {@code route}. {@code message} is only valid for the scope of this callback and
     * should be copied if necessary.
     *
     * <p><b>The C API {@code void*} buffer parameter has been converted to {@link java.nio.ByteBuffer}; the hidden
     * {@code message_size} parameter is derived from the buffer's capacity.</b>
     *
     * <p>Definition generated from cef_media_router_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* on_route_message_received)(struct _cef_media_observer_t* self, struct _cef_media_route_t* route, const void* message, size_t message_size);
     * </pre>
     *
     * @param message <b>a direct {@link java.nio.ByteBuffer} whose capacity is the buffer size. This buffer is not
     *     reference-counted; its lifetime is not predictable beyond the scope of this callback. Storing a reference to
     *     it is unsafe unless explicitly permitted by the CEF documentation and may lead to native crashes.</b>
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__media__router_8h.html">cef_media_router.h:148</a>
     */
    default void onRouteMessageReceived(@Nullable CefMediaRoute route, @Nonnull ByteBuffer message) {}
    /**
     * Composite that fans callbacks out to every registered delegate. {@code void} methods invoke all delegates in
     * order; {@code boolean} methods short-circuit on the first {@code true}; handler-returning {@code Optional}s
     * collect every non-empty delegate and wrap them in the handler's own {@code Delegating} wrapper; other
     * {@code Optional}s pick the first non-empty; any other return type yields the first delegate's value.
     */
    class Delegating implements CefMediaObserver {
        private final java.util.List<CefMediaObserver> delegates;

        public Delegating(java.util.List<CefMediaObserver> delegates) {
            this.delegates = java.util.List.copyOf(delegates);
        }

        @Override
        public void onSinks(long sinksCount, @Nullable CefMediaSink[] sinks) {
            for (CefMediaObserver d : delegates) d.onSinks(sinksCount, sinks);
        }

        @Override
        public void onRoutes(long routesCount, @Nullable CefMediaRoute[] routes) {
            for (CefMediaObserver d : delegates) d.onRoutes(routesCount, routes);
        }

        @Override
        public void onRouteStateChanged(@Nullable CefMediaRoute route, @Nonnull CefMediaRouteConnectionState state) {
            for (CefMediaObserver d : delegates) d.onRouteStateChanged(route, state);
        }

        @Override
        public void onRouteMessageReceived(@Nullable CefMediaRoute route, @Nonnull ByteBuffer message) {
            for (CefMediaObserver d : delegates) d.onRouteMessageReceived(route, message);
        }
    }
}
