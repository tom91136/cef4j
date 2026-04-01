// GENERATED - do not edit.
#include <jni.h>
#include "include/capi/cef_app_capi.h"
#include "include/capi/cef_callback_capi.h"
#include "include/capi/cef_command_line_capi.h"
#include "include/capi/cef_crash_util_capi.h"
#include "include/capi/cef_file_util_capi.h"
#include "include/capi/cef_i18n_util_capi.h"
#include "include/capi/cef_origin_whitelist_capi.h"
#include "include/capi/cef_parser_capi.h"
#include "include/capi/cef_path_util_capi.h"
#include "include/capi/cef_process_util_capi.h"
#include "include/capi/cef_scheme_capi.h"
#include "include/capi/cef_ssl_info_capi.h"
#include "include/capi/cef_task_capi.h"
#include "include/capi/cef_trace_capi.h"
#include "include/capi/cef_v8_capi.h"
#include "include/capi/cef_values_capi.h"
#include "jni_util.h"

extern "C" cef_completion_callback_t* Create_JniCefCompletionCallback(JNIEnv *env, jobject handler);
extern "C" cef_end_tracing_callback_t* Create_JniCefEndTracingCallback(JNIEnv *env, jobject handler);
extern "C" cef_task_t* Create_JniCefTask(JNIEnv *env, jobject handler);
extern "C" cef_scheme_handler_factory_t* Create_JniCefSchemeHandlerFactory(JNIEnv *env, jobject handler);
extern "C" cef_app_t* Create_JniCefApp(JNIEnv *env, jobject handler);
extern "C" JNIEXPORT jint JNICALL Java_net_kurobako_cef4j_gen_CefGlobals_N_1IsRtl(JNIEnv* env, jclass clz) {
    return static_cast<jint>(cef_is_rtl());
}

extern "C" JNIEXPORT jint JNICALL Java_net_kurobako_cef4j_gen_CefGlobals_N_1BeginTracing(JNIEnv* env, jclass clz, jstring categories, jobject callback) {
    if (!categories) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "categories must not be null"); return 0;}
    if (!callback) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "callback must not be null"); return 0;}
    auto _categories_str = JStringToCefString(env, categories);
    cef_completion_callback_t* _callback_ptr = Create_JniCefCompletionCallback(env, callback);
    return static_cast<jint>(cef_begin_tracing(_categories_str, _callback_ptr));
}

extern "C" JNIEXPORT jint JNICALL Java_net_kurobako_cef4j_gen_CefGlobals_N_1EndTracing(JNIEnv* env, jclass clz, jstring tracing_file, jobject callback) {
    if (!tracing_file) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "tracingFile must not be null"); return 0;}
    if (!callback) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "callback must not be null"); return 0;}
    auto _tracing_file_str = JStringToCefString(env, tracing_file);
    cef_end_tracing_callback_t* _callback_ptr = Create_JniCefEndTracingCallback(env, callback);
    return static_cast<jint>(cef_end_tracing(_tracing_file_str, _callback_ptr));
}

extern "C" JNIEXPORT jlong JNICALL Java_net_kurobako_cef4j_gen_CefGlobals_N_1NowFromSystemTraceTime(JNIEnv* env, jclass clz) {
    return static_cast<jlong>(cef_now_from_system_trace_time());
}

extern "C" JNIEXPORT jint JNICALL Java_net_kurobako_cef4j_gen_CefGlobals_N_1ResolveUrl(JNIEnv* env, jclass clz, jstring base_url, jstring relative_url, jstring resolved_url) {
    if (!base_url) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "baseUrl must not be null"); return 0;}
    if (!relative_url) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "relativeUrl must not be null"); return 0;}
    if (!resolved_url) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "resolvedUrl must not be null"); return 0;}
    auto _base_url_str = JStringToCefString(env, base_url);
    auto _relative_url_str = JStringToCefString(env, relative_url);
    auto _resolved_url_str = JStringToCefString(env, resolved_url);
    return static_cast<jint>(cef_resolve_url(_base_url_str, _relative_url_str, _resolved_url_str));
}

