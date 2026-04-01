// GENERATED - do not edit.
#include <jni.h>
#include "include/capi/cef_unresponsive_process_callback_capi.h"
#include "jni_util.h"

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefUnresponsiveProcessCallback_00024NativePeer_N_1Release(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefUnresponsiveProcessCallback_00024NativePeer_N_1Wait(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_unresponsive_process_callback_t*>(self);
    if (!s) return;
    s->wait(s);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefUnresponsiveProcessCallback_00024NativePeer_N_1Terminate(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_unresponsive_process_callback_t*>(self);
    if (!s) return;
    s->terminate(s);
}
