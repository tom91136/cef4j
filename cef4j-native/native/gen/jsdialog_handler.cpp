// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
#include <jni.h>
#include "include/capi/cef_jsdialog_handler_capi.h"
#include "include/capi/cef_browser_capi.h"
#include "jni_util.h"

#include <atomic>
#include "jni_util.h"

struct JniCefJsDialogHandler : public cef_jsdialog_handler_t {
    JavaVM* jvm;
    jobject javaHandler;  // global ref
    std::atomic<int> refCount{1};

    JniCefJsDialogHandler(JavaVM* vm, jobject handler) : cef_jsdialog_handler_t{}, jvm(vm) {
        javaHandler = handler;
        InitRefCount<JniCefJsDialogHandler, cef_jsdialog_handler_t>(reinterpret_cast<cef_base_ref_counted_t*>(static_cast<cef_jsdialog_handler_t*>(this)));
        on_jsdialog = &_on_jsdialog;
        on_before_unload_dialog = &_on_before_unload_dialog;
        on_reset_dialog_state = &_on_reset_dialog_state;
        on_dialog_closed = &_on_dialog_closed;
    }

    static int CEF_CALLBACK _on_jsdialog(cef_jsdialog_handler_t* self, struct _cef_browser_t* browser, const cef_string_t* origin_url, cef_jsdialog_type_t dialog_type, const cef_string_t* message_text, const cef_string_t* default_prompt_text, struct _cef_jsdialog_callback_t* callback, int* suppress_message) {
        auto* h = reinterpret_cast<JniCefJsDialogHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(18) < 0) { return false; }
        cef_browser_t* _p_browser = browser;
        if (_p_browser) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b); }
        auto j_browser_cls = env->FindClass("net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        auto j_origin_url = CefStringToJString(env, origin_url);
        auto j_dialog_type_cls = env->FindClass("net/kurobako/cef4j/gen/CefJsDialogType");
        auto j_dialog_type_from = env->GetStaticMethodID(j_dialog_type_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefJsDialogType;");
        auto j_dialog_type = env->CallStaticObjectMethod(j_dialog_type_cls, j_dialog_type_from, static_cast<jlong>(dialog_type));
        auto j_message_text = CefStringToJString(env, message_text);
        auto j_default_prompt_text = CefStringToJString(env, default_prompt_text);
        cef_jsdialog_callback_t* _p_callback = callback;
        if (_p_callback) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_callback); _b->add_ref(_b); }
        auto j_callback_cls = env->FindClass("net/kurobako/cef4j/gen/CefJsDialogCallback$NativePeer");
        auto j_callback_ctor = env->GetMethodID(j_callback_cls, "<init>", "(J)V");
        auto j_callback = _p_callback ? env->NewObject(j_callback_cls, j_callback_ctor, reinterpret_cast<jlong>(_p_callback)) : nullptr;
        jintArray j_suppress_message = env->NewIntArray(1);
        if (suppress_message) { jint _v = *suppress_message; env->SetIntArrayRegion(j_suppress_message, 0, 1, &_v); }
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onJsDialog", "(Lnet/kurobako/cef4j/gen/CefBrowser;Ljava/lang/String;Lnet/kurobako/cef4j/gen/CefJsDialogType;Ljava/lang/String;Ljava/lang/String;Lnet/kurobako/cef4j/gen/CefJsDialogCallback;[I)Z");
        if (!mid) { env->PopLocalFrame(nullptr); return false; }
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, j_browser, j_origin_url, j_dialog_type, j_message_text, j_default_prompt_text, j_callback, j_suppress_message);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return false; }
        if (suppress_message) { jint _v; env->GetIntArrayRegion(j_suppress_message, 0, 1, &_v); *suppress_message = _v; }
        env->PopLocalFrame(nullptr);
        return jResult;
    }

    static int CEF_CALLBACK _on_before_unload_dialog(cef_jsdialog_handler_t* self, struct _cef_browser_t* browser, const cef_string_t* message_text, int is_reload, struct _cef_jsdialog_callback_t* callback) {
        auto* h = reinterpret_cast<JniCefJsDialogHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(12) < 0) { return false; }
        cef_browser_t* _p_browser = browser;
        if (_p_browser) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b); }
        auto j_browser_cls = env->FindClass("net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        auto j_message_text = CefStringToJString(env, message_text);
        cef_jsdialog_callback_t* _p_callback = callback;
        if (_p_callback) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_callback); _b->add_ref(_b); }
        auto j_callback_cls = env->FindClass("net/kurobako/cef4j/gen/CefJsDialogCallback$NativePeer");
        auto j_callback_ctor = env->GetMethodID(j_callback_cls, "<init>", "(J)V");
        auto j_callback = _p_callback ? env->NewObject(j_callback_cls, j_callback_ctor, reinterpret_cast<jlong>(_p_callback)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onBeforeUnloadDialog", "(Lnet/kurobako/cef4j/gen/CefBrowser;Ljava/lang/String;ZLnet/kurobako/cef4j/gen/CefJsDialogCallback;)Z");
        if (!mid) { env->PopLocalFrame(nullptr); return false; }
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, j_browser, j_message_text, static_cast<jboolean>(is_reload), j_callback);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return false; }
        env->PopLocalFrame(nullptr);
        return jResult;
    }

    static void CEF_CALLBACK _on_reset_dialog_state(cef_jsdialog_handler_t* self, struct _cef_browser_t* browser) {
        auto* h = reinterpret_cast<JniCefJsDialogHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) { return; }
        cef_browser_t* _p_browser = browser;
        if (_p_browser) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b); }
        auto j_browser_cls = env->FindClass("net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onResetDialogState", "(Lnet/kurobako/cef4j/gen/CefBrowser;)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, j_browser);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        env->PopLocalFrame(nullptr);
    }

    static void CEF_CALLBACK _on_dialog_closed(cef_jsdialog_handler_t* self, struct _cef_browser_t* browser) {
        auto* h = reinterpret_cast<JniCefJsDialogHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) { return; }
        cef_browser_t* _p_browser = browser;
        if (_p_browser) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b); }
        auto j_browser_cls = env->FindClass("net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onDialogClosed", "(Lnet/kurobako/cef4j/gen/CefBrowser;)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, j_browser);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        env->PopLocalFrame(nullptr);
    }
};

extern "C" cef_jsdialog_handler_t* Create_JniCefJsDialogHandler(JNIEnv* env, jobject handler) {
    JavaVM* jvm;
    env->GetJavaVM(&jvm);
    auto globalRef = env->NewGlobalRef(handler);
    return reinterpret_cast<cef_jsdialog_handler_t*>(new JniCefJsDialogHandler(jvm, globalRef));
}
