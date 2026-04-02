// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.nio.ByteBuffer;
import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Class used to write data to a stream. The methods of this class may be called on any thread.
 *
 * <p>Definition generated from cef_stream_capi.h
 *
 * <pre>typedef struct _cef_stream_writer_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_stream_writer_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__stream_8h.html">cef_stream.h:187</a>
 */
public interface CefStreamWriter extends CefLibraryObject {

    /**
     * Write raw binary data.
     *
     * <p><b>The C API {@code void*} buffer parameter has been converted to {@link java.nio.ByteBuffer}; the hidden
     * {@code size} parameter is derived from the buffer's capacity.</b>
     *
     * <p><b>This follows the {@code fread}/{@code fwrite} convention where {@code n} is the element count and the
     * buffer capacity is the element size.</b>
     *
     * <p>Definition generated from cef_stream_capi.h
     *
     * <pre>size_t (CEF_CALLBACK* write)(struct _cef_stream_writer_t* self, const void* ptr, size_t size, size_t n);
     * </pre>
     *
     * @param ptr <b>a direct {@link java.nio.ByteBuffer} whose capacity is the buffer size. This buffer is not
     *     reference-counted; its lifetime is not predictable beyond the scope of this callback. Storing a reference to
     *     it is unsafe unless explicitly permitted by the CEF documentation and may lead to native crashes.</b>
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__stream_8h.html">cef_stream.h:206</a>
     */
    long write(@Nonnull ByteBuffer ptr, long n);

    /**
     * Seek to the specified offset position. {@code whence} may be any one of SEEK_CUR, SEEK_END or SEEK_SET. Returns
     * zero on success and non-zero on failure.
     *
     * <p>Definition generated from cef_stream_capi.h
     *
     * <pre>int (CEF_CALLBACK* seek)(struct _cef_stream_writer_t* self, int64_t offset, int whence);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__stream_8h.html">cef_stream.h:212</a>
     */
    int seek(long offset, int whence);

    /**
     * Return the current offset position.
     *
     * <p>Definition generated from cef_stream_capi.h
     *
     * <pre>int64_t (CEF_CALLBACK* tell)(struct _cef_stream_writer_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__stream_8h.html">cef_stream.h:220</a>
     */
    long tell();

    /**
     * Flush the stream.
     *
     * <p>Definition generated from cef_stream_capi.h
     *
     * <pre>int (CEF_CALLBACK* flush)(struct _cef_stream_writer_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__stream_8h.html">cef_stream.h:226</a>
     */
    int flush();

    /**
     * Returns {@code true} if this writer performs work like accessing the file system which may block. Used as a hint
     * for determining the thread to access the writer from.
     *
     * <p>Definition generated from cef_stream_capi.h
     *
     * <pre>int (CEF_CALLBACK* may_block)(struct _cef_stream_writer_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__stream_8h.html">cef_stream.h:232</a>
     */
    boolean mayBlock();
    /**
     * Create a new CefStreamWriter object for a file.
     *
     * <p>Definition generated from cef_stream_capi.h
     *
     * <pre>CEF_EXPORT cef_stream_writer_t* cef_stream_writer_create_for_file(const cef_string_t* fileName);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__stream_8h.html">cef_stream.h:194</a>
     */
    static Optional<CefStreamWriter> createForFile(@Nullable String fileName) {
        return Optional.ofNullable(NativePeer.N_CreateForFile(fileName));
    }

    /**
     * Create a new CefStreamWriter object for a custom handler.
     *
     * <p>Definition generated from cef_stream_capi.h
     *
     * <pre>CEF_EXPORT cef_stream_writer_t* cef_stream_writer_create_for_handler(cef_write_handler_t* handler);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__stream_8h.html">cef_stream.h:199</a>
     */
    static Optional<CefStreamWriter> createForHandler(@Nullable CefWriteHandler handler) {
        return Optional.ofNullable(NativePeer.N_CreateForHandler(handler));
    }

    final class NativePeer implements CefStreamWriter, AutoCloseable {
        private final long nativePtr;
        private final java.lang.ref.Cleaner.Cleanable cleanable;
        private volatile boolean closed;

        NativePeer(long ptr) {
            this.nativePtr = ptr;
            this.cleanable = net.kurobako.cef4j.NativeCleaner.INSTANCE.register(this, new Release(ptr));
        }

        @Override
        public void close() {
            closed = true;
            cleanable.clean();
        }

        @Override
        public boolean isClosed() {
            return closed;
        }

        private void checkNotClosed() {
            if (closed) throw new IllegalStateException("CefStreamWriter has been closed");
        }

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefStreamWriter.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefStreamWriter 0x{}", Long.toHexString(ptr));
                N_Release(ptr);
            }
        }

        private static native void N_Release(long ptr);

        @Override
        public long write(@Nonnull ByteBuffer ptr, long n) {
            checkNotClosed();
            return N_Write(nativePtr, ptr, n);
        }

        @Override
        public int seek(long offset, int whence) {
            checkNotClosed();
            return N_Seek(nativePtr, offset, whence);
        }

        @Override
        public long tell() {
            checkNotClosed();
            return N_Tell(nativePtr);
        }

        @Override
        public int flush() {
            checkNotClosed();
            return N_Flush(nativePtr);
        }

        @Override
        public boolean mayBlock() {
            checkNotClosed();
            return N_MayBlock(nativePtr);
        }

        private static native long N_Write(long self, ByteBuffer ptr, long n);

        private static native int N_Seek(long self, long offset, int whence);

        private static native long N_Tell(long self);

        private static native int N_Flush(long self);

        private static native boolean N_MayBlock(long self);

        static native CefStreamWriter N_CreateForFile(String fileName);

        static native CefStreamWriter N_CreateForHandler(CefWriteHandler handler);

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof NativePeer)) return false;
            return this.nativePtr == ((NativePeer) obj).nativePtr;
        }

        @Override
        public int hashCode() {
            return Long.hashCode(nativePtr);
        }

        @Override
        public String toString() {
            return "CefStreamWriter{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
