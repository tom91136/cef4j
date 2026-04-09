// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
#include <jni.h>
#include "include/capi/cef_resource_bundle_handler_capi.h"
#include "jni_util.h"

#include <atomic>
#include "jni_util.h"

struct JniCefResourceBundleHandler : public cef_resource_bundle_handler_t {
    JavaVM* jvm;
    jobject javaHandler;  // global ref
    std::atomic<int> refCount{1};

    JniCefResourceBundleHandler(JavaVM* vm, jobject handler) : cef_resource_bundle_handler_t{}, jvm(vm) {
        javaHandler = handler;
        InitRefCount<JniCefResourceBundleHandler, cef_resource_bundle_handler_t>(reinterpret_cast<cef_base_ref_counted_t*>(static_cast<cef_resource_bundle_handler_t*>(this)));
        get_localized_string = &_get_localized_string;
        get_data_resource = &_get_data_resource;
        get_data_resource_for_scale = &_get_data_resource_for_scale;
    }

    static int CEF_CALLBACK _get_localized_string(cef_resource_bundle_handler_t* self, int string_id, cef_string_t* string) {
        auto* h = reinterpret_cast<JniCefResourceBundleHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(6) < 0) { return false; }
        auto j_string = CefStringToJString(env, string);
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "getLocalizedString", "(ILjava/lang/String;)Z");
        if (!mid) { env->PopLocalFrame(nullptr); return false; }
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, static_cast<jint>(string_id), j_string);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return false; }
        env->PopLocalFrame(nullptr);
        return jResult;
    }

    static int CEF_CALLBACK _get_data_resource(cef_resource_bundle_handler_t* self, int resource_id, void** data, size_t* data_size) {
        auto* h = reinterpret_cast<JniCefResourceBundleHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(9) < 0) { return false; }
        auto j_data_cls = env->FindClass("net/kurobako/cef4j/gen/NativePointer");
        auto j_data_ctor = env->GetMethodID(j_data_cls, "<init>", "(J)V");
        auto j_data = env->NewObject(j_data_cls, j_data_ctor, reinterpret_cast<jlong>(data));
        jlongArray j_data_size = env->NewLongArray(1);
        if (data_size) { jlong _v = static_cast<jlong>(*data_size); env->SetLongArrayRegion(j_data_size, 0, 1, &_v); }
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "getDataResource", "(ILnet/kurobako/cef4j/gen/NativePointer;[J)Z");
        if (!mid) { env->PopLocalFrame(nullptr); return false; }
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, static_cast<jint>(resource_id), j_data, j_data_size);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return false; }
        if (data_size) { jlong _v; env->GetLongArrayRegion(j_data_size, 0, 1, &_v); *data_size = static_cast<size_t>(_v); }
        env->PopLocalFrame(nullptr);
        return jResult;
    }

    static int CEF_CALLBACK _get_data_resource_for_scale(cef_resource_bundle_handler_t* self, int resource_id, cef_scale_factor_t scale_factor, void** data, size_t* data_size) {
        auto* h = reinterpret_cast<JniCefResourceBundleHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(12) < 0) { return false; }
        auto j_scale_factor_cls = env->FindClass("net/kurobako/cef4j/gen/CefScaleFactor");
        auto j_scale_factor_from = env->GetStaticMethodID(j_scale_factor_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefScaleFactor;");
        auto j_scale_factor = env->CallStaticObjectMethod(j_scale_factor_cls, j_scale_factor_from, static_cast<jlong>(scale_factor));
        auto j_data_cls = env->FindClass("net/kurobako/cef4j/gen/NativePointer");
        auto j_data_ctor = env->GetMethodID(j_data_cls, "<init>", "(J)V");
        auto j_data = env->NewObject(j_data_cls, j_data_ctor, reinterpret_cast<jlong>(data));
        jlongArray j_data_size = env->NewLongArray(1);
        if (data_size) { jlong _v = static_cast<jlong>(*data_size); env->SetLongArrayRegion(j_data_size, 0, 1, &_v); }
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "getDataResourceForScale", "(ILnet/kurobako/cef4j/gen/CefScaleFactor;Lnet/kurobako/cef4j/gen/NativePointer;[J)Z");
        if (!mid) { env->PopLocalFrame(nullptr); return false; }
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, static_cast<jint>(resource_id), j_scale_factor, j_data, j_data_size);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return false; }
        if (data_size) { jlong _v; env->GetLongArrayRegion(j_data_size, 0, 1, &_v); *data_size = static_cast<size_t>(_v); }
        env->PopLocalFrame(nullptr);
        return jResult;
    }
};

extern "C" cef_resource_bundle_handler_t* Create_JniCefResourceBundleHandler(JNIEnv* env, jobject handler) {
    JavaVM* jvm;
    env->GetJavaVM(&jvm);
    auto globalRef = env->NewGlobalRef(handler);
    return reinterpret_cast<cef_resource_bundle_handler_t*>(new JniCefResourceBundleHandler(jvm, globalRef));
}
