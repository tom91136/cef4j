// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
#include <jni.h>
#include "include/capi/cef_resource_request_handler_capi.h"
#include "include/capi/cef_browser_capi.h"
#include "include/capi/cef_callback_capi.h"
#include "include/capi/cef_frame_capi.h"
#include "include/capi/cef_request_capi.h"
#include "include/capi/cef_resource_handler_capi.h"
#include "include/capi/cef_response_capi.h"
#include "include/capi/cef_response_filter_capi.h"
#include "jni_util.h"

#include <atomic>

extern "C" cef_cookie_access_filter_t* Create_JniCefCookieAccessFilter(JNIEnv* env, jobject handler);
extern "C" cef_resource_handler_t* Create_JniCefResourceHandler(JNIEnv* env, jobject handler);
extern "C" cef_response_filter_t* Create_JniCefResponseFilter(JNIEnv* env, jobject handler);

struct JniCefResourceRequestHandler : public cef_resource_request_handler_t {
    JavaVM* jvm;
    jobject javaHandler;  // global ref
    std::atomic<int> refCount{1};

    JniCefResourceRequestHandler(JavaVM* vm, jobject handler) : cef_resource_request_handler_t{}, jvm(vm) {
        javaHandler = handler;
        InitRefCount<JniCefResourceRequestHandler, cef_resource_request_handler_t>(reinterpret_cast<cef_base_ref_counted_t*>(static_cast<cef_resource_request_handler_t*>(this)));
        get_cookie_access_filter = &_get_cookie_access_filter;
        on_before_resource_load = &_on_before_resource_load;
        get_resource_handler = &_get_resource_handler;
        on_resource_redirect = &_on_resource_redirect;
        on_resource_response = &_on_resource_response;
        get_resource_response_filter = &_get_resource_response_filter;
        on_resource_load_complete = &_on_resource_load_complete;
        on_protocol_execution = &_on_protocol_execution;
    }

