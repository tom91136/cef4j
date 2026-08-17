// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
#include <jni.h>
#include "include/capi/views/cef_button_delegate_capi.h"
#include "include/capi/views/cef_button_capi.h"
#include "jni_util.h"

#include <atomic>
#include "jni_util.h"

struct JniCefButtonDelegate : public cef_button_delegate_t {
    JavaVM* jvm;
    jobject javaHandler;  // global ref
    std::atomic<int> refCount{1};

    JniCefButtonDelegate(JavaVM* vm, jobject handler) : cef_button_delegate_t{}, jvm(vm) {
        javaHandler = handler;
        InitRefCount<JniCefButtonDelegate, cef_button_delegate_t>(reinterpret_cast<cef_base_ref_counted_t*>(static_cast<cef_button_delegate_t*>(this)));
        on_button_pressed = &_on_button_pressed;
        on_button_state_changed = &_on_button_state_changed;
    }

    static void CEF_CALLBACK _on_button_pressed(cef_button_delegate_t* self, struct _cef_button_t* button) {
        auto* h = reinterpret_cast<JniCefButtonDelegate*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) { return; }
        cef_button_t* _p_button = button;
        if (_p_button) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_button); _b->add_ref(_b); }
        auto j_button_cls = FindClassCached(env, "net/kurobako/cef4j/gen/views/CefButton$NativePeer");
        auto j_button_ctor = env->GetMethodID(j_button_cls, "<init>", "(J)V");
        auto j_button = _p_button ? env->NewObject(j_button_cls, j_button_ctor, reinterpret_cast<jlong>(_p_button)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onButtonPressed", "(Lnet/kurobako/cef4j/gen/views/CefButton;)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, j_button);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        env->PopLocalFrame(nullptr);
    }

    static void CEF_CALLBACK _on_button_state_changed(cef_button_delegate_t* self, struct _cef_button_t* button) {
        auto* h = reinterpret_cast<JniCefButtonDelegate*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) { return; }
        cef_button_t* _p_button = button;
        if (_p_button) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_button); _b->add_ref(_b); }
        auto j_button_cls = FindClassCached(env, "net/kurobako/cef4j/gen/views/CefButton$NativePeer");
        auto j_button_ctor = env->GetMethodID(j_button_cls, "<init>", "(J)V");
        auto j_button = _p_button ? env->NewObject(j_button_cls, j_button_ctor, reinterpret_cast<jlong>(_p_button)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onButtonStateChanged", "(Lnet/kurobako/cef4j/gen/views/CefButton;)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, j_button);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        env->PopLocalFrame(nullptr);
    }
};

extern "C" cef_button_delegate_t* Create_JniCefButtonDelegate(JNIEnv* env, jobject handler) {
    JavaVM* jvm;
    env->GetJavaVM(&jvm);
    auto globalRef = env->NewGlobalRef(handler);
    return reinterpret_cast<cef_button_delegate_t*>(new JniCefButtonDelegate(jvm, globalRef));
}
