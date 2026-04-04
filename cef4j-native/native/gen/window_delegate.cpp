// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
#include <jni.h>
#include "include/capi/views/cef_window_delegate_capi.h"
#include "include/capi/views/cef_window_capi.h"
#include "jni_util.h"

#include <atomic>
#include "jni_util.h"

struct JniCefWindowDelegate: public cef_window_delegate_t {
    JavaVM *jvm;
    jobject javaHandler;  // global ref
    std::atomic<int> refCount { 1 };

    JniCefWindowDelegate(JavaVM *vm, jobject handler) : cef_window_delegate_t { }, jvm(vm) {
        javaHandler = handler;
        InitRefCount<JniCefWindowDelegate, cef_window_delegate_t>(reinterpret_cast<cef_base_ref_counted_t*>(static_cast<cef_window_delegate_t*>(this)));
        on_window_created = &_on_window_created;
        on_window_closing = &_on_window_closing;
        on_window_destroyed = &_on_window_destroyed;
        on_window_activation_changed = &_on_window_activation_changed;
        on_window_bounds_changed = &_on_window_bounds_changed;
        on_window_fullscreen_transition = &_on_window_fullscreen_transition;
        get_parent_window = &_get_parent_window;
        is_window_modal_dialog = &_is_window_modal_dialog;
        get_initial_bounds = &_get_initial_bounds;
        get_initial_show_state = &_get_initial_show_state;
        is_frameless = &_is_frameless;
        with_standard_window_buttons = &_with_standard_window_buttons;
        get_titlebar_height = &_get_titlebar_height;
        accepts_first_mouse = &_accepts_first_mouse;
        can_resize = &_can_resize;
        can_maximize = &_can_maximize;
        can_minimize = &_can_minimize;
        can_close = &_can_close;
        on_accelerator = &_on_accelerator;
        on_key_event = &_on_key_event;
        on_theme_colors_changed = &_on_theme_colors_changed;
        get_window_runtime_style = &_get_window_runtime_style;
        get_linux_window_properties = &_get_linux_window_properties;
    }

