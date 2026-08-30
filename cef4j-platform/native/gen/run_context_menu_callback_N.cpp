// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
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
    if (!event_flags) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "eventFlags must not be null"); return; }
    s->cont(s, command_id, static_cast<cef_event_flags_t>(env->GetLongField(event_flags, env->GetFieldID(env->GetObjectClass(event_flags), "value", "J"))));
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefRunContextMenuCallback), cancel0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_run_context_menu_callback_t*>(self);
    if (!s) return;
    s->cancel(s);
}
