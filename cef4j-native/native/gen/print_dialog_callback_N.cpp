// GENERATED - do not edit.
#include <jni.h>
#include "include/capi/cef_print_handler_capi.h"
#include "include/capi/cef_print_settings_capi.h"
#include "jni_util.h"

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefPrintDialogCallback_00024NativePeer_N_1Release(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefPrintDialogCallback_00024NativePeer_N_1Cont(JNIEnv* env, jobject obj, jlong self, jobject settings) {
    auto* s = reinterpret_cast<cef_print_dialog_callback_t*>(self);
    if (!s) return;
    if (!settings) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "settings must not be null"); return;}
    cef_print_settings_t* _settings_ptr = reinterpret_cast<cef_print_settings_t*>(env->GetLongField(settings, env->GetFieldID(env->GetObjectClass(settings), "nativePtr", "J")));
    if (_settings_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_settings_ptr); _b->add_ref(_b);}
    s->cont(s, _settings_ptr);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefPrintDialogCallback_00024NativePeer_N_1Cancel(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_print_dialog_callback_t*>(self);
    if (!s) return;
    s->cancel(s);
}
