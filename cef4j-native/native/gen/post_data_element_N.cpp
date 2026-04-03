// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
#include <jni.h>
#include "include/capi/cef_request_capi.h"
#include "jni_util.h"

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefPostDataElement), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefPostDataElement), isReadOnly0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_post_data_element_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_read_only(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefPostDataElement), setToEmpty0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_post_data_element_t*>(self);
    if (!s) return;
    s->set_to_empty(s);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefPostDataElement), setToFile0)(JNIEnv* env, jobject obj, jlong self, jstring fileName) {
    auto* s = reinterpret_cast<cef_post_data_element_t*>(self);
    if (!s) return;
    auto _fileName_str = JStringToCefString(env, fileName);
    s->set_to_file(s, _fileName_str);
    if (_fileName_str) cef_string_userfree_free(_fileName_str);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefPostDataElement), setToBytes0)(JNIEnv* env, jobject obj, jlong self, jobject bytes) {
    auto* s = reinterpret_cast<cef_post_data_element_t*>(self);
    if (!s) return;
    if (!bytes) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "bytes must not be null"); return;}
    const void* _bytes_addr = bytes ? env->GetDirectBufferAddress(bytes) : nullptr;
    if (bytes && !_bytes_addr) {env->ThrowNew(env->FindClass("java/lang/IllegalArgumentException"), "bytes must be a direct ByteBuffer; use ByteBuffer.allocateDirect(...)"); return;}
    s->set_to_bytes(s, static_cast<size_t>(env->GetDirectBufferCapacity(bytes)), _bytes_addr);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefPostDataElement), getType0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_post_data_element_t*>(self);
    if (!s) return 0;
    auto _r = s->get_type(s);
    auto _eCls = env->FindClass("net/kurobako/cef4j/gen/CefPostdataelementType");
    auto _eOf = env->GetStaticMethodID(_eCls, "of", "(J)Lnet/kurobako/cef4j/gen/CefPostdataelementType;");
    return env->CallStaticObjectMethod(_eCls, _eOf, static_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefPostDataElement), getFile0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_post_data_element_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_file(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jlong, CEF4J_PEER(CefPostDataElement), getBytesCount0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_post_data_element_t*>(self);
    if (!s) return 0;
    return static_cast<jlong>(s->get_bytes_count(s));
}

CEF4J_JNI_EXPORT(jlong, CEF4J_PEER(CefPostDataElement), getBytes0)(JNIEnv* env, jobject obj, jlong self, jobject bytes) {
    auto* s = reinterpret_cast<cef_post_data_element_t*>(self);
    if (!s) return 0;
    if (!bytes) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "bytes must not be null"); return 0;}
    void* _bytes_addr = bytes ? env->GetDirectBufferAddress(bytes) : nullptr;
    if (bytes && !_bytes_addr) {env->ThrowNew(env->FindClass("java/lang/IllegalArgumentException"), "bytes must be a direct ByteBuffer; use ByteBuffer.allocateDirect(...)"); return 0;}
    return static_cast<jlong>(s->get_bytes(s, static_cast<size_t>(env->GetDirectBufferCapacity(bytes)), _bytes_addr));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefPostDataElement), create0)(JNIEnv* env, jclass clz) {
    auto _r = cef_post_data_element_create();
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefPostDataElement$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}
