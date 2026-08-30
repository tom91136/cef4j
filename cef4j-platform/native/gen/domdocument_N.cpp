// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
#include <jni.h>
#include "include/capi/cef_dom_capi.h"
#include "jni_util.h"

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefDomDocument), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefDomDocument), getType0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_domdocument_t*>(self);
    if (!s) return 0;
    auto _r = s->get_type(s);
    auto _eCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefDomDocumentType");
    auto _eOf = env->GetStaticMethodID(_eCls, "of", "(J)Lnet/kurobako/cef4j/gen/CefDomDocumentType;");
    return env->CallStaticObjectMethod(_eCls, _eOf, static_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefDomDocument), getDocument0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_domdocument_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_document(s);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefDomNode$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefDomDocument), getBody0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_domdocument_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_body(s);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefDomNode$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefDomDocument), getHead0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_domdocument_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_head(s);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefDomNode$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefDomDocument), getTitle0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_domdocument_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_title(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefDomDocument), getElementById0)(JNIEnv* env, jobject obj, jlong self, jstring id) {
    auto* s = reinterpret_cast<cef_domdocument_t*>(self);
    if (!s) return nullptr;
    auto _id_str = JStringToCefString(env, id);
    auto _r = s->get_element_by_id(s, _id_str);
    if (_id_str) cef_string_userfree_free(_id_str);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefDomNode$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefDomDocument), getFocusedNode0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_domdocument_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_focused_node(s);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefDomNode$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefDomDocument), hasSelection0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_domdocument_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->has_selection(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jint, CEF4J_PEER(CefDomDocument), getSelectionStartOffset0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_domdocument_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->get_selection_start_offset(s));
}

CEF4J_JNI_EXPORT(jint, CEF4J_PEER(CefDomDocument), getSelectionEndOffset0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_domdocument_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->get_selection_end_offset(s));
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefDomDocument), getSelectionAsMarkup0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_domdocument_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_selection_as_markup(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefDomDocument), getSelectionAsText0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_domdocument_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_selection_as_text(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefDomDocument), getBaseUrl0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_domdocument_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_base_url(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefDomDocument), getCompleteUrl0)(JNIEnv* env, jobject obj, jlong self, jstring partialURL) {
    auto* s = reinterpret_cast<cef_domdocument_t*>(self);
    if (!s) return nullptr;
    auto _partialURL_str = JStringToCefString(env, partialURL);
    auto result = s->get_complete_url(s, _partialURL_str);
    if (_partialURL_str) cef_string_userfree_free(_partialURL_str);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}
