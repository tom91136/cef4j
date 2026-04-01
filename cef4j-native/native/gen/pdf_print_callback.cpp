// GENERATED - do not edit.
#include <jni.h>
#include "include/capi/cef_browser_capi.h"
#include "jni_util.h"

#include <atomic>
#include "ref_counted_base.h"

// JNI wrapper struct for cef_pdf_print_callback_t
struct JniCefPdfPrintCallback: public cef_pdf_print_callback_t {
    JavaVM *jvm;
    jobject javaHandler;  // global ref
    std::atomic<int> refCount { 1 };

    JniCefPdfPrintCallback(JavaVM *vm, jobject handler) : cef_pdf_print_callback_t { }, jvm(vm) {
        javaHandler = handler;
        InitRefCount<JniCefPdfPrintCallback, cef_pdf_print_callback_t> (&base);
        on_pdf_print_finished = &_on_pdf_print_finished;
    }

    static void CEF_CALLBACK _on_pdf_print_finished(cef_pdf_print_callback_t* self, const cef_string_t* path, int ok) {
        auto* h = reinterpret_cast<JniCefPdfPrintCallback*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(6) < 0) {return;}
        auto j_path = CefStringToJString(env, path);
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onPdfPrintFinished", "(Ljava/lang/String;Z)V");
        if (!mid) {env->PopLocalFrame(nullptr); return;}
        env->CallVoidMethod(h->javaHandler, mid, j_path, static_cast<jboolean>(ok));
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return;}
        env->PopLocalFrame(nullptr);
    }
};

extern "C" cef_pdf_print_callback_t* Create_JniCefPdfPrintCallback(JNIEnv *env, jobject handler) {
    JavaVM *jvm;
    env->GetJavaVM(&jvm);
    auto globalRef = env->NewGlobalRef(handler);
    return reinterpret_cast<cef_pdf_print_callback_t*>(new JniCefPdfPrintCallback(jvm, globalRef));
}

extern "C" JNIEXPORT jlong JNICALL Java_net_kurobako_cef4j_gen_CefPdfPrintCallback_1N_N_1Create(JNIEnv* env, jobject obj) {
    return reinterpret_cast<jlong>(Create_JniCefPdfPrintCallback(env, obj));
}
