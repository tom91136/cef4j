// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.util.Optional;
import javax.annotation.Nullable;

/**
 * Class representing a list value. Can be used on any process and thread.
 *
 * <p>Definition generated from cef_values_capi.h
 *
 * <pre>typedef struct _cef_list_value_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_list_value_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:537</a>
 */
public interface CefListValue extends CefLibraryObject {

    /**
     * Returns {@code true} if this object is valid. This object may become invalid if the underlying data is owned by
     * another object (e.g. list or dictionary) and that other object is then modified or destroyed. Do not call any
     * other methods if this method returns {@code false}.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_valid)(struct _cef_list_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:549</a>
     */
    boolean isValid();

    /**
     * Returns {@code true} if this object is currently owned by another object.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_owned)(struct _cef_list_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:558</a>
     */
    boolean isOwned();

    /**
     * Returns {@code true} if the values of this object are read-only. Some APIs may expose read-only objects.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_read_only)(struct _cef_list_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:564</a>
     */
    boolean isReadOnly();

    /**
     * Returns {@code true} if this object and {@code that} object have the same underlying data. If {@code true}
     * modifications to this object will also affect {@code that} object and vice-versa.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_same)(struct _cef_list_value_t* self, struct _cef_list_value_t* that);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:571</a>
     */
    boolean isSame(@Nullable CefListValue that);

    /**
     * Returns {@code true} if this object and {@code that} object have an equivalent underlying value but are not
     * necessarily the same object.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_equal)(struct _cef_list_value_t* self, struct _cef_list_value_t* that);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:579</a>
     */
    boolean isEqual(@Nullable CefListValue that);

    /**
     * Returns a writable copy of this object.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>cef_list_value_t* (CEF_CALLBACK* copy)(struct _cef_list_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:586</a>
     */
    Optional<CefListValue> copy();

    /**
     * Sets the number of values. If the number of values is expanded all new value slots will default to type null.
     * Returns {@code true} on success.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* set_size)(struct _cef_list_value_t* self, size_t size);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:592</a>
     */
    boolean setSize(long size);

    /**
     * Returns the number of values.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>size_t (CEF_CALLBACK* get_size)(struct _cef_list_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:599</a>
     */
    long getSize();

    /**
     * Removes all values. Returns {@code true} on success.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* clear)(struct _cef_list_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:605</a>
     */
    boolean clear();

    /**
     * Removes the value at the specified index.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* remove)(struct _cef_list_value_t* self, size_t index);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:611</a>
     */
    boolean remove(long index);

    /**
     * Returns the value type at the specified index.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>cef_value_type_t (CEF_CALLBACK* get_type)(struct _cef_list_value_t* self, size_t index);</pre>
     *
     * @return the result, or {@code MENUITEMTYPE_NONE} for default handling
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:617</a>
     */
    CefValueType getType(long index);

    /**
     * Returns the value at the specified index. For simple types the returned value will copy existing data and
     * modifications to the value will not modify this object. For complex types (binary, dictionary and list) the
     * returned value will reference existing data and modifications to the value will modify this object.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>cef_value_t* (CEF_CALLBACK* get_value)(struct _cef_list_value_t* self, size_t index);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:623</a>
     */
    Optional<CefValue> getValue(long index);

    /**
     * Returns the value at the specified index as type bool.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* get_bool)(struct _cef_list_value_t* self, size_t index);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:633</a>
     */
    boolean getBool(long index);

    /**
     * Returns the value at the specified index as type int.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* get_int)(struct _cef_list_value_t* self, size_t index);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:639</a>
     */
    int getInt(long index);

    /**
     * Returns the value at the specified index as type double.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>double (CEF_CALLBACK* get_double)(struct _cef_list_value_t* self, size_t index);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:645</a>
     */
    double getDouble(long index);

    /**
     * Returns the value at the specified index as type dictionary. The returned value will reference existing data and
     * modifications to the value will modify this object.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>cef_dictionary_value_t* (CEF_CALLBACK* get_dictionary)(struct _cef_list_value_t* self, size_t index);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:664</a>
     */
    Optional<CefDictionaryValue> getDictionary(long index);

    /**
     * Sets the value at the specified index. Returns {@code true} if the value was set successfully. If {@code value}
     * represents simple data then the underlying data will be copied and modifications to {@code value} will not modify
     * this object. If {@code value} represents complex data (binary, dictionary or list) then the underlying data will
     * be referenced and modifications to {@code value} will modify this object.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* set_value)(struct _cef_list_value_t* self, size_t index, struct _cef_value_t* value);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:680</a>
     */
    boolean setValue(long index, @Nullable CefValue value);

