// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
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

extern "C" cef_app_t* Create_JniCefApp(JNIEnv* env, jobject handler);
extern "C" cef_scheme_handler_factory_t* Create_JniCefSchemeHandlerFactory(JNIEnv* env, jobject handler);
extern "C" cef_task_t* Create_JniCefTask(JNIEnv* env, jobject handler);
extern "C" cef_completion_callback_t* Create_JniCefCompletionCallback(JNIEnv* env, jobject handler);
extern "C" cef_end_tracing_callback_t* Create_JniCefEndTracingCallback(JNIEnv* env, jobject handler);
extern "C" cef_v8_handler_t* Create_JniCefV8Handler(JNIEnv* env, jobject handler);
CEF4J_JNI_EXPORT(jint, CefGlobals, executeProcess0)(JNIEnv* env, jclass clz, jobject args, jobject application, jobject windows_sandbox_info) {
    cef_app_t* _application_ptr = application ? Create_JniCefApp(env, application) : nullptr;
    return static_cast<jint>(cef_execute_process(reinterpret_cast<const cef_main_args_t*>(args ? env->GetLongField(args, env->GetFieldID(env->GetObjectClass(args), "address", "J")) : 0), _application_ptr, reinterpret_cast<void*>(windows_sandbox_info ? env->GetLongField(windows_sandbox_info, env->GetFieldID(env->GetObjectClass(windows_sandbox_info), "address", "J")) : 0)));
}

