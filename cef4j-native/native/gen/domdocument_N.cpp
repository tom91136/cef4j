// GENERATED - do not edit.
#include <jni.h>
#include "include/capi/cef_dom_capi.h"
#include "jni_util.h"

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefDomDocument_00024NativePeer_N_1Release(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefDomDocument_00024NativePeer_N_1GetType(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_domdocument_t*>(self);
    if (!s) return 0;
    auto _r = s->get_type(s);
    auto _eCls = env->FindClass("net/kurobako/cef4j/gen/CefDomDocumentType");
    auto _eOf = env->GetStaticMethodID(_eCls, "of", "(J)Lnet/kurobako/cef4j/gen/CefDomDocumentType;");
    return env->CallStaticObjectMethod(_eCls, _eOf, static_cast<jlong>(_r));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefDomDocument_00024NativePeer_N_1GetDocument(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_domdocument_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_document(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefDomNode$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefDomDocument_00024NativePeer_N_1GetBody(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_domdocument_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_body(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefDomNode$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefDomDocument_00024NativePeer_N_1GetHead(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_domdocument_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_head(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefDomNode$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefDomDocument_00024NativePeer_N_1GetTitle(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_domdocument_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_title(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefDomDocument_00024NativePeer_N_1GetElementById(JNIEnv* env, jobject obj, jlong self, jstring id) {
    auto* s = reinterpret_cast<cef_domdocument_t*>(self);
    if (!s) return nullptr;
    if (!id) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "id must not be null"); return nullptr;}
    auto _id_str = JStringToCefString(env, id);
    auto _r = s->get_element_by_id(s, _id_str);
    if (_id_str) cef_string_userfree_free(_id_str);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefDomNode$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefDomDocument_00024NativePeer_N_1GetFocusedNode(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_domdocument_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_focused_node(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefDomNode$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefDomDocument_00024NativePeer_N_1HasSelection(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_domdocument_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->has_selection(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jint JNICALL Java_net_kurobako_cef4j_gen_CefDomDocument_00024NativePeer_N_1GetSelectionStartOffset(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_domdocument_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->get_selection_start_offset(s));
}

extern "C" JNIEXPORT jint JNICALL Java_net_kurobako_cef4j_gen_CefDomDocument_00024NativePeer_N_1GetSelectionEndOffset(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_domdocument_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->get_selection_end_offset(s));
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefDomDocument_00024NativePeer_N_1GetSelectionAsMarkup(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_domdocument_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_selection_as_markup(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefDomDocument_00024NativePeer_N_1GetSelectionAsText(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_domdocument_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_selection_as_text(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefDomDocument_00024NativePeer_N_1GetBaseUrl(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_domdocument_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_base_url(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefDomDocument_00024NativePeer_N_1GetCompleteUrl(JNIEnv* env, jobject obj, jlong self, jstring partialURL) {
    auto* s = reinterpret_cast<cef_domdocument_t*>(self);
    if (!s) return nullptr;
    if (!partialURL) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "partialurl must not be null"); return nullptr;}
    auto _partialURL_str = JStringToCefString(env, partialURL);
    auto result = s->get_complete_url(s, _partialURL_str);
    if (_partialURL_str) cef_string_userfree_free(_partialURL_str);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}
