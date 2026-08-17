// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
#include <jni.h>
#include "include/capi/cef_download_handler_capi.h"
#include "jni_util.h"

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefDownloadItemCallback), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefDownloadItemCallback), cancel0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_download_item_callback_t*>(self);
    if (!s) return;
    s->cancel(s);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefDownloadItemCallback), pause0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_download_item_callback_t*>(self);
    if (!s) return;
    s->pause(s);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefDownloadItemCallback), resume0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_download_item_callback_t*>(self);
    if (!s) return;
    s->resume(s);
}
