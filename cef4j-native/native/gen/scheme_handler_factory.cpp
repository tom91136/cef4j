// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
#include <jni.h>
#include "include/capi/cef_scheme_capi.h"
#include "include/capi/cef_browser_capi.h"
#include "include/capi/cef_frame_capi.h"
#include "include/capi/cef_request_capi.h"
#include "include/capi/cef_resource_handler_capi.h"
#include "jni_util.h"

#include <atomic>
#include "jni_util.h"

extern "C" cef_resource_handler_t* Create_JniCefResourceHandler(JNIEnv* env, jobject handler);

struct JniCefSchemeHandlerFactory : public cef_scheme_handler_factory_t {
    JavaVM* jvm;
    jobject javaHandler;  // global ref
    std::atomic<int> refCount{1};

    JniCefSchemeHandlerFactory(JavaVM* vm, jobject handler) : cef_scheme_handler_factory_t{}, jvm(vm) {
        javaHandler = handler;
        InitRefCount<JniCefSchemeHandlerFactory, cef_scheme_handler_factory_t>(reinterpret_cast<cef_base_ref_counted_t*>(static_cast<cef_scheme_handler_factory_t*>(this)));
        create = &_create;
    }

    static cef_resource_handler_t* CEF_CALLBACK _create(cef_scheme_handler_factory_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, const cef_string_t* scheme_name, struct _cef_request_t* request) {
        auto* h = reinterpret_cast<JniCefSchemeHandlerFactory*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(19) < 0) { return nullptr; }
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
        auto j_scheme_name = CefStringToJString(env, scheme_name);
        cef_request_t* _p_request = request;
        if (_p_request) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_request); _b->add_ref(_b); }
        auto j_request_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefRequest$NativePeer");
        auto j_request_ctor = env->GetMethodID(j_request_cls, "<init>", "(J)V");
        auto j_request = _p_request ? env->NewObject(j_request_cls, j_request_ctor, reinterpret_cast<jlong>(_p_request)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "create", "(Lnet/kurobako/cef4j/gen/CefBrowser;Lnet/kurobako/cef4j/gen/CefFrame;Ljava/lang/String;Lnet/kurobako/cef4j/gen/CefRequest;)Ljava/util/Optional;");
        if (!mid) { env->PopLocalFrame(nullptr); return nullptr; }
        auto jResult = (jobject)env->CallObjectMethod(h->javaHandler, mid, j_browser, j_frame, j_scheme_name, j_request);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return nullptr; }
        cef_resource_handler_t* nativeResult = nullptr;
        if (jResult) {
            auto _optCls = FindClassCached(env, "java/util/Optional");
            auto _isPresentMid = env->GetMethodID(_optCls, "isPresent", "()Z");
            if (env->CallBooleanMethod(jResult, _isPresentMid) == JNI_TRUE) {
                auto _getMid = env->GetMethodID(_optCls, "get", "()Ljava/lang/Object;");
                auto _handlerObj = env->CallObjectMethod(jResult, _getMid);
                nativeResult = Create_JniCefResourceHandler(env, _handlerObj);
            }
        }
        env->PopLocalFrame(nullptr);
        return nativeResult;
    }
};

extern "C" cef_scheme_handler_factory_t* Create_JniCefSchemeHandlerFactory(JNIEnv* env, jobject handler) {
    JavaVM* jvm;
    env->GetJavaVM(&jvm);
    auto globalRef = env->NewGlobalRef(handler);
    return reinterpret_cast<cef_scheme_handler_factory_t*>(new JniCefSchemeHandlerFactory(jvm, globalRef));
}
