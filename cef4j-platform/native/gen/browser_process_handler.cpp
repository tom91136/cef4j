// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
#include <jni.h>
#include "include/capi/cef_browser_process_handler_capi.h"
#include "include/capi/cef_client_capi.h"
#include "include/capi/cef_command_line_capi.h"
#include "include/capi/cef_preference_capi.h"
#include "include/capi/cef_request_context_handler_capi.h"
#include "jni_util.h"

#include <atomic>

extern "C" cef_client_t* Create_JniCefClient(JNIEnv* env, jobject handler);
extern "C" cef_request_context_handler_t* Create_JniCefRequestContextHandler(JNIEnv* env, jobject handler);

struct JniCefBrowserProcessHandler : public cef_browser_process_handler_t {
    JavaVM* jvm;
    jobject javaHandler;  // global ref
    std::atomic<int> refCount{1};

    JniCefBrowserProcessHandler(JavaVM* vm, jobject handler) : cef_browser_process_handler_t{}, jvm(vm) {
        javaHandler = handler;
        InitRefCount<JniCefBrowserProcessHandler, cef_browser_process_handler_t>(reinterpret_cast<cef_base_ref_counted_t*>(static_cast<cef_browser_process_handler_t*>(this)));
        on_register_custom_preferences = &_on_register_custom_preferences;
        on_context_initialized = &_on_context_initialized;
        on_before_child_process_launch = &_on_before_child_process_launch;
        on_already_running_app_relaunch = &_on_already_running_app_relaunch;
        on_schedule_message_pump_work = &_on_schedule_message_pump_work;
        get_default_client = &_get_default_client;
        get_default_request_context_handler = &_get_default_request_context_handler;
    }

    static void CEF_CALLBACK _on_register_custom_preferences(cef_browser_process_handler_t* self, cef_preferences_type_t type, struct _cef_preference_registrar_t* registrar) {
        auto* h = reinterpret_cast<JniCefBrowserProcessHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(11) < 0) { return; }
        auto j_type_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefPreferencesType");
        auto j_type_from = env->GetStaticMethodID(j_type_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefPreferencesType;");
        auto j_type = env->CallStaticObjectMethod(j_type_cls, j_type_from, static_cast<jlong>(type));
        cef_preference_registrar_t* _p_registrar = registrar;
        auto j_registrar_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefPreferenceRegistrar$NativePeer");
        auto j_registrar_ctor = env->GetMethodID(j_registrar_cls, "<init>", "(J)V");
        auto j_registrar = _p_registrar ? env->NewObject(j_registrar_cls, j_registrar_ctor, reinterpret_cast<jlong>(_p_registrar)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onRegisterCustomPreferences", "(Lnet/kurobako/cef4j/gen/CefPreferencesType;Lnet/kurobako/cef4j/gen/CefPreferenceRegistrar;)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, j_type, j_registrar);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        env->PopLocalFrame(nullptr);
    }

