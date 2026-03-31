// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Structure representing a V8 value handle. V8 handles can only be accessed from the thread on which they are created.
 * Valid threads for creating a V8 handle include the render process main thread (TID_RENDERER) and WebWorker threads. A
 * task runner for posting tasks on the associated thread can be retrieved via the cef_v8_context_t::get_task_runner()
 * function. NOTE: This struct is allocated DLL-side.
 */
public interface CefV8Value {

    /** Returns true if this object is valid. Do not call any other methods if this function returns false. */
    boolean isValid();

    /** True if the value type is undefined. */
    boolean isUndefined();

    /** True if the value type is null. */
    boolean isNull();

    /** True if the value type is bool. */
    boolean isBool();

    /** True if the value type is int. */
    boolean isInt();

    int isUint();

    /** True if the value type is double. */
    boolean isDouble();

    /** True if the value type is Date. */
    boolean isDate();

    /** True if the value type is string. */
    boolean isString();

    /** True if the value type is object. */
    boolean isObject();

    /** True if the value type is array. */
    boolean isArray();

    /** True if the value type is an ArrayBuffer. */
    boolean isArrayBuffer();

    /** True if the value type is function. */
    boolean isFunction();

    /** True if the value type is a Promise. */
    boolean isPromise();

    /** Returns true if this object is pointing to the same handle as |that| object. */
    boolean isSame(long that);

    /** Return a bool value. */
    boolean getBoolValue();

    /** Return an int value. */
    int getIntValue();

    int getUintValue();

    /** Return a double value. */
    double getDoubleValue();

    /** Return a Date value. */
    CefBasetime getDateValue();

    /** Return a string value. */
    Optional<String> getStringValue();

    /** Returns true if this is a user created object. */
    boolean isUserCreated();

    /**
     * Returns true if the last method call resulted in an exception. This attribute exists only in the scope of the
     * current CEF value object.
     */
    boolean hasException();

    /**
     * Returns the exception resulting from the last method call. This attribute exists only in the scope of the current
     * CEF value object.
     */
    long getException();

    /** Clears the last exception and returns true on success. */
    boolean clearException();

    /**
     * Returns true if this object will re-throw future exceptions. This attribute exists only in the scope of the
     * current CEF value object.
     */
    boolean willRethrowExceptions();

    /**
     * Set whether this object will re-throw future exceptions. By default exceptions are not re-thrown. If a exception
     * is re-thrown the current context should not be accessed again until after the exception has been caught and not
     * re-thrown. Returns true on success. This attribute exists only in the scope of the current CEF value object.
     */
    boolean setRethrowExceptions(boolean rethrow);

    /**
     * Returns true if the object has a value with the specified identifier.
     *
     * @param key may be null
     */
    int hasValueBykey(@Nullable String key);

    /**
     * Returns true if the object has a value with the specified identifier.
     *
     * @param index zero-based index
     */
    int hasValueByindex(int index);

    /**
     * Deletes the value with the specified identifier and returns true on success. Returns false if this method is
     * called incorrectly or an exception is thrown. For read-only and don't-delete values this method will return true
     * even though deletion failed.
     *
     * @param key may be null
     */
    int deleteValueBykey(@Nullable String key);

    /**
     * Deletes the value with the specified identifier and returns true on success. Returns false if this method is
     * called incorrectly, deletion fails or an exception is thrown. For read-only and don't-delete values this method
     * will return true even though deletion failed.
     *
     * @param index zero-based index
     */
    int deleteValueByindex(int index);

    /**
     * Returns the value with the specified identifier on success. Returns NULL if this method is called incorrectly or
     * an exception is thrown.
     *
     * @param key may be null
     */
    long getValueBykey(@Nullable String key);

    /**
     * Associates a value with the specified identifier and returns true on success. Returns false if this method is
     * called incorrectly or an exception is thrown. For read-only values this method will return true even though
     * assignment failed.
     *
     * @param key may be null
     */
    int setValueBykey(@Nullable String key, long value, @Nonnull CefV8Propertyattribute attribute);