extern "C" JNIEXPORT jint JNICALL Java_net_kurobako_cef4j_gen_CefGlobals_N_1ParseUrl(JNIEnv* env, jclass clz, jstring url, jobject parts) {
    if (!url) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "url must not be null"); return 0;}
    if (!parts) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "parts must not be null"); return 0;}
    auto _url_str = JStringToCefString(env, url);
    return static_cast<jint>(cef_parse_url(_url_str, reinterpret_cast<struct _cef_urlparts_t*>(parts ? env->GetLongField(parts, env->GetFieldID(env->GetObjectClass(parts), "address", "J")) : 0)));
}

extern "C" JNIEXPORT jint JNICALL Java_net_kurobako_cef4j_gen_CefGlobals_N_1CreateUrl(JNIEnv* env, jclass clz, jobject parts, jstring url) {
    if (!parts) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "parts must not be null"); return 0;}
    if (!url) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "url must not be null"); return 0;}
    auto _url_str = JStringToCefString(env, url);
    return static_cast<jint>(cef_create_url(reinterpret_cast<const struct _cef_urlparts_t*>(parts ? env->GetLongField(parts, env->GetFieldID(env->GetObjectClass(parts), "address", "J")) : 0), _url_str));
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefGlobals_N_1FormatUrlForSecurityDisplay(JNIEnv* env, jclass clz, jstring origin_url) {
    if (!origin_url) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "originUrl must not be null"); return nullptr;}
    auto _origin_url_str = JStringToCefString(env, origin_url);
    auto result = cef_format_url_for_security_display(_origin_url_str);
    if (_origin_url_str) cef_string_userfree_free(_origin_url_str);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefGlobals_N_1GetMimeType(JNIEnv* env, jclass clz, jstring extension) {
    if (!extension) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "extension must not be null"); return nullptr;}
    auto _extension_str = JStringToCefString(env, extension);
    auto result = cef_get_mime_type(_extension_str);
    if (_extension_str) cef_string_userfree_free(_extension_str);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefGlobals_N_1GetExtensionsForMimeType(JNIEnv* env, jclass clz, jstring mime_type, jobject extensions) {
    if (!mime_type) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "mimeType must not be null"); return;}
    if (!extensions) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "extensions must not be null"); return;}
    auto _mime_type_str = JStringToCefString(env, mime_type);
    auto _extensions_csl = JavaListToCefStringList(env, extensions);
    cef_get_extensions_for_mime_type(_mime_type_str, _extensions_csl);
    if (_mime_type_str) cef_string_userfree_free(_mime_type_str);
    CefStringListWriteBack(env, _extensions_csl, extensions);
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefGlobals_N_1Base64Encode(JNIEnv* env, jclass clz, jobject data) {
    if (!data) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "data must not be null"); return nullptr;}
    const void* _data_addr = data ? env->GetDirectBufferAddress(data) : nullptr;
    auto result = cef_base64_encode(_data_addr, static_cast<size_t>(env->GetDirectBufferCapacity(data)));
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefGlobals_N_1Uriencode(JNIEnv* env, jclass clz, jstring text, jint use_plus) {
    if (!text) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "text must not be null"); return nullptr;}
    auto _text_str = JStringToCefString(env, text);
    auto result = cef_uriencode(_text_str, use_plus);
    if (_text_str) cef_string_userfree_free(_text_str);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefGlobals_N_1Uridecode(JNIEnv* env, jclass clz, jstring text, jint convert_to_utf8, jobject unescape_rule) {
    if (!text) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "text must not be null"); return nullptr;}
    if (!unescape_rule) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "unescapeRule must not be null"); return nullptr;}
    auto _text_str = JStringToCefString(env, text);
    auto result = cef_uridecode(_text_str, convert_to_utf8, static_cast<cef_uri_unescape_rule_t>(env->GetLongField(unescape_rule, env->GetFieldID(env->GetObjectClass(unescape_rule), "value", "J"))));
    if (_text_str) cef_string_userfree_free(_text_str);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefGlobals_N_1WriteJson(JNIEnv* env, jclass clz, jobject node, jobject options) {
    if (!node) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "node must not be null"); return nullptr;}
    if (!options) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "options must not be null"); return nullptr;}
    cef_value_t* _node_ptr = reinterpret_cast<cef_value_t*>(env->GetLongField(node, env->GetFieldID(env->GetObjectClass(node), "nativePtr", "J")));
    if (_node_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_node_ptr); _b->add_ref(_b);}
    auto result = cef_write_json(_node_ptr, static_cast<cef_json_writer_options_t>(env->GetLongField(options, env->GetFieldID(env->GetObjectClass(options), "value", "J"))));
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jint JNICALL Java_net_kurobako_cef4j_gen_CefGlobals_N_1CrashReportingEnabled(JNIEnv* env, jclass clz) {
    return static_cast<jint>(cef_crash_reporting_enabled());
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefGlobals_N_1SetCrashKeyValue(JNIEnv* env, jclass clz, jstring key, jstring value) {
    if (!key) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "key must not be null"); return;}
    if (!value) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "value must not be null"); return;}
    auto _key_str = JStringToCefString(env, key);
    auto _value_str = JStringToCefString(env, value);
    cef_set_crash_key_value(_key_str, _value_str);
    if (_key_str) cef_string_userfree_free(_key_str);
    if (_value_str) cef_string_userfree_free(_value_str);
}

