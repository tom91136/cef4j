// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
#include <jni.h>
#include "include/capi/cef_download_item_capi.h"
#include "jni_util.h"

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefDownloadItem), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefDownloadItem), isValid0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_download_item_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_valid(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefDownloadItem), isInProgress0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_download_item_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_in_progress(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefDownloadItem), isComplete0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_download_item_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_complete(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefDownloadItem), isCanceled0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_download_item_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_canceled(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefDownloadItem), isInterrupted0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_download_item_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_interrupted(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefDownloadItem), getInterruptReason0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_download_item_t*>(self);
    if (!s) return 0;
    auto _r = s->get_interrupt_reason(s);
    auto _eCls = env->FindClass("net/kurobako/cef4j/gen/CefDownloadInterruptReason");
    auto _eOf = env->GetStaticMethodID(_eCls, "of", "(J)Lnet/kurobako/cef4j/gen/CefDownloadInterruptReason;");
    return env->CallStaticObjectMethod(_eCls, _eOf, static_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jlong, CEF4J_PEER(CefDownloadItem), getCurrentSpeed0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_download_item_t*>(self);
    if (!s) return 0;
    return static_cast<jlong>(s->get_current_speed(s));
}

CEF4J_JNI_EXPORT(jint, CEF4J_PEER(CefDownloadItem), getPercentComplete0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_download_item_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->get_percent_complete(s));
}

CEF4J_JNI_EXPORT(jlong, CEF4J_PEER(CefDownloadItem), getTotalBytes0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_download_item_t*>(self);
    if (!s) return 0;
    return static_cast<jlong>(s->get_total_bytes(s));
}

CEF4J_JNI_EXPORT(jlong, CEF4J_PEER(CefDownloadItem), getReceivedBytes0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_download_item_t*>(self);
    if (!s) return 0;
    return static_cast<jlong>(s->get_received_bytes(s));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefDownloadItem), getStartTime0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_download_item_t*>(self);
    if (!s) return nullptr;
    cef_basetime_t result = s->get_start_time(s);
    auto cls = env->FindClass("net/kurobako/cef4j/gen/CefBasetime");
    auto ctor = env->GetMethodID(cls, "<init>", "(J)V");
    auto _dsResult = env->NewObject(cls, ctor, static_cast<jlong>((&result)->val));
    return _dsResult;
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefDownloadItem), getEndTime0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_download_item_t*>(self);
    if (!s) return nullptr;
    cef_basetime_t result = s->get_end_time(s);
    auto cls = env->FindClass("net/kurobako/cef4j/gen/CefBasetime");
    auto ctor = env->GetMethodID(cls, "<init>", "(J)V");
    auto _dsResult = env->NewObject(cls, ctor, static_cast<jlong>((&result)->val));
    return _dsResult;
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefDownloadItem), getFullPath0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_download_item_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_full_path(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jint, CEF4J_PEER(CefDownloadItem), getId0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_download_item_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->get_id(s));
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefDownloadItem), getUrl0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_download_item_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_url(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefDownloadItem), getOriginalUrl0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_download_item_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_original_url(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefDownloadItem), getSuggestedFileName0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_download_item_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_suggested_file_name(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefDownloadItem), getContentDisposition0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_download_item_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_content_disposition(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefDownloadItem), getMimeType0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_download_item_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_mime_type(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefDownloadItem), isPaused0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_download_item_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_paused(s);
    return static_cast<jboolean>(_r);
}
