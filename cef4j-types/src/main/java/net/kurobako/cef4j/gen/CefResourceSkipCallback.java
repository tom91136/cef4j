// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-types
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * Callback for asynchronous continuation of {@link net.kurobako.cef4j.gen.CefResourceHandler#skip(long, long[],
 * CefResourceSkipCallback)}.
 *
 * <p>Definition generated from cef_resource_handler_capi.h
 *
 * <pre>typedef struct _cef_resource_skip_callback_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_resource_skip_callback_t;</pre>
 *
 * @see <a
 *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__resource__handler_8h.html">cef_resource_handler.h:48</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-types -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@NullableBoundary("generated external ABI")
public interface CefResourceSkipCallback extends CefLibraryObject {

    /**
     * Callback for asynchronous continuation of Skip(). If {@code bytes_skipped} > 0 then either Skip() will be called
     * again until the requested number of bytes have been skipped or the request will proceed. If {@code bytes_skipped}
     * &lt;= 0 the request will fail with {@code ERR_REQUEST_RANGE_NOT_SATISFIABLE}.
     *
     * <p>Definition generated from cef_resource_handler_capi.h
     *
     * <pre>void (CEF_CALLBACK* cont)(struct _cef_resource_skip_callback_t* self, int64_t bytes_skipped);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__resource__handler_8h.html">cef_resource_handler.h:54</a>
     */
    void cont(long bytesSkipped);

    final class NativePeer extends net.kurobako.cef4j.peer.NativeObject implements CefResourceSkipCallback {
        NativePeer(long ptr) {
            super("CefResourceSkipCallback", ptr, NativePeer::release0);
        }

        private static native void release0(long ptr);

        @Override
        public void cont(long bytesSkipped) {
            checkNotClosed();
            cont0(nativePtr, bytesSkipped);
        }

        static native void cont0(long self, long bytesSkipped);
    }
}
