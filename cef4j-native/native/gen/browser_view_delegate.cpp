// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
#include <jni.h>
#include "include/capi/views/cef_browser_view_delegate_capi.h"
#include "include/capi/cef_browser_capi.h"
#include "include/capi/views/cef_browser_view_capi.h"
#include "jni_util.h"

#include <atomic>
#include "jni_util.h"

struct JniCefBrowserViewDelegate : public cef_browser_view_delegate_t {
    JavaVM* jvm;
    jobject javaHandler;  // global ref
    std::atomic<int> refCount{1};

    JniCefBrowserViewDelegate(JavaVM* vm, jobject handler) : cef_browser_view_delegate_t{}, jvm(vm) {
        javaHandler = handler;
        InitRefCount<JniCefBrowserViewDelegate, cef_browser_view_delegate_t>(reinterpret_cast<cef_base_ref_counted_t*>(static_cast<cef_browser_view_delegate_t*>(this)));
        on_browser_created = &_on_browser_created;
        on_browser_destroyed = &_on_browser_destroyed;
        on_popup_browser_view_created = &_on_popup_browser_view_created;
        get_chrome_toolbar_type = &_get_chrome_toolbar_type;
        use_frameless_window_for_picture_in_picture = &_use_frameless_window_for_picture_in_picture;
        on_gesture_command = &_on_gesture_command;
        get_browser_runtime_style = &_get_browser_runtime_style;
        allow_move_for_picture_in_picture = &_allow_move_for_picture_in_picture;
        allow_picture_in_picture_without_user_activation = &_allow_picture_in_picture_without_user_activation;
    }

