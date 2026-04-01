// GENERATED - do not edit.
#include <jni.h>
#include "include/capi/cef_frame_capi.h"
#include "include/capi/cef_browser_capi.h"
#include "include/capi/cef_dom_capi.h"
#include "include/capi/cef_process_message_capi.h"
#include "include/capi/cef_request_capi.h"
#include "include/capi/cef_string_visitor_capi.h"
#include "include/capi/cef_urlrequest_capi.h"
#include "include/capi/cef_v8_capi.h"
#include "jni_util.h"

extern "C" cef_string_visitor_t* Create_JniCefStringVisitor(JNIEnv *env, jobject handler);
extern "C" cef_domvisitor_t* Create_JniCefDomVisitor(JNIEnv *env, jobject handler);
extern "C" cef_urlrequest_client_t* Create_JniCefUrlRequestClient(JNIEnv *env, jobject handler);
extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefFrame_00024NativePeer_N_1Release(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefFrame_00024NativePeer_N_1IsValid(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_frame_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_valid(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefFrame_00024NativePeer_N_1Undo(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_frame_t*>(self);
    if (!s) return;
    s->undo(s);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefFrame_00024NativePeer_N_1Redo(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_frame_t*>(self);
    if (!s) return;
    s->redo(s);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefFrame_00024NativePeer_N_1Cut(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_frame_t*>(self);
    if (!s) return;
    s->cut(s);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefFrame_00024NativePeer_N_1Copy(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_frame_t*>(self);
    if (!s) return;
    s->copy(s);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefFrame_00024NativePeer_N_1Paste(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_frame_t*>(self);
    if (!s) return;
    s->paste(s);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefFrame_00024NativePeer_N_1PasteAndMatchStyle(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_frame_t*>(self);
    if (!s) return;
    s->paste_and_match_style(s);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefFrame_00024NativePeer_N_1Del(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_frame_t*>(self);
    if (!s) return;
    s->del(s);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefFrame_00024NativePeer_N_1SelectAll(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_frame_t*>(self);
    if (!s) return;
    s->select_all(s);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefFrame_00024NativePeer_N_1ViewSource(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_frame_t*>(self);
    if (!s) return;
    s->view_source(s);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefFrame_00024NativePeer_N_1GetSource(JNIEnv* env, jobject obj, jlong self, jobject visitor) {
    auto* s = reinterpret_cast<cef_frame_t*>(self);
    if (!s) return;
    if (!visitor) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "visitor must not be null"); return;}
    cef_string_visitor_t* _visitor_ptr = Create_JniCefStringVisitor(env, visitor);
    s->get_source(s, _visitor_ptr);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefFrame_00024NativePeer_N_1GetText(JNIEnv* env, jobject obj, jlong self, jobject visitor) {
    auto* s = reinterpret_cast<cef_frame_t*>(self);
    if (!s) return;
    if (!visitor) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "visitor must not be null"); return;}
    cef_string_visitor_t* _visitor_ptr = Create_JniCefStringVisitor(env, visitor);
    s->get_text(s, _visitor_ptr);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefFrame_00024NativePeer_N_1LoadRequest(JNIEnv* env, jobject obj, jlong self, jobject request) {
    auto* s = reinterpret_cast<cef_frame_t*>(self);
    if (!s) return;
    if (!request) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "request must not be null"); return;}
    cef_request_t* _request_ptr = reinterpret_cast<cef_request_t*>(env->GetLongField(request, env->GetFieldID(env->GetObjectClass(request), "nativePtr", "J")));
    if (_request_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_request_ptr); _b->add_ref(_b);}
    s->load_request(s, _request_ptr);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefFrame_00024NativePeer_N_1LoadUrl(JNIEnv* env, jobject obj, jlong self, jstring url) {
    auto* s = reinterpret_cast<cef_frame_t*>(self);
    if (!s) return;
    if (!url) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "url must not be null"); return;}
    auto _url_str = JStringToCefString(env, url);
    s->load_url(s, _url_str);
    if (_url_str) cef_string_userfree_free(_url_str);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefFrame_00024NativePeer_N_1ExecuteJavaScript(JNIEnv* env, jobject obj, jlong self, jstring code, jstring script_url, jint start_line) {
    auto* s = reinterpret_cast<cef_frame_t*>(self);
    if (!s) return;
    if (!code) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "code must not be null"); return;}
    auto _code_str = JStringToCefString(env, code);
    auto _script_url_str = script_url ? JStringToCefString(env, script_url) : nullptr;
    s->execute_java_script(s, _code_str, _script_url_str, start_line);
    if (_code_str) cef_string_userfree_free(_code_str);
    if (_script_url_str) cef_string_userfree_free(_script_url_str);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefFrame_00024NativePeer_N_1IsMain(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_frame_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_main(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefFrame_00024NativePeer_N_1IsFocused(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_frame_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_focused(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefFrame_00024NativePeer_N_1GetName(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_frame_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_name(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefFrame_00024NativePeer_N_1GetIdentifier(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_frame_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_identifier(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefFrame_00024NativePeer_N_1GetParent(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_frame_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_parent(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefFrame$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefFrame_00024NativePeer_N_1GetUrl(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_frame_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_url(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefFrame_00024NativePeer_N_1GetBrowser(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_frame_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_browser(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefBrowser$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefFrame_00024NativePeer_N_1GetV8context(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_frame_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_v8_context(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefV8Context$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefFrame_00024NativePeer_N_1VisitDom(JNIEnv* env, jobject obj, jlong self, jobject visitor) {
    auto* s = reinterpret_cast<cef_frame_t*>(self);
    if (!s) return;
    if (!visitor) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "visitor must not be null"); return;}
    cef_domvisitor_t* _visitor_ptr = Create_JniCefDomVisitor(env, visitor);
    s->visit_dom(s, _visitor_ptr);
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefFrame_00024NativePeer_N_1CreateUrlRequest(JNIEnv* env, jobject obj, jlong self, jobject request, jobject client) {
    auto* s = reinterpret_cast<cef_frame_t*>(self);
    if (!s) return nullptr;
    if (!request) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "request must not be null"); return nullptr;}
    if (!client) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "client must not be null"); return nullptr;}
    cef_request_t* _request_ptr = reinterpret_cast<cef_request_t*>(env->GetLongField(request, env->GetFieldID(env->GetObjectClass(request), "nativePtr", "J")));
    if (_request_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_request_ptr); _b->add_ref(_b);}
    cef_urlrequest_client_t* _client_ptr = Create_JniCefUrlRequestClient(env, client);
    auto _r = s->create_urlrequest(s, _request_ptr, _client_ptr);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefUrlRequest$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefFrame_00024NativePeer_N_1SendProcessMessage(JNIEnv* env, jobject obj, jlong self, jobject target_process, jobject message) {
    auto* s = reinterpret_cast<cef_frame_t*>(self);
    if (!s) return;
    if (!target_process) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "targetProcess must not be null"); return;}
    if (!message) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "message must not be null"); return;}
    cef_process_message_t* _message_ptr = reinterpret_cast<cef_process_message_t*>(env->GetLongField(message, env->GetFieldID(env->GetObjectClass(message), "nativePtr", "J")));
    if (_message_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_message_ptr); _b->add_ref(_b);}
    s->send_process_message(s, static_cast<cef_process_id_t>(env->GetLongField(target_process, env->GetFieldID(env->GetObjectClass(target_process), "value", "J"))), _message_ptr);
}