extern "C" JNIEXPORT jint JNICALL Java_net_kurobako_cef4j_gen_CefGlobals_N_1CurrentlyOn(JNIEnv* env, jclass clz, jobject threadId) {
    if (!threadId) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "threadid must not be null"); return 0;}
    return static_cast<jint>(cef_currently_on(static_cast<cef_thread_id_t>(env->GetLongField(threadId, env->GetFieldID(env->GetObjectClass(threadId), "value", "J")))));
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefGlobals_N_1PostTask(JNIEnv* env, jclass clz, jobject threadId, jobject task) {
    if (!threadId) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "threadid must not be null"); return JNI_FALSE;}
    if (!task) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "task must not be null"); return JNI_FALSE;}
    cef_task_t* _task_ptr = Create_JniCefTask(env, task);
    auto _r = cef_post_task(static_cast<cef_thread_id_t>(env->GetLongField(threadId, env->GetFieldID(env->GetObjectClass(threadId), "value", "J"))), _task_ptr);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefGlobals_N_1PostDelayedTask(JNIEnv* env, jclass clz, jobject threadId, jobject task, jlong delay_ms) {
    if (!threadId) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "threadid must not be null"); return JNI_FALSE;}
    if (!task) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "task must not be null"); return JNI_FALSE;}
    cef_task_t* _task_ptr = Create_JniCefTask(env, task);
    auto _r = cef_post_delayed_task(static_cast<cef_thread_id_t>(env->GetLongField(threadId, env->GetFieldID(env->GetObjectClass(threadId), "value", "J"))), _task_ptr, delay_ms);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jint JNICALL Java_net_kurobako_cef4j_gen_CefGlobals_N_1AddCrossOriginWhitelistEntry(JNIEnv* env, jclass clz, jstring source_origin, jstring target_protocol, jstring target_domain, jint allow_target_subdomains) {
    if (!source_origin) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "sourceOrigin must not be null"); return 0;}
    if (!target_protocol) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "targetProtocol must not be null"); return 0;}
    if (!target_domain) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "targetDomain must not be null"); return 0;}
    auto _source_origin_str = JStringToCefString(env, source_origin);
    auto _target_protocol_str = JStringToCefString(env, target_protocol);
    auto _target_domain_str = JStringToCefString(env, target_domain);
    return static_cast<jint>(cef_add_cross_origin_whitelist_entry(_source_origin_str, _target_protocol_str, _target_domain_str, allow_target_subdomains));
}

