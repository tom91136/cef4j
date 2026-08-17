// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
#include <jni.h>
#include "include/capi/cef_media_router_capi.h"
#include "jni_util.h"

extern "C" cef_media_sink_device_info_callback_t* Create_JniCefMediaSinkDeviceInfoCallback(JNIEnv* env, jobject handler);
CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefMediaSink), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefMediaSink), getId0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_media_sink_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_id(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefMediaSink), getName0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_media_sink_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_name(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefMediaSink), getIconType0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_media_sink_t*>(self);
    if (!s) return 0;
    auto _r = s->get_icon_type(s);
    auto _eCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefMediaSinkIconType");
    auto _eOf = env->GetStaticMethodID(_eCls, "of", "(J)Lnet/kurobako/cef4j/gen/CefMediaSinkIconType;");
    return env->CallStaticObjectMethod(_eCls, _eOf, static_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefMediaSink), getDeviceInfo0)(JNIEnv* env, jobject obj, jlong self, jobject callback) {
    auto* s = reinterpret_cast<cef_media_sink_t*>(self);
    if (!s) return;
    cef_media_sink_device_info_callback_t* _callback_ptr = callback ? Create_JniCefMediaSinkDeviceInfoCallback(env, callback) : nullptr;
    s->get_device_info(s, _callback_ptr);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefMediaSink), isCastSink0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_media_sink_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_cast_sink(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefMediaSink), isDialSink0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_media_sink_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_dial_sink(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefMediaSink), isCompatibleWith0)(JNIEnv* env, jobject obj, jlong self, jobject source) {
    auto* s = reinterpret_cast<cef_media_sink_t*>(self);
    if (!s) return JNI_FALSE;
    cef_media_source_t* _source_ptr = source ? reinterpret_cast<cef_media_source_t*>(env->GetLongField(source, env->GetFieldID(env->GetObjectClass(source), "nativePtr", "J"))) : nullptr;
    if (_source_ptr) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_source_ptr); _b->add_ref(_b); }
    auto _r = s->is_compatible_with(s, _source_ptr);
    return static_cast<jboolean>(_r);
}
