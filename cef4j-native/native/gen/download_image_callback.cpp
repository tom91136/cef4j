// GENERATED - do not edit.
#include <jni.h>
#include "include/capi/cef_browser_capi.h"
#include "include/capi/cef_image_capi.h"
#include "jni_util.h"

#include <atomic>
#include "jni_util.h"

struct JniCefDownloadImageCallback: public cef_download_image_callback_t {
    JavaVM *jvm;
    jobject javaHandler;  // global ref
    std::atomic<int> refCount { 1 };

    JniCefDownloadImageCallback(JavaVM *vm, jobject handler) : cef_download_image_callback_t { }, jvm(vm) {
        javaHandler = handler;
        InitRefCount<JniCefDownloadImageCallback, cef_download_image_callback_t> (&base);
        on_download_image_finished = &_on_download_image_finished;
    }

    static void CEF_CALLBACK _on_download_image_finished(cef_download_image_callback_t* self, const cef_string_t* image_url, int http_status_code, struct _cef_image_t* image) {
        auto* h = reinterpret_cast<JniCefDownloadImageCallback*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(9) < 0) {return;}
        auto j_image_url = CefStringToJString(env, image_url);
        cef_image_t* _p_image = image;
        if (_p_image) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_image); _b->add_ref(_b);}
        auto j_image_cls = env->FindClass("net/kurobako/cef4j/gen/CefImage$NativePeer");
        auto j_image_ctor = env->GetMethodID(j_image_cls, "<init>", "(J)V");
        auto j_image = _p_image ? env->NewObject(j_image_cls, j_image_ctor, reinterpret_cast<jlong>(_p_image)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onDownloadImageFinished", "(Ljava/lang/String;ILnet/kurobako/cef4j/gen/CefImage;)V");
        if (!mid) {env->PopLocalFrame(nullptr); return;}
        env->CallVoidMethod(h->javaHandler, mid, j_image_url, static_cast<jint>(http_status_code), j_image);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return;}
        env->PopLocalFrame(nullptr);
    }
};

extern "C" cef_download_image_callback_t* Create_JniCefDownloadImageCallback(JNIEnv *env, jobject handler) {
    JavaVM *jvm;
    env->GetJavaVM(&jvm);
    auto globalRef = env->NewGlobalRef(handler);
    return reinterpret_cast<cef_download_image_callback_t*>(new JniCefDownloadImageCallback(jvm, globalRef));
}

extern "C" JNIEXPORT jlong JNICALL Java_net_kurobako_cef4j_gen_CefDownloadImageCallback_1N_N_1Create(JNIEnv* env, jobject obj) {
    return reinterpret_cast<jlong>(Create_JniCefDownloadImageCallback(env, obj));
}
