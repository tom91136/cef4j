// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-api
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import java.util.Optional;
import javax.annotation.Nullable;
import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;

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
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__media__router_8h.html">cef_media_router.h:54</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-api -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
@NullableBoundary("generated external ABI")
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
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__media__router_8h.html">cef_media_router.h:72</a>
     */
    Optional<CefRegistration> addObserver(@Nullable CefMediaObserver observer);

    /**
     * Returns a MediaSource object for the specified media source URN. Supported URN schemes include "cast:" and
     * "dial:", and will be already known by the client application (e.g.
     * "cast:&lt;appId&gt;?clientId=&lt;clientId&gt;").
     *
     * <p>Definition generated from cef_media_router_capi.h
     *
     * <pre>cef_media_source_t* (CEF_CALLBACK* get_source)(struct _cef_media_router_t* self, const cef_string_t* urn);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__media__router_8h.html">cef_media_router.h:80</a>
     */
    Optional<CefMediaSource> getSource(@Nullable String urn);

    /**
     * Trigger an asynchronous call to {@link net.kurobako.cef4j.gen.CefMediaObserver#onSinks(long, CefMediaSink[])} on
     * all registered observers.
     *
     * <p>Definition generated from cef_media_router_capi.h
     *
     * <pre>void (CEF_CALLBACK* notify_current_sinks)(struct _cef_media_router_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__media__router_8h.html">cef_media_router.h:88</a>
     */
    void notifyCurrentSinks();

    /**
     * Create a new route between {@code source} and {@code sink}. Source and sink must be valid, compatible (as
     * reported by {@link net.kurobako.cef4j.gen.CefMediaSink#isCompatibleWith(CefMediaSource)}), and a route between
     * them must not already exist. {@code callback} will be executed on success or failure. If route creation succeeds
     * it will also trigger an asynchronous call to {@link net.kurobako.cef4j.gen.CefMediaObserver#onRoutes(long,
     * CefMediaRoute[])} on all registered observers.
     *
     * <p>Definition generated from cef_media_router_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* create_route)(struct _cef_media_router_t* self, struct _cef_media_source_t* source, struct _cef_media_sink_t* sink, struct _cef_media_route_create_callback_t* callback);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__media__router_8h.html">cef_media_router.h:95</a>
     */
    void createRoute(
            @Nullable CefMediaSource source,
            @Nullable CefMediaSink sink,
            @Nullable CefMediaRouteCreateCallback callback);

    /**
     * Trigger an asynchronous call to {@link net.kurobako.cef4j.gen.CefMediaObserver#onRoutes(long, CefMediaRoute[])}
     * on all registered observers.
     *
     * <p>Definition generated from cef_media_router_capi.h
     *
     * <pre>void (CEF_CALLBACK* notify_current_routes)(struct _cef_media_router_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__media__router_8h.html">cef_media_router.h:108</a>
     */
    void notifyCurrentRoutes();
    /**
     * Returns the MediaRouter object associated with the global request context. If {@code callback} is
     * non-{@code null} it will be executed asnychronously on the UI thread after the manager's storage has been
     * initialized. Equivalent to calling net.kurobako.cef4j.gen.CefRequestContext.getGlobalContext()->GetMediaRouter().
     *
     * <p>Definition generated from cef_media_router_capi.h
     *
     * <pre>CEF_EXPORT cef_media_router_t* cef_media_router_get_global(struct _cef_completion_callback_t* callback);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__media__router_8h.html">cef_media_router.h:62</a>
     */
    static Optional<CefMediaRouter> getGlobal(@Nullable CefCompletionCallback callback) {
        return Optional.ofNullable(NativePeer.getGlobal0(callback));
    }

    final class NativePeer implements CefMediaRouter, AutoCloseable {
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
            if (closed) throw new IllegalStateException("CefMediaRouter has been closed");
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
                release0(ptr);
            }
        }

        private static native void release0(long ptr);

        @Override
        public Optional<CefRegistration> addObserver(@Nullable CefMediaObserver observer) {
            checkNotClosed();
            return Optional.ofNullable(addObserver0(nativePtr, observer));
        }

        @Override
        public Optional<CefMediaSource> getSource(@Nullable String urn) {
            checkNotClosed();
            return Optional.ofNullable(getSource0(nativePtr, urn));
        }

        @Override
        public void notifyCurrentSinks() {
            checkNotClosed();
            notifyCurrentSinks0(nativePtr);
        }

        @Override
        public void createRoute(
                @Nullable CefMediaSource source,
                @Nullable CefMediaSink sink,
                @Nullable CefMediaRouteCreateCallback callback) {
            checkNotClosed();
            CefLibraryObject.requireOpen(source, "CefMediaSource");
            CefLibraryObject.requireOpen(sink, "CefMediaSink");
            createRoute0(nativePtr, source, sink, callback);
        }

        @Override
        public void notifyCurrentRoutes() {
            checkNotClosed();
            notifyCurrentRoutes0(nativePtr);
        }

        static native CefRegistration addObserver0(long self, @Nullable CefMediaObserver observer);

        static native CefMediaSource getSource0(long self, @Nullable String urn);

        static native void notifyCurrentSinks0(long self);

        static native void createRoute0(
                long self,
                @Nullable CefMediaSource source,
                @Nullable CefMediaSink sink,
                @Nullable CefMediaRouteCreateCallback callback);

        static native void notifyCurrentRoutes0(long self);

        static native CefMediaRouter getGlobal0(@Nullable CefCompletionCallback callback);

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
