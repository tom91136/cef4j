// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
#include <jni.h>
#include "include/capi/cef_v8_capi.h"
#include "jni_util.h"

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefV8ArrayBufferReleaseCallback), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefV8ArrayBufferReleaseCallback), releaseBuffer0)(JNIEnv* env, jobject obj, jlong self, jobject buffer) {
    auto* s = reinterpret_cast<cef_v8_array_buffer_release_callback_t*>(self);
    if (!s) return;
    s->release_buffer(s, reinterpret_cast<void*>(buffer ? env->GetLongField(buffer, env->GetFieldID(env->GetObjectClass(buffer), "address", "J")) : 0));
}
