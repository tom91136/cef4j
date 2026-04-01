// GENERATED - do not edit.
#include <jni.h>
#include "include/capi/cef_media_router_capi.h"
#include "jni_util.h"

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefMediaSource_00024NativePeer_N_1Release(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefMediaSource_00024NativePeer_N_1GetId(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_media_source_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_id(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefMediaSource_00024NativePeer_N_1IsCastSource(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_media_source_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_cast_source(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefMediaSource_00024NativePeer_N_1IsDialSource(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_media_source_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_dial_source(s);
    return static_cast<jboolean>(_r);
}
