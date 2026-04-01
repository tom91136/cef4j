// GENERATED - do not edit.
#include <jni.h>
#include "include/capi/cef_download_item_capi.h"
#include "jni_util.h"

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefDownloadItem_00024NativePeer_N_1Release(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefDownloadItem_00024NativePeer_N_1IsValid(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_download_item_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_valid(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefDownloadItem_00024NativePeer_N_1IsInProgress(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_download_item_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_in_progress(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefDownloadItem_00024NativePeer_N_1IsComplete(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_download_item_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_complete(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefDownloadItem_00024NativePeer_N_1IsCanceled(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_download_item_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_canceled(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefDownloadItem_00024NativePeer_N_1IsInterrupted(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_download_item_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_interrupted(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefDownloadItem_00024NativePeer_N_1GetInterruptReason(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_download_item_t*>(self);
    if (!s) return 0;
    auto _r = s->get_interrupt_reason(s);
    auto _eCls = env->FindClass("net/kurobako/cef4j/gen/CefDownloadInterruptReason");
    auto _eOf = env->GetStaticMethodID(_eCls, "of", "(J)Lnet/kurobako/cef4j/gen/CefDownloadInterruptReason;");
    return env->CallStaticObjectMethod(_eCls, _eOf, static_cast<jlong>(_r));
}

extern "C" JNIEXPORT jlong JNICALL Java_net_kurobako_cef4j_gen_CefDownloadItem_00024NativePeer_N_1GetCurrentSpeed(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_download_item_t*>(self);
    if (!s) return 0;
    return static_cast<jlong>(s->get_current_speed(s));
}

extern "C" JNIEXPORT jint JNICALL Java_net_kurobako_cef4j_gen_CefDownloadItem_00024NativePeer_N_1GetPercentComplete(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_download_item_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->get_percent_complete(s));
}

extern "C" JNIEXPORT jlong JNICALL Java_net_kurobako_cef4j_gen_CefDownloadItem_00024NativePeer_N_1GetTotalBytes(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_download_item_t*>(self);
    if (!s) return 0;
    return static_cast<jlong>(s->get_total_bytes(s));
}

extern "C" JNIEXPORT jlong JNICALL Java_net_kurobako_cef4j_gen_CefDownloadItem_00024NativePeer_N_1GetReceivedBytes(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_download_item_t*>(self);
    if (!s) return 0;
    return static_cast<jlong>(s->get_received_bytes(s));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefDownloadItem_00024NativePeer_N_1GetStartTime(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_download_item_t*>(self);
    if (!s) return nullptr;
    cef_basetime_t result = s->get_start_time(s);
    auto cls = env->FindClass("net/kurobako/cef4j/gen/CefBasetime");
    auto ctor = env->GetMethodID(cls, "<init>", "(J)V");
    auto _dsResult = env->NewObject(cls, ctor, static_cast<jlong>((&result)->val));
    return _dsResult;
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefDownloadItem_00024NativePeer_N_1GetEndTime(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_download_item_t*>(self);
    if (!s) return nullptr;
    cef_basetime_t result = s->get_end_time(s);
    auto cls = env->FindClass("net/kurobako/cef4j/gen/CefBasetime");
    auto ctor = env->GetMethodID(cls, "<init>", "(J)V");
    auto _dsResult = env->NewObject(cls, ctor, static_cast<jlong>((&result)->val));
    return _dsResult;
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefDownloadItem_00024NativePeer_N_1GetFullPath(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_download_item_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_full_path(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jint JNICALL Java_net_kurobako_cef4j_gen_CefDownloadItem_00024NativePeer_N_1GetId(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_download_item_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->get_id(s));
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefDownloadItem_00024NativePeer_N_1GetUrl(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_download_item_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_url(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefDownloadItem_00024NativePeer_N_1GetOriginalUrl(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_download_item_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_original_url(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefDownloadItem_00024NativePeer_N_1GetSuggestedFileName(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_download_item_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_suggested_file_name(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefDownloadItem_00024NativePeer_N_1GetContentDisposition(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_download_item_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_content_disposition(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefDownloadItem_00024NativePeer_N_1GetMimeType(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_download_item_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_mime_type(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefDownloadItem_00024NativePeer_N_1IsPaused(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_download_item_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_paused(s);
    return static_cast<jboolean>(_r);
}
