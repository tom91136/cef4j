// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Class that wraps platform-dependent share memory region mapping. */
public interface CefSharedMemoryRegion {

    /** Returns true if this object is valid. Do not call any other methods if this function returns false. */
    boolean isValid();

    /** Returns the size of the shared memory region in bytes. Returns 0 for invalid instances. */
    long size();

    long memory();

    static class NativePeer implements CefSharedMemoryRegion {
        private volatile long nativePtr;

        @Override
        public boolean isValid() {
            return N_IsValid(nativePtr);
        }

        @Override
        public long size() {
            return N_Size(nativePtr);
        }

        @Override
        public long memory() {
            return N_Memory(nativePtr);
        }

        private native boolean N_IsValid(long self);

        private native long N_Size(long self);

        private native long N_Memory(long self);

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
            return "CefSharedMemoryRegion{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
