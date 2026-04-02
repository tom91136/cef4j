// GENERATED - do not edit.
#include <jni.h>
#include "include/capi/cef_stream_capi.h"
#include "jni_util.h"

#include <atomic>
#include "jni_util.h"

struct JniCefWriteHandler: public cef_write_handler_t {
    JavaVM *jvm;
    jobject javaHandler;  // global ref
    std::atomic<int> refCount { 1 };

    JniCefWriteHandler(JavaVM *vm, jobject handler) : cef_write_handler_t { }, jvm(vm) {
        javaHandler = handler;
        InitRefCount<JniCefWriteHandler, cef_write_handler_t> (&base);
        write = &_write;
        seek = &_seek;
        tell = &_tell;
        flush = &_flush;
        may_block = &_may_block;
    }

    static size_t CEF_CALLBACK _write(cef_write_handler_t* self, const void* ptr, size_t size, size_t n) {
        auto* h = reinterpret_cast<JniCefWriteHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(6) < 0) {return 0;}
        jobject j_ptr = (size > 0 && ptr) ? env->NewDirectByteBuffer(const_cast<void*>(static_cast<const void*>(ptr)), static_cast<jlong>(size)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "write", "(Ljava/nio/ByteBuffer;J)J");
        if (!mid) {env->PopLocalFrame(nullptr); return 0;}
        auto jResult = env->CallLongMethod(h->javaHandler, mid, j_ptr, static_cast<jlong>(n));
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return 0;}
        size_t nativeResult = static_cast<size_t>(jResult);
        env->PopLocalFrame(nullptr);
        return nativeResult;
    }

    static int CEF_CALLBACK _seek(cef_write_handler_t* self, int64_t offset, int whence) {
        auto* h = reinterpret_cast<JniCefWriteHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(5) < 0) {return 0;}
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "seek", "(JI)I");
        if (!mid) {env->PopLocalFrame(nullptr); return 0;}
        auto jResult = env->CallIntMethod(h->javaHandler, mid, static_cast<jlong>(offset), static_cast<jint>(whence));
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return 0;}
        int nativeResult = static_cast<int>(jResult);
        env->PopLocalFrame(nullptr);
        return nativeResult;
    }

    static int64_t CEF_CALLBACK _tell(cef_write_handler_t* self) {
        auto* h = reinterpret_cast<JniCefWriteHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(5) < 0) {return 0;}
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "tell", "()J");
        if (!mid) {env->PopLocalFrame(nullptr); return 0;}
        auto jResult = env->CallLongMethod(h->javaHandler, mid);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return 0;}
        int64_t nativeResult = static_cast<int64_t>(jResult);
        env->PopLocalFrame(nullptr);
        return nativeResult;
    }

    static int CEF_CALLBACK _flush(cef_write_handler_t* self) {
        auto* h = reinterpret_cast<JniCefWriteHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(5) < 0) {return 0;}
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "flush", "()I");
        if (!mid) {env->PopLocalFrame(nullptr); return 0;}
        auto jResult = env->CallIntMethod(h->javaHandler, mid);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return 0;}
        int nativeResult = static_cast<int>(jResult);
        env->PopLocalFrame(nullptr);
        return nativeResult;
    }

    static int CEF_CALLBACK _may_block(cef_write_handler_t* self) {
        auto* h = reinterpret_cast<JniCefWriteHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(5) < 0) {return false;}
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "mayBlock", "()Z");
        if (!mid) {env->PopLocalFrame(nullptr); return false;}
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return false;}
        env->PopLocalFrame(nullptr);
        return jResult;
    }
};

extern "C" cef_write_handler_t* Create_JniCefWriteHandler(JNIEnv *env, jobject handler) {
    JavaVM *jvm;
    env->GetJavaVM(&jvm);
    auto globalRef = env->NewGlobalRef(handler);
    return reinterpret_cast<cef_write_handler_t*>(new JniCefWriteHandler(jvm, globalRef));
}

extern "C" JNIEXPORT jlong JNICALL Java_net_kurobako_cef4j_gen_CefWriteHandler_1N_N_1Create(JNIEnv* env, jobject obj) {
    return reinterpret_cast<jlong>(Create_JniCefWriteHandler(env, obj));
}
