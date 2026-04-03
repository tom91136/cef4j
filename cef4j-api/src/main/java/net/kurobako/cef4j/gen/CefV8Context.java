// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
// -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nullable;
import javax.annotation.processing.Generated;

/**
 * Structure representing a V8 context handle. V8 handles can only be accessed from the thread on which they are
 * created. Valid threads for creating a V8 handle include the render process main thread ({@code TID_RENDERER}) and
 * WebWorker threads. A task runner for posting tasks on the associated thread can be retrieved via the
 * {@link CefV8Context#getTaskRunner()} function. NOTE: This struct is allocated DLL-side.
 *
 * <p>Definition generated from cef_v8_capi.h
 *
 * <pre>typedef struct _cef_v8_context_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_v8_context_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8__capi_8h.html">cef_v8_capi.h:64</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
public interface CefV8Context extends CefLibraryObject {

    /**
     * Returns the CefTaskRunner that will execute code on this thread's message loop. This method is safe to call from
     * any thread.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>cef_task_runner_t* (CEF_CALLBACK* get_task_runner)(struct _cef_v8_context_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__thread_8h.html">cef_thread.h:87</a>
     */
    Optional<CefTaskRunner> getTaskRunner();

    /**
     * Returns {@code true} if this object is valid. Do not call any other methods if this function returns
     * {@code false}.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_valid)(struct _cef_v8_context_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__download__item_8h.html">cef_download_item.h:49</a>
     */
    boolean isValid();

    /**
     * Returns the browser for this context. This method will return an empty reference for WebWorker contexts.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>cef_browser_t* (CEF_CALLBACK* get_browser)(struct _cef_v8_context_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:163</a>
     */
    Optional<CefBrowser> getBrowser();

    /**
     * Returns the stack frame at the specified 0-based index.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>cef_frame_t* (CEF_CALLBACK* get_frame)(struct _cef_v8_context_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:1063</a>
     */
    Optional<CefFrame> getFrame();

    /**
     * Returns the global object for this context. The context must be entered before calling this method.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>cef_v8_value_t* (CEF_CALLBACK* get_global)(struct _cef_v8_context_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:177</a>
     */
    Optional<CefV8Value> getGlobal();

    /**
     * Enter this context. A context must be explicitly entered before creating a V8 Object, Array, Function or Date
     * asynchronously. Exit() must be called the same number of times as Enter() before releasing this context. V8
     * objects belong to the context in which they are created. Returns {@code true} if the scope was entered
     * successfully.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>int (CEF_CALLBACK* enter)(struct _cef_v8_context_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:184</a>
     */
    boolean enter();

    /**
     * Exit this context. Call this method only after calling Enter(). Returns {@code true} if the scope was exited
     * successfully.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>int (CEF_CALLBACK* exit)(struct _cef_v8_context_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:194</a>
     */
    boolean exit();

    /**
     * Returns {@code true} if this object is pointing to the same handle as {@code that} object.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_same)(struct _cef_v8_context_t* self, struct _cef_v8_context_t* that);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__dom_8h.html">cef_dom.h:208</a>
     */
    boolean isSame(@Nullable CefV8Context that);

    /**
     * Execute a string of JavaScript code in this V8 context. The {@code script_url} parameter is the URL where the
     * script in question can be found, if any. The {@code start_line} parameter is the base line number to use for
     * error reporting. On success {@code retval} will be set to the return value, if any, and the function will return
     * {@code true}. On failure {@code exception} will be set to the exception, if any, and the function will return
     * {@code false}.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* eval)(struct _cef_v8_context_t* self, const cef_string_t* code, const cef_string_t* script_url, int start_line, struct _cef_v8_value_t** retval, struct _cef_v8_exception_t** exception);
     * </pre>
     *
     * @param scriptUrl may be null
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:208</a>
     */
    boolean eval(
            @Nullable String code,
            @Nullable String scriptUrl,
            int startLine,
            @Nullable AtomicReference<CefV8Value> retval,
            @Nullable AtomicReference<CefV8Exception> exception);
    /**
     * Returns the current (top) context object in the V8 context stack.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>CEF_EXPORT cef_v8_context_t* cef_v8_context_get_current_context(void);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:129</a>
     */
    static Optional<CefV8Context> getCurrentContext() {
        return Optional.ofNullable(NativePeer.getCurrentContext0());
    }

    /**
     * Returns the entered (bottom) context object in the V8 context stack.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>CEF_EXPORT cef_v8_context_t* cef_v8_context_get_entered_context(void);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:135</a>
     */
    static Optional<CefV8Context> getEnteredContext() {
        return Optional.ofNullable(NativePeer.getEnteredContext0());
    }

    /**
     * Returns {@code true} if V8 is currently inside a context.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>CEF_EXPORT int cef_v8_context_in_context(void);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:141</a>
     */
    static int inContext() {
        return NativePeer.inContext0();
    }

    final class NativePeer implements CefV8Context, AutoCloseable {
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
            if (closed) throw new IllegalStateException("CefV8Context has been closed");
        }

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefV8Context.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefV8Context 0x{}", Long.toHexString(ptr));
                release0(ptr);
            }
        }

        private static native void release0(long ptr);

        @Override
        public Optional<CefTaskRunner> getTaskRunner() {
            checkNotClosed();
            return Optional.ofNullable(getTaskRunner0(nativePtr));
        }

        @Override
        public boolean isValid() {
            checkNotClosed();
            return isValid0(nativePtr);
        }

        @Override
        public Optional<CefBrowser> getBrowser() {
            checkNotClosed();
            return Optional.ofNullable(getBrowser0(nativePtr));
        }

        @Override
        public Optional<CefFrame> getFrame() {
            checkNotClosed();
            return Optional.ofNullable(getFrame0(nativePtr));
        }

        @Override
        public Optional<CefV8Value> getGlobal() {
            checkNotClosed();
            return Optional.ofNullable(getGlobal0(nativePtr));
        }

        @Override
        public boolean enter() {
            checkNotClosed();
            return enter0(nativePtr);
        }

        @Override
        public boolean exit() {
            checkNotClosed();
            return exit0(nativePtr);
        }

        @Override
        public boolean isSame(@Nullable CefV8Context that) {
            checkNotClosed();
            CefLibraryObject.requireOpen(that, "CefV8Context");
            return isSame0(nativePtr, that);
        }

        @Override
        public boolean eval(
                @Nullable String code,
                @Nullable String scriptUrl,
                int startLine,
                @Nullable AtomicReference<CefV8Value> retval,
                @Nullable AtomicReference<CefV8Exception> exception) {
            checkNotClosed();
            return eval0(nativePtr, code, scriptUrl, startLine, retval, exception);
        }

        private static native CefTaskRunner getTaskRunner0(long self);

        private static native boolean isValid0(long self);

        private static native CefBrowser getBrowser0(long self);

        private static native CefFrame getFrame0(long self);

        private static native CefV8Value getGlobal0(long self);

        private static native boolean enter0(long self);

        private static native boolean exit0(long self);

        private static native boolean isSame0(long self, CefV8Context that);

        private static native boolean eval0(
                long self,
                String code,
                String scriptUrl,
                int startLine,
                AtomicReference<CefV8Value> retval,
                AtomicReference<CefV8Exception> exception);

        static native CefV8Context getCurrentContext0();

        static native CefV8Context getEnteredContext0();

        static native int inContext0();

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
            return "CefV8Context{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
