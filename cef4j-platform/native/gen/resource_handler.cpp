// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
#include <jni.h>
#include "include/capi/cef_resource_handler_capi.h"
#include "include/capi/cef_callback_capi.h"
#include "include/capi/cef_request_capi.h"
#include "include/capi/cef_response_capi.h"
#include "jni_util.h"

#include <atomic>
#include "jni_util.h"

struct JniCefResourceHandler : public cef_resource_handler_t {
    JavaVM* jvm;
    jobject javaHandler;  // global ref
    std::atomic<int> refCount{1};

    JniCefResourceHandler(JavaVM* vm, jobject handler) : cef_resource_handler_t{}, jvm(vm) {
        javaHandler = handler;
        InitRefCount<JniCefResourceHandler, cef_resource_handler_t>(reinterpret_cast<cef_base_ref_counted_t*>(static_cast<cef_resource_handler_t*>(this)));
        open = &_open;
        process_request = &_process_request;
        get_response_headers = &_get_response_headers;
        skip = &_skip;
        read = &_read;
        read_response = &_read_response;
        cancel = &_cancel;
    }

    static int CEF_CALLBACK _open(cef_resource_handler_t* self, struct _cef_request_t* request, int* handle_request, struct _cef_callback_t* callback) {
        auto* h = reinterpret_cast<JniCefResourceHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(12) < 0) { return false; }
        cef_request_t* _p_request = request;
        if (_p_request) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_request); _b->add_ref(_b); }
        auto j_request_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefRequest$NativePeer");
        auto j_request_ctor = env->GetMethodID(j_request_cls, "<init>", "(J)V");
        auto j_request = _p_request ? env->NewObject(j_request_cls, j_request_ctor, reinterpret_cast<jlong>(_p_request)) : nullptr;
        jintArray j_handle_request = env->NewIntArray(1);
        if (handle_request) { jint _v = *handle_request; env->SetIntArrayRegion(j_handle_request, 0, 1, &_v); }
        cef_callback_t* _p_callback = callback;
        if (_p_callback) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_callback); _b->add_ref(_b); }
        auto j_callback_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefCallback$NativePeer");
        auto j_callback_ctor = env->GetMethodID(j_callback_cls, "<init>", "(J)V");
        auto j_callback = _p_callback ? env->NewObject(j_callback_cls, j_callback_ctor, reinterpret_cast<jlong>(_p_callback)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "open", "(Lnet/kurobako/cef4j/gen/CefRequest;[ILnet/kurobako/cef4j/gen/CefCallback;)Z");
        if (!mid) { env->PopLocalFrame(nullptr); return false; }
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, j_request, j_handle_request, j_callback);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return false; }
        if (handle_request) { jint _v; env->GetIntArrayRegion(j_handle_request, 0, 1, &_v); *handle_request = _v; }
        env->PopLocalFrame(nullptr);
        return jResult;
    }

    static int CEF_CALLBACK _process_request(cef_resource_handler_t* self, struct _cef_request_t* request, struct _cef_callback_t* callback) {
        auto* h = reinterpret_cast<JniCefResourceHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(11) < 0) { return false; }
        cef_request_t* _p_request = request;
        if (_p_request) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_request); _b->add_ref(_b); }
        auto j_request_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefRequest$NativePeer");
        auto j_request_ctor = env->GetMethodID(j_request_cls, "<init>", "(J)V");
        auto j_request = _p_request ? env->NewObject(j_request_cls, j_request_ctor, reinterpret_cast<jlong>(_p_request)) : nullptr;
        cef_callback_t* _p_callback = callback;
        if (_p_callback) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_callback); _b->add_ref(_b); }
        auto j_callback_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefCallback$NativePeer");
        auto j_callback_ctor = env->GetMethodID(j_callback_cls, "<init>", "(J)V");
        auto j_callback = _p_callback ? env->NewObject(j_callback_cls, j_callback_ctor, reinterpret_cast<jlong>(_p_callback)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "processRequest", "(Lnet/kurobako/cef4j/gen/CefRequest;Lnet/kurobako/cef4j/gen/CefCallback;)Z");
        if (!mid) { env->PopLocalFrame(nullptr); return false; }
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, j_request, j_callback);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return false; }
        env->PopLocalFrame(nullptr);
        return jResult;
    }

    static void CEF_CALLBACK _get_response_headers(cef_resource_handler_t* self, struct _cef_response_t* response, int64_t* response_length, cef_string_t* redirectUrl) {
        auto* h = reinterpret_cast<JniCefResourceHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(10) < 0) { return; }
        cef_response_t* _p_response = response;
        if (_p_response) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_response); _b->add_ref(_b); }
        auto j_response_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefResponse$NativePeer");
        auto j_response_ctor = env->GetMethodID(j_response_cls, "<init>", "(J)V");
        auto j_response = _p_response ? env->NewObject(j_response_cls, j_response_ctor, reinterpret_cast<jlong>(_p_response)) : nullptr;
        jlongArray j_response_length = env->NewLongArray(1);
        if (response_length) { jlong _v = static_cast<jlong>(*response_length); env->SetLongArrayRegion(j_response_length, 0, 1, &_v); }
        auto j_redirectUrl = CefStringToJString(env, redirectUrl);
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "getResponseHeaders", "(Lnet/kurobako/cef4j/gen/CefResponse;[JLjava/lang/String;)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, j_response, j_response_length, j_redirectUrl);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        if (response_length) { jlong _v; env->GetLongArrayRegion(j_response_length, 0, 1, &_v); *response_length = static_cast<int64_t>(_v); }
        env->PopLocalFrame(nullptr);
    }

    static int CEF_CALLBACK _skip(cef_resource_handler_t* self, int64_t bytes_to_skip, int64_t* bytes_skipped, struct _cef_resource_skip_callback_t* callback) {
        auto* h = reinterpret_cast<JniCefResourceHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(9) < 0) { return false; }
        jlongArray j_bytes_skipped = env->NewLongArray(1);
        if (bytes_skipped) { jlong _v = static_cast<jlong>(*bytes_skipped); env->SetLongArrayRegion(j_bytes_skipped, 0, 1, &_v); }
        cef_resource_skip_callback_t* _p_callback = callback;
        if (_p_callback) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_callback); _b->add_ref(_b); }
        auto j_callback_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefResourceSkipCallback$NativePeer");
        auto j_callback_ctor = env->GetMethodID(j_callback_cls, "<init>", "(J)V");
        auto j_callback = _p_callback ? env->NewObject(j_callback_cls, j_callback_ctor, reinterpret_cast<jlong>(_p_callback)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "skip", "(J[JLnet/kurobako/cef4j/gen/CefResourceSkipCallback;)Z");
        if (!mid) { env->PopLocalFrame(nullptr); return false; }
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, static_cast<jlong>(bytes_to_skip), j_bytes_skipped, j_callback);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return false; }
        if (bytes_skipped) { jlong _v; env->GetLongArrayRegion(j_bytes_skipped, 0, 1, &_v); *bytes_skipped = static_cast<int64_t>(_v); }
        env->PopLocalFrame(nullptr);
        return jResult;
    }

    static int CEF_CALLBACK _read(cef_resource_handler_t* self, void* data_out, int bytes_to_read, int* bytes_read, struct _cef_resource_read_callback_t* callback) {
        auto* h = reinterpret_cast<JniCefResourceHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(10) < 0) { return false; }
        jobject j_data_out = (bytes_to_read > 0 && data_out) ? env->NewDirectByteBuffer(static_cast<void*>(data_out), static_cast<jlong>(bytes_to_read)) : nullptr;
        jintArray j_bytes_read = env->NewIntArray(1);
        if (bytes_read) { jint _v = *bytes_read; env->SetIntArrayRegion(j_bytes_read, 0, 1, &_v); }
        cef_resource_read_callback_t* _p_callback = callback;
        if (_p_callback) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_callback); _b->add_ref(_b); }
        auto j_callback_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefResourceReadCallback$NativePeer");
        auto j_callback_ctor = env->GetMethodID(j_callback_cls, "<init>", "(J)V");
        auto j_callback = _p_callback ? env->NewObject(j_callback_cls, j_callback_ctor, reinterpret_cast<jlong>(_p_callback)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "read", "(Ljava/nio/ByteBuffer;[ILnet/kurobako/cef4j/gen/CefResourceReadCallback;)Z");
        if (!mid) { env->PopLocalFrame(nullptr); return false; }
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, j_data_out, j_bytes_read, j_callback);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return false; }
        if (bytes_read) { jint _v; env->GetIntArrayRegion(j_bytes_read, 0, 1, &_v); *bytes_read = _v; }
        env->PopLocalFrame(nullptr);
        return jResult;
    }

    static int CEF_CALLBACK _read_response(cef_resource_handler_t* self, void* data_out, int bytes_to_read, int* bytes_read, struct _cef_callback_t* callback) {
        auto* h = reinterpret_cast<JniCefResourceHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(10) < 0) { return false; }
        jobject j_data_out = (bytes_to_read > 0 && data_out) ? env->NewDirectByteBuffer(static_cast<void*>(data_out), static_cast<jlong>(bytes_to_read)) : nullptr;
        jintArray j_bytes_read = env->NewIntArray(1);
        if (bytes_read) { jint _v = *bytes_read; env->SetIntArrayRegion(j_bytes_read, 0, 1, &_v); }
        cef_callback_t* _p_callback = callback;
        if (_p_callback) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_callback); _b->add_ref(_b); }
        auto j_callback_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefCallback$NativePeer");
        auto j_callback_ctor = env->GetMethodID(j_callback_cls, "<init>", "(J)V");
        auto j_callback = _p_callback ? env->NewObject(j_callback_cls, j_callback_ctor, reinterpret_cast<jlong>(_p_callback)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "readResponse", "(Ljava/nio/ByteBuffer;[ILnet/kurobako/cef4j/gen/CefCallback;)Z");
        if (!mid) { env->PopLocalFrame(nullptr); return false; }
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, j_data_out, j_bytes_read, j_callback);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return false; }
        if (bytes_read) { jint _v; env->GetIntArrayRegion(j_bytes_read, 0, 1, &_v); *bytes_read = _v; }
        env->PopLocalFrame(nullptr);
        return jResult;
    }

    static void CEF_CALLBACK _cancel(cef_resource_handler_t* self) {
        auto* h = reinterpret_cast<JniCefResourceHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(5) < 0) { return; }
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "cancel", "()V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        env->PopLocalFrame(nullptr);
    }
};

extern "C" cef_resource_handler_t* Create_JniCefResourceHandler(JNIEnv* env, jobject handler) {
    JavaVM* jvm;
    env->GetJavaVM(&jvm);
    auto globalRef = env->NewGlobalRef(handler);
    return reinterpret_cast<cef_resource_handler_t*>(new JniCefResourceHandler(jvm, globalRef));
}
