// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
#include <jni.h>
#include "include/capi/views/cef_menu_button_capi.h"
#include "include/capi/cef_menu_model_capi.h"
#include "include/capi/views/cef_menu_button_delegate_capi.h"
#include "jni_util.h"

extern "C" cef_menu_button_delegate_t* Create_JniCefMenuButtonDelegate(JNIEnv* env, jobject handler);
CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefMenuButton), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefMenuButton), showMenu0)(JNIEnv* env, jobject obj, jlong self, jobject menu_model, jobject screen_point, jobject anchor_position) {
    auto* s = reinterpret_cast<cef_menu_button_t*>(self);
    if (!s) return;
    if (!screen_point) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "screenPoint must not be null"); return; }
    if (!anchor_position) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "anchorPosition must not be null"); return; }
    cef_menu_model_t* _menu_model_ptr = menu_model ? reinterpret_cast<cef_menu_model_t*>(env->GetLongField(menu_model, env->GetFieldID(env->GetObjectClass(menu_model), "nativePtr", "J"))) : nullptr;
    if (_menu_model_ptr) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_menu_model_ptr); _b->add_ref(_b); }
    cef_point_t _screen_point_val = {};
    auto _screen_point_c = FindClassCached(env, "net/kurobako/cef4j/gen/CefPoint");
    _screen_point_val.x = static_cast<decltype(_screen_point_val.x)>(env->GetIntField(screen_point, env->GetFieldID(_screen_point_c, "x", "I")));
    _screen_point_val.y = static_cast<decltype(_screen_point_val.y)>(env->GetIntField(screen_point, env->GetFieldID(_screen_point_c, "y", "I")));
    s->show_menu(s, _menu_model_ptr, &_screen_point_val, static_cast<cef_menu_anchor_position_t>(env->GetLongField(anchor_position, env->GetFieldID(env->GetObjectClass(anchor_position), "value", "J"))));
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefMenuButton), triggerMenu0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_menu_button_t*>(self);
    if (!s) return;
    s->trigger_menu(s);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(views_CefMenuButton), create0)(JNIEnv* env, jclass clz, jobject delegate, jstring text) {
    cef_menu_button_delegate_t* _delegate_ptr = delegate ? Create_JniCefMenuButtonDelegate(env, delegate) : nullptr;
    auto _text_str = text ? JStringToCefString(env, text) : nullptr;
    auto _r = cef_menu_button_create(_delegate_ptr, _text_str);
    if (_text_str) cef_string_userfree_free(_text_str);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/views/CefMenuButton$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}
