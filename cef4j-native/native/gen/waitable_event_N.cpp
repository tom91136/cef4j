// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
#include <jni.h>
#include "include/capi/cef_waitable_event_capi.h"
#include "jni_util.h"

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefWaitableEvent), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefWaitableEvent), reset0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_waitable_event_t*>(self);
    if (!s) return;
    s->reset(s);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefWaitableEvent), signal0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_waitable_event_t*>(self);
    if (!s) return;
    s->signal(s);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefWaitableEvent), isSignaled0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_waitable_event_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_signaled(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefWaitableEvent), cefWait0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_waitable_event_t*>(self);
    if (!s) return;
    s->wait(s);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefWaitableEvent), timedWait0)(JNIEnv* env, jobject obj, jlong self, jlong max_ms) {
    auto* s = reinterpret_cast<cef_waitable_event_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->timed_wait(s, max_ms);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefWaitableEvent), create0)(JNIEnv* env, jclass clz, jint automatic_reset, jint initially_signaled) {
    auto _r = cef_waitable_event_create(automatic_reset, initially_signaled);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefWaitableEvent$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}
