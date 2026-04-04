// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
// -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nullable;
import javax.annotation.processing.Generated;

/**
 * Interface that should be implemented to handle V8 accessor calls. Accessor identifiers are registered by calling
 * net.kurobako.cef4j.gen.CefV8Value.setValue(). The methods of this class will be called on the thread associated with
 * the V8 accessor.
 *
 * <p>Definition generated from cef_v8_capi.h
 *
 * <pre>typedef struct _cef_v8_accessor_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_v8_accessor_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:249</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
public interface CefV8Accessor extends CefClientHandler {

    /**
     * Handle retrieval the accessor value identified by {@code name}. {@code object} is the receiver ('this' object) of
     * the accessor. If retrieval succeeds set {@code retval} to the return value. If retrieval fails set
     * {@code exception} to the exception that will be thrown. Return {@code true} if accessor retrieval was handled.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* get)(struct _cef_v8_accessor_t* self, const cef_string_t* name, struct _cef_v8_value_t* object, struct _cef_v8_value_t** retval, cef_string_t* exception);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:257</a>
     */
    default boolean get(
            @Nullable String name,
            @Nullable CefV8Value object,
            @Nullable AtomicReference<CefV8Value> retval,
            @Nullable String exception) {
        return false;
    }

    /**
     * Handle assignment of the accessor value identified by {@code name}. {@code object} is the receiver ('this'
     * object) of the accessor. {@code value} is the new value being assigned to the accessor. If assignment fails set
     * {@code exception} to the exception that will be thrown. Return {@code true} if accessor assignment was handled.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* set)(struct _cef_v8_accessor_t* self, const cef_string_t* name, struct _cef_v8_value_t* object, struct _cef_v8_value_t* value, cef_string_t* exception);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:270</a>
     */
    default boolean set(
            @Nullable String name,
            @Nullable CefV8Value object,
            @Nullable CefV8Value value,
            @Nullable String exception) {
        return false;
    }
}
