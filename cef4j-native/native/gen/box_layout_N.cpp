// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
#include <jni.h>
#include "include/capi/views/cef_box_layout_capi.h"
#include "include/capi/views/cef_view_capi.h"
#include "jni_util.h"

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefBoxLayout), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefBoxLayout), setFlexForView0)(JNIEnv* env, jobject obj, jlong self, jobject view, jint flex) {
    auto* s = reinterpret_cast<cef_box_layout_t*>(self);
    if (!s) return;
    cef_view_t* _view_ptr = view ? reinterpret_cast<cef_view_t*>(env->GetLongField(view, env->GetFieldID(env->GetObjectClass(view), "nativePtr", "J"))) : nullptr;
    if (_view_ptr) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_view_ptr); _b->add_ref(_b); }
    s->set_flex_for_view(s, _view_ptr, flex);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefBoxLayout), clearFlexForView0)(JNIEnv* env, jobject obj, jlong self, jobject view) {
    auto* s = reinterpret_cast<cef_box_layout_t*>(self);
    if (!s) return;
    cef_view_t* _view_ptr = view ? reinterpret_cast<cef_view_t*>(env->GetLongField(view, env->GetFieldID(env->GetObjectClass(view), "nativePtr", "J"))) : nullptr;
    if (_view_ptr) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_view_ptr); _b->add_ref(_b); }
    s->clear_flex_for_view(s, _view_ptr);
}
