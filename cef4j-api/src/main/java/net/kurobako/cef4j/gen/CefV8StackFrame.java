// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.util.Optional;

/**
 * Structure representing a V8 stack frame handle. V8 handles can only be accessed from the thread on which they are
 * created. Valid threads for creating a V8 handle include the render process main thread (TID_RENDERER) and WebWorker
 * threads. A task runner for posting tasks on the associated thread can be retrieved via the
 * cef_v8_context_t::get_task_runner() function. NOTE: This struct is allocated DLL-side.
 */
public interface CefV8StackFrame {

    /** Returns true if this object is valid. Do not call any other methods if this function returns false. */
    boolean isValid();

    /** Returns the name of the resource script that contains the function. */
    Optional<String> getScriptName();

    Optional<String> getScriptNameOrSourceUrl();

    /** Returns the name of the function. */
    Optional<String> getFunctionName();

    /** Returns the 1-based line number for the function call or 0 if unknown. */
    int getLineNumber();

    /** Returns the 1-based column offset on the line for the function call or 0 if unknown. */
    int getColumn();

    /** Returns true if the function was compiled using eval(). */
    boolean isEval();

    /** Returns true if the function was called as a constructor via "new". */
    boolean isConstructor();

    static class NativePeer implements CefV8StackFrame {
        private volatile long nativePtr;

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

        private native boolean N_IsValid(long self);

        private native String N_GetScriptName(long self);

        private native String N_GetScriptNameOrSourceUrl(long self);

        private native String N_GetFunctionName(long self);

        private native int N_GetLineNumber(long self);

        private native int N_GetColumn(long self);

        private native boolean N_IsEval(long self);

        private native boolean N_IsConstructor(long self);

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
