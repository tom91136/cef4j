// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
#include <jni.h>
#include "include/capi/cef_component_updater_capi.h"
#include "jni_util.h"

#include <atomic>
#include "jni_util.h"

struct JniCefComponentUpdateCallback: public cef_component_update_callback_t {
    JavaVM *jvm;
    jobject javaHandler;  // global ref
    std::atomic<int> refCount { 1 };

    JniCefComponentUpdateCallback(JavaVM *vm, jobject handler) : cef_component_update_callback_t { }, jvm(vm) {
        javaHandler = handler;
        InitRefCount<JniCefComponentUpdateCallback, cef_component_update_callback_t>(
                reinterpret_cast<cef_base_ref_counted_t*>(static_cast<cef_component_update_callback_t*>(this)));
        on_complete = &_on_complete;
    }

    static void CEF_CALLBACK _on_complete(cef_component_update_callback_t* self, const cef_string_t* component_id, cef_component_update_error_t error) {
        auto* h = reinterpret_cast<JniCefComponentUpdateCallback*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(9) < 0) {return;}
        auto j_component_id = CefStringToJString(env, component_id);
        auto j_error_cls = env->FindClass("net/kurobako/cef4j/gen/CefComponentUpdateError");
        auto j_error_from = env->GetStaticMethodID(j_error_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefComponentUpdateError;");
        auto j_error = env->CallStaticObjectMethod(j_error_cls, j_error_from, static_cast<jlong>(error));
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onComplete", "(Ljava/lang/String;Lnet/kurobako/cef4j/gen/CefComponentUpdateError;)V");
        if (!mid) {env->PopLocalFrame(nullptr); return;}
        env->CallVoidMethod(h->javaHandler, mid, j_component_id, j_error);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return;}
        env->PopLocalFrame(nullptr);
    }
};

extern "C" cef_component_update_callback_t* Create_JniCefComponentUpdateCallback(JNIEnv *env, jobject handler) {
    JavaVM *jvm;
    env->GetJavaVM(&jvm);
    auto globalRef = env->NewGlobalRef(handler);
    return reinterpret_cast<cef_component_update_callback_t*>(new JniCefComponentUpdateCallback(jvm, globalRef));
}
