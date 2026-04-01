// GENERATED - do not edit.
#include <jni.h>
#include "include/capi/cef_v8_capi.h"
#include "jni_util.h"

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefV8Interceptor_00024NativePeer_N_1Release(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

extern "C" JNIEXPORT jint JNICALL Java_net_kurobako_cef4j_gen_CefV8Interceptor_00024NativePeer_N_1GetByname(JNIEnv* env, jobject obj, jlong self, jstring name, jobject object, jobject retval, jstring exception) {
    auto* s = reinterpret_cast<cef_v8_interceptor_t*>(self);
    if (!s) return 0;
    if (!name) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "name must not be null"); return 0;}
    if (!object) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "object must not be null"); return 0;}
    if (!retval) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "retval must not be null"); return 0;}
    if (!exception) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "exception must not be null"); return 0;}
    auto _name_str = JStringToCefString(env, name);
    cef_v8_value_t* _object_ptr = reinterpret_cast<cef_v8_value_t*>(env->GetLongField(object, env->GetFieldID(env->GetObjectClass(object), "nativePtr", "J")));
    if (_object_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_object_ptr); _b->add_ref(_b);}
    cef_v8_value_t* _retval_ptr = nullptr;
    if (retval) {
        auto _get = env->GetMethodID(env->GetObjectClass(retval), "get", "()Ljava/lang/Object;");
        auto _cur = env->CallObjectMethod(retval, _get);
        if (_cur) _retval_ptr = reinterpret_cast<cef_v8_value_t*>(env->GetLongField(_cur, env->GetFieldID(env->GetObjectClass(_cur), "nativePtr", "J")));
    }
    auto _exception_str = JStringToCefString(env, exception);
    return static_cast<jint>(s->get_byname(s, _name_str, _object_ptr, &_retval_ptr, _exception_str));
}

extern "C" JNIEXPORT jint JNICALL Java_net_kurobako_cef4j_gen_CefV8Interceptor_00024NativePeer_N_1GetByindex(JNIEnv* env, jobject obj, jlong self, jint index, jobject object, jobject retval, jstring exception) {
    auto* s = reinterpret_cast<cef_v8_interceptor_t*>(self);
    if (!s) return 0;
    if (!object) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "object must not be null"); return 0;}
    if (!retval) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "retval must not be null"); return 0;}
    if (!exception) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "exception must not be null"); return 0;}
    cef_v8_value_t* _object_ptr = reinterpret_cast<cef_v8_value_t*>(env->GetLongField(object, env->GetFieldID(env->GetObjectClass(object), "nativePtr", "J")));
    if (_object_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_object_ptr); _b->add_ref(_b);}
    cef_v8_value_t* _retval_ptr = nullptr;
    if (retval) {
        auto _get = env->GetMethodID(env->GetObjectClass(retval), "get", "()Ljava/lang/Object;");
        auto _cur = env->CallObjectMethod(retval, _get);
        if (_cur) _retval_ptr = reinterpret_cast<cef_v8_value_t*>(env->GetLongField(_cur, env->GetFieldID(env->GetObjectClass(_cur), "nativePtr", "J")));
    }
    auto _exception_str = JStringToCefString(env, exception);
    return static_cast<jint>(s->get_byindex(s, index, _object_ptr, &_retval_ptr, _exception_str));
}

extern "C" JNIEXPORT jint JNICALL Java_net_kurobako_cef4j_gen_CefV8Interceptor_00024NativePeer_N_1SetByname(JNIEnv* env, jobject obj, jlong self, jstring name, jobject object, jobject value, jstring exception) {
    auto* s = reinterpret_cast<cef_v8_interceptor_t*>(self);
    if (!s) return 0;
    if (!name) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "name must not be null"); return 0;}
    if (!object) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "object must not be null"); return 0;}
    if (!value) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "value must not be null"); return 0;}
    if (!exception) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "exception must not be null"); return 0;}
    auto _name_str = JStringToCefString(env, name);
    cef_v8_value_t* _object_ptr = reinterpret_cast<cef_v8_value_t*>(env->GetLongField(object, env->GetFieldID(env->GetObjectClass(object), "nativePtr", "J")));
    if (_object_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_object_ptr); _b->add_ref(_b);}
    cef_v8_value_t* _value_ptr = reinterpret_cast<cef_v8_value_t*>(env->GetLongField(value, env->GetFieldID(env->GetObjectClass(value), "nativePtr", "J")));
    if (_value_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_value_ptr); _b->add_ref(_b);}
    auto _exception_str = JStringToCefString(env, exception);
    return static_cast<jint>(s->set_byname(s, _name_str, _object_ptr, _value_ptr, _exception_str));
}

extern "C" JNIEXPORT jint JNICALL Java_net_kurobako_cef4j_gen_CefV8Interceptor_00024NativePeer_N_1SetByindex(JNIEnv* env, jobject obj, jlong self, jint index, jobject object, jobject value, jstring exception) {
    auto* s = reinterpret_cast<cef_v8_interceptor_t*>(self);
    if (!s) return 0;
    if (!object) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "object must not be null"); return 0;}
    if (!value) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "value must not be null"); return 0;}
    if (!exception) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "exception must not be null"); return 0;}
    cef_v8_value_t* _object_ptr = reinterpret_cast<cef_v8_value_t*>(env->GetLongField(object, env->GetFieldID(env->GetObjectClass(object), "nativePtr", "J")));
    if (_object_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_object_ptr); _b->add_ref(_b);}
    cef_v8_value_t* _value_ptr = reinterpret_cast<cef_v8_value_t*>(env->GetLongField(value, env->GetFieldID(env->GetObjectClass(value), "nativePtr", "J")));
    if (_value_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_value_ptr); _b->add_ref(_b);}
    auto _exception_str = JStringToCefString(env, exception);
    return static_cast<jint>(s->set_byindex(s, index, _object_ptr, _value_ptr, _exception_str));
}
