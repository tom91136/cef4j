// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;

/**
 * Generic callback interface used for asynchronous completion.
 * <p>Definition generated from cef_callback_capi.h
 * <pre>typedef struct _cef_completion_callback_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_completion_callback_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__callback_8h.html">cef_callback.h:62</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public interface CefCompletionCallback extends CefClientHandler {

    /**
     * Method that will be called once the task is complete.
     * <p>Definition generated from cef_callback_capi.h
     * <pre>void (CEF_CALLBACK* on_complete)(struct _cef_completion_callback_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__callback_8h.html">cef_callback.h:68</a>
     */
    default void onComplete() {
    }
    /**
     * Composite that fans callbacks out to every registered delegate. {@code void} methods invoke all
     * delegates in order; {@code boolean} methods short-circuit on the first {@code true}; handler-returning
     * {@code Optional}s collect every non-empty delegate and wrap them in the handler's own {@code Delegating}
     * wrapper; other {@code Optional}s pick the first non-empty; any other return type yields the first
     * delegate's value.
     */
    class Delegating implements CefCompletionCallback {
        private final java.util.List<CefCompletionCallback> delegates;

        public Delegating(java.util.List<CefCompletionCallback> delegates) {
            this.delegates = java.util.List.copyOf(delegates);
        }

        @Override
        public void onComplete() {
            for (CefCompletionCallback d : delegates) d.onComplete();
        }
    }

}
