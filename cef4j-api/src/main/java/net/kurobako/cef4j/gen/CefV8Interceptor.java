// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nullable;

/**
 * Interface that should be implemented to handle V8 interceptor calls. The methods of this class will be called on the thread associated with the V8 interceptor. Interceptor's named property handlers (with first argument of type CefString) are called when object is indexed by string. Indexed property handlers (with first argument of type int) are called when object is indexed by integer.
 * <p>Definition generated from cef_v8_capi.h
 * <pre>typedef struct _cef_v8_interceptor_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_v8_interceptor_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:284</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public interface CefV8Interceptor extends CefClientHandler {

    /**
     * Handle retrieval of the interceptor value identified by {@code name}. {@code object} is the receiver ('this' object) of the interceptor. If retrieval succeeds, set {@code retval} to the return value. If the requested value does not exist, don't set either {@code retval} or {@code exception}. If retrieval fails, set {@code exception} to the exception that will be thrown. If the property has an associated accessor, it will be called only if you don't set {@code retval}. Return {@code true} if interceptor retrieval was handled, {@code false} otherwise.
     * <p>Definition generated from cef_v8_capi.h
     * <pre>int (CEF_CALLBACK* get_byname)(struct _cef_v8_interceptor_t* self, const cef_string_t* name, struct _cef_v8_value_t* object, struct _cef_v8_value_t** retval, cef_string_t* exception);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:295</a>
     */
    default int getByname(@Nullable String name, @Nullable CefV8Value object, @Nullable AtomicReference<CefV8Value> retval, @Nullable String exception) {
        return 0;
    }

    /**
     * Handle retrieval of the interceptor value identified by {@code index}. {@code object} is the receiver ('this' object) of the interceptor. If retrieval succeeds, set {@code retval} to the return value. If the requested value does not exist, don't set either {@code retval} or {@code exception}. If retrieval fails, set {@code exception} to the exception that will be thrown. Return {@code true} if interceptor retrieval was handled, {@code false} otherwise.
     * <p>Definition generated from cef_v8_capi.h
     * <pre>int (CEF_CALLBACK* get_byindex)(struct _cef_v8_interceptor_t* self, int index, struct _cef_v8_value_t* object, struct _cef_v8_value_t** retval, cef_string_t* exception);</pre>
     *
     * @param index zero-based index
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:310</a>
     */
    default int getByindex(int index, @Nullable CefV8Value object, @Nullable AtomicReference<CefV8Value> retval, @Nullable String exception) {
        return 0;
    }

    /**
     * Handle assignment of the interceptor value identified by {@code name}. {@code object} is the receiver ('this' object) of the interceptor. {@code value} is the new value being assigned to the interceptor. If assignment fails, set {@code exception} to the exception that will be thrown. This setter will always be called, even when the property has an associated accessor. Return {@code true} if interceptor assignment was handled, {@code false} otherwise.
     * <p>Definition generated from cef_v8_capi.h
     * <pre>int (CEF_CALLBACK* set_byname)(struct _cef_v8_interceptor_t* self, const cef_string_t* name, struct _cef_v8_value_t* object, struct _cef_v8_value_t* value, cef_string_t* exception);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:324</a>
     */
    default int setByname(@Nullable String name, @Nullable CefV8Value object, @Nullable CefV8Value value, @Nullable String exception) {
        return 0;
    }

    /**
     * Handle assignment of the interceptor value identified by {@code index}. {@code object} is the receiver ('this' object) of the interceptor. {@code value} is the new value being assigned to the interceptor. If assignment fails, set {@code exception} to the exception that will be thrown. Return {@code true} if interceptor assignment was handled, {@code false} otherwise.
     * <p>Definition generated from cef_v8_capi.h
     * <pre>int (CEF_CALLBACK* set_byindex)(struct _cef_v8_interceptor_t* self, int index, struct _cef_v8_value_t* object, struct _cef_v8_value_t* value, cef_string_t* exception);</pre>
     *
     * @param index zero-based index
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:338</a>
     */
    default int setByindex(int index, @Nullable CefV8Value object, @Nullable CefV8Value value, @Nullable String exception) {
        return 0;
    }
    /**
     * Composite that fans callbacks out to every registered delegate. {@code void} methods invoke all
     * delegates in order; {@code boolean} methods short-circuit on the first {@code true}; handler-returning
     * {@code Optional}s collect every non-empty delegate and wrap them in the handler's own {@code Delegating}
     * wrapper; other {@code Optional}s pick the first non-empty; any other return type yields the first
     * delegate's value.
     */
    class Delegating implements CefV8Interceptor {
        private final java.util.List<CefV8Interceptor> delegates;

        public Delegating(java.util.List<CefV8Interceptor> delegates) {
            this.delegates = java.util.List.copyOf(delegates);
        }

        @Override
        public int getByname(@Nullable String name, @Nullable CefV8Value object, @Nullable AtomicReference<CefV8Value> retval, @Nullable String exception) {
            if (!delegates.isEmpty()) return delegates.get(0).getByname(name, object, retval, exception);
            return 0;
        }

        @Override
        public int getByindex(int index, @Nullable CefV8Value object, @Nullable AtomicReference<CefV8Value> retval, @Nullable String exception) {
            if (!delegates.isEmpty()) return delegates.get(0).getByindex(index, object, retval, exception);
            return 0;
        }

        @Override
        public int setByname(@Nullable String name, @Nullable CefV8Value object, @Nullable CefV8Value value, @Nullable String exception) {
            if (!delegates.isEmpty()) return delegates.get(0).setByname(name, object, value, exception);
            return 0;
        }

        @Override
        public int setByindex(int index, @Nullable CefV8Value object, @Nullable CefV8Value value, @Nullable String exception) {
            if (!delegates.isEmpty()) return delegates.get(0).setByindex(index, object, value, exception);
            return 0;
        }
    }

}
