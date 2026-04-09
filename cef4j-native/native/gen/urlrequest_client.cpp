// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
#include <jni.h>
#include "include/capi/cef_urlrequest_capi.h"
#include "include/capi/cef_auth_callback_capi.h"
#include "jni_util.h"

#include <atomic>
#include "jni_util.h"

struct JniCefUrlRequestClient : public cef_urlrequest_client_t {
    JavaVM* jvm;
    jobject javaHandler;  // global ref
    std::atomic<int> refCount{1};

    JniCefUrlRequestClient(JavaVM* vm, jobject handler) : cef_urlrequest_client_t{}, jvm(vm) {
        javaHandler = handler;
        InitRefCount<JniCefUrlRequestClient, cef_urlrequest_client_t>(reinterpret_cast<cef_base_ref_counted_t*>(static_cast<cef_urlrequest_client_t*>(this)));
        on_request_complete = &_on_request_complete;
        on_upload_progress = &_on_upload_progress;
        on_download_progress = &_on_download_progress;
        on_download_data = &_on_download_data;
        get_auth_credentials = &_get_auth_credentials;
    }

    static void CEF_CALLBACK _on_request_complete(cef_urlrequest_client_t* self, struct _cef_urlrequest_t* request) {
        auto* h = reinterpret_cast<JniCefUrlRequestClient*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) { return; }
        cef_urlrequest_t* _p_request = request;
        if (_p_request) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_request); _b->add_ref(_b); }
        auto j_request_cls = env->FindClass("net/kurobako/cef4j/gen/CefUrlRequest$NativePeer");
        auto j_request_ctor = env->GetMethodID(j_request_cls, "<init>", "(J)V");
        auto j_request = _p_request ? env->NewObject(j_request_cls, j_request_ctor, reinterpret_cast<jlong>(_p_request)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onRequestComplete", "(Lnet/kurobako/cef4j/gen/CefUrlRequest;)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, j_request);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        env->PopLocalFrame(nullptr);
    }

    static void CEF_CALLBACK _on_upload_progress(cef_urlrequest_client_t* self, struct _cef_urlrequest_t* request, int64_t current, int64_t total) {
        auto* h = reinterpret_cast<JniCefUrlRequestClient*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) { return; }
        cef_urlrequest_t* _p_request = request;
        if (_p_request) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_request); _b->add_ref(_b); }
        auto j_request_cls = env->FindClass("net/kurobako/cef4j/gen/CefUrlRequest$NativePeer");
        auto j_request_ctor = env->GetMethodID(j_request_cls, "<init>", "(J)V");
        auto j_request = _p_request ? env->NewObject(j_request_cls, j_request_ctor, reinterpret_cast<jlong>(_p_request)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onUploadProgress", "(Lnet/kurobako/cef4j/gen/CefUrlRequest;JJ)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, j_request, static_cast<jlong>(current), static_cast<jlong>(total));
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        env->PopLocalFrame(nullptr);
    }

    static void CEF_CALLBACK _on_download_progress(cef_urlrequest_client_t* self, struct _cef_urlrequest_t* request, int64_t current, int64_t total) {
        auto* h = reinterpret_cast<JniCefUrlRequestClient*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) { return; }
        cef_urlrequest_t* _p_request = request;
        if (_p_request) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_request); _b->add_ref(_b); }
        auto j_request_cls = env->FindClass("net/kurobako/cef4j/gen/CefUrlRequest$NativePeer");
        auto j_request_ctor = env->GetMethodID(j_request_cls, "<init>", "(J)V");
        auto j_request = _p_request ? env->NewObject(j_request_cls, j_request_ctor, reinterpret_cast<jlong>(_p_request)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onDownloadProgress", "(Lnet/kurobako/cef4j/gen/CefUrlRequest;JJ)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, j_request, static_cast<jlong>(current), static_cast<jlong>(total));
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        env->PopLocalFrame(nullptr);
    }

    static void CEF_CALLBACK _on_download_data(cef_urlrequest_client_t* self, struct _cef_urlrequest_t* request, const void* data, size_t data_length) {
        auto* h = reinterpret_cast<JniCefUrlRequestClient*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(9) < 0) { return; }
        cef_urlrequest_t* _p_request = request;
        if (_p_request) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_request); _b->add_ref(_b); }
        auto j_request_cls = env->FindClass("net/kurobako/cef4j/gen/CefUrlRequest$NativePeer");
        auto j_request_ctor = env->GetMethodID(j_request_cls, "<init>", "(J)V");
        auto j_request = _p_request ? env->NewObject(j_request_cls, j_request_ctor, reinterpret_cast<jlong>(_p_request)) : nullptr;
        jobject j_data = (data_length > 0 && data) ? env->NewDirectByteBuffer(const_cast<void*>(static_cast<const void*>(data)), static_cast<jlong>(data_length)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onDownloadData", "(Lnet/kurobako/cef4j/gen/CefUrlRequest;Ljava/nio/ByteBuffer;)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, j_request, j_data);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        env->PopLocalFrame(nullptr);
    }

    static int CEF_CALLBACK _get_auth_credentials(cef_urlrequest_client_t* self, int isProxy, const cef_string_t* host, int port, const cef_string_t* realm, const cef_string_t* scheme, struct _cef_auth_callback_t* callback) {
        auto* h = reinterpret_cast<JniCefUrlRequestClient*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(11) < 0) { return false; }
        auto j_host = CefStringToJString(env, host);
        auto j_realm = CefStringToJString(env, realm);
        auto j_scheme = CefStringToJString(env, scheme);
        cef_auth_callback_t* _p_callback = callback;
        if (_p_callback) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_callback); _b->add_ref(_b); }
        auto j_callback_cls = env->FindClass("net/kurobako/cef4j/gen/CefAuthCallback$NativePeer");
        auto j_callback_ctor = env->GetMethodID(j_callback_cls, "<init>", "(J)V");
        auto j_callback = _p_callback ? env->NewObject(j_callback_cls, j_callback_ctor, reinterpret_cast<jlong>(_p_callback)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "getAuthCredentials", "(ZLjava/lang/String;ILjava/lang/String;Ljava/lang/String;Lnet/kurobako/cef4j/gen/CefAuthCallback;)Z");
        if (!mid) { env->PopLocalFrame(nullptr); return false; }
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, static_cast<jboolean>(isProxy), j_host, static_cast<jint>(port), j_realm, j_scheme, j_callback);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return false; }
        env->PopLocalFrame(nullptr);
        return jResult;
    }
};

extern "C" cef_urlrequest_client_t* Create_JniCefUrlRequestClient(JNIEnv* env, jobject handler) {
    JavaVM* jvm;
    env->GetJavaVM(&jvm);
    auto globalRef = env->NewGlobalRef(handler);
    return reinterpret_cast<cef_urlrequest_client_t*>(new JniCefUrlRequestClient(jvm, globalRef));
}
