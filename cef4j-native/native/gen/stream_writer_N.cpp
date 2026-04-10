// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
#include <jni.h>
#include "include/capi/cef_stream_capi.h"
#include "jni_util.h"

extern "C" cef_write_handler_t* Create_JniCefWriteHandler(JNIEnv* env, jobject handler);
CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefStreamWriter), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

CEF4J_JNI_EXPORT(jlong, CEF4J_PEER(CefStreamWriter), write0)(JNIEnv* env, jobject obj, jlong self, jobject ptr, jlong n) {
    auto* s = reinterpret_cast<cef_stream_writer_t*>(self);
    if (!s) return 0;
    if (!ptr) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "ptr must not be null"); return 0; }
    const void* _ptr_addr = ptr ? env->GetDirectBufferAddress(ptr) : nullptr;
    if (ptr && !_ptr_addr) { env->ThrowNew(FindClassCached(env, "java/lang/IllegalArgumentException"), "ptr must be a direct ByteBuffer; use ByteBuffer.allocateDirect(...)"); return 0; }
    return static_cast<jlong>(s->write(s, _ptr_addr, static_cast<size_t>(env->GetDirectBufferCapacity(ptr)), n));
}

CEF4J_JNI_EXPORT(jint, CEF4J_PEER(CefStreamWriter), seek0)(JNIEnv* env, jobject obj, jlong self, jlong offset, jint whence) {
    auto* s = reinterpret_cast<cef_stream_writer_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->seek(s, offset, whence));
}

CEF4J_JNI_EXPORT(jlong, CEF4J_PEER(CefStreamWriter), tell0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_stream_writer_t*>(self);
    if (!s) return 0;
    return static_cast<jlong>(s->tell(s));
}

CEF4J_JNI_EXPORT(jint, CEF4J_PEER(CefStreamWriter), flush0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_stream_writer_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->flush(s));
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefStreamWriter), mayBlock0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_stream_writer_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->may_block(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefStreamWriter), createForFile0)(JNIEnv* env, jclass clz, jstring fileName) {
    auto _fileName_str = JStringToCefString(env, fileName);
    auto _r = cef_stream_writer_create_for_file(_fileName_str);
    if (_fileName_str) cef_string_userfree_free(_fileName_str);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefStreamWriter$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefStreamWriter), createForHandler0)(JNIEnv* env, jclass clz, jobject handler) {
    cef_write_handler_t* _handler_ptr = handler ? Create_JniCefWriteHandler(env, handler) : nullptr;
    auto _r = cef_stream_writer_create_for_handler(_handler_ptr);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefStreamWriter$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}
