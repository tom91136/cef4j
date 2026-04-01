// GENERATED - do not edit.
#include <jni.h>
#include "include/capi/cef_print_handler_capi.h"
#include "include/capi/cef_browser_capi.h"
#include "include/capi/cef_print_settings_capi.h"
#include "jni_util.h"

#include <atomic>
#include "ref_counted_base.h"

// JNI wrapper struct for cef_print_handler_t
struct JniCefPrintHandler: public cef_print_handler_t {
    JavaVM *jvm;
    jobject javaHandler;  // global ref
    std::atomic<int> refCount { 1 };

    JniCefPrintHandler(JavaVM *vm, jobject handler) : cef_print_handler_t { }, jvm(vm) {
        javaHandler = handler;
        InitRefCount<JniCefPrintHandler, cef_print_handler_t> (&base);
        on_print_start = &_on_print_start;
        on_print_settings = &_on_print_settings;
        on_print_dialog = &_on_print_dialog;
        on_print_job = &_on_print_job;
        on_print_reset = &_on_print_reset;
        get_pdf_paper_size = &_get_pdf_paper_size;
    }

    static void CEF_CALLBACK _on_print_start(cef_print_handler_t* self, struct _cef_browser_t* browser) {
        auto* h = reinterpret_cast<JniCefPrintHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) {return;}
        cef_browser_t* _p_browser = browser;
        if (_p_browser) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b);}
        auto j_browser_cls = env->FindClass("net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onPrintStart", "(Lnet/kurobako/cef4j/gen/CefBrowser;)V");
        if (!mid) {env->PopLocalFrame(nullptr); return;}
        env->CallVoidMethod(h->javaHandler, mid, j_browser);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return;}
        env->PopLocalFrame(nullptr);
    }

    static void CEF_CALLBACK _on_print_settings(cef_print_handler_t* self, struct _cef_browser_t* browser, struct _cef_print_settings_t* settings, int get_defaults) {
        auto* h = reinterpret_cast<JniCefPrintHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(11) < 0) {return;}
        cef_browser_t* _p_browser = browser;
        if (_p_browser) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b);}
        auto j_browser_cls = env->FindClass("net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        cef_print_settings_t* _p_settings = settings;
        if (_p_settings) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_settings); _b->add_ref(_b);}
        auto j_settings_cls = env->FindClass("net/kurobako/cef4j/gen/CefPrintSettings$NativePeer");
        auto j_settings_ctor = env->GetMethodID(j_settings_cls, "<init>", "(J)V");
        auto j_settings = _p_settings ? env->NewObject(j_settings_cls, j_settings_ctor, reinterpret_cast<jlong>(_p_settings)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onPrintSettings", "(Lnet/kurobako/cef4j/gen/CefBrowser;Lnet/kurobako/cef4j/gen/CefPrintSettings;Z)V");
        if (!mid) {env->PopLocalFrame(nullptr); return;}
        env->CallVoidMethod(h->javaHandler, mid, j_browser, j_settings, static_cast<jboolean>(get_defaults));
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return;}
        env->PopLocalFrame(nullptr);
    }

    static int CEF_CALLBACK _on_print_dialog(cef_print_handler_t* self, struct _cef_browser_t* browser, int has_selection, struct _cef_print_dialog_callback_t* callback) {
        auto* h = reinterpret_cast<JniCefPrintHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(11) < 0) {return false;}
        cef_browser_t* _p_browser = browser;
        if (_p_browser) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b);}
        auto j_browser_cls = env->FindClass("net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        cef_print_dialog_callback_t* _p_callback = callback;
        if (_p_callback) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_callback); _b->add_ref(_b);}
        auto j_callback_cls = env->FindClass("net/kurobako/cef4j/gen/CefPrintDialogCallback$NativePeer");
        auto j_callback_ctor = env->GetMethodID(j_callback_cls, "<init>", "(J)V");
        auto j_callback = _p_callback ? env->NewObject(j_callback_cls, j_callback_ctor, reinterpret_cast<jlong>(_p_callback)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onPrintDialog", "(Lnet/kurobako/cef4j/gen/CefBrowser;ZLnet/kurobako/cef4j/gen/CefPrintDialogCallback;)Z");
        if (!mid) {env->PopLocalFrame(nullptr); return false;}
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, j_browser, static_cast<jboolean>(has_selection), j_callback);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return false;}
        env->PopLocalFrame(nullptr);
        return jResult;
    }

    static int CEF_CALLBACK _on_print_job(cef_print_handler_t* self, struct _cef_browser_t* browser, const cef_string_t* document_name, const cef_string_t* pdf_file_path, struct _cef_print_job_callback_t* callback) {
        auto* h = reinterpret_cast<JniCefPrintHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(13) < 0) {return false;}
        cef_browser_t* _p_browser = browser;
        if (_p_browser) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b);}
        auto j_browser_cls = env->FindClass("net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        auto j_document_name = CefStringToJString(env, document_name);
        auto j_pdf_file_path = CefStringToJString(env, pdf_file_path);
        cef_print_job_callback_t* _p_callback = callback;
        if (_p_callback) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_callback); _b->add_ref(_b);}
        auto j_callback_cls = env->FindClass("net/kurobako/cef4j/gen/CefPrintJobCallback$NativePeer");
        auto j_callback_ctor = env->GetMethodID(j_callback_cls, "<init>", "(J)V");
        auto j_callback = _p_callback ? env->NewObject(j_callback_cls, j_callback_ctor, reinterpret_cast<jlong>(_p_callback)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onPrintJob", "(Lnet/kurobako/cef4j/gen/CefBrowser;Ljava/lang/String;Ljava/lang/String;Lnet/kurobako/cef4j/gen/CefPrintJobCallback;)Z");
        if (!mid) {env->PopLocalFrame(nullptr); return false;}
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, j_browser, j_document_name, j_pdf_file_path, j_callback);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return false;}
        env->PopLocalFrame(nullptr);
        return jResult;
    }

    static void CEF_CALLBACK _on_print_reset(cef_print_handler_t* self, struct _cef_browser_t* browser) {
        auto* h = reinterpret_cast<JniCefPrintHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) {return;}
        cef_browser_t* _p_browser = browser;
        if (_p_browser) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b);}
        auto j_browser_cls = env->FindClass("net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onPrintReset", "(Lnet/kurobako/cef4j/gen/CefBrowser;)V");
        if (!mid) {env->PopLocalFrame(nullptr); return;}
        env->CallVoidMethod(h->javaHandler, mid, j_browser);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return;}
        env->PopLocalFrame(nullptr);
    }

    static cef_size_t CEF_CALLBACK _get_pdf_paper_size(cef_print_handler_t* self, struct _cef_browser_t* browser, int device_units_per_inch) {
        auto* h = reinterpret_cast<JniCefPrintHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) {return cef_size_t {};}
        cef_browser_t* _p_browser = browser;
        if (_p_browser) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b);}
        auto j_browser_cls = env->FindClass("net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "getPdfPaperSize", "(Lnet/kurobako/cef4j/gen/CefBrowser;I)Ljava/lang/Object;");
        if (!mid) {env->PopLocalFrame(nullptr); return cef_size_t {};}
        auto jResult = (jobject)env->CallObjectMethod(h->javaHandler, mid, j_browser, static_cast<jint>(device_units_per_inch));
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return cef_size_t {};}
        cef_size_t nativeResult = /* TODO: DataStruct by-value return (cef_size_t) */cef_size_t {};
        env->PopLocalFrame(nullptr);
        return nativeResult;
    }
};

extern "C" cef_print_handler_t* Create_JniCefPrintHandler(JNIEnv *env, jobject handler) {
    JavaVM *jvm;
    env->GetJavaVM(&jvm);
    auto globalRef = env->NewGlobalRef(handler);
    return reinterpret_cast<cef_print_handler_t*>(new JniCefPrintHandler(jvm, globalRef));
}

extern "C" JNIEXPORT jlong JNICALL Java_net_kurobako_cef4j_gen_CefPrintHandler_1N_N_1Create(JNIEnv* env, jobject obj) {
    return reinterpret_cast<jlong>(Create_JniCefPrintHandler(env, obj));
}
