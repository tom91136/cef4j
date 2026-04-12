// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
// JNI stubs for hand-written native methods.
// Include this header and jni_util.h, then implement each function.

#pragma once
#include <jni.h>

CEF4J_JNI_EXPORT_RT(void, NativeMemory, putBytes)(JNIEnv* env, jclass clz, jlong address, jobjectArray src, jint offset, jint length);

CEF4J_JNI_EXPORT_RT(jobjectArray, NativeStderr, redirectStderr0)(JNIEnv* env, jclass clz);

