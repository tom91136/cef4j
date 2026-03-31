// Hand-written CefApp JNI bindings - stable across CEF versions.
//
// cef_initialize / cef_shutdown / cef_do_message_loop_work are not generated
// because CefMainArgs is a platform-discriminated union and cef_settings_t
// has complex string fields that need manual marshaling.

#include <jni.h>
#include <cstring>
#include <string>
#include <vector>
#include "jni_util.h"
#include "include/capi/cef_app_capi.h"
#include "include/cef_api_hash.h"

// Forward-declare GetJVM from jni_util.cpp
extern JavaVM* GetJVM();

// ---------------------------------------------------------------------------
// Java_net_kurobako_cef4j_CefApp_N_1Initialize
//
// Signature: (Ljava/lang/String;Ljava/lang/String;ZZ)Z
//   cachePath       - path for cache directory (may be null)
//   userAgent       - custom user-agent string (may be null)
//   windowlessMode  - always true for cef4j (OSR-only)
//   externalMessagePump - if true, caller drives the message loop
// ---------------------------------------------------------------------------
extern "C" JNIEXPORT jboolean JNICALL
Java_net_kurobako_cef4j_CefApp_N_1Initialize(
    JNIEnv* env, jobject /*obj*/,
    jstring cachePath, jstring userAgent,
    jboolean windowlessMode, jboolean externalMessagePump,
    jstring subprocessPath, jstring resourcesPath,
    jobjectArray extraArgs) {

    cef_settings_t settings{};
    settings.size = sizeof(cef_settings_t);
    settings.no_sandbox = 1;
    settings.windowless_rendering_enabled = windowlessMode ? 1 : 0;
    settings.external_message_pump = externalMessagePump ? 1 : 0;
    settings.multi_threaded_message_loop = 0;
#if !defined(_WIN32)
    settings.disable_signal_handlers = 1;  // JVM manages its own signal handlers
#endif

    if (cachePath) {
        JStringToCefString(env, cachePath, &settings.cache_path);
        JStringToCefString(env, cachePath, &settings.root_cache_path);
    }
    if (userAgent) {
        JStringToCefString(env, userAgent, &settings.user_agent);
    }
    if (subprocessPath) {
        JStringToCefString(env, subprocessPath, &settings.browser_subprocess_path);
    }
    if (resourcesPath) {
        JStringToCefString(env, resourcesPath, &settings.resources_dir_path);
        // Locales are in a subdirectory of the resources path
        std::string localesStr;
        const char* rp = env->GetStringUTFChars(resourcesPath, nullptr);
        localesStr = std::string(rp) + "/locales";
        env->ReleaseStringUTFChars(resourcesPath, rp);
        cef_string_utf8_to_utf16(localesStr.c_str(), localesStr.size(), &settings.locales_dir_path);
    }

    // Platform-specific main args
#if defined(_WIN32)
    cef_main_args_t mainArgs{};
    mainArgs.instance = GetModuleHandle(nullptr);
    (void)extraArgs;  // Windows uses HINSTANCE, not argc/argv
#else
    // On Linux/macOS, main_args expects argc/argv.
    // Always prepend --no-zygote to avoid zygote fork issues in JVM.
    std::vector<std::string> argStorage;
    argStorage.push_back("");            // argv[0] placeholder
    argStorage.push_back("--no-zygote"); // required for JVM-loaded CEF

    if (extraArgs) {
        jsize len = env->GetArrayLength(extraArgs);
        for (jsize i = 0; i < len; i++) {
            jstring js = static_cast<jstring>(env->GetObjectArrayElement(extraArgs, i));
            if (!js) continue;
            const char* s = env->GetStringUTFChars(js, nullptr);
            argStorage.push_back(s);
            env->ReleaseStringUTFChars(js, s);
            env->DeleteLocalRef(js);
        }
    }

    std::vector<char*> argv;
    for (auto& s : argStorage) {
        argv.push_back(&s[0]);
    }
    argv.push_back(nullptr);

    cef_main_args_t mainArgs{};
    mainArgs.argc = static_cast<int>(argStorage.size());
    mainArgs.argv = argv.data();
#endif

    // Configure CEF API version before initialization.
    // CEF 146+ validates struct versions at the DLL boundary.
    cef_api_hash(CEF_API_VERSION, 0);

    int result = cef_initialize(&mainArgs, &settings, nullptr, nullptr);

    // Clean up CEF strings allocated by JStringToCefString
    cef_string_clear(&settings.cache_path);
    cef_string_clear(&settings.root_cache_path);
    cef_string_clear(&settings.user_agent);
    cef_string_clear(&settings.browser_subprocess_path);
    cef_string_clear(&settings.resources_dir_path);
    cef_string_clear(&settings.locales_dir_path);

    return result ? JNI_TRUE : JNI_FALSE;
}

// ---------------------------------------------------------------------------
// Java_net_kurobako_cef4j_CefApp_N_1Shutdown
// ---------------------------------------------------------------------------
extern "C" JNIEXPORT void JNICALL
Java_net_kurobako_cef4j_CefApp_N_1Shutdown(JNIEnv* /*env*/, jobject /*obj*/) {
    cef_shutdown();
}

// ---------------------------------------------------------------------------
// Java_net_kurobako_cef4j_CefApp_N_1DoMessageLoopWork
//
// Called from the Java message pump timer when external_message_pump is true.
// ---------------------------------------------------------------------------
extern "C" JNIEXPORT void JNICALL
Java_net_kurobako_cef4j_CefApp_N_1DoMessageLoopWork(JNIEnv* /*env*/, jobject /*obj*/) {
    cef_do_message_loop_work();
}

// ---------------------------------------------------------------------------
// Java_net_kurobako_cef4j_CefApp_N_1RunMessageLoop
//
// Blocks until CefQuitMessageLoop is called. Used when external_message_pump
// is false - the caller runs this on a dedicated thread.
// ---------------------------------------------------------------------------
extern "C" JNIEXPORT void JNICALL
Java_net_kurobako_cef4j_CefApp_N_1RunMessageLoop(JNIEnv* /*env*/, jobject /*obj*/) {
    cef_run_message_loop();
}

// ---------------------------------------------------------------------------
// Java_net_kurobako_cef4j_CefApp_N_1QuitMessageLoop
// ---------------------------------------------------------------------------
extern "C" JNIEXPORT void JNICALL
Java_net_kurobako_cef4j_CefApp_N_1QuitMessageLoop(JNIEnv* /*env*/, jobject /*obj*/) {
    cef_quit_message_loop();
}
