// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import javax.annotation.Nonnull;

/**
 * Structure that should be implemented to handle V8 accessor calls. Accessor identifiers are registered by calling
 * cef_v8_value_t::set_value(). The functions of this structure will be called on the thread associated with the V8
 * accessor. NOTE: This struct is allocated client-side.
 */
public interface CefV8Accessor {

    /**
     * Handle retrieval of the interceptor value identified by |index|. |object| is the receiver ('this' object) of the
     * interceptor. If retrieval succeeds, set |retval| to the return value. If the requested value does not exist,
     * don't set either |retval| or |exception|. If retrieval fails, set |exception| to the exception that will be
     * thrown. Return true if interceptor retrieval was handled, false otherwise.
     */
    boolean get(@Nonnull String name, long object, long retval, @Nonnull String exception);

    /**
     * Handle assignment of the interceptor value identified by |index|. |object| is the receiver ('this' object) of the
     * interceptor. |value| is the new value being assigned to the interceptor. If assignment fails, set |exception| to
     * the exception that will be thrown. Return true if interceptor assignment was handled, false otherwise.
     */
    boolean set(@Nonnull String name, long object, long value, @Nonnull String exception);

    static class NativePeer implements CefV8Accessor {
        private volatile long nativePtr;

        @Override
        public boolean get(String name, long object, long retval, String exception) {
            return N_Get(nativePtr, name, object, retval, exception);
        }

        @Override
        public boolean set(String name, long object, long value, String exception) {
            return N_Set(nativePtr, name, object, value, exception);
        }

        private native boolean N_Get(long self, String name, long object, long retval, String exception);

        private native boolean N_Set(long self, String name, long object, long value, String exception);

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
            return "CefV8Accessor{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
