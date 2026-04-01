// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.util.Optional;
import javax.annotation.Nonnull;

/**
 * Represents a sink to which media can be routed. Instances of this object are retrieved via
 * {@link CefMediaObserver#onSinks(long, CefMediaSink[])}. The methods of this class may be called on any browser
 * process thread unless otherwise indicated.
 *
 * <p>Definition generated from cef_media_router_capi.h
 *
 * <pre>typedef struct _cef_media_sink_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_media_sink_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__media__router_8h.html">cef_media_router.h:221</a>
 */
public interface CefMediaSink extends CefLibraryObject {

    /**
     * Returns the unique identifier for this download.
     *
     * <p>Definition generated from cef_media_router_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_id)(struct _cef_media_sink_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__download__item_8h.html">cef_download_item.h:137</a>
     */
    Optional<String> getId();

    /**
     * Returns the name of this node.
     *
     * <p>Definition generated from cef_media_router_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_name)(struct _cef_media_sink_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__dom_8h.html">cef_dom.h:215</a>
     */
    Optional<String> getName();

    /**
     * Returns the icon type for this sink.
     *
     * <p>Definition generated from cef_media_router_capi.h
     *
     * <pre>cef_media_sink_icon_type_t (CEF_CALLBACK* get_icon_type)(struct _cef_media_sink_t* self);</pre>
     *
     * @return the result, or {@code CEF_MSIT_GENERIC} for default handling
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__media__router_8h.html">cef_media_router.h:243</a>
     */
    CefMediaSinkIconType getIconType();

    /**
     * Asynchronously retrieves device info.
     *
     * <p>Definition generated from cef_media_router_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* get_device_info)(struct _cef_media_sink_t* self, struct _cef_media_sink_device_info_callback_t* callback);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__media__router_8h.html">cef_media_router.h:249</a>
     */
    void getDeviceInfo(@Nonnull CefMediaSinkDeviceInfoCallback callback);

    /**
     * Returns {@code true} if this sink accepts content via Cast.
     *
     * <p>Definition generated from cef_media_router_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_cast_sink)(struct _cef_media_sink_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__media__router_8h.html">cef_media_router.h:256</a>
     */
    boolean isCastSink();

    /**
     * Returns {@code true} if this sink accepts content via DIAL.
     *
     * <p>Definition generated from cef_media_router_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_dial_sink)(struct _cef_media_sink_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__media__router_8h.html">cef_media_router.h:262</a>
     */
    boolean isDialSink();

    /**
     * Returns {@code true} if this sink is compatible with {@code source}.
     *
     * <p>Definition generated from cef_media_router_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_compatible_with)(struct _cef_media_sink_t* self, struct _cef_media_source_t* source);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__media__router_8h.html">cef_media_router.h:268</a>
     */
    boolean isCompatibleWith(@Nonnull CefMediaSource source);

    final class NativePeer implements CefMediaSink, AutoCloseable {
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

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefMediaSink.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefMediaSink 0x{}", Long.toHexString(ptr));
                N_Release(ptr);
            }
        }

        private static native void N_Release(long ptr);

        @Override
        public Optional<String> getId() {
            return Optional.ofNullable(N_GetId(nativePtr));
        }

        @Override
        public Optional<String> getName() {
            return Optional.ofNullable(N_GetName(nativePtr));
        }

        @Override
        public CefMediaSinkIconType getIconType() {
            return N_GetIconType(nativePtr);
        }

        @Override
        public void getDeviceInfo(@Nonnull CefMediaSinkDeviceInfoCallback callback) {
            N_GetDeviceInfo(nativePtr, callback);
        }

        @Override
        public boolean isCastSink() {
            return N_IsCastSink(nativePtr);
        }

        @Override
        public boolean isDialSink() {
            return N_IsDialSink(nativePtr);
        }

        @Override
        public boolean isCompatibleWith(@Nonnull CefMediaSource source) {
            return N_IsCompatibleWith(nativePtr, source);
        }

        private static native String N_GetId(long self);

        private static native String N_GetName(long self);

        private static native CefMediaSinkIconType N_GetIconType(long self);

        private static native void N_GetDeviceInfo(long self, CefMediaSinkDeviceInfoCallback callback);

        private static native boolean N_IsCastSink(long self);

        private static native boolean N_IsDialSink(long self);

        private static native boolean N_IsCompatibleWith(long self, CefMediaSource source);

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
            return "CefMediaSink{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
