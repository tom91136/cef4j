// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
#include <jni.h>
#include "include/capi/cef_request_context_handler_capi.h"
#include "include/capi/cef_browser_capi.h"
#include "include/capi/cef_frame_capi.h"
#include "include/capi/cef_request_capi.h"
#include "include/capi/cef_request_context_capi.h"
#include "include/capi/cef_resource_request_handler_capi.h"
#include "jni_util.h"

#include <atomic>
#include "jni_util.h"

extern "C" cef_resource_request_handler_t* Create_JniCefResourceRequestHandler(JNIEnv* env, jobject handler);

struct JniCefRequestContextHandler : public cef_request_context_handler_t {
    JavaVM* jvm;
    jobject javaHandler;  // global ref
    std::atomic<int> refCount{1};

    JniCefRequestContextHandler(JavaVM* vm, jobject handler) : cef_request_context_handler_t{}, jvm(vm) {
        javaHandler = handler;
        InitRefCount<JniCefRequestContextHandler, cef_request_context_handler_t>(reinterpret_cast<cef_base_ref_counted_t*>(static_cast<cef_request_context_handler_t*>(this)));
        on_request_context_initialized = &_on_request_context_initialized;
        get_resource_request_handler = &_get_resource_request_handler;
    }

    static void CEF_CALLBACK _on_request_context_initialized(cef_request_context_handler_t* self, struct _cef_request_context_t* request_context) {
        auto* h = reinterpret_cast<JniCefRequestContextHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) { return; }
        cef_request_context_t* _p_request_context = request_context;
        if (_p_request_context) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_request_context); _b->add_ref(_b); }
        auto j_request_context_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefRequestContext$NativePeer");
        auto j_request_context_ctor = env->GetMethodID(j_request_context_cls, "<init>", "(J)V");
        auto j_request_context = _p_request_context ? env->NewObject(j_request_context_cls, j_request_context_ctor, reinterpret_cast<jlong>(_p_request_context)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onRequestContextInitialized", "(Lnet/kurobako/cef4j/gen/CefRequestContext;)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, j_request_context);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        env->PopLocalFrame(nullptr);
    }

    static cef_resource_request_handler_t* CEF_CALLBACK _get_resource_request_handler(cef_request_context_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, struct _cef_request_t* request, int is_navigation, int is_download, const cef_string_t* request_initiator, int* disable_default_handling) {
        auto* h = reinterpret_cast<JniCefRequestContextHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(20) < 0) { return nullptr; }
        cef_browser_t* _p_browser = browser;
        if (_p_browser) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b); }
        auto j_browser_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        cef_frame_t* _p_frame = frame;
        if (_p_frame) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_frame); _b->add_ref(_b); }
        auto j_frame_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefFrame$NativePeer");
        auto j_frame_ctor = env->GetMethodID(j_frame_cls, "<init>", "(J)V");
        auto j_frame = _p_frame ? env->NewObject(j_frame_cls, j_frame_ctor, reinterpret_cast<jlong>(_p_frame)) : nullptr;
        cef_request_t* _p_request = request;
        if (_p_request) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_request); _b->add_ref(_b); }
        auto j_request_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefRequest$NativePeer");
        auto j_request_ctor = env->GetMethodID(j_request_cls, "<init>", "(J)V");
        auto j_request = _p_request ? env->NewObject(j_request_cls, j_request_ctor, reinterpret_cast<jlong>(_p_request)) : nullptr;
        auto j_request_initiator = CefStringToJString(env, request_initiator);
        jintArray j_disable_default_handling = env->NewIntArray(1);
        if (disable_default_handling) { jint _v = *disable_default_handling; env->SetIntArrayRegion(j_disable_default_handling, 0, 1, &_v); }
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "getResourceRequestHandler", "(Lnet/kurobako/cef4j/gen/CefBrowser;Lnet/kurobako/cef4j/gen/CefFrame;Lnet/kurobako/cef4j/gen/CefRequest;ZZLjava/lang/String;[I)Ljava/util/Optional;");
        if (!mid) { env->PopLocalFrame(nullptr); return nullptr; }
        auto jResult = (jobject)env->CallObjectMethod(h->javaHandler, mid, j_browser, j_frame, j_request, static_cast<jboolean>(is_navigation), static_cast<jboolean>(is_download), j_request_initiator, j_disable_default_handling);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return nullptr; }
        cef_resource_request_handler_t* nativeResult = nullptr;
        if (jResult) {
            auto _optCls = FindClassCached(env, "java/util/Optional");
            auto _isPresentMid = env->GetMethodID(_optCls, "isPresent", "()Z");
            if (env->CallBooleanMethod(jResult, _isPresentMid) == JNI_TRUE) {
                auto _getMid = env->GetMethodID(_optCls, "get", "()Ljava/lang/Object;");
                auto _handlerObj = env->CallObjectMethod(jResult, _getMid);
                nativeResult = Create_JniCefResourceRequestHandler(env, _handlerObj);
            }
        }
        env->PopLocalFrame(nullptr);
        return nativeResult;
    }
};

extern "C" cef_request_context_handler_t* Create_JniCefRequestContextHandler(JNIEnv* env, jobject handler) {
    JavaVM* jvm;
    env->GetJavaVM(&jvm);
    auto globalRef = env->NewGlobalRef(handler);
    return reinterpret_cast<cef_request_context_handler_t*>(new JniCefRequestContextHandler(jvm, globalRef));
}
