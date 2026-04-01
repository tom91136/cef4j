// GENERATED - do not edit.
#include <jni.h>
#include "include/capi/cef_download_handler_capi.h"
#include "jni_util.h"

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefDownloadItemCallback_00024NativePeer_N_1Release(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefDownloadItemCallback_00024NativePeer_N_1Cancel(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_download_item_callback_t*>(self);
    if (!s) return;
    s->cancel(s);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefDownloadItemCallback_00024NativePeer_N_1Pause(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_download_item_callback_t*>(self);
    if (!s) return;
    s->pause(s);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefDownloadItemCallback_00024NativePeer_N_1Resume(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_download_item_callback_t*>(self);
    if (!s) return;
    s->resume(s);
}