extern "C" JNIEXPORT jint JNICALL Java_net_kurobako_cef4j_gen_CefGlobals_N_1RemoveCrossOriginWhitelistEntry(JNIEnv* env, jclass clz, jstring source_origin, jstring target_protocol, jstring target_domain, jint allow_target_subdomains) {
    if (!source_origin) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "sourceOrigin must not be null"); return 0;}
    if (!target_protocol) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "targetProtocol must not be null"); return 0;}
    if (!target_domain) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "targetDomain must not be null"); return 0;}
    auto _source_origin_str = JStringToCefString(env, source_origin);
    auto _target_protocol_str = JStringToCefString(env, target_protocol);
    auto _target_domain_str = JStringToCefString(env, target_domain);
    return static_cast<jint>(cef_remove_cross_origin_whitelist_entry(_source_origin_str, _target_protocol_str, _target_domain_str, allow_target_subdomains));
}

extern "C" JNIEXPORT jint JNICALL Java_net_kurobako_cef4j_gen_CefGlobals_N_1ClearCrossOriginWhitelist(JNIEnv* env, jclass clz) {
    return static_cast<jint>(cef_clear_cross_origin_whitelist());
}

extern "C" JNIEXPORT jint JNICALL Java_net_kurobako_cef4j_gen_CefGlobals_N_1IsCertStatusError(JNIEnv* env, jclass clz, jobject status) {
    if (!status) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "status must not be null"); return 0;}
    return static_cast<jint>(cef_is_cert_status_error(static_cast<cef_cert_status_t>(env->GetLongField(status, env->GetFieldID(env->GetObjectClass(status), "value", "J")))));
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefGlobals_N_1RegisterSchemeHandlerFactory(JNIEnv* env, jclass clz, jstring scheme_name, jstring domain_name, jobject factory) {
    if (!scheme_name) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "schemeName must not be null"); return JNI_FALSE;}
    auto _scheme_name_str = JStringToCefString(env, scheme_name);
    auto _domain_name_str = domain_name ? JStringToCefString(env, domain_name) : nullptr;
    cef_scheme_handler_factory_t* _factory_ptr = factory ? Create_JniCefSchemeHandlerFactory(env, factory) : nullptr;
    auto _r = cef_register_scheme_handler_factory(_scheme_name_str, _domain_name_str, _factory_ptr);
    if (_scheme_name_str) cef_string_userfree_free(_scheme_name_str);
    if (_domain_name_str) cef_string_userfree_free(_domain_name_str);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefGlobals_N_1ClearSchemeHandlerFactories(JNIEnv* env, jclass clz) {
    auto _r = cef_clear_scheme_handler_factories();
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jint JNICALL Java_net_kurobako_cef4j_gen_CefGlobals_N_1RegisterExtension(JNIEnv* env, jclass clz, jstring extension_name, jstring javascript_code, jobject handler) {
    if (!extension_name) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "extensionName must not be null"); return 0;}
    if (!javascript_code) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "javascriptCode must not be null"); return 0;}
    if (!handler) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "handler must not be null"); return 0;}
    auto _extension_name_str = JStringToCefString(env, extension_name);
    auto _javascript_code_str = JStringToCefString(env, javascript_code);
    cef_v8_handler_t* _handler_ptr = reinterpret_cast<cef_v8_handler_t*>(env->GetLongField(handler, env->GetFieldID(env->GetObjectClass(handler), "nativePtr", "J")));
    if (_handler_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_handler_ptr); _b->add_ref(_b);}
    return static_cast<jint>(cef_register_extension(_extension_name_str, _javascript_code_str, _handler_ptr));
}

extern "C" JNIEXPORT jint JNICALL Java_net_kurobako_cef4j_gen_CefGlobals_N_1LaunchProcess(JNIEnv* env, jclass clz, jobject command_line) {
    if (!command_line) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "commandLine must not be null"); return 0;}
    cef_command_line_t* _command_line_ptr = reinterpret_cast<cef_command_line_t*>(env->GetLongField(command_line, env->GetFieldID(env->GetObjectClass(command_line), "nativePtr", "J")));
    if (_command_line_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_command_line_ptr); _b->add_ref(_b);}
    return static_cast<jint>(cef_launch_process(_command_line_ptr));
}

