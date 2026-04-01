// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.nio.ByteBuffer;
import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Class that wraps other data value types. Complex types (binary, dictionary and list) will be referenced but not owned
 * by this object. Can be used on any process and thread.
 *
 * <p>Definition generated from cef_values_capi.h
 *
 * <pre>typedef struct _cef_value_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_value_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:51</a>
 */
public interface CefValue extends CefLibraryObject {

    /**
     * Returns {@code true} if this object is valid. Do not call any other methods if this function returns
     * {@code false}.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_valid)(struct _cef_value_t* self);</pre>
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
     * <pre>int (CEF_CALLBACK* is_owned)(struct _cef_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:558</a>
     */
    boolean isOwned();

    /**
     * Returns {@code true} if the values of this object are read-only. Some APIs may expose read-only objects.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_read_only)(struct _cef_value_t* self);</pre>
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
     * <pre>int (CEF_CALLBACK* is_same)(struct _cef_value_t* self, struct _cef_value_t* that);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__dom_8h.html">cef_dom.h:208</a>
     */
    boolean isSame(@Nonnull CefValue that);

    /**
     * Returns {@code true} if this object and {@code that} object have an equivalent underlying value but are not
     * necessarily the same object.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_equal)(struct _cef_value_t* self, struct _cef_value_t* that);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:579</a>
     */
    boolean isEqual(@Nonnull CefValue that);

    /**
     * Returns a writable copy of this object.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>cef_value_t* (CEF_CALLBACK* copy)(struct _cef_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__command__line_8h.html">cef_command_line.h:90</a>
     */
    Optional<CefValue> copy();

    /**
     * Returns the item type for the specified {@code command_id}.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>cef_value_type_t (CEF_CALLBACK* get_type)(struct _cef_value_t* self);</pre>
     *
     * @return the result, or {@code MENUITEMTYPE_NONE} for default handling
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__menu__model_8h.html">cef_menu_model.h:215</a>
     */
    CefValueType getType();

    /**
     * Returns the value at the specified index as type bool.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* get_bool)(struct _cef_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:633</a>
     */
    boolean getBool();

    /**
     * Returns the value at the specified index as type int.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* get_int)(struct _cef_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:639</a>
     */
    int getInt();

    /**
     * Returns the value at the specified index as type double.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>double (CEF_CALLBACK* get_double)(struct _cef_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:645</a>
     */
    double getDouble();

    /**
     * Returns the value at the specified index as type string.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_string)(struct _cef_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:651</a>
     */
    Optional<String> getString();

    /**
     * Returns the value at the specified index as type binary. The returned value will reference existing data.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>cef_binary_value_t* (CEF_CALLBACK* get_binary)(struct _cef_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:657</a>
     */
    Optional<CefBinaryValue> getBinary();

    /**
     * Returns the value at the specified index as type dictionary. The returned value will reference existing data and
     * modifications to the value will modify this object.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>cef_dictionary_value_t* (CEF_CALLBACK* get_dictionary)(struct _cef_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:664</a>
     */
    Optional<CefDictionaryValue> getDictionary();

    /**
     * Returns the value at the specified index as type list. The returned value will reference existing data and
     * modifications to the value will modify this object.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>cef_list_value_t* (CEF_CALLBACK* get_list)(struct _cef_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:672</a>
     */
    Optional<CefListValue> getList();

    /**
     * Sets the value at the specified index as type null. Returns {@code true} if the value was set successfully.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* set_null)(struct _cef_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:691</a>
     */
    boolean setNull();

    /**
     * Sets the value at the specified index as type bool. Returns {@code true} if the value was set successfully.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* set_bool)(struct _cef_value_t* self, int value);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:698</a>
     */
    boolean setBool(boolean value);

    /**
     * Sets the value at the specified index as type int. Returns {@code true} if the value was set successfully.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* set_int)(struct _cef_value_t* self, int value);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:705</a>
     */
    boolean setInt(int value);

    /**
     * Sets the value at the specified index as type double. Returns {@code true} if the value was set successfully.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* set_double)(struct _cef_value_t* self, double value);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:712</a>
     */
    boolean setDouble(double value);

    /**
     * Sets the value at the specified index as type string. Returns {@code true} if the value was set successfully.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* set_string)(struct _cef_value_t* self, const cef_string_t* value);</pre>
     *
     * @param value may be null
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:719</a>
     */
    boolean setString(@Nullable String value);

    /**
     * Sets the value at the specified index as type binary. Returns {@code true} if the value was set successfully. If
     * {@code value} is currently owned by another object then the value will be copied and the {@code value} reference
     * will not change. Otherwise, ownership will be transferred to this object and the {@code value} reference will be
     * invalidated.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* set_binary)(struct _cef_value_t* self, struct _cef_binary_value_t* value);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:726</a>
     */
    boolean setBinary(@Nonnull CefBinaryValue value);

