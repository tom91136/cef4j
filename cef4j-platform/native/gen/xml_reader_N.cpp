// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
#include <jni.h>
#include "include/capi/cef_xml_reader_capi.h"
#include "include/capi/cef_stream_capi.h"
#include "jni_util.h"

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefXmlReader), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefXmlReader), moveToNextNode0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_xml_reader_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->move_to_next_node(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefXmlReader), cefClose0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_xml_reader_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->close(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefXmlReader), hasError0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_xml_reader_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->has_error(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefXmlReader), getError0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_xml_reader_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_error(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefXmlReader), getType0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_xml_reader_t*>(self);
    if (!s) return 0;
    auto _r = s->get_type(s);
    auto _eCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefXmlNodeType");
    auto _eOf = env->GetStaticMethodID(_eCls, "of", "(J)Lnet/kurobako/cef4j/gen/CefXmlNodeType;");
    return env->CallStaticObjectMethod(_eCls, _eOf, static_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jint, CEF4J_PEER(CefXmlReader), getDepth0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_xml_reader_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->get_depth(s));
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefXmlReader), getLocalName0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_xml_reader_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_local_name(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefXmlReader), getPrefix0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_xml_reader_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_prefix(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefXmlReader), getQualifiedName0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_xml_reader_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_qualified_name(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefXmlReader), getNamespaceUri0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_xml_reader_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_namespace_uri(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefXmlReader), getBaseUri0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_xml_reader_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_base_uri(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefXmlReader), getXmlLang0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_xml_reader_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_xml_lang(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefXmlReader), isEmptyElement0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_xml_reader_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_empty_element(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefXmlReader), hasValue0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_xml_reader_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->has_value(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefXmlReader), getValue0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_xml_reader_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_value(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefXmlReader), hasAttributes0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_xml_reader_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->has_attributes(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jlong, CEF4J_PEER(CefXmlReader), getAttributeCount0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_xml_reader_t*>(self);
    if (!s) return 0;
    return static_cast<jlong>(s->get_attribute_count(s));
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefXmlReader), getAttributeByindex0)(JNIEnv* env, jobject obj, jlong self, jint index) {
    auto* s = reinterpret_cast<cef_xml_reader_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_attribute_byindex(s, index);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefXmlReader), getAttributeByqname0)(JNIEnv* env, jobject obj, jlong self, jstring qualifiedName) {
    auto* s = reinterpret_cast<cef_xml_reader_t*>(self);
    if (!s) return nullptr;
    auto _qualifiedName_str = JStringToCefString(env, qualifiedName);
    auto result = s->get_attribute_byqname(s, _qualifiedName_str);
    if (_qualifiedName_str) cef_string_userfree_free(_qualifiedName_str);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefXmlReader), getAttributeBylname0)(JNIEnv* env, jobject obj, jlong self, jstring localName, jstring namespaceURI) {
    auto* s = reinterpret_cast<cef_xml_reader_t*>(self);
    if (!s) return nullptr;
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

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefXmlReader), getInnerXml0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_xml_reader_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_inner_xml(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefXmlReader), getOuterXml0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_xml_reader_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_outer_xml(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jint, CEF4J_PEER(CefXmlReader), getLineNumber0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_xml_reader_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->get_line_number(s));
}

CEF4J_JNI_EXPORT(jint, CEF4J_PEER(CefXmlReader), moveToAttributeByindex0)(JNIEnv* env, jobject obj, jlong self, jint index) {
    auto* s = reinterpret_cast<cef_xml_reader_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->move_to_attribute_byindex(s, index));
}

CEF4J_JNI_EXPORT(jint, CEF4J_PEER(CefXmlReader), moveToAttributeByqname0)(JNIEnv* env, jobject obj, jlong self, jstring qualifiedName) {
    auto* s = reinterpret_cast<cef_xml_reader_t*>(self);
    if (!s) return 0;
    auto _qualifiedName_str = JStringToCefString(env, qualifiedName);
    return static_cast<jint>(s->move_to_attribute_byqname(s, _qualifiedName_str));
}

CEF4J_JNI_EXPORT(jint, CEF4J_PEER(CefXmlReader), moveToAttributeBylname0)(JNIEnv* env, jobject obj, jlong self, jstring localName, jstring namespaceURI) {
    auto* s = reinterpret_cast<cef_xml_reader_t*>(self);
    if (!s) return 0;
    auto _localName_str = JStringToCefString(env, localName);
    auto _namespaceURI_str = JStringToCefString(env, namespaceURI);
    return static_cast<jint>(s->move_to_attribute_bylname(s, _localName_str, _namespaceURI_str));
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefXmlReader), moveToFirstAttribute0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_xml_reader_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->move_to_first_attribute(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefXmlReader), moveToNextAttribute0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_xml_reader_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->move_to_next_attribute(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefXmlReader), moveToCarryingElement0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_xml_reader_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->move_to_carrying_element(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefXmlReader), create0)(JNIEnv* env, jclass clz, jobject stream, jobject encodingType, jstring URI) {
    if (!encodingType) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "encodingType must not be null"); return nullptr; }
    cef_stream_reader_t* _stream_ptr = stream ? reinterpret_cast<cef_stream_reader_t*>(env->GetLongField(stream, env->GetFieldID(env->GetObjectClass(stream), "nativePtr", "J"))) : nullptr;
    if (_stream_ptr) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_stream_ptr); _b->add_ref(_b); }
    auto _URI_str = JStringToCefString(env, URI);
    auto _r = cef_xml_reader_create(_stream_ptr, static_cast<cef_xml_encoding_type_t>(env->GetLongField(encodingType, env->GetFieldID(env->GetObjectClass(encodingType), "value", "J"))), _URI_str);
    if (_URI_str) cef_string_userfree_free(_URI_str);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefXmlReader$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}
