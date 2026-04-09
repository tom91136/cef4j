// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
#include <jni.h>
#include "include/capi/cef_browser_capi.h"
#include "include/capi/cef_client_capi.h"
#include "include/capi/cef_devtools_message_observer_capi.h"
#include "include/capi/cef_drag_data_capi.h"
#include "include/capi/cef_navigation_entry_capi.h"
#include "include/capi/cef_registration_capi.h"
#include "include/capi/cef_request_context_capi.h"
#include "include/capi/cef_values_capi.h"
#include "jni_util.h"

extern "C" cef_run_file_dialog_callback_t* Create_JniCefRunFileDialogCallback(JNIEnv* env, jobject handler);
extern "C" cef_download_image_callback_t* Create_JniCefDownloadImageCallback(JNIEnv* env, jobject handler);
extern "C" cef_pdf_print_callback_t* Create_JniCefPdfPrintCallback(JNIEnv* env, jobject handler);
extern "C" cef_client_t* Create_JniCefClient(JNIEnv* env, jobject handler);
extern "C" cef_dev_tools_message_observer_t* Create_JniCefDevToolsMessageObserver(JNIEnv* env, jobject handler);
extern "C" cef_navigation_entry_visitor_t* Create_JniCefNavigationEntryVisitor(JNIEnv* env, jobject handler);
CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefBrowserHost), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefBrowserHost), getBrowser0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_browser_host_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_browser(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefBrowser$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefBrowserHost), closeBrowser0)(JNIEnv* env, jobject obj, jlong self, jboolean force_close) {
    auto* s = reinterpret_cast<cef_browser_host_t*>(self);
    if (!s) return;
    s->close_browser(s, static_cast<bool>(force_close));
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefBrowserHost), tryCloseBrowser0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_browser_host_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->try_close_browser(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefBrowserHost), isReadyToBeClosed0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_browser_host_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_ready_to_be_closed(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefBrowserHost), setFocus0)(JNIEnv* env, jobject obj, jlong self, jboolean focus) {
    auto* s = reinterpret_cast<cef_browser_host_t*>(self);
    if (!s) return;
    s->set_focus(s, static_cast<bool>(focus));
}

CEF4J_JNI_EXPORT(jlong, CEF4J_PEER(CefBrowserHost), getWindowHandle0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_browser_host_t*>(self);
    if (!s) return 0;
    return static_cast<jlong>(s->get_window_handle(s));
}

CEF4J_JNI_EXPORT(jlong, CEF4J_PEER(CefBrowserHost), getOpenerWindowHandle0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_browser_host_t*>(self);
    if (!s) return 0;
    return static_cast<jlong>(s->get_opener_window_handle(s));
}

CEF4J_JNI_EXPORT(jint, CEF4J_PEER(CefBrowserHost), getOpenerIdentifier0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_browser_host_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->get_opener_identifier(s));
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefBrowserHost), hasView0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_browser_host_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->has_view(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefBrowserHost), getClient0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_browser_host_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_client(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefClient$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefBrowserHost), getRequestContext0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_browser_host_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_request_context(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefRequestContext$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefBrowserHost), canZoom0)(JNIEnv* env, jobject obj, jlong self, jobject command) {
    auto* s = reinterpret_cast<cef_browser_host_t*>(self);
    if (!s) return JNI_FALSE;
    if (!command) { env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "command must not be null"); return JNI_FALSE; }
    auto _r = s->can_zoom(s, static_cast<cef_zoom_command_t>(env->GetLongField(command, env->GetFieldID(env->GetObjectClass(command), "value", "J"))));
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefBrowserHost), zoom0)(JNIEnv* env, jobject obj, jlong self, jobject command) {
    auto* s = reinterpret_cast<cef_browser_host_t*>(self);
    if (!s) return;
    if (!command) { env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "command must not be null"); return; }
    s->zoom(s, static_cast<cef_zoom_command_t>(env->GetLongField(command, env->GetFieldID(env->GetObjectClass(command), "value", "J"))));
}

CEF4J_JNI_EXPORT(jdouble, CEF4J_PEER(CefBrowserHost), getDefaultZoomLevel0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_browser_host_t*>(self);
    if (!s) return 0;
    return static_cast<jdouble>(s->get_default_zoom_level(s));
}

