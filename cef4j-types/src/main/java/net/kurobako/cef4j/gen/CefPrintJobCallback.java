// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-types
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;

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
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__print__handler_8h.html">cef_print_handler.h:64</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-types -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@NullableBoundary("generated external ABI")
public interface CefPrintJobCallback extends CefLibraryObject {

    /**
     * Indicate completion of the print job.
     *
     * <p>Definition generated from cef_print_handler_capi.h
     *
     * <pre>void (CEF_CALLBACK* cont)(struct _cef_print_job_callback_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__print__handler_8h.html">cef_print_handler.h:70</a>
     */
    void cont();

    final class NativePeer extends net.kurobako.cef4j.peer.NativeObject implements CefPrintJobCallback {
        NativePeer(long ptr) {
            super("CefPrintJobCallback", ptr, NativePeer::release0);
        }

        private static native void release0(long ptr);

        @Override
        public void cont() {
            checkNotClosed();
            cont0(nativePtr);
        }

        static native void cont0(long self);
    }
}
