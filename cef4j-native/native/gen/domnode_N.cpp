// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
#include <jni.h>
#include "include/capi/cef_dom_capi.h"
#include "jni_util.h"

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefDomNode), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefDomNode), getType0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_domnode_t*>(self);
    if (!s) return 0;
    auto _r = s->get_type(s);
    auto _eCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefDomNodeType");
    auto _eOf = env->GetStaticMethodID(_eCls, "of", "(J)Lnet/kurobako/cef4j/gen/CefDomNodeType;");
    return env->CallStaticObjectMethod(_eCls, _eOf, static_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefDomNode), isText0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_domnode_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_text(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefDomNode), isElement0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_domnode_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_element(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefDomNode), isEditable0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_domnode_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_editable(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefDomNode), isFormControlElement0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_domnode_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_form_control_element(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefDomNode), getFormControlElementType0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_domnode_t*>(self);
    if (!s) return 0;
    auto _r = s->get_form_control_element_type(s);
    auto _eCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefDomFormControlType");
    auto _eOf = env->GetStaticMethodID(_eCls, "of", "(J)Lnet/kurobako/cef4j/gen/CefDomFormControlType;");
    return env->CallStaticObjectMethod(_eCls, _eOf, static_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefDomNode), isSame0)(JNIEnv* env, jobject obj, jlong self, jobject that) {
    auto* s = reinterpret_cast<cef_domnode_t*>(self);
    if (!s) return JNI_FALSE;
    cef_domnode_t* _that_ptr = that ? reinterpret_cast<cef_domnode_t*>(env->GetLongField(that, env->GetFieldID(env->GetObjectClass(that), "nativePtr", "J"))) : nullptr;
    if (_that_ptr) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_that_ptr); _b->add_ref(_b); }
    auto _r = s->is_same(s, _that_ptr);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefDomNode), getName0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_domnode_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_name(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefDomNode), getValue0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_domnode_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_value(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefDomNode), setValue0)(JNIEnv* env, jobject obj, jlong self, jstring value) {
    auto* s = reinterpret_cast<cef_domnode_t*>(self);
    if (!s) return JNI_FALSE;
    auto _value_str = JStringToCefString(env, value);
    auto _r = s->set_value(s, _value_str);
    if (_value_str) cef_string_userfree_free(_value_str);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefDomNode), getAsMarkup0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_domnode_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_as_markup(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefDomNode), getDocument0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_domnode_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_document(s);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefDomDocument$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefDomNode), getParent0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_domnode_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_parent(s);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefDomNode$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefDomNode), getPreviousSibling0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_domnode_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_previous_sibling(s);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefDomNode$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefDomNode), getNextSibling0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_domnode_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_next_sibling(s);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefDomNode$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefDomNode), hasChildren0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_domnode_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->has_children(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefDomNode), getFirstChild0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_domnode_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_first_child(s);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefDomNode$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefDomNode), getLastChild0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_domnode_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_last_child(s);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefDomNode$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefDomNode), getElementTagName0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_domnode_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_element_tag_name(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefDomNode), hasElementAttributes0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_domnode_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->has_element_attributes(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefDomNode), hasElementAttribute0)(JNIEnv* env, jobject obj, jlong self, jstring attrName) {
    auto* s = reinterpret_cast<cef_domnode_t*>(self);
    if (!s) return JNI_FALSE;
    auto _attrName_str = JStringToCefString(env, attrName);
    auto _r = s->has_element_attribute(s, _attrName_str);
    if (_attrName_str) cef_string_userfree_free(_attrName_str);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefDomNode), getElementAttribute0)(JNIEnv* env, jobject obj, jlong self, jstring attrName) {
    auto* s = reinterpret_cast<cef_domnode_t*>(self);
    if (!s) return nullptr;
    auto _attrName_str = JStringToCefString(env, attrName);
    auto result = s->get_element_attribute(s, _attrName_str);
    if (_attrName_str) cef_string_userfree_free(_attrName_str);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefDomNode), getElementAttributes0)(JNIEnv* env, jobject obj, jlong self, jobject attrMap) {
    auto* s = reinterpret_cast<cef_domnode_t*>(self);
    if (!s) return;
    if (!attrMap) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "attrMap must not be null"); return; }
    auto _attrMap_csm = JavaMapToCefStringMap(env, attrMap);
    s->get_element_attributes(s, _attrMap_csm);
    CefStringMapWriteBack(env, _attrMap_csm, attrMap);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefDomNode), setElementAttribute0)(JNIEnv* env, jobject obj, jlong self, jstring attrName, jstring value) {
    auto* s = reinterpret_cast<cef_domnode_t*>(self);
    if (!s) return JNI_FALSE;
    auto _attrName_str = JStringToCefString(env, attrName);
    auto _value_str = JStringToCefString(env, value);
    auto _r = s->set_element_attribute(s, _attrName_str, _value_str);
    if (_attrName_str) cef_string_userfree_free(_attrName_str);
    if (_value_str) cef_string_userfree_free(_value_str);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefDomNode), getElementInnerText0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_domnode_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_element_inner_text(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefDomNode), getElementBounds0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_domnode_t*>(self);
    if (!s) return nullptr;
    cef_rect_t result = s->get_element_bounds(s);
    auto cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefRect");
    auto ctor = env->GetMethodID(cls, "<init>", "(IIII)V");
    auto _dsResult = env->NewObject(cls, ctor, static_cast<jint>(((&result))->x), static_cast<jint>(((&result))->y), static_cast<jint>(((&result))->width), static_cast<jint>(((&result))->height));
    return _dsResult;
}
