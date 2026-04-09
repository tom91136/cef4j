// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
#include <jni.h>
#include "include/capi/cef_task_capi.h"
#include "jni_util.h"

extern "C" cef_task_t* Create_JniCefTask(JNIEnv* env, jobject handler);
CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefTaskRunner), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefTaskRunner), isSame0)(JNIEnv* env, jobject obj, jlong self, jobject that) {
    auto* s = reinterpret_cast<cef_task_runner_t*>(self);
    if (!s) return JNI_FALSE;
    cef_task_runner_t* _that_ptr = that ? reinterpret_cast<cef_task_runner_t*>(env->GetLongField(that, env->GetFieldID(env->GetObjectClass(that), "nativePtr", "J"))) : nullptr;
    if (_that_ptr) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_that_ptr); _b->add_ref(_b); }
    auto _r = s->is_same(s, _that_ptr);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefTaskRunner), belongsToCurrentThread0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_task_runner_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->belongs_to_current_thread(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefTaskRunner), belongsToThread0)(JNIEnv* env, jobject obj, jlong self, jobject threadId) {
    auto* s = reinterpret_cast<cef_task_runner_t*>(self);
    if (!s) return JNI_FALSE;
    if (!threadId) { env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "threadId must not be null"); return JNI_FALSE; }
    auto _r = s->belongs_to_thread(s, static_cast<cef_thread_id_t>(env->GetLongField(threadId, env->GetFieldID(env->GetObjectClass(threadId), "value", "J"))));
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefTaskRunner), postTask0)(JNIEnv* env, jobject obj, jlong self, jobject task) {
    auto* s = reinterpret_cast<cef_task_runner_t*>(self);
    if (!s) return JNI_FALSE;
    cef_task_t* _task_ptr = task ? Create_JniCefTask(env, task) : nullptr;
    auto _r = s->post_task(s, _task_ptr);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefTaskRunner), postDelayedTask0)(JNIEnv* env, jobject obj, jlong self, jobject task, jlong delay_ms) {
    auto* s = reinterpret_cast<cef_task_runner_t*>(self);
    if (!s) return JNI_FALSE;
    cef_task_t* _task_ptr = task ? Create_JniCefTask(env, task) : nullptr;
    auto _r = s->post_delayed_task(s, _task_ptr, delay_ms);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefTaskRunner), getForCurrentThread0)(JNIEnv* env, jclass clz) {
    auto _r = cef_task_runner_get_for_current_thread();
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefTaskRunner$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefTaskRunner), getForThread0)(JNIEnv* env, jclass clz, jobject threadId) {
    if (!threadId) { env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "threadId must not be null"); return nullptr; }
    auto _r = cef_task_runner_get_for_thread(static_cast<cef_thread_id_t>(env->GetLongField(threadId, env->GetFieldID(env->GetObjectClass(threadId), "value", "J"))));
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefTaskRunner$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}
