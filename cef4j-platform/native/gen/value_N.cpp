// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
#include <jni.h>
#include "include/capi/cef_values_capi.h"
#include "include/capi/cef_parser_capi.h"
#include "jni_util.h"

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefValue), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefValue), isValid0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_valid(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefValue), isOwned0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_owned(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefValue), isReadOnly0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_read_only(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefValue), isSame0)(JNIEnv* env, jobject obj, jlong self, jobject that) {
    auto* s = reinterpret_cast<cef_value_t*>(self);
    if (!s) return JNI_FALSE;
    cef_value_t* _that_ptr = that ? reinterpret_cast<cef_value_t*>(env->GetLongField(that, env->GetFieldID(env->GetObjectClass(that), "nativePtr", "J"))) : nullptr;
    if (_that_ptr) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_that_ptr); _b->add_ref(_b); }
    auto _r = s->is_same(s, _that_ptr);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefValue), isEqual0)(JNIEnv* env, jobject obj, jlong self, jobject that) {
    auto* s = reinterpret_cast<cef_value_t*>(self);
    if (!s) return JNI_FALSE;
    cef_value_t* _that_ptr = that ? reinterpret_cast<cef_value_t*>(env->GetLongField(that, env->GetFieldID(env->GetObjectClass(that), "nativePtr", "J"))) : nullptr;
    if (_that_ptr) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_that_ptr); _b->add_ref(_b); }
    auto _r = s->is_equal(s, _that_ptr);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefValue), copy0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_value_t*>(self);
    if (!s) return nullptr;
    auto _r = s->copy(s);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefValue$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefValue), getType0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_value_t*>(self);
    if (!s) return 0;
    auto _r = s->get_type(s);
    auto _eCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefValueType");
    auto _eOf = env->GetStaticMethodID(_eCls, "of", "(J)Lnet/kurobako/cef4j/gen/CefValueType;");
    return env->CallStaticObjectMethod(_eCls, _eOf, static_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefValue), getBool0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->get_bool(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jint, CEF4J_PEER(CefValue), getInt0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_value_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->get_int(s));
}

CEF4J_JNI_EXPORT(jdouble, CEF4J_PEER(CefValue), getDouble0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_value_t*>(self);
    if (!s) return 0;
    return static_cast<jdouble>(s->get_double(s));
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefValue), getString0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_value_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_string(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefValue), getBinary0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_value_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_binary(s);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefBinaryValue$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefValue), getDictionary0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_value_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_dictionary(s);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefDictionaryValue$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefValue), getList0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_value_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_list(s);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefListValue$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefValue), setNull0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->set_null(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefValue), setBool0)(JNIEnv* env, jobject obj, jlong self, jboolean value) {
    auto* s = reinterpret_cast<cef_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->set_bool(s, static_cast<bool>(value));
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefValue), setInt0)(JNIEnv* env, jobject obj, jlong self, jint value) {
    auto* s = reinterpret_cast<cef_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->set_int(s, value);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefValue), setDouble0)(JNIEnv* env, jobject obj, jlong self, jdouble value) {
    auto* s = reinterpret_cast<cef_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->set_double(s, value);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefValue), setString0)(JNIEnv* env, jobject obj, jlong self, jstring value) {
    auto* s = reinterpret_cast<cef_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _value_str = value ? JStringToCefString(env, value) : nullptr;
    auto _r = s->set_string(s, _value_str);
    if (_value_str) cef_string_userfree_free(_value_str);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefValue), setBinary0)(JNIEnv* env, jobject obj, jlong self, jobject value) {
    auto* s = reinterpret_cast<cef_value_t*>(self);
    if (!s) return JNI_FALSE;
    cef_binary_value_t* _value_ptr = value ? reinterpret_cast<cef_binary_value_t*>(env->GetLongField(value, env->GetFieldID(env->GetObjectClass(value), "nativePtr", "J"))) : nullptr;
    if (_value_ptr) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_value_ptr); _b->add_ref(_b); }
    auto _r = s->set_binary(s, _value_ptr);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefValue), setDictionary0)(JNIEnv* env, jobject obj, jlong self, jobject value) {
    auto* s = reinterpret_cast<cef_value_t*>(self);
    if (!s) return JNI_FALSE;
    cef_dictionary_value_t* _value_ptr = value ? reinterpret_cast<cef_dictionary_value_t*>(env->GetLongField(value, env->GetFieldID(env->GetObjectClass(value), "nativePtr", "J"))) : nullptr;
    if (_value_ptr) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_value_ptr); _b->add_ref(_b); }
    auto _r = s->set_dictionary(s, _value_ptr);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefValue), setList0)(JNIEnv* env, jobject obj, jlong self, jobject value) {
    auto* s = reinterpret_cast<cef_value_t*>(self);
    if (!s) return JNI_FALSE;
    cef_list_value_t* _value_ptr = value ? reinterpret_cast<cef_list_value_t*>(env->GetLongField(value, env->GetFieldID(env->GetObjectClass(value), "nativePtr", "J"))) : nullptr;
    if (_value_ptr) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_value_ptr); _b->add_ref(_b); }
    auto _r = s->set_list(s, _value_ptr);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefValue), parseJson0)(JNIEnv* env, jclass clz, jstring json_string, jobject options) {
    if (!options) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "options must not be null"); return nullptr; }
    auto _json_string_str = JStringToCefString(env, json_string);
    auto _r = cef_parse_json(_json_string_str, static_cast<cef_json_parser_options_t>(env->GetLongField(options, env->GetFieldID(env->GetObjectClass(options), "value", "J"))));
    if (_json_string_str) cef_string_userfree_free(_json_string_str);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefValue$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefValue), parseJsonBuffer0)(JNIEnv* env, jclass clz, jobject json, jobject options) {
    if (!json) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "json must not be null"); return nullptr; }
    if (!options) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "options must not be null"); return nullptr; }
    const void* _json_addr = json ? env->GetDirectBufferAddress(json) : nullptr;
    if (json && !_json_addr) { env->ThrowNew(FindClassCached(env, "java/lang/IllegalArgumentException"), "json must be a direct ByteBuffer; use ByteBuffer.allocateDirect(...)"); return nullptr; }
    auto _r = cef_parse_json_buffer(_json_addr, static_cast<size_t>(env->GetDirectBufferCapacity(json)), static_cast<cef_json_parser_options_t>(env->GetLongField(options, env->GetFieldID(env->GetObjectClass(options), "value", "J"))));
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefValue$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefValue), parseJsonandReturnError0)(JNIEnv* env, jclass clz, jstring json_string, jobject options, jstring error_msg_out) {
    if (!options) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "options must not be null"); return nullptr; }
    auto _json_string_str = JStringToCefString(env, json_string);
    auto _error_msg_out_str = JStringToCefString(env, error_msg_out);
    auto _r = cef_parse_jsonand_return_error(_json_string_str, static_cast<cef_json_parser_options_t>(env->GetLongField(options, env->GetFieldID(env->GetObjectClass(options), "value", "J"))), _error_msg_out_str);
    if (_json_string_str) cef_string_userfree_free(_json_string_str);
    if (_error_msg_out_str) cef_string_userfree_free(_error_msg_out_str);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefValue$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefValue), create0)(JNIEnv* env, jclass clz) {
    auto _r = cef_value_create();
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefValue$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}
