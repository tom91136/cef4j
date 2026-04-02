// GENERATED - do not edit.
#include <jni.h>
#include "include/capi/cef_v8_capi.h"
#include "jni_util.h"

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefV8Handler_00024NativePeer_N_1Release(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefV8Handler_00024NativePeer_N_1Execute(JNIEnv* env, jobject obj, jlong self, jstring name, jobject object, jlong argumentsCount, jobjectArray arguments, jobject retval, jstring exception) {
    auto* s = reinterpret_cast<cef_v8_handler_t*>(self);
    if (!s) return JNI_FALSE;
    auto _name_str = JStringToCefString(env, name);
    cef_v8_value_t* _object_ptr = object ? reinterpret_cast<cef_v8_value_t*>(env->GetLongField(object, env->GetFieldID(env->GetObjectClass(object), "nativePtr", "J"))) : nullptr;
    if (_object_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_object_ptr); _b->add_ref(_b);}
    size_t _arguments_sz = static_cast<size_t>(argumentsCount);
    cef_v8_value_t** _arguments_arr = _arguments_sz > 0 ? new cef_v8_value_t*[_arguments_sz]() : nullptr;
    cef_v8_value_t* _retval_ptr = nullptr;
    if (retval) {
        auto _get = env->GetMethodID(env->GetObjectClass(retval), "get", "()Ljava/lang/Object;");
        auto _cur = env->CallObjectMethod(retval, _get);
        if (_cur) _retval_ptr = reinterpret_cast<cef_v8_value_t*>(env->GetLongField(_cur, env->GetFieldID(env->GetObjectClass(_cur), "nativePtr", "J")));
    }
    auto _exception_str = JStringToCefString(env, exception);
    auto _r = s->execute(s, _name_str, _object_ptr, argumentsCount, _arguments_arr, &_retval_ptr, _exception_str);
    if (_name_str) cef_string_userfree_free(_name_str);
    auto _arguments_cls = env->FindClass("net/kurobako/cef4j/gen/CefV8Value$NativePeer");
    auto _arguments_ctor = env->GetMethodID(_arguments_cls, "<init>", "(J)V");
    for (size_t _i = 0; _i < _arguments_sz; _i++) {
        auto _elem = _arguments_arr[_i] ? env->NewObject(_arguments_cls, _arguments_ctor, reinterpret_cast<jlong>(_arguments_arr[_i])) : nullptr;
        env->SetObjectArrayElement(arguments, _i, _elem);
    }
    delete[] _arguments_arr;
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
