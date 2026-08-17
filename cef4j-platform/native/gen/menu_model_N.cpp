// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
#include <jni.h>
#include "include/capi/cef_menu_model_capi.h"
#include "include/capi/cef_menu_model_delegate_capi.h"
#include "jni_util.h"

extern "C" cef_menu_model_delegate_t* Create_JniCefMenuModelDelegate(JNIEnv* env, jobject handler);
CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefMenuModel), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefMenuModel), isSubMenu0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_menu_model_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_sub_menu(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefMenuModel), clear0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_menu_model_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->clear(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jlong, CEF4J_PEER(CefMenuModel), getCount0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_menu_model_t*>(self);
    if (!s) return 0;
    return static_cast<jlong>(s->get_count(s));
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefMenuModel), addSeparator0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_menu_model_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->add_separator(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefMenuModel), addItem0)(JNIEnv* env, jobject obj, jlong self, jint command_id, jstring label) {
    auto* s = reinterpret_cast<cef_menu_model_t*>(self);
    if (!s) return JNI_FALSE;
    auto _label_str = JStringToCefString(env, label);
    auto _r = s->add_item(s, command_id, _label_str);
    if (_label_str) cef_string_userfree_free(_label_str);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefMenuModel), addCheckItem0)(JNIEnv* env, jobject obj, jlong self, jint command_id, jstring label) {
    auto* s = reinterpret_cast<cef_menu_model_t*>(self);
    if (!s) return JNI_FALSE;
    auto _label_str = JStringToCefString(env, label);
    auto _r = s->add_check_item(s, command_id, _label_str);
    if (_label_str) cef_string_userfree_free(_label_str);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefMenuModel), addRadioItem0)(JNIEnv* env, jobject obj, jlong self, jint command_id, jstring label, jint group_id) {
    auto* s = reinterpret_cast<cef_menu_model_t*>(self);
    if (!s) return JNI_FALSE;
    auto _label_str = JStringToCefString(env, label);
    auto _r = s->add_radio_item(s, command_id, _label_str, group_id);
    if (_label_str) cef_string_userfree_free(_label_str);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefMenuModel), addSubMenu0)(JNIEnv* env, jobject obj, jlong self, jint command_id, jstring label) {
    auto* s = reinterpret_cast<cef_menu_model_t*>(self);
    if (!s) return nullptr;
    auto _label_str = JStringToCefString(env, label);
    auto _r = s->add_sub_menu(s, command_id, _label_str);
    if (_label_str) cef_string_userfree_free(_label_str);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefMenuModel$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefMenuModel), insertSeparatorAt0)(JNIEnv* env, jobject obj, jlong self, jlong index) {
    auto* s = reinterpret_cast<cef_menu_model_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->insert_separator_at(s, index);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefMenuModel), insertItemAt0)(JNIEnv* env, jobject obj, jlong self, jlong index, jint command_id, jstring label) {
    auto* s = reinterpret_cast<cef_menu_model_t*>(self);
    if (!s) return JNI_FALSE;
    auto _label_str = JStringToCefString(env, label);
    auto _r = s->insert_item_at(s, index, command_id, _label_str);
    if (_label_str) cef_string_userfree_free(_label_str);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefMenuModel), insertCheckItemAt0)(JNIEnv* env, jobject obj, jlong self, jlong index, jint command_id, jstring label) {
    auto* s = reinterpret_cast<cef_menu_model_t*>(self);
    if (!s) return JNI_FALSE;
    auto _label_str = JStringToCefString(env, label);
    auto _r = s->insert_check_item_at(s, index, command_id, _label_str);
    if (_label_str) cef_string_userfree_free(_label_str);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefMenuModel), insertRadioItemAt0)(JNIEnv* env, jobject obj, jlong self, jlong index, jint command_id, jstring label, jint group_id) {
    auto* s = reinterpret_cast<cef_menu_model_t*>(self);
    if (!s) return JNI_FALSE;
    auto _label_str = JStringToCefString(env, label);
    auto _r = s->insert_radio_item_at(s, index, command_id, _label_str, group_id);
    if (_label_str) cef_string_userfree_free(_label_str);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefMenuModel), insertSubMenuAt0)(JNIEnv* env, jobject obj, jlong self, jlong index, jint command_id, jstring label) {
    auto* s = reinterpret_cast<cef_menu_model_t*>(self);
    if (!s) return nullptr;
    auto _label_str = JStringToCefString(env, label);
    auto _r = s->insert_sub_menu_at(s, index, command_id, _label_str);
    if (_label_str) cef_string_userfree_free(_label_str);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefMenuModel$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefMenuModel), remove0)(JNIEnv* env, jobject obj, jlong self, jint command_id) {
    auto* s = reinterpret_cast<cef_menu_model_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->remove(s, command_id);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefMenuModel), removeAt0)(JNIEnv* env, jobject obj, jlong self, jlong index) {
    auto* s = reinterpret_cast<cef_menu_model_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->remove_at(s, index);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jint, CEF4J_PEER(CefMenuModel), getIndexOf0)(JNIEnv* env, jobject obj, jlong self, jint command_id) {
    auto* s = reinterpret_cast<cef_menu_model_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->get_index_of(s, command_id));
}