CEF4J_JNI_EXPORT(jint, CefGlobals, initialize0)(JNIEnv* env, jclass clz, jobject args, jobject settings, jobject application, jobject windows_sandbox_info) {
    if (!settings) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "settings must not be null"); return 0; }
    cef_settings_t _settings_val = {};
    if (settings) {
        auto _c = FindClassCached(env, "net/kurobako/cef4j/gen/CefSettings");
        _settings_val.no_sandbox = static_cast<decltype(_settings_val.no_sandbox)>(env->GetIntField(settings, env->GetFieldID(_c, "noSandbox", "I")));
        jstring _rd_browser_subprocess_path = (jstring)env->GetObjectField(settings, env->GetFieldID(_c, "browserSubprocessPath", "Ljava/lang/String;"));
        if (_rd_browser_subprocess_path) {
            const jchar* _rd_browser_subprocess_path_chars = env->GetStringChars(_rd_browser_subprocess_path, nullptr);
            jsize _rd_browser_subprocess_path_len = env->GetStringLength(_rd_browser_subprocess_path);
            cef_string_set(reinterpret_cast<const char16_t*>(_rd_browser_subprocess_path_chars), _rd_browser_subprocess_path_len, &_settings_val.browser_subprocess_path, 1);
            env->ReleaseStringChars(_rd_browser_subprocess_path, _rd_browser_subprocess_path_chars);
        }
        jstring _rd_framework_dir_path = (jstring)env->GetObjectField(settings, env->GetFieldID(_c, "frameworkDirPath", "Ljava/lang/String;"));
        if (_rd_framework_dir_path) {
            const jchar* _rd_framework_dir_path_chars = env->GetStringChars(_rd_framework_dir_path, nullptr);
            jsize _rd_framework_dir_path_len = env->GetStringLength(_rd_framework_dir_path);
            cef_string_set(reinterpret_cast<const char16_t*>(_rd_framework_dir_path_chars), _rd_framework_dir_path_len, &_settings_val.framework_dir_path, 1);
            env->ReleaseStringChars(_rd_framework_dir_path, _rd_framework_dir_path_chars);
        }
        jstring _rd_main_bundle_path = (jstring)env->GetObjectField(settings, env->GetFieldID(_c, "mainBundlePath", "Ljava/lang/String;"));
        if (_rd_main_bundle_path) {
            const jchar* _rd_main_bundle_path_chars = env->GetStringChars(_rd_main_bundle_path, nullptr);
            jsize _rd_main_bundle_path_len = env->GetStringLength(_rd_main_bundle_path);
            cef_string_set(reinterpret_cast<const char16_t*>(_rd_main_bundle_path_chars), _rd_main_bundle_path_len, &_settings_val.main_bundle_path, 1);
            env->ReleaseStringChars(_rd_main_bundle_path, _rd_main_bundle_path_chars);
        }
        _settings_val.multi_threaded_message_loop = static_cast<decltype(_settings_val.multi_threaded_message_loop)>(env->GetIntField(settings, env->GetFieldID(_c, "multiThreadedMessageLoop", "I")));
        _settings_val.external_message_pump = static_cast<decltype(_settings_val.external_message_pump)>(env->GetIntField(settings, env->GetFieldID(_c, "externalMessagePump", "I")));
        _settings_val.windowless_rendering_enabled = static_cast<decltype(_settings_val.windowless_rendering_enabled)>(env->GetIntField(settings, env->GetFieldID(_c, "windowlessRenderingEnabled", "I")));
        _settings_val.command_line_args_disabled = static_cast<decltype(_settings_val.command_line_args_disabled)>(env->GetIntField(settings, env->GetFieldID(_c, "commandLineArgsDisabled", "I")));
        jstring _rd_cache_path = (jstring)env->GetObjectField(settings, env->GetFieldID(_c, "cachePath", "Ljava/lang/String;"));
        if (_rd_cache_path) {
            const jchar* _rd_cache_path_chars = env->GetStringChars(_rd_cache_path, nullptr);
            jsize _rd_cache_path_len = env->GetStringLength(_rd_cache_path);
            cef_string_set(reinterpret_cast<const char16_t*>(_rd_cache_path_chars), _rd_cache_path_len, &_settings_val.cache_path, 1);
            env->ReleaseStringChars(_rd_cache_path, _rd_cache_path_chars);
        }
        jstring _rd_root_cache_path = (jstring)env->GetObjectField(settings, env->GetFieldID(_c, "rootCachePath", "Ljava/lang/String;"));
        if (_rd_root_cache_path) {
            const jchar* _rd_root_cache_path_chars = env->GetStringChars(_rd_root_cache_path, nullptr);
            jsize _rd_root_cache_path_len = env->GetStringLength(_rd_root_cache_path);
            cef_string_set(reinterpret_cast<const char16_t*>(_rd_root_cache_path_chars), _rd_root_cache_path_len, &_settings_val.root_cache_path, 1);
            env->ReleaseStringChars(_rd_root_cache_path, _rd_root_cache_path_chars);
        }
        _settings_val.persist_session_cookies = static_cast<decltype(_settings_val.persist_session_cookies)>(env->GetIntField(settings, env->GetFieldID(_c, "persistSessionCookies", "I")));
        jstring _rd_user_agent = (jstring)env->GetObjectField(settings, env->GetFieldID(_c, "userAgent", "Ljava/lang/String;"));
        if (_rd_user_agent) {
            const jchar* _rd_user_agent_chars = env->GetStringChars(_rd_user_agent, nullptr);
            jsize _rd_user_agent_len = env->GetStringLength(_rd_user_agent);
            cef_string_set(reinterpret_cast<const char16_t*>(_rd_user_agent_chars), _rd_user_agent_len, &_settings_val.user_agent, 1);
            env->ReleaseStringChars(_rd_user_agent, _rd_user_agent_chars);
        }
        jstring _rd_user_agent_product = (jstring)env->GetObjectField(settings, env->GetFieldID(_c, "userAgentProduct", "Ljava/lang/String;"));
        if (_rd_user_agent_product) {
            const jchar* _rd_user_agent_product_chars = env->GetStringChars(_rd_user_agent_product, nullptr);
            jsize _rd_user_agent_product_len = env->GetStringLength(_rd_user_agent_product);
            cef_string_set(reinterpret_cast<const char16_t*>(_rd_user_agent_product_chars), _rd_user_agent_product_len, &_settings_val.user_agent_product, 1);
            env->ReleaseStringChars(_rd_user_agent_product, _rd_user_agent_product_chars);
        }
        jstring _rd_locale = (jstring)env->GetObjectField(settings, env->GetFieldID(_c, "locale", "Ljava/lang/String;"));
        if (_rd_locale) {
            const jchar* _rd_locale_chars = env->GetStringChars(_rd_locale, nullptr);
            jsize _rd_locale_len = env->GetStringLength(_rd_locale);
            cef_string_set(reinterpret_cast<const char16_t*>(_rd_locale_chars), _rd_locale_len, &_settings_val.locale, 1);
            env->ReleaseStringChars(_rd_locale, _rd_locale_chars);
        }
        jstring _rd_log_file = (jstring)env->GetObjectField(settings, env->GetFieldID(_c, "logFile", "Ljava/lang/String;"));
        if (_rd_log_file) {
            const jchar* _rd_log_file_chars = env->GetStringChars(_rd_log_file, nullptr);
            jsize _rd_log_file_len = env->GetStringLength(_rd_log_file);
            cef_string_set(reinterpret_cast<const char16_t*>(_rd_log_file_chars), _rd_log_file_len, &_settings_val.log_file, 1);
            env->ReleaseStringChars(_rd_log_file, _rd_log_file_chars);
        }
        auto _rd_log_severity = env->GetObjectField(settings, env->GetFieldID(_c, "logSeverity", "Lnet/kurobako/cef4j/gen/CefLogSeverity;"));
        if (_rd_log_severity) {
            _settings_val.log_severity = static_cast<decltype(_settings_val.log_severity)>(env->GetLongField(_rd_log_severity, env->GetFieldID(env->GetObjectClass(_rd_log_severity), "value", "J")));
        }
        auto _rd_log_items = env->GetObjectField(settings, env->GetFieldID(_c, "logItems", "Lnet/kurobako/cef4j/gen/CefLogItems;"));
        if (_rd_log_items) {
            _settings_val.log_items = static_cast<decltype(_settings_val.log_items)>(env->GetLongField(_rd_log_items, env->GetFieldID(env->GetObjectClass(_rd_log_items), "value", "J")));
        }
        jstring _rd_javascript_flags = (jstring)env->GetObjectField(settings, env->GetFieldID(_c, "javascriptFlags", "Ljava/lang/String;"));
        if (_rd_javascript_flags) {
            const jchar* _rd_javascript_flags_chars = env->GetStringChars(_rd_javascript_flags, nullptr);
            jsize _rd_javascript_flags_len = env->GetStringLength(_rd_javascript_flags);
            cef_string_set(reinterpret_cast<const char16_t*>(_rd_javascript_flags_chars), _rd_javascript_flags_len, &_settings_val.javascript_flags, 1);
            env->ReleaseStringChars(_rd_javascript_flags, _rd_javascript_flags_chars);
        }
        jstring _rd_resources_dir_path = (jstring)env->GetObjectField(settings, env->GetFieldID(_c, "resourcesDirPath", "Ljava/lang/String;"));
        if (_rd_resources_dir_path) {
            const jchar* _rd_resources_dir_path_chars = env->GetStringChars(_rd_resources_dir_path, nullptr);
            jsize _rd_resources_dir_path_len = env->GetStringLength(_rd_resources_dir_path);
            cef_string_set(reinterpret_cast<const char16_t*>(_rd_resources_dir_path_chars), _rd_resources_dir_path_len, &_settings_val.resources_dir_path, 1);
            env->ReleaseStringChars(_rd_resources_dir_path, _rd_resources_dir_path_chars);
        }
        jstring _rd_locales_dir_path = (jstring)env->GetObjectField(settings, env->GetFieldID(_c, "localesDirPath", "Ljava/lang/String;"));
        if (_rd_locales_dir_path) {
            const jchar* _rd_locales_dir_path_chars = env->GetStringChars(_rd_locales_dir_path, nullptr);
            jsize _rd_locales_dir_path_len = env->GetStringLength(_rd_locales_dir_path);
            cef_string_set(reinterpret_cast<const char16_t*>(_rd_locales_dir_path_chars), _rd_locales_dir_path_len, &_settings_val.locales_dir_path, 1);
            env->ReleaseStringChars(_rd_locales_dir_path, _rd_locales_dir_path_chars);
        }
        _settings_val.remote_debugging_port = static_cast<decltype(_settings_val.remote_debugging_port)>(env->GetIntField(settings, env->GetFieldID(_c, "remoteDebuggingPort", "I")));
        _settings_val.uncaught_exception_stack_size = static_cast<decltype(_settings_val.uncaught_exception_stack_size)>(env->GetIntField(settings, env->GetFieldID(_c, "uncaughtExceptionStackSize", "I")));
        _settings_val.background_color = static_cast<decltype(_settings_val.background_color)>(env->GetIntField(settings, env->GetFieldID(_c, "backgroundColor", "I")));
        jstring _rd_accept_language_list = (jstring)env->GetObjectField(settings, env->GetFieldID(_c, "acceptLanguageList", "Ljava/lang/String;"));
        if (_rd_accept_language_list) {
            const jchar* _rd_accept_language_list_chars = env->GetStringChars(_rd_accept_language_list, nullptr);
            jsize _rd_accept_language_list_len = env->GetStringLength(_rd_accept_language_list);
            cef_string_set(reinterpret_cast<const char16_t*>(_rd_accept_language_list_chars), _rd_accept_language_list_len, &_settings_val.accept_language_list, 1);
            env->ReleaseStringChars(_rd_accept_language_list, _rd_accept_language_list_chars);
        }
        jstring _rd_cookieable_schemes_list = (jstring)env->GetObjectField(settings, env->GetFieldID(_c, "cookieableSchemesList", "Ljava/lang/String;"));
        if (_rd_cookieable_schemes_list) {
            const jchar* _rd_cookieable_schemes_list_chars = env->GetStringChars(_rd_cookieable_schemes_list, nullptr);
            jsize _rd_cookieable_schemes_list_len = env->GetStringLength(_rd_cookieable_schemes_list);
            cef_string_set(reinterpret_cast<const char16_t*>(_rd_cookieable_schemes_list_chars), _rd_cookieable_schemes_list_len, &_settings_val.cookieable_schemes_list, 1);
            env->ReleaseStringChars(_rd_cookieable_schemes_list, _rd_cookieable_schemes_list_chars);
        }
        _settings_val.cookieable_schemes_exclude_defaults = static_cast<decltype(_settings_val.cookieable_schemes_exclude_defaults)>(env->GetIntField(settings, env->GetFieldID(_c, "cookieableSchemesExcludeDefaults", "I")));
        jstring _rd_chrome_policy_id = (jstring)env->GetObjectField(settings, env->GetFieldID(_c, "chromePolicyId", "Ljava/lang/String;"));
        if (_rd_chrome_policy_id) {
            const jchar* _rd_chrome_policy_id_chars = env->GetStringChars(_rd_chrome_policy_id, nullptr);
            jsize _rd_chrome_policy_id_len = env->GetStringLength(_rd_chrome_policy_id);
            cef_string_set(reinterpret_cast<const char16_t*>(_rd_chrome_policy_id_chars), _rd_chrome_policy_id_len, &_settings_val.chrome_policy_id, 1);
            env->ReleaseStringChars(_rd_chrome_policy_id, _rd_chrome_policy_id_chars);
        }
        _settings_val.chrome_app_icon_id = static_cast<decltype(_settings_val.chrome_app_icon_id)>(env->GetIntField(settings, env->GetFieldID(_c, "chromeAppIconId", "I")));
        _settings_val.disable_signal_handlers = static_cast<decltype(_settings_val.disable_signal_handlers)>(env->GetIntField(settings, env->GetFieldID(_c, "disableSignalHandlers", "I")));
        _settings_val.use_views_default_popup = static_cast<decltype(_settings_val.use_views_default_popup)>(env->GetIntField(settings, env->GetFieldID(_c, "useViewsDefaultPopup", "I")));
        _settings_val.size = sizeof(cef_settings_t);
    }
    cef_app_t* _application_ptr = application ? Create_JniCefApp(env, application) : nullptr;
    return static_cast<jint>(cef_initialize(reinterpret_cast<const cef_main_args_t*>(args ? env->GetLongField(args, env->GetFieldID(env->GetObjectClass(args), "address", "J")) : 0), &_settings_val, _application_ptr, reinterpret_cast<void*>(windows_sandbox_info ? env->GetLongField(windows_sandbox_info, env->GetFieldID(env->GetObjectClass(windows_sandbox_info), "address", "J")) : 0)));
}

