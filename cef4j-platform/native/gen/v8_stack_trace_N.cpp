// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
#include <jni.h>
#include "include/capi/cef_v8_capi.h"
#include "jni_util.h"

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefV8StackTrace), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefV8StackTrace), isValid0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_stack_trace_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_valid(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jint, CEF4J_PEER(CefV8StackTrace), getFrameCount0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_stack_trace_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->get_frame_count(s));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefV8StackTrace), getFrame0)(JNIEnv* env, jobject obj, jlong self, jint index) {
    auto* s = reinterpret_cast<cef_v8_stack_trace_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_frame(s, index);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefV8StackFrame$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefV8StackTrace), getCurrent0)(JNIEnv* env, jclass clz, jint frame_limit) {
    auto _r = cef_v8_stack_trace_get_current(frame_limit);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefV8StackTrace$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}