    /**
     * Sets the value at the specified index as type null. Returns {@code true} if the value was set successfully.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* set_null)(struct _cef_list_value_t* self, size_t index);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:691</a>
     */
    boolean setNull(long index);

    /**
     * Sets the value at the specified index as type bool. Returns {@code true} if the value was set successfully.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* set_bool)(struct _cef_list_value_t* self, size_t index, int value);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:698</a>
     */
    boolean setBool(long index, boolean value);

    /**
     * Sets the value at the specified index as type int. Returns {@code true} if the value was set successfully.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* set_int)(struct _cef_list_value_t* self, size_t index, int value);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:705</a>
     */
    boolean setInt(long index, int value);

    /**
     * Sets the value at the specified index as type double. Returns {@code true} if the value was set successfully.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* set_double)(struct _cef_list_value_t* self, size_t index, double value);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:712</a>
     */
    boolean setDouble(long index, double value);

    /**
     * Sets the value at the specified index as type string. Returns {@code true} if the value was set successfully.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* set_string)(struct _cef_list_value_t* self, size_t index, const cef_string_t* value);
     * </pre>
     *
     * @param value may be null
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:719</a>
     */
    boolean setString(long index, @Nullable String value);

    /**
     * Sets the value at the specified index as type binary. Returns {@code true} if the value was set successfully. If
     * {@code value} is currently owned by another object then the value will be copied and the {@code value} reference
     * will not change. Otherwise, ownership will be transferred to this object and the {@code value} reference will be
     * invalidated.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* set_binary)(struct _cef_list_value_t* self, size_t index, struct _cef_binary_value_t* value);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:726</a>
     */
    boolean setBinary(long index, @Nullable CefBinaryValue value);

    /**
     * Sets the value at the specified index as type dict. Returns {@code true} if the value was set successfully. If
     * {@code value} is currently owned by another object then the value will be copied and the {@code value} reference
     * will not change. Otherwise, ownership will be transferred to this object and the {@code value} reference will be
     * invalidated.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* set_dictionary)(struct _cef_list_value_t* self, size_t index, struct _cef_dictionary_value_t* value);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:736</a>
     */
    boolean setDictionary(long index, @Nullable CefDictionaryValue value);

    /**
     * Sets the value at the specified index as type list. Returns {@code true} if the value was set successfully. If
     * {@code value} is currently owned by another object then the value will be copied and the {@code value} reference
     * will not change. Otherwise, ownership will be transferred to this object and the {@code value} reference will be
     * invalidated.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* set_list)(struct _cef_list_value_t* self, size_t index, struct _cef_list_value_t* value);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:747</a>
     */
    boolean setList(long index, @Nullable CefListValue value);
    /**
     * Create a new backing store with allocated memory of {@code byte_length} bytes. The memory is uninitialized. This
     * method must be called on a thread with a valid V8 isolate. The returned object can safely be passed to other
     * threads. Returns {@code null} on failure.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>CEF_EXPORT cef_list_value_t* cef_list_value_create(void);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:445</a>
     */
    static Optional<CefListValue> create() {
        return Optional.ofNullable(NativePeer.N_Create());
    }

    final class NativePeer implements CefListValue, AutoCloseable {
        private final long nativePtr;
        private final java.lang.ref.Cleaner.Cleanable cleanable;
        private volatile boolean closed;

        NativePeer(long ptr) {
            this.nativePtr = ptr;
            this.cleanable = net.kurobako.cef4j.NativeCleaner.INSTANCE.register(this, new Release(ptr));
        }

        @Override
        public void close() {
            closed = true;
            cleanable.clean();
        }

        @Override
        public boolean isClosed() {
            return closed;
        }

