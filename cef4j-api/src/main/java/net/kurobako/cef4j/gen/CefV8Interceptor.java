// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import javax.annotation.Nonnull;

/**
 * Structure that should be implemented to handle V8 interceptor calls. The functions of this structure will be called
 * on the thread associated with the V8 interceptor. Interceptor's named property handlers (with first argument of type
 * CefString) are called when object is indexed by string. Indexed property handlers (with first argument of type int)
 * are called when object is indexed by integer. NOTE: This struct is allocated client-side.
 */
public interface CefV8Interceptor {

    /**
     * Handle retrieval of the interceptor value identified by |name|. |object| is the receiver ('this' object) of the
     * interceptor. If retrieval succeeds, set |retval| to the return value. If the requested value does not exist,
     * don't set either |retval| or |exception|. If retrieval fails, set |exception| to the exception that will be
     * thrown. If the property has an associated accessor, it will be called only if you don't set |retval|. Return true
     * if interceptor retrieval was handled, false otherwise.
     */
    int getByname(@Nonnull String name, long object, long retval, @Nonnull String exception);

    /**
     * Handle retrieval of the interceptor value identified by |index|. |object| is the receiver ('this' object) of the
     * interceptor. If retrieval succeeds, set |retval| to the return value. If the requested value does not exist,
     * don't set either |retval| or |exception|. If retrieval fails, set |exception| to the exception that will be
     * thrown. Return true if interceptor retrieval was handled, false otherwise.
     *
     * @param index zero-based index
     */
    int getByindex(int index, long object, long retval, @Nonnull String exception);

    /**
     * Handle assignment of the interceptor value identified by |name|. |object| is the receiver ('this' object) of the
     * interceptor. |value| is the new value being assigned to the interceptor. If assignment fails, set |exception| to
     * the exception that will be thrown. This setter will always be called, even when the property has an associated
     * accessor. Return true if interceptor assignment was handled, false otherwise.
     */
    int setByname(@Nonnull String name, long object, long value, @Nonnull String exception);

    /**
     * Handle assignment of the interceptor value identified by |index|. |object| is the receiver ('this' object) of the
     * interceptor. |value| is the new value being assigned to the interceptor. If assignment fails, set |exception| to
     * the exception that will be thrown. Return true if interceptor assignment was handled, false otherwise.
     *
     * @param index zero-based index
     */
    int setByindex(int index, long object, long value, @Nonnull String exception);

    static class NativePeer implements CefV8Interceptor {
        private volatile long nativePtr;

        @Override
        public int getByname(String name, long object, long retval, String exception) {
            return N_GetByname(nativePtr, name, object, retval, exception);
        }

        @Override
        public int getByindex(int index, long object, long retval, String exception) {
            return N_GetByindex(nativePtr, index, object, retval, exception);
        }

        @Override
        public int setByname(String name, long object, long value, String exception) {
            return N_SetByname(nativePtr, name, object, value, exception);
        }

        @Override
        public int setByindex(int index, long object, long value, String exception) {
            return N_SetByindex(nativePtr, index, object, value, exception);
        }

        private native int N_GetByname(long self, String name, long object, long retval, String exception);

        private native int N_GetByindex(long self, int index, long object, long retval, String exception);

        private native int N_SetByname(long self, String name, long object, long value, String exception);

        private native int N_SetByindex(long self, int index, long object, long value, String exception);

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
            return "CefV8Interceptor{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
