// GENERATED - do not edit.
#include <jni.h>
#include "include/capi/cef_menu_model_delegate_capi.h"
#include "include/capi/cef_menu_model_capi.h"
#include "jni_util.h"

#include <atomic>
#include "jni_util.h"

struct JniCefMenuModelDelegate: public cef_menu_model_delegate_t {
    JavaVM *jvm;
    jobject javaHandler;  // global ref
    std::atomic<int> refCount { 1 };

    JniCefMenuModelDelegate(JavaVM *vm, jobject handler) : cef_menu_model_delegate_t { }, jvm(vm) {
        javaHandler = handler;
        InitRefCount<JniCefMenuModelDelegate, cef_menu_model_delegate_t> (&base);
        execute_command = &_execute_command;
        mouse_outside_menu = &_mouse_outside_menu;
        unhandled_open_submenu = &_unhandled_open_submenu;
        unhandled_close_submenu = &_unhandled_close_submenu;
        menu_will_show = &_menu_will_show;
        menu_closed = &_menu_closed;
        format_label = &_format_label;
    }

    static void CEF_CALLBACK _execute_command(cef_menu_model_delegate_t* self, struct _cef_menu_model_t* menu_model, int command_id, cef_event_flags_t event_flags) {
        auto* h = reinterpret_cast<JniCefMenuModelDelegate*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(11) < 0) {return;}
        cef_menu_model_t* _p_menu_model = menu_model;
        if (_p_menu_model) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_menu_model); _b->add_ref(_b);}
        auto j_menu_model_cls = env->FindClass("net/kurobako/cef4j/gen/CefMenuModel$NativePeer");
        auto j_menu_model_ctor = env->GetMethodID(j_menu_model_cls, "<init>", "(J)V");
        auto j_menu_model = _p_menu_model ? env->NewObject(j_menu_model_cls, j_menu_model_ctor, reinterpret_cast<jlong>(_p_menu_model)) : nullptr;
        auto j_event_flags_cls = env->FindClass("net/kurobako/cef4j/gen/CefEventFlags");
        auto j_event_flags_from = env->GetStaticMethodID(j_event_flags_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefEventFlags;");
        auto j_event_flags = env->CallStaticObjectMethod(j_event_flags_cls, j_event_flags_from, static_cast<jlong>(event_flags));
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "executeCommand", "(Lnet/kurobako/cef4j/gen/CefMenuModel;ILnet/kurobako/cef4j/gen/CefEventFlags;)V");
        if (!mid) {env->PopLocalFrame(nullptr); return;}
        env->CallVoidMethod(h->javaHandler, mid, j_menu_model, static_cast<jint>(command_id), j_event_flags);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return;}
        env->PopLocalFrame(nullptr);
    }

    static void CEF_CALLBACK _mouse_outside_menu(cef_menu_model_delegate_t* self, struct _cef_menu_model_t* menu_model, const cef_point_t* screen_point) {
        auto* h = reinterpret_cast<JniCefMenuModelDelegate*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(11) < 0) {return;}
        cef_menu_model_t* _p_menu_model = menu_model;
        if (_p_menu_model) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_menu_model); _b->add_ref(_b);}
        auto j_menu_model_cls = env->FindClass("net/kurobako/cef4j/gen/CefMenuModel$NativePeer");
        auto j_menu_model_ctor = env->GetMethodID(j_menu_model_cls, "<init>", "(J)V");
        auto j_menu_model = _p_menu_model ? env->NewObject(j_menu_model_cls, j_menu_model_ctor, reinterpret_cast<jlong>(_p_menu_model)) : nullptr;
        auto j_screen_point_cls = env->FindClass("net/kurobako/cef4j/gen/CefPoint");
        auto j_screen_point_ctor = env->GetMethodID(j_screen_point_cls, "<init>", "(II)V");
        auto j_screen_point = screen_point ? env->NewObject(j_screen_point_cls, j_screen_point_ctor, static_cast<jint>(screen_point->x), static_cast<jint>(screen_point->y)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "mouseOutsideMenu", "(Lnet/kurobako/cef4j/gen/CefMenuModel;Lnet/kurobako/cef4j/gen/CefPoint;)V");
        if (!mid) {env->PopLocalFrame(nullptr); return;}
        env->CallVoidMethod(h->javaHandler, mid, j_menu_model, j_screen_point);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return;}
        env->PopLocalFrame(nullptr);
    }

    static void CEF_CALLBACK _unhandled_open_submenu(cef_menu_model_delegate_t* self, struct _cef_menu_model_t* menu_model, int is_rtl) {
        auto* h = reinterpret_cast<JniCefMenuModelDelegate*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) {return;}
        cef_menu_model_t* _p_menu_model = menu_model;
        if (_p_menu_model) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_menu_model); _b->add_ref(_b);}
        auto j_menu_model_cls = env->FindClass("net/kurobako/cef4j/gen/CefMenuModel$NativePeer");
        auto j_menu_model_ctor = env->GetMethodID(j_menu_model_cls, "<init>", "(J)V");
        auto j_menu_model = _p_menu_model ? env->NewObject(j_menu_model_cls, j_menu_model_ctor, reinterpret_cast<jlong>(_p_menu_model)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "unhandledOpenSubmenu", "(Lnet/kurobako/cef4j/gen/CefMenuModel;Z)V");
        if (!mid) {env->PopLocalFrame(nullptr); return;}
        env->CallVoidMethod(h->javaHandler, mid, j_menu_model, static_cast<jboolean>(is_rtl));
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return;}
        env->PopLocalFrame(nullptr);
    }

    static void CEF_CALLBACK _unhandled_close_submenu(cef_menu_model_delegate_t* self, struct _cef_menu_model_t* menu_model, int is_rtl) {
        auto* h = reinterpret_cast<JniCefMenuModelDelegate*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) {return;}
        cef_menu_model_t* _p_menu_model = menu_model;
        if (_p_menu_model) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_menu_model); _b->add_ref(_b);}
        auto j_menu_model_cls = env->FindClass("net/kurobako/cef4j/gen/CefMenuModel$NativePeer");
        auto j_menu_model_ctor = env->GetMethodID(j_menu_model_cls, "<init>", "(J)V");
        auto j_menu_model = _p_menu_model ? env->NewObject(j_menu_model_cls, j_menu_model_ctor, reinterpret_cast<jlong>(_p_menu_model)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "unhandledCloseSubmenu", "(Lnet/kurobako/cef4j/gen/CefMenuModel;Z)V");
        if (!mid) {env->PopLocalFrame(nullptr); return;}
        env->CallVoidMethod(h->javaHandler, mid, j_menu_model, static_cast<jboolean>(is_rtl));
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return;}
        env->PopLocalFrame(nullptr);
    }

    static void CEF_CALLBACK _menu_will_show(cef_menu_model_delegate_t* self, struct _cef_menu_model_t* menu_model) {
        auto* h = reinterpret_cast<JniCefMenuModelDelegate*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) {return;}
        cef_menu_model_t* _p_menu_model = menu_model;
        if (_p_menu_model) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_menu_model); _b->add_ref(_b);}
        auto j_menu_model_cls = env->FindClass("net/kurobako/cef4j/gen/CefMenuModel$NativePeer");
        auto j_menu_model_ctor = env->GetMethodID(j_menu_model_cls, "<init>", "(J)V");
        auto j_menu_model = _p_menu_model ? env->NewObject(j_menu_model_cls, j_menu_model_ctor, reinterpret_cast<jlong>(_p_menu_model)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "menuWillShow", "(Lnet/kurobako/cef4j/gen/CefMenuModel;)V");
        if (!mid) {env->PopLocalFrame(nullptr); return;}
        env->CallVoidMethod(h->javaHandler, mid, j_menu_model);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return;}
        env->PopLocalFrame(nullptr);
    }

    static void CEF_CALLBACK _menu_closed(cef_menu_model_delegate_t* self, struct _cef_menu_model_t* menu_model) {
        auto* h = reinterpret_cast<JniCefMenuModelDelegate*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) {return;}
        cef_menu_model_t* _p_menu_model = menu_model;
        if (_p_menu_model) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_menu_model); _b->add_ref(_b);}
        auto j_menu_model_cls = env->FindClass("net/kurobako/cef4j/gen/CefMenuModel$NativePeer");
        auto j_menu_model_ctor = env->GetMethodID(j_menu_model_cls, "<init>", "(J)V");
        auto j_menu_model = _p_menu_model ? env->NewObject(j_menu_model_cls, j_menu_model_ctor, reinterpret_cast<jlong>(_p_menu_model)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "menuClosed", "(Lnet/kurobako/cef4j/gen/CefMenuModel;)V");
        if (!mid) {env->PopLocalFrame(nullptr); return;}
        env->CallVoidMethod(h->javaHandler, mid, j_menu_model);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return;}
        env->PopLocalFrame(nullptr);
    }

    static int CEF_CALLBACK _format_label(cef_menu_model_delegate_t* self, struct _cef_menu_model_t* menu_model, cef_string_t* label) {
        auto* h = reinterpret_cast<JniCefMenuModelDelegate*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(9) < 0) {return false;}
        cef_menu_model_t* _p_menu_model = menu_model;
        if (_p_menu_model) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_menu_model); _b->add_ref(_b);}
        auto j_menu_model_cls = env->FindClass("net/kurobako/cef4j/gen/CefMenuModel$NativePeer");
        auto j_menu_model_ctor = env->GetMethodID(j_menu_model_cls, "<init>", "(J)V");
        auto j_menu_model = _p_menu_model ? env->NewObject(j_menu_model_cls, j_menu_model_ctor, reinterpret_cast<jlong>(_p_menu_model)) : nullptr;
        auto j_label = CefStringToJString(env, label);
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "formatLabel", "(Lnet/kurobako/cef4j/gen/CefMenuModel;Ljava/lang/String;)Z");
        if (!mid) {env->PopLocalFrame(nullptr); return false;}
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, j_menu_model, j_label);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return false;}
        env->PopLocalFrame(nullptr);
        return jResult;
    }
};

extern "C" cef_menu_model_delegate_t* Create_JniCefMenuModelDelegate(JNIEnv *env, jobject handler) {
    JavaVM *jvm;
    env->GetJavaVM(&jvm);
    auto globalRef = env->NewGlobalRef(handler);
    return reinterpret_cast<cef_menu_model_delegate_t*>(new JniCefMenuModelDelegate(jvm, globalRef));
}

extern "C" JNIEXPORT jlong JNICALL Java_net_kurobako_cef4j_gen_CefMenuModelDelegate_1N_N_1Create(JNIEnv* env, jobject obj) {
    return reinterpret_cast<jlong>(Create_JniCefMenuModelDelegate(env, obj));
}
