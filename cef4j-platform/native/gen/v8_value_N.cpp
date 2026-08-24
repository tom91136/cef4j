// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
#include <jni.h>
#include "include/capi/cef_v8_capi.h"
#include "jni_util.h"

extern "C" cef_v8_accessor_t* Create_JniCefV8Accessor(JNIEnv* env, jobject handler);
extern "C" cef_v8_interceptor_t* Create_JniCefV8Interceptor(JNIEnv* env, jobject handler);
extern "C" cef_v8_array_buffer_release_callback_t* Create_JniCefV8ArrayBufferReleaseCallback(JNIEnv* env, jobject handler);
extern "C" cef_v8_handler_t* Create_JniCefV8Handler(JNIEnv* env, jobject handler);
CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefV8Value), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefV8Value), isValid0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_valid(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefV8Value), isUndefined0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_undefined(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefV8Value), isNull0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_null(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefV8Value), isBool0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_bool(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefV8Value), isInt0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_int(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefV8Value), isUInt0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_uint(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefV8Value), isDouble0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_double(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefV8Value), isDate0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_date(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefV8Value), isString0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_string(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefV8Value), isObject0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_object(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefV8Value), isArray0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_array(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefV8Value), isArrayBuffer0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_array_buffer(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefV8Value), isFunction0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_function(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefV8Value), isPromise0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_promise(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefV8Value), isSame0)(JNIEnv* env, jobject obj, jlong self, jobject that) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return JNI_FALSE;
    cef_v8_value_t* _that_ptr = that ? reinterpret_cast<cef_v8_value_t*>(env->GetLongField(that, env->GetFieldID(env->GetObjectClass(that), "nativePtr", "J"))) : nullptr;
    if (_that_ptr) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_that_ptr); _b->add_ref(_b); }
    auto _r = s->is_same(s, _that_ptr);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefV8Value), getBoolValue0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->get_bool_value(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jint, CEF4J_PEER(CefV8Value), getIntValue0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->get_int_value(s));
}

CEF4J_JNI_EXPORT(jint, CEF4J_PEER(CefV8Value), getUIntValue0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->get_uint_value(s));
}

CEF4J_JNI_EXPORT(jdouble, CEF4J_PEER(CefV8Value), getDoubleValue0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return 0;
    return static_cast<jdouble>(s->get_double_value(s));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefV8Value), getDateValue0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return nullptr;
    cef_basetime_t result = s->get_date_value(s);
    auto cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefBasetime");
    auto ctor = env->GetMethodID(cls, "<init>", "(J)V");
    auto _dsResult = env->NewObject(cls, ctor, to_jlong(((&result))->val));
    return _dsResult;
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefV8Value), getStringValue0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_string_value(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefV8Value), isUserCreated0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_user_created(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefV8Value), hasException0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->has_exception(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefV8Value), getException0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_exception(s);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefV8Exception$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefV8Value), clearException0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->clear_exception(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefV8Value), willRethrowExceptions0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->will_rethrow_exceptions(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefV8Value), setRethrowExceptions0)(JNIEnv* env, jobject obj, jlong self, jboolean rethrow) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->set_rethrow_exceptions(s, static_cast<bool>(rethrow));
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jint, CEF4J_PEER(CefV8Value), hasValueBykey0)(JNIEnv* env, jobject obj, jlong self, jstring key) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return 0;
    auto _key_str = key ? JStringToCefString(env, key) : nullptr;
    return static_cast<jint>(s->has_value_bykey(s, _key_str));
}

CEF4J_JNI_EXPORT(jint, CEF4J_PEER(CefV8Value), hasValueByindex0)(JNIEnv* env, jobject obj, jlong self, jint index) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->has_value_byindex(s, index));
}

CEF4J_JNI_EXPORT(jint, CEF4J_PEER(CefV8Value), deleteValueBykey0)(JNIEnv* env, jobject obj, jlong self, jstring key) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return 0;
    auto _key_str = key ? JStringToCefString(env, key) : nullptr;
    return static_cast<jint>(s->delete_value_bykey(s, _key_str));
}

