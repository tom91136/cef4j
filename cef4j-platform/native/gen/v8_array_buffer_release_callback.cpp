// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
#include <jni.h>
#include "include/capi/cef_v8_capi.h"
#include "jni_util.h"

#include <atomic>
#include "jni_util.h"

struct JniCefV8ArrayBufferReleaseCallback : public cef_v8_array_buffer_release_callback_t {
    JavaVM* jvm;
    jobject javaHandler;  // global ref
    std::atomic<int> refCount{1};

    JniCefV8ArrayBufferReleaseCallback(JavaVM* vm, jobject handler) : cef_v8_array_buffer_release_callback_t{}, jvm(vm) {
        javaHandler = handler;
        InitRefCount<JniCefV8ArrayBufferReleaseCallback, cef_v8_array_buffer_release_callback_t>(reinterpret_cast<cef_base_ref_counted_t*>(static_cast<cef_v8_array_buffer_release_callback_t*>(this)));
        release_buffer = &_release_buffer;
    }

    static void CEF_CALLBACK _release_buffer(cef_v8_array_buffer_release_callback_t* self, void* buffer) {
        auto* h = reinterpret_cast<JniCefV8ArrayBufferReleaseCallback*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) { return; }
        auto j_buffer_cls = FindClassCached(env, "net/kurobako/cef4j/gen/NativePointer");
        auto j_buffer_ctor = env->GetMethodID(j_buffer_cls, "<init>", "(J)V");
        auto j_buffer = env->NewObject(j_buffer_cls, j_buffer_ctor, reinterpret_cast<jlong>(buffer));
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "releaseBuffer", "(Lnet/kurobako/cef4j/gen/NativePointer;)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, j_buffer);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        env->PopLocalFrame(nullptr);
    }
};

extern "C" cef_v8_array_buffer_release_callback_t* Create_JniCefV8ArrayBufferReleaseCallback(JNIEnv* env, jobject handler) {
    JavaVM* jvm;
    env->GetJavaVM(&jvm);
    auto globalRef = env->NewGlobalRef(handler);
    return reinterpret_cast<cef_v8_array_buffer_release_callback_t*>(new JniCefV8ArrayBufferReleaseCallback(jvm, globalRef));
}
