// GENERATED - do not edit.
#include <jni.h>
#include "include/capi/cef_command_line_capi.h"
#include "jni_util.h"

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefCommandLine_00024NativePeer_N_1Release(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefCommandLine_00024NativePeer_N_1IsValid(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_command_line_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_valid(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefCommandLine_00024NativePeer_N_1IsReadOnly(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_command_line_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_read_only(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefCommandLine_00024NativePeer_N_1Copy(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_command_line_t*>(self);
    if (!s) return nullptr;
    auto _r = s->copy(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefCommandLine$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefCommandLine_00024NativePeer_N_1InitFromArgv(JNIEnv* env, jobject obj, jlong self, jint argc, jobject argv) {
    auto* s = reinterpret_cast<cef_command_line_t*>(self);
    if (!s) return;
    if (!argv) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "argv must not be null"); return;}
    s->init_from_argv(s, argc, reinterpret_cast<const char* const*>(argv ? env->GetLongField(argv, env->GetFieldID(env->GetObjectClass(argv), "address", "J")) : 0));
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefCommandLine_00024NativePeer_N_1InitFromString(JNIEnv* env, jobject obj, jlong self, jstring command_line) {
    auto* s = reinterpret_cast<cef_command_line_t*>(self);
    if (!s) return;
    if (!command_line) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "commandLine must not be null"); return;}
    auto _command_line_str = JStringToCefString(env, command_line);
    s->init_from_string(s, _command_line_str);
    if (_command_line_str) cef_string_userfree_free(_command_line_str);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefCommandLine_00024NativePeer_N_1Reset(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_command_line_t*>(self);
    if (!s) return;
    s->reset(s);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefCommandLine_00024NativePeer_N_1GetArgv(JNIEnv* env, jobject obj, jlong self, jobject argv) {
    auto* s = reinterpret_cast<cef_command_line_t*>(self);
    if (!s) return;
    if (!argv) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "argv must not be null"); return;}
    auto _argv_csl = JavaListToCefStringList(env, argv);
    s->get_argv(s, _argv_csl);
    CefStringListWriteBack(env, _argv_csl, argv);
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefCommandLine_00024NativePeer_N_1GetCommandLineString(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_command_line_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_command_line_string(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefCommandLine_00024NativePeer_N_1GetProgram(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_command_line_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_program(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefCommandLine_00024NativePeer_N_1SetProgram(JNIEnv* env, jobject obj, jlong self, jstring program) {
    auto* s = reinterpret_cast<cef_command_line_t*>(self);
    if (!s) return;
    if (!program) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "program must not be null"); return;}
    auto _program_str = JStringToCefString(env, program);
    s->set_program(s, _program_str);
    if (_program_str) cef_string_userfree_free(_program_str);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefCommandLine_00024NativePeer_N_1HasSwitches(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_command_line_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->has_switches(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefCommandLine_00024NativePeer_N_1HasSwitch(JNIEnv* env, jobject obj, jlong self, jstring name) {
    auto* s = reinterpret_cast<cef_command_line_t*>(self);
    if (!s) return JNI_FALSE;
    if (!name) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "name must not be null"); return JNI_FALSE;}
    auto _name_str = JStringToCefString(env, name);
    auto _r = s->has_switch(s, _name_str);
    if (_name_str) cef_string_userfree_free(_name_str);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefCommandLine_00024NativePeer_N_1GetSwitchValue(JNIEnv* env, jobject obj, jlong self, jstring name) {
    auto* s = reinterpret_cast<cef_command_line_t*>(self);
    if (!s) return nullptr;
    if (!name) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "name must not be null"); return nullptr;}
    auto _name_str = JStringToCefString(env, name);
    auto result = s->get_switch_value(s, _name_str);
    if (_name_str) cef_string_userfree_free(_name_str);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefCommandLine_00024NativePeer_N_1GetSwitches(JNIEnv* env, jobject obj, jlong self, jobject switches) {
    auto* s = reinterpret_cast<cef_command_line_t*>(self);
    if (!s) return;
    if (!switches) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "switches must not be null"); return;}
    auto _switches_csm = JavaMapToCefStringMap(env, switches);
    s->get_switches(s, _switches_csm);
    CefStringMapWriteBack(env, _switches_csm, switches);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefCommandLine_00024NativePeer_N_1AppendSwitch(JNIEnv* env, jobject obj, jlong self, jstring name) {
    auto* s = reinterpret_cast<cef_command_line_t*>(self);
    if (!s) return;
    if (!name) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "name must not be null"); return;}
    auto _name_str = JStringToCefString(env, name);
    s->append_switch(s, _name_str);
    if (_name_str) cef_string_userfree_free(_name_str);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefCommandLine_00024NativePeer_N_1AppendSwitchWithValue(JNIEnv* env, jobject obj, jlong self, jstring name, jstring value) {
    auto* s = reinterpret_cast<cef_command_line_t*>(self);
    if (!s) return;
    if (!name) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "name must not be null"); return;}
    if (!value) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "value must not be null"); return;}
    auto _name_str = JStringToCefString(env, name);
    auto _value_str = JStringToCefString(env, value);
    s->append_switch_with_value(s, _name_str, _value_str);
    if (_name_str) cef_string_userfree_free(_name_str);
    if (_value_str) cef_string_userfree_free(_value_str);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefCommandLine_00024NativePeer_N_1HasArguments(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_command_line_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->has_arguments(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefCommandLine_00024NativePeer_N_1GetArguments(JNIEnv* env, jobject obj, jlong self, jobject arguments) {
    auto* s = reinterpret_cast<cef_command_line_t*>(self);
    if (!s) return;
    if (!arguments) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "arguments must not be null"); return;}
    auto _arguments_csl = JavaListToCefStringList(env, arguments);
    s->get_arguments(s, _arguments_csl);
    CefStringListWriteBack(env, _arguments_csl, arguments);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefCommandLine_00024NativePeer_N_1AppendArgument(JNIEnv* env, jobject obj, jlong self, jstring argument) {
    auto* s = reinterpret_cast<cef_command_line_t*>(self);
    if (!s) return;
    if (!argument) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "argument must not be null"); return;}
    auto _argument_str = JStringToCefString(env, argument);
    s->append_argument(s, _argument_str);
    if (_argument_str) cef_string_userfree_free(_argument_str);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefCommandLine_00024NativePeer_N_1PrependWrapper(JNIEnv* env, jobject obj, jlong self, jstring wrapper) {
    auto* s = reinterpret_cast<cef_command_line_t*>(self);
    if (!s) return;
    if (!wrapper) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "wrapper must not be null"); return;}
    auto _wrapper_str = JStringToCefString(env, wrapper);
    s->prepend_wrapper(s, _wrapper_str);
    if (_wrapper_str) cef_string_userfree_free(_wrapper_str);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefCommandLine_00024NativePeer_N_1RemoveSwitch(JNIEnv* env, jobject obj, jlong self, jstring name) {
    auto* s = reinterpret_cast<cef_command_line_t*>(self);
    if (!s) return;
    if (!name) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "name must not be null"); return;}
    auto _name_str = JStringToCefString(env, name);
    s->remove_switch(s, _name_str);
    if (_name_str) cef_string_userfree_free(_name_str);
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefCommandLine_00024NativePeer_N_1Create(JNIEnv* env, jclass clz) {
    auto _r = cef_command_line_create();
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefCommandLine$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefCommandLine_00024NativePeer_N_1GetGlobal(JNIEnv* env, jclass clz) {
    auto _r = cef_command_line_get_global();
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefCommandLine$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}
