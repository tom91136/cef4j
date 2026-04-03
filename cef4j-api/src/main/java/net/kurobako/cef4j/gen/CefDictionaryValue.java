// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
// -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import java.util.List;
import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.processing.Generated;

/**
 * Class representing a dictionary value. Can be used on any process and thread.
 *
 * <p>Definition generated from cef_values_capi.h
 *
 * <pre>typedef struct _cef_dictionary_value_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_dictionary_value_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:305</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
public interface CefDictionaryValue extends CefLibraryObject {

    /**
     * Returns {@code true} if this object is valid. This object may become invalid if the underlying data is owned by
     * another object (e.g. list or dictionary) and that other object is then modified or destroyed. Do not call any
     * other methods if this method returns {@code false}.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_valid)(struct _cef_dictionary_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:320</a>
     */
    boolean isValid();

    /**
     * Returns {@code true} if this object is currently owned by another object.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_owned)(struct _cef_dictionary_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:329</a>
     */
    boolean isOwned();

    /**
     * Returns {@code true} if the values of this object are read-only. Some APIs may expose read-only objects.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_read_only)(struct _cef_dictionary_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:335</a>
     */
    boolean isReadOnly();

    /**
     * Returns {@code true} if this object and {@code that} object have the same underlying data. If {@code true}
     * modifications to this object will also affect {@code that} object and vice-versa.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_same)(struct _cef_dictionary_value_t* self, struct _cef_dictionary_value_t* that);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:342</a>
     */
    boolean isSame(@Nullable CefDictionaryValue that);

    /**
     * Returns {@code true} if this object and {@code that} object have an equivalent underlying value but are not
     * necessarily the same object.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_equal)(struct _cef_dictionary_value_t* self, struct _cef_dictionary_value_t* that);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:350</a>
     */
    boolean isEqual(@Nullable CefDictionaryValue that);

    /**
     * Returns a writable copy of this object. If {@code exclude_empty_children} is {@code true} any empty dictionaries
     * or lists will be excluded from the copy.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>
     * cef_dictionary_value_t* (CEF_CALLBACK* copy)(struct _cef_dictionary_value_t* self, int exclude_empty_children);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:357</a>
     */
    Optional<CefDictionaryValue> copy(int excludeEmptyChildren);

    /**
     * Returns the number of values.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>size_t (CEF_CALLBACK* get_size)(struct _cef_dictionary_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:364</a>
     */
    long getSize();

    /**
     * Removes all values. Returns {@code true} on success.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* clear)(struct _cef_dictionary_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:370</a>
     */
    boolean clear();

    /**
     * Returns {@code true} if the current dictionary has a value for the given key.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* has_key)(struct _cef_dictionary_value_t* self, const cef_string_t* key);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:376</a>
     */
    boolean hasKey(@Nullable String key);

    /**
     * Reads all keys for this dictionary into the specified vector.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* get_keys)(struct _cef_dictionary_value_t* self, cef_string_list_t keys);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:382</a>
     */
    boolean getKeys(@Nonnull List<String> keys);

    /**
     * Removes the value at the specified key. Returns {@code true} is the value was removed successfully.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* remove)(struct _cef_dictionary_value_t* self, const cef_string_t* key);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:388</a>
     */
    boolean remove(@Nullable String key);

    /**
     * Returns the value type for the specified key.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>cef_value_type_t (CEF_CALLBACK* get_type)(struct _cef_dictionary_value_t* self, const cef_string_t* key);
     * </pre>
     *
     * @return the result, or {@code MENUITEMTYPE_NONE} for default handling
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:395</a>
     */
    CefValueType getType(@Nullable String key);

    /**
     * Returns the value at the specified key. For simple types the returned value will copy existing data and
     * modifications to the value will not modify this object. For complex types (binary, dictionary and list) the
     * returned value will reference existing data and modifications to the value will modify this object.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>cef_value_t* (CEF_CALLBACK* get_value)(struct _cef_dictionary_value_t* self, const cef_string_t* key);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:401</a>
     */
    Optional<CefValue> getValue(@Nullable String key);

    /**
     * Returns the value at the specified key as type bool.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* get_bool)(struct _cef_dictionary_value_t* self, const cef_string_t* key);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:411</a>
     */
    boolean getBool(@Nullable String key);

    /**
     * Returns the value at the specified key as type int.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* get_int)(struct _cef_dictionary_value_t* self, const cef_string_t* key);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:417</a>
     */
    int getInt(@Nullable String key);

    /**
     * Returns the value at the specified key as type double.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>double (CEF_CALLBACK* get_double)(struct _cef_dictionary_value_t* self, const cef_string_t* key);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:423</a>
     */
    double getDouble(@Nullable String key);

    /**
     * Returns the value at the specified key as type string.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>
     * cef_string_userfree_t (CEF_CALLBACK* get_string)(struct _cef_dictionary_value_t* self, const cef_string_t* key);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:429</a>
     */
    Optional<String> getString(@Nullable String key);

    /**
     * Returns the value at the specified key as type binary. The returned value will reference existing data.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>
     * cef_binary_value_t* (CEF_CALLBACK* get_binary)(struct _cef_dictionary_value_t* self, const cef_string_t* key);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:435</a>
     */
    Optional<CefBinaryValue> getBinary(@Nullable String key);

    /**
     * Returns the value at the specified key as type dictionary. The returned value will reference existing data and
     * modifications to the value will modify this object.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>
     * cef_dictionary_value_t* (CEF_CALLBACK* get_dictionary)(struct _cef_dictionary_value_t* self, const cef_string_t* key);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:442</a>
     */
    Optional<CefDictionaryValue> getDictionary(@Nullable String key);

    /**
     * Returns the value at the specified key as type list. The returned value will reference existing data and
     * modifications to the value will modify this object.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>cef_list_value_t* (CEF_CALLBACK* get_list)(struct _cef_dictionary_value_t* self, const cef_string_t* key);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:450</a>
     */
    Optional<CefListValue> getList(@Nullable String key);

    /**
     * Sets the value at the specified key. Returns {@code true} if the value was set successfully. If {@code value}
     * represents simple data then the underlying data will be copied and modifications to {@code value} will not modify
     * this object. If {@code value} represents complex data (binary, dictionary or list) then the underlying data will
     * be referenced and modifications to {@code value} will modify this object.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* set_value)(struct _cef_dictionary_value_t* self, const cef_string_t* key, struct _cef_value_t* value);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:458</a>
     */
    boolean setValue(@Nullable String key, @Nullable CefValue value);

    /**
     * Sets the value at the specified key as type null. Returns {@code true} if the value was set successfully.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* set_null)(struct _cef_dictionary_value_t* self, const cef_string_t* key);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:469</a>
     */
    boolean setNull(@Nullable String key);

    /**
     * Sets the value at the specified key as type bool. Returns {@code true} if the value was set successfully.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* set_bool)(struct _cef_dictionary_value_t* self, const cef_string_t* key, int value);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:476</a>
     */
    boolean setBool(@Nullable String key, boolean value);

    /**
     * Sets the value at the specified key as type int. Returns {@code true} if the value was set successfully.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* set_int)(struct _cef_dictionary_value_t* self, const cef_string_t* key, int value);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:483</a>
     */
    boolean setInt(@Nullable String key, int value);

    /**
     * Sets the value at the specified key as type double. Returns {@code true} if the value was set successfully.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* set_double)(struct _cef_dictionary_value_t* self, const cef_string_t* key, double value);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:490</a>
     */
    boolean setDouble(@Nullable String key, double value);

    /**
     * Sets the value at the specified key as type string. Returns {@code true} if the value was set successfully.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* set_string)(struct _cef_dictionary_value_t* self, const cef_string_t* key, const cef_string_t* value);
     * </pre>
     *
     * @param value may be null
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:497</a>
     */
    boolean setString(@Nullable String key, @Nullable String value);

    /**
     * Sets the value at the specified key as type binary. Returns {@code true} if the value was set successfully. If
     * {@code value} is currently owned by another object then the value will be copied and the {@code value} reference
     * will not change. Otherwise, ownership will be transferred to this object and the {@code value} reference will be
     * invalidated.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* set_binary)(struct _cef_dictionary_value_t* self, const cef_string_t* key, struct _cef_binary_value_t* value);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:504</a>
     */
    boolean setBinary(@Nullable String key, @Nullable CefBinaryValue value);

    /**
     * Sets the value at the specified key as type dict. Returns {@code true} if the value was set successfully. If
     * {@code value} is currently owned by another object then the value will be copied and the {@code value} reference
     * will not change. Otherwise, ownership will be transferred to this object and the {@code value} reference will be
     * invalidated.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* set_dictionary)(struct _cef_dictionary_value_t* self, const cef_string_t* key, struct _cef_dictionary_value_t* value);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:515</a>
     */
    boolean setDictionary(@Nullable String key, @Nullable CefDictionaryValue value);

    /**
     * Sets the value at the specified key as type list. Returns {@code true} if the value was set successfully. If
     * {@code value} is currently owned by another object then the value will be copied and the {@code value} reference
     * will not change. Otherwise, ownership will be transferred to this object and the {@code value} reference will be
     * invalidated.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* set_list)(struct _cef_dictionary_value_t* self, const cef_string_t* key, struct _cef_list_value_t* value);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:526</a>
     */
    boolean setList(@Nullable String key, @Nullable CefListValue value);
    /**
     * Create a new backing store with allocated memory of {@code byte_length} bytes. The memory is uninitialized. This
     * method must be called on a thread with a valid V8 isolate. The returned object can safely be passed to other
     * threads. Returns {@code null} on failure.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>CEF_EXPORT cef_dictionary_value_t* cef_dictionary_value_create(void);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:445</a>
     */
    static Optional<CefDictionaryValue> create() {
        return Optional.ofNullable(NativePeer.create0());
    }

    final class NativePeer implements CefDictionaryValue, AutoCloseable {
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
            if (closed) throw new IllegalStateException("CefDictionaryValue has been closed");
        }

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefDictionaryValue.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefDictionaryValue 0x{}", Long.toHexString(ptr));
                release0(ptr);
            }
        }

        private static native void release0(long ptr);

        @Override
        public boolean isValid() {
            checkNotClosed();
            return isValid0(nativePtr);
        }

        @Override
        public boolean isOwned() {
            checkNotClosed();
            return isOwned0(nativePtr);
        }

        @Override
        public boolean isReadOnly() {
            checkNotClosed();
            return isReadOnly0(nativePtr);
        }

        @Override
        public boolean isSame(@Nullable CefDictionaryValue that) {
            checkNotClosed();
            CefLibraryObject.requireOpen(that, "CefDictionaryValue");
            return isSame0(nativePtr, that);
        }

        @Override
        public boolean isEqual(@Nullable CefDictionaryValue that) {
            checkNotClosed();
            CefLibraryObject.requireOpen(that, "CefDictionaryValue");
            return isEqual0(nativePtr, that);
        }

        @Override
        public Optional<CefDictionaryValue> copy(int excludeEmptyChildren) {
            checkNotClosed();
            return Optional.ofNullable(copy0(nativePtr, excludeEmptyChildren));
        }

        @Override
        public long getSize() {
            checkNotClosed();
            return getSize0(nativePtr);
        }

        @Override
        public boolean clear() {
            checkNotClosed();
            return clear0(nativePtr);
        }

        @Override
        public boolean hasKey(@Nullable String key) {
            checkNotClosed();
            return hasKey0(nativePtr, key);
        }

        @Override
        public boolean getKeys(@Nonnull List<String> keys) {
            checkNotClosed();
            return getKeys0(nativePtr, keys);
        }

        @Override
        public boolean remove(@Nullable String key) {
            checkNotClosed();
            return remove0(nativePtr, key);
        }

        @Override
        public CefValueType getType(@Nullable String key) {
            checkNotClosed();
            return getType0(nativePtr, key);
        }

        @Override
        public Optional<CefValue> getValue(@Nullable String key) {
            checkNotClosed();
            return Optional.ofNullable(getValue0(nativePtr, key));
        }

        @Override
        public boolean getBool(@Nullable String key) {
            checkNotClosed();
            return getBool0(nativePtr, key);
        }

        @Override
        public int getInt(@Nullable String key) {
            checkNotClosed();
            return getInt0(nativePtr, key);
        }

        @Override
        public double getDouble(@Nullable String key) {
            checkNotClosed();
            return getDouble0(nativePtr, key);
        }

        @Override
        public Optional<String> getString(@Nullable String key) {
            checkNotClosed();
            return Optional.ofNullable(getString0(nativePtr, key));
        }

        @Override
        public Optional<CefBinaryValue> getBinary(@Nullable String key) {
            checkNotClosed();
            return Optional.ofNullable(getBinary0(nativePtr, key));
        }

        @Override
        public Optional<CefDictionaryValue> getDictionary(@Nullable String key) {
            checkNotClosed();
            return Optional.ofNullable(getDictionary0(nativePtr, key));
        }

        @Override
        public Optional<CefListValue> getList(@Nullable String key) {
            checkNotClosed();
            return Optional.ofNullable(getList0(nativePtr, key));
        }

        @Override
        public boolean setValue(@Nullable String key, @Nullable CefValue value) {
            checkNotClosed();
            CefLibraryObject.requireOpen(value, "CefValue");
            return setValue0(nativePtr, key, value);
        }

        @Override
        public boolean setNull(@Nullable String key) {
            checkNotClosed();
            return setNull0(nativePtr, key);
        }

        @Override
        public boolean setBool(@Nullable String key, boolean value) {
            checkNotClosed();
            return setBool0(nativePtr, key, value);
        }

        @Override
        public boolean setInt(@Nullable String key, int value) {
            checkNotClosed();
            return setInt0(nativePtr, key, value);
        }

        @Override
        public boolean setDouble(@Nullable String key, double value) {
            checkNotClosed();
            return setDouble0(nativePtr, key, value);
        }

        @Override
        public boolean setString(@Nullable String key, @Nullable String value) {
            checkNotClosed();
            return setString0(nativePtr, key, value);
        }

        @Override
        public boolean setBinary(@Nullable String key, @Nullable CefBinaryValue value) {
            checkNotClosed();
            CefLibraryObject.requireOpen(value, "CefBinaryValue");
            return setBinary0(nativePtr, key, value);
        }

        @Override
        public boolean setDictionary(@Nullable String key, @Nullable CefDictionaryValue value) {
            checkNotClosed();
            CefLibraryObject.requireOpen(value, "CefDictionaryValue");
            return setDictionary0(nativePtr, key, value);
        }

        @Override
        public boolean setList(@Nullable String key, @Nullable CefListValue value) {
            checkNotClosed();
            CefLibraryObject.requireOpen(value, "CefListValue");
            return setList0(nativePtr, key, value);
        }

        private static native boolean isValid0(long self);

        private static native boolean isOwned0(long self);

        private static native boolean isReadOnly0(long self);

        private static native boolean isSame0(long self, CefDictionaryValue that);

        private static native boolean isEqual0(long self, CefDictionaryValue that);

        private static native CefDictionaryValue copy0(long self, int excludeEmptyChildren);

        private static native long getSize0(long self);

        private static native boolean clear0(long self);

        private static native boolean hasKey0(long self, String key);

        private static native boolean getKeys0(long self, List<String> keys);

        private static native boolean remove0(long self, String key);

        private static native CefValueType getType0(long self, String key);

        private static native CefValue getValue0(long self, String key);

        private static native boolean getBool0(long self, String key);

        private static native int getInt0(long self, String key);

        private static native double getDouble0(long self, String key);

        private static native String getString0(long self, String key);

        private static native CefBinaryValue getBinary0(long self, String key);

        private static native CefDictionaryValue getDictionary0(long self, String key);

        private static native CefListValue getList0(long self, String key);

        private static native boolean setValue0(long self, String key, CefValue value);

        private static native boolean setNull0(long self, String key);

        private static native boolean setBool0(long self, String key, boolean value);

        private static native boolean setInt0(long self, String key, int value);

        private static native boolean setDouble0(long self, String key, double value);

        private static native boolean setString0(long self, String key, String value);

        private static native boolean setBinary0(long self, String key, CefBinaryValue value);

        private static native boolean setDictionary0(long self, String key, CefDictionaryValue value);

        private static native boolean setList0(long self, String key, CefListValue value);

        static native CefDictionaryValue create0();

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
