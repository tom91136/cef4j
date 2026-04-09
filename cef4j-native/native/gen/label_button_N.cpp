// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
#include <jni.h>
#include "include/capi/views/cef_label_button_capi.h"
#include "include/capi/cef_image_capi.h"
#include "include/capi/views/cef_button_delegate_capi.h"
#include "include/capi/views/cef_menu_button_capi.h"
#include "jni_util.h"

extern "C" cef_button_delegate_t* Create_JniCefButtonDelegate(JNIEnv* env, jobject handler);
CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefLabelButton), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(views_CefLabelButton), asMenuButton0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_label_button_t*>(self);
    if (!s) return nullptr;
    auto _r = s->as_menu_button(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/views/CefMenuButton$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefLabelButton), setText0)(JNIEnv* env, jobject obj, jlong self, jstring text) {
    auto* s = reinterpret_cast<cef_label_button_t*>(self);
    if (!s) return;
    auto _text_str = JStringToCefString(env, text);
    s->set_text(s, _text_str);
    if (_text_str) cef_string_userfree_free(_text_str);
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(views_CefLabelButton), getText0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_label_button_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_text(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefLabelButton), setImage0)(JNIEnv* env, jobject obj, jlong self, jobject button_state, jobject image) {
    auto* s = reinterpret_cast<cef_label_button_t*>(self);
    if (!s) return;
    if (!button_state) { env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "buttonState must not be null"); return; }
    cef_image_t* _image_ptr = image ? reinterpret_cast<cef_image_t*>(env->GetLongField(image, env->GetFieldID(env->GetObjectClass(image), "nativePtr", "J"))) : nullptr;
    if (_image_ptr) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_image_ptr); _b->add_ref(_b); }
    s->set_image(s, static_cast<cef_button_state_t>(env->GetLongField(button_state, env->GetFieldID(env->GetObjectClass(button_state), "value", "J"))), _image_ptr);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(views_CefLabelButton), getImage0)(JNIEnv* env, jobject obj, jlong self, jobject button_state) {
    auto* s = reinterpret_cast<cef_label_button_t*>(self);
    if (!s) return nullptr;
    if (!button_state) { env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "buttonState must not be null"); return nullptr; }
    auto _r = s->get_image(s, static_cast<cef_button_state_t>(env->GetLongField(button_state, env->GetFieldID(env->GetObjectClass(button_state), "value", "J"))));
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefImage$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefLabelButton), setTextColor0)(JNIEnv* env, jobject obj, jlong self, jobject for_state, jint color) {
    auto* s = reinterpret_cast<cef_label_button_t*>(self);
    if (!s) return;
    if (!for_state) { env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "forState must not be null"); return; }
    s->set_text_color(s, static_cast<cef_button_state_t>(env->GetLongField(for_state, env->GetFieldID(env->GetObjectClass(for_state), "value", "J"))), color);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefLabelButton), setEnabledTextColors0)(JNIEnv* env, jobject obj, jlong self, jint color) {
    auto* s = reinterpret_cast<cef_label_button_t*>(self);
    if (!s) return;
    s->set_enabled_text_colors(s, color);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefLabelButton), setFontList0)(JNIEnv* env, jobject obj, jlong self, jstring font_list) {
    auto* s = reinterpret_cast<cef_label_button_t*>(self);
    if (!s) return;
    auto _font_list_str = JStringToCefString(env, font_list);
    s->set_font_list(s, _font_list_str);
    if (_font_list_str) cef_string_userfree_free(_font_list_str);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefLabelButton), setHorizontalAlignment0)(JNIEnv* env, jobject obj, jlong self, jobject alignment) {
    auto* s = reinterpret_cast<cef_label_button_t*>(self);
    if (!s) return;
    if (!alignment) { env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "alignment must not be null"); return; }
    s->set_horizontal_alignment(s, static_cast<cef_horizontal_alignment_t>(env->GetLongField(alignment, env->GetFieldID(env->GetObjectClass(alignment), "value", "J"))));
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefLabelButton), setMinimumSize0)(JNIEnv* env, jobject obj, jlong self, jobject size) {
    auto* s = reinterpret_cast<cef_label_button_t*>(self);
    if (!s) return;
    if (!size) { env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "size must not be null"); return; }
    cef_size_t _size_val = {};
    if (size) {
        auto _c = env->FindClass("net/kurobako/cef4j/gen/CefSize");
        _size_val.width = static_cast<decltype(_size_val.width)>(env->GetIntField(size, env->GetFieldID(_c, "width", "I")));
        _size_val.height = static_cast<decltype(_size_val.height)>(env->GetIntField(size, env->GetFieldID(_c, "height", "I")));
    }
    s->set_minimum_size(s, &_size_val);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefLabelButton), setMaximumSize0)(JNIEnv* env, jobject obj, jlong self, jobject size) {
    auto* s = reinterpret_cast<cef_label_button_t*>(self);
    if (!s) return;
    if (!size) { env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "size must not be null"); return; }
    cef_size_t _size_val = {};
    if (size) {
        auto _c = env->FindClass("net/kurobako/cef4j/gen/CefSize");
        _size_val.width = static_cast<decltype(_size_val.width)>(env->GetIntField(size, env->GetFieldID(_c, "width", "I")));
        _size_val.height = static_cast<decltype(_size_val.height)>(env->GetIntField(size, env->GetFieldID(_c, "height", "I")));
    }
    s->set_maximum_size(s, &_size_val);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(views_CefLabelButton), create0)(JNIEnv* env, jclass clz, jobject delegate, jstring text) {
    cef_button_delegate_t* _delegate_ptr = delegate ? Create_JniCefButtonDelegate(env, delegate) : nullptr;
    auto _text_str = text ? JStringToCefString(env, text) : nullptr;
    auto _r = cef_label_button_create(_delegate_ptr, _text_str);
    if (_text_str) cef_string_userfree_free(_text_str);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/views/CefLabelButton$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}
