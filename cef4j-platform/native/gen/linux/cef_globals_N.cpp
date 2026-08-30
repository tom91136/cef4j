// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
#include <jni.h>
#include "include/capi/cef_app_capi.h"
#include "jni_util.h"

extern "C" cef_app_t* Create_JniCefApp(JNIEnv* env, jobject handler);
CEF4J_JNI_EXPORT(jint, CefGlobals, executeProcess0)(JNIEnv* env, jclass clz, jobject args, jobject application, jobject windows_sandbox_info) {
    if (!args) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "args must not be null"); return 0; }
    cef_main_args_t _args_val = {};
    auto _args_c = FindClassCached(env, "net/kurobako/cef4j/gen/linux/CefMainArgs");
    _args_val.argc = static_cast<decltype(_args_val.argc)>(env->GetIntField(args, env->GetFieldID(_args_c, "argc", "I")));
    auto _rd_argv_list = env->GetObjectField(args, env->GetFieldID(_args_c, "argv", "Ljava/util/List;"));
    std::vector<std::string> _rd_argv_storage;
    std::vector<char*> _rd_argv_ptrs;
    auto _rd_argv_arr = JavaListToCStringArray(env, _rd_argv_list, _rd_argv_storage, _rd_argv_ptrs);
    _args_val.argv = static_cast<decltype(_args_val.argv)>(_rd_argv_arr);
    cef_app_t* _application_ptr = application ? Create_JniCefApp(env, application) : nullptr;
    return static_cast<jint>(cef_execute_process(&_args_val, _application_ptr, reinterpret_cast<void*>(windows_sandbox_info ? env->GetLongField(windows_sandbox_info, env->GetFieldID(env->GetObjectClass(windows_sandbox_info), "address", "J")) : 0)));
}

