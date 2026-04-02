// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.nio.ByteBuffer;
import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Class used to read data from a stream. The methods of this class may be called on any thread.
 *
 * <p>Definition generated from cef_stream_capi.h
 *
 * <pre>typedef struct _cef_stream_reader_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_stream_reader_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__stream_8h.html">cef_stream.h:84</a>
 */
public interface CefStreamReader extends CefLibraryObject {

    /**
     * Read raw binary data.
     *
     * <p><b>The C API {@code void*} buffer parameter has been converted to {@link java.nio.ByteBuffer}; the hidden
     * {@code size} parameter is derived from the buffer's capacity.</b>
     *
     * <p><b>This follows the {@code fread}/{@code fwrite} convention where {@code n} is the element count and the
     * buffer capacity is the element size.</b>
     *
     * <p>Definition generated from cef_stream_capi.h
     *
     * <pre>size_t (CEF_CALLBACK* read)(struct _cef_stream_reader_t* self, void* ptr, size_t size, size_t n);</pre>
     *
     * @param ptr <b>a direct {@link java.nio.ByteBuffer} whose capacity is the buffer size. This buffer is not
     *     reference-counted; its lifetime is not predictable beyond the scope of this callback. Storing a reference to
     *     it is unsafe unless explicitly permitted by the CEF documentation and may lead to native crashes.</b>
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__stream_8h.html">cef_stream.h:110</a>
     */
    long read(@Nonnull ByteBuffer ptr, long n);

    /**
     * Seek to the specified offset position. {@code whence} may be any one of SEEK_CUR, SEEK_END or SEEK_SET. Returns
     * zero on success and non-zero on failure.
     *
     * <p>Definition generated from cef_stream_capi.h
     *
     * <pre>int (CEF_CALLBACK* seek)(struct _cef_stream_reader_t* self, int64_t offset, int whence);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__stream_8h.html">cef_stream.h:116</a>
     */
    int seek(long offset, int whence);

    /**
     * Return the current offset position.
     *
     * <p>Definition generated from cef_stream_capi.h
     *
     * <pre>int64_t (CEF_CALLBACK* tell)(struct _cef_stream_reader_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__stream_8h.html">cef_stream.h:124</a>
     */
    long tell();

    /**
     * Return non-zero if at end of file.
     *
     * <p>Definition generated from cef_stream_capi.h
     *
     * <pre>int (CEF_CALLBACK* eof)(struct _cef_stream_reader_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__stream_8h.html">cef_stream.h:130</a>
     */
    int eof();

    /**
     * Returns {@code true} if this reader performs work like accessing the file system which may block. Used as a hint
     * for determining the thread to access the reader from.
     *
     * <p>Definition generated from cef_stream_capi.h
     *
     * <pre>int (CEF_CALLBACK* may_block)(struct _cef_stream_reader_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__stream_8h.html">cef_stream.h:136</a>
     */
    boolean mayBlock();
    /**
     * Create a new CefStreamWriter object for a file.
     *
     * <p>Definition generated from cef_stream_capi.h
     *
     * <pre>CEF_EXPORT cef_stream_reader_t* cef_stream_reader_create_for_file(const cef_string_t* fileName);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__stream_8h.html">cef_stream.h:194</a>
     */
    static Optional<CefStreamReader> createForFile(@Nullable String fileName) {
        return Optional.ofNullable(NativePeer.N_CreateForFile(fileName));
    }

    /**
     * Create a new CefStreamReader object from data.
     *
     * <p>Definition generated from cef_stream_capi.h
     *
     * <pre>CEF_EXPORT cef_stream_reader_t* cef_stream_reader_create_for_data(void* data, size_t size);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__stream_8h.html">cef_stream.h:97</a>
     */
    static Optional<CefStreamReader> createForData(@Nonnull ByteBuffer data) {
        return Optional.ofNullable(NativePeer.N_CreateForData(data));
    }

    /**
     * Create a new CefStreamWriter object for a custom handler.
     *
     * <p>Definition generated from cef_stream_capi.h
     *
     * <pre>CEF_EXPORT cef_stream_reader_t* cef_stream_reader_create_for_handler(cef_read_handler_t* handler);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__stream_8h.html">cef_stream.h:199</a>
     */
    static Optional<CefStreamReader> createForHandler(@Nullable CefReadHandler handler) {
        return Optional.ofNullable(NativePeer.N_CreateForHandler(handler));
    }

    final class NativePeer implements CefStreamReader, AutoCloseable {
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
            if (closed) throw new IllegalStateException("CefStreamReader has been closed");
        }

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefStreamReader.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefStreamReader 0x{}", Long.toHexString(ptr));
                N_Release(ptr);
            }
        }

        private static native void N_Release(long ptr);

        @Override
        public long read(@Nonnull ByteBuffer ptr, long n) {
            checkNotClosed();
            return N_Read(nativePtr, ptr, n);
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
        public int eof() {
            checkNotClosed();
            return N_Eof(nativePtr);
        }

        @Override
        public boolean mayBlock() {
            checkNotClosed();
            return N_MayBlock(nativePtr);
        }

        private static native long N_Read(long self, ByteBuffer ptr, long n);

        private static native int N_Seek(long self, long offset, int whence);

        private static native long N_Tell(long self);

        private static native int N_Eof(long self);

        private static native boolean N_MayBlock(long self);

        static native CefStreamReader N_CreateForFile(String fileName);

        static native CefStreamReader N_CreateForData(ByteBuffer data);

        static native CefStreamReader N_CreateForHandler(CefReadHandler handler);

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
            return "CefStreamReader{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
