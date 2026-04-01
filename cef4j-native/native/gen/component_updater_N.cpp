// GENERATED - do not edit.
#include <jni.h>
#include "include/capi/cef_component_updater_capi.h"
#include "jni_util.h"

extern "C" cef_component_update_callback_t* Create_JniCefComponentUpdateCallback(JNIEnv *env, jobject handler);
extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefComponentUpdater_00024NativePeer_N_1Release(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

extern "C" JNIEXPORT jlong JNICALL Java_net_kurobako_cef4j_gen_CefComponentUpdater_00024NativePeer_N_1GetComponentCount(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_component_updater_t*>(self);
    if (!s) return 0;
    return static_cast<jlong>(s->get_component_count(s));
}

extern "C" JNIEXPORT jobjectArray JNICALL Java_net_kurobako_cef4j_gen_CefComponentUpdater_00024NativePeer_N_1GetComponents(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_component_updater_t*>(self);
    if (!s) return nullptr;
    size_t _count = s->get_component_count(s);
    cef_component_t** _arr = _count > 0 ? new cef_component_t*[_count]() : nullptr;
    s->get_components(s, &_count, _arr);
    auto _elemCls = env->FindClass("net/kurobako/cef4j/gen/CefComponent$NativePeer");
    auto _elemCtor = env->GetMethodID(_elemCls, "<init>", "(J)V");
    auto _result = env->NewObjectArray(static_cast<jsize>(_count), _elemCls, nullptr);
    for (size_t _i = 0; _i < _count; _i++) {
        if (_arr[_i]) {
            auto _peer = env->NewObject(_elemCls, _elemCtor, reinterpret_cast<jlong>(_arr[_i]));
            env->SetObjectArrayElement(_result, static_cast<jsize>(_i), _peer);
        }
    }
    delete[] _arr;
    return _result;
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefComponentUpdater_00024NativePeer_N_1GetComponentById(JNIEnv* env, jobject obj, jlong self, jstring component_id) {
    auto* s = reinterpret_cast<cef_component_updater_t*>(self);
    if (!s) return nullptr;
    if (!component_id) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "componentId must not be null"); return nullptr;}
    auto _component_id_str = JStringToCefString(env, component_id);
    auto _r = s->get_component_by_id(s, _component_id_str);
    if (_component_id_str) cef_string_userfree_free(_component_id_str);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefComponent$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefComponentUpdater_00024NativePeer_N_1Update(JNIEnv* env, jobject obj, jlong self, jstring component_id, jobject priority, jobject callback) {
    auto* s = reinterpret_cast<cef_component_updater_t*>(self);
    if (!s) return;
    if (!component_id) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "componentId must not be null"); return;}
    if (!priority) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "priority must not be null"); return;}
    auto _component_id_str = JStringToCefString(env, component_id);
    cef_component_update_callback_t* _callback_ptr = callback ? Create_JniCefComponentUpdateCallback(env, callback) : nullptr;
    s->update(s, _component_id_str, static_cast<cef_component_update_priority_t>(env->GetLongField(priority, env->GetFieldID(env->GetObjectClass(priority), "value", "J"))), _callback_ptr);
    if (_component_id_str) cef_string_userfree_free(_component_id_str);
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefComponentUpdater_00024NativePeer_N_1Get(JNIEnv* env, jclass clz) {
    auto _r = cef_component_updater_get();
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefComponentUpdater$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}
