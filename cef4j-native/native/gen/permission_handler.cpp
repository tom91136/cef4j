// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
#include <jni.h>
#include "include/capi/cef_permission_handler_capi.h"
#include "include/capi/cef_browser_capi.h"
#include "include/capi/cef_frame_capi.h"
#include "jni_util.h"

#include <atomic>
#include "jni_util.h"

struct JniCefPermissionHandler : public cef_permission_handler_t {
    JavaVM* jvm;
    jobject javaHandler;  // global ref
    std::atomic<int> refCount{1};

    JniCefPermissionHandler(JavaVM* vm, jobject handler) : cef_permission_handler_t{}, jvm(vm) {
        javaHandler = handler;
        InitRefCount<JniCefPermissionHandler, cef_permission_handler_t>(reinterpret_cast<cef_base_ref_counted_t*>(static_cast<cef_permission_handler_t*>(this)));
        on_request_media_access_permission = &_on_request_media_access_permission;
        on_show_permission_prompt = &_on_show_permission_prompt;
        on_dismiss_permission_prompt = &_on_dismiss_permission_prompt;
    }

    static int CEF_CALLBACK _on_request_media_access_permission(cef_permission_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, const cef_string_t* requesting_origin, uint32_t requested_permissions, struct _cef_media_access_callback_t* callback) {
        auto* h = reinterpret_cast<JniCefPermissionHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(15) < 0) { return false; }
        cef_browser_t* _p_browser = browser;
        if (_p_browser) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b); }
        auto j_browser_cls = env->FindClass("net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        cef_frame_t* _p_frame = frame;
        if (_p_frame) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_frame); _b->add_ref(_b); }
        auto j_frame_cls = env->FindClass("net/kurobako/cef4j/gen/CefFrame$NativePeer");
        auto j_frame_ctor = env->GetMethodID(j_frame_cls, "<init>", "(J)V");
        auto j_frame = _p_frame ? env->NewObject(j_frame_cls, j_frame_ctor, reinterpret_cast<jlong>(_p_frame)) : nullptr;
        auto j_requesting_origin = CefStringToJString(env, requesting_origin);
        cef_media_access_callback_t* _p_callback = callback;
        if (_p_callback) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_callback); _b->add_ref(_b); }
        auto j_callback_cls = env->FindClass("net/kurobako/cef4j/gen/CefMediaAccessCallback$NativePeer");
        auto j_callback_ctor = env->GetMethodID(j_callback_cls, "<init>", "(J)V");
        auto j_callback = _p_callback ? env->NewObject(j_callback_cls, j_callback_ctor, reinterpret_cast<jlong>(_p_callback)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onRequestMediaAccessPermission", "(Lnet/kurobako/cef4j/gen/CefBrowser;Lnet/kurobako/cef4j/gen/CefFrame;Ljava/lang/String;ILnet/kurobako/cef4j/gen/CefMediaAccessCallback;)Z");
        if (!mid) { env->PopLocalFrame(nullptr); return false; }
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, j_browser, j_frame, j_requesting_origin, static_cast<jint>(requested_permissions), j_callback);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return false; }
        env->PopLocalFrame(nullptr);
        return jResult;
    }

    static int CEF_CALLBACK _on_show_permission_prompt(cef_permission_handler_t* self, struct _cef_browser_t* browser, uint64_t prompt_id, const cef_string_t* requesting_origin, uint32_t requested_permissions, struct _cef_permission_prompt_callback_t* callback) {
        auto* h = reinterpret_cast<JniCefPermissionHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(12) < 0) { return false; }
        cef_browser_t* _p_browser = browser;
        if (_p_browser) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b); }
        auto j_browser_cls = env->FindClass("net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        auto j_requesting_origin = CefStringToJString(env, requesting_origin);
        cef_permission_prompt_callback_t* _p_callback = callback;
        if (_p_callback) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_callback); _b->add_ref(_b); }
        auto j_callback_cls = env->FindClass("net/kurobako/cef4j/gen/CefPermissionPromptCallback$NativePeer");
        auto j_callback_ctor = env->GetMethodID(j_callback_cls, "<init>", "(J)V");
        auto j_callback = _p_callback ? env->NewObject(j_callback_cls, j_callback_ctor, reinterpret_cast<jlong>(_p_callback)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onShowPermissionPrompt", "(Lnet/kurobako/cef4j/gen/CefBrowser;JLjava/lang/String;ILnet/kurobako/cef4j/gen/CefPermissionPromptCallback;)Z");
        if (!mid) { env->PopLocalFrame(nullptr); return false; }
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, j_browser, static_cast<jlong>(prompt_id), j_requesting_origin, static_cast<jint>(requested_permissions), j_callback);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return false; }
        env->PopLocalFrame(nullptr);
        return jResult;
    }

    static void CEF_CALLBACK _on_dismiss_permission_prompt(cef_permission_handler_t* self, struct _cef_browser_t* browser, uint64_t prompt_id, cef_permission_request_result_t result) {
        auto* h = reinterpret_cast<JniCefPermissionHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(11) < 0) { return; }
        cef_browser_t* _p_browser = browser;
        if (_p_browser) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b); }
        auto j_browser_cls = env->FindClass("net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        auto j_result_cls = env->FindClass("net/kurobako/cef4j/gen/CefPermissionRequestResult");
        auto j_result_from = env->GetStaticMethodID(j_result_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefPermissionRequestResult;");
        auto j_result = env->CallStaticObjectMethod(j_result_cls, j_result_from, static_cast<jlong>(result));
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onDismissPermissionPrompt", "(Lnet/kurobako/cef4j/gen/CefBrowser;JLnet/kurobako/cef4j/gen/CefPermissionRequestResult;)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, j_browser, static_cast<jlong>(prompt_id), j_result);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        env->PopLocalFrame(nullptr);
    }
};

extern "C" cef_permission_handler_t* Create_JniCefPermissionHandler(JNIEnv* env, jobject handler) {
    JavaVM* jvm;
    env->GetJavaVM(&jvm);
    auto globalRef = env->NewGlobalRef(handler);
    return reinterpret_cast<cef_permission_handler_t*>(new JniCefPermissionHandler(jvm, globalRef));
}
