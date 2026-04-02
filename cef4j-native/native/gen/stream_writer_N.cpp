// GENERATED - do not edit.
#include <jni.h>
#include "include/capi/cef_stream_capi.h"
#include "jni_util.h"

extern "C" cef_write_handler_t* Create_JniCefWriteHandler(JNIEnv *env, jobject handler);
extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefStreamWriter_00024NativePeer_N_1Release(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

extern "C" JNIEXPORT jlong JNICALL Java_net_kurobako_cef4j_gen_CefStreamWriter_00024NativePeer_N_1Write(JNIEnv* env, jobject obj, jlong self, jobject ptr, jlong n) {
    auto* s = reinterpret_cast<cef_stream_writer_t*>(self);
    if (!s) return 0;
    if (!ptr) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "ptr must not be null"); return 0;}
    const void* _ptr_addr = ptr ? env->GetDirectBufferAddress(ptr) : nullptr;
    if (ptr && !_ptr_addr) {env->ThrowNew(env->FindClass("java/lang/IllegalArgumentException"), "ptr must be a direct ByteBuffer; use ByteBuffer.allocateDirect(...)"); return 0;}
    return static_cast<jlong>(s->write(s, _ptr_addr, static_cast<size_t>(env->GetDirectBufferCapacity(ptr)), n));
}

extern "C" JNIEXPORT jint JNICALL Java_net_kurobako_cef4j_gen_CefStreamWriter_00024NativePeer_N_1Seek(JNIEnv* env, jobject obj, jlong self, jlong offset, jint whence) {
    auto* s = reinterpret_cast<cef_stream_writer_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->seek(s, offset, whence));
}

extern "C" JNIEXPORT jlong JNICALL Java_net_kurobako_cef4j_gen_CefStreamWriter_00024NativePeer_N_1Tell(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_stream_writer_t*>(self);
    if (!s) return 0;
    return static_cast<jlong>(s->tell(s));
}

extern "C" JNIEXPORT jint JNICALL Java_net_kurobako_cef4j_gen_CefStreamWriter_00024NativePeer_N_1Flush(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_stream_writer_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->flush(s));
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefStreamWriter_00024NativePeer_N_1MayBlock(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_stream_writer_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->may_block(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefStreamWriter_00024NativePeer_N_1CreateForFile(JNIEnv* env, jclass clz, jstring fileName) {
    auto _fileName_str = JStringToCefString(env, fileName);
    auto _r = cef_stream_writer_create_for_file(_fileName_str);
    if (_fileName_str) cef_string_userfree_free(_fileName_str);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefStreamWriter$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefStreamWriter_00024NativePeer_N_1CreateForHandler(JNIEnv* env, jclass clz, jobject handler) {
    cef_write_handler_t* _handler_ptr = handler ? Create_JniCefWriteHandler(env, handler) : nullptr;
    auto _r = cef_stream_writer_create_for_handler(_handler_ptr);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefStreamWriter$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}
