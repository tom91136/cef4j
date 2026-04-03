// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
#include <jni.h>
#include "include/capi/cef_unresponsive_process_callback_capi.h"
#include "jni_util.h"

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefUnresponsiveProcessCallback), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefUnresponsiveProcessCallback), cefWait0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_unresponsive_process_callback_t*>(self);
    if (!s) return;
    s->wait(s);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefUnresponsiveProcessCallback), terminate0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_unresponsive_process_callback_t*>(self);
    if (!s) return;
    s->terminate(s);
}
