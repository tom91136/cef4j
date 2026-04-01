// GENERATED - do not edit.
#include <jni.h>
#include "include/capi/cef_download_handler_capi.h"
#include "jni_util.h"

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefBeforeDownloadCallback_00024NativePeer_N_1Release(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefBeforeDownloadCallback_00024NativePeer_N_1Cont(JNIEnv* env, jobject obj, jlong self, jstring download_path, jint show_dialog) {
    auto* s = reinterpret_cast<cef_before_download_callback_t*>(self);
    if (!s) return;
    auto _download_path_str = download_path ? JStringToCefString(env, download_path) : nullptr;
    s->cont(s, _download_path_str, show_dialog);
    if (_download_path_str) cef_string_userfree_free(_download_path_str);
}
