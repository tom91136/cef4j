// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.util.Optional;

/**
 * Structure representing a V8 exception. The functions of this structure may be called on any render process thread.
 * NOTE: This struct is allocated DLL-side.
 */
public interface CefV8Exception {

    /** Returns the exception message. */
    Optional<String> getMessage();

    /** Returns the line of source code that the exception occurred within. */
    Optional<String> getSourceLine();

    /** Returns the resource name for the script from where the function causing the error originates. */
    Optional<String> getScriptResourceName();

    /** Returns the 1-based line number for the function call or 0 if unknown. */
    int getLineNumber();

    /** Returns the index within the script of the first character where the error occurred. */
    int getStartPosition();

    /** Returns the index within the script of the last character where the error occurred. */
    int getEndPosition();

    /** Returns the index within the line of the first character where the error occurred. */
    int getStartColumn();

    /** Returns the index within the line of the last character where the error occurred. */
    int getEndColumn();

    static class NativePeer implements CefV8Exception {
        private volatile long nativePtr;

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

        private native String N_GetMessage(long self);

        private native String N_GetSourceLine(long self);

        private native String N_GetScriptResourceName(long self);

        private native int N_GetLineNumber(long self);

        private native int N_GetStartPosition(long self);

        private native int N_GetEndPosition(long self);

        private native int N_GetStartColumn(long self);

        private native int N_GetEndColumn(long self);

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
