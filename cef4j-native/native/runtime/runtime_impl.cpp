// Hand-written runtime JNI methods that are intentionally not generated.

#include "jni_util.h"
#include "runtime_stubs.gen.h"

#ifdef __APPLE__
#include "include/wrapper/cef_library_loader.h"
#include "include/cef_api_hash.h"
#include "include/cef_version.h"
extern "C" void cef4j_fix_main_bundle_id(void);
#endif

// JNI class: net.kurobako.cef4j.NativeMemory
// Method: public static native void putBytes(long address, byte[] src, int offset, int length)

CEF4J_JNI_EXPORT_RT(void, NativeMemory, putBytes)(JNIEnv* env, jclass /*clz*/,
        jlong address, jobjectArray src, jint offset, jint length) {
    if (address == 0 || !src || length <= 0) return;
    auto* dest = reinterpret_cast<void*>(address);
    env->GetByteArrayRegion(reinterpret_cast<jbyteArray>(src), offset, length, static_cast<jbyte*>(dest));
}

// JNI class: net.kurobako.cef4j.SystemBootstrap
// Method: private static native boolean loadCefLibrary0(String frameworkBinaryPath)
// macOS only: loads the CEF framework via cef_load_library() then verifies the API hash.

CEF4J_JNI_EXPORT_RT(jboolean, SystemBootstrap, loadCefLibrary0)(JNIEnv* env, jclass /*clz*/,
        jstring frameworkBinaryPath) {
#ifdef __APPLE__
    // Swizzle NSBundle.mainBundle.bundleIdentifier to "cef4j" so the Mach port
    // rendezvous service name matches between browser and subprocess.
    cef4j_fix_main_bundle_id();
    const char* path = env->GetStringUTFChars(frameworkBinaryPath, nullptr);
    if (!path) return JNI_FALSE;
    int ok = cef_load_library(path);
    env->ReleaseStringUTFChars(frameworkBinaryPath, path);
    if (!ok) return JNI_FALSE;
    // Now that the function-pointer stubs are initialized, verify the API version.
    cef_api_hash(CEF_API_VERSION, 0);
    return JNI_TRUE;
#else
    (void)env; (void)frameworkBinaryPath;
    return JNI_FALSE;
#endif
}