CEF4J_JNI_EXPORT(jint, CefGlobals, initialize0)(JNIEnv* env, jclass clz, jobject args, jobject settings, jobject application, jobject windows_sandbox_info) {
    if (!args) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "args must not be null"); return 0; }
    if (!settings) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "settings must not be null"); return 0; }
    cef_main_args_t _args_val = {};
    auto _args_c = FindClassCached(env, "net/kurobako/cef4j/gen/linux/CefMainArgs");
    _args_val.argc = static_cast<decltype(_args_val.argc)>(env->GetIntField(args, env->GetFieldID(_args_c, "argc", "I")));
    auto _rd_argv_list = env->GetObjectField(args, env->GetFieldID(_args_c, "argv", "Ljava/util/List;"));
    std::vector<std::string> _rd_argv_storage;
    std::vector<char*> _rd_argv_ptrs;
    auto _rd_argv_arr = JavaListToCStringArray(env, _rd_argv_list, _rd_argv_storage, _rd_argv_ptrs);
    _args_val.argv = static_cast<decltype(_args_val.argv)>(_rd_argv_arr);
    cef_settings_t _settings_val = {};
    auto _settings_c = FindClassCached(env, "net/kurobako/cef4j/gen/CefSettings");
    _settings_val.no_sandbox = static_cast<decltype(_settings_val.no_sandbox)>(env->GetIntField(settings, env->GetFieldID(_settings_c, "noSandbox", "I")));
    jstring _rd_browser_subprocess_path = (jstring)env->GetObjectField(settings, env->GetFieldID(_settings_c, "browserSubprocessPath", "Ljava/lang/String;"));
    CefStringSetFromJString(env, _rd_browser_subprocess_path, &_settings_val.browser_subprocess_path);
    jstring _rd_framework_dir_path = (jstring)env->GetObjectField(settings, env->GetFieldID(_settings_c, "frameworkDirPath", "Ljava/lang/String;"));
    CefStringSetFromJString(env, _rd_framework_dir_path, &_settings_val.framework_dir_path);
    jstring _rd_main_bundle_path = (jstring)env->GetObjectField(settings, env->GetFieldID(_settings_c, "mainBundlePath", "Ljava/lang/String;"));
    CefStringSetFromJString(env, _rd_main_bundle_path, &_settings_val.main_bundle_path);
    _settings_val.multi_threaded_message_loop = static_cast<decltype(_settings_val.multi_threaded_message_loop)>(env->GetIntField(settings, env->GetFieldID(_settings_c, "multiThreadedMessageLoop", "I")));
    _settings_val.external_message_pump = static_cast<decltype(_settings_val.external_message_pump)>(env->GetIntField(settings, env->GetFieldID(_settings_c, "externalMessagePump", "I")));
    _settings_val.windowless_rendering_enabled = static_cast<decltype(_settings_val.windowless_rendering_enabled)>(env->GetIntField(settings, env->GetFieldID(_settings_c, "windowlessRenderingEnabled", "I")));
    _settings_val.command_line_args_disabled = static_cast<decltype(_settings_val.command_line_args_disabled)>(env->GetIntField(settings, env->GetFieldID(_settings_c, "commandLineArgsDisabled", "I")));
    jstring _rd_cache_path = (jstring)env->GetObjectField(settings, env->GetFieldID(_settings_c, "cachePath", "Ljava/lang/String;"));
    CefStringSetFromJString(env, _rd_cache_path, &_settings_val.cache_path);
    jstring _rd_root_cache_path = (jstring)env->GetObjectField(settings, env->GetFieldID(_settings_c, "rootCachePath", "Ljava/lang/String;"));
    CefStringSetFromJString(env, _rd_root_cache_path, &_settings_val.root_cache_path);
    _settings_val.persist_session_cookies = static_cast<decltype(_settings_val.persist_session_cookies)>(env->GetIntField(settings, env->GetFieldID(_settings_c, "persistSessionCookies", "I")));
    jstring _rd_user_agent = (jstring)env->GetObjectField(settings, env->GetFieldID(_settings_c, "userAgent", "Ljava/lang/String;"));
    CefStringSetFromJString(env, _rd_user_agent, &_settings_val.user_agent);
    jstring _rd_user_agent_product = (jstring)env->GetObjectField(settings, env->GetFieldID(_settings_c, "userAgentProduct", "Ljava/lang/String;"));
    CefStringSetFromJString(env, _rd_user_agent_product, &_settings_val.user_agent_product);
    jstring _rd_locale = (jstring)env->GetObjectField(settings, env->GetFieldID(_settings_c, "locale", "Ljava/lang/String;"));
    CefStringSetFromJString(env, _rd_locale, &_settings_val.locale);
    jstring _rd_log_file = (jstring)env->GetObjectField(settings, env->GetFieldID(_settings_c, "logFile", "Ljava/lang/String;"));
    CefStringSetFromJString(env, _rd_log_file, &_settings_val.log_file);
    auto _rd_log_severity = env->GetObjectField(settings, env->GetFieldID(_settings_c, "logSeverity", "Lnet/kurobako/cef4j/gen/CefLogSeverity;"));
    if (_rd_log_severity) {
        _settings_val.log_severity = static_cast<decltype(_settings_val.log_severity)>(env->GetLongField(_rd_log_severity, env->GetFieldID(env->GetObjectClass(_rd_log_severity), "value", "J")));
    }
    auto _rd_log_items = env->GetObjectField(settings, env->GetFieldID(_settings_c, "logItems", "Lnet/kurobako/cef4j/gen/CefLogItems;"));
    if (_rd_log_items) {
        _settings_val.log_items = static_cast<decltype(_settings_val.log_items)>(env->GetLongField(_rd_log_items, env->GetFieldID(env->GetObjectClass(_rd_log_items), "value", "J")));
    }
    jstring _rd_javascript_flags = (jstring)env->GetObjectField(settings, env->GetFieldID(_settings_c, "javascriptFlags", "Ljava/lang/String;"));
    CefStringSetFromJString(env, _rd_javascript_flags, &_settings_val.javascript_flags);
    jstring _rd_resources_dir_path = (jstring)env->GetObjectField(settings, env->GetFieldID(_settings_c, "resourcesDirPath", "Ljava/lang/String;"));
    CefStringSetFromJString(env, _rd_resources_dir_path, &_settings_val.resources_dir_path);
    jstring _rd_locales_dir_path = (jstring)env->GetObjectField(settings, env->GetFieldID(_settings_c, "localesDirPath", "Ljava/lang/String;"));
    CefStringSetFromJString(env, _rd_locales_dir_path, &_settings_val.locales_dir_path);
    _settings_val.remote_debugging_port = static_cast<decltype(_settings_val.remote_debugging_port)>(env->GetIntField(settings, env->GetFieldID(_settings_c, "remoteDebuggingPort", "I")));
    _settings_val.uncaught_exception_stack_size = static_cast<decltype(_settings_val.uncaught_exception_stack_size)>(env->GetIntField(settings, env->GetFieldID(_settings_c, "uncaughtExceptionStackSize", "I")));
    _settings_val.background_color = static_cast<decltype(_settings_val.background_color)>(env->GetIntField(settings, env->GetFieldID(_settings_c, "backgroundColor", "I")));
    jstring _rd_accept_language_list = (jstring)env->GetObjectField(settings, env->GetFieldID(_settings_c, "acceptLanguageList", "Ljava/lang/String;"));
    CefStringSetFromJString(env, _rd_accept_language_list, &_settings_val.accept_language_list);
    jstring _rd_cookieable_schemes_list = (jstring)env->GetObjectField(settings, env->GetFieldID(_settings_c, "cookieableSchemesList", "Ljava/lang/String;"));
    CefStringSetFromJString(env, _rd_cookieable_schemes_list, &_settings_val.cookieable_schemes_list);
    _settings_val.cookieable_schemes_exclude_defaults = static_cast<decltype(_settings_val.cookieable_schemes_exclude_defaults)>(env->GetIntField(settings, env->GetFieldID(_settings_c, "cookieableSchemesExcludeDefaults", "I")));
    jstring _rd_chrome_policy_id = (jstring)env->GetObjectField(settings, env->GetFieldID(_settings_c, "chromePolicyId", "Ljava/lang/String;"));
    CefStringSetFromJString(env, _rd_chrome_policy_id, &_settings_val.chrome_policy_id);
    _settings_val.chrome_app_icon_id = static_cast<decltype(_settings_val.chrome_app_icon_id)>(env->GetIntField(settings, env->GetFieldID(_settings_c, "chromeAppIconId", "I")));
    _settings_val.disable_signal_handlers = static_cast<decltype(_settings_val.disable_signal_handlers)>(env->GetIntField(settings, env->GetFieldID(_settings_c, "disableSignalHandlers", "I")));
    _settings_val.use_views_default_popup = static_cast<decltype(_settings_val.use_views_default_popup)>(env->GetIntField(settings, env->GetFieldID(_settings_c, "useViewsDefaultPopup", "I")));
    _settings_val.size = sizeof(cef_settings_t);
    cef_app_t* _application_ptr = application ? Create_JniCefApp(env, application) : nullptr;
    return static_cast<jint>(Cef4jInitialize(&_args_val, &_settings_val, _application_ptr, reinterpret_cast<void*>(windows_sandbox_info ? env->GetLongField(windows_sandbox_info, env->GetFieldID(env->GetObjectClass(windows_sandbox_info), "address", "J")) : 0)));
}