extern "C" JNIEXPORT jint JNICALL Java_net_kurobako_cef4j_gen_CefGlobals_N_1GetPath(JNIEnv* env, jclass clz, jobject key, jstring path) {
    if (!key) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "key must not be null"); return 0;}
    if (!path) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "path must not be null"); return 0;}
    auto _path_str = JStringToCefString(env, path);
    return static_cast<jint>(cef_get_path(static_cast<cef_path_key_t>(env->GetLongField(key, env->GetFieldID(env->GetObjectClass(key), "value", "J"))), _path_str));
}

extern "C" JNIEXPORT jint JNICALL Java_net_kurobako_cef4j_gen_CefGlobals_N_1ExecuteProcess(JNIEnv* env, jclass clz, jobject args, jobject application, jobject windows_sandbox_info) {
    if (!args) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "args must not be null"); return 0;}
    if (!application) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "application must not be null"); return 0;}
    if (!windows_sandbox_info) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "windowsSandboxInfo must not be null"); return 0;}
    cef_app_t* _application_ptr = Create_JniCefApp(env, application);
    return static_cast<jint>(cef_execute_process(reinterpret_cast<const cef_main_args_t*>(args ? env->GetLongField(args, env->GetFieldID(env->GetObjectClass(args), "address", "J")) : 0), _application_ptr, reinterpret_cast<void*>(windows_sandbox_info ? env->GetLongField(windows_sandbox_info, env->GetFieldID(env->GetObjectClass(windows_sandbox_info), "address", "J")) : 0)));
}

extern "C" JNIEXPORT jint JNICALL Java_net_kurobako_cef4j_gen_CefGlobals_N_1Initialize(JNIEnv* env, jclass clz, jobject args, jobject settings, jobject application, jobject windows_sandbox_info) {
    if (!args) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "args must not be null"); return 0;}
    if (!settings) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "settings must not be null"); return 0;}
    if (!application) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "application must not be null"); return 0;}
    if (!windows_sandbox_info) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "windowsSandboxInfo must not be null"); return 0;}
    cef_app_t* _application_ptr = Create_JniCefApp(env, application);
    return static_cast<jint>(cef_initialize(reinterpret_cast<const cef_main_args_t*>(args ? env->GetLongField(args, env->GetFieldID(env->GetObjectClass(args), "address", "J")) : 0), reinterpret_cast<const struct _cef_settings_t*>(settings ? env->GetLongField(settings, env->GetFieldID(env->GetObjectClass(settings), "address", "J")) : 0), _application_ptr, reinterpret_cast<void*>(windows_sandbox_info ? env->GetLongField(windows_sandbox_info, env->GetFieldID(env->GetObjectClass(windows_sandbox_info), "address", "J")) : 0)));
}

