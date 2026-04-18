// Hand-written runtime JNI methods that are intentionally not generated.

#include "jni_util.h"
#include "runtime_stubs.gen.h"
#include "cef_compat.h"

#ifdef __APPLE__
#include "include/wrapper/cef_library_loader.h"
#include "include/cef_api_hash.h"
#include "include/cef_version.h"
#include "include/capi/cef_app_capi.h"
#include <CoreFoundation/CoreFoundation.h>
#include <dispatch/dispatch.h>
#include <pthread.h>
#include <stdio.h>
extern "C" void cef4j_fix_main_bundle_id(void);
extern "C" void cef4j_stop_nsapp(void);
extern "C" void cef4j_activate_app(void);

// Write diagnostic messages to a file, bypassing stderr/pipe issues.
#define CEF4J_DIAG(msg) do { \
    FILE* _f = fopen("/tmp/cef4j_diag.txt", "a"); \
    if (_f) { fprintf(_f, "[cef4j] " msg "\n"); fclose(_f); } \
} while(0)
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
    cef4j_fix_main_bundle_id();
    const char* path = env->GetStringUTFChars(frameworkBinaryPath, nullptr);
    if (!path) return JNI_FALSE;
    int ok = cef_load_library(path);
    env->ReleaseStringUTFChars(frameworkBinaryPath, path);
    if (!ok) return JNI_FALSE;
    cef4j_verify_api_hash();
    return JNI_TRUE;
#else
    (void)env; (void)frameworkBinaryPath;
    return JNI_FALSE;
#endif
}

// macOS Thread 0 dispatch helpers.
//
// CEF on macOS must be initialised on Thread 0 (the AppKit main thread) because
// NSNotification observers registered by cef_initialize() fire on Thread 0 and
// CEF's base::SequenceChecker CHECK-fails unless those callbacks land on the
// same thread that called cef_initialize().
//
// Strategy: dispatch_async a single block to Thread 0 that runs cef_initialize(),
// then cef_run_message_loop(), then cleanup.  cef_run_message_loop() calls
// [NSApp run] which becomes the event loop for Thread 0 — AWT/Glass events
// are processed inside it.

#ifdef __APPLE__
static dispatch_semaphore_t g_cef_init_done = nullptr;
static dispatch_semaphore_t g_cef_message_loop_done = nullptr;

static void InvokeJavaRunnableFromNative(JavaVM* jvm, jobject globalRef) {
    ScopedJNIEnv scoped(jvm);
    JNIEnv* env = scoped.get();
    if (!env) return;
    jclass runnableCls = env->GetObjectClass(globalRef);
    jmethodID runMid = env->GetMethodID(runnableCls, "run", "()V");
    env->CallVoidMethod(globalRef, runMid);
    if (env->ExceptionCheck()) {
        env->ExceptionDescribe();
        env->ExceptionClear();
    }
    env->DeleteLocalRef(runnableCls);
    env->DeleteGlobalRef(globalRef);
}
#endif

// macOS only: dispatch init + cef_run_message_loop() + cleanup onto Thread 0 in a
// SINGLE dispatch_async block.