CEF4J_JNI_EXPORT(jint, CefGlobals, getExitCode0)(JNIEnv* env, jclass clz) {
    return static_cast<jint>(cef_get_exit_code());
}

CEF4J_JNI_EXPORT(void, CefGlobals, shutdown0)(JNIEnv* env, jclass clz) {
    cef_shutdown();
}

CEF4J_JNI_EXPORT(void, CefGlobals, doMessageLoopWork0)(JNIEnv* env, jclass clz) {
    cef_do_message_loop_work();
}

CEF4J_JNI_EXPORT(void, CefGlobals, runMessageLoop0)(JNIEnv* env, jclass clz) {
    cef_run_message_loop();
}

CEF4J_JNI_EXPORT(void, CefGlobals, quitMessageLoop0)(JNIEnv* env, jclass clz) {
    cef_quit_message_loop();
}

CEF4J_JNI_EXPORT(void, CefGlobals, setNestableTasksAllowed0)(JNIEnv* env, jclass clz, jint allowed) {
    cef_set_nestable_tasks_allowed(allowed);
}

CEF4J_JNI_EXPORT(jint, CefGlobals, crashReportingEnabled0)(JNIEnv* env, jclass clz) {
    return static_cast<jint>(cef_crash_reporting_enabled());
}

CEF4J_JNI_EXPORT(void, CefGlobals, setCrashKeyValue0)(JNIEnv* env, jclass clz, jstring key, jstring value) {
    auto _key_str = JStringToCefString(env, key);
    auto _value_str = value ? JStringToCefString(env, value) : nullptr;
    cef_set_crash_key_value(_key_str, _value_str);
    if (_key_str) cef_string_userfree_free(_key_str);
    if (_value_str) cef_string_userfree_free(_value_str);
}

