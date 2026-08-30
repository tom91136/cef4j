// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
#include <jni.h>
#include "include/capi/cef_media_router_capi.h"
#include "jni_util.h"

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefMediaSource), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefMediaSource), getId0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_media_source_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_id(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefMediaSource), isCastSource0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_media_source_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_cast_source(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefMediaSource), isDialSource0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_media_source_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_dial_source(s);
    return static_cast<jboolean>(_r);
}
