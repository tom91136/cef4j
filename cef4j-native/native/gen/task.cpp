// GENERATED - do not edit.
#include <jni.h>
#include "include/capi/cef_task_capi.h"
#include "jni_util.h"

#include <atomic>
#include "jni_util.h"

struct JniCefTask: public cef_task_t {
    JavaVM *jvm;
    jobject javaHandler;  // global ref
    std::atomic<int> refCount { 1 };

    JniCefTask(JavaVM *vm, jobject handler) : cef_task_t { }, jvm(vm) {
        javaHandler = handler;
        InitRefCount<JniCefTask, cef_task_t> (&base);
        execute = &_execute;
    }

    static void CEF_CALLBACK _execute(cef_task_t* self) {
        auto* h = reinterpret_cast<JniCefTask*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(5) < 0) {return;}
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "execute", "()V");
        if (!mid) {env->PopLocalFrame(nullptr); return;}
        env->CallVoidMethod(h->javaHandler, mid);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return;}
        env->PopLocalFrame(nullptr);
    }
};

extern "C" cef_task_t* Create_JniCefTask(JNIEnv *env, jobject handler) {
    JavaVM *jvm;
    env->GetJavaVM(&jvm);
    auto globalRef = env->NewGlobalRef(handler);
    return reinterpret_cast<cef_task_t*>(new JniCefTask(jvm, globalRef));
}

extern "C" JNIEXPORT jlong JNICALL Java_net_kurobako_cef4j_gen_CefTask_1N_N_1Create(JNIEnv* env, jobject obj) {
    return reinterpret_cast<jlong>(Create_JniCefTask(env, obj));
}