CEF4J_JNI_EXPORT(jint, CefGlobals, createDirectory0)(JNIEnv* env, jclass clz, jstring full_path) {
    auto _full_path_str = JStringToCefString(env, full_path);
    return static_cast<jint>(cef_create_directory(_full_path_str));
}

CEF4J_JNI_EXPORT(jint, CefGlobals, getTempDirectory0)(JNIEnv* env, jclass clz, jstring temp_dir) {
    auto _temp_dir_str = JStringToCefString(env, temp_dir);
    return static_cast<jint>(cef_get_temp_directory(_temp_dir_str));
}

CEF4J_JNI_EXPORT(jint, CefGlobals, createNewTempDirectory0)(JNIEnv* env, jclass clz, jstring prefix, jstring new_temp_path) {
    auto _prefix_str = prefix ? JStringToCefString(env, prefix) : nullptr;
    auto _new_temp_path_str = JStringToCefString(env, new_temp_path);
    return static_cast<jint>(cef_create_new_temp_directory(_prefix_str, _new_temp_path_str));
}

CEF4J_JNI_EXPORT(jint, CefGlobals, createTempDirectoryInDirectory0)(JNIEnv* env, jclass clz, jstring base_dir, jstring prefix, jstring new_dir) {
    auto _base_dir_str = JStringToCefString(env, base_dir);
    auto _prefix_str = prefix ? JStringToCefString(env, prefix) : nullptr;
    auto _new_dir_str = JStringToCefString(env, new_dir);
    return static_cast<jint>(cef_create_temp_directory_in_directory(_base_dir_str, _prefix_str, _new_dir_str));
}

