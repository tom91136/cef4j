// GENERATED - do not edit.
#include <jni.h>
#include "include/capi/cef_media_router_capi.h"
#include "jni_util.h"

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefMediaRoute_00024NativePeer_N_1Release(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefMediaRoute_00024NativePeer_N_1GetId(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_media_route_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_id(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefMediaRoute_00024NativePeer_N_1GetSource(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_media_route_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_source(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefMediaSource$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefMediaRoute_00024NativePeer_N_1GetSink(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_media_route_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_sink(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefMediaSink$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefMediaRoute_00024NativePeer_N_1SendRouteMessage(JNIEnv* env, jobject obj, jlong self, jobject message) {
    auto* s = reinterpret_cast<cef_media_route_t*>(self);
    if (!s) return;
    if (!message) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "message must not be null"); return;}
    const void* _message_addr = message ? env->GetDirectBufferAddress(message) : nullptr;
    s->send_route_message(s, _message_addr, static_cast<size_t>(env->GetDirectBufferCapacity(message)));
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefMediaRoute_00024NativePeer_N_1Terminate(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_media_route_t*>(self);
    if (!s) return;
    s->terminate(s);
}
