// GENERATED - do not edit.
#include <jni.h>
#include "include/capi/cef_frame_handler_capi.h"
#include "include/capi/cef_browser_capi.h"
#include "include/capi/cef_frame_capi.h"
#include "jni_util.h"

#include <atomic>
#include "jni_util.h"

struct JniCefFrameHandler: public cef_frame_handler_t {
    JavaVM *jvm;
    jobject javaHandler;  // global ref
    std::atomic<int> refCount { 1 };

    JniCefFrameHandler(JavaVM *vm, jobject handler) : cef_frame_handler_t { }, jvm(vm) {
        javaHandler = handler;
        InitRefCount<JniCefFrameHandler, cef_frame_handler_t> (&base);
        on_frame_created = &_on_frame_created;
        on_frame_destroyed = &_on_frame_destroyed;
        on_frame_attached = &_on_frame_attached;
        on_frame_detached = &_on_frame_detached;
        on_main_frame_changed = &_on_main_frame_changed;
    }

    static void CEF_CALLBACK _on_frame_created(cef_frame_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame) {
        auto* h = reinterpret_cast<JniCefFrameHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(11) < 0) {return;}
        cef_browser_t* _p_browser = browser;
        if (_p_browser) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b);}
        auto j_browser_cls = env->FindClass("net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        cef_frame_t* _p_frame = frame;
        if (_p_frame) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_frame); _b->add_ref(_b);}
        auto j_frame_cls = env->FindClass("net/kurobako/cef4j/gen/CefFrame$NativePeer");
        auto j_frame_ctor = env->GetMethodID(j_frame_cls, "<init>", "(J)V");
        auto j_frame = _p_frame ? env->NewObject(j_frame_cls, j_frame_ctor, reinterpret_cast<jlong>(_p_frame)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onFrameCreated", "(Lnet/kurobako/cef4j/gen/CefBrowser;Lnet/kurobako/cef4j/gen/CefFrame;)V");
        if (!mid) {env->PopLocalFrame(nullptr); return;}
        env->CallVoidMethod(h->javaHandler, mid, j_browser, j_frame);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return;}
        env->PopLocalFrame(nullptr);
    }

    static void CEF_CALLBACK _on_frame_destroyed(cef_frame_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame) {
        auto* h = reinterpret_cast<JniCefFrameHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(11) < 0) {return;}
        cef_browser_t* _p_browser = browser;
        if (_p_browser) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b);}
        auto j_browser_cls = env->FindClass("net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        cef_frame_t* _p_frame = frame;
        if (_p_frame) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_frame); _b->add_ref(_b);}
        auto j_frame_cls = env->FindClass("net/kurobako/cef4j/gen/CefFrame$NativePeer");
        auto j_frame_ctor = env->GetMethodID(j_frame_cls, "<init>", "(J)V");
        auto j_frame = _p_frame ? env->NewObject(j_frame_cls, j_frame_ctor, reinterpret_cast<jlong>(_p_frame)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onFrameDestroyed", "(Lnet/kurobako/cef4j/gen/CefBrowser;Lnet/kurobako/cef4j/gen/CefFrame;)V");
        if (!mid) {env->PopLocalFrame(nullptr); return;}
        env->CallVoidMethod(h->javaHandler, mid, j_browser, j_frame);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return;}
        env->PopLocalFrame(nullptr);
    }

    static void CEF_CALLBACK _on_frame_attached(cef_frame_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, int reattached) {
        auto* h = reinterpret_cast<JniCefFrameHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(11) < 0) {return;}
        cef_browser_t* _p_browser = browser;
        if (_p_browser) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b);}
        auto j_browser_cls = env->FindClass("net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        cef_frame_t* _p_frame = frame;
        if (_p_frame) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_frame); _b->add_ref(_b);}
        auto j_frame_cls = env->FindClass("net/kurobako/cef4j/gen/CefFrame$NativePeer");
        auto j_frame_ctor = env->GetMethodID(j_frame_cls, "<init>", "(J)V");
        auto j_frame = _p_frame ? env->NewObject(j_frame_cls, j_frame_ctor, reinterpret_cast<jlong>(_p_frame)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onFrameAttached", "(Lnet/kurobako/cef4j/gen/CefBrowser;Lnet/kurobako/cef4j/gen/CefFrame;Z)V");
        if (!mid) {env->PopLocalFrame(nullptr); return;}
        env->CallVoidMethod(h->javaHandler, mid, j_browser, j_frame, static_cast<jboolean>(reattached));
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return;}
        env->PopLocalFrame(nullptr);
    }

    static void CEF_CALLBACK _on_frame_detached(cef_frame_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame) {
        auto* h = reinterpret_cast<JniCefFrameHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(11) < 0) {return;}
        cef_browser_t* _p_browser = browser;
        if (_p_browser) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b);}
        auto j_browser_cls = env->FindClass("net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        cef_frame_t* _p_frame = frame;
        if (_p_frame) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_frame); _b->add_ref(_b);}
        auto j_frame_cls = env->FindClass("net/kurobako/cef4j/gen/CefFrame$NativePeer");
        auto j_frame_ctor = env->GetMethodID(j_frame_cls, "<init>", "(J)V");
        auto j_frame = _p_frame ? env->NewObject(j_frame_cls, j_frame_ctor, reinterpret_cast<jlong>(_p_frame)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onFrameDetached", "(Lnet/kurobako/cef4j/gen/CefBrowser;Lnet/kurobako/cef4j/gen/CefFrame;)V");
        if (!mid) {env->PopLocalFrame(nullptr); return;}
        env->CallVoidMethod(h->javaHandler, mid, j_browser, j_frame);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return;}
        env->PopLocalFrame(nullptr);
    }

    static void CEF_CALLBACK _on_main_frame_changed(cef_frame_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* old_frame, struct _cef_frame_t* new_frame) {
        auto* h = reinterpret_cast<JniCefFrameHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(14) < 0) {return;}
        cef_browser_t* _p_browser = browser;
        if (_p_browser) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b);}
        auto j_browser_cls = env->FindClass("net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        cef_frame_t* _p_old_frame = old_frame;
        if (_p_old_frame) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_old_frame); _b->add_ref(_b);}
        auto j_old_frame_cls = env->FindClass("net/kurobako/cef4j/gen/CefFrame$NativePeer");
        auto j_old_frame_ctor = env->GetMethodID(j_old_frame_cls, "<init>", "(J)V");
        auto j_old_frame = _p_old_frame ? env->NewObject(j_old_frame_cls, j_old_frame_ctor, reinterpret_cast<jlong>(_p_old_frame)) : nullptr;
        cef_frame_t* _p_new_frame = new_frame;
        if (_p_new_frame) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_new_frame); _b->add_ref(_b);}
        auto j_new_frame_cls = env->FindClass("net/kurobako/cef4j/gen/CefFrame$NativePeer");
        auto j_new_frame_ctor = env->GetMethodID(j_new_frame_cls, "<init>", "(J)V");
        auto j_new_frame = _p_new_frame ? env->NewObject(j_new_frame_cls, j_new_frame_ctor, reinterpret_cast<jlong>(_p_new_frame)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onMainFrameChanged", "(Lnet/kurobako/cef4j/gen/CefBrowser;Lnet/kurobako/cef4j/gen/CefFrame;Lnet/kurobako/cef4j/gen/CefFrame;)V");
        if (!mid) {env->PopLocalFrame(nullptr); return;}
        env->CallVoidMethod(h->javaHandler, mid, j_browser, j_old_frame, j_new_frame);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return;}
        env->PopLocalFrame(nullptr);
    }
};

extern "C" cef_frame_handler_t* Create_JniCefFrameHandler(JNIEnv *env, jobject handler) {
    JavaVM *jvm;
    env->GetJavaVM(&jvm);
    auto globalRef = env->NewGlobalRef(handler);
    return reinterpret_cast<cef_frame_handler_t*>(new JniCefFrameHandler(jvm, globalRef));
}

extern "C" JNIEXPORT jlong JNICALL Java_net_kurobako_cef4j_gen_CefFrameHandler_1N_N_1Create(JNIEnv* env, jobject obj) {
    return reinterpret_cast<jlong>(Create_JniCefFrameHandler(env, obj));
}
