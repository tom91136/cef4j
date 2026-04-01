// GENERATED - do not edit.
#include <jni.h>
#include "include/capi/cef_xml_reader_capi.h"
#include "include/capi/cef_stream_capi.h"
#include "jni_util.h"

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefXmlReader_00024NativePeer_N_1Release(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefXmlReader_00024NativePeer_N_1MoveToNextNode(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_xml_reader_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->move_to_next_node(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefXmlReader_00024NativePeer_N_1Close(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_xml_reader_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->close(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefXmlReader_00024NativePeer_N_1HasError(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_xml_reader_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->has_error(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefXmlReader_00024NativePeer_N_1GetError(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_xml_reader_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_error(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefXmlReader_00024NativePeer_N_1GetType(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_xml_reader_t*>(self);
    if (!s) return 0;
    auto _r = s->get_type(s);
    auto _eCls = env->FindClass("net/kurobako/cef4j/gen/CefXmlNodeType");
    auto _eOf = env->GetStaticMethodID(_eCls, "of", "(J)Lnet/kurobako/cef4j/gen/CefXmlNodeType;");
    return env->CallStaticObjectMethod(_eCls, _eOf, static_cast<jlong>(_r));
}

extern "C" JNIEXPORT jint JNICALL Java_net_kurobako_cef4j_gen_CefXmlReader_00024NativePeer_N_1GetDepth(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_xml_reader_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->get_depth(s));
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefXmlReader_00024NativePeer_N_1GetLocalName(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_xml_reader_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_local_name(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefXmlReader_00024NativePeer_N_1GetPrefix(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_xml_reader_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_prefix(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefXmlReader_00024NativePeer_N_1GetQualifiedName(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_xml_reader_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_qualified_name(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefXmlReader_00024NativePeer_N_1GetNamespaceUri(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_xml_reader_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_namespace_uri(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefXmlReader_00024NativePeer_N_1GetBaseUri(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_xml_reader_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_base_uri(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefXmlReader_00024NativePeer_N_1GetXmlLang(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_xml_reader_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_xml_lang(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefXmlReader_00024NativePeer_N_1IsEmptyElement(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_xml_reader_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_empty_element(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefXmlReader_00024NativePeer_N_1HasValue(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_xml_reader_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->has_value(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefXmlReader_00024NativePeer_N_1GetValue(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_xml_reader_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_value(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefXmlReader_00024NativePeer_N_1HasAttributes(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_xml_reader_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->has_attributes(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jlong JNICALL Java_net_kurobako_cef4j_gen_CefXmlReader_00024NativePeer_N_1GetAttributeCount(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_xml_reader_t*>(self);
    if (!s) return 0;
    return static_cast<jlong>(s->get_attribute_count(s));
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefXmlReader_00024NativePeer_N_1GetAttributeByindex(JNIEnv* env, jobject obj, jlong self, jint index) {
    auto* s = reinterpret_cast<cef_xml_reader_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_attribute_byindex(s, index);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefXmlReader_00024NativePeer_N_1GetAttributeByqname(JNIEnv* env, jobject obj, jlong self, jstring qualifiedName) {
    auto* s = reinterpret_cast<cef_xml_reader_t*>(self);
    if (!s) return nullptr;
    if (!qualifiedName) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "qualifiedname must not be null"); return nullptr;}
    auto _qualifiedName_str = JStringToCefString(env, qualifiedName);
    auto result = s->get_attribute_byqname(s, _qualifiedName_str);
    if (_qualifiedName_str) cef_string_userfree_free(_qualifiedName_str);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefXmlReader_00024NativePeer_N_1GetAttributeBylname(JNIEnv* env, jobject obj, jlong self, jstring localName, jstring namespaceURI) {
    auto* s = reinterpret_cast<cef_xml_reader_t*>(self);
    if (!s) return nullptr;
    if (!localName) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "localname must not be null"); return nullptr;}
    if (!namespaceURI) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "namespaceuri must not be null"); return nullptr;}
    auto _localName_str = JStringToCefString(env, localName);
    auto _namespaceURI_str = JStringToCefString(env, namespaceURI);
    auto result = s->get_attribute_bylname(s, _localName_str, _namespaceURI_str);
    if (_localName_str) cef_string_userfree_free(_localName_str);
    if (_namespaceURI_str) cef_string_userfree_free(_namespaceURI_str);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefXmlReader_00024NativePeer_N_1GetInnerXml(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_xml_reader_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_inner_xml(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefXmlReader_00024NativePeer_N_1GetOuterXml(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_xml_reader_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_outer_xml(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jint JNICALL Java_net_kurobako_cef4j_gen_CefXmlReader_00024NativePeer_N_1GetLineNumber(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_xml_reader_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->get_line_number(s));
}

extern "C" JNIEXPORT jint JNICALL Java_net_kurobako_cef4j_gen_CefXmlReader_00024NativePeer_N_1MoveToAttributeByindex(JNIEnv* env, jobject obj, jlong self, jint index) {
    auto* s = reinterpret_cast<cef_xml_reader_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->move_to_attribute_byindex(s, index));
}

extern "C" JNIEXPORT jint JNICALL Java_net_kurobako_cef4j_gen_CefXmlReader_00024NativePeer_N_1MoveToAttributeByqname(JNIEnv* env, jobject obj, jlong self, jstring qualifiedName) {
    auto* s = reinterpret_cast<cef_xml_reader_t*>(self);
    if (!s) return 0;
    if (!qualifiedName) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "qualifiedname must not be null"); return 0;}
    auto _qualifiedName_str = JStringToCefString(env, qualifiedName);
    return static_cast<jint>(s->move_to_attribute_byqname(s, _qualifiedName_str));
}

extern "C" JNIEXPORT jint JNICALL Java_net_kurobako_cef4j_gen_CefXmlReader_00024NativePeer_N_1MoveToAttributeBylname(JNIEnv* env, jobject obj, jlong self, jstring localName, jstring namespaceURI) {
    auto* s = reinterpret_cast<cef_xml_reader_t*>(self);
    if (!s) return 0;
    if (!localName) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "localname must not be null"); return 0;}
    if (!namespaceURI) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "namespaceuri must not be null"); return 0;}
    auto _localName_str = JStringToCefString(env, localName);
    auto _namespaceURI_str = JStringToCefString(env, namespaceURI);
    return static_cast<jint>(s->move_to_attribute_bylname(s, _localName_str, _namespaceURI_str));
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefXmlReader_00024NativePeer_N_1MoveToFirstAttribute(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_xml_reader_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->move_to_first_attribute(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefXmlReader_00024NativePeer_N_1MoveToNextAttribute(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_xml_reader_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->move_to_next_attribute(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefXmlReader_00024NativePeer_N_1MoveToCarryingElement(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_xml_reader_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->move_to_carrying_element(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefXmlReader_00024NativePeer_N_1Create(JNIEnv* env, jclass clz, jobject stream, jobject encodingType, jstring URI) {
    if (!stream) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "stream must not be null"); return nullptr;}
    if (!encodingType) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "encodingtype must not be null"); return nullptr;}
    if (!URI) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "uri must not be null"); return nullptr;}
    cef_stream_reader_t* _stream_ptr = reinterpret_cast<cef_stream_reader_t*>(env->GetLongField(stream, env->GetFieldID(env->GetObjectClass(stream), "nativePtr", "J")));
    if (_stream_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_stream_ptr); _b->add_ref(_b);}
    auto _URI_str = JStringToCefString(env, URI);
    auto _r = cef_xml_reader_create(_stream_ptr, static_cast<cef_xml_encoding_type_t>(env->GetLongField(encodingType, env->GetFieldID(env->GetObjectClass(encodingType), "value", "J"))), _URI_str);
    if (_URI_str) cef_string_userfree_free(_URI_str);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefXmlReader$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}
