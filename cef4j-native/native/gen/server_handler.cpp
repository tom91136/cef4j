// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
#include <jni.h>
#include "include/capi/cef_server_capi.h"
#include "include/capi/cef_callback_capi.h"
#include "include/capi/cef_request_capi.h"
#include "jni_util.h"

#include <atomic>
#include "jni_util.h"

struct JniCefServerHandler: public cef_server_handler_t {
    JavaVM *jvm;
    jobject javaHandler;  // global ref
    std::atomic<int> refCount { 1 };

    JniCefServerHandler(JavaVM *vm, jobject handler) : cef_server_handler_t { }, jvm(vm) {
        javaHandler = handler;
        InitRefCount<JniCefServerHandler, cef_server_handler_t> (&base);
        on_server_created = &_on_server_created;
        on_server_destroyed = &_on_server_destroyed;
        on_client_connected = &_on_client_connected;
        on_client_disconnected = &_on_client_disconnected;
        on_http_request = &_on_http_request;
        on_web_socket_request = &_on_web_socket_request;
        on_web_socket_connected = &_on_web_socket_connected;
        on_web_socket_message = &_on_web_socket_message;
    }

    static void CEF_CALLBACK _on_server_created(cef_server_handler_t* self, struct _cef_server_t* server) {
        auto* h = reinterpret_cast<JniCefServerHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) {return;}
        cef_server_t* _p_server = server;
        if (_p_server) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_server); _b->add_ref(_b);}
        auto j_server_cls = env->FindClass("net/kurobako/cef4j/gen/CefServer$NativePeer");
        auto j_server_ctor = env->GetMethodID(j_server_cls, "<init>", "(J)V");
        auto j_server = _p_server ? env->NewObject(j_server_cls, j_server_ctor, reinterpret_cast<jlong>(_p_server)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onServerCreated", "(Lnet/kurobako/cef4j/gen/CefServer;)V");
        if (!mid) {env->PopLocalFrame(nullptr); return;}
        env->CallVoidMethod(h->javaHandler, mid, j_server);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return;}
        env->PopLocalFrame(nullptr);
    }

    static void CEF_CALLBACK _on_server_destroyed(cef_server_handler_t* self, struct _cef_server_t* server) {
        auto* h = reinterpret_cast<JniCefServerHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) {return;}
        cef_server_t* _p_server = server;
        if (_p_server) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_server); _b->add_ref(_b);}
        auto j_server_cls = env->FindClass("net/kurobako/cef4j/gen/CefServer$NativePeer");
        auto j_server_ctor = env->GetMethodID(j_server_cls, "<init>", "(J)V");
        auto j_server = _p_server ? env->NewObject(j_server_cls, j_server_ctor, reinterpret_cast<jlong>(_p_server)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onServerDestroyed", "(Lnet/kurobako/cef4j/gen/CefServer;)V");
        if (!mid) {env->PopLocalFrame(nullptr); return;}
        env->CallVoidMethod(h->javaHandler, mid, j_server);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return;}
        env->PopLocalFrame(nullptr);
    }

    static void CEF_CALLBACK _on_client_connected(cef_server_handler_t* self, struct _cef_server_t* server, int connection_id) {
        auto* h = reinterpret_cast<JniCefServerHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) {return;}
        cef_server_t* _p_server = server;
        if (_p_server) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_server); _b->add_ref(_b);}
        auto j_server_cls = env->FindClass("net/kurobako/cef4j/gen/CefServer$NativePeer");
        auto j_server_ctor = env->GetMethodID(j_server_cls, "<init>", "(J)V");
        auto j_server = _p_server ? env->NewObject(j_server_cls, j_server_ctor, reinterpret_cast<jlong>(_p_server)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onClientConnected", "(Lnet/kurobako/cef4j/gen/CefServer;I)V");
        if (!mid) {env->PopLocalFrame(nullptr); return;}
        env->CallVoidMethod(h->javaHandler, mid, j_server, static_cast<jint>(connection_id));
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return;}
        env->PopLocalFrame(nullptr);
    }

    static void CEF_CALLBACK _on_client_disconnected(cef_server_handler_t* self, struct _cef_server_t* server, int connection_id) {
        auto* h = reinterpret_cast<JniCefServerHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) {return;}
        cef_server_t* _p_server = server;
        if (_p_server) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_server); _b->add_ref(_b);}
        auto j_server_cls = env->FindClass("net/kurobako/cef4j/gen/CefServer$NativePeer");
        auto j_server_ctor = env->GetMethodID(j_server_cls, "<init>", "(J)V");
        auto j_server = _p_server ? env->NewObject(j_server_cls, j_server_ctor, reinterpret_cast<jlong>(_p_server)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onClientDisconnected", "(Lnet/kurobako/cef4j/gen/CefServer;I)V");
        if (!mid) {env->PopLocalFrame(nullptr); return;}
        env->CallVoidMethod(h->javaHandler, mid, j_server, static_cast<jint>(connection_id));
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return;}
        env->PopLocalFrame(nullptr);
    }

    static void CEF_CALLBACK _on_http_request(cef_server_handler_t* self, struct _cef_server_t* server, int connection_id, const cef_string_t* client_address, struct _cef_request_t* request) {
        auto* h = reinterpret_cast<JniCefServerHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(12) < 0) {return;}
        cef_server_t* _p_server = server;
        if (_p_server) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_server); _b->add_ref(_b);}
        auto j_server_cls = env->FindClass("net/kurobako/cef4j/gen/CefServer$NativePeer");
        auto j_server_ctor = env->GetMethodID(j_server_cls, "<init>", "(J)V");
        auto j_server = _p_server ? env->NewObject(j_server_cls, j_server_ctor, reinterpret_cast<jlong>(_p_server)) : nullptr;
        auto j_client_address = CefStringToJString(env, client_address);
        cef_request_t* _p_request = request;
        if (_p_request) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_request); _b->add_ref(_b);}
        auto j_request_cls = env->FindClass("net/kurobako/cef4j/gen/CefRequest$NativePeer");
        auto j_request_ctor = env->GetMethodID(j_request_cls, "<init>", "(J)V");
        auto j_request = _p_request ? env->NewObject(j_request_cls, j_request_ctor, reinterpret_cast<jlong>(_p_request)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onHttpRequest", "(Lnet/kurobako/cef4j/gen/CefServer;ILjava/lang/String;Lnet/kurobako/cef4j/gen/CefRequest;)V");
        if (!mid) {env->PopLocalFrame(nullptr); return;}
        env->CallVoidMethod(h->javaHandler, mid, j_server, static_cast<jint>(connection_id), j_client_address, j_request);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return;}
        env->PopLocalFrame(nullptr);
    }

    static void CEF_CALLBACK _on_web_socket_request(cef_server_handler_t* self, struct _cef_server_t* server, int connection_id, const cef_string_t* client_address, struct _cef_request_t* request, struct _cef_callback_t* callback) {
        auto* h = reinterpret_cast<JniCefServerHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(15) < 0) {return;}
        cef_server_t* _p_server = server;
        if (_p_server) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_server); _b->add_ref(_b);}
        auto j_server_cls = env->FindClass("net/kurobako/cef4j/gen/CefServer$NativePeer");
        auto j_server_ctor = env->GetMethodID(j_server_cls, "<init>", "(J)V");
        auto j_server = _p_server ? env->NewObject(j_server_cls, j_server_ctor, reinterpret_cast<jlong>(_p_server)) : nullptr;
        auto j_client_address = CefStringToJString(env, client_address);
        cef_request_t* _p_request = request;
        if (_p_request) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_request); _b->add_ref(_b);}
        auto j_request_cls = env->FindClass("net/kurobako/cef4j/gen/CefRequest$NativePeer");
        auto j_request_ctor = env->GetMethodID(j_request_cls, "<init>", "(J)V");
        auto j_request = _p_request ? env->NewObject(j_request_cls, j_request_ctor, reinterpret_cast<jlong>(_p_request)) : nullptr;
        cef_callback_t* _p_callback = callback;
        if (_p_callback) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_callback); _b->add_ref(_b);}
        auto j_callback_cls = env->FindClass("net/kurobako/cef4j/gen/CefCallback$NativePeer");
        auto j_callback_ctor = env->GetMethodID(j_callback_cls, "<init>", "(J)V");
        auto j_callback = _p_callback ? env->NewObject(j_callback_cls, j_callback_ctor, reinterpret_cast<jlong>(_p_callback)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onWebSocketRequest", "(Lnet/kurobako/cef4j/gen/CefServer;ILjava/lang/String;Lnet/kurobako/cef4j/gen/CefRequest;Lnet/kurobako/cef4j/gen/CefCallback;)V");
        if (!mid) {env->PopLocalFrame(nullptr); return;}
        env->CallVoidMethod(h->javaHandler, mid, j_server, static_cast<jint>(connection_id), j_client_address, j_request, j_callback);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return;}
        env->PopLocalFrame(nullptr);
    }

    static void CEF_CALLBACK _on_web_socket_connected(cef_server_handler_t* self, struct _cef_server_t* server, int connection_id) {
        auto* h = reinterpret_cast<JniCefServerHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) {return;}
        cef_server_t* _p_server = server;
        if (_p_server) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_server); _b->add_ref(_b);}
        auto j_server_cls = env->FindClass("net/kurobako/cef4j/gen/CefServer$NativePeer");
        auto j_server_ctor = env->GetMethodID(j_server_cls, "<init>", "(J)V");
        auto j_server = _p_server ? env->NewObject(j_server_cls, j_server_ctor, reinterpret_cast<jlong>(_p_server)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onWebSocketConnected", "(Lnet/kurobako/cef4j/gen/CefServer;I)V");
        if (!mid) {env->PopLocalFrame(nullptr); return;}
        env->CallVoidMethod(h->javaHandler, mid, j_server, static_cast<jint>(connection_id));
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return;}
        env->PopLocalFrame(nullptr);
    }

    static void CEF_CALLBACK _on_web_socket_message(cef_server_handler_t* self, struct _cef_server_t* server, int connection_id, const void* data, size_t data_size) {
        auto* h = reinterpret_cast<JniCefServerHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(9) < 0) {return;}
        cef_server_t* _p_server = server;
        if (_p_server) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_server); _b->add_ref(_b);}
        auto j_server_cls = env->FindClass("net/kurobako/cef4j/gen/CefServer$NativePeer");
        auto j_server_ctor = env->GetMethodID(j_server_cls, "<init>", "(J)V");
        auto j_server = _p_server ? env->NewObject(j_server_cls, j_server_ctor, reinterpret_cast<jlong>(_p_server)) : nullptr;
        jobject j_data = (data_size > 0 && data) ? env->NewDirectByteBuffer(const_cast<void*>(static_cast<const void*>(data)), static_cast<jlong>(data_size)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onWebSocketMessage", "(Lnet/kurobako/cef4j/gen/CefServer;ILjava/nio/ByteBuffer;)V");
        if (!mid) {env->PopLocalFrame(nullptr); return;}
        env->CallVoidMethod(h->javaHandler, mid, j_server, static_cast<jint>(connection_id), j_data);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return;}
        env->PopLocalFrame(nullptr);
    }
};

extern "C" cef_server_handler_t* Create_JniCefServerHandler(JNIEnv *env, jobject handler) {
    JavaVM *jvm;
    env->GetJavaVM(&jvm);
    auto globalRef = env->NewGlobalRef(handler);
    return reinterpret_cast<cef_server_handler_t*>(new JniCefServerHandler(jvm, globalRef));
}