    /**
     * Associates a value with the specified identifier and returns true on success. Returns false if this method is
     * called incorrectly or an exception is thrown. For read-only values this method will return true even though
     * assignment failed.
     *
     * @param index zero-based index
     */
    int setValueByindex(int index, long value);

    /**
     * Registers an identifier and returns true on success. Access to the identifier will be forwarded to the
     * CefV8Accessor instance passed to CefV8Value::CreateObject(). Returns false if this method is called incorrectly
     * or an exception is thrown. For read-only values this method will return true even though assignment failed.
     *
     * @param key may be null
     */
    int setValueByaccessor(@Nullable String key, @Nonnull CefV8Propertyattribute attribute);

    /**
     * Read the keys for the object's values into the specified vector. Integer- based keys will also be returned as
     * strings.
     */
    boolean getKeys(@Nonnull java.util.List<String> keys);

    /**
     * Sets the user data for this object and returns true on success. Returns false if this method is called
     * incorrectly. This method can only be called on user created objects.
     *
     * @param userData may be null
     */
    boolean setUserData(long userData);

    /** Returns the amount of externally allocated memory registered for the object. */
    int getExternallyAllocatedMemory();

    /**
     * Adjusts the amount of registered external memory for the object. Used to give V8 an indication of the amount of
     * externally allocated memory that is kept alive by JavaScript objects. V8 uses this information to decide when to
     * perform global garbage collection. Each CefV8Value tracks the amount of external memory associated with it and
     * automatically decreases the global total by the appropriate amount on its destruction. |change_in_bytes|
     * specifies the number of bytes to adjust by. This method returns the number of bytes associated with the object
     * after the adjustment. This method can only be called on user created objects.
     */
    int adjustExternallyAllocatedMemory(int changeInBytes);

    /** Returns the number of elements in the array. */
    int getArrayLength();

    /**
     * Prevent the ArrayBuffer from using it's memory block by setting the length to zero. This operation cannot be
     * undone. If the ArrayBuffer was created with CreateArrayBuffer then CefV8ArrayBufferReleaseCallback::ReleaseBuffer
     * will be called to release the underlying buffer.
     */
    boolean neuterArrayBuffer();

    /** Returns the length (in bytes) of the ArrayBuffer. */
    long getArrayBufferByteLength();

    long getArrayBufferData();

    /** Returns the name of the function. */
    Optional<String> getFunctionName();

    /** Returns the function handler or NULL if not a CEF-created function. */
    long getFunctionHandler();

    /**
     * Execute the function using the current V8 context. This method should only be called from within the scope of a
     * CefV8Handler or CefV8Accessor callback, or in combination with calling Enter() and Exit() on a stored
     * CefV8Context reference. |object| is the receiver ('this' object) of the function. If |object| is empty the
     * current context's global object will be used. |arguments| is the list of arguments that will be passed to the
     * function. Returns the function return value on success. Returns NULL if this method is called incorrectly or an
     * exception is thrown.
     *
     * @param object may be null
     */
    long executeFunction(long object, long argumentsCount, long arguments);

    /**
     * Execute the function using the specified V8 context. |object| is the receiver ('this' object) of the function. If
     * |object| is empty the specified context's global object will be used. |arguments| is the list of arguments that
     * will be passed to the function. Returns the function return value on success. Returns NULL if this method is
     * called incorrectly or an exception is thrown.
     *
     * @param object may be null
     */
    long executeFunctionWithContext(long context, long object, long argumentsCount, long arguments);

    /**
     * Resolve the Promise using the current V8 context. This method should only be called from within the scope of a
     * CefV8Handler or CefV8Accessor callback, or in combination with calling Enter() and Exit() on a stored
     * CefV8Context reference. |arg| is the argument passed to the resolved promise. Returns true on success. Returns
     * false if this method is called incorrectly or an exception is thrown.
     *
     * @param arg may be null
     */
    boolean resolvePromise(long arg);

    /**
     * Reject the Promise using the current V8 context. This method should only be called from within the scope of a
     * CefV8Handler or CefV8Accessor callback, or in combination with calling Enter() and Exit() on a stored
     * CefV8Context reference. Returns true on success. Returns false if this method is called incorrectly or an
     * exception is thrown.
     */
    boolean rejectPromise(@Nonnull String errorMsg);

