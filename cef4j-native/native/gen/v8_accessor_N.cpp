// GENERATED - do not edit.
#include <jni.h>
#include "include/capi/cef_v8_capi.h"
#include "jni_util.h"

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefV8Accessor_00024NativePeer_N_1Release(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefV8Accessor_00024NativePeer_N_1Get(JNIEnv* env, jobject obj, jlong self, jstring name, jobject object, jobject retval, jstring exception) {
    auto* s = reinterpret_cast<cef_v8_accessor_t*>(self);
    if (!s) return JNI_FALSE;
    if (!name) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "name must not be null"); return JNI_FALSE;}
    if (!object) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "object must not be null"); return JNI_FALSE;}
    if (!retval) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "retval must not be null"); return JNI_FALSE;}
    if (!exception) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "exception must not be null"); return JNI_FALSE;}
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
    auto _r = s->get(s, _name_str, _object_ptr, &_retval_ptr, _exception_str);
    if (_name_str) cef_string_userfree_free(_name_str);
    if (retval && _retval_ptr) {
        auto _peerCls = env->FindClass("net/kurobako/cef4j/gen/CefV8Value$NativePeer");
        auto _peerCtor = env->GetMethodID(_peerCls, "<init>", "(J)V");
        {   auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_retval_ptr); _b->add_ref(_b);}
        auto _newPeer = env->NewObject(_peerCls, _peerCtor, reinterpret_cast<jlong>(_retval_ptr));
        auto _set = env->GetMethodID(env->GetObjectClass(retval), "set", "(Ljava/lang/Object;)V");
        env->CallVoidMethod(retval, _set, _newPeer);
    }
    if (_exception_str) cef_string_userfree_free(_exception_str);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefV8Accessor_00024NativePeer_N_1Set(JNIEnv* env, jobject obj, jlong self, jstring name, jobject object, jobject value, jstring exception) {
    auto* s = reinterpret_cast<cef_v8_accessor_t*>(self);
    if (!s) return JNI_FALSE;
    if (!name) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "name must not be null"); return JNI_FALSE;}
    if (!object) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "object must not be null"); return JNI_FALSE;}
    if (!value) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "value must not be null"); return JNI_FALSE;}
    if (!exception) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "exception must not be null"); return JNI_FALSE;}
    auto _name_str = JStringToCefString(env, name);
    cef_v8_value_t* _object_ptr = reinterpret_cast<cef_v8_value_t*>(env->GetLongField(object, env->GetFieldID(env->GetObjectClass(object), "nativePtr", "J")));
    if (_object_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_object_ptr); _b->add_ref(_b);}
    cef_v8_value_t* _value_ptr = reinterpret_cast<cef_v8_value_t*>(env->GetLongField(value, env->GetFieldID(env->GetObjectClass(value), "nativePtr", "J")));
    if (_value_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_value_ptr); _b->add_ref(_b);}
    auto _exception_str = JStringToCefString(env, exception);
    auto _r = s->set(s, _name_str, _object_ptr, _value_ptr, _exception_str);
    if (_name_str) cef_string_userfree_free(_name_str);
    if (_exception_str) cef_string_userfree_free(_exception_str);
    return static_cast<jboolean>(_r);
}
