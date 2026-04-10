// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
#include <jni.h>
#include "include/capi/cef_shared_process_message_builder_capi.h"
#include "include/capi/cef_process_message_capi.h"
#include "jni_util.h"

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefSharedProcessMessageBuilder), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefSharedProcessMessageBuilder), isValid0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_shared_process_message_builder_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_valid(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jlong, CEF4J_PEER(CefSharedProcessMessageBuilder), size0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_shared_process_message_builder_t*>(self);
    if (!s) return 0;
    return static_cast<jlong>(s->size(s));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefSharedProcessMessageBuilder), memory0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_shared_process_message_builder_t*>(self);
    if (!s) return nullptr;
    auto _r = s->memory(s);
    auto _npCls = FindClassCached(env, "net/kurobako/cef4j/gen/NativePointer");
    auto _npCtor = env->GetMethodID(_npCls, "<init>", "(J)V");
    return env->NewObject(_npCls, _npCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefSharedProcessMessageBuilder), build0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_shared_process_message_builder_t*>(self);
    if (!s) return nullptr;
    auto _r = s->build(s);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefProcessMessage$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefSharedProcessMessageBuilder), create0)(JNIEnv* env, jclass clz, jstring name, jlong byte_size) {
    auto _name_str = JStringToCefString(env, name);
    auto _r = cef_shared_process_message_builder_create(_name_str, byte_size);
    if (_name_str) cef_string_userfree_free(_name_str);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefSharedProcessMessageBuilder$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}