CEF4J_JNI_EXPORT(jdouble, CEF4J_PEER(CefBrowserHost), getZoomLevel0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_browser_host_t*>(self);
    if (!s) return 0;
    return static_cast<jdouble>(s->get_zoom_level(s));
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefBrowserHost), setZoomLevel0)(JNIEnv* env, jobject obj, jlong self, jdouble zoomLevel) {
    auto* s = reinterpret_cast<cef_browser_host_t*>(self);
    if (!s) return;
    s->set_zoom_level(s, zoomLevel);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefBrowserHost), runFileDialog0)(JNIEnv* env, jobject obj, jlong self, jobject mode, jstring title, jstring default_file_path, jobject accept_filters, jobject callback) {
    auto* s = reinterpret_cast<cef_browser_host_t*>(self);
    if (!s) return;
    if (!mode) { env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "mode must not be null"); return; }
    auto _title_str = title ? JStringToCefString(env, title) : nullptr;
    auto _default_file_path_str = default_file_path ? JStringToCefString(env, default_file_path) : nullptr;
    auto _accept_filters_csl = JavaListToCefStringList(env, accept_filters);
    cef_run_file_dialog_callback_t* _callback_ptr = callback ? Create_JniCefRunFileDialogCallback(env, callback) : nullptr;
    s->run_file_dialog(s, static_cast<cef_file_dialog_mode_t>(env->GetLongField(mode, env->GetFieldID(env->GetObjectClass(mode), "value", "J"))), _title_str, _default_file_path_str, _accept_filters_csl, _callback_ptr);
    if (_title_str) cef_string_userfree_free(_title_str);
    if (_default_file_path_str) cef_string_userfree_free(_default_file_path_str);
    cef_string_list_free(_accept_filters_csl);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefBrowserHost), startDownload0)(JNIEnv* env, jobject obj, jlong self, jstring url) {
    auto* s = reinterpret_cast<cef_browser_host_t*>(self);
    if (!s) return;
    auto _url_str = JStringToCefString(env, url);
    s->start_download(s, _url_str);
    if (_url_str) cef_string_userfree_free(_url_str);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefBrowserHost), downloadImage0)(JNIEnv* env, jobject obj, jlong self, jstring image_url, jboolean is_favicon, jint max_image_size, jboolean bypass_cache, jobject callback) {
    auto* s = reinterpret_cast<cef_browser_host_t*>(self);
    if (!s) return;
    auto _image_url_str = JStringToCefString(env, image_url);
    cef_download_image_callback_t* _callback_ptr = callback ? Create_JniCefDownloadImageCallback(env, callback) : nullptr;
    s->download_image(s, _image_url_str, static_cast<bool>(is_favicon), max_image_size, static_cast<bool>(bypass_cache), _callback_ptr);
    if (_image_url_str) cef_string_userfree_free(_image_url_str);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefBrowserHost), print0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_browser_host_t*>(self);
    if (!s) return;
    s->print(s);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefBrowserHost), printToPdf0)(JNIEnv* env, jobject obj, jlong self, jstring path, jobject settings, jobject callback) {
    auto* s = reinterpret_cast<cef_browser_host_t*>(self);
    if (!s) return;
    if (!settings) { env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "settings must not be null"); return; }
    auto _path_str = JStringToCefString(env, path);
    cef_pdf_print_settings_t _settings_val = {};
    if (settings) {
        auto _c = env->FindClass("net/kurobako/cef4j/gen/CefPdfPrintSettings");
        _settings_val.landscape = static_cast<decltype(_settings_val.landscape)>(env->GetIntField(settings, env->GetFieldID(_c, "landscape", "I")));
        _settings_val.print_background = static_cast<decltype(_settings_val.print_background)>(env->GetIntField(settings, env->GetFieldID(_c, "printBackground", "I")));
        _settings_val.scale = static_cast<decltype(_settings_val.scale)>(env->GetDoubleField(settings, env->GetFieldID(_c, "scale", "D")));
        _settings_val.paper_width = static_cast<decltype(_settings_val.paper_width)>(env->GetDoubleField(settings, env->GetFieldID(_c, "paperWidth", "D")));
        _settings_val.paper_height = static_cast<decltype(_settings_val.paper_height)>(env->GetDoubleField(settings, env->GetFieldID(_c, "paperHeight", "D")));
        _settings_val.prefer_css_page_size = static_cast<decltype(_settings_val.prefer_css_page_size)>(env->GetIntField(settings, env->GetFieldID(_c, "preferCssPageSize", "I")));
        auto _rd_margin_type = env->GetObjectField(settings, env->GetFieldID(_c, "marginType", "Lnet/kurobako/cef4j/gen/CefPdfPrintMarginType;"));
        if (_rd_margin_type) {
            _settings_val.margin_type = static_cast<decltype(_settings_val.margin_type)>(env->GetLongField(_rd_margin_type, env->GetFieldID(env->GetObjectClass(_rd_margin_type), "value", "J")));
        }
        _settings_val.margin_top = static_cast<decltype(_settings_val.margin_top)>(env->GetDoubleField(settings, env->GetFieldID(_c, "marginTop", "D")));
        _settings_val.margin_right = static_cast<decltype(_settings_val.margin_right)>(env->GetDoubleField(settings, env->GetFieldID(_c, "marginRight", "D")));
        _settings_val.margin_bottom = static_cast<decltype(_settings_val.margin_bottom)>(env->GetDoubleField(settings, env->GetFieldID(_c, "marginBottom", "D")));
        _settings_val.margin_left = static_cast<decltype(_settings_val.margin_left)>(env->GetDoubleField(settings, env->GetFieldID(_c, "marginLeft", "D")));
        jstring _rd_page_ranges = (jstring)env->GetObjectField(settings, env->GetFieldID(_c, "pageRanges", "Ljava/lang/String;"));
        if (_rd_page_ranges) {
            const jchar* _rd_page_ranges_chars = env->GetStringChars(_rd_page_ranges, nullptr);
            jsize _rd_page_ranges_len = env->GetStringLength(_rd_page_ranges);
            cef_string_set(reinterpret_cast<const char16_t*>(_rd_page_ranges_chars), _rd_page_ranges_len, &_settings_val.page_ranges, 1);
            env->ReleaseStringChars(_rd_page_ranges, _rd_page_ranges_chars);
        }
        _settings_val.display_header_footer = static_cast<decltype(_settings_val.display_header_footer)>(env->GetIntField(settings, env->GetFieldID(_c, "displayHeaderFooter", "I")));
        jstring _rd_header_template = (jstring)env->GetObjectField(settings, env->GetFieldID(_c, "headerTemplate", "Ljava/lang/String;"));
        if (_rd_header_template) {
            const jchar* _rd_header_template_chars = env->GetStringChars(_rd_header_template, nullptr);
            jsize _rd_header_template_len = env->GetStringLength(_rd_header_template);
            cef_string_set(reinterpret_cast<const char16_t*>(_rd_header_template_chars), _rd_header_template_len, &_settings_val.header_template, 1);
            env->ReleaseStringChars(_rd_header_template, _rd_header_template_chars);
        }
        jstring _rd_footer_template = (jstring)env->GetObjectField(settings, env->GetFieldID(_c, "footerTemplate", "Ljava/lang/String;"));
        if (_rd_footer_template) {
            const jchar* _rd_footer_template_chars = env->GetStringChars(_rd_footer_template, nullptr);
            jsize _rd_footer_template_len = env->GetStringLength(_rd_footer_template);
            cef_string_set(reinterpret_cast<const char16_t*>(_rd_footer_template_chars), _rd_footer_template_len, &_settings_val.footer_template, 1);
            env->ReleaseStringChars(_rd_footer_template, _rd_footer_template_chars);
        }
        _settings_val.generate_tagged_pdf = static_cast<decltype(_settings_val.generate_tagged_pdf)>(env->GetIntField(settings, env->GetFieldID(_c, "generateTaggedPdf", "I")));
        _settings_val.generate_document_outline = static_cast<decltype(_settings_val.generate_document_outline)>(env->GetIntField(settings, env->GetFieldID(_c, "generateDocumentOutline", "I")));
        _settings_val.size = sizeof(cef_pdf_print_settings_t);
    }
    cef_pdf_print_callback_t* _callback_ptr = callback ? Create_JniCefPdfPrintCallback(env, callback) : nullptr;
    s->print_to_pdf(s, _path_str, &_settings_val, _callback_ptr);
    if (_path_str) cef_string_userfree_free(_path_str);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefBrowserHost), find0)(JNIEnv* env, jobject obj, jlong self, jstring searchText, jboolean forward, jboolean matchCase, jboolean findNext) {
    auto* s = reinterpret_cast<cef_browser_host_t*>(self);
    if (!s) return;
    auto _searchText_str = JStringToCefString(env, searchText);
    s->find(s, _searchText_str, static_cast<bool>(forward), static_cast<bool>(matchCase), static_cast<bool>(findNext));
    if (_searchText_str) cef_string_userfree_free(_searchText_str);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefBrowserHost), stopFinding0)(JNIEnv* env, jobject obj, jlong self, jboolean clearSelection) {
    auto* s = reinterpret_cast<cef_browser_host_t*>(self);
    if (!s) return;
    s->stop_finding(s, static_cast<bool>(clearSelection));
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefBrowserHost), showDevTools0)(JNIEnv* env, jobject obj, jlong self, jobject windowInfo, jobject client, jobject settings, jobject inspect_element_at) {
    auto* s = reinterpret_cast<cef_browser_host_t*>(self);
    if (!s) return;
    cef_window_info_t _windowInfo_val = {};
    if (windowInfo) {
        auto _c = env->FindClass("net/kurobako/cef4j/gen/CefWindowInfo");
        jstring _rd_window_name = (jstring)env->GetObjectField(windowInfo, env->GetFieldID(_c, "windowName", "Ljava/lang/String;"));
        if (_rd_window_name) {
            const jchar* _rd_window_name_chars = env->GetStringChars(_rd_window_name, nullptr);
            jsize _rd_window_name_len = env->GetStringLength(_rd_window_name);
            cef_string_set(reinterpret_cast<const char16_t*>(_rd_window_name_chars), _rd_window_name_len, &_windowInfo_val.window_name, 1);
            env->ReleaseStringChars(_rd_window_name, _rd_window_name_chars);
        }
        auto _rd_bounds = env->GetObjectField(windowInfo, env->GetFieldID(_c, "bounds", "Lnet/kurobako/cef4j/gen/CefRect;"));
        if (_rd_bounds) {
            auto _rd_boundsc = env->GetObjectClass(_rd_bounds);
            _windowInfo_val.bounds.x = static_cast<decltype(_windowInfo_val.bounds.x)>(env->GetIntField(_rd_bounds, env->GetFieldID(_rd_boundsc, "x", "I")));
            _windowInfo_val.bounds.y = static_cast<decltype(_windowInfo_val.bounds.y)>(env->GetIntField(_rd_bounds, env->GetFieldID(_rd_boundsc, "y", "I")));
            _windowInfo_val.bounds.width = static_cast<decltype(_windowInfo_val.bounds.width)>(env->GetIntField(_rd_bounds, env->GetFieldID(_rd_boundsc, "width", "I")));
            _windowInfo_val.bounds.height = static_cast<decltype(_windowInfo_val.bounds.height)>(env->GetIntField(_rd_bounds, env->GetFieldID(_rd_boundsc, "height", "I")));
        }
        _windowInfo_val.parent_window = static_cast<decltype(_windowInfo_val.parent_window)>(static_cast<size_t>(env->GetLongField(windowInfo, env->GetFieldID(_c, "parentWindow", "J"))));
        _windowInfo_val.windowless_rendering_enabled = static_cast<decltype(_windowInfo_val.windowless_rendering_enabled)>(env->GetIntField(windowInfo, env->GetFieldID(_c, "windowlessRenderingEnabled", "I")));
        _windowInfo_val.shared_texture_enabled = static_cast<decltype(_windowInfo_val.shared_texture_enabled)>(env->GetIntField(windowInfo, env->GetFieldID(_c, "sharedTextureEnabled", "I")));
        _windowInfo_val.external_begin_frame_enabled = static_cast<decltype(_windowInfo_val.external_begin_frame_enabled)>(env->GetIntField(windowInfo, env->GetFieldID(_c, "externalBeginFrameEnabled", "I")));
        _windowInfo_val.window = static_cast<decltype(_windowInfo_val.window)>(static_cast<size_t>(env->GetLongField(windowInfo, env->GetFieldID(_c, "window", "J"))));
        auto _rd_runtime_style = env->GetObjectField(windowInfo, env->GetFieldID(_c, "runtimeStyle", "Lnet/kurobako/cef4j/gen/CefRuntimeStyle;"));
        if (_rd_runtime_style) {
            _windowInfo_val.runtime_style = static_cast<decltype(_windowInfo_val.runtime_style)>(env->GetLongField(_rd_runtime_style, env->GetFieldID(env->GetObjectClass(_rd_runtime_style), "value", "J")));
        }
        _windowInfo_val.size = sizeof(cef_window_info_t);
    }
    cef_client_t* _client_ptr = client ? Create_JniCefClient(env, client) : nullptr;
    cef_browser_settings_t _settings_val = {};
    if (settings) {
        auto _c = env->FindClass("net/kurobako/cef4j/gen/CefBrowserSettings");
        _settings_val.windowless_frame_rate = static_cast<decltype(_settings_val.windowless_frame_rate)>(env->GetIntField(settings, env->GetFieldID(_c, "windowlessFrameRate", "I")));
        jstring _rd_standard_font_family = (jstring)env->GetObjectField(settings, env->GetFieldID(_c, "standardFontFamily", "Ljava/lang/String;"));
        if (_rd_standard_font_family) {
            const jchar* _rd_standard_font_family_chars = env->GetStringChars(_rd_standard_font_family, nullptr);
            jsize _rd_standard_font_family_len = env->GetStringLength(_rd_standard_font_family);
            cef_string_set(reinterpret_cast<const char16_t*>(_rd_standard_font_family_chars), _rd_standard_font_family_len, &_settings_val.standard_font_family, 1);
            env->ReleaseStringChars(_rd_standard_font_family, _rd_standard_font_family_chars);
        }
        jstring _rd_fixed_font_family = (jstring)env->GetObjectField(settings, env->GetFieldID(_c, "fixedFontFamily", "Ljava/lang/String;"));
        if (_rd_fixed_font_family) {
            const jchar* _rd_fixed_font_family_chars = env->GetStringChars(_rd_fixed_font_family, nullptr);
            jsize _rd_fixed_font_family_len = env->GetStringLength(_rd_fixed_font_family);
            cef_string_set(reinterpret_cast<const char16_t*>(_rd_fixed_font_family_chars), _rd_fixed_font_family_len, &_settings_val.fixed_font_family, 1);
            env->ReleaseStringChars(_rd_fixed_font_family, _rd_fixed_font_family_chars);
        }
        jstring _rd_serif_font_family = (jstring)env->GetObjectField(settings, env->GetFieldID(_c, "serifFontFamily", "Ljava/lang/String;"));
        if (_rd_serif_font_family) {
            const jchar* _rd_serif_font_family_chars = env->GetStringChars(_rd_serif_font_family, nullptr);
            jsize _rd_serif_font_family_len = env->GetStringLength(_rd_serif_font_family);
            cef_string_set(reinterpret_cast<const char16_t*>(_rd_serif_font_family_chars), _rd_serif_font_family_len, &_settings_val.serif_font_family, 1);
            env->ReleaseStringChars(_rd_serif_font_family, _rd_serif_font_family_chars);
        }
        jstring _rd_sans_serif_font_family = (jstring)env->GetObjectField(settings, env->GetFieldID(_c, "sansSerifFontFamily", "Ljava/lang/String;"));
        if (_rd_sans_serif_font_family) {
            const jchar* _rd_sans_serif_font_family_chars = env->GetStringChars(_rd_sans_serif_font_family, nullptr);
            jsize _rd_sans_serif_font_family_len = env->GetStringLength(_rd_sans_serif_font_family);
            cef_string_set(reinterpret_cast<const char16_t*>(_rd_sans_serif_font_family_chars), _rd_sans_serif_font_family_len, &_settings_val.sans_serif_font_family, 1);
            env->ReleaseStringChars(_rd_sans_serif_font_family, _rd_sans_serif_font_family_chars);
        }
        jstring _rd_cursive_font_family = (jstring)env->GetObjectField(settings, env->GetFieldID(_c, "cursiveFontFamily", "Ljava/lang/String;"));
        if (_rd_cursive_font_family) {
            const jchar* _rd_cursive_font_family_chars = env->GetStringChars(_rd_cursive_font_family, nullptr);
            jsize _rd_cursive_font_family_len = env->GetStringLength(_rd_cursive_font_family);
            cef_string_set(reinterpret_cast<const char16_t*>(_rd_cursive_font_family_chars), _rd_cursive_font_family_len, &_settings_val.cursive_font_family, 1);
            env->ReleaseStringChars(_rd_cursive_font_family, _rd_cursive_font_family_chars);
        }
        jstring _rd_fantasy_font_family = (jstring)env->GetObjectField(settings, env->GetFieldID(_c, "fantasyFontFamily", "Ljava/lang/String;"));
        if (_rd_fantasy_font_family) {
            const jchar* _rd_fantasy_font_family_chars = env->GetStringChars(_rd_fantasy_font_family, nullptr);
            jsize _rd_fantasy_font_family_len = env->GetStringLength(_rd_fantasy_font_family);
            cef_string_set(reinterpret_cast<const char16_t*>(_rd_fantasy_font_family_chars), _rd_fantasy_font_family_len, &_settings_val.fantasy_font_family, 1);
            env->ReleaseStringChars(_rd_fantasy_font_family, _rd_fantasy_font_family_chars);
        }
        _settings_val.default_font_size = static_cast<decltype(_settings_val.default_font_size)>(env->GetIntField(settings, env->GetFieldID(_c, "defaultFontSize", "I")));
        _settings_val.default_fixed_font_size = static_cast<decltype(_settings_val.default_fixed_font_size)>(env->GetIntField(settings, env->GetFieldID(_c, "defaultFixedFontSize", "I")));
        _settings_val.minimum_font_size = static_cast<decltype(_settings_val.minimum_font_size)>(env->GetIntField(settings, env->GetFieldID(_c, "minimumFontSize", "I")));
        _settings_val.minimum_logical_font_size = static_cast<decltype(_settings_val.minimum_logical_font_size)>(env->GetIntField(settings, env->GetFieldID(_c, "minimumLogicalFontSize", "I")));
        jstring _rd_default_encoding = (jstring)env->GetObjectField(settings, env->GetFieldID(_c, "defaultEncoding", "Ljava/lang/String;"));
        if (_rd_default_encoding) {
            const jchar* _rd_default_encoding_chars = env->GetStringChars(_rd_default_encoding, nullptr);
            jsize _rd_default_encoding_len = env->GetStringLength(_rd_default_encoding);
            cef_string_set(reinterpret_cast<const char16_t*>(_rd_default_encoding_chars), _rd_default_encoding_len, &_settings_val.default_encoding, 1);
            env->ReleaseStringChars(_rd_default_encoding, _rd_default_encoding_chars);
        }
        auto _rd_remote_fonts = env->GetObjectField(settings, env->GetFieldID(_c, "remoteFonts", "Lnet/kurobako/cef4j/gen/CefState;"));
        if (_rd_remote_fonts) {
            _settings_val.remote_fonts = static_cast<decltype(_settings_val.remote_fonts)>(env->GetLongField(_rd_remote_fonts, env->GetFieldID(env->GetObjectClass(_rd_remote_fonts), "value", "J")));
        }
        auto _rd_javascript = env->GetObjectField(settings, env->GetFieldID(_c, "javascript", "Lnet/kurobako/cef4j/gen/CefState;"));
        if (_rd_javascript) {
            _settings_val.javascript = static_cast<decltype(_settings_val.javascript)>(env->GetLongField(_rd_javascript, env->GetFieldID(env->GetObjectClass(_rd_javascript), "value", "J")));
        }
        auto _rd_javascript_close_windows = env->GetObjectField(settings, env->GetFieldID(_c, "javascriptCloseWindows", "Lnet/kurobako/cef4j/gen/CefState;"));
        if (_rd_javascript_close_windows) {
            _settings_val.javascript_close_windows = static_cast<decltype(_settings_val.javascript_close_windows)>(env->GetLongField(_rd_javascript_close_windows, env->GetFieldID(env->GetObjectClass(_rd_javascript_close_windows), "value", "J")));
        }
        auto _rd_javascript_access_clipboard = env->GetObjectField(settings, env->GetFieldID(_c, "javascriptAccessClipboard", "Lnet/kurobako/cef4j/gen/CefState;"));
        if (_rd_javascript_access_clipboard) {
            _settings_val.javascript_access_clipboard = static_cast<decltype(_settings_val.javascript_access_clipboard)>(env->GetLongField(_rd_javascript_access_clipboard, env->GetFieldID(env->GetObjectClass(_rd_javascript_access_clipboard), "value", "J")));
        }
        auto _rd_javascript_dom_paste = env->GetObjectField(settings, env->GetFieldID(_c, "javascriptDomPaste", "Lnet/kurobako/cef4j/gen/CefState;"));
        if (_rd_javascript_dom_paste) {
            _settings_val.javascript_dom_paste = static_cast<decltype(_settings_val.javascript_dom_paste)>(env->GetLongField(_rd_javascript_dom_paste, env->GetFieldID(env->GetObjectClass(_rd_javascript_dom_paste), "value", "J")));
        }
        auto _rd_image_loading = env->GetObjectField(settings, env->GetFieldID(_c, "imageLoading", "Lnet/kurobako/cef4j/gen/CefState;"));
        if (_rd_image_loading) {
            _settings_val.image_loading = static_cast<decltype(_settings_val.image_loading)>(env->GetLongField(_rd_image_loading, env->GetFieldID(env->GetObjectClass(_rd_image_loading), "value", "J")));
        }
        auto _rd_image_shrink_standalone_to_fit = env->GetObjectField(settings, env->GetFieldID(_c, "imageShrinkStandaloneToFit", "Lnet/kurobako/cef4j/gen/CefState;"));
        if (_rd_image_shrink_standalone_to_fit) {
            _settings_val.image_shrink_standalone_to_fit = static_cast<decltype(_settings_val.image_shrink_standalone_to_fit)>(env->GetLongField(_rd_image_shrink_standalone_to_fit, env->GetFieldID(env->GetObjectClass(_rd_image_shrink_standalone_to_fit), "value", "J")));
        }
        auto _rd_text_area_resize = env->GetObjectField(settings, env->GetFieldID(_c, "textAreaResize", "Lnet/kurobako/cef4j/gen/CefState;"));
        if (_rd_text_area_resize) {
            _settings_val.text_area_resize = static_cast<decltype(_settings_val.text_area_resize)>(env->GetLongField(_rd_text_area_resize, env->GetFieldID(env->GetObjectClass(_rd_text_area_resize), "value", "J")));
        }
        auto _rd_tab_to_links = env->GetObjectField(settings, env->GetFieldID(_c, "tabToLinks", "Lnet/kurobako/cef4j/gen/CefState;"));
        if (_rd_tab_to_links) {
            _settings_val.tab_to_links = static_cast<decltype(_settings_val.tab_to_links)>(env->GetLongField(_rd_tab_to_links, env->GetFieldID(env->GetObjectClass(_rd_tab_to_links), "value", "J")));
        }
        auto _rd_local_storage = env->GetObjectField(settings, env->GetFieldID(_c, "localStorage", "Lnet/kurobako/cef4j/gen/CefState;"));
        if (_rd_local_storage) {
            _settings_val.local_storage = static_cast<decltype(_settings_val.local_storage)>(env->GetLongField(_rd_local_storage, env->GetFieldID(env->GetObjectClass(_rd_local_storage), "value", "J")));
        }
        auto _rd_databases_deprecated = env->GetObjectField(settings, env->GetFieldID(_c, "databasesDeprecated", "Lnet/kurobako/cef4j/gen/CefState;"));
        if (_rd_databases_deprecated) {
            _settings_val.databases_deprecated = static_cast<decltype(_settings_val.databases_deprecated)>(env->GetLongField(_rd_databases_deprecated, env->GetFieldID(env->GetObjectClass(_rd_databases_deprecated), "value", "J")));
        }
        auto _rd_webgl = env->GetObjectField(settings, env->GetFieldID(_c, "webgl", "Lnet/kurobako/cef4j/gen/CefState;"));
        if (_rd_webgl) {
            _settings_val.webgl = static_cast<decltype(_settings_val.webgl)>(env->GetLongField(_rd_webgl, env->GetFieldID(env->GetObjectClass(_rd_webgl), "value", "J")));
        }
        _settings_val.background_color = static_cast<decltype(_settings_val.background_color)>(env->GetIntField(settings, env->GetFieldID(_c, "backgroundColor", "I")));
        auto _rd_chrome_status_bubble = env->GetObjectField(settings, env->GetFieldID(_c, "chromeStatusBubble", "Lnet/kurobako/cef4j/gen/CefState;"));
        if (_rd_chrome_status_bubble) {
            _settings_val.chrome_status_bubble = static_cast<decltype(_settings_val.chrome_status_bubble)>(env->GetLongField(_rd_chrome_status_bubble, env->GetFieldID(env->GetObjectClass(_rd_chrome_status_bubble), "value", "J")));
        }
        auto _rd_chrome_zoom_bubble = env->GetObjectField(settings, env->GetFieldID(_c, "chromeZoomBubble", "Lnet/kurobako/cef4j/gen/CefState;"));
        if (_rd_chrome_zoom_bubble) {
            _settings_val.chrome_zoom_bubble = static_cast<decltype(_settings_val.chrome_zoom_bubble)>(env->GetLongField(_rd_chrome_zoom_bubble, env->GetFieldID(env->GetObjectClass(_rd_chrome_zoom_bubble), "value", "J")));
        }
        auto _rd_ax_viewport_collapse = env->GetObjectField(settings, env->GetFieldID(_c, "axViewportCollapse", "Lnet/kurobako/cef4j/gen/CefState;"));
        if (_rd_ax_viewport_collapse) {
            _settings_val.ax_viewport_collapse = static_cast<decltype(_settings_val.ax_viewport_collapse)>(env->GetLongField(_rd_ax_viewport_collapse, env->GetFieldID(env->GetObjectClass(_rd_ax_viewport_collapse), "value", "J")));
        }
        _settings_val.size = sizeof(cef_browser_settings_t);
    }
    cef_point_t _inspect_element_at_val = {};
    if (inspect_element_at) {
        auto _c = env->FindClass("net/kurobako/cef4j/gen/CefPoint");
        _inspect_element_at_val.x = static_cast<decltype(_inspect_element_at_val.x)>(env->GetIntField(inspect_element_at, env->GetFieldID(_c, "x", "I")));
        _inspect_element_at_val.y = static_cast<decltype(_inspect_element_at_val.y)>(env->GetIntField(inspect_element_at, env->GetFieldID(_c, "y", "I")));
    }
    s->show_dev_tools(s, &_windowInfo_val, _client_ptr, &_settings_val, &_inspect_element_at_val);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefBrowserHost), closeDevTools0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_browser_host_t*>(self);
    if (!s) return;
    s->close_dev_tools(s);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefBrowserHost), hasDevTools0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_browser_host_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->has_dev_tools(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefBrowserHost), sendDevToolsMessage0)(JNIEnv* env, jobject obj, jlong self, jobject message) {
    auto* s = reinterpret_cast<cef_browser_host_t*>(self);
    if (!s) return JNI_FALSE;
    if (!message) { env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "message must not be null"); return JNI_FALSE; }
    const void* _message_addr = message ? env->GetDirectBufferAddress(message) : nullptr;
    if (message && !_message_addr) { env->ThrowNew(env->FindClass("java/lang/IllegalArgumentException"), "message must be a direct ByteBuffer; use ByteBuffer.allocateDirect(...)"); return JNI_FALSE; }
    auto _r = s->send_dev_tools_message(s, _message_addr, static_cast<size_t>(env->GetDirectBufferCapacity(message)));
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jint, CEF4J_PEER(CefBrowserHost), executeDevToolsMethod0)(JNIEnv* env, jobject obj, jlong self, jint message_id, jstring method, jobject params) {
    auto* s = reinterpret_cast<cef_browser_host_t*>(self);
    if (!s) return 0;
    auto _method_str = JStringToCefString(env, method);
    cef_dictionary_value_t* _params_ptr = params ? reinterpret_cast<cef_dictionary_value_t*>(env->GetLongField(params, env->GetFieldID(env->GetObjectClass(params), "nativePtr", "J"))) : nullptr;
    if (_params_ptr) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_params_ptr); _b->add_ref(_b); }
    return static_cast<jint>(s->execute_dev_tools_method(s, message_id, _method_str, _params_ptr));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefBrowserHost), addDevToolsMessageObserver0)(JNIEnv* env, jobject obj, jlong self, jobject observer) {
    auto* s = reinterpret_cast<cef_browser_host_t*>(self);
    if (!s) return nullptr;
    cef_dev_tools_message_observer_t* _observer_ptr = observer ? Create_JniCefDevToolsMessageObserver(env, observer) : nullptr;
    auto _r = s->add_dev_tools_message_observer(s, _observer_ptr);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefRegistration$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefBrowserHost), getNavigationEntries0)(JNIEnv* env, jobject obj, jlong self, jobject visitor, jboolean current_only) {
    auto* s = reinterpret_cast<cef_browser_host_t*>(self);
    if (!s) return;
    cef_navigation_entry_visitor_t* _visitor_ptr = visitor ? Create_JniCefNavigationEntryVisitor(env, visitor) : nullptr;
    s->get_navigation_entries(s, _visitor_ptr, static_cast<bool>(current_only));
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefBrowserHost), replaceMisspelling0)(JNIEnv* env, jobject obj, jlong self, jstring word) {
    auto* s = reinterpret_cast<cef_browser_host_t*>(self);
    if (!s) return;
    auto _word_str = JStringToCefString(env, word);
    s->replace_misspelling(s, _word_str);
    if (_word_str) cef_string_userfree_free(_word_str);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefBrowserHost), addWordToDictionary0)(JNIEnv* env, jobject obj, jlong self, jstring word) {
    auto* s = reinterpret_cast<cef_browser_host_t*>(self);
    if (!s) return;
    auto _word_str = JStringToCefString(env, word);
    s->add_word_to_dictionary(s, _word_str);
    if (_word_str) cef_string_userfree_free(_word_str);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefBrowserHost), isWindowRenderingDisabled0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_browser_host_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_window_rendering_disabled(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefBrowserHost), wasResized0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_browser_host_t*>(self);
    if (!s) return;
    s->was_resized(s);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefBrowserHost), wasHidden0)(JNIEnv* env, jobject obj, jlong self, jboolean hidden) {
    auto* s = reinterpret_cast<cef_browser_host_t*>(self);
    if (!s) return;
    s->was_hidden(s, static_cast<bool>(hidden));
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefBrowserHost), notifyScreenInfoChanged0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_browser_host_t*>(self);
    if (!s) return;
    s->notify_screen_info_changed(s);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefBrowserHost), invalidate0)(JNIEnv* env, jobject obj, jlong self, jobject type) {
    auto* s = reinterpret_cast<cef_browser_host_t*>(self);
    if (!s) return;
    if (!type) { env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "type must not be null"); return; }
    s->invalidate(s, static_cast<cef_paint_element_type_t>(env->GetLongField(type, env->GetFieldID(env->GetObjectClass(type), "value", "J"))));
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefBrowserHost), sendExternalBeginFrame0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_browser_host_t*>(self);
    if (!s) return;
    s->send_external_begin_frame(s);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefBrowserHost), sendKeyEvent0)(JNIEnv* env, jobject obj, jlong self, jobject event) {
    auto* s = reinterpret_cast<cef_browser_host_t*>(self);
    if (!s) return;
    if (!event) { env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "event must not be null"); return; }
    cef_key_event_t _event_val = {};
    if (event) {
        auto _c = env->FindClass("net/kurobako/cef4j/gen/CefKeyEvent");
        auto _rd_type = env->GetObjectField(event, env->GetFieldID(_c, "type", "Lnet/kurobako/cef4j/gen/CefKeyEventType;"));
        if (_rd_type) {
            _event_val.type = static_cast<decltype(_event_val.type)>(env->GetLongField(_rd_type, env->GetFieldID(env->GetObjectClass(_rd_type), "value", "J")));
        }
        _event_val.modifiers = static_cast<decltype(_event_val.modifiers)>(env->GetIntField(event, env->GetFieldID(_c, "modifiers", "I")));
        _event_val.windows_key_code = static_cast<decltype(_event_val.windows_key_code)>(env->GetIntField(event, env->GetFieldID(_c, "windowsKeyCode", "I")));
        _event_val.native_key_code = static_cast<decltype(_event_val.native_key_code)>(env->GetIntField(event, env->GetFieldID(_c, "nativeKeyCode", "I")));
        _event_val.is_system_key = static_cast<decltype(_event_val.is_system_key)>(env->GetIntField(event, env->GetFieldID(_c, "isSystemKey", "I")));
        _event_val.character = static_cast<decltype(_event_val.character)>(env->GetCharField(event, env->GetFieldID(_c, "character", "C")));
        _event_val.unmodified_character = static_cast<decltype(_event_val.unmodified_character)>(env->GetCharField(event, env->GetFieldID(_c, "unmodifiedCharacter", "C")));
        _event_val.focus_on_editable_field = static_cast<decltype(_event_val.focus_on_editable_field)>(env->GetIntField(event, env->GetFieldID(_c, "focusOnEditableField", "I")));
        _event_val.size = sizeof(cef_key_event_t);
    }
    s->send_key_event(s, &_event_val);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefBrowserHost), sendMouseClickEvent0)(JNIEnv* env, jobject obj, jlong self, jobject event, jobject type, jboolean mouseUp, jint clickCount) {
    auto* s = reinterpret_cast<cef_browser_host_t*>(self);
    if (!s) return;
    if (!event) { env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "event must not be null"); return; }
    if (!type) { env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "type must not be null"); return; }
    cef_mouse_event_t _event_val = {};
    if (event) {
        auto _c = env->FindClass("net/kurobako/cef4j/gen/CefMouseEvent");
        _event_val.x = static_cast<decltype(_event_val.x)>(env->GetIntField(event, env->GetFieldID(_c, "x", "I")));
        _event_val.y = static_cast<decltype(_event_val.y)>(env->GetIntField(event, env->GetFieldID(_c, "y", "I")));
        _event_val.modifiers = static_cast<decltype(_event_val.modifiers)>(env->GetIntField(event, env->GetFieldID(_c, "modifiers", "I")));
    }
    s->send_mouse_click_event(s, &_event_val, static_cast<cef_mouse_button_type_t>(env->GetLongField(type, env->GetFieldID(env->GetObjectClass(type), "value", "J"))), static_cast<bool>(mouseUp), clickCount);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefBrowserHost), sendMouseMoveEvent0)(JNIEnv* env, jobject obj, jlong self, jobject event, jboolean mouseLeave) {
    auto* s = reinterpret_cast<cef_browser_host_t*>(self);
    if (!s) return;
    if (!event) { env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "event must not be null"); return; }
    cef_mouse_event_t _event_val = {};
    if (event) {
        auto _c = env->FindClass("net/kurobako/cef4j/gen/CefMouseEvent");
        _event_val.x = static_cast<decltype(_event_val.x)>(env->GetIntField(event, env->GetFieldID(_c, "x", "I")));
        _event_val.y = static_cast<decltype(_event_val.y)>(env->GetIntField(event, env->GetFieldID(_c, "y", "I")));
        _event_val.modifiers = static_cast<decltype(_event_val.modifiers)>(env->GetIntField(event, env->GetFieldID(_c, "modifiers", "I")));
    }
    s->send_mouse_move_event(s, &_event_val, static_cast<bool>(mouseLeave));
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefBrowserHost), sendMouseWheelEvent0)(JNIEnv* env, jobject obj, jlong self, jobject event, jint deltaX, jint deltaY) {
    auto* s = reinterpret_cast<cef_browser_host_t*>(self);
    if (!s) return;
    if (!event) { env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "event must not be null"); return; }
    cef_mouse_event_t _event_val = {};
    if (event) {
        auto _c = env->FindClass("net/kurobako/cef4j/gen/CefMouseEvent");
        _event_val.x = static_cast<decltype(_event_val.x)>(env->GetIntField(event, env->GetFieldID(_c, "x", "I")));
        _event_val.y = static_cast<decltype(_event_val.y)>(env->GetIntField(event, env->GetFieldID(_c, "y", "I")));
        _event_val.modifiers = static_cast<decltype(_event_val.modifiers)>(env->GetIntField(event, env->GetFieldID(_c, "modifiers", "I")));
    }
    s->send_mouse_wheel_event(s, &_event_val, deltaX, deltaY);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefBrowserHost), sendTouchEvent0)(JNIEnv* env, jobject obj, jlong self, jobject event) {
    auto* s = reinterpret_cast<cef_browser_host_t*>(self);
    if (!s) return;
    if (!event) { env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "event must not be null"); return; }
    cef_touch_event_t _event_val = {};
    if (event) {
        auto _c = env->FindClass("net/kurobako/cef4j/gen/CefTouchEvent");
        _event_val.id = static_cast<decltype(_event_val.id)>(env->GetIntField(event, env->GetFieldID(_c, "id", "I")));
        _event_val.x = static_cast<decltype(_event_val.x)>(env->GetFloatField(event, env->GetFieldID(_c, "x", "F")));
        _event_val.y = static_cast<decltype(_event_val.y)>(env->GetFloatField(event, env->GetFieldID(_c, "y", "F")));
        _event_val.radius_x = static_cast<decltype(_event_val.radius_x)>(env->GetFloatField(event, env->GetFieldID(_c, "radiusX", "F")));
        _event_val.radius_y = static_cast<decltype(_event_val.radius_y)>(env->GetFloatField(event, env->GetFieldID(_c, "radiusY", "F")));
        _event_val.rotation_angle = static_cast<decltype(_event_val.rotation_angle)>(env->GetFloatField(event, env->GetFieldID(_c, "rotationAngle", "F")));
        _event_val.pressure = static_cast<decltype(_event_val.pressure)>(env->GetFloatField(event, env->GetFieldID(_c, "pressure", "F")));
        auto _rd_type = env->GetObjectField(event, env->GetFieldID(_c, "type", "Lnet/kurobako/cef4j/gen/CefTouchEventType;"));
        if (_rd_type) {
            _event_val.type = static_cast<decltype(_event_val.type)>(env->GetLongField(_rd_type, env->GetFieldID(env->GetObjectClass(_rd_type), "value", "J")));
        }
        _event_val.modifiers = static_cast<decltype(_event_val.modifiers)>(env->GetIntField(event, env->GetFieldID(_c, "modifiers", "I")));
        auto _rd_pointer_type = env->GetObjectField(event, env->GetFieldID(_c, "pointerType", "Lnet/kurobako/cef4j/gen/CefPointerType;"));
        if (_rd_pointer_type) {
            _event_val.pointer_type = static_cast<decltype(_event_val.pointer_type)>(env->GetLongField(_rd_pointer_type, env->GetFieldID(env->GetObjectClass(_rd_pointer_type), "value", "J")));
        }
    }
    s->send_touch_event(s, &_event_val);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefBrowserHost), sendCaptureLostEvent0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_browser_host_t*>(self);
    if (!s) return;
    s->send_capture_lost_event(s);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefBrowserHost), notifyMoveOrResizeStarted0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_browser_host_t*>(self);
    if (!s) return;
    s->notify_move_or_resize_started(s);
}

