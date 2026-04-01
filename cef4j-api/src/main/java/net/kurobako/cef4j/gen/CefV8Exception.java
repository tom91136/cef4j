// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.util.Optional;

/**
 * Structure representing a V8 exception. The functions of this structure may be called on any render process thread.
 * NOTE: This struct is allocated DLL-side.
 *
 * <p>Definition generated from cef_v8_capi.h
 *
 * <pre>typedef struct _cef_v8_exception_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_v8_exception_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8__capi_8h.html">cef_v8_capi.h:310</a>
 */
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
     * Returns the 1-based line number for the function call or 0 if unknown.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>int (CEF_CALLBACK* get_line_number)(struct _cef_v8_exception_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:1108</a>
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

        NativePeer(long ptr) {
            this.nativePtr = ptr;
            this.cleanable = net.kurobako.cef4j.NativeCleaner.INSTANCE.register(this, new Release(ptr));
        }

        @Override
        public void close() {
            cleanable.clean();
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
                N_Release(ptr);
            }
        }

        private static native void N_Release(long ptr);

        @Override
        public Optional<String> getMessage() {
            return Optional.ofNullable(N_GetMessage(nativePtr));
        }

        @Override
        public Optional<String> getSourceLine() {
            return Optional.ofNullable(N_GetSourceLine(nativePtr));
        }

        @Override
        public Optional<String> getScriptResourceName() {
            return Optional.ofNullable(N_GetScriptResourceName(nativePtr));
        }

        @Override
        public int getLineNumber() {
            return N_GetLineNumber(nativePtr);
        }

        @Override
        public int getStartPosition() {
            return N_GetStartPosition(nativePtr);
        }

        @Override
        public int getEndPosition() {
            return N_GetEndPosition(nativePtr);
        }

        @Override
        public int getStartColumn() {
            return N_GetStartColumn(nativePtr);
        }

        @Override
        public int getEndColumn() {
            return N_GetEndColumn(nativePtr);
        }

        private static native String N_GetMessage(long self);

        private static native String N_GetSourceLine(long self);

        private static native String N_GetScriptResourceName(long self);

        private static native int N_GetLineNumber(long self);

        private static native int N_GetStartPosition(long self);

        private static native int N_GetEndPosition(long self);

        private static native int N_GetStartColumn(long self);

        private static native int N_GetEndColumn(long self);

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
