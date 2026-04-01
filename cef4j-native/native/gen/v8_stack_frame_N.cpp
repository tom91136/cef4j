// GENERATED - do not edit.
#include <jni.h>
#include "include/capi/cef_v8_capi.h"
#include "jni_util.h"

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefV8StackFrame_00024NativePeer_N_1Release(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefV8StackFrame_00024NativePeer_N_1IsValid(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_stack_frame_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_valid(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefV8StackFrame_00024NativePeer_N_1GetScriptName(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_stack_frame_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_script_name(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefV8StackFrame_00024NativePeer_N_1GetScriptNameOrSourceUrl(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_stack_frame_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_script_name_or_source_url(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefV8StackFrame_00024NativePeer_N_1GetFunctionName(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_stack_frame_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_function_name(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jint JNICALL Java_net_kurobako_cef4j_gen_CefV8StackFrame_00024NativePeer_N_1GetLineNumber(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_stack_frame_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->get_line_number(s));
}

extern "C" JNIEXPORT jint JNICALL Java_net_kurobako_cef4j_gen_CefV8StackFrame_00024NativePeer_N_1GetColumn(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_stack_frame_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->get_column(s));
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefV8StackFrame_00024NativePeer_N_1IsEval(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_stack_frame_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_eval(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefV8StackFrame_00024NativePeer_N_1IsConstructor(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_stack_frame_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_constructor(s);
    return static_cast<jboolean>(_r);
}
