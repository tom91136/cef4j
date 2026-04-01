// GENERATED - do not edit.
#include <jni.h>
#include "include/capi/cef_response_filter_capi.h"
#include "jni_util.h"

#include <atomic>
#include "ref_counted_base.h"

// JNI wrapper struct for cef_response_filter_t
struct JniCefResponseFilter: public cef_response_filter_t {
    JavaVM *jvm;
    jobject javaHandler;  // global ref
    std::atomic<int> refCount { 1 };

    JniCefResponseFilter(JavaVM *vm, jobject handler) : cef_response_filter_t { }, jvm(vm) {
        javaHandler = handler;
        InitRefCount<JniCefResponseFilter, cef_response_filter_t> (&base);
        init_filter = &_init_filter;
        filter = &_filter;
    }

    static int CEF_CALLBACK _init_filter(cef_response_filter_t* self) {
        auto* h = reinterpret_cast<JniCefResponseFilter*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(5) < 0) {return false;}
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "initFilter", "()Z");
        if (!mid) {env->PopLocalFrame(nullptr); return false;}
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return false;}
        env->PopLocalFrame(nullptr);
        return jResult;
    }

    static cef_response_filter_status_t CEF_CALLBACK _filter(cef_response_filter_t* self, void* data_in, size_t data_in_size, size_t* data_in_read, void* data_out, size_t data_out_size, size_t* data_out_written) {
        auto* h = reinterpret_cast<JniCefResponseFilter*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(10) < 0) {return cef_response_filter_status_t::RESPONSE_FILTER_ERROR;}
        jobject j_data_in = (data_in_size > 0 && data_in) ? env->NewDirectByteBuffer(static_cast<void*>(data_in), static_cast<jlong>(data_in_size)) : nullptr;
        jlongArray j_data_in_read = env->NewLongArray(1);
        if (data_in_read) {jlong _v = static_cast<jlong>(*data_in_read); env->SetLongArrayRegion(j_data_in_read, 0, 1, &_v);}
        jobject j_data_out = (data_out_size > 0 && data_out) ? env->NewDirectByteBuffer(static_cast<void*>(data_out), static_cast<jlong>(data_out_size)) : nullptr;
        jlongArray j_data_out_written = env->NewLongArray(1);
        if (data_out_written) {jlong _v = static_cast<jlong>(*data_out_written); env->SetLongArrayRegion(j_data_out_written, 0, 1, &_v);}
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "filter", "(Ljava/nio/ByteBuffer;[JLjava/nio/ByteBuffer;[J)Lnet/kurobako/cef4j/gen/CefResponseFilterStatus;");
        if (!mid) {env->PopLocalFrame(nullptr); return cef_response_filter_status_t::RESPONSE_FILTER_ERROR;}
        auto jResult = (jobject)env->CallObjectMethod(h->javaHandler, mid, j_data_in, j_data_in_read, j_data_out, j_data_out_written);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return cef_response_filter_status_t::RESPONSE_FILTER_ERROR;}
        if (data_in_read) {jlong _v; env->GetLongArrayRegion(j_data_in_read, 0, 1, &_v); *data_in_read = static_cast<size_t>(_v);}
        if (data_out_written) {jlong _v; env->GetLongArrayRegion(j_data_out_written, 0, 1, &_v); *data_out_written = static_cast<size_t>(_v);}
        cef_response_filter_status_t nativeResult = static_cast<cef_response_filter_status_t>(jResult ? env->GetLongField(jResult, env->GetFieldID(env->GetObjectClass(jResult), "value", "J")) : 0);
        env->PopLocalFrame(nullptr);
        return nativeResult;
    }
};

extern "C" cef_response_filter_t* Create_JniCefResponseFilter(JNIEnv *env, jobject handler) {
    JavaVM *jvm;
    env->GetJavaVM(&jvm);
    auto globalRef = env->NewGlobalRef(handler);
    return reinterpret_cast<cef_response_filter_t*>(new JniCefResponseFilter(jvm, globalRef));
}

extern "C" JNIEXPORT jlong JNICALL Java_net_kurobako_cef4j_gen_CefResponseFilter_1N_N_1Create(JNIEnv* env, jobject obj) {
    return reinterpret_cast<jlong>(Create_JniCefResponseFilter(env, obj));
}
