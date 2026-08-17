// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import java.nio.ByteBuffer;
import javax.annotation.Nonnull;

/**
 * Interface the client can implement to provide a custom stream writer. The methods of this class may be called on any thread.
 * <p>Definition generated from cef_stream_capi.h
 * <pre>typedef struct _cef_write_handler_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_write_handler_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__stream_8h.html">cef_stream.h:145</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public interface CefWriteHandler extends CefClientHandler {

    /**
     * Write raw binary data.
     * <p><b>The C API {@code void*} buffer parameter has been converted to {@link java.nio.ByteBuffer}; the hidden {@code size} parameter is derived from the buffer's capacity.</b>
     * <p><b>This follows the {@code fread}/{@code fwrite} convention where {@code n} is the element count and the buffer capacity is the element size.</b>
     * <p>Definition generated from cef_stream_capi.h
     * <pre>size_t (CEF_CALLBACK* write)(struct _cef_write_handler_t* self, const void* ptr, size_t size, size_t n);</pre>
     *
     * @param ptr <b>a direct {@link java.nio.ByteBuffer} whose capacity is the buffer size. This buffer is not reference-counted; its lifetime is not predictable beyond the scope of this callback. Storing a reference to it is unsafe unless explicitly permitted by the CEF documentation and may lead to native crashes.</b>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__stream_8h.html">cef_stream.h:152</a>
     */
    default long write(@Nonnull ByteBuffer ptr, long n) {
        return 0L;
    }

    /**
     * Seek to the specified offset position. {@code whence} may be any one of SEEK_CUR, SEEK_END or SEEK_SET. Return zero on success and non-zero on failure.
     * <p>Definition generated from cef_stream_capi.h
     * <pre>int (CEF_CALLBACK* seek)(struct _cef_write_handler_t* self, int64_t offset, int whence);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__stream_8h.html">cef_stream.h:158</a>
     */
    default int seek(long offset, int whence) {
        return 0;
    }

    /**
     * Return the current offset position.
     * <p>Definition generated from cef_stream_capi.h
     * <pre>int64_t (CEF_CALLBACK* tell)(struct _cef_write_handler_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__stream_8h.html">cef_stream.h:166</a>
     */
    default long tell() {
        return 0L;
    }

    /**
     * Flush the stream.
     * <p>Definition generated from cef_stream_capi.h
     * <pre>int (CEF_CALLBACK* flush)(struct _cef_write_handler_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__stream_8h.html">cef_stream.h:172</a>
     */
    default int flush() {
        return 0;
    }

    /**
     * Return {@code true} if this handler performs work like accessing the file system which may block. Used as a hint for determining the thread to access the handler from.
     * <p>Definition generated from cef_stream_capi.h
     * <pre>int (CEF_CALLBACK* may_block)(struct _cef_write_handler_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__stream_8h.html">cef_stream.h:178</a>
     */
    default boolean mayBlock() {
        return false;
    }
    /**
     * Composite that fans callbacks out to every registered delegate. {@code void} methods invoke all
     * delegates in order; {@code boolean} methods short-circuit on the first {@code true}; handler-returning
     * {@code Optional}s collect every non-empty delegate and wrap them in the handler's own {@code Delegating}
     * wrapper; other {@code Optional}s pick the first non-empty; any other return type yields the first
     * delegate's value.
     */
    class Delegating implements CefWriteHandler {
        private final java.util.List<CefWriteHandler> delegates;

        public Delegating(java.util.List<CefWriteHandler> delegates) {
            this.delegates = java.util.List.copyOf(delegates);
        }

        @Override
        public long write(@Nonnull ByteBuffer ptr, long n) {
            if (!delegates.isEmpty()) return delegates.get(0).write(ptr, n);
            return 0L;
        }

        @Override
        public int seek(long offset, int whence) {
            if (!delegates.isEmpty()) return delegates.get(0).seek(offset, whence);
            return 0;
        }

        @Override
        public long tell() {
            if (!delegates.isEmpty()) return delegates.get(0).tell();
            return 0L;
        }

        @Override
        public int flush() {
            if (!delegates.isEmpty()) return delegates.get(0).flush();
            return 0;
        }

        @Override
        public boolean mayBlock() {
            for (CefWriteHandler d : delegates) {
                if (d.mayBlock()) return true;
            }
            if (!delegates.isEmpty()) return false;
            return false;
        }
    }

}
