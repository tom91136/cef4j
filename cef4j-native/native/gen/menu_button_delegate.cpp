// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
#include <jni.h>
#include "include/capi/views/cef_menu_button_delegate_capi.h"
#include "include/capi/views/cef_menu_button_capi.h"
#include "jni_util.h"

#include <atomic>
#include "jni_util.h"

struct JniCefMenuButtonDelegate: public cef_menu_button_delegate_t {
    JavaVM *jvm;
    jobject javaHandler;  // global ref
    std::atomic<int> refCount { 1 };

    JniCefMenuButtonDelegate(JavaVM *vm, jobject handler) : cef_menu_button_delegate_t { }, jvm(vm) {
        javaHandler = handler;
        InitRefCount<JniCefMenuButtonDelegate, cef_menu_button_delegate_t>(
                reinterpret_cast<cef_base_ref_counted_t*>(static_cast<cef_menu_button_delegate_t*>(this)));
        on_menu_button_pressed = &_on_menu_button_pressed;
    }

    static void CEF_CALLBACK _on_menu_button_pressed(cef_menu_button_delegate_t* self, struct _cef_menu_button_t* menu_button, const cef_point_t* screen_point, struct _cef_menu_button_pressed_lock_t* button_pressed_lock) {
        auto* h = reinterpret_cast<JniCefMenuButtonDelegate*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(14) < 0) {return;}
        cef_menu_button_t* _p_menu_button = menu_button;
        if (_p_menu_button) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_menu_button); _b->add_ref(_b);}
        auto j_menu_button_cls = env->FindClass("net/kurobako/cef4j/gen/views/CefMenuButton$NativePeer");
        auto j_menu_button_ctor = env->GetMethodID(j_menu_button_cls, "<init>", "(J)V");
        auto j_menu_button = _p_menu_button ? env->NewObject(j_menu_button_cls, j_menu_button_ctor, reinterpret_cast<jlong>(_p_menu_button)) : nullptr;
        auto j_screen_point_cls = env->FindClass("net/kurobako/cef4j/gen/CefPoint");
        auto j_screen_point_ctor = env->GetMethodID(j_screen_point_cls, "<init>", "(II)V");
        auto j_screen_point = screen_point ? env->NewObject(j_screen_point_cls, j_screen_point_ctor, static_cast<jint>(screen_point->x), static_cast<jint>(screen_point->y)) : nullptr;
        cef_menu_button_pressed_lock_t* _p_button_pressed_lock = button_pressed_lock;
        if (_p_button_pressed_lock) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_button_pressed_lock); _b->add_ref(_b);}
        auto j_button_pressed_lock_cls = env->FindClass("net/kurobako/cef4j/gen/views/CefMenuButtonPressedLock$NativePeer");
        auto j_button_pressed_lock_ctor = env->GetMethodID(j_button_pressed_lock_cls, "<init>", "(J)V");
        auto j_button_pressed_lock = _p_button_pressed_lock ? env->NewObject(j_button_pressed_lock_cls, j_button_pressed_lock_ctor, reinterpret_cast<jlong>(_p_button_pressed_lock)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onMenuButtonPressed", "(Lnet/kurobako/cef4j/gen/views/CefMenuButton;Lnet/kurobako/cef4j/gen/CefPoint;Lnet/kurobako/cef4j/gen/views/CefMenuButtonPressedLock;)V");
        if (!mid) {env->PopLocalFrame(nullptr); return;}
        env->CallVoidMethod(h->javaHandler, mid, j_menu_button, j_screen_point, j_button_pressed_lock);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return;}
        env->PopLocalFrame(nullptr);
    }
};

extern "C" cef_menu_button_delegate_t* Create_JniCefMenuButtonDelegate(JNIEnv *env, jobject handler) {
    JavaVM *jvm;
    env->GetJavaVM(&jvm);
    auto globalRef = env->NewGlobalRef(handler);
    return reinterpret_cast<cef_menu_button_delegate_t*>(new JniCefMenuButtonDelegate(jvm, globalRef));
}
