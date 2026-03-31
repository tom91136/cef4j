// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import javax.annotation.Nullable;

/** Class representing a list value. Can be used on any process and thread. */
public interface CefListValue {

    /** Returns true if this object is valid. Do not call any other methods if this function returns false. */
    boolean isValid();

    /** Returns true if this object is currently owned by another object. */
    boolean isOwned();

    /** Returns true if the values of this object are read-only. Some APIs may expose read-only objects. */
    boolean isReadOnly();

    /** Returns true if this object is pointing to the same handle as |that| object. */
    boolean isSame(long that);

    /**
     * Returns true if this object and |that| object have an equivalent underlying value but are not necessarily the
     * same object.
     */
    boolean isEqual(long that);

    /** Returns a writable copy of this object. */
    long copy();

    /**
     * Sets the number of values. If the number of values is expanded all new value slots will default to type null.
     * Returns true on success.
     */
    boolean setSize(long size);

    /** Returns the number of values. */
    long getSize();

    /** Clears the menu. Returns true on success. */
    boolean clear();

    /** Removes the item with the specified |command_id|. Returns true on success. */
    boolean remove(long index);

    /**
     * Returns the item type for the specified |command_id|.
     *
     * @return the result, or {@code MENUITEMTYPE_NONE} for default handling
     */
    CefValueType getType(long index);

    /** Returns the value of this node. */
    long getValue(long index);

    /** Returns the value at the specified index as type bool. */
    boolean getBool(long index);

    /** Returns the value at the specified index as type int. */
    int getInt(long index);

    /** Returns the value at the specified index as type double. */
    double getDouble(long index);

    /**
     * Returns the value at the specified index as type dictionary. The returned value will reference existing data and
     * modifications to the value will modify this object.
     */
    long getDictionary(long index);

    /** Set the value of this node. Returns true on success. */
    boolean setValue(long index, long value);

    /** Sets the value at the specified index as type null. Returns true if the value was set successfully. */
    boolean setNull(long index);

    /** Sets the value at the specified index as type bool. Returns true if the value was set successfully. */
    boolean setBool(long index, boolean value);

    /** Sets the value at the specified index as type int. Returns true if the value was set successfully. */
    boolean setInt(long index, int value);

    /** Sets the value at the specified index as type double. Returns true if the value was set successfully. */
    boolean setDouble(long index, double value);

    /**
     * Sets the value at the specified index as type string. Returns true if the value was set successfully.
     *
     * @param value may be null
     */
    boolean setString(long index, @Nullable String value);

    /**
     * Sets the value at the specified index as type binary. Returns true if the value was set successfully. If |value|
     * is currently owned by another object then the value will be copied and the |value| reference will not change.
     * Otherwise, ownership will be transferred to this object and the |value| reference will be invalidated.
     */
    boolean setBinary(long index, long value);

    /**
     * Sets the value at the specified index as type dict. Returns true if the value was set successfully. If |value| is
     * currently owned by another object then the value will be copied and the |value| reference will not change.
     * Otherwise, ownership will be transferred to this object and the |value| reference will be invalidated.
     */
    boolean setDictionary(long index, long value);

    /**
     * Sets the value at the specified index as type list. Returns true if the value was set successfully. If |value| is
     * currently owned by another object then the value will be copied and the |value| reference will not change.
     * Otherwise, ownership will be transferred to this object and the |value| reference will be invalidated.
     */
    boolean setList(long index, long value);

    static class NativePeer implements CefListValue {
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
        public boolean isReadOnly() {
            return N_IsReadOnly(nativePtr);
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
        public boolean setSize(long size) {
            return N_SetSize(nativePtr, size);
        }

        @Override
        public long getSize() {
            return N_GetSize(nativePtr);
        }

        @Override
        public boolean clear() {
            return N_Clear(nativePtr);
        }

        @Override
        public boolean remove(long index) {
            return N_Remove(nativePtr, index);
        }

        @Override
        public CefValueType getType(long index) {
            return N_GetType(nativePtr, index);
        }

        @Override
        public long getValue(long index) {
            return N_GetValue(nativePtr, index);
        }

        @Override
        public boolean getBool(long index) {
            return N_GetBool(nativePtr, index);
        }

        @Override
        public int getInt(long index) {
            return N_GetInt(nativePtr, index);
        }

        @Override
        public double getDouble(long index) {
            return N_GetDouble(nativePtr, index);
        }

        @Override
        public long getDictionary(long index) {
            return N_GetDictionary(nativePtr, index);
        }

        @Override
        public boolean setValue(long index, long value) {
            return N_SetValue(nativePtr, index, value);
        }

        @Override
        public boolean setNull(long index) {
            return N_SetNull(nativePtr, index);
        }

        @Override
        public boolean setBool(long index, boolean value) {
            return N_SetBool(nativePtr, index, value);
        }

        @Override
        public boolean setInt(long index, int value) {
            return N_SetInt(nativePtr, index, value);
        }

        @Override
        public boolean setDouble(long index, double value) {
            return N_SetDouble(nativePtr, index, value);
        }

        @Override
        public boolean setString(long index, String value) {
            return N_SetString(nativePtr, index, value);
        }

        @Override
        public boolean setBinary(long index, long value) {
            return N_SetBinary(nativePtr, index, value);
        }

        @Override
        public boolean setDictionary(long index, long value) {
            return N_SetDictionary(nativePtr, index, value);
        }

        @Override
        public boolean setList(long index, long value) {
            return N_SetList(nativePtr, index, value);
        }

        private native boolean N_IsValid(long self);

        private native boolean N_IsOwned(long self);

        private native boolean N_IsReadOnly(long self);

        private native boolean N_IsSame(long self, long that);

        private native boolean N_IsEqual(long self, long that);

        private native long N_Copy(long self);

        private native boolean N_SetSize(long self, long size);

        private native long N_GetSize(long self);

        private native boolean N_Clear(long self);

        private native boolean N_Remove(long self, long index);

        private native CefValueType N_GetType(long self, long index);

        private native long N_GetValue(long self, long index);

        private native boolean N_GetBool(long self, long index);

        private native int N_GetInt(long self, long index);

        private native double N_GetDouble(long self, long index);

        private native long N_GetDictionary(long self, long index);

        private native boolean N_SetValue(long self, long index, long value);

        private native boolean N_SetNull(long self, long index);

        private native boolean N_SetBool(long self, long index, boolean value);

        private native boolean N_SetInt(long self, long index, int value);

        private native boolean N_SetDouble(long self, long index, double value);

        private native boolean N_SetString(long self, long index, String value);

        private native boolean N_SetBinary(long self, long index, long value);

        private native boolean N_SetDictionary(long self, long index, long value);

        private native boolean N_SetList(long self, long index, long value);

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
            return "CefListValue{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
