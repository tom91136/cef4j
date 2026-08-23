// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
#include <jni.h>
#include "include/capi/cef_string_visitor_capi.h"
#include "jni_util.h"

#include <atomic>

struct JniCefStringVisitor : public cef_string_visitor_t {
    JavaVM* jvm;
    jobject javaHandler;  // global ref
    std::atomic<int> refCount{1};

    JniCefStringVisitor(JavaVM* vm, jobject handler) : cef_string_visitor_t{}, jvm(vm) {
        javaHandler = handler;
        InitRefCount<JniCefStringVisitor, cef_string_visitor_t>(reinterpret_cast<cef_base_ref_counted_t*>(static_cast<cef_string_visitor_t*>(this)));
        visit = &_visit;
    }

    static void CEF_CALLBACK _visit(cef_string_visitor_t* self, const cef_string_t* string) {
        auto* h = reinterpret_cast<JniCefStringVisitor*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(6) < 0) { return; }
        auto j_string = CefStringToJString(env, string);
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "visit", "(Ljava/lang/String;)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, j_string);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        env->PopLocalFrame(nullptr);
    }
};

extern "C" cef_string_visitor_t* Create_JniCefStringVisitor(JNIEnv* env, jobject handler) {
    JavaVM* jvm;
    env->GetJavaVM(&jvm);
    auto globalRef = env->NewGlobalRef(handler);
    return reinterpret_cast<cef_string_visitor_t*>(new JniCefStringVisitor(jvm, globalRef));
}
