// GENERATED - do not edit.
#include <jni.h>
#include "include/capi/cef_zip_reader_capi.h"
#include "include/capi/cef_stream_capi.h"
#include "jni_util.h"

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefZipReader_00024NativePeer_N_1Release(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefZipReader_00024NativePeer_N_1MoveToFirstFile(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_zip_reader_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->move_to_first_file(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefZipReader_00024NativePeer_N_1MoveToNextFile(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_zip_reader_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->move_to_next_file(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefZipReader_00024NativePeer_N_1MoveToFile(JNIEnv* env, jobject obj, jlong self, jstring fileName, jboolean caseSensitive) {
    auto* s = reinterpret_cast<cef_zip_reader_t*>(self);
    if (!s) return JNI_FALSE;
    auto _fileName_str = JStringToCefString(env, fileName);
    auto _r = s->move_to_file(s, _fileName_str, static_cast<bool>(caseSensitive));
    if (_fileName_str) cef_string_userfree_free(_fileName_str);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefZipReader_00024NativePeer_N_1Close(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_zip_reader_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->close(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefZipReader_00024NativePeer_N_1GetFileName(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_zip_reader_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_file_name(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jlong JNICALL Java_net_kurobako_cef4j_gen_CefZipReader_00024NativePeer_N_1GetFileSize(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_zip_reader_t*>(self);
    if (!s) return 0;
    return static_cast<jlong>(s->get_file_size(s));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefZipReader_00024NativePeer_N_1GetFileLastModified(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_zip_reader_t*>(self);
    if (!s) return nullptr;
    cef_basetime_t result = s->get_file_last_modified(s);
    auto cls = env->FindClass("net/kurobako/cef4j/gen/CefBasetime");
    auto ctor = env->GetMethodID(cls, "<init>", "(J)V");
    auto _dsResult = env->NewObject(cls, ctor, static_cast<jlong>((&result)->val));
    return _dsResult;
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefZipReader_00024NativePeer_N_1OpenFile(JNIEnv* env, jobject obj, jlong self, jstring password) {
    auto* s = reinterpret_cast<cef_zip_reader_t*>(self);
    if (!s) return JNI_FALSE;
    auto _password_str = password ? JStringToCefString(env, password) : nullptr;
    auto _r = s->open_file(s, _password_str);
    if (_password_str) cef_string_userfree_free(_password_str);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefZipReader_00024NativePeer_N_1CloseFile(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_zip_reader_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->close_file(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jint JNICALL Java_net_kurobako_cef4j_gen_CefZipReader_00024NativePeer_N_1ReadFile(JNIEnv* env, jobject obj, jlong self, jobject buffer) {
    auto* s = reinterpret_cast<cef_zip_reader_t*>(self);
    if (!s) return 0;
    if (!buffer) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "buffer must not be null"); return 0;}
    void* _buffer_addr = buffer ? env->GetDirectBufferAddress(buffer) : nullptr;
    if (buffer && !_buffer_addr) {env->ThrowNew(env->FindClass("java/lang/IllegalArgumentException"), "buffer must be a direct ByteBuffer; use ByteBuffer.allocateDirect(...)"); return 0;}
    return static_cast<jint>(s->read_file(s, _buffer_addr, static_cast<size_t>(env->GetDirectBufferCapacity(buffer))));
}

extern "C" JNIEXPORT jlong JNICALL Java_net_kurobako_cef4j_gen_CefZipReader_00024NativePeer_N_1Tell(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_zip_reader_t*>(self);
    if (!s) return 0;
    return static_cast<jlong>(s->tell(s));
}

extern "C" JNIEXPORT jint JNICALL Java_net_kurobako_cef4j_gen_CefZipReader_00024NativePeer_N_1Eof(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_zip_reader_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->eof(s));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefZipReader_00024NativePeer_N_1Create(JNIEnv* env, jclass clz, jobject stream) {
    cef_stream_reader_t* _stream_ptr = stream ? reinterpret_cast<cef_stream_reader_t*>(env->GetLongField(stream, env->GetFieldID(env->GetObjectClass(stream), "nativePtr", "J"))) : nullptr;
    if (_stream_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_stream_ptr); _b->add_ref(_b);}
    auto _r = cef_zip_reader_create(_stream_ptr);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefZipReader$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}
