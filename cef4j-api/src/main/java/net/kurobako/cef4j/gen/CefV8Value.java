// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-api
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * Class representing a V8 value handle. V8 handles can only be accessed from the thread on which they are created.
 * Valid threads for creating a V8 handle include the render process main thread ({@code TID_RENDERER}) and WebWorker
 * threads. A task runner for posting tasks on the associated thread can be retrieved via the
 * {@link net.kurobako.cef4j.gen.CefV8Context#getTaskRunner()} method.
 *
 * <p>Definition generated from cef_v8_capi.h
 *
 * <pre>typedef struct _cef_v8_value_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_v8_value_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:482</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-api -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
@NullableBoundary("generated external ABI")
public interface CefV8Value extends CefLibraryObject {

    /**
     * Returns {@code true} if the underlying handle is valid and it can be accessed on the current thread. Do not call
     * any other methods if this method returns {@code false}.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_valid)(struct _cef_v8_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:632</a>
     */
    boolean isValid();

    /**
     * True if the value type is undefined.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_undefined)(struct _cef_v8_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:640</a>
     */
    boolean isUndefined();

    /**
     * True if the value type is null.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_null)(struct _cef_v8_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:646</a>
     */
    boolean isNull();

    /**
     * True if the value type is bool.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_bool)(struct _cef_v8_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:652</a>
     */
    boolean isBool();

    /**
     * True if the value type is int.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_int)(struct _cef_v8_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:658</a>
     */
    boolean isInt();

    /**
     * True if the value type is unsigned int.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_uint)(struct _cef_v8_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:664</a>
     */
    boolean isUInt();

    /**
     * True if the value type is double.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_double)(struct _cef_v8_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:670</a>
     */
    boolean isDouble();

    /**
     * True if the value type is Date.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_date)(struct _cef_v8_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:676</a>
     */
    boolean isDate();

    /**
     * True if the value type is string.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_string)(struct _cef_v8_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:682</a>
     */
    boolean isString();

    /**
     * True if the value type is object.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_object)(struct _cef_v8_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:688</a>
     */
    boolean isObject();

    /**
     * True if the value type is array.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_array)(struct _cef_v8_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:694</a>
     */
    boolean isArray();

    /**
     * True if the value type is an ArrayBuffer.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_array_buffer)(struct _cef_v8_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:700</a>
     */
    boolean isArrayBuffer();

    /**
     * True if the value type is function.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_function)(struct _cef_v8_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:706</a>
     */
    boolean isFunction();

    /**
     * True if the value type is a Promise.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_promise)(struct _cef_v8_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:712</a>
     */
    boolean isPromise();

    /**
     * Returns {@code true} if this object is pointing to the same handle as {@code that} object.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_same)(struct _cef_v8_value_t* self, struct _cef_v8_value_t* that);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:718</a>
     */
    boolean isSame(@Nullable CefV8Value that);

    /**
     * Return a bool value.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>int (CEF_CALLBACK* get_bool_value)(struct _cef_v8_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:725</a>
     */
    boolean getBoolValue();

    /**
     * Return an int value.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>int (CEF_CALLBACK* get_int_value)(struct _cef_v8_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:731</a>
     */
    int getIntValue();

    /**
     * Return an unsigned int value.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>unsigned int (CEF_CALLBACK* get_uint_value)(struct _cef_v8_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:737</a>
     */
    int getUIntValue();

    /**
     * Return a double value.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>double (CEF_CALLBACK* get_double_value)(struct _cef_v8_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:743</a>
     */
    double getDoubleValue();

    /**
     * Return a Date value.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>cef_basetime_t* (CEF_CALLBACK* get_date_value)(struct _cef_v8_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:749</a>
     */
    CefBaseTime getDateValue();

    /**
     * Return a string value.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_string_value)(struct _cef_v8_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:755</a>
     */
    Optional<String> getStringValue();

    /**
     * Returns {@code true} if this is a user created object.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_user_created)(struct _cef_v8_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:765</a>
     */
    boolean isUserCreated();

    /**
     * Returns {@code true} if the last method call resulted in an exception. This attribute exists only in the scope of
     * the current CEF value object.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>int (CEF_CALLBACK* has_exception)(struct _cef_v8_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:771</a>
     */
    boolean hasException();

    /**
     * Returns the exception resulting from the last method call. This attribute exists only in the scope of the current
     * CEF value object.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>cef_v8_exception_t* (CEF_CALLBACK* get_exception)(struct _cef_v8_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:778</a>
     */
    Optional<CefV8Exception> getException();

    /**
     * Clears the last exception and returns {@code true} on success.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>int (CEF_CALLBACK* clear_exception)(struct _cef_v8_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:785</a>
     */
    boolean clearException();

    /**
     * Returns {@code true} if this object will re-throw future exceptions. This attribute exists only in the scope of
     * the current CEF value object.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>int (CEF_CALLBACK* will_rethrow_exceptions)(struct _cef_v8_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:791</a>
     */
    boolean willRethrowExceptions();

    /**
     * Set whether this object will re-throw future exceptions. By default exceptions are not re-thrown. If a exception
     * is re-thrown the current context should not be accessed again until after the exception has been caught and not
     * re-thrown. Returns {@code true} on success. This attribute exists only in the scope of the current CEF value
     * object.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>int (CEF_CALLBACK* set_rethrow_exceptions)(struct _cef_v8_value_t* self, int rethrow);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:798</a>
     */
    boolean setRethrowExceptions(boolean rethrow);

    /**
     * Returns {@code true} if the object has a value with the specified identifier.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>int (CEF_CALLBACK* has_value_bykey)(struct _cef_v8_value_t* self, const cef_string_t* key);</pre>
     *
     * @param key may be null
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:808</a>
     */
    int hasValueBykey(@Nullable String key);

    /**
     * Returns {@code true} if the object has a value with the specified identifier.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>int (CEF_CALLBACK* has_value_byindex)(struct _cef_v8_value_t* self, int index);</pre>
     *
     * @param index zero-based index
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:814</a>
     */
    int hasValueByindex(int index);

    /**
     * Deletes the value with the specified identifier and returns {@code true} on success. Returns {@code false} if
     * this method is called incorrectly or an exception is thrown. For read-only and don't-delete values this method
     * will return {@code true} even though deletion failed.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>int (CEF_CALLBACK* delete_value_bykey)(struct _cef_v8_value_t* self, const cef_string_t* key);</pre>
     *
     * @param key may be null
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:820</a>
     */
    int deleteValueBykey(@Nullable String key);

    /**
     * Deletes the value with the specified identifier and returns {@code true} on success. Returns {@code false} if
     * this method is called incorrectly, deletion fails or an exception is thrown. For read-only and don't-delete
     * values this method will return {@code true} even though deletion failed.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>int (CEF_CALLBACK* delete_value_byindex)(struct _cef_v8_value_t* self, int index);</pre>
     *
     * @param index zero-based index
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:829</a>
     */
    int deleteValueByindex(int index);

    /**
     * Returns the value with the specified identifier on success. Returns {@code null} if this method is called
     * incorrectly or an exception is thrown.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>cef_v8_value_t* (CEF_CALLBACK* get_value_bykey)(struct _cef_v8_value_t* self, const cef_string_t* key);
     * </pre>
     *
     * @param key may be null
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:838</a>
     */
    Optional<CefV8Value> getValueBykey(@Nullable String key);

    /**
     * Returns the value with the specified identifier on success. Returns {@code null} if this method is called
     * incorrectly or an exception is thrown.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>cef_v8_value_t* (CEF_CALLBACK* get_value_byindex)(struct _cef_v8_value_t* self, int index);</pre>
     *
     * @param index zero-based index
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:845</a>
     */
    Optional<CefV8Value> getValueByindex(int index);

    /**
     * Associates a value with the specified identifier and returns {@code true} on success. Returns {@code false} if
     * this method is called incorrectly or an exception is thrown. For read-only values this method will return
     * {@code true} even though assignment failed.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* set_value_bykey)(struct _cef_v8_value_t* self, const cef_string_t* key, struct _cef_v8_value_t* value, cef_v8_propertyattribute_t attribute);
     * </pre>
     *
     * @param key may be null
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:852</a>
     */
    int setValueBykey(@Nullable String key, @Nullable CefV8Value value, @Nonnull CefV8PropertyAttribute attribute);

    /**
     * Associates a value with the specified identifier and returns {@code true} on success. Returns {@code false} if
     * this method is called incorrectly or an exception is thrown. For read-only values this method will return
     * {@code true} even though assignment failed.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* set_value_byindex)(struct _cef_v8_value_t* self, int index, struct _cef_v8_value_t* value);
     * </pre>
     *
     * @param index zero-based index
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:863</a>
     */
    int setValueByindex(int index, @Nullable CefV8Value value);

    /**
     * Registers an identifier and returns {@code true} on success. Access to the identifier will be forwarded to the
     * CefV8Accessor instance passed to net.kurobako.cef4j.gen.CefV8Value.createObject(). Returns {@code false} if this
     * method is called incorrectly or an exception is thrown. For read-only values this method will return {@code true}
     * even though assignment failed.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* set_value_byaccessor)(struct _cef_v8_value_t* self, const cef_string_t* key, cef_v8_propertyattribute_t attribute);
     * </pre>
     *
     * @param key may be null
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:872</a>
     */
    int setValueByaccessor(@Nullable String key, @Nonnull CefV8PropertyAttribute attribute);

    /**
     * Read the keys for the object's values into the specified vector. Integer-based keys will also be returned as
     * strings.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>int (CEF_CALLBACK* get_keys)(struct _cef_v8_value_t* self, cef_string_list_t keys);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:882</a>
     */
    boolean getKeys(@Nonnull List<String> keys);

    /**
     * Sets the user data for this object and returns {@code true} on success. Returns {@code false} if this method is
     * called incorrectly. This method can only be called on user created objects.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>int (CEF_CALLBACK* set_user_data)(struct _cef_v8_value_t* self, struct _cef_base_ref_counted_t* user_data);
     * </pre>
     *
     * @param userData may be null
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:889</a>
     */
    boolean setUserData(@Nullable NativePointer userData);

    /**
     * Returns the user data, if any, assigned to this object.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>void* (CEF_CALLBACK* get_user_data)(struct _cef_v8_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:897</a>
     */
    NativePointer getUserData();

    /**
     * Returns the amount of externally allocated memory registered for the object.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>int (CEF_CALLBACK* get_externally_allocated_memory)(struct _cef_v8_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:903</a>
     */
    int getExternallyAllocatedMemory();

    /**
     * Adjusts the amount of registered external memory for the object. Used to give V8 an indication of the amount of
     * externally allocated memory that is kept alive by JavaScript objects. V8 uses this information to decide when to
     * perform global garbage collection. Each CefV8Value tracks the amount of external memory associated with it and
     * automatically decreases the global total by the appropriate amount on its destruction. {@code change_in_bytes}
     * specifies the number of bytes to adjust by. This method returns the number of bytes associated with the object
     * after the adjustment. This method can only be called on user created objects.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>int (CEF_CALLBACK* adjust_externally_allocated_memory)(struct _cef_v8_value_t* self, int change_in_bytes);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:910</a>
     */
    int adjustExternallyAllocatedMemory(int changeInBytes);

    /**
     * Returns the number of elements in the array.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>int (CEF_CALLBACK* get_array_length)(struct _cef_v8_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:926</a>
     */
    int getArrayLength();

    Optional<CefV8ArrayBufferReleaseCallback> getArrayBufferReleaseCallback();

    /**
     * Prevent the ArrayBuffer from using it's memory block by setting the length to zero. This operation cannot be
     * undone. If the ArrayBuffer was created with CreateArrayBuffer then
     * {@link net.kurobako.cef4j.gen.CefV8ArrayBufferReleaseCallback#releaseBuffer(NativePointer)} will be called to
     * release the underlying buffer.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>int (CEF_CALLBACK* neuter_array_buffer)(struct _cef_v8_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:942</a>
     */
    boolean neuterArrayBuffer();

    /**
     * Returns the length (in bytes) of the ArrayBuffer.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>size_t (CEF_CALLBACK* get_array_buffer_byte_length)(struct _cef_v8_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:951</a>
     */
    long getArrayBufferByteLength();

    NativePointer getArrayBufferData();

    /**
     * Returns the function name.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_function_name)(struct _cef_v8_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:967</a>
     */
    Optional<String> getFunctionName();

    /**
     * Returns the function handler or {@code null} if not a CEF-created function.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>cef_v8_handler_t* (CEF_CALLBACK* get_function_handler)(struct _cef_v8_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:973</a>
     */
    Optional<CefV8Handler> getFunctionHandler();

    /**
     * Execute the function using the current V8 context. This method should only be called from within the scope of a
     * CefV8Handler or CefV8Accessor callback, or in combination with calling Enter() and Exit() on a stored
     * CefV8Context reference. {@code object} is the receiver ('this' object) of the function. If {@code object} is
     * empty the current context's global object will be used. {@code arguments} is the list of arguments that will be
     * passed to the function. Returns the function return value on success. Returns {@code null} if this method is
     * called incorrectly or an exception is thrown.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>
     * cef_v8_value_t* (CEF_CALLBACK* execute_function)(struct _cef_v8_value_t* self, struct _cef_v8_value_t* object, size_t argumentsCount, struct _cef_v8_value_t* const* arguments);
     * </pre>
     *
     * @param object may be null
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:979</a>
     */
    Optional<CefV8Value> executeFunction(
            @Nullable CefV8Value object, long argumentsCount, @Nullable CefV8Value[] arguments);

    /**
     * Execute the function using the specified V8 context. {@code object} is the receiver ('this' object) of the
     * function. If {@code object} is empty the specified context's global object will be used. {@code arguments} is the
     * list of arguments that will be passed to the function. Returns the function return value on success. Returns
     * {@code null} if this method is called incorrectly or an exception is thrown.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>
     * cef_v8_value_t* (CEF_CALLBACK* execute_function_with_context)(struct _cef_v8_value_t* self, struct _cef_v8_context_t* context, struct _cef_v8_value_t* object, size_t argumentsCount, struct _cef_v8_value_t* const* arguments);
     * </pre>
     *
     * @param object may be null
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:994</a>
     */
    Optional<CefV8Value> executeFunctionWithContext(
            @Nullable CefV8Context context,
            @Nullable CefV8Value object,
            long argumentsCount,
            @Nullable CefV8Value[] arguments);

    /**
     * Resolve the Promise using the current V8 context. This method should only be called from within the scope of a
     * CefV8Handler or CefV8Accessor callback, or in combination with calling Enter() and Exit() on a stored
     * CefV8Context reference. {@code arg} is the argument passed to the resolved promise. Returns {@code true} on
     * success. Returns {@code false} if this method is called incorrectly or an exception is thrown.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>int (CEF_CALLBACK* resolve_promise)(struct _cef_v8_value_t* self, struct _cef_v8_value_t* arg);</pre>
     *
     * @param arg may be null
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:1010</a>
     */
    boolean resolvePromise(@Nullable CefV8Value arg);

    /**
     * Reject the Promise using the current V8 context. This method should only be called from within the scope of a
     * CefV8Handler or CefV8Accessor callback, or in combination with calling Enter() and Exit() on a stored
     * CefV8Context reference. Returns {@code true} on success. Returns {@code false} if this method is called
     * incorrectly or an exception is thrown.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>int (CEF_CALLBACK* reject_promise)(struct _cef_v8_value_t* self, const cef_string_t* errorMsg);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:1021</a>
     */
    boolean rejectPromise(@Nullable String errorMsg);
    /**
     * Create a new CefV8Value object of type undefined.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>CEF_EXPORT cef_v8_value_t* cef_v8_value_create_undefined(void);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:494</a>
     */
    static Optional<CefV8Value> createUndefined() {
        return Optional.ofNullable(NativePeer.createUndefined0());
    }

    /**
     * Create a new CefV8Value object of type null.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>CEF_EXPORT cef_v8_value_t* cef_v8_value_create_null(void);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:500</a>
     */
    static Optional<CefV8Value> createNull() {
        return Optional.ofNullable(NativePeer.createNull0());
    }

    /**
     * Create a new CefV8Value object of type bool.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>CEF_EXPORT cef_v8_value_t* cef_v8_value_create_bool(int value);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:506</a>
     */
    static Optional<CefV8Value> createBool(int value) {
        return Optional.ofNullable(NativePeer.createBool0(value));
    }

    /**
     * Create a new CefV8Value object of type int.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>CEF_EXPORT cef_v8_value_t* cef_v8_value_create_int(int32_t value);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:512</a>
     */
    static Optional<CefV8Value> createInt(int value) {
        return Optional.ofNullable(NativePeer.createInt0(value));
    }

    static Optional<CefV8Value> createUint(int value) {
        return Optional.ofNullable(NativePeer.createUint0(value));
    }

    /**
     * Create a new CefV8Value object of type double.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>CEF_EXPORT cef_v8_value_t* cef_v8_value_create_double(double value);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:524</a>
     */
    static Optional<CefV8Value> createDouble(double value) {
        return Optional.ofNullable(NativePeer.createDouble0(value));
    }

    /**
     * Create a new CefV8Value object of type Date. This method should only be called from within the scope of a
     * CefRenderProcessHandler, CefV8Handler or CefV8Accessor callback, or in combination with calling Enter() and
     * Exit() on a stored CefV8Context reference.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>CEF_EXPORT cef_v8_value_t* cef_v8_value_create_date(cef_basetime_t date);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:530</a>
     */
    static Optional<CefV8Value> createDate(@Nullable CefBaseTime date) {
        return Optional.ofNullable(NativePeer.createDate0(date));
    }

    /**
     * Create a new CefV8Value object of type string.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>CEF_EXPORT cef_v8_value_t* cef_v8_value_create_string(const cef_string_t* value);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:539</a>
     */
    static Optional<CefV8Value> createString(@Nullable String value) {
        return Optional.ofNullable(NativePeer.createString0(value));
    }

    /**
     * Create a new CefV8Value object of type object with optional accessor and/or interceptor. This method should only
     * be called from within the scope of a CefRenderProcessHandler, CefV8Handler or CefV8Accessor callback, or in
     * combination with calling Enter() and Exit() on a stored CefV8Context reference.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>
     * CEF_EXPORT cef_v8_value_t* cef_v8_value_create_object(cef_v8_accessor_t* accessor, cef_v8_interceptor_t* interceptor);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:545</a>
     */
    static Optional<CefV8Value> createObject(@Nullable CefV8Accessor accessor, @Nullable CefV8Interceptor interceptor) {
        return Optional.ofNullable(NativePeer.createObject0(accessor, interceptor));
    }

    /**
     * Create a new CefV8Value object of type array with the specified {@code length}. If {@code length} is negative the
     * returned array will have length 0. This method should only be called from within the scope of a
     * CefRenderProcessHandler, CefV8Handler or CefV8Accessor callback, or in combination with calling Enter() and
     * Exit() on a stored CefV8Context reference.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>CEF_EXPORT cef_v8_value_t* cef_v8_value_create_array(int length);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:557</a>
     */
    static Optional<CefV8Value> createArray(int length) {
        return Optional.ofNullable(NativePeer.createArray0(length));
    }

    /**
     * Create a new CefV8Value object of type ArrayBuffer which wraps the provided {@code buffer} of size {@code length}
     * bytes. The ArrayBuffer is externalized, meaning that it does not own {@code buffer}. The caller is responsible
     * for freeing {@code buffer} when requested via a call to
     * {@link net.kurobako.cef4j.gen.CefV8ArrayBufferReleaseCallback#releaseBuffer(NativePointer)}. This method should
     * only be called from within the scope of a CefRenderProcessHandler, CefV8Handler or CefV8Accessor callback, or in
     * combination with calling Enter() and Exit() on a stored CefV8Context reference.
     *
     * <p>NOTE: Always returns {@code null} when V8 sandbox is enabled.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>
     * CEF_EXPORT cef_v8_value_t* cef_v8_value_create_array_buffer(void* buffer, size_t length, cef_v8_array_buffer_release_callback_t* release_callback);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:567</a>
     */
    static Optional<CefV8Value> createArrayBuffer(
            @Nullable ByteBuffer buffer, @Nullable CefV8ArrayBufferReleaseCallback releaseCallback) {
        return Optional.ofNullable(NativePeer.createArrayBuffer0(buffer, releaseCallback));
    }

    /**
     * Create a new CefV8Value object of type ArrayBuffer which copies the provided {@code buffer} of size
     * {@code length} bytes. This method should only be called from within the scope of a CefRenderProcessHandler,
     * CefV8Handler or CefV8Accessor callback, or in combination with calling Enter() and Exit() on a stored
     * CefV8Context reference.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>CEF_EXPORT cef_v8_value_t* cef_v8_value_create_array_buffer_with_copy(void* buffer, size_t length);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:585</a>
     */
    static Optional<CefV8Value> createArrayBufferWithCopy(@Nullable ByteBuffer buffer) {
        return Optional.ofNullable(NativePeer.createArrayBufferWithCopy0(buffer));
    }

    /**
     * Create a new CefV8Value object of type ArrayBuffer from a backing store previously created with
     * net.kurobako.cef4j.gen.CefV8BackingStore.create(). This is a zero-copy operation - the ArrayBuffer uses the
     * memory already allocated by the backing store. The backing store is consumed and becomes invalid after this call.
     * This method should only be called from within the scope of a CefRenderProcessHandler, CefV8Handler or
     * CefV8Accessor callback, or in combination with calling Enter() and Exit() on a stored CefV8Context reference.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>
     * CEF_EXPORT cef_v8_value_t* cef_v8_value_create_array_buffer_from_backing_store(cef_v8_backing_store_t* backing_store);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:598</a>
     */
    static Optional<CefV8Value> createArrayBufferFromBackingStore(@Nullable CefV8BackingStore backingStore) {
        return Optional.ofNullable(NativePeer.createArrayBufferFromBackingStore0(backingStore));
    }

    /**
     * Create a new CefV8Value object of type function. This method should only be called from within the scope of a
     * CefRenderProcessHandler, CefV8Handler or CefV8Accessor callback, or in combination with calling Enter() and
     * Exit() on a stored CefV8Context reference.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>
     * CEF_EXPORT cef_v8_value_t* cef_v8_value_create_function(const cef_string_t* name, cef_v8_handler_t* handler);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:613</a>
     */
    static Optional<CefV8Value> createFunction(@Nullable String name, @Nullable CefV8Handler handler) {
        return Optional.ofNullable(NativePeer.createFunction0(name, handler));
    }

    /**
     * Create a new CefV8Value object of type Promise. This method should only be called from within the scope of a
     * CefRenderProcessHandler, CefV8Handler or CefV8Accessor callback, or in combination with calling Enter() and
     * Exit() on a stored CefV8Context reference.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>CEF_EXPORT cef_v8_value_t* cef_v8_value_create_promise(void);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:623</a>
     */
    static Optional<CefV8Value> createPromise() {
        return Optional.ofNullable(NativePeer.createPromise0());
    }

    final class NativePeer implements CefV8Value, AutoCloseable {
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
            if (closed) throw new IllegalStateException("CefV8Value has been closed");
        }

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefV8Value.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefV8Value 0x{}", Long.toHexString(ptr));
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
        public boolean isUndefined() {
            checkNotClosed();
            return isUndefined0(nativePtr);
        }

        @Override
        public boolean isNull() {
            checkNotClosed();
            return isNull0(nativePtr);
        }

        @Override
        public boolean isBool() {
            checkNotClosed();
            return isBool0(nativePtr);
        }

        @Override
        public boolean isInt() {
            checkNotClosed();
            return isInt0(nativePtr);
        }

        @Override
        public boolean isUInt() {
            checkNotClosed();
            return isUInt0(nativePtr);
        }

        @Override
        public boolean isDouble() {
            checkNotClosed();
            return isDouble0(nativePtr);
        }

        @Override
        public boolean isDate() {
            checkNotClosed();
            return isDate0(nativePtr);
        }

        @Override
        public boolean isString() {
            checkNotClosed();
            return isString0(nativePtr);
        }

        @Override
        public boolean isObject() {
            checkNotClosed();
            return isObject0(nativePtr);
        }

        @Override
        public boolean isArray() {
            checkNotClosed();
            return isArray0(nativePtr);
        }

        @Override
        public boolean isArrayBuffer() {
            checkNotClosed();
            return isArrayBuffer0(nativePtr);
        }

        @Override
        public boolean isFunction() {
            checkNotClosed();
            return isFunction0(nativePtr);
        }

        @Override
        public boolean isPromise() {
            checkNotClosed();
            return isPromise0(nativePtr);
        }

        @Override
        public boolean isSame(@Nullable CefV8Value that) {
            checkNotClosed();
            CefLibraryObject.requireOpen(that, "CefV8Value");
            return isSame0(nativePtr, that);
        }

        @Override
        public boolean getBoolValue() {
            checkNotClosed();
            return getBoolValue0(nativePtr);
        }

        @Override
        public int getIntValue() {
            checkNotClosed();
            return getIntValue0(nativePtr);
        }

        @Override
        public int getUIntValue() {
            checkNotClosed();
            return getUIntValue0(nativePtr);
        }

        @Override
        public double getDoubleValue() {
            checkNotClosed();
            return getDoubleValue0(nativePtr);
        }

        @Override
        public CefBaseTime getDateValue() {
            checkNotClosed();
            return getDateValue0(nativePtr);
        }

        @Override
        public Optional<String> getStringValue() {
            checkNotClosed();
            return Optional.ofNullable(getStringValue0(nativePtr));
        }

        @Override
        public boolean isUserCreated() {
            checkNotClosed();
            return isUserCreated0(nativePtr);
        }

        @Override
        public boolean hasException() {
            checkNotClosed();
            return hasException0(nativePtr);
        }

        @Override
        public Optional<CefV8Exception> getException() {
            checkNotClosed();
            return Optional.ofNullable(getException0(nativePtr));
        }

        @Override
        public boolean clearException() {
            checkNotClosed();
            return clearException0(nativePtr);
        }

        @Override
        public boolean willRethrowExceptions() {
            checkNotClosed();
            return willRethrowExceptions0(nativePtr);
        }

        @Override
        public boolean setRethrowExceptions(boolean rethrow) {
            checkNotClosed();
            return setRethrowExceptions0(nativePtr, rethrow);
        }

        @Override
        public int hasValueBykey(@Nullable String key) {
            checkNotClosed();
            return hasValueBykey0(nativePtr, key);
        }

        @Override
        public int hasValueByindex(int index) {
            checkNotClosed();
            return hasValueByindex0(nativePtr, index);
        }

        @Override
        public int deleteValueBykey(@Nullable String key) {
            checkNotClosed();
            return deleteValueBykey0(nativePtr, key);
        }

        @Override
        public int deleteValueByindex(int index) {
            checkNotClosed();
            return deleteValueByindex0(nativePtr, index);
        }

        @Override
        public Optional<CefV8Value> getValueBykey(@Nullable String key) {
            checkNotClosed();
            return Optional.ofNullable(getValueBykey0(nativePtr, key));
        }

        @Override
        public Optional<CefV8Value> getValueByindex(int index) {
            checkNotClosed();
            return Optional.ofNullable(getValueByindex0(nativePtr, index));
        }

        @Override
        public int setValueBykey(
                @Nullable String key, @Nullable CefV8Value value, @Nonnull CefV8PropertyAttribute attribute) {
            checkNotClosed();
            CefLibraryObject.requireOpen(value, "CefV8Value");
            return setValueBykey0(nativePtr, key, value, attribute);
        }

        @Override
        public int setValueByindex(int index, @Nullable CefV8Value value) {
            checkNotClosed();
            CefLibraryObject.requireOpen(value, "CefV8Value");
            return setValueByindex0(nativePtr, index, value);
        }

        @Override
        public int setValueByaccessor(@Nullable String key, @Nonnull CefV8PropertyAttribute attribute) {
            checkNotClosed();
            return setValueByaccessor0(nativePtr, key, attribute);
        }

        @Override
        public boolean getKeys(@Nonnull List<String> keys) {
            checkNotClosed();
            return getKeys0(nativePtr, keys);
        }

        @Override
        public boolean setUserData(@Nullable NativePointer userData) {
            checkNotClosed();
            return setUserData0(nativePtr, userData);
        }

        @Override
        public NativePointer getUserData() {
            checkNotClosed();
            return getUserData0(nativePtr);
        }

        @Override
        public int getExternallyAllocatedMemory() {
            checkNotClosed();
            return getExternallyAllocatedMemory0(nativePtr);
        }

        @Override
        public int adjustExternallyAllocatedMemory(int changeInBytes) {
            checkNotClosed();
            return adjustExternallyAllocatedMemory0(nativePtr, changeInBytes);
        }

        @Override
        public int getArrayLength() {
            checkNotClosed();
            return getArrayLength0(nativePtr);
        }

        @Override
        public Optional<CefV8ArrayBufferReleaseCallback> getArrayBufferReleaseCallback() {
            checkNotClosed();
            return Optional.ofNullable(getArrayBufferReleaseCallback0(nativePtr));
        }

        @Override
        public boolean neuterArrayBuffer() {
            checkNotClosed();
            return neuterArrayBuffer0(nativePtr);
        }

        @Override
        public long getArrayBufferByteLength() {
            checkNotClosed();
            return getArrayBufferByteLength0(nativePtr);
        }

        @Override
        public NativePointer getArrayBufferData() {
            checkNotClosed();
            return getArrayBufferData0(nativePtr);
        }

        @Override
        public Optional<String> getFunctionName() {
            checkNotClosed();
            return Optional.ofNullable(getFunctionName0(nativePtr));
        }

        @Override
        public Optional<CefV8Handler> getFunctionHandler() {
            checkNotClosed();
            return Optional.ofNullable(getFunctionHandler0(nativePtr));
        }

        @Override
        public Optional<CefV8Value> executeFunction(
                @Nullable CefV8Value object, long argumentsCount, @Nullable CefV8Value[] arguments) {
            checkNotClosed();
            CefLibraryObject.requireOpen(object, "CefV8Value");
            return Optional.ofNullable(executeFunction0(nativePtr, object, argumentsCount, arguments));
        }

        @Override
        public Optional<CefV8Value> executeFunctionWithContext(
                @Nullable CefV8Context context,
                @Nullable CefV8Value object,
                long argumentsCount,
                @Nullable CefV8Value[] arguments) {
            checkNotClosed();
            CefLibraryObject.requireOpen(context, "CefV8Context");
            CefLibraryObject.requireOpen(object, "CefV8Value");
            return Optional.ofNullable(
                    executeFunctionWithContext0(nativePtr, context, object, argumentsCount, arguments));
        }

        @Override
        public boolean resolvePromise(@Nullable CefV8Value arg) {
            checkNotClosed();
            CefLibraryObject.requireOpen(arg, "CefV8Value");
            return resolvePromise0(nativePtr, arg);
        }

        @Override
        public boolean rejectPromise(@Nullable String errorMsg) {
            checkNotClosed();
            return rejectPromise0(nativePtr, errorMsg);
        }

        static native boolean isValid0(long self);

        static native boolean isUndefined0(long self);

        static native boolean isNull0(long self);

        static native boolean isBool0(long self);

        static native boolean isInt0(long self);

        static native boolean isUInt0(long self);

        static native boolean isDouble0(long self);

        static native boolean isDate0(long self);

        static native boolean isString0(long self);

        static native boolean isObject0(long self);

        static native boolean isArray0(long self);

        static native boolean isArrayBuffer0(long self);

        static native boolean isFunction0(long self);

        static native boolean isPromise0(long self);

        static native boolean isSame0(long self, @Nullable CefV8Value that);

        static native boolean getBoolValue0(long self);

        static native int getIntValue0(long self);

        static native int getUIntValue0(long self);

        static native double getDoubleValue0(long self);

        static native CefBaseTime getDateValue0(long self);

        static native String getStringValue0(long self);

        static native boolean isUserCreated0(long self);

        static native boolean hasException0(long self);

        static native CefV8Exception getException0(long self);

        static native boolean clearException0(long self);

        static native boolean willRethrowExceptions0(long self);

        static native boolean setRethrowExceptions0(long self, boolean rethrow);

        static native int hasValueBykey0(long self, @Nullable String key);

        static native int hasValueByindex0(long self, int index);

        static native int deleteValueBykey0(long self, @Nullable String key);

        static native int deleteValueByindex0(long self, int index);

        static native CefV8Value getValueBykey0(long self, @Nullable String key);

        static native CefV8Value getValueByindex0(long self, int index);

        static native int setValueBykey0(
                long self, @Nullable String key, @Nullable CefV8Value value, @Nonnull CefV8PropertyAttribute attribute);

        static native int setValueByindex0(long self, int index, @Nullable CefV8Value value);

        static native int setValueByaccessor0(
                long self, @Nullable String key, @Nonnull CefV8PropertyAttribute attribute);

        static native boolean getKeys0(long self, @Nonnull List<String> keys);

        static native boolean setUserData0(long self, @Nullable NativePointer userData);

        static native NativePointer getUserData0(long self);

        static native int getExternallyAllocatedMemory0(long self);

        static native int adjustExternallyAllocatedMemory0(long self, int changeInBytes);

        static native int getArrayLength0(long self);

        static native CefV8ArrayBufferReleaseCallback getArrayBufferReleaseCallback0(long self);

        static native boolean neuterArrayBuffer0(long self);

        static native long getArrayBufferByteLength0(long self);

        static native NativePointer getArrayBufferData0(long self);

        static native String getFunctionName0(long self);

        static native CefV8Handler getFunctionHandler0(long self);

        static native CefV8Value executeFunction0(
                long self, @Nullable CefV8Value object, long argumentsCount, @Nullable CefV8Value[] arguments);

        static native CefV8Value executeFunctionWithContext0(
                long self,
                @Nullable CefV8Context context,
                @Nullable CefV8Value object,
                long argumentsCount,
                @Nullable CefV8Value[] arguments);

        static native boolean resolvePromise0(long self, @Nullable CefV8Value arg);

        static native boolean rejectPromise0(long self, @Nullable String errorMsg);

        static native CefV8Value createUndefined0();

        static native CefV8Value createNull0();

        static native CefV8Value createBool0(int value);

        static native CefV8Value createInt0(int value);

        static native CefV8Value createUint0(int value);

        static native CefV8Value createDouble0(double value);

        static native CefV8Value createDate0(@Nullable CefBaseTime date);

        static native CefV8Value createString0(@Nullable String value);

        static native CefV8Value createObject0(
                @Nullable CefV8Accessor accessor, @Nullable CefV8Interceptor interceptor);

        static native CefV8Value createArray0(int length);

        static native CefV8Value createArrayBuffer0(
                @Nullable ByteBuffer buffer, @Nullable CefV8ArrayBufferReleaseCallback releaseCallback);

        static native CefV8Value createArrayBufferWithCopy0(@Nullable ByteBuffer buffer);

        static native CefV8Value createArrayBufferFromBackingStore0(@Nullable CefV8BackingStore backingStore);

        static native CefV8Value createFunction0(@Nullable String name, @Nullable CefV8Handler handler);

        static native CefV8Value createPromise0();

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
            return "CefV8Value{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
