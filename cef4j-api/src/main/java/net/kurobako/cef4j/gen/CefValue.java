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
     * Returns {@code true} if the underlying data is valid. This will always be {@code true} for simple types. For
     * complex types (binary, dictionary and list) the underlying data may become invalid if owned by another object
     * (e.g. list or dictionary) and that other object is then modified or destroyed. This value object can be re-used
     * by calling Set*() even if the underlying data is invalid.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_valid)(struct _cef_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:65</a>
     */
    boolean isValid();

    /**
     * Returns {@code true} if the underlying data is owned by another object.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_owned)(struct _cef_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:76</a>
     */
    boolean isOwned();

    /**
     * Returns {@code true} if the underlying data is read-only. Some APIs may expose read-only objects.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_read_only)(struct _cef_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:82</a>
     */
    boolean isReadOnly();

    /**
     * Returns {@code true} if this object and {@code that} object have the same underlying data. If {@code true}
     * modifications to this object will also affect {@code that} object and vice-versa.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_same)(struct _cef_value_t* self, struct _cef_value_t* that);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:89</a>
     */
    boolean isSame(@Nullable CefValue that);

    /**
     * Returns {@code true} if this object and {@code that} object have an equivalent underlying value but are not
     * necessarily the same object.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_equal)(struct _cef_value_t* self, struct _cef_value_t* that);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:97</a>
     */
    boolean isEqual(@Nullable CefValue that);

    /**
     * Returns a copy of this object. The underlying data will also be copied.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>cef_value_t* (CEF_CALLBACK* copy)(struct _cef_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:104</a>
     */
    Optional<CefValue> copy();

    /**
     * Returns the underlying value type.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>cef_value_type_t (CEF_CALLBACK* get_type)(struct _cef_value_t* self);</pre>
     *
     * @return the result, or {@code MENUITEMTYPE_NONE} for default handling
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:110</a>
     */
    CefValueType getType();

    /**
     * Returns the underlying value as type bool.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* get_bool)(struct _cef_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:116</a>
     */
    boolean getBool();

    /**
     * Returns the underlying value as type int.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* get_int)(struct _cef_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:122</a>
     */
    int getInt();

    /**
     * Returns the underlying value as type double.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>double (CEF_CALLBACK* get_double)(struct _cef_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:128</a>
     */
    double getDouble();

    /**
     * Returns the underlying value as type string.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_string)(struct _cef_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:134</a>
     */
    Optional<String> getString();

    /**
     * Returns the underlying value as type binary. The returned reference may become invalid if the value is owned by
     * another object or if ownership is transferred to another object in the future. To maintain a reference to the
     * value after assigning ownership to a dictionary or list pass this object to the SetValue() method instead of
     * passing the returned reference to SetBinary().
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>cef_binary_value_t* (CEF_CALLBACK* get_binary)(struct _cef_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:140</a>
     */
    Optional<CefBinaryValue> getBinary();

    /**
     * Returns the underlying value as type dictionary. The returned reference may become invalid if the value is owned
     * by another object or if ownership is transferred to another object in the future. To maintain a reference to the
     * value after assigning ownership to a dictionary or list pass this object to the SetValue() method instead of
     * passing the returned reference to SetDictionary().
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>cef_dictionary_value_t* (CEF_CALLBACK* get_dictionary)(struct _cef_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:151</a>
     */
    Optional<CefDictionaryValue> getDictionary();

    /**
     * Returns the underlying value as type list. The returned reference may become invalid if the value is owned by
     * another object or if ownership is transferred to another object in the future. To maintain a reference to the
     * value after assigning ownership to a dictionary or list pass this object to the SetValue() method instead of
     * passing the returned reference to SetList().
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>cef_list_value_t* (CEF_CALLBACK* get_list)(struct _cef_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:162</a>
     */
    Optional<CefListValue> getList();

    /**
     * Sets the underlying value as type null. Returns {@code true} if the value was set successfully.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* set_null)(struct _cef_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:173</a>
     */
    boolean setNull();

    /**
     * Sets the underlying value as type bool. Returns {@code true} if the value was set successfully.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* set_bool)(struct _cef_value_t* self, int value);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:180</a>
     */
    boolean setBool(boolean value);

    /**
     * Sets the underlying value as type int. Returns {@code true} if the value was set successfully.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* set_int)(struct _cef_value_t* self, int value);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:187</a>
     */
    boolean setInt(int value);

    /**
     * Sets the underlying value as type double. Returns {@code true} if the value was set successfully.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* set_double)(struct _cef_value_t* self, double value);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:194</a>
     */
    boolean setDouble(double value);

    /**
     * Sets the underlying value as type string. Returns {@code true} if the value was set successfully.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* set_string)(struct _cef_value_t* self, const cef_string_t* value);</pre>
     *
     * @param value may be null
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:201</a>
     */
    boolean setString(@Nullable String value);

    /**
     * Sets the underlying value as type binary. Returns {@code true} if the value was set successfully. This object
     * keeps a reference to {@code value} and ownership of the underlying data remains unchanged.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* set_binary)(struct _cef_value_t* self, struct _cef_binary_value_t* value);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:208</a>
     */
    boolean setBinary(@Nullable CefBinaryValue value);

    /**
     * Sets the underlying value as type dict. Returns {@code true} if the value was set successfully. This object keeps
     * a reference to {@code value} and ownership of the underlying data remains unchanged.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* set_dictionary)(struct _cef_value_t* self, struct _cef_dictionary_value_t* value);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:216</a>
     */
    boolean setDictionary(@Nullable CefDictionaryValue value);

    /**
     * Sets the underlying value as type list. Returns {@code true} if the value was set successfully. This object keeps
     * a reference to {@code value} and ownership of the underlying data remains unchanged.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* set_list)(struct _cef_value_t* self, struct _cef_list_value_t* value);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:224</a>
     */
    boolean setList(@Nullable CefListValue value);
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

    static Optional<CefValue> parseJson(@Nullable String jsonString, @Nonnull CefJsonParserOptions options) {
        return Optional.ofNullable(NativePeer.N_ParseJson(jsonString, options));
    }

    /**
     * Parses the specified UTF8-encoded {@code json} buffer of size {@code json_size} and returns a dictionary or list
     * representation. If JSON parsing fails this method returns {@code null}.
     *
     * <p>Definition generated from cef_parser_capi.h
     *
     * <pre>
     * CEF_EXPORT cef_value_t* cef_parse_json_buffer(const void* json, size_t json_size, cef_json_parser_options_t options);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__parser_8h.html">cef_parser.h:145</a>
     */
    static Optional<CefValue> parseJsonBuffer(@Nonnull ByteBuffer json, @Nonnull CefJsonParserOptions options) {
        return Optional.ofNullable(NativePeer.N_ParseJsonBuffer(json, options));
    }

    static Optional<CefValue> parseJsonandReturnError(
            @Nullable String jsonString, @Nonnull CefJsonParserOptions options, @Nullable String errorMsgOut) {
        return Optional.ofNullable(NativePeer.N_ParseJsonandReturnError(jsonString, options, errorMsgOut));
    }

    final class NativePeer implements CefValue, AutoCloseable {
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
            if (closed) throw new IllegalStateException("CefValue has been closed");
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
        public boolean isSame(@Nullable CefValue that) {
            checkNotClosed();
            CefLibraryObject.requireOpen(that, "CefValue");
            return N_IsSame(nativePtr, that);
        }

        @Override
        public boolean isEqual(@Nullable CefValue that) {
            checkNotClosed();
            CefLibraryObject.requireOpen(that, "CefValue");
            return N_IsEqual(nativePtr, that);
        }

        @Override
        public Optional<CefValue> copy() {
            checkNotClosed();
            return Optional.ofNullable(N_Copy(nativePtr));
        }

        @Override
        public CefValueType getType() {
            checkNotClosed();
            return N_GetType(nativePtr);
        }

        @Override
        public boolean getBool() {
            checkNotClosed();
            return N_GetBool(nativePtr);
        }

        @Override
        public int getInt() {
            checkNotClosed();
            return N_GetInt(nativePtr);
        }

        @Override
        public double getDouble() {
            checkNotClosed();
            return N_GetDouble(nativePtr);
        }

        @Override
        public Optional<String> getString() {
            checkNotClosed();
            return Optional.ofNullable(N_GetString(nativePtr));
        }

        @Override
        public Optional<CefBinaryValue> getBinary() {
            checkNotClosed();
            return Optional.ofNullable(N_GetBinary(nativePtr));
        }

        @Override
        public Optional<CefDictionaryValue> getDictionary() {
            checkNotClosed();
            return Optional.ofNullable(N_GetDictionary(nativePtr));
        }

        @Override
        public Optional<CefListValue> getList() {
            checkNotClosed();
            return Optional.ofNullable(N_GetList(nativePtr));
        }

        @Override
        public boolean setNull() {
            checkNotClosed();
            return N_SetNull(nativePtr);
        }

        @Override
        public boolean setBool(boolean value) {
            checkNotClosed();
            return N_SetBool(nativePtr, value);
        }

        @Override
        public boolean setInt(int value) {
            checkNotClosed();
            return N_SetInt(nativePtr, value);
        }

        @Override
        public boolean setDouble(double value) {
            checkNotClosed();
            return N_SetDouble(nativePtr, value);
        }

        @Override
        public boolean setString(@Nullable String value) {
            checkNotClosed();
            return N_SetString(nativePtr, value);
        }

        @Override
        public boolean setBinary(@Nullable CefBinaryValue value) {
            checkNotClosed();
            CefLibraryObject.requireOpen(value, "CefBinaryValue");
            return N_SetBinary(nativePtr, value);
        }

        @Override
        public boolean setDictionary(@Nullable CefDictionaryValue value) {
            checkNotClosed();
            CefLibraryObject.requireOpen(value, "CefDictionaryValue");
            return N_SetDictionary(nativePtr, value);
        }

        @Override
        public boolean setList(@Nullable CefListValue value) {
            checkNotClosed();
            CefLibraryObject.requireOpen(value, "CefListValue");
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
