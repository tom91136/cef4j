// GENERATED - do not edit.
#include <jni.h>
#include "include/capi/cef_context_menu_handler_capi.h"
#include "jni_util.h"

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefRunQuickMenuCallback_00024NativePeer_N_1Release(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefRunQuickMenuCallback_00024NativePeer_N_1Cont(JNIEnv* env, jobject obj, jlong self, jint command_id, jobject event_flags) {
    auto* s = reinterpret_cast<cef_run_quick_menu_callback_t*>(self);
    if (!s) return;
    if (!event_flags) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "eventFlags must not be null"); return;}
    s->cont(s, command_id, static_cast<cef_event_flags_t>(env->GetLongField(event_flags, env->GetFieldID(env->GetObjectClass(event_flags), "value", "J"))));
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefRunQuickMenuCallback_00024NativePeer_N_1Cancel(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_run_quick_menu_callback_t*>(self);
    if (!s) return;
    s->cancel(s);
}
