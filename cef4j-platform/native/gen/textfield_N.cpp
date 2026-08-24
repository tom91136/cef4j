// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
#include <jni.h>
#include "include/capi/views/cef_textfield_capi.h"
#include "include/capi/views/cef_textfield_delegate_capi.h"
#include "jni_util.h"

extern "C" cef_textfield_delegate_t* Create_JniCefTextfieldDelegate(JNIEnv* env, jobject handler);
CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefTextfield), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefTextfield), setPasswordInput0)(JNIEnv* env, jobject obj, jlong self, jboolean password_input) {
    auto* s = reinterpret_cast<cef_textfield_t*>(self);
    if (!s) return;
    s->set_password_input(s, static_cast<bool>(password_input));
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(views_CefTextfield), isPasswordInput0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_textfield_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_password_input(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefTextfield), setReadOnly0)(JNIEnv* env, jobject obj, jlong self, jboolean read_only) {
    auto* s = reinterpret_cast<cef_textfield_t*>(self);
    if (!s) return;
    s->set_read_only(s, static_cast<bool>(read_only));
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(views_CefTextfield), isReadOnly0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_textfield_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_read_only(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(views_CefTextfield), getText0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_textfield_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_text(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefTextfield), setText0)(JNIEnv* env, jobject obj, jlong self, jstring text) {
    auto* s = reinterpret_cast<cef_textfield_t*>(self);
    if (!s) return;
    auto _text_str = JStringToCefString(env, text);
    s->set_text(s, _text_str);
    if (_text_str) cef_string_userfree_free(_text_str);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefTextfield), appendText0)(JNIEnv* env, jobject obj, jlong self, jstring text) {
    auto* s = reinterpret_cast<cef_textfield_t*>(self);
    if (!s) return;
    auto _text_str = JStringToCefString(env, text);
    s->append_text(s, _text_str);
    if (_text_str) cef_string_userfree_free(_text_str);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefTextfield), insertOrReplaceText0)(JNIEnv* env, jobject obj, jlong self, jstring text) {
    auto* s = reinterpret_cast<cef_textfield_t*>(self);
    if (!s) return;
    auto _text_str = JStringToCefString(env, text);
    s->insert_or_replace_text(s, _text_str);
    if (_text_str) cef_string_userfree_free(_text_str);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(views_CefTextfield), hasSelection0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_textfield_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->has_selection(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(views_CefTextfield), getSelectedText0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_textfield_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_selected_text(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefTextfield), selectAll0)(JNIEnv* env, jobject obj, jlong self, jboolean reversed) {
    auto* s = reinterpret_cast<cef_textfield_t*>(self);
    if (!s) return;
    s->select_all(s, static_cast<bool>(reversed));
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefTextfield), clearSelection0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_textfield_t*>(self);
    if (!s) return;
    s->clear_selection(s);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(views_CefTextfield), getSelectedRange0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_textfield_t*>(self);
    if (!s) return nullptr;
    cef_range_t result = s->get_selected_range(s);
    auto cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefRange");
    auto ctor = env->GetMethodID(cls, "<init>", "(II)V");
    auto _dsResult = env->NewObject(cls, ctor, static_cast<jint>(((&result))->from), static_cast<jint>(((&result))->to));
    return _dsResult;
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefTextfield), selectRange0)(JNIEnv* env, jobject obj, jlong self, jobject range) {
    auto* s = reinterpret_cast<cef_textfield_t*>(self);
    if (!s) return;
    if (!range) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "range must not be null"); return; }
    cef_range_t _range_val = {};
    auto _range_c = FindClassCached(env, "net/kurobako/cef4j/gen/CefRange");
    _range_val.from = static_cast<decltype(_range_val.from)>(env->GetIntField(range, env->GetFieldID(_range_c, "from", "I")));
    _range_val.to = static_cast<decltype(_range_val.to)>(env->GetIntField(range, env->GetFieldID(_range_c, "to", "I")));
    s->select_range(s, &_range_val);
}

