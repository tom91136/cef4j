// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
#include <jni.h>
#include "include/capi/cef_preference_capi.h"
#include "include/capi/cef_registration_capi.h"
#include "include/capi/cef_values_capi.h"
#include "jni_util.h"

extern "C" cef_preference_observer_t* Create_JniCefPreferenceObserver(JNIEnv* env, jobject handler);
CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefPreferenceManager), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefPreferenceManager), hasPreference0)(JNIEnv* env, jobject obj, jlong self, jstring name) {
    auto* s = reinterpret_cast<cef_preference_manager_t*>(self);
    if (!s) return JNI_FALSE;
    auto _name_str = JStringToCefString(env, name);
    auto _r = s->has_preference(s, _name_str);
    if (_name_str) cef_string_userfree_free(_name_str);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefPreferenceManager), getPreference0)(JNIEnv* env, jobject obj, jlong self, jstring name) {
    auto* s = reinterpret_cast<cef_preference_manager_t*>(self);
    if (!s) return nullptr;
    auto _name_str = JStringToCefString(env, name);
    auto _r = s->get_preference(s, _name_str);
    if (_name_str) cef_string_userfree_free(_name_str);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefValue$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefPreferenceManager), getAllPreferences0)(JNIEnv* env, jobject obj, jlong self, jboolean include_defaults) {
    auto* s = reinterpret_cast<cef_preference_manager_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_all_preferences(s, static_cast<bool>(include_defaults));
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefDictionaryValue$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefPreferenceManager), canSetPreference0)(JNIEnv* env, jobject obj, jlong self, jstring name) {
    auto* s = reinterpret_cast<cef_preference_manager_t*>(self);
    if (!s) return JNI_FALSE;
    auto _name_str = JStringToCefString(env, name);
    auto _r = s->can_set_preference(s, _name_str);
    if (_name_str) cef_string_userfree_free(_name_str);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefPreferenceManager), setPreference0)(JNIEnv* env, jobject obj, jlong self, jstring name, jobject value, jstring error) {
    auto* s = reinterpret_cast<cef_preference_manager_t*>(self);
    if (!s) return JNI_FALSE;
    auto _name_str = JStringToCefString(env, name);
    cef_value_t* _value_ptr = value ? reinterpret_cast<cef_value_t*>(env->GetLongField(value, env->GetFieldID(env->GetObjectClass(value), "nativePtr", "J"))) : nullptr;
    if (_value_ptr) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_value_ptr); _b->add_ref(_b); }
    auto _error_str = JStringToCefString(env, error);
    auto _r = s->set_preference(s, _name_str, _value_ptr, _error_str);
    if (_name_str) cef_string_userfree_free(_name_str);
    if (_error_str) cef_string_userfree_free(_error_str);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefPreferenceManager), addPreferenceObserver0)(JNIEnv* env, jobject obj, jlong self, jstring name, jobject observer) {
    auto* s = reinterpret_cast<cef_preference_manager_t*>(self);
    if (!s) return nullptr;
    auto _name_str = name ? JStringToCefString(env, name) : nullptr;
    cef_preference_observer_t* _observer_ptr = observer ? Create_JniCefPreferenceObserver(env, observer) : nullptr;
    auto _r = s->add_preference_observer(s, _name_str, _observer_ptr);
    if (_name_str) cef_string_userfree_free(_name_str);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefRegistration$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefPreferenceManager), getChromeVariationsAsSwitches0)(JNIEnv* env, jclass clz, jobject switches) {
    if (!switches) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "switches must not be null"); return; }
    auto _switches_csl = JavaListToCefStringList(env, switches);
    cef_preference_manager_get_chrome_variations_as_switches(_switches_csl);
    CefStringListWriteBack(env, _switches_csl, switches);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefPreferenceManager), getChromeVariationsAsStrings0)(JNIEnv* env, jclass clz, jobject strings) {
    if (!strings) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "strings must not be null"); return; }
    auto _strings_csl = JavaListToCefStringList(env, strings);
    cef_preference_manager_get_chrome_variations_as_strings(_strings_csl);
    CefStringListWriteBack(env, _strings_csl, strings);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefPreferenceManager), getGlobal0)(JNIEnv* env, jclass clz) {
    auto _r = cef_preference_manager_get_global();
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefPreferenceManager$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}