CEF4J_JNI_EXPORT(jint, CefGlobals, directoryExists0)(JNIEnv* env, jclass clz, jstring path) {
    auto _path_str = JStringToCefString(env, path);
    return static_cast<jint>(cef_directory_exists(_path_str));
}

CEF4J_JNI_EXPORT(jint, CefGlobals, deleteFile0)(JNIEnv* env, jclass clz, jstring path, jint recursive) {
    auto _path_str = JStringToCefString(env, path);
    return static_cast<jint>(cef_delete_file(_path_str, recursive));
}

CEF4J_JNI_EXPORT(jint, CefGlobals, zipDirectory0)(JNIEnv* env, jclass clz, jstring src_dir, jstring dest_file, jint include_hidden_files) {
    auto _src_dir_str = JStringToCefString(env, src_dir);
    auto _dest_file_str = JStringToCefString(env, dest_file);
    return static_cast<jint>(cef_zip_directory(_src_dir_str, _dest_file_str, include_hidden_files));
}

CEF4J_JNI_EXPORT(void, CefGlobals, loadCrlsetsFile0)(JNIEnv* env, jclass clz, jstring path) {
    auto _path_str = JStringToCefString(env, path);
    cef_load_crlsets_file(_path_str);
    if (_path_str) cef_string_userfree_free(_path_str);
}

