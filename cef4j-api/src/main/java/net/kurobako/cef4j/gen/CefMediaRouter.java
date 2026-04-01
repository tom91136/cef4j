// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.util.Optional;
import javax.annotation.Nonnull;

/**
 * Supports discovery of and communication with media devices on the local network via the Cast and DIAL protocols. The
 * methods of this class may be called on any browser process thread unless otherwise indicated.
 *
 * <p>Definition generated from cef_media_router_capi.h
 *
 * <pre>typedef struct _cef_media_router_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_media_router_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__media__router_8h.html">cef_media_router.h:54</a>
 */
public interface CefMediaRouter extends CefLibraryObject {

    /**
     * Add an observer for MediaRouter events. The observer will remain registered until the returned Registration
     * object is destroyed.
     *
     * <p>Definition generated from cef_media_router_capi.h
     *
     * <pre>
     * cef_registration_t* (CEF_CALLBACK* add_observer)(struct _cef_media_router_t* self, struct _cef_media_observer_t* observer);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__media__router_8h.html">cef_media_router.h:72</a>
     */
    Optional<CefRegistration> addObserver(@Nonnull CefMediaObserver observer);

    /**
     * Retrieve this frame's HTML source as a string sent to the specified visitor.
     *
     * <p>Definition generated from cef_media_router_capi.h
     *
     * <pre>cef_media_source_t* (CEF_CALLBACK* get_source)(struct _cef_media_router_t* self, const cef_string_t* urn);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__frame_8h.html">cef_frame.h:124</a>
     */
    Optional<CefMediaSource> getSource(@Nonnull String urn);

    /**
     * Trigger an asynchronous call to {@link CefMediaObserver#onSinks(long, CefMediaSink[])} on all registered
     * observers.
     *
     * <p>Definition generated from cef_media_router_capi.h
     *
     * <pre>void (CEF_CALLBACK* notify_current_sinks)(struct _cef_media_router_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__media__router_8h.html">cef_media_router.h:88</a>
     */
    void notifyCurrentSinks();

    /**
     * Create a new route between {@code source} and {@code sink}. Source and sink must be valid, compatible (as
     * reported by {@link CefMediaSink#isCompatibleWith(CefMediaSource)}), and a route between them must not already
     * exist. {@code callback} will be executed on success or failure. If route creation succeeds it will also trigger
     * an asynchronous call to {@link CefMediaObserver#onRoutes(long, CefMediaRoute[])} on all registered observers.
     *
     * <p>Definition generated from cef_media_router_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* create_route)(struct _cef_media_router_t* self, struct _cef_media_source_t* source, struct _cef_media_sink_t* sink, struct _cef_media_route_create_callback_t* callback);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__media__router_8h.html">cef_media_router.h:95</a>
     */
    void createRoute(
            @Nonnull CefMediaSource source, @Nonnull CefMediaSink sink, @Nonnull CefMediaRouteCreateCallback callback);

    /**
     * Trigger an asynchronous call to {@link CefMediaObserver#onRoutes(long, CefMediaRoute[])} on all registered
     * observers.
     *
     * <p>Definition generated from cef_media_router_capi.h
     *
     * <pre>void (CEF_CALLBACK* notify_current_routes)(struct _cef_media_router_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__media__router_8h.html">cef_media_router.h:108</a>
     */
    void notifyCurrentRoutes();
    /**
     * Returns the global object for this context. The context must be entered before calling this method.
     *
     * <p>Definition generated from cef_media_router_capi.h
     *
     * <pre>CEF_EXPORT cef_media_router_t* cef_media_router_get_global(struct _cef_completion_callback_t* callback);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:177</a>
     */
    static Optional<CefMediaRouter> getGlobal(@Nonnull CefCompletionCallback callback) {
        return Optional.ofNullable(NativePeer.N_GetGlobal(callback));
    }

    final class NativePeer implements CefMediaRouter, AutoCloseable {
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

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefMediaRouter.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefMediaRouter 0x{}", Long.toHexString(ptr));
                N_Release(ptr);
            }
        }

        private static native void N_Release(long ptr);

        @Override
        public Optional<CefRegistration> addObserver(@Nonnull CefMediaObserver observer) {
            return Optional.ofNullable(N_AddObserver(nativePtr, observer));
        }

        @Override
        public Optional<CefMediaSource> getSource(@Nonnull String urn) {
            return Optional.ofNullable(N_GetSource(nativePtr, urn));
        }

        @Override
        public void notifyCurrentSinks() {
            N_NotifyCurrentSinks(nativePtr);
        }

        @Override
        public void createRoute(
                @Nonnull CefMediaSource source,
                @Nonnull CefMediaSink sink,
                @Nonnull CefMediaRouteCreateCallback callback) {
            N_CreateRoute(nativePtr, source, sink, callback);
        }

        @Override
        public void notifyCurrentRoutes() {
            N_NotifyCurrentRoutes(nativePtr);
        }

        private static native CefRegistration N_AddObserver(long self, CefMediaObserver observer);

        private static native CefMediaSource N_GetSource(long self, String urn);

        private static native void N_NotifyCurrentSinks(long self);

        private static native void N_CreateRoute(
                long self, CefMediaSource source, CefMediaSink sink, CefMediaRouteCreateCallback callback);

        private static native void N_NotifyCurrentRoutes(long self);

        static native CefMediaRouter N_GetGlobal(CefCompletionCallback callback);

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
            return "CefMediaRouter{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
