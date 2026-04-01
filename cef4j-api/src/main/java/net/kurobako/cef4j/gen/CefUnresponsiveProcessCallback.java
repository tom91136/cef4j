// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

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
public interface CefUnresponsiveProcessCallback extends CefLibraryObject {

    /**
     * Wait indefinitely for the event to be signaled. This method will not return until after the call to Signal() has
     * completed. This method cannot be called on the browser process UI or IO threads.
     *
     * <p>Definition generated from cef_unresponsive_process_callback_capi.h
     *
     * <pre>void (CEF_CALLBACK* wait)(struct _cef_unresponsive_process_callback_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__waitable__event_8h.html">cef_waitable_event.h:90</a>
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

        NativePeer(long ptr) {
            this.nativePtr = ptr;
            this.cleanable = net.kurobako.cef4j.NativeCleaner.INSTANCE.register(this, new Release(ptr));
        }

        @Override
        public void close() {
            cleanable.clean();
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
                N_Release(ptr);
            }
        }

        private static native void N_Release(long ptr);

        @Override
        public void cefWait() {
            N_Wait(nativePtr);
        }

        @Override
        public void terminate() {
            N_Terminate(nativePtr);
        }

        private static native void N_Wait(long self);

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
            return "CefUnresponsiveProcessCallback{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
