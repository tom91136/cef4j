// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
#include <jni.h>
#include "include/capi/cef_values_capi.h"
#include "jni_util.h"

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefListValue), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefListValue), isValid0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_list_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_valid(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefListValue), isOwned0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_list_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_owned(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefListValue), isReadOnly0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_list_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_read_only(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefListValue), isSame0)(JNIEnv* env, jobject obj, jlong self, jobject that) {
    auto* s = reinterpret_cast<cef_list_value_t*>(self);
    if (!s) return JNI_FALSE;
    cef_list_value_t* _that_ptr = that ? reinterpret_cast<cef_list_value_t*>(env->GetLongField(that, env->GetFieldID(env->GetObjectClass(that), "nativePtr", "J"))) : nullptr;
    if (_that_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_that_ptr); _b->add_ref(_b);}
    auto _r = s->is_same(s, _that_ptr);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefListValue), isEqual0)(JNIEnv* env, jobject obj, jlong self, jobject that) {
    auto* s = reinterpret_cast<cef_list_value_t*>(self);
    if (!s) return JNI_FALSE;
    cef_list_value_t* _that_ptr = that ? reinterpret_cast<cef_list_value_t*>(env->GetLongField(that, env->GetFieldID(env->GetObjectClass(that), "nativePtr", "J"))) : nullptr;
    if (_that_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_that_ptr); _b->add_ref(_b);}
    auto _r = s->is_equal(s, _that_ptr);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefListValue), copy0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_list_value_t*>(self);
    if (!s) return nullptr;
    auto _r = s->copy(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefListValue$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefListValue), setSize0)(JNIEnv* env, jobject obj, jlong self, jlong size) {
    auto* s = reinterpret_cast<cef_list_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->set_size(s, size);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jlong, CEF4J_PEER(CefListValue), getSize0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_list_value_t*>(self);
    if (!s) return 0;
    return static_cast<jlong>(s->get_size(s));
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefListValue), clear0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_list_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->clear(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefListValue), remove0)(JNIEnv* env, jobject obj, jlong self, jlong index) {
    auto* s = reinterpret_cast<cef_list_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->remove(s, index);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefListValue), getType0)(JNIEnv* env, jobject obj, jlong self, jlong index) {
    auto* s = reinterpret_cast<cef_list_value_t*>(self);
    if (!s) return 0;
    auto _r = s->get_type(s, index);
    auto _eCls = env->FindClass("net/kurobako/cef4j/gen/CefValueType");
    auto _eOf = env->GetStaticMethodID(_eCls, "of", "(J)Lnet/kurobako/cef4j/gen/CefValueType;");
    return env->CallStaticObjectMethod(_eCls, _eOf, static_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefListValue), getValue0)(JNIEnv* env, jobject obj, jlong self, jlong index) {
    auto* s = reinterpret_cast<cef_list_value_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_value(s, index);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefValue$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefListValue), getBool0)(JNIEnv* env, jobject obj, jlong self, jlong index) {
    auto* s = reinterpret_cast<cef_list_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->get_bool(s, index);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jint, CEF4J_PEER(CefListValue), getInt0)(JNIEnv* env, jobject obj, jlong self, jlong index) {
    auto* s = reinterpret_cast<cef_list_value_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->get_int(s, index));
}

CEF4J_JNI_EXPORT(jdouble, CEF4J_PEER(CefListValue), getDouble0)(JNIEnv* env, jobject obj, jlong self, jlong index) {
    auto* s = reinterpret_cast<cef_list_value_t*>(self);
    if (!s) return 0;
    return static_cast<jdouble>(s->get_double(s, index));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefListValue), getDictionary0)(JNIEnv* env, jobject obj, jlong self, jlong index) {
    auto* s = reinterpret_cast<cef_list_value_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_dictionary(s, index);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefDictionaryValue$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefListValue), setValue0)(JNIEnv* env, jobject obj, jlong self, jlong index, jobject value) {
    auto* s = reinterpret_cast<cef_list_value_t*>(self);
    if (!s) return JNI_FALSE;
    cef_value_t* _value_ptr = value ? reinterpret_cast<cef_value_t*>(env->GetLongField(value, env->GetFieldID(env->GetObjectClass(value), "nativePtr", "J"))) : nullptr;
    if (_value_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_value_ptr); _b->add_ref(_b);}
    auto _r = s->set_value(s, index, _value_ptr);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefListValue), setNull0)(JNIEnv* env, jobject obj, jlong self, jlong index) {
    auto* s = reinterpret_cast<cef_list_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->set_null(s, index);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefListValue), setBool0)(JNIEnv* env, jobject obj, jlong self, jlong index, jboolean value) {
    auto* s = reinterpret_cast<cef_list_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->set_bool(s, index, static_cast<bool>(value));
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefListValue), setInt0)(JNIEnv* env, jobject obj, jlong self, jlong index, jint value) {
    auto* s = reinterpret_cast<cef_list_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->set_int(s, index, value);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefListValue), setDouble0)(JNIEnv* env, jobject obj, jlong self, jlong index, jdouble value) {
    auto* s = reinterpret_cast<cef_list_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->set_double(s, index, value);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefListValue), setString0)(JNIEnv* env, jobject obj, jlong self, jlong index, jstring value) {
    auto* s = reinterpret_cast<cef_list_value_t*>(self);
    if (!s) return JNI_FALSE;
    auto _value_str = value ? JStringToCefString(env, value) : nullptr;
    auto _r = s->set_string(s, index, _value_str);
    if (_value_str) cef_string_userfree_free(_value_str);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefListValue), setBinary0)(JNIEnv* env, jobject obj, jlong self, jlong index, jobject value) {
    auto* s = reinterpret_cast<cef_list_value_t*>(self);
    if (!s) return JNI_FALSE;
    cef_binary_value_t* _value_ptr = value ? reinterpret_cast<cef_binary_value_t*>(env->GetLongField(value, env->GetFieldID(env->GetObjectClass(value), "nativePtr", "J"))) : nullptr;
    if (_value_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_value_ptr); _b->add_ref(_b);}
    auto _r = s->set_binary(s, index, _value_ptr);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefListValue), setDictionary0)(JNIEnv* env, jobject obj, jlong self, jlong index, jobject value) {
    auto* s = reinterpret_cast<cef_list_value_t*>(self);
    if (!s) return JNI_FALSE;
    cef_dictionary_value_t* _value_ptr = value ? reinterpret_cast<cef_dictionary_value_t*>(env->GetLongField(value, env->GetFieldID(env->GetObjectClass(value), "nativePtr", "J"))) : nullptr;
    if (_value_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_value_ptr); _b->add_ref(_b);}
    auto _r = s->set_dictionary(s, index, _value_ptr);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefListValue), setList0)(JNIEnv* env, jobject obj, jlong self, jlong index, jobject value) {
    auto* s = reinterpret_cast<cef_list_value_t*>(self);
    if (!s) return JNI_FALSE;
    cef_list_value_t* _value_ptr = value ? reinterpret_cast<cef_list_value_t*>(env->GetLongField(value, env->GetFieldID(env->GetObjectClass(value), "nativePtr", "J"))) : nullptr;
    if (_value_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_value_ptr); _b->add_ref(_b);}
    auto _r = s->set_list(s, index, _value_ptr);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefListValue), create0)(JNIEnv* env, jclass clz) {
    auto _r = cef_list_value_create();
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefListValue$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}
