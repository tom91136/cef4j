// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
#include <jni.h>
#include "include/capi/cef_request_handler_capi.h"
#include "include/capi/cef_auth_callback_capi.h"
#include "include/capi/cef_browser_capi.h"
#include "include/capi/cef_callback_capi.h"
#include "include/capi/cef_frame_capi.h"
#include "include/capi/cef_request_capi.h"
#include "include/capi/cef_ssl_info_capi.h"
#include "include/capi/cef_unresponsive_process_callback_capi.h"
#include "include/capi/cef_x509_certificate_capi.h"
#include "jni_util.h"

#include <atomic>
#include "jni_util.h"

struct JniCefRequestHandler : public cef_request_handler_t {
    JavaVM* jvm;
    jobject javaHandler;  // global ref
    std::atomic<int> refCount{1};

    JniCefRequestHandler(JavaVM* vm, jobject handler) : cef_request_handler_t{}, jvm(vm) {
        javaHandler = handler;
        InitRefCount<JniCefRequestHandler, cef_request_handler_t>(reinterpret_cast<cef_base_ref_counted_t*>(static_cast<cef_request_handler_t*>(this)));
        on_before_browse = &_on_before_browse;
        on_open_urlfrom_tab = &_on_open_urlfrom_tab;
        get_auth_credentials = &_get_auth_credentials;
        on_certificate_error = &_on_certificate_error;
        on_select_client_certificate = &_on_select_client_certificate;
        on_render_view_ready = &_on_render_view_ready;
        on_render_process_unresponsive = &_on_render_process_unresponsive;
        on_render_process_responsive = &_on_render_process_responsive;
        on_render_process_terminated = &_on_render_process_terminated;
        on_document_available_in_main_frame = &_on_document_available_in_main_frame;
    }