    static void CEF_CALLBACK _on_window_created(cef_window_delegate_t* self, struct _cef_window_t* window) {
        auto* h = reinterpret_cast<JniCefWindowDelegate*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) {return;}
        cef_window_t* _p_window = window;
        if (_p_window) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_window); _b->add_ref(_b);}
        auto j_window_cls = env->FindClass("net/kurobako/cef4j/gen/views/CefWindow$NativePeer");
        auto j_window_ctor = env->GetMethodID(j_window_cls, "<init>", "(J)V");
        auto j_window = _p_window ? env->NewObject(j_window_cls, j_window_ctor, reinterpret_cast<jlong>(_p_window)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onWindowCreated", "(Lnet/kurobako/cef4j/gen/views/CefWindow;)V");
        if (!mid) {env->PopLocalFrame(nullptr); return;}
        env->CallVoidMethod(h->javaHandler, mid, j_window);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return;}
        env->PopLocalFrame(nullptr);
    }

    static void CEF_CALLBACK _on_window_closing(cef_window_delegate_t* self, struct _cef_window_t* window) {
        auto* h = reinterpret_cast<JniCefWindowDelegate*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) {return;}
        cef_window_t* _p_window = window;
        if (_p_window) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_window); _b->add_ref(_b);}
        auto j_window_cls = env->FindClass("net/kurobako/cef4j/gen/views/CefWindow$NativePeer");
        auto j_window_ctor = env->GetMethodID(j_window_cls, "<init>", "(J)V");
        auto j_window = _p_window ? env->NewObject(j_window_cls, j_window_ctor, reinterpret_cast<jlong>(_p_window)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onWindowClosing", "(Lnet/kurobako/cef4j/gen/views/CefWindow;)V");
        if (!mid) {env->PopLocalFrame(nullptr); return;}
        env->CallVoidMethod(h->javaHandler, mid, j_window);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return;}
        env->PopLocalFrame(nullptr);
    }

    static void CEF_CALLBACK _on_window_destroyed(cef_window_delegate_t* self, struct _cef_window_t* window) {
        auto* h = reinterpret_cast<JniCefWindowDelegate*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) {return;}
        cef_window_t* _p_window = window;
        if (_p_window) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_window); _b->add_ref(_b);}
        auto j_window_cls = env->FindClass("net/kurobako/cef4j/gen/views/CefWindow$NativePeer");
        auto j_window_ctor = env->GetMethodID(j_window_cls, "<init>", "(J)V");
        auto j_window = _p_window ? env->NewObject(j_window_cls, j_window_ctor, reinterpret_cast<jlong>(_p_window)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onWindowDestroyed", "(Lnet/kurobako/cef4j/gen/views/CefWindow;)V");
        if (!mid) {env->PopLocalFrame(nullptr); return;}
        env->CallVoidMethod(h->javaHandler, mid, j_window);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return;}
        env->PopLocalFrame(nullptr);
    }

    static void CEF_CALLBACK _on_window_activation_changed(cef_window_delegate_t* self, struct _cef_window_t* window, int active) {
        auto* h = reinterpret_cast<JniCefWindowDelegate*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) {return;}
        cef_window_t* _p_window = window;
        if (_p_window) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_window); _b->add_ref(_b);}
        auto j_window_cls = env->FindClass("net/kurobako/cef4j/gen/views/CefWindow$NativePeer");
        auto j_window_ctor = env->GetMethodID(j_window_cls, "<init>", "(J)V");
        auto j_window = _p_window ? env->NewObject(j_window_cls, j_window_ctor, reinterpret_cast<jlong>(_p_window)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onWindowActivationChanged", "(Lnet/kurobako/cef4j/gen/views/CefWindow;Z)V");
        if (!mid) {env->PopLocalFrame(nullptr); return;}
        env->CallVoidMethod(h->javaHandler, mid, j_window, static_cast<jboolean>(active));
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return;}
        env->PopLocalFrame(nullptr);
    }

    static void CEF_CALLBACK _on_window_bounds_changed(cef_window_delegate_t* self, struct _cef_window_t* window, const cef_rect_t* new_bounds) {
        auto* h = reinterpret_cast<JniCefWindowDelegate*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(11) < 0) {return;}
        cef_window_t* _p_window = window;
        if (_p_window) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_window); _b->add_ref(_b);}
        auto j_window_cls = env->FindClass("net/kurobako/cef4j/gen/views/CefWindow$NativePeer");
        auto j_window_ctor = env->GetMethodID(j_window_cls, "<init>", "(J)V");
        auto j_window = _p_window ? env->NewObject(j_window_cls, j_window_ctor, reinterpret_cast<jlong>(_p_window)) : nullptr;
        auto j_new_bounds_cls = env->FindClass("net/kurobako/cef4j/gen/CefRect");
        auto j_new_bounds_ctor = env->GetMethodID(j_new_bounds_cls, "<init>", "(IIII)V");
        auto j_new_bounds = new_bounds ? env->NewObject(j_new_bounds_cls, j_new_bounds_ctor, static_cast<jint>(new_bounds->x), static_cast<jint>(new_bounds->y), static_cast<jint>(new_bounds->width), static_cast<jint>(new_bounds->height)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onWindowBoundsChanged", "(Lnet/kurobako/cef4j/gen/views/CefWindow;Lnet/kurobako/cef4j/gen/CefRect;)V");
        if (!mid) {env->PopLocalFrame(nullptr); return;}
        env->CallVoidMethod(h->javaHandler, mid, j_window, j_new_bounds);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return;}
        env->PopLocalFrame(nullptr);
    }

    static void CEF_CALLBACK _on_window_fullscreen_transition(cef_window_delegate_t* self, struct _cef_window_t* window, int is_completed) {
        auto* h = reinterpret_cast<JniCefWindowDelegate*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) {return;}
        cef_window_t* _p_window = window;
        if (_p_window) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_window); _b->add_ref(_b);}
        auto j_window_cls = env->FindClass("net/kurobako/cef4j/gen/views/CefWindow$NativePeer");
        auto j_window_ctor = env->GetMethodID(j_window_cls, "<init>", "(J)V");
        auto j_window = _p_window ? env->NewObject(j_window_cls, j_window_ctor, reinterpret_cast<jlong>(_p_window)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onWindowFullscreenTransition", "(Lnet/kurobako/cef4j/gen/views/CefWindow;Z)V");
        if (!mid) {env->PopLocalFrame(nullptr); return;}
        env->CallVoidMethod(h->javaHandler, mid, j_window, static_cast<jboolean>(is_completed));
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return;}
        env->PopLocalFrame(nullptr);
    }

    static cef_window_t* CEF_CALLBACK _get_parent_window(cef_window_delegate_t* self, struct _cef_window_t* window, int* is_menu, int* can_activate_menu) {
        auto* h = reinterpret_cast<JniCefWindowDelegate*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(11) < 0) {return nullptr;}
        cef_window_t* _p_window = window;
        if (_p_window) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_window); _b->add_ref(_b);}
        auto j_window_cls = env->FindClass("net/kurobako/cef4j/gen/views/CefWindow$NativePeer");
        auto j_window_ctor = env->GetMethodID(j_window_cls, "<init>", "(J)V");
        auto j_window = _p_window ? env->NewObject(j_window_cls, j_window_ctor, reinterpret_cast<jlong>(_p_window)) : nullptr;
        jintArray j_is_menu = env->NewIntArray(1);
        if (is_menu) {jint _v = *is_menu; env->SetIntArrayRegion(j_is_menu, 0, 1, &_v);}
        jintArray j_can_activate_menu = env->NewIntArray(1);
        if (can_activate_menu) {jint _v = *can_activate_menu; env->SetIntArrayRegion(j_can_activate_menu, 0, 1, &_v);}
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "getParentWindow", "(Lnet/kurobako/cef4j/gen/views/CefWindow;[I[I)Lnet/kurobako/cef4j/gen/views/CefWindow;");
        if (!mid) {env->PopLocalFrame(nullptr); return nullptr;}
        auto jResult = (jobject)env->CallObjectMethod(h->javaHandler, mid, j_window, j_is_menu, j_can_activate_menu);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return nullptr;}
        if (is_menu) {jint _v; env->GetIntArrayRegion(j_is_menu, 0, 1, &_v); *is_menu = _v;}
        if (can_activate_menu) {jint _v; env->GetIntArrayRegion(j_can_activate_menu, 0, 1, &_v); *can_activate_menu = _v;}
        cef_window_t* nativeResult = jResult ? reinterpret_cast<cef_window_t*>(env->GetLongField(jResult, env->GetFieldID(env->GetObjectClass(jResult), "nativePtr", "J"))) : nullptr;
        env->PopLocalFrame(nullptr);
        return nativeResult;
    }

    static int CEF_CALLBACK _is_window_modal_dialog(cef_window_delegate_t* self, struct _cef_window_t* window) {
        auto* h = reinterpret_cast<JniCefWindowDelegate*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) {return false;}
        cef_window_t* _p_window = window;
        if (_p_window) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_window); _b->add_ref(_b);}
        auto j_window_cls = env->FindClass("net/kurobako/cef4j/gen/views/CefWindow$NativePeer");
        auto j_window_ctor = env->GetMethodID(j_window_cls, "<init>", "(J)V");
        auto j_window = _p_window ? env->NewObject(j_window_cls, j_window_ctor, reinterpret_cast<jlong>(_p_window)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "isWindowModalDialog", "(Lnet/kurobako/cef4j/gen/views/CefWindow;)Z");
        if (!mid) {env->PopLocalFrame(nullptr); return false;}
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, j_window);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return false;}
        env->PopLocalFrame(nullptr);
        return jResult;
    }

    static cef_rect_t CEF_CALLBACK _get_initial_bounds(cef_window_delegate_t* self, struct _cef_window_t* window) {
        auto* h = reinterpret_cast<JniCefWindowDelegate*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) {return cef_rect_t {};}
        cef_window_t* _p_window = window;
        if (_p_window) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_window); _b->add_ref(_b);}
        auto j_window_cls = env->FindClass("net/kurobako/cef4j/gen/views/CefWindow$NativePeer");
        auto j_window_ctor = env->GetMethodID(j_window_cls, "<init>", "(J)V");
        auto j_window = _p_window ? env->NewObject(j_window_cls, j_window_ctor, reinterpret_cast<jlong>(_p_window)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "getInitialBounds", "(Lnet/kurobako/cef4j/gen/views/CefWindow;)Ljava/lang/Object;");
        if (!mid) {env->PopLocalFrame(nullptr); return cef_rect_t {};}
        auto jResult = (jobject)env->CallObjectMethod(h->javaHandler, mid, j_window);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return cef_rect_t {};}
        cef_rect_t nativeResult = ([&]() {
                    cef_rect_t _result = {};
                    if (jResult) {
                        auto _c = env->FindClass("net/kurobako/cef4j/gen/CefRect");
                        _result.x = static_cast<decltype(_result.x)>(env->GetIntField(jResult, env->GetFieldID(_c, "x", "I")));
                        _result.y = static_cast<decltype(_result.y)>(env->GetIntField(jResult, env->GetFieldID(_c, "y", "I")));
                        _result.width = static_cast<decltype(_result.width)>(env->GetIntField(jResult, env->GetFieldID(_c, "width", "I")));
                        _result.height = static_cast<decltype(_result.height)>(env->GetIntField(jResult, env->GetFieldID(_c, "height", "I")));
                    }
                    return _result;
                })();
        env->PopLocalFrame(nullptr);
        return nativeResult;
    }

    static cef_show_state_t CEF_CALLBACK _get_initial_show_state(cef_window_delegate_t* self, struct _cef_window_t* window) {
        auto* h = reinterpret_cast<JniCefWindowDelegate*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(9) < 0) {return cef_show_state_t::CEF_SHOW_STATE_NORMAL;}
        cef_window_t* _p_window = window;
        if (_p_window) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_window); _b->add_ref(_b);}
        auto j_window_cls = env->FindClass("net/kurobako/cef4j/gen/views/CefWindow$NativePeer");
        auto j_window_ctor = env->GetMethodID(j_window_cls, "<init>", "(J)V");
        auto j_window = _p_window ? env->NewObject(j_window_cls, j_window_ctor, reinterpret_cast<jlong>(_p_window)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "getInitialShowState", "(Lnet/kurobako/cef4j/gen/views/CefWindow;)Lnet/kurobako/cef4j/gen/CefShowState;");
        if (!mid) {env->PopLocalFrame(nullptr); return cef_show_state_t::CEF_SHOW_STATE_NORMAL;}
        auto jResult = (jobject)env->CallObjectMethod(h->javaHandler, mid, j_window);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return cef_show_state_t::CEF_SHOW_STATE_NORMAL;}
        cef_show_state_t nativeResult = static_cast<cef_show_state_t>(jResult ? env->GetLongField(jResult, env->GetFieldID(env->GetObjectClass(jResult), "value", "J")) : 0);
        env->PopLocalFrame(nullptr);
        return nativeResult;
    }

    static int CEF_CALLBACK _is_frameless(cef_window_delegate_t* self, struct _cef_window_t* window) {
        auto* h = reinterpret_cast<JniCefWindowDelegate*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) {return false;}
        cef_window_t* _p_window = window;
        if (_p_window) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_window); _b->add_ref(_b);}
        auto j_window_cls = env->FindClass("net/kurobako/cef4j/gen/views/CefWindow$NativePeer");
        auto j_window_ctor = env->GetMethodID(j_window_cls, "<init>", "(J)V");
        auto j_window = _p_window ? env->NewObject(j_window_cls, j_window_ctor, reinterpret_cast<jlong>(_p_window)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "isFrameless", "(Lnet/kurobako/cef4j/gen/views/CefWindow;)Z");
        if (!mid) {env->PopLocalFrame(nullptr); return false;}
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, j_window);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return false;}
        env->PopLocalFrame(nullptr);
        return jResult;
    }

    static int CEF_CALLBACK _with_standard_window_buttons(cef_window_delegate_t* self, struct _cef_window_t* window) {
        auto* h = reinterpret_cast<JniCefWindowDelegate*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) {return false;}
        cef_window_t* _p_window = window;
        if (_p_window) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_window); _b->add_ref(_b);}
        auto j_window_cls = env->FindClass("net/kurobako/cef4j/gen/views/CefWindow$NativePeer");
        auto j_window_ctor = env->GetMethodID(j_window_cls, "<init>", "(J)V");
        auto j_window = _p_window ? env->NewObject(j_window_cls, j_window_ctor, reinterpret_cast<jlong>(_p_window)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "withStandardWindowButtons", "(Lnet/kurobako/cef4j/gen/views/CefWindow;)Z");
        if (!mid) {env->PopLocalFrame(nullptr); return false;}
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, j_window);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return false;}
        env->PopLocalFrame(nullptr);
        return jResult;
    }

    static int CEF_CALLBACK _get_titlebar_height(cef_window_delegate_t* self, struct _cef_window_t* window, float* titlebar_height) {
        auto* h = reinterpret_cast<JniCefWindowDelegate*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(9) < 0) {return false;}
        cef_window_t* _p_window = window;
        if (_p_window) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_window); _b->add_ref(_b);}
        auto j_window_cls = env->FindClass("net/kurobako/cef4j/gen/views/CefWindow$NativePeer");
        auto j_window_ctor = env->GetMethodID(j_window_cls, "<init>", "(J)V");
        auto j_window = _p_window ? env->NewObject(j_window_cls, j_window_ctor, reinterpret_cast<jlong>(_p_window)) : nullptr;
        jfloatArray j_titlebar_height = env->NewFloatArray(1);
        if (titlebar_height) {jfloat _v = static_cast<jfloat>(*titlebar_height); env->SetFloatArrayRegion(j_titlebar_height, 0, 1, &_v);}
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "getTitlebarHeight", "(Lnet/kurobako/cef4j/gen/views/CefWindow;[F)Z");
        if (!mid) {env->PopLocalFrame(nullptr); return false;}
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, j_window, j_titlebar_height);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return false;}
        if (titlebar_height) {jfloat _v; env->GetFloatArrayRegion(j_titlebar_height, 0, 1, &_v); *titlebar_height = static_cast<float>(_v);}
        env->PopLocalFrame(nullptr);
        return jResult;
    }

    static cef_state_t CEF_CALLBACK _accepts_first_mouse(cef_window_delegate_t* self, struct _cef_window_t* window) {
        auto* h = reinterpret_cast<JniCefWindowDelegate*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(9) < 0) {return cef_state_t::STATE_DEFAULT;}
        cef_window_t* _p_window = window;
        if (_p_window) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_window); _b->add_ref(_b);}
        auto j_window_cls = env->FindClass("net/kurobako/cef4j/gen/views/CefWindow$NativePeer");
        auto j_window_ctor = env->GetMethodID(j_window_cls, "<init>", "(J)V");
        auto j_window = _p_window ? env->NewObject(j_window_cls, j_window_ctor, reinterpret_cast<jlong>(_p_window)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "acceptsFirstMouse", "(Lnet/kurobako/cef4j/gen/views/CefWindow;)Lnet/kurobako/cef4j/gen/CefState;");
        if (!mid) {env->PopLocalFrame(nullptr); return cef_state_t::STATE_DEFAULT;}
        auto jResult = (jobject)env->CallObjectMethod(h->javaHandler, mid, j_window);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return cef_state_t::STATE_DEFAULT;}
        cef_state_t nativeResult = static_cast<cef_state_t>(jResult ? env->GetLongField(jResult, env->GetFieldID(env->GetObjectClass(jResult), "value", "J")) : 0);
        env->PopLocalFrame(nullptr);
        return nativeResult;
    }

    static int CEF_CALLBACK _can_resize(cef_window_delegate_t* self, struct _cef_window_t* window) {
        auto* h = reinterpret_cast<JniCefWindowDelegate*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) {return false;}
        cef_window_t* _p_window = window;
        if (_p_window) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_window); _b->add_ref(_b);}
        auto j_window_cls = env->FindClass("net/kurobako/cef4j/gen/views/CefWindow$NativePeer");
        auto j_window_ctor = env->GetMethodID(j_window_cls, "<init>", "(J)V");
        auto j_window = _p_window ? env->NewObject(j_window_cls, j_window_ctor, reinterpret_cast<jlong>(_p_window)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "canResize", "(Lnet/kurobako/cef4j/gen/views/CefWindow;)Z");
        if (!mid) {env->PopLocalFrame(nullptr); return false;}
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, j_window);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return false;}
        env->PopLocalFrame(nullptr);
        return jResult;
    }

    static int CEF_CALLBACK _can_maximize(cef_window_delegate_t* self, struct _cef_window_t* window) {
        auto* h = reinterpret_cast<JniCefWindowDelegate*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) {return false;}
        cef_window_t* _p_window = window;
        if (_p_window) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_window); _b->add_ref(_b);}
        auto j_window_cls = env->FindClass("net/kurobako/cef4j/gen/views/CefWindow$NativePeer");
        auto j_window_ctor = env->GetMethodID(j_window_cls, "<init>", "(J)V");
        auto j_window = _p_window ? env->NewObject(j_window_cls, j_window_ctor, reinterpret_cast<jlong>(_p_window)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "canMaximize", "(Lnet/kurobako/cef4j/gen/views/CefWindow;)Z");
        if (!mid) {env->PopLocalFrame(nullptr); return false;}
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, j_window);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return false;}
        env->PopLocalFrame(nullptr);
        return jResult;
    }

    static int CEF_CALLBACK _can_minimize(cef_window_delegate_t* self, struct _cef_window_t* window) {
        auto* h = reinterpret_cast<JniCefWindowDelegate*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) {return false;}
        cef_window_t* _p_window = window;
        if (_p_window) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_window); _b->add_ref(_b);}
        auto j_window_cls = env->FindClass("net/kurobako/cef4j/gen/views/CefWindow$NativePeer");
        auto j_window_ctor = env->GetMethodID(j_window_cls, "<init>", "(J)V");
        auto j_window = _p_window ? env->NewObject(j_window_cls, j_window_ctor, reinterpret_cast<jlong>(_p_window)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "canMinimize", "(Lnet/kurobako/cef4j/gen/views/CefWindow;)Z");
        if (!mid) {env->PopLocalFrame(nullptr); return false;}
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, j_window);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return false;}
        env->PopLocalFrame(nullptr);
        return jResult;
    }

    static int CEF_CALLBACK _can_close(cef_window_delegate_t* self, struct _cef_window_t* window) {
        auto* h = reinterpret_cast<JniCefWindowDelegate*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) {return false;}
        cef_window_t* _p_window = window;
        if (_p_window) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_window); _b->add_ref(_b);}
        auto j_window_cls = env->FindClass("net/kurobako/cef4j/gen/views/CefWindow$NativePeer");
        auto j_window_ctor = env->GetMethodID(j_window_cls, "<init>", "(J)V");
        auto j_window = _p_window ? env->NewObject(j_window_cls, j_window_ctor, reinterpret_cast<jlong>(_p_window)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "canClose", "(Lnet/kurobako/cef4j/gen/views/CefWindow;)Z");
        if (!mid) {env->PopLocalFrame(nullptr); return false;}
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, j_window);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return false;}
        env->PopLocalFrame(nullptr);
        return jResult;
    }

    static int CEF_CALLBACK _on_accelerator(cef_window_delegate_t* self, struct _cef_window_t* window, int command_id) {
        auto* h = reinterpret_cast<JniCefWindowDelegate*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) {return false;}
        cef_window_t* _p_window = window;
        if (_p_window) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_window); _b->add_ref(_b);}
        auto j_window_cls = env->FindClass("net/kurobako/cef4j/gen/views/CefWindow$NativePeer");
        auto j_window_ctor = env->GetMethodID(j_window_cls, "<init>", "(J)V");
        auto j_window = _p_window ? env->NewObject(j_window_cls, j_window_ctor, reinterpret_cast<jlong>(_p_window)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onAccelerator", "(Lnet/kurobako/cef4j/gen/views/CefWindow;I)Z");
        if (!mid) {env->PopLocalFrame(nullptr); return false;}
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, j_window, static_cast<jint>(command_id));
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return false;}
        env->PopLocalFrame(nullptr);
        return jResult;
    }

    static int CEF_CALLBACK _on_key_event(cef_window_delegate_t* self, struct _cef_window_t* window, const cef_key_event_t* event) {
        auto* h = reinterpret_cast<JniCefWindowDelegate*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(14) < 0) {return false;}
        cef_window_t* _p_window = window;
        if (_p_window) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_window); _b->add_ref(_b);}
        auto j_window_cls = env->FindClass("net/kurobako/cef4j/gen/views/CefWindow$NativePeer");
        auto j_window_ctor = env->GetMethodID(j_window_cls, "<init>", "(J)V");
        auto j_window = _p_window ? env->NewObject(j_window_cls, j_window_ctor, reinterpret_cast<jlong>(_p_window)) : nullptr;
        auto _bv_event_type_cls = env->FindClass("net/kurobako/cef4j/gen/CefKeyEventType");
        auto _bv_event_type_of = env->GetStaticMethodID(_bv_event_type_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefKeyEventType;");
        auto _bv_event_type = env->CallStaticObjectMethod(_bv_event_type_cls, _bv_event_type_of, static_cast<jlong>(event->type));
        auto j_event_cls = env->FindClass("net/kurobako/cef4j/gen/CefKeyEvent");
        auto j_event_ctor = env->GetMethodID(j_event_cls, "<init>", "(Lnet/kurobako/cef4j/gen/CefKeyEventType;IIIICCI)V");
        auto j_event = event
        ? env->NewObject(j_event_cls, j_event_ctor,
                _bv_event_type,
                static_cast<jint>(event->modifiers),
                static_cast<jint>(event->windows_key_code),
                static_cast<jint>(event->native_key_code),
                static_cast<jint>(event->is_system_key),
                static_cast<jchar>(event->character),
                static_cast<jchar>(event->unmodified_character),
                static_cast<jint>(event->focus_on_editable_field))
        : nullptr;
        if (j_event) env->SetLongField(j_event, env->GetFieldID(j_event_cls, "size", "J"), static_cast<jlong>(event->size));
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onKeyEvent", "(Lnet/kurobako/cef4j/gen/views/CefWindow;Lnet/kurobako/cef4j/gen/CefKeyEvent;)Z");
        if (!mid) {env->PopLocalFrame(nullptr); return false;}
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, j_window, j_event);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return false;}
        env->PopLocalFrame(nullptr);
        return jResult;
    }

    static void CEF_CALLBACK _on_theme_colors_changed(cef_window_delegate_t* self, struct _cef_window_t* window, int chrome_theme) {
        auto* h = reinterpret_cast<JniCefWindowDelegate*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) {return;}
        cef_window_t* _p_window = window;
        if (_p_window) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_window); _b->add_ref(_b);}
        auto j_window_cls = env->FindClass("net/kurobako/cef4j/gen/views/CefWindow$NativePeer");
        auto j_window_ctor = env->GetMethodID(j_window_cls, "<init>", "(J)V");
        auto j_window = _p_window ? env->NewObject(j_window_cls, j_window_ctor, reinterpret_cast<jlong>(_p_window)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onThemeColorsChanged", "(Lnet/kurobako/cef4j/gen/views/CefWindow;Z)V");
        if (!mid) {env->PopLocalFrame(nullptr); return;}
        env->CallVoidMethod(h->javaHandler, mid, j_window, static_cast<jboolean>(chrome_theme));
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return;}
        env->PopLocalFrame(nullptr);
    }

    static cef_runtime_style_t CEF_CALLBACK _get_window_runtime_style(cef_window_delegate_t* self) {
        auto* h = reinterpret_cast<JniCefWindowDelegate*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(6) < 0) {return cef_runtime_style_t::CEF_RUNTIME_STYLE_DEFAULT;}
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "getWindowRuntimeStyle", "()Lnet/kurobako/cef4j/gen/CefRuntimeStyle;");
        if (!mid) {env->PopLocalFrame(nullptr); return cef_runtime_style_t::CEF_RUNTIME_STYLE_DEFAULT;}
        auto jResult = (jobject)env->CallObjectMethod(h->javaHandler, mid);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return cef_runtime_style_t::CEF_RUNTIME_STYLE_DEFAULT;}
        cef_runtime_style_t nativeResult = static_cast<cef_runtime_style_t>(jResult ? env->GetLongField(jResult, env->GetFieldID(env->GetObjectClass(jResult), "value", "J")) : 0);
        env->PopLocalFrame(nullptr);
        return nativeResult;
    }

    static int CEF_CALLBACK _get_linux_window_properties(cef_window_delegate_t* self, struct _cef_window_t* window, struct _cef_linux_window_properties_t* properties) {
        auto* h = reinterpret_cast<JniCefWindowDelegate*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(11) < 0) {return false;}
        cef_window_t* _p_window = window;
        if (_p_window) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_window); _b->add_ref(_b);}
        auto j_window_cls = env->FindClass("net/kurobako/cef4j/gen/views/CefWindow$NativePeer");
        auto j_window_ctor = env->GetMethodID(j_window_cls, "<init>", "(J)V");
        auto j_window = _p_window ? env->NewObject(j_window_cls, j_window_ctor, reinterpret_cast<jlong>(_p_window)) : nullptr;
        auto j_properties_cls = env->FindClass("net/kurobako/cef4j/gen/NativePointer");
        auto j_properties_ctor = env->GetMethodID(j_properties_cls, "<init>", "(J)V");
        auto j_properties = env->NewObject(j_properties_cls, j_properties_ctor, reinterpret_cast<jlong>(properties));
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "getLinuxWindowProperties", "(Lnet/kurobako/cef4j/gen/views/CefWindow;Lnet/kurobako/cef4j/gen/NativePointer;)Z");
        if (!mid) {env->PopLocalFrame(nullptr); return false;}
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, j_window, j_properties);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return false;}
        env->PopLocalFrame(nullptr);
        return jResult;
    }
};

extern "C" cef_window_delegate_t* Create_JniCefWindowDelegate(JNIEnv *env, jobject handler) {
    JavaVM *jvm;
    env->GetJavaVM(&jvm);
    auto globalRef = env->NewGlobalRef(handler);
    return reinterpret_cast<cef_window_delegate_t*>(new JniCefWindowDelegate(jvm, globalRef));
}
