// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
#include <jni.h>
#include "include/capi/cef_request_context_handler_capi.h"
#include "include/capi/cef_request_context_capi.h"
#include "jni_util.h"

#include <atomic>
#include "jni_util.h"

struct JniCefRequestContextHandler : public cef_request_context_handler_t {
    JavaVM* jvm;
    jobject javaHandler;  // global ref
    std::atomic<int> refCount{1};

    JniCefRequestContextHandler(JavaVM* vm, jobject handler) : cef_request_context_handler_t{}, jvm(vm) {
        javaHandler = handler;
        InitRefCount<JniCefRequestContextHandler, cef_request_context_handler_t>(reinterpret_cast<cef_base_ref_counted_t*>(static_cast<cef_request_context_handler_t*>(this)));
        on_request_context_initialized = &_on_request_context_initialized;
    }

    static void CEF_CALLBACK _on_request_context_initialized(cef_request_context_handler_t* self, struct _cef_request_context_t* request_context) {
        auto* h = reinterpret_cast<JniCefRequestContextHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) { return; }
        cef_request_context_t* _p_request_context = request_context;
        if (_p_request_context) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_request_context); _b->add_ref(_b); }
        auto j_request_context_cls = env->FindClass("net/kurobako/cef4j/gen/CefRequestContext$NativePeer");
        auto j_request_context_ctor = env->GetMethodID(j_request_context_cls, "<init>", "(J)V");
        auto j_request_context = _p_request_context ? env->NewObject(j_request_context_cls, j_request_context_ctor, reinterpret_cast<jlong>(_p_request_context)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onRequestContextInitialized", "(Lnet/kurobako/cef4j/gen/CefRequestContext;)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, j_request_context);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        env->PopLocalFrame(nullptr);
    }
};

extern "C" cef_request_context_handler_t* Create_JniCefRequestContextHandler(JNIEnv* env, jobject handler) {
    JavaVM* jvm;
    env->GetJavaVM(&jvm);
    auto globalRef = env->NewGlobalRef(handler);
    return reinterpret_cast<cef_request_context_handler_t*>(new JniCefRequestContextHandler(jvm, globalRef));
}
