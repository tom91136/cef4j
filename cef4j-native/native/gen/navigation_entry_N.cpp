// GENERATED - do not edit.
#include <jni.h>
#include "include/capi/cef_navigation_entry_capi.h"
#include "include/capi/cef_ssl_status_capi.h"
#include "jni_util.h"

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefNavigationEntry_00024NativePeer_N_1Release(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefNavigationEntry_00024NativePeer_N_1IsValid(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_navigation_entry_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_valid(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefNavigationEntry_00024NativePeer_N_1GetUrl(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_navigation_entry_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_url(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefNavigationEntry_00024NativePeer_N_1GetDisplayUrl(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_navigation_entry_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_display_url(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefNavigationEntry_00024NativePeer_N_1GetOriginalUrl(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_navigation_entry_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_original_url(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefNavigationEntry_00024NativePeer_N_1GetTitle(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_navigation_entry_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_title(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefNavigationEntry_00024NativePeer_N_1GetTransitionType(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_navigation_entry_t*>(self);
    if (!s) return 0;
    auto _r = s->get_transition_type(s);
    auto _eCls = env->FindClass("net/kurobako/cef4j/gen/CefTransitionType");
    auto _eOf = env->GetStaticMethodID(_eCls, "of", "(J)Lnet/kurobako/cef4j/gen/CefTransitionType;");
    return env->CallStaticObjectMethod(_eCls, _eOf, static_cast<jlong>(_r));
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefNavigationEntry_00024NativePeer_N_1HasPostData(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_navigation_entry_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->has_post_data(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefNavigationEntry_00024NativePeer_N_1GetCompletionTime(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_navigation_entry_t*>(self);
    if (!s) return nullptr;
    cef_basetime_t result = s->get_completion_time(s);
    auto cls = env->FindClass("net/kurobako/cef4j/gen/CefBasetime");
    auto ctor = env->GetMethodID(cls, "<init>", "(J)V");
    auto _dsResult = env->NewObject(cls, ctor, static_cast<jlong>((&result)->val));
    return _dsResult;
}

extern "C" JNIEXPORT jint JNICALL Java_net_kurobako_cef4j_gen_CefNavigationEntry_00024NativePeer_N_1GetHttpStatusCode(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_navigation_entry_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->get_http_status_code(s));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefNavigationEntry_00024NativePeer_N_1GetSslStatus(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_navigation_entry_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_sslstatus(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefSslStatus$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}
