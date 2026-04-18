// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
#include <jni.h>
#include "include/capi/cef_life_span_handler_capi.h"
#include "include/capi/cef_browser_capi.h"
#include "include/capi/cef_client_capi.h"
#include "include/capi/cef_frame_capi.h"
#include "include/capi/cef_values_capi.h"
#include "jni_util.h"

#include <atomic>
#include "jni_util.h"

extern "C" cef_client_t* Create_JniCefClient(JNIEnv* env, jobject handler);

struct JniCefLifeSpanHandler : public cef_life_span_handler_t {
    JavaVM* jvm;
    jobject javaHandler;  // global ref
    std::atomic<int> refCount{1};

    JniCefLifeSpanHandler(JavaVM* vm, jobject handler) : cef_life_span_handler_t{}, jvm(vm) {
        javaHandler = handler;
        InitRefCount<JniCefLifeSpanHandler, cef_life_span_handler_t>(reinterpret_cast<cef_base_ref_counted_t*>(static_cast<cef_life_span_handler_t*>(this)));
        on_before_popup = &_on_before_popup;
        on_before_popup_aborted = &_on_before_popup_aborted;
        on_before_dev_tools_popup = &_on_before_dev_tools_popup;
        on_after_created = &_on_after_created;
        do_close = &_do_close;
        on_before_close = &_on_before_close;
    }

