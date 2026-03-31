// Hand-written JNI utilities - stable across CEF versions.
#include "jni_util.h"

// The JavaVM pointer is cached at JNI_OnLoad so that it can be retrieved by
// code that doesn't have a convenient ScopedJNIEnv in scope (e.g., CefApp
// initialization). CEF4J stores one global - there can only be one JVM per
// process anyway.
static JavaVM* g_jvm = nullptr;

JavaVM* GetJVM() {
    return g_jvm;
}

// Called by the JVM when the native library is loaded via System.loadLibrary.
extern "C" JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM* vm, void* /*reserved*/) {
    g_jvm = vm;

    JNIEnv* env;
    if (vm->GetEnv(reinterpret_cast<void**>(&env), JNI_VERSION_1_8) != JNI_OK) {
        return JNI_ERR;
    }

    return JNI_VERSION_1_8;
}

// Called by the JVM before unloading the native library.
extern "C" JNIEXPORT void JNICALL JNI_OnUnload(JavaVM* vm, void* /*reserved*/) {
    g_jvm = nullptr;
}