CEF4J_JNI_EXPORT(jint, CefGlobals, isRtl0)(JNIEnv* env, jclass clz) {
    return static_cast<jint>(cef_is_rtl());
}

CEF4J_JNI_EXPORT(jint, CefGlobals, addCrossOriginWhitelistEntry0)(JNIEnv* env, jclass clz, jstring source_origin, jstring target_protocol, jstring target_domain, jint allow_target_subdomains) {
    auto _source_origin_str = JStringToCefString(env, source_origin);
    auto _target_protocol_str = JStringToCefString(env, target_protocol);
    auto _target_domain_str = target_domain ? JStringToCefString(env, target_domain) : nullptr;
    return static_cast<jint>(cef_add_cross_origin_whitelist_entry(_source_origin_str, _target_protocol_str, _target_domain_str, allow_target_subdomains));
}

CEF4J_JNI_EXPORT(jint, CefGlobals, removeCrossOriginWhitelistEntry0)(JNIEnv* env, jclass clz, jstring source_origin, jstring target_protocol, jstring target_domain, jint allow_target_subdomains) {
    auto _source_origin_str = JStringToCefString(env, source_origin);
    auto _target_protocol_str = JStringToCefString(env, target_protocol);
    auto _target_domain_str = target_domain ? JStringToCefString(env, target_domain) : nullptr;
    return static_cast<jint>(cef_remove_cross_origin_whitelist_entry(_source_origin_str, _target_protocol_str, _target_domain_str, allow_target_subdomains));
}

CEF4J_JNI_EXPORT(jint, CefGlobals, clearCrossOriginWhitelist0)(JNIEnv* env, jclass clz) {
    return static_cast<jint>(cef_clear_cross_origin_whitelist());
}

CEF4J_JNI_EXPORT(jint, CefGlobals, resolveUrl0)(JNIEnv* env, jclass clz, jstring base_url, jstring relative_url, jstring resolved_url) {
    auto _base_url_str = JStringToCefString(env, base_url);
    auto _relative_url_str = JStringToCefString(env, relative_url);
    auto _resolved_url_str = JStringToCefString(env, resolved_url);
    return static_cast<jint>(cef_resolve_url(_base_url_str, _relative_url_str, _resolved_url_str));
}

CEF4J_JNI_EXPORT(jint, CefGlobals, parseUrl0)(JNIEnv* env, jclass clz, jstring url, jobject parts) {
    auto _url_str = JStringToCefString(env, url);
    return static_cast<jint>(cef_parse_url(_url_str, reinterpret_cast<struct _cef_urlparts_t*>(parts ? env->GetLongField(parts, env->GetFieldID(env->GetObjectClass(parts), "address", "J")) : 0)));
}

CEF4J_JNI_EXPORT(jint, CefGlobals, createUrl0)(JNIEnv* env, jclass clz, jobject parts, jstring url) {
    auto _url_str = JStringToCefString(env, url);
    return static_cast<jint>(cef_create_url(reinterpret_cast<const struct _cef_urlparts_t*>(parts ? env->GetLongField(parts, env->GetFieldID(env->GetObjectClass(parts), "address", "J")) : 0), _url_str));
}

