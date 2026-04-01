// GENERATED - do not edit.
#include <jni.h>
#include "include/capi/cef_shared_memory_region_capi.h"
#include "jni_util.h"

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefSharedMemoryRegion_00024NativePeer_N_1Release(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefSharedMemoryRegion_00024NativePeer_N_1IsValid(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_shared_memory_region_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_valid(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jlong JNICALL Java_net_kurobako_cef4j_gen_CefSharedMemoryRegion_00024NativePeer_N_1Size(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_shared_memory_region_t*>(self);
    if (!s) return 0;
    return static_cast<jlong>(s->size(s));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefSharedMemoryRegion_00024NativePeer_N_1Memory(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_shared_memory_region_t*>(self);
    if (!s) return nullptr;
    auto _r = s->memory(s);
    auto _npCls = env->FindClass("net/kurobako/cef4j/gen/NativePointer");
    auto _npCtor = env->GetMethodID(_npCls, "<init>", "(J)V");
    return env->NewObject(_npCls, _npCtor, reinterpret_cast<jlong>(_r));
}
