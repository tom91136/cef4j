// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
#include <jni.h>
#include "include/capi/cef_shared_memory_region_capi.h"
#include "jni_util.h"

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefSharedMemoryRegion), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefSharedMemoryRegion), isValid0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_shared_memory_region_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_valid(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jlong, CEF4J_PEER(CefSharedMemoryRegion), size0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_shared_memory_region_t*>(self);
    if (!s) return 0;
    return static_cast<jlong>(s->size(s));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefSharedMemoryRegion), memory0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_shared_memory_region_t*>(self);
    if (!s) return nullptr;
    auto _r = s->memory(s);
    auto _npCls = env->FindClass("net/kurobako/cef4j/gen/NativePointer");
    auto _npCtor = env->GetMethodID(_npCls, "<init>", "(J)V");
    return env->NewObject(_npCls, _npCtor, reinterpret_cast<jlong>(_r));
}
