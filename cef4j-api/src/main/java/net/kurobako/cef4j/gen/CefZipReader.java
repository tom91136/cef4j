// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.nio.ByteBuffer;
import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

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
    boolean moveToFile(@Nonnull String filename, boolean casesensitive);

    /**
     * Close the document. This should be called directly to ensure that cleanup occurs on the correct thread.
     *
     * <p>Definition generated from cef_zip_reader_capi.h
     *
     * <pre>int (CEF_CALLBACK* close)(struct _cef_zip_reader_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__xml__reader_8h.html">cef_xml_reader.h:72</a>
     */
    boolean cefClose();

    /**
     * Return the name of the file being dragged out of the browser window.
     *
     * <p>Definition generated from cef_zip_reader_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_file_name)(struct _cef_zip_reader_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__drag__data_8h.html">cef_drag_data.h:127</a>
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
     * Read uncompressed file contents into the specified buffer. Returns < 0 if an error occurred, 0 if at the end of
     * file, or the number of bytes read.
     *
     * <p>Definition generated from cef_zip_reader_capi.h
     *
     * <pre>int (CEF_CALLBACK* read_file)(struct _cef_zip_reader_t* self, void* buffer, size_t bufferSize);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__zip__reader_8h.html">cef_zip_reader.h:120</a>
     */
    int readFile(@Nonnull ByteBuffer buffer);

    /**
     * Return the current offset position.
     *
     * <p>Definition generated from cef_zip_reader_capi.h
     *
     * <pre>int64_t (CEF_CALLBACK* tell)(struct _cef_zip_reader_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__stream_8h.html">cef_stream.h:220</a>
     */
    long tell();

    /**
     * Return non-zero if at end of file.
     *
     * <p>Definition generated from cef_zip_reader_capi.h
     *
     * <pre>int (CEF_CALLBACK* eof)(struct _cef_zip_reader_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__stream_8h.html">cef_stream.h:130</a>
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
    static Optional<CefZipReader> create(@Nonnull CefStreamReader stream) {
        return Optional.ofNullable(NativePeer.N_Create(stream));
    }

    final class NativePeer implements CefZipReader, AutoCloseable {
        private final long nativePtr;
        private final java.lang.ref.Cleaner.Cleanable cleanable;

        NativePeer(long ptr) {
            this.nativePtr = ptr;
            this.cleanable = net.kurobako.cef4j.NativeCleaner.INSTANCE.register(this, new Release(ptr));
        }

        @Override
        public void close() {
            cleanable.clean();
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
                N_Release(ptr);
            }
        }

        private static native void N_Release(long ptr);

        @Override
        public boolean moveToFirstFile() {
            return N_MoveToFirstFile(nativePtr);
        }

        @Override
        public boolean moveToNextFile() {
            return N_MoveToNextFile(nativePtr);
        }

        @Override
        public boolean moveToFile(@Nonnull String filename, boolean casesensitive) {
            return N_MoveToFile(nativePtr, filename, casesensitive);
        }

        @Override
        public boolean cefClose() {
            return N_Close(nativePtr);
        }

        @Override
        public Optional<String> getFileName() {
            return Optional.ofNullable(N_GetFileName(nativePtr));
        }

        @Override
        public long getFileSize() {
            return N_GetFileSize(nativePtr);
        }

        @Override
        public CefBasetime getFileLastModified() {
            return N_GetFileLastModified(nativePtr);
        }

        @Override
        public boolean openFile(@Nullable String password) {
            return N_OpenFile(nativePtr, password);
        }

        @Override
        public boolean closeFile() {
            return N_CloseFile(nativePtr);
        }

        @Override
        public int readFile(@Nonnull ByteBuffer buffer) {
            return N_ReadFile(nativePtr, buffer);
        }

        @Override
        public long tell() {
            return N_Tell(nativePtr);
        }

        @Override
        public int eof() {
            return N_Eof(nativePtr);
        }

        private static native boolean N_MoveToFirstFile(long self);

        private static native boolean N_MoveToNextFile(long self);

        private static native boolean N_MoveToFile(long self, String filename, boolean casesensitive);

        private static native boolean N_Close(long self);

        private static native String N_GetFileName(long self);

        private static native long N_GetFileSize(long self);

        private static native CefBasetime N_GetFileLastModified(long self);

        private static native boolean N_OpenFile(long self, String password);

        private static native boolean N_CloseFile(long self);

        private static native int N_ReadFile(long self, ByteBuffer buffer);

        private static native long N_Tell(long self);

        private static native int N_Eof(long self);

        static native CefZipReader N_Create(CefStreamReader stream);

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
