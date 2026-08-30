// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
#include <jni.h>
#include "include/capi/cef_media_router_capi.h"
#include "include/capi/cef_callback_capi.h"
#include "include/capi/cef_registration_capi.h"
#include "jni_util.h"

extern "C" cef_media_observer_t* Create_JniCefMediaObserver(JNIEnv* env, jobject handler);
extern "C" cef_media_route_create_callback_t* Create_JniCefMediaRouteCreateCallback(JNIEnv* env, jobject handler);
extern "C" cef_completion_callback_t* Create_JniCefCompletionCallback(JNIEnv* env, jobject handler);
CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefMediaRouter), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefMediaRouter), addObserver0)(JNIEnv* env, jobject obj, jlong self, jobject observer) {
    auto* s = reinterpret_cast<cef_media_router_t*>(self);
    if (!s) return nullptr;
    cef_media_observer_t* _observer_ptr = observer ? Create_JniCefMediaObserver(env, observer) : nullptr;
    auto _r = s->add_observer(s, _observer_ptr);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefRegistration$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefMediaRouter), getSource0)(JNIEnv* env, jobject obj, jlong self, jstring urn) {
    auto* s = reinterpret_cast<cef_media_router_t*>(self);
    if (!s) return nullptr;
    auto _urn_str = JStringToCefString(env, urn);
    auto _r = s->get_source(s, _urn_str);
    if (_urn_str) cef_string_userfree_free(_urn_str);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefMediaSource$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefMediaRouter), notifyCurrentSinks0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_media_router_t*>(self);
    if (!s) return;
    s->notify_current_sinks(s);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefMediaRouter), createRoute0)(JNIEnv* env, jobject obj, jlong self, jobject source, jobject sink, jobject callback) {
    auto* s = reinterpret_cast<cef_media_router_t*>(self);
    if (!s) return;
    cef_media_source_t* _source_ptr = source ? reinterpret_cast<cef_media_source_t*>(env->GetLongField(source, env->GetFieldID(env->GetObjectClass(source), "nativePtr", "J"))) : nullptr;
    if (_source_ptr) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_source_ptr); _b->add_ref(_b); }
    cef_media_sink_t* _sink_ptr = sink ? reinterpret_cast<cef_media_sink_t*>(env->GetLongField(sink, env->GetFieldID(env->GetObjectClass(sink), "nativePtr", "J"))) : nullptr;
    if (_sink_ptr) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_sink_ptr); _b->add_ref(_b); }
    cef_media_route_create_callback_t* _callback_ptr = callback ? Create_JniCefMediaRouteCreateCallback(env, callback) : nullptr;
    s->create_route(s, _source_ptr, _sink_ptr, _callback_ptr);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefMediaRouter), notifyCurrentRoutes0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_media_router_t*>(self);
    if (!s) return;
    s->notify_current_routes(s);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefMediaRouter), getGlobal0)(JNIEnv* env, jclass clz, jobject callback) {
    cef_completion_callback_t* _callback_ptr = callback ? Create_JniCefCompletionCallback(env, callback) : nullptr;
    auto _r = cef_media_router_get_global(_callback_ptr);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefMediaRouter$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}
