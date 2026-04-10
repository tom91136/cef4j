// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
#include <jni.h>
#include "include/capi/cef_request_context_capi.h"
#include "jni_util.h"

#include <atomic>
#include "jni_util.h"

struct JniCefSettingObserver : public cef_setting_observer_t {
    JavaVM* jvm;
    jobject javaHandler;  // global ref
    std::atomic<int> refCount{1};

    JniCefSettingObserver(JavaVM* vm, jobject handler) : cef_setting_observer_t{}, jvm(vm) {
        javaHandler = handler;
        InitRefCount<JniCefSettingObserver, cef_setting_observer_t>(reinterpret_cast<cef_base_ref_counted_t*>(static_cast<cef_setting_observer_t*>(this)));
        on_setting_changed = &_on_setting_changed;
    }

    static void CEF_CALLBACK _on_setting_changed(cef_setting_observer_t* self, const cef_string_t* requesting_url, const cef_string_t* top_level_url, cef_content_setting_types_t content_type) {
        auto* h = reinterpret_cast<JniCefSettingObserver*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(10) < 0) { return; }
        auto j_requesting_url = CefStringToJString(env, requesting_url);
        auto j_top_level_url = CefStringToJString(env, top_level_url);
        auto j_content_type_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefContentSettingTypes");
        auto j_content_type_from = env->GetStaticMethodID(j_content_type_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefContentSettingTypes;");
        auto j_content_type = env->CallStaticObjectMethod(j_content_type_cls, j_content_type_from, static_cast<jlong>(content_type));
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onSettingChanged", "(Ljava/lang/String;Ljava/lang/String;Lnet/kurobako/cef4j/gen/CefContentSettingTypes;)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, j_requesting_url, j_top_level_url, j_content_type);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        env->PopLocalFrame(nullptr);
    }
};

extern "C" cef_setting_observer_t* Create_JniCefSettingObserver(JNIEnv* env, jobject handler) {
    JavaVM* jvm;
    env->GetJavaVM(&jvm);
    auto globalRef = env->NewGlobalRef(handler);
    return reinterpret_cast<cef_setting_observer_t*>(new JniCefSettingObserver(jvm, globalRef));
}
