// Hand-written JNI helper for constructing platform-correct cef_main_args_t.
//
// Linux/macOS: cef_main_args_t = { int argc; char** argv; }
//   - Allocates argc/argv from Java String[], prepends --no-zygote.
//   - CEF copies what it needs during cef_initialize; caller frees after.
//
// Windows: cef_main_args_t = { HINSTANCE instance; }
//   - Uses GetModuleHandle(NULL). Extra args must go through
//     CefApp::OnBeforeCommandLineProcessing instead.

#include <jni.h>
#include <cstdlib>
#include <cstring>

#ifdef _WIN32
#include <windows.h>
#include "include/internal/cef_types_win.h"
#else
#include "include/internal/cef_types_linux.h"
#endif

// JNI class: net.kurobako.cef4j.CefApp
// Method: private static native long nCreateMainArgs(String[] extraArgs)
//
// Linux/macOS: builds argc/argv with --no-zygote + any extra args.
// Windows: sets HINSTANCE only (extraArgs ignored - Java routes them through
//          CefApp::OnBeforeCommandLineProcessing instead).

extern "C" JNIEXPORT jlong JNICALL
Java_net_kurobako_cef4j_CefApp_nCreateMainArgs(JNIEnv* env, jclass clz, jobjectArray extraArgs) {

#ifdef _WIN32
    auto* args = static_cast<cef_main_args_t*>(std::malloc(sizeof(cef_main_args_t)));
    if (!args) {
        env->ThrowNew(env->FindClass("java/lang/OutOfMemoryError"), "Failed to allocate cef_main_args_t");
        return 0;
    }
    args->instance = GetModuleHandle(NULL);

#else
    // Linux/macOS: argv = { "cef4j", "--no-zygote", ...extraArgs, NULL }
    int extraCount = extraArgs ? env->GetArrayLength(extraArgs) : 0;
    int argc = 2 + extraCount;
    auto** argv = static_cast<char**>(std::malloc(sizeof(char*) * (argc + 1)));
    if (!argv) {
        env->ThrowNew(env->FindClass("java/lang/OutOfMemoryError"), "Failed to allocate argv");
        return 0;
    }

    auto freeArgv = [&](int n) {
        for (int j = 0; j < n; j++) std::free(argv[j]);
        std::free(argv);
    };

    argv[0] = strdup("cef4j");
    argv[1] = strdup("--no-zygote");
    if (!argv[0] || !argv[1]) {
        freeArgv(2);
        env->ThrowNew(env->FindClass("java/lang/OutOfMemoryError"), "Failed to allocate argv strings");
        return 0;
    }

    for (int i = 0; i < extraCount; i++) {
        auto jStr = static_cast<jstring>(env->GetObjectArrayElement(extraArgs, i));
        if (jStr) {
            const char* utf = env->GetStringUTFChars(jStr, nullptr);
            if (!utf) { freeArgv(2 + i); return 0; }
            argv[2 + i] = strdup(utf);
            env->ReleaseStringUTFChars(jStr, utf);
        } else {
            argv[2 + i] = strdup("");
        }
        if (!argv[2 + i]) {
            freeArgv(2 + i);
            env->ThrowNew(env->FindClass("java/lang/OutOfMemoryError"), "Failed to allocate argv string");
            return 0;
        }
    }
    argv[argc] = nullptr;

    auto* args = static_cast<cef_main_args_t*>(std::malloc(sizeof(cef_main_args_t)));
    if (!args) {
        freeArgv(argc);
        env->ThrowNew(env->FindClass("java/lang/OutOfMemoryError"), "Failed to allocate cef_main_args_t");
        return 0;
    }
    args->argc = argc;
    args->argv = argv;
#endif

    return reinterpret_cast<jlong>(args);
}

// JNI class: net.kurobako.cef4j.CefApp
// Method: private static native void nFreeMainArgs(long address)

extern "C" JNIEXPORT void JNICALL
Java_net_kurobako_cef4j_CefApp_nFreeMainArgs(JNIEnv* env, jclass clz, jlong address) {
    if (address == 0) return;

    auto* args = reinterpret_cast<cef_main_args_t*>(address);

#ifndef _WIN32
    // Free the argv strings and array
    if (args->argv) {
        for (int i = 0; i < args->argc; i++) {
            std::free(args->argv[i]);
        }
        std::free(args->argv);
    }
#endif

    std::free(args);
}
