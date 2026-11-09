// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-types
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * Generic callback interface used for asynchronous continuation.
 *
 * <p>Definition generated from cef_callback_capi.h
 *
 * <pre>typedef struct _cef_callback_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_callback_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__callback_8h.html">cef_callback.h:43</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-types -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@NullableBoundary("generated external ABI")
public interface CefCallback extends CefLibraryObject {

    /**
     * Continue processing.
     *
     * <p>Definition generated from cef_callback_capi.h
     *
     * <pre>void (CEF_CALLBACK* cont)(struct _cef_callback_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__callback_8h.html">cef_callback.h:49</a>
     */
    void cont();

    /**
     * Cancel processing.
     *
     * <p>Definition generated from cef_callback_capi.h
     *
     * <pre>void (CEF_CALLBACK* cancel)(struct _cef_callback_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__callback_8h.html">cef_callback.h:55</a>
     */
    void cancel();

    final class NativePeer extends net.kurobako.cef4j.peer.NativeObject implements CefCallback {
        NativePeer(long ptr) {
            super("CefCallback", ptr, NativePeer::release0);
        }

        private static native void release0(long ptr);

        @Override
        public void cont() {
            checkNotClosed();
            cont0(nativePtr);
        }

        @Override
        public void cancel() {
            checkNotClosed();
            cancel0(nativePtr);
        }

        static native void cont0(long self);

        static native void cancel0(long self);
    }
}
