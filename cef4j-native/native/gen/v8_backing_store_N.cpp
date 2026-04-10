// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
#include <jni.h>
#include "include/capi/cef_v8_capi.h"
#include "jni_util.h"

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefV8BackingStore), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefV8BackingStore), data0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_backing_store_t*>(self);
    if (!s) return nullptr;
    auto _r = s->data(s);
    auto _npCls = FindClassCached(env, "net/kurobako/cef4j/gen/NativePointer");
    auto _npCtor = env->GetMethodID(_npCls, "<init>", "(J)V");
    return env->NewObject(_npCls, _npCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jlong, CEF4J_PEER(CefV8BackingStore), byteLength0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_backing_store_t*>(self);
    if (!s) return 0;
    return static_cast<jlong>(s->byte_length(s));
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefV8BackingStore), isValid0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_backing_store_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_valid(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefV8BackingStore), create0)(JNIEnv* env, jclass clz, jlong byte_length) {
    auto _r = cef_v8_backing_store_create(byte_length);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefV8BackingStore$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}
