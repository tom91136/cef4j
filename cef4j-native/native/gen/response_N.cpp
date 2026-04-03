// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
#include <jni.h>
#include "include/capi/cef_response_capi.h"
#include "jni_util.h"

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefResponse), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefResponse), isReadOnly0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_response_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_read_only(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefResponse), getError0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_response_t*>(self);
    if (!s) return 0;
    auto _r = s->get_error(s);
    auto _eCls = env->FindClass("net/kurobako/cef4j/gen/CefErrorCode");
    auto _eOf = env->GetStaticMethodID(_eCls, "of", "(J)Lnet/kurobako/cef4j/gen/CefErrorCode;");
    return env->CallStaticObjectMethod(_eCls, _eOf, static_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefResponse), setError0)(JNIEnv* env, jobject obj, jlong self, jobject error) {
    auto* s = reinterpret_cast<cef_response_t*>(self);
    if (!s) return;
    if (!error) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "error must not be null"); return;}
    s->set_error(s, static_cast<cef_errorcode_t>(env->GetLongField(error, env->GetFieldID(env->GetObjectClass(error), "value", "J"))));
}

CEF4J_JNI_EXPORT(jint, CEF4J_PEER(CefResponse), getStatus0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_response_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->get_status(s));
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefResponse), setStatus0)(JNIEnv* env, jobject obj, jlong self, jint status) {
    auto* s = reinterpret_cast<cef_response_t*>(self);
    if (!s) return;
    s->set_status(s, status);
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefResponse), getStatusText0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_response_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_status_text(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefResponse), setStatusText0)(JNIEnv* env, jobject obj, jlong self, jstring statusText) {
    auto* s = reinterpret_cast<cef_response_t*>(self);
    if (!s) return;
    auto _statusText_str = statusText ? JStringToCefString(env, statusText) : nullptr;
    s->set_status_text(s, _statusText_str);
    if (_statusText_str) cef_string_userfree_free(_statusText_str);
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefResponse), getMimeType0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_response_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_mime_type(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefResponse), setMimeType0)(JNIEnv* env, jobject obj, jlong self, jstring mimeType) {
    auto* s = reinterpret_cast<cef_response_t*>(self);
    if (!s) return;
    auto _mimeType_str = mimeType ? JStringToCefString(env, mimeType) : nullptr;
    s->set_mime_type(s, _mimeType_str);
    if (_mimeType_str) cef_string_userfree_free(_mimeType_str);
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefResponse), getCharset0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_response_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_charset(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefResponse), setCharset0)(JNIEnv* env, jobject obj, jlong self, jstring charset) {
    auto* s = reinterpret_cast<cef_response_t*>(self);
    if (!s) return;
    auto _charset_str = charset ? JStringToCefString(env, charset) : nullptr;
    s->set_charset(s, _charset_str);
    if (_charset_str) cef_string_userfree_free(_charset_str);
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefResponse), getHeaderByName0)(JNIEnv* env, jobject obj, jlong self, jstring name) {
    auto* s = reinterpret_cast<cef_response_t*>(self);
    if (!s) return nullptr;
    auto _name_str = JStringToCefString(env, name);
    auto result = s->get_header_by_name(s, _name_str);
    if (_name_str) cef_string_userfree_free(_name_str);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefResponse), setHeaderByName0)(JNIEnv* env, jobject obj, jlong self, jstring name, jstring value, jboolean overwrite) {
    auto* s = reinterpret_cast<cef_response_t*>(self);
    if (!s) return;
    auto _name_str = JStringToCefString(env, name);
    auto _value_str = value ? JStringToCefString(env, value) : nullptr;
    s->set_header_by_name(s, _name_str, _value_str, static_cast<bool>(overwrite));
    if (_name_str) cef_string_userfree_free(_name_str);
    if (_value_str) cef_string_userfree_free(_value_str);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefResponse), getHeaderMap0)(JNIEnv* env, jobject obj, jlong self, jobject headerMap) {
    auto* s = reinterpret_cast<cef_response_t*>(self);
    if (!s) return;
    if (!headerMap) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "headerMap must not be null"); return;}
    auto _headerMap_csmm = JavaMapToCefStringMultimap(env, headerMap);
    s->get_header_map(s, _headerMap_csmm);
    CefStringMultimapWriteBack(env, _headerMap_csmm, headerMap);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefResponse), setHeaderMap0)(JNIEnv* env, jobject obj, jlong self, jobject headerMap) {
    auto* s = reinterpret_cast<cef_response_t*>(self);
    if (!s) return;
    if (!headerMap) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "headerMap must not be null"); return;}
    auto _headerMap_csmm = JavaMapToCefStringMultimap(env, headerMap);
    s->set_header_map(s, _headerMap_csmm);
    cef_string_multimap_free(_headerMap_csmm);
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefResponse), getUrl0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_response_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_url(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefResponse), setUrl0)(JNIEnv* env, jobject obj, jlong self, jstring url) {
    auto* s = reinterpret_cast<cef_response_t*>(self);
    if (!s) return;
    auto _url_str = url ? JStringToCefString(env, url) : nullptr;
    s->set_url(s, _url_str);
    if (_url_str) cef_string_userfree_free(_url_str);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefResponse), create0)(JNIEnv* env, jclass clz) {
    auto _r = cef_response_create();
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefResponse$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}
