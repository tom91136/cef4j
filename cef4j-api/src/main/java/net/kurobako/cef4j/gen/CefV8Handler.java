// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nullable;

/**
 * Interface that should be implemented to handle V8 function calls. The methods of this class will be called on the thread associated with the V8 function.
 * <p>Definition generated from cef_v8_capi.h
 * <pre>typedef struct _cef_v8_handler_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_v8_handler_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:226</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public interface CefV8Handler extends CefClientHandler {

    /**
     * Handle execution of the function identified by {@code name}. {@code object} is the receiver ('this' object) of the function. {@code arguments} is the list of arguments passed to the function. If execution succeeds set {@code retval} to the function return value. If execution fails set {@code exception} to the exception that will be thrown. Return {@code true} if execution was handled.
     * <p>Definition generated from cef_v8_capi.h
     * <pre>int (CEF_CALLBACK* execute)(struct _cef_v8_handler_t* self, const cef_string_t* name, struct _cef_v8_value_t* object, size_t argumentsCount, struct _cef_v8_value_t* const* arguments, struct _cef_v8_value_t** retval, cef_string_t* exception);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:234</a>
     */
    default boolean execute(@Nullable String name, @Nullable CefV8Value object, long argumentsCount, @Nullable CefV8Value[] arguments, @Nullable AtomicReference<CefV8Value> retval, @Nullable String exception) {
        return false;
    }
}
