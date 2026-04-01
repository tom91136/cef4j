// GENERATED - do not edit.
#include <jni.h>
#include "include/capi/cef_resource_handler_capi.h"
#include "jni_util.h"

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefResourceSkipCallback_00024NativePeer_N_1Release(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefResourceSkipCallback_00024NativePeer_N_1Cont(JNIEnv* env, jobject obj, jlong self, jlong bytes_skipped) {
    auto* s = reinterpret_cast<cef_resource_skip_callback_t*>(self);
    if (!s) return;
    s->cont(s, bytes_skipped);
}
