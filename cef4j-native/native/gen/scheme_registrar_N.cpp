// GENERATED - do not edit.
#include <jni.h>
#include "include/capi/cef_scheme_capi.h"
#include "jni_util.h"

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefSchemeRegistrar_00024NativePeer_N_1Release(JNIEnv* env, jclass clz, jlong ptr) {
    // Scoped struct — no ref-counting, release is a no-op.
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefSchemeRegistrar_00024NativePeer_N_1AddCustomScheme(JNIEnv* env, jobject obj, jlong self, jstring scheme_name, jint options) {
    auto* s = reinterpret_cast<cef_scheme_registrar_t*>(self);
    if (!s) return JNI_FALSE;
    if (!scheme_name) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "schemeName must not be null"); return JNI_FALSE;}
    auto _scheme_name_str = JStringToCefString(env, scheme_name);
    auto _r = s->add_custom_scheme(s, _scheme_name_str, options);
    if (_scheme_name_str) cef_string_userfree_free(_scheme_name_str);
    return static_cast<jboolean>(_r);
}
