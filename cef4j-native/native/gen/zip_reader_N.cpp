// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
#include <jni.h>
#include "include/capi/cef_zip_reader_capi.h"
#include "include/capi/cef_stream_capi.h"
#include "jni_util.h"

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefZipReader), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefZipReader), moveToFirstFile0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_zip_reader_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->move_to_first_file(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefZipReader), moveToNextFile0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_zip_reader_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->move_to_next_file(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefZipReader), moveToFile0)(JNIEnv* env, jobject obj, jlong self, jstring fileName, jboolean caseSensitive) {
    auto* s = reinterpret_cast<cef_zip_reader_t*>(self);
    if (!s) return JNI_FALSE;
    auto _fileName_str = JStringToCefString(env, fileName);
    auto _r = s->move_to_file(s, _fileName_str, static_cast<bool>(caseSensitive));
    if (_fileName_str) cef_string_userfree_free(_fileName_str);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefZipReader), cefClose0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_zip_reader_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->close(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefZipReader), getFileName0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_zip_reader_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_file_name(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jlong, CEF4J_PEER(CefZipReader), getFileSize0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_zip_reader_t*>(self);
    if (!s) return 0;
    return static_cast<jlong>(s->get_file_size(s));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefZipReader), getFileLastModified0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_zip_reader_t*>(self);
    if (!s) return nullptr;
    cef_basetime_t result = s->get_file_last_modified(s);
    auto cls = env->FindClass("net/kurobako/cef4j/gen/CefBasetime");
    auto ctor = env->GetMethodID(cls, "<init>", "(J)V");
    auto _dsResult = env->NewObject(cls, ctor, static_cast<jlong>((&result)->val));
    return _dsResult;
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefZipReader), openFile0)(JNIEnv* env, jobject obj, jlong self, jstring password) {
    auto* s = reinterpret_cast<cef_zip_reader_t*>(self);
    if (!s) return JNI_FALSE;
    auto _password_str = password ? JStringToCefString(env, password) : nullptr;
    auto _r = s->open_file(s, _password_str);
    if (_password_str) cef_string_userfree_free(_password_str);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefZipReader), closeFile0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_zip_reader_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->close_file(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jint, CEF4J_PEER(CefZipReader), readFile0)(JNIEnv* env, jobject obj, jlong self, jobject buffer) {
    auto* s = reinterpret_cast<cef_zip_reader_t*>(self);
    if (!s) return 0;
    if (!buffer) { env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "buffer must not be null"); return 0; }
    void* _buffer_addr = buffer ? env->GetDirectBufferAddress(buffer) : nullptr;
    if (buffer && !_buffer_addr) { env->ThrowNew(env->FindClass("java/lang/IllegalArgumentException"), "buffer must be a direct ByteBuffer; use ByteBuffer.allocateDirect(...)"); return 0; }
    return static_cast<jint>(s->read_file(s, _buffer_addr, static_cast<size_t>(env->GetDirectBufferCapacity(buffer))));
}

CEF4J_JNI_EXPORT(jlong, CEF4J_PEER(CefZipReader), tell0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_zip_reader_t*>(self);
    if (!s) return 0;
    return static_cast<jlong>(s->tell(s));
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefZipReader), eof0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_zip_reader_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->eof(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefZipReader), create0)(JNIEnv* env, jclass clz, jobject stream) {
    cef_stream_reader_t* _stream_ptr = stream ? reinterpret_cast<cef_stream_reader_t*>(env->GetLongField(stream, env->GetFieldID(env->GetObjectClass(stream), "nativePtr", "J"))) : nullptr;
    if (_stream_ptr) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_stream_ptr); _b->add_ref(_b); }
    auto _r = cef_zip_reader_create(_stream_ptr);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefZipReader$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}
