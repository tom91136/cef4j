// GENERATED - do not edit.
#include <jni.h>
#include "include/capi/cef_dom_capi.h"
#include "jni_util.h"

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefDomNode_00024NativePeer_N_1Release(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefDomNode_00024NativePeer_N_1GetType(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_domnode_t*>(self);
    if (!s) return 0;
    auto _r = s->get_type(s);
    auto _eCls = env->FindClass("net/kurobako/cef4j/gen/CefDomNodeType");
    auto _eOf = env->GetStaticMethodID(_eCls, "of", "(J)Lnet/kurobako/cef4j/gen/CefDomNodeType;");
    return env->CallStaticObjectMethod(_eCls, _eOf, static_cast<jlong>(_r));
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefDomNode_00024NativePeer_N_1IsText(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_domnode_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_text(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefDomNode_00024NativePeer_N_1IsElement(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_domnode_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_element(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefDomNode_00024NativePeer_N_1IsEditable(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_domnode_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_editable(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefDomNode_00024NativePeer_N_1IsFormControlElement(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_domnode_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_form_control_element(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefDomNode_00024NativePeer_N_1GetFormControlElementType(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_domnode_t*>(self);
    if (!s) return 0;
    auto _r = s->get_form_control_element_type(s);
    auto _eCls = env->FindClass("net/kurobako/cef4j/gen/CefDomFormControlType");
    auto _eOf = env->GetStaticMethodID(_eCls, "of", "(J)Lnet/kurobako/cef4j/gen/CefDomFormControlType;");
    return env->CallStaticObjectMethod(_eCls, _eOf, static_cast<jlong>(_r));
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefDomNode_00024NativePeer_N_1IsSame(JNIEnv* env, jobject obj, jlong self, jobject that) {
    auto* s = reinterpret_cast<cef_domnode_t*>(self);
    if (!s) return JNI_FALSE;
    cef_domnode_t* _that_ptr = that ? reinterpret_cast<cef_domnode_t*>(env->GetLongField(that, env->GetFieldID(env->GetObjectClass(that), "nativePtr", "J"))) : nullptr;
    if (_that_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_that_ptr); _b->add_ref(_b);}
    auto _r = s->is_same(s, _that_ptr);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefDomNode_00024NativePeer_N_1GetName(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_domnode_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_name(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefDomNode_00024NativePeer_N_1GetValue(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_domnode_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_value(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefDomNode_00024NativePeer_N_1SetValue(JNIEnv* env, jobject obj, jlong self, jstring value) {
    auto* s = reinterpret_cast<cef_domnode_t*>(self);
    if (!s) return JNI_FALSE;
    auto _value_str = JStringToCefString(env, value);
    auto _r = s->set_value(s, _value_str);
    if (_value_str) cef_string_userfree_free(_value_str);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefDomNode_00024NativePeer_N_1GetAsMarkup(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_domnode_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_as_markup(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefDomNode_00024NativePeer_N_1GetDocument(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_domnode_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_document(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefDomDocument$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefDomNode_00024NativePeer_N_1GetParent(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_domnode_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_parent(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefDomNode$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefDomNode_00024NativePeer_N_1GetPreviousSibling(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_domnode_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_previous_sibling(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefDomNode$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefDomNode_00024NativePeer_N_1GetNextSibling(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_domnode_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_next_sibling(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefDomNode$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefDomNode_00024NativePeer_N_1HasChildren(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_domnode_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->has_children(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefDomNode_00024NativePeer_N_1GetFirstChild(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_domnode_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_first_child(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefDomNode$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefDomNode_00024NativePeer_N_1GetLastChild(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_domnode_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_last_child(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefDomNode$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefDomNode_00024NativePeer_N_1GetElementTagName(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_domnode_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_element_tag_name(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefDomNode_00024NativePeer_N_1HasElementAttributes(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_domnode_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->has_element_attributes(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefDomNode_00024NativePeer_N_1HasElementAttribute(JNIEnv* env, jobject obj, jlong self, jstring attrName) {
    auto* s = reinterpret_cast<cef_domnode_t*>(self);
    if (!s) return JNI_FALSE;
    auto _attrName_str = JStringToCefString(env, attrName);
    auto _r = s->has_element_attribute(s, _attrName_str);
    if (_attrName_str) cef_string_userfree_free(_attrName_str);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefDomNode_00024NativePeer_N_1GetElementAttribute(JNIEnv* env, jobject obj, jlong self, jstring attrName) {
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

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefDomNode_00024NativePeer_N_1GetElementAttributes(JNIEnv* env, jobject obj, jlong self, jobject attrMap) {
    auto* s = reinterpret_cast<cef_domnode_t*>(self);
    if (!s) return;
    if (!attrMap) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "attrMap must not be null"); return;}
    auto _attrMap_csm = JavaMapToCefStringMap(env, attrMap);
    s->get_element_attributes(s, _attrMap_csm);
    CefStringMapWriteBack(env, _attrMap_csm, attrMap);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefDomNode_00024NativePeer_N_1SetElementAttribute(JNIEnv* env, jobject obj, jlong self, jstring attrName, jstring value) {
    auto* s = reinterpret_cast<cef_domnode_t*>(self);
    if (!s) return JNI_FALSE;
    auto _attrName_str = JStringToCefString(env, attrName);
    auto _value_str = JStringToCefString(env, value);
    auto _r = s->set_element_attribute(s, _attrName_str, _value_str);
    if (_attrName_str) cef_string_userfree_free(_attrName_str);
    if (_value_str) cef_string_userfree_free(_value_str);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefDomNode_00024NativePeer_N_1GetElementInnerText(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_domnode_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_element_inner_text(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefDomNode_00024NativePeer_N_1GetElementBounds(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_domnode_t*>(self);
    if (!s) return nullptr;
    cef_rect_t result = s->get_element_bounds(s);
    auto cls = env->FindClass("net/kurobako/cef4j/gen/CefRect");
    auto ctor = env->GetMethodID(cls, "<init>", "(IIII)V");
    auto _dsResult = env->NewObject(cls, ctor, static_cast<jint>((&result)->x), static_cast<jint>((&result)->y), static_cast<jint>((&result)->width), static_cast<jint>((&result)->height));
    return _dsResult;
}
