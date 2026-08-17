// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
#include <jni.h>
#include "include/capi/cef_media_router_capi.h"
#include "jni_util.h"

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefMediaRoute), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefMediaRoute), getId0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_media_route_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_id(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefMediaRoute), getSource0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_media_route_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_source(s);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefMediaSource$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefMediaRoute), getSink0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_media_route_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_sink(s);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefMediaSink$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefMediaRoute), sendRouteMessage0)(JNIEnv* env, jobject obj, jlong self, jobject message) {
    auto* s = reinterpret_cast<cef_media_route_t*>(self);
    if (!s) return;
    if (!message) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "message must not be null"); return; }
    const void* _message_addr = message ? env->GetDirectBufferAddress(message) : nullptr;
    if (message && !_message_addr) { env->ThrowNew(FindClassCached(env, "java/lang/IllegalArgumentException"), "message must be a direct ByteBuffer; use ByteBuffer.allocateDirect(...)"); return; }
    s->send_route_message(s, _message_addr, static_cast<size_t>(env->GetDirectBufferCapacity(message)));
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefMediaRoute), terminate0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_media_route_t*>(self);
    if (!s) return;
    s->terminate(s);
}
