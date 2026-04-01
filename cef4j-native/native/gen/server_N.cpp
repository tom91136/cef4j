// GENERATED - do not edit.
#include <jni.h>
#include "include/capi/cef_server_capi.h"
#include "include/capi/cef_task_capi.h"
#include "jni_util.h"

extern "C" cef_server_handler_t* Create_JniCefServerHandler(JNIEnv *env, jobject handler);
extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefServer_00024NativePeer_N_1Release(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefServer_00024NativePeer_N_1GetTaskRunner(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_server_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_task_runner(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefTaskRunner$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefServer_00024NativePeer_N_1Shutdown(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_server_t*>(self);
    if (!s) return;
    s->shutdown(s);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefServer_00024NativePeer_N_1IsRunning(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_server_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_running(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefServer_00024NativePeer_N_1GetAddress(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_server_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_address(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefServer_00024NativePeer_N_1HasConnection(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_server_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->has_connection(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefServer_00024NativePeer_N_1IsValidConnection(JNIEnv* env, jobject obj, jlong self, jint connection_id) {
    auto* s = reinterpret_cast<cef_server_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_valid_connection(s, connection_id);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefServer_00024NativePeer_N_1SendHttp200response(JNIEnv* env, jobject obj, jlong self, jint connection_id, jstring content_type, jobject data) {
    auto* s = reinterpret_cast<cef_server_t*>(self);
    if (!s) return;
    if (!content_type) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "contentType must not be null"); return;}
    if (!data) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "data must not be null"); return;}
    auto _content_type_str = JStringToCefString(env, content_type);
    const void* _data_addr = data ? env->GetDirectBufferAddress(data) : nullptr;
    s->send_http200_response(s, connection_id, _content_type_str, _data_addr, static_cast<size_t>(env->GetDirectBufferCapacity(data)));
    if (_content_type_str) cef_string_userfree_free(_content_type_str);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefServer_00024NativePeer_N_1SendHttp404response(JNIEnv* env, jobject obj, jlong self, jint connection_id) {
    auto* s = reinterpret_cast<cef_server_t*>(self);
    if (!s) return;
    s->send_http404_response(s, connection_id);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefServer_00024NativePeer_N_1SendHttp500response(JNIEnv* env, jobject obj, jlong self, jint connection_id, jstring error_message) {
    auto* s = reinterpret_cast<cef_server_t*>(self);
    if (!s) return;
    if (!error_message) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "errorMessage must not be null"); return;}
    auto _error_message_str = JStringToCefString(env, error_message);
    s->send_http500_response(s, connection_id, _error_message_str);
    if (_error_message_str) cef_string_userfree_free(_error_message_str);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefServer_00024NativePeer_N_1SendHttpResponse(JNIEnv* env, jobject obj, jlong self, jint connection_id, jint response_code, jstring content_type, jlong content_length, jobject extra_headers) {
    auto* s = reinterpret_cast<cef_server_t*>(self);
    if (!s) return;
    if (!content_type) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "contentType must not be null"); return;}
    auto _content_type_str = JStringToCefString(env, content_type);
    auto _extra_headers_csmm = JavaMapToCefStringMultimap(env, extra_headers);
    s->send_http_response(s, connection_id, response_code, _content_type_str, content_length, _extra_headers_csmm);
    if (_content_type_str) cef_string_userfree_free(_content_type_str);
    cef_string_multimap_free(_extra_headers_csmm);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefServer_00024NativePeer_N_1SendRawData(JNIEnv* env, jobject obj, jlong self, jint connection_id, jobject data) {
    auto* s = reinterpret_cast<cef_server_t*>(self);
    if (!s) return;
    if (!data) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "data must not be null"); return;}
    const void* _data_addr = data ? env->GetDirectBufferAddress(data) : nullptr;
    s->send_raw_data(s, connection_id, _data_addr, static_cast<size_t>(env->GetDirectBufferCapacity(data)));
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefServer_00024NativePeer_N_1CloseConnection(JNIEnv* env, jobject obj, jlong self, jint connection_id) {
    auto* s = reinterpret_cast<cef_server_t*>(self);
    if (!s) return;
    s->close_connection(s, connection_id);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefServer_00024NativePeer_N_1SendWebSocketMessage(JNIEnv* env, jobject obj, jlong self, jint connection_id, jobject data) {
    auto* s = reinterpret_cast<cef_server_t*>(self);
    if (!s) return;
    if (!data) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "data must not be null"); return;}
    const void* _data_addr = data ? env->GetDirectBufferAddress(data) : nullptr;
    s->send_web_socket_message(s, connection_id, _data_addr, static_cast<size_t>(env->GetDirectBufferCapacity(data)));
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefServer_00024NativePeer_N_1Create(JNIEnv* env, jclass clz, jstring address, jint port, jint backlog, jobject handler) {
    if (!address) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "address must not be null"); return;}
    if (!handler) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "handler must not be null"); return;}
    auto _address_str = JStringToCefString(env, address);
    cef_server_handler_t* _handler_ptr = Create_JniCefServerHandler(env, handler);
    cef_server_create(_address_str, port, backlog, _handler_ptr);
    if (_address_str) cef_string_userfree_free(_address_str);
}
