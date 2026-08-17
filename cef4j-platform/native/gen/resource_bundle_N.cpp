// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
#include <jni.h>
#include "include/capi/cef_resource_bundle_capi.h"
#include "include/capi/cef_values_capi.h"
#include "jni_util.h"

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefResourceBundle), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefResourceBundle), getLocalizedString0)(JNIEnv* env, jobject obj, jlong self, jint string_id) {
    auto* s = reinterpret_cast<cef_resource_bundle_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_localized_string(s, string_id);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefResourceBundle), getDataResource0)(JNIEnv* env, jobject obj, jlong self, jint resource_id) {
    auto* s = reinterpret_cast<cef_resource_bundle_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_data_resource(s, resource_id);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefBinaryValue$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefResourceBundle), getDataResourceForScale0)(JNIEnv* env, jobject obj, jlong self, jint resource_id, jobject scale_factor) {
    auto* s = reinterpret_cast<cef_resource_bundle_t*>(self);
    if (!s) return nullptr;
    if (!scale_factor) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "scaleFactor must not be null"); return nullptr; }
    auto _r = s->get_data_resource_for_scale(s, resource_id, static_cast<cef_scale_factor_t>(env->GetLongField(scale_factor, env->GetFieldID(env->GetObjectClass(scale_factor), "value", "J"))));
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefBinaryValue$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefResourceBundle), getGlobal0)(JNIEnv* env, jclass clz) {
    auto _r = cef_resource_bundle_get_global();
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefResourceBundle$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}
