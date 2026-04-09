// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
#include <jni.h>
#include "include/capi/cef_command_handler_capi.h"
#include "include/capi/cef_browser_capi.h"
#include "jni_util.h"

#include <atomic>
#include "jni_util.h"

struct JniCefCommandHandler : public cef_command_handler_t {
    JavaVM* jvm;
    jobject javaHandler;  // global ref
    std::atomic<int> refCount{1};

    JniCefCommandHandler(JavaVM* vm, jobject handler) : cef_command_handler_t{}, jvm(vm) {
        javaHandler = handler;
        InitRefCount<JniCefCommandHandler, cef_command_handler_t>(reinterpret_cast<cef_base_ref_counted_t*>(static_cast<cef_command_handler_t*>(this)));
        on_chrome_command = &_on_chrome_command;
        is_chrome_app_menu_item_visible = &_is_chrome_app_menu_item_visible;
        is_chrome_app_menu_item_enabled = &_is_chrome_app_menu_item_enabled;
        is_chrome_page_action_icon_visible = &_is_chrome_page_action_icon_visible;
        is_chrome_toolbar_button_visible = &_is_chrome_toolbar_button_visible;
    }

    static int CEF_CALLBACK _on_chrome_command(cef_command_handler_t* self, struct _cef_browser_t* browser, int command_id, cef_window_open_disposition_t disposition) {
        auto* h = reinterpret_cast<JniCefCommandHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(11) < 0) { return false; }
        cef_browser_t* _p_browser = browser;
        if (_p_browser) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b); }
        auto j_browser_cls = env->FindClass("net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        auto j_disposition_cls = env->FindClass("net/kurobako/cef4j/gen/CefWindowOpenDisposition");
        auto j_disposition_from = env->GetStaticMethodID(j_disposition_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefWindowOpenDisposition;");
        auto j_disposition = env->CallStaticObjectMethod(j_disposition_cls, j_disposition_from, static_cast<jlong>(disposition));
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onChromeCommand", "(Lnet/kurobako/cef4j/gen/CefBrowser;ILnet/kurobako/cef4j/gen/CefWindowOpenDisposition;)Z");
        if (!mid) { env->PopLocalFrame(nullptr); return false; }
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, j_browser, static_cast<jint>(command_id), j_disposition);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return false; }
        env->PopLocalFrame(nullptr);
        return jResult;
    }

    static int CEF_CALLBACK _is_chrome_app_menu_item_visible(cef_command_handler_t* self, struct _cef_browser_t* browser, int command_id) {
        auto* h = reinterpret_cast<JniCefCommandHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) { return false; }
        cef_browser_t* _p_browser = browser;
        if (_p_browser) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b); }
        auto j_browser_cls = env->FindClass("net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "isChromeAppMenuItemVisible", "(Lnet/kurobako/cef4j/gen/CefBrowser;I)Z");
        if (!mid) { env->PopLocalFrame(nullptr); return false; }
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, j_browser, static_cast<jint>(command_id));
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return false; }
        env->PopLocalFrame(nullptr);
        return jResult;
    }

    static int CEF_CALLBACK _is_chrome_app_menu_item_enabled(cef_command_handler_t* self, struct _cef_browser_t* browser, int command_id) {
        auto* h = reinterpret_cast<JniCefCommandHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) { return false; }
        cef_browser_t* _p_browser = browser;
        if (_p_browser) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b); }
        auto j_browser_cls = env->FindClass("net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "isChromeAppMenuItemEnabled", "(Lnet/kurobako/cef4j/gen/CefBrowser;I)Z");
        if (!mid) { env->PopLocalFrame(nullptr); return false; }
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, j_browser, static_cast<jint>(command_id));
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return false; }
        env->PopLocalFrame(nullptr);
        return jResult;
    }

    static int CEF_CALLBACK _is_chrome_page_action_icon_visible(cef_command_handler_t* self, cef_chrome_page_action_icon_type_t icon_type) {
        auto* h = reinterpret_cast<JniCefCommandHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) { return false; }
        auto j_icon_type_cls = env->FindClass("net/kurobako/cef4j/gen/CefChromePageActionIconType");
        auto j_icon_type_from = env->GetStaticMethodID(j_icon_type_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefChromePageActionIconType;");
        auto j_icon_type = env->CallStaticObjectMethod(j_icon_type_cls, j_icon_type_from, static_cast<jlong>(icon_type));
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "isChromePageActionIconVisible", "(Lnet/kurobako/cef4j/gen/CefChromePageActionIconType;)Z");
        if (!mid) { env->PopLocalFrame(nullptr); return false; }
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, j_icon_type);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return false; }
        env->PopLocalFrame(nullptr);
        return jResult;
    }

    static int CEF_CALLBACK _is_chrome_toolbar_button_visible(cef_command_handler_t* self, cef_chrome_toolbar_button_type_t button_type) {
        auto* h = reinterpret_cast<JniCefCommandHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) { return false; }
        auto j_button_type_cls = env->FindClass("net/kurobako/cef4j/gen/CefChromeToolbarButtonType");
        auto j_button_type_from = env->GetStaticMethodID(j_button_type_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefChromeToolbarButtonType;");
        auto j_button_type = env->CallStaticObjectMethod(j_button_type_cls, j_button_type_from, static_cast<jlong>(button_type));
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "isChromeToolbarButtonVisible", "(Lnet/kurobako/cef4j/gen/CefChromeToolbarButtonType;)Z");
        if (!mid) { env->PopLocalFrame(nullptr); return false; }
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, j_button_type);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return false; }
        env->PopLocalFrame(nullptr);
        return jResult;
    }
};

extern "C" cef_command_handler_t* Create_JniCefCommandHandler(JNIEnv* env, jobject handler) {
    JavaVM* jvm;
    env->GetJavaVM(&jvm);
    auto globalRef = env->NewGlobalRef(handler);
    return reinterpret_cast<cef_command_handler_t*>(new JniCefCommandHandler(jvm, globalRef));
}
