// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import java.nio.ByteBuffer;
import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Class that wraps other data value types. Complex types (binary, dictionary and list) will be referenced but not owned by this object. Can be used on any process and thread.
 * <p>Definition generated from cef_values_capi.h
 * <pre>typedef struct _cef_value_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_value_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:51</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public interface CefValue extends CefLibraryObject {

    /**
     * Returns {@code true} if the underlying data is valid. This will always be {@code true} for simple types. For complex types (binary, dictionary and list) the underlying data may become invalid if owned by another object (e.g. list or dictionary) and that other object is then modified or destroyed. This value object can be re-used by calling Set*() even if the underlying data is invalid.
     * <p>Definition generated from cef_values_capi.h
     * <pre>int (CEF_CALLBACK* is_valid)(struct _cef_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:65</a>
     */
    boolean isValid();

    /**
     * Returns {@code true} if the underlying data is owned by another object.
     * <p>Definition generated from cef_values_capi.h
     * <pre>int (CEF_CALLBACK* is_owned)(struct _cef_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:76</a>
     */
    boolean isOwned();

    /**
     * Returns {@code true} if the underlying data is read-only. Some APIs may expose read-only objects.
     * <p>Definition generated from cef_values_capi.h
     * <pre>int (CEF_CALLBACK* is_read_only)(struct _cef_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:82</a>
     */
    boolean isReadOnly();

    /**
     * Returns {@code true} if this object and {@code that} object have the same underlying data. If {@code true} modifications to this object will also affect {@code that} object and vice-versa.
     * <p>Definition generated from cef_values_capi.h
     * <pre>int (CEF_CALLBACK* is_same)(struct _cef_value_t* self, struct _cef_value_t* that);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:89</a>
     */
    boolean isSame(@Nullable CefValue that);

    /**
     * Returns {@code true} if this object and {@code that} object have an equivalent underlying value but are not necessarily the same object.
     * <p>Definition generated from cef_values_capi.h
     * <pre>int (CEF_CALLBACK* is_equal)(struct _cef_value_t* self, struct _cef_value_t* that);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:97</a>
     */
    boolean isEqual(@Nullable CefValue that);

    /**
     * Returns a copy of this object. The underlying data will also be copied.
     * <p>Definition generated from cef_values_capi.h
     * <pre>cef_value_t* (CEF_CALLBACK* copy)(struct _cef_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:104</a>
     */
    Optional<CefValue> copy();

    /**
     * Returns the underlying value type.
     * <p>Definition generated from cef_values_capi.h
     * <pre>cef_value_type_t (CEF_CALLBACK* get_type)(struct _cef_value_t* self);</pre>
     *
     * @return the result, or {@code VTYPE_INVALID} for default handling
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:110</a>
     */
    CefValueType getType();

    /**
     * Returns the underlying value as type bool.
     * <p>Definition generated from cef_values_capi.h
     * <pre>int (CEF_CALLBACK* get_bool)(struct _cef_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:116</a>
     */
    boolean getBool();

    /**
     * Returns the underlying value as type int.
     * <p>Definition generated from cef_values_capi.h
     * <pre>int (CEF_CALLBACK* get_int)(struct _cef_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:122</a>
     */
    int getInt();

    /**
     * Returns the underlying value as type double.
     * <p>Definition generated from cef_values_capi.h
     * <pre>double (CEF_CALLBACK* get_double)(struct _cef_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:128</a>
     */
    double getDouble();

    /**
     * Returns the underlying value as type string.
     * <p>Definition generated from cef_values_capi.h
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_string)(struct _cef_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:134</a>
     */
    Optional<String> getString();

    /**
     * Returns the underlying value as type binary. The returned reference may become invalid if the value is owned by another object or if ownership is transferred to another object in the future. To maintain a reference to the value after assigning ownership to a dictionary or list pass this object to the SetValue() method instead of passing the returned reference to SetBinary().
     * <p>Definition generated from cef_values_capi.h
     * <pre>cef_binary_value_t* (CEF_CALLBACK* get_binary)(struct _cef_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:140</a>
     */
    Optional<CefBinaryValue> getBinary();

    /**
     * Returns the underlying value as type dictionary. The returned reference may become invalid if the value is owned by another object or if ownership is transferred to another object in the future. To maintain a reference to the value after assigning ownership to a dictionary or list pass this object to the SetValue() method instead of passing the returned reference to SetDictionary().
     * <p>Definition generated from cef_values_capi.h
     * <pre>cef_dictionary_value_t* (CEF_CALLBACK* get_dictionary)(struct _cef_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:151</a>
     */
    Optional<CefDictionaryValue> getDictionary();

    /**
     * Returns the underlying value as type list. The returned reference may become invalid if the value is owned by another object or if ownership is transferred to another object in the future. To maintain a reference to the value after assigning ownership to a dictionary or list pass this object to the SetValue() method instead of passing the returned reference to SetList().
     * <p>Definition generated from cef_values_capi.h
     * <pre>cef_list_value_t* (CEF_CALLBACK* get_list)(struct _cef_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:162</a>
     */
    Optional<CefListValue> getList();

    /**
     * Sets the underlying value as type null. Returns {@code true} if the value was set successfully.
     * <p>Definition generated from cef_values_capi.h
     * <pre>int (CEF_CALLBACK* set_null)(struct _cef_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:173</a>
     */
    boolean setNull();

    /**
     * Sets the underlying value as type bool. Returns {@code true} if the value was set successfully.
     * <p>Definition generated from cef_values_capi.h
     * <pre>int (CEF_CALLBACK* set_bool)(struct _cef_value_t* self, int value);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:180</a>
     */
    boolean setBool(boolean value);

    /**
     * Sets the underlying value as type int. Returns {@code true} if the value was set successfully.
     * <p>Definition generated from cef_values_capi.h
     * <pre>int (CEF_CALLBACK* set_int)(struct _cef_value_t* self, int value);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:187</a>
     */
    boolean setInt(int value);

    /**
     * Sets the underlying value as type double. Returns {@code true} if the value was set successfully.
     * <p>Definition generated from cef_values_capi.h
     * <pre>int (CEF_CALLBACK* set_double)(struct _cef_value_t* self, double value);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:194</a>
     */
    boolean setDouble(double value);

    /**
     * Sets the underlying value as type string. Returns {@code true} if the value was set successfully.
     * <p>Definition generated from cef_values_capi.h
     * <pre>int (CEF_CALLBACK* set_string)(struct _cef_value_t* self, const cef_string_t* value);</pre>
     *
     * @param value may be null
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:201</a>
     */
    boolean setString(@Nullable String value);

    /**
     * Sets the underlying value as type binary. Returns {@code true} if the value was set successfully. This object keeps a reference to {@code value} and ownership of the underlying data remains unchanged.
     * <p>Definition generated from cef_values_capi.h
     * <pre>int (CEF_CALLBACK* set_binary)(struct _cef_value_t* self, struct _cef_binary_value_t* value);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:208</a>
     */
    boolean setBinary(@Nullable CefBinaryValue value);

    /**
     * Sets the underlying value as type dict. Returns {@code true} if the value was set successfully. This object keeps a reference to {@code value} and ownership of the underlying data remains unchanged.
     * <p>Definition generated from cef_values_capi.h
     * <pre>int (CEF_CALLBACK* set_dictionary)(struct _cef_value_t* self, struct _cef_dictionary_value_t* value);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:216</a>
     */
    boolean setDictionary(@Nullable CefDictionaryValue value);

    /**
     * Sets the underlying value as type list. Returns {@code true} if the value was set successfully. This object keeps a reference to {@code value} and ownership of the underlying data remains unchanged.
     * <p>Definition generated from cef_values_capi.h
     * <pre>int (CEF_CALLBACK* set_list)(struct _cef_value_t* self, struct _cef_list_value_t* value);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:224</a>
     */
    boolean setList(@Nullable CefListValue value);
    static Optional<CefValue> parseJson(@Nullable String jsonString, @Nonnull CefJsonParserOptions options) {
      return Optional.ofNullable(NativePeer.parseJson0(jsonString, options));
  }

    /**
     * Parses the specified UTF8-encoded {@code json} buffer of size {@code json_size} and returns a dictionary or list representation. If JSON parsing fails this method returns {@code null}.
     * <p>Definition generated from cef_parser_capi.h
     * <pre>CEF_EXPORT cef_value_t* cef_parse_json_buffer(const void* json, size_t json_size, cef_json_parser_options_t options);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__parser_8h.html">cef_parser.h:145</a>
     */
    static Optional<CefValue> parseJsonBuffer(@Nonnull ByteBuffer json, @Nonnull CefJsonParserOptions options) {
      return Optional.ofNullable(NativePeer.parseJsonBuffer0(json, options));
  }

    static Optional<CefValue> parseJsonandReturnError(@Nullable String jsonString, @Nonnull CefJsonParserOptions options, @Nullable String errorMsgOut) {
      return Optional.ofNullable(NativePeer.parseJsonandReturnError0(jsonString, options, errorMsgOut));
  }

    /**
     * Creates a new object.
     * <p>Definition generated from cef_values_capi.h
     * <pre>CEF_EXPORT cef_value_t* cef_value_create(void);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:59</a>
     */
    static Optional<CefValue> create() {
      return Optional.ofNullable(NativePeer.create0());
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
        public void peerClose() {
            closed = true;
            cleanable.clean();
        }

        @Override
        public boolean peerIsClosed() {
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
      public boolean isSame(@Nullable CefValue that) {
          checkNotClosed();
            CefLibraryObject.requireOpen(that, "CefValue");
          return isSame0(nativePtr, that);
      }

        @Override
      public boolean isEqual(@Nullable CefValue that) {
          checkNotClosed();
            CefLibraryObject.requireOpen(that, "CefValue");
          return isEqual0(nativePtr, that);
      }

        @Override
      public Optional<CefValue> copy() {
          checkNotClosed();
          return Optional.ofNullable(copy0(nativePtr));
      }

        @Override
      public CefValueType getType() {
          checkNotClosed();
          return getType0(nativePtr);
      }

        @Override
      public boolean getBool() {
          checkNotClosed();
          return getBool0(nativePtr);
      }

        @Override
      public int getInt() {
          checkNotClosed();
          return getInt0(nativePtr);
      }

        @Override
      public double getDouble() {
          checkNotClosed();
          return getDouble0(nativePtr);
      }

        @Override
      public Optional<String> getString() {
          checkNotClosed();
          return Optional.ofNullable(getString0(nativePtr));
      }

        @Override
      public Optional<CefBinaryValue> getBinary() {
          checkNotClosed();
          return Optional.ofNullable(getBinary0(nativePtr));
      }

        @Override
      public Optional<CefDictionaryValue> getDictionary() {
          checkNotClosed();
          return Optional.ofNullable(getDictionary0(nativePtr));
      }

        @Override
      public Optional<CefListValue> getList() {
          checkNotClosed();
          return Optional.ofNullable(getList0(nativePtr));
      }

        @Override
      public boolean setNull() {
          checkNotClosed();
          return setNull0(nativePtr);
      }

        @Override
      public boolean setBool(boolean value) {
          checkNotClosed();
          return setBool0(nativePtr, value);
      }

        @Override
      public boolean setInt(int value) {
          checkNotClosed();
          return setInt0(nativePtr, value);
      }

        @Override
      public boolean setDouble(double value) {
          checkNotClosed();
          return setDouble0(nativePtr, value);
      }

        @Override
      public boolean setString(@Nullable String value) {
          checkNotClosed();
          return setString0(nativePtr, value);
      }

        @Override
      public boolean setBinary(@Nullable CefBinaryValue value) {
          checkNotClosed();
            CefLibraryObject.requireOpen(value, "CefBinaryValue");
          return setBinary0(nativePtr, value);
      }

        @Override
      public boolean setDictionary(@Nullable CefDictionaryValue value) {
          checkNotClosed();
            CefLibraryObject.requireOpen(value, "CefDictionaryValue");
          return setDictionary0(nativePtr, value);
      }

        @Override
      public boolean setList(@Nullable CefListValue value) {
          checkNotClosed();
            CefLibraryObject.requireOpen(value, "CefListValue");
          return setList0(nativePtr, value);
      }


        static native boolean isValid0(long self);

        static native boolean isOwned0(long self);

        static native boolean isReadOnly0(long self);

        static native boolean isSame0(long self, CefValue that);

        static native boolean isEqual0(long self, CefValue that);

        static native CefValue copy0(long self);

        static native CefValueType getType0(long self);

        static native boolean getBool0(long self);

        static native int getInt0(long self);

        static native double getDouble0(long self);

        static native String getString0(long self);

        static native CefBinaryValue getBinary0(long self);

        static native CefDictionaryValue getDictionary0(long self);

        static native CefListValue getList0(long self);

        static native boolean setNull0(long self);

        static native boolean setBool0(long self, boolean value);

        static native boolean setInt0(long self, int value);

        static native boolean setDouble0(long self, double value);

        static native boolean setString0(long self, String value);

        static native boolean setBinary0(long self, CefBinaryValue value);

        static native boolean setDictionary0(long self, CefDictionaryValue value);

        static native boolean setList0(long self, CefListValue value);

        static native CefValue parseJson0(String jsonString, CefJsonParserOptions options);
        static native CefValue parseJsonBuffer0(ByteBuffer json, CefJsonParserOptions options);
        static native CefValue parseJsonandReturnError0(String jsonString, CefJsonParserOptions options, String errorMsgOut);
        static native CefValue create0();

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