CEF4J_JNI_EXPORT(jint, CEF4J_PEER(CefMenuModel), getCommandIdAt0)(JNIEnv* env, jobject obj, jlong self, jlong index) {
    auto* s = reinterpret_cast<cef_menu_model_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->get_command_id_at(s, index));
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefMenuModel), setCommandIdAt0)(JNIEnv* env, jobject obj, jlong self, jlong index, jint command_id) {
    auto* s = reinterpret_cast<cef_menu_model_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->set_command_id_at(s, index, command_id);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefMenuModel), getLabel0)(JNIEnv* env, jobject obj, jlong self, jint command_id) {
    auto* s = reinterpret_cast<cef_menu_model_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_label(s, command_id);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefMenuModel), setLabel0)(JNIEnv* env, jobject obj, jlong self, jint command_id, jstring label) {
    auto* s = reinterpret_cast<cef_menu_model_t*>(self);
    if (!s) return JNI_FALSE;
    auto _label_str = JStringToCefString(env, label);
    auto _r = s->set_label(s, command_id, _label_str);
    if (_label_str) cef_string_userfree_free(_label_str);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefMenuModel), setLabelAt0)(JNIEnv* env, jobject obj, jlong self, jlong index, jstring label) {
    auto* s = reinterpret_cast<cef_menu_model_t*>(self);
    if (!s) return JNI_FALSE;
    auto _label_str = JStringToCefString(env, label);
    auto _r = s->set_label_at(s, index, _label_str);
    if (_label_str) cef_string_userfree_free(_label_str);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefMenuModel), getType0)(JNIEnv* env, jobject obj, jlong self, jint command_id) {
    auto* s = reinterpret_cast<cef_menu_model_t*>(self);
    if (!s) return 0;
    auto _r = s->get_type(s, command_id);
    auto _eCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefMenuItemType");
    auto _eOf = env->GetStaticMethodID(_eCls, "of", "(J)Lnet/kurobako/cef4j/gen/CefMenuItemType;");
    return env->CallStaticObjectMethod(_eCls, _eOf, static_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jint, CEF4J_PEER(CefMenuModel), getGroupId0)(JNIEnv* env, jobject obj, jlong self, jint command_id) {
    auto* s = reinterpret_cast<cef_menu_model_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->get_group_id(s, command_id));
}

