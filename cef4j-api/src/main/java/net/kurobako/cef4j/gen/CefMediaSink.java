// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import java.util.Optional;
import javax.annotation.Nullable;

/**
 * Represents a sink to which media can be routed. Instances of this object are retrieved via {@link net.kurobako.cef4j.gen.CefMediaObserver#onSinks(long, CefMediaSink[])}. The methods of this class may be called on any browser process thread unless otherwise indicated.
 * <p>Definition generated from cef_media_router_capi.h
 * <pre>typedef struct _cef_media_sink_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_media_sink_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__media__router_8h.html">cef_media_router.h:221</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public interface CefMediaSink extends CefLibraryObject {

    /**
     * Returns the ID for this sink.
     * <p>Definition generated from cef_media_router_capi.h
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_id)(struct _cef_media_sink_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__media__router_8h.html">cef_media_router.h:231</a>
     */
    Optional<String> getId();

    /**
     * Returns the name of this sink.
     * <p>Definition generated from cef_media_router_capi.h
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_name)(struct _cef_media_sink_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__media__router_8h.html">cef_media_router.h:237</a>
     */
    Optional<String> getName();

    /**
     * Returns the icon type for this sink.
     * <p>Definition generated from cef_media_router_capi.h
     * <pre>cef_media_sink_icon_type_t (CEF_CALLBACK* get_icon_type)(struct _cef_media_sink_t* self);</pre>
     *
     * @return the result, or {@code CEF_MSIT_GENERIC} for default handling
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__media__router_8h.html">cef_media_router.h:243</a>
     */
    CefMediaSinkIconType getIconType();

    /**
     * Asynchronously retrieves device info.
     * <p>Definition generated from cef_media_router_capi.h
     * <pre>void (CEF_CALLBACK* get_device_info)(struct _cef_media_sink_t* self, struct _cef_media_sink_device_info_callback_t* callback);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__media__router_8h.html">cef_media_router.h:249</a>
     */
    void getDeviceInfo(@Nullable CefMediaSinkDeviceInfoCallback callback);

    /**
     * Returns {@code true} if this sink accepts content via Cast.
     * <p>Definition generated from cef_media_router_capi.h
     * <pre>int (CEF_CALLBACK* is_cast_sink)(struct _cef_media_sink_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__media__router_8h.html">cef_media_router.h:256</a>
     */
    boolean isCastSink();

    /**
     * Returns {@code true} if this sink accepts content via DIAL.
     * <p>Definition generated from cef_media_router_capi.h
     * <pre>int (CEF_CALLBACK* is_dial_sink)(struct _cef_media_sink_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__media__router_8h.html">cef_media_router.h:262</a>
     */
    boolean isDialSink();

    /**
     * Returns {@code true} if this sink is compatible with {@code source}.
     * <p>Definition generated from cef_media_router_capi.h
     * <pre>int (CEF_CALLBACK* is_compatible_with)(struct _cef_media_sink_t* self, struct _cef_media_source_t* source);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__media__router_8h.html">cef_media_router.h:268</a>
     */
    boolean isCompatibleWith(@Nullable CefMediaSource source);
    final class NativePeer implements CefMediaSink, AutoCloseable {
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
            if (closed) throw new IllegalStateException("CefMediaSink has been closed");
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
      public Optional<String> getName() {
          checkNotClosed();
          return Optional.ofNullable(getName0(nativePtr));
      }

        @Override
      public CefMediaSinkIconType getIconType() {
          checkNotClosed();
          return getIconType0(nativePtr);
      }

        @Override
      public void getDeviceInfo(@Nullable CefMediaSinkDeviceInfoCallback callback) {
          checkNotClosed();
          getDeviceInfo0(nativePtr, callback);
      }

        @Override
      public boolean isCastSink() {
          checkNotClosed();
          return isCastSink0(nativePtr);
      }

        @Override
      public boolean isDialSink() {
          checkNotClosed();
          return isDialSink0(nativePtr);
      }

        @Override
      public boolean isCompatibleWith(@Nullable CefMediaSource source) {
          checkNotClosed();
            CefLibraryObject.requireOpen(source, "CefMediaSource");
          return isCompatibleWith0(nativePtr, source);
      }


        static native String getId0(long self);

        static native String getName0(long self);

        static native CefMediaSinkIconType getIconType0(long self);

        static native void getDeviceInfo0(long self, @Nullable CefMediaSinkDeviceInfoCallback callback);

        static native boolean isCastSink0(long self);

        static native boolean isDialSink0(long self);

        static native boolean isCompatibleWith0(long self, @Nullable CefMediaSource source);


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