CEF4J_JNI_EXPORT_RT(void, SystemBootstrap, initAndRunOnMainThread0)(JNIEnv* env, jclass /*clz*/,
        jobject initRunnable, jobject cleanupRunnable) {
#ifdef __APPLE__
    if (g_cef_message_loop_done) return; // already started
    if (!initRunnable) return;
    JavaVM* jvm = nullptr;
    env->GetJavaVM(&jvm);
    if (!jvm) return;
    jobject initRef = env->NewGlobalRef(initRunnable);
    if (!initRef) return;
    jobject cleanupRef = cleanupRunnable ? env->NewGlobalRef(cleanupRunnable) : nullptr;

    g_cef_init_done = dispatch_semaphore_create(0);
    g_cef_message_loop_done = dispatch_semaphore_create(0);
    dispatch_semaphore_t initDone = g_cef_init_done;
    dispatch_semaphore_t loopDone = g_cef_message_loop_done;

    dispatch_async(dispatch_get_main_queue(), ^{
        CEF4J_DIAG("dispatch block: running init");
        InvokeJavaRunnableFromNative(jvm, initRef);
        CEF4J_DIAG("dispatch block: init done, signaling");
        dispatch_semaphore_signal(initDone);
        CEF4J_DIAG("dispatch block: entering cef_run_message_loop");
        cef_run_message_loop();
        CEF4J_DIAG("dispatch block: cef_run_message_loop returned");
        if (cleanupRef) {
            CEF4J_DIAG("dispatch block: running cleanup");
            InvokeJavaRunnableFromNative(jvm, cleanupRef);
            CEF4J_DIAG("dispatch block: cleanup done");
        }
        // cef_shutdown() is intentionally skipped.  Browser close operations
        // (closeBrowser(true)) are asynchronous and may not have fully completed
        // before cef4j_stop_nsapp forced [NSApp run] to return.  Calling
        // cef_shutdown() with live browser objects triggers a CHECK/SIGTRAP.
        dispatch_semaphore_signal(loopDone);
        CEF4J_DIAG("dispatch block: all done, parking Thread 0");
        // CRITICAL: do NOT return from this block.  Returning gives control back
        // to the JVM's ParkEventLoop CFRunLoop, which fires CEF's registered
        // observers (still installed because cef_shutdown was skipped) and
        // triggers a CHECK/SIGTRAP.  Park here until halt(0)/_exit kills us.
        // Safety net: if the Java side doesn't halt within 5 seconds, force-exit.
        dispatch_semaphore_wait(dispatch_semaphore_create(0),
                dispatch_time(DISPATCH_TIME_NOW, 5ull * NSEC_PER_SEC));
        CEF4J_DIAG("dispatch block: safety-net _exit(0)");
        _exit(0);
    });

    // Schedule app activation after a short delay — by this time [NSApp run]
    // is processing events and the caller will have created AWT/JFX windows.
    // NOTE: dispatch_after to the main GCD queue does NOT work here because
    // the dispatch_async block above is still "executing" (blocked inside
    // cef_run_message_loop), and GCD serial queues cannot dequeue the next
    // block until the current one finishes.  Use a CFRunLoopTimer instead —
    // it fires inside [NSApp run]'s CFRunLoop directly.
    CFRunLoopTimerRef timer = CFRunLoopTimerCreateWithHandler(
            kCFAllocatorDefault,
            CFAbsoluteTimeGetCurrent() + 0.5, // 500ms from now
            0, 0, 0,
            ^(CFRunLoopTimerRef t) {
                (void)t;
                cef4j_activate_app();
            });
    CFRunLoopAddTimer(CFRunLoopGetMain(), timer, kCFRunLoopCommonModes);
    CFRelease(timer);

    dispatch_semaphore_wait(g_cef_init_done, DISPATCH_TIME_FOREVER);
    g_cef_init_done = nullptr;
    CEF4J_DIAG("initAndRun: init wait complete, returning");
#else
    (void)env; (void)initRunnable; (void)cleanupRunnable;
#endif
}

CEF4J_JNI_EXPORT_RT(void, SystemBootstrap, dispatchToMainThreadSync0)(JNIEnv* env, jclass /*clz*/,
        jobject runnable) {
#ifdef __APPLE__
    if (!runnable) return;
    JavaVM* jvm = nullptr;
    env->GetJavaVM(&jvm);
    if (!jvm) return;
    jobject globalRef = env->NewGlobalRef(runnable);
    if (!globalRef) return;
    if (pthread_main_np()) {
        InvokeJavaRunnableFromNative(jvm, globalRef);
        return;
    }
    dispatch_sync(dispatch_get_main_queue(), ^{
        InvokeJavaRunnableFromNative(jvm, globalRef);
    });
#else
    (void)env; (void)runnable;
#endif
}

CEF4J_JNI_EXPORT_RT(void, SystemBootstrap, quitAndWaitMainThreadMessageLoop0)(JNIEnv* /*env*/, jclass /*clz*/) {
#ifdef __APPLE__
    CEF4J_DIAG("quit: calling cef_quit_message_loop");
    cef_quit_message_loop();
    CEF4J_DIAG("quit: calling cef4j_stop_nsapp");
    // cef_quit_message_loop() posts a quit task, but AWT's NSApplicationAWT
    // overrides [NSApp run] and may not honour [NSApp stop:] from the quit task.
    // Force-stop via cef4j_stop_nsapp() + dummy event to unblock [NSApp run].
    cef4j_stop_nsapp();
    CEF4J_DIAG("quit: waiting for message loop done semaphore");
    if (g_cef_message_loop_done) {
        dispatch_semaphore_wait(g_cef_message_loop_done, DISPATCH_TIME_FOREVER);
        g_cef_message_loop_done = nullptr;
    }
    CEF4J_DIAG("quit: done");
#endif
}
