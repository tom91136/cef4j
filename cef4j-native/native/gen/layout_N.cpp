// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
#include <jni.h>
#include "include/capi/views/cef_layout_capi.h"
#include "include/capi/views/cef_box_layout_capi.h"
#include "include/capi/views/cef_fill_layout_capi.h"
#include "jni_util.h"

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefLayout), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefLayout), asBoxLayout0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_layout_t*>(self);
    if (!s) return nullptr;
    auto _r = s->as_box_layout(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefBoxLayout$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefLayout), asFillLayout0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_layout_t*>(self);
    if (!s) return nullptr;
    auto _r = s->as_fill_layout(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefFillLayout$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefLayout), isValid0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_layout_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_valid(s);
    return static_cast<jboolean>(_r);
}