    /**
     * Sets the value at the specified index as type dict. Returns {@code true} if the value was set successfully. If
     * {@code value} is currently owned by another object then the value will be copied and the {@code value} reference
     * will not change. Otherwise, ownership will be transferred to this object and the {@code value} reference will be
     * invalidated.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* set_dictionary)(struct _cef_value_t* self, struct _cef_dictionary_value_t* value);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:736</a>
     */
    boolean setDictionary(@Nonnull CefDictionaryValue value);

    /**
     * Sets the value at the specified index as type list. Returns {@code true} if the value was set successfully. If
     * {@code value} is currently owned by another object then the value will be copied and the {@code value} reference
     * will not change. Otherwise, ownership will be transferred to this object and the {@code value} reference will be
     * invalidated.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* set_list)(struct _cef_value_t* self, struct _cef_list_value_t* value);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:747</a>
     */
    boolean setList(@Nonnull CefListValue value);
    /**
     * Create a new backing store with allocated memory of {@code byte_length} bytes. The memory is uninitialized. This
     * method must be called on a thread with a valid V8 isolate. The returned object can safely be passed to other
     * threads. Returns {@code null} on failure.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>CEF_EXPORT cef_value_t* cef_value_create(void);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:445</a>
     */
    static Optional<CefValue> create() {
        return Optional.ofNullable(NativePeer.N_Create());
    }

    static Optional<CefValue> parseJson(@Nonnull String jsonString, @Nonnull CefJsonParserOptions options) {
        return Optional.ofNullable(NativePeer.N_ParseJson(jsonString, options));
    }

    static Optional<CefValue> parseJsonBuffer(@Nonnull ByteBuffer json, @Nonnull CefJsonParserOptions options) {
        return Optional.ofNullable(NativePeer.N_ParseJsonBuffer(json, options));
    }

    static Optional<CefValue> parseJsonandReturnError(
            @Nonnull String jsonString, @Nonnull CefJsonParserOptions options, @Nonnull String errorMsgOut) {
        return Optional.ofNullable(NativePeer.N_ParseJsonandReturnError(jsonString, options, errorMsgOut));
    }

    final class NativePeer implements CefValue, AutoCloseable {
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

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefValue.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefValue 0x{}", Long.toHexString(ptr));
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
        public boolean isSame(@Nonnull CefValue that) {
            return N_IsSame(nativePtr, that);
        }

        @Override
        public boolean isEqual(@Nonnull CefValue that) {
            return N_IsEqual(nativePtr, that);
        }

        @Override
        public Optional<CefValue> copy() {
            return Optional.ofNullable(N_Copy(nativePtr));
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
        public Optional<CefBinaryValue> getBinary() {
            return Optional.ofNullable(N_GetBinary(nativePtr));
        }

        @Override
        public Optional<CefDictionaryValue> getDictionary() {
            return Optional.ofNullable(N_GetDictionary(nativePtr));
        }

        @Override
        public Optional<CefListValue> getList() {
            return Optional.ofNullable(N_GetList(nativePtr));
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
        public boolean setString(@Nullable String value) {
            return N_SetString(nativePtr, value);
        }

        @Override
        public boolean setBinary(@Nonnull CefBinaryValue value) {
            return N_SetBinary(nativePtr, value);
        }

        @Override
        public boolean setDictionary(@Nonnull CefDictionaryValue value) {
            return N_SetDictionary(nativePtr, value);
        }

        @Override
        public boolean setList(@Nonnull CefListValue value) {
            return N_SetList(nativePtr, value);
        }

        private static native boolean N_IsValid(long self);

        private static native boolean N_IsOwned(long self);

        private static native boolean N_IsReadOnly(long self);

        private static native boolean N_IsSame(long self, CefValue that);

        private static native boolean N_IsEqual(long self, CefValue that);

        private static native CefValue N_Copy(long self);

        private static native CefValueType N_GetType(long self);

        private static native boolean N_GetBool(long self);

        private static native int N_GetInt(long self);

        private static native double N_GetDouble(long self);

        private static native String N_GetString(long self);

        private static native CefBinaryValue N_GetBinary(long self);

        private static native CefDictionaryValue N_GetDictionary(long self);

        private static native CefListValue N_GetList(long self);

        private static native boolean N_SetNull(long self);

        private static native boolean N_SetBool(long self, boolean value);

        private static native boolean N_SetInt(long self, int value);

        private static native boolean N_SetDouble(long self, double value);

        private static native boolean N_SetString(long self, String value);

        private static native boolean N_SetBinary(long self, CefBinaryValue value);

        private static native boolean N_SetDictionary(long self, CefDictionaryValue value);

        private static native boolean N_SetList(long self, CefListValue value);

        static native CefValue N_Create();

        static native CefValue N_ParseJson(String jsonString, CefJsonParserOptions options);

        static native CefValue N_ParseJsonBuffer(ByteBuffer json, CefJsonParserOptions options);

        static native CefValue N_ParseJsonandReturnError(
                String jsonString, CefJsonParserOptions options, String errorMsgOut);

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
