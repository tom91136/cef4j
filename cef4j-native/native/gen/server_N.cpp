// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
#include <jni.h>
#include "include/capi/cef_server_capi.h"
#include "include/capi/cef_task_capi.h"
#include "jni_util.h"

extern "C" cef_server_handler_t* Create_JniCefServerHandler(JNIEnv* env, jobject handler);
CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefServer), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefServer), getTaskRunner0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_server_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_task_runner(s);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefTaskRunner$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefServer), shutdown0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_server_t*>(self);
    if (!s) return;
    s->shutdown(s);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefServer), isRunning0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_server_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_running(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefServer), getAddress0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_server_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_address(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefServer), hasConnection0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_server_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->has_connection(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefServer), isValidConnection0)(JNIEnv* env, jobject obj, jlong self, jint connection_id) {
    auto* s = reinterpret_cast<cef_server_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_valid_connection(s, connection_id);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefServer), sendHttp200Response0)(JNIEnv* env, jobject obj, jlong self, jint connection_id, jstring content_type, jobject data) {
    auto* s = reinterpret_cast<cef_server_t*>(self);
    if (!s) return;
    if (!data) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "data must not be null"); return; }
    auto _content_type_str = JStringToCefString(env, content_type);
    const void* _data_addr = data ? env->GetDirectBufferAddress(data) : nullptr;
    if (data && !_data_addr) { env->ThrowNew(FindClassCached(env, "java/lang/IllegalArgumentException"), "data must be a direct ByteBuffer; use ByteBuffer.allocateDirect(...)"); return; }
    s->send_http200_response(s, connection_id, _content_type_str, _data_addr, static_cast<size_t>(env->GetDirectBufferCapacity(data)));
    if (_content_type_str) cef_string_userfree_free(_content_type_str);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefServer), sendHttp404Response0)(JNIEnv* env, jobject obj, jlong self, jint connection_id) {
    auto* s = reinterpret_cast<cef_server_t*>(self);
    if (!s) return;
    s->send_http404_response(s, connection_id);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefServer), sendHttp500Response0)(JNIEnv* env, jobject obj, jlong self, jint connection_id, jstring error_message) {
    auto* s = reinterpret_cast<cef_server_t*>(self);
    if (!s) return;
    auto _error_message_str = JStringToCefString(env, error_message);
    s->send_http500_response(s, connection_id, _error_message_str);
    if (_error_message_str) cef_string_userfree_free(_error_message_str);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefServer), sendHttpResponse0)(JNIEnv* env, jobject obj, jlong self, jint connection_id, jint response_code, jstring content_type, jlong content_length, jobject extra_headers) {
    auto* s = reinterpret_cast<cef_server_t*>(self);
    if (!s) return;
    auto _content_type_str = JStringToCefString(env, content_type);
    auto _extra_headers_csmm = JavaMapToCefStringMultimap(env, extra_headers);
    s->send_http_response(s, connection_id, response_code, _content_type_str, content_length, _extra_headers_csmm);
    if (_content_type_str) cef_string_userfree_free(_content_type_str);
    cef_string_multimap_free(_extra_headers_csmm);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefServer), sendRawData0)(JNIEnv* env, jobject obj, jlong self, jint connection_id, jobject data) {
    auto* s = reinterpret_cast<cef_server_t*>(self);
    if (!s) return;
    if (!data) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "data must not be null"); return; }
    const void* _data_addr = data ? env->GetDirectBufferAddress(data) : nullptr;
    if (data && !_data_addr) { env->ThrowNew(FindClassCached(env, "java/lang/IllegalArgumentException"), "data must be a direct ByteBuffer; use ByteBuffer.allocateDirect(...)"); return; }
    s->send_raw_data(s, connection_id, _data_addr, static_cast<size_t>(env->GetDirectBufferCapacity(data)));
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefServer), closeConnection0)(JNIEnv* env, jobject obj, jlong self, jint connection_id) {
    auto* s = reinterpret_cast<cef_server_t*>(self);
    if (!s) return;
    s->close_connection(s, connection_id);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefServer), sendWebSocketMessage0)(JNIEnv* env, jobject obj, jlong self, jint connection_id, jobject data) {
    auto* s = reinterpret_cast<cef_server_t*>(self);
    if (!s) return;
    if (!data) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "data must not be null"); return; }
    const void* _data_addr = data ? env->GetDirectBufferAddress(data) : nullptr;
    if (data && !_data_addr) { env->ThrowNew(FindClassCached(env, "java/lang/IllegalArgumentException"), "data must be a direct ByteBuffer; use ByteBuffer.allocateDirect(...)"); return; }
    s->send_web_socket_message(s, connection_id, _data_addr, static_cast<size_t>(env->GetDirectBufferCapacity(data)));
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefServer), create0)(JNIEnv* env, jclass clz, jstring address, jint port, jint backlog, jobject handler) {
    auto _address_str = JStringToCefString(env, address);
    cef_server_handler_t* _handler_ptr = handler ? Create_JniCefServerHandler(env, handler) : nullptr;
    cef_server_create(_address_str, port, backlog, _handler_ptr);
    if (_address_str) cef_string_userfree_free(_address_str);
}
