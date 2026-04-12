// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
#include <jni.h>
#include "include/capi/cef_media_router_capi.h"
#include "jni_util.h"

#include <atomic>
#include "jni_util.h"

struct JniCefMediaSinkDeviceInfoCallback : public cef_media_sink_device_info_callback_t {
    JavaVM* jvm;
    jobject javaHandler;  // global ref
    std::atomic<int> refCount{1};

    JniCefMediaSinkDeviceInfoCallback(JavaVM* vm, jobject handler) : cef_media_sink_device_info_callback_t{}, jvm(vm) {
        javaHandler = handler;
        InitRefCount<JniCefMediaSinkDeviceInfoCallback, cef_media_sink_device_info_callback_t>(reinterpret_cast<cef_base_ref_counted_t*>(static_cast<cef_media_sink_device_info_callback_t*>(this)));
        on_media_sink_device_info = &_on_media_sink_device_info;
    }

    static void CEF_CALLBACK _on_media_sink_device_info(cef_media_sink_device_info_callback_t* self, const struct _cef_media_sink_device_info_t* device_info) {
        auto* h = reinterpret_cast<JniCefMediaSinkDeviceInfoCallback*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(10) < 0) { return; }
        jobject j_device_info = nullptr;
        if (device_info) {
            auto _bv_device_info_ip_address = CefStringToJString(env, &(device_info)->ip_address);
            auto _bv_device_info_model_name = CefStringToJString(env, &(device_info)->model_name);
            auto j_device_info_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefMediaSinkDeviceInfo");
            auto j_device_info_ctor = env->GetMethodID(j_device_info_cls, "<init>", "(Ljava/lang/String;ILjava/lang/String;)V");
            j_device_info = env->NewObject(j_device_info_cls, j_device_info_ctor, _bv_device_info_ip_address, static_cast<jint>((device_info)->port), _bv_device_info_model_name);
            if (j_device_info) env->SetLongField(j_device_info, env->GetFieldID(j_device_info_cls, "size", "J"), static_cast<jlong>(device_info->size));
        }
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onMediaSinkDeviceInfo", "(Lnet/kurobako/cef4j/gen/CefMediaSinkDeviceInfo;)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, j_device_info);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        env->PopLocalFrame(nullptr);
    }
};

extern "C" cef_media_sink_device_info_callback_t* Create_JniCefMediaSinkDeviceInfoCallback(JNIEnv* env, jobject handler) {
    JavaVM* jvm;
    env->GetJavaVM(&jvm);
    auto globalRef = env->NewGlobalRef(handler);
    return reinterpret_cast<cef_media_sink_device_info_callback_t*>(new JniCefMediaSinkDeviceInfoCallback(jvm, globalRef));
}
