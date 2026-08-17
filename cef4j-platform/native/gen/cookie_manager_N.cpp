// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
#include <jni.h>
#include "include/capi/cef_cookie_capi.h"
#include "include/capi/cef_callback_capi.h"
#include "jni_util.h"

extern "C" cef_cookie_visitor_t* Create_JniCefCookieVisitor(JNIEnv* env, jobject handler);
extern "C" cef_set_cookie_callback_t* Create_JniCefSetCookieCallback(JNIEnv* env, jobject handler);
extern "C" cef_delete_cookies_callback_t* Create_JniCefDeleteCookiesCallback(JNIEnv* env, jobject handler);
extern "C" cef_completion_callback_t* Create_JniCefCompletionCallback(JNIEnv* env, jobject handler);
CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefCookieManager), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefCookieManager), visitAllCookies0)(JNIEnv* env, jobject obj, jlong self, jobject visitor) {
    auto* s = reinterpret_cast<cef_cookie_manager_t*>(self);
    if (!s) return JNI_FALSE;
    cef_cookie_visitor_t* _visitor_ptr = visitor ? Create_JniCefCookieVisitor(env, visitor) : nullptr;
    auto _r = s->visit_all_cookies(s, _visitor_ptr);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefCookieManager), visitUrlCookies0)(JNIEnv* env, jobject obj, jlong self, jstring url, jboolean includeHttpOnly, jobject visitor) {
    auto* s = reinterpret_cast<cef_cookie_manager_t*>(self);
    if (!s) return JNI_FALSE;
    auto _url_str = JStringToCefString(env, url);
    cef_cookie_visitor_t* _visitor_ptr = visitor ? Create_JniCefCookieVisitor(env, visitor) : nullptr;
    auto _r = s->visit_url_cookies(s, _url_str, static_cast<bool>(includeHttpOnly), _visitor_ptr);
    if (_url_str) cef_string_userfree_free(_url_str);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefCookieManager), setCookie0)(JNIEnv* env, jobject obj, jlong self, jstring url, jobject cookie, jobject callback) {
    auto* s = reinterpret_cast<cef_cookie_manager_t*>(self);
    if (!s) return JNI_FALSE;
    if (!cookie) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "cookie must not be null"); return JNI_FALSE; }
    auto _url_str = JStringToCefString(env, url);
    cef_cookie_t _cookie_val = {};
    auto _cookie_c = FindClassCached(env, "net/kurobako/cef4j/gen/CefCookie");
    jstring _rd_name = (jstring)env->GetObjectField(cookie, env->GetFieldID(_cookie_c, "name", "Ljava/lang/String;"));
    CefStringSetFromJString(env, _rd_name, &_cookie_val.name);
    jstring _rd_value = (jstring)env->GetObjectField(cookie, env->GetFieldID(_cookie_c, "value", "Ljava/lang/String;"));
    CefStringSetFromJString(env, _rd_value, &_cookie_val.value);
    jstring _rd_domain = (jstring)env->GetObjectField(cookie, env->GetFieldID(_cookie_c, "domain", "Ljava/lang/String;"));
    CefStringSetFromJString(env, _rd_domain, &_cookie_val.domain);
    jstring _rd_path = (jstring)env->GetObjectField(cookie, env->GetFieldID(_cookie_c, "path", "Ljava/lang/String;"));
    CefStringSetFromJString(env, _rd_path, &_cookie_val.path);
    _cookie_val.secure = static_cast<decltype(_cookie_val.secure)>(env->GetIntField(cookie, env->GetFieldID(_cookie_c, "secure", "I")));
    _cookie_val.httponly = static_cast<decltype(_cookie_val.httponly)>(env->GetIntField(cookie, env->GetFieldID(_cookie_c, "httponly", "I")));
    auto _rd_creation = env->GetObjectField(cookie, env->GetFieldID(_cookie_c, "creation", "Lnet/kurobako/cef4j/gen/CefBasetime;"));
    if (_rd_creation) {
        auto _rd_creationc = env->GetObjectClass(_rd_creation);
        _cookie_val.creation.val = from_jlong<decltype(_cookie_val.creation.val)>(env->GetLongField(_rd_creation, env->GetFieldID(_rd_creationc, "val", "J")));
    }
    auto _rd_last_access = env->GetObjectField(cookie, env->GetFieldID(_cookie_c, "lastAccess", "Lnet/kurobako/cef4j/gen/CefBasetime;"));
    if (_rd_last_access) {
        auto _rd_last_accessc = env->GetObjectClass(_rd_last_access);
        _cookie_val.last_access.val = from_jlong<decltype(_cookie_val.last_access.val)>(env->GetLongField(_rd_last_access, env->GetFieldID(_rd_last_accessc, "val", "J")));
    }
    _cookie_val.has_expires = static_cast<decltype(_cookie_val.has_expires)>(env->GetIntField(cookie, env->GetFieldID(_cookie_c, "hasExpires", "I")));
    auto _rd_expires = env->GetObjectField(cookie, env->GetFieldID(_cookie_c, "expires", "Lnet/kurobako/cef4j/gen/CefBasetime;"));
    if (_rd_expires) {
        auto _rd_expiresc = env->GetObjectClass(_rd_expires);
        _cookie_val.expires.val = from_jlong<decltype(_cookie_val.expires.val)>(env->GetLongField(_rd_expires, env->GetFieldID(_rd_expiresc, "val", "J")));
    }
    auto _rd_same_site = env->GetObjectField(cookie, env->GetFieldID(_cookie_c, "sameSite", "Lnet/kurobako/cef4j/gen/CefCookieSameSite;"));
    if (_rd_same_site) {
        _cookie_val.same_site = static_cast<decltype(_cookie_val.same_site)>(env->GetLongField(_rd_same_site, env->GetFieldID(env->GetObjectClass(_rd_same_site), "value", "J")));
    }
    auto _rd_priority = env->GetObjectField(cookie, env->GetFieldID(_cookie_c, "priority", "Lnet/kurobako/cef4j/gen/CefCookiePriority;"));
    if (_rd_priority) {
        _cookie_val.priority = static_cast<decltype(_cookie_val.priority)>(env->GetLongField(_rd_priority, env->GetFieldID(env->GetObjectClass(_rd_priority), "value", "J")));
    }
    _cookie_val.size = sizeof(cef_cookie_t);
    cef_set_cookie_callback_t* _callback_ptr = callback ? Create_JniCefSetCookieCallback(env, callback) : nullptr;
    auto _r = s->set_cookie(s, _url_str, &_cookie_val, _callback_ptr);
    if (_url_str) cef_string_userfree_free(_url_str);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefCookieManager), deleteCookies0)(JNIEnv* env, jobject obj, jlong self, jstring url, jstring cookie_name, jobject callback) {
    auto* s = reinterpret_cast<cef_cookie_manager_t*>(self);
    if (!s) return JNI_FALSE;
    auto _url_str = url ? JStringToCefString(env, url) : nullptr;
    auto _cookie_name_str = cookie_name ? JStringToCefString(env, cookie_name) : nullptr;
    cef_delete_cookies_callback_t* _callback_ptr = callback ? Create_JniCefDeleteCookiesCallback(env, callback) : nullptr;
    auto _r = s->delete_cookies(s, _url_str, _cookie_name_str, _callback_ptr);
    if (_url_str) cef_string_userfree_free(_url_str);
    if (_cookie_name_str) cef_string_userfree_free(_cookie_name_str);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefCookieManager), flushStore0)(JNIEnv* env, jobject obj, jlong self, jobject callback) {
    auto* s = reinterpret_cast<cef_cookie_manager_t*>(self);
    if (!s) return JNI_FALSE;
    cef_completion_callback_t* _callback_ptr = callback ? Create_JniCefCompletionCallback(env, callback) : nullptr;
    auto _r = s->flush_store(s, _callback_ptr);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefCookieManager), getGlobalManager0)(JNIEnv* env, jclass clz, jobject callback) {
    cef_completion_callback_t* _callback_ptr = callback ? Create_JniCefCompletionCallback(env, callback) : nullptr;
    auto _r = cef_cookie_manager_get_global_manager(_callback_ptr);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefCookieManager$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}
