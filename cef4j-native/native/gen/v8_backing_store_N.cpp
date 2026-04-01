// GENERATED - do not edit.
#include <jni.h>
#include "include/capi/cef_v8_capi.h"
#include "jni_util.h"

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefV8BackingStore_00024NativePeer_N_1Release(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefV8BackingStore_00024NativePeer_N_1Data(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_backing_store_t*>(self);
    if (!s) return nullptr;
    auto _r = s->data(s);
    auto _npCls = env->FindClass("net/kurobako/cef4j/gen/NativePointer");
    auto _npCtor = env->GetMethodID(_npCls, "<init>", "(J)V");
    return env->NewObject(_npCls, _npCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jlong JNICALL Java_net_kurobako_cef4j_gen_CefV8BackingStore_00024NativePeer_N_1ByteLength(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_backing_store_t*>(self);
    if (!s) return 0;
    return static_cast<jlong>(s->byte_length(s));
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefV8BackingStore_00024NativePeer_N_1IsValid(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_backing_store_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_valid(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefV8BackingStore_00024NativePeer_N_1Create(JNIEnv* env, jclass clz, jlong byte_length) {
    auto _r = cef_v8_backing_store_create(byte_length);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefV8BackingStore$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}
