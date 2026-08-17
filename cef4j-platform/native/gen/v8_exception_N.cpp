// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
#include <jni.h>
#include "include/capi/cef_v8_capi.h"
#include "jni_util.h"

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefV8Exception), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefV8Exception), getMessage0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_exception_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_message(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefV8Exception), getSourceLine0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_exception_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_source_line(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefV8Exception), getScriptResourceName0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_exception_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_script_resource_name(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jint, CEF4J_PEER(CefV8Exception), getLineNumber0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_exception_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->get_line_number(s));
}

CEF4J_JNI_EXPORT(jint, CEF4J_PEER(CefV8Exception), getStartPosition0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_exception_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->get_start_position(s));
}

CEF4J_JNI_EXPORT(jint, CEF4J_PEER(CefV8Exception), getEndPosition0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_exception_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->get_end_position(s));
}

CEF4J_JNI_EXPORT(jint, CEF4J_PEER(CefV8Exception), getStartColumn0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_exception_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->get_start_column(s));
}

CEF4J_JNI_EXPORT(jint, CEF4J_PEER(CefV8Exception), getEndColumn0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_exception_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->get_end_column(s));
}