    static class NativePeer implements CefV8Value {
        private volatile long nativePtr;

        @Override
        public boolean isValid() {
            return N_IsValid(nativePtr);
        }

        @Override
        public boolean isUndefined() {
            return N_IsUndefined(nativePtr);
        }

        @Override
        public boolean isNull() {
            return N_IsNull(nativePtr);
        }

        @Override
        public boolean isBool() {
            return N_IsBool(nativePtr);
        }

        @Override
        public boolean isInt() {
            return N_IsInt(nativePtr);
        }

        @Override
        public int isUint() {
            return N_IsUint(nativePtr);
        }

        @Override
        public boolean isDouble() {
            return N_IsDouble(nativePtr);
        }

        @Override
        public boolean isDate() {
            return N_IsDate(nativePtr);
        }

        @Override
        public boolean isString() {
            return N_IsString(nativePtr);
        }

        @Override
        public boolean isObject() {
            return N_IsObject(nativePtr);
        }

        @Override
        public boolean isArray() {
            return N_IsArray(nativePtr);
        }

        @Override
        public boolean isArrayBuffer() {
            return N_IsArrayBuffer(nativePtr);
        }

        @Override
        public boolean isFunction() {
            return N_IsFunction(nativePtr);
        }

        @Override
        public boolean isPromise() {
            return N_IsPromise(nativePtr);
        }

        @Override
        public boolean isSame(long that) {
            return N_IsSame(nativePtr, that);
        }

        @Override
        public boolean getBoolValue() {
            return N_GetBoolValue(nativePtr);
        }

        @Override
        public int getIntValue() {
            return N_GetIntValue(nativePtr);
        }

        @Override
        public int getUintValue() {
            return N_GetUintValue(nativePtr);
        }

        @Override
        public double getDoubleValue() {
            return N_GetDoubleValue(nativePtr);
        }

        @Override
        public CefBasetime getDateValue() {
            return N_GetDateValue(nativePtr);
        }

        @Override
        public Optional<String> getStringValue() {
            return Optional.ofNullable(N_GetStringValue(nativePtr));
        }

        @Override
        public boolean isUserCreated() {
            return N_IsUserCreated(nativePtr);
        }

        @Override
        public boolean hasException() {
            return N_HasException(nativePtr);
        }

        @Override
        public long getException() {
            return N_GetException(nativePtr);
        }

        @Override
        public boolean clearException() {
            return N_ClearException(nativePtr);
        }

        @Override
        public boolean willRethrowExceptions() {
            return N_WillRethrowExceptions(nativePtr);
        }

        @Override
        public boolean setRethrowExceptions(boolean rethrow) {
            return N_SetRethrowExceptions(nativePtr, rethrow);
        }

        @Override
        public int hasValueBykey(String key) {
            return N_HasValueBykey(nativePtr, key);
        }

        @Override
        public int hasValueByindex(int index) {
            return N_HasValueByindex(nativePtr, index);
        }

        @Override
        public int deleteValueBykey(String key) {
            return N_DeleteValueBykey(nativePtr, key);
        }

        @Override
        public int deleteValueByindex(int index) {
            return N_DeleteValueByindex(nativePtr, index);
        }

        @Override
        public long getValueBykey(String key) {
            return N_GetValueBykey(nativePtr, key);
        }

        @Override
        public int setValueBykey(String key, long value, CefV8Propertyattribute attribute) {
            return N_SetValueBykey(nativePtr, key, value, attribute);
        }

        @Override
        public int setValueByindex(int index, long value) {
            return N_SetValueByindex(nativePtr, index, value);
        }

        @Override
        public int setValueByaccessor(String key, CefV8Propertyattribute attribute) {
            return N_SetValueByaccessor(nativePtr, key, attribute);
        }

        @Override
        public boolean getKeys(java.util.List<String> keys) {
            return N_GetKeys(nativePtr, keys);
        }

        @Override
        public boolean setUserData(long userData) {
            return N_SetUserData(nativePtr, userData);
        }

        @Override
        public int getExternallyAllocatedMemory() {
            return N_GetExternallyAllocatedMemory(nativePtr);
        }