CEF4J_JNI_EXPORT(jint, CEF4J_PEER(CefBrowserHost), getWindowlessFrameRate0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_browser_host_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->get_windowless_frame_rate(s));
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefBrowserHost), setWindowlessFrameRate0)(JNIEnv* env, jobject obj, jlong self, jint frame_rate) {
    auto* s = reinterpret_cast<cef_browser_host_t*>(self);
    if (!s) return;
    s->set_windowless_frame_rate(s, frame_rate);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefBrowserHost), imeSetComposition0)(JNIEnv* env, jobject obj, jlong self, jstring text, jlong underlinesCount, jobjectArray underlines, jobject replacement_range, jobject selection_range) {
    auto* s = reinterpret_cast<cef_browser_host_t*>(self);
    if (!s) return;
    if (!replacement_range) { env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "replacementRange must not be null"); return; }
    if (!selection_range) { env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "selectionRange must not be null"); return; }
    auto _text_str = text ? JStringToCefString(env, text) : nullptr;
    size_t _underlines_sz = static_cast<size_t>(underlinesCount);
    cef_composition_underline_t* _underlines_arr = _underlines_sz > 0 ? new cef_composition_underline_t[_underlines_sz]() : nullptr;
    { auto _bvac = env->FindClass("net/kurobako/cef4j/gen/CefCompositionUnderline");
    for (size_t _i = 0; _i < _underlines_sz; _i++) {
        auto _elem = env->GetObjectArrayElement(underlines, _i);
        if (_elem) {
            auto _rd_range = env->GetObjectField(_elem, env->GetFieldID(_bvac, "range", "Lnet/kurobako/cef4j/gen/CefRange;"));
            if (_rd_range) {
                auto _rd_rangec = env->GetObjectClass(_rd_range);
                _underlines_arr[_i].range.from = static_cast<decltype(_underlines_arr[_i].range.from)>(env->GetIntField(_rd_range, env->GetFieldID(_rd_rangec, "from", "I")));
                _underlines_arr[_i].range.to = static_cast<decltype(_underlines_arr[_i].range.to)>(env->GetIntField(_rd_range, env->GetFieldID(_rd_rangec, "to", "I")));
            }
            _underlines_arr[_i].color = static_cast<decltype(_underlines_arr[_i].color)>(env->GetIntField(_elem, env->GetFieldID(_bvac, "color", "I")));
            _underlines_arr[_i].background_color = static_cast<decltype(_underlines_arr[_i].background_color)>(env->GetIntField(_elem, env->GetFieldID(_bvac, "backgroundColor", "I")));
            _underlines_arr[_i].thick = static_cast<decltype(_underlines_arr[_i].thick)>(env->GetIntField(_elem, env->GetFieldID(_bvac, "thick", "I")));
            auto _rd_style = env->GetObjectField(_elem, env->GetFieldID(_bvac, "style", "Lnet/kurobako/cef4j/gen/CefCompositionUnderlineStyle;"));
            if (_rd_style) {
                _underlines_arr[_i].style = static_cast<decltype(_underlines_arr[_i].style)>(env->GetLongField(_rd_style, env->GetFieldID(env->GetObjectClass(_rd_style), "value", "J")));
            }
            _underlines_arr[_i].size = sizeof(cef_composition_underline_t);
        }
    } }
    cef_range_t _replacement_range_val = {};
    if (replacement_range) {
        auto _c = env->FindClass("net/kurobako/cef4j/gen/CefRange");
        _replacement_range_val.from = static_cast<decltype(_replacement_range_val.from)>(env->GetIntField(replacement_range, env->GetFieldID(_c, "from", "I")));
        _replacement_range_val.to = static_cast<decltype(_replacement_range_val.to)>(env->GetIntField(replacement_range, env->GetFieldID(_c, "to", "I")));
    }
    cef_range_t _selection_range_val = {};
    if (selection_range) {
        auto _c = env->FindClass("net/kurobako/cef4j/gen/CefRange");
        _selection_range_val.from = static_cast<decltype(_selection_range_val.from)>(env->GetIntField(selection_range, env->GetFieldID(_c, "from", "I")));
        _selection_range_val.to = static_cast<decltype(_selection_range_val.to)>(env->GetIntField(selection_range, env->GetFieldID(_c, "to", "I")));
    }
    s->ime_set_composition(s, _text_str, underlinesCount, _underlines_arr, &_replacement_range_val, &_selection_range_val);
    if (_text_str) cef_string_userfree_free(_text_str);
    delete[] _underlines_arr;
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefBrowserHost), imeCommitText0)(JNIEnv* env, jobject obj, jlong self, jstring text, jobject replacement_range, jint relative_cursor_pos) {
    auto* s = reinterpret_cast<cef_browser_host_t*>(self);
    if (!s) return;
    if (!replacement_range) { env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "replacementRange must not be null"); return; }
    auto _text_str = text ? JStringToCefString(env, text) : nullptr;
    cef_range_t _replacement_range_val = {};
    if (replacement_range) {
        auto _c = env->FindClass("net/kurobako/cef4j/gen/CefRange");
        _replacement_range_val.from = static_cast<decltype(_replacement_range_val.from)>(env->GetIntField(replacement_range, env->GetFieldID(_c, "from", "I")));
        _replacement_range_val.to = static_cast<decltype(_replacement_range_val.to)>(env->GetIntField(replacement_range, env->GetFieldID(_c, "to", "I")));
    }
    s->ime_commit_text(s, _text_str, &_replacement_range_val, relative_cursor_pos);
    if (_text_str) cef_string_userfree_free(_text_str);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefBrowserHost), imeFinishComposingText0)(JNIEnv* env, jobject obj, jlong self, jboolean keep_selection) {
    auto* s = reinterpret_cast<cef_browser_host_t*>(self);
    if (!s) return;
    s->ime_finish_composing_text(s, static_cast<bool>(keep_selection));
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefBrowserHost), imeCancelComposition0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_browser_host_t*>(self);
    if (!s) return;
    s->ime_cancel_composition(s);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefBrowserHost), dragTargetDragEnter0)(JNIEnv* env, jobject obj, jlong self, jobject drag_data, jobject event, jobject allowed_ops) {
    auto* s = reinterpret_cast<cef_browser_host_t*>(self);
    if (!s) return;
    if (!event) { env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "event must not be null"); return; }
    if (!allowed_ops) { env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "allowedOps must not be null"); return; }
    cef_drag_data_t* _drag_data_ptr = drag_data ? reinterpret_cast<cef_drag_data_t*>(env->GetLongField(drag_data, env->GetFieldID(env->GetObjectClass(drag_data), "nativePtr", "J"))) : nullptr;
    if (_drag_data_ptr) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_drag_data_ptr); _b->add_ref(_b); }
    cef_mouse_event_t _event_val = {};
    if (event) {
        auto _c = env->FindClass("net/kurobako/cef4j/gen/CefMouseEvent");
        _event_val.x = static_cast<decltype(_event_val.x)>(env->GetIntField(event, env->GetFieldID(_c, "x", "I")));
        _event_val.y = static_cast<decltype(_event_val.y)>(env->GetIntField(event, env->GetFieldID(_c, "y", "I")));
        _event_val.modifiers = static_cast<decltype(_event_val.modifiers)>(env->GetIntField(event, env->GetFieldID(_c, "modifiers", "I")));
    }
    s->drag_target_drag_enter(s, _drag_data_ptr, &_event_val, static_cast<cef_drag_operations_mask_t>(env->GetLongField(allowed_ops, env->GetFieldID(env->GetObjectClass(allowed_ops), "value", "J"))));
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefBrowserHost), dragTargetDragOver0)(JNIEnv* env, jobject obj, jlong self, jobject event, jobject allowed_ops) {
    auto* s = reinterpret_cast<cef_browser_host_t*>(self);
    if (!s) return;
    if (!event) { env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "event must not be null"); return; }
    if (!allowed_ops) { env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "allowedOps must not be null"); return; }
    cef_mouse_event_t _event_val = {};
    if (event) {
        auto _c = env->FindClass("net/kurobako/cef4j/gen/CefMouseEvent");
        _event_val.x = static_cast<decltype(_event_val.x)>(env->GetIntField(event, env->GetFieldID(_c, "x", "I")));
        _event_val.y = static_cast<decltype(_event_val.y)>(env->GetIntField(event, env->GetFieldID(_c, "y", "I")));
        _event_val.modifiers = static_cast<decltype(_event_val.modifiers)>(env->GetIntField(event, env->GetFieldID(_c, "modifiers", "I")));
    }
    s->drag_target_drag_over(s, &_event_val, static_cast<cef_drag_operations_mask_t>(env->GetLongField(allowed_ops, env->GetFieldID(env->GetObjectClass(allowed_ops), "value", "J"))));
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefBrowserHost), dragTargetDragLeave0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_browser_host_t*>(self);
    if (!s) return;
    s->drag_target_drag_leave(s);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefBrowserHost), dragTargetDrop0)(JNIEnv* env, jobject obj, jlong self, jobject event) {
    auto* s = reinterpret_cast<cef_browser_host_t*>(self);
    if (!s) return;
    if (!event) { env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "event must not be null"); return; }
    cef_mouse_event_t _event_val = {};
    if (event) {
        auto _c = env->FindClass("net/kurobako/cef4j/gen/CefMouseEvent");
        _event_val.x = static_cast<decltype(_event_val.x)>(env->GetIntField(event, env->GetFieldID(_c, "x", "I")));
        _event_val.y = static_cast<decltype(_event_val.y)>(env->GetIntField(event, env->GetFieldID(_c, "y", "I")));
        _event_val.modifiers = static_cast<decltype(_event_val.modifiers)>(env->GetIntField(event, env->GetFieldID(_c, "modifiers", "I")));
    }
    s->drag_target_drop(s, &_event_val);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefBrowserHost), dragSourceEndedAt0)(JNIEnv* env, jobject obj, jlong self, jint x, jint y, jobject op) {
    auto* s = reinterpret_cast<cef_browser_host_t*>(self);
    if (!s) return;
    if (!op) { env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "op must not be null"); return; }
    s->drag_source_ended_at(s, x, y, static_cast<cef_drag_operations_mask_t>(env->GetLongField(op, env->GetFieldID(env->GetObjectClass(op), "value", "J"))));
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefBrowserHost), dragSourceSystemDragEnded0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_browser_host_t*>(self);
    if (!s) return;
    s->drag_source_system_drag_ended(s);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefBrowserHost), getVisibleNavigationEntry0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_browser_host_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_visible_navigation_entry(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefNavigationEntry$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefBrowserHost), setAccessibilityState0)(JNIEnv* env, jobject obj, jlong self, jobject accessibility_state) {
    auto* s = reinterpret_cast<cef_browser_host_t*>(self);
    if (!s) return;
    if (!accessibility_state) { env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "accessibilityState must not be null"); return; }
    s->set_accessibility_state(s, static_cast<cef_state_t>(env->GetLongField(accessibility_state, env->GetFieldID(env->GetObjectClass(accessibility_state), "value", "J"))));
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefBrowserHost), setAutoResizeEnabled0)(JNIEnv* env, jobject obj, jlong self, jboolean enabled, jobject min_size, jobject max_size) {
    auto* s = reinterpret_cast<cef_browser_host_t*>(self);
    if (!s) return;
    if (!min_size) { env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "minSize must not be null"); return; }
    if (!max_size) { env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "maxSize must not be null"); return; }
    cef_size_t _min_size_val = {};
    if (min_size) {
        auto _c = env->FindClass("net/kurobako/cef4j/gen/CefSize");
        _min_size_val.width = static_cast<decltype(_min_size_val.width)>(env->GetIntField(min_size, env->GetFieldID(_c, "width", "I")));
        _min_size_val.height = static_cast<decltype(_min_size_val.height)>(env->GetIntField(min_size, env->GetFieldID(_c, "height", "I")));
    }
    cef_size_t _max_size_val = {};
    if (max_size) {
        auto _c = env->FindClass("net/kurobako/cef4j/gen/CefSize");
        _max_size_val.width = static_cast<decltype(_max_size_val.width)>(env->GetIntField(max_size, env->GetFieldID(_c, "width", "I")));
        _max_size_val.height = static_cast<decltype(_max_size_val.height)>(env->GetIntField(max_size, env->GetFieldID(_c, "height", "I")));
    }
    s->set_auto_resize_enabled(s, static_cast<bool>(enabled), &_min_size_val, &_max_size_val);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefBrowserHost), setAudioMuted0)(JNIEnv* env, jobject obj, jlong self, jboolean mute) {
    auto* s = reinterpret_cast<cef_browser_host_t*>(self);
    if (!s) return;
    s->set_audio_muted(s, static_cast<bool>(mute));
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefBrowserHost), isAudioMuted0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_browser_host_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_audio_muted(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefBrowserHost), isFullscreen0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_browser_host_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_fullscreen(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefBrowserHost), exitFullscreen0)(JNIEnv* env, jobject obj, jlong self, jboolean will_cause_resize) {
    auto* s = reinterpret_cast<cef_browser_host_t*>(self);
    if (!s) return;
    s->exit_fullscreen(s, static_cast<bool>(will_cause_resize));
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefBrowserHost), canExecuteChromeCommand0)(JNIEnv* env, jobject obj, jlong self, jint command_id) {
    auto* s = reinterpret_cast<cef_browser_host_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->can_execute_chrome_command(s, command_id);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefBrowserHost), executeChromeCommand0)(JNIEnv* env, jobject obj, jlong self, jint command_id, jobject disposition) {
    auto* s = reinterpret_cast<cef_browser_host_t*>(self);
    if (!s) return;
    if (!disposition) { env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "disposition must not be null"); return; }
    s->execute_chrome_command(s, command_id, static_cast<cef_window_open_disposition_t>(env->GetLongField(disposition, env->GetFieldID(env->GetObjectClass(disposition), "value", "J"))));
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefBrowserHost), isRenderProcessUnresponsive0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_browser_host_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_render_process_unresponsive(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefBrowserHost), getRuntimeStyle0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_browser_host_t*>(self);
    if (!s) return 0;
    auto _r = s->get_runtime_style(s);
    auto _eCls = env->FindClass("net/kurobako/cef4j/gen/CefRuntimeStyle");
    auto _eOf = env->GetStaticMethodID(_eCls, "of", "(J)Lnet/kurobako/cef4j/gen/CefRuntimeStyle;");
    return env->CallStaticObjectMethod(_eCls, _eOf, static_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefBrowserHost), setAxViewportCollapse0)(JNIEnv* env, jobject obj, jlong self, jboolean enabled) {
    auto* s = reinterpret_cast<cef_browser_host_t*>(self);
    if (!s) return;
    s->set_ax_viewport_collapse(s, static_cast<bool>(enabled));
}

