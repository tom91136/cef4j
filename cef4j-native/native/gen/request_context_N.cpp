// GENERATED - do not edit.
#include <jni.h>
#include "include/capi/cef_request_context_capi.h"
#include "include/capi/cef_callback_capi.h"
#include "include/capi/cef_cookie_capi.h"
#include "include/capi/cef_media_router_capi.h"
#include "include/capi/cef_registration_capi.h"
#include "include/capi/cef_request_context_handler_capi.h"
#include "include/capi/cef_scheme_capi.h"
#include "include/capi/cef_values_capi.h"
#include "jni_util.h"

extern "C" cef_completion_callback_t* Create_JniCefCompletionCallback(JNIEnv *env, jobject handler);
extern "C" cef_scheme_handler_factory_t* Create_JniCefSchemeHandlerFactory(JNIEnv *env, jobject handler);
extern "C" cef_resolve_callback_t* Create_JniCefResolveCallback(JNIEnv *env, jobject handler);
extern "C" cef_setting_observer_t* Create_JniCefSettingObserver(JNIEnv *env, jobject handler);
extern "C" cef_request_context_handler_t* Create_JniCefRequestContextHandler(JNIEnv *env, jobject handler);
extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefRequestContext_00024NativePeer_N_1Release(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefRequestContext_00024NativePeer_N_1IsSame(JNIEnv* env, jobject obj, jlong self, jobject other) {
    auto* s = reinterpret_cast<cef_request_context_t*>(self);
    if (!s) return JNI_FALSE;
    if (!other) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "other must not be null"); return JNI_FALSE;}
    cef_request_context_t* _other_ptr = reinterpret_cast<cef_request_context_t*>(env->GetLongField(other, env->GetFieldID(env->GetObjectClass(other), "nativePtr", "J")));
    if (_other_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_other_ptr); _b->add_ref(_b);}
    auto _r = s->is_same(s, _other_ptr);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefRequestContext_00024NativePeer_N_1IsSharingWith(JNIEnv* env, jobject obj, jlong self, jobject other) {
    auto* s = reinterpret_cast<cef_request_context_t*>(self);
    if (!s) return JNI_FALSE;
    if (!other) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "other must not be null"); return JNI_FALSE;}
    cef_request_context_t* _other_ptr = reinterpret_cast<cef_request_context_t*>(env->GetLongField(other, env->GetFieldID(env->GetObjectClass(other), "nativePtr", "J")));
    if (_other_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_other_ptr); _b->add_ref(_b);}
    auto _r = s->is_sharing_with(s, _other_ptr);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefRequestContext_00024NativePeer_N_1IsGlobal(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_request_context_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_global(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefRequestContext_00024NativePeer_N_1GetHandler(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_request_context_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_handler(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefRequestContextHandler$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefRequestContext_00024NativePeer_N_1GetCachePath(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_request_context_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_cache_path(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefRequestContext_00024NativePeer_N_1GetCookieManager(JNIEnv* env, jobject obj, jlong self, jobject callback) {
    auto* s = reinterpret_cast<cef_request_context_t*>(self);
    if (!s) return nullptr;
    cef_completion_callback_t* _callback_ptr = callback ? Create_JniCefCompletionCallback(env, callback) : nullptr;
    auto _r = s->get_cookie_manager(s, _callback_ptr);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefCookieManager$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefRequestContext_00024NativePeer_N_1RegisterSchemeHandlerFactory(JNIEnv* env, jobject obj, jlong self, jstring scheme_name, jstring domain_name, jobject factory) {
    auto* s = reinterpret_cast<cef_request_context_t*>(self);
    if (!s) return JNI_FALSE;
    if (!scheme_name) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "schemeName must not be null"); return JNI_FALSE;}
    auto _scheme_name_str = JStringToCefString(env, scheme_name);
    auto _domain_name_str = domain_name ? JStringToCefString(env, domain_name) : nullptr;
    cef_scheme_handler_factory_t* _factory_ptr = factory ? Create_JniCefSchemeHandlerFactory(env, factory) : nullptr;
    auto _r = s->register_scheme_handler_factory(s, _scheme_name_str, _domain_name_str, _factory_ptr);
    if (_scheme_name_str) cef_string_userfree_free(_scheme_name_str);
    if (_domain_name_str) cef_string_userfree_free(_domain_name_str);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefRequestContext_00024NativePeer_N_1ClearSchemeHandlerFactories(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_request_context_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->clear_scheme_handler_factories(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefRequestContext_00024NativePeer_N_1ClearCertificateExceptions(JNIEnv* env, jobject obj, jlong self, jobject callback) {
    auto* s = reinterpret_cast<cef_request_context_t*>(self);
    if (!s) return;
    cef_completion_callback_t* _callback_ptr = callback ? Create_JniCefCompletionCallback(env, callback) : nullptr;
    s->clear_certificate_exceptions(s, _callback_ptr);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefRequestContext_00024NativePeer_N_1ClearHttpAuthCredentials(JNIEnv* env, jobject obj, jlong self, jobject callback) {
    auto* s = reinterpret_cast<cef_request_context_t*>(self);
    if (!s) return;
    cef_completion_callback_t* _callback_ptr = callback ? Create_JniCefCompletionCallback(env, callback) : nullptr;
    s->clear_http_auth_credentials(s, _callback_ptr);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefRequestContext_00024NativePeer_N_1CloseAllConnections(JNIEnv* env, jobject obj, jlong self, jobject callback) {
    auto* s = reinterpret_cast<cef_request_context_t*>(self);
    if (!s) return;
    cef_completion_callback_t* _callback_ptr = callback ? Create_JniCefCompletionCallback(env, callback) : nullptr;
    s->close_all_connections(s, _callback_ptr);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefRequestContext_00024NativePeer_N_1ResolveHost(JNIEnv* env, jobject obj, jlong self, jstring origin, jobject callback) {
    auto* s = reinterpret_cast<cef_request_context_t*>(self);
    if (!s) return;
    if (!origin) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "origin must not be null"); return;}
    if (!callback) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "callback must not be null"); return;}
    auto _origin_str = JStringToCefString(env, origin);
    cef_resolve_callback_t* _callback_ptr = Create_JniCefResolveCallback(env, callback);
    s->resolve_host(s, _origin_str, _callback_ptr);
    if (_origin_str) cef_string_userfree_free(_origin_str);
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefRequestContext_00024NativePeer_N_1GetMediaRouter(JNIEnv* env, jobject obj, jlong self, jobject callback) {
    auto* s = reinterpret_cast<cef_request_context_t*>(self);
    if (!s) return nullptr;
    cef_completion_callback_t* _callback_ptr = callback ? Create_JniCefCompletionCallback(env, callback) : nullptr;
    auto _r = s->get_media_router(s, _callback_ptr);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefMediaRouter$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefRequestContext_00024NativePeer_N_1GetWebsiteSetting(JNIEnv* env, jobject obj, jlong self, jstring requesting_url, jstring top_level_url, jobject content_type) {
    auto* s = reinterpret_cast<cef_request_context_t*>(self);
    if (!s) return nullptr;
    if (!content_type) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "contentType must not be null"); return nullptr;}
    auto _requesting_url_str = requesting_url ? JStringToCefString(env, requesting_url) : nullptr;
    auto _top_level_url_str = top_level_url ? JStringToCefString(env, top_level_url) : nullptr;
    auto _r = s->get_website_setting(s, _requesting_url_str, _top_level_url_str, static_cast<cef_content_setting_types_t>(env->GetLongField(content_type, env->GetFieldID(env->GetObjectClass(content_type), "value", "J"))));
    if (_requesting_url_str) cef_string_userfree_free(_requesting_url_str);
    if (_top_level_url_str) cef_string_userfree_free(_top_level_url_str);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefValue$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefRequestContext_00024NativePeer_N_1SetWebsiteSetting(JNIEnv* env, jobject obj, jlong self, jstring requesting_url, jstring top_level_url, jobject content_type, jobject value) {
    auto* s = reinterpret_cast<cef_request_context_t*>(self);
    if (!s) return;
    if (!content_type) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "contentType must not be null"); return;}
    auto _requesting_url_str = requesting_url ? JStringToCefString(env, requesting_url) : nullptr;
    auto _top_level_url_str = top_level_url ? JStringToCefString(env, top_level_url) : nullptr;
    cef_value_t* _value_ptr = value ? reinterpret_cast<cef_value_t*>(env->GetLongField(value, env->GetFieldID(env->GetObjectClass(value), "nativePtr", "J"))) : nullptr;
    if (_value_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_value_ptr); _b->add_ref(_b);}
    s->set_website_setting(s, _requesting_url_str, _top_level_url_str, static_cast<cef_content_setting_types_t>(env->GetLongField(content_type, env->GetFieldID(env->GetObjectClass(content_type), "value", "J"))), _value_ptr);
    if (_requesting_url_str) cef_string_userfree_free(_requesting_url_str);
    if (_top_level_url_str) cef_string_userfree_free(_top_level_url_str);
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefRequestContext_00024NativePeer_N_1GetContentSetting(JNIEnv* env, jobject obj, jlong self, jstring requesting_url, jstring top_level_url, jobject content_type) {
    auto* s = reinterpret_cast<cef_request_context_t*>(self);
    if (!s) return 0;
    if (!content_type) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "contentType must not be null"); return 0;}
    auto _requesting_url_str = requesting_url ? JStringToCefString(env, requesting_url) : nullptr;
    auto _top_level_url_str = top_level_url ? JStringToCefString(env, top_level_url) : nullptr;
    auto _r = s->get_content_setting(s, _requesting_url_str, _top_level_url_str, static_cast<cef_content_setting_types_t>(env->GetLongField(content_type, env->GetFieldID(env->GetObjectClass(content_type), "value", "J"))));
    if (_requesting_url_str) cef_string_userfree_free(_requesting_url_str);
    if (_top_level_url_str) cef_string_userfree_free(_top_level_url_str);
    auto _eCls = env->FindClass("net/kurobako/cef4j/gen/CefContentSettingValues");
    auto _eOf = env->GetStaticMethodID(_eCls, "of", "(J)Lnet/kurobako/cef4j/gen/CefContentSettingValues;");
    return env->CallStaticObjectMethod(_eCls, _eOf, static_cast<jlong>(_r));
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefRequestContext_00024NativePeer_N_1SetContentSetting(JNIEnv* env, jobject obj, jlong self, jstring requesting_url, jstring top_level_url, jobject content_type, jobject value) {
    auto* s = reinterpret_cast<cef_request_context_t*>(self);
    if (!s) return;
    if (!content_type) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "contentType must not be null"); return;}
    if (!value) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "value must not be null"); return;}
    auto _requesting_url_str = requesting_url ? JStringToCefString(env, requesting_url) : nullptr;
    auto _top_level_url_str = top_level_url ? JStringToCefString(env, top_level_url) : nullptr;
    s->set_content_setting(s, _requesting_url_str, _top_level_url_str, static_cast<cef_content_setting_types_t>(env->GetLongField(content_type, env->GetFieldID(env->GetObjectClass(content_type), "value", "J"))), static_cast<cef_content_setting_values_t>(env->GetLongField(value, env->GetFieldID(env->GetObjectClass(value), "value", "J"))));
    if (_requesting_url_str) cef_string_userfree_free(_requesting_url_str);
    if (_top_level_url_str) cef_string_userfree_free(_top_level_url_str);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefRequestContext_00024NativePeer_N_1SetChromeColorScheme(JNIEnv* env, jobject obj, jlong self, jobject variant, jint user_color) {
    auto* s = reinterpret_cast<cef_request_context_t*>(self);
    if (!s) return;
    if (!variant) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "variant must not be null"); return;}
    s->set_chrome_color_scheme(s, static_cast<cef_color_variant_t>(env->GetLongField(variant, env->GetFieldID(env->GetObjectClass(variant), "value", "J"))), user_color);
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefRequestContext_00024NativePeer_N_1GetChromeColorSchemeMode(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_request_context_t*>(self);
    if (!s) return 0;
    auto _r = s->get_chrome_color_scheme_mode(s);
    auto _eCls = env->FindClass("net/kurobako/cef4j/gen/CefColorVariant");
    auto _eOf = env->GetStaticMethodID(_eCls, "of", "(J)Lnet/kurobako/cef4j/gen/CefColorVariant;");
    return env->CallStaticObjectMethod(_eCls, _eOf, static_cast<jlong>(_r));
}

extern "C" JNIEXPORT jint JNICALL Java_net_kurobako_cef4j_gen_CefRequestContext_00024NativePeer_N_1GetChromeColorSchemeColor(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_request_context_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->get_chrome_color_scheme_color(s));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefRequestContext_00024NativePeer_N_1GetChromeColorSchemeVariant(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_request_context_t*>(self);
    if (!s) return 0;
    auto _r = s->get_chrome_color_scheme_variant(s);
    auto _eCls = env->FindClass("net/kurobako/cef4j/gen/CefColorVariant");
    auto _eOf = env->GetStaticMethodID(_eCls, "of", "(J)Lnet/kurobako/cef4j/gen/CefColorVariant;");
    return env->CallStaticObjectMethod(_eCls, _eOf, static_cast<jlong>(_r));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefRequestContext_00024NativePeer_N_1AddSettingObserver(JNIEnv* env, jobject obj, jlong self, jobject observer) {
    auto* s = reinterpret_cast<cef_request_context_t*>(self);
    if (!s) return nullptr;
    if (!observer) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "observer must not be null"); return nullptr;}
    cef_setting_observer_t* _observer_ptr = Create_JniCefSettingObserver(env, observer);
    auto _r = s->add_setting_observer(s, _observer_ptr);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefRegistration$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefRequestContext_00024NativePeer_N_1ClearHttpCache(JNIEnv* env, jobject obj, jlong self, jobject callback) {
    auto* s = reinterpret_cast<cef_request_context_t*>(self);
    if (!s) return;
    cef_completion_callback_t* _callback_ptr = callback ? Create_JniCefCompletionCallback(env, callback) : nullptr;
    s->clear_http_cache(s, _callback_ptr);
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefRequestContext_00024NativePeer_N_1GetGlobalContext(JNIEnv* env, jclass clz) {
    auto _r = cef_request_context_get_global_context();
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefRequestContext$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefRequestContext_00024NativePeer_N_1CreateContext(JNIEnv* env, jclass clz, jobject settings, jobject handler) {
    if (!settings) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "settings must not be null"); return nullptr;}
    cef_request_context_handler_t* _handler_ptr = handler ? Create_JniCefRequestContextHandler(env, handler) : nullptr;
    auto _r = cef_request_context_create_context(reinterpret_cast<const struct _cef_request_context_settings_t*>(settings ? env->GetLongField(settings, env->GetFieldID(env->GetObjectClass(settings), "address", "J")) : 0), _handler_ptr);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefRequestContext$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefRequestContext_00024NativePeer_N_1CefCreateContextShared(JNIEnv* env, jclass clz, jobject other, jobject handler) {
    if (!other) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "other must not be null"); return nullptr;}
    if (!handler) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "handler must not be null"); return nullptr;}
    cef_request_context_t* _other_ptr = reinterpret_cast<cef_request_context_t*>(env->GetLongField(other, env->GetFieldID(env->GetObjectClass(other), "nativePtr", "J")));
    if (_other_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_other_ptr); _b->add_ref(_b);}
    cef_request_context_handler_t* _handler_ptr = Create_JniCefRequestContextHandler(env, handler);
    auto _r = cef_request_context_cef_create_context_shared(_other_ptr, _handler_ptr);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefRequestContext$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}