    static void CEF_CALLBACK _on_browser_created(cef_browser_view_delegate_t* self, struct _cef_browser_view_t* browser_view, struct _cef_browser_t* browser) {
        auto* h = reinterpret_cast<JniCefBrowserViewDelegate*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(11) < 0) { return; }
        cef_browser_view_t* _p_browser_view = browser_view;
        if (_p_browser_view) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser_view); _b->add_ref(_b); }
        auto j_browser_view_cls = env->FindClass("net/kurobako/cef4j/gen/views/CefBrowserView$NativePeer");
        auto j_browser_view_ctor = env->GetMethodID(j_browser_view_cls, "<init>", "(J)V");
        auto j_browser_view = _p_browser_view ? env->NewObject(j_browser_view_cls, j_browser_view_ctor, reinterpret_cast<jlong>(_p_browser_view)) : nullptr;
        cef_browser_t* _p_browser = browser;
        if (_p_browser) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b); }
        auto j_browser_cls = env->FindClass("net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onBrowserCreated", "(Lnet/kurobako/cef4j/gen/views/CefBrowserView;Lnet/kurobako/cef4j/gen/CefBrowser;)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, j_browser_view, j_browser);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        env->PopLocalFrame(nullptr);
    }

    static void CEF_CALLBACK _on_browser_destroyed(cef_browser_view_delegate_t* self, struct _cef_browser_view_t* browser_view, struct _cef_browser_t* browser) {
        auto* h = reinterpret_cast<JniCefBrowserViewDelegate*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(11) < 0) { return; }
        cef_browser_view_t* _p_browser_view = browser_view;
        if (_p_browser_view) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser_view); _b->add_ref(_b); }
        auto j_browser_view_cls = env->FindClass("net/kurobako/cef4j/gen/views/CefBrowserView$NativePeer");
        auto j_browser_view_ctor = env->GetMethodID(j_browser_view_cls, "<init>", "(J)V");
        auto j_browser_view = _p_browser_view ? env->NewObject(j_browser_view_cls, j_browser_view_ctor, reinterpret_cast<jlong>(_p_browser_view)) : nullptr;
        cef_browser_t* _p_browser = browser;
        if (_p_browser) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b); }
        auto j_browser_cls = env->FindClass("net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onBrowserDestroyed", "(Lnet/kurobako/cef4j/gen/views/CefBrowserView;Lnet/kurobako/cef4j/gen/CefBrowser;)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, j_browser_view, j_browser);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        env->PopLocalFrame(nullptr);
    }

    static int CEF_CALLBACK _on_popup_browser_view_created(cef_browser_view_delegate_t* self, struct _cef_browser_view_t* browser_view, struct _cef_browser_view_t* popup_browser_view, int is_devtools) {
        auto* h = reinterpret_cast<JniCefBrowserViewDelegate*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(11) < 0) { return false; }
        cef_browser_view_t* _p_browser_view = browser_view;
        if (_p_browser_view) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser_view); _b->add_ref(_b); }
        auto j_browser_view_cls = env->FindClass("net/kurobako/cef4j/gen/views/CefBrowserView$NativePeer");
        auto j_browser_view_ctor = env->GetMethodID(j_browser_view_cls, "<init>", "(J)V");
        auto j_browser_view = _p_browser_view ? env->NewObject(j_browser_view_cls, j_browser_view_ctor, reinterpret_cast<jlong>(_p_browser_view)) : nullptr;
        cef_browser_view_t* _p_popup_browser_view = popup_browser_view;
        if (_p_popup_browser_view) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_popup_browser_view); _b->add_ref(_b); }
        auto j_popup_browser_view_cls = env->FindClass("net/kurobako/cef4j/gen/views/CefBrowserView$NativePeer");
        auto j_popup_browser_view_ctor = env->GetMethodID(j_popup_browser_view_cls, "<init>", "(J)V");
        auto j_popup_browser_view = _p_popup_browser_view ? env->NewObject(j_popup_browser_view_cls, j_popup_browser_view_ctor, reinterpret_cast<jlong>(_p_popup_browser_view)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onPopupBrowserViewCreated", "(Lnet/kurobako/cef4j/gen/views/CefBrowserView;Lnet/kurobako/cef4j/gen/views/CefBrowserView;Z)Z");
        if (!mid) { env->PopLocalFrame(nullptr); return false; }
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, j_browser_view, j_popup_browser_view, static_cast<jboolean>(is_devtools));
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return false; }
        env->PopLocalFrame(nullptr);
        return jResult;
    }

    static cef_chrome_toolbar_type_t CEF_CALLBACK _get_chrome_toolbar_type(cef_browser_view_delegate_t* self, struct _cef_browser_view_t* browser_view) {
        auto* h = reinterpret_cast<JniCefBrowserViewDelegate*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(9) < 0) { return cef_chrome_toolbar_type_t::CEF_CTT_NONE; }
        cef_browser_view_t* _p_browser_view = browser_view;
        if (_p_browser_view) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser_view); _b->add_ref(_b); }
        auto j_browser_view_cls = env->FindClass("net/kurobako/cef4j/gen/views/CefBrowserView$NativePeer");
        auto j_browser_view_ctor = env->GetMethodID(j_browser_view_cls, "<init>", "(J)V");
        auto j_browser_view = _p_browser_view ? env->NewObject(j_browser_view_cls, j_browser_view_ctor, reinterpret_cast<jlong>(_p_browser_view)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "getChromeToolbarType", "(Lnet/kurobako/cef4j/gen/views/CefBrowserView;)Lnet/kurobako/cef4j/gen/CefChromeToolbarType;");
        if (!mid) { env->PopLocalFrame(nullptr); return cef_chrome_toolbar_type_t::CEF_CTT_NONE; }
        auto jResult = (jobject)env->CallObjectMethod(h->javaHandler, mid, j_browser_view);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return cef_chrome_toolbar_type_t::CEF_CTT_NONE; }
        cef_chrome_toolbar_type_t nativeResult = static_cast<cef_chrome_toolbar_type_t>(jResult ? env->GetLongField(jResult, env->GetFieldID(env->GetObjectClass(jResult), "value", "J")) : 0);
        env->PopLocalFrame(nullptr);
        return nativeResult;
    }

    static int CEF_CALLBACK _use_frameless_window_for_picture_in_picture(cef_browser_view_delegate_t* self, struct _cef_browser_view_t* browser_view) {
        auto* h = reinterpret_cast<JniCefBrowserViewDelegate*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) { return false; }
        cef_browser_view_t* _p_browser_view = browser_view;
        if (_p_browser_view) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser_view); _b->add_ref(_b); }
        auto j_browser_view_cls = env->FindClass("net/kurobako/cef4j/gen/views/CefBrowserView$NativePeer");
        auto j_browser_view_ctor = env->GetMethodID(j_browser_view_cls, "<init>", "(J)V");
        auto j_browser_view = _p_browser_view ? env->NewObject(j_browser_view_cls, j_browser_view_ctor, reinterpret_cast<jlong>(_p_browser_view)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "useFramelessWindowForPictureInPicture", "(Lnet/kurobako/cef4j/gen/views/CefBrowserView;)Z");
        if (!mid) { env->PopLocalFrame(nullptr); return false; }
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, j_browser_view);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return false; }
        env->PopLocalFrame(nullptr);
        return jResult;
    }

    static int CEF_CALLBACK _on_gesture_command(cef_browser_view_delegate_t* self, struct _cef_browser_view_t* browser_view, cef_gesture_command_t gesture_command) {
        auto* h = reinterpret_cast<JniCefBrowserViewDelegate*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(11) < 0) { return false; }
        cef_browser_view_t* _p_browser_view = browser_view;
        if (_p_browser_view) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser_view); _b->add_ref(_b); }
        auto j_browser_view_cls = env->FindClass("net/kurobako/cef4j/gen/views/CefBrowserView$NativePeer");
        auto j_browser_view_ctor = env->GetMethodID(j_browser_view_cls, "<init>", "(J)V");
        auto j_browser_view = _p_browser_view ? env->NewObject(j_browser_view_cls, j_browser_view_ctor, reinterpret_cast<jlong>(_p_browser_view)) : nullptr;
        auto j_gesture_command_cls = env->FindClass("net/kurobako/cef4j/gen/CefGestureCommand");
        auto j_gesture_command_from = env->GetStaticMethodID(j_gesture_command_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefGestureCommand;");
        auto j_gesture_command = env->CallStaticObjectMethod(j_gesture_command_cls, j_gesture_command_from, static_cast<jlong>(gesture_command));
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onGestureCommand", "(Lnet/kurobako/cef4j/gen/views/CefBrowserView;Lnet/kurobako/cef4j/gen/CefGestureCommand;)Z");
        if (!mid) { env->PopLocalFrame(nullptr); return false; }
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, j_browser_view, j_gesture_command);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return false; }
        env->PopLocalFrame(nullptr);
        return jResult;
    }

    static cef_runtime_style_t CEF_CALLBACK _get_browser_runtime_style(cef_browser_view_delegate_t* self) {
        auto* h = reinterpret_cast<JniCefBrowserViewDelegate*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(6) < 0) { return cef_runtime_style_t::CEF_RUNTIME_STYLE_DEFAULT; }
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "getBrowserRuntimeStyle", "()Lnet/kurobako/cef4j/gen/CefRuntimeStyle;");
        if (!mid) { env->PopLocalFrame(nullptr); return cef_runtime_style_t::CEF_RUNTIME_STYLE_DEFAULT; }
        auto jResult = (jobject)env->CallObjectMethod(h->javaHandler, mid);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return cef_runtime_style_t::CEF_RUNTIME_STYLE_DEFAULT; }
        cef_runtime_style_t nativeResult = static_cast<cef_runtime_style_t>(jResult ? env->GetLongField(jResult, env->GetFieldID(env->GetObjectClass(jResult), "value", "J")) : 0);
        env->PopLocalFrame(nullptr);
        return nativeResult;
    }

    static int CEF_CALLBACK _allow_move_for_picture_in_picture(cef_browser_view_delegate_t* self, struct _cef_browser_view_t* browser_view) {
        auto* h = reinterpret_cast<JniCefBrowserViewDelegate*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) { return false; }
        cef_browser_view_t* _p_browser_view = browser_view;
        if (_p_browser_view) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser_view); _b->add_ref(_b); }
        auto j_browser_view_cls = env->FindClass("net/kurobako/cef4j/gen/views/CefBrowserView$NativePeer");
        auto j_browser_view_ctor = env->GetMethodID(j_browser_view_cls, "<init>", "(J)V");
        auto j_browser_view = _p_browser_view ? env->NewObject(j_browser_view_cls, j_browser_view_ctor, reinterpret_cast<jlong>(_p_browser_view)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "allowMoveForPictureInPicture", "(Lnet/kurobako/cef4j/gen/views/CefBrowserView;)Z");
        if (!mid) { env->PopLocalFrame(nullptr); return false; }
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, j_browser_view);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return false; }
        env->PopLocalFrame(nullptr);
        return jResult;
    }

    static int CEF_CALLBACK _allow_picture_in_picture_without_user_activation(cef_browser_view_delegate_t* self, struct _cef_browser_view_t* browser_view) {
        auto* h = reinterpret_cast<JniCefBrowserViewDelegate*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) { return false; }
        cef_browser_view_t* _p_browser_view = browser_view;
        if (_p_browser_view) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser_view); _b->add_ref(_b); }
        auto j_browser_view_cls = env->FindClass("net/kurobako/cef4j/gen/views/CefBrowserView$NativePeer");
        auto j_browser_view_ctor = env->GetMethodID(j_browser_view_cls, "<init>", "(J)V");
        auto j_browser_view = _p_browser_view ? env->NewObject(j_browser_view_cls, j_browser_view_ctor, reinterpret_cast<jlong>(_p_browser_view)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "allowPictureInPictureWithoutUserActivation", "(Lnet/kurobako/cef4j/gen/views/CefBrowserView;)Z");
        if (!mid) { env->PopLocalFrame(nullptr); return false; }
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, j_browser_view);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return false; }
        env->PopLocalFrame(nullptr);
        return jResult;
    }
};

extern "C" cef_browser_view_delegate_t* Create_JniCefBrowserViewDelegate(JNIEnv* env, jobject handler) {
    JavaVM* jvm;
    env->GetJavaVM(&jvm);
    auto globalRef = env->NewGlobalRef(handler);
    return reinterpret_cast<cef_browser_view_delegate_t*>(new JniCefBrowserViewDelegate(jvm, globalRef));
}
