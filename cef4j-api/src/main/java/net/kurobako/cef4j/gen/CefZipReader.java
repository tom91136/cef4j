// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
// -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import java.nio.ByteBuffer;
import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.processing.Generated;

/**
 * Class that supports the reading of zip archives via the zlib unzip API. The methods of this class should only be
 * called on the thread that creates the object.
 *
 * <p>Definition generated from cef_zip_reader_capi.h
 *
 * <pre>typedef struct _cef_zip_reader_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_zip_reader_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__zip__reader_8h.html">cef_zip_reader.h:43</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
public interface CefZipReader extends CefLibraryObject {

    /**
     * Moves the cursor to the first file in the archive. Returns {@code true} if the cursor position was set
     * successfully.
     *
     * <p>Definition generated from cef_zip_reader_capi.h
     *
     * <pre>int (CEF_CALLBACK* move_to_first_file)(struct _cef_zip_reader_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__zip__reader_8h.html">cef_zip_reader.h:58</a>
     */
    boolean moveToFirstFile();

    /**
     * Moves the cursor to the next file in the archive. Returns {@code true} if the cursor position was set
     * successfully.
     *
     * <p>Definition generated from cef_zip_reader_capi.h
     *
     * <pre>int (CEF_CALLBACK* move_to_next_file)(struct _cef_zip_reader_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__zip__reader_8h.html">cef_zip_reader.h:65</a>
     */
    boolean moveToNextFile();

    /**
     * Moves the cursor to the specified file in the archive. If {@code caseSensitive} is {@code true} then the search
     * will be case sensitive. Returns {@code true} if the cursor position was set successfully.
     *
     * <p>Definition generated from cef_zip_reader_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* move_to_file)(struct _cef_zip_reader_t* self, const cef_string_t* fileName, int caseSensitive);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__zip__reader_8h.html">cef_zip_reader.h:72</a>
     */
    boolean moveToFile(@Nullable String fileName, boolean caseSensitive);

    /**
     * Closes the archive. This should be called directly to ensure that cleanup occurs on the correct thread.
     *
     * <p>Definition generated from cef_zip_reader_capi.h
     *
     * <pre>int (CEF_CALLBACK* close)(struct _cef_zip_reader_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__zip__reader_8h.html">cef_zip_reader.h:80</a>
     */
    boolean cefClose();

    /**
     * Returns the name of the file.
     *
     * <p>Definition generated from cef_zip_reader_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_file_name)(struct _cef_zip_reader_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__zip__reader_8h.html">cef_zip_reader.h:89</a>
     */
    Optional<String> getFileName();

    /**
     * Returns the uncompressed size of the file.
     *
     * <p>Definition generated from cef_zip_reader_capi.h
     *
     * <pre>int64_t (CEF_CALLBACK* get_file_size)(struct _cef_zip_reader_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__zip__reader_8h.html">cef_zip_reader.h:95</a>
     */
    long getFileSize();

    /**
     * Returns the last modified timestamp for the file.
     *
     * <p>Definition generated from cef_zip_reader_capi.h
     *
     * <pre>cef_basetime_t* (CEF_CALLBACK* get_file_last_modified)(struct _cef_zip_reader_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__zip__reader_8h.html">cef_zip_reader.h:101</a>
     */
    CefBasetime getFileLastModified();

    /**
     * Opens the file for reading of uncompressed data. A read password may optionally be specified.
     *
     * <p>Definition generated from cef_zip_reader_capi.h
     *
     * <pre>int (CEF_CALLBACK* open_file)(struct _cef_zip_reader_t* self, const cef_string_t* password);</pre>
     *
     * @param password may be null
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__zip__reader_8h.html">cef_zip_reader.h:107</a>
     */
    boolean openFile(@Nullable String password);

    /**
     * Closes the file.
     *
     * <p>Definition generated from cef_zip_reader_capi.h
     *
     * <pre>int (CEF_CALLBACK* close_file)(struct _cef_zip_reader_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__zip__reader_8h.html">cef_zip_reader.h:114</a>
     */
    boolean closeFile();

    /**
     * Read uncompressed file contents into the specified buffer. Returns &lt; 0 if an error occurred, 0 if at the end
     * of file, or the number of bytes read.
     *
     * <p><b>The C API {@code void*} buffer parameter has been converted to {@link java.nio.ByteBuffer}; the hidden
     * {@code bufferSize} parameter is derived from the buffer's capacity.</b>
     *
     * <p>Definition generated from cef_zip_reader_capi.h
     *
     * <pre>int (CEF_CALLBACK* read_file)(struct _cef_zip_reader_t* self, void* buffer, size_t bufferSize);</pre>
     *
     * @param buffer <b>a direct {@link java.nio.ByteBuffer} whose capacity is the buffer size. This buffer is not
     *     reference-counted; its lifetime is not predictable beyond the scope of this callback. Storing a reference to
     *     it is unsafe unless explicitly permitted by the CEF documentation and may lead to native crashes.</b>
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__zip__reader_8h.html">cef_zip_reader.h:120</a>
     */
    int readFile(@Nonnull ByteBuffer buffer);

    /**
     * Returns the current offset in the uncompressed file contents.
     *
     * <p>Definition generated from cef_zip_reader_capi.h
     *
     * <pre>int64_t (CEF_CALLBACK* tell)(struct _cef_zip_reader_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__zip__reader_8h.html">cef_zip_reader.h:127</a>
     */
    long tell();

    /**
     * Returns {@code true} if at end of the file contents.
     *
     * <p>Definition generated from cef_zip_reader_capi.h
     *
     * <pre>int (CEF_CALLBACK* eof)(struct _cef_zip_reader_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__zip__reader_8h.html">cef_zip_reader.h:133</a>
     */
    int eof();
    /**
     * Create a new backing store with allocated memory of {@code byte_length} bytes. The memory is uninitialized. This
     * method must be called on a thread with a valid V8 isolate. The returned object can safely be passed to other
     * threads. Returns {@code null} on failure.
     *
     * <p>Definition generated from cef_zip_reader_capi.h
     *
     * <pre>CEF_EXPORT cef_zip_reader_t* cef_zip_reader_create(struct _cef_stream_reader_t* stream);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:445</a>
     */
    static Optional<CefZipReader> create(@Nullable CefStreamReader stream) {
        return Optional.ofNullable(NativePeer.create0(stream));
    }

    final class NativePeer implements CefZipReader, AutoCloseable {
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
            if (closed) throw new IllegalStateException("CefZipReader has been closed");
        }

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefZipReader.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefZipReader 0x{}", Long.toHexString(ptr));
                release0(ptr);
            }
        }

        private static native void release0(long ptr);

        @Override
        public boolean moveToFirstFile() {
            checkNotClosed();
            return moveToFirstFile0(nativePtr);
        }

        @Override
        public boolean moveToNextFile() {
            checkNotClosed();
            return moveToNextFile0(nativePtr);
        }

        @Override
        public boolean moveToFile(@Nullable String fileName, boolean caseSensitive) {
            checkNotClosed();
            return moveToFile0(nativePtr, fileName, caseSensitive);
        }

        @Override
        public boolean cefClose() {
            checkNotClosed();
            return cefClose0(nativePtr);
        }

        @Override
        public Optional<String> getFileName() {
            checkNotClosed();
            return Optional.ofNullable(getFileName0(nativePtr));
        }

        @Override
        public long getFileSize() {
            checkNotClosed();
            return getFileSize0(nativePtr);
        }

        @Override
        public CefBasetime getFileLastModified() {
            checkNotClosed();
            return getFileLastModified0(nativePtr);
        }

        @Override
        public boolean openFile(@Nullable String password) {
            checkNotClosed();
            return openFile0(nativePtr, password);
        }

        @Override
        public boolean closeFile() {
            checkNotClosed();
            return closeFile0(nativePtr);
        }

        @Override
        public int readFile(@Nonnull ByteBuffer buffer) {
            checkNotClosed();
            return readFile0(nativePtr, buffer);
        }

        @Override
        public long tell() {
            checkNotClosed();
            return tell0(nativePtr);
        }

        @Override
        public int eof() {
            checkNotClosed();
            return eof0(nativePtr);
        }

        private static native boolean moveToFirstFile0(long self);

        private static native boolean moveToNextFile0(long self);

        private static native boolean moveToFile0(long self, String fileName, boolean caseSensitive);

        private static native boolean cefClose0(long self);

        private static native String getFileName0(long self);

        private static native long getFileSize0(long self);

        private static native CefBasetime getFileLastModified0(long self);

        private static native boolean openFile0(long self, String password);

        private static native boolean closeFile0(long self);

        private static native int readFile0(long self, ByteBuffer buffer);

        private static native long tell0(long self);

        private static native int eof0(long self);

        static native CefZipReader create0(CefStreamReader stream);

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
            return "CefZipReader{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
