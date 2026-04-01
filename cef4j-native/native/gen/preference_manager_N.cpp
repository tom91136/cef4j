// GENERATED - do not edit.
#include <jni.h>
#include "include/capi/cef_preference_capi.h"
#include "include/capi/cef_registration_capi.h"
#include "include/capi/cef_values_capi.h"
#include "jni_util.h"

extern "C" cef_preference_observer_t* Create_JniCefPreferenceObserver(JNIEnv *env, jobject handler);
extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefPreferenceManager_00024NativePeer_N_1Release(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefPreferenceManager_00024NativePeer_N_1HasPreference(JNIEnv* env, jobject obj, jlong self, jstring name) {
    auto* s = reinterpret_cast<cef_preference_manager_t*>(self);
    if (!s) return JNI_FALSE;
    if (!name) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "name must not be null"); return JNI_FALSE;}
    auto _name_str = JStringToCefString(env, name);
    auto _r = s->has_preference(s, _name_str);
    if (_name_str) cef_string_userfree_free(_name_str);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefPreferenceManager_00024NativePeer_N_1GetPreference(JNIEnv* env, jobject obj, jlong self, jstring name) {
    auto* s = reinterpret_cast<cef_preference_manager_t*>(self);
    if (!s) return nullptr;
    if (!name) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "name must not be null"); return nullptr;}
    auto _name_str = JStringToCefString(env, name);
    auto _r = s->get_preference(s, _name_str);
    if (_name_str) cef_string_userfree_free(_name_str);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefValue$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefPreferenceManager_00024NativePeer_N_1GetAllPreferences(JNIEnv* env, jobject obj, jlong self, jboolean include_defaults) {
    auto* s = reinterpret_cast<cef_preference_manager_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_all_preferences(s, static_cast<bool>(include_defaults));
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefDictionaryValue$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefPreferenceManager_00024NativePeer_N_1CanSetPreference(JNIEnv* env, jobject obj, jlong self, jstring name) {
    auto* s = reinterpret_cast<cef_preference_manager_t*>(self);
    if (!s) return JNI_FALSE;
    if (!name) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "name must not be null"); return JNI_FALSE;}
    auto _name_str = JStringToCefString(env, name);
    auto _r = s->can_set_preference(s, _name_str);
    if (_name_str) cef_string_userfree_free(_name_str);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefPreferenceManager_00024NativePeer_N_1SetPreference(JNIEnv* env, jobject obj, jlong self, jstring name, jobject value, jstring error) {
    auto* s = reinterpret_cast<cef_preference_manager_t*>(self);
    if (!s) return JNI_FALSE;
    if (!name) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "name must not be null"); return JNI_FALSE;}
    if (!error) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "error must not be null"); return JNI_FALSE;}
    auto _name_str = JStringToCefString(env, name);
    cef_value_t* _value_ptr = value ? reinterpret_cast<cef_value_t*>(env->GetLongField(value, env->GetFieldID(env->GetObjectClass(value), "nativePtr", "J"))) : nullptr;
    if (_value_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_value_ptr); _b->add_ref(_b);}
    auto _error_str = JStringToCefString(env, error);
    auto _r = s->set_preference(s, _name_str, _value_ptr, _error_str);
    if (_name_str) cef_string_userfree_free(_name_str);
    if (_error_str) cef_string_userfree_free(_error_str);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefPreferenceManager_00024NativePeer_N_1AddPreferenceObserver(JNIEnv* env, jobject obj, jlong self, jstring name, jobject observer) {
    auto* s = reinterpret_cast<cef_preference_manager_t*>(self);
    if (!s) return nullptr;
    if (!observer) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "observer must not be null"); return nullptr;}
    auto _name_str = name ? JStringToCefString(env, name) : nullptr;
    cef_preference_observer_t* _observer_ptr = Create_JniCefPreferenceObserver(env, observer);
    auto _r = s->add_preference_observer(s, _name_str, _observer_ptr);
    if (_name_str) cef_string_userfree_free(_name_str);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefRegistration$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefPreferenceManager_00024NativePeer_N_1GetChromeVariationsAsSwitches(JNIEnv* env, jclass clz, jobject switches) {
    if (!switches) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "switches must not be null"); return;}
    auto _switches_csl = JavaListToCefStringList(env, switches);
    cef_preference_manager_get_chrome_variations_as_switches(_switches_csl);
    CefStringListWriteBack(env, _switches_csl, switches);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefPreferenceManager_00024NativePeer_N_1GetChromeVariationsAsStrings(JNIEnv* env, jclass clz, jobject strings) {
    if (!strings) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "strings must not be null"); return;}
    auto _strings_csl = JavaListToCefStringList(env, strings);
    cef_preference_manager_get_chrome_variations_as_strings(_strings_csl);
    CefStringListWriteBack(env, _strings_csl, strings);
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefPreferenceManager_00024NativePeer_N_1GetGlobal(JNIEnv* env, jclass clz) {
    auto _r = cef_preference_manager_get_global();
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefPreferenceManager$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}