CEF4J_JNI_EXPORT(jstring, CefGlobals, formatUrlForSecurityDisplay0)(JNIEnv* env, jclass clz, jstring origin_url) {
    auto _origin_url_str = JStringToCefString(env, origin_url);
    auto result = cef_format_url_for_security_display(_origin_url_str);
    if (_origin_url_str) cef_string_userfree_free(_origin_url_str);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jstring, CefGlobals, getMimeType0)(JNIEnv* env, jclass clz, jstring extension) {
    auto _extension_str = JStringToCefString(env, extension);
    auto result = cef_get_mime_type(_extension_str);
    if (_extension_str) cef_string_userfree_free(_extension_str);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(void, CefGlobals, getExtensionsForMimeType0)(JNIEnv* env, jclass clz, jstring mime_type, jobject extensions) {
    if (!extensions) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "extensions must not be null"); return; }
    auto _mime_type_str = JStringToCefString(env, mime_type);
    auto _extensions_csl = JavaListToCefStringList(env, extensions);
    cef_get_extensions_for_mime_type(_mime_type_str, _extensions_csl);
    if (_mime_type_str) cef_string_userfree_free(_mime_type_str);
    CefStringListWriteBack(env, _extensions_csl, extensions);
}

CEF4J_JNI_EXPORT(jstring, CefGlobals, base64Encode0)(JNIEnv* env, jclass clz, jobject data) {
    if (!data) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "data must not be null"); return nullptr; }
    const void* _data_addr = data ? env->GetDirectBufferAddress(data) : nullptr;
    if (data && !_data_addr) { env->ThrowNew(FindClassCached(env, "java/lang/IllegalArgumentException"), "data must be a direct ByteBuffer; use ByteBuffer.allocateDirect(...)"); return nullptr; }
    auto result = cef_base64_encode(_data_addr, static_cast<size_t>(env->GetDirectBufferCapacity(data)));
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jstring, CefGlobals, uriencode0)(JNIEnv* env, jclass clz, jstring text, jint use_plus) {
    auto _text_str = JStringToCefString(env, text);
    auto result = cef_uriencode(_text_str, use_plus);
    if (_text_str) cef_string_userfree_free(_text_str);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jstring, CefGlobals, uridecode0)(JNIEnv* env, jclass clz, jstring text, jint convert_to_utf8, jobject unescape_rule) {
    if (!unescape_rule) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "unescapeRule must not be null"); return nullptr; }
    auto _text_str = JStringToCefString(env, text);
    auto result = cef_uridecode(_text_str, convert_to_utf8, static_cast<cef_uri_unescape_rule_t>(env->GetLongField(unescape_rule, env->GetFieldID(env->GetObjectClass(unescape_rule), "value", "J"))));
    if (_text_str) cef_string_userfree_free(_text_str);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jstring, CefGlobals, writeJson0)(JNIEnv* env, jclass clz, jobject node, jobject options) {
    if (!options) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "options must not be null"); return nullptr; }
    cef_value_t* _node_ptr = node ? reinterpret_cast<cef_value_t*>(env->GetLongField(node, env->GetFieldID(env->GetObjectClass(node), "nativePtr", "J"))) : nullptr;
    if (_node_ptr) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_node_ptr); _b->add_ref(_b); }
    auto result = cef_write_json(_node_ptr, static_cast<cef_json_writer_options_t>(env->GetLongField(options, env->GetFieldID(env->GetObjectClass(options), "value", "J"))));
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jint, CefGlobals, getPath0)(JNIEnv* env, jclass clz, jobject key, jstring path) {
    if (!key) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "key must not be null"); return 0; }
    auto _path_str = JStringToCefString(env, path);
    return static_cast<jint>(cef_get_path(static_cast<cef_path_key_t>(env->GetLongField(key, env->GetFieldID(env->GetObjectClass(key), "value", "J"))), _path_str));
}

CEF4J_JNI_EXPORT(jint, CefGlobals, launchProcess0)(JNIEnv* env, jclass clz, jobject command_line) {
    cef_command_line_t* _command_line_ptr = command_line ? reinterpret_cast<cef_command_line_t*>(env->GetLongField(command_line, env->GetFieldID(env->GetObjectClass(command_line), "nativePtr", "J"))) : nullptr;
    if (_command_line_ptr) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_command_line_ptr); _b->add_ref(_b); }
    return static_cast<jint>(cef_launch_process(_command_line_ptr));
}

