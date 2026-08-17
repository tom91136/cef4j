// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
#include <jni.h>
#include "include/capi/cef_navigation_entry_capi.h"
#include "include/capi/cef_ssl_status_capi.h"
#include "jni_util.h"

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefNavigationEntry), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefNavigationEntry), isValid0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_navigation_entry_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_valid(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefNavigationEntry), getUrl0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_navigation_entry_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_url(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefNavigationEntry), getDisplayUrl0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_navigation_entry_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_display_url(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefNavigationEntry), getOriginalUrl0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_navigation_entry_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_original_url(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefNavigationEntry), getTitle0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_navigation_entry_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_title(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefNavigationEntry), getTransitionType0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_navigation_entry_t*>(self);
    if (!s) return 0;
    auto _r = s->get_transition_type(s);
    auto _eCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefTransitionType");
    auto _eOf = env->GetStaticMethodID(_eCls, "of", "(J)Lnet/kurobako/cef4j/gen/CefTransitionType;");
    return env->CallStaticObjectMethod(_eCls, _eOf, static_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefNavigationEntry), hasPostData0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_navigation_entry_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->has_post_data(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefNavigationEntry), getCompletionTime0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_navigation_entry_t*>(self);
    if (!s) return nullptr;
    cef_basetime_t result = s->get_completion_time(s);
    auto cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefBasetime");
    auto ctor = env->GetMethodID(cls, "<init>", "(J)V");
    auto _dsResult = env->NewObject(cls, ctor, to_jlong(((&result))->val));
    return _dsResult;
}

CEF4J_JNI_EXPORT(jint, CEF4J_PEER(CefNavigationEntry), getHttpStatusCode0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_navigation_entry_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->get_http_status_code(s));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefNavigationEntry), getSslStatus0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_navigation_entry_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_sslstatus(s);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefSslStatus$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}
