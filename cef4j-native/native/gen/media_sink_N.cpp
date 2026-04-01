// GENERATED - do not edit.
#include <jni.h>
#include "include/capi/cef_media_router_capi.h"
#include "jni_util.h"

extern "C" cef_media_sink_device_info_callback_t* Create_JniCefMediaSinkDeviceInfoCallback(JNIEnv *env, jobject handler);
extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefMediaSink_00024NativePeer_N_1Release(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefMediaSink_00024NativePeer_N_1GetId(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_media_sink_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_id(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefMediaSink_00024NativePeer_N_1GetName(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_media_sink_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_name(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefMediaSink_00024NativePeer_N_1GetIconType(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_media_sink_t*>(self);
    if (!s) return 0;
    auto _r = s->get_icon_type(s);
    auto _eCls = env->FindClass("net/kurobako/cef4j/gen/CefMediaSinkIconType");
    auto _eOf = env->GetStaticMethodID(_eCls, "of", "(J)Lnet/kurobako/cef4j/gen/CefMediaSinkIconType;");
    return env->CallStaticObjectMethod(_eCls, _eOf, static_cast<jlong>(_r));
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefMediaSink_00024NativePeer_N_1GetDeviceInfo(JNIEnv* env, jobject obj, jlong self, jobject callback) {
    auto* s = reinterpret_cast<cef_media_sink_t*>(self);
    if (!s) return;
    if (!callback) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "callback must not be null"); return;}
    cef_media_sink_device_info_callback_t* _callback_ptr = Create_JniCefMediaSinkDeviceInfoCallback(env, callback);
    s->get_device_info(s, _callback_ptr);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefMediaSink_00024NativePeer_N_1IsCastSink(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_media_sink_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_cast_sink(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefMediaSink_00024NativePeer_N_1IsDialSink(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_media_sink_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_dial_sink(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefMediaSink_00024NativePeer_N_1IsCompatibleWith(JNIEnv* env, jobject obj, jlong self, jobject source) {
    auto* s = reinterpret_cast<cef_media_sink_t*>(self);
    if (!s) return JNI_FALSE;
    if (!source) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "source must not be null"); return JNI_FALSE;}
    cef_media_source_t* _source_ptr = reinterpret_cast<cef_media_source_t*>(env->GetLongField(source, env->GetFieldID(env->GetObjectClass(source), "nativePtr", "J")));
    if (_source_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_source_ptr); _b->add_ref(_b);}
    auto _r = s->is_compatible_with(s, _source_ptr);
    return static_cast<jboolean>(_r);
}
