// GENERATED - do not edit.
#include <jni.h>
#include "include/capi/cef_browser_capi.h"
#include "include/capi/cef_navigation_entry_capi.h"
#include "jni_util.h"

#include <atomic>
#include "jni_util.h"

struct JniCefNavigationEntryVisitor: public cef_navigation_entry_visitor_t {
    JavaVM *jvm;
    jobject javaHandler;  // global ref
    std::atomic<int> refCount { 1 };

    JniCefNavigationEntryVisitor(JavaVM *vm, jobject handler) : cef_navigation_entry_visitor_t { }, jvm(vm) {
        javaHandler = handler;
        InitRefCount<JniCefNavigationEntryVisitor, cef_navigation_entry_visitor_t> (&base);
        visit = &_visit;
    }

    static int CEF_CALLBACK _visit(cef_navigation_entry_visitor_t* self, struct _cef_navigation_entry_t* entry, int current, int index, int total) {
        auto* h = reinterpret_cast<JniCefNavigationEntryVisitor*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) {return 0;}
        cef_navigation_entry_t* _p_entry = entry;
        if (_p_entry) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_entry); _b->add_ref(_b);}
        auto j_entry_cls = env->FindClass("net/kurobako/cef4j/gen/CefNavigationEntry$NativePeer");
        auto j_entry_ctor = env->GetMethodID(j_entry_cls, "<init>", "(J)V");
        auto j_entry = _p_entry ? env->NewObject(j_entry_cls, j_entry_ctor, reinterpret_cast<jlong>(_p_entry)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "visit", "(Lnet/kurobako/cef4j/gen/CefNavigationEntry;III)I");
        if (!mid) {env->PopLocalFrame(nullptr); return 0;}
        auto jResult = env->CallIntMethod(h->javaHandler, mid, j_entry, static_cast<jint>(current), static_cast<jint>(index), static_cast<jint>(total));
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return 0;}
        int nativeResult = static_cast<int>(jResult);
        env->PopLocalFrame(nullptr);
        return nativeResult;
    }
};

extern "C" cef_navigation_entry_visitor_t* Create_JniCefNavigationEntryVisitor(JNIEnv *env, jobject handler) {
    JavaVM *jvm;
    env->GetJavaVM(&jvm);
    auto globalRef = env->NewGlobalRef(handler);
    return reinterpret_cast<cef_navigation_entry_visitor_t*>(new JniCefNavigationEntryVisitor(jvm, globalRef));
}

extern "C" JNIEXPORT jlong JNICALL Java_net_kurobako_cef4j_gen_CefNavigationEntryVisitor_1N_N_1Create(JNIEnv* env, jobject obj) {
    return reinterpret_cast<jlong>(Create_JniCefNavigationEntryVisitor(env, obj));
}
