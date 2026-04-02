// GENERATED - do not edit.
#include <jni.h>
#include "include/capi/cef_find_handler_capi.h"
#include "include/capi/cef_browser_capi.h"
#include "jni_util.h"

#include <atomic>
#include "jni_util.h"

struct JniCefFindHandler: public cef_find_handler_t {
    JavaVM *jvm;
    jobject javaHandler;  // global ref
    std::atomic<int> refCount { 1 };

    JniCefFindHandler(JavaVM *vm, jobject handler) : cef_find_handler_t { }, jvm(vm) {
        javaHandler = handler;
        InitRefCount<JniCefFindHandler, cef_find_handler_t> (&base);
        on_find_result = &_on_find_result;
    }

    static void CEF_CALLBACK _on_find_result(cef_find_handler_t* self, struct _cef_browser_t* browser, int identifier, int count, const cef_rect_t* selectionRect, int activeMatchOrdinal, int finalUpdate) {
        auto* h = reinterpret_cast<JniCefFindHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(11) < 0) {return;}
        cef_browser_t* _p_browser = browser;
        if (_p_browser) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b);}
        auto j_browser_cls = env->FindClass("net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        auto j_selectionRect_cls = env->FindClass("net/kurobako/cef4j/gen/CefRect");
        auto j_selectionRect_ctor = env->GetMethodID(j_selectionRect_cls, "<init>", "(IIII)V");
        auto j_selectionRect = selectionRect ? env->NewObject(j_selectionRect_cls, j_selectionRect_ctor, static_cast<jint>(selectionRect->x), static_cast<jint>(selectionRect->y), static_cast<jint>(selectionRect->width), static_cast<jint>(selectionRect->height)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onFindResult", "(Lnet/kurobako/cef4j/gen/CefBrowser;IILnet/kurobako/cef4j/gen/CefRect;IZ)V");
        if (!mid) {env->PopLocalFrame(nullptr); return;}
        env->CallVoidMethod(h->javaHandler, mid, j_browser, static_cast<jint>(identifier), static_cast<jint>(count), j_selectionRect, static_cast<jint>(activeMatchOrdinal), static_cast<jboolean>(finalUpdate));
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return;}
        env->PopLocalFrame(nullptr);
    }
};

extern "C" cef_find_handler_t* Create_JniCefFindHandler(JNIEnv *env, jobject handler) {
    JavaVM *jvm;
    env->GetJavaVM(&jvm);
    auto globalRef = env->NewGlobalRef(handler);
    return reinterpret_cast<cef_find_handler_t*>(new JniCefFindHandler(jvm, globalRef));
}

extern "C" JNIEXPORT jlong JNICALL Java_net_kurobako_cef4j_gen_CefFindHandler_1N_N_1Create(JNIEnv* env, jobject obj) {
    return reinterpret_cast<jlong>(Create_JniCefFindHandler(env, obj));
}
