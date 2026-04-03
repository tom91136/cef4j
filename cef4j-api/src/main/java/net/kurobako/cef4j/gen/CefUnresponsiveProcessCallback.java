// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
// -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;

/**
 * Callback interface for asynchronous handling of an unresponsive process.
 *
 * <p>Definition generated from cef_unresponsive_process_callback_capi.h
 *
 * <pre>typedef struct _cef_unresponsive_process_callback_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_unresponsive_process_callback_t;</pre>
 *
 * @see <a
 *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__unresponsive__process__callback_8h.html">cef_unresponsive_process_callback.h:43</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
public interface CefUnresponsiveProcessCallback extends CefLibraryObject {

    /**
     * Reset the timeout for the unresponsive process.
     *
     * <p>Definition generated from cef_unresponsive_process_callback_capi.h
     *
     * <pre>void (CEF_CALLBACK* wait)(struct _cef_unresponsive_process_callback_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__unresponsive__process__callback_8h.html">cef_unresponsive_process_callback.h:49</a>
     */
    void cefWait();

    /**
     * Terminate the unresponsive process.
     *
     * <p>Definition generated from cef_unresponsive_process_callback_capi.h
     *
     * <pre>void (CEF_CALLBACK* terminate)(struct _cef_unresponsive_process_callback_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__unresponsive__process__callback_8h.html">cef_unresponsive_process_callback.h:55</a>
     */
    void terminate();

    final class NativePeer implements CefUnresponsiveProcessCallback, AutoCloseable {
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
            if (closed) throw new IllegalStateException("CefUnresponsiveProcessCallback has been closed");
        }

        private static final org.slf4j.Logger _log =
                org.slf4j.LoggerFactory.getLogger(CefUnresponsiveProcessCallback.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled())
                    _log.trace("release CefUnresponsiveProcessCallback 0x{}", Long.toHexString(ptr));
                release0(ptr);
            }
        }

        private static native void release0(long ptr);

        @Override
        public void cefWait() {
            checkNotClosed();
            cefWait0(nativePtr);
        }

        @Override
        public void terminate() {
            checkNotClosed();
            terminate0(nativePtr);
        }

        private static native void cefWait0(long self);

        private static native void terminate0(long self);

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
            return "CefUnresponsiveProcessCallback{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
