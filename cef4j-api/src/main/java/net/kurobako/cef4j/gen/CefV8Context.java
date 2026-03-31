// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Structure representing a V8 context handle. V8 handles can only be accessed from the thread on which they are
 * created. Valid threads for creating a V8 handle include the render process main thread (TID_RENDERER) and WebWorker
 * threads. A task runner for posting tasks on the associated thread can be retrieved via the
 * cef_v8_context_t::get_task_runner() function. NOTE: This struct is allocated DLL-side.
 */
public interface CefV8Context {

    /**
     * Returns the CefTaskRunner that will execute code on this thread's message loop. This method is safe to call from
     * any thread.
     */
    long getTaskRunner();

    /** Returns true if this object is valid. Do not call any other methods if this function returns false. */
    boolean isValid();

    /** Returns the browser for this context. This method will return an empty reference for WebWorker contexts. */
    long getBrowser();

    /** Returns the stack frame at the specified 0-based index. */
    long getFrame();

    /** Returns the global object for this context. The context must be entered before calling this method. */
    long getGlobal();

    /**
     * Enter this context. A context must be explicitly entered before creating a V8 Object, Array, Function or Date
     * asynchronously. Exit() must be called the same number of times as Enter() before releasing this context. V8
     * objects belong to the context in which they are created. Returns true if the scope was entered successfully.
     */
    boolean enter();

    /**
     * Exit this context. Call this method only after calling Enter(). Returns true if the scope was exited
     * successfully.
     */
    boolean exit();

    /** Returns true if this object is pointing to the same handle as |that| object. */
    boolean isSame(long that);

    /**
     * Execute a string of JavaScript code in this V8 context. The |script_url| parameter is the URL where the script in
     * question can be found, if any. The |start_line| parameter is the base line number to use for error reporting. On
     * success |retval| will be set to the return value, if any, and the function will return true. On failure
     * |exception| will be set to the exception, if any, and the function will return false.
     *
     * @param scriptUrl may be null
     */
    boolean eval(@Nonnull String code, @Nullable String scriptUrl, int startLine, long retval, long exception);

    static class NativePeer implements CefV8Context {
        private volatile long nativePtr;

        @Override
        public long getTaskRunner() {
            return N_GetTaskRunner(nativePtr);
        }

        @Override
        public boolean isValid() {
            return N_IsValid(nativePtr);
        }

        @Override
        public long getBrowser() {
            return N_GetBrowser(nativePtr);
        }

        @Override
        public long getFrame() {
            return N_GetFrame(nativePtr);
        }

        @Override
        public long getGlobal() {
            return N_GetGlobal(nativePtr);
        }

        @Override
        public boolean enter() {
            return N_Enter(nativePtr);
        }

        @Override
        public boolean exit() {
            return N_Exit(nativePtr);
        }

        @Override
        public boolean isSame(long that) {
            return N_IsSame(nativePtr, that);
        }

        @Override
        public boolean eval(String code, String scriptUrl, int startLine, long retval, long exception) {
            return N_Eval(nativePtr, code, scriptUrl, startLine, retval, exception);
        }

        private native long N_GetTaskRunner(long self);

        private native boolean N_IsValid(long self);

        private native long N_GetBrowser(long self);

        private native long N_GetFrame(long self);

        private native long N_GetGlobal(long self);

        private native boolean N_Enter(long self);

        private native boolean N_Exit(long self);

        private native boolean N_IsSame(long self, long that);

        private native boolean N_Eval(
                long self, String code, String scriptUrl, int startLine, long retval, long exception);

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
