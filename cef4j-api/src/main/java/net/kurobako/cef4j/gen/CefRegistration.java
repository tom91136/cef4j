// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Generic callback interface used for managing the lifespan of a registration. */
public interface CefRegistration {

    static class NativePeer implements CefRegistration {
        private volatile long nativePtr;

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
            return "CefRegistration{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
