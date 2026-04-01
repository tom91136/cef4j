// GENERATED - do not edit.
#include <jni.h>
#include "include/capi/cef_shared_process_message_builder_capi.h"
#include "include/capi/cef_process_message_capi.h"
#include "jni_util.h"

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefSharedProcessMessageBuilder_00024NativePeer_N_1Release(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefSharedProcessMessageBuilder_00024NativePeer_N_1IsValid(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_shared_process_message_builder_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_valid(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jlong JNICALL Java_net_kurobako_cef4j_gen_CefSharedProcessMessageBuilder_00024NativePeer_N_1Size(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_shared_process_message_builder_t*>(self);
    if (!s) return 0;
    return static_cast<jlong>(s->size(s));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefSharedProcessMessageBuilder_00024NativePeer_N_1Memory(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_shared_process_message_builder_t*>(self);
    if (!s) return nullptr;
    auto _r = s->memory(s);
    auto _npCls = env->FindClass("net/kurobako/cef4j/gen/NativePointer");
    auto _npCtor = env->GetMethodID(_npCls, "<init>", "(J)V");
    return env->NewObject(_npCls, _npCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefSharedProcessMessageBuilder_00024NativePeer_N_1Build(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_shared_process_message_builder_t*>(self);
    if (!s) return nullptr;
    auto _r = s->build(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefProcessMessage$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefSharedProcessMessageBuilder_00024NativePeer_N_1Create(JNIEnv* env, jclass clz, jstring name, jlong byte_size) {
    if (!name) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "name must not be null"); return nullptr;}
    auto _name_str = JStringToCefString(env, name);
    auto _r = cef_shared_process_message_builder_create(_name_str, byte_size);
    if (_name_str) cef_string_userfree_free(_name_str);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefSharedProcessMessageBuilder$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}
