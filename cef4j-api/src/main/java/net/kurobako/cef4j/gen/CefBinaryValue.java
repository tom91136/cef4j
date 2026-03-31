// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Class representing a binary value. Can be used on any process and thread. */
public interface CefBinaryValue {

    /** Returns true if this object is valid. Do not call any other methods if this function returns false. */
    boolean isValid();

    /** Returns true if this object is currently owned by another object. */
    boolean isOwned();

    /** Returns true if this object is pointing to the same handle as |that| object. */
    boolean isSame(long that);

    /**
     * Returns true if this object and |that| object have an equivalent underlying value but are not necessarily the
     * same object.
     */
    boolean isEqual(long that);

    /** Returns a writable copy of this object. */
    long copy();

    long getRawData();

    /** Returns the number of values. */
    long getSize();

    /**
     * Read up to |buffer_size| number of bytes into |buffer|. Reading begins at the specified byte |data_offset|.
     * Returns the number of bytes read.
     */
    long getData(long buffer, long bufferSize, long dataOffset);

    static class NativePeer implements CefBinaryValue {
        private volatile long nativePtr;

        @Override
        public boolean isValid() {
            return N_IsValid(nativePtr);
        }

        @Override
        public boolean isOwned() {
            return N_IsOwned(nativePtr);
        }

        @Override
        public boolean isSame(long that) {
            return N_IsSame(nativePtr, that);
        }

        @Override
        public boolean isEqual(long that) {
            return N_IsEqual(nativePtr, that);
        }

        @Override
        public long copy() {
            return N_Copy(nativePtr);
        }

        @Override
        public long getRawData() {
            return N_GetRawData(nativePtr);
        }

        @Override
        public long getSize() {
            return N_GetSize(nativePtr);
        }

        @Override
        public long getData(long buffer, long bufferSize, long dataOffset) {
            return N_GetData(nativePtr, buffer, bufferSize, dataOffset);
        }

        private native boolean N_IsValid(long self);

        private native boolean N_IsOwned(long self);

        private native boolean N_IsSame(long self, long that);

        private native boolean N_IsEqual(long self, long that);

        private native long N_Copy(long self);

        private native long N_GetRawData(long self);

        private native long N_GetSize(long self);

        private native long N_GetData(long self, long buffer, long bufferSize, long dataOffset);

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
            return "CefBinaryValue{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
