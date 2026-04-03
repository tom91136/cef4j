// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
#include <jni.h>
#include "include/capi/cef_scheme_capi.h"
#include "jni_util.h"

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefSchemeRegistrar), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    // Scoped struct - no ref-counting, release is a no-op.
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefSchemeRegistrar), addCustomScheme0)(JNIEnv* env, jobject obj, jlong self, jstring scheme_name, jint options) {
    auto* s = reinterpret_cast<cef_scheme_registrar_t*>(self);
    if (!s) return JNI_FALSE;
    auto _scheme_name_str = JStringToCefString(env, scheme_name);
    auto _r = s->add_custom_scheme(s, _scheme_name_str, options);
    if (_scheme_name_str) cef_string_userfree_free(_scheme_name_str);
    return static_cast<jboolean>(_r);
}
