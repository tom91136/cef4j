// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
#include <jni.h>
#include "include/capi/views/cef_button_capi.h"
#include "include/capi/views/cef_label_button_capi.h"
#include "jni_util.h"

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefButton), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(views_CefButton), asLabelButton0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_button_t*>(self);
    if (!s) return nullptr;
    auto _r = s->as_label_button(s);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/views/CefLabelButton$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefButton), setState0)(JNIEnv* env, jobject obj, jlong self, jobject state) {
    auto* s = reinterpret_cast<cef_button_t*>(self);
    if (!s) return;
    if (!state) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "state must not be null"); return; }
    s->set_state(s, static_cast<cef_button_state_t>(env->GetLongField(state, env->GetFieldID(env->GetObjectClass(state), "value", "J"))));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(views_CefButton), getState0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_button_t*>(self);
    if (!s) return 0;
    auto _r = s->get_state(s);
    auto _eCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefButtonState");
    auto _eOf = env->GetStaticMethodID(_eCls, "of", "(J)Lnet/kurobako/cef4j/gen/CefButtonState;");
    return env->CallStaticObjectMethod(_eCls, _eOf, static_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefButton), setInkDropEnabled0)(JNIEnv* env, jobject obj, jlong self, jboolean enabled) {
    auto* s = reinterpret_cast<cef_button_t*>(self);
    if (!s) return;
    s->set_ink_drop_enabled(s, static_cast<bool>(enabled));
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefButton), setTooltipText0)(JNIEnv* env, jobject obj, jlong self, jstring tooltip_text) {
    auto* s = reinterpret_cast<cef_button_t*>(self);
    if (!s) return;
    auto _tooltip_text_str = JStringToCefString(env, tooltip_text);
    s->set_tooltip_text(s, _tooltip_text_str);
    if (_tooltip_text_str) cef_string_userfree_free(_tooltip_text_str);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefButton), setAccessibleName0)(JNIEnv* env, jobject obj, jlong self, jstring name) {
    auto* s = reinterpret_cast<cef_button_t*>(self);
    if (!s) return;
    auto _name_str = JStringToCefString(env, name);
    s->set_accessible_name(s, _name_str);
    if (_name_str) cef_string_userfree_free(_name_str);
}
