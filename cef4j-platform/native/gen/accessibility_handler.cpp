// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
#include <jni.h>
#include "include/capi/cef_accessibility_handler_capi.h"
#include "include/capi/cef_values_capi.h"
#include "jni_util.h"

#include <atomic>

struct JniCefAccessibilityHandler : public cef_accessibility_handler_t {
    JavaVM* jvm;
    jobject javaHandler;  // global ref
    std::atomic<int> refCount{1};

    JniCefAccessibilityHandler(JavaVM* vm, jobject handler) : cef_accessibility_handler_t{}, jvm(vm) {
        javaHandler = handler;
        InitRefCount<JniCefAccessibilityHandler, cef_accessibility_handler_t>(reinterpret_cast<cef_base_ref_counted_t*>(static_cast<cef_accessibility_handler_t*>(this)));
        on_accessibility_tree_change = &_on_accessibility_tree_change;
        on_accessibility_location_change = &_on_accessibility_location_change;
    }

    static void CEF_CALLBACK _on_accessibility_tree_change(cef_accessibility_handler_t* self, struct _cef_value_t* value) {
        auto* h = reinterpret_cast<JniCefAccessibilityHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) { return; }
        cef_value_t* _p_value = value;
        if (_p_value) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_value); _b->add_ref(_b); }
        auto j_value_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefValue$NativePeer");
        auto j_value_ctor = env->GetMethodID(j_value_cls, "<init>", "(J)V");
        auto j_value = _p_value ? env->NewObject(j_value_cls, j_value_ctor, reinterpret_cast<jlong>(_p_value)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onAccessibilityTreeChange", "(Lnet/kurobako/cef4j/gen/CefValue;)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, j_value);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        env->PopLocalFrame(nullptr);
    }

    static void CEF_CALLBACK _on_accessibility_location_change(cef_accessibility_handler_t* self, struct _cef_value_t* value) {
        auto* h = reinterpret_cast<JniCefAccessibilityHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) { return; }
        cef_value_t* _p_value = value;
        if (_p_value) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_value); _b->add_ref(_b); }
        auto j_value_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefValue$NativePeer");
        auto j_value_ctor = env->GetMethodID(j_value_cls, "<init>", "(J)V");
        auto j_value = _p_value ? env->NewObject(j_value_cls, j_value_ctor, reinterpret_cast<jlong>(_p_value)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onAccessibilityLocationChange", "(Lnet/kurobako/cef4j/gen/CefValue;)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, j_value);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        env->PopLocalFrame(nullptr);
    }
};

extern "C" cef_accessibility_handler_t* Create_JniCefAccessibilityHandler(JNIEnv* env, jobject handler) {
    JavaVM* jvm;
    env->GetJavaVM(&jvm);
    auto globalRef = env->NewGlobalRef(handler);
    return reinterpret_cast<cef_accessibility_handler_t*>(new JniCefAccessibilityHandler(jvm, globalRef));
}
