// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import javax.annotation.Nonnull;

/**
 * Interface the client can implement to provide a custom stream reader. The methods of this class may be called on any
 * thread.
 *
 * <p>Definition generated from cef_stream_capi.h
 *
 * <pre>typedef struct _cef_read_handler_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_read_handler_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__stream_8h.html">cef_stream.h:42</a>
 */
public interface CefReadHandler extends CefClientHandler {

    /**
     * Read raw binary data.
     *
     * <p>Definition generated from cef_stream_capi.h
     *
     * <pre>size_t (CEF_CALLBACK* read)(struct _cef_read_handler_t* self, void* ptr, size_t size, size_t n);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__stream_8h.html">cef_stream.h:110</a>
     */
    default long read(@Nonnull NativePointer ptr, long size, long n) {
        return 0L;
    }

    /**
     * Seek to the specified offset position. {@code whence} may be any one of SEEK_CUR, SEEK_END or SEEK_SET. Returns
     * zero on success and non-zero on failure.
     *
     * <p>Definition generated from cef_stream_capi.h
     *
     * <pre>int (CEF_CALLBACK* seek)(struct _cef_read_handler_t* self, int64_t offset, int whence);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__stream_8h.html">cef_stream.h:212</a>
     */
    default int seek(long offset, int whence) {
        return 0;
    }

    /**
     * Return the current offset position.
     *
     * <p>Definition generated from cef_stream_capi.h
     *
     * <pre>int64_t (CEF_CALLBACK* tell)(struct _cef_read_handler_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__stream_8h.html">cef_stream.h:220</a>
     */
    default long tell() {
        return 0L;
    }

    /**
     * Return non-zero if at end of file.
     *
     * <p>Definition generated from cef_stream_capi.h
     *
     * <pre>int (CEF_CALLBACK* eof)(struct _cef_read_handler_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__stream_8h.html">cef_stream.h:130</a>
     */
    default int eof() {
        return 0;
    }

    /**
     * Returns {@code true} if this writer performs work like accessing the file system which may block. Used as a hint
     * for determining the thread to access the writer from.
     *
     * <p>Definition generated from cef_stream_capi.h
     *
     * <pre>int (CEF_CALLBACK* may_block)(struct _cef_read_handler_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__stream_8h.html">cef_stream.h:232</a>
     */
    default boolean mayBlock() {
        return false;
    }
}
