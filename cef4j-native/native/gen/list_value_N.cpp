// GENERATED - do not edit.
#include <jni.h>
#include "include/capi/cef_values_capi.h"
#include "jni_util.h"

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefListValue_00024NativePeer_N_1Release(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefListValue_00024NativePeer_N_1IsValid(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_list_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_valid(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefListValue_00024NativePeer_N_1IsOwned(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_list_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_owned(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefListValue_00024NativePeer_N_1IsReadOnly(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_list_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_read_only(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefListValue_00024NativePeer_N_1IsSame(JNIEnv* env, jobject obj, jlong self, jobject that) {
    auto* s = reinterpret_cast<cef_list_value_t*>(self);
    if (!s) return JNI_FALSE;
    cef_list_value_t* _that_ptr = that ? reinterpret_cast<cef_list_value_t*>(env->GetLongField(that, env->GetFieldID(env->GetObjectClass(that), "nativePtr", "J"))) : nullptr;
    if (_that_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_that_ptr); _b->add_ref(_b);}
    auto _r = s->is_same(s, _that_ptr);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefListValue_00024NativePeer_N_1IsEqual(JNIEnv* env, jobject obj, jlong self, jobject that) {
    auto* s = reinterpret_cast<cef_list_value_t*>(self);
    if (!s) return JNI_FALSE;
    cef_list_value_t* _that_ptr = that ? reinterpret_cast<cef_list_value_t*>(env->GetLongField(that, env->GetFieldID(env->GetObjectClass(that), "nativePtr", "J"))) : nullptr;
    if (_that_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_that_ptr); _b->add_ref(_b);}
    auto _r = s->is_equal(s, _that_ptr);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefListValue_00024NativePeer_N_1Copy(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_list_value_t*>(self);
    if (!s) return nullptr;
    auto _r = s->copy(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefListValue$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefListValue_00024NativePeer_N_1SetSize(JNIEnv* env, jobject obj, jlong self, jlong size) {
    auto* s = reinterpret_cast<cef_list_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->set_size(s, size);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jlong JNICALL Java_net_kurobako_cef4j_gen_CefListValue_00024NativePeer_N_1GetSize(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_list_value_t*>(self);
    if (!s) return 0;
    return static_cast<jlong>(s->get_size(s));
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefListValue_00024NativePeer_N_1Clear(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_list_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->clear(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefListValue_00024NativePeer_N_1Remove(JNIEnv* env, jobject obj, jlong self, jlong index) {
    auto* s = reinterpret_cast<cef_list_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->remove(s, index);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefListValue_00024NativePeer_N_1GetType(JNIEnv* env, jobject obj, jlong self, jlong index) {
    auto* s = reinterpret_cast<cef_list_value_t*>(self);
    if (!s) return 0;
    auto _r = s->get_type(s, index);
    auto _eCls = env->FindClass("net/kurobako/cef4j/gen/CefValueType");
    auto _eOf = env->GetStaticMethodID(_eCls, "of", "(J)Lnet/kurobako/cef4j/gen/CefValueType;");
    return env->CallStaticObjectMethod(_eCls, _eOf, static_cast<jlong>(_r));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefListValue_00024NativePeer_N_1GetValue(JNIEnv* env, jobject obj, jlong self, jlong index) {
    auto* s = reinterpret_cast<cef_list_value_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_value(s, index);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefValue$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefListValue_00024NativePeer_N_1GetBool(JNIEnv* env, jobject obj, jlong self, jlong index) {
    auto* s = reinterpret_cast<cef_list_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->get_bool(s, index);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jint JNICALL Java_net_kurobako_cef4j_gen_CefListValue_00024NativePeer_N_1GetInt(JNIEnv* env, jobject obj, jlong self, jlong index) {
    auto* s = reinterpret_cast<cef_list_value_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->get_int(s, index));
}

extern "C" JNIEXPORT jdouble JNICALL Java_net_kurobako_cef4j_gen_CefListValue_00024NativePeer_N_1GetDouble(JNIEnv* env, jobject obj, jlong self, jlong index) {
    auto* s = reinterpret_cast<cef_list_value_t*>(self);
    if (!s) return 0;
    return static_cast<jdouble>(s->get_double(s, index));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefListValue_00024NativePeer_N_1GetDictionary(JNIEnv* env, jobject obj, jlong self, jlong index) {
    auto* s = reinterpret_cast<cef_list_value_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_dictionary(s, index);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefDictionaryValue$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefListValue_00024NativePeer_N_1SetValue(JNIEnv* env, jobject obj, jlong self, jlong index, jobject value) {
    auto* s = reinterpret_cast<cef_list_value_t*>(self);
    if (!s) return JNI_FALSE;
    cef_value_t* _value_ptr = value ? reinterpret_cast<cef_value_t*>(env->GetLongField(value, env->GetFieldID(env->GetObjectClass(value), "nativePtr", "J"))) : nullptr;
    if (_value_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_value_ptr); _b->add_ref(_b);}
    auto _r = s->set_value(s, index, _value_ptr);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefListValue_00024NativePeer_N_1SetNull(JNIEnv* env, jobject obj, jlong self, jlong index) {
    auto* s = reinterpret_cast<cef_list_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->set_null(s, index);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefListValue_00024NativePeer_N_1SetBool(JNIEnv* env, jobject obj, jlong self, jlong index, jboolean value) {
    auto* s = reinterpret_cast<cef_list_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->set_bool(s, index, static_cast<bool>(value));
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefListValue_00024NativePeer_N_1SetInt(JNIEnv* env, jobject obj, jlong self, jlong index, jint value) {
    auto* s = reinterpret_cast<cef_list_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->set_int(s, index, value);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefListValue_00024NativePeer_N_1SetDouble(JNIEnv* env, jobject obj, jlong self, jlong index, jdouble value) {
    auto* s = reinterpret_cast<cef_list_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->set_double(s, index, value);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefListValue_00024NativePeer_N_1SetString(JNIEnv* env, jobject obj, jlong self, jlong index, jstring value) {
    auto* s = reinterpret_cast<cef_list_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _value_str = value ? JStringToCefString(env, value) : nullptr;
    auto _r = s->set_string(s, index, _value_str);
    if (_value_str) cef_string_userfree_free(_value_str);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefListValue_00024NativePeer_N_1SetBinary(JNIEnv* env, jobject obj, jlong self, jlong index, jobject value) {
    auto* s = reinterpret_cast<cef_list_value_t*>(self);
    if (!s) return JNI_FALSE;
    cef_binary_value_t* _value_ptr = value ? reinterpret_cast<cef_binary_value_t*>(env->GetLongField(value, env->GetFieldID(env->GetObjectClass(value), "nativePtr", "J"))) : nullptr;
    if (_value_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_value_ptr); _b->add_ref(_b);}
    auto _r = s->set_binary(s, index, _value_ptr);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefListValue_00024NativePeer_N_1SetDictionary(JNIEnv* env, jobject obj, jlong self, jlong index, jobject value) {
    auto* s = reinterpret_cast<cef_list_value_t*>(self);
    if (!s) return JNI_FALSE;
    cef_dictionary_value_t* _value_ptr = value ? reinterpret_cast<cef_dictionary_value_t*>(env->GetLongField(value, env->GetFieldID(env->GetObjectClass(value), "nativePtr", "J"))) : nullptr;
    if (_value_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_value_ptr); _b->add_ref(_b);}
    auto _r = s->set_dictionary(s, index, _value_ptr);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefListValue_00024NativePeer_N_1SetList(JNIEnv* env, jobject obj, jlong self, jlong index, jobject value) {
    auto* s = reinterpret_cast<cef_list_value_t*>(self);
    if (!s) return JNI_FALSE;
    cef_list_value_t* _value_ptr = value ? reinterpret_cast<cef_list_value_t*>(env->GetLongField(value, env->GetFieldID(env->GetObjectClass(value), "nativePtr", "J"))) : nullptr;
    if (_value_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_value_ptr); _b->add_ref(_b);}
    auto _r = s->set_list(s, index, _value_ptr);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefListValue_00024NativePeer_N_1Create(JNIEnv* env, jclass clz) {
    auto _r = cef_list_value_create();
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefListValue$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}
