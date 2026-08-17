// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
#include <jni.h>
#include "include/capi/cef_preference_capi.h"
#include "include/capi/cef_values_capi.h"
#include "jni_util.h"

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefPreferenceRegistrar), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    // Scoped struct - no ref-counting, release is a no-op.
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefPreferenceRegistrar), addPreference0)(JNIEnv* env, jobject obj, jlong self, jstring name, jobject default_value) {
    auto* s = reinterpret_cast<cef_preference_registrar_t*>(self);
    if (!s) return JNI_FALSE;
    auto _name_str = JStringToCefString(env, name);
    cef_value_t* _default_value_ptr = default_value ? reinterpret_cast<cef_value_t*>(env->GetLongField(default_value, env->GetFieldID(env->GetObjectClass(default_value), "nativePtr", "J"))) : nullptr;
    if (_default_value_ptr) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_default_value_ptr); _b->add_ref(_b); }
    auto _r = s->add_preference(s, _name_str, _default_value_ptr);
    if (_name_str) cef_string_userfree_free(_name_str);
    return static_cast<jboolean>(_r);
}
