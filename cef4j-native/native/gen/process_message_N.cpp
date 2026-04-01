// GENERATED - do not edit.
#include <jni.h>
#include "include/capi/cef_process_message_capi.h"
#include "include/capi/cef_shared_memory_region_capi.h"
#include "include/capi/cef_values_capi.h"
#include "jni_util.h"

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefProcessMessage_00024NativePeer_N_1Release(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefProcessMessage_00024NativePeer_N_1IsValid(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_process_message_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_valid(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefProcessMessage_00024NativePeer_N_1IsReadOnly(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_process_message_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_read_only(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefProcessMessage_00024NativePeer_N_1Copy(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_process_message_t*>(self);
    if (!s) return nullptr;
    auto _r = s->copy(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefProcessMessage$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefProcessMessage_00024NativePeer_N_1GetName(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_process_message_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_name(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefProcessMessage_00024NativePeer_N_1GetArgumentList(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_process_message_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_argument_list(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefListValue$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefProcessMessage_00024NativePeer_N_1GetSharedMemoryRegion(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_process_message_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_shared_memory_region(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefSharedMemoryRegion$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefProcessMessage_00024NativePeer_N_1Create(JNIEnv* env, jclass clz, jstring name) {
    if (!name) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "name must not be null"); return nullptr;}
    auto _name_str = JStringToCefString(env, name);
    auto _r = cef_process_message_create(_name_str);
    if (_name_str) cef_string_userfree_free(_name_str);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefProcessMessage$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}
