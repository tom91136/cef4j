// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.util.Optional;

/**
 * Structure representing a V8 stack trace handle. V8 handles can only be accessed from the thread on which they are
 * created. Valid threads for creating a V8 handle include the render process main thread ({@code TID_RENDERER}) and
 * WebWorker threads. A task runner for posting tasks on the associated thread can be retrieved via the
 * {@link CefV8Context#getTaskRunner()} function. NOTE: This struct is allocated DLL-side.
 *
 * <p>Definition generated from cef_v8_capi.h
 *
 * <pre>typedef struct _cef_v8_stack_trace_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_v8_stack_trace_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8__capi_8h.html">cef_v8_capi.h:966</a>
 */
public interface CefV8StackTrace extends CefLibraryObject {

    /**
     * Returns {@code true} if this object is valid. Do not call any other methods if this function returns
     * {@code false}.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_valid)(struct _cef_v8_stack_trace_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__download__item_8h.html">cef_download_item.h:49</a>
     */
    boolean isValid();

    /**
     * Returns the number of stack frames.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>int (CEF_CALLBACK* get_frame_count)(struct _cef_v8_stack_trace_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:1057</a>
     */
    int getFrameCount();
    /**
     * Returns the stack trace for the currently active context. {@code frame_limit} is the maximum number of frames
     * that will be captured.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>CEF_EXPORT cef_v8_stack_trace_t* cef_v8_stack_trace_get_current(int frame_limit);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:1042</a>
     */
    static Optional<CefV8StackTrace> getCurrent(int frameLimit) {
        return Optional.ofNullable(NativePeer.N_GetCurrent(frameLimit));
    }

    final class NativePeer implements CefV8StackTrace, AutoCloseable {
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

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefV8StackTrace.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefV8StackTrace 0x{}", Long.toHexString(ptr));
                N_Release(ptr);
            }
        }

        private static native void N_Release(long ptr);

        @Override
        public boolean isValid() {
            return N_IsValid(nativePtr);
        }

        @Override
        public int getFrameCount() {
            return N_GetFrameCount(nativePtr);
        }

        private static native boolean N_IsValid(long self);

        private static native int N_GetFrameCount(long self);

        static native CefV8StackTrace N_GetCurrent(int frameLimit);

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
