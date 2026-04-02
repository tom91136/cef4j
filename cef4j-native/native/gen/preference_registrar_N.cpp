// GENERATED - do not edit.
#include <jni.h>
#include "include/capi/cef_preference_capi.h"
#include "include/capi/cef_values_capi.h"
#include "jni_util.h"

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefPreferenceRegistrar_00024NativePeer_N_1Release(JNIEnv* env, jclass clz, jlong ptr) {
    // Scoped struct - no ref-counting, release is a no-op.
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefPreferenceRegistrar_00024NativePeer_N_1AddPreference(JNIEnv* env, jobject obj, jlong self, jstring name, jobject default_value) {
    auto* s = reinterpret_cast<cef_preference_registrar_t*>(self);
    if (!s) return JNI_FALSE;
    auto _name_str = JStringToCefString(env, name);
    cef_value_t* _default_value_ptr = default_value ? reinterpret_cast<cef_value_t*>(env->GetLongField(default_value, env->GetFieldID(env->GetObjectClass(default_value), "nativePtr", "J"))) : nullptr;
    if (_default_value_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_default_value_ptr); _b->add_ref(_b);}
    auto _r = s->add_preference(s, _name_str, _default_value_ptr);
    if (_name_str) cef_string_userfree_free(_name_str);
    return static_cast<jboolean>(_r);
}
