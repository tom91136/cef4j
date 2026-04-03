// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
#include <jni.h>
#include "include/capi/cef_media_router_capi.h"
#include "jni_util.h"

#include <atomic>
#include "jni_util.h"

struct JniCefMediaSinkDeviceInfoCallback: public cef_media_sink_device_info_callback_t {
    JavaVM *jvm;
    jobject javaHandler;  // global ref
    std::atomic<int> refCount { 1 };

    JniCefMediaSinkDeviceInfoCallback(JavaVM *vm, jobject handler) : cef_media_sink_device_info_callback_t { }, jvm(vm) {
        javaHandler = handler;
        InitRefCount<JniCefMediaSinkDeviceInfoCallback, cef_media_sink_device_info_callback_t> (&base);
        on_media_sink_device_info = &_on_media_sink_device_info;
    }

    static void CEF_CALLBACK _on_media_sink_device_info(cef_media_sink_device_info_callback_t* self, const struct _cef_media_sink_device_info_t* device_info) {
        auto* h = reinterpret_cast<JniCefMediaSinkDeviceInfoCallback*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) {return;}
        auto j_device_info_cls = env->FindClass("net/kurobako/cef4j/gen/NativePointer");
        auto j_device_info_ctor = env->GetMethodID(j_device_info_cls, "<init>", "(J)V");
        auto j_device_info = env->NewObject(j_device_info_cls, j_device_info_ctor, reinterpret_cast<jlong>(device_info));
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onMediaSinkDeviceInfo", "(Lnet/kurobako/cef4j/gen/NativePointer;)V");
        if (!mid) {env->PopLocalFrame(nullptr); return;}
        env->CallVoidMethod(h->javaHandler, mid, j_device_info);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return;}
        env->PopLocalFrame(nullptr);
    }
};

extern "C" cef_media_sink_device_info_callback_t* Create_JniCefMediaSinkDeviceInfoCallback(JNIEnv *env, jobject handler) {
    JavaVM *jvm;
    env->GetJavaVM(&jvm);
    auto globalRef = env->NewGlobalRef(handler);
    return reinterpret_cast<cef_media_sink_device_info_callback_t*>(new JniCefMediaSinkDeviceInfoCallback(jvm, globalRef));
}