CEF4J_JNI_EXPORT(jint, CEF4J_PEER(CefBrowserHost), createBrowser0)(JNIEnv* env, jclass clz, jobject windowInfo, jobject client, jstring url, jobject settings, jobject extra_info, jobject request_context) {
    if (!windowInfo) { env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "windowInfo must not be null"); return 0; }
    if (!settings) { env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "settings must not be null"); return 0; }
    cef_window_info_t _windowInfo_val = {};
    if (windowInfo) {
        auto _c = env->FindClass("net/kurobako/cef4j/gen/CefWindowInfo");
        jstring _rd_window_name = (jstring)env->GetObjectField(windowInfo, env->GetFieldID(_c, "windowName", "Ljava/lang/String;"));
        if (_rd_window_name) {
            const jchar* _rd_window_name_chars = env->GetStringChars(_rd_window_name, nullptr);
            jsize _rd_window_name_len = env->GetStringLength(_rd_window_name);
            cef_string_set(reinterpret_cast<const char16_t*>(_rd_window_name_chars), _rd_window_name_len, &_windowInfo_val.window_name, 1);
            env->ReleaseStringChars(_rd_window_name, _rd_window_name_chars);
        }
        auto _rd_bounds = env->GetObjectField(windowInfo, env->GetFieldID(_c, "bounds", "Lnet/kurobako/cef4j/gen/CefRect;"));
        if (_rd_bounds) {
            auto _rd_boundsc = env->GetObjectClass(_rd_bounds);
            _windowInfo_val.bounds.x = static_cast<decltype(_windowInfo_val.bounds.x)>(env->GetIntField(_rd_bounds, env->GetFieldID(_rd_boundsc, "x", "I")));
            _windowInfo_val.bounds.y = static_cast<decltype(_windowInfo_val.bounds.y)>(env->GetIntField(_rd_bounds, env->GetFieldID(_rd_boundsc, "y", "I")));
            _windowInfo_val.bounds.width = static_cast<decltype(_windowInfo_val.bounds.width)>(env->GetIntField(_rd_bounds, env->GetFieldID(_rd_boundsc, "width", "I")));
            _windowInfo_val.bounds.height = static_cast<decltype(_windowInfo_val.bounds.height)>(env->GetIntField(_rd_bounds, env->GetFieldID(_rd_boundsc, "height", "I")));
        }
        _windowInfo_val.parent_window = static_cast<decltype(_windowInfo_val.parent_window)>(static_cast<size_t>(env->GetLongField(windowInfo, env->GetFieldID(_c, "parentWindow", "J"))));
        _windowInfo_val.windowless_rendering_enabled = static_cast<decltype(_windowInfo_val.windowless_rendering_enabled)>(env->GetIntField(windowInfo, env->GetFieldID(_c, "windowlessRenderingEnabled", "I")));
        _windowInfo_val.shared_texture_enabled = static_cast<decltype(_windowInfo_val.shared_texture_enabled)>(env->GetIntField(windowInfo, env->GetFieldID(_c, "sharedTextureEnabled", "I")));
        _windowInfo_val.external_begin_frame_enabled = static_cast<decltype(_windowInfo_val.external_begin_frame_enabled)>(env->GetIntField(windowInfo, env->GetFieldID(_c, "externalBeginFrameEnabled", "I")));
        _windowInfo_val.window = static_cast<decltype(_windowInfo_val.window)>(static_cast<size_t>(env->GetLongField(windowInfo, env->GetFieldID(_c, "window", "J"))));
        auto _rd_runtime_style = env->GetObjectField(windowInfo, env->GetFieldID(_c, "runtimeStyle", "Lnet/kurobako/cef4j/gen/CefRuntimeStyle;"));
        if (_rd_runtime_style) {
            _windowInfo_val.runtime_style = static_cast<decltype(_windowInfo_val.runtime_style)>(env->GetLongField(_rd_runtime_style, env->GetFieldID(env->GetObjectClass(_rd_runtime_style), "value", "J")));
        }
        _windowInfo_val.size = sizeof(cef_window_info_t);
    }
    cef_client_t* _client_ptr = client ? Create_JniCefClient(env, client) : nullptr;
    auto _url_str = url ? JStringToCefString(env, url) : nullptr;
    cef_browser_settings_t _settings_val = {};
    if (settings) {
        auto _c = env->FindClass("net/kurobako/cef4j/gen/CefBrowserSettings");
        _settings_val.windowless_frame_rate = static_cast<decltype(_settings_val.windowless_frame_rate)>(env->GetIntField(settings, env->GetFieldID(_c, "windowlessFrameRate", "I")));
        jstring _rd_standard_font_family = (jstring)env->GetObjectField(settings, env->GetFieldID(_c, "standardFontFamily", "Ljava/lang/String;"));
        if (_rd_standard_font_family) {
            const jchar* _rd_standard_font_family_chars = env->GetStringChars(_rd_standard_font_family, nullptr);
            jsize _rd_standard_font_family_len = env->GetStringLength(_rd_standard_font_family);
            cef_string_set(reinterpret_cast<const char16_t*>(_rd_standard_font_family_chars), _rd_standard_font_family_len, &_settings_val.standard_font_family, 1);
            env->ReleaseStringChars(_rd_standard_font_family, _rd_standard_font_family_chars);
        }
        jstring _rd_fixed_font_family = (jstring)env->GetObjectField(settings, env->GetFieldID(_c, "fixedFontFamily", "Ljava/lang/String;"));
        if (_rd_fixed_font_family) {
            const jchar* _rd_fixed_font_family_chars = env->GetStringChars(_rd_fixed_font_family, nullptr);
            jsize _rd_fixed_font_family_len = env->GetStringLength(_rd_fixed_font_family);
            cef_string_set(reinterpret_cast<const char16_t*>(_rd_fixed_font_family_chars), _rd_fixed_font_family_len, &_settings_val.fixed_font_family, 1);
            env->ReleaseStringChars(_rd_fixed_font_family, _rd_fixed_font_family_chars);
        }
        jstring _rd_serif_font_family = (jstring)env->GetObjectField(settings, env->GetFieldID(_c, "serifFontFamily", "Ljava/lang/String;"));
        if (_rd_serif_font_family) {
            const jchar* _rd_serif_font_family_chars = env->GetStringChars(_rd_serif_font_family, nullptr);
            jsize _rd_serif_font_family_len = env->GetStringLength(_rd_serif_font_family);
            cef_string_set(reinterpret_cast<const char16_t*>(_rd_serif_font_family_chars), _rd_serif_font_family_len, &_settings_val.serif_font_family, 1);
            env->ReleaseStringChars(_rd_serif_font_family, _rd_serif_font_family_chars);
        }
        jstring _rd_sans_serif_font_family = (jstring)env->GetObjectField(settings, env->GetFieldID(_c, "sansSerifFontFamily", "Ljava/lang/String;"));
        if (_rd_sans_serif_font_family) {
            const jchar* _rd_sans_serif_font_family_chars = env->GetStringChars(_rd_sans_serif_font_family, nullptr);
            jsize _rd_sans_serif_font_family_len = env->GetStringLength(_rd_sans_serif_font_family);
            cef_string_set(reinterpret_cast<const char16_t*>(_rd_sans_serif_font_family_chars), _rd_sans_serif_font_family_len, &_settings_val.sans_serif_font_family, 1);
            env->ReleaseStringChars(_rd_sans_serif_font_family, _rd_sans_serif_font_family_chars);
        }
        jstring _rd_cursive_font_family = (jstring)env->GetObjectField(settings, env->GetFieldID(_c, "cursiveFontFamily", "Ljava/lang/String;"));
        if (_rd_cursive_font_family) {
            const jchar* _rd_cursive_font_family_chars = env->GetStringChars(_rd_cursive_font_family, nullptr);
            jsize _rd_cursive_font_family_len = env->GetStringLength(_rd_cursive_font_family);
            cef_string_set(reinterpret_cast<const char16_t*>(_rd_cursive_font_family_chars), _rd_cursive_font_family_len, &_settings_val.cursive_font_family, 1);
            env->ReleaseStringChars(_rd_cursive_font_family, _rd_cursive_font_family_chars);
        }
        jstring _rd_fantasy_font_family = (jstring)env->GetObjectField(settings, env->GetFieldID(_c, "fantasyFontFamily", "Ljava/lang/String;"));
        if (_rd_fantasy_font_family) {
            const jchar* _rd_fantasy_font_family_chars = env->GetStringChars(_rd_fantasy_font_family, nullptr);
            jsize _rd_fantasy_font_family_len = env->GetStringLength(_rd_fantasy_font_family);
            cef_string_set(reinterpret_cast<const char16_t*>(_rd_fantasy_font_family_chars), _rd_fantasy_font_family_len, &_settings_val.fantasy_font_family, 1);
            env->ReleaseStringChars(_rd_fantasy_font_family, _rd_fantasy_font_family_chars);
        }
        _settings_val.default_font_size = static_cast<decltype(_settings_val.default_font_size)>(env->GetIntField(settings, env->GetFieldID(_c, "defaultFontSize", "I")));
        _settings_val.default_fixed_font_size = static_cast<decltype(_settings_val.default_fixed_font_size)>(env->GetIntField(settings, env->GetFieldID(_c, "defaultFixedFontSize", "I")));
        _settings_val.minimum_font_size = static_cast<decltype(_settings_val.minimum_font_size)>(env->GetIntField(settings, env->GetFieldID(_c, "minimumFontSize", "I")));
        _settings_val.minimum_logical_font_size = static_cast<decltype(_settings_val.minimum_logical_font_size)>(env->GetIntField(settings, env->GetFieldID(_c, "minimumLogicalFontSize", "I")));
        jstring _rd_default_encoding = (jstring)env->GetObjectField(settings, env->GetFieldID(_c, "defaultEncoding", "Ljava/lang/String;"));
        if (_rd_default_encoding) {
            const jchar* _rd_default_encoding_chars = env->GetStringChars(_rd_default_encoding, nullptr);
            jsize _rd_default_encoding_len = env->GetStringLength(_rd_default_encoding);
            cef_string_set(reinterpret_cast<const char16_t*>(_rd_default_encoding_chars), _rd_default_encoding_len, &_settings_val.default_encoding, 1);
            env->ReleaseStringChars(_rd_default_encoding, _rd_default_encoding_chars);
        }
        auto _rd_remote_fonts = env->GetObjectField(settings, env->GetFieldID(_c, "remoteFonts", "Lnet/kurobako/cef4j/gen/CefState;"));
        if (_rd_remote_fonts) {
            _settings_val.remote_fonts = static_cast<decltype(_settings_val.remote_fonts)>(env->GetLongField(_rd_remote_fonts, env->GetFieldID(env->GetObjectClass(_rd_remote_fonts), "value", "J")));
        }
        auto _rd_javascript = env->GetObjectField(settings, env->GetFieldID(_c, "javascript", "Lnet/kurobako/cef4j/gen/CefState;"));
        if (_rd_javascript) {
            _settings_val.javascript = static_cast<decltype(_settings_val.javascript)>(env->GetLongField(_rd_javascript, env->GetFieldID(env->GetObjectClass(_rd_javascript), "value", "J")));
        }
        auto _rd_javascript_close_windows = env->GetObjectField(settings, env->GetFieldID(_c, "javascriptCloseWindows", "Lnet/kurobako/cef4j/gen/CefState;"));
        if (_rd_javascript_close_windows) {
            _settings_val.javascript_close_windows = static_cast<decltype(_settings_val.javascript_close_windows)>(env->GetLongField(_rd_javascript_close_windows, env->GetFieldID(env->GetObjectClass(_rd_javascript_close_windows), "value", "J")));
        }
        auto _rd_javascript_access_clipboard = env->GetObjectField(settings, env->GetFieldID(_c, "javascriptAccessClipboard", "Lnet/kurobako/cef4j/gen/CefState;"));
        if (_rd_javascript_access_clipboard) {
            _settings_val.javascript_access_clipboard = static_cast<decltype(_settings_val.javascript_access_clipboard)>(env->GetLongField(_rd_javascript_access_clipboard, env->GetFieldID(env->GetObjectClass(_rd_javascript_access_clipboard), "value", "J")));
        }
        auto _rd_javascript_dom_paste = env->GetObjectField(settings, env->GetFieldID(_c, "javascriptDomPaste", "Lnet/kurobako/cef4j/gen/CefState;"));
        if (_rd_javascript_dom_paste) {
            _settings_val.javascript_dom_paste = static_cast<decltype(_settings_val.javascript_dom_paste)>(env->GetLongField(_rd_javascript_dom_paste, env->GetFieldID(env->GetObjectClass(_rd_javascript_dom_paste), "value", "J")));
        }
        auto _rd_image_loading = env->GetObjectField(settings, env->GetFieldID(_c, "imageLoading", "Lnet/kurobako/cef4j/gen/CefState;"));
        if (_rd_image_loading) {
            _settings_val.image_loading = static_cast<decltype(_settings_val.image_loading)>(env->GetLongField(_rd_image_loading, env->GetFieldID(env->GetObjectClass(_rd_image_loading), "value", "J")));
        }
        auto _rd_image_shrink_standalone_to_fit = env->GetObjectField(settings, env->GetFieldID(_c, "imageShrinkStandaloneToFit", "Lnet/kurobako/cef4j/gen/CefState;"));
        if (_rd_image_shrink_standalone_to_fit) {
            _settings_val.image_shrink_standalone_to_fit = static_cast<decltype(_settings_val.image_shrink_standalone_to_fit)>(env->GetLongField(_rd_image_shrink_standalone_to_fit, env->GetFieldID(env->GetObjectClass(_rd_image_shrink_standalone_to_fit), "value", "J")));
        }
        auto _rd_text_area_resize = env->GetObjectField(settings, env->GetFieldID(_c, "textAreaResize", "Lnet/kurobako/cef4j/gen/CefState;"));
        if (_rd_text_area_resize) {
            _settings_val.text_area_resize = static_cast<decltype(_settings_val.text_area_resize)>(env->GetLongField(_rd_text_area_resize, env->GetFieldID(env->GetObjectClass(_rd_text_area_resize), "value", "J")));
        }
        auto _rd_tab_to_links = env->GetObjectField(settings, env->GetFieldID(_c, "tabToLinks", "Lnet/kurobako/cef4j/gen/CefState;"));
        if (_rd_tab_to_links) {
            _settings_val.tab_to_links = static_cast<decltype(_settings_val.tab_to_links)>(env->GetLongField(_rd_tab_to_links, env->GetFieldID(env->GetObjectClass(_rd_tab_to_links), "value", "J")));
        }
        auto _rd_local_storage = env->GetObjectField(settings, env->GetFieldID(_c, "localStorage", "Lnet/kurobako/cef4j/gen/CefState;"));
        if (_rd_local_storage) {
            _settings_val.local_storage = static_cast<decltype(_settings_val.local_storage)>(env->GetLongField(_rd_local_storage, env->GetFieldID(env->GetObjectClass(_rd_local_storage), "value", "J")));
        }
        auto _rd_databases_deprecated = env->GetObjectField(settings, env->GetFieldID(_c, "databasesDeprecated", "Lnet/kurobako/cef4j/gen/CefState;"));
        if (_rd_databases_deprecated) {
            _settings_val.databases_deprecated = static_cast<decltype(_settings_val.databases_deprecated)>(env->GetLongField(_rd_databases_deprecated, env->GetFieldID(env->GetObjectClass(_rd_databases_deprecated), "value", "J")));
        }
        auto _rd_webgl = env->GetObjectField(settings, env->GetFieldID(_c, "webgl", "Lnet/kurobako/cef4j/gen/CefState;"));
        if (_rd_webgl) {
            _settings_val.webgl = static_cast<decltype(_settings_val.webgl)>(env->GetLongField(_rd_webgl, env->GetFieldID(env->GetObjectClass(_rd_webgl), "value", "J")));
        }
        _settings_val.background_color = static_cast<decltype(_settings_val.background_color)>(env->GetIntField(settings, env->GetFieldID(_c, "backgroundColor", "I")));
        auto _rd_chrome_status_bubble = env->GetObjectField(settings, env->GetFieldID(_c, "chromeStatusBubble", "Lnet/kurobako/cef4j/gen/CefState;"));
        if (_rd_chrome_status_bubble) {
            _settings_val.chrome_status_bubble = static_cast<decltype(_settings_val.chrome_status_bubble)>(env->GetLongField(_rd_chrome_status_bubble, env->GetFieldID(env->GetObjectClass(_rd_chrome_status_bubble), "value", "J")));
        }
        auto _rd_chrome_zoom_bubble = env->GetObjectField(settings, env->GetFieldID(_c, "chromeZoomBubble", "Lnet/kurobako/cef4j/gen/CefState;"));
        if (_rd_chrome_zoom_bubble) {
            _settings_val.chrome_zoom_bubble = static_cast<decltype(_settings_val.chrome_zoom_bubble)>(env->GetLongField(_rd_chrome_zoom_bubble, env->GetFieldID(env->GetObjectClass(_rd_chrome_zoom_bubble), "value", "J")));
        }
        auto _rd_ax_viewport_collapse = env->GetObjectField(settings, env->GetFieldID(_c, "axViewportCollapse", "Lnet/kurobako/cef4j/gen/CefState;"));
        if (_rd_ax_viewport_collapse) {
            _settings_val.ax_viewport_collapse = static_cast<decltype(_settings_val.ax_viewport_collapse)>(env->GetLongField(_rd_ax_viewport_collapse, env->GetFieldID(env->GetObjectClass(_rd_ax_viewport_collapse), "value", "J")));
        }
        _settings_val.size = sizeof(cef_browser_settings_t);
    }
    cef_dictionary_value_t* _extra_info_ptr = extra_info ? reinterpret_cast<cef_dictionary_value_t*>(env->GetLongField(extra_info, env->GetFieldID(env->GetObjectClass(extra_info), "nativePtr", "J"))) : nullptr;
    if (_extra_info_ptr) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_extra_info_ptr); _b->add_ref(_b); }
    cef_request_context_t* _request_context_ptr = request_context ? reinterpret_cast<cef_request_context_t*>(env->GetLongField(request_context, env->GetFieldID(env->GetObjectClass(request_context), "nativePtr", "J"))) : nullptr;
    if (_request_context_ptr) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_request_context_ptr); _b->add_ref(_b); }
    return static_cast<jint>(cef_browser_host_create_browser(&_windowInfo_val, _client_ptr, _url_str, &_settings_val, _extra_info_ptr, _request_context_ptr));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefBrowserHost), createBrowserSync0)(JNIEnv* env, jclass clz, jobject windowInfo, jobject client, jstring url, jobject settings, jobject extra_info, jobject request_context) {
    if (!windowInfo) { env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "windowInfo must not be null"); return nullptr; }
    if (!settings) { env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "settings must not be null"); return nullptr; }
    cef_window_info_t _windowInfo_val = {};
    if (windowInfo) {
        auto _c = env->FindClass("net/kurobako/cef4j/gen/CefWindowInfo");
        jstring _rd_window_name = (jstring)env->GetObjectField(windowInfo, env->GetFieldID(_c, "windowName", "Ljava/lang/String;"));
        if (_rd_window_name) {
            const jchar* _rd_window_name_chars = env->GetStringChars(_rd_window_name, nullptr);
            jsize _rd_window_name_len = env->GetStringLength(_rd_window_name);
            cef_string_set(reinterpret_cast<const char16_t*>(_rd_window_name_chars), _rd_window_name_len, &_windowInfo_val.window_name, 1);
            env->ReleaseStringChars(_rd_window_name, _rd_window_name_chars);
        }
        auto _rd_bounds = env->GetObjectField(windowInfo, env->GetFieldID(_c, "bounds", "Lnet/kurobako/cef4j/gen/CefRect;"));
        if (_rd_bounds) {
            auto _rd_boundsc = env->GetObjectClass(_rd_bounds);
            _windowInfo_val.bounds.x = static_cast<decltype(_windowInfo_val.bounds.x)>(env->GetIntField(_rd_bounds, env->GetFieldID(_rd_boundsc, "x", "I")));
            _windowInfo_val.bounds.y = static_cast<decltype(_windowInfo_val.bounds.y)>(env->GetIntField(_rd_bounds, env->GetFieldID(_rd_boundsc, "y", "I")));
            _windowInfo_val.bounds.width = static_cast<decltype(_windowInfo_val.bounds.width)>(env->GetIntField(_rd_bounds, env->GetFieldID(_rd_boundsc, "width", "I")));
            _windowInfo_val.bounds.height = static_cast<decltype(_windowInfo_val.bounds.height)>(env->GetIntField(_rd_bounds, env->GetFieldID(_rd_boundsc, "height", "I")));
        }
        _windowInfo_val.parent_window = static_cast<decltype(_windowInfo_val.parent_window)>(static_cast<size_t>(env->GetLongField(windowInfo, env->GetFieldID(_c, "parentWindow", "J"))));
        _windowInfo_val.windowless_rendering_enabled = static_cast<decltype(_windowInfo_val.windowless_rendering_enabled)>(env->GetIntField(windowInfo, env->GetFieldID(_c, "windowlessRenderingEnabled", "I")));
        _windowInfo_val.shared_texture_enabled = static_cast<decltype(_windowInfo_val.shared_texture_enabled)>(env->GetIntField(windowInfo, env->GetFieldID(_c, "sharedTextureEnabled", "I")));
        _windowInfo_val.external_begin_frame_enabled = static_cast<decltype(_windowInfo_val.external_begin_frame_enabled)>(env->GetIntField(windowInfo, env->GetFieldID(_c, "externalBeginFrameEnabled", "I")));
        _windowInfo_val.window = static_cast<decltype(_windowInfo_val.window)>(static_cast<size_t>(env->GetLongField(windowInfo, env->GetFieldID(_c, "window", "J"))));
        auto _rd_runtime_style = env->GetObjectField(windowInfo, env->GetFieldID(_c, "runtimeStyle", "Lnet/kurobako/cef4j/gen/CefRuntimeStyle;"));
        if (_rd_runtime_style) {
            _windowInfo_val.runtime_style = static_cast<decltype(_windowInfo_val.runtime_style)>(env->GetLongField(_rd_runtime_style, env->GetFieldID(env->GetObjectClass(_rd_runtime_style), "value", "J")));
        }
        _windowInfo_val.size = sizeof(cef_window_info_t);
    }
    cef_client_t* _client_ptr = client ? Create_JniCefClient(env, client) : nullptr;
    auto _url_str = url ? JStringToCefString(env, url) : nullptr;
    cef_browser_settings_t _settings_val = {};
    if (settings) {
        auto _c = env->FindClass("net/kurobako/cef4j/gen/CefBrowserSettings");
        _settings_val.windowless_frame_rate = static_cast<decltype(_settings_val.windowless_frame_rate)>(env->GetIntField(settings, env->GetFieldID(_c, "windowlessFrameRate", "I")));
        jstring _rd_standard_font_family = (jstring)env->GetObjectField(settings, env->GetFieldID(_c, "standardFontFamily", "Ljava/lang/String;"));
        if (_rd_standard_font_family) {
            const jchar* _rd_standard_font_family_chars = env->GetStringChars(_rd_standard_font_family, nullptr);
            jsize _rd_standard_font_family_len = env->GetStringLength(_rd_standard_font_family);
            cef_string_set(reinterpret_cast<const char16_t*>(_rd_standard_font_family_chars), _rd_standard_font_family_len, &_settings_val.standard_font_family, 1);
            env->ReleaseStringChars(_rd_standard_font_family, _rd_standard_font_family_chars);
        }
        jstring _rd_fixed_font_family = (jstring)env->GetObjectField(settings, env->GetFieldID(_c, "fixedFontFamily", "Ljava/lang/String;"));
        if (_rd_fixed_font_family) {
            const jchar* _rd_fixed_font_family_chars = env->GetStringChars(_rd_fixed_font_family, nullptr);
            jsize _rd_fixed_font_family_len = env->GetStringLength(_rd_fixed_font_family);
            cef_string_set(reinterpret_cast<const char16_t*>(_rd_fixed_font_family_chars), _rd_fixed_font_family_len, &_settings_val.fixed_font_family, 1);
            env->ReleaseStringChars(_rd_fixed_font_family, _rd_fixed_font_family_chars);
        }
        jstring _rd_serif_font_family = (jstring)env->GetObjectField(settings, env->GetFieldID(_c, "serifFontFamily", "Ljava/lang/String;"));
        if (_rd_serif_font_family) {
            const jchar* _rd_serif_font_family_chars = env->GetStringChars(_rd_serif_font_family, nullptr);
            jsize _rd_serif_font_family_len = env->GetStringLength(_rd_serif_font_family);
            cef_string_set(reinterpret_cast<const char16_t*>(_rd_serif_font_family_chars), _rd_serif_font_family_len, &_settings_val.serif_font_family, 1);
            env->ReleaseStringChars(_rd_serif_font_family, _rd_serif_font_family_chars);
        }
        jstring _rd_sans_serif_font_family = (jstring)env->GetObjectField(settings, env->GetFieldID(_c, "sansSerifFontFamily", "Ljava/lang/String;"));
        if (_rd_sans_serif_font_family) {
            const jchar* _rd_sans_serif_font_family_chars = env->GetStringChars(_rd_sans_serif_font_family, nullptr);
            jsize _rd_sans_serif_font_family_len = env->GetStringLength(_rd_sans_serif_font_family);
            cef_string_set(reinterpret_cast<const char16_t*>(_rd_sans_serif_font_family_chars), _rd_sans_serif_font_family_len, &_settings_val.sans_serif_font_family, 1);
            env->ReleaseStringChars(_rd_sans_serif_font_family, _rd_sans_serif_font_family_chars);
        }
        jstring _rd_cursive_font_family = (jstring)env->GetObjectField(settings, env->GetFieldID(_c, "cursiveFontFamily", "Ljava/lang/String;"));
        if (_rd_cursive_font_family) {
            const jchar* _rd_cursive_font_family_chars = env->GetStringChars(_rd_cursive_font_family, nullptr);
            jsize _rd_cursive_font_family_len = env->GetStringLength(_rd_cursive_font_family);
            cef_string_set(reinterpret_cast<const char16_t*>(_rd_cursive_font_family_chars), _rd_cursive_font_family_len, &_settings_val.cursive_font_family, 1);
            env->ReleaseStringChars(_rd_cursive_font_family, _rd_cursive_font_family_chars);
        }
        jstring _rd_fantasy_font_family = (jstring)env->GetObjectField(settings, env->GetFieldID(_c, "fantasyFontFamily", "Ljava/lang/String;"));
        if (_rd_fantasy_font_family) {
            const jchar* _rd_fantasy_font_family_chars = env->GetStringChars(_rd_fantasy_font_family, nullptr);
            jsize _rd_fantasy_font_family_len = env->GetStringLength(_rd_fantasy_font_family);
            cef_string_set(reinterpret_cast<const char16_t*>(_rd_fantasy_font_family_chars), _rd_fantasy_font_family_len, &_settings_val.fantasy_font_family, 1);
            env->ReleaseStringChars(_rd_fantasy_font_family, _rd_fantasy_font_family_chars);
        }
        _settings_val.default_font_size = static_cast<decltype(_settings_val.default_font_size)>(env->GetIntField(settings, env->GetFieldID(_c, "defaultFontSize", "I")));
        _settings_val.default_fixed_font_size = static_cast<decltype(_settings_val.default_fixed_font_size)>(env->GetIntField(settings, env->GetFieldID(_c, "defaultFixedFontSize", "I")));
        _settings_val.minimum_font_size = static_cast<decltype(_settings_val.minimum_font_size)>(env->GetIntField(settings, env->GetFieldID(_c, "minimumFontSize", "I")));
        _settings_val.minimum_logical_font_size = static_cast<decltype(_settings_val.minimum_logical_font_size)>(env->GetIntField(settings, env->GetFieldID(_c, "minimumLogicalFontSize", "I")));
        jstring _rd_default_encoding = (jstring)env->GetObjectField(settings, env->GetFieldID(_c, "defaultEncoding", "Ljava/lang/String;"));
        if (_rd_default_encoding) {
            const jchar* _rd_default_encoding_chars = env->GetStringChars(_rd_default_encoding, nullptr);
            jsize _rd_default_encoding_len = env->GetStringLength(_rd_default_encoding);
            cef_string_set(reinterpret_cast<const char16_t*>(_rd_default_encoding_chars), _rd_default_encoding_len, &_settings_val.default_encoding, 1);
            env->ReleaseStringChars(_rd_default_encoding, _rd_default_encoding_chars);
        }
        auto _rd_remote_fonts = env->GetObjectField(settings, env->GetFieldID(_c, "remoteFonts", "Lnet/kurobako/cef4j/gen/CefState;"));
        if (_rd_remote_fonts) {
            _settings_val.remote_fonts = static_cast<decltype(_settings_val.remote_fonts)>(env->GetLongField(_rd_remote_fonts, env->GetFieldID(env->GetObjectClass(_rd_remote_fonts), "value", "J")));
        }
        auto _rd_javascript = env->GetObjectField(settings, env->GetFieldID(_c, "javascript", "Lnet/kurobako/cef4j/gen/CefState;"));
        if (_rd_javascript) {
            _settings_val.javascript = static_cast<decltype(_settings_val.javascript)>(env->GetLongField(_rd_javascript, env->GetFieldID(env->GetObjectClass(_rd_javascript), "value", "J")));
        }
        auto _rd_javascript_close_windows = env->GetObjectField(settings, env->GetFieldID(_c, "javascriptCloseWindows", "Lnet/kurobako/cef4j/gen/CefState;"));
        if (_rd_javascript_close_windows) {
            _settings_val.javascript_close_windows = static_cast<decltype(_settings_val.javascript_close_windows)>(env->GetLongField(_rd_javascript_close_windows, env->GetFieldID(env->GetObjectClass(_rd_javascript_close_windows), "value", "J")));
        }
        auto _rd_javascript_access_clipboard = env->GetObjectField(settings, env->GetFieldID(_c, "javascriptAccessClipboard", "Lnet/kurobako/cef4j/gen/CefState;"));
        if (_rd_javascript_access_clipboard) {
            _settings_val.javascript_access_clipboard = static_cast<decltype(_settings_val.javascript_access_clipboard)>(env->GetLongField(_rd_javascript_access_clipboard, env->GetFieldID(env->GetObjectClass(_rd_javascript_access_clipboard), "value", "J")));
        }
        auto _rd_javascript_dom_paste = env->GetObjectField(settings, env->GetFieldID(_c, "javascriptDomPaste", "Lnet/kurobako/cef4j/gen/CefState;"));
        if (_rd_javascript_dom_paste) {
            _settings_val.javascript_dom_paste = static_cast<decltype(_settings_val.javascript_dom_paste)>(env->GetLongField(_rd_javascript_dom_paste, env->GetFieldID(env->GetObjectClass(_rd_javascript_dom_paste), "value", "J")));
        }
        auto _rd_image_loading = env->GetObjectField(settings, env->GetFieldID(_c, "imageLoading", "Lnet/kurobako/cef4j/gen/CefState;"));
        if (_rd_image_loading) {
            _settings_val.image_loading = static_cast<decltype(_settings_val.image_loading)>(env->GetLongField(_rd_image_loading, env->GetFieldID(env->GetObjectClass(_rd_image_loading), "value", "J")));
        }
        auto _rd_image_shrink_standalone_to_fit = env->GetObjectField(settings, env->GetFieldID(_c, "imageShrinkStandaloneToFit", "Lnet/kurobako/cef4j/gen/CefState;"));
        if (_rd_image_shrink_standalone_to_fit) {
            _settings_val.image_shrink_standalone_to_fit = static_cast<decltype(_settings_val.image_shrink_standalone_to_fit)>(env->GetLongField(_rd_image_shrink_standalone_to_fit, env->GetFieldID(env->GetObjectClass(_rd_image_shrink_standalone_to_fit), "value", "J")));
        }
        auto _rd_text_area_resize = env->GetObjectField(settings, env->GetFieldID(_c, "textAreaResize", "Lnet/kurobako/cef4j/gen/CefState;"));
        if (_rd_text_area_resize) {
            _settings_val.text_area_resize = static_cast<decltype(_settings_val.text_area_resize)>(env->GetLongField(_rd_text_area_resize, env->GetFieldID(env->GetObjectClass(_rd_text_area_resize), "value", "J")));
        }
        auto _rd_tab_to_links = env->GetObjectField(settings, env->GetFieldID(_c, "tabToLinks", "Lnet/kurobako/cef4j/gen/CefState;"));
        if (_rd_tab_to_links) {
            _settings_val.tab_to_links = static_cast<decltype(_settings_val.tab_to_links)>(env->GetLongField(_rd_tab_to_links, env->GetFieldID(env->GetObjectClass(_rd_tab_to_links), "value", "J")));
        }
        auto _rd_local_storage = env->GetObjectField(settings, env->GetFieldID(_c, "localStorage", "Lnet/kurobako/cef4j/gen/CefState;"));
        if (_rd_local_storage) {
            _settings_val.local_storage = static_cast<decltype(_settings_val.local_storage)>(env->GetLongField(_rd_local_storage, env->GetFieldID(env->GetObjectClass(_rd_local_storage), "value", "J")));
        }
        auto _rd_databases_deprecated = env->GetObjectField(settings, env->GetFieldID(_c, "databasesDeprecated", "Lnet/kurobako/cef4j/gen/CefState;"));
        if (_rd_databases_deprecated) {
            _settings_val.databases_deprecated = static_cast<decltype(_settings_val.databases_deprecated)>(env->GetLongField(_rd_databases_deprecated, env->GetFieldID(env->GetObjectClass(_rd_databases_deprecated), "value", "J")));
        }
        auto _rd_webgl = env->GetObjectField(settings, env->GetFieldID(_c, "webgl", "Lnet/kurobako/cef4j/gen/CefState;"));
        if (_rd_webgl) {
            _settings_val.webgl = static_cast<decltype(_settings_val.webgl)>(env->GetLongField(_rd_webgl, env->GetFieldID(env->GetObjectClass(_rd_webgl), "value", "J")));
        }
        _settings_val.background_color = static_cast<decltype(_settings_val.background_color)>(env->GetIntField(settings, env->GetFieldID(_c, "backgroundColor", "I")));
        auto _rd_chrome_status_bubble = env->GetObjectField(settings, env->GetFieldID(_c, "chromeStatusBubble", "Lnet/kurobako/cef4j/gen/CefState;"));
        if (_rd_chrome_status_bubble) {
            _settings_val.chrome_status_bubble = static_cast<decltype(_settings_val.chrome_status_bubble)>(env->GetLongField(_rd_chrome_status_bubble, env->GetFieldID(env->GetObjectClass(_rd_chrome_status_bubble), "value", "J")));
        }
        auto _rd_chrome_zoom_bubble = env->GetObjectField(settings, env->GetFieldID(_c, "chromeZoomBubble", "Lnet/kurobako/cef4j/gen/CefState;"));
        if (_rd_chrome_zoom_bubble) {
            _settings_val.chrome_zoom_bubble = static_cast<decltype(_settings_val.chrome_zoom_bubble)>(env->GetLongField(_rd_chrome_zoom_bubble, env->GetFieldID(env->GetObjectClass(_rd_chrome_zoom_bubble), "value", "J")));
        }
        auto _rd_ax_viewport_collapse = env->GetObjectField(settings, env->GetFieldID(_c, "axViewportCollapse", "Lnet/kurobako/cef4j/gen/CefState;"));
        if (_rd_ax_viewport_collapse) {
            _settings_val.ax_viewport_collapse = static_cast<decltype(_settings_val.ax_viewport_collapse)>(env->GetLongField(_rd_ax_viewport_collapse, env->GetFieldID(env->GetObjectClass(_rd_ax_viewport_collapse), "value", "J")));
        }
        _settings_val.size = sizeof(cef_browser_settings_t);
    }
    cef_dictionary_value_t* _extra_info_ptr = extra_info ? reinterpret_cast<cef_dictionary_value_t*>(env->GetLongField(extra_info, env->GetFieldID(env->GetObjectClass(extra_info), "nativePtr", "J"))) : nullptr;
    if (_extra_info_ptr) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_extra_info_ptr); _b->add_ref(_b); }
    cef_request_context_t* _request_context_ptr = request_context ? reinterpret_cast<cef_request_context_t*>(env->GetLongField(request_context, env->GetFieldID(env->GetObjectClass(request_context), "nativePtr", "J"))) : nullptr;
    if (_request_context_ptr) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_request_context_ptr); _b->add_ref(_b); }
    auto _r = cef_browser_host_create_browser_sync(&_windowInfo_val, _client_ptr, _url_str, &_settings_val, _extra_info_ptr, _request_context_ptr);
    if (_url_str) cef_string_userfree_free(_url_str);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefBrowser$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefBrowserHost), getBrowserByIdentifier0)(JNIEnv* env, jclass clz, jint browser_id) {
    auto _r = cef_browser_host_get_browser_by_identifier(browser_id);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefBrowser$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}
