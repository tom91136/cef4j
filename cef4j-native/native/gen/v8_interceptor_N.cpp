// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
#include <jni.h>
#include "include/capi/cef_v8_capi.h"
#include "jni_util.h"

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefV8Interceptor), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

CEF4J_JNI_EXPORT(jint, CEF4J_PEER(CefV8Interceptor), getByname0)(JNIEnv* env, jobject obj, jlong self, jstring name, jobject object, jobject retval, jstring exception) {
    auto* s = reinterpret_cast<cef_v8_interceptor_t*>(self);
    if (!s) return 0;
    auto _name_str = JStringToCefString(env, name);
    cef_v8_value_t* _object_ptr = object ? reinterpret_cast<cef_v8_value_t*>(env->GetLongField(object, env->GetFieldID(env->GetObjectClass(object), "nativePtr", "J"))) : nullptr;
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

CEF4J_JNI_EXPORT(jint, CEF4J_PEER(CefV8Interceptor), getByindex0)(JNIEnv* env, jobject obj, jlong self, jint index, jobject object, jobject retval, jstring exception) {
    auto* s = reinterpret_cast<cef_v8_interceptor_t*>(self);
    if (!s) return 0;
    cef_v8_value_t* _object_ptr = object ? reinterpret_cast<cef_v8_value_t*>(env->GetLongField(object, env->GetFieldID(env->GetObjectClass(object), "nativePtr", "J"))) : nullptr;
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

CEF4J_JNI_EXPORT(jint, CEF4J_PEER(CefV8Interceptor), setByname0)(JNIEnv* env, jobject obj, jlong self, jstring name, jobject object, jobject value, jstring exception) {
    auto* s = reinterpret_cast<cef_v8_interceptor_t*>(self);
    if (!s) return 0;
    auto _name_str = JStringToCefString(env, name);
    cef_v8_value_t* _object_ptr = object ? reinterpret_cast<cef_v8_value_t*>(env->GetLongField(object, env->GetFieldID(env->GetObjectClass(object), "nativePtr", "J"))) : nullptr;
    if (_object_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_object_ptr); _b->add_ref(_b);}
    cef_v8_value_t* _value_ptr = value ? reinterpret_cast<cef_v8_value_t*>(env->GetLongField(value, env->GetFieldID(env->GetObjectClass(value), "nativePtr", "J"))) : nullptr;
    if (_value_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_value_ptr); _b->add_ref(_b);}
    auto _exception_str = JStringToCefString(env, exception);
    return static_cast<jint>(s->set_byname(s, _name_str, _object_ptr, _value_ptr, _exception_str));
}

CEF4J_JNI_EXPORT(jint, CEF4J_PEER(CefV8Interceptor), setByindex0)(JNIEnv* env, jobject obj, jlong self, jint index, jobject object, jobject value, jstring exception) {
    auto* s = reinterpret_cast<cef_v8_interceptor_t*>(self);
    if (!s) return 0;
    cef_v8_value_t* _object_ptr = object ? reinterpret_cast<cef_v8_value_t*>(env->GetLongField(object, env->GetFieldID(env->GetObjectClass(object), "nativePtr", "J"))) : nullptr;
    if (_object_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_object_ptr); _b->add_ref(_b);}
    cef_v8_value_t* _value_ptr = value ? reinterpret_cast<cef_v8_value_t*>(env->GetLongField(value, env->GetFieldID(env->GetObjectClass(value), "nativePtr", "J"))) : nullptr;
    if (_value_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_value_ptr); _b->add_ref(_b);}
    auto _exception_str = JStringToCefString(env, exception);
    return static_cast<jint>(s->set_byindex(s, index, _object_ptr, _value_ptr, _exception_str));
}
