// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import javax.annotation.Nonnull;

/**
 * Implement this interface to receive notification when tracing has completed. The methods of this class will be called
 * on the browser process UI thread.
 *
 * <p>Definition generated from cef_trace_capi.h
 *
 * <pre>typedef struct _cef_end_tracing_callback_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_end_tracing_callback_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__trace_8h.html">cef_trace.h:47</a>
 */
public interface CefEndTracingCallback extends CefClientHandler {

    /**
     * Called after all processes have sent their trace data. {@code tracing_file} is the path at which tracing data was
     * written. The client is responsible for deleting {@code tracing_file}.
     *
     * <p>Definition generated from cef_trace_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* on_end_tracing_complete)(struct _cef_end_tracing_callback_t* self, const cef_string_t* tracing_file);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__trace_8h.html">cef_trace.h:54</a>
     */
    default void onEndTracingComplete(@Nonnull String tracingFile) {}
}
