// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
#include <jni.h>
#include "include/capi/cef_component_updater_capi.h"
#include "jni_util.h"

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefComponent), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefComponent), getId0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_component_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_id(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefComponent), getName0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_component_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_name(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefComponent), getVersion0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_component_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_version(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefComponent), getState0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_component_t*>(self);
    if (!s) return 0;
    auto _r = s->get_state(s);
    auto _eCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefComponentState");
    auto _eOf = env->GetStaticMethodID(_eCls, "of", "(J)Lnet/kurobako/cef4j/gen/CefComponentState;");
    return env->CallStaticObjectMethod(_eCls, _eOf, static_cast<jlong>(_r));
}
