// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
#include <jni.h>
#include "include/capi/cef_print_handler_capi.h"
#include "include/capi/cef_print_settings_capi.h"
#include "jni_util.h"

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefPrintDialogCallback), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefPrintDialogCallback), cont0)(JNIEnv* env, jobject obj, jlong self, jobject settings) {
    auto* s = reinterpret_cast<cef_print_dialog_callback_t*>(self);
    if (!s) return;
    cef_print_settings_t* _settings_ptr = settings ? reinterpret_cast<cef_print_settings_t*>(env->GetLongField(settings, env->GetFieldID(env->GetObjectClass(settings), "nativePtr", "J"))) : nullptr;
    if (_settings_ptr) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_settings_ptr); _b->add_ref(_b); }
    s->cont(s, _settings_ptr);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefPrintDialogCallback), cancel0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_print_dialog_callback_t*>(self);
    if (!s) return;
    s->cancel(s);
}
