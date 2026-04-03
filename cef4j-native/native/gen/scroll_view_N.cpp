// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
#include <jni.h>
#include "include/capi/views/cef_scroll_view_capi.h"
#include "include/capi/views/cef_view_capi.h"
#include "include/capi/views/cef_view_delegate_capi.h"
#include "jni_util.h"

extern "C" cef_view_delegate_t* Create_JniCefViewDelegate(JNIEnv *env, jobject handler);
CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefScrollView), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefScrollView), setContentView0)(JNIEnv* env, jobject obj, jlong self, jobject view) {
    auto* s = reinterpret_cast<cef_scroll_view_t*>(self);
    if (!s) return;
    cef_view_t* _view_ptr = view ? reinterpret_cast<cef_view_t*>(env->GetLongField(view, env->GetFieldID(env->GetObjectClass(view), "nativePtr", "J"))) : nullptr;
    if (_view_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_view_ptr); _b->add_ref(_b);}
    s->set_content_view(s, _view_ptr);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefScrollView), getContentView0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_scroll_view_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_content_view(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefView$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefScrollView), getVisibleContentRect0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_scroll_view_t*>(self);
    if (!s) return nullptr;
    cef_rect_t result = s->get_visible_content_rect(s);
    auto cls = env->FindClass("net/kurobako/cef4j/gen/CefRect");
    auto ctor = env->GetMethodID(cls, "<init>", "(IIII)V");
    auto _dsResult = env->NewObject(cls, ctor, static_cast<jint>((&result)->x), static_cast<jint>((&result)->y), static_cast<jint>((&result)->width), static_cast<jint>((&result)->height));
    return _dsResult;
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefScrollView), hasHorizontalScrollbar0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_scroll_view_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->has_horizontal_scrollbar(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jint, CEF4J_PEER(CefScrollView), getHorizontalScrollbarHeight0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_scroll_view_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->get_horizontal_scrollbar_height(s));
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefScrollView), hasVerticalScrollbar0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_scroll_view_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->has_vertical_scrollbar(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jint, CEF4J_PEER(CefScrollView), getVerticalScrollbarWidth0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_scroll_view_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->get_vertical_scrollbar_width(s));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefScrollView), create0)(JNIEnv* env, jclass clz, jobject delegate) {
    cef_view_delegate_t* _delegate_ptr = delegate ? Create_JniCefViewDelegate(env, delegate) : nullptr;
    auto _r = cef_scroll_view_create(_delegate_ptr);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefScrollView$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}
