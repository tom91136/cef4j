// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
#include <jni.h>
#include "include/capi/views/cef_browser_view_capi.h"
#include "include/capi/cef_browser_capi.h"
#include "include/capi/cef_client_capi.h"
#include "include/capi/cef_request_context_capi.h"
#include "include/capi/cef_values_capi.h"
#include "include/capi/views/cef_browser_view_delegate_capi.h"
#include "include/capi/views/cef_view_capi.h"
#include "jni_util.h"

extern "C" cef_client_t* Create_JniCefClient(JNIEnv* env, jobject handler);
extern "C" cef_browser_view_delegate_t* Create_JniCefBrowserViewDelegate(JNIEnv* env, jobject handler);
CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefBrowserView), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(views_CefBrowserView), getBrowser0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_browser_view_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_browser(s);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefBrowser$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(views_CefBrowserView), getChromeToolbar0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_browser_view_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_chrome_toolbar(s);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/views/CefView$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefBrowserView), setPreferAccelerators0)(JNIEnv* env, jobject obj, jlong self, jboolean prefer_accelerators) {
    auto* s = reinterpret_cast<cef_browser_view_t*>(self);
    if (!s) return;
    s->set_prefer_accelerators(s, static_cast<bool>(prefer_accelerators));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(views_CefBrowserView), getRuntimeStyle0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_browser_view_t*>(self);
    if (!s) return 0;
    auto _r = s->get_runtime_style(s);
    auto _eCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefRuntimeStyle");
    auto _eOf = env->GetStaticMethodID(_eCls, "of", "(J)Lnet/kurobako/cef4j/gen/CefRuntimeStyle;");
    return env->CallStaticObjectMethod(_eCls, _eOf, static_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(views_CefBrowserView), create0)(JNIEnv* env, jclass clz, jobject client, jstring url, jobject settings, jobject extra_info, jobject request_context, jobject delegate) {
    if (!settings) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "settings must not be null"); return nullptr; }
    cef_client_t* _client_ptr = client ? Create_JniCefClient(env, client) : nullptr;
    auto _url_str = url ? JStringToCefString(env, url) : nullptr;
    cef_browser_settings_t _settings_val = {};
    auto _settings_c = FindClassCached(env, "net/kurobako/cef4j/gen/CefBrowserSettings");
    _settings_val.windowless_frame_rate = static_cast<decltype(_settings_val.windowless_frame_rate)>(env->GetIntField(settings, env->GetFieldID(_settings_c, "windowlessFrameRate", "I")));
    jstring _rd_standard_font_family = (jstring)env->GetObjectField(settings, env->GetFieldID(_settings_c, "standardFontFamily", "Ljava/lang/String;"));
    CefStringSetFromJString(env, _rd_standard_font_family, &_settings_val.standard_font_family);
    jstring _rd_fixed_font_family = (jstring)env->GetObjectField(settings, env->GetFieldID(_settings_c, "fixedFontFamily", "Ljava/lang/String;"));
    CefStringSetFromJString(env, _rd_fixed_font_family, &_settings_val.fixed_font_family);
    jstring _rd_serif_font_family = (jstring)env->GetObjectField(settings, env->GetFieldID(_settings_c, "serifFontFamily", "Ljava/lang/String;"));
    CefStringSetFromJString(env, _rd_serif_font_family, &_settings_val.serif_font_family);
    jstring _rd_sans_serif_font_family = (jstring)env->GetObjectField(settings, env->GetFieldID(_settings_c, "sansSerifFontFamily", "Ljava/lang/String;"));
    CefStringSetFromJString(env, _rd_sans_serif_font_family, &_settings_val.sans_serif_font_family);
    jstring _rd_cursive_font_family = (jstring)env->GetObjectField(settings, env->GetFieldID(_settings_c, "cursiveFontFamily", "Ljava/lang/String;"));
    CefStringSetFromJString(env, _rd_cursive_font_family, &_settings_val.cursive_font_family);
    jstring _rd_fantasy_font_family = (jstring)env->GetObjectField(settings, env->GetFieldID(_settings_c, "fantasyFontFamily", "Ljava/lang/String;"));
    CefStringSetFromJString(env, _rd_fantasy_font_family, &_settings_val.fantasy_font_family);
    _settings_val.default_font_size = static_cast<decltype(_settings_val.default_font_size)>(env->GetIntField(settings, env->GetFieldID(_settings_c, "defaultFontSize", "I")));
    _settings_val.default_fixed_font_size = static_cast<decltype(_settings_val.default_fixed_font_size)>(env->GetIntField(settings, env->GetFieldID(_settings_c, "defaultFixedFontSize", "I")));
    _settings_val.minimum_font_size = static_cast<decltype(_settings_val.minimum_font_size)>(env->GetIntField(settings, env->GetFieldID(_settings_c, "minimumFontSize", "I")));
    _settings_val.minimum_logical_font_size = static_cast<decltype(_settings_val.minimum_logical_font_size)>(env->GetIntField(settings, env->GetFieldID(_settings_c, "minimumLogicalFontSize", "I")));
    jstring _rd_default_encoding = (jstring)env->GetObjectField(settings, env->GetFieldID(_settings_c, "defaultEncoding", "Ljava/lang/String;"));
    CefStringSetFromJString(env, _rd_default_encoding, &_settings_val.default_encoding);
    auto _rd_remote_fonts = env->GetObjectField(settings, env->GetFieldID(_settings_c, "remoteFonts", "Lnet/kurobako/cef4j/gen/CefState;"));
    if (_rd_remote_fonts) {
        _settings_val.remote_fonts = static_cast<decltype(_settings_val.remote_fonts)>(env->GetLongField(_rd_remote_fonts, env->GetFieldID(env->GetObjectClass(_rd_remote_fonts), "value", "J")));
    }
    auto _rd_javascript = env->GetObjectField(settings, env->GetFieldID(_settings_c, "javascript", "Lnet/kurobako/cef4j/gen/CefState;"));
    if (_rd_javascript) {
        _settings_val.javascript = static_cast<decltype(_settings_val.javascript)>(env->GetLongField(_rd_javascript, env->GetFieldID(env->GetObjectClass(_rd_javascript), "value", "J")));
    }
    auto _rd_javascript_close_windows = env->GetObjectField(settings, env->GetFieldID(_settings_c, "javascriptCloseWindows", "Lnet/kurobako/cef4j/gen/CefState;"));
    if (_rd_javascript_close_windows) {
        _settings_val.javascript_close_windows = static_cast<decltype(_settings_val.javascript_close_windows)>(env->GetLongField(_rd_javascript_close_windows, env->GetFieldID(env->GetObjectClass(_rd_javascript_close_windows), "value", "J")));
    }
    auto _rd_javascript_access_clipboard = env->GetObjectField(settings, env->GetFieldID(_settings_c, "javascriptAccessClipboard", "Lnet/kurobako/cef4j/gen/CefState;"));
    if (_rd_javascript_access_clipboard) {
        _settings_val.javascript_access_clipboard = static_cast<decltype(_settings_val.javascript_access_clipboard)>(env->GetLongField(_rd_javascript_access_clipboard, env->GetFieldID(env->GetObjectClass(_rd_javascript_access_clipboard), "value", "J")));
    }
    auto _rd_javascript_dom_paste = env->GetObjectField(settings, env->GetFieldID(_settings_c, "javascriptDomPaste", "Lnet/kurobako/cef4j/gen/CefState;"));
    if (_rd_javascript_dom_paste) {
        _settings_val.javascript_dom_paste = static_cast<decltype(_settings_val.javascript_dom_paste)>(env->GetLongField(_rd_javascript_dom_paste, env->GetFieldID(env->GetObjectClass(_rd_javascript_dom_paste), "value", "J")));
    }
    auto _rd_image_loading = env->GetObjectField(settings, env->GetFieldID(_settings_c, "imageLoading", "Lnet/kurobako/cef4j/gen/CefState;"));
    if (_rd_image_loading) {
        _settings_val.image_loading = static_cast<decltype(_settings_val.image_loading)>(env->GetLongField(_rd_image_loading, env->GetFieldID(env->GetObjectClass(_rd_image_loading), "value", "J")));
    }
    auto _rd_image_shrink_standalone_to_fit = env->GetObjectField(settings, env->GetFieldID(_settings_c, "imageShrinkStandaloneToFit", "Lnet/kurobako/cef4j/gen/CefState;"));
    if (_rd_image_shrink_standalone_to_fit) {
        _settings_val.image_shrink_standalone_to_fit = static_cast<decltype(_settings_val.image_shrink_standalone_to_fit)>(env->GetLongField(_rd_image_shrink_standalone_to_fit, env->GetFieldID(env->GetObjectClass(_rd_image_shrink_standalone_to_fit), "value", "J")));
    }
    auto _rd_text_area_resize = env->GetObjectField(settings, env->GetFieldID(_settings_c, "textAreaResize", "Lnet/kurobako/cef4j/gen/CefState;"));
    if (_rd_text_area_resize) {
        _settings_val.text_area_resize = static_cast<decltype(_settings_val.text_area_resize)>(env->GetLongField(_rd_text_area_resize, env->GetFieldID(env->GetObjectClass(_rd_text_area_resize), "value", "J")));
    }
    auto _rd_tab_to_links = env->GetObjectField(settings, env->GetFieldID(_settings_c, "tabToLinks", "Lnet/kurobako/cef4j/gen/CefState;"));
    if (_rd_tab_to_links) {
        _settings_val.tab_to_links = static_cast<decltype(_settings_val.tab_to_links)>(env->GetLongField(_rd_tab_to_links, env->GetFieldID(env->GetObjectClass(_rd_tab_to_links), "value", "J")));
    }
    auto _rd_local_storage = env->GetObjectField(settings, env->GetFieldID(_settings_c, "localStorage", "Lnet/kurobako/cef4j/gen/CefState;"));
    if (_rd_local_storage) {
        _settings_val.local_storage = static_cast<decltype(_settings_val.local_storage)>(env->GetLongField(_rd_local_storage, env->GetFieldID(env->GetObjectClass(_rd_local_storage), "value", "J")));
    }
    auto _rd_databases_deprecated = env->GetObjectField(settings, env->GetFieldID(_settings_c, "databasesDeprecated", "Lnet/kurobako/cef4j/gen/CefState;"));
    if (_rd_databases_deprecated) {
        _settings_val.databases_deprecated = static_cast<decltype(_settings_val.databases_deprecated)>(env->GetLongField(_rd_databases_deprecated, env->GetFieldID(env->GetObjectClass(_rd_databases_deprecated), "value", "J")));
    }
    auto _rd_webgl = env->GetObjectField(settings, env->GetFieldID(_settings_c, "webgl", "Lnet/kurobako/cef4j/gen/CefState;"));
    if (_rd_webgl) {
        _settings_val.webgl = static_cast<decltype(_settings_val.webgl)>(env->GetLongField(_rd_webgl, env->GetFieldID(env->GetObjectClass(_rd_webgl), "value", "J")));
    }
    _settings_val.background_color = static_cast<decltype(_settings_val.background_color)>(env->GetIntField(settings, env->GetFieldID(_settings_c, "backgroundColor", "I")));
    auto _rd_chrome_status_bubble = env->GetObjectField(settings, env->GetFieldID(_settings_c, "chromeStatusBubble", "Lnet/kurobako/cef4j/gen/CefState;"));
    if (_rd_chrome_status_bubble) {
        _settings_val.chrome_status_bubble = static_cast<decltype(_settings_val.chrome_status_bubble)>(env->GetLongField(_rd_chrome_status_bubble, env->GetFieldID(env->GetObjectClass(_rd_chrome_status_bubble), "value", "J")));
    }
    auto _rd_chrome_zoom_bubble = env->GetObjectField(settings, env->GetFieldID(_settings_c, "chromeZoomBubble", "Lnet/kurobako/cef4j/gen/CefState;"));
    if (_rd_chrome_zoom_bubble) {
        _settings_val.chrome_zoom_bubble = static_cast<decltype(_settings_val.chrome_zoom_bubble)>(env->GetLongField(_rd_chrome_zoom_bubble, env->GetFieldID(env->GetObjectClass(_rd_chrome_zoom_bubble), "value", "J")));
    }
    _settings_val.size = sizeof(cef_browser_settings_t);
    cef_dictionary_value_t* _extra_info_ptr = extra_info ? reinterpret_cast<cef_dictionary_value_t*>(env->GetLongField(extra_info, env->GetFieldID(env->GetObjectClass(extra_info), "nativePtr", "J"))) : nullptr;
    if (_extra_info_ptr) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_extra_info_ptr); _b->add_ref(_b); }
    cef_request_context_t* _request_context_ptr = request_context ? reinterpret_cast<cef_request_context_t*>(env->GetLongField(request_context, env->GetFieldID(env->GetObjectClass(request_context), "nativePtr", "J"))) : nullptr;
    if (_request_context_ptr) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_request_context_ptr); _b->add_ref(_b); }
    cef_browser_view_delegate_t* _delegate_ptr = delegate ? Create_JniCefBrowserViewDelegate(env, delegate) : nullptr;
    auto _r = cef_browser_view_create(_client_ptr, _url_str, &_settings_val, _extra_info_ptr, _request_context_ptr, _delegate_ptr);
    if (_url_str) cef_string_userfree_free(_url_str);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/views/CefBrowserView$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(views_CefBrowserView), getForBrowser0)(JNIEnv* env, jclass clz, jobject browser) {
    cef_browser_t* _browser_ptr = browser ? reinterpret_cast<cef_browser_t*>(env->GetLongField(browser, env->GetFieldID(env->GetObjectClass(browser), "nativePtr", "J"))) : nullptr;
    if (_browser_ptr) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_browser_ptr); _b->add_ref(_b); }
    auto _r = cef_browser_view_get_for_browser(_browser_ptr);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/views/CefBrowserView$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}