    static int CEF_CALLBACK _on_before_browse(cef_request_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, struct _cef_request_t* request, int user_gesture, int is_redirect) {
        auto* h = reinterpret_cast<JniCefRequestHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(14) < 0) { return false; }
        cef_browser_t* _p_browser = browser;
        if (_p_browser) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b); }
        auto j_browser_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        cef_frame_t* _p_frame = frame;
        if (_p_frame) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_frame); _b->add_ref(_b); }
        auto j_frame_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefFrame$NativePeer");
        auto j_frame_ctor = env->GetMethodID(j_frame_cls, "<init>", "(J)V");
        auto j_frame = _p_frame ? env->NewObject(j_frame_cls, j_frame_ctor, reinterpret_cast<jlong>(_p_frame)) : nullptr;
        cef_request_t* _p_request = request;
        if (_p_request) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_request); _b->add_ref(_b); }
        auto j_request_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefRequest$NativePeer");
        auto j_request_ctor = env->GetMethodID(j_request_cls, "<init>", "(J)V");
        auto j_request = _p_request ? env->NewObject(j_request_cls, j_request_ctor, reinterpret_cast<jlong>(_p_request)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onBeforeBrowse", "(Lnet/kurobako/cef4j/gen/CefBrowser;Lnet/kurobako/cef4j/gen/CefFrame;Lnet/kurobako/cef4j/gen/CefRequest;ZZ)Z");
        if (!mid) { env->PopLocalFrame(nullptr); return false; }
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, j_browser, j_frame, j_request, static_cast<jboolean>(user_gesture), static_cast<jboolean>(is_redirect));
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return false; }
        env->PopLocalFrame(nullptr);
        return jResult;
    }

    static int CEF_CALLBACK _on_open_urlfrom_tab(cef_request_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, const cef_string_t* target_url, cef_window_open_disposition_t target_disposition, int user_gesture) {
        auto* h = reinterpret_cast<JniCefRequestHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(15) < 0) { return false; }
        cef_browser_t* _p_browser = browser;
        if (_p_browser) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b); }
        auto j_browser_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        cef_frame_t* _p_frame = frame;
        if (_p_frame) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_frame); _b->add_ref(_b); }
        auto j_frame_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefFrame$NativePeer");
        auto j_frame_ctor = env->GetMethodID(j_frame_cls, "<init>", "(J)V");
        auto j_frame = _p_frame ? env->NewObject(j_frame_cls, j_frame_ctor, reinterpret_cast<jlong>(_p_frame)) : nullptr;
        auto j_target_url = CefStringToJString(env, target_url);
        auto j_target_disposition_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefWindowOpenDisposition");
        auto j_target_disposition_from = env->GetStaticMethodID(j_target_disposition_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefWindowOpenDisposition;");
        auto j_target_disposition = env->CallStaticObjectMethod(j_target_disposition_cls, j_target_disposition_from, static_cast<jlong>(target_disposition));
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onOpenUrlFromTab", "(Lnet/kurobako/cef4j/gen/CefBrowser;Lnet/kurobako/cef4j/gen/CefFrame;Ljava/lang/String;Lnet/kurobako/cef4j/gen/CefWindowOpenDisposition;Z)Z");
        if (!mid) { env->PopLocalFrame(nullptr); return false; }
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, j_browser, j_frame, j_target_url, j_target_disposition, static_cast<jboolean>(user_gesture));
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return false; }
        env->PopLocalFrame(nullptr);
        return jResult;
    }

    static int CEF_CALLBACK _get_auth_credentials(cef_request_handler_t* self, struct _cef_browser_t* browser, const cef_string_t* origin_url, int isProxy, const cef_string_t* host, int port, const cef_string_t* realm, const cef_string_t* scheme, struct _cef_auth_callback_t* callback) {
        auto* h = reinterpret_cast<JniCefRequestHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(15) < 0) { return false; }
        cef_browser_t* _p_browser = browser;
        if (_p_browser) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b); }
        auto j_browser_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        auto j_origin_url = CefStringToJString(env, origin_url);
        auto j_host = CefStringToJString(env, host);
        auto j_realm = CefStringToJString(env, realm);
        auto j_scheme = CefStringToJString(env, scheme);
        cef_auth_callback_t* _p_callback = callback;
        if (_p_callback) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_callback); _b->add_ref(_b); }
        auto j_callback_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefAuthCallback$NativePeer");
        auto j_callback_ctor = env->GetMethodID(j_callback_cls, "<init>", "(J)V");
        auto j_callback = _p_callback ? env->NewObject(j_callback_cls, j_callback_ctor, reinterpret_cast<jlong>(_p_callback)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "getAuthCredentials", "(Lnet/kurobako/cef4j/gen/CefBrowser;Ljava/lang/String;ZLjava/lang/String;ILjava/lang/String;Ljava/lang/String;Lnet/kurobako/cef4j/gen/CefAuthCallback;)Z");
        if (!mid) { env->PopLocalFrame(nullptr); return false; }
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, j_browser, j_origin_url, static_cast<jboolean>(isProxy), j_host, static_cast<jint>(port), j_realm, j_scheme, j_callback);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return false; }
        env->PopLocalFrame(nullptr);
        return jResult;
    }

    static int CEF_CALLBACK _on_certificate_error(cef_request_handler_t* self, struct _cef_browser_t* browser, cef_errorcode_t cert_error, const cef_string_t* request_url, struct _cef_sslinfo_t* ssl_info, struct _cef_callback_t* callback) {
        auto* h = reinterpret_cast<JniCefRequestHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(18) < 0) { return false; }
        cef_browser_t* _p_browser = browser;
        if (_p_browser) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b); }
        auto j_browser_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        auto j_cert_error_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefErrorCode");
        auto j_cert_error_from = env->GetStaticMethodID(j_cert_error_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefErrorCode;");
        auto j_cert_error = env->CallStaticObjectMethod(j_cert_error_cls, j_cert_error_from, static_cast<jlong>(cert_error));
        auto j_request_url = CefStringToJString(env, request_url);
        cef_sslinfo_t* _p_ssl_info = ssl_info;
        if (_p_ssl_info) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_ssl_info); _b->add_ref(_b); }
        auto j_ssl_info_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefSslInfo$NativePeer");
        auto j_ssl_info_ctor = env->GetMethodID(j_ssl_info_cls, "<init>", "(J)V");
        auto j_ssl_info = _p_ssl_info ? env->NewObject(j_ssl_info_cls, j_ssl_info_ctor, reinterpret_cast<jlong>(_p_ssl_info)) : nullptr;
        cef_callback_t* _p_callback = callback;
        if (_p_callback) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_callback); _b->add_ref(_b); }
        auto j_callback_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefCallback$NativePeer");
        auto j_callback_ctor = env->GetMethodID(j_callback_cls, "<init>", "(J)V");
        auto j_callback = _p_callback ? env->NewObject(j_callback_cls, j_callback_ctor, reinterpret_cast<jlong>(_p_callback)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onCertificateError", "(Lnet/kurobako/cef4j/gen/CefBrowser;Lnet/kurobako/cef4j/gen/CefErrorCode;Ljava/lang/String;Lnet/kurobako/cef4j/gen/CefSslInfo;Lnet/kurobako/cef4j/gen/CefCallback;)Z");
        if (!mid) { env->PopLocalFrame(nullptr); return false; }
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, j_browser, j_cert_error, j_request_url, j_ssl_info, j_callback);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return false; }
        env->PopLocalFrame(nullptr);
        return jResult;
    }

    static int CEF_CALLBACK _on_select_client_certificate(cef_request_handler_t* self, struct _cef_browser_t* browser, int isProxy, const cef_string_t* host, int port, size_t certificatesCount, struct _cef_x509_certificate_t* const* certificates, struct _cef_select_client_certificate_callback_t* callback) {
        auto* h = reinterpret_cast<JniCefRequestHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(31) < 0) { return false; }
        cef_browser_t* _p_browser = browser;
        if (_p_browser) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b); }
        auto j_browser_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        auto j_host = CefStringToJString(env, host);
        auto j_certificates_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefX509Certificate$NativePeer");
        auto j_certificates_ctor = env->GetMethodID(j_certificates_cls, "<init>", "(J)V");
        jsize j_certificates_len = static_cast<jsize>(certificatesCount);
        auto j_certificates = env->NewObjectArray(j_certificates_len, j_certificates_cls, nullptr);
        for (jsize _i = 0; _i < j_certificates_len; _i++) {
            cef_x509_certificate_t* _elem = certificates[_i];
            if (_elem) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_elem); _b->add_ref(_b); }
            auto _jelem = _elem ? env->NewObject(j_certificates_cls, j_certificates_ctor, reinterpret_cast<jlong>(_elem)) : nullptr;
            env->SetObjectArrayElement(j_certificates, _i, _jelem);
        }
        cef_select_client_certificate_callback_t* _p_callback = callback;
        if (_p_callback) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_callback); _b->add_ref(_b); }
        auto j_callback_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefSelectClientCertificateCallback$NativePeer");
        auto j_callback_ctor = env->GetMethodID(j_callback_cls, "<init>", "(J)V");
        auto j_callback = _p_callback ? env->NewObject(j_callback_cls, j_callback_ctor, reinterpret_cast<jlong>(_p_callback)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onSelectClientCertificate", "(Lnet/kurobako/cef4j/gen/CefBrowser;ZLjava/lang/String;IJ[Lnet/kurobako/cef4j/gen/CefX509Certificate;Lnet/kurobako/cef4j/gen/CefSelectClientCertificateCallback;)Z");
        if (!mid) { env->PopLocalFrame(nullptr); return false; }
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, j_browser, static_cast<jboolean>(isProxy), j_host, static_cast<jint>(port), static_cast<jlong>(certificatesCount), j_certificates, j_callback);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return false; }
        env->PopLocalFrame(nullptr);
        return jResult;
    }

    static void CEF_CALLBACK _on_render_view_ready(cef_request_handler_t* self, struct _cef_browser_t* browser) {
        auto* h = reinterpret_cast<JniCefRequestHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) { return; }
        cef_browser_t* _p_browser = browser;
        if (_p_browser) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b); }
        auto j_browser_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onRenderViewReady", "(Lnet/kurobako/cef4j/gen/CefBrowser;)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, j_browser);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        env->PopLocalFrame(nullptr);
    }

    static int CEF_CALLBACK _on_render_process_unresponsive(cef_request_handler_t* self, struct _cef_browser_t* browser, struct _cef_unresponsive_process_callback_t* callback) {
        auto* h = reinterpret_cast<JniCefRequestHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(11) < 0) { return false; }
        cef_browser_t* _p_browser = browser;
        if (_p_browser) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b); }
        auto j_browser_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        cef_unresponsive_process_callback_t* _p_callback = callback;
        if (_p_callback) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_callback); _b->add_ref(_b); }
        auto j_callback_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefUnresponsiveProcessCallback$NativePeer");
        auto j_callback_ctor = env->GetMethodID(j_callback_cls, "<init>", "(J)V");
        auto j_callback = _p_callback ? env->NewObject(j_callback_cls, j_callback_ctor, reinterpret_cast<jlong>(_p_callback)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onRenderProcessUnresponsive", "(Lnet/kurobako/cef4j/gen/CefBrowser;Lnet/kurobako/cef4j/gen/CefUnresponsiveProcessCallback;)Z");
        if (!mid) { env->PopLocalFrame(nullptr); return false; }
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, j_browser, j_callback);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return false; }
        env->PopLocalFrame(nullptr);
        return jResult;
    }

    static void CEF_CALLBACK _on_render_process_responsive(cef_request_handler_t* self, struct _cef_browser_t* browser) {
        auto* h = reinterpret_cast<JniCefRequestHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) { return; }
        cef_browser_t* _p_browser = browser;
        if (_p_browser) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b); }
        auto j_browser_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onRenderProcessResponsive", "(Lnet/kurobako/cef4j/gen/CefBrowser;)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, j_browser);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        env->PopLocalFrame(nullptr);
    }

    static void CEF_CALLBACK _on_render_process_terminated(cef_request_handler_t* self, struct _cef_browser_t* browser, cef_termination_status_t status, int error_code, const cef_string_t* error_string) {
        auto* h = reinterpret_cast<JniCefRequestHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(12) < 0) { return; }
        cef_browser_t* _p_browser = browser;
        if (_p_browser) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b); }
        auto j_browser_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        auto j_status_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefTerminationStatus");
        auto j_status_from = env->GetStaticMethodID(j_status_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefTerminationStatus;");
        auto j_status = env->CallStaticObjectMethod(j_status_cls, j_status_from, static_cast<jlong>(status));
        auto j_error_string = CefStringToJString(env, error_string);
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onRenderProcessTerminated", "(Lnet/kurobako/cef4j/gen/CefBrowser;Lnet/kurobako/cef4j/gen/CefTerminationStatus;ILjava/lang/String;)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, j_browser, j_status, static_cast<jint>(error_code), j_error_string);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        env->PopLocalFrame(nullptr);
    }

    static void CEF_CALLBACK _on_document_available_in_main_frame(cef_request_handler_t* self, struct _cef_browser_t* browser) {
        auto* h = reinterpret_cast<JniCefRequestHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) { return; }
        cef_browser_t* _p_browser = browser;
        if (_p_browser) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b); }
        auto j_browser_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onDocumentAvailableInMainFrame", "(Lnet/kurobako/cef4j/gen/CefBrowser;)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, j_browser);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        env->PopLocalFrame(nullptr);
    }
};

extern "C" cef_request_handler_t* Create_JniCefRequestHandler(JNIEnv* env, jobject handler) {
    JavaVM* jvm;
    env->GetJavaVM(&jvm);
    auto globalRef = env->NewGlobalRef(handler);
    return reinterpret_cast<cef_request_handler_t*>(new JniCefRequestHandler(jvm, globalRef));
}
