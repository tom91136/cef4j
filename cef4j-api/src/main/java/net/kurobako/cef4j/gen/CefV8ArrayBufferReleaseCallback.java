// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import javax.annotation.Nullable;

/**
 * Callback interface that is passed to net.kurobako.cef4j.gen.CefV8Value.createArrayBuffer().
 * <p>Definition generated from cef_v8_capi.h
 * <pre>typedef struct _cef_v8_array_buffer_release_callback_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_v8_array_buffer_release_callback_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:414</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public interface CefV8ArrayBufferReleaseCallback extends CefClientHandler {

    /**
     * Called to release {@code buffer} when the ArrayBuffer JS object is garbage collected. {@code buffer} is the value that was passed to CreateArrayBuffer along with this object.
     * <p>Definition generated from cef_v8_capi.h
     * <pre>void (CEF_CALLBACK* release_buffer)(struct _cef_v8_array_buffer_release_callback_t* self, void* buffer);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:420</a>
     */
    default void releaseBuffer(@Nullable NativePointer buffer) {
    }
    /**
     * Composite that fans callbacks out to every registered delegate. {@code void} methods invoke all
     * delegates in order; {@code boolean} methods short-circuit on the first {@code true}; handler-returning
     * {@code Optional}s collect every non-empty delegate and wrap them in the handler's own {@code Delegating}
     * wrapper; other {@code Optional}s pick the first non-empty; any other return type yields the first
     * delegate's value.
     */
    class Delegating implements CefV8ArrayBufferReleaseCallback {
        private final java.util.List<CefV8ArrayBufferReleaseCallback> delegates;

        public Delegating(java.util.List<CefV8ArrayBufferReleaseCallback> delegates) {
            this.delegates = java.util.List.copyOf(delegates);
        }

        @Override
        public void releaseBuffer(@Nullable NativePointer buffer) {
            for (CefV8ArrayBufferReleaseCallback d : delegates) d.releaseBuffer(buffer);
        }
    }

}