        @Override
        public int adjustExternallyAllocatedMemory(int changeInBytes) {
            return N_AdjustExternallyAllocatedMemory(nativePtr, changeInBytes);
        }

        @Override
        public int getArrayLength() {
            return N_GetArrayLength(nativePtr);
        }

        @Override
        public boolean neuterArrayBuffer() {
            return N_NeuterArrayBuffer(nativePtr);
        }

        @Override
        public long getArrayBufferByteLength() {
            return N_GetArrayBufferByteLength(nativePtr);
        }

        @Override
        public long getArrayBufferData() {
            return N_GetArrayBufferData(nativePtr);
        }

        @Override
        public Optional<String> getFunctionName() {
            return Optional.ofNullable(N_GetFunctionName(nativePtr));
        }

        @Override
        public long getFunctionHandler() {
            return N_GetFunctionHandler(nativePtr);
        }

        @Override
        public long executeFunction(long object, long argumentsCount, long arguments) {
            return N_ExecuteFunction(nativePtr, object, argumentsCount, arguments);
        }

        @Override
        public long executeFunctionWithContext(long context, long object, long argumentsCount, long arguments) {
            return N_ExecuteFunctionWithContext(nativePtr, context, object, argumentsCount, arguments);
        }

        @Override
        public boolean resolvePromise(long arg) {
            return N_ResolvePromise(nativePtr, arg);
        }

        @Override
        public boolean rejectPromise(String errorMsg) {
            return N_RejectPromise(nativePtr, errorMsg);
        }

        private native boolean N_IsValid(long self);

        private native boolean N_IsUndefined(long self);

        private native boolean N_IsNull(long self);

        private native boolean N_IsBool(long self);

        private native boolean N_IsInt(long self);

        private native int N_IsUint(long self);

        private native boolean N_IsDouble(long self);

        private native boolean N_IsDate(long self);

        private native boolean N_IsString(long self);

        private native boolean N_IsObject(long self);

        private native boolean N_IsArray(long self);

        private native boolean N_IsArrayBuffer(long self);

        private native boolean N_IsFunction(long self);

        private native boolean N_IsPromise(long self);

        private native boolean N_IsSame(long self, long that);

        private native boolean N_GetBoolValue(long self);

        private native int N_GetIntValue(long self);

        private native int N_GetUintValue(long self);

        private native double N_GetDoubleValue(long self);

        private native CefBasetime N_GetDateValue(long self);

        private native String N_GetStringValue(long self);

        private native boolean N_IsUserCreated(long self);

        private native boolean N_HasException(long self);

        private native long N_GetException(long self);

        private native boolean N_ClearException(long self);

        private native boolean N_WillRethrowExceptions(long self);

        private native boolean N_SetRethrowExceptions(long self, boolean rethrow);

        private native int N_HasValueBykey(long self, String key);

        private native int N_HasValueByindex(long self, int index);

        private native int N_DeleteValueBykey(long self, String key);

        private native int N_DeleteValueByindex(long self, int index);

        private native long N_GetValueBykey(long self, String key);

        private native int N_SetValueBykey(long self, String key, long value, CefV8Propertyattribute attribute);

        private native int N_SetValueByindex(long self, int index, long value);

        private native int N_SetValueByaccessor(long self, String key, CefV8Propertyattribute attribute);

        private native boolean N_GetKeys(long self, java.util.List<String> keys);

        private native boolean N_SetUserData(long self, long userData);

        private native int N_GetExternallyAllocatedMemory(long self);

        private native int N_AdjustExternallyAllocatedMemory(long self, int changeInBytes);

        private native int N_GetArrayLength(long self);

        private native boolean N_NeuterArrayBuffer(long self);

        private native long N_GetArrayBufferByteLength(long self);

        private native long N_GetArrayBufferData(long self);

        private native String N_GetFunctionName(long self);

        private native long N_GetFunctionHandler(long self);

        private native long N_ExecuteFunction(long self, long object, long argumentsCount, long arguments);

        private native long N_ExecuteFunctionWithContext(
                long self, long context, long object, long argumentsCount, long arguments);

        private native boolean N_ResolvePromise(long self, long arg);

        private native boolean N_RejectPromise(long self, String errorMsg);

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
