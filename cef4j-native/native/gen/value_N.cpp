// GENERATED - do not edit.
#include <jni.h>
#include "include/capi/cef_values_capi.h"
#include "include/capi/cef_parser_capi.h"
#include "jni_util.h"

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefValue_00024NativePeer_N_1Release(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefValue_00024NativePeer_N_1IsValid(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_valid(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefValue_00024NativePeer_N_1IsOwned(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_owned(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefValue_00024NativePeer_N_1IsReadOnly(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_read_only(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefValue_00024NativePeer_N_1IsSame(JNIEnv* env, jobject obj, jlong self, jobject that) {
    auto* s = reinterpret_cast<cef_value_t*>(self);
    if (!s) return JNI_FALSE;
    cef_value_t* _that_ptr = that ? reinterpret_cast<cef_value_t*>(env->GetLongField(that, env->GetFieldID(env->GetObjectClass(that), "nativePtr", "J"))) : nullptr;
    if (_that_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_that_ptr); _b->add_ref(_b);}
    auto _r = s->is_same(s, _that_ptr);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefValue_00024NativePeer_N_1IsEqual(JNIEnv* env, jobject obj, jlong self, jobject that) {
    auto* s = reinterpret_cast<cef_value_t*>(self);
    if (!s) return JNI_FALSE;
    cef_value_t* _that_ptr = that ? reinterpret_cast<cef_value_t*>(env->GetLongField(that, env->GetFieldID(env->GetObjectClass(that), "nativePtr", "J"))) : nullptr;
    if (_that_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_that_ptr); _b->add_ref(_b);}
    auto _r = s->is_equal(s, _that_ptr);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefValue_00024NativePeer_N_1Copy(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_value_t*>(self);
    if (!s) return nullptr;
    auto _r = s->copy(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefValue$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefValue_00024NativePeer_N_1GetType(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_value_t*>(self);
    if (!s) return 0;
    auto _r = s->get_type(s);
    auto _eCls = env->FindClass("net/kurobako/cef4j/gen/CefValueType");
    auto _eOf = env->GetStaticMethodID(_eCls, "of", "(J)Lnet/kurobako/cef4j/gen/CefValueType;");
    return env->CallStaticObjectMethod(_eCls, _eOf, static_cast<jlong>(_r));
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefValue_00024NativePeer_N_1GetBool(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->get_bool(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jint JNICALL Java_net_kurobako_cef4j_gen_CefValue_00024NativePeer_N_1GetInt(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_value_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->get_int(s));
}

extern "C" JNIEXPORT jdouble JNICALL Java_net_kurobako_cef4j_gen_CefValue_00024NativePeer_N_1GetDouble(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_value_t*>(self);
    if (!s) return 0;
    return static_cast<jdouble>(s->get_double(s));
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefValue_00024NativePeer_N_1GetString(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_value_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_string(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefValue_00024NativePeer_N_1GetBinary(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_value_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_binary(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefBinaryValue$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefValue_00024NativePeer_N_1GetDictionary(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_value_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_dictionary(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefDictionaryValue$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefValue_00024NativePeer_N_1GetList(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_value_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_list(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefListValue$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefValue_00024NativePeer_N_1SetNull(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->set_null(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefValue_00024NativePeer_N_1SetBool(JNIEnv* env, jobject obj, jlong self, jboolean value) {
    auto* s = reinterpret_cast<cef_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->set_bool(s, static_cast<bool>(value));
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefValue_00024NativePeer_N_1SetInt(JNIEnv* env, jobject obj, jlong self, jint value) {
    auto* s = reinterpret_cast<cef_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->set_int(s, value);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefValue_00024NativePeer_N_1SetDouble(JNIEnv* env, jobject obj, jlong self, jdouble value) {
    auto* s = reinterpret_cast<cef_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->set_double(s, value);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefValue_00024NativePeer_N_1SetString(JNIEnv* env, jobject obj, jlong self, jstring value) {
    auto* s = reinterpret_cast<cef_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _value_str = value ? JStringToCefString(env, value) : nullptr;
    auto _r = s->set_string(s, _value_str);
    if (_value_str) cef_string_userfree_free(_value_str);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefValue_00024NativePeer_N_1SetBinary(JNIEnv* env, jobject obj, jlong self, jobject value) {
    auto* s = reinterpret_cast<cef_value_t*>(self);
    if (!s) return JNI_FALSE;
    cef_binary_value_t* _value_ptr = value ? reinterpret_cast<cef_binary_value_t*>(env->GetLongField(value, env->GetFieldID(env->GetObjectClass(value), "nativePtr", "J"))) : nullptr;
    if (_value_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_value_ptr); _b->add_ref(_b);}
    auto _r = s->set_binary(s, _value_ptr);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefValue_00024NativePeer_N_1SetDictionary(JNIEnv* env, jobject obj, jlong self, jobject value) {
    auto* s = reinterpret_cast<cef_value_t*>(self);
    if (!s) return JNI_FALSE;
    cef_dictionary_value_t* _value_ptr = value ? reinterpret_cast<cef_dictionary_value_t*>(env->GetLongField(value, env->GetFieldID(env->GetObjectClass(value), "nativePtr", "J"))) : nullptr;
    if (_value_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_value_ptr); _b->add_ref(_b);}
    auto _r = s->set_dictionary(s, _value_ptr);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefValue_00024NativePeer_N_1SetList(JNIEnv* env, jobject obj, jlong self, jobject value) {
    auto* s = reinterpret_cast<cef_value_t*>(self);
    if (!s) return JNI_FALSE;
    cef_list_value_t* _value_ptr = value ? reinterpret_cast<cef_list_value_t*>(env->GetLongField(value, env->GetFieldID(env->GetObjectClass(value), "nativePtr", "J"))) : nullptr;
    if (_value_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_value_ptr); _b->add_ref(_b);}
    auto _r = s->set_list(s, _value_ptr);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefValue_00024NativePeer_N_1Create(JNIEnv* env, jclass clz) {
    auto _r = cef_value_create();
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefValue$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefValue_00024NativePeer_N_1ParseJson(JNIEnv* env, jclass clz, jstring json_string, jobject options) {
    if (!options) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "options must not be null"); return nullptr;}
    auto _json_string_str = JStringToCefString(env, json_string);
    auto _r = cef_parse_json(_json_string_str, static_cast<cef_json_parser_options_t>(env->GetLongField(options, env->GetFieldID(env->GetObjectClass(options), "value", "J"))));
    if (_json_string_str) cef_string_userfree_free(_json_string_str);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefValue$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefValue_00024NativePeer_N_1ParseJsonBuffer(JNIEnv* env, jclass clz, jobject json, jobject options) {
    if (!json) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "json must not be null"); return nullptr;}
    if (!options) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "options must not be null"); return nullptr;}
    const void* _json_addr = json ? env->GetDirectBufferAddress(json) : nullptr;
    if (json && !_json_addr) {env->ThrowNew(env->FindClass("java/lang/IllegalArgumentException"), "json must be a direct ByteBuffer; use ByteBuffer.allocateDirect(...)"); return nullptr;}
    auto _r = cef_parse_json_buffer(_json_addr, static_cast<size_t>(env->GetDirectBufferCapacity(json)), static_cast<cef_json_parser_options_t>(env->GetLongField(options, env->GetFieldID(env->GetObjectClass(options), "value", "J"))));
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefValue$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefValue_00024NativePeer_N_1ParseJsonandReturnError(JNIEnv* env, jclass clz, jstring json_string, jobject options, jstring error_msg_out) {
    if (!options) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "options must not be null"); return nullptr;}
    auto _json_string_str = JStringToCefString(env, json_string);
    auto _error_msg_out_str = JStringToCefString(env, error_msg_out);
    auto _r = cef_parse_jsonand_return_error(_json_string_str, static_cast<cef_json_parser_options_t>(env->GetLongField(options, env->GetFieldID(env->GetObjectClass(options), "value", "J"))), _error_msg_out_str);
    if (_json_string_str) cef_string_userfree_free(_json_string_str);
    if (_error_msg_out_str) cef_string_userfree_free(_error_msg_out_str);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefValue$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}
