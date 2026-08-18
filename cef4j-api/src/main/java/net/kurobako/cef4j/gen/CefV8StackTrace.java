// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import java.util.Optional;

/**
 * Class representing a V8 stack trace handle. V8 handles can only be accessed from the thread on which they are created. Valid threads for creating a V8 handle include the render process main thread ({@code TID_RENDERER}) and WebWorker threads. A task runner for posting tasks on the associated thread can be retrieved via the {@link net.kurobako.cef4j.gen.CefV8Context#getTaskRunner()} method.
 * <p>Definition generated from cef_v8_capi.h
 * <pre>typedef struct _cef_v8_stack_trace_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_v8_stack_trace_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:1032</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused", "NullableForbidden"})
public interface CefV8StackTrace extends CefLibraryObject {

    /**
     * Returns {@code true} if the underlying handle is valid and it can be accessed on the current thread. Do not call any other methods if this method returns {@code false}.
     * <p>Definition generated from cef_v8_capi.h
     * <pre>int (CEF_CALLBACK* is_valid)(struct _cef_v8_stack_trace_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:1049</a>
     */
    boolean isValid();

    /**
     * Returns the number of stack frames.
     * <p>Definition generated from cef_v8_capi.h
     * <pre>int (CEF_CALLBACK* get_frame_count)(struct _cef_v8_stack_trace_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:1057</a>
     */
    int getFrameCount();

    /**
     * Returns the stack frame at the specified 0-based index.
     * <p>Definition generated from cef_v8_capi.h
     * <pre>cef_v8_stack_frame_t* (CEF_CALLBACK* get_frame)(struct _cef_v8_stack_trace_t* self, int index);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:1063</a>
     */
    Optional<CefV8StackFrame> getFrame(int index);
    /**
     * Returns the stack trace for the currently active context. {@code frame_limit} is the maximum number of frames that will be captured.
     * <p>Definition generated from cef_v8_capi.h
     * <pre>CEF_EXPORT cef_v8_stack_trace_t* cef_v8_stack_trace_get_current(int frame_limit);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:1042</a>
     */
    static Optional<CefV8StackTrace> getCurrent(int frameLimit) {
      return Optional.ofNullable(NativePeer.getCurrent0(frameLimit));
  }

    final class NativePeer implements CefV8StackTrace, AutoCloseable {
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
            if (closed) throw new IllegalStateException("CefV8StackTrace has been closed");
        }

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefV8StackTrace.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefV8StackTrace 0x{}", Long.toHexString(ptr));
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
      public int getFrameCount() {
          checkNotClosed();
          return getFrameCount0(nativePtr);
      }

        @Override
      public Optional<CefV8StackFrame> getFrame(int index) {
          checkNotClosed();
          return Optional.ofNullable(getFrame0(nativePtr, index));
      }


        static native boolean isValid0(long self);

        static native int getFrameCount0(long self);

        static native CefV8StackFrame getFrame0(long self, int index);

        static native CefV8StackTrace getCurrent0(int frameLimit);

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
            return "CefV8StackTrace{0x" + Long.toHexString(nativePtr) + "}";
        }
    }

}
