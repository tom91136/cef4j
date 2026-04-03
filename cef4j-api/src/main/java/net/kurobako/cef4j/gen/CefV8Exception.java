// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
// -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import java.util.Optional;
import javax.annotation.processing.Generated;

/**
 * Class representing a V8 exception. The methods of this class may be called on any render process thread.
 *
 * <p>Definition generated from cef_v8_capi.h
 *
 * <pre>typedef struct _cef_v8_exception_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_v8_exception_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:352</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
public interface CefV8Exception extends CefLibraryObject {

    /**
     * Returns the exception message.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_message)(struct _cef_v8_exception_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:359</a>
     */
    Optional<String> getMessage();

    /**
     * Returns the line of source code that the exception occurred within.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_source_line)(struct _cef_v8_exception_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:365</a>
     */
    Optional<String> getSourceLine();

    /**
     * Returns the resource name for the script from where the function causing the error originates.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_script_resource_name)(struct _cef_v8_exception_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:371</a>
     */
    Optional<String> getScriptResourceName();

    /**
     * Returns the 1-based number of the line where the error occurred or 0 if the line number is unknown.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>int (CEF_CALLBACK* get_line_number)(struct _cef_v8_exception_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:378</a>
     */
    int getLineNumber();

    /**
     * Returns the index within the script of the first character where the error occurred.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>int (CEF_CALLBACK* get_start_position)(struct _cef_v8_exception_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:385</a>
     */
    int getStartPosition();

    /**
     * Returns the index within the script of the last character where the error occurred.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>int (CEF_CALLBACK* get_end_position)(struct _cef_v8_exception_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:392</a>
     */
    int getEndPosition();

    /**
     * Returns the index within the line of the first character where the error occurred.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>int (CEF_CALLBACK* get_start_column)(struct _cef_v8_exception_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:399</a>
     */
    int getStartColumn();

    /**
     * Returns the index within the line of the last character where the error occurred.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>int (CEF_CALLBACK* get_end_column)(struct _cef_v8_exception_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:406</a>
     */
    int getEndColumn();

    final class NativePeer implements CefV8Exception, AutoCloseable {
        private final long nativePtr;
        private final java.lang.ref.Cleaner.Cleanable cleanable;
        private volatile boolean closed;

        NativePeer(long ptr) {
            this.nativePtr = ptr;
            this.cleanable = net.kurobako.cef4j.NativeCleaner.INSTANCE.register(this, new Release(ptr));
        }

        @Override
        public void peerClose() {
            closed = true;
            cleanable.clean();
        }

        @Override
        public boolean peerIsClosed() {
            return closed;
        }

        private void checkNotClosed() {
            if (closed) throw new IllegalStateException("CefV8Exception has been closed");
        }

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefV8Exception.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefV8Exception 0x{}", Long.toHexString(ptr));
                release0(ptr);
            }
        }

        private static native void release0(long ptr);

        @Override
        public Optional<String> getMessage() {
            checkNotClosed();
            return Optional.ofNullable(getMessage0(nativePtr));
        }

        @Override
        public Optional<String> getSourceLine() {
            checkNotClosed();
            return Optional.ofNullable(getSourceLine0(nativePtr));
        }

        @Override
        public Optional<String> getScriptResourceName() {
            checkNotClosed();
            return Optional.ofNullable(getScriptResourceName0(nativePtr));
        }

        @Override
        public int getLineNumber() {
            checkNotClosed();
            return getLineNumber0(nativePtr);
        }

        @Override
        public int getStartPosition() {
            checkNotClosed();
            return getStartPosition0(nativePtr);
        }

        @Override
        public int getEndPosition() {
            checkNotClosed();
            return getEndPosition0(nativePtr);
        }

        @Override
        public int getStartColumn() {
            checkNotClosed();
            return getStartColumn0(nativePtr);
        }

        @Override
        public int getEndColumn() {
            checkNotClosed();
            return getEndColumn0(nativePtr);
        }

        private static native String getMessage0(long self);

        private static native String getSourceLine0(long self);

        private static native String getScriptResourceName0(long self);

        private static native int getLineNumber0(long self);

        private static native int getStartPosition0(long self);

        private static native int getEndPosition0(long self);

        private static native int getStartColumn0(long self);

        private static native int getEndColumn0(long self);

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
            return "CefV8Exception{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
