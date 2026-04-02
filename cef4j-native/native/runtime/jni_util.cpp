// Hand-written JNI utilities - stable across CEF versions.
#include "jni_util.h"
#include "include/cef_api_hash.h"

// Cached JavaVM pointer - there can only be one JVM per process.
static JavaVM* jvm = nullptr;

// Called by the JVM when the native library is loaded via System.loadLibrary.
extern "C" JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM* vm, void* /*reserved*/) {
    jvm = vm;

    // Configure the CEF API version. This must happen before any CEF C API
    // functions are called. The version determines struct layouts and which
    // members are available.
    cef_api_hash(CEF_API_VERSION, 0);

    JNIEnv* env;
    if (vm->GetEnv(reinterpret_cast<void**>(&env), JNI_VERSION_1_8) != JNI_OK) {
        return JNI_ERR;
    }

    return JNI_VERSION_1_8;
}

// Called by the JVM before unloading the native library.
extern "C" JNIEXPORT void JNICALL JNI_OnUnload(JavaVM* vm, void* /*reserved*/) {
    jvm = nullptr;
}
