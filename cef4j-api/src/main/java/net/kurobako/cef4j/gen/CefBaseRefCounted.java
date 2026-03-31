// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** All ref-counted framework structures must include this structure first. */
public interface CefBaseRefCounted {

    static class NativePeer implements CefBaseRefCounted {
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
            return "CefBaseRefCounted{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
