// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
#include <jni.h>
#include "include/capi/cef_cookie_capi.h"
#include "jni_util.h"

#include <atomic>
#include "jni_util.h"

struct JniCefDeleteCookiesCallback : public cef_delete_cookies_callback_t {
    JavaVM* jvm;
    jobject javaHandler;  // global ref
    std::atomic<int> refCount{1};

    JniCefDeleteCookiesCallback(JavaVM* vm, jobject handler) : cef_delete_cookies_callback_t{}, jvm(vm) {
        javaHandler = handler;
        InitRefCount<JniCefDeleteCookiesCallback, cef_delete_cookies_callback_t>(reinterpret_cast<cef_base_ref_counted_t*>(static_cast<cef_delete_cookies_callback_t*>(this)));
        on_complete = &_on_complete;
    }

    static void CEF_CALLBACK _on_complete(cef_delete_cookies_callback_t* self, int num_deleted) {
        auto* h = reinterpret_cast<JniCefDeleteCookiesCallback*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(5) < 0) { return; }
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onComplete", "(I)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, static_cast<jint>(num_deleted));
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        env->PopLocalFrame(nullptr);
    }
};

extern "C" cef_delete_cookies_callback_t* Create_JniCefDeleteCookiesCallback(JNIEnv* env, jobject handler) {
    JavaVM* jvm;
    env->GetJavaVM(&jvm);
    auto globalRef = env->NewGlobalRef(handler);
    return reinterpret_cast<cef_delete_cookies_callback_t*>(new JniCefDeleteCookiesCallback(jvm, globalRef));
}
