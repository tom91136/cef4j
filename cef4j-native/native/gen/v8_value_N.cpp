// GENERATED - do not edit.
#include <jni.h>
#include "include/capi/cef_v8_capi.h"
#include "jni_util.h"

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefV8Value_00024NativePeer_N_1Release(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefV8Value_00024NativePeer_N_1IsValid(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_valid(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefV8Value_00024NativePeer_N_1IsUndefined(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_undefined(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefV8Value_00024NativePeer_N_1IsNull(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_null(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefV8Value_00024NativePeer_N_1IsBool(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_bool(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefV8Value_00024NativePeer_N_1IsInt(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_int(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefV8Value_00024NativePeer_N_1IsUInt(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_uint(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefV8Value_00024NativePeer_N_1IsDouble(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_double(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefV8Value_00024NativePeer_N_1IsDate(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_date(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefV8Value_00024NativePeer_N_1IsString(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_string(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefV8Value_00024NativePeer_N_1IsObject(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_object(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefV8Value_00024NativePeer_N_1IsArray(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_array(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefV8Value_00024NativePeer_N_1IsArrayBuffer(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_array_buffer(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefV8Value_00024NativePeer_N_1IsFunction(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_function(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefV8Value_00024NativePeer_N_1IsPromise(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_promise(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefV8Value_00024NativePeer_N_1IsSame(JNIEnv* env, jobject obj, jlong self, jobject that) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return JNI_FALSE;
    if (!that) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "that must not be null"); return JNI_FALSE;}
    cef_v8_value_t* _that_ptr = reinterpret_cast<cef_v8_value_t*>(env->GetLongField(that, env->GetFieldID(env->GetObjectClass(that), "nativePtr", "J")));
    if (_that_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_that_ptr); _b->add_ref(_b);}
    auto _r = s->is_same(s, _that_ptr);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefV8Value_00024NativePeer_N_1GetBoolValue(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->get_bool_value(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jint JNICALL Java_net_kurobako_cef4j_gen_CefV8Value_00024NativePeer_N_1GetIntValue(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->get_int_value(s));
}

extern "C" JNIEXPORT jint JNICALL Java_net_kurobako_cef4j_gen_CefV8Value_00024NativePeer_N_1GetUIntValue(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->get_uint_value(s));
}

extern "C" JNIEXPORT jdouble JNICALL Java_net_kurobako_cef4j_gen_CefV8Value_00024NativePeer_N_1GetDoubleValue(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return 0;
    return static_cast<jdouble>(s->get_double_value(s));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefV8Value_00024NativePeer_N_1GetDateValue(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return nullptr;
    cef_basetime_t result = s->get_date_value(s);
    auto cls = env->FindClass("net/kurobako/cef4j/gen/CefBasetime");
    auto ctor = env->GetMethodID(cls, "<init>", "(J)V");
    auto _dsResult = env->NewObject(cls, ctor, static_cast<jlong>((&result)->val));
    return _dsResult;
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefV8Value_00024NativePeer_N_1GetStringValue(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_string_value(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefV8Value_00024NativePeer_N_1IsUserCreated(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_user_created(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefV8Value_00024NativePeer_N_1HasException(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->has_exception(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefV8Value_00024NativePeer_N_1GetException(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_exception(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefV8Exception$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefV8Value_00024NativePeer_N_1ClearException(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->clear_exception(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefV8Value_00024NativePeer_N_1WillRethrowExceptions(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->will_rethrow_exceptions(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefV8Value_00024NativePeer_N_1SetRethrowExceptions(JNIEnv* env, jobject obj, jlong self, jboolean rethrow) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->set_rethrow_exceptions(s, static_cast<bool>(rethrow));
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jint JNICALL Java_net_kurobako_cef4j_gen_CefV8Value_00024NativePeer_N_1HasValueBykey(JNIEnv* env, jobject obj, jlong self, jstring key) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return 0;
    auto _key_str = key ? JStringToCefString(env, key) : nullptr;
    return static_cast<jint>(s->has_value_bykey(s, _key_str));
}

extern "C" JNIEXPORT jint JNICALL Java_net_kurobako_cef4j_gen_CefV8Value_00024NativePeer_N_1HasValueByindex(JNIEnv* env, jobject obj, jlong self, jint index) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->has_value_byindex(s, index));
}

extern "C" JNIEXPORT jint JNICALL Java_net_kurobako_cef4j_gen_CefV8Value_00024NativePeer_N_1DeleteValueBykey(JNIEnv* env, jobject obj, jlong self, jstring key) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return 0;
    auto _key_str = key ? JStringToCefString(env, key) : nullptr;
    return static_cast<jint>(s->delete_value_bykey(s, _key_str));
}

extern "C" JNIEXPORT jint JNICALL Java_net_kurobako_cef4j_gen_CefV8Value_00024NativePeer_N_1DeleteValueByindex(JNIEnv* env, jobject obj, jlong self, jint index) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->delete_value_byindex(s, index));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefV8Value_00024NativePeer_N_1GetValueBykey(JNIEnv* env, jobject obj, jlong self, jstring key) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return nullptr;
    auto _key_str = key ? JStringToCefString(env, key) : nullptr;
    auto _r = s->get_value_bykey(s, _key_str);
    if (_key_str) cef_string_userfree_free(_key_str);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefV8Value$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jint JNICALL Java_net_kurobako_cef4j_gen_CefV8Value_00024NativePeer_N_1SetValueBykey(JNIEnv* env, jobject obj, jlong self, jstring key, jobject value, jobject attribute) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return 0;
    if (!value) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "value must not be null"); return 0;}
    if (!attribute) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "attribute must not be null"); return 0;}
    auto _key_str = key ? JStringToCefString(env, key) : nullptr;
    cef_v8_value_t* _value_ptr = reinterpret_cast<cef_v8_value_t*>(env->GetLongField(value, env->GetFieldID(env->GetObjectClass(value), "nativePtr", "J")));
    if (_value_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_value_ptr); _b->add_ref(_b);}
    return static_cast<jint>(s->set_value_bykey(s, _key_str, _value_ptr, static_cast<cef_v8_propertyattribute_t>(env->GetLongField(attribute, env->GetFieldID(env->GetObjectClass(attribute), "value", "J")))));
}

extern "C" JNIEXPORT jint JNICALL Java_net_kurobako_cef4j_gen_CefV8Value_00024NativePeer_N_1SetValueByindex(JNIEnv* env, jobject obj, jlong self, jint index, jobject value) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return 0;
    if (!value) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "value must not be null"); return 0;}
    cef_v8_value_t* _value_ptr = reinterpret_cast<cef_v8_value_t*>(env->GetLongField(value, env->GetFieldID(env->GetObjectClass(value), "nativePtr", "J")));
    if (_value_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_value_ptr); _b->add_ref(_b);}
    return static_cast<jint>(s->set_value_byindex(s, index, _value_ptr));
}

extern "C" JNIEXPORT jint JNICALL Java_net_kurobako_cef4j_gen_CefV8Value_00024NativePeer_N_1SetValueByaccessor(JNIEnv* env, jobject obj, jlong self, jstring key, jobject attribute) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return 0;
    if (!attribute) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "attribute must not be null"); return 0;}
    auto _key_str = key ? JStringToCefString(env, key) : nullptr;
    return static_cast<jint>(s->set_value_byaccessor(s, _key_str, static_cast<cef_v8_propertyattribute_t>(env->GetLongField(attribute, env->GetFieldID(env->GetObjectClass(attribute), "value", "J")))));
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefV8Value_00024NativePeer_N_1GetKeys(JNIEnv* env, jobject obj, jlong self, jobject keys) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return JNI_FALSE;
    if (!keys) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "keys must not be null"); return JNI_FALSE;}
    auto _keys_csl = JavaListToCefStringList(env, keys);
    auto _r = s->get_keys(s, _keys_csl);
    CefStringListWriteBack(env, _keys_csl, keys);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefV8Value_00024NativePeer_N_1SetUserData(JNIEnv* env, jobject obj, jlong self, jobject user_data) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->set_user_data(s, reinterpret_cast<struct _cef_base_ref_counted_t*>(user_data ? env->GetLongField(user_data, env->GetFieldID(env->GetObjectClass(user_data), "address", "J")) : 0));
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefV8Value_00024NativePeer_N_1GetUserData(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_user_data(s);
    auto _npCls = env->FindClass("net/kurobako/cef4j/gen/NativePointer");
    auto _npCtor = env->GetMethodID(_npCls, "<init>", "(J)V");
    return env->NewObject(_npCls, _npCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jint JNICALL Java_net_kurobako_cef4j_gen_CefV8Value_00024NativePeer_N_1GetExternallyAllocatedMemory(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->get_externally_allocated_memory(s));
}

extern "C" JNIEXPORT jint JNICALL Java_net_kurobako_cef4j_gen_CefV8Value_00024NativePeer_N_1AdjustExternallyAllocatedMemory(JNIEnv* env, jobject obj, jlong self, jint change_in_bytes) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->adjust_externally_allocated_memory(s, change_in_bytes));
}

extern "C" JNIEXPORT jint JNICALL Java_net_kurobako_cef4j_gen_CefV8Value_00024NativePeer_N_1GetArrayLength(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->get_array_length(s));
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefV8Value_00024NativePeer_N_1NeuterArrayBuffer(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->neuter_array_buffer(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jlong JNICALL Java_net_kurobako_cef4j_gen_CefV8Value_00024NativePeer_N_1GetArrayBufferByteLength(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return 0;
    return static_cast<jlong>(s->get_array_buffer_byte_length(s));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefV8Value_00024NativePeer_N_1GetArrayBufferData(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_array_buffer_data(s);
    auto _npCls = env->FindClass("net/kurobako/cef4j/gen/NativePointer");
    auto _npCtor = env->GetMethodID(_npCls, "<init>", "(J)V");
    return env->NewObject(_npCls, _npCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefV8Value_00024NativePeer_N_1GetFunctionName(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_function_name(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefV8Value_00024NativePeer_N_1GetFunctionHandler(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_function_handler(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefV8Handler$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefV8Value_00024NativePeer_N_1ExecuteFunction(JNIEnv* env, jobject obj, jlong self, jobject object, jlong argumentsCount, jobjectArray arguments) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return nullptr;
    if (!arguments) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "arguments must not be null"); return nullptr;}
    cef_v8_value_t* _object_ptr = object ? reinterpret_cast<cef_v8_value_t*>(env->GetLongField(object, env->GetFieldID(env->GetObjectClass(object), "nativePtr", "J"))) : nullptr;
    if (_object_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_object_ptr); _b->add_ref(_b);}
    size_t _arguments_sz = static_cast<size_t>(argumentsCount);
    cef_v8_value_t** _arguments_arr = _arguments_sz > 0 ? new cef_v8_value_t*[_arguments_sz]() : nullptr;
    auto _r = s->execute_function(s, _object_ptr, argumentsCount, _arguments_arr);
    auto _arguments_cls = env->FindClass("net/kurobako/cef4j/gen/CefV8Value$NativePeer");
    auto _arguments_ctor = env->GetMethodID(_arguments_cls, "<init>", "(J)V");
    for (size_t _i = 0; _i < _arguments_sz; _i++) {
        auto _elem = _arguments_arr[_i] ? env->NewObject(_arguments_cls, _arguments_ctor, reinterpret_cast<jlong>(_arguments_arr[_i])) : nullptr;
        env->SetObjectArrayElement(arguments, _i, _elem);
    }
    delete[] _arguments_arr;
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefV8Value$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefV8Value_00024NativePeer_N_1ExecuteFunctionWithContext(JNIEnv* env, jobject obj, jlong self, jobject context, jobject object, jlong argumentsCount, jobjectArray arguments) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return nullptr;
    if (!context) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "context must not be null"); return nullptr;}
    if (!arguments) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "arguments must not be null"); return nullptr;}
    cef_v8_context_t* _context_ptr = reinterpret_cast<cef_v8_context_t*>(env->GetLongField(context, env->GetFieldID(env->GetObjectClass(context), "nativePtr", "J")));
    if (_context_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_context_ptr); _b->add_ref(_b);}
    cef_v8_value_t* _object_ptr = object ? reinterpret_cast<cef_v8_value_t*>(env->GetLongField(object, env->GetFieldID(env->GetObjectClass(object), "nativePtr", "J"))) : nullptr;
    if (_object_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_object_ptr); _b->add_ref(_b);}
    size_t _arguments_sz = static_cast<size_t>(argumentsCount);
    cef_v8_value_t** _arguments_arr = _arguments_sz > 0 ? new cef_v8_value_t*[_arguments_sz]() : nullptr;
    auto _r = s->execute_function_with_context(s, _context_ptr, _object_ptr, argumentsCount, _arguments_arr);
    auto _arguments_cls = env->FindClass("net/kurobako/cef4j/gen/CefV8Value$NativePeer");
    auto _arguments_ctor = env->GetMethodID(_arguments_cls, "<init>", "(J)V");
    for (size_t _i = 0; _i < _arguments_sz; _i++) {
        auto _elem = _arguments_arr[_i] ? env->NewObject(_arguments_cls, _arguments_ctor, reinterpret_cast<jlong>(_arguments_arr[_i])) : nullptr;
        env->SetObjectArrayElement(arguments, _i, _elem);
    }
    delete[] _arguments_arr;
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefV8Value$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefV8Value_00024NativePeer_N_1ResolvePromise(JNIEnv* env, jobject obj, jlong self, jobject arg) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return JNI_FALSE;
    cef_v8_value_t* _arg_ptr = arg ? reinterpret_cast<cef_v8_value_t*>(env->GetLongField(arg, env->GetFieldID(env->GetObjectClass(arg), "nativePtr", "J"))) : nullptr;
    if (_arg_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_arg_ptr); _b->add_ref(_b);}
    auto _r = s->resolve_promise(s, _arg_ptr);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefV8Value_00024NativePeer_N_1RejectPromise(JNIEnv* env, jobject obj, jlong self, jstring errorMsg) {
    auto* s = reinterpret_cast<cef_v8_value_t*>(self);
    if (!s) return JNI_FALSE;
    if (!errorMsg) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "errormsg must not be null"); return JNI_FALSE;}
    auto _errorMsg_str = JStringToCefString(env, errorMsg);
    auto _r = s->reject_promise(s, _errorMsg_str);
    if (_errorMsg_str) cef_string_userfree_free(_errorMsg_str);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefV8Value_00024NativePeer_N_1CreateUndefined(JNIEnv* env, jclass clz) {
    auto _r = cef_v8_value_create_undefined();
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefV8Value$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefV8Value_00024NativePeer_N_1CreateNull(JNIEnv* env, jclass clz) {
    auto _r = cef_v8_value_create_null();
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefV8Value$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefV8Value_00024NativePeer_N_1CreateBool(JNIEnv* env, jclass clz, jint value) {
    auto _r = cef_v8_value_create_bool(value);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefV8Value$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefV8Value_00024NativePeer_N_1CreateInt(JNIEnv* env, jclass clz, jint value) {
    auto _r = cef_v8_value_create_int(value);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefV8Value$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefV8Value_00024NativePeer_N_1CreateUint(JNIEnv* env, jclass clz, jint value) {
    auto _r = cef_v8_value_create_uint(value);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefV8Value$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefV8Value_00024NativePeer_N_1CreateDouble(JNIEnv* env, jclass clz, jdouble value) {
    auto _r = cef_v8_value_create_double(value);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefV8Value$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefV8Value_00024NativePeer_N_1CreateDate(JNIEnv* env, jclass clz, jobject date) {
    if (!date) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "date must not be null"); return nullptr;}
    cef_basetime_t _date_val = {};
    if (date) {auto _c = env->FindClass("net/kurobako/cef4j/gen/CefBasetime"); _date_val.val = static_cast<decltype(_date_val.val)>(static_cast<size_t>(env->GetLongField(date, env->GetFieldID(_c, "val", "J"))));}
    auto _r = cef_v8_value_create_date(_date_val);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefV8Value$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefV8Value_00024NativePeer_N_1CreateString(JNIEnv* env, jclass clz, jstring value) {
    auto _value_str = value ? JStringToCefString(env, value) : nullptr;
    auto _r = cef_v8_value_create_string(_value_str);
    if (_value_str) cef_string_userfree_free(_value_str);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefV8Value$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefV8Value_00024NativePeer_N_1CreateObject(JNIEnv* env, jclass clz, jobject accessor, jobject interceptor) {
    cef_v8_accessor_t* _accessor_ptr = accessor ? reinterpret_cast<cef_v8_accessor_t*>(env->GetLongField(accessor, env->GetFieldID(env->GetObjectClass(accessor), "nativePtr", "J"))) : nullptr;
    if (_accessor_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_accessor_ptr); _b->add_ref(_b);}
    cef_v8_interceptor_t* _interceptor_ptr = interceptor ? reinterpret_cast<cef_v8_interceptor_t*>(env->GetLongField(interceptor, env->GetFieldID(env->GetObjectClass(interceptor), "nativePtr", "J"))) : nullptr;
    if (_interceptor_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_interceptor_ptr); _b->add_ref(_b);}
    auto _r = cef_v8_value_create_object(_accessor_ptr, _interceptor_ptr);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefV8Value$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefV8Value_00024NativePeer_N_1CreateArray(JNIEnv* env, jclass clz, jint length) {
    auto _r = cef_v8_value_create_array(length);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefV8Value$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefV8Value_00024NativePeer_N_1CreateArrayBuffer(JNIEnv* env, jclass clz, jobject buffer, jlong length, jobject release_callback) {
    if (!release_callback) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "releaseCallback must not be null"); return nullptr;}
    cef_v8_array_buffer_release_callback_t* _release_callback_ptr = reinterpret_cast<cef_v8_array_buffer_release_callback_t*>(env->GetLongField(release_callback, env->GetFieldID(env->GetObjectClass(release_callback), "nativePtr", "J")));
    if (_release_callback_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_release_callback_ptr); _b->add_ref(_b);}
    auto _r = cef_v8_value_create_array_buffer(reinterpret_cast<void*>(buffer ? env->GetLongField(buffer, env->GetFieldID(env->GetObjectClass(buffer), "address", "J")) : 0), length, _release_callback_ptr);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefV8Value$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefV8Value_00024NativePeer_N_1CreateArrayBufferWithCopy(JNIEnv* env, jclass clz, jobject buffer, jlong length) {
    auto _r = cef_v8_value_create_array_buffer_with_copy(reinterpret_cast<void*>(buffer ? env->GetLongField(buffer, env->GetFieldID(env->GetObjectClass(buffer), "address", "J")) : 0), length);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefV8Value$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefV8Value_00024NativePeer_N_1CreateArrayBufferFromBackingStore(JNIEnv* env, jclass clz, jobject backing_store) {
    if (!backing_store) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "backingStore must not be null"); return nullptr;}
    cef_v8_backing_store_t* _backing_store_ptr = reinterpret_cast<cef_v8_backing_store_t*>(env->GetLongField(backing_store, env->GetFieldID(env->GetObjectClass(backing_store), "nativePtr", "J")));
    if (_backing_store_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_backing_store_ptr); _b->add_ref(_b);}
    auto _r = cef_v8_value_create_array_buffer_from_backing_store(_backing_store_ptr);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefV8Value$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefV8Value_00024NativePeer_N_1CreateFunction(JNIEnv* env, jclass clz, jstring name, jobject handler) {
    if (!name) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "name must not be null"); return nullptr;}
    if (!handler) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "handler must not be null"); return nullptr;}
    auto _name_str = JStringToCefString(env, name);
    cef_v8_handler_t* _handler_ptr = reinterpret_cast<cef_v8_handler_t*>(env->GetLongField(handler, env->GetFieldID(env->GetObjectClass(handler), "nativePtr", "J")));
    if (_handler_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_handler_ptr); _b->add_ref(_b);}
    auto _r = cef_v8_value_create_function(_name_str, _handler_ptr);
    if (_name_str) cef_string_userfree_free(_name_str);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefV8Value$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefV8Value_00024NativePeer_N_1CreatePromise(JNIEnv* env, jclass clz) {
    auto _r = cef_v8_value_create_promise();
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefV8Value$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}
