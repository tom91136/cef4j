// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.util.Optional;

/**
 * Structure representing a V8 stack frame handle. V8 handles can only be accessed from the thread on which they are
 * created. Valid threads for creating a V8 handle include the render process main thread ({@code TID_RENDERER}) and
 * WebWorker threads. A task runner for posting tasks on the associated thread can be retrieved via the
 * {@link CefV8Context#getTaskRunner()} function. NOTE: This struct is allocated DLL-side.
 *
 * <p>Definition generated from cef_v8_capi.h
 *
 * <pre>typedef struct _cef_v8_stack_frame_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_v8_stack_frame_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8__capi_8h.html">cef_v8_capi.h:1008</a>
 */
public interface CefV8StackFrame extends CefLibraryObject {

    /**
     * Returns {@code true} if this object is valid. Do not call any other methods if this function returns
     * {@code false}.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_valid)(struct _cef_v8_stack_frame_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__download__item_8h.html">cef_download_item.h:49</a>
     */
    boolean isValid();

    /**
     * Returns the name of the resource script that contains the function.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_script_name)(struct _cef_v8_stack_frame_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:1088</a>
     */
    Optional<String> getScriptName();

    /**
     * Returns the name of the resource script that contains the function or the sourceURL value if the script name is
     * undefined and its source ends with a "//@ sourceURL=..." string.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_script_name_or_source_url)(struct _cef_v8_stack_frame_t* self);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:1094</a>
     */
    Optional<String> getScriptNameOrSourceUrl();

    /**
     * Returns the name of the function.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_function_name)(struct _cef_v8_stack_frame_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:1102</a>
     */
    Optional<String> getFunctionName();

    /**
     * Returns the 1-based line number for the function call or 0 if unknown.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>int (CEF_CALLBACK* get_line_number)(struct _cef_v8_stack_frame_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:1108</a>
     */
    int getLineNumber();

    /**
     * Returns the 1-based column offset on the line for the function call or 0 if unknown.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>int (CEF_CALLBACK* get_column)(struct _cef_v8_stack_frame_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:1114</a>
     */
    int getColumn();

    /**
     * Returns {@code true} if the function was compiled using eval().
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_eval)(struct _cef_v8_stack_frame_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:1121</a>
     */
    boolean isEval();

    /**
     * Returns {@code true} if the function was called as a constructor via "new".
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_constructor)(struct _cef_v8_stack_frame_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:1127</a>
     */
    boolean isConstructor();

    final class NativePeer implements CefV8StackFrame, AutoCloseable {
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

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefV8StackFrame.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefV8StackFrame 0x{}", Long.toHexString(ptr));
                N_Release(ptr);
            }
        }

        private static native void N_Release(long ptr);

        @Override
        public boolean isValid() {
            return N_IsValid(nativePtr);
        }

        @Override
        public Optional<String> getScriptName() {
            return Optional.ofNullable(N_GetScriptName(nativePtr));
        }

        @Override
        public Optional<String> getScriptNameOrSourceUrl() {
            return Optional.ofNullable(N_GetScriptNameOrSourceUrl(nativePtr));
        }

        @Override
        public Optional<String> getFunctionName() {
            return Optional.ofNullable(N_GetFunctionName(nativePtr));
        }

        @Override
        public int getLineNumber() {
            return N_GetLineNumber(nativePtr);
        }

        @Override
        public int getColumn() {
            return N_GetColumn(nativePtr);
        }

        @Override
        public boolean isEval() {
            return N_IsEval(nativePtr);
        }

        @Override
        public boolean isConstructor() {
            return N_IsConstructor(nativePtr);
        }

        private static native boolean N_IsValid(long self);

        private static native String N_GetScriptName(long self);

        private static native String N_GetScriptNameOrSourceUrl(long self);

        private static native String N_GetFunctionName(long self);

        private static native int N_GetLineNumber(long self);

        private static native int N_GetColumn(long self);

        private static native boolean N_IsEval(long self);

        private static native boolean N_IsConstructor(long self);

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
            return "CefV8StackFrame{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
