// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
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
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:226</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public interface CefV8Handler extends CefClientHandler {

    /**
     * Handle execution of the function identified by {@code name}. {@code object} is the receiver ('this' object) of the function. {@code arguments} is the list of arguments passed to the function. If execution succeeds set {@code retval} to the function return value. If execution fails set {@code exception} to the exception that will be thrown. Return {@code true} if execution was handled.
     * <p>Definition generated from cef_v8_capi.h
     * <pre>int (CEF_CALLBACK* execute)(struct _cef_v8_handler_t* self, const cef_string_t* name, struct _cef_v8_value_t* object, size_t argumentsCount, struct _cef_v8_value_t* const* arguments, struct _cef_v8_value_t** retval, cef_string_t* exception);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:234</a>
     */
    default boolean execute(@Nullable String name, @Nullable CefV8Value object, long argumentsCount, @Nullable CefV8Value[] arguments, @Nullable AtomicReference<CefV8Value> retval, @Nullable String exception) {
        return false;
    }
    /**
     * Composite that fans callbacks out to every registered delegate. {@code void} methods invoke all
     * delegates in order; {@code boolean} methods short-circuit on the first {@code true}; handler-returning
     * {@code Optional}s collect every non-empty delegate and wrap them in the handler's own {@code Delegating}
     * wrapper; other {@code Optional}s pick the first non-empty; any other return type yields the first
     * delegate's value.
     */
    class Delegating implements CefV8Handler {
        private final java.util.List<CefV8Handler> delegates;

        public Delegating(java.util.List<CefV8Handler> delegates) {
            this.delegates = java.util.List.copyOf(delegates);
        }

        @Override
        public boolean execute(@Nullable String name, @Nullable CefV8Value object, long argumentsCount, @Nullable CefV8Value[] arguments, @Nullable AtomicReference<CefV8Value> retval, @Nullable String exception) {
            for (CefV8Handler d : delegates) {
                if (d.execute(name, object, argumentsCount, arguments, retval, exception)) return true;
            }
            if (!delegates.isEmpty()) return false;
            return false;
        }
    }

}
