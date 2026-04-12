// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
#include <jni.h>
#include "include/capi/cef_task_manager_capi.h"
#include "jni_util.h"

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefTaskManager), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

CEF4J_JNI_EXPORT(jlong, CEF4J_PEER(CefTaskManager), getTasksCount0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_task_manager_t*>(self);
    if (!s) return 0;
    return static_cast<jlong>(s->get_tasks_count(s));
}

CEF4J_JNI_EXPORT(jlongArray, CEF4J_PEER(CefTaskManager), getTaskIdsList0)(JNIEnv* env, jobject obj, jlong self) {
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

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefTaskManager), getTaskInfo0)(JNIEnv* env, jobject obj, jlong self, jlong task_id, jobject info) {
    auto* s = reinterpret_cast<cef_task_manager_t*>(self);
    if (!s) return JNI_FALSE;
    if (!info) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "info must not be null"); return JNI_FALSE; }
    cef_task_info_t _info_val = {};
    auto _info_c = FindClassCached(env, "net/kurobako/cef4j/gen/CefTaskInfo$Mutable");
    _info_val.id = from_jlong<decltype(_info_val.id)>(env->GetLongField(info, env->GetFieldID(_info_c, "id", "J")));
    auto _rd_type = env->GetObjectField(info, env->GetFieldID(_info_c, "type", "Lnet/kurobako/cef4j/gen/CefTaskType;"));
    if (_rd_type) {
        _info_val.type = static_cast<decltype(_info_val.type)>(env->GetLongField(_rd_type, env->GetFieldID(env->GetObjectClass(_rd_type), "value", "J")));
    }
    _info_val.is_killable = static_cast<decltype(_info_val.is_killable)>(env->GetIntField(info, env->GetFieldID(_info_c, "isKillable", "I")));
    jstring _rd_title = (jstring)env->GetObjectField(info, env->GetFieldID(_info_c, "title", "Ljava/lang/String;"));
    CefStringSetFromJString(env, _rd_title, &_info_val.title);
    _info_val.cpu_usage = static_cast<decltype(_info_val.cpu_usage)>(env->GetDoubleField(info, env->GetFieldID(_info_c, "cpuUsage", "D")));
    _info_val.number_of_processors = static_cast<decltype(_info_val.number_of_processors)>(env->GetIntField(info, env->GetFieldID(_info_c, "numberOfProcessors", "I")));
    _info_val.memory = from_jlong<decltype(_info_val.memory)>(env->GetLongField(info, env->GetFieldID(_info_c, "memory", "J")));
    _info_val.gpu_memory = from_jlong<decltype(_info_val.gpu_memory)>(env->GetLongField(info, env->GetFieldID(_info_c, "gpuMemory", "J")));
    _info_val.is_gpu_memory_inflated = static_cast<decltype(_info_val.is_gpu_memory_inflated)>(env->GetIntField(info, env->GetFieldID(_info_c, "isGpuMemoryInflated", "I")));
    _info_val.size = sizeof(cef_task_info_t);
    auto _r = s->get_task_info(s, task_id, &_info_val);
    env->SetLongField(info, env->GetFieldID(_info_c, "id", "J"), to_jlong((&_info_val)->id));
    auto _wb_type_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefTaskType");
    auto _wb_type_of = env->GetStaticMethodID(_wb_type_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefTaskType;");
    auto _wb_type_obj = env->CallStaticObjectMethod(_wb_type_cls, _wb_type_of, static_cast<jlong>((&_info_val)->type));
    env->SetObjectField(info, env->GetFieldID(_info_c, "type", "Lnet/kurobako/cef4j/gen/CefTaskType;"), _wb_type_obj);
    if (_wb_type_obj) env->DeleteLocalRef(_wb_type_obj);
    env->SetIntField(info, env->GetFieldID(_info_c, "isKillable", "I"), static_cast<jint>((&_info_val)->is_killable));
    auto _wb_title_jstr = CefStringToJStringAuto(env, (&_info_val)->title);
    env->SetObjectField(info, env->GetFieldID(_info_c, "title", "Ljava/lang/String;"), _wb_title_jstr);
    if (_wb_title_jstr) env->DeleteLocalRef(_wb_title_jstr);
    env->SetDoubleField(info, env->GetFieldID(_info_c, "cpuUsage", "D"), static_cast<jdouble>((&_info_val)->cpu_usage));
    env->SetIntField(info, env->GetFieldID(_info_c, "numberOfProcessors", "I"), static_cast<jint>((&_info_val)->number_of_processors));
    env->SetLongField(info, env->GetFieldID(_info_c, "memory", "J"), to_jlong((&_info_val)->memory));
    env->SetLongField(info, env->GetFieldID(_info_c, "gpuMemory", "J"), to_jlong((&_info_val)->gpu_memory));
    env->SetIntField(info, env->GetFieldID(_info_c, "isGpuMemoryInflated", "I"), static_cast<jint>((&_info_val)->is_gpu_memory_inflated));
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefTaskManager), killTask0)(JNIEnv* env, jobject obj, jlong self, jlong task_id) {
    auto* s = reinterpret_cast<cef_task_manager_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->kill_task(s, task_id);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jlong, CEF4J_PEER(CefTaskManager), getTaskIdForBrowserId0)(JNIEnv* env, jobject obj, jlong self, jint browser_id) {
    auto* s = reinterpret_cast<cef_task_manager_t*>(self);
    if (!s) return 0;
    return to_jlong(s->get_task_id_for_browser_id(s, browser_id));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefTaskManager), get0)(JNIEnv* env, jclass clz) {
    auto _r = cef_task_manager_get();
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefTaskManager$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}