CEF4J_JNI_EXPORT(jlong, CEF4J_PEER(views_CefTextfield), getCursorPosition0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_textfield_t*>(self);
    if (!s) return 0;
    return static_cast<jlong>(s->get_cursor_position(s));
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefTextfield), setFontList0)(JNIEnv* env, jobject obj, jlong self, jstring font_list) {
    auto* s = reinterpret_cast<cef_textfield_t*>(self);
    if (!s) return;
    auto _font_list_str = JStringToCefString(env, font_list);
    s->set_font_list(s, _font_list_str);
    if (_font_list_str) cef_string_userfree_free(_font_list_str);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefTextfield), applyTextColor0)(JNIEnv* env, jobject obj, jlong self, jint color, jobject range) {
    auto* s = reinterpret_cast<cef_textfield_t*>(self);
    if (!s) return;
    if (!range) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "range must not be null"); return; }
    cef_range_t _range_val = {};
    auto _range_c = FindClassCached(env, "net/kurobako/cef4j/gen/CefRange");
    _range_val.from = static_cast<decltype(_range_val.from)>(env->GetIntField(range, env->GetFieldID(_range_c, "from", "I")));
    _range_val.to = static_cast<decltype(_range_val.to)>(env->GetIntField(range, env->GetFieldID(_range_c, "to", "I")));
    s->apply_text_color(s, static_cast<cef_color_t>(color), &_range_val);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefTextfield), applyTextStyle0)(JNIEnv* env, jobject obj, jlong self, jobject style, jboolean add, jobject range) {
    auto* s = reinterpret_cast<cef_textfield_t*>(self);
    if (!s) return;
    if (!style) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "style must not be null"); return; }
    if (!range) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "range must not be null"); return; }
    cef_range_t _range_val = {};
    auto _range_c = FindClassCached(env, "net/kurobako/cef4j/gen/CefRange");
    _range_val.from = static_cast<decltype(_range_val.from)>(env->GetIntField(range, env->GetFieldID(_range_c, "from", "I")));
    _range_val.to = static_cast<decltype(_range_val.to)>(env->GetIntField(range, env->GetFieldID(_range_c, "to", "I")));
    s->apply_text_style(s, static_cast<cef_text_style_t>(env->GetLongField(style, env->GetFieldID(env->GetObjectClass(style), "value", "J"))), static_cast<bool>(add), &_range_val);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(views_CefTextfield), isCommandEnabled0)(JNIEnv* env, jobject obj, jlong self, jobject command_id) {
    auto* s = reinterpret_cast<cef_textfield_t*>(self);
    if (!s) return JNI_FALSE;
    if (!command_id) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "commandId must not be null"); return JNI_FALSE; }
    auto _r = s->is_command_enabled(s, static_cast<cef_text_field_commands_t>(env->GetLongField(command_id, env->GetFieldID(env->GetObjectClass(command_id), "value", "J"))));
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefTextfield), executeCommand0)(JNIEnv* env, jobject obj, jlong self, jobject command_id) {
    auto* s = reinterpret_cast<cef_textfield_t*>(self);
    if (!s) return;
    if (!command_id) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "commandId must not be null"); return; }
    s->execute_command(s, static_cast<cef_text_field_commands_t>(env->GetLongField(command_id, env->GetFieldID(env->GetObjectClass(command_id), "value", "J"))));
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefTextfield), clearEditHistory0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_textfield_t*>(self);
    if (!s) return;
    s->clear_edit_history(s);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefTextfield), setPlaceholderText0)(JNIEnv* env, jobject obj, jlong self, jstring text) {
    auto* s = reinterpret_cast<cef_textfield_t*>(self);
    if (!s) return;
    auto _text_str = JStringToCefString(env, text);
    s->set_placeholder_text(s, _text_str);
    if (_text_str) cef_string_userfree_free(_text_str);
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(views_CefTextfield), getPlaceholderText0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_textfield_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_placeholder_text(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefTextfield), setAccessibleName0)(JNIEnv* env, jobject obj, jlong self, jstring name) {
    auto* s = reinterpret_cast<cef_textfield_t*>(self);
    if (!s) return;
    auto _name_str = JStringToCefString(env, name);
    s->set_accessible_name(s, _name_str);
    if (_name_str) cef_string_userfree_free(_name_str);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(views_CefTextfield), create0)(JNIEnv* env, jclass clz, jobject delegate) {
    cef_textfield_delegate_t* _delegate_ptr = delegate ? Create_JniCefTextfieldDelegate(env, delegate) : nullptr;
    auto _r = cef_textfield_create(_delegate_ptr);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/views/CefTextfield$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}
