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
}