    static void CEF_CALLBACK _on_context_initialized(cef_browser_process_handler_t* self) {
        auto* h = reinterpret_cast<JniCefBrowserProcessHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(5) < 0) { return; }
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onContextInitialized", "()V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        env->PopLocalFrame(nullptr);
    }

    static void CEF_CALLBACK _on_before_child_process_launch(cef_browser_process_handler_t* self, struct _cef_command_line_t* command_line) {
        auto* h = reinterpret_cast<JniCefBrowserProcessHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) { return; }
        cef_command_line_t* _p_command_line = command_line;
        if (_p_command_line) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_command_line); _b->add_ref(_b); }
        auto j_command_line_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefCommandLine$NativePeer");
        auto j_command_line_ctor = env->GetMethodID(j_command_line_cls, "<init>", "(J)V");
        auto j_command_line = _p_command_line ? env->NewObject(j_command_line_cls, j_command_line_ctor, reinterpret_cast<jlong>(_p_command_line)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onBeforeChildProcessLaunch", "(Lnet/kurobako/cef4j/gen/CefCommandLine;)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, j_command_line);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        env->PopLocalFrame(nullptr);
    }

    static int CEF_CALLBACK _on_already_running_app_relaunch(cef_browser_process_handler_t* self, struct _cef_command_line_t* command_line, const cef_string_t* current_directory) {
        auto* h = reinterpret_cast<JniCefBrowserProcessHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(9) < 0) { return false; }
        cef_command_line_t* _p_command_line = command_line;
        if (_p_command_line) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_command_line); _b->add_ref(_b); }
        auto j_command_line_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefCommandLine$NativePeer");
        auto j_command_line_ctor = env->GetMethodID(j_command_line_cls, "<init>", "(J)V");
        auto j_command_line = _p_command_line ? env->NewObject(j_command_line_cls, j_command_line_ctor, reinterpret_cast<jlong>(_p_command_line)) : nullptr;
        auto j_current_directory = CefStringToJString(env, current_directory);
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onAlreadyRunningAppRelaunch", "(Lnet/kurobako/cef4j/gen/CefCommandLine;Ljava/lang/String;)Z");
        if (!mid) { env->PopLocalFrame(nullptr); return false; }
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, j_command_line, j_current_directory);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return false; }
        env->PopLocalFrame(nullptr);
        return jResult;
    }

    static void CEF_CALLBACK _on_schedule_message_pump_work(cef_browser_process_handler_t* self, int64_t delay_ms) {
        auto* h = reinterpret_cast<JniCefBrowserProcessHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(5) < 0) { return; }
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onScheduleMessagePumpWork", "(J)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, static_cast<jlong>(delay_ms));
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        env->PopLocalFrame(nullptr);
    }

    static cef_client_t* CEF_CALLBACK _get_default_client(cef_browser_process_handler_t* self) {
        auto* h = reinterpret_cast<JniCefBrowserProcessHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(9) < 0) { return nullptr; }
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "getDefaultClient", "()Ljava/util/Optional;");
        if (!mid) { env->PopLocalFrame(nullptr); return nullptr; }
        auto jResult = (jobject)env->CallObjectMethod(h->javaHandler, mid);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return nullptr; }
        cef_client_t* nativeResult = nullptr;
        if (jResult) {
            auto _optCls = FindClassCached(env, "java/util/Optional");
            auto _isPresentMid = env->GetMethodID(_optCls, "isPresent", "()Z");
            if (env->CallBooleanMethod(jResult, _isPresentMid) == JNI_TRUE) {
                auto _getMid = env->GetMethodID(_optCls, "get", "()Ljava/lang/Object;");
                auto _handlerObj = env->CallObjectMethod(jResult, _getMid);
                nativeResult = Create_JniCefClient(env, _handlerObj);
            }
        }
        env->PopLocalFrame(nullptr);
        return nativeResult;
    }

    static cef_request_context_handler_t* CEF_CALLBACK _get_default_request_context_handler(cef_browser_process_handler_t* self) {
        auto* h = reinterpret_cast<JniCefBrowserProcessHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(9) < 0) { return nullptr; }
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "getDefaultRequestContextHandler", "()Ljava/util/Optional;");
        if (!mid) { env->PopLocalFrame(nullptr); return nullptr; }
        auto jResult = (jobject)env->CallObjectMethod(h->javaHandler, mid);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return nullptr; }
        cef_request_context_handler_t* nativeResult = nullptr;
        if (jResult) {
            auto _optCls = FindClassCached(env, "java/util/Optional");
            auto _isPresentMid = env->GetMethodID(_optCls, "isPresent", "()Z");
            if (env->CallBooleanMethod(jResult, _isPresentMid) == JNI_TRUE) {
                auto _getMid = env->GetMethodID(_optCls, "get", "()Ljava/lang/Object;");
                auto _handlerObj = env->CallObjectMethod(jResult, _getMid);
                nativeResult = Create_JniCefRequestContextHandler(env, _handlerObj);
            }
        }
        env->PopLocalFrame(nullptr);
        return nativeResult;
    }
};

extern "C" cef_browser_process_handler_t* Create_JniCefBrowserProcessHandler(JNIEnv* env, jobject handler) {
    JavaVM* jvm;
    env->GetJavaVM(&jvm);
    auto globalRef = env->NewGlobalRef(handler);
    return reinterpret_cast<cef_browser_process_handler_t*>(new JniCefBrowserProcessHandler(jvm, globalRef));
}
