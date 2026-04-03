// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
#include <jni.h>
#include "include/capi/cef_urlrequest_capi.h"
#include "include/capi/cef_request_capi.h"
#include "include/capi/cef_request_context_capi.h"
#include "include/capi/cef_response_capi.h"
#include "jni_util.h"

extern "C" cef_urlrequest_client_t* Create_JniCefUrlRequestClient(JNIEnv *env, jobject handler);
CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefUrlRequest), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefUrlRequest), getRequest0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_urlrequest_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_request(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefRequest$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefUrlRequest), getClient0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_urlrequest_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_client(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefUrlRequestClient$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefUrlRequest), getRequestStatus0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_urlrequest_t*>(self);
    if (!s) return 0;
    auto _r = s->get_request_status(s);
    auto _eCls = env->FindClass("net/kurobako/cef4j/gen/CefUrlRequestStatus");
    auto _eOf = env->GetStaticMethodID(_eCls, "of", "(J)Lnet/kurobako/cef4j/gen/CefUrlRequestStatus;");
    return env->CallStaticObjectMethod(_eCls, _eOf, static_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefUrlRequest), getRequestError0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_urlrequest_t*>(self);
    if (!s) return 0;
    auto _r = s->get_request_error(s);
    auto _eCls = env->FindClass("net/kurobako/cef4j/gen/CefErrorCode");
    auto _eOf = env->GetStaticMethodID(_eCls, "of", "(J)Lnet/kurobako/cef4j/gen/CefErrorCode;");
    return env->CallStaticObjectMethod(_eCls, _eOf, static_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefUrlRequest), getResponse0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_urlrequest_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_response(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefResponse$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefUrlRequest), responseWasCached0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_urlrequest_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->response_was_cached(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefUrlRequest), cancel0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_urlrequest_t*>(self);
    if (!s) return;
    s->cancel(s);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefUrlRequest), create0)(JNIEnv* env, jclass clz, jobject request, jobject client, jobject request_context) {
    cef_request_t* _request_ptr = request ? reinterpret_cast<cef_request_t*>(env->GetLongField(request, env->GetFieldID(env->GetObjectClass(request), "nativePtr", "J"))) : nullptr;
    if (_request_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_request_ptr); _b->add_ref(_b);}
    cef_urlrequest_client_t* _client_ptr = client ? Create_JniCefUrlRequestClient(env, client) : nullptr;
    cef_request_context_t* _request_context_ptr = request_context ? reinterpret_cast<cef_request_context_t*>(env->GetLongField(request_context, env->GetFieldID(env->GetObjectClass(request_context), "nativePtr", "J"))) : nullptr;
    if (_request_context_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_request_context_ptr); _b->add_ref(_b);}
    auto _r = cef_urlrequest_create(_request_ptr, _client_ptr, _request_context_ptr);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefUrlRequest$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}
