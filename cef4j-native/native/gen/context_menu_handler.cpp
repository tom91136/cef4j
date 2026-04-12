// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
#include <jni.h>
#include "include/capi/cef_context_menu_handler_capi.h"
#include "include/capi/cef_browser_capi.h"
#include "include/capi/cef_frame_capi.h"
#include "include/capi/cef_menu_model_capi.h"
#include "jni_util.h"

#include <atomic>
#include "jni_util.h"

struct JniCefContextMenuHandler : public cef_context_menu_handler_t {
    JavaVM* jvm;
    jobject javaHandler;  // global ref
    std::atomic<int> refCount{1};

    JniCefContextMenuHandler(JavaVM* vm, jobject handler) : cef_context_menu_handler_t{}, jvm(vm) {
        javaHandler = handler;
        InitRefCount<JniCefContextMenuHandler, cef_context_menu_handler_t>(reinterpret_cast<cef_base_ref_counted_t*>(static_cast<cef_context_menu_handler_t*>(this)));
        on_before_context_menu = &_on_before_context_menu;
        run_context_menu = &_run_context_menu;
        on_context_menu_command = &_on_context_menu_command;
        on_context_menu_dismissed = &_on_context_menu_dismissed;
        run_quick_menu = &_run_quick_menu;
        on_quick_menu_command = &_on_quick_menu_command;
        on_quick_menu_dismissed = &_on_quick_menu_dismissed;
    }

