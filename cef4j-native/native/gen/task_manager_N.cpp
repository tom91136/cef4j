// GENERATED - do not edit.
#include <jni.h>
#include "include/capi/cef_task_manager_capi.h"
#include "jni_util.h"

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefTaskManager_00024NativePeer_N_1Release(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

extern "C" JNIEXPORT jlong JNICALL Java_net_kurobako_cef4j_gen_CefTaskManager_00024NativePeer_N_1GetTasksCount(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_task_manager_t*>(self);
    if (!s) return 0;
    return static_cast<jlong>(s->get_tasks_count(s));
}

extern "C" JNIEXPORT jlongArray JNICALL Java_net_kurobako_cef4j_gen_CefTaskManager_00024NativePeer_N_1GetTaskIdsList(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_task_manager_t*>(self);
    if (!s) return nullptr;
    size_t _count = s->get_tasks_count(s);
    int64_t* _arr = _count > 0 ? new int64_t[_count]() : nullptr;
    s->get_task_ids_list(s, &_count, _arr);
    jlongArray _result = env->NewLongArray(static_cast<jsize>(_count));
    if (_count > 0) {
        // Copy with static_cast in case of size_t -> jlong
        auto* _tmp = new jlong[_count];
        for (size_t _i = 0; _i < _count; _i++) _tmp[_i] = static_cast<jlong>(_arr[_i]);
        env->SetLongArrayRegion(_result, 0, static_cast<jsize>(_count), _tmp);
        delete[] _tmp;
    }
    delete[] _arr;
    return _result;
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefTaskManager_00024NativePeer_N_1GetTaskInfo(JNIEnv* env, jobject obj, jlong self, jlong task_id, jobject info) {
    auto* s = reinterpret_cast<cef_task_manager_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->get_task_info(s, task_id, reinterpret_cast<struct _cef_task_info_t*>(info ? env->GetLongField(info, env->GetFieldID(env->GetObjectClass(info), "address", "J")) : 0));
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefTaskManager_00024NativePeer_N_1KillTask(JNIEnv* env, jobject obj, jlong self, jlong task_id) {
    auto* s = reinterpret_cast<cef_task_manager_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->kill_task(s, task_id);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jlong JNICALL Java_net_kurobako_cef4j_gen_CefTaskManager_00024NativePeer_N_1GetTaskIdForBrowserId(JNIEnv* env, jobject obj, jlong self, jint browser_id) {
    auto* s = reinterpret_cast<cef_task_manager_t*>(self);
    if (!s) return 0;
    return static_cast<jlong>(s->get_task_id_for_browser_id(s, browser_id));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefTaskManager_00024NativePeer_N_1Get(JNIEnv* env, jclass clz) {
    auto _r = cef_task_manager_get();
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefTaskManager$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}