    static int CEF_CALLBACK _on_before_popup(cef_life_span_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, int popup_id, const cef_string_t* target_url, const cef_string_t* target_frame_name, cef_window_open_disposition_t target_disposition, int user_gesture, const cef_popup_features_t* popupFeatures, struct _cef_window_info_t* windowInfo, struct _cef_client_t** client, struct _cef_browser_settings_t* settings, struct _cef_dictionary_value_t** extra_info, int* no_javascript_access) {
        auto* h = reinterpret_cast<JniCefLifeSpanHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(136) < 0) { return false; }
        cef_browser_t* _p_browser = browser;
        if (_p_browser) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b); }
        auto j_browser_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        cef_frame_t* _p_frame = frame;
        if (_p_frame) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_frame); _b->add_ref(_b); }
        auto j_frame_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefFrame$NativePeer");
        auto j_frame_ctor = env->GetMethodID(j_frame_cls, "<init>", "(J)V");
        auto j_frame = _p_frame ? env->NewObject(j_frame_cls, j_frame_ctor, reinterpret_cast<jlong>(_p_frame)) : nullptr;
        auto j_target_url = CefStringToJString(env, target_url);
        auto j_target_frame_name = CefStringToJString(env, target_frame_name);
        auto j_target_disposition_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefWindowOpenDisposition");
        auto j_target_disposition_from = env->GetStaticMethodID(j_target_disposition_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefWindowOpenDisposition;");
        auto j_target_disposition = env->CallStaticObjectMethod(j_target_disposition_cls, j_target_disposition_from, static_cast<jlong>(target_disposition));
        jobject j_popupFeatures = nullptr;
        if (popupFeatures) {
            auto j_popupFeatures_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefPopupFeatures");
            auto j_popupFeatures_ctor = env->GetMethodID(j_popupFeatures_cls, "<init>", "(IIIIIIIII)V");
            j_popupFeatures = env->NewObject(j_popupFeatures_cls, j_popupFeatures_ctor,
        static_cast<jint>((popupFeatures)->x),
        static_cast<jint>((popupFeatures)->xSet),
        static_cast<jint>((popupFeatures)->y),
        static_cast<jint>((popupFeatures)->ySet),
        static_cast<jint>((popupFeatures)->width),
        static_cast<jint>((popupFeatures)->widthSet),
        static_cast<jint>((popupFeatures)->height),
        static_cast<jint>((popupFeatures)->heightSet),
        static_cast<jint>((popupFeatures)->isPopup));
            if (j_popupFeatures) env->SetLongField(j_popupFeatures, env->GetFieldID(j_popupFeatures_cls, "size", "J"), static_cast<jlong>(popupFeatures->size));
        }
        auto _bv_windowInfo_window_name = CefStringToJString(env, &(windowInfo)->window_name);
        auto _bv_windowInfo_bounds_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefRect");
        auto _bv_windowInfo_bounds_ctor = env->GetMethodID(_bv_windowInfo_bounds_cls, "<init>", "(IIII)V");
        auto _bv_windowInfo_bounds = env->NewObject(_bv_windowInfo_bounds_cls, _bv_windowInfo_bounds_ctor, static_cast<jint>((windowInfo)->bounds.x), static_cast<jint>((windowInfo)->bounds.y), static_cast<jint>((windowInfo)->bounds.width), static_cast<jint>((windowInfo)->bounds.height));
        auto _bv_windowInfo_runtime_style_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefRuntimeStyle");
        auto _bv_windowInfo_runtime_style_of = env->GetStaticMethodID(_bv_windowInfo_runtime_style_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefRuntimeStyle;");
        auto _bv_windowInfo_runtime_style = env->CallStaticObjectMethod(_bv_windowInfo_runtime_style_cls, _bv_windowInfo_runtime_style_of, static_cast<jlong>((windowInfo)->runtime_style));
        auto j_windowInfo_cls = FindClassCached(env, "net/kurobako/cef4j/gen/linux/CefWindowInfo$Mutable");
        auto j_windowInfo_ctor = env->GetMethodID(j_windowInfo_cls, "<init>", "(Ljava/lang/String;Lnet/kurobako/cef4j/gen/CefRect;JIIIJLnet/kurobako/cef4j/gen/CefRuntimeStyle;)V");
        auto j_windowInfo = windowInfo
    ? env->NewObject(j_windowInfo_cls, j_windowInfo_ctor,
        _bv_windowInfo_window_name,
        _bv_windowInfo_bounds,
        to_jlong((windowInfo)->parent_window),
        static_cast<jint>((windowInfo)->windowless_rendering_enabled),
        static_cast<jint>((windowInfo)->shared_texture_enabled),
        static_cast<jint>((windowInfo)->external_begin_frame_enabled),
        to_jlong((windowInfo)->window),
        _bv_windowInfo_runtime_style)
    : nullptr;
        if (j_windowInfo) env->SetLongField(j_windowInfo, env->GetFieldID(j_windowInfo_cls, "size", "J"), static_cast<jlong>(windowInfo->size));
        auto j_client_ar_cls = FindClassCached(env, "java/util/concurrent/atomic/AtomicReference");
        auto j_client_ar_ctor = env->GetMethodID(j_client_ar_cls, "<init>", "(Ljava/lang/Object;)V");
        jobject j_client_init = nullptr;
        auto j_client = env->NewObject(j_client_ar_cls, j_client_ar_ctor, j_client_init);
        auto _bv_settings_standard_font_family = CefStringToJString(env, &(settings)->standard_font_family);
        auto _bv_settings_fixed_font_family = CefStringToJString(env, &(settings)->fixed_font_family);
        auto _bv_settings_serif_font_family = CefStringToJString(env, &(settings)->serif_font_family);
        auto _bv_settings_sans_serif_font_family = CefStringToJString(env, &(settings)->sans_serif_font_family);
        auto _bv_settings_cursive_font_family = CefStringToJString(env, &(settings)->cursive_font_family);
        auto _bv_settings_fantasy_font_family = CefStringToJString(env, &(settings)->fantasy_font_family);
        auto _bv_settings_default_encoding = CefStringToJString(env, &(settings)->default_encoding);
        auto _bv_settings_remote_fonts_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefState");
        auto _bv_settings_remote_fonts_of = env->GetStaticMethodID(_bv_settings_remote_fonts_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefState;");
        auto _bv_settings_remote_fonts = env->CallStaticObjectMethod(_bv_settings_remote_fonts_cls, _bv_settings_remote_fonts_of, static_cast<jlong>((settings)->remote_fonts));
        auto _bv_settings_javascript_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefState");
        auto _bv_settings_javascript_of = env->GetStaticMethodID(_bv_settings_javascript_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefState;");
        auto _bv_settings_javascript = env->CallStaticObjectMethod(_bv_settings_javascript_cls, _bv_settings_javascript_of, static_cast<jlong>((settings)->javascript));
        auto _bv_settings_javascript_close_windows_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefState");
        auto _bv_settings_javascript_close_windows_of = env->GetStaticMethodID(_bv_settings_javascript_close_windows_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefState;");
        auto _bv_settings_javascript_close_windows = env->CallStaticObjectMethod(_bv_settings_javascript_close_windows_cls, _bv_settings_javascript_close_windows_of, static_cast<jlong>((settings)->javascript_close_windows));
        auto _bv_settings_javascript_access_clipboard_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefState");
        auto _bv_settings_javascript_access_clipboard_of = env->GetStaticMethodID(_bv_settings_javascript_access_clipboard_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefState;");
        auto _bv_settings_javascript_access_clipboard = env->CallStaticObjectMethod(_bv_settings_javascript_access_clipboard_cls, _bv_settings_javascript_access_clipboard_of, static_cast<jlong>((settings)->javascript_access_clipboard));
        auto _bv_settings_javascript_dom_paste_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefState");
        auto _bv_settings_javascript_dom_paste_of = env->GetStaticMethodID(_bv_settings_javascript_dom_paste_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefState;");
        auto _bv_settings_javascript_dom_paste = env->CallStaticObjectMethod(_bv_settings_javascript_dom_paste_cls, _bv_settings_javascript_dom_paste_of, static_cast<jlong>((settings)->javascript_dom_paste));
        auto _bv_settings_image_loading_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefState");
        auto _bv_settings_image_loading_of = env->GetStaticMethodID(_bv_settings_image_loading_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefState;");
        auto _bv_settings_image_loading = env->CallStaticObjectMethod(_bv_settings_image_loading_cls, _bv_settings_image_loading_of, static_cast<jlong>((settings)->image_loading));
        auto _bv_settings_image_shrink_standalone_to_fit_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefState");
        auto _bv_settings_image_shrink_standalone_to_fit_of = env->GetStaticMethodID(_bv_settings_image_shrink_standalone_to_fit_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefState;");
        auto _bv_settings_image_shrink_standalone_to_fit = env->CallStaticObjectMethod(_bv_settings_image_shrink_standalone_to_fit_cls, _bv_settings_image_shrink_standalone_to_fit_of, static_cast<jlong>((settings)->image_shrink_standalone_to_fit));
        auto _bv_settings_text_area_resize_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefState");
        auto _bv_settings_text_area_resize_of = env->GetStaticMethodID(_bv_settings_text_area_resize_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefState;");
        auto _bv_settings_text_area_resize = env->CallStaticObjectMethod(_bv_settings_text_area_resize_cls, _bv_settings_text_area_resize_of, static_cast<jlong>((settings)->text_area_resize));
        auto _bv_settings_tab_to_links_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefState");
        auto _bv_settings_tab_to_links_of = env->GetStaticMethodID(_bv_settings_tab_to_links_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefState;");
        auto _bv_settings_tab_to_links = env->CallStaticObjectMethod(_bv_settings_tab_to_links_cls, _bv_settings_tab_to_links_of, static_cast<jlong>((settings)->tab_to_links));
        auto _bv_settings_local_storage_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefState");
        auto _bv_settings_local_storage_of = env->GetStaticMethodID(_bv_settings_local_storage_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefState;");
        auto _bv_settings_local_storage = env->CallStaticObjectMethod(_bv_settings_local_storage_cls, _bv_settings_local_storage_of, static_cast<jlong>((settings)->local_storage));
        auto _bv_settings_databases_deprecated_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefState");
        auto _bv_settings_databases_deprecated_of = env->GetStaticMethodID(_bv_settings_databases_deprecated_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefState;");
        auto _bv_settings_databases_deprecated = env->CallStaticObjectMethod(_bv_settings_databases_deprecated_cls, _bv_settings_databases_deprecated_of, static_cast<jlong>((settings)->databases_deprecated));
        auto _bv_settings_webgl_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefState");
        auto _bv_settings_webgl_of = env->GetStaticMethodID(_bv_settings_webgl_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefState;");
        auto _bv_settings_webgl = env->CallStaticObjectMethod(_bv_settings_webgl_cls, _bv_settings_webgl_of, static_cast<jlong>((settings)->webgl));
        auto _bv_settings_chrome_status_bubble_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefState");
        auto _bv_settings_chrome_status_bubble_of = env->GetStaticMethodID(_bv_settings_chrome_status_bubble_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefState;");
        auto _bv_settings_chrome_status_bubble = env->CallStaticObjectMethod(_bv_settings_chrome_status_bubble_cls, _bv_settings_chrome_status_bubble_of, static_cast<jlong>((settings)->chrome_status_bubble));
        auto _bv_settings_chrome_zoom_bubble_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefState");
        auto _bv_settings_chrome_zoom_bubble_of = env->GetStaticMethodID(_bv_settings_chrome_zoom_bubble_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefState;");
        auto _bv_settings_chrome_zoom_bubble = env->CallStaticObjectMethod(_bv_settings_chrome_zoom_bubble_cls, _bv_settings_chrome_zoom_bubble_of, static_cast<jlong>((settings)->chrome_zoom_bubble));
        auto j_settings_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefBrowserSettings$Mutable");
        auto j_settings_ctor = env->GetMethodID(j_settings_cls, "<init>", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IIIILjava/lang/String;Lnet/kurobako/cef4j/gen/CefState;Lnet/kurobako/cef4j/gen/CefState;Lnet/kurobako/cef4j/gen/CefState;Lnet/kurobako/cef4j/gen/CefState;Lnet/kurobako/cef4j/gen/CefState;Lnet/kurobako/cef4j/gen/CefState;Lnet/kurobako/cef4j/gen/CefState;Lnet/kurobako/cef4j/gen/CefState;Lnet/kurobako/cef4j/gen/CefState;Lnet/kurobako/cef4j/gen/CefState;Lnet/kurobako/cef4j/gen/CefState;Lnet/kurobako/cef4j/gen/CefState;ILnet/kurobako/cef4j/gen/CefState;Lnet/kurobako/cef4j/gen/CefState;)V");
        auto j_settings = settings
    ? env->NewObject(j_settings_cls, j_settings_ctor,
        static_cast<jint>((settings)->windowless_frame_rate),
        _bv_settings_standard_font_family,
        _bv_settings_fixed_font_family,
        _bv_settings_serif_font_family,
        _bv_settings_sans_serif_font_family,
        _bv_settings_cursive_font_family,
        _bv_settings_fantasy_font_family,
        static_cast<jint>((settings)->default_font_size),
        static_cast<jint>((settings)->default_fixed_font_size),
        static_cast<jint>((settings)->minimum_font_size),
        static_cast<jint>((settings)->minimum_logical_font_size),
        _bv_settings_default_encoding,
        _bv_settings_remote_fonts,
        _bv_settings_javascript,
        _bv_settings_javascript_close_windows,
        _bv_settings_javascript_access_clipboard,
        _bv_settings_javascript_dom_paste,
        _bv_settings_image_loading,
        _bv_settings_image_shrink_standalone_to_fit,
        _bv_settings_text_area_resize,
        _bv_settings_tab_to_links,
        _bv_settings_local_storage,
        _bv_settings_databases_deprecated,
        _bv_settings_webgl,
        static_cast<jint>((settings)->background_color),
        _bv_settings_chrome_status_bubble,
        _bv_settings_chrome_zoom_bubble)
    : nullptr;
        if (j_settings) env->SetLongField(j_settings, env->GetFieldID(j_settings_cls, "size", "J"), static_cast<jlong>(settings->size));
        auto j_extra_info_ar_cls = FindClassCached(env, "java/util/concurrent/atomic/AtomicReference");
        auto j_extra_info_ar_ctor = env->GetMethodID(j_extra_info_ar_cls, "<init>", "(Ljava/lang/Object;)V");
        auto j_extra_info_peer_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefDictionaryValue$NativePeer");
        auto j_extra_info_peer_ctor = env->GetMethodID(j_extra_info_peer_cls, "<init>", "(J)V");
        jobject j_extra_info_init = nullptr;
        if (extra_info && *extra_info) {
            { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(*extra_info); _b->add_ref(_b); }
            j_extra_info_init = env->NewObject(j_extra_info_peer_cls, j_extra_info_peer_ctor, reinterpret_cast<jlong>(*extra_info));
        }
        auto j_extra_info = env->NewObject(j_extra_info_ar_cls, j_extra_info_ar_ctor, j_extra_info_init);
        jintArray j_no_javascript_access = env->NewIntArray(1);
        if (no_javascript_access) { jint _v = *no_javascript_access; env->SetIntArrayRegion(j_no_javascript_access, 0, 1, &_v); }
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onBeforePopup", "(Lnet/kurobako/cef4j/gen/CefBrowser;Lnet/kurobako/cef4j/gen/CefFrame;ILjava/lang/String;Ljava/lang/String;Lnet/kurobako/cef4j/gen/CefWindowOpenDisposition;ZLnet/kurobako/cef4j/gen/CefPopupFeatures;Lnet/kurobako/cef4j/gen/CefWindowInfo$Mutable;Ljava/util/concurrent/atomic/AtomicReference;Lnet/kurobako/cef4j/gen/CefBrowserSettings$Mutable;Ljava/util/concurrent/atomic/AtomicReference;[I)Z");
        if (!mid) { env->PopLocalFrame(nullptr); return false; }
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, j_browser, j_frame, static_cast<jint>(popup_id), j_target_url, j_target_frame_name, j_target_disposition, static_cast<jboolean>(user_gesture), j_popupFeatures, j_windowInfo, j_client, j_settings, j_extra_info, j_no_javascript_access);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return false; }
        if (windowInfo && j_windowInfo) {
            jstring _wbn_window_name = (jstring)env->GetObjectField(j_windowInfo, env->GetFieldID(j_windowInfo_cls, "windowName", "Ljava/lang/String;"));
            CefStringSetFromJString(env, _wbn_window_name, &(windowInfo)->window_name);
            auto _wbn_bounds = env->GetObjectField(j_windowInfo, env->GetFieldID(j_windowInfo_cls, "bounds", "Lnet/kurobako/cef4j/gen/CefRect;"));
            if (_wbn_bounds) {
                auto _wbn_boundsc = env->GetObjectClass(_wbn_bounds);
                (windowInfo)->bounds.x = static_cast<decltype((windowInfo)->bounds.x)>(env->GetIntField(_wbn_bounds, env->GetFieldID(_wbn_boundsc, "x", "I")));
                (windowInfo)->bounds.y = static_cast<decltype((windowInfo)->bounds.y)>(env->GetIntField(_wbn_bounds, env->GetFieldID(_wbn_boundsc, "y", "I")));
                (windowInfo)->bounds.width = static_cast<decltype((windowInfo)->bounds.width)>(env->GetIntField(_wbn_bounds, env->GetFieldID(_wbn_boundsc, "width", "I")));
                (windowInfo)->bounds.height = static_cast<decltype((windowInfo)->bounds.height)>(env->GetIntField(_wbn_bounds, env->GetFieldID(_wbn_boundsc, "height", "I")));
            }
            (windowInfo)->parent_window = from_jlong<decltype((windowInfo)->parent_window)>(env->GetLongField(j_windowInfo, env->GetFieldID(j_windowInfo_cls, "parentWindow", "J")));
            (windowInfo)->windowless_rendering_enabled = static_cast<decltype((windowInfo)->windowless_rendering_enabled)>(env->GetIntField(j_windowInfo, env->GetFieldID(j_windowInfo_cls, "windowlessRenderingEnabled", "I")));
            (windowInfo)->shared_texture_enabled = static_cast<decltype((windowInfo)->shared_texture_enabled)>(env->GetIntField(j_windowInfo, env->GetFieldID(j_windowInfo_cls, "sharedTextureEnabled", "I")));
            (windowInfo)->external_begin_frame_enabled = static_cast<decltype((windowInfo)->external_begin_frame_enabled)>(env->GetIntField(j_windowInfo, env->GetFieldID(j_windowInfo_cls, "externalBeginFrameEnabled", "I")));
            (windowInfo)->window = from_jlong<decltype((windowInfo)->window)>(env->GetLongField(j_windowInfo, env->GetFieldID(j_windowInfo_cls, "window", "J")));
            auto _wbn_runtime_style = env->GetObjectField(j_windowInfo, env->GetFieldID(j_windowInfo_cls, "runtimeStyle", "Lnet/kurobako/cef4j/gen/CefRuntimeStyle;"));
            if (_wbn_runtime_style) {
                (windowInfo)->runtime_style = static_cast<decltype((windowInfo)->runtime_style)>(env->GetLongField(_wbn_runtime_style, env->GetFieldID(env->GetObjectClass(_wbn_runtime_style), "value", "J")));
            }
        }
        if (client) {
            auto j_client_get = env->GetMethodID(j_client_ar_cls, "get", "()Ljava/lang/Object;");
            auto j_client_new = env->CallObjectMethod(j_client, j_client_get);
            if (j_client_new) {
                *client = Create_JniCefClient(env, j_client_new);
            } else if (!j_client_new && j_client_new != j_client_init) {
                *client = nullptr;
            }
        }
        if (settings && j_settings) {
            (settings)->windowless_frame_rate = static_cast<decltype((settings)->windowless_frame_rate)>(env->GetIntField(j_settings, env->GetFieldID(j_settings_cls, "windowlessFrameRate", "I")));
            jstring _wbn_standard_font_family = (jstring)env->GetObjectField(j_settings, env->GetFieldID(j_settings_cls, "standardFontFamily", "Ljava/lang/String;"));
            CefStringSetFromJString(env, _wbn_standard_font_family, &(settings)->standard_font_family);
            jstring _wbn_fixed_font_family = (jstring)env->GetObjectField(j_settings, env->GetFieldID(j_settings_cls, "fixedFontFamily", "Ljava/lang/String;"));
            CefStringSetFromJString(env, _wbn_fixed_font_family, &(settings)->fixed_font_family);
            jstring _wbn_serif_font_family = (jstring)env->GetObjectField(j_settings, env->GetFieldID(j_settings_cls, "serifFontFamily", "Ljava/lang/String;"));
            CefStringSetFromJString(env, _wbn_serif_font_family, &(settings)->serif_font_family);
            jstring _wbn_sans_serif_font_family = (jstring)env->GetObjectField(j_settings, env->GetFieldID(j_settings_cls, "sansSerifFontFamily", "Ljava/lang/String;"));
            CefStringSetFromJString(env, _wbn_sans_serif_font_family, &(settings)->sans_serif_font_family);
            jstring _wbn_cursive_font_family = (jstring)env->GetObjectField(j_settings, env->GetFieldID(j_settings_cls, "cursiveFontFamily", "Ljava/lang/String;"));
            CefStringSetFromJString(env, _wbn_cursive_font_family, &(settings)->cursive_font_family);
            jstring _wbn_fantasy_font_family = (jstring)env->GetObjectField(j_settings, env->GetFieldID(j_settings_cls, "fantasyFontFamily", "Ljava/lang/String;"));
            CefStringSetFromJString(env, _wbn_fantasy_font_family, &(settings)->fantasy_font_family);
            (settings)->default_font_size = static_cast<decltype((settings)->default_font_size)>(env->GetIntField(j_settings, env->GetFieldID(j_settings_cls, "defaultFontSize", "I")));
            (settings)->default_fixed_font_size = static_cast<decltype((settings)->default_fixed_font_size)>(env->GetIntField(j_settings, env->GetFieldID(j_settings_cls, "defaultFixedFontSize", "I")));
            (settings)->minimum_font_size = static_cast<decltype((settings)->minimum_font_size)>(env->GetIntField(j_settings, env->GetFieldID(j_settings_cls, "minimumFontSize", "I")));
            (settings)->minimum_logical_font_size = static_cast<decltype((settings)->minimum_logical_font_size)>(env->GetIntField(j_settings, env->GetFieldID(j_settings_cls, "minimumLogicalFontSize", "I")));
            jstring _wbn_default_encoding = (jstring)env->GetObjectField(j_settings, env->GetFieldID(j_settings_cls, "defaultEncoding", "Ljava/lang/String;"));
            CefStringSetFromJString(env, _wbn_default_encoding, &(settings)->default_encoding);
            auto _wbn_remote_fonts = env->GetObjectField(j_settings, env->GetFieldID(j_settings_cls, "remoteFonts", "Lnet/kurobako/cef4j/gen/CefState;"));
            if (_wbn_remote_fonts) {
                (settings)->remote_fonts = static_cast<decltype((settings)->remote_fonts)>(env->GetLongField(_wbn_remote_fonts, env->GetFieldID(env->GetObjectClass(_wbn_remote_fonts), "value", "J")));
            }
            auto _wbn_javascript = env->GetObjectField(j_settings, env->GetFieldID(j_settings_cls, "javascript", "Lnet/kurobako/cef4j/gen/CefState;"));
            if (_wbn_javascript) {
                (settings)->javascript = static_cast<decltype((settings)->javascript)>(env->GetLongField(_wbn_javascript, env->GetFieldID(env->GetObjectClass(_wbn_javascript), "value", "J")));
            }
            auto _wbn_javascript_close_windows = env->GetObjectField(j_settings, env->GetFieldID(j_settings_cls, "javascriptCloseWindows", "Lnet/kurobako/cef4j/gen/CefState;"));
            if (_wbn_javascript_close_windows) {
                (settings)->javascript_close_windows = static_cast<decltype((settings)->javascript_close_windows)>(env->GetLongField(_wbn_javascript_close_windows, env->GetFieldID(env->GetObjectClass(_wbn_javascript_close_windows), "value", "J")));
            }
            auto _wbn_javascript_access_clipboard = env->GetObjectField(j_settings, env->GetFieldID(j_settings_cls, "javascriptAccessClipboard", "Lnet/kurobako/cef4j/gen/CefState;"));
            if (_wbn_javascript_access_clipboard) {
                (settings)->javascript_access_clipboard = static_cast<decltype((settings)->javascript_access_clipboard)>(env->GetLongField(_wbn_javascript_access_clipboard, env->GetFieldID(env->GetObjectClass(_wbn_javascript_access_clipboard), "value", "J")));
            }
            auto _wbn_javascript_dom_paste = env->GetObjectField(j_settings, env->GetFieldID(j_settings_cls, "javascriptDomPaste", "Lnet/kurobako/cef4j/gen/CefState;"));
            if (_wbn_javascript_dom_paste) {
                (settings)->javascript_dom_paste = static_cast<decltype((settings)->javascript_dom_paste)>(env->GetLongField(_wbn_javascript_dom_paste, env->GetFieldID(env->GetObjectClass(_wbn_javascript_dom_paste), "value", "J")));
            }
            auto _wbn_image_loading = env->GetObjectField(j_settings, env->GetFieldID(j_settings_cls, "imageLoading", "Lnet/kurobako/cef4j/gen/CefState;"));
            if (_wbn_image_loading) {
                (settings)->image_loading = static_cast<decltype((settings)->image_loading)>(env->GetLongField(_wbn_image_loading, env->GetFieldID(env->GetObjectClass(_wbn_image_loading), "value", "J")));
            }
            auto _wbn_image_shrink_standalone_to_fit = env->GetObjectField(j_settings, env->GetFieldID(j_settings_cls, "imageShrinkStandaloneToFit", "Lnet/kurobako/cef4j/gen/CefState;"));
            if (_wbn_image_shrink_standalone_to_fit) {
                (settings)->image_shrink_standalone_to_fit = static_cast<decltype((settings)->image_shrink_standalone_to_fit)>(env->GetLongField(_wbn_image_shrink_standalone_to_fit, env->GetFieldID(env->GetObjectClass(_wbn_image_shrink_standalone_to_fit), "value", "J")));
            }
            auto _wbn_text_area_resize = env->GetObjectField(j_settings, env->GetFieldID(j_settings_cls, "textAreaResize", "Lnet/kurobako/cef4j/gen/CefState;"));
            if (_wbn_text_area_resize) {
                (settings)->text_area_resize = static_cast<decltype((settings)->text_area_resize)>(env->GetLongField(_wbn_text_area_resize, env->GetFieldID(env->GetObjectClass(_wbn_text_area_resize), "value", "J")));
            }
            auto _wbn_tab_to_links = env->GetObjectField(j_settings, env->GetFieldID(j_settings_cls, "tabToLinks", "Lnet/kurobako/cef4j/gen/CefState;"));
            if (_wbn_tab_to_links) {
                (settings)->tab_to_links = static_cast<decltype((settings)->tab_to_links)>(env->GetLongField(_wbn_tab_to_links, env->GetFieldID(env->GetObjectClass(_wbn_tab_to_links), "value", "J")));
            }
            auto _wbn_local_storage = env->GetObjectField(j_settings, env->GetFieldID(j_settings_cls, "localStorage", "Lnet/kurobako/cef4j/gen/CefState;"));
            if (_wbn_local_storage) {
                (settings)->local_storage = static_cast<decltype((settings)->local_storage)>(env->GetLongField(_wbn_local_storage, env->GetFieldID(env->GetObjectClass(_wbn_local_storage), "value", "J")));
            }
            auto _wbn_databases_deprecated = env->GetObjectField(j_settings, env->GetFieldID(j_settings_cls, "databasesDeprecated", "Lnet/kurobako/cef4j/gen/CefState;"));
            if (_wbn_databases_deprecated) {
                (settings)->databases_deprecated = static_cast<decltype((settings)->databases_deprecated)>(env->GetLongField(_wbn_databases_deprecated, env->GetFieldID(env->GetObjectClass(_wbn_databases_deprecated), "value", "J")));
            }
            auto _wbn_webgl = env->GetObjectField(j_settings, env->GetFieldID(j_settings_cls, "webgl", "Lnet/kurobako/cef4j/gen/CefState;"));
            if (_wbn_webgl) {
                (settings)->webgl = static_cast<decltype((settings)->webgl)>(env->GetLongField(_wbn_webgl, env->GetFieldID(env->GetObjectClass(_wbn_webgl), "value", "J")));
            }
            (settings)->background_color = static_cast<decltype((settings)->background_color)>(env->GetIntField(j_settings, env->GetFieldID(j_settings_cls, "backgroundColor", "I")));
            auto _wbn_chrome_status_bubble = env->GetObjectField(j_settings, env->GetFieldID(j_settings_cls, "chromeStatusBubble", "Lnet/kurobako/cef4j/gen/CefState;"));
            if (_wbn_chrome_status_bubble) {
                (settings)->chrome_status_bubble = static_cast<decltype((settings)->chrome_status_bubble)>(env->GetLongField(_wbn_chrome_status_bubble, env->GetFieldID(env->GetObjectClass(_wbn_chrome_status_bubble), "value", "J")));
            }
            auto _wbn_chrome_zoom_bubble = env->GetObjectField(j_settings, env->GetFieldID(j_settings_cls, "chromeZoomBubble", "Lnet/kurobako/cef4j/gen/CefState;"));
            if (_wbn_chrome_zoom_bubble) {
                (settings)->chrome_zoom_bubble = static_cast<decltype((settings)->chrome_zoom_bubble)>(env->GetLongField(_wbn_chrome_zoom_bubble, env->GetFieldID(env->GetObjectClass(_wbn_chrome_zoom_bubble), "value", "J")));
            }
        }
        if (extra_info) {
            auto j_extra_info_get = env->GetMethodID(j_extra_info_ar_cls, "get", "()Ljava/lang/Object;");
            auto j_extra_info_new = env->CallObjectMethod(j_extra_info, j_extra_info_get);
            if (j_extra_info_new && j_extra_info_new != j_extra_info_init) {
                auto j_extra_info_fid = env->GetFieldID(j_extra_info_peer_cls, "nativePtr", "J");
                jlong j_extra_info_ptr = env->GetLongField(j_extra_info_new, j_extra_info_fid);
                *extra_info = reinterpret_cast<cef_dictionary_value_t*>(j_extra_info_ptr);
            } else if (!j_extra_info_new) {
                *extra_info = nullptr;
            }
        }
        if (no_javascript_access) { jint _v; env->GetIntArrayRegion(j_no_javascript_access, 0, 1, &_v); *no_javascript_access = _v; }
        env->PopLocalFrame(nullptr);
        return jResult;
    }

    static void CEF_CALLBACK _on_before_popup_aborted(cef_life_span_handler_t* self, struct _cef_browser_t* browser, int popup_id) {
        auto* h = reinterpret_cast<JniCefLifeSpanHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) { return; }
        cef_browser_t* _p_browser = browser;
        if (_p_browser) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b); }
        auto j_browser_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onBeforePopupAborted", "(Lnet/kurobako/cef4j/gen/CefBrowser;I)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, j_browser, static_cast<jint>(popup_id));
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        env->PopLocalFrame(nullptr);
    }

    static void CEF_CALLBACK _on_before_dev_tools_popup(cef_life_span_handler_t* self, struct _cef_browser_t* browser, struct _cef_window_info_t* windowInfo, struct _cef_client_t** client, struct _cef_browser_settings_t* settings, struct _cef_dictionary_value_t** extra_info, int* use_default_window) {
        auto* h = reinterpret_cast<JniCefLifeSpanHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(125) < 0) { return; }
        cef_browser_t* _p_browser = browser;
        if (_p_browser) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b); }
        auto j_browser_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        auto _bv_windowInfo_window_name = CefStringToJString(env, &(windowInfo)->window_name);
        auto _bv_windowInfo_bounds_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefRect");
        auto _bv_windowInfo_bounds_ctor = env->GetMethodID(_bv_windowInfo_bounds_cls, "<init>", "(IIII)V");
        auto _bv_windowInfo_bounds = env->NewObject(_bv_windowInfo_bounds_cls, _bv_windowInfo_bounds_ctor, static_cast<jint>((windowInfo)->bounds.x), static_cast<jint>((windowInfo)->bounds.y), static_cast<jint>((windowInfo)->bounds.width), static_cast<jint>((windowInfo)->bounds.height));
        auto _bv_windowInfo_runtime_style_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefRuntimeStyle");
        auto _bv_windowInfo_runtime_style_of = env->GetStaticMethodID(_bv_windowInfo_runtime_style_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefRuntimeStyle;");
        auto _bv_windowInfo_runtime_style = env->CallStaticObjectMethod(_bv_windowInfo_runtime_style_cls, _bv_windowInfo_runtime_style_of, static_cast<jlong>((windowInfo)->runtime_style));
        auto j_windowInfo_cls = FindClassCached(env, "net/kurobako/cef4j/gen/linux/CefWindowInfo$Mutable");
        auto j_windowInfo_ctor = env->GetMethodID(j_windowInfo_cls, "<init>", "(Ljava/lang/String;Lnet/kurobako/cef4j/gen/CefRect;JIIIJLnet/kurobako/cef4j/gen/CefRuntimeStyle;)V");
        auto j_windowInfo = windowInfo
    ? env->NewObject(j_windowInfo_cls, j_windowInfo_ctor,
        _bv_windowInfo_window_name,
        _bv_windowInfo_bounds,
        to_jlong((windowInfo)->parent_window),
        static_cast<jint>((windowInfo)->windowless_rendering_enabled),
        static_cast<jint>((windowInfo)->shared_texture_enabled),
        static_cast<jint>((windowInfo)->external_begin_frame_enabled),
        to_jlong((windowInfo)->window),
        _bv_windowInfo_runtime_style)
    : nullptr;
        if (j_windowInfo) env->SetLongField(j_windowInfo, env->GetFieldID(j_windowInfo_cls, "size", "J"), static_cast<jlong>(windowInfo->size));
        auto j_client_ar_cls = FindClassCached(env, "java/util/concurrent/atomic/AtomicReference");
        auto j_client_ar_ctor = env->GetMethodID(j_client_ar_cls, "<init>", "(Ljava/lang/Object;)V");
        jobject j_client_init = nullptr;
        auto j_client = env->NewObject(j_client_ar_cls, j_client_ar_ctor, j_client_init);
        auto _bv_settings_standard_font_family = CefStringToJString(env, &(settings)->standard_font_family);
        auto _bv_settings_fixed_font_family = CefStringToJString(env, &(settings)->fixed_font_family);
        auto _bv_settings_serif_font_family = CefStringToJString(env, &(settings)->serif_font_family);
        auto _bv_settings_sans_serif_font_family = CefStringToJString(env, &(settings)->sans_serif_font_family);
        auto _bv_settings_cursive_font_family = CefStringToJString(env, &(settings)->cursive_font_family);
        auto _bv_settings_fantasy_font_family = CefStringToJString(env, &(settings)->fantasy_font_family);
        auto _bv_settings_default_encoding = CefStringToJString(env, &(settings)->default_encoding);
        auto _bv_settings_remote_fonts_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefState");
        auto _bv_settings_remote_fonts_of = env->GetStaticMethodID(_bv_settings_remote_fonts_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefState;");
        auto _bv_settings_remote_fonts = env->CallStaticObjectMethod(_bv_settings_remote_fonts_cls, _bv_settings_remote_fonts_of, static_cast<jlong>((settings)->remote_fonts));
        auto _bv_settings_javascript_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefState");
        auto _bv_settings_javascript_of = env->GetStaticMethodID(_bv_settings_javascript_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefState;");
        auto _bv_settings_javascript = env->CallStaticObjectMethod(_bv_settings_javascript_cls, _bv_settings_javascript_of, static_cast<jlong>((settings)->javascript));
        auto _bv_settings_javascript_close_windows_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefState");
        auto _bv_settings_javascript_close_windows_of = env->GetStaticMethodID(_bv_settings_javascript_close_windows_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefState;");
        auto _bv_settings_javascript_close_windows = env->CallStaticObjectMethod(_bv_settings_javascript_close_windows_cls, _bv_settings_javascript_close_windows_of, static_cast<jlong>((settings)->javascript_close_windows));
        auto _bv_settings_javascript_access_clipboard_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefState");
        auto _bv_settings_javascript_access_clipboard_of = env->GetStaticMethodID(_bv_settings_javascript_access_clipboard_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefState;");
        auto _bv_settings_javascript_access_clipboard = env->CallStaticObjectMethod(_bv_settings_javascript_access_clipboard_cls, _bv_settings_javascript_access_clipboard_of, static_cast<jlong>((settings)->javascript_access_clipboard));
        auto _bv_settings_javascript_dom_paste_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefState");
        auto _bv_settings_javascript_dom_paste_of = env->GetStaticMethodID(_bv_settings_javascript_dom_paste_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefState;");
        auto _bv_settings_javascript_dom_paste = env->CallStaticObjectMethod(_bv_settings_javascript_dom_paste_cls, _bv_settings_javascript_dom_paste_of, static_cast<jlong>((settings)->javascript_dom_paste));
        auto _bv_settings_image_loading_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefState");
        auto _bv_settings_image_loading_of = env->GetStaticMethodID(_bv_settings_image_loading_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefState;");
        auto _bv_settings_image_loading = env->CallStaticObjectMethod(_bv_settings_image_loading_cls, _bv_settings_image_loading_of, static_cast<jlong>((settings)->image_loading));
        auto _bv_settings_image_shrink_standalone_to_fit_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefState");
        auto _bv_settings_image_shrink_standalone_to_fit_of = env->GetStaticMethodID(_bv_settings_image_shrink_standalone_to_fit_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefState;");
        auto _bv_settings_image_shrink_standalone_to_fit = env->CallStaticObjectMethod(_bv_settings_image_shrink_standalone_to_fit_cls, _bv_settings_image_shrink_standalone_to_fit_of, static_cast<jlong>((settings)->image_shrink_standalone_to_fit));
        auto _bv_settings_text_area_resize_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefState");
        auto _bv_settings_text_area_resize_of = env->GetStaticMethodID(_bv_settings_text_area_resize_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefState;");
        auto _bv_settings_text_area_resize = env->CallStaticObjectMethod(_bv_settings_text_area_resize_cls, _bv_settings_text_area_resize_of, static_cast<jlong>((settings)->text_area_resize));
        auto _bv_settings_tab_to_links_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefState");
        auto _bv_settings_tab_to_links_of = env->GetStaticMethodID(_bv_settings_tab_to_links_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefState;");
        auto _bv_settings_tab_to_links = env->CallStaticObjectMethod(_bv_settings_tab_to_links_cls, _bv_settings_tab_to_links_of, static_cast<jlong>((settings)->tab_to_links));
        auto _bv_settings_local_storage_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefState");
        auto _bv_settings_local_storage_of = env->GetStaticMethodID(_bv_settings_local_storage_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefState;");
        auto _bv_settings_local_storage = env->CallStaticObjectMethod(_bv_settings_local_storage_cls, _bv_settings_local_storage_of, static_cast<jlong>((settings)->local_storage));
        auto _bv_settings_databases_deprecated_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefState");
        auto _bv_settings_databases_deprecated_of = env->GetStaticMethodID(_bv_settings_databases_deprecated_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefState;");
        auto _bv_settings_databases_deprecated = env->CallStaticObjectMethod(_bv_settings_databases_deprecated_cls, _bv_settings_databases_deprecated_of, static_cast<jlong>((settings)->databases_deprecated));
        auto _bv_settings_webgl_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefState");
        auto _bv_settings_webgl_of = env->GetStaticMethodID(_bv_settings_webgl_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefState;");
        auto _bv_settings_webgl = env->CallStaticObjectMethod(_bv_settings_webgl_cls, _bv_settings_webgl_of, static_cast<jlong>((settings)->webgl));
        auto _bv_settings_chrome_status_bubble_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefState");
        auto _bv_settings_chrome_status_bubble_of = env->GetStaticMethodID(_bv_settings_chrome_status_bubble_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefState;");
        auto _bv_settings_chrome_status_bubble = env->CallStaticObjectMethod(_bv_settings_chrome_status_bubble_cls, _bv_settings_chrome_status_bubble_of, static_cast<jlong>((settings)->chrome_status_bubble));
        auto _bv_settings_chrome_zoom_bubble_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefState");
        auto _bv_settings_chrome_zoom_bubble_of = env->GetStaticMethodID(_bv_settings_chrome_zoom_bubble_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefState;");
        auto _bv_settings_chrome_zoom_bubble = env->CallStaticObjectMethod(_bv_settings_chrome_zoom_bubble_cls, _bv_settings_chrome_zoom_bubble_of, static_cast<jlong>((settings)->chrome_zoom_bubble));
        auto j_settings_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefBrowserSettings$Mutable");
        auto j_settings_ctor = env->GetMethodID(j_settings_cls, "<init>", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IIIILjava/lang/String;Lnet/kurobako/cef4j/gen/CefState;Lnet/kurobako/cef4j/gen/CefState;Lnet/kurobako/cef4j/gen/CefState;Lnet/kurobako/cef4j/gen/CefState;Lnet/kurobako/cef4j/gen/CefState;Lnet/kurobako/cef4j/gen/CefState;Lnet/kurobako/cef4j/gen/CefState;Lnet/kurobako/cef4j/gen/CefState;Lnet/kurobako/cef4j/gen/CefState;Lnet/kurobako/cef4j/gen/CefState;Lnet/kurobako/cef4j/gen/CefState;Lnet/kurobako/cef4j/gen/CefState;ILnet/kurobako/cef4j/gen/CefState;Lnet/kurobako/cef4j/gen/CefState;)V");
        auto j_settings = settings
    ? env->NewObject(j_settings_cls, j_settings_ctor,
        static_cast<jint>((settings)->windowless_frame_rate),
        _bv_settings_standard_font_family,
        _bv_settings_fixed_font_family,
        _bv_settings_serif_font_family,
        _bv_settings_sans_serif_font_family,
        _bv_settings_cursive_font_family,
        _bv_settings_fantasy_font_family,
        static_cast<jint>((settings)->default_font_size),
        static_cast<jint>((settings)->default_fixed_font_size),
        static_cast<jint>((settings)->minimum_font_size),
        static_cast<jint>((settings)->minimum_logical_font_size),
        _bv_settings_default_encoding,
        _bv_settings_remote_fonts,
        _bv_settings_javascript,
        _bv_settings_javascript_close_windows,
        _bv_settings_javascript_access_clipboard,
        _bv_settings_javascript_dom_paste,
        _bv_settings_image_loading,
        _bv_settings_image_shrink_standalone_to_fit,
        _bv_settings_text_area_resize,
        _bv_settings_tab_to_links,
        _bv_settings_local_storage,
        _bv_settings_databases_deprecated,
        _bv_settings_webgl,
        static_cast<jint>((settings)->background_color),
        _bv_settings_chrome_status_bubble,
        _bv_settings_chrome_zoom_bubble)
    : nullptr;
        if (j_settings) env->SetLongField(j_settings, env->GetFieldID(j_settings_cls, "size", "J"), static_cast<jlong>(settings->size));
        auto j_extra_info_ar_cls = FindClassCached(env, "java/util/concurrent/atomic/AtomicReference");
        auto j_extra_info_ar_ctor = env->GetMethodID(j_extra_info_ar_cls, "<init>", "(Ljava/lang/Object;)V");
        auto j_extra_info_peer_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefDictionaryValue$NativePeer");
        auto j_extra_info_peer_ctor = env->GetMethodID(j_extra_info_peer_cls, "<init>", "(J)V");
        jobject j_extra_info_init = nullptr;
        if (extra_info && *extra_info) {
            { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(*extra_info); _b->add_ref(_b); }
            j_extra_info_init = env->NewObject(j_extra_info_peer_cls, j_extra_info_peer_ctor, reinterpret_cast<jlong>(*extra_info));
        }
        auto j_extra_info = env->NewObject(j_extra_info_ar_cls, j_extra_info_ar_ctor, j_extra_info_init);
        jintArray j_use_default_window = env->NewIntArray(1);
        if (use_default_window) { jint _v = *use_default_window; env->SetIntArrayRegion(j_use_default_window, 0, 1, &_v); }
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onBeforeDevToolsPopup", "(Lnet/kurobako/cef4j/gen/CefBrowser;Lnet/kurobako/cef4j/gen/CefWindowInfo$Mutable;Ljava/util/concurrent/atomic/AtomicReference;Lnet/kurobako/cef4j/gen/CefBrowserSettings$Mutable;Ljava/util/concurrent/atomic/AtomicReference;[I)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, j_browser, j_windowInfo, j_client, j_settings, j_extra_info, j_use_default_window);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        if (windowInfo && j_windowInfo) {
            jstring _wbn_window_name = (jstring)env->GetObjectField(j_windowInfo, env->GetFieldID(j_windowInfo_cls, "windowName", "Ljava/lang/String;"));
            CefStringSetFromJString(env, _wbn_window_name, &(windowInfo)->window_name);
            auto _wbn_bounds = env->GetObjectField(j_windowInfo, env->GetFieldID(j_windowInfo_cls, "bounds", "Lnet/kurobako/cef4j/gen/CefRect;"));
            if (_wbn_bounds) {
                auto _wbn_boundsc = env->GetObjectClass(_wbn_bounds);
                (windowInfo)->bounds.x = static_cast<decltype((windowInfo)->bounds.x)>(env->GetIntField(_wbn_bounds, env->GetFieldID(_wbn_boundsc, "x", "I")));
                (windowInfo)->bounds.y = static_cast<decltype((windowInfo)->bounds.y)>(env->GetIntField(_wbn_bounds, env->GetFieldID(_wbn_boundsc, "y", "I")));
                (windowInfo)->bounds.width = static_cast<decltype((windowInfo)->bounds.width)>(env->GetIntField(_wbn_bounds, env->GetFieldID(_wbn_boundsc, "width", "I")));
                (windowInfo)->bounds.height = static_cast<decltype((windowInfo)->bounds.height)>(env->GetIntField(_wbn_bounds, env->GetFieldID(_wbn_boundsc, "height", "I")));
            }
            (windowInfo)->parent_window = from_jlong<decltype((windowInfo)->parent_window)>(env->GetLongField(j_windowInfo, env->GetFieldID(j_windowInfo_cls, "parentWindow", "J")));
            (windowInfo)->windowless_rendering_enabled = static_cast<decltype((windowInfo)->windowless_rendering_enabled)>(env->GetIntField(j_windowInfo, env->GetFieldID(j_windowInfo_cls, "windowlessRenderingEnabled", "I")));
            (windowInfo)->shared_texture_enabled = static_cast<decltype((windowInfo)->shared_texture_enabled)>(env->GetIntField(j_windowInfo, env->GetFieldID(j_windowInfo_cls, "sharedTextureEnabled", "I")));
            (windowInfo)->external_begin_frame_enabled = static_cast<decltype((windowInfo)->external_begin_frame_enabled)>(env->GetIntField(j_windowInfo, env->GetFieldID(j_windowInfo_cls, "externalBeginFrameEnabled", "I")));
            (windowInfo)->window = from_jlong<decltype((windowInfo)->window)>(env->GetLongField(j_windowInfo, env->GetFieldID(j_windowInfo_cls, "window", "J")));
            auto _wbn_runtime_style = env->GetObjectField(j_windowInfo, env->GetFieldID(j_windowInfo_cls, "runtimeStyle", "Lnet/kurobako/cef4j/gen/CefRuntimeStyle;"));
            if (_wbn_runtime_style) {
                (windowInfo)->runtime_style = static_cast<decltype((windowInfo)->runtime_style)>(env->GetLongField(_wbn_runtime_style, env->GetFieldID(env->GetObjectClass(_wbn_runtime_style), "value", "J")));
            }
        }
        if (client) {
            auto j_client_get = env->GetMethodID(j_client_ar_cls, "get", "()Ljava/lang/Object;");
            auto j_client_new = env->CallObjectMethod(j_client, j_client_get);
            if (j_client_new) {
                *client = Create_JniCefClient(env, j_client_new);
            } else if (!j_client_new && j_client_new != j_client_init) {
                *client = nullptr;
            }
        }
        if (settings && j_settings) {
            (settings)->windowless_frame_rate = static_cast<decltype((settings)->windowless_frame_rate)>(env->GetIntField(j_settings, env->GetFieldID(j_settings_cls, "windowlessFrameRate", "I")));
            jstring _wbn_standard_font_family = (jstring)env->GetObjectField(j_settings, env->GetFieldID(j_settings_cls, "standardFontFamily", "Ljava/lang/String;"));
            CefStringSetFromJString(env, _wbn_standard_font_family, &(settings)->standard_font_family);
            jstring _wbn_fixed_font_family = (jstring)env->GetObjectField(j_settings, env->GetFieldID(j_settings_cls, "fixedFontFamily", "Ljava/lang/String;"));
            CefStringSetFromJString(env, _wbn_fixed_font_family, &(settings)->fixed_font_family);
            jstring _wbn_serif_font_family = (jstring)env->GetObjectField(j_settings, env->GetFieldID(j_settings_cls, "serifFontFamily", "Ljava/lang/String;"));
            CefStringSetFromJString(env, _wbn_serif_font_family, &(settings)->serif_font_family);
            jstring _wbn_sans_serif_font_family = (jstring)env->GetObjectField(j_settings, env->GetFieldID(j_settings_cls, "sansSerifFontFamily", "Ljava/lang/String;"));
            CefStringSetFromJString(env, _wbn_sans_serif_font_family, &(settings)->sans_serif_font_family);
            jstring _wbn_cursive_font_family = (jstring)env->GetObjectField(j_settings, env->GetFieldID(j_settings_cls, "cursiveFontFamily", "Ljava/lang/String;"));
            CefStringSetFromJString(env, _wbn_cursive_font_family, &(settings)->cursive_font_family);
            jstring _wbn_fantasy_font_family = (jstring)env->GetObjectField(j_settings, env->GetFieldID(j_settings_cls, "fantasyFontFamily", "Ljava/lang/String;"));
            CefStringSetFromJString(env, _wbn_fantasy_font_family, &(settings)->fantasy_font_family);
            (settings)->default_font_size = static_cast<decltype((settings)->default_font_size)>(env->GetIntField(j_settings, env->GetFieldID(j_settings_cls, "defaultFontSize", "I")));
            (settings)->default_fixed_font_size = static_cast<decltype((settings)->default_fixed_font_size)>(env->GetIntField(j_settings, env->GetFieldID(j_settings_cls, "defaultFixedFontSize", "I")));
            (settings)->minimum_font_size = static_cast<decltype((settings)->minimum_font_size)>(env->GetIntField(j_settings, env->GetFieldID(j_settings_cls, "minimumFontSize", "I")));
            (settings)->minimum_logical_font_size = static_cast<decltype((settings)->minimum_logical_font_size)>(env->GetIntField(j_settings, env->GetFieldID(j_settings_cls, "minimumLogicalFontSize", "I")));
            jstring _wbn_default_encoding = (jstring)env->GetObjectField(j_settings, env->GetFieldID(j_settings_cls, "defaultEncoding", "Ljava/lang/String;"));
            CefStringSetFromJString(env, _wbn_default_encoding, &(settings)->default_encoding);
            auto _wbn_remote_fonts = env->GetObjectField(j_settings, env->GetFieldID(j_settings_cls, "remoteFonts", "Lnet/kurobako/cef4j/gen/CefState;"));
            if (_wbn_remote_fonts) {
                (settings)->remote_fonts = static_cast<decltype((settings)->remote_fonts)>(env->GetLongField(_wbn_remote_fonts, env->GetFieldID(env->GetObjectClass(_wbn_remote_fonts), "value", "J")));
            }
            auto _wbn_javascript = env->GetObjectField(j_settings, env->GetFieldID(j_settings_cls, "javascript", "Lnet/kurobako/cef4j/gen/CefState;"));
            if (_wbn_javascript) {
                (settings)->javascript = static_cast<decltype((settings)->javascript)>(env->GetLongField(_wbn_javascript, env->GetFieldID(env->GetObjectClass(_wbn_javascript), "value", "J")));
            }
            auto _wbn_javascript_close_windows = env->GetObjectField(j_settings, env->GetFieldID(j_settings_cls, "javascriptCloseWindows", "Lnet/kurobako/cef4j/gen/CefState;"));
            if (_wbn_javascript_close_windows) {
                (settings)->javascript_close_windows = static_cast<decltype((settings)->javascript_close_windows)>(env->GetLongField(_wbn_javascript_close_windows, env->GetFieldID(env->GetObjectClass(_wbn_javascript_close_windows), "value", "J")));
            }
            auto _wbn_javascript_access_clipboard = env->GetObjectField(j_settings, env->GetFieldID(j_settings_cls, "javascriptAccessClipboard", "Lnet/kurobako/cef4j/gen/CefState;"));
            if (_wbn_javascript_access_clipboard) {
                (settings)->javascript_access_clipboard = static_cast<decltype((settings)->javascript_access_clipboard)>(env->GetLongField(_wbn_javascript_access_clipboard, env->GetFieldID(env->GetObjectClass(_wbn_javascript_access_clipboard), "value", "J")));
            }
            auto _wbn_javascript_dom_paste = env->GetObjectField(j_settings, env->GetFieldID(j_settings_cls, "javascriptDomPaste", "Lnet/kurobako/cef4j/gen/CefState;"));
            if (_wbn_javascript_dom_paste) {
                (settings)->javascript_dom_paste = static_cast<decltype((settings)->javascript_dom_paste)>(env->GetLongField(_wbn_javascript_dom_paste, env->GetFieldID(env->GetObjectClass(_wbn_javascript_dom_paste), "value", "J")));
            }
            auto _wbn_image_loading = env->GetObjectField(j_settings, env->GetFieldID(j_settings_cls, "imageLoading", "Lnet/kurobako/cef4j/gen/CefState;"));
            if (_wbn_image_loading) {
                (settings)->image_loading = static_cast<decltype((settings)->image_loading)>(env->GetLongField(_wbn_image_loading, env->GetFieldID(env->GetObjectClass(_wbn_image_loading), "value", "J")));
            }
            auto _wbn_image_shrink_standalone_to_fit = env->GetObjectField(j_settings, env->GetFieldID(j_settings_cls, "imageShrinkStandaloneToFit", "Lnet/kurobako/cef4j/gen/CefState;"));
            if (_wbn_image_shrink_standalone_to_fit) {
                (settings)->image_shrink_standalone_to_fit = static_cast<decltype((settings)->image_shrink_standalone_to_fit)>(env->GetLongField(_wbn_image_shrink_standalone_to_fit, env->GetFieldID(env->GetObjectClass(_wbn_image_shrink_standalone_to_fit), "value", "J")));
            }
            auto _wbn_text_area_resize = env->GetObjectField(j_settings, env->GetFieldID(j_settings_cls, "textAreaResize", "Lnet/kurobako/cef4j/gen/CefState;"));
            if (_wbn_text_area_resize) {
                (settings)->text_area_resize = static_cast<decltype((settings)->text_area_resize)>(env->GetLongField(_wbn_text_area_resize, env->GetFieldID(env->GetObjectClass(_wbn_text_area_resize), "value", "J")));
            }
            auto _wbn_tab_to_links = env->GetObjectField(j_settings, env->GetFieldID(j_settings_cls, "tabToLinks", "Lnet/kurobako/cef4j/gen/CefState;"));
            if (_wbn_tab_to_links) {
                (settings)->tab_to_links = static_cast<decltype((settings)->tab_to_links)>(env->GetLongField(_wbn_tab_to_links, env->GetFieldID(env->GetObjectClass(_wbn_tab_to_links), "value", "J")));
            }
            auto _wbn_local_storage = env->GetObjectField(j_settings, env->GetFieldID(j_settings_cls, "localStorage", "Lnet/kurobako/cef4j/gen/CefState;"));
            if (_wbn_local_storage) {
                (settings)->local_storage = static_cast<decltype((settings)->local_storage)>(env->GetLongField(_wbn_local_storage, env->GetFieldID(env->GetObjectClass(_wbn_local_storage), "value", "J")));
            }
            auto _wbn_databases_deprecated = env->GetObjectField(j_settings, env->GetFieldID(j_settings_cls, "databasesDeprecated", "Lnet/kurobako/cef4j/gen/CefState;"));
            if (_wbn_databases_deprecated) {
                (settings)->databases_deprecated = static_cast<decltype((settings)->databases_deprecated)>(env->GetLongField(_wbn_databases_deprecated, env->GetFieldID(env->GetObjectClass(_wbn_databases_deprecated), "value", "J")));
            }
            auto _wbn_webgl = env->GetObjectField(j_settings, env->GetFieldID(j_settings_cls, "webgl", "Lnet/kurobako/cef4j/gen/CefState;"));
            if (_wbn_webgl) {
                (settings)->webgl = static_cast<decltype((settings)->webgl)>(env->GetLongField(_wbn_webgl, env->GetFieldID(env->GetObjectClass(_wbn_webgl), "value", "J")));
            }
            (settings)->background_color = static_cast<decltype((settings)->background_color)>(env->GetIntField(j_settings, env->GetFieldID(j_settings_cls, "backgroundColor", "I")));
            auto _wbn_chrome_status_bubble = env->GetObjectField(j_settings, env->GetFieldID(j_settings_cls, "chromeStatusBubble", "Lnet/kurobako/cef4j/gen/CefState;"));
            if (_wbn_chrome_status_bubble) {
                (settings)->chrome_status_bubble = static_cast<decltype((settings)->chrome_status_bubble)>(env->GetLongField(_wbn_chrome_status_bubble, env->GetFieldID(env->GetObjectClass(_wbn_chrome_status_bubble), "value", "J")));
            }
            auto _wbn_chrome_zoom_bubble = env->GetObjectField(j_settings, env->GetFieldID(j_settings_cls, "chromeZoomBubble", "Lnet/kurobako/cef4j/gen/CefState;"));
            if (_wbn_chrome_zoom_bubble) {
                (settings)->chrome_zoom_bubble = static_cast<decltype((settings)->chrome_zoom_bubble)>(env->GetLongField(_wbn_chrome_zoom_bubble, env->GetFieldID(env->GetObjectClass(_wbn_chrome_zoom_bubble), "value", "J")));
            }
        }
        if (extra_info) {
            auto j_extra_info_get = env->GetMethodID(j_extra_info_ar_cls, "get", "()Ljava/lang/Object;");
            auto j_extra_info_new = env->CallObjectMethod(j_extra_info, j_extra_info_get);
            if (j_extra_info_new && j_extra_info_new != j_extra_info_init) {
                auto j_extra_info_fid = env->GetFieldID(j_extra_info_peer_cls, "nativePtr", "J");
                jlong j_extra_info_ptr = env->GetLongField(j_extra_info_new, j_extra_info_fid);
                *extra_info = reinterpret_cast<cef_dictionary_value_t*>(j_extra_info_ptr);
            } else if (!j_extra_info_new) {
                *extra_info = nullptr;
            }
        }
        if (use_default_window) { jint _v; env->GetIntArrayRegion(j_use_default_window, 0, 1, &_v); *use_default_window = _v; }
        env->PopLocalFrame(nullptr);
    }

    static void CEF_CALLBACK _on_after_created(cef_life_span_handler_t* self, struct _cef_browser_t* browser) {
        auto* h = reinterpret_cast<JniCefLifeSpanHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) { return; }
        cef_browser_t* _p_browser = browser;
        if (_p_browser) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b); }
        auto j_browser_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onAfterCreated", "(Lnet/kurobako/cef4j/gen/CefBrowser;)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, j_browser);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        env->PopLocalFrame(nullptr);
    }

    static int CEF_CALLBACK _do_close(cef_life_span_handler_t* self, struct _cef_browser_t* browser) {
        auto* h = reinterpret_cast<JniCefLifeSpanHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) { return false; }
        cef_browser_t* _p_browser = browser;
        if (_p_browser) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b); }
        auto j_browser_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "doClose", "(Lnet/kurobako/cef4j/gen/CefBrowser;)Z");
        if (!mid) { env->PopLocalFrame(nullptr); return false; }
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, j_browser);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return false; }
        env->PopLocalFrame(nullptr);
        return jResult;
    }

    static void CEF_CALLBACK _on_before_close(cef_life_span_handler_t* self, struct _cef_browser_t* browser) {
        auto* h = reinterpret_cast<JniCefLifeSpanHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) { return; }
        cef_browser_t* _p_browser = browser;
        if (_p_browser) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b); }
        auto j_browser_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onBeforeClose", "(Lnet/kurobako/cef4j/gen/CefBrowser;)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, j_browser);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        env->PopLocalFrame(nullptr);
    }
};

extern "C" cef_life_span_handler_t* Create_JniCefLifeSpanHandler(JNIEnv* env, jobject handler) {
    JavaVM* jvm;
    env->GetJavaVM(&jvm);
    auto globalRef = env->NewGlobalRef(handler);
    return reinterpret_cast<cef_life_span_handler_t*>(new JniCefLifeSpanHandler(jvm, globalRef));
}
