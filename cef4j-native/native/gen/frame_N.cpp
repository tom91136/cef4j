// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
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
CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefFrame), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefFrame), isValid0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_frame_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_valid(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefFrame), undo0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_frame_t*>(self);
    if (!s) return;
    s->undo(s);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefFrame), redo0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_frame_t*>(self);
    if (!s) return;
    s->redo(s);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefFrame), cut0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_frame_t*>(self);
    if (!s) return;
    s->cut(s);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefFrame), copy0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_frame_t*>(self);
    if (!s) return;
    s->copy(s);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefFrame), paste0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_frame_t*>(self);
    if (!s) return;
    s->paste(s);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefFrame), pasteAndMatchStyle0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_frame_t*>(self);
    if (!s) return;
    s->paste_and_match_style(s);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefFrame), del0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_frame_t*>(self);
    if (!s) return;
    s->del(s);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefFrame), selectAll0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_frame_t*>(self);
    if (!s) return;
    s->select_all(s);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefFrame), viewSource0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_frame_t*>(self);
    if (!s) return;
    s->view_source(s);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefFrame), getSource0)(JNIEnv* env, jobject obj, jlong self, jobject visitor) {
    auto* s = reinterpret_cast<cef_frame_t*>(self);
    if (!s) return;
    cef_string_visitor_t* _visitor_ptr = visitor ? Create_JniCefStringVisitor(env, visitor) : nullptr;
    s->get_source(s, _visitor_ptr);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefFrame), getText0)(JNIEnv* env, jobject obj, jlong self, jobject visitor) {
    auto* s = reinterpret_cast<cef_frame_t*>(self);
    if (!s) return;
    cef_string_visitor_t* _visitor_ptr = visitor ? Create_JniCefStringVisitor(env, visitor) : nullptr;
    s->get_text(s, _visitor_ptr);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefFrame), loadRequest0)(JNIEnv* env, jobject obj, jlong self, jobject request) {
    auto* s = reinterpret_cast<cef_frame_t*>(self);
    if (!s) return;
    cef_request_t* _request_ptr = request ? reinterpret_cast<cef_request_t*>(env->GetLongField(request, env->GetFieldID(env->GetObjectClass(request), "nativePtr", "J"))) : nullptr;
    if (_request_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_request_ptr); _b->add_ref(_b);}
    s->load_request(s, _request_ptr);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefFrame), loadUrl0)(JNIEnv* env, jobject obj, jlong self, jstring url) {
    auto* s = reinterpret_cast<cef_frame_t*>(self);
    if (!s) return;
    auto _url_str = JStringToCefString(env, url);
    s->load_url(s, _url_str);
    if (_url_str) cef_string_userfree_free(_url_str);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefFrame), executeJavaScript0)(JNIEnv* env, jobject obj, jlong self, jstring code, jstring script_url, jint start_line) {
    auto* s = reinterpret_cast<cef_frame_t*>(self);
    if (!s) return;
    auto _code_str = JStringToCefString(env, code);
    auto _script_url_str = script_url ? JStringToCefString(env, script_url) : nullptr;
    s->execute_java_script(s, _code_str, _script_url_str, start_line);
    if (_code_str) cef_string_userfree_free(_code_str);
    if (_script_url_str) cef_string_userfree_free(_script_url_str);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefFrame), isMain0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_frame_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_main(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefFrame), isFocused0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_frame_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_focused(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefFrame), getName0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_frame_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_name(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefFrame), getIdentifier0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_frame_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_identifier(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefFrame), getParent0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_frame_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_parent(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefFrame$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefFrame), getUrl0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_frame_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_url(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefFrame), getBrowser0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_frame_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_browser(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefBrowser$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefFrame), getV8context0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_frame_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_v8_context(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefV8Context$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefFrame), visitDom0)(JNIEnv* env, jobject obj, jlong self, jobject visitor) {
    auto* s = reinterpret_cast<cef_frame_t*>(self);
    if (!s) return;
    cef_domvisitor_t* _visitor_ptr = visitor ? Create_JniCefDomVisitor(env, visitor) : nullptr;
    s->visit_dom(s, _visitor_ptr);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefFrame), createUrlRequest0)(JNIEnv* env, jobject obj, jlong self, jobject request, jobject client) {
    auto* s = reinterpret_cast<cef_frame_t*>(self);
    if (!s) return nullptr;
    cef_request_t* _request_ptr = request ? reinterpret_cast<cef_request_t*>(env->GetLongField(request, env->GetFieldID(env->GetObjectClass(request), "nativePtr", "J"))) : nullptr;
    if (_request_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_request_ptr); _b->add_ref(_b);}
    cef_urlrequest_client_t* _client_ptr = client ? Create_JniCefUrlRequestClient(env, client) : nullptr;
    auto _r = s->create_urlrequest(s, _request_ptr, _client_ptr);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefUrlRequest$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefFrame), sendProcessMessage0)(JNIEnv* env, jobject obj, jlong self, jobject target_process, jobject message) {
    auto* s = reinterpret_cast<cef_frame_t*>(self);
    if (!s) return;
    if (!target_process) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "targetProcess must not be null"); return;}
    cef_process_message_t* _message_ptr = message ? reinterpret_cast<cef_process_message_t*>(env->GetLongField(message, env->GetFieldID(env->GetObjectClass(message), "nativePtr", "J"))) : nullptr;
    if (_message_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_message_ptr); _b->add_ref(_b);}
    s->send_process_message(s, static_cast<cef_process_id_t>(env->GetLongField(target_process, env->GetFieldID(env->GetObjectClass(target_process), "value", "J"))), _message_ptr);
}
