// GENERATED - do not edit.
#include <jni.h>
#include "include/capi/cef_v8_capi.h"
#include "jni_util.h"

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefV8Exception_00024NativePeer_N_1Release(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefV8Exception_00024NativePeer_N_1GetMessage(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_exception_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_message(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefV8Exception_00024NativePeer_N_1GetSourceLine(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_exception_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_source_line(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefV8Exception_00024NativePeer_N_1GetScriptResourceName(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_exception_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_script_resource_name(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jint JNICALL Java_net_kurobako_cef4j_gen_CefV8Exception_00024NativePeer_N_1GetLineNumber(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_exception_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->get_line_number(s));
}

extern "C" JNIEXPORT jint JNICALL Java_net_kurobako_cef4j_gen_CefV8Exception_00024NativePeer_N_1GetStartPosition(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_exception_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->get_start_position(s));
}

extern "C" JNIEXPORT jint JNICALL Java_net_kurobako_cef4j_gen_CefV8Exception_00024NativePeer_N_1GetEndPosition(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_exception_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->get_end_position(s));
}

extern "C" JNIEXPORT jint JNICALL Java_net_kurobako_cef4j_gen_CefV8Exception_00024NativePeer_N_1GetStartColumn(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_exception_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->get_start_column(s));
}

extern "C" JNIEXPORT jint JNICALL Java_net_kurobako_cef4j_gen_CefV8Exception_00024NativePeer_N_1GetEndColumn(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_exception_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->get_end_column(s));
}
