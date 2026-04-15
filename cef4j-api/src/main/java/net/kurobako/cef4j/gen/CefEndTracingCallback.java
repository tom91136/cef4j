// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import javax.annotation.Nullable;

/**
 * Implement this interface to receive notification when tracing has completed. The methods of this class will be called on the browser process UI thread.
 * <p>Definition generated from cef_trace_capi.h
 * <pre>typedef struct _cef_end_tracing_callback_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_end_tracing_callback_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__trace_8h.html">cef_trace.h:47</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public interface CefEndTracingCallback extends CefClientHandler {

    /**
     * Called after all processes have sent their trace data. {@code tracing_file} is the path at which tracing data was written. The client is responsible for deleting {@code tracing_file}.
     * <p>Definition generated from cef_trace_capi.h
     * <pre>void (CEF_CALLBACK* on_end_tracing_complete)(struct _cef_end_tracing_callback_t* self, const cef_string_t* tracing_file);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__trace_8h.html">cef_trace.h:54</a>
     */
    default void onEndTracingComplete(@Nullable String tracingFile) {
    }
    /**
     * Composite that fans callbacks out to every registered delegate. {@code void} methods invoke all
     * delegates in order; {@code boolean} methods short-circuit on the first {@code true}; handler-returning
     * {@code Optional}s collect every non-empty delegate and wrap them in the handler's own {@code Delegating}
     * wrapper; other {@code Optional}s pick the first non-empty; any other return type yields the first
     * delegate's value.
     */
    class Delegating implements CefEndTracingCallback {
        private final java.util.List<CefEndTracingCallback> delegates;

        public Delegating(java.util.List<CefEndTracingCallback> delegates) {
            this.delegates = java.util.List.copyOf(delegates);
        }

        @Override
        public void onEndTracingComplete(@Nullable String tracingFile) {
            for (CefEndTracingCallback d : delegates) d.onEndTracingComplete(tracingFile);
        }
    }

}
