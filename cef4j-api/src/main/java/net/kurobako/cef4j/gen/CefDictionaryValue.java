// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/** Class representing a dictionary value. Can be used on any process and thread. */
public interface CefDictionaryValue {

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
    long copy(int excludeEmptyChildren);

    /** Returns the number of values. */
    long getSize();

    /** Clears the menu. Returns true on success. */
    boolean clear();

    /** Returns true if the current dictionary has a value for the given key. */
    boolean hasKey(@Nonnull String key);

    /**
     * Read the keys for the object's values into the specified vector. Integer- based keys will also be returned as
     * strings.
     */
    boolean getKeys(@Nonnull java.util.List<String> keys);

    /** Removes the item with the specified |command_id|. Returns true on success. */
    boolean remove(@Nonnull String key);

    /**
     * Returns the item type for the specified |command_id|.
     *
     * @return the result, or {@code MENUITEMTYPE_NONE} for default handling
     */
    CefValueType getType(@Nonnull String key);

    /** Returns the value of this node. */
    long getValue(@Nonnull String key);

    /** Returns the value at the specified index as type bool. */
    boolean getBool(@Nonnull String key);

    /** Returns the value at the specified index as type int. */
    int getInt(@Nonnull String key);

    /** Returns the value at the specified index as type double. */
    double getDouble(@Nonnull String key);

    /** Returns the value at the specified index as type string. */
    Optional<String> getString(@Nonnull String key);

    /** Returns the value at the specified index as type binary. The returned value will reference existing data. */
    long getBinary(@Nonnull String key);

    /**
     * Returns the value at the specified index as type dictionary. The returned value will reference existing data and
     * modifications to the value will modify this object.
     */
    long getDictionary(@Nonnull String key);

    /**
     * Returns the value at the specified index as type list. The returned value will reference existing data and
     * modifications to the value will modify this object.
     */
    long getList(@Nonnull String key);

    /** Set the value of this node. Returns true on success. */
    boolean setValue(@Nonnull String key, long value);

    /** Sets the value at the specified index as type null. Returns true if the value was set successfully. */
    boolean setNull(@Nonnull String key);

    /** Sets the value at the specified index as type bool. Returns true if the value was set successfully. */
    boolean setBool(@Nonnull String key, boolean value);

    /** Sets the value at the specified index as type int. Returns true if the value was set successfully. */
    boolean setInt(@Nonnull String key, int value);

    /** Sets the value at the specified index as type double. Returns true if the value was set successfully. */
    boolean setDouble(@Nonnull String key, double value);

    /**
     * Sets the value at the specified index as type string. Returns true if the value was set successfully.
     *
     * @param value may be null
     */
    boolean setString(@Nonnull String key, @Nullable String value);

    /**
     * Sets the value at the specified index as type binary. Returns true if the value was set successfully. If |value|
     * is currently owned by another object then the value will be copied and the |value| reference will not change.
     * Otherwise, ownership will be transferred to this object and the |value| reference will be invalidated.
     */
    boolean setBinary(@Nonnull String key, long value);

    /**
     * Sets the value at the specified index as type dict. Returns true if the value was set successfully. If |value| is
     * currently owned by another object then the value will be copied and the |value| reference will not change.
     * Otherwise, ownership will be transferred to this object and the |value| reference will be invalidated.
     */
    boolean setDictionary(@Nonnull String key, long value);

    /**
     * Sets the value at the specified index as type list. Returns true if the value was set successfully. If |value| is
     * currently owned by another object then the value will be copied and the |value| reference will not change.
     * Otherwise, ownership will be transferred to this object and the |value| reference will be invalidated.
     */
    boolean setList(@Nonnull String key, long value);

    static class NativePeer implements CefDictionaryValue {
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
        public long copy(int excludeEmptyChildren) {
            return N_Copy(nativePtr, excludeEmptyChildren);
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
        public boolean hasKey(String key) {
            return N_HasKey(nativePtr, key);
        }

        @Override
        public boolean getKeys(java.util.List<String> keys) {
            return N_GetKeys(nativePtr, keys);
        }

        @Override
        public boolean remove(String key) {
            return N_Remove(nativePtr, key);
        }

        @Override
        public CefValueType getType(String key) {
            return N_GetType(nativePtr, key);
        }

        @Override
        public long getValue(String key) {
            return N_GetValue(nativePtr, key);
        }

        @Override
        public boolean getBool(String key) {
            return N_GetBool(nativePtr, key);
        }

        @Override
        public int getInt(String key) {
            return N_GetInt(nativePtr, key);
        }

        @Override
        public double getDouble(String key) {
            return N_GetDouble(nativePtr, key);
        }

        @Override
        public Optional<String> getString(String key) {
            return Optional.ofNullable(N_GetString(nativePtr, key));
        }

        @Override
        public long getBinary(String key) {
            return N_GetBinary(nativePtr, key);
        }

        @Override
        public long getDictionary(String key) {
            return N_GetDictionary(nativePtr, key);
        }

        @Override
        public long getList(String key) {
            return N_GetList(nativePtr, key);
        }

        @Override
        public boolean setValue(String key, long value) {
            return N_SetValue(nativePtr, key, value);
        }

        @Override
        public boolean setNull(String key) {
            return N_SetNull(nativePtr, key);
        }

        @Override
        public boolean setBool(String key, boolean value) {
            return N_SetBool(nativePtr, key, value);
        }

        @Override
        public boolean setInt(String key, int value) {
            return N_SetInt(nativePtr, key, value);
        }

        @Override
        public boolean setDouble(String key, double value) {
            return N_SetDouble(nativePtr, key, value);
        }

        @Override
        public boolean setString(String key, String value) {
            return N_SetString(nativePtr, key, value);
        }

        @Override
        public boolean setBinary(String key, long value) {
            return N_SetBinary(nativePtr, key, value);
        }

        @Override
        public boolean setDictionary(String key, long value) {
            return N_SetDictionary(nativePtr, key, value);
        }

        @Override
        public boolean setList(String key, long value) {
            return N_SetList(nativePtr, key, value);
        }

        private native boolean N_IsValid(long self);

        private native boolean N_IsOwned(long self);

        private native boolean N_IsReadOnly(long self);

        private native boolean N_IsSame(long self, long that);

        private native boolean N_IsEqual(long self, long that);

        private native long N_Copy(long self, int excludeEmptyChildren);

        private native long N_GetSize(long self);

        private native boolean N_Clear(long self);

        private native boolean N_HasKey(long self, String key);

        private native boolean N_GetKeys(long self, java.util.List<String> keys);

        private native boolean N_Remove(long self, String key);

        private native CefValueType N_GetType(long self, String key);

        private native long N_GetValue(long self, String key);

        private native boolean N_GetBool(long self, String key);

        private native int N_GetInt(long self, String key);

        private native double N_GetDouble(long self, String key);

        private native String N_GetString(long self, String key);

        private native long N_GetBinary(long self, String key);

        private native long N_GetDictionary(long self, String key);

        private native long N_GetList(long self, String key);

        private native boolean N_SetValue(long self, String key, long value);

        private native boolean N_SetNull(long self, String key);

        private native boolean N_SetBool(long self, String key, boolean value);

        private native boolean N_SetInt(long self, String key, int value);

        private native boolean N_SetDouble(long self, String key, double value);

        private native boolean N_SetString(long self, String key, String value);

        private native boolean N_SetBinary(long self, String key, long value);

        private native boolean N_SetDictionary(long self, String key, long value);

        private native boolean N_SetList(long self, String key, long value);

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
            return "CefDictionaryValue{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