    static cef_cookie_access_filter_t* CEF_CALLBACK _get_cookie_access_filter(cef_resource_request_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, struct _cef_request_t* request) {
        auto* h = reinterpret_cast<JniCefResourceRequestHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(18) < 0) { return nullptr; }
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
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "getCookieAccessFilter", "(Lnet/kurobako/cef4j/gen/CefBrowser;Lnet/kurobako/cef4j/gen/CefFrame;Lnet/kurobako/cef4j/gen/CefRequest;)Ljava/util/Optional;");
        if (!mid) { env->PopLocalFrame(nullptr); return nullptr; }
        auto jResult = (jobject)env->CallObjectMethod(h->javaHandler, mid, j_browser, j_frame, j_request);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return nullptr; }
        cef_cookie_access_filter_t* nativeResult = nullptr;
        if (jResult) {
            auto _optCls = FindClassCached(env, "java/util/Optional");
            auto _isPresentMid = env->GetMethodID(_optCls, "isPresent", "()Z");
            if (env->CallBooleanMethod(jResult, _isPresentMid) == JNI_TRUE) {
                auto _getMid = env->GetMethodID(_optCls, "get", "()Ljava/lang/Object;");
                auto _handlerObj = env->CallObjectMethod(jResult, _getMid);
                nativeResult = Create_JniCefCookieAccessFilter(env, _handlerObj);
            }
        }
        env->PopLocalFrame(nullptr);
        return nativeResult;
    }

    static cef_return_value_t CEF_CALLBACK _on_before_resource_load(cef_resource_request_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, struct _cef_request_t* request, struct _cef_callback_t* callback) {
        auto* h = reinterpret_cast<JniCefResourceRequestHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(18) < 0) { return cef_return_value_t::RV_CONTINUE; }
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
        cef_callback_t* _p_callback = callback;
        if (_p_callback) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_callback); _b->add_ref(_b); }
        auto j_callback_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefCallback$NativePeer");
        auto j_callback_ctor = env->GetMethodID(j_callback_cls, "<init>", "(J)V");
        auto j_callback = _p_callback ? env->NewObject(j_callback_cls, j_callback_ctor, reinterpret_cast<jlong>(_p_callback)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onBeforeResourceLoad", "(Lnet/kurobako/cef4j/gen/CefBrowser;Lnet/kurobako/cef4j/gen/CefFrame;Lnet/kurobako/cef4j/gen/CefRequest;Lnet/kurobako/cef4j/gen/CefCallback;)Lnet/kurobako/cef4j/gen/CefReturnValue;");
        if (!mid) { env->PopLocalFrame(nullptr); return cef_return_value_t::RV_CONTINUE; }
        auto jResult = (jobject)env->CallObjectMethod(h->javaHandler, mid, j_browser, j_frame, j_request, j_callback);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return cef_return_value_t::RV_CONTINUE; }
        cef_return_value_t nativeResult = static_cast<cef_return_value_t>(jResult ? env->GetLongField(jResult, env->GetFieldID(env->GetObjectClass(jResult), "value", "J")) : 0);
        env->PopLocalFrame(nullptr);
        return nativeResult;
    }

    static cef_resource_handler_t* CEF_CALLBACK _get_resource_handler(cef_resource_request_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, struct _cef_request_t* request) {
        auto* h = reinterpret_cast<JniCefResourceRequestHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(18) < 0) { return nullptr; }
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
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "getResourceHandler", "(Lnet/kurobako/cef4j/gen/CefBrowser;Lnet/kurobako/cef4j/gen/CefFrame;Lnet/kurobako/cef4j/gen/CefRequest;)Ljava/util/Optional;");
        if (!mid) { env->PopLocalFrame(nullptr); return nullptr; }
        auto jResult = (jobject)env->CallObjectMethod(h->javaHandler, mid, j_browser, j_frame, j_request);
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

    static void CEF_CALLBACK _on_resource_redirect(cef_resource_request_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, struct _cef_request_t* request, struct _cef_response_t* response, cef_string_t* new_url) {
        auto* h = reinterpret_cast<JniCefResourceRequestHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(18) < 0) { return; }
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
        cef_response_t* _p_response = response;
        if (_p_response) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_response); _b->add_ref(_b); }
        auto j_response_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefResponse$NativePeer");
        auto j_response_ctor = env->GetMethodID(j_response_cls, "<init>", "(J)V");
        auto j_response = _p_response ? env->NewObject(j_response_cls, j_response_ctor, reinterpret_cast<jlong>(_p_response)) : nullptr;
        auto j_new_url = CefStringToJString(env, new_url);
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onResourceRedirect", "(Lnet/kurobako/cef4j/gen/CefBrowser;Lnet/kurobako/cef4j/gen/CefFrame;Lnet/kurobako/cef4j/gen/CefRequest;Lnet/kurobako/cef4j/gen/CefResponse;Ljava/lang/String;)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, j_browser, j_frame, j_request, j_response, j_new_url);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        env->PopLocalFrame(nullptr);
    }

    static int CEF_CALLBACK _on_resource_response(cef_resource_request_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, struct _cef_request_t* request, struct _cef_response_t* response) {
        auto* h = reinterpret_cast<JniCefResourceRequestHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(17) < 0) { return false; }
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
        cef_response_t* _p_response = response;
        if (_p_response) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_response); _b->add_ref(_b); }
        auto j_response_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefResponse$NativePeer");
        auto j_response_ctor = env->GetMethodID(j_response_cls, "<init>", "(J)V");
        auto j_response = _p_response ? env->NewObject(j_response_cls, j_response_ctor, reinterpret_cast<jlong>(_p_response)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onResourceResponse", "(Lnet/kurobako/cef4j/gen/CefBrowser;Lnet/kurobako/cef4j/gen/CefFrame;Lnet/kurobako/cef4j/gen/CefRequest;Lnet/kurobako/cef4j/gen/CefResponse;)Z");
        if (!mid) { env->PopLocalFrame(nullptr); return false; }
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, j_browser, j_frame, j_request, j_response);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return false; }
        env->PopLocalFrame(nullptr);
        return jResult;
    }

    static cef_response_filter_t* CEF_CALLBACK _get_resource_response_filter(cef_resource_request_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, struct _cef_request_t* request, struct _cef_response_t* response) {
        auto* h = reinterpret_cast<JniCefResourceRequestHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(21) < 0) { return nullptr; }
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
        cef_response_t* _p_response = response;
        if (_p_response) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_response); _b->add_ref(_b); }
        auto j_response_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefResponse$NativePeer");
        auto j_response_ctor = env->GetMethodID(j_response_cls, "<init>", "(J)V");
        auto j_response = _p_response ? env->NewObject(j_response_cls, j_response_ctor, reinterpret_cast<jlong>(_p_response)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "getResourceResponseFilter", "(Lnet/kurobako/cef4j/gen/CefBrowser;Lnet/kurobako/cef4j/gen/CefFrame;Lnet/kurobako/cef4j/gen/CefRequest;Lnet/kurobako/cef4j/gen/CefResponse;)Ljava/util/Optional;");
        if (!mid) { env->PopLocalFrame(nullptr); return nullptr; }
        auto jResult = (jobject)env->CallObjectMethod(h->javaHandler, mid, j_browser, j_frame, j_request, j_response);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return nullptr; }
        cef_response_filter_t* nativeResult = nullptr;
        if (jResult) {
            auto _optCls = FindClassCached(env, "java/util/Optional");
            auto _isPresentMid = env->GetMethodID(_optCls, "isPresent", "()Z");
            if (env->CallBooleanMethod(jResult, _isPresentMid) == JNI_TRUE) {
                auto _getMid = env->GetMethodID(_optCls, "get", "()Ljava/lang/Object;");
                auto _handlerObj = env->CallObjectMethod(jResult, _getMid);
                nativeResult = Create_JniCefResponseFilter(env, _handlerObj);
            }
        }
        env->PopLocalFrame(nullptr);
        return nativeResult;
    }

    static void CEF_CALLBACK _on_resource_load_complete(cef_resource_request_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, struct _cef_request_t* request, struct _cef_response_t* response, cef_urlrequest_status_t status, int64_t received_content_length) {
        auto* h = reinterpret_cast<JniCefResourceRequestHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(20) < 0) { return; }
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
        cef_response_t* _p_response = response;
        if (_p_response) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_response); _b->add_ref(_b); }
        auto j_response_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefResponse$NativePeer");
        auto j_response_ctor = env->GetMethodID(j_response_cls, "<init>", "(J)V");
        auto j_response = _p_response ? env->NewObject(j_response_cls, j_response_ctor, reinterpret_cast<jlong>(_p_response)) : nullptr;
        auto j_status_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefUrlRequestStatus");
        auto j_status_from = env->GetStaticMethodID(j_status_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefUrlRequestStatus;");
        auto j_status = env->CallStaticObjectMethod(j_status_cls, j_status_from, static_cast<jlong>(status));
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onResourceLoadComplete", "(Lnet/kurobako/cef4j/gen/CefBrowser;Lnet/kurobako/cef4j/gen/CefFrame;Lnet/kurobako/cef4j/gen/CefRequest;Lnet/kurobako/cef4j/gen/CefResponse;Lnet/kurobako/cef4j/gen/CefUrlRequestStatus;J)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, j_browser, j_frame, j_request, j_response, j_status, static_cast<jlong>(received_content_length));
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        env->PopLocalFrame(nullptr);
    }

    static void CEF_CALLBACK _on_protocol_execution(cef_resource_request_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, struct _cef_request_t* request, int* allow_os_execution) {
        auto* h = reinterpret_cast<JniCefResourceRequestHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(15) < 0) { return; }
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
        jintArray j_allow_os_execution = env->NewIntArray(1);
        if (allow_os_execution) { jint _v = *allow_os_execution; env->SetIntArrayRegion(j_allow_os_execution, 0, 1, &_v); }
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onProtocolExecution", "(Lnet/kurobako/cef4j/gen/CefBrowser;Lnet/kurobako/cef4j/gen/CefFrame;Lnet/kurobako/cef4j/gen/CefRequest;[I)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, j_browser, j_frame, j_request, j_allow_os_execution);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        if (allow_os_execution) { jint _v; env->GetIntArrayRegion(j_allow_os_execution, 0, 1, &_v); *allow_os_execution = _v; }
        env->PopLocalFrame(nullptr);
    }
};

extern "C" cef_resource_request_handler_t* Create_JniCefResourceRequestHandler(JNIEnv* env, jobject handler) {
    JavaVM* jvm;
    env->GetJavaVM(&jvm);
    auto globalRef = env->NewGlobalRef(handler);
    return reinterpret_cast<cef_resource_request_handler_t*>(new JniCefResourceRequestHandler(jvm, globalRef));
}
