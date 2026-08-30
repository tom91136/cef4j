// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
#include <jni.h>
#include "include/capi/cef_auth_callback_capi.h"
#include "jni_util.h"

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefAuthCallback), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefAuthCallback), cont0)(JNIEnv* env, jobject obj, jlong self, jstring username, jstring password) {
    auto* s = reinterpret_cast<cef_auth_callback_t*>(self);
    if (!s) return;
    auto _username_str = username ? JStringToCefString(env, username) : nullptr;
    auto _password_str = password ? JStringToCefString(env, password) : nullptr;
    s->cont(s, _username_str, _password_str);
    if (_username_str) cef_string_userfree_free(_username_str);
    if (_password_str) cef_string_userfree_free(_password_str);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefAuthCallback), cancel0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_auth_callback_t*>(self);
    if (!s) return;
    s->cancel(s);
}
