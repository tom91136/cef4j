// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
#include <jni.h>
#include "include/capi/cef_download_handler_capi.h"
#include "jni_util.h"

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefBeforeDownloadCallback), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefBeforeDownloadCallback), cont0)(JNIEnv* env, jobject obj, jlong self, jstring download_path, jint show_dialog) {
    auto* s = reinterpret_cast<cef_before_download_callback_t*>(self);
    if (!s) return;
    auto _download_path_str = download_path ? JStringToCefString(env, download_path) : nullptr;
    s->cont(s, _download_path_str, show_dialog);
    if (_download_path_str) cef_string_userfree_free(_download_path_str);
}
