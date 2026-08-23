#include "jni_util.h"
#include "runtime_stubs.gen.h"
#include "cef_compat.h"

#ifndef _WIN32
#include <array>
#include <signal.h>
#endif

int Cef4jInitialize(const cef_main_args_t* args, const cef_settings_t* settings,
        cef_app_t* application, void* windowsSandboxInfo) {
#ifndef _WIN32
    // XXX: CEF 109.1.18 and 116.0.27 lack disable_signal_handlers; preserve HotSpot's fatal handlers until those
    // compatibility lanes are dropped.
    constexpr std::array<int, 7> fatalSignals{
        SIGILL, SIGABRT, SIGFPE, SIGBUS, SIGSEGV, SIGSYS, SIGTRAP};
    std::array<struct sigaction, fatalSignals.size()> previous{};
    std::array<bool, fatalSignals.size()> captured{};
    for (size_t i = 0; i < fatalSignals.size(); ++i) {
        captured[i] = sigaction(fatalSignals[i], nullptr, &previous[i]) == 0;
    }
#endif

    int result = cef_initialize(args, settings, application, windowsSandboxInfo);

#ifndef _WIN32
    for (size_t i = 0; i < fatalSignals.size(); ++i) {
        if (captured[i]) sigaction(fatalSignals[i], &previous[i], nullptr);
    }
#endif
    return result;
}

#ifdef __APPLE__
#include "include/wrapper/cef_library_loader.h"
#include "include/cef_api_hash.h"
#include "include/cef_version.h"
#include "include/capi/cef_task_capi.h"
#include <CoreFoundation/CoreFoundation.h>
#include <atomic>
#include <dispatch/dispatch.h>
#include <pthread.h>
extern "C" void cef4j_fix_main_bundle_id(void);
extern "C" void cef4j_stop_nsapp(void);
extern "C" void cef4j_activate_app(void);
#endif

CEF4J_JNI_EXPORT_RT(void, NativeMemory, putBytes)(JNIEnv* env, jclass /*clz*/,
        jlong address, jobjectArray src, jint offset, jint length) {
    if (address == 0 || !src || length <= 0) return;
    auto* dest = reinterpret_cast<void*>(address);
    env->GetByteArrayRegion(reinterpret_cast<jbyteArray>(src), offset, length, static_cast<jbyte*>(dest));
}

CEF4J_JNI_EXPORT_RT(jboolean, SystemBootstrap, loadCefLibrary0)(JNIEnv* env, jclass /*clz*/,
        jstring frameworkBinaryPath) {
#ifdef __APPLE__
    cef4j_fix_main_bundle_id();
    const char* path = env->GetStringUTFChars(frameworkBinaryPath, nullptr);
    if (!path) return JNI_FALSE;
    int ok = cef_load_library(path);
    env->ReleaseStringUTFChars(frameworkBinaryPath, path);
    if (!ok) return JNI_FALSE;
    if (!cef4j_verify_api_hash()) {
        cef_unload_library();
        return JNI_FALSE;
    }
    return JNI_TRUE;
#else
    (void)env; (void)frameworkBinaryPath;
    return JNI_FALSE;
#endif
}

// XXX: CEF 109-150 requires macOS initialization, the message loop, and shutdown on Thread 0; revalidate when the
// minimum supported CEF exceeds 150.

#ifdef __APPLE__
static dispatch_semaphore_t g_cef_init_done = nullptr;
static dispatch_semaphore_t g_cef_message_loop_done = nullptr;

struct QuitMessageLoopTask : cef_task_t {
    std::atomic<int> refCount{1};

    QuitMessageLoopTask() : cef_task_t{} {
        auto* base = reinterpret_cast<cef_base_ref_counted_t*>(static_cast<cef_task_t*>(this));
        base->size = sizeof(cef_task_t);
        base->add_ref = [](cef_base_ref_counted_t* self) {
            reinterpret_cast<QuitMessageLoopTask*>(self)->refCount.fetch_add(1, std::memory_order_relaxed);
        };
        base->release = [](cef_base_ref_counted_t* self) -> int {
            auto* task = reinterpret_cast<QuitMessageLoopTask*>(self);
            if (task->refCount.fetch_sub(1, std::memory_order_acq_rel) != 1) return 0;
            delete task;
            return 1;
        };
        base->has_one_ref = [](cef_base_ref_counted_t* self) -> int {
            return reinterpret_cast<QuitMessageLoopTask*>(self)->refCount.load(std::memory_order_acquire) == 1;
        };
        base->has_at_least_one_ref = [](cef_base_ref_counted_t* self) -> int {
            return reinterpret_cast<QuitMessageLoopTask*>(self)->refCount.load(std::memory_order_acquire) >= 1;
        };
        execute = [](cef_task_t* /*self*/) { cef_quit_message_loop(); };
    }
};

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

