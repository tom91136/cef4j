// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.util.Optional;
import javax.annotation.Nullable;

/**
 * Class that wraps other data value types. Complex types (binary, dictionary and list) will be referenced but not owned
 * by this object. Can be used on any process and thread.
 */
public interface CefValue {

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
     * Returns the item type for the specified |command_id|.
     *
     * @return the result, or {@code MENUITEMTYPE_NONE} for default handling
     */
    CefValueType getType();

    /** Returns the value at the specified index as type bool. */
    boolean getBool();

    /** Returns the value at the specified index as type int. */
    int getInt();

    /** Returns the value at the specified index as type double. */
    double getDouble();

    /** Returns the value at the specified index as type string. */
    Optional<String> getString();

    /** Returns the value at the specified index as type binary. The returned value will reference existing data. */
    long getBinary();

    /**
     * Returns the value at the specified index as type dictionary. The returned value will reference existing data and
     * modifications to the value will modify this object.
     */
    long getDictionary();

    /**
     * Returns the value at the specified index as type list. The returned value will reference existing data and
     * modifications to the value will modify this object.
     */
    long getList();

    /** Sets the value at the specified index as type null. Returns true if the value was set successfully. */
    boolean setNull();

    /** Sets the value at the specified index as type bool. Returns true if the value was set successfully. */
    boolean setBool(boolean value);

    /** Sets the value at the specified index as type int. Returns true if the value was set successfully. */
    boolean setInt(int value);

    /** Sets the value at the specified index as type double. Returns true if the value was set successfully. */
    boolean setDouble(double value);

    /**
     * Sets the value at the specified index as type string. Returns true if the value was set successfully.
     *
     * @param value may be null
     */
    boolean setString(@Nullable String value);

    /**
     * Sets the value at the specified index as type binary. Returns true if the value was set successfully. If |value|
     * is currently owned by another object then the value will be copied and the |value| reference will not change.
     * Otherwise, ownership will be transferred to this object and the |value| reference will be invalidated.
     */
    boolean setBinary(long value);

    /**
     * Sets the value at the specified index as type dict. Returns true if the value was set successfully. If |value| is
     * currently owned by another object then the value will be copied and the |value| reference will not change.
     * Otherwise, ownership will be transferred to this object and the |value| reference will be invalidated.
     */
    boolean setDictionary(long value);

    /**
     * Sets the value at the specified index as type list. Returns true if the value was set successfully. If |value| is
     * currently owned by another object then the value will be copied and the |value| reference will not change.
     * Otherwise, ownership will be transferred to this object and the |value| reference will be invalidated.
     */
    boolean setList(long value);

    static class NativePeer implements CefValue {
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
        public CefValueType getType() {
            return N_GetType(nativePtr);
        }

        @Override
        public boolean getBool() {
            return N_GetBool(nativePtr);
        }

        @Override
        public int getInt() {
            return N_GetInt(nativePtr);
        }

        @Override
        public double getDouble() {
            return N_GetDouble(nativePtr);
        }

        @Override
        public Optional<String> getString() {
            return Optional.ofNullable(N_GetString(nativePtr));
        }

        @Override
        public long getBinary() {
            return N_GetBinary(nativePtr);
        }

        @Override
        public long getDictionary() {
            return N_GetDictionary(nativePtr);
        }

        @Override
        public long getList() {
            return N_GetList(nativePtr);
        }

        @Override
        public boolean setNull() {
            return N_SetNull(nativePtr);
        }

        @Override
        public boolean setBool(boolean value) {
            return N_SetBool(nativePtr, value);
        }

        @Override
        public boolean setInt(int value) {
            return N_SetInt(nativePtr, value);
        }

        @Override
        public boolean setDouble(double value) {
            return N_SetDouble(nativePtr, value);
        }

        @Override
        public boolean setString(String value) {
            return N_SetString(nativePtr, value);
        }

        @Override
        public boolean setBinary(long value) {
            return N_SetBinary(nativePtr, value);
        }

        @Override
        public boolean setDictionary(long value) {
            return N_SetDictionary(nativePtr, value);
        }

        @Override
        public boolean setList(long value) {
            return N_SetList(nativePtr, value);
        }

        private native boolean N_IsValid(long self);

        private native boolean N_IsOwned(long self);

        private native boolean N_IsReadOnly(long self);

        private native boolean N_IsSame(long self, long that);

        private native boolean N_IsEqual(long self, long that);

        private native long N_Copy(long self);

        private native CefValueType N_GetType(long self);

        private native boolean N_GetBool(long self);

        private native int N_GetInt(long self);

        private native double N_GetDouble(long self);

        private native String N_GetString(long self);

        private native long N_GetBinary(long self);

        private native long N_GetDictionary(long self);

        private native long N_GetList(long self);

        private native boolean N_SetNull(long self);

        private native boolean N_SetBool(long self, boolean value);

        private native boolean N_SetInt(long self, int value);

        private native boolean N_SetDouble(long self, double value);

        private native boolean N_SetString(long self, String value);

        private native boolean N_SetBinary(long self, long value);

        private native boolean N_SetDictionary(long self, long value);

        private native boolean N_SetList(long self, long value);

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
            return "CefValue{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
