// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
#include <jni.h>
#include "include/capi/cef_v8_capi.h"
#include "jni_util.h"

#include <atomic>
#include <cstdio>
#include <limits>

struct JniCefV8Handler : public cef_v8_handler_t {
    JavaVM* jvm;
    jobject javaHandler;  // global ref
    std::atomic<int> refCount{1};

    JniCefV8Handler(JavaVM* vm, jobject handler) : cef_v8_handler_t{}, jvm(vm) {
        javaHandler = handler;
        InitRefCount<JniCefV8Handler, cef_v8_handler_t>(reinterpret_cast<cef_base_ref_counted_t*>(static_cast<cef_v8_handler_t*>(this)));
        execute = &_execute;
    }

    static int CEF_CALLBACK _execute(cef_v8_handler_t* self, const cef_string_t* name, struct _cef_v8_value_t* object, size_t argumentsCount, struct _cef_v8_value_t* const* arguments, struct _cef_v8_value_t** retval, cef_string_t* exception) {
        auto* h = reinterpret_cast<JniCefV8Handler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(36) < 0) { return false; }
        if (static_cast<unsigned long long>(argumentsCount) > static_cast<unsigned long long>((std::numeric_limits<jsize>::max)())) { std::fprintf(stderr, "[cef4j] native arguments count exceeds Java array capacity\n"); env->PopLocalFrame(nullptr); return false; }
        if (argumentsCount > 0 && !arguments) { std::fprintf(stderr, "[cef4j] native arguments array is null with a positive count\n"); env->PopLocalFrame(nullptr); return false; }
        auto j_name = CefStringToJString(env, name);
        cef_v8_value_t* _p_object = object;
        if (_p_object) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_object); _b->add_ref(_b); }
        auto j_object_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefV8Value$NativePeer");
        auto j_object_ctor = env->GetMethodID(j_object_cls, "<init>", "(J)V");
        auto j_object = _p_object ? env->NewObject(j_object_cls, j_object_ctor, reinterpret_cast<jlong>(_p_object)) : nullptr;
        auto j_arguments_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefV8Value$NativePeer");
        auto j_arguments_ctor = env->GetMethodID(j_arguments_cls, "<init>", "(J)V");
        jsize j_arguments_len = static_cast<jsize>(argumentsCount);
        auto j_arguments = env->NewObjectArray(j_arguments_len, j_arguments_cls, nullptr);
        for (jsize _i = 0; _i < j_arguments_len; _i++) {
            cef_v8_value_t* _elem = arguments[_i];
            if (_elem) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_elem); _b->add_ref(_b); }
            auto _jelem = _elem ? env->NewObject(j_arguments_cls, j_arguments_ctor, reinterpret_cast<jlong>(_elem)) : nullptr;
            env->SetObjectArrayElement(j_arguments, _i, _jelem);
        }
        auto j_retval_ar_cls = FindClassCached(env, "java/util/concurrent/atomic/AtomicReference");
        auto j_retval_ar_ctor = env->GetMethodID(j_retval_ar_cls, "<init>", "(Ljava/lang/Object;)V");
        auto j_retval_peer_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefV8Value$NativePeer");
        auto j_retval_peer_ctor = env->GetMethodID(j_retval_peer_cls, "<init>", "(J)V");
        jobject j_retval_init = nullptr;
        if (retval && *retval) {
            { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(*retval); _b->add_ref(_b); }
            j_retval_init = env->NewObject(j_retval_peer_cls, j_retval_peer_ctor, reinterpret_cast<jlong>(*retval));
        }
        auto j_retval = env->NewObject(j_retval_ar_cls, j_retval_ar_ctor, j_retval_init);
        auto j_exception = CefStringToJString(env, exception);
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "execute", "(Ljava/lang/String;Lnet/kurobako/cef4j/gen/CefV8Value;J[Lnet/kurobako/cef4j/gen/CefV8Value;Ljava/util/concurrent/atomic/AtomicReference;Ljava/lang/String;)Z");
        if (!mid) { env->PopLocalFrame(nullptr); return false; }
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, j_name, j_object, static_cast<jlong>(argumentsCount), j_arguments, j_retval, j_exception);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return false; }
        if (retval) {
            auto j_retval_get = env->GetMethodID(j_retval_ar_cls, "get", "()Ljava/lang/Object;");
            auto j_retval_new = env->CallObjectMethod(j_retval, j_retval_get);
            if (j_retval_new && j_retval_new != j_retval_init) {
                auto j_retval_fid = env->GetFieldID(j_retval_peer_cls, "nativePtr", "J");
                jlong j_retval_ptr = env->GetLongField(j_retval_new, j_retval_fid);
                *retval = reinterpret_cast<cef_v8_value_t*>(j_retval_ptr);
            } else if (!j_retval_new) {
                *retval = nullptr;
            }
        }
        env->PopLocalFrame(nullptr);
        return jResult;
    }
};

extern "C" cef_v8_handler_t* Create_JniCefV8Handler(JNIEnv* env, jobject handler) {
    JavaVM* jvm;
    env->GetJavaVM(&jvm);
    auto globalRef = env->NewGlobalRef(handler);
    return reinterpret_cast<cef_v8_handler_t*>(new JniCefV8Handler(jvm, globalRef));
}
