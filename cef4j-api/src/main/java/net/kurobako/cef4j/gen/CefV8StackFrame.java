// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import java.util.Optional;

/**
 * Class representing a V8 stack frame handle. V8 handles can only be accessed from the thread on which they are created. Valid threads for creating a V8 handle include the render process main thread ({@code TID_RENDERER}) and WebWorker threads. A task runner for posting tasks on the associated thread can be retrieved via the {@link net.kurobako.cef4j.gen.CefV8Context#getTaskRunner()} method.
 * <p>Definition generated from cef_v8_capi.h
 * <pre>typedef struct _cef_v8_stack_frame_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_v8_stack_frame_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:1070</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public interface CefV8StackFrame extends CefLibraryObject {

    /**
     * Returns {@code true} if the underlying handle is valid and it can be accessed on the current thread. Do not call any other methods if this method returns {@code false}.
     * <p>Definition generated from cef_v8_capi.h
     * <pre>int (CEF_CALLBACK* is_valid)(struct _cef_v8_stack_frame_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:1080</a>
     */
    boolean isValid();

    /**
     * Returns the name of the resource script that contains the function.
     * <p>Definition generated from cef_v8_capi.h
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_script_name)(struct _cef_v8_stack_frame_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:1088</a>
     */
    Optional<String> getScriptName();

    /**
     * Returns the name of the resource script that contains the function or the sourceURL value if the script name is undefined and its source ends with a "//@ sourceURL=..." string.
     * <p>Definition generated from cef_v8_capi.h
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_script_name_or_source_url)(struct _cef_v8_stack_frame_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:1094</a>
     */
    Optional<String> getScriptNameOrSourceUrl();

    /**
     * Returns the name of the function.
     * <p>Definition generated from cef_v8_capi.h
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_function_name)(struct _cef_v8_stack_frame_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:1102</a>
     */
    Optional<String> getFunctionName();

    /**
     * Returns the 1-based line number for the function call or 0 if unknown.
     * <p>Definition generated from cef_v8_capi.h
     * <pre>int (CEF_CALLBACK* get_line_number)(struct _cef_v8_stack_frame_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:1108</a>
     */
    int getLineNumber();

    /**
     * Returns the 1-based column offset on the line for the function call or 0 if unknown.
     * <p>Definition generated from cef_v8_capi.h
     * <pre>int (CEF_CALLBACK* get_column)(struct _cef_v8_stack_frame_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:1114</a>
     */
    int getColumn();

    /**
     * Returns {@code true} if the function was compiled using eval().
     * <p>Definition generated from cef_v8_capi.h
     * <pre>int (CEF_CALLBACK* is_eval)(struct _cef_v8_stack_frame_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:1121</a>
     */
    boolean isEval();

    /**
     * Returns {@code true} if the function was called as a constructor via "new".
     * <p>Definition generated from cef_v8_capi.h
     * <pre>int (CEF_CALLBACK* is_constructor)(struct _cef_v8_stack_frame_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:1127</a>
     */
    boolean isConstructor();
    final class NativePeer implements CefV8StackFrame, AutoCloseable {
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
            if (closed) throw new IllegalStateException("CefV8StackFrame has been closed");
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
                release0(ptr);
            }
        }

        private static native void release0(long ptr);

        @Override
      public boolean isValid() {
          checkNotClosed();
          return isValid0(nativePtr);
      }

        @Override
      public Optional<String> getScriptName() {
          checkNotClosed();
          return Optional.ofNullable(getScriptName0(nativePtr));
      }

        @Override
      public Optional<String> getScriptNameOrSourceUrl() {
          checkNotClosed();
          return Optional.ofNullable(getScriptNameOrSourceUrl0(nativePtr));
      }

        @Override
      public Optional<String> getFunctionName() {
          checkNotClosed();
          return Optional.ofNullable(getFunctionName0(nativePtr));
      }

        @Override
      public int getLineNumber() {
          checkNotClosed();
          return getLineNumber0(nativePtr);
      }

        @Override
      public int getColumn() {
          checkNotClosed();
          return getColumn0(nativePtr);
      }

        @Override
      public boolean isEval() {
          checkNotClosed();
          return isEval0(nativePtr);
      }

        @Override
      public boolean isConstructor() {
          checkNotClosed();
          return isConstructor0(nativePtr);
      }


        static native boolean isValid0(long self);

        static native String getScriptName0(long self);

        static native String getScriptNameOrSourceUrl0(long self);

        static native String getFunctionName0(long self);

        static native int getLineNumber0(long self);

        static native int getColumn0(long self);

        static native boolean isEval0(long self);

        static native boolean isConstructor0(long self);


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