    static void CEF_CALLBACK _on_before_context_menu(cef_context_menu_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, struct _cef_context_menu_params_t* params, struct _cef_menu_model_t* model) {
        auto* h = reinterpret_cast<JniCefContextMenuHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(17) < 0) { return; }
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
        cef_context_menu_params_t* _p_params = params;
        if (_p_params) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_params); _b->add_ref(_b); }
        auto j_params_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefContextMenuParams$NativePeer");
        auto j_params_ctor = env->GetMethodID(j_params_cls, "<init>", "(J)V");
        auto j_params = _p_params ? env->NewObject(j_params_cls, j_params_ctor, reinterpret_cast<jlong>(_p_params)) : nullptr;
        cef_menu_model_t* _p_model = model;
        if (_p_model) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_model); _b->add_ref(_b); }
        auto j_model_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefMenuModel$NativePeer");
        auto j_model_ctor = env->GetMethodID(j_model_cls, "<init>", "(J)V");
        auto j_model = _p_model ? env->NewObject(j_model_cls, j_model_ctor, reinterpret_cast<jlong>(_p_model)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onBeforeContextMenu", "(Lnet/kurobako/cef4j/gen/CefBrowser;Lnet/kurobako/cef4j/gen/CefFrame;Lnet/kurobako/cef4j/gen/CefContextMenuParams;Lnet/kurobako/cef4j/gen/CefMenuModel;)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, j_browser, j_frame, j_params, j_model);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        env->PopLocalFrame(nullptr);
    }

    static int CEF_CALLBACK _run_context_menu(cef_context_menu_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, struct _cef_context_menu_params_t* params, struct _cef_menu_model_t* model, struct _cef_run_context_menu_callback_t* callback) {
        auto* h = reinterpret_cast<JniCefContextMenuHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(20) < 0) { return false; }
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
        cef_context_menu_params_t* _p_params = params;
        if (_p_params) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_params); _b->add_ref(_b); }
        auto j_params_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefContextMenuParams$NativePeer");
        auto j_params_ctor = env->GetMethodID(j_params_cls, "<init>", "(J)V");
        auto j_params = _p_params ? env->NewObject(j_params_cls, j_params_ctor, reinterpret_cast<jlong>(_p_params)) : nullptr;
        cef_menu_model_t* _p_model = model;
        if (_p_model) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_model); _b->add_ref(_b); }
        auto j_model_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefMenuModel$NativePeer");
        auto j_model_ctor = env->GetMethodID(j_model_cls, "<init>", "(J)V");
        auto j_model = _p_model ? env->NewObject(j_model_cls, j_model_ctor, reinterpret_cast<jlong>(_p_model)) : nullptr;
        cef_run_context_menu_callback_t* _p_callback = callback;
        if (_p_callback) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_callback); _b->add_ref(_b); }
        auto j_callback_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefRunContextMenuCallback$NativePeer");
        auto j_callback_ctor = env->GetMethodID(j_callback_cls, "<init>", "(J)V");
        auto j_callback = _p_callback ? env->NewObject(j_callback_cls, j_callback_ctor, reinterpret_cast<jlong>(_p_callback)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "runContextMenu", "(Lnet/kurobako/cef4j/gen/CefBrowser;Lnet/kurobako/cef4j/gen/CefFrame;Lnet/kurobako/cef4j/gen/CefContextMenuParams;Lnet/kurobako/cef4j/gen/CefMenuModel;Lnet/kurobako/cef4j/gen/CefRunContextMenuCallback;)Z");
        if (!mid) { env->PopLocalFrame(nullptr); return false; }
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, j_browser, j_frame, j_params, j_model, j_callback);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return false; }
        env->PopLocalFrame(nullptr);
        return jResult;
    }

    static int CEF_CALLBACK _on_context_menu_command(cef_context_menu_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, struct _cef_context_menu_params_t* params, int command_id, cef_event_flags_t event_flags) {
        auto* h = reinterpret_cast<JniCefContextMenuHandler*>(self);
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
        cef_context_menu_params_t* _p_params = params;
        if (_p_params) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_params); _b->add_ref(_b); }
        auto j_params_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefContextMenuParams$NativePeer");
        auto j_params_ctor = env->GetMethodID(j_params_cls, "<init>", "(J)V");
        auto j_params = _p_params ? env->NewObject(j_params_cls, j_params_ctor, reinterpret_cast<jlong>(_p_params)) : nullptr;
        auto j_event_flags_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefEventFlags");
        auto j_event_flags_from = env->GetStaticMethodID(j_event_flags_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefEventFlags;");
        auto j_event_flags = env->CallStaticObjectMethod(j_event_flags_cls, j_event_flags_from, static_cast<jlong>(event_flags));
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onContextMenuCommand", "(Lnet/kurobako/cef4j/gen/CefBrowser;Lnet/kurobako/cef4j/gen/CefFrame;Lnet/kurobako/cef4j/gen/CefContextMenuParams;ILnet/kurobako/cef4j/gen/CefEventFlags;)Z");
        if (!mid) { env->PopLocalFrame(nullptr); return false; }
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, j_browser, j_frame, j_params, static_cast<jint>(command_id), j_event_flags);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return false; }
        env->PopLocalFrame(nullptr);
        return jResult;
    }

    static void CEF_CALLBACK _on_context_menu_dismissed(cef_context_menu_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame) {
        auto* h = reinterpret_cast<JniCefContextMenuHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(11) < 0) { return; }
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
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onContextMenuDismissed", "(Lnet/kurobako/cef4j/gen/CefBrowser;Lnet/kurobako/cef4j/gen/CefFrame;)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, j_browser, j_frame);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        env->PopLocalFrame(nullptr);
    }

    static int CEF_CALLBACK _run_quick_menu(cef_context_menu_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, const cef_point_t* location, const cef_size_t* size, cef_quick_menu_edit_state_flags_t edit_state_flags, struct _cef_run_quick_menu_callback_t* callback) {
        auto* h = reinterpret_cast<JniCefContextMenuHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(23) < 0) { return false; }
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
        auto j_location_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefPoint");
        auto j_location_ctor = env->GetMethodID(j_location_cls, "<init>", "(II)V");
        auto j_location = location ? env->NewObject(j_location_cls, j_location_ctor, static_cast<jint>((location)->x), static_cast<jint>((location)->y)) : nullptr;
        auto j_size_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefSize");
        auto j_size_ctor = env->GetMethodID(j_size_cls, "<init>", "(II)V");
        auto j_size = size ? env->NewObject(j_size_cls, j_size_ctor, static_cast<jint>((size)->width), static_cast<jint>((size)->height)) : nullptr;
        auto j_edit_state_flags_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefQuickMenuEditStateFlags");
        auto j_edit_state_flags_from = env->GetStaticMethodID(j_edit_state_flags_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefQuickMenuEditStateFlags;");
        auto j_edit_state_flags = env->CallStaticObjectMethod(j_edit_state_flags_cls, j_edit_state_flags_from, static_cast<jlong>(edit_state_flags));
        cef_run_quick_menu_callback_t* _p_callback = callback;
        if (_p_callback) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_callback); _b->add_ref(_b); }
        auto j_callback_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefRunQuickMenuCallback$NativePeer");
        auto j_callback_ctor = env->GetMethodID(j_callback_cls, "<init>", "(J)V");
        auto j_callback = _p_callback ? env->NewObject(j_callback_cls, j_callback_ctor, reinterpret_cast<jlong>(_p_callback)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "runQuickMenu", "(Lnet/kurobako/cef4j/gen/CefBrowser;Lnet/kurobako/cef4j/gen/CefFrame;Lnet/kurobako/cef4j/gen/CefPoint;Lnet/kurobako/cef4j/gen/CefSize;Lnet/kurobako/cef4j/gen/CefQuickMenuEditStateFlags;Lnet/kurobako/cef4j/gen/CefRunQuickMenuCallback;)Z");
        if (!mid) { env->PopLocalFrame(nullptr); return false; }
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, j_browser, j_frame, j_location, j_size, j_edit_state_flags, j_callback);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return false; }
        env->PopLocalFrame(nullptr);
        return jResult;
    }

    static int CEF_CALLBACK _on_quick_menu_command(cef_context_menu_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, int command_id, cef_event_flags_t event_flags) {
        auto* h = reinterpret_cast<JniCefContextMenuHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(14) < 0) { return false; }
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
        auto j_event_flags_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefEventFlags");
        auto j_event_flags_from = env->GetStaticMethodID(j_event_flags_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefEventFlags;");
        auto j_event_flags = env->CallStaticObjectMethod(j_event_flags_cls, j_event_flags_from, static_cast<jlong>(event_flags));
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onQuickMenuCommand", "(Lnet/kurobako/cef4j/gen/CefBrowser;Lnet/kurobako/cef4j/gen/CefFrame;ILnet/kurobako/cef4j/gen/CefEventFlags;)Z");
        if (!mid) { env->PopLocalFrame(nullptr); return false; }
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, j_browser, j_frame, static_cast<jint>(command_id), j_event_flags);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return false; }
        env->PopLocalFrame(nullptr);
        return jResult;
    }

    static void CEF_CALLBACK _on_quick_menu_dismissed(cef_context_menu_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame) {
        auto* h = reinterpret_cast<JniCefContextMenuHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(11) < 0) { return; }
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
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onQuickMenuDismissed", "(Lnet/kurobako/cef4j/gen/CefBrowser;Lnet/kurobako/cef4j/gen/CefFrame;)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, j_browser, j_frame);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        env->PopLocalFrame(nullptr);
    }
};

extern "C" cef_context_menu_handler_t* Create_JniCefContextMenuHandler(JNIEnv* env, jobject handler) {
    JavaVM* jvm;
    env->GetJavaVM(&jvm);
    auto globalRef = env->NewGlobalRef(handler);
    return reinterpret_cast<cef_context_menu_handler_t*>(new JniCefContextMenuHandler(jvm, globalRef));
}
