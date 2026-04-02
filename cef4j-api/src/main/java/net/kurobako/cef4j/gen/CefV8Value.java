// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.util.List;
import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Structure representing a V8 value handle. V8 handles can only be accessed from the thread on which they are created.
 * Valid threads for creating a V8 handle include the render process main thread ({@code TID_RENDERER}) and WebWorker
 * threads. A task runner for posting tasks on the associated thread can be retrieved via the
 * {@link CefV8Context#getTaskRunner()} function. NOTE: This struct is allocated DLL-side.
 *
 * <p>Definition generated from cef_v8_capi.h
 *
 * <pre>typedef struct _cef_v8_value_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_v8_value_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8__capi_8h.html">cef_v8_capi.h:454</a>
 */
public interface CefV8Value extends CefLibraryObject {

    /**
     * Returns {@code true} if this object is valid. Do not call any other methods if this function returns
     * {@code false}.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_valid)(struct _cef_v8_value_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__download__item_8h.html">cef_download_item.h:49</a>
     */
    boolean isValid();

    /**
     * True if the value type is undefined.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_undefined)(struct _cef_v8_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:640</a>
     */
    boolean isUndefined();

    /**
     * True if the value type is null.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_null)(struct _cef_v8_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:646</a>
     */
    boolean isNull();

    /**
     * True if the value type is bool.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_bool)(struct _cef_v8_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:652</a>
     */
    boolean isBool();

    /**
     * True if the value type is int.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_int)(struct _cef_v8_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:658</a>
     */
    boolean isInt();

    /**
     * True if the value type is unsigned int.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_uint)(struct _cef_v8_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:664</a>
     */
    boolean isUInt();

    /**
     * True if the value type is double.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_double)(struct _cef_v8_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:670</a>
     */
    boolean isDouble();

    /**
     * True if the value type is Date.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_date)(struct _cef_v8_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:676</a>
     */
    boolean isDate();

    /**
     * True if the value type is string.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_string)(struct _cef_v8_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:682</a>
     */
    boolean isString();

    /**
     * True if the value type is object.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_object)(struct _cef_v8_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:688</a>
     */
    boolean isObject();

    /**
     * True if the value type is array.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_array)(struct _cef_v8_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:694</a>
     */
    boolean isArray();

    /**
     * True if the value type is an ArrayBuffer.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_array_buffer)(struct _cef_v8_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:700</a>
     */
    boolean isArrayBuffer();

    /**
     * True if the value type is function.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_function)(struct _cef_v8_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:706</a>
     */
    boolean isFunction();

    /**
     * True if the value type is a Promise.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_promise)(struct _cef_v8_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:712</a>
     */
    boolean isPromise();

    /**
     * Returns {@code true} if this object is pointing to the same handle as {@code that} object.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_same)(struct _cef_v8_value_t* self, struct _cef_v8_value_t* that);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__dom_8h.html">cef_dom.h:208</a>
     */
    boolean isSame(@Nullable CefV8Value that);

    /**
     * Return a bool value.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>int (CEF_CALLBACK* get_bool_value)(struct _cef_v8_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:725</a>
     */
    boolean getBoolValue();

    /**
     * Return an int value.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>int (CEF_CALLBACK* get_int_value)(struct _cef_v8_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:731</a>
     */
    int getIntValue();

    /**
     * Return an unsigned int value.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>unsigned int (CEF_CALLBACK* get_uint_value)(struct _cef_v8_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:737</a>
     */
    int getUIntValue();

    /**
     * Return a double value.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>double (CEF_CALLBACK* get_double_value)(struct _cef_v8_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:743</a>
     */
    double getDoubleValue();

    /**
     * Return a Date value.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>cef_basetime_t* (CEF_CALLBACK* get_date_value)(struct _cef_v8_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:749</a>
     */
    CefBasetime getDateValue();

    /**
     * Return a string value.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_string_value)(struct _cef_v8_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:755</a>
     */
    Optional<String> getStringValue();

    /**
     * Returns {@code true} if this is a user created object.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_user_created)(struct _cef_v8_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:765</a>
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
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:771</a>
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
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:778</a>
     */
    Optional<CefV8Exception> getException();

    /**
     * Clears the last exception and returns {@code true} on success.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>int (CEF_CALLBACK* clear_exception)(struct _cef_v8_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:785</a>
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
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:791</a>
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
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:798</a>
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
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:808</a>
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
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:814</a>
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
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:820</a>
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
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:829</a>
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
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:838</a>
     */
    Optional<CefV8Value> getValueBykey(@Nullable String key);

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
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:852</a>
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
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:863</a>
     */
    int setValueByindex(int index, @Nullable CefV8Value value);

    /**
     * Registers an identifier and returns {@code true} on success. Access to the identifier will be forwarded to the
     * CefV8Accessor instance passed to CefV8Value.createObject(). Returns {@code false} if this method is called
     * incorrectly or an exception is thrown. For read-only values this method will return {@code true} even though
     * assignment failed.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* set_value_byaccessor)(struct _cef_v8_value_t* self, const cef_string_t* key, cef_v8_propertyattribute_t attribute);
     * </pre>
     *
     * @param key may be null
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:872</a>
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
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:882</a>
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
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:889</a>
     */
    boolean setUserData(@Nullable NativePointer userData);

    /**
     * Returns the user data, if any, assigned to this object.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>void* (CEF_CALLBACK* get_user_data)(struct _cef_v8_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:897</a>
     */
    NativePointer getUserData();

    /**
     * Returns the amount of externally allocated memory registered for the object.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>int (CEF_CALLBACK* get_externally_allocated_memory)(struct _cef_v8_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:903</a>
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
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:910</a>
     */
    int adjustExternallyAllocatedMemory(int changeInBytes);

    /**
     * Returns the number of elements in the array.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>int (CEF_CALLBACK* get_array_length)(struct _cef_v8_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:926</a>
     */
    int getArrayLength();

    /**
     * Prevent the ArrayBuffer from using it's memory block by setting the length to zero. This operation cannot be
     * undone. If the ArrayBuffer was created with CreateArrayBuffer then
     * {@link CefV8ArrayBufferReleaseCallback#releaseBuffer(NativePointer)} will be called to release the underlying
     * buffer.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>int (CEF_CALLBACK* neuter_array_buffer)(struct _cef_v8_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:942</a>
     */
    boolean neuterArrayBuffer();

    /**
     * Returns the length (in bytes) of the ArrayBuffer.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>size_t (CEF_CALLBACK* get_array_buffer_byte_length)(struct _cef_v8_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:951</a>
     */
    long getArrayBufferByteLength();

    NativePointer getArrayBufferData();

    /**
     * Returns the name of the function.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_function_name)(struct _cef_v8_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:1102</a>
     */
    Optional<String> getFunctionName();

    /**
     * Returns the function handler or {@code null} if not a CEF-created function.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>cef_v8_handler_t* (CEF_CALLBACK* get_function_handler)(struct _cef_v8_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:973</a>
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
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:979</a>
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
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:994</a>
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
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:1010</a>
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
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:1021</a>
     */
    boolean rejectPromise(@Nullable String errorMsg);
    /**
     * Create a new CefV8Value object of type undefined.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>CEF_EXPORT cef_v8_value_t* cef_v8_value_create_undefined(void);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:494</a>
     */
    static Optional<CefV8Value> createUndefined() {
        return Optional.ofNullable(NativePeer.N_CreateUndefined());
    }

    /**
     * Create a new CefV8Value object of type null.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>CEF_EXPORT cef_v8_value_t* cef_v8_value_create_null(void);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:500</a>
     */
    static Optional<CefV8Value> createNull() {
        return Optional.ofNullable(NativePeer.N_CreateNull());
    }

    /**
     * Create a new CefV8Value object of type bool.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>CEF_EXPORT cef_v8_value_t* cef_v8_value_create_bool(int value);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:506</a>
     */
    static Optional<CefV8Value> createBool(int value) {
        return Optional.ofNullable(NativePeer.N_CreateBool(value));
    }

    /**
     * Create a new CefV8Value object of type int.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>CEF_EXPORT cef_v8_value_t* cef_v8_value_create_int(int32_t value);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:512</a>
     */
    static Optional<CefV8Value> createInt(int value) {
        return Optional.ofNullable(NativePeer.N_CreateInt(value));
    }

    static Optional<CefV8Value> createUint(int value) {
        return Optional.ofNullable(NativePeer.N_CreateUint(value));
    }

    /**
     * Create a new CefV8Value object of type double.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>CEF_EXPORT cef_v8_value_t* cef_v8_value_create_double(double value);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:524</a>
     */
    static Optional<CefV8Value> createDouble(double value) {
        return Optional.ofNullable(NativePeer.N_CreateDouble(value));
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
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:530</a>
     */
    static Optional<CefV8Value> createDate(@Nullable CefBasetime date) {
        return Optional.ofNullable(NativePeer.N_CreateDate(date));
    }

    /**
     * Create a new CefV8Value object of type string.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>CEF_EXPORT cef_v8_value_t* cef_v8_value_create_string(const cef_string_t* value);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:539</a>
     */
    static Optional<CefV8Value> createString(@Nullable String value) {
        return Optional.ofNullable(NativePeer.N_CreateString(value));
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
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:545</a>
     */
    static Optional<CefV8Value> createObject(@Nullable CefV8Accessor accessor, @Nullable CefV8Interceptor interceptor) {
        return Optional.ofNullable(NativePeer.N_CreateObject(accessor, interceptor));
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
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:557</a>
     */
    static Optional<CefV8Value> createArray(int length) {
        return Optional.ofNullable(NativePeer.N_CreateArray(length));
    }

    /**
     * Create a new CefV8Value object of type ArrayBuffer which wraps the provided {@code buffer} of size {@code length}
     * bytes. The ArrayBuffer is externalized, meaning that it does not own {@code buffer}. The caller is responsible
     * for freeing {@code buffer} when requested via a call to
     * {@link CefV8ArrayBufferReleaseCallback#releaseBuffer(NativePointer)}. This method should only be called from
     * within the scope of a CefRenderProcessHandler, CefV8Handler or CefV8Accessor callback, or in combination with
     * calling Enter() and Exit() on a stored CefV8Context reference.
     *
     * <p>NOTE: Always returns {@code null} when V8 sandbox is enabled.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>
     * CEF_EXPORT cef_v8_value_t* cef_v8_value_create_array_buffer(void* buffer, size_t length, cef_v8_array_buffer_release_callback_t* release_callback);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:567</a>
     */
    static Optional<CefV8Value> createArrayBuffer(
            @Nullable NativePointer buffer, long length, @Nullable CefV8ArrayBufferReleaseCallback releaseCallback) {
        return Optional.ofNullable(NativePeer.N_CreateArrayBuffer(buffer, length, releaseCallback));
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
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:585</a>
     */
    static Optional<CefV8Value> createArrayBufferWithCopy(@Nullable NativePointer buffer, long length) {
        return Optional.ofNullable(NativePeer.N_CreateArrayBufferWithCopy(buffer, length));
    }

    /**
     * Create a new CefV8Value object of type ArrayBuffer from a backing store previously created with
     * CefV8BackingStore.create(). This is a zero-copy operation — the ArrayBuffer uses the memory already allocated by
     * the backing store. The backing store is consumed and becomes invalid after this call. This method should only be
     * called from within the scope of a CefRenderProcessHandler, CefV8Handler or CefV8Accessor callback, or in
     * combination with calling Enter() and Exit() on a stored CefV8Context reference.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>
     * CEF_EXPORT cef_v8_value_t* cef_v8_value_create_array_buffer_from_backing_store(cef_v8_backing_store_t* backing_store);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:598</a>
     */
    static Optional<CefV8Value> createArrayBufferFromBackingStore(@Nullable CefV8BackingStore backingStore) {
        return Optional.ofNullable(NativePeer.N_CreateArrayBufferFromBackingStore(backingStore));
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
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:613</a>
     */
    static Optional<CefV8Value> createFunction(@Nullable String name, @Nullable CefV8Handler handler) {
        return Optional.ofNullable(NativePeer.N_CreateFunction(name, handler));
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
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:623</a>
     */
    static Optional<CefV8Value> createPromise() {
        return Optional.ofNullable(NativePeer.N_CreatePromise());
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
        public void close() {
            closed = true;
            cleanable.clean();
        }

        @Override
        public boolean isClosed() {
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
        public boolean isUndefined() {
            checkNotClosed();
            return N_IsUndefined(nativePtr);
        }

        @Override
        public boolean isNull() {
            checkNotClosed();
            return N_IsNull(nativePtr);
        }

        @Override
        public boolean isBool() {
            checkNotClosed();
            return N_IsBool(nativePtr);
        }

        @Override
        public boolean isInt() {
            checkNotClosed();
            return N_IsInt(nativePtr);
        }

        @Override
        public boolean isUInt() {
            checkNotClosed();
            return N_IsUInt(nativePtr);
        }

        @Override
        public boolean isDouble() {
            checkNotClosed();
            return N_IsDouble(nativePtr);
        }

        @Override
        public boolean isDate() {
            checkNotClosed();
            return N_IsDate(nativePtr);
        }

        @Override
        public boolean isString() {
            checkNotClosed();
            return N_IsString(nativePtr);
        }

        @Override
        public boolean isObject() {
            checkNotClosed();
            return N_IsObject(nativePtr);
        }

        @Override
        public boolean isArray() {
            checkNotClosed();
            return N_IsArray(nativePtr);
        }

        @Override
        public boolean isArrayBuffer() {
            checkNotClosed();
            return N_IsArrayBuffer(nativePtr);
        }

        @Override
        public boolean isFunction() {
            checkNotClosed();
            return N_IsFunction(nativePtr);
        }

        @Override
        public boolean isPromise() {
            checkNotClosed();
            return N_IsPromise(nativePtr);
        }

        @Override
        public boolean isSame(@Nullable CefV8Value that) {
            checkNotClosed();
            CefLibraryObject.requireOpen(that, "CefV8Value");
            return N_IsSame(nativePtr, that);
        }

        @Override
        public boolean getBoolValue() {
            checkNotClosed();
            return N_GetBoolValue(nativePtr);
        }

        @Override
        public int getIntValue() {
            checkNotClosed();
            return N_GetIntValue(nativePtr);
        }

        @Override
        public int getUIntValue() {
            checkNotClosed();
            return N_GetUIntValue(nativePtr);
        }

        @Override
        public double getDoubleValue() {
            checkNotClosed();
            return N_GetDoubleValue(nativePtr);
        }

        @Override
        public CefBasetime getDateValue() {
            checkNotClosed();
            return N_GetDateValue(nativePtr);
        }

        @Override
        public Optional<String> getStringValue() {
            checkNotClosed();
            return Optional.ofNullable(N_GetStringValue(nativePtr));
        }

        @Override
        public boolean isUserCreated() {
            checkNotClosed();
            return N_IsUserCreated(nativePtr);
        }

        @Override
        public boolean hasException() {
            checkNotClosed();
            return N_HasException(nativePtr);
        }

        @Override
        public Optional<CefV8Exception> getException() {
            checkNotClosed();
            return Optional.ofNullable(N_GetException(nativePtr));
        }

        @Override
        public boolean clearException() {
            checkNotClosed();
            return N_ClearException(nativePtr);
        }

        @Override
        public boolean willRethrowExceptions() {
            checkNotClosed();
            return N_WillRethrowExceptions(nativePtr);
        }

        @Override
        public boolean setRethrowExceptions(boolean rethrow) {
            checkNotClosed();
            return N_SetRethrowExceptions(nativePtr, rethrow);
        }

        @Override
        public int hasValueBykey(@Nullable String key) {
            checkNotClosed();
            return N_HasValueBykey(nativePtr, key);
        }

        @Override
        public int hasValueByindex(int index) {
            checkNotClosed();
            return N_HasValueByindex(nativePtr, index);
        }

        @Override
        public int deleteValueBykey(@Nullable String key) {
            checkNotClosed();
            return N_DeleteValueBykey(nativePtr, key);
        }

        @Override
        public int deleteValueByindex(int index) {
            checkNotClosed();
            return N_DeleteValueByindex(nativePtr, index);
        }

        @Override
        public Optional<CefV8Value> getValueBykey(@Nullable String key) {
            checkNotClosed();
            return Optional.ofNullable(N_GetValueBykey(nativePtr, key));
        }

        @Override
        public int setValueBykey(
                @Nullable String key, @Nullable CefV8Value value, @Nonnull CefV8PropertyAttribute attribute) {
            checkNotClosed();
            CefLibraryObject.requireOpen(value, "CefV8Value");
            return N_SetValueBykey(nativePtr, key, value, attribute);
        }

        @Override
        public int setValueByindex(int index, @Nullable CefV8Value value) {
            checkNotClosed();
            CefLibraryObject.requireOpen(value, "CefV8Value");
            return N_SetValueByindex(nativePtr, index, value);
        }

        @Override
        public int setValueByaccessor(@Nullable String key, @Nonnull CefV8PropertyAttribute attribute) {
            checkNotClosed();
            return N_SetValueByaccessor(nativePtr, key, attribute);
        }

        @Override
        public boolean getKeys(@Nonnull List<String> keys) {
            checkNotClosed();
            return N_GetKeys(nativePtr, keys);
        }

        @Override
        public boolean setUserData(@Nullable NativePointer userData) {
            checkNotClosed();
            return N_SetUserData(nativePtr, userData);
        }

        @Override
        public NativePointer getUserData() {
            checkNotClosed();
            return N_GetUserData(nativePtr);
        }

        @Override
        public int getExternallyAllocatedMemory() {
            checkNotClosed();
            return N_GetExternallyAllocatedMemory(nativePtr);
        }

        @Override
        public int adjustExternallyAllocatedMemory(int changeInBytes) {
            checkNotClosed();
            return N_AdjustExternallyAllocatedMemory(nativePtr, changeInBytes);
        }

        @Override
        public int getArrayLength() {
            checkNotClosed();
            return N_GetArrayLength(nativePtr);
        }

        @Override
        public boolean neuterArrayBuffer() {
            checkNotClosed();
            return N_NeuterArrayBuffer(nativePtr);
        }

        @Override
        public long getArrayBufferByteLength() {
            checkNotClosed();
            return N_GetArrayBufferByteLength(nativePtr);
        }

        @Override
        public NativePointer getArrayBufferData() {
            checkNotClosed();
            return N_GetArrayBufferData(nativePtr);
        }

        @Override
        public Optional<String> getFunctionName() {
            checkNotClosed();
            return Optional.ofNullable(N_GetFunctionName(nativePtr));
        }

        @Override
        public Optional<CefV8Handler> getFunctionHandler() {
            checkNotClosed();
            return Optional.ofNullable(N_GetFunctionHandler(nativePtr));
        }

        @Override
        public Optional<CefV8Value> executeFunction(
                @Nullable CefV8Value object, long argumentsCount, @Nullable CefV8Value[] arguments) {
            checkNotClosed();
            CefLibraryObject.requireOpen(object, "CefV8Value");
            return Optional.ofNullable(N_ExecuteFunction(nativePtr, object, argumentsCount, arguments));
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
                    N_ExecuteFunctionWithContext(nativePtr, context, object, argumentsCount, arguments));
        }

        @Override
        public boolean resolvePromise(@Nullable CefV8Value arg) {
            checkNotClosed();
            CefLibraryObject.requireOpen(arg, "CefV8Value");
            return N_ResolvePromise(nativePtr, arg);
        }

        @Override
        public boolean rejectPromise(@Nullable String errorMsg) {
            checkNotClosed();
            return N_RejectPromise(nativePtr, errorMsg);
        }

        private static native boolean N_IsValid(long self);

        private static native boolean N_IsUndefined(long self);

        private static native boolean N_IsNull(long self);

        private static native boolean N_IsBool(long self);

        private static native boolean N_IsInt(long self);

        private static native boolean N_IsUInt(long self);

        private static native boolean N_IsDouble(long self);

        private static native boolean N_IsDate(long self);

        private static native boolean N_IsString(long self);

        private static native boolean N_IsObject(long self);

        private static native boolean N_IsArray(long self);

        private static native boolean N_IsArrayBuffer(long self);

        private static native boolean N_IsFunction(long self);

        private static native boolean N_IsPromise(long self);

        private static native boolean N_IsSame(long self, CefV8Value that);

        private static native boolean N_GetBoolValue(long self);

        private static native int N_GetIntValue(long self);

        private static native int N_GetUIntValue(long self);

        private static native double N_GetDoubleValue(long self);

        private static native CefBasetime N_GetDateValue(long self);

        private static native String N_GetStringValue(long self);

        private static native boolean N_IsUserCreated(long self);

        private static native boolean N_HasException(long self);

        private static native CefV8Exception N_GetException(long self);

        private static native boolean N_ClearException(long self);

        private static native boolean N_WillRethrowExceptions(long self);

        private static native boolean N_SetRethrowExceptions(long self, boolean rethrow);

        private static native int N_HasValueBykey(long self, String key);

        private static native int N_HasValueByindex(long self, int index);

        private static native int N_DeleteValueBykey(long self, String key);

        private static native int N_DeleteValueByindex(long self, int index);

        private static native CefV8Value N_GetValueBykey(long self, String key);

        private static native int N_SetValueBykey(
                long self, String key, CefV8Value value, CefV8PropertyAttribute attribute);

        private static native int N_SetValueByindex(long self, int index, CefV8Value value);

        private static native int N_SetValueByaccessor(long self, String key, CefV8PropertyAttribute attribute);

        private static native boolean N_GetKeys(long self, List<String> keys);

        private static native boolean N_SetUserData(long self, NativePointer userData);

        private static native NativePointer N_GetUserData(long self);

        private static native int N_GetExternallyAllocatedMemory(long self);

        private static native int N_AdjustExternallyAllocatedMemory(long self, int changeInBytes);

        private static native int N_GetArrayLength(long self);

        private static native boolean N_NeuterArrayBuffer(long self);

        private static native long N_GetArrayBufferByteLength(long self);

        private static native NativePointer N_GetArrayBufferData(long self);

        private static native String N_GetFunctionName(long self);

        private static native CefV8Handler N_GetFunctionHandler(long self);

        private static native CefV8Value N_ExecuteFunction(
                long self, CefV8Value object, long argumentsCount, CefV8Value[] arguments);

        private static native CefV8Value N_ExecuteFunctionWithContext(
                long self, CefV8Context context, CefV8Value object, long argumentsCount, CefV8Value[] arguments);

        private static native boolean N_ResolvePromise(long self, CefV8Value arg);

        private static native boolean N_RejectPromise(long self, String errorMsg);

        static native CefV8Value N_CreateUndefined();

        static native CefV8Value N_CreateNull();

        static native CefV8Value N_CreateBool(int value);

        static native CefV8Value N_CreateInt(int value);

        static native CefV8Value N_CreateUint(int value);

        static native CefV8Value N_CreateDouble(double value);

        static native CefV8Value N_CreateDate(CefBasetime date);

        static native CefV8Value N_CreateString(String value);

        static native CefV8Value N_CreateObject(CefV8Accessor accessor, CefV8Interceptor interceptor);

        static native CefV8Value N_CreateArray(int length);

        static native CefV8Value N_CreateArrayBuffer(
                NativePointer buffer, long length, CefV8ArrayBufferReleaseCallback releaseCallback);

        static native CefV8Value N_CreateArrayBufferWithCopy(NativePointer buffer, long length);

        static native CefV8Value N_CreateArrayBufferFromBackingStore(CefV8BackingStore backingStore);

        static native CefV8Value N_CreateFunction(String name, CefV8Handler handler);

        static native CefV8Value N_CreatePromise();

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
