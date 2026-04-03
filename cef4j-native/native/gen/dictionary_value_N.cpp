// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
#include <jni.h>
#include "include/capi/cef_values_capi.h"
#include "jni_util.h"

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefDictionaryValue), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefDictionaryValue), isValid0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_dictionary_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_valid(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefDictionaryValue), isOwned0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_dictionary_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_owned(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefDictionaryValue), isReadOnly0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_dictionary_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_read_only(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefDictionaryValue), isSame0)(JNIEnv* env, jobject obj, jlong self, jobject that) {
    auto* s = reinterpret_cast<cef_dictionary_value_t*>(self);
    if (!s) return JNI_FALSE;
    cef_dictionary_value_t* _that_ptr = that ? reinterpret_cast<cef_dictionary_value_t*>(env->GetLongField(that, env->GetFieldID(env->GetObjectClass(that), "nativePtr", "J"))) : nullptr;
    if (_that_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_that_ptr); _b->add_ref(_b);}
    auto _r = s->is_same(s, _that_ptr);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefDictionaryValue), isEqual0)(JNIEnv* env, jobject obj, jlong self, jobject that) {
    auto* s = reinterpret_cast<cef_dictionary_value_t*>(self);
    if (!s) return JNI_FALSE;
    cef_dictionary_value_t* _that_ptr = that ? reinterpret_cast<cef_dictionary_value_t*>(env->GetLongField(that, env->GetFieldID(env->GetObjectClass(that), "nativePtr", "J"))) : nullptr;
    if (_that_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_that_ptr); _b->add_ref(_b);}
    auto _r = s->is_equal(s, _that_ptr);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefDictionaryValue), copy0)(JNIEnv* env, jobject obj, jlong self, jint exclude_empty_children) {
    auto* s = reinterpret_cast<cef_dictionary_value_t*>(self);
    if (!s) return nullptr;
    auto _r = s->copy(s, exclude_empty_children);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefDictionaryValue$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jlong, CEF4J_PEER(CefDictionaryValue), getSize0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_dictionary_value_t*>(self);
    if (!s) return 0;
    return static_cast<jlong>(s->get_size(s));
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefDictionaryValue), clear0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_dictionary_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->clear(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefDictionaryValue), hasKey0)(JNIEnv* env, jobject obj, jlong self, jstring key) {
    auto* s = reinterpret_cast<cef_dictionary_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _key_str = JStringToCefString(env, key);
    auto _r = s->has_key(s, _key_str);
    if (_key_str) cef_string_userfree_free(_key_str);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefDictionaryValue), getKeys0)(JNIEnv* env, jobject obj, jlong self, jobject keys) {
    auto* s = reinterpret_cast<cef_dictionary_value_t*>(self);
    if (!s) return JNI_FALSE;
    if (!keys) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "keys must not be null"); return JNI_FALSE;}
    auto _keys_csl = JavaListToCefStringList(env, keys);
    auto _r = s->get_keys(s, _keys_csl);
    CefStringListWriteBack(env, _keys_csl, keys);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefDictionaryValue), remove0)(JNIEnv* env, jobject obj, jlong self, jstring key) {
    auto* s = reinterpret_cast<cef_dictionary_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _key_str = JStringToCefString(env, key);
    auto _r = s->remove(s, _key_str);
    if (_key_str) cef_string_userfree_free(_key_str);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefDictionaryValue), getType0)(JNIEnv* env, jobject obj, jlong self, jstring key) {
    auto* s = reinterpret_cast<cef_dictionary_value_t*>(self);
    if (!s) return 0;
    auto _key_str = JStringToCefString(env, key);
    auto _r = s->get_type(s, _key_str);
    if (_key_str) cef_string_userfree_free(_key_str);
    auto _eCls = env->FindClass("net/kurobako/cef4j/gen/CefValueType");
    auto _eOf = env->GetStaticMethodID(_eCls, "of", "(J)Lnet/kurobako/cef4j/gen/CefValueType;");
    return env->CallStaticObjectMethod(_eCls, _eOf, static_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefDictionaryValue), getValue0)(JNIEnv* env, jobject obj, jlong self, jstring key) {
    auto* s = reinterpret_cast<cef_dictionary_value_t*>(self);
    if (!s) return nullptr;
    auto _key_str = JStringToCefString(env, key);
    auto _r = s->get_value(s, _key_str);
    if (_key_str) cef_string_userfree_free(_key_str);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefValue$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefDictionaryValue), getBool0)(JNIEnv* env, jobject obj, jlong self, jstring key) {
    auto* s = reinterpret_cast<cef_dictionary_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _key_str = JStringToCefString(env, key);
    auto _r = s->get_bool(s, _key_str);
    if (_key_str) cef_string_userfree_free(_key_str);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jint, CEF4J_PEER(CefDictionaryValue), getInt0)(JNIEnv* env, jobject obj, jlong self, jstring key) {
    auto* s = reinterpret_cast<cef_dictionary_value_t*>(self);
    if (!s) return 0;
    auto _key_str = JStringToCefString(env, key);
    return static_cast<jint>(s->get_int(s, _key_str));
}

