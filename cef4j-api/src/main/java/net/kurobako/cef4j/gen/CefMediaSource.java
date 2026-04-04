// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
// -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import java.util.Optional;
import javax.annotation.processing.Generated;

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
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__media__router_8h.html">cef_media_router.h:291</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
public interface CefMediaSource extends CefLibraryObject {

    /**
     * Returns the ID (media source URN or URL) for this source.
     *
     * <p>Definition generated from cef_media_router_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_id)(struct _cef_media_source_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__media__router_8h.html">cef_media_router.h:299</a>
     */
    Optional<String> getId();

    /**
     * Returns {@code true} if this source outputs its content via Cast.
     *
     * <p>Definition generated from cef_media_router_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_cast_source)(struct _cef_media_source_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__media__router_8h.html">cef_media_router.h:305</a>
     */
    boolean isCastSource();

    /**
     * Returns {@code true} if this source outputs its content via DIAL.
     *
     * <p>Definition generated from cef_media_router_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_dial_source)(struct _cef_media_source_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__media__router_8h.html">cef_media_router.h:311</a>
     */
    boolean isDialSource();

    final class NativePeer implements CefMediaSource, AutoCloseable {
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
            if (closed) throw new IllegalStateException("CefMediaSource has been closed");
        }

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefMediaSource.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefMediaSource 0x{}", Long.toHexString(ptr));
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
        public boolean isCastSource() {
            checkNotClosed();
            return isCastSource0(nativePtr);
        }

        @Override
        public boolean isDialSource() {
            checkNotClosed();
            return isDialSource0(nativePtr);
        }

        private static native String getId0(long self);

        private static native boolean isCastSource0(long self);

        private static native boolean isDialSource0(long self);

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
            return "CefMediaSource{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
