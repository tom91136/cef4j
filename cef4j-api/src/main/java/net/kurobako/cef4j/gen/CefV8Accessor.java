// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nullable;

/**
 * Interface that should be implemented to handle V8 accessor calls. Accessor identifiers are registered by calling net.kurobako.cef4j.gen.CefV8Value.setValue(). The methods of this class will be called on the thread associated with the V8 accessor.
 * <p>Definition generated from cef_v8_capi.h
 * <pre>typedef struct _cef_v8_accessor_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_v8_accessor_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:249</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public interface CefV8Accessor extends CefClientHandler {

    /**
     * Handle retrieval the accessor value identified by {@code name}. {@code object} is the receiver ('this' object) of the accessor. If retrieval succeeds set {@code retval} to the return value. If retrieval fails set {@code exception} to the exception that will be thrown. Return {@code true} if accessor retrieval was handled.
     * <p>Definition generated from cef_v8_capi.h
     * <pre>int (CEF_CALLBACK* get)(struct _cef_v8_accessor_t* self, const cef_string_t* name, struct _cef_v8_value_t* object, struct _cef_v8_value_t** retval, cef_string_t* exception);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:257</a>
     */
    default boolean get(@Nullable String name, @Nullable CefV8Value object, @Nullable AtomicReference<CefV8Value> retval, @Nullable String exception) {
        return false;
    }

    /**
     * Handle assignment of the accessor value identified by {@code name}. {@code object} is the receiver ('this' object) of the accessor. {@code value} is the new value being assigned to the accessor. If assignment fails set {@code exception} to the exception that will be thrown. Return {@code true} if accessor assignment was handled.
     * <p>Definition generated from cef_v8_capi.h
     * <pre>int (CEF_CALLBACK* set)(struct _cef_v8_accessor_t* self, const cef_string_t* name, struct _cef_v8_value_t* object, struct _cef_v8_value_t* value, cef_string_t* exception);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:270</a>
     */
    default boolean set(@Nullable String name, @Nullable CefV8Value object, @Nullable CefV8Value value, @Nullable String exception) {
        return false;
    }
    /**
     * Composite that fans callbacks out to every registered delegate. {@code void} methods invoke all
     * delegates in order; {@code boolean} methods short-circuit on the first {@code true}; handler-returning
     * {@code Optional}s collect every non-empty delegate and wrap them in the handler's own {@code Delegating}
     * wrapper; other {@code Optional}s pick the first non-empty; any other return type yields the first
     * delegate's value.
     */
    class Delegating implements CefV8Accessor {
        private final java.util.List<CefV8Accessor> delegates;

        public Delegating(java.util.List<CefV8Accessor> delegates) {
            this.delegates = java.util.List.copyOf(delegates);
        }

        @Override
        public boolean get(@Nullable String name, @Nullable CefV8Value object, @Nullable AtomicReference<CefV8Value> retval, @Nullable String exception) {
            for (CefV8Accessor d : delegates) {
                if (d.get(name, object, retval, exception)) return true;
            }
            if (!delegates.isEmpty()) return false;
            return false;
        }

        @Override
        public boolean set(@Nullable String name, @Nullable CefV8Value object, @Nullable CefV8Value value, @Nullable String exception) {
            for (CefV8Accessor d : delegates) {
                if (d.set(name, object, value, exception)) return true;
            }
            if (!delegates.isEmpty()) return false;
            return false;
        }
    }

}
