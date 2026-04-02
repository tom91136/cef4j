// GENERATED - do not edit.
#include <jni.h>
#include "include/capi/cef_focus_handler_capi.h"
#include "include/capi/cef_browser_capi.h"
#include "jni_util.h"

#include <atomic>
#include "jni_util.h"

struct JniCefFocusHandler: public cef_focus_handler_t {
    JavaVM *jvm;
    jobject javaHandler;  // global ref
    std::atomic<int> refCount { 1 };

    JniCefFocusHandler(JavaVM *vm, jobject handler) : cef_focus_handler_t { }, jvm(vm) {
        javaHandler = handler;
        InitRefCount<JniCefFocusHandler, cef_focus_handler_t> (&base);
        on_take_focus = &_on_take_focus;
        on_set_focus = &_on_set_focus;
        on_got_focus = &_on_got_focus;
    }

    static void CEF_CALLBACK _on_take_focus(cef_focus_handler_t* self, struct _cef_browser_t* browser, int next) {
        auto* h = reinterpret_cast<JniCefFocusHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) {return;}
        cef_browser_t* _p_browser = browser;
        if (_p_browser) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b);}
        auto j_browser_cls = env->FindClass("net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onTakeFocus", "(Lnet/kurobako/cef4j/gen/CefBrowser;Z)V");
        if (!mid) {env->PopLocalFrame(nullptr); return;}
        env->CallVoidMethod(h->javaHandler, mid, j_browser, static_cast<jboolean>(next));
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return;}
        env->PopLocalFrame(nullptr);
    }

    static int CEF_CALLBACK _on_set_focus(cef_focus_handler_t* self, struct _cef_browser_t* browser, cef_focus_source_t source) {
        auto* h = reinterpret_cast<JniCefFocusHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(11) < 0) {return false;}
        cef_browser_t* _p_browser = browser;
        if (_p_browser) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b);}
        auto j_browser_cls = env->FindClass("net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        auto j_source_cls = env->FindClass("net/kurobako/cef4j/gen/CefFocusSource");
        auto j_source_from = env->GetStaticMethodID(j_source_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefFocusSource;");
        auto j_source = env->CallStaticObjectMethod(j_source_cls, j_source_from, static_cast<jlong>(source));
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onSetFocus", "(Lnet/kurobako/cef4j/gen/CefBrowser;Lnet/kurobako/cef4j/gen/CefFocusSource;)Z");
        if (!mid) {env->PopLocalFrame(nullptr); return false;}
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, j_browser, j_source);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return false;}
        env->PopLocalFrame(nullptr);
        return jResult;
    }

    static void CEF_CALLBACK _on_got_focus(cef_focus_handler_t* self, struct _cef_browser_t* browser) {
        auto* h = reinterpret_cast<JniCefFocusHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) {return;}
        cef_browser_t* _p_browser = browser;
        if (_p_browser) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b);}
        auto j_browser_cls = env->FindClass("net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onGotFocus", "(Lnet/kurobako/cef4j/gen/CefBrowser;)V");
        if (!mid) {env->PopLocalFrame(nullptr); return;}
        env->CallVoidMethod(h->javaHandler, mid, j_browser);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return;}
        env->PopLocalFrame(nullptr);
    }
};

extern "C" cef_focus_handler_t* Create_JniCefFocusHandler(JNIEnv *env, jobject handler) {
    JavaVM *jvm;
    env->GetJavaVM(&jvm);
    auto globalRef = env->NewGlobalRef(handler);
    return reinterpret_cast<cef_focus_handler_t*>(new JniCefFocusHandler(jvm, globalRef));
}

extern "C" JNIEXPORT jlong JNICALL Java_net_kurobako_cef4j_gen_CefFocusHandler_1N_N_1Create(JNIEnv* env, jobject obj) {
    return reinterpret_cast<jlong>(Create_JniCefFocusHandler(env, obj));
}