static bool InvokeJavaBooleanSupplierFromNative(JavaVM* jvm, jobject globalRef) {
    ScopedJNIEnv scoped(jvm);
    JNIEnv* env = scoped.get();
    if (!env) return false;
    jclass supplierCls = env->GetObjectClass(globalRef);
    jmethodID getMid = env->GetMethodID(supplierCls, "getAsBoolean", "()Z");
    jboolean result = env->CallBooleanMethod(globalRef, getMid);
    bool failed = env->ExceptionCheck();
    if (failed) env->ExceptionClear();
    env->DeleteLocalRef(supplierCls);
    env->DeleteGlobalRef(globalRef);
    return !failed && result == JNI_TRUE;
}
#endif

CEF4J_JNI_EXPORT_RT(void, SystemBootstrap, initAndRunOnMainThread0)(JNIEnv* env, jclass /*clz*/,
        jobject initializer, jobject cleanupRunnable) {
#ifdef __APPLE__
    if (g_cef_message_loop_done) return;
    if (!initializer) return;
    JavaVM* jvm = nullptr;
    env->GetJavaVM(&jvm);
    if (!jvm) return;
    jobject initRef = env->NewGlobalRef(initializer);
    if (!initRef) return;
    jobject cleanupRef = cleanupRunnable ? env->NewGlobalRef(cleanupRunnable) : nullptr;

    g_cef_init_done = dispatch_semaphore_create(0);
    g_cef_message_loop_done = dispatch_semaphore_create(0);
    dispatch_semaphore_t initDone = g_cef_init_done;
    dispatch_semaphore_t loopDone = g_cef_message_loop_done;

    dispatch_async(dispatch_get_main_queue(), ^{
        bool initialized = InvokeJavaBooleanSupplierFromNative(jvm, initRef);
        dispatch_semaphore_signal(initDone);
        if (!initialized) {
            if (cleanupRef) InvokeJavaRunnableFromNative(jvm, cleanupRef);
            dispatch_semaphore_signal(loopDone);
            g_cef_message_loop_done = nullptr;
            return;
        }
        cef_run_message_loop();
        if (cleanupRef) {
            InvokeJavaRunnableFromNative(jvm, cleanupRef);
        }
        // XXX: CEF 109-150 leaves macOS CFRunLoop observers installed until cef_shutdown; keep shutdown before JVM
        // teardown until the minimum supported CEF exceeds 150.
        cef_shutdown();
        cef_unload_library();
        dispatch_semaphore_signal(loopDone);
    });

    // XXX: While CEF 109-150 blocks the serial main GCD queue in cef_run_message_loop, schedule activation on the
    // CFRunLoop; replace this when CEF no longer occupies that queue.
    CFRunLoopTimerRef timer = CFRunLoopTimerCreateWithHandler(
            kCFAllocatorDefault,
            CFAbsoluteTimeGetCurrent() + 0.5,
            0, 0, 0,
            ^(CFRunLoopTimerRef t) {
                (void)t;
                cef4j_activate_app();
            });
    CFRunLoopAddTimer(CFRunLoopGetMain(), timer, kCFRunLoopCommonModes);
    CFRelease(timer);

    dispatch_semaphore_wait(g_cef_init_done, DISPATCH_TIME_FOREVER);
    g_cef_init_done = nullptr;
#else
    (void)env; (void)initializer; (void)cleanupRunnable;
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
    // XXX: CEF 109-150 requires quit on TID_UI; keep the bounded NSApp wake-up fallback until the minimum supported
    // CEF exceeds 150 or upstream guarantees delivery under AWT/Glass.
    cef_post_task(TID_UI, new QuitMessageLoopTask());
    if (g_cef_message_loop_done) {
        long waitResult = dispatch_semaphore_wait(
                g_cef_message_loop_done, dispatch_time(DISPATCH_TIME_NOW, 5 * NSEC_PER_SEC));
        if (waitResult != 0) {
            cef4j_stop_nsapp();
            dispatch_semaphore_wait(g_cef_message_loop_done, DISPATCH_TIME_FOREVER);
        }
        g_cef_message_loop_done = nullptr;
    }
#endif
}
