// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
#include <jni.h>
#include "include/capi/cef_app_capi.h"
#include "include/capi/cef_browser_process_handler_capi.h"
#include "include/capi/cef_command_line_capi.h"
#include "include/capi/cef_render_process_handler_capi.h"
#include "include/capi/cef_resource_bundle_handler_capi.h"
#include "include/capi/cef_scheme_capi.h"
#include "jni_util.h"

#include <atomic>

extern "C" cef_resource_bundle_handler_t* Create_JniCefResourceBundleHandler(JNIEnv* env, jobject handler);
extern "C" cef_browser_process_handler_t* Create_JniCefBrowserProcessHandler(JNIEnv* env, jobject handler);
extern "C" cef_render_process_handler_t* Create_JniCefRenderProcessHandler(JNIEnv* env, jobject handler);

struct JniCefApp : public cef_app_t {
    JavaVM* jvm;
    jobject javaHandler;  // global ref
    std::atomic<int> refCount{1};

    JniCefApp(JavaVM* vm, jobject handler) : cef_app_t{}, jvm(vm) {
        javaHandler = handler;
        InitRefCount<JniCefApp, cef_app_t>(reinterpret_cast<cef_base_ref_counted_t*>(static_cast<cef_app_t*>(this)));
        on_before_command_line_processing = &_on_before_command_line_processing;
        on_register_custom_schemes = &_on_register_custom_schemes;
        get_resource_bundle_handler = &_get_resource_bundle_handler;
        get_browser_process_handler = &_get_browser_process_handler;
        get_render_process_handler = &_get_render_process_handler;
    }

    static void CEF_CALLBACK _on_before_command_line_processing(cef_app_t* self, const cef_string_t* process_type, struct _cef_command_line_t* command_line) {
        auto* h = reinterpret_cast<JniCefApp*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(9) < 0) { return; }
        auto j_process_type = CefStringToJString(env, process_type);
        cef_command_line_t* _p_command_line = command_line;
        if (_p_command_line) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_command_line); _b->add_ref(_b); }
        auto j_command_line_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefCommandLine$NativePeer");
        auto j_command_line_ctor = env->GetMethodID(j_command_line_cls, "<init>", "(J)V");
        auto j_command_line = _p_command_line ? env->NewObject(j_command_line_cls, j_command_line_ctor, reinterpret_cast<jlong>(_p_command_line)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onBeforeCommandLineProcessing", "(Ljava/lang/String;Lnet/kurobako/cef4j/gen/CefCommandLine;)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, j_process_type, j_command_line);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        env->PopLocalFrame(nullptr);
    }

    static void CEF_CALLBACK _on_register_custom_schemes(cef_app_t* self, struct _cef_scheme_registrar_t* registrar) {
        auto* h = reinterpret_cast<JniCefApp*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) { return; }
        cef_scheme_registrar_t* _p_registrar = registrar;
        auto j_registrar_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefSchemeRegistrar$NativePeer");
        auto j_registrar_ctor = env->GetMethodID(j_registrar_cls, "<init>", "(J)V");
        auto j_registrar = _p_registrar ? env->NewObject(j_registrar_cls, j_registrar_ctor, reinterpret_cast<jlong>(_p_registrar)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onRegisterCustomSchemes", "(Lnet/kurobako/cef4j/gen/CefSchemeRegistrar;)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, j_registrar);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        env->PopLocalFrame(nullptr);
    }

    static cef_resource_bundle_handler_t* CEF_CALLBACK _get_resource_bundle_handler(cef_app_t* self) {
        auto* h = reinterpret_cast<JniCefApp*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(9) < 0) { return nullptr; }
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "getResourceBundleHandler", "()Ljava/util/Optional;");
        if (!mid) { env->PopLocalFrame(nullptr); return nullptr; }
        auto jResult = (jobject)env->CallObjectMethod(h->javaHandler, mid);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return nullptr; }
        cef_resource_bundle_handler_t* nativeResult = nullptr;
        if (jResult) {
            auto _optCls = FindClassCached(env, "java/util/Optional");
            auto _isPresentMid = env->GetMethodID(_optCls, "isPresent", "()Z");
            if (env->CallBooleanMethod(jResult, _isPresentMid) == JNI_TRUE) {
                auto _getMid = env->GetMethodID(_optCls, "get", "()Ljava/lang/Object;");
                auto _handlerObj = env->CallObjectMethod(jResult, _getMid);
                nativeResult = Create_JniCefResourceBundleHandler(env, _handlerObj);
            }
        }
        env->PopLocalFrame(nullptr);
        return nativeResult;
    }

    static cef_browser_process_handler_t* CEF_CALLBACK _get_browser_process_handler(cef_app_t* self) {
        auto* h = reinterpret_cast<JniCefApp*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(9) < 0) { return nullptr; }
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "getBrowserProcessHandler", "()Ljava/util/Optional;");
        if (!mid) { env->PopLocalFrame(nullptr); return nullptr; }
        auto jResult = (jobject)env->CallObjectMethod(h->javaHandler, mid);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return nullptr; }
        cef_browser_process_handler_t* nativeResult = nullptr;
        if (jResult) {
            auto _optCls = FindClassCached(env, "java/util/Optional");
            auto _isPresentMid = env->GetMethodID(_optCls, "isPresent", "()Z");
            if (env->CallBooleanMethod(jResult, _isPresentMid) == JNI_TRUE) {
                auto _getMid = env->GetMethodID(_optCls, "get", "()Ljava/lang/Object;");
                auto _handlerObj = env->CallObjectMethod(jResult, _getMid);
                nativeResult = Create_JniCefBrowserProcessHandler(env, _handlerObj);
            }
        }
        env->PopLocalFrame(nullptr);
        return nativeResult;
    }

    static cef_render_process_handler_t* CEF_CALLBACK _get_render_process_handler(cef_app_t* self) {
        auto* h = reinterpret_cast<JniCefApp*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(9) < 0) { return nullptr; }
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "getRenderProcessHandler", "()Ljava/util/Optional;");
        if (!mid) { env->PopLocalFrame(nullptr); return nullptr; }
        auto jResult = (jobject)env->CallObjectMethod(h->javaHandler, mid);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return nullptr; }
        cef_render_process_handler_t* nativeResult = nullptr;
        if (jResult) {
            auto _optCls = FindClassCached(env, "java/util/Optional");
            auto _isPresentMid = env->GetMethodID(_optCls, "isPresent", "()Z");
            if (env->CallBooleanMethod(jResult, _isPresentMid) == JNI_TRUE) {
                auto _getMid = env->GetMethodID(_optCls, "get", "()Ljava/lang/Object;");
                auto _handlerObj = env->CallObjectMethod(jResult, _getMid);
                nativeResult = Create_JniCefRenderProcessHandler(env, _handlerObj);
            }
        }
        env->PopLocalFrame(nullptr);
        return nativeResult;
    }
};

extern "C" cef_app_t* Create_JniCefApp(JNIEnv* env, jobject handler) {
    JavaVM* jvm;
    env->GetJavaVM(&jvm);
    auto globalRef = env->NewGlobalRef(handler);
    return reinterpret_cast<cef_app_t*>(new JniCefApp(jvm, globalRef));
}