CEF4J_JNI_EXPORT(jdouble, CEF4J_PEER(CefDictionaryValue), getDouble0)(JNIEnv* env, jobject obj, jlong self, jstring key) {
    auto* s = reinterpret_cast<cef_dictionary_value_t*>(self);
    if (!s) return 0;
    auto _key_str = JStringToCefString(env, key);
    return static_cast<jdouble>(s->get_double(s, _key_str));
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefDictionaryValue), getString0)(JNIEnv* env, jobject obj, jlong self, jstring key) {
    auto* s = reinterpret_cast<cef_dictionary_value_t*>(self);
    if (!s) return nullptr;
    auto _key_str = JStringToCefString(env, key);
    auto result = s->get_string(s, _key_str);
    if (_key_str) cef_string_userfree_free(_key_str);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefDictionaryValue), getBinary0)(JNIEnv* env, jobject obj, jlong self, jstring key) {
    auto* s = reinterpret_cast<cef_dictionary_value_t*>(self);
    if (!s) return nullptr;
    auto _key_str = JStringToCefString(env, key);
    auto _r = s->get_binary(s, _key_str);
    if (_key_str) cef_string_userfree_free(_key_str);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefBinaryValue$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefDictionaryValue), getDictionary0)(JNIEnv* env, jobject obj, jlong self, jstring key) {
    auto* s = reinterpret_cast<cef_dictionary_value_t*>(self);
    if (!s) return nullptr;
    auto _key_str = JStringToCefString(env, key);
    auto _r = s->get_dictionary(s, _key_str);
    if (_key_str) cef_string_userfree_free(_key_str);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefDictionaryValue$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefDictionaryValue), getList0)(JNIEnv* env, jobject obj, jlong self, jstring key) {
    auto* s = reinterpret_cast<cef_dictionary_value_t*>(self);
    if (!s) return nullptr;
    auto _key_str = JStringToCefString(env, key);
    auto _r = s->get_list(s, _key_str);
    if (_key_str) cef_string_userfree_free(_key_str);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefListValue$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefDictionaryValue), setValue0)(JNIEnv* env, jobject obj, jlong self, jstring key, jobject value) {
    auto* s = reinterpret_cast<cef_dictionary_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _key_str = JStringToCefString(env, key);
    cef_value_t* _value_ptr = value ? reinterpret_cast<cef_value_t*>(env->GetLongField(value, env->GetFieldID(env->GetObjectClass(value), "nativePtr", "J"))) : nullptr;
    if (_value_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_value_ptr); _b->add_ref(_b);}
    auto _r = s->set_value(s, _key_str, _value_ptr);
    if (_key_str) cef_string_userfree_free(_key_str);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefDictionaryValue), setNull0)(JNIEnv* env, jobject obj, jlong self, jstring key) {
    auto* s = reinterpret_cast<cef_dictionary_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _key_str = JStringToCefString(env, key);
    auto _r = s->set_null(s, _key_str);
    if (_key_str) cef_string_userfree_free(_key_str);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefDictionaryValue), setBool0)(JNIEnv* env, jobject obj, jlong self, jstring key, jboolean value) {
    auto* s = reinterpret_cast<cef_dictionary_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _key_str = JStringToCefString(env, key);
    auto _r = s->set_bool(s, _key_str, static_cast<bool>(value));
    if (_key_str) cef_string_userfree_free(_key_str);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefDictionaryValue), setInt0)(JNIEnv* env, jobject obj, jlong self, jstring key, jint value) {
    auto* s = reinterpret_cast<cef_dictionary_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _key_str = JStringToCefString(env, key);
    auto _r = s->set_int(s, _key_str, value);
    if (_key_str) cef_string_userfree_free(_key_str);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefDictionaryValue), setDouble0)(JNIEnv* env, jobject obj, jlong self, jstring key, jdouble value) {
    auto* s = reinterpret_cast<cef_dictionary_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _key_str = JStringToCefString(env, key);
    auto _r = s->set_double(s, _key_str, value);
    if (_key_str) cef_string_userfree_free(_key_str);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefDictionaryValue), setString0)(JNIEnv* env, jobject obj, jlong self, jstring key, jstring value) {
    auto* s = reinterpret_cast<cef_dictionary_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _key_str = JStringToCefString(env, key);
    auto _value_str = value ? JStringToCefString(env, value) : nullptr;
    auto _r = s->set_string(s, _key_str, _value_str);
    if (_key_str) cef_string_userfree_free(_key_str);
    if (_value_str) cef_string_userfree_free(_value_str);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefDictionaryValue), setBinary0)(JNIEnv* env, jobject obj, jlong self, jstring key, jobject value) {
    auto* s = reinterpret_cast<cef_dictionary_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _key_str = JStringToCefString(env, key);
    cef_binary_value_t* _value_ptr = value ? reinterpret_cast<cef_binary_value_t*>(env->GetLongField(value, env->GetFieldID(env->GetObjectClass(value), "nativePtr", "J"))) : nullptr;
    if (_value_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_value_ptr); _b->add_ref(_b);}
    auto _r = s->set_binary(s, _key_str, _value_ptr);
    if (_key_str) cef_string_userfree_free(_key_str);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefDictionaryValue), setDictionary0)(JNIEnv* env, jobject obj, jlong self, jstring key, jobject value) {
    auto* s = reinterpret_cast<cef_dictionary_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _key_str = JStringToCefString(env, key);
    cef_dictionary_value_t* _value_ptr = value ? reinterpret_cast<cef_dictionary_value_t*>(env->GetLongField(value, env->GetFieldID(env->GetObjectClass(value), "nativePtr", "J"))) : nullptr;
    if (_value_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_value_ptr); _b->add_ref(_b);}
    auto _r = s->set_dictionary(s, _key_str, _value_ptr);
    if (_key_str) cef_string_userfree_free(_key_str);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefDictionaryValue), setList0)(JNIEnv* env, jobject obj, jlong self, jstring key, jobject value) {
    auto* s = reinterpret_cast<cef_dictionary_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _key_str = JStringToCefString(env, key);
    cef_list_value_t* _value_ptr = value ? reinterpret_cast<cef_list_value_t*>(env->GetLongField(value, env->GetFieldID(env->GetObjectClass(value), "nativePtr", "J"))) : nullptr;
    if (_value_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_value_ptr); _b->add_ref(_b);}
    auto _r = s->set_list(s, _key_str, _value_ptr);
    if (_key_str) cef_string_userfree_free(_key_str);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefDictionaryValue), create0)(JNIEnv* env, jclass clz) {
    auto _r = cef_dictionary_value_create();
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefDictionaryValue$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}
