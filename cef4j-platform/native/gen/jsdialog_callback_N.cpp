// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
#include <jni.h>
#include "include/capi/cef_jsdialog_handler_capi.h"
#include "jni_util.h"

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefJsDialogCallback), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefJsDialogCallback), cont0)(JNIEnv* env, jobject obj, jlong self, jint success, jstring user_input) {
    auto* s = reinterpret_cast<cef_jsdialog_callback_t*>(self);
    if (!s) return;
    auto _user_input_str = user_input ? JStringToCefString(env, user_input) : nullptr;
    s->cont(s, success, _user_input_str);
    if (_user_input_str) cef_string_userfree_free(_user_input_str);
}
