// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.nio.ByteBuffer;
import java.util.Optional;
import javax.annotation.Nonnull;

/**
 * Represents the route between a media source and sink. Instances of this object are created via
 * {@link CefMediaRouter#createRoute(CefMediaSource, CefMediaSink, CefMediaRouteCreateCallback)} and retrieved via
 * {@link CefMediaObserver#onRoutes(long, CefMediaRoute[])}. Contains the status and metadata of a routing operation.
 * The methods of this class may be called on any browser process thread unless otherwise indicated.
 *
 * <p>Definition generated from cef_media_router_capi.h
 *
 * <pre>typedef struct _cef_media_route_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_media_route_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__media__router_8h.html">cef_media_router.h:158</a>
 */
public interface CefMediaRoute extends CefLibraryObject {

    /**
     * Returns the unique identifier for this download.
     *
     * <p>Definition generated from cef_media_router_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_id)(struct _cef_media_route_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__download__item_8h.html">cef_download_item.h:137</a>
     */
    Optional<String> getId();

    /**
     * Retrieve this frame's HTML source as a string sent to the specified visitor.
     *
     * <p>Definition generated from cef_media_router_capi.h
     *
     * <pre>cef_media_source_t* (CEF_CALLBACK* get_source)(struct _cef_media_route_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__frame_8h.html">cef_frame.h:124</a>
     */
    Optional<CefMediaSource> getSource();

    /**
     * Returns the sink associated with this route.
     *
     * <p>Definition generated from cef_media_router_capi.h
     *
     * <pre>cef_media_sink_t* (CEF_CALLBACK* get_sink)(struct _cef_media_route_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__media__router_8h.html">cef_media_router.h:180</a>
     */
    Optional<CefMediaSink> getSink();

    /**
     * Send a message over this route. {@code message} will be copied if necessary.
     *
     * <p>Definition generated from cef_media_router_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* send_route_message)(struct _cef_media_route_t* self, const void* message, size_t message_size);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__media__router_8h.html">cef_media_router.h:186</a>
     */
    void sendRouteMessage(@Nonnull ByteBuffer message);

    /**
     * Terminate the unresponsive process.
     *
     * <p>Definition generated from cef_media_router_capi.h
     *
     * <pre>void (CEF_CALLBACK* terminate)(struct _cef_media_route_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__unresponsive__process__callback_8h.html">cef_unresponsive_process_callback.h:55</a>
     */
    void terminate();

    final class NativePeer implements CefMediaRoute, AutoCloseable {
        private final long nativePtr;
        private final java.lang.ref.Cleaner.Cleanable cleanable;

        NativePeer(long ptr) {
            this.nativePtr = ptr;
            this.cleanable = net.kurobako.cef4j.NativeCleaner.INSTANCE.register(this, new Release(ptr));
        }

        @Override
        public void close() {
            cleanable.clean();
        }

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefMediaRoute.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefMediaRoute 0x{}", Long.toHexString(ptr));
                N_Release(ptr);
            }
        }

        private static native void N_Release(long ptr);

        @Override
        public Optional<String> getId() {
            return Optional.ofNullable(N_GetId(nativePtr));
        }

        @Override
        public Optional<CefMediaSource> getSource() {
            return Optional.ofNullable(N_GetSource(nativePtr));
        }

        @Override
        public Optional<CefMediaSink> getSink() {
            return Optional.ofNullable(N_GetSink(nativePtr));
        }

        @Override
        public void sendRouteMessage(@Nonnull ByteBuffer message) {
            N_SendRouteMessage(nativePtr, message);
        }

        @Override
        public void terminate() {
            N_Terminate(nativePtr);
        }

        private static native String N_GetId(long self);

        private static native CefMediaSource N_GetSource(long self);

        private static native CefMediaSink N_GetSink(long self);

        private static native void N_SendRouteMessage(long self, ByteBuffer message);

        private static native void N_Terminate(long self);

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof NativePeer)) return false;
            return this.nativePtr == ((NativePeer) obj).nativePtr;
        }

        @Override
        public int hashCode() {
            return Long.hashCode(nativePtr);
        }

        @Override
        public String toString() {
            return "CefMediaRoute{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
