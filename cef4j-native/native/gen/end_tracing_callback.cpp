// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
#include <jni.h>
#include "include/capi/cef_trace_capi.h"
#include "jni_util.h"

#include <atomic>
#include "jni_util.h"

struct JniCefEndTracingCallback: public cef_end_tracing_callback_t {
    JavaVM *jvm;
    jobject javaHandler;  // global ref
    std::atomic<int> refCount { 1 };

    JniCefEndTracingCallback(JavaVM *vm, jobject handler) : cef_end_tracing_callback_t { }, jvm(vm) {
        javaHandler = handler;
        InitRefCount<JniCefEndTracingCallback, cef_end_tracing_callback_t>(
                reinterpret_cast<cef_base_ref_counted_t*>(static_cast<cef_end_tracing_callback_t*>(this)));
        on_end_tracing_complete = &_on_end_tracing_complete;
    }

    static void CEF_CALLBACK _on_end_tracing_complete(cef_end_tracing_callback_t* self, const cef_string_t* tracing_file) {
        auto* h = reinterpret_cast<JniCefEndTracingCallback*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(6) < 0) {return;}
        auto j_tracing_file = CefStringToJString(env, tracing_file);
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onEndTracingComplete", "(Ljava/lang/String;)V");
        if (!mid) {env->PopLocalFrame(nullptr); return;}
        env->CallVoidMethod(h->javaHandler, mid, j_tracing_file);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return;}
        env->PopLocalFrame(nullptr);
    }
};

extern "C" cef_end_tracing_callback_t* Create_JniCefEndTracingCallback(JNIEnv *env, jobject handler) {
    JavaVM *jvm;
    env->GetJavaVM(&jvm);
    auto globalRef = env->NewGlobalRef(handler);
    return reinterpret_cast<cef_end_tracing_callback_t*>(new JniCefEndTracingCallback(jvm, globalRef));
}
