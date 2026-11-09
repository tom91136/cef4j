// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-types
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import java.util.Optional;
import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * Represents a source from which media can be routed. Instances of this object are retrieved via
 * {@link net.kurobako.cef4j.gen.CefMediaRouter#getSource(String)}. The methods of this class may be called on any
 * browser process thread unless otherwise indicated.
 *
 * <p>Definition generated from cef_media_router_capi.h
 *
 * <pre>typedef struct _cef_media_source_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_media_source_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__media__router_8h.html">cef_media_router.h:291</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-types -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@NullableBoundary("generated external ABI")
public interface CefMediaSource extends CefLibraryObject {

    /**
     * Returns the ID (media source URN or URL) for this source.
     *
     * <p>Definition generated from cef_media_router_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_id)(struct _cef_media_source_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__media__router_8h.html">cef_media_router.h:299</a>
     */
    Optional<String> getId();

    /**
     * Returns {@code true} if this source outputs its content via Cast.
     *
     * <p>Definition generated from cef_media_router_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_cast_source)(struct _cef_media_source_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__media__router_8h.html">cef_media_router.h:305</a>
     */
    boolean isCastSource();

    /**
     * Returns {@code true} if this source outputs its content via DIAL.
     *
     * <p>Definition generated from cef_media_router_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_dial_source)(struct _cef_media_source_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__media__router_8h.html">cef_media_router.h:311</a>
     */
    boolean isDialSource();

    final class NativePeer extends net.kurobako.cef4j.peer.NativeObject implements CefMediaSource {
        NativePeer(long ptr) {
            super("CefMediaSource", ptr, NativePeer::release0);
        }

        private static native void release0(long ptr);

        @Override
        public Optional<String> getId() {
            checkNotClosed();
            return Optional.ofNullable(getId0(nativePtr));
        }

        @Override
        public boolean isCastSource() {
            checkNotClosed();
            return isCastSource0(nativePtr);
        }

        @Override
        public boolean isDialSource() {
            checkNotClosed();
            return isDialSource0(nativePtr);
        }

        static native String getId0(long self);

        static native boolean isCastSource0(long self);

        static native boolean isDialSource0(long self);
    }
}