        private void checkNotClosed() {
            if (closed) throw new IllegalStateException("CefListValue has been closed");
        }

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefListValue.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefListValue 0x{}", Long.toHexString(ptr));
                N_Release(ptr);
            }
        }

        private static native void N_Release(long ptr);

        @Override
        public boolean isValid() {
            checkNotClosed();
            return N_IsValid(nativePtr);
        }

        @Override
        public boolean isOwned() {
            checkNotClosed();
            return N_IsOwned(nativePtr);
        }

        @Override
        public boolean isReadOnly() {
            checkNotClosed();
            return N_IsReadOnly(nativePtr);
        }

        @Override
        public boolean isSame(@Nullable CefListValue that) {
            checkNotClosed();
            CefLibraryObject.requireOpen(that, "CefListValue");
            return N_IsSame(nativePtr, that);
        }

        @Override
        public boolean isEqual(@Nullable CefListValue that) {
            checkNotClosed();
            CefLibraryObject.requireOpen(that, "CefListValue");
            return N_IsEqual(nativePtr, that);
        }

        @Override
        public Optional<CefListValue> copy() {
            checkNotClosed();
            return Optional.ofNullable(N_Copy(nativePtr));
        }

        @Override
        public boolean setSize(long size) {
            checkNotClosed();
            return N_SetSize(nativePtr, size);
        }

        @Override
        public long getSize() {
            checkNotClosed();
            return N_GetSize(nativePtr);
        }

        @Override
        public boolean clear() {
            checkNotClosed();
            return N_Clear(nativePtr);
        }

        @Override
        public boolean remove(long index) {
            checkNotClosed();
            return N_Remove(nativePtr, index);
        }

        @Override
        public CefValueType getType(long index) {
            checkNotClosed();
            return N_GetType(nativePtr, index);
        }

        @Override
        public Optional<CefValue> getValue(long index) {
            checkNotClosed();
            return Optional.ofNullable(N_GetValue(nativePtr, index));
        }

        @Override
        public boolean getBool(long index) {
            checkNotClosed();
            return N_GetBool(nativePtr, index);
        }

        @Override
        public int getInt(long index) {
            checkNotClosed();
            return N_GetInt(nativePtr, index);
        }

        @Override
        public double getDouble(long index) {
            checkNotClosed();
            return N_GetDouble(nativePtr, index);
        }

        @Override
        public Optional<CefDictionaryValue> getDictionary(long index) {
            checkNotClosed();
            return Optional.ofNullable(N_GetDictionary(nativePtr, index));
        }

        @Override
        public boolean setValue(long index, @Nullable CefValue value) {
            checkNotClosed();
            CefLibraryObject.requireOpen(value, "CefValue");
            return N_SetValue(nativePtr, index, value);
        }

        @Override
        public boolean setNull(long index) {
            checkNotClosed();
            return N_SetNull(nativePtr, index);
        }

        @Override
        public boolean setBool(long index, boolean value) {
            checkNotClosed();
            return N_SetBool(nativePtr, index, value);
        }

        @Override
        public boolean setInt(long index, int value) {
            checkNotClosed();
            return N_SetInt(nativePtr, index, value);
        }

        @Override
        public boolean setDouble(long index, double value) {
            checkNotClosed();
            return N_SetDouble(nativePtr, index, value);
        }

        @Override
        public boolean setString(long index, @Nullable String value) {
            checkNotClosed();
            return N_SetString(nativePtr, index, value);
        }

        @Override
        public boolean setBinary(long index, @Nullable CefBinaryValue value) {
            checkNotClosed();
            CefLibraryObject.requireOpen(value, "CefBinaryValue");
            return N_SetBinary(nativePtr, index, value);
        }

        @Override
        public boolean setDictionary(long index, @Nullable CefDictionaryValue value) {
            checkNotClosed();
            CefLibraryObject.requireOpen(value, "CefDictionaryValue");
            return N_SetDictionary(nativePtr, index, value);
        }

        @Override
        public boolean setList(long index, @Nullable CefListValue value) {
            checkNotClosed();
            CefLibraryObject.requireOpen(value, "CefListValue");
            return N_SetList(nativePtr, index, value);
        }

        private static native boolean N_IsValid(long self);

        private static native boolean N_IsOwned(long self);

        private static native boolean N_IsReadOnly(long self);

        private static native boolean N_IsSame(long self, CefListValue that);

        private static native boolean N_IsEqual(long self, CefListValue that);

        private static native CefListValue N_Copy(long self);

        private static native boolean N_SetSize(long self, long size);

        private static native long N_GetSize(long self);

        private static native boolean N_Clear(long self);

        private static native boolean N_Remove(long self, long index);

        private static native CefValueType N_GetType(long self, long index);

        private static native CefValue N_GetValue(long self, long index);

        private static native boolean N_GetBool(long self, long index);

        private static native int N_GetInt(long self, long index);

        private static native double N_GetDouble(long self, long index);

        private static native CefDictionaryValue N_GetDictionary(long self, long index);

        private static native boolean N_SetValue(long self, long index, CefValue value);

        private static native boolean N_SetNull(long self, long index);

        private static native boolean N_SetBool(long self, long index, boolean value);

        private static native boolean N_SetInt(long self, long index, int value);

        private static native boolean N_SetDouble(long self, long index, double value);

        private static native boolean N_SetString(long self, long index, String value);

        private static native boolean N_SetBinary(long self, long index, CefBinaryValue value);

        private static native boolean N_SetDictionary(long self, long index, CefDictionaryValue value);

        private static native boolean N_SetList(long self, long index, CefListValue value);

        static native CefListValue N_Create();

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
