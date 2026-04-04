// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
#include <jni.h>
#include "include/capi/views/cef_panel_capi.h"
#include "include/capi/views/cef_box_layout_capi.h"
#include "include/capi/views/cef_fill_layout_capi.h"
#include "include/capi/views/cef_layout_capi.h"
#include "include/capi/views/cef_panel_delegate_capi.h"
#include "include/capi/views/cef_view_capi.h"
#include "include/capi/views/cef_window_capi.h"
#include "jni_util.h"

extern "C" cef_panel_delegate_t* Create_JniCefPanelDelegate(JNIEnv *env, jobject handler);
CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefPanel), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(views_CefPanel), asWindow0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_panel_t*>(self);
    if (!s) return nullptr;
    auto _r = s->as_window(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/views/CefWindow$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(views_CefPanel), setToFillLayout0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_panel_t*>(self);
    if (!s) return nullptr;
    auto _r = s->set_to_fill_layout(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/views/CefFillLayout$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(views_CefPanel), setToBoxLayout0)(JNIEnv* env, jobject obj, jlong self, jobject settings) {
    auto* s = reinterpret_cast<cef_panel_t*>(self);
    if (!s) return nullptr;
    auto _r = s->set_to_box_layout(s, reinterpret_cast<const cef_box_layout_settings_t*>(settings ? env->GetLongField(settings, env->GetFieldID(env->GetObjectClass(settings), "address", "J")) : 0));
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/views/CefBoxLayout$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(views_CefPanel), getLayout0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_panel_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_layout(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/views/CefLayout$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefPanel), layout0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_panel_t*>(self);
    if (!s) return;
    s->layout(s);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefPanel), addChildView0)(JNIEnv* env, jobject obj, jlong self, jobject view) {
    auto* s = reinterpret_cast<cef_panel_t*>(self);
    if (!s) return;
    cef_view_t* _view_ptr = view ? reinterpret_cast<cef_view_t*>(env->GetLongField(view, env->GetFieldID(env->GetObjectClass(view), "nativePtr", "J"))) : nullptr;
    if (_view_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_view_ptr); _b->add_ref(_b);}
    s->add_child_view(s, _view_ptr);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefPanel), addChildViewAt0)(JNIEnv* env, jobject obj, jlong self, jobject view, jint index) {
    auto* s = reinterpret_cast<cef_panel_t*>(self);
    if (!s) return;
    cef_view_t* _view_ptr = view ? reinterpret_cast<cef_view_t*>(env->GetLongField(view, env->GetFieldID(env->GetObjectClass(view), "nativePtr", "J"))) : nullptr;
    if (_view_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_view_ptr); _b->add_ref(_b);}
    s->add_child_view_at(s, _view_ptr, index);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefPanel), reorderChildView0)(JNIEnv* env, jobject obj, jlong self, jobject view, jint index) {
    auto* s = reinterpret_cast<cef_panel_t*>(self);
    if (!s) return;
    cef_view_t* _view_ptr = view ? reinterpret_cast<cef_view_t*>(env->GetLongField(view, env->GetFieldID(env->GetObjectClass(view), "nativePtr", "J"))) : nullptr;
    if (_view_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_view_ptr); _b->add_ref(_b);}
    s->reorder_child_view(s, _view_ptr, index);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefPanel), removeChildView0)(JNIEnv* env, jobject obj, jlong self, jobject view) {
    auto* s = reinterpret_cast<cef_panel_t*>(self);
    if (!s) return;
    cef_view_t* _view_ptr = view ? reinterpret_cast<cef_view_t*>(env->GetLongField(view, env->GetFieldID(env->GetObjectClass(view), "nativePtr", "J"))) : nullptr;
    if (_view_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_view_ptr); _b->add_ref(_b);}
    s->remove_child_view(s, _view_ptr);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefPanel), removeAllChildViews0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_panel_t*>(self);
    if (!s) return;
    s->remove_all_child_views(s);
}

CEF4J_JNI_EXPORT(jlong, CEF4J_PEER(views_CefPanel), getChildViewCount0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_panel_t*>(self);
    if (!s) return 0;
    return static_cast<jlong>(s->get_child_view_count(s));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(views_CefPanel), create0)(JNIEnv* env, jclass clz, jobject delegate) {
    cef_panel_delegate_t* _delegate_ptr = delegate ? Create_JniCefPanelDelegate(env, delegate) : nullptr;
    auto _r = cef_panel_create(_delegate_ptr);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/views/CefPanel$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}
