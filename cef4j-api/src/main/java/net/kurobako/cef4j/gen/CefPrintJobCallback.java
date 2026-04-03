// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
// -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;

/**
 * Callback interface for asynchronous continuation of print job requests.
 *
 * <p>Definition generated from cef_print_handler_capi.h
 *
 * <pre>typedef struct _cef_print_job_callback_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_print_job_callback_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__print__handler_8h.html">cef_print_handler.h:64</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
public interface CefPrintJobCallback extends CefLibraryObject {

    /**
     * Indicate completion of the print job.
     *
     * <p>Definition generated from cef_print_handler_capi.h
     *
     * <pre>void (CEF_CALLBACK* cont)(struct _cef_print_job_callback_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__print__handler_8h.html">cef_print_handler.h:70</a>
     */
    void cont();

    final class NativePeer implements CefPrintJobCallback, AutoCloseable {
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
            if (closed) throw new IllegalStateException("CefPrintJobCallback has been closed");
        }

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefPrintJobCallback.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefPrintJobCallback 0x{}", Long.toHexString(ptr));
                release0(ptr);
            }
        }

        private static native void release0(long ptr);

        @Override
        public void cont() {
            checkNotClosed();
            cont0(nativePtr);
        }

        private static native void cont0(long self);

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
            return "CefPrintJobCallback{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
