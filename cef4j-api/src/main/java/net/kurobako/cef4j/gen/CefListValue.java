// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import java.util.Optional;
import javax.annotation.Nullable;

/**
 * Class representing a list value. Can be used on any process and thread.
 * <p>Definition generated from cef_values_capi.h
 * <pre>typedef struct _cef_list_value_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_list_value_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:537</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public interface CefListValue extends CefLibraryObject {

    /**
     * Returns {@code true} if this object is valid. This object may become invalid if the underlying data is owned by another object (e.g. list or dictionary) and that other object is then modified or destroyed. Do not call any other methods if this method returns {@code false}.
     * <p>Definition generated from cef_values_capi.h
     * <pre>int (CEF_CALLBACK* is_valid)(struct _cef_list_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:549</a>
     */
    boolean isValid();

    /**
     * Returns {@code true} if this object is currently owned by another object.
     * <p>Definition generated from cef_values_capi.h
     * <pre>int (CEF_CALLBACK* is_owned)(struct _cef_list_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:558</a>
     */
    boolean isOwned();

    /**
     * Returns {@code true} if the values of this object are read-only. Some APIs may expose read-only objects.
     * <p>Definition generated from cef_values_capi.h
     * <pre>int (CEF_CALLBACK* is_read_only)(struct _cef_list_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:564</a>
     */
    boolean isReadOnly();

    /**
     * Returns {@code true} if this object and {@code that} object have the same underlying data. If {@code true} modifications to this object will also affect {@code that} object and vice-versa.
     * <p>Definition generated from cef_values_capi.h
     * <pre>int (CEF_CALLBACK* is_same)(struct _cef_list_value_t* self, struct _cef_list_value_t* that);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:571</a>
     */
    boolean isSame(@Nullable CefListValue that);

    /**
     * Returns {@code true} if this object and {@code that} object have an equivalent underlying value but are not necessarily the same object.
     * <p>Definition generated from cef_values_capi.h
     * <pre>int (CEF_CALLBACK* is_equal)(struct _cef_list_value_t* self, struct _cef_list_value_t* that);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:579</a>
     */
    boolean isEqual(@Nullable CefListValue that);

    /**
     * Returns a writable copy of this object.
     * <p>Definition generated from cef_values_capi.h
     * <pre>cef_list_value_t* (CEF_CALLBACK* copy)(struct _cef_list_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:586</a>
     */
    Optional<CefListValue> copy();

    /**
     * Sets the number of values. If the number of values is expanded all new value slots will default to type null. Returns {@code true} on success.
     * <p>Definition generated from cef_values_capi.h
     * <pre>int (CEF_CALLBACK* set_size)(struct _cef_list_value_t* self, size_t size);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:592</a>
     */
    boolean setSize(long size);

    /**
     * Returns the number of values.
     * <p>Definition generated from cef_values_capi.h
     * <pre>size_t (CEF_CALLBACK* get_size)(struct _cef_list_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:599</a>
     */
    long getSize();

    /**
     * Removes all values. Returns {@code true} on success.
     * <p>Definition generated from cef_values_capi.h
     * <pre>int (CEF_CALLBACK* clear)(struct _cef_list_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:605</a>
     */
    boolean clear();

    /**
     * Removes the value at the specified index.
     * <p>Definition generated from cef_values_capi.h
     * <pre>int (CEF_CALLBACK* remove)(struct _cef_list_value_t* self, size_t index);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:611</a>
     */
    boolean remove(long index);

    /**
     * Returns the value type at the specified index.
     * <p>Definition generated from cef_values_capi.h
     * <pre>cef_value_type_t (CEF_CALLBACK* get_type)(struct _cef_list_value_t* self, size_t index);</pre>
     *
     * @return the result, or {@code VTYPE_INVALID} for default handling
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:617</a>
     */
    CefValueType getType(long index);

    /**
     * Returns the value at the specified index. For simple types the returned value will copy existing data and modifications to the value will not modify this object. For complex types (binary, dictionary and list) the returned value will reference existing data and modifications to the value will modify this object.
     * <p>Definition generated from cef_values_capi.h
     * <pre>cef_value_t* (CEF_CALLBACK* get_value)(struct _cef_list_value_t* self, size_t index);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:623</a>
     */
    Optional<CefValue> getValue(long index);

    /**
     * Returns the value at the specified index as type bool.
     * <p>Definition generated from cef_values_capi.h
     * <pre>int (CEF_CALLBACK* get_bool)(struct _cef_list_value_t* self, size_t index);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:633</a>
     */
    boolean getBool(long index);

    /**
     * Returns the value at the specified index as type int.
     * <p>Definition generated from cef_values_capi.h
     * <pre>int (CEF_CALLBACK* get_int)(struct _cef_list_value_t* self, size_t index);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:639</a>
     */
    int getInt(long index);

    /**
     * Returns the value at the specified index as type double.
     * <p>Definition generated from cef_values_capi.h
     * <pre>double (CEF_CALLBACK* get_double)(struct _cef_list_value_t* self, size_t index);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:645</a>
     */
    double getDouble(long index);

    /**
     * Returns the value at the specified index as type dictionary. The returned value will reference existing data and modifications to the value will modify this object.
     * <p>Definition generated from cef_values_capi.h
     * <pre>cef_dictionary_value_t* (CEF_CALLBACK* get_dictionary)(struct _cef_list_value_t* self, size_t index);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:664</a>
     */
    Optional<CefDictionaryValue> getDictionary(long index);

    /**
     * Sets the value at the specified index. Returns {@code true} if the value was set successfully. If {@code value} represents simple data then the underlying data will be copied and modifications to {@code value} will not modify this object. If {@code value} represents complex data (binary, dictionary or list) then the underlying data will be referenced and modifications to {@code value} will modify this object.
     * <p>Definition generated from cef_values_capi.h
     * <pre>int (CEF_CALLBACK* set_value)(struct _cef_list_value_t* self, size_t index, struct _cef_value_t* value);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:680</a>
     */
    boolean setValue(long index, @Nullable CefValue value);

    /**
     * Sets the value at the specified index as type null. Returns {@code true} if the value was set successfully.
     * <p>Definition generated from cef_values_capi.h
     * <pre>int (CEF_CALLBACK* set_null)(struct _cef_list_value_t* self, size_t index);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:691</a>
     */
    boolean setNull(long index);

    /**
     * Sets the value at the specified index as type bool. Returns {@code true} if the value was set successfully.
     * <p>Definition generated from cef_values_capi.h
     * <pre>int (CEF_CALLBACK* set_bool)(struct _cef_list_value_t* self, size_t index, int value);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:698</a>
     */
    boolean setBool(long index, boolean value);

    /**
     * Sets the value at the specified index as type int. Returns {@code true} if the value was set successfully.
     * <p>Definition generated from cef_values_capi.h
     * <pre>int (CEF_CALLBACK* set_int)(struct _cef_list_value_t* self, size_t index, int value);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:705</a>
     */
    boolean setInt(long index, int value);

    /**
     * Sets the value at the specified index as type double. Returns {@code true} if the value was set successfully.
     * <p>Definition generated from cef_values_capi.h
     * <pre>int (CEF_CALLBACK* set_double)(struct _cef_list_value_t* self, size_t index, double value);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:712</a>
     */
    boolean setDouble(long index, double value);

    /**
     * Sets the value at the specified index as type string. Returns {@code true} if the value was set successfully.
     * <p>Definition generated from cef_values_capi.h
     * <pre>int (CEF_CALLBACK* set_string)(struct _cef_list_value_t* self, size_t index, const cef_string_t* value);</pre>
     *
     * @param value may be null
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:719</a>
     */
    boolean setString(long index, @Nullable String value);

    /**
     * Sets the value at the specified index as type binary. Returns {@code true} if the value was set successfully. If {@code value} is currently owned by another object then the value will be copied and the {@code value} reference will not change. Otherwise, ownership will be transferred to this object and the {@code value} reference will be invalidated.
     * <p>Definition generated from cef_values_capi.h
     * <pre>int (CEF_CALLBACK* set_binary)(struct _cef_list_value_t* self, size_t index, struct _cef_binary_value_t* value);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:726</a>
     */
    boolean setBinary(long index, @Nullable CefBinaryValue value);

    /**
     * Sets the value at the specified index as type dict. Returns {@code true} if the value was set successfully. If {@code value} is currently owned by another object then the value will be copied and the {@code value} reference will not change. Otherwise, ownership will be transferred to this object and the {@code value} reference will be invalidated.
     * <p>Definition generated from cef_values_capi.h
     * <pre>int (CEF_CALLBACK* set_dictionary)(struct _cef_list_value_t* self, size_t index, struct _cef_dictionary_value_t* value);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:736</a>
     */
    boolean setDictionary(long index, @Nullable CefDictionaryValue value);

    /**
     * Sets the value at the specified index as type list. Returns {@code true} if the value was set successfully. If {@code value} is currently owned by another object then the value will be copied and the {@code value} reference will not change. Otherwise, ownership will be transferred to this object and the {@code value} reference will be invalidated.
     * <p>Definition generated from cef_values_capi.h
     * <pre>int (CEF_CALLBACK* set_list)(struct _cef_list_value_t* self, size_t index, struct _cef_list_value_t* value);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:747</a>
     */
    boolean setList(long index, @Nullable CefListValue value);
    /**
     * Creates a new object that is not owned by any other object.
     * <p>Definition generated from cef_values_capi.h
     * <pre>CEF_EXPORT cef_list_value_t* cef_list_value_create(void);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:543</a>
     */
    static Optional<CefListValue> create() {
      return Optional.ofNullable(NativePeer.create0());
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
        public void peerClose() {
            closed = true;
            cleanable.clean();
        }

        @Override
        public boolean peerIsClosed() {
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
      public boolean isSame(@Nullable CefListValue that) {
          checkNotClosed();
            CefLibraryObject.requireOpen(that, "CefListValue");
          return isSame0(nativePtr, that);
      }

        @Override
      public boolean isEqual(@Nullable CefListValue that) {
          checkNotClosed();
            CefLibraryObject.requireOpen(that, "CefListValue");
          return isEqual0(nativePtr, that);
      }

        @Override
      public Optional<CefListValue> copy() {
          checkNotClosed();
          return Optional.ofNullable(copy0(nativePtr));
      }

        @Override
      public boolean setSize(long size) {
          checkNotClosed();
          return setSize0(nativePtr, size);
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
      public boolean remove(long index) {
          checkNotClosed();
          return remove0(nativePtr, index);
      }

        @Override
      public CefValueType getType(long index) {
          checkNotClosed();
          return getType0(nativePtr, index);
      }

        @Override
      public Optional<CefValue> getValue(long index) {
          checkNotClosed();
          return Optional.ofNullable(getValue0(nativePtr, index));
      }

        @Override
      public boolean getBool(long index) {
          checkNotClosed();
          return getBool0(nativePtr, index);
      }

        @Override
      public int getInt(long index) {
          checkNotClosed();
          return getInt0(nativePtr, index);
      }

        @Override
      public double getDouble(long index) {
          checkNotClosed();
          return getDouble0(nativePtr, index);
      }

        @Override
      public Optional<CefDictionaryValue> getDictionary(long index) {
          checkNotClosed();
          return Optional.ofNullable(getDictionary0(nativePtr, index));
      }

        @Override
      public boolean setValue(long index, @Nullable CefValue value) {
          checkNotClosed();
            CefLibraryObject.requireOpen(value, "CefValue");
          return setValue0(nativePtr, index, value);
      }

        @Override
      public boolean setNull(long index) {
          checkNotClosed();
          return setNull0(nativePtr, index);
      }

        @Override
      public boolean setBool(long index, boolean value) {
          checkNotClosed();
          return setBool0(nativePtr, index, value);
      }

        @Override
      public boolean setInt(long index, int value) {
          checkNotClosed();
          return setInt0(nativePtr, index, value);
      }

        @Override
      public boolean setDouble(long index, double value) {
          checkNotClosed();
          return setDouble0(nativePtr, index, value);
      }

        @Override
      public boolean setString(long index, @Nullable String value) {
          checkNotClosed();
          return setString0(nativePtr, index, value);
      }

        @Override
      public boolean setBinary(long index, @Nullable CefBinaryValue value) {
          checkNotClosed();
            CefLibraryObject.requireOpen(value, "CefBinaryValue");
          return setBinary0(nativePtr, index, value);
      }

        @Override
      public boolean setDictionary(long index, @Nullable CefDictionaryValue value) {
          checkNotClosed();
            CefLibraryObject.requireOpen(value, "CefDictionaryValue");
          return setDictionary0(nativePtr, index, value);
      }

        @Override
      public boolean setList(long index, @Nullable CefListValue value) {
          checkNotClosed();
            CefLibraryObject.requireOpen(value, "CefListValue");
          return setList0(nativePtr, index, value);
      }


        static native boolean isValid0(long self);

        static native boolean isOwned0(long self);

        static native boolean isReadOnly0(long self);

        static native boolean isSame0(long self, CefListValue that);

        static native boolean isEqual0(long self, CefListValue that);

        static native CefListValue copy0(long self);

        static native boolean setSize0(long self, long size);

        static native long getSize0(long self);

        static native boolean clear0(long self);

        static native boolean remove0(long self, long index);

        static native CefValueType getType0(long self, long index);

        static native CefValue getValue0(long self, long index);

        static native boolean getBool0(long self, long index);

        static native int getInt0(long self, long index);

        static native double getDouble0(long self, long index);

        static native CefDictionaryValue getDictionary0(long self, long index);

        static native boolean setValue0(long self, long index, CefValue value);

        static native boolean setNull0(long self, long index);

        static native boolean setBool0(long self, long index, boolean value);

        static native boolean setInt0(long self, long index, int value);

        static native boolean setDouble0(long self, long index, double value);

        static native boolean setString0(long self, long index, String value);

        static native boolean setBinary0(long self, long index, CefBinaryValue value);

        static native boolean setDictionary0(long self, long index, CefDictionaryValue value);

        static native boolean setList0(long self, long index, CefListValue value);

        static native CefListValue create0();

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
