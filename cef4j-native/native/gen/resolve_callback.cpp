// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
#include <jni.h>
#include "include/capi/cef_request_context_capi.h"
#include "jni_util.h"

#include <atomic>
#include "jni_util.h"

struct JniCefResolveCallback: public cef_resolve_callback_t {
    JavaVM *jvm;
    jobject javaHandler;  // global ref
    std::atomic<int> refCount { 1 };

    JniCefResolveCallback(JavaVM *vm, jobject handler) : cef_resolve_callback_t { }, jvm(vm) {
        javaHandler = handler;
        InitRefCount<JniCefResolveCallback, cef_resolve_callback_t> (&base);
        on_resolve_completed = &_on_resolve_completed;
    }

    static void CEF_CALLBACK _on_resolve_completed(cef_resolve_callback_t* self, cef_errorcode_t result, cef_string_list_t resolved_ips) {
        auto* h = reinterpret_cast<JniCefResolveCallback*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(9) < 0) {return;}
        auto j_result_cls = env->FindClass("net/kurobako/cef4j/gen/CefErrorCode");
        auto j_result_from = env->GetStaticMethodID(j_result_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefErrorCode;");
        auto j_result = env->CallStaticObjectMethod(j_result_cls, j_result_from, static_cast<jlong>(result));
        auto j_resolved_ips = CefStringListToJavaList(env, resolved_ips);
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onResolveCompleted", "(Lnet/kurobako/cef4j/gen/CefErrorCode;Ljava/util/List;)V");
        if (!mid) {env->PopLocalFrame(nullptr); return;}
        env->CallVoidMethod(h->javaHandler, mid, j_result, j_resolved_ips);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return;}
        env->PopLocalFrame(nullptr);
    }
};

extern "C" cef_resolve_callback_t* Create_JniCefResolveCallback(JNIEnv *env, jobject handler) {
    JavaVM *jvm;
    env->GetJavaVM(&jvm);
    auto globalRef = env->NewGlobalRef(handler);
    return reinterpret_cast<cef_resolve_callback_t*>(new JniCefResolveCallback(jvm, globalRef));
}
