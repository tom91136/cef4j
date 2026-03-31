// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Structure representing a V8 stack trace handle. V8 handles can only be accessed from the thread on which they are
 * created. Valid threads for creating a V8 handle include the render process main thread (TID_RENDERER) and WebWorker
 * threads. A task runner for posting tasks on the associated thread can be retrieved via the
 * cef_v8_context_t::get_task_runner() function. NOTE: This struct is allocated DLL-side.
 */
public interface CefV8StackTrace {

    /** Returns true if this object is valid. Do not call any other methods if this function returns false. */
    boolean isValid();

    /** Returns the number of stack frames. */
    int getFrameCount();

    static class NativePeer implements CefV8StackTrace {
        private volatile long nativePtr;

        @Override
        public boolean isValid() {
            return N_IsValid(nativePtr);
        }

        @Override
        public int getFrameCount() {
            return N_GetFrameCount(nativePtr);
        }

        private native boolean N_IsValid(long self);

        private native int N_GetFrameCount(long self);

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
