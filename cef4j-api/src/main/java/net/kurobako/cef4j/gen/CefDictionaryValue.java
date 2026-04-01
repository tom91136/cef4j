// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.util.List;
import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

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
public interface CefDictionaryValue extends CefLibraryObject {

    /**
     * Returns {@code true} if this object is valid. Do not call any other methods if this function returns
     * {@code false}.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_valid)(struct _cef_dictionary_value_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__download__item_8h.html">cef_download_item.h:49</a>
     */
    boolean isValid();

    /**
     * Returns {@code true} if this object is currently owned by another object.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_owned)(struct _cef_dictionary_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:558</a>
     */
    boolean isOwned();

    /**
     * Returns {@code true} if the values of this object are read-only. Some APIs may expose read-only objects.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_read_only)(struct _cef_dictionary_value_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__print__settings_8h.html">cef_print_settings.h:68</a>
     */
    boolean isReadOnly();

    /**
     * Returns {@code true} if this object is pointing to the same handle as {@code that} object.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_same)(struct _cef_dictionary_value_t* self, struct _cef_dictionary_value_t* that);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__dom_8h.html">cef_dom.h:208</a>
     */
    boolean isSame(@Nonnull CefDictionaryValue that);

    /**
     * Returns {@code true} if this object and {@code that} object have an equivalent underlying value but are not
     * necessarily the same object.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_equal)(struct _cef_dictionary_value_t* self, struct _cef_dictionary_value_t* that);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:579</a>
     */
    boolean isEqual(@Nonnull CefDictionaryValue that);

    /**
     * Returns a writable copy of this object.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>
     * cef_dictionary_value_t* (CEF_CALLBACK* copy)(struct _cef_dictionary_value_t* self, int exclude_empty_children);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__command__line_8h.html">cef_command_line.h:90</a>
     */
    Optional<CefDictionaryValue> copy(int excludeEmptyChildren);

    /**
     * Returns the number of values.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>size_t (CEF_CALLBACK* get_size)(struct _cef_dictionary_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:599</a>
     */
    long getSize();

    /**
     * Clears the menu. Returns {@code true} on success.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* clear)(struct _cef_dictionary_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__menu__model_8h.html">cef_menu_model.h:68</a>
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
    boolean hasKey(@Nonnull String key);

    /**
     * Read the keys for the object's values into the specified vector. Integer-based keys will also be returned as
     * strings.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* get_keys)(struct _cef_dictionary_value_t* self, cef_string_list_t keys);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:882</a>
     */
    boolean getKeys(@Nonnull List<String> keys);

    /**
     * Removes the item with the specified {@code command_id}. Returns {@code true} on success.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* remove)(struct _cef_dictionary_value_t* self, const cef_string_t* key);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__menu__model_8h.html">cef_menu_model.h:158</a>
     */
    boolean remove(@Nonnull String key);

    /**
     * Returns the item type for the specified {@code command_id}.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>cef_value_type_t (CEF_CALLBACK* get_type)(struct _cef_dictionary_value_t* self, const cef_string_t* key);
     * </pre>
     *
     * @return the result, or {@code MENUITEMTYPE_NONE} for default handling
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__menu__model_8h.html">cef_menu_model.h:215</a>
     */
    CefValueType getType(@Nonnull String key);

    /**
     * Returns the value of this node.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>cef_value_t* (CEF_CALLBACK* get_value)(struct _cef_dictionary_value_t* self, const cef_string_t* key);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__dom_8h.html">cef_dom.h:221</a>
     */
    Optional<CefValue> getValue(@Nonnull String key);

    /**
     * Returns the value at the specified index as type bool.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* get_bool)(struct _cef_dictionary_value_t* self, const cef_string_t* key);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:633</a>
     */
    boolean getBool(@Nonnull String key);

    /**
     * Returns the value at the specified index as type int.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* get_int)(struct _cef_dictionary_value_t* self, const cef_string_t* key);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:639</a>
     */
    int getInt(@Nonnull String key);

    /**
     * Returns the value at the specified index as type double.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>double (CEF_CALLBACK* get_double)(struct _cef_dictionary_value_t* self, const cef_string_t* key);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:645</a>
     */
    double getDouble(@Nonnull String key);

    /**
     * Returns the value at the specified index as type string.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>
     * cef_string_userfree_t (CEF_CALLBACK* get_string)(struct _cef_dictionary_value_t* self, const cef_string_t* key);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:651</a>
     */
    Optional<String> getString(@Nonnull String key);

