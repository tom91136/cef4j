// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
#include <jni.h>
#include "include/capi/cef_download_handler_capi.h"
#include "include/capi/cef_browser_capi.h"
#include "include/capi/cef_download_item_capi.h"
#include "jni_util.h"

#include <atomic>
#include "jni_util.h"

struct JniCefDownloadHandler : public cef_download_handler_t {
    JavaVM* jvm;
    jobject javaHandler;  // global ref
    std::atomic<int> refCount{1};

    JniCefDownloadHandler(JavaVM* vm, jobject handler) : cef_download_handler_t{}, jvm(vm) {
        javaHandler = handler;
        InitRefCount<JniCefDownloadHandler, cef_download_handler_t>(reinterpret_cast<cef_base_ref_counted_t*>(static_cast<cef_download_handler_t*>(this)));
        can_download = &_can_download;
        on_before_download = &_on_before_download;
        on_download_updated = &_on_download_updated;
    }

    static int CEF_CALLBACK _can_download(cef_download_handler_t* self, struct _cef_browser_t* browser, const cef_string_t* url, const cef_string_t* request_method) {
        auto* h = reinterpret_cast<JniCefDownloadHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(10) < 0) { return false; }
        cef_browser_t* _p_browser = browser;
        if (_p_browser) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b); }
        auto j_browser_cls = env->FindClass("net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        auto j_url = CefStringToJString(env, url);
        auto j_request_method = CefStringToJString(env, request_method);
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "canDownload", "(Lnet/kurobako/cef4j/gen/CefBrowser;Ljava/lang/String;Ljava/lang/String;)Z");
        if (!mid) { env->PopLocalFrame(nullptr); return false; }
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, j_browser, j_url, j_request_method);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return false; }
        env->PopLocalFrame(nullptr);
        return jResult;
    }

    static int CEF_CALLBACK _on_before_download(cef_download_handler_t* self, struct _cef_browser_t* browser, struct _cef_download_item_t* download_item, const cef_string_t* suggested_name, struct _cef_before_download_callback_t* callback) {
        auto* h = reinterpret_cast<JniCefDownloadHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(15) < 0) { return false; }
        cef_browser_t* _p_browser = browser;
        if (_p_browser) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b); }
        auto j_browser_cls = env->FindClass("net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        cef_download_item_t* _p_download_item = download_item;
        if (_p_download_item) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_download_item); _b->add_ref(_b); }
        auto j_download_item_cls = env->FindClass("net/kurobako/cef4j/gen/CefDownloadItem$NativePeer");
        auto j_download_item_ctor = env->GetMethodID(j_download_item_cls, "<init>", "(J)V");
        auto j_download_item = _p_download_item ? env->NewObject(j_download_item_cls, j_download_item_ctor, reinterpret_cast<jlong>(_p_download_item)) : nullptr;
        auto j_suggested_name = CefStringToJString(env, suggested_name);
        cef_before_download_callback_t* _p_callback = callback;
        if (_p_callback) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_callback); _b->add_ref(_b); }
        auto j_callback_cls = env->FindClass("net/kurobako/cef4j/gen/CefBeforeDownloadCallback$NativePeer");
        auto j_callback_ctor = env->GetMethodID(j_callback_cls, "<init>", "(J)V");
        auto j_callback = _p_callback ? env->NewObject(j_callback_cls, j_callback_ctor, reinterpret_cast<jlong>(_p_callback)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onBeforeDownload", "(Lnet/kurobako/cef4j/gen/CefBrowser;Lnet/kurobako/cef4j/gen/CefDownloadItem;Ljava/lang/String;Lnet/kurobako/cef4j/gen/CefBeforeDownloadCallback;)Z");
        if (!mid) { env->PopLocalFrame(nullptr); return false; }
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, j_browser, j_download_item, j_suggested_name, j_callback);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return false; }
        env->PopLocalFrame(nullptr);
        return jResult;
    }

    static void CEF_CALLBACK _on_download_updated(cef_download_handler_t* self, struct _cef_browser_t* browser, struct _cef_download_item_t* download_item, struct _cef_download_item_callback_t* callback) {
        auto* h = reinterpret_cast<JniCefDownloadHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(14) < 0) { return; }
        cef_browser_t* _p_browser = browser;
        if (_p_browser) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b); }
        auto j_browser_cls = env->FindClass("net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        cef_download_item_t* _p_download_item = download_item;
        if (_p_download_item) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_download_item); _b->add_ref(_b); }
        auto j_download_item_cls = env->FindClass("net/kurobako/cef4j/gen/CefDownloadItem$NativePeer");
        auto j_download_item_ctor = env->GetMethodID(j_download_item_cls, "<init>", "(J)V");
        auto j_download_item = _p_download_item ? env->NewObject(j_download_item_cls, j_download_item_ctor, reinterpret_cast<jlong>(_p_download_item)) : nullptr;
        cef_download_item_callback_t* _p_callback = callback;
        if (_p_callback) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_callback); _b->add_ref(_b); }
        auto j_callback_cls = env->FindClass("net/kurobako/cef4j/gen/CefDownloadItemCallback$NativePeer");
        auto j_callback_ctor = env->GetMethodID(j_callback_cls, "<init>", "(J)V");
        auto j_callback = _p_callback ? env->NewObject(j_callback_cls, j_callback_ctor, reinterpret_cast<jlong>(_p_callback)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onDownloadUpdated", "(Lnet/kurobako/cef4j/gen/CefBrowser;Lnet/kurobako/cef4j/gen/CefDownloadItem;Lnet/kurobako/cef4j/gen/CefDownloadItemCallback;)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, j_browser, j_download_item, j_callback);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        env->PopLocalFrame(nullptr);
    }
};

extern "C" cef_download_handler_t* Create_JniCefDownloadHandler(JNIEnv* env, jobject handler) {
    JavaVM* jvm;
    env->GetJavaVM(&jvm);
    auto globalRef = env->NewGlobalRef(handler);
    return reinterpret_cast<cef_download_handler_t*>(new JniCefDownloadHandler(jvm, globalRef));
}
