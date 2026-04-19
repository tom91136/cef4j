// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import java.nio.ByteBuffer;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Implement this interface to filter resource response content. The methods of this class will be called on the browser process IO thread.
 * <p>Definition generated from cef_response_filter_capi.h
 * <pre>typedef struct _cef_response_filter_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_response_filter_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__response__filter_8h.html">cef_response_filter.h:43</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public interface CefResponseFilter extends CefClientHandler {

    /**
     * Initialize the response filter. Will only be called a single time. The filter will not be installed if this method returns {@code false}.
     * <p>Definition generated from cef_response_filter_capi.h
     * <pre>int (CEF_CALLBACK* init_filter)(struct _cef_response_filter_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__response__filter_8h.html">cef_response_filter.h:52</a>
     */
    default boolean initFilter() {
        return false;
    }

    /**
     * Called to filter a chunk of data. Expected usage is as follows:
     * <p>
     * 1. Read input data from {@code data_in} and set {@code data_in_read} to the number of bytes that were read up to a maximum of {@code data_in_size}. {@code data_in} will be {@code null} if {@code data_in_size} is zero. 2. Write filtered output data to {@code data_out} and set {@code data_out_written} to the number of bytes that were written up to a maximum of {@code data_out_size}. If no output data was written then all data must be read from {@code data_in} (user must set {@code data_in_read} = {@code data_in_size}). 3. Return RESPONSE_FILTER_DONE if all output data was written or RESPONSE_FILTER_NEED_MORE_DATA if output data is still pending.
     * <p>
     * This method will be called repeatedly until the input buffer has been fully read (user sets {@code data_in_read} = {@code data_in_size}) and there is no more input data to filter (the resource response is complete). This method may then be called an additional time with an empty input buffer if the user filled the output buffer (set {@code data_out_written} = {@code data_out_size}) and returned RESPONSE_FILTER_NEED_MORE_DATA to indicate that output data is still pending.
     * <p>
     * Calls to this method will stop when one of the following conditions is met:
     * <p>
     * 1. There is no more input data to filter (the resource response is complete) and the user sets {@code data_out_written} = 0 or returns RESPONSE_FILTER_DONE to indicate that all data has been written, or; 2. The user returns RESPONSE_FILTER_ERROR to indicate an error.
     * <p>
     * Do not keep a reference to the buffers passed to this method.
     * <p><b>The C API {@code void*} buffer parameter has been converted to {@link java.nio.ByteBuffer}; the hidden {@code data_in_size}, {@code data_out_size} parameter is derived from the buffer's capacity.</b>
     * <p>Definition generated from cef_response_filter_capi.h
     * <pre>cef_response_filter_status_t (CEF_CALLBACK* filter)(struct _cef_response_filter_t* self, void* data_in, size_t data_in_size, size_t* data_in_read, void* data_out, size_t data_out_size, size_t* data_out_written);</pre>
     *
     * @param dataIn may be null, <b>a direct {@link java.nio.ByteBuffer} whose capacity is the buffer size. This buffer is not reference-counted; its lifetime is not predictable beyond the scope of this callback. Storing a reference to it is unsafe unless explicitly permitted by the CEF documentation and may lead to native crashes.</b>
     * @param dataOut <b>a direct {@link java.nio.ByteBuffer} whose capacity is the buffer size. This buffer is not reference-counted; its lifetime is not predictable beyond the scope of this callback. Storing a reference to it is unsafe unless explicitly permitted by the CEF documentation and may lead to native crashes.</b>
     *
     * @return the result, or {@code RESPONSE_FILTER_ERROR} for default handling
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__response__filter_8h.html">cef_response_filter.h:59</a>
     */
    default @Nullable CefResponseFilterStatus filter(@Nullable ByteBuffer dataIn, long[] dataInRead, @Nonnull ByteBuffer dataOut, long[] dataOutWritten) {
        return CefResponseFilterStatus.of(net.kurobako.cef4j.gen.CefResponseFilterStatus.Kind.ERROR);
    }
    /**
     * Composite that fans callbacks out to every registered delegate. {@code void} methods invoke all
     * delegates in order; {@code boolean} methods short-circuit on the first {@code true}; handler-returning
     * {@code Optional}s collect every non-empty delegate and wrap them in the handler's own {@code Delegating}
     * wrapper; other {@code Optional}s pick the first non-empty; any other return type yields the first
     * delegate's value.
     */
    class Delegating implements CefResponseFilter {
        private final java.util.List<CefResponseFilter> delegates;

        public Delegating(java.util.List<CefResponseFilter> delegates) {
            this.delegates = java.util.List.copyOf(delegates);
        }

        @Override
        public boolean initFilter() {
            for (CefResponseFilter d : delegates) {
                if (d.initFilter()) return true;
            }
            if (!delegates.isEmpty()) return false;
            return false;
        }

        @Override
        public @Nullable CefResponseFilterStatus filter(@Nullable ByteBuffer dataIn, long[] dataInRead, @Nonnull ByteBuffer dataOut, long[] dataOutWritten) {
            if (!delegates.isEmpty()) return delegates.get(0).filter(dataIn, dataInRead, dataOut, dataOutWritten);
            return CefResponseFilterStatus.of(net.kurobako.cef4j.gen.CefResponseFilterStatus.Kind.ERROR);
        }
    }

}
