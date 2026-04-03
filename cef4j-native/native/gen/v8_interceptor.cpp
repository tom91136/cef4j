// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
#include <jni.h>
#include "include/capi/cef_v8_capi.h"
#include "jni_util.h"

#include <atomic>
#include "jni_util.h"

struct JniCefV8Interceptor: public cef_v8_interceptor_t {
    JavaVM *jvm;
    jobject javaHandler;  // global ref
    std::atomic<int> refCount { 1 };

    JniCefV8Interceptor(JavaVM *vm, jobject handler) : cef_v8_interceptor_t { }, jvm(vm) {
        javaHandler = handler;
        InitRefCount<JniCefV8Interceptor, cef_v8_interceptor_t>(reinterpret_cast<cef_base_ref_counted_t*>(static_cast<cef_v8_interceptor_t*>(this)));
        get_byname = &_get_byname;
        get_byindex = &_get_byindex;
        set_byname = &_set_byname;
        set_byindex = &_set_byindex;
    }

    static int CEF_CALLBACK _get_byname(cef_v8_interceptor_t* self, const cef_string_t* name, struct _cef_v8_value_t* object, struct _cef_v8_value_t** retval, cef_string_t* exception) {
        auto* h = reinterpret_cast<JniCefV8Interceptor*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(17) < 0) {return 0;}
        auto j_name = CefStringToJString(env, name);
        cef_v8_value_t* _p_object = object;
        if (_p_object) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_object); _b->add_ref(_b);}
        auto j_object_cls = env->FindClass("net/kurobako/cef4j/gen/CefV8Value$NativePeer");
        auto j_object_ctor = env->GetMethodID(j_object_cls, "<init>", "(J)V");
        auto j_object = _p_object ? env->NewObject(j_object_cls, j_object_ctor, reinterpret_cast<jlong>(_p_object)) : nullptr;
        auto j_retval_ar_cls = env->FindClass("java/util/concurrent/atomic/AtomicReference");
        auto j_retval_ar_ctor = env->GetMethodID(j_retval_ar_cls, "<init>", "(Ljava/lang/Object;)V");
        auto j_retval_peer_cls = env->FindClass("net/kurobako/cef4j/gen/CefV8Value$NativePeer");
        auto j_retval_peer_ctor = env->GetMethodID(j_retval_peer_cls, "<init>", "(J)V");
        jobject j_retval_init = nullptr;
        if (retval && *retval) {
            {   auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(*retval); _b->add_ref(_b);}
            j_retval_init = env->NewObject(j_retval_peer_cls, j_retval_peer_ctor, reinterpret_cast<jlong>(*retval));
        }
        auto j_retval = env->NewObject(j_retval_ar_cls, j_retval_ar_ctor, j_retval_init);
        auto j_exception = CefStringToJString(env, exception);
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "getByname", "(Ljava/lang/String;Lnet/kurobako/cef4j/gen/CefV8Value;Ljava/util/concurrent/atomic/AtomicReference;Ljava/lang/String;)I");
        if (!mid) {env->PopLocalFrame(nullptr); return 0;}
        auto jResult = env->CallIntMethod(h->javaHandler, mid, j_name, j_object, j_retval, j_exception);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return 0;}
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
        int nativeResult = static_cast<int>(jResult);
        env->PopLocalFrame(nullptr);
        return nativeResult;
    }

    static int CEF_CALLBACK _get_byindex(cef_v8_interceptor_t* self, int index, struct _cef_v8_value_t* object, struct _cef_v8_value_t** retval, cef_string_t* exception) {
        auto* h = reinterpret_cast<JniCefV8Interceptor*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(16) < 0) {return 0;}
        cef_v8_value_t* _p_object = object;
        if (_p_object) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_object); _b->add_ref(_b);}
        auto j_object_cls = env->FindClass("net/kurobako/cef4j/gen/CefV8Value$NativePeer");
        auto j_object_ctor = env->GetMethodID(j_object_cls, "<init>", "(J)V");
        auto j_object = _p_object ? env->NewObject(j_object_cls, j_object_ctor, reinterpret_cast<jlong>(_p_object)) : nullptr;
        auto j_retval_ar_cls = env->FindClass("java/util/concurrent/atomic/AtomicReference");
        auto j_retval_ar_ctor = env->GetMethodID(j_retval_ar_cls, "<init>", "(Ljava/lang/Object;)V");
        auto j_retval_peer_cls = env->FindClass("net/kurobako/cef4j/gen/CefV8Value$NativePeer");
        auto j_retval_peer_ctor = env->GetMethodID(j_retval_peer_cls, "<init>", "(J)V");
        jobject j_retval_init = nullptr;
        if (retval && *retval) {
            {   auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(*retval); _b->add_ref(_b);}
            j_retval_init = env->NewObject(j_retval_peer_cls, j_retval_peer_ctor, reinterpret_cast<jlong>(*retval));
        }
        auto j_retval = env->NewObject(j_retval_ar_cls, j_retval_ar_ctor, j_retval_init);
        auto j_exception = CefStringToJString(env, exception);
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "getByindex", "(ILnet/kurobako/cef4j/gen/CefV8Value;Ljava/util/concurrent/atomic/AtomicReference;Ljava/lang/String;)I");
        if (!mid) {env->PopLocalFrame(nullptr); return 0;}
        auto jResult = env->CallIntMethod(h->javaHandler, mid, static_cast<jint>(index), j_object, j_retval, j_exception);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return 0;}
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
        int nativeResult = static_cast<int>(jResult);
        env->PopLocalFrame(nullptr);
        return nativeResult;
    }

    static int CEF_CALLBACK _set_byname(cef_v8_interceptor_t* self, const cef_string_t* name, struct _cef_v8_value_t* object, struct _cef_v8_value_t* value, cef_string_t* exception) {
        auto* h = reinterpret_cast<JniCefV8Interceptor*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(13) < 0) {return 0;}
        auto j_name = CefStringToJString(env, name);
        cef_v8_value_t* _p_object = object;
        if (_p_object) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_object); _b->add_ref(_b);}
        auto j_object_cls = env->FindClass("net/kurobako/cef4j/gen/CefV8Value$NativePeer");
        auto j_object_ctor = env->GetMethodID(j_object_cls, "<init>", "(J)V");
        auto j_object = _p_object ? env->NewObject(j_object_cls, j_object_ctor, reinterpret_cast<jlong>(_p_object)) : nullptr;
        cef_v8_value_t* _p_value = value;
        if (_p_value) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_value); _b->add_ref(_b);}
        auto j_value_cls = env->FindClass("net/kurobako/cef4j/gen/CefV8Value$NativePeer");
        auto j_value_ctor = env->GetMethodID(j_value_cls, "<init>", "(J)V");
        auto j_value = _p_value ? env->NewObject(j_value_cls, j_value_ctor, reinterpret_cast<jlong>(_p_value)) : nullptr;
        auto j_exception = CefStringToJString(env, exception);
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "setByname", "(Ljava/lang/String;Lnet/kurobako/cef4j/gen/CefV8Value;Lnet/kurobako/cef4j/gen/CefV8Value;Ljava/lang/String;)I");
        if (!mid) {env->PopLocalFrame(nullptr); return 0;}
        auto jResult = env->CallIntMethod(h->javaHandler, mid, j_name, j_object, j_value, j_exception);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return 0;}
        int nativeResult = static_cast<int>(jResult);
        env->PopLocalFrame(nullptr);
        return nativeResult;
    }

    static int CEF_CALLBACK _set_byindex(cef_v8_interceptor_t* self, int index, struct _cef_v8_value_t* object, struct _cef_v8_value_t* value, cef_string_t* exception) {
        auto* h = reinterpret_cast<JniCefV8Interceptor*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(12) < 0) {return 0;}
        cef_v8_value_t* _p_object = object;
        if (_p_object) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_object); _b->add_ref(_b);}
        auto j_object_cls = env->FindClass("net/kurobako/cef4j/gen/CefV8Value$NativePeer");
        auto j_object_ctor = env->GetMethodID(j_object_cls, "<init>", "(J)V");
        auto j_object = _p_object ? env->NewObject(j_object_cls, j_object_ctor, reinterpret_cast<jlong>(_p_object)) : nullptr;
        cef_v8_value_t* _p_value = value;
        if (_p_value) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_value); _b->add_ref(_b);}
        auto j_value_cls = env->FindClass("net/kurobako/cef4j/gen/CefV8Value$NativePeer");
        auto j_value_ctor = env->GetMethodID(j_value_cls, "<init>", "(J)V");
        auto j_value = _p_value ? env->NewObject(j_value_cls, j_value_ctor, reinterpret_cast<jlong>(_p_value)) : nullptr;
        auto j_exception = CefStringToJString(env, exception);
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "setByindex", "(ILnet/kurobako/cef4j/gen/CefV8Value;Lnet/kurobako/cef4j/gen/CefV8Value;Ljava/lang/String;)I");
        if (!mid) {env->PopLocalFrame(nullptr); return 0;}
        auto jResult = env->CallIntMethod(h->javaHandler, mid, static_cast<jint>(index), j_object, j_value, j_exception);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return 0;}
        int nativeResult = static_cast<int>(jResult);
        env->PopLocalFrame(nullptr);
        return nativeResult;
    }
};

extern "C" cef_v8_interceptor_t* Create_JniCefV8Interceptor(JNIEnv *env, jobject handler) {
    JavaVM *jvm;
    env->GetJavaVM(&jvm);
    auto globalRef = env->NewGlobalRef(handler);
    return reinterpret_cast<cef_v8_interceptor_t*>(new JniCefV8Interceptor(jvm, globalRef));
}
