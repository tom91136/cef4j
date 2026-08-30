// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
#include <jni.h>
#include "include/capi/views/cef_layout_capi.h"
#include "include/capi/views/cef_box_layout_capi.h"
#include "include/capi/views/cef_fill_layout_capi.h"
#include "jni_util.h"

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefLayout), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(views_CefLayout), asBoxLayout0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_layout_t*>(self);
    if (!s) return nullptr;
    auto _r = s->as_box_layout(s);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/views/CefBoxLayout$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(views_CefLayout), asFillLayout0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_layout_t*>(self);
    if (!s) return nullptr;
    auto _r = s->as_fill_layout(s);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/views/CefFillLayout$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(views_CefLayout), isValid0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_layout_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_valid(s);
    return static_cast<jboolean>(_r);
}
