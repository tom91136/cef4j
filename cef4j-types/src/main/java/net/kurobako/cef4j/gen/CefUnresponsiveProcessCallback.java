// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-types
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;

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
 *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__unresponsive__process__callback_8h.html">cef_unresponsive_process_callback.h:43</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-types -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@NullableBoundary("generated external ABI")
public interface CefUnresponsiveProcessCallback extends CefLibraryObject {

    /**
     * Reset the timeout for the unresponsive process.
     *
     * <p>Definition generated from cef_unresponsive_process_callback_capi.h
     *
     * <pre>void (CEF_CALLBACK* wait)(struct _cef_unresponsive_process_callback_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__unresponsive__process__callback_8h.html">cef_unresponsive_process_callback.h:49</a>
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
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__unresponsive__process__callback_8h.html">cef_unresponsive_process_callback.h:55</a>
     */
    void terminate();

    final class NativePeer extends net.kurobako.cef4j.peer.NativeObject implements CefUnresponsiveProcessCallback {
        NativePeer(long ptr) {
            super("CefUnresponsiveProcessCallback", ptr, NativePeer::release0);
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

        static native void cefWait0(long self);

        static native void terminate0(long self);
    }
}
