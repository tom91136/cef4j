// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import java.nio.ByteBuffer;
import java.util.Optional;
import javax.annotation.Nonnull;

/**
 * Represents the route between a media source and sink. Instances of this object are created via {@link net.kurobako.cef4j.gen.CefMediaRouter#createRoute(CefMediaSource, CefMediaSink, CefMediaRouteCreateCallback)} and retrieved via {@link net.kurobako.cef4j.gen.CefMediaObserver#onRoutes(long, CefMediaRoute[])}. Contains the status and metadata of a routing operation. The methods of this class may be called on any browser process thread unless otherwise indicated.
 * <p>Definition generated from cef_media_router_capi.h
 * <pre>typedef struct _cef_media_route_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_media_route_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__media__router_8h.html">cef_media_router.h:158</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public interface CefMediaRoute extends CefLibraryObject {

    /**
     * Returns the ID for this route.
     * <p>Definition generated from cef_media_router_capi.h
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_id)(struct _cef_media_route_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__media__router_8h.html">cef_media_router.h:168</a>
     */
    Optional<String> getId();

    /**
     * Returns the source associated with this route.
     * <p>Definition generated from cef_media_router_capi.h
     * <pre>cef_media_source_t* (CEF_CALLBACK* get_source)(struct _cef_media_route_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__media__router_8h.html">cef_media_router.h:174</a>
     */
    Optional<CefMediaSource> getSource();

    /**
     * Returns the sink associated with this route.
     * <p>Definition generated from cef_media_router_capi.h
     * <pre>cef_media_sink_t* (CEF_CALLBACK* get_sink)(struct _cef_media_route_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__media__router_8h.html">cef_media_router.h:180</a>
     */
    Optional<CefMediaSink> getSink();

    /**
     * Send a message over this route. {@code message} will be copied if necessary.
     * <p><b>The C API {@code void*} buffer parameter has been converted to {@link java.nio.ByteBuffer}; the hidden {@code message_size} parameter is derived from the buffer's capacity.</b>
     * <p>Definition generated from cef_media_router_capi.h
     * <pre>void (CEF_CALLBACK* send_route_message)(struct _cef_media_route_t* self, const void* message, size_t message_size);</pre>
     *
     * @param message <b>a direct {@link java.nio.ByteBuffer} whose capacity is the buffer size. This buffer is not reference-counted; its lifetime is not predictable beyond the scope of this callback. Storing a reference to it is unsafe unless explicitly permitted by the CEF documentation and may lead to native crashes.</b>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__media__router_8h.html">cef_media_router.h:186</a>
     */
    void sendRouteMessage(@Nonnull ByteBuffer message);

    /**
     * Terminate this route. Will result in an asynchronous call to {@link net.kurobako.cef4j.gen.CefMediaObserver#onRoutes(long, CefMediaRoute[])} on all registered observers.
     * <p>Definition generated from cef_media_router_capi.h
     * <pre>void (CEF_CALLBACK* terminate)(struct _cef_media_route_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__media__router_8h.html">cef_media_router.h:192</a>
     */
    void terminate();
    final class NativePeer implements CefMediaRoute, AutoCloseable {
        private final long nativePtr;
        private final java.lang.ref.Cleaner.Cleanable cleanable;
        private volatile boolean closed;

        NativePeer(long ptr) {
            this.nativePtr = ptr;
            this.cleanable = net.kurobako.cef4j.NativeCleaner.INSTANCE.register(this, new Release(ptr));
        }

        @Override
        public void peerClose() {
            closed = true;
            cleanable.clean();
        }

        @Override
        public boolean peerIsClosed() {
            return closed;
        }

        private void checkNotClosed() {
            if (closed) throw new IllegalStateException("CefMediaRoute has been closed");
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
                release0(ptr);
            }
        }

        private static native void release0(long ptr);

        @Override
      public Optional<String> getId() {
          checkNotClosed();
          return Optional.ofNullable(getId0(nativePtr));
      }

        @Override
      public Optional<CefMediaSource> getSource() {
          checkNotClosed();
          return Optional.ofNullable(getSource0(nativePtr));
      }

        @Override
      public Optional<CefMediaSink> getSink() {
          checkNotClosed();
          return Optional.ofNullable(getSink0(nativePtr));
      }

        @Override
      public void sendRouteMessage(@Nonnull ByteBuffer message) {
          checkNotClosed();
          sendRouteMessage0(nativePtr, message);
      }

        @Override
      public void terminate() {
          checkNotClosed();
          terminate0(nativePtr);
      }


        static native String getId0(long self);

        static native CefMediaSource getSource0(long self);

        static native CefMediaSink getSink0(long self);

        static native void sendRouteMessage0(long self, @Nonnull ByteBuffer message);

        static native void terminate0(long self);


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
