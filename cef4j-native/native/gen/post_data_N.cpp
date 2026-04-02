// GENERATED - do not edit.
#include <jni.h>
#include "include/capi/cef_request_capi.h"
#include "jni_util.h"

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefPostData_00024NativePeer_N_1Release(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefPostData_00024NativePeer_N_1IsReadOnly(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_post_data_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_read_only(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefPostData_00024NativePeer_N_1HasExcludedElements(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_post_data_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->has_excluded_elements(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jlong JNICALL Java_net_kurobako_cef4j_gen_CefPostData_00024NativePeer_N_1GetElementCount(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_post_data_t*>(self);
    if (!s) return 0;
    return static_cast<jlong>(s->get_element_count(s));
}

extern "C" JNIEXPORT jobjectArray JNICALL Java_net_kurobako_cef4j_gen_CefPostData_00024NativePeer_N_1GetElements(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_post_data_t*>(self);
    if (!s) return nullptr;
    size_t _count = s->get_element_count(s);
    cef_post_data_element_t** _arr = _count > 0 ? new cef_post_data_element_t*[_count]() : nullptr;
    s->get_elements(s, &_count, _arr);
    auto _elemCls = env->FindClass("net/kurobako/cef4j/gen/CefPostDataElement$NativePeer");
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

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefPostData_00024NativePeer_N_1RemoveElement(JNIEnv* env, jobject obj, jlong self, jobject element) {
    auto* s = reinterpret_cast<cef_post_data_t*>(self);
    if (!s) return JNI_FALSE;
    cef_post_data_element_t* _element_ptr = element ? reinterpret_cast<cef_post_data_element_t*>(env->GetLongField(element, env->GetFieldID(env->GetObjectClass(element), "nativePtr", "J"))) : nullptr;
    if (_element_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_element_ptr); _b->add_ref(_b);}
    auto _r = s->remove_element(s, _element_ptr);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefPostData_00024NativePeer_N_1AddElement(JNIEnv* env, jobject obj, jlong self, jobject element) {
    auto* s = reinterpret_cast<cef_post_data_t*>(self);
    if (!s) return JNI_FALSE;
    cef_post_data_element_t* _element_ptr = element ? reinterpret_cast<cef_post_data_element_t*>(env->GetLongField(element, env->GetFieldID(env->GetObjectClass(element), "nativePtr", "J"))) : nullptr;
    if (_element_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_element_ptr); _b->add_ref(_b);}
    auto _r = s->add_element(s, _element_ptr);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefPostData_00024NativePeer_N_1RemoveElements(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_post_data_t*>(self);
    if (!s) return;
    s->remove_elements(s);
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefPostData_00024NativePeer_N_1Create(JNIEnv* env, jclass clz) {
    auto _r = cef_post_data_create();
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefPostData$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}
