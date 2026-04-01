// GENERATED - do not edit.
#include <jni.h>
#include "include/capi/cef_media_router_capi.h"
#include "include/capi/cef_callback_capi.h"
#include "include/capi/cef_registration_capi.h"
#include "jni_util.h"

extern "C" cef_media_observer_t* Create_JniCefMediaObserver(JNIEnv *env, jobject handler);
extern "C" cef_media_route_create_callback_t* Create_JniCefMediaRouteCreateCallback(JNIEnv *env, jobject handler);
extern "C" cef_completion_callback_t* Create_JniCefCompletionCallback(JNIEnv *env, jobject handler);
extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefMediaRouter_00024NativePeer_N_1Release(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefMediaRouter_00024NativePeer_N_1AddObserver(JNIEnv* env, jobject obj, jlong self, jobject observer) {
    auto* s = reinterpret_cast<cef_media_router_t*>(self);
    if (!s) return nullptr;
    if (!observer) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "observer must not be null"); return nullptr;}
    cef_media_observer_t* _observer_ptr = Create_JniCefMediaObserver(env, observer);
    auto _r = s->add_observer(s, _observer_ptr);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefRegistration$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefMediaRouter_00024NativePeer_N_1GetSource(JNIEnv* env, jobject obj, jlong self, jstring urn) {
    auto* s = reinterpret_cast<cef_media_router_t*>(self);
    if (!s) return nullptr;
    if (!urn) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "urn must not be null"); return nullptr;}
    auto _urn_str = JStringToCefString(env, urn);
    auto _r = s->get_source(s, _urn_str);
    if (_urn_str) cef_string_userfree_free(_urn_str);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefMediaSource$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefMediaRouter_00024NativePeer_N_1NotifyCurrentSinks(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_media_router_t*>(self);
    if (!s) return;
    s->notify_current_sinks(s);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefMediaRouter_00024NativePeer_N_1CreateRoute(JNIEnv* env, jobject obj, jlong self, jobject source, jobject sink, jobject callback) {
    auto* s = reinterpret_cast<cef_media_router_t*>(self);
    if (!s) return;
    if (!source) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "source must not be null"); return;}
    if (!sink) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "sink must not be null"); return;}
    if (!callback) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "callback must not be null"); return;}
    cef_media_source_t* _source_ptr = reinterpret_cast<cef_media_source_t*>(env->GetLongField(source, env->GetFieldID(env->GetObjectClass(source), "nativePtr", "J")));
    if (_source_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_source_ptr); _b->add_ref(_b);}
    cef_media_sink_t* _sink_ptr = reinterpret_cast<cef_media_sink_t*>(env->GetLongField(sink, env->GetFieldID(env->GetObjectClass(sink), "nativePtr", "J")));
    if (_sink_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_sink_ptr); _b->add_ref(_b);}
    cef_media_route_create_callback_t* _callback_ptr = Create_JniCefMediaRouteCreateCallback(env, callback);
    s->create_route(s, _source_ptr, _sink_ptr, _callback_ptr);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefMediaRouter_00024NativePeer_N_1NotifyCurrentRoutes(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_media_router_t*>(self);
    if (!s) return;
    s->notify_current_routes(s);
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefMediaRouter_00024NativePeer_N_1GetGlobal(JNIEnv* env, jclass clz, jobject callback) {
    if (!callback) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "callback must not be null"); return nullptr;}
    cef_completion_callback_t* _callback_ptr = Create_JniCefCompletionCallback(env, callback);
    auto _r = cef_media_router_get_global(_callback_ptr);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefMediaRouter$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}
