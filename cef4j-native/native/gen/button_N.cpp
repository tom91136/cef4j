// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
#include <jni.h>
#include "include/capi/views/cef_button_capi.h"
#include "include/capi/views/cef_label_button_capi.h"
#include "jni_util.h"

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefButton), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefButton), asLabelButton0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_button_t*>(self);
    if (!s) return nullptr;
    auto _r = s->as_label_button(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefLabelButton$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefButton), setState0)(JNIEnv* env, jobject obj, jlong self, jobject state) {
    auto* s = reinterpret_cast<cef_button_t*>(self);
    if (!s) return;
    if (!state) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "state must not be null"); return;}
    s->set_state(s, static_cast<cef_button_state_t>(env->GetLongField(state, env->GetFieldID(env->GetObjectClass(state), "value", "J"))));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefButton), getState0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_button_t*>(self);
    if (!s) return 0;
    auto _r = s->get_state(s);
    auto _eCls = env->FindClass("net/kurobako/cef4j/gen/CefButtonState");
    auto _eOf = env->GetStaticMethodID(_eCls, "of", "(J)Lnet/kurobako/cef4j/gen/CefButtonState;");
    return env->CallStaticObjectMethod(_eCls, _eOf, static_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefButton), setInkDropEnabled0)(JNIEnv* env, jobject obj, jlong self, jboolean enabled) {
    auto* s = reinterpret_cast<cef_button_t*>(self);
    if (!s) return;
    s->set_ink_drop_enabled(s, static_cast<bool>(enabled));
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefButton), setTooltipText0)(JNIEnv* env, jobject obj, jlong self, jstring tooltip_text) {
    auto* s = reinterpret_cast<cef_button_t*>(self);
    if (!s) return;
    auto _tooltip_text_str = JStringToCefString(env, tooltip_text);
    s->set_tooltip_text(s, _tooltip_text_str);
    if (_tooltip_text_str) cef_string_userfree_free(_tooltip_text_str);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefButton), setAccessibleName0)(JNIEnv* env, jobject obj, jlong self, jstring name) {
    auto* s = reinterpret_cast<cef_button_t*>(self);
    if (!s) return;
    auto _name_str = JStringToCefString(env, name);
    s->set_accessible_name(s, _name_str);
    if (_name_str) cef_string_userfree_free(_name_str);
}
