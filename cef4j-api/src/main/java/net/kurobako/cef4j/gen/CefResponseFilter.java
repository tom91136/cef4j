// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Implement this interface to filter resource response content. The methods of this class will be called on the browser
 * process IO thread.
 */
public interface CefResponseFilter {

    /**
     * Initialize the response filter. Will only be called a single time. The filter will not be installed if this
     * method returns false.
     */
    default boolean initFilter() {
        return false;
    }

    /**
     * Called to filter a chunk of data. Expected usage is as follows: 1. Read input data from |data_in| and set
     * |data_in_read| to the number of bytes that were read up to a maximum of |data_in_size|. |data_in| will be NULL if
     * |data_in_size| is zero. 2. Write filtered output data to |data_out| and set |data_out_written| to the number of
     * bytes that were written up to a maximum of |data_out_size|. If no output data was written then all data must be
     * read from |data_in| (user must set |data_in_read| = |data_in_size|). 3. Return RESPONSE_FILTER_DONE if all output
     * data was written or RESPONSE_FILTER_NEED_MORE_DATA if output data is still pending. This method will be called
     * repeatedly until the input buffer has been fully read (user sets |data_in_read| = |data_in_size|) and there is no
     * more input data to filter (the resource response is complete). This method may then be called an additional time
     * with an empty input buffer if the user filled the output buffer (set |data_out_written| = |data_out_size|) and
     * returned RESPONSE_FILTER_NEED_MORE_DATA to indicate that output data is still pending. Calls to this method will
     * stop when one of the following conditions is met: 1. There is no more input data to filter (the resource response
     * is complete) and the user sets |data_out_written| = 0 or returns RESPONSE_FILTER_DONE to indicate that all data
     * has been written, or; 2. The user returns RESPONSE_FILTER_ERROR to indicate an error. Do not keep a reference to
     * the buffers passed to this method.
     *
     * @param dataIn may be null
     * @return the result, or {@code RESPONSE_FILTER_ERROR} for default handling
     */
    default CefResponseFilterStatus filter(
            long dataIn, long dataInSize, long dataInRead, long dataOut, long dataOutSize, long dataOutWritten) {
        return CefResponseFilterStatus.RESPONSE_FILTER_ERROR;
    }
}
