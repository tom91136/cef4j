// GENERATED - do not edit.
#include <jni.h>
#include "include/capi/cef_thread_capi.h"
#include "include/capi/cef_task_capi.h"
#include "jni_util.h"

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefThread_00024NativePeer_N_1Release(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefThread_00024NativePeer_N_1GetTaskRunner(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_thread_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_task_runner(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefTaskRunner$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jlong JNICALL Java_net_kurobako_cef4j_gen_CefThread_00024NativePeer_N_1GetPlatformThreadId(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_thread_t*>(self);
    if (!s) return 0;
    return static_cast<jlong>(s->get_platform_thread_id(s));
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefThread_00024NativePeer_N_1Stop(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_thread_t*>(self);
    if (!s) return;
    s->stop(s);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefThread_00024NativePeer_N_1IsRunning(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_thread_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_running(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefThread_00024NativePeer_N_1Create(JNIEnv* env, jclass clz, jstring display_name, jobject priority, jobject message_loop_type, jint stoppable, jobject com_init_mode) {
    if (!priority) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "priority must not be null"); return nullptr;}
    if (!message_loop_type) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "messageLoopType must not be null"); return nullptr;}
    if (!com_init_mode) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "comInitMode must not be null"); return nullptr;}
    auto _display_name_str = JStringToCefString(env, display_name);
    auto _r = cef_thread_create(_display_name_str, static_cast<cef_thread_priority_t>(env->GetLongField(priority, env->GetFieldID(env->GetObjectClass(priority), "value", "J"))), static_cast<cef_message_loop_type_t>(env->GetLongField(message_loop_type, env->GetFieldID(env->GetObjectClass(message_loop_type), "value", "J"))), stoppable, static_cast<cef_com_init_mode_t>(env->GetLongField(com_init_mode, env->GetFieldID(env->GetObjectClass(com_init_mode), "value", "J"))));
    if (_display_name_str) cef_string_userfree_free(_display_name_str);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefThread$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}
