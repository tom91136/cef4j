// GENERATED - do not edit.
#include <jni.h>
#include "include/capi/cef_resource_request_handler_capi.h"
#include "include/capi/cef_browser_capi.h"
#include "include/capi/cef_frame_capi.h"
#include "include/capi/cef_request_capi.h"
#include "include/capi/cef_response_capi.h"
#include "jni_util.h"

#include <atomic>
#include "ref_counted_base.h"

// JNI wrapper struct for cef_cookie_access_filter_t
struct JniCefCookieAccessFilter: public cef_cookie_access_filter_t {
    JavaVM *jvm;
    jobject javaHandler;  // global ref
    std::atomic<int> refCount { 1 };

    JniCefCookieAccessFilter(JavaVM *vm, jobject handler) : cef_cookie_access_filter_t { }, jvm(vm) {
        javaHandler = handler;
        InitRefCount<JniCefCookieAccessFilter, cef_cookie_access_filter_t> (&base);
        can_send_cookie = &_can_send_cookie;
        can_save_cookie = &_can_save_cookie;
    }

    static int CEF_CALLBACK _can_send_cookie(cef_cookie_access_filter_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, struct _cef_request_t* request, const struct _cef_cookie_t* cookie) {
        auto* h = reinterpret_cast<JniCefCookieAccessFilter*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(36) < 0) {return false;}
        cef_browser_t* _p_browser = browser;
        if (_p_browser) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b);}
        auto j_browser_cls = env->FindClass("net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        cef_frame_t* _p_frame = frame;
        if (_p_frame) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_frame); _b->add_ref(_b);}
        auto j_frame_cls = env->FindClass("net/kurobako/cef4j/gen/CefFrame$NativePeer");
        auto j_frame_ctor = env->GetMethodID(j_frame_cls, "<init>", "(J)V");
        auto j_frame = _p_frame ? env->NewObject(j_frame_cls, j_frame_ctor, reinterpret_cast<jlong>(_p_frame)) : nullptr;
        cef_request_t* _p_request = request;
        if (_p_request) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_request); _b->add_ref(_b);}
        auto j_request_cls = env->FindClass("net/kurobako/cef4j/gen/CefRequest$NativePeer");
        auto j_request_ctor = env->GetMethodID(j_request_cls, "<init>", "(J)V");
        auto j_request = _p_request ? env->NewObject(j_request_cls, j_request_ctor, reinterpret_cast<jlong>(_p_request)) : nullptr;
        auto _bv_cookie_name = CefStringToJString(env, &cookie->name);
        auto _bv_cookie_value = CefStringToJString(env, &cookie->value);
        auto _bv_cookie_domain = CefStringToJString(env, &cookie->domain);
        auto _bv_cookie_path = CefStringToJString(env, &cookie->path);
        auto _bv_cookie_creation_cls = env->FindClass("net/kurobako/cef4j/gen/CefBasetime");
        auto _bv_cookie_creation_ctor = env->GetMethodID(_bv_cookie_creation_cls, "<init>", "(J)V");
        auto _bv_cookie_creation = env->NewObject(_bv_cookie_creation_cls, _bv_cookie_creation_ctor, static_cast<jlong>(cookie->creation.val));
        auto _bv_cookie_last_access_cls = env->FindClass("net/kurobako/cef4j/gen/CefBasetime");
        auto _bv_cookie_last_access_ctor = env->GetMethodID(_bv_cookie_last_access_cls, "<init>", "(J)V");
        auto _bv_cookie_last_access = env->NewObject(_bv_cookie_last_access_cls, _bv_cookie_last_access_ctor, static_cast<jlong>(cookie->last_access.val));
        auto _bv_cookie_expires_cls = env->FindClass("net/kurobako/cef4j/gen/CefBasetime");
        auto _bv_cookie_expires_ctor = env->GetMethodID(_bv_cookie_expires_cls, "<init>", "(J)V");
        auto _bv_cookie_expires = env->NewObject(_bv_cookie_expires_cls, _bv_cookie_expires_ctor, static_cast<jlong>(cookie->expires.val));
        auto _bv_cookie_same_site_cls = env->FindClass("net/kurobako/cef4j/gen/CefCookieSameSite");
        auto _bv_cookie_same_site_of = env->GetStaticMethodID(_bv_cookie_same_site_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefCookieSameSite;");
        auto _bv_cookie_same_site = env->CallStaticObjectMethod(_bv_cookie_same_site_cls, _bv_cookie_same_site_of, static_cast<jlong>(cookie->same_site));
        auto _bv_cookie_priority_cls = env->FindClass("net/kurobako/cef4j/gen/CefCookiePriority");
        auto _bv_cookie_priority_of = env->GetStaticMethodID(_bv_cookie_priority_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefCookiePriority;");
        auto _bv_cookie_priority = env->CallStaticObjectMethod(_bv_cookie_priority_cls, _bv_cookie_priority_of, static_cast<jlong>(cookie->priority));
        auto j_cookie_cls = env->FindClass("net/kurobako/cef4j/gen/CefCookie");
        auto j_cookie_ctor = env->GetMethodID(j_cookie_cls, "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IILnet/kurobako/cef4j/gen/CefBasetime;Lnet/kurobako/cef4j/gen/CefBasetime;ILnet/kurobako/cef4j/gen/CefBasetime;Lnet/kurobako/cef4j/gen/CefCookieSameSite;Lnet/kurobako/cef4j/gen/CefCookiePriority;)V");
        auto j_cookie = cookie ? env->NewObject(j_cookie_cls, j_cookie_ctor, _bv_cookie_name, _bv_cookie_value, _bv_cookie_domain, _bv_cookie_path, static_cast<jint>(cookie->secure), static_cast<jint>(cookie->httponly), _bv_cookie_creation, _bv_cookie_last_access, static_cast<jint>(cookie->has_expires), _bv_cookie_expires, _bv_cookie_same_site, _bv_cookie_priority) : nullptr;
        if (j_cookie) env->SetLongField(j_cookie, env->GetFieldID(j_cookie_cls, "size", "J"), static_cast<jlong>(cookie->size));
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "canSendCookie", "(Lnet/kurobako/cef4j/gen/CefBrowser;Lnet/kurobako/cef4j/gen/CefFrame;Lnet/kurobako/cef4j/gen/CefRequest;Lnet/kurobako/cef4j/gen/CefCookie;)Z");
        if (!mid) {env->PopLocalFrame(nullptr); return false;}
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, j_browser, j_frame, j_request, j_cookie);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return false;}
        env->PopLocalFrame(nullptr);
        return jResult;
    }

    static int CEF_CALLBACK _can_save_cookie(cef_cookie_access_filter_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, struct _cef_request_t* request, struct _cef_response_t* response, const struct _cef_cookie_t* cookie) {
        auto* h = reinterpret_cast<JniCefCookieAccessFilter*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(39) < 0) {return false;}
        cef_browser_t* _p_browser = browser;
        if (_p_browser) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b);}
        auto j_browser_cls = env->FindClass("net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        cef_frame_t* _p_frame = frame;
        if (_p_frame) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_frame); _b->add_ref(_b);}
        auto j_frame_cls = env->FindClass("net/kurobako/cef4j/gen/CefFrame$NativePeer");
        auto j_frame_ctor = env->GetMethodID(j_frame_cls, "<init>", "(J)V");
        auto j_frame = _p_frame ? env->NewObject(j_frame_cls, j_frame_ctor, reinterpret_cast<jlong>(_p_frame)) : nullptr;
        cef_request_t* _p_request = request;
        if (_p_request) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_request); _b->add_ref(_b);}
        auto j_request_cls = env->FindClass("net/kurobako/cef4j/gen/CefRequest$NativePeer");
        auto j_request_ctor = env->GetMethodID(j_request_cls, "<init>", "(J)V");
        auto j_request = _p_request ? env->NewObject(j_request_cls, j_request_ctor, reinterpret_cast<jlong>(_p_request)) : nullptr;
        cef_response_t* _p_response = response;
        if (_p_response) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_response); _b->add_ref(_b);}
        auto j_response_cls = env->FindClass("net/kurobako/cef4j/gen/CefResponse$NativePeer");
        auto j_response_ctor = env->GetMethodID(j_response_cls, "<init>", "(J)V");
        auto j_response = _p_response ? env->NewObject(j_response_cls, j_response_ctor, reinterpret_cast<jlong>(_p_response)) : nullptr;
        auto _bv_cookie_name = CefStringToJString(env, &cookie->name);
        auto _bv_cookie_value = CefStringToJString(env, &cookie->value);
        auto _bv_cookie_domain = CefStringToJString(env, &cookie->domain);
        auto _bv_cookie_path = CefStringToJString(env, &cookie->path);
        auto _bv_cookie_creation_cls = env->FindClass("net/kurobako/cef4j/gen/CefBasetime");
        auto _bv_cookie_creation_ctor = env->GetMethodID(_bv_cookie_creation_cls, "<init>", "(J)V");
        auto _bv_cookie_creation = env->NewObject(_bv_cookie_creation_cls, _bv_cookie_creation_ctor, static_cast<jlong>(cookie->creation.val));
        auto _bv_cookie_last_access_cls = env->FindClass("net/kurobako/cef4j/gen/CefBasetime");
        auto _bv_cookie_last_access_ctor = env->GetMethodID(_bv_cookie_last_access_cls, "<init>", "(J)V");
        auto _bv_cookie_last_access = env->NewObject(_bv_cookie_last_access_cls, _bv_cookie_last_access_ctor, static_cast<jlong>(cookie->last_access.val));
        auto _bv_cookie_expires_cls = env->FindClass("net/kurobako/cef4j/gen/CefBasetime");
        auto _bv_cookie_expires_ctor = env->GetMethodID(_bv_cookie_expires_cls, "<init>", "(J)V");
        auto _bv_cookie_expires = env->NewObject(_bv_cookie_expires_cls, _bv_cookie_expires_ctor, static_cast<jlong>(cookie->expires.val));
        auto _bv_cookie_same_site_cls = env->FindClass("net/kurobako/cef4j/gen/CefCookieSameSite");
        auto _bv_cookie_same_site_of = env->GetStaticMethodID(_bv_cookie_same_site_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefCookieSameSite;");
        auto _bv_cookie_same_site = env->CallStaticObjectMethod(_bv_cookie_same_site_cls, _bv_cookie_same_site_of, static_cast<jlong>(cookie->same_site));
        auto _bv_cookie_priority_cls = env->FindClass("net/kurobako/cef4j/gen/CefCookiePriority");
        auto _bv_cookie_priority_of = env->GetStaticMethodID(_bv_cookie_priority_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefCookiePriority;");
        auto _bv_cookie_priority = env->CallStaticObjectMethod(_bv_cookie_priority_cls, _bv_cookie_priority_of, static_cast<jlong>(cookie->priority));
        auto j_cookie_cls = env->FindClass("net/kurobako/cef4j/gen/CefCookie");
        auto j_cookie_ctor = env->GetMethodID(j_cookie_cls, "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IILnet/kurobako/cef4j/gen/CefBasetime;Lnet/kurobako/cef4j/gen/CefBasetime;ILnet/kurobako/cef4j/gen/CefBasetime;Lnet/kurobako/cef4j/gen/CefCookieSameSite;Lnet/kurobako/cef4j/gen/CefCookiePriority;)V");
        auto j_cookie = cookie ? env->NewObject(j_cookie_cls, j_cookie_ctor, _bv_cookie_name, _bv_cookie_value, _bv_cookie_domain, _bv_cookie_path, static_cast<jint>(cookie->secure), static_cast<jint>(cookie->httponly), _bv_cookie_creation, _bv_cookie_last_access, static_cast<jint>(cookie->has_expires), _bv_cookie_expires, _bv_cookie_same_site, _bv_cookie_priority) : nullptr;
        if (j_cookie) env->SetLongField(j_cookie, env->GetFieldID(j_cookie_cls, "size", "J"), static_cast<jlong>(cookie->size));
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "canSaveCookie", "(Lnet/kurobako/cef4j/gen/CefBrowser;Lnet/kurobako/cef4j/gen/CefFrame;Lnet/kurobako/cef4j/gen/CefRequest;Lnet/kurobako/cef4j/gen/CefResponse;Lnet/kurobako/cef4j/gen/CefCookie;)Z");
        if (!mid) {env->PopLocalFrame(nullptr); return false;}
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, j_browser, j_frame, j_request, j_response, j_cookie);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return false;}
        env->PopLocalFrame(nullptr);
        return jResult;
    }
};

extern "C" cef_cookie_access_filter_t* Create_JniCefCookieAccessFilter(JNIEnv *env, jobject handler) {
    JavaVM *jvm;
    env->GetJavaVM(&jvm);
    auto globalRef = env->NewGlobalRef(handler);
    return reinterpret_cast<cef_cookie_access_filter_t*>(new JniCefCookieAccessFilter(jvm, globalRef));
}

extern "C" JNIEXPORT jlong JNICALL Java_net_kurobako_cef4j_gen_CefCookieAccessFilter_1N_N_1Create(JNIEnv* env, jobject obj) {
    return reinterpret_cast<jlong>(Create_JniCefCookieAccessFilter(env, obj));
}