CEF4J_JNI_EXPORT(jint, CEF4J_PEER(CefV8Value), deleteValueByindex0)(JNIEnv* env, jobject obj, jlong self, jint index) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->delete_value_byindex(s, index));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefV8Value), getValueBykey0)(JNIEnv* env, jobject obj, jlong self, jstring key) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return nullptr;
    auto _key_str = key ? JStringToCefString(env, key) : nullptr;
    auto _r = s->get_value_bykey(s, _key_str);
    if (_key_str) cef_string_userfree_free(_key_str);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefV8Value$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefV8Value), getValueByindex0)(JNIEnv* env, jobject obj, jlong self, jint index) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_value_byindex(s, index);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefV8Value$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jint, CEF4J_PEER(CefV8Value), setValueBykey0)(JNIEnv* env, jobject obj, jlong self, jstring key, jobject value, jobject attribute) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return 0;
    if (!attribute) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "attribute must not be null"); return 0; }
    auto _key_str = key ? JStringToCefString(env, key) : nullptr;
    cef_v8_value_t* _value_ptr = value ? reinterpret_cast<cef_v8_value_t*>(env->GetLongField(value, env->GetFieldID(env->GetObjectClass(value), "nativePtr", "J"))) : nullptr;
    if (_value_ptr) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_value_ptr); _b->add_ref(_b); }
    return static_cast<jint>(s->set_value_bykey(s, _key_str, _value_ptr, static_cast<cef_v8_propertyattribute_t>(env->GetLongField(attribute, env->GetFieldID(env->GetObjectClass(attribute), "value", "J")))));
}

CEF4J_JNI_EXPORT(jint, CEF4J_PEER(CefV8Value), setValueByindex0)(JNIEnv* env, jobject obj, jlong self, jint index, jobject value) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return 0;
    cef_v8_value_t* _value_ptr = value ? reinterpret_cast<cef_v8_value_t*>(env->GetLongField(value, env->GetFieldID(env->GetObjectClass(value), "nativePtr", "J"))) : nullptr;
    if (_value_ptr) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_value_ptr); _b->add_ref(_b); }
    return static_cast<jint>(s->set_value_byindex(s, index, _value_ptr));
}

CEF4J_JNI_EXPORT(jint, CEF4J_PEER(CefV8Value), setValueByaccessor0)(JNIEnv* env, jobject obj, jlong self, jstring key, jobject attribute) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return 0;
    if (!attribute) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "attribute must not be null"); return 0; }
    auto _key_str = key ? JStringToCefString(env, key) : nullptr;
    return static_cast<jint>(s->set_value_byaccessor(s, _key_str, static_cast<cef_v8_propertyattribute_t>(env->GetLongField(attribute, env->GetFieldID(env->GetObjectClass(attribute), "value", "J")))));
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefV8Value), getKeys0)(JNIEnv* env, jobject obj, jlong self, jobject keys) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return JNI_FALSE;
    if (!keys) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "keys must not be null"); return JNI_FALSE; }
    auto _keys_csl = JavaListToCefStringList(env, keys);
    auto _r = s->get_keys(s, _keys_csl);
    CefStringListWriteBack(env, _keys_csl, keys);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefV8Value), setUserData0)(JNIEnv* env, jobject obj, jlong self, jobject user_data) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->set_user_data(s, reinterpret_cast<struct _cef_base_ref_counted_t*>(user_data ? env->GetLongField(user_data, env->GetFieldID(env->GetObjectClass(user_data), "address", "J")) : 0));
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefV8Value), getUserData0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_user_data(s);
    auto _npCls = FindClassCached(env, "net/kurobako/cef4j/gen/NativePointer");
    auto _npCtor = env->GetMethodID(_npCls, "<init>", "(J)V");
    return env->NewObject(_npCls, _npCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jint, CEF4J_PEER(CefV8Value), getExternallyAllocatedMemory0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->get_externally_allocated_memory(s));
}

CEF4J_JNI_EXPORT(jint, CEF4J_PEER(CefV8Value), adjustExternallyAllocatedMemory0)(JNIEnv* env, jobject obj, jlong self, jint change_in_bytes) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->adjust_externally_allocated_memory(s, change_in_bytes));
}

