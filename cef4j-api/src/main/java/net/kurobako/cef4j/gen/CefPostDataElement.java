// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.util.Optional;
import javax.annotation.Nonnull;

/**
 * Class used to represent a single element in the request post data. The methods of this class may be called on any
 * thread.
 */
public interface CefPostDataElement {

    /** Returns true if the values of this object are read-only. Some APIs may expose read-only objects. */
    boolean isReadOnly();

    /** Remove all contents from the post data element. */
    void setToEmpty();

    /** The post data element will represent a file. */
    void setToFile(@Nonnull String fileName);

    /** The post data element will represent bytes. The bytes passed in will be copied. */
    void setToBytes(long size, long bytes);

    /**
     * Returns the item type for the specified |command_id|.
     *
     * @return the result, or {@code MENUITEMTYPE_NONE} for default handling
     */
    CefPostdataelementType getType();

    /** Return the file name. */
    Optional<String> getFile();

    /** Return the number of bytes. */
    long getBytesCount();

    /** Read up to |size| bytes into |bytes| and return the number of bytes actually read. */
    long getBytes(long size, long bytes);

    static class NativePeer implements CefPostDataElement {
        private volatile long nativePtr;

        @Override
        public boolean isReadOnly() {
            return N_IsReadOnly(nativePtr);
        }

        @Override
        public void setToEmpty() {
            N_SetToEmpty(nativePtr);
        }

        @Override
        public void setToFile(String fileName) {
            N_SetToFile(nativePtr, fileName);
        }

        @Override
        public void setToBytes(long size, long bytes) {
            N_SetToBytes(nativePtr, size, bytes);
        }

        @Override
        public CefPostdataelementType getType() {
            return N_GetType(nativePtr);
        }

        @Override
        public Optional<String> getFile() {
            return Optional.ofNullable(N_GetFile(nativePtr));
        }

        @Override
        public long getBytesCount() {
            return N_GetBytesCount(nativePtr);
        }

        @Override
        public long getBytes(long size, long bytes) {
            return N_GetBytes(nativePtr, size, bytes);
        }

        private native boolean N_IsReadOnly(long self);

        private native void N_SetToEmpty(long self);

        private native void N_SetToFile(long self, String fileName);

        private native void N_SetToBytes(long self, long size, long bytes);

        private native CefPostdataelementType N_GetType(long self);

        private native String N_GetFile(long self);

        private native long N_GetBytesCount(long self);

        private native long N_GetBytes(long self, long size, long bytes);

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
            return "CefPostDataElement{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
