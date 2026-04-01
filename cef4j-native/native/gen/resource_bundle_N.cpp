// GENERATED - do not edit.
#include <jni.h>
#include "include/capi/cef_resource_bundle_capi.h"
#include "include/capi/cef_values_capi.h"
#include "jni_util.h"

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefResourceBundle_00024NativePeer_N_1Release(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefResourceBundle_00024NativePeer_N_1GetLocalizedString(JNIEnv* env, jobject obj, jlong self, jint string_id) {
    auto* s = reinterpret_cast<cef_resource_bundle_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_localized_string(s, string_id);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefResourceBundle_00024NativePeer_N_1GetDataResource(JNIEnv* env, jobject obj, jlong self, jint resource_id) {
    auto* s = reinterpret_cast<cef_resource_bundle_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_data_resource(s, resource_id);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefBinaryValue$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefResourceBundle_00024NativePeer_N_1GetDataResourceForScale(JNIEnv* env, jobject obj, jlong self, jint resource_id, jobject scale_factor) {
    auto* s = reinterpret_cast<cef_resource_bundle_t*>(self);
    if (!s) return nullptr;
    if (!scale_factor) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "scaleFactor must not be null"); return nullptr;}
    auto _r = s->get_data_resource_for_scale(s, resource_id, static_cast<cef_scale_factor_t>(env->GetLongField(scale_factor, env->GetFieldID(env->GetObjectClass(scale_factor), "value", "J"))));
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefBinaryValue$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefResourceBundle_00024NativePeer_N_1GetGlobal(JNIEnv* env, jclass clz) {
    auto _r = cef_resource_bundle_get_global();
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefResourceBundle$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}