CEF4J_JNI_EXPORT(jboolean, CefGlobals, registerSchemeHandlerFactory0)(JNIEnv* env, jclass clz, jstring scheme_name, jstring domain_name, jobject factory) {
    auto _scheme_name_str = JStringToCefString(env, scheme_name);
    auto _domain_name_str = domain_name ? JStringToCefString(env, domain_name) : nullptr;
    cef_scheme_handler_factory_t* _factory_ptr = factory ? Create_JniCefSchemeHandlerFactory(env, factory) : nullptr;
    auto _r = cef_register_scheme_handler_factory(_scheme_name_str, _domain_name_str, _factory_ptr);
    if (_scheme_name_str) cef_string_userfree_free(_scheme_name_str);
    if (_domain_name_str) cef_string_userfree_free(_domain_name_str);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CefGlobals, clearSchemeHandlerFactories0)(JNIEnv* env, jclass clz) {
    auto _r = cef_clear_scheme_handler_factories();
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jint, CefGlobals, isCertStatusError0)(JNIEnv* env, jclass clz, jobject status) {
    if (!status) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "status must not be null"); return 0; }
    return static_cast<jint>(cef_is_cert_status_error(static_cast<cef_cert_status_t>(env->GetLongField(status, env->GetFieldID(env->GetObjectClass(status), "value", "J")))));
}

CEF4J_JNI_EXPORT(jint, CefGlobals, currentlyOn0)(JNIEnv* env, jclass clz, jobject threadId) {
    if (!threadId) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "threadId must not be null"); return 0; }
    return static_cast<jint>(cef_currently_on(static_cast<cef_thread_id_t>(env->GetLongField(threadId, env->GetFieldID(env->GetObjectClass(threadId), "value", "J")))));
}

CEF4J_JNI_EXPORT(jboolean, CefGlobals, postTask0)(JNIEnv* env, jclass clz, jobject threadId, jobject task) {
    if (!threadId) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "threadId must not be null"); return JNI_FALSE; }
    cef_task_t* _task_ptr = task ? Create_JniCefTask(env, task) : nullptr;
    auto _r = cef_post_task(static_cast<cef_thread_id_t>(env->GetLongField(threadId, env->GetFieldID(env->GetObjectClass(threadId), "value", "J"))), _task_ptr);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CefGlobals, postDelayedTask0)(JNIEnv* env, jclass clz, jobject threadId, jobject task, jlong delay_ms) {
    if (!threadId) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "threadId must not be null"); return JNI_FALSE; }
    cef_task_t* _task_ptr = task ? Create_JniCefTask(env, task) : nullptr;
    auto _r = cef_post_delayed_task(static_cast<cef_thread_id_t>(env->GetLongField(threadId, env->GetFieldID(env->GetObjectClass(threadId), "value", "J"))), _task_ptr, delay_ms);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jint, CefGlobals, beginTracing0)(JNIEnv* env, jclass clz, jstring categories, jobject callback) {
    auto _categories_str = categories ? JStringToCefString(env, categories) : nullptr;
    cef_completion_callback_t* _callback_ptr = callback ? Create_JniCefCompletionCallback(env, callback) : nullptr;
    return static_cast<jint>(cef_begin_tracing(_categories_str, _callback_ptr));
}

CEF4J_JNI_EXPORT(jint, CefGlobals, endTracing0)(JNIEnv* env, jclass clz, jstring tracing_file, jobject callback) {
    auto _tracing_file_str = tracing_file ? JStringToCefString(env, tracing_file) : nullptr;
    cef_end_tracing_callback_t* _callback_ptr = callback ? Create_JniCefEndTracingCallback(env, callback) : nullptr;
    return static_cast<jint>(cef_end_tracing(_tracing_file_str, _callback_ptr));
}

CEF4J_JNI_EXPORT(jlong, CefGlobals, nowFromSystemTraceTime0)(JNIEnv* env, jclass clz) {
    return static_cast<jlong>(cef_now_from_system_trace_time());
}

CEF4J_JNI_EXPORT(jint, CefGlobals, registerExtension0)(JNIEnv* env, jclass clz, jstring extension_name, jstring javascript_code, jobject handler) {
    auto _extension_name_str = JStringToCefString(env, extension_name);
    auto _javascript_code_str = JStringToCefString(env, javascript_code);
    cef_v8_handler_t* _handler_ptr = handler ? Create_JniCefV8Handler(env, handler) : nullptr;
    return static_cast<jint>(cef_register_extension(_extension_name_str, _javascript_code_str, _handler_ptr));
}
