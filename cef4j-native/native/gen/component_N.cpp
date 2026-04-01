// GENERATED - do not edit.
#include <jni.h>
#include "include/capi/cef_component_updater_capi.h"
#include "jni_util.h"

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefComponent_00024NativePeer_N_1Release(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefComponent_00024NativePeer_N_1GetId(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_component_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_id(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefComponent_00024NativePeer_N_1GetName(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_component_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_name(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefComponent_00024NativePeer_N_1GetVersion(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_component_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_version(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefComponent_00024NativePeer_N_1GetState(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_component_t*>(self);
    if (!s) return 0;
    auto _r = s->get_state(s);
    auto _eCls = env->FindClass("net/kurobako/cef4j/gen/CefComponentState");
    auto _eOf = env->GetStaticMethodID(_eCls, "of", "(J)Lnet/kurobako/cef4j/gen/CefComponentState;");
    return env->CallStaticObjectMethod(_eCls, _eOf, static_cast<jlong>(_r));
}
