// GENERATED - do not edit.
#include <jni.h>
#include "include/capi/cef_stream_capi.h"
#include "jni_util.h"

extern "C" cef_read_handler_t* Create_JniCefReadHandler(JNIEnv *env, jobject handler);
extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefStreamReader_00024NativePeer_N_1Release(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

extern "C" JNIEXPORT jlong JNICALL Java_net_kurobako_cef4j_gen_CefStreamReader_00024NativePeer_N_1Read(JNIEnv* env, jobject obj, jlong self, jobject ptr, jlong size, jlong n) {
    auto* s = reinterpret_cast<cef_stream_reader_t*>(self);
    if (!s) return 0;
    if (!ptr) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "ptr must not be null"); return 0;}
    return static_cast<jlong>(s->read(s, reinterpret_cast<void*>(ptr ? env->GetLongField(ptr, env->GetFieldID(env->GetObjectClass(ptr), "address", "J")) : 0), size, n));
}

extern "C" JNIEXPORT jint JNICALL Java_net_kurobako_cef4j_gen_CefStreamReader_00024NativePeer_N_1Seek(JNIEnv* env, jobject obj, jlong self, jlong offset, jint whence) {
    auto* s = reinterpret_cast<cef_stream_reader_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->seek(s, offset, whence));
}

extern "C" JNIEXPORT jlong JNICALL Java_net_kurobako_cef4j_gen_CefStreamReader_00024NativePeer_N_1Tell(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_stream_reader_t*>(self);
    if (!s) return 0;
    return static_cast<jlong>(s->tell(s));
}

extern "C" JNIEXPORT jint JNICALL Java_net_kurobako_cef4j_gen_CefStreamReader_00024NativePeer_N_1Eof(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_stream_reader_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->eof(s));
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefStreamReader_00024NativePeer_N_1MayBlock(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_stream_reader_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->may_block(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefStreamReader_00024NativePeer_N_1CreateForFile(JNIEnv* env, jclass clz, jstring fileName) {
    if (!fileName) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "filename must not be null"); return nullptr;}
    auto _fileName_str = JStringToCefString(env, fileName);
    auto _r = cef_stream_reader_create_for_file(_fileName_str);
    if (_fileName_str) cef_string_userfree_free(_fileName_str);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefStreamReader$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefStreamReader_00024NativePeer_N_1CreateForData(JNIEnv* env, jclass clz, jobject data) {
    if (!data) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "data must not be null"); return nullptr;}
    void* _data_addr = data ? env->GetDirectBufferAddress(data) : nullptr;
    auto _r = cef_stream_reader_create_for_data(_data_addr, static_cast<size_t>(env->GetDirectBufferCapacity(data)));
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefStreamReader$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefStreamReader_00024NativePeer_N_1CreateForHandler(JNIEnv* env, jclass clz, jobject handler) {
    if (!handler) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "handler must not be null"); return nullptr;}
    cef_read_handler_t* _handler_ptr = Create_JniCefReadHandler(env, handler);
    auto _r = cef_stream_reader_create_for_handler(_handler_ptr);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefStreamReader$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}
