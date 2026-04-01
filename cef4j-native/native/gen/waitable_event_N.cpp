// GENERATED - do not edit.
#include <jni.h>
#include "include/capi/cef_waitable_event_capi.h"
#include "jni_util.h"

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefWaitableEvent_00024NativePeer_N_1Release(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefWaitableEvent_00024NativePeer_N_1Reset(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_waitable_event_t*>(self);
    if (!s) return;
    s->reset(s);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefWaitableEvent_00024NativePeer_N_1Signal(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_waitable_event_t*>(self);
    if (!s) return;
    s->signal(s);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefWaitableEvent_00024NativePeer_N_1IsSignaled(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_waitable_event_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_signaled(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefWaitableEvent_00024NativePeer_N_1Wait(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_waitable_event_t*>(self);
    if (!s) return;
    s->wait(s);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefWaitableEvent_00024NativePeer_N_1TimedWait(JNIEnv* env, jobject obj, jlong self, jlong max_ms) {
    auto* s = reinterpret_cast<cef_waitable_event_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->timed_wait(s, max_ms);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefWaitableEvent_00024NativePeer_N_1Create(JNIEnv* env, jclass clz, jint automatic_reset, jint initially_signaled) {
    auto _r = cef_waitable_event_create(automatic_reset, initially_signaled);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefWaitableEvent$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}