    /**
     * Returns the value at the specified index as type binary. The returned value will reference existing data.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>
     * cef_binary_value_t* (CEF_CALLBACK* get_binary)(struct _cef_dictionary_value_t* self, const cef_string_t* key);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:657</a>
     */
    Optional<CefBinaryValue> getBinary(@Nonnull String key);

    /**
     * Returns the value at the specified index as type dictionary. The returned value will reference existing data and
     * modifications to the value will modify this object.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>
     * cef_dictionary_value_t* (CEF_CALLBACK* get_dictionary)(struct _cef_dictionary_value_t* self, const cef_string_t* key);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:664</a>
     */
    Optional<CefDictionaryValue> getDictionary(@Nonnull String key);

    /**
     * Returns the value at the specified index as type list. The returned value will reference existing data and
     * modifications to the value will modify this object.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>cef_list_value_t* (CEF_CALLBACK* get_list)(struct _cef_dictionary_value_t* self, const cef_string_t* key);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:672</a>
     */
    Optional<CefListValue> getList(@Nonnull String key);

    /**
     * Set the value of this node. Returns {@code true} on success.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* set_value)(struct _cef_dictionary_value_t* self, const cef_string_t* key, struct _cef_value_t* value);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__dom_8h.html">cef_dom.h:227</a>
     */
    boolean setValue(@Nonnull String key, @Nonnull CefValue value);

    /**
     * Sets the value at the specified index as type null. Returns {@code true} if the value was set successfully.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* set_null)(struct _cef_dictionary_value_t* self, const cef_string_t* key);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:691</a>
     */
    boolean setNull(@Nonnull String key);

    /**
     * Sets the value at the specified index as type bool. Returns {@code true} if the value was set successfully.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* set_bool)(struct _cef_dictionary_value_t* self, const cef_string_t* key, int value);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:698</a>
     */
    boolean setBool(@Nonnull String key, boolean value);

    /**
     * Sets the value at the specified index as type int. Returns {@code true} if the value was set successfully.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* set_int)(struct _cef_dictionary_value_t* self, const cef_string_t* key, int value);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:705</a>
     */
    boolean setInt(@Nonnull String key, int value);

    /**
     * Sets the value at the specified index as type double. Returns {@code true} if the value was set successfully.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* set_double)(struct _cef_dictionary_value_t* self, const cef_string_t* key, double value);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:712</a>
     */
    boolean setDouble(@Nonnull String key, double value);

    /**
     * Sets the value at the specified index as type string. Returns {@code true} if the value was set successfully.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* set_string)(struct _cef_dictionary_value_t* self, const cef_string_t* key, const cef_string_t* value);
     * </pre>
     *
     * @param value may be null
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:719</a>
     */
    boolean setString(@Nonnull String key, @Nullable String value);

    /**
     * Sets the value at the specified index as type binary. Returns {@code true} if the value was set successfully. If
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
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:726</a>
     */
    boolean setBinary(@Nonnull String key, @Nonnull CefBinaryValue value);

    /**
     * Sets the value at the specified index as type dict. Returns {@code true} if the value was set successfully. If
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
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:736</a>
     */
    boolean setDictionary(@Nonnull String key, @Nonnull CefDictionaryValue value);