CEF4J_JNI_EXPORT(jint, CEF4J_PEER(CefV8Value), getArrayLength0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->get_array_length(s));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefV8Value), getArrayBufferReleaseCallback0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_array_buffer_release_callback(s);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefV8ArrayBufferReleaseCallback$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefV8Value), neuterArrayBuffer0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->neuter_array_buffer(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jlong, CEF4J_PEER(CefV8Value), getArrayBufferByteLength0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return 0;
    return static_cast<jlong>(s->get_array_buffer_byte_length(s));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefV8Value), getArrayBufferData0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_array_buffer_data(s);
    auto _npCls = FindClassCached(env, "net/kurobako/cef4j/gen/NativePointer");
    auto _npCtor = env->GetMethodID(_npCls, "<init>", "(J)V");
    return env->NewObject(_npCls, _npCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefV8Value), getFunctionName0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_function_name(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefV8Value), getFunctionHandler0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_function_handler(s);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefV8Handler$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefV8Value), executeFunction0)(JNIEnv* env, jobject obj, jlong self, jobject object, jlong argumentsCount, jobjectArray arguments) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return nullptr;
    jsize _arguments_len = arguments ? env->GetArrayLength(arguments) : 0;
    if (static_cast<unsigned long long>(argumentsCount) > static_cast<unsigned long long>(_arguments_len)) { env->ThrowNew(FindClassCached(env, "java/lang/IllegalArgumentException"), "arguments count exceeds array length"); return nullptr; }
    cef_v8_value_t* _object_ptr = object ? reinterpret_cast<cef_v8_value_t*>(env->GetLongField(object, env->GetFieldID(env->GetObjectClass(object), "nativePtr", "J"))) : nullptr;
    if (_object_ptr) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_object_ptr); _b->add_ref(_b); }
    size_t _arguments_sz = static_cast<size_t>(argumentsCount);
    cef_v8_value_t** _arguments_arr = _arguments_sz > 0 ? new cef_v8_value_t*[_arguments_sz]() : nullptr;
    auto _r = s->execute_function(s, _object_ptr, argumentsCount, _arguments_arr);
    auto _arguments_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefV8Value$NativePeer");
    auto _arguments_ctor = env->GetMethodID(_arguments_cls, "<init>", "(J)V");
    for (size_t _i = 0; _i < _arguments_sz; _i++) {
        auto _elem = _arguments_arr[_i] ? env->NewObject(_arguments_cls, _arguments_ctor, reinterpret_cast<jlong>(_arguments_arr[_i])) : nullptr;
        env->SetObjectArrayElement(arguments, static_cast<jsize>(_i), _elem);
    }
    delete[] _arguments_arr;
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefV8Value$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefV8Value), executeFunctionWithContext0)(JNIEnv* env, jobject obj, jlong self, jobject context, jobject object, jlong argumentsCount, jobjectArray arguments) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return nullptr;
    jsize _arguments_len = arguments ? env->GetArrayLength(arguments) : 0;
    if (static_cast<unsigned long long>(argumentsCount) > static_cast<unsigned long long>(_arguments_len)) { env->ThrowNew(FindClassCached(env, "java/lang/IllegalArgumentException"), "arguments count exceeds array length"); return nullptr; }
    cef_v8_context_t* _context_ptr = context ? reinterpret_cast<cef_v8_context_t*>(env->GetLongField(context, env->GetFieldID(env->GetObjectClass(context), "nativePtr", "J"))) : nullptr;
    if (_context_ptr) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_context_ptr); _b->add_ref(_b); }
    cef_v8_value_t* _object_ptr = object ? reinterpret_cast<cef_v8_value_t*>(env->GetLongField(object, env->GetFieldID(env->GetObjectClass(object), "nativePtr", "J"))) : nullptr;
    if (_object_ptr) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_object_ptr); _b->add_ref(_b); }
    size_t _arguments_sz = static_cast<size_t>(argumentsCount);
    cef_v8_value_t** _arguments_arr = _arguments_sz > 0 ? new cef_v8_value_t*[_arguments_sz]() : nullptr;
    auto _r = s->execute_function_with_context(s, _context_ptr, _object_ptr, argumentsCount, _arguments_arr);
    auto _arguments_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefV8Value$NativePeer");
    auto _arguments_ctor = env->GetMethodID(_arguments_cls, "<init>", "(J)V");
    for (size_t _i = 0; _i < _arguments_sz; _i++) {
        auto _elem = _arguments_arr[_i] ? env->NewObject(_arguments_cls, _arguments_ctor, reinterpret_cast<jlong>(_arguments_arr[_i])) : nullptr;
        env->SetObjectArrayElement(arguments, static_cast<jsize>(_i), _elem);
    }
    delete[] _arguments_arr;
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefV8Value$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefV8Value), resolvePromise0)(JNIEnv* env, jobject obj, jlong self, jobject arg) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return JNI_FALSE;
    cef_v8_value_t* _arg_ptr = arg ? reinterpret_cast<cef_v8_value_t*>(env->GetLongField(arg, env->GetFieldID(env->GetObjectClass(arg), "nativePtr", "J"))) : nullptr;
    if (_arg_ptr) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_arg_ptr); _b->add_ref(_b); }
    auto _r = s->resolve_promise(s, _arg_ptr);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefV8Value), rejectPromise0)(JNIEnv* env, jobject obj, jlong self, jstring errorMsg) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _errorMsg_str = JStringToCefString(env, errorMsg);
    auto _r = s->reject_promise(s, _errorMsg_str);
    if (_errorMsg_str) cef_string_userfree_free(_errorMsg_str);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefV8Value), createUndefined0)(JNIEnv* env, jclass clz) {
    auto _r = cef_v8_value_create_undefined();
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefV8Value$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefV8Value), createNull0)(JNIEnv* env, jclass clz) {
    auto _r = cef_v8_value_create_null();
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefV8Value$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefV8Value), createBool0)(JNIEnv* env, jclass clz, jint value) {
    auto _r = cef_v8_value_create_bool(value);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefV8Value$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefV8Value), createInt0)(JNIEnv* env, jclass clz, jint value) {
    auto _r = cef_v8_value_create_int(static_cast<int32_t>(value));
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefV8Value$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefV8Value), createUint0)(JNIEnv* env, jclass clz, jint value) {
    auto _r = cef_v8_value_create_uint(static_cast<uint32_t>(value));
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefV8Value$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefV8Value), createDouble0)(JNIEnv* env, jclass clz, jdouble value) {
    auto _r = cef_v8_value_create_double(value);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefV8Value$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefV8Value), createDate0)(JNIEnv* env, jclass clz, jobject date) {
    cef_basetime_t _date_val = {};
    auto _date_c = FindClassCached(env, "net/kurobako/cef4j/gen/CefBasetime");
    _date_val.val = from_jlong<decltype(_date_val.val)>(env->GetLongField(date, env->GetFieldID(_date_c, "val", "J")));
    auto _r = cef_v8_value_create_date(_date_val);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefV8Value$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefV8Value), createString0)(JNIEnv* env, jclass clz, jstring value) {
    auto _value_str = value ? JStringToCefString(env, value) : nullptr;
    auto _r = cef_v8_value_create_string(_value_str);
    if (_value_str) cef_string_userfree_free(_value_str);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefV8Value$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefV8Value), createObject0)(JNIEnv* env, jclass clz, jobject accessor, jobject interceptor) {
    cef_v8_accessor_t* _accessor_ptr = accessor ? Create_JniCefV8Accessor(env, accessor) : nullptr;
    cef_v8_interceptor_t* _interceptor_ptr = interceptor ? Create_JniCefV8Interceptor(env, interceptor) : nullptr;
    auto _r = cef_v8_value_create_object(_accessor_ptr, _interceptor_ptr);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefV8Value$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefV8Value), createArray0)(JNIEnv* env, jclass clz, jint length) {
    auto _r = cef_v8_value_create_array(length);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefV8Value$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefV8Value), createArrayBuffer0)(JNIEnv* env, jclass clz, jobject buffer, jobject release_callback) {
    void* _buffer_addr = buffer ? env->GetDirectBufferAddress(buffer) : nullptr;
    if (buffer && !_buffer_addr) { env->ThrowNew(FindClassCached(env, "java/lang/IllegalArgumentException"), "buffer must be a direct ByteBuffer; use ByteBuffer.allocateDirect(...)"); return nullptr; }
    cef_v8_array_buffer_release_callback_t* _release_callback_ptr = release_callback ? Create_JniCefV8ArrayBufferReleaseCallback(env, release_callback) : nullptr;
    auto _r = cef_v8_value_create_array_buffer(_buffer_addr, static_cast<size_t>(env->GetDirectBufferCapacity(buffer)), _release_callback_ptr);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefV8Value$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefV8Value), createArrayBufferWithCopy0)(JNIEnv* env, jclass clz, jobject buffer) {
    void* _buffer_addr = buffer ? env->GetDirectBufferAddress(buffer) : nullptr;
    if (buffer && !_buffer_addr) { env->ThrowNew(FindClassCached(env, "java/lang/IllegalArgumentException"), "buffer must be a direct ByteBuffer; use ByteBuffer.allocateDirect(...)"); return nullptr; }
    auto _r = cef_v8_value_create_array_buffer_with_copy(_buffer_addr, static_cast<size_t>(env->GetDirectBufferCapacity(buffer)));
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefV8Value$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefV8Value), createArrayBufferFromBackingStore0)(JNIEnv* env, jclass clz, jobject backing_store) {
    cef_v8_backing_store_t* _backing_store_ptr = backing_store ? reinterpret_cast<cef_v8_backing_store_t*>(env->GetLongField(backing_store, env->GetFieldID(env->GetObjectClass(backing_store), "nativePtr", "J"))) : nullptr;
    if (_backing_store_ptr) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_backing_store_ptr); _b->add_ref(_b); }
    auto _r = cef_v8_value_create_array_buffer_from_backing_store(_backing_store_ptr);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefV8Value$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefV8Value), createFunction0)(JNIEnv* env, jclass clz, jstring name, jobject handler) {
    auto _name_str = JStringToCefString(env, name);
    cef_v8_handler_t* _handler_ptr = handler ? Create_JniCefV8Handler(env, handler) : nullptr;
    auto _r = cef_v8_value_create_function(_name_str, _handler_ptr);
    if (_name_str) cef_string_userfree_free(_name_str);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefV8Value$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefV8Value), createPromise0)(JNIEnv* env, jclass clz) {
    auto _r = cef_v8_value_create_promise();
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefV8Value$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}