CEF4J_JNI_EXPORT(jint, CEF4J_PEER(CefMenuModel), getGroupIdAt0)(JNIEnv* env, jobject obj, jlong self, jlong index) {
    auto* s = reinterpret_cast<cef_menu_model_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->get_group_id_at(s, index));
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefMenuModel), setGroupId0)(JNIEnv* env, jobject obj, jlong self, jint command_id, jint group_id) {
    auto* s = reinterpret_cast<cef_menu_model_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->set_group_id(s, command_id, group_id);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefMenuModel), setGroupIdAt0)(JNIEnv* env, jobject obj, jlong self, jlong index, jint group_id) {
    auto* s = reinterpret_cast<cef_menu_model_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->set_group_id_at(s, index, group_id);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefMenuModel), getSubMenu0)(JNIEnv* env, jobject obj, jlong self, jint command_id) {
    auto* s = reinterpret_cast<cef_menu_model_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_sub_menu(s, command_id);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefMenuModel$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefMenuModel), getSubMenuAt0)(JNIEnv* env, jobject obj, jlong self, jlong index) {
    auto* s = reinterpret_cast<cef_menu_model_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_sub_menu_at(s, index);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefMenuModel$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefMenuModel), isVisible0)(JNIEnv* env, jobject obj, jlong self, jint command_id) {
    auto* s = reinterpret_cast<cef_menu_model_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_visible(s, command_id);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefMenuModel), isVisibleAt0)(JNIEnv* env, jobject obj, jlong self, jlong index) {
    auto* s = reinterpret_cast<cef_menu_model_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_visible_at(s, index);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefMenuModel), setVisible0)(JNIEnv* env, jobject obj, jlong self, jint command_id, jboolean visible) {
    auto* s = reinterpret_cast<cef_menu_model_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->set_visible(s, command_id, static_cast<bool>(visible));
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefMenuModel), setVisibleAt0)(JNIEnv* env, jobject obj, jlong self, jlong index, jboolean visible) {
    auto* s = reinterpret_cast<cef_menu_model_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->set_visible_at(s, index, static_cast<bool>(visible));
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefMenuModel), isEnabled0)(JNIEnv* env, jobject obj, jlong self, jint command_id) {
    auto* s = reinterpret_cast<cef_menu_model_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_enabled(s, command_id);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefMenuModel), isEnabledAt0)(JNIEnv* env, jobject obj, jlong self, jlong index) {
    auto* s = reinterpret_cast<cef_menu_model_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_enabled_at(s, index);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefMenuModel), setEnabled0)(JNIEnv* env, jobject obj, jlong self, jint command_id, jboolean enabled) {
    auto* s = reinterpret_cast<cef_menu_model_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->set_enabled(s, command_id, static_cast<bool>(enabled));
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefMenuModel), setEnabledAt0)(JNIEnv* env, jobject obj, jlong self, jlong index, jboolean enabled) {
    auto* s = reinterpret_cast<cef_menu_model_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->set_enabled_at(s, index, static_cast<bool>(enabled));
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefMenuModel), isChecked0)(JNIEnv* env, jobject obj, jlong self, jint command_id) {
    auto* s = reinterpret_cast<cef_menu_model_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_checked(s, command_id);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefMenuModel), isCheckedAt0)(JNIEnv* env, jobject obj, jlong self, jlong index) {
    auto* s = reinterpret_cast<cef_menu_model_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_checked_at(s, index);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefMenuModel), setChecked0)(JNIEnv* env, jobject obj, jlong self, jint command_id, jboolean checked) {
    auto* s = reinterpret_cast<cef_menu_model_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->set_checked(s, command_id, static_cast<bool>(checked));
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefMenuModel), setCheckedAt0)(JNIEnv* env, jobject obj, jlong self, jlong index, jboolean checked) {
    auto* s = reinterpret_cast<cef_menu_model_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->set_checked_at(s, index, static_cast<bool>(checked));
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefMenuModel), hasAccelerator0)(JNIEnv* env, jobject obj, jlong self, jint command_id) {
    auto* s = reinterpret_cast<cef_menu_model_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->has_accelerator(s, command_id);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefMenuModel), hasAcceleratorAt0)(JNIEnv* env, jobject obj, jlong self, jlong index) {
    auto* s = reinterpret_cast<cef_menu_model_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->has_accelerator_at(s, index);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefMenuModel), setAccelerator0)(JNIEnv* env, jobject obj, jlong self, jint command_id, jint key_code, jboolean shift_pressed, jboolean ctrl_pressed, jboolean alt_pressed) {
    auto* s = reinterpret_cast<cef_menu_model_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->set_accelerator(s, command_id, key_code, static_cast<bool>(shift_pressed), static_cast<bool>(ctrl_pressed), static_cast<bool>(alt_pressed));
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefMenuModel), setAcceleratorAt0)(JNIEnv* env, jobject obj, jlong self, jlong index, jint key_code, jboolean shift_pressed, jboolean ctrl_pressed, jboolean alt_pressed) {
    auto* s = reinterpret_cast<cef_menu_model_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->set_accelerator_at(s, index, key_code, static_cast<bool>(shift_pressed), static_cast<bool>(ctrl_pressed), static_cast<bool>(alt_pressed));
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefMenuModel), removeAccelerator0)(JNIEnv* env, jobject obj, jlong self, jint command_id) {
    auto* s = reinterpret_cast<cef_menu_model_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->remove_accelerator(s, command_id);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefMenuModel), removeAcceleratorAt0)(JNIEnv* env, jobject obj, jlong self, jlong index) {
    auto* s = reinterpret_cast<cef_menu_model_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->remove_accelerator_at(s, index);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefMenuModel), getAccelerator0)(JNIEnv* env, jobject obj, jlong self, jint command_id, jintArray key_code, jintArray shift_pressed, jintArray ctrl_pressed, jintArray alt_pressed) {
    auto* s = reinterpret_cast<cef_menu_model_t*>(self);
    if (!s) return JNI_FALSE;
    int _key_code_val = 0;
    if (key_code) { jint _jv; env->GetIntArrayRegion(key_code, 0, 1, &_jv); _key_code_val = _jv; }
    int _shift_pressed_val = 0;
    if (shift_pressed) { jint _jv; env->GetIntArrayRegion(shift_pressed, 0, 1, &_jv); _shift_pressed_val = _jv; }
    int _ctrl_pressed_val = 0;
    if (ctrl_pressed) { jint _jv; env->GetIntArrayRegion(ctrl_pressed, 0, 1, &_jv); _ctrl_pressed_val = _jv; }
    int _alt_pressed_val = 0;
    if (alt_pressed) { jint _jv; env->GetIntArrayRegion(alt_pressed, 0, 1, &_jv); _alt_pressed_val = _jv; }
    auto _r = s->get_accelerator(s, command_id, &_key_code_val, &_shift_pressed_val, &_ctrl_pressed_val, &_alt_pressed_val);
    if (key_code) { jint _jv = _key_code_val; env->SetIntArrayRegion(key_code, 0, 1, &_jv); }
    if (shift_pressed) { jint _jv = _shift_pressed_val; env->SetIntArrayRegion(shift_pressed, 0, 1, &_jv); }
    if (ctrl_pressed) { jint _jv = _ctrl_pressed_val; env->SetIntArrayRegion(ctrl_pressed, 0, 1, &_jv); }
    if (alt_pressed) { jint _jv = _alt_pressed_val; env->SetIntArrayRegion(alt_pressed, 0, 1, &_jv); }
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefMenuModel), getAcceleratorAt0)(JNIEnv* env, jobject obj, jlong self, jlong index, jintArray key_code, jintArray shift_pressed, jintArray ctrl_pressed, jintArray alt_pressed) {
    auto* s = reinterpret_cast<cef_menu_model_t*>(self);
    if (!s) return JNI_FALSE;
    int _key_code_val = 0;
    if (key_code) { jint _jv; env->GetIntArrayRegion(key_code, 0, 1, &_jv); _key_code_val = _jv; }
    int _shift_pressed_val = 0;
    if (shift_pressed) { jint _jv; env->GetIntArrayRegion(shift_pressed, 0, 1, &_jv); _shift_pressed_val = _jv; }
    int _ctrl_pressed_val = 0;
    if (ctrl_pressed) { jint _jv; env->GetIntArrayRegion(ctrl_pressed, 0, 1, &_jv); _ctrl_pressed_val = _jv; }
    int _alt_pressed_val = 0;
    if (alt_pressed) { jint _jv; env->GetIntArrayRegion(alt_pressed, 0, 1, &_jv); _alt_pressed_val = _jv; }
    auto _r = s->get_accelerator_at(s, index, &_key_code_val, &_shift_pressed_val, &_ctrl_pressed_val, &_alt_pressed_val);
    if (key_code) { jint _jv = _key_code_val; env->SetIntArrayRegion(key_code, 0, 1, &_jv); }
    if (shift_pressed) { jint _jv = _shift_pressed_val; env->SetIntArrayRegion(shift_pressed, 0, 1, &_jv); }
    if (ctrl_pressed) { jint _jv = _ctrl_pressed_val; env->SetIntArrayRegion(ctrl_pressed, 0, 1, &_jv); }
    if (alt_pressed) { jint _jv = _alt_pressed_val; env->SetIntArrayRegion(alt_pressed, 0, 1, &_jv); }
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefMenuModel), setColor0)(JNIEnv* env, jobject obj, jlong self, jint command_id, jobject color_type, jint color) {
    auto* s = reinterpret_cast<cef_menu_model_t*>(self);
    if (!s) return JNI_FALSE;
    if (!color_type) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "colorType must not be null"); return JNI_FALSE; }
    auto _r = s->set_color(s, command_id, static_cast<cef_menu_color_type_t>(env->GetLongField(color_type, env->GetFieldID(env->GetObjectClass(color_type), "value", "J"))), color);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefMenuModel), setColorAt0)(JNIEnv* env, jobject obj, jlong self, jint index, jobject color_type, jint color) {
    auto* s = reinterpret_cast<cef_menu_model_t*>(self);
    if (!s) return JNI_FALSE;
    if (!color_type) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "colorType must not be null"); return JNI_FALSE; }
    auto _r = s->set_color_at(s, index, static_cast<cef_menu_color_type_t>(env->GetLongField(color_type, env->GetFieldID(env->GetObjectClass(color_type), "value", "J"))), color);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefMenuModel), getColor0)(JNIEnv* env, jobject obj, jlong self, jint command_id, jobject color_type, jintArray color) {
    auto* s = reinterpret_cast<cef_menu_model_t*>(self);
    if (!s) return JNI_FALSE;
    if (!color_type) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "colorType must not be null"); return JNI_FALSE; }
    unsigned int _color_val = 0;
    if (color) { jint _jv; env->GetIntArrayRegion(color, 0, 1, &_jv); _color_val = static_cast<unsigned int>(_jv); }
    auto _r = s->get_color(s, command_id, static_cast<cef_menu_color_type_t>(env->GetLongField(color_type, env->GetFieldID(env->GetObjectClass(color_type), "value", "J"))), &_color_val);
    if (color) { jint _jv = static_cast<jint>(_color_val); env->SetIntArrayRegion(color, 0, 1, &_jv); }
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefMenuModel), getColorAt0)(JNIEnv* env, jobject obj, jlong self, jint index, jobject color_type, jintArray color) {
    auto* s = reinterpret_cast<cef_menu_model_t*>(self);
    if (!s) return JNI_FALSE;
    if (!color_type) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "colorType must not be null"); return JNI_FALSE; }
    unsigned int _color_val = 0;
    if (color) { jint _jv; env->GetIntArrayRegion(color, 0, 1, &_jv); _color_val = static_cast<unsigned int>(_jv); }
    auto _r = s->get_color_at(s, index, static_cast<cef_menu_color_type_t>(env->GetLongField(color_type, env->GetFieldID(env->GetObjectClass(color_type), "value", "J"))), &_color_val);
    if (color) { jint _jv = static_cast<jint>(_color_val); env->SetIntArrayRegion(color, 0, 1, &_jv); }
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefMenuModel), setFontList0)(JNIEnv* env, jobject obj, jlong self, jint command_id, jstring font_list) {
    auto* s = reinterpret_cast<cef_menu_model_t*>(self);
    if (!s) return JNI_FALSE;
    auto _font_list_str = font_list ? JStringToCefString(env, font_list) : nullptr;
    auto _r = s->set_font_list(s, command_id, _font_list_str);
    if (_font_list_str) cef_string_userfree_free(_font_list_str);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefMenuModel), setFontListAt0)(JNIEnv* env, jobject obj, jlong self, jint index, jstring font_list) {
    auto* s = reinterpret_cast<cef_menu_model_t*>(self);
    if (!s) return JNI_FALSE;
    auto _font_list_str = font_list ? JStringToCefString(env, font_list) : nullptr;
    auto _r = s->set_font_list_at(s, index, _font_list_str);
    if (_font_list_str) cef_string_userfree_free(_font_list_str);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefMenuModel), create0)(JNIEnv* env, jclass clz, jobject delegate) {
    cef_menu_model_delegate_t* _delegate_ptr = delegate ? Create_JniCefMenuModelDelegate(env, delegate) : nullptr;
    auto _r = cef_menu_model_create(_delegate_ptr);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefMenuModel$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}
