// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import javax.annotation.Nonnull;

/**
 * Structure that should be implemented to handle V8 function calls. The functions of this structure will be called on
 * the thread associated with the V8 function. NOTE: This struct is allocated client-side.
 */
public interface CefV8Handler {

    /**
     * Handle execution of the function identified by |name|. |object| is the receiver ('this' object) of the function.
     * |arguments| is the list of arguments passed to the function. If execution succeeds set |retval| to the function
     * return value. If execution fails set |exception| to the exception that will be thrown. Return true if execution
     * was handled.
     */
    boolean execute(
            @Nonnull String name,
            long object,
            long argumentsCount,
            long arguments,
            long retval,
            @Nonnull String exception);

    static class NativePeer implements CefV8Handler {
        private volatile long nativePtr;

        @Override
        public boolean execute(
                String name, long object, long argumentsCount, long arguments, long retval, String exception) {
            return N_Execute(nativePtr, name, object, argumentsCount, arguments, retval, exception);
        }

        private native boolean N_Execute(
                long self,
                String name,
                long object,
                long argumentsCount,
                long arguments,
                long retval,
                String exception);

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
            return "CefV8Handler{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
