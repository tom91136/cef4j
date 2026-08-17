// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
#include <jni.h>
#include "include/capi/cef_permission_handler_capi.h"
#include "jni_util.h"

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefPermissionPromptCallback), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefPermissionPromptCallback), cont0)(JNIEnv* env, jobject obj, jlong self, jobject result) {
    auto* s = reinterpret_cast<cef_permission_prompt_callback_t*>(self);
    if (!s) return;
    if (!result) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "result must not be null"); return; }
    s->cont(s, static_cast<cef_permission_request_result_t>(env->GetLongField(result, env->GetFieldID(env->GetObjectClass(result), "value", "J"))));
}
