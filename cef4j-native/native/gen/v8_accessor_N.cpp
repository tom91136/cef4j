// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
#include <jni.h>
#include "include/capi/cef_v8_capi.h"
#include "jni_util.h"

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefV8Accessor), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefV8Accessor), get0)(JNIEnv* env, jobject obj, jlong self, jstring name, jobject object, jobject retval, jstring exception) {
    auto* s = reinterpret_cast<cef_v8_accessor_t*>(self);
    if (!s) return JNI_FALSE;
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

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefV8Accessor), set0)(JNIEnv* env, jobject obj, jlong self, jstring name, jobject object, jobject value, jstring exception) {
    auto* s = reinterpret_cast<cef_v8_accessor_t*>(self);
    if (!s) return JNI_FALSE;
    auto _name_str = JStringToCefString(env, name);
    cef_v8_value_t* _object_ptr = object ? reinterpret_cast<cef_v8_value_t*>(env->GetLongField(object, env->GetFieldID(env->GetObjectClass(object), "nativePtr", "J"))) : nullptr;
    if (_object_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_object_ptr); _b->add_ref(_b);}
    cef_v8_value_t* _value_ptr = value ? reinterpret_cast<cef_v8_value_t*>(env->GetLongField(value, env->GetFieldID(env->GetObjectClass(value), "nativePtr", "J"))) : nullptr;
    if (_value_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_value_ptr); _b->add_ref(_b);}
    auto _exception_str = JStringToCefString(env, exception);
    auto _r = s->set(s, _name_str, _object_ptr, _value_ptr, _exception_str);
    if (_name_str) cef_string_userfree_free(_name_str);
    if (_exception_str) cef_string_userfree_free(_exception_str);
    return static_cast<jboolean>(_r);
}
