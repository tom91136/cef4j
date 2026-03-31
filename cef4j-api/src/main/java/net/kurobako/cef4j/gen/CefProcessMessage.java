// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.util.Optional;

/** Class representing a message. Can be used on any process and thread. */
public interface CefProcessMessage {

    /** Returns true if this object is valid. Do not call any other methods if this function returns false. */
    boolean isValid();

    /** Returns true if the values of this object are read-only. Some APIs may expose read-only objects. */
    boolean isReadOnly();

    /** Returns a writable copy of this object. */
    long copy();

    /** Returns the name of this node. */
    Optional<String> getName();

    /** Returns the list of arguments. Returns nullptr when message contains a shared memory region. */
    long getArgumentList();

    /** Returns the shared memory region. Returns nullptr when message contains an argument list. */
    long getSharedMemoryRegion();

    static class NativePeer implements CefProcessMessage {
        private volatile long nativePtr;

        @Override
        public boolean isValid() {
            return N_IsValid(nativePtr);
        }

        @Override
        public boolean isReadOnly() {
            return N_IsReadOnly(nativePtr);
        }

        @Override
        public long copy() {
            return N_Copy(nativePtr);
        }

        @Override
        public Optional<String> getName() {
            return Optional.ofNullable(N_GetName(nativePtr));
        }

        @Override
        public long getArgumentList() {
            return N_GetArgumentList(nativePtr);
        }

        @Override
        public long getSharedMemoryRegion() {
            return N_GetSharedMemoryRegion(nativePtr);
        }

        private native boolean N_IsValid(long self);

        private native boolean N_IsReadOnly(long self);

        private native long N_Copy(long self);

        private native String N_GetName(long self);

        private native long N_GetArgumentList(long self);

        private native long N_GetSharedMemoryRegion(long self);

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
            return "CefProcessMessage{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
