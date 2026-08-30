// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
#include <jni.h>
#include "include/capi/cef_preference_capi.h"
#include "jni_util.h"

#include <atomic>

struct JniCefPreferenceObserver : public cef_preference_observer_t {
    JavaVM* jvm;
    jobject javaHandler;  // global ref
    std::atomic<int> refCount{1};

    JniCefPreferenceObserver(JavaVM* vm, jobject handler) : cef_preference_observer_t{}, jvm(vm) {
        javaHandler = handler;
        InitRefCount<JniCefPreferenceObserver, cef_preference_observer_t>(reinterpret_cast<cef_base_ref_counted_t*>(static_cast<cef_preference_observer_t*>(this)));
        on_preference_changed = &_on_preference_changed;
    }

    static void CEF_CALLBACK _on_preference_changed(cef_preference_observer_t* self, const cef_string_t* name) {
        auto* h = reinterpret_cast<JniCefPreferenceObserver*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(6) < 0) { return; }
        auto j_name = CefStringToJString(env, name);
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onPreferenceChanged", "(Ljava/lang/String;)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, j_name);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        env->PopLocalFrame(nullptr);
    }
};

extern "C" cef_preference_observer_t* Create_JniCefPreferenceObserver(JNIEnv* env, jobject handler) {
    JavaVM* jvm;
    env->GetJavaVM(&jvm);
    auto globalRef = env->NewGlobalRef(handler);
    return reinterpret_cast<cef_preference_observer_t*>(new JniCefPreferenceObserver(jvm, globalRef));
}
