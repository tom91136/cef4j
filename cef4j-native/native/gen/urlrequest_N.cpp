// GENERATED - do not edit.
#include <jni.h>
#include "include/capi/cef_urlrequest_capi.h"
#include "include/capi/cef_request_capi.h"
#include "include/capi/cef_request_context_capi.h"
#include "include/capi/cef_response_capi.h"
#include "jni_util.h"

extern "C" cef_urlrequest_client_t* Create_JniCefUrlRequestClient(JNIEnv *env, jobject handler);
extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefUrlRequest_00024NativePeer_N_1Release(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefUrlRequest_00024NativePeer_N_1GetRequest(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_urlrequest_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_request(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefRequest$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefUrlRequest_00024NativePeer_N_1GetClient(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_urlrequest_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_client(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefUrlRequestClient$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefUrlRequest_00024NativePeer_N_1GetRequestStatus(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_urlrequest_t*>(self);
    if (!s) return 0;
    auto _r = s->get_request_status(s);
    auto _eCls = env->FindClass("net/kurobako/cef4j/gen/CefUrlRequestStatus");
    auto _eOf = env->GetStaticMethodID(_eCls, "of", "(J)Lnet/kurobako/cef4j/gen/CefUrlRequestStatus;");
    return env->CallStaticObjectMethod(_eCls, _eOf, static_cast<jlong>(_r));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefUrlRequest_00024NativePeer_N_1GetRequestError(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_urlrequest_t*>(self);
    if (!s) return 0;
    auto _r = s->get_request_error(s);
    auto _eCls = env->FindClass("net/kurobako/cef4j/gen/CefErrorCode");
    auto _eOf = env->GetStaticMethodID(_eCls, "of", "(J)Lnet/kurobako/cef4j/gen/CefErrorCode;");
    return env->CallStaticObjectMethod(_eCls, _eOf, static_cast<jlong>(_r));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefUrlRequest_00024NativePeer_N_1GetResponse(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_urlrequest_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_response(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefResponse$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefUrlRequest_00024NativePeer_N_1ResponseWasCached(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_urlrequest_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->response_was_cached(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefUrlRequest_00024NativePeer_N_1Cancel(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_urlrequest_t*>(self);
    if (!s) return;
    s->cancel(s);
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefUrlRequest_00024NativePeer_N_1Create(JNIEnv* env, jclass clz, jobject request, jobject client, jobject request_context) {
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
