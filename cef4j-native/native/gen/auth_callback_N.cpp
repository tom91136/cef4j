// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
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
    auto _username_str = JStringToCefString(env, username);
    auto _password_str = JStringToCefString(env, password);
    s->cont(s, _username_str, _password_str);
    if (_username_str) cef_string_userfree_free(_username_str);
    if (_password_str) cef_string_userfree_free(_password_str);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefAuthCallback), cancel0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_auth_callback_t*>(self);
    if (!s) return;
    s->cancel(s);
}
