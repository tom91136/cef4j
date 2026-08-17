// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
// JNI stubs for hand-written native methods.
// Include this header and jni_util.h, then implement each function.

#pragma once
#include <jni.h>

CEF4J_JNI_EXPORT_RT(void, NativeMemory, putBytes)(JNIEnv* env, jclass clz, jlong address, jobjectArray src, jint offset, jint length);

CEF4J_JNI_EXPORT_RT(jobjectArray, NativeStderr, redirectStderr0)(JNIEnv* env, jclass clz);

CEF4J_JNI_EXPORT_RT(void, NativeStderr, setCrashLogPath0)(JNIEnv* env, jclass clz, jstring path);

CEF4J_JNI_EXPORT_RT(void, SystemBootstrap, dispatchToMainThreadSync0)(JNIEnv* env, jclass clz, jobject runnable);

CEF4J_JNI_EXPORT_RT(void, SystemBootstrap, initAndRunOnMainThread0)(JNIEnv* env, jclass clz, jobject initRunnable, jobject cleanupRunnable);

CEF4J_JNI_EXPORT_RT(jboolean, SystemBootstrap, loadCefLibrary0)(JNIEnv* env, jclass clz, jstring frameworkBinaryPath);

CEF4J_JNI_EXPORT_RT(void, SystemBootstrap, quitAndWaitMainThreadMessageLoop0)(JNIEnv* env, jclass clz);

