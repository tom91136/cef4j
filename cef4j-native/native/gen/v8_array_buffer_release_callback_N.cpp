// GENERATED - do not edit.
#include <jni.h>
#include "include/capi/cef_v8_capi.h"
#include "jni_util.h"

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefV8ArrayBufferReleaseCallback_00024NativePeer_N_1Release(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefV8ArrayBufferReleaseCallback_00024NativePeer_N_1ReleaseBuffer(JNIEnv* env, jobject obj, jlong self, jobject buffer) {
    auto* s = reinterpret_cast<cef_v8_array_buffer_release_callback_t*>(self);
    if (!s) return;
    if (!buffer) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "buffer must not be null"); return;}
    s->release_buffer(s, reinterpret_cast<void*>(buffer ? env->GetLongField(buffer, env->GetFieldID(env->GetObjectClass(buffer), "address", "J")) : 0));
}