extern "C" JNIEXPORT jint JNICALL Java_net_kurobako_cef4j_gen_CefGlobals_N_1GetExitCode(JNIEnv* env, jclass clz) {
    return static_cast<jint>(cef_get_exit_code());
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefGlobals_N_1Shutdown(JNIEnv* env, jclass clz) {
    cef_shutdown();
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefGlobals_N_1DoMessageLoopWork(JNIEnv* env, jclass clz) {
    cef_do_message_loop_work();
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefGlobals_N_1RunMessageLoop(JNIEnv* env, jclass clz) {
    cef_run_message_loop();
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefGlobals_N_1QuitMessageLoop(JNIEnv* env, jclass clz) {
    cef_quit_message_loop();
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefGlobals_N_1SetNestableTasksAllowed(JNIEnv* env, jclass clz, jint allowed) {
    cef_set_nestable_tasks_allowed(allowed);
}

extern "C" JNIEXPORT jint JNICALL Java_net_kurobako_cef4j_gen_CefGlobals_N_1CreateDirectory(JNIEnv* env, jclass clz, jstring full_path) {
    if (!full_path) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "fullPath must not be null"); return 0;}
    auto _full_path_str = JStringToCefString(env, full_path);
    return static_cast<jint>(cef_create_directory(_full_path_str));
}

extern "C" JNIEXPORT jint JNICALL Java_net_kurobako_cef4j_gen_CefGlobals_N_1GetTempDirectory(JNIEnv* env, jclass clz, jstring temp_dir) {
    if (!temp_dir) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "tempDir must not be null"); return 0;}
    auto _temp_dir_str = JStringToCefString(env, temp_dir);
    return static_cast<jint>(cef_get_temp_directory(_temp_dir_str));
}

extern "C" JNIEXPORT jint JNICALL Java_net_kurobako_cef4j_gen_CefGlobals_N_1CreateNewTempDirectory(JNIEnv* env, jclass clz, jstring prefix, jstring new_temp_path) {
    if (!prefix) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "prefix must not be null"); return 0;}
    if (!new_temp_path) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "newTempPath must not be null"); return 0;}
    auto _prefix_str = JStringToCefString(env, prefix);
    auto _new_temp_path_str = JStringToCefString(env, new_temp_path);
    return static_cast<jint>(cef_create_new_temp_directory(_prefix_str, _new_temp_path_str));
}

extern "C" JNIEXPORT jint JNICALL Java_net_kurobako_cef4j_gen_CefGlobals_N_1CreateTempDirectoryInDirectory(JNIEnv* env, jclass clz, jstring base_dir, jstring prefix, jstring new_dir) {
    if (!base_dir) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "baseDir must not be null"); return 0;}
    if (!prefix) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "prefix must not be null"); return 0;}
    if (!new_dir) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "newDir must not be null"); return 0;}
    auto _base_dir_str = JStringToCefString(env, base_dir);
    auto _prefix_str = JStringToCefString(env, prefix);
    auto _new_dir_str = JStringToCefString(env, new_dir);
    return static_cast<jint>(cef_create_temp_directory_in_directory(_base_dir_str, _prefix_str, _new_dir_str));
}

extern "C" JNIEXPORT jint JNICALL Java_net_kurobako_cef4j_gen_CefGlobals_N_1DirectoryExists(JNIEnv* env, jclass clz, jstring path) {
    if (!path) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "path must not be null"); return 0;}
    auto _path_str = JStringToCefString(env, path);
    return static_cast<jint>(cef_directory_exists(_path_str));
}

extern "C" JNIEXPORT jint JNICALL Java_net_kurobako_cef4j_gen_CefGlobals_N_1DeleteFile(JNIEnv* env, jclass clz, jstring path, jint recursive) {
    if (!path) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "path must not be null"); return 0;}
    auto _path_str = JStringToCefString(env, path);
    return static_cast<jint>(cef_delete_file(_path_str, recursive));
}

extern "C" JNIEXPORT jint JNICALL Java_net_kurobako_cef4j_gen_CefGlobals_N_1ZipDirectory(JNIEnv* env, jclass clz, jstring src_dir, jstring dest_file, jint include_hidden_files) {
    if (!src_dir) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "srcDir must not be null"); return 0;}
    if (!dest_file) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "destFile must not be null"); return 0;}
    auto _src_dir_str = JStringToCefString(env, src_dir);
    auto _dest_file_str = JStringToCefString(env, dest_file);
    return static_cast<jint>(cef_zip_directory(_src_dir_str, _dest_file_str, include_hidden_files));
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefGlobals_N_1LoadCrlsetsFile(JNIEnv* env, jclass clz, jstring path) {
    if (!path) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "path must not be null"); return;}
    auto _path_str = JStringToCefString(env, path);
    cef_load_crlsets_file(_path_str);
    if (_path_str) cef_string_userfree_free(_path_str);
}
