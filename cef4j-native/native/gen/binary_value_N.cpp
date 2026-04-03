// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
#include <jni.h>
#include "include/capi/cef_values_capi.h"
#include "include/capi/cef_parser_capi.h"
#include "jni_util.h"

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefBinaryValue), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefBinaryValue), isValid0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_binary_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_valid(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefBinaryValue), isOwned0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_binary_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_owned(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefBinaryValue), isSame0)(JNIEnv* env, jobject obj, jlong self, jobject that) {
    auto* s = reinterpret_cast<cef_binary_value_t*>(self);
    if (!s) return JNI_FALSE;
    cef_binary_value_t* _that_ptr = that ? reinterpret_cast<cef_binary_value_t*>(env->GetLongField(that, env->GetFieldID(env->GetObjectClass(that), "nativePtr", "J"))) : nullptr;
    if (_that_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_that_ptr); _b->add_ref(_b);}
    auto _r = s->is_same(s, _that_ptr);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefBinaryValue), isEqual0)(JNIEnv* env, jobject obj, jlong self, jobject that) {
    auto* s = reinterpret_cast<cef_binary_value_t*>(self);
    if (!s) return JNI_FALSE;
    cef_binary_value_t* _that_ptr = that ? reinterpret_cast<cef_binary_value_t*>(env->GetLongField(that, env->GetFieldID(env->GetObjectClass(that), "nativePtr", "J"))) : nullptr;
    if (_that_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_that_ptr); _b->add_ref(_b);}
    auto _r = s->is_equal(s, _that_ptr);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefBinaryValue), copy0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_binary_value_t*>(self);
    if (!s) return nullptr;
    auto _r = s->copy(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefBinaryValue$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefBinaryValue), getRawData0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_binary_value_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_raw_data(s);
    auto _npCls = env->FindClass("net/kurobako/cef4j/gen/NativePointer");
    auto _npCtor = env->GetMethodID(_npCls, "<init>", "(J)V");
    return env->NewObject(_npCls, _npCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jlong, CEF4J_PEER(CefBinaryValue), getSize0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_binary_value_t*>(self);
    if (!s) return 0;
    return static_cast<jlong>(s->get_size(s));
}

CEF4J_JNI_EXPORT(jlong, CEF4J_PEER(CefBinaryValue), getData0)(JNIEnv* env, jobject obj, jlong self, jobject buffer, jlong data_offset) {
    auto* s = reinterpret_cast<cef_binary_value_t*>(self);
    if (!s) return 0;
    if (!buffer) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "buffer must not be null"); return 0;}
    void* _buffer_addr = buffer ? env->GetDirectBufferAddress(buffer) : nullptr;
    if (buffer && !_buffer_addr) {env->ThrowNew(env->FindClass("java/lang/IllegalArgumentException"), "buffer must be a direct ByteBuffer; use ByteBuffer.allocateDirect(...)"); return 0;}
    return static_cast<jlong>(s->get_data(s, _buffer_addr, static_cast<size_t>(env->GetDirectBufferCapacity(buffer)), data_offset));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefBinaryValue), create0)(JNIEnv* env, jclass clz, jobject data) {
    if (!data) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "data must not be null"); return nullptr;}
    const void* _data_addr = data ? env->GetDirectBufferAddress(data) : nullptr;
    if (data && !_data_addr) {env->ThrowNew(env->FindClass("java/lang/IllegalArgumentException"), "data must be a direct ByteBuffer; use ByteBuffer.allocateDirect(...)"); return nullptr;}
    auto _r = cef_binary_value_create(_data_addr, static_cast<size_t>(env->GetDirectBufferCapacity(data)));
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefBinaryValue$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefBinaryValue), base64Decode0)(JNIEnv* env, jclass clz, jstring data) {
    auto _data_str = JStringToCefString(env, data);
    auto _r = cef_base64_decode(_data_str);
    if (_data_str) cef_string_userfree_free(_data_str);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefBinaryValue$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}
