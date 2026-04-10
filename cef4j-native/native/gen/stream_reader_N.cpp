// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
#include <jni.h>
#include "include/capi/cef_stream_capi.h"
#include "jni_util.h"

extern "C" cef_read_handler_t* Create_JniCefReadHandler(JNIEnv* env, jobject handler);
CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefStreamReader), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

CEF4J_JNI_EXPORT(jlong, CEF4J_PEER(CefStreamReader), read0)(JNIEnv* env, jobject obj, jlong self, jobject ptr, jlong n) {
    auto* s = reinterpret_cast<cef_stream_reader_t*>(self);
    if (!s) return 0;
    if (!ptr) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "ptr must not be null"); return 0; }
    void* _ptr_addr = ptr ? env->GetDirectBufferAddress(ptr) : nullptr;
    if (ptr && !_ptr_addr) { env->ThrowNew(FindClassCached(env, "java/lang/IllegalArgumentException"), "ptr must be a direct ByteBuffer; use ByteBuffer.allocateDirect(...)"); return 0; }
    return static_cast<jlong>(s->read(s, _ptr_addr, static_cast<size_t>(env->GetDirectBufferCapacity(ptr)), n));
}

CEF4J_JNI_EXPORT(jint, CEF4J_PEER(CefStreamReader), seek0)(JNIEnv* env, jobject obj, jlong self, jlong offset, jint whence) {
    auto* s = reinterpret_cast<cef_stream_reader_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->seek(s, offset, whence));
}

CEF4J_JNI_EXPORT(jlong, CEF4J_PEER(CefStreamReader), tell0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_stream_reader_t*>(self);
    if (!s) return 0;
    return static_cast<jlong>(s->tell(s));
}

CEF4J_JNI_EXPORT(jint, CEF4J_PEER(CefStreamReader), eof0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_stream_reader_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->eof(s));
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefStreamReader), mayBlock0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_stream_reader_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->may_block(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefStreamReader), createForFile0)(JNIEnv* env, jclass clz, jstring fileName) {
    auto _fileName_str = JStringToCefString(env, fileName);
    auto _r = cef_stream_reader_create_for_file(_fileName_str);
    if (_fileName_str) cef_string_userfree_free(_fileName_str);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefStreamReader$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefStreamReader), createForData0)(JNIEnv* env, jclass clz, jobject data) {
    if (!data) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "data must not be null"); return nullptr; }
    void* _data_addr = data ? env->GetDirectBufferAddress(data) : nullptr;
    if (data && !_data_addr) { env->ThrowNew(FindClassCached(env, "java/lang/IllegalArgumentException"), "data must be a direct ByteBuffer; use ByteBuffer.allocateDirect(...)"); return nullptr; }
    auto _r = cef_stream_reader_create_for_data(_data_addr, static_cast<size_t>(env->GetDirectBufferCapacity(data)));
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefStreamReader$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefStreamReader), createForHandler0)(JNIEnv* env, jclass clz, jobject handler) {
    cef_read_handler_t* _handler_ptr = handler ? Create_JniCefReadHandler(env, handler) : nullptr;
    auto _r = cef_stream_reader_create_for_handler(_handler_ptr);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefStreamReader$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}
