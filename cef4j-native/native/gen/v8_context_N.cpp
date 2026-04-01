// GENERATED - do not edit.
#include <jni.h>
#include "include/capi/cef_v8_capi.h"
#include "include/capi/cef_browser_capi.h"
#include "include/capi/cef_frame_capi.h"
#include "include/capi/cef_task_capi.h"
#include "jni_util.h"

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefV8Context_00024NativePeer_N_1Release(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefV8Context_00024NativePeer_N_1GetTaskRunner(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_context_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_task_runner(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefTaskRunner$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefV8Context_00024NativePeer_N_1IsValid(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_context_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_valid(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefV8Context_00024NativePeer_N_1GetBrowser(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_context_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_browser(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefBrowser$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefV8Context_00024NativePeer_N_1GetFrame(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_context_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_frame(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefFrame$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefV8Context_00024NativePeer_N_1GetGlobal(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_context_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_global(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefV8Value$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefV8Context_00024NativePeer_N_1Enter(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_context_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->enter(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefV8Context_00024NativePeer_N_1Exit(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_v8_context_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->exit(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefV8Context_00024NativePeer_N_1IsSame(JNIEnv* env, jobject obj, jlong self, jobject that) {
    auto* s = reinterpret_cast<cef_v8_context_t*>(self);
    if (!s) return JNI_FALSE;
    if (!that) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "that must not be null"); return JNI_FALSE;}
    cef_v8_context_t* _that_ptr = reinterpret_cast<cef_v8_context_t*>(env->GetLongField(that, env->GetFieldID(env->GetObjectClass(that), "nativePtr", "J")));
    if (_that_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_that_ptr); _b->add_ref(_b);}
    auto _r = s->is_same(s, _that_ptr);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefV8Context_00024NativePeer_N_1Eval(JNIEnv* env, jobject obj, jlong self, jstring code, jstring script_url, jint start_line, jobject retval, jobject exception) {
    auto* s = reinterpret_cast<cef_v8_context_t*>(self);
    if (!s) return JNI_FALSE;
    if (!code) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "code must not be null"); return JNI_FALSE;}
    if (!retval) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "retval must not be null"); return JNI_FALSE;}
    if (!exception) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "exception must not be null"); return JNI_FALSE;}
    auto _code_str = JStringToCefString(env, code);
    auto _script_url_str = script_url ? JStringToCefString(env, script_url) : nullptr;
    cef_v8_value_t* _retval_ptr = nullptr;
    if (retval) {
        auto _get = env->GetMethodID(env->GetObjectClass(retval), "get", "()Ljava/lang/Object;");
        auto _cur = env->CallObjectMethod(retval, _get);
        if (_cur) _retval_ptr = reinterpret_cast<cef_v8_value_t*>(env->GetLongField(_cur, env->GetFieldID(env->GetObjectClass(_cur), "nativePtr", "J")));
    }
    cef_v8_exception_t* _exception_ptr = nullptr;
    if (exception) {
        auto _get = env->GetMethodID(env->GetObjectClass(exception), "get", "()Ljava/lang/Object;");
        auto _cur = env->CallObjectMethod(exception, _get);
        if (_cur) _exception_ptr = reinterpret_cast<cef_v8_exception_t*>(env->GetLongField(_cur, env->GetFieldID(env->GetObjectClass(_cur), "nativePtr", "J")));
    }
    auto _r = s->eval(s, _code_str, _script_url_str, start_line, &_retval_ptr, &_exception_ptr);
    if (_code_str) cef_string_userfree_free(_code_str);
    if (_script_url_str) cef_string_userfree_free(_script_url_str);
    if (retval && _retval_ptr) {
        auto _peerCls = env->FindClass("net/kurobako/cef4j/gen/CefV8Value$NativePeer");
        auto _peerCtor = env->GetMethodID(_peerCls, "<init>", "(J)V");
        {   auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_retval_ptr); _b->add_ref(_b);}
        auto _newPeer = env->NewObject(_peerCls, _peerCtor, reinterpret_cast<jlong>(_retval_ptr));
        auto _set = env->GetMethodID(env->GetObjectClass(retval), "set", "(Ljava/lang/Object;)V");
        env->CallVoidMethod(retval, _set, _newPeer);
    }
    if (exception && _exception_ptr) {
        auto _peerCls = env->FindClass("net/kurobako/cef4j/gen/CefV8Exception$NativePeer");
        auto _peerCtor = env->GetMethodID(_peerCls, "<init>", "(J)V");
        {   auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_exception_ptr); _b->add_ref(_b);}
        auto _newPeer = env->NewObject(_peerCls, _peerCtor, reinterpret_cast<jlong>(_exception_ptr));
        auto _set = env->GetMethodID(env->GetObjectClass(exception), "set", "(Ljava/lang/Object;)V");
        env->CallVoidMethod(exception, _set, _newPeer);
    }
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefV8Context_00024NativePeer_N_1GetCurrentContext(JNIEnv* env, jclass clz) {
    auto _r = cef_v8_context_get_current_context();
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefV8Context$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefV8Context_00024NativePeer_N_1GetEnteredContext(JNIEnv* env, jclass clz) {
    auto _r = cef_v8_context_get_entered_context();
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefV8Context$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jint JNICALL Java_net_kurobako_cef4j_gen_CefV8Context_00024NativePeer_N_1InContext(JNIEnv* env, jclass clz) {
    return static_cast<jint>(cef_v8_context_in_context());
}
