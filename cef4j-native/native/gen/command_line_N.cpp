// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
#include <jni.h>
#include "include/capi/cef_command_line_capi.h"
#include "jni_util.h"

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefCommandLine), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefCommandLine), isValid0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_command_line_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_valid(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefCommandLine), isReadOnly0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_command_line_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_read_only(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefCommandLine), copy0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_command_line_t*>(self);
    if (!s) return nullptr;
    auto _r = s->copy(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefCommandLine$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefCommandLine), initFromArgv0)(JNIEnv* env, jobject obj, jlong self, jint argc, jobject argv) {
    auto* s = reinterpret_cast<cef_command_line_t*>(self);
    if (!s) return;
    s->init_from_argv(s, argc, reinterpret_cast<const char* const*>(argv ? env->GetLongField(argv, env->GetFieldID(env->GetObjectClass(argv), "address", "J")) : 0));
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefCommandLine), initFromString0)(JNIEnv* env, jobject obj, jlong self, jstring command_line) {
    auto* s = reinterpret_cast<cef_command_line_t*>(self);
    if (!s) return;
    auto _command_line_str = JStringToCefString(env, command_line);
    s->init_from_string(s, _command_line_str);
    if (_command_line_str) cef_string_userfree_free(_command_line_str);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefCommandLine), reset0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_command_line_t*>(self);
    if (!s) return;
    s->reset(s);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefCommandLine), getArgv0)(JNIEnv* env, jobject obj, jlong self, jobject argv) {
    auto* s = reinterpret_cast<cef_command_line_t*>(self);
    if (!s) return;
    if (!argv) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "argv must not be null"); return;}
    auto _argv_csl = JavaListToCefStringList(env, argv);
    s->get_argv(s, _argv_csl);
    CefStringListWriteBack(env, _argv_csl, argv);
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefCommandLine), getCommandLineString0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_command_line_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_command_line_string(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefCommandLine), getProgram0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_command_line_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_program(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefCommandLine), setProgram0)(JNIEnv* env, jobject obj, jlong self, jstring program) {
    auto* s = reinterpret_cast<cef_command_line_t*>(self);
    if (!s) return;
    auto _program_str = JStringToCefString(env, program);
    s->set_program(s, _program_str);
    if (_program_str) cef_string_userfree_free(_program_str);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefCommandLine), hasSwitches0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_command_line_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->has_switches(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefCommandLine), hasSwitch0)(JNIEnv* env, jobject obj, jlong self, jstring name) {
    auto* s = reinterpret_cast<cef_command_line_t*>(self);
    if (!s) return JNI_FALSE;
    auto _name_str = JStringToCefString(env, name);
    auto _r = s->has_switch(s, _name_str);
    if (_name_str) cef_string_userfree_free(_name_str);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefCommandLine), getSwitchValue0)(JNIEnv* env, jobject obj, jlong self, jstring name) {
    auto* s = reinterpret_cast<cef_command_line_t*>(self);
    if (!s) return nullptr;
    auto _name_str = JStringToCefString(env, name);
    auto result = s->get_switch_value(s, _name_str);
    if (_name_str) cef_string_userfree_free(_name_str);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefCommandLine), getSwitches0)(JNIEnv* env, jobject obj, jlong self, jobject switches) {
    auto* s = reinterpret_cast<cef_command_line_t*>(self);
    if (!s) return;
    if (!switches) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "switches must not be null"); return;}
    auto _switches_csm = JavaMapToCefStringMap(env, switches);
    s->get_switches(s, _switches_csm);
    CefStringMapWriteBack(env, _switches_csm, switches);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefCommandLine), appendSwitch0)(JNIEnv* env, jobject obj, jlong self, jstring name) {
    auto* s = reinterpret_cast<cef_command_line_t*>(self);
    if (!s) return;
    auto _name_str = JStringToCefString(env, name);
    s->append_switch(s, _name_str);
    if (_name_str) cef_string_userfree_free(_name_str);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefCommandLine), appendSwitchWithValue0)(JNIEnv* env, jobject obj, jlong self, jstring name, jstring value) {
    auto* s = reinterpret_cast<cef_command_line_t*>(self);
    if (!s) return;
    auto _name_str = JStringToCefString(env, name);
    auto _value_str = JStringToCefString(env, value);
    s->append_switch_with_value(s, _name_str, _value_str);
    if (_name_str) cef_string_userfree_free(_name_str);
    if (_value_str) cef_string_userfree_free(_value_str);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefCommandLine), hasArguments0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_command_line_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->has_arguments(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefCommandLine), getArguments0)(JNIEnv* env, jobject obj, jlong self, jobject arguments) {
    auto* s = reinterpret_cast<cef_command_line_t*>(self);
    if (!s) return;
    if (!arguments) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "arguments must not be null"); return;}
    auto _arguments_csl = JavaListToCefStringList(env, arguments);
    s->get_arguments(s, _arguments_csl);
    CefStringListWriteBack(env, _arguments_csl, arguments);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefCommandLine), appendArgument0)(JNIEnv* env, jobject obj, jlong self, jstring argument) {
    auto* s = reinterpret_cast<cef_command_line_t*>(self);
    if (!s) return;
    auto _argument_str = JStringToCefString(env, argument);
    s->append_argument(s, _argument_str);
    if (_argument_str) cef_string_userfree_free(_argument_str);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefCommandLine), prependWrapper0)(JNIEnv* env, jobject obj, jlong self, jstring wrapper) {
    auto* s = reinterpret_cast<cef_command_line_t*>(self);
    if (!s) return;
    auto _wrapper_str = JStringToCefString(env, wrapper);
    s->prepend_wrapper(s, _wrapper_str);
    if (_wrapper_str) cef_string_userfree_free(_wrapper_str);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefCommandLine), removeSwitch0)(JNIEnv* env, jobject obj, jlong self, jstring name) {
    auto* s = reinterpret_cast<cef_command_line_t*>(self);
    if (!s) return;
    auto _name_str = JStringToCefString(env, name);
    s->remove_switch(s, _name_str);
    if (_name_str) cef_string_userfree_free(_name_str);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefCommandLine), create0)(JNIEnv* env, jclass clz) {
    auto _r = cef_command_line_create();
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefCommandLine$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefCommandLine), getGlobal0)(JNIEnv* env, jclass clz) {
    auto _r = cef_command_line_get_global();
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefCommandLine$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}
