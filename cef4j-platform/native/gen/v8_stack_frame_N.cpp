// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
#include <jni.h>
#include "include/capi/cef_v8_capi.h"
#include "jni_util.h"

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefV8StackFrame), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefV8StackFrame), isValid0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_stack_frame_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_valid(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefV8StackFrame), getScriptName0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_stack_frame_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_script_name(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefV8StackFrame), getScriptNameOrSourceUrl0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_stack_frame_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_script_name_or_source_url(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefV8StackFrame), getFunctionName0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_stack_frame_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_function_name(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jint, CEF4J_PEER(CefV8StackFrame), getLineNumber0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_stack_frame_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->get_line_number(s));
}

CEF4J_JNI_EXPORT(jint, CEF4J_PEER(CefV8StackFrame), getColumn0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_stack_frame_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->get_column(s));
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefV8StackFrame), isEval0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_stack_frame_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_eval(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefV8StackFrame), isConstructor0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_stack_frame_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_constructor(s);
    return static_cast<jboolean>(_r);
}
