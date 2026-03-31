// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Structure representing a V8 ArrayBuffer backing store. The backing store holds the memory that backs an ArrayBuffer.
 * It must be created on a thread with a valid V8 isolate (renderer main thread or WebWorker thread). Once created, the
 * data() pointer can be safely read/written from any thread. This allows expensive operations like memcpy to be
 * performed on a background thread before creating the ArrayBuffer on the V8 thread. The backing store is consumed when
 * passed to cef_v8_value_t::cef_v8_value_create_array_buffer_from_backing_store(), after which is_valid() returns false
 * (0). NOTE: This struct is allocated DLL-side.
 */
public interface CefV8BackingStore {

    long data();

    /** Returns the size of the allocated memory in bytes, or 0 if the backing store has been consumed. */
    long byteLength();

    /** Returns true if this object is valid. Do not call any other methods if this function returns false. */
    boolean isValid();

    static class NativePeer implements CefV8BackingStore {
        private volatile long nativePtr;

        @Override
        public long data() {
            return N_Data(nativePtr);
        }

        @Override
        public long byteLength() {
            return N_ByteLength(nativePtr);
        }

        @Override
        public boolean isValid() {
            return N_IsValid(nativePtr);
        }

        private native long N_Data(long self);

        private native long N_ByteLength(long self);

        private native boolean N_IsValid(long self);

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
            return "CefV8BackingStore{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
