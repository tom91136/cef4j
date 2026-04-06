// Hand-written JNI helper for constructing platform-correct cef_main_args_t.
//
// Linux/macOS: cef_main_args_t = { int argc; char** argv; }
//   - Allocates argc/argv from Java String[].
//   - CEF copies what it needs during cef_initialize; caller frees after.
//
// Windows: cef_main_args_t = { HINSTANCE instance; }
//   - Uses GetModuleHandle(NULL). Extra args must go through
//     CefApp::OnBeforeCommandLineProcessing instead.

#include "jni_util.h"
#include "runtime_stubs.gen.h"
#include <cstdlib>
#include <cstring>

#ifdef _WIN32
#include <windows.h>
#include "include/internal/cef_types_win.h"
#else
#include "include/internal/cef_types_linux.h"
#endif

// JNI class: net.kurobako.cef4j.CefApp
// Method: private static native long createMainArgs0(String[] args)
//
// Linux/macOS: builds argc/argv from the Java String[].
// Windows: sets HINSTANCE only (args ignored - Java routes them through
//          CefApp::OnBeforeCommandLineProcessing instead).

CEF4J_JNI_EXPORT_RT(jlong, Cef, createMainArgs0)(JNIEnv* env, jclass /*clz*/, jobjectArray jArgs) {

#ifdef _WIN32
    auto* args = static_cast<cef_main_args_t*>(std::malloc(sizeof(cef_main_args_t)));
    if (!args) {
        env->ThrowNew(env->FindClass("java/lang/OutOfMemoryError"), "Failed to allocate cef_main_args_t");
        return 0;
    }
    args->instance = GetModuleHandle(NULL);

#else
    // Linux/macOS: argv = { "cef4j", ...jArgs, NULL }
    int argCount = jArgs ? env->GetArrayLength(jArgs) : 0;
    int argc = 1 + argCount;
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
    if (!argv[0]) {
        freeArgv(0);
        env->ThrowNew(env->FindClass("java/lang/OutOfMemoryError"), "Failed to allocate argv strings");
        return 0;
    }

    for (int i = 0; i < argCount; i++) {
        auto jStr = static_cast<jstring>(env->GetObjectArrayElement(jArgs, i));
        if (jStr) {
            const char* utf = env->GetStringUTFChars(jStr, nullptr);
            if (!utf) { freeArgv(1 + i); return 0; }
            argv[1 + i] = strdup(utf);
            env->ReleaseStringUTFChars(jStr, utf);
        } else {
            argv[1 + i] = strdup("");
        }
        if (!argv[1 + i]) {
            freeArgv(1 + i);
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

// JNI class: net.kurobako.cef4j.NativeMemory
// Method: public static native void putBytes(long address, byte[] src, int offset, int length)

CEF4J_JNI_EXPORT_RT(void, NativeMemory, putBytes)(JNIEnv* env, jclass /*clz*/,
        jlong address, jobjectArray src, jint offset, jint length) {
    if (address == 0 || !src || length <= 0) return;
    auto* dest = reinterpret_cast<void*>(address);
    env->GetByteArrayRegion(reinterpret_cast<jbyteArray>(src), offset, length, static_cast<jbyte*>(dest));
}

// JNI class: net.kurobako.cef4j.CefApp
// Method: private static native void freeMainArgs0(long address)

CEF4J_JNI_EXPORT_RT(void, Cef, freeMainArgs0)(JNIEnv* /*env*/, jclass /*clz*/, jlong address) {
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