    /**
     * Sets the value at the specified index as type list. Returns {@code true} if the value was set successfully. If
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
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:747</a>
     */
    boolean setList(@Nonnull String key, @Nonnull CefListValue value);
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
        return Optional.ofNullable(NativePeer.N_Create());
    }

    final class NativePeer implements CefDictionaryValue, AutoCloseable {
        private final long nativePtr;
        private final java.lang.ref.Cleaner.Cleanable cleanable;

        NativePeer(long ptr) {
            this.nativePtr = ptr;
            this.cleanable = net.kurobako.cef4j.NativeCleaner.INSTANCE.register(this, new Release(ptr));
        }

        @Override
        public void close() {
            cleanable.clean();
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
                N_Release(ptr);
            }
        }

        private static native void N_Release(long ptr);

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
        public boolean isSame(@Nonnull CefDictionaryValue that) {
            return N_IsSame(nativePtr, that);
        }

        @Override
        public boolean isEqual(@Nonnull CefDictionaryValue that) {
            return N_IsEqual(nativePtr, that);
        }

        @Override
        public Optional<CefDictionaryValue> copy(int excludeEmptyChildren) {
            return Optional.ofNullable(N_Copy(nativePtr, excludeEmptyChildren));
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
        public boolean hasKey(@Nonnull String key) {
            return N_HasKey(nativePtr, key);
        }

        @Override
        public boolean getKeys(@Nonnull List<String> keys) {
            return N_GetKeys(nativePtr, keys);
        }

        @Override
        public boolean remove(@Nonnull String key) {
            return N_Remove(nativePtr, key);
        }

        @Override
        public CefValueType getType(@Nonnull String key) {
            return N_GetType(nativePtr, key);
        }

        @Override
        public Optional<CefValue> getValue(@Nonnull String key) {
            return Optional.ofNullable(N_GetValue(nativePtr, key));
        }

        @Override
        public boolean getBool(@Nonnull String key) {
            return N_GetBool(nativePtr, key);
        }

        @Override
        public int getInt(@Nonnull String key) {
            return N_GetInt(nativePtr, key);
        }

        @Override
        public double getDouble(@Nonnull String key) {
            return N_GetDouble(nativePtr, key);
        }

        @Override
        public Optional<String> getString(@Nonnull String key) {
            return Optional.ofNullable(N_GetString(nativePtr, key));
        }

        @Override
        public Optional<CefBinaryValue> getBinary(@Nonnull String key) {
            return Optional.ofNullable(N_GetBinary(nativePtr, key));
        }

        @Override
        public Optional<CefDictionaryValue> getDictionary(@Nonnull String key) {
            return Optional.ofNullable(N_GetDictionary(nativePtr, key));
        }

        @Override
        public Optional<CefListValue> getList(@Nonnull String key) {
            return Optional.ofNullable(N_GetList(nativePtr, key));
        }

        @Override
        public boolean setValue(@Nonnull String key, @Nonnull CefValue value) {
            return N_SetValue(nativePtr, key, value);
        }

        @Override
        public boolean setNull(@Nonnull String key) {
            return N_SetNull(nativePtr, key);
        }

        @Override
        public boolean setBool(@Nonnull String key, boolean value) {
            return N_SetBool(nativePtr, key, value);
        }

        @Override
        public boolean setInt(@Nonnull String key, int value) {
            return N_SetInt(nativePtr, key, value);
        }

        @Override
        public boolean setDouble(@Nonnull String key, double value) {
            return N_SetDouble(nativePtr, key, value);
        }

        @Override
        public boolean setString(@Nonnull String key, @Nullable String value) {
            return N_SetString(nativePtr, key, value);
        }

        @Override
        public boolean setBinary(@Nonnull String key, @Nonnull CefBinaryValue value) {
            return N_SetBinary(nativePtr, key, value);
        }

        @Override
        public boolean setDictionary(@Nonnull String key, @Nonnull CefDictionaryValue value) {
            return N_SetDictionary(nativePtr, key, value);
        }

        @Override
        public boolean setList(@Nonnull String key, @Nonnull CefListValue value) {
            return N_SetList(nativePtr, key, value);
        }

        private static native boolean N_IsValid(long self);

        private static native boolean N_IsOwned(long self);

        private static native boolean N_IsReadOnly(long self);

        private static native boolean N_IsSame(long self, CefDictionaryValue that);

        private static native boolean N_IsEqual(long self, CefDictionaryValue that);

        private static native CefDictionaryValue N_Copy(long self, int excludeEmptyChildren);

        private static native long N_GetSize(long self);

        private static native boolean N_Clear(long self);

        private static native boolean N_HasKey(long self, String key);

        private static native boolean N_GetKeys(long self, List<String> keys);

        private static native boolean N_Remove(long self, String key);

        private static native CefValueType N_GetType(long self, String key);

        private static native CefValue N_GetValue(long self, String key);

        private static native boolean N_GetBool(long self, String key);

        private static native int N_GetInt(long self, String key);

        private static native double N_GetDouble(long self, String key);

        private static native String N_GetString(long self, String key);

        private static native CefBinaryValue N_GetBinary(long self, String key);

        private static native CefDictionaryValue N_GetDictionary(long self, String key);

        private static native CefListValue N_GetList(long self, String key);

        private static native boolean N_SetValue(long self, String key, CefValue value);

        private static native boolean N_SetNull(long self, String key);

        private static native boolean N_SetBool(long self, String key, boolean value);

        private static native boolean N_SetInt(long self, String key, int value);

        private static native boolean N_SetDouble(long self, String key, double value);

        private static native boolean N_SetString(long self, String key, String value);

        private static native boolean N_SetBinary(long self, String key, CefBinaryValue value);

        private static native boolean N_SetDictionary(long self, String key, CefDictionaryValue value);

        private static native boolean N_SetList(long self, String key, CefListValue value);

        static native CefDictionaryValue N_Create();

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
