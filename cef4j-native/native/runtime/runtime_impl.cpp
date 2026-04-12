// Hand-written runtime JNI methods that are intentionally not generated.

#include "jni_util.h"
#include "runtime_stubs.gen.h"

// JNI class: net.kurobako.cef4j.NativeMemory
// Method: public static native void putBytes(long address, byte[] src, int offset, int length)

CEF4J_JNI_EXPORT_RT(void, NativeMemory, putBytes)(JNIEnv* env, jclass /*clz*/,
        jlong address, jobjectArray src, jint offset, jint length) {
    if (address == 0 || !src || length <= 0) return;
    auto* dest = reinterpret_cast<void*>(address);
    env->GetByteArrayRegion(reinterpret_cast<jbyteArray>(src), offset, length, static_cast<jbyte*>(dest));
}
