// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
#include <jni.h>
#include "include/capi/cef_cookie_capi.h"
#include "jni_util.h"

#include <atomic>
#include "jni_util.h"

struct JniCefSetCookieCallback : public cef_set_cookie_callback_t {
    JavaVM* jvm;
    jobject javaHandler;  // global ref
    std::atomic<int> refCount{1};

    JniCefSetCookieCallback(JavaVM* vm, jobject handler) : cef_set_cookie_callback_t{}, jvm(vm) {
        javaHandler = handler;
        InitRefCount<JniCefSetCookieCallback, cef_set_cookie_callback_t>(reinterpret_cast<cef_base_ref_counted_t*>(static_cast<cef_set_cookie_callback_t*>(this)));
        on_complete = &_on_complete;
    }

    static void CEF_CALLBACK _on_complete(cef_set_cookie_callback_t* self, int success) {
        auto* h = reinterpret_cast<JniCefSetCookieCallback*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(5) < 0) { return; }
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onComplete", "(Z)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, static_cast<jboolean>(success));
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        env->PopLocalFrame(nullptr);
    }
};

extern "C" cef_set_cookie_callback_t* Create_JniCefSetCookieCallback(JNIEnv* env, jobject handler) {
    JavaVM* jvm;
    env->GetJavaVM(&jvm);
    auto globalRef = env->NewGlobalRef(handler);
    return reinterpret_cast<cef_set_cookie_callback_t*>(new JniCefSetCookieCallback(jvm, globalRef));
}
