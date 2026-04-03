// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
#include <jni.h>
#include "include/capi/cef_context_menu_handler_capi.h"
#include "jni_util.h"

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefRunContextMenuCallback), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefRunContextMenuCallback), cont0)(JNIEnv* env, jobject obj, jlong self, jint command_id, jobject event_flags) {
    auto* s = reinterpret_cast<cef_run_context_menu_callback_t*>(self);
    if (!s) return;
    if (!event_flags) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "eventFlags must not be null"); return;}
    s->cont(s, command_id, static_cast<cef_event_flags_t>(env->GetLongField(event_flags, env->GetFieldID(env->GetObjectClass(event_flags), "value", "J"))));
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefRunContextMenuCallback), cancel0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_run_context_menu_callback_t*>(self);
    if (!s) return;
    s->cancel(s);
}
