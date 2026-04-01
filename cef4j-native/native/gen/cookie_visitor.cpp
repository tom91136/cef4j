// GENERATED - do not edit.
#include <jni.h>
#include "include/capi/cef_cookie_capi.h"
#include "jni_util.h"

#include <atomic>
#include "ref_counted_base.h"

// JNI wrapper struct for cef_cookie_visitor_t
struct JniCefCookieVisitor: public cef_cookie_visitor_t {
    JavaVM *jvm;
    jobject javaHandler;  // global ref
    std::atomic<int> refCount { 1 };

    JniCefCookieVisitor(JavaVM *vm, jobject handler) : cef_cookie_visitor_t { }, jvm(vm) {
        javaHandler = handler;
        InitRefCount<JniCefCookieVisitor, cef_cookie_visitor_t> (&base);
        visit = &_visit;
    }

    static int CEF_CALLBACK _visit(cef_cookie_visitor_t* self, const struct _cef_cookie_t* cookie, int count, int total, int* deleteCookie) {
        auto* h = reinterpret_cast<JniCefCookieVisitor*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(28) < 0) {return 0;}
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
        jintArray j_deleteCookie = env->NewIntArray(1);
        if (deleteCookie) {jint _v = *deleteCookie; env->SetIntArrayRegion(j_deleteCookie, 0, 1, &_v);}
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "visit", "(Lnet/kurobako/cef4j/gen/CefCookie;II[I)I");
        if (!mid) {env->PopLocalFrame(nullptr); return 0;}
        auto jResult = env->CallIntMethod(h->javaHandler, mid, j_cookie, static_cast<jint>(count), static_cast<jint>(total), j_deleteCookie);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return 0;}
        if (deleteCookie) {jint _v; env->GetIntArrayRegion(j_deleteCookie, 0, 1, &_v); *deleteCookie = _v;}
        int nativeResult = static_cast<int>(jResult);
        env->PopLocalFrame(nullptr);
        return nativeResult;
    }
};

extern "C" cef_cookie_visitor_t* Create_JniCefCookieVisitor(JNIEnv *env, jobject handler) {
    JavaVM *jvm;
    env->GetJavaVM(&jvm);
    auto globalRef = env->NewGlobalRef(handler);
    return reinterpret_cast<cef_cookie_visitor_t*>(new JniCefCookieVisitor(jvm, globalRef));
}

extern "C" JNIEXPORT jlong JNICALL Java_net_kurobako_cef4j_gen_CefCookieVisitor_1N_N_1Create(JNIEnv* env, jobject obj) {
    return reinterpret_cast<jlong>(Create_JniCefCookieVisitor(env, obj));
}
