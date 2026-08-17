// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
#include <jni.h>
#include "include/capi/views/cef_browser_view_delegate_capi.h"
#include "include/capi/cef_browser_capi.h"
#include "include/capi/cef_client_capi.h"
#include "include/capi/views/cef_browser_view_capi.h"
#include "jni_util.h"

#include <atomic>
#include "jni_util.h"

extern "C" cef_browser_view_delegate_t* Create_JniCefBrowserViewDelegate(JNIEnv* env, jobject handler);

struct JniCefBrowserViewDelegate : public cef_browser_view_delegate_t {
    JavaVM* jvm;
    jobject javaHandler;  // global ref
    std::atomic<int> refCount{1};

    JniCefBrowserViewDelegate(JavaVM* vm, jobject handler) : cef_browser_view_delegate_t{}, jvm(vm) {
        javaHandler = handler;
        InitRefCount<JniCefBrowserViewDelegate, cef_browser_view_delegate_t>(reinterpret_cast<cef_base_ref_counted_t*>(static_cast<cef_browser_view_delegate_t*>(this)));
        on_browser_created = &_on_browser_created;
        on_browser_destroyed = &_on_browser_destroyed;
        get_delegate_for_popup_browser_view = &_get_delegate_for_popup_browser_view;
        on_popup_browser_view_created = &_on_popup_browser_view_created;
        get_chrome_toolbar_type = &_get_chrome_toolbar_type;
        use_frameless_window_for_picture_in_picture = &_use_frameless_window_for_picture_in_picture;
        on_gesture_command = &_on_gesture_command;
        get_browser_runtime_style = &_get_browser_runtime_style;
        allow_move_for_picture_in_picture = &_allow_move_for_picture_in_picture;
        allow_picture_in_picture_without_user_activation = &_allow_picture_in_picture_without_user_activation;
    }

    static void CEF_CALLBACK _on_browser_created(cef_browser_view_delegate_t* self, struct _cef_browser_view_t* browser_view, struct _cef_browser_t* browser) {
        auto* h = reinterpret_cast<JniCefBrowserViewDelegate*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(11) < 0) { return; }
        cef_browser_view_t* _p_browser_view = browser_view;
        if (_p_browser_view) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser_view); _b->add_ref(_b); }
        auto j_browser_view_cls = FindClassCached(env, "net/kurobako/cef4j/gen/views/CefBrowserView$NativePeer");
        auto j_browser_view_ctor = env->GetMethodID(j_browser_view_cls, "<init>", "(J)V");
        auto j_browser_view = _p_browser_view ? env->NewObject(j_browser_view_cls, j_browser_view_ctor, reinterpret_cast<jlong>(_p_browser_view)) : nullptr;
        cef_browser_t* _p_browser = browser;
        if (_p_browser) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b); }
        auto j_browser_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onBrowserCreated", "(Lnet/kurobako/cef4j/gen/views/CefBrowserView;Lnet/kurobako/cef4j/gen/CefBrowser;)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, j_browser_view, j_browser);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        env->PopLocalFrame(nullptr);
    }

    static void CEF_CALLBACK _on_browser_destroyed(cef_browser_view_delegate_t* self, struct _cef_browser_view_t* browser_view, struct _cef_browser_t* browser) {
        auto* h = reinterpret_cast<JniCefBrowserViewDelegate*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(11) < 0) { return; }
        cef_browser_view_t* _p_browser_view = browser_view;
        if (_p_browser_view) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser_view); _b->add_ref(_b); }
        auto j_browser_view_cls = FindClassCached(env, "net/kurobako/cef4j/gen/views/CefBrowserView$NativePeer");
        auto j_browser_view_ctor = env->GetMethodID(j_browser_view_cls, "<init>", "(J)V");
        auto j_browser_view = _p_browser_view ? env->NewObject(j_browser_view_cls, j_browser_view_ctor, reinterpret_cast<jlong>(_p_browser_view)) : nullptr;
        cef_browser_t* _p_browser = browser;
        if (_p_browser) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b); }
        auto j_browser_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onBrowserDestroyed", "(Lnet/kurobako/cef4j/gen/views/CefBrowserView;Lnet/kurobako/cef4j/gen/CefBrowser;)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, j_browser_view, j_browser);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        env->PopLocalFrame(nullptr);
    }

    static cef_browser_view_delegate_t* CEF_CALLBACK _get_delegate_for_popup_browser_view(cef_browser_view_delegate_t* self, struct _cef_browser_view_t* browser_view, const struct _cef_browser_settings_t* settings, struct _cef_client_t* client, int is_devtools) {
        auto* h = reinterpret_cast<JniCefBrowserViewDelegate*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(67) < 0) { return nullptr; }
        cef_browser_view_t* _p_browser_view = browser_view;
        if (_p_browser_view) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser_view); _b->add_ref(_b); }
        auto j_browser_view_cls = FindClassCached(env, "net/kurobako/cef4j/gen/views/CefBrowserView$NativePeer");
        auto j_browser_view_ctor = env->GetMethodID(j_browser_view_cls, "<init>", "(J)V");
        auto j_browser_view = _p_browser_view ? env->NewObject(j_browser_view_cls, j_browser_view_ctor, reinterpret_cast<jlong>(_p_browser_view)) : nullptr;
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
        auto j_settings_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefBrowserSettings");
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
        cef_client_t* _p_client = client;
        if (_p_client) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_client); _b->add_ref(_b); }
        auto j_client_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefClient$NativePeer");
        auto j_client_ctor = env->GetMethodID(j_client_cls, "<init>", "(J)V");
        auto j_client = _p_client ? env->NewObject(j_client_cls, j_client_ctor, reinterpret_cast<jlong>(_p_client)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "getDelegateForPopupBrowserView", "(Lnet/kurobako/cef4j/gen/views/CefBrowserView;Lnet/kurobako/cef4j/gen/CefBrowserSettings;Lnet/kurobako/cef4j/gen/CefClient;Z)Ljava/util/Optional;");
        if (!mid) { env->PopLocalFrame(nullptr); return nullptr; }
        auto jResult = (jobject)env->CallObjectMethod(h->javaHandler, mid, j_browser_view, j_settings, j_client, static_cast<jboolean>(is_devtools));
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return nullptr; }
        cef_browser_view_delegate_t* nativeResult = nullptr;
        if (jResult) {
            auto _optCls = FindClassCached(env, "java/util/Optional");
            auto _isPresentMid = env->GetMethodID(_optCls, "isPresent", "()Z");
            if (env->CallBooleanMethod(jResult, _isPresentMid) == JNI_TRUE) {
                auto _getMid = env->GetMethodID(_optCls, "get", "()Ljava/lang/Object;");
                auto _handlerObj = env->CallObjectMethod(jResult, _getMid);
                nativeResult = Create_JniCefBrowserViewDelegate(env, _handlerObj);
            }
        }
        env->PopLocalFrame(nullptr);
        return nativeResult;
    }

    static int CEF_CALLBACK _on_popup_browser_view_created(cef_browser_view_delegate_t* self, struct _cef_browser_view_t* browser_view, struct _cef_browser_view_t* popup_browser_view, int is_devtools) {
        auto* h = reinterpret_cast<JniCefBrowserViewDelegate*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(11) < 0) { return false; }
        cef_browser_view_t* _p_browser_view = browser_view;
        if (_p_browser_view) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser_view); _b->add_ref(_b); }
        auto j_browser_view_cls = FindClassCached(env, "net/kurobako/cef4j/gen/views/CefBrowserView$NativePeer");
        auto j_browser_view_ctor = env->GetMethodID(j_browser_view_cls, "<init>", "(J)V");
        auto j_browser_view = _p_browser_view ? env->NewObject(j_browser_view_cls, j_browser_view_ctor, reinterpret_cast<jlong>(_p_browser_view)) : nullptr;
        cef_browser_view_t* _p_popup_browser_view = popup_browser_view;
        if (_p_popup_browser_view) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_popup_browser_view); _b->add_ref(_b); }
        auto j_popup_browser_view_cls = FindClassCached(env, "net/kurobako/cef4j/gen/views/CefBrowserView$NativePeer");
        auto j_popup_browser_view_ctor = env->GetMethodID(j_popup_browser_view_cls, "<init>", "(J)V");
        auto j_popup_browser_view = _p_popup_browser_view ? env->NewObject(j_popup_browser_view_cls, j_popup_browser_view_ctor, reinterpret_cast<jlong>(_p_popup_browser_view)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onPopupBrowserViewCreated", "(Lnet/kurobako/cef4j/gen/views/CefBrowserView;Lnet/kurobako/cef4j/gen/views/CefBrowserView;Z)Z");
        if (!mid) { env->PopLocalFrame(nullptr); return false; }
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, j_browser_view, j_popup_browser_view, static_cast<jboolean>(is_devtools));
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return false; }
        env->PopLocalFrame(nullptr);
        return jResult;
    }

    static cef_chrome_toolbar_type_t CEF_CALLBACK _get_chrome_toolbar_type(cef_browser_view_delegate_t* self, struct _cef_browser_view_t* browser_view) {
        auto* h = reinterpret_cast<JniCefBrowserViewDelegate*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(9) < 0) { return cef_chrome_toolbar_type_t::CEF_CTT_NONE; }
        cef_browser_view_t* _p_browser_view = browser_view;
        if (_p_browser_view) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser_view); _b->add_ref(_b); }
        auto j_browser_view_cls = FindClassCached(env, "net/kurobako/cef4j/gen/views/CefBrowserView$NativePeer");
        auto j_browser_view_ctor = env->GetMethodID(j_browser_view_cls, "<init>", "(J)V");
        auto j_browser_view = _p_browser_view ? env->NewObject(j_browser_view_cls, j_browser_view_ctor, reinterpret_cast<jlong>(_p_browser_view)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "getChromeToolbarType", "(Lnet/kurobako/cef4j/gen/views/CefBrowserView;)Lnet/kurobako/cef4j/gen/CefChromeToolbarType;");
        if (!mid) { env->PopLocalFrame(nullptr); return cef_chrome_toolbar_type_t::CEF_CTT_NONE; }
        auto jResult = (jobject)env->CallObjectMethod(h->javaHandler, mid, j_browser_view);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return cef_chrome_toolbar_type_t::CEF_CTT_NONE; }
        cef_chrome_toolbar_type_t nativeResult = static_cast<cef_chrome_toolbar_type_t>(jResult ? env->GetLongField(jResult, env->GetFieldID(env->GetObjectClass(jResult), "value", "J")) : 0);
        env->PopLocalFrame(nullptr);
        return nativeResult;
    }

    static int CEF_CALLBACK _use_frameless_window_for_picture_in_picture(cef_browser_view_delegate_t* self, struct _cef_browser_view_t* browser_view) {
        auto* h = reinterpret_cast<JniCefBrowserViewDelegate*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) { return false; }
        cef_browser_view_t* _p_browser_view = browser_view;
        if (_p_browser_view) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser_view); _b->add_ref(_b); }
        auto j_browser_view_cls = FindClassCached(env, "net/kurobako/cef4j/gen/views/CefBrowserView$NativePeer");
        auto j_browser_view_ctor = env->GetMethodID(j_browser_view_cls, "<init>", "(J)V");
        auto j_browser_view = _p_browser_view ? env->NewObject(j_browser_view_cls, j_browser_view_ctor, reinterpret_cast<jlong>(_p_browser_view)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "useFramelessWindowForPictureInPicture", "(Lnet/kurobako/cef4j/gen/views/CefBrowserView;)Z");
        if (!mid) { env->PopLocalFrame(nullptr); return false; }
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, j_browser_view);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return false; }
        env->PopLocalFrame(nullptr);
        return jResult;
    }

    static int CEF_CALLBACK _on_gesture_command(cef_browser_view_delegate_t* self, struct _cef_browser_view_t* browser_view, cef_gesture_command_t gesture_command) {
        auto* h = reinterpret_cast<JniCefBrowserViewDelegate*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(11) < 0) { return false; }
        cef_browser_view_t* _p_browser_view = browser_view;
        if (_p_browser_view) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser_view); _b->add_ref(_b); }
        auto j_browser_view_cls = FindClassCached(env, "net/kurobako/cef4j/gen/views/CefBrowserView$NativePeer");
        auto j_browser_view_ctor = env->GetMethodID(j_browser_view_cls, "<init>", "(J)V");
        auto j_browser_view = _p_browser_view ? env->NewObject(j_browser_view_cls, j_browser_view_ctor, reinterpret_cast<jlong>(_p_browser_view)) : nullptr;
        auto j_gesture_command_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefGestureCommand");
        auto j_gesture_command_from = env->GetStaticMethodID(j_gesture_command_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefGestureCommand;");
        auto j_gesture_command = env->CallStaticObjectMethod(j_gesture_command_cls, j_gesture_command_from, static_cast<jlong>(gesture_command));
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onGestureCommand", "(Lnet/kurobako/cef4j/gen/views/CefBrowserView;Lnet/kurobako/cef4j/gen/CefGestureCommand;)Z");
        if (!mid) { env->PopLocalFrame(nullptr); return false; }
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, j_browser_view, j_gesture_command);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return false; }
        env->PopLocalFrame(nullptr);
        return jResult;
    }

    static cef_runtime_style_t CEF_CALLBACK _get_browser_runtime_style(cef_browser_view_delegate_t* self) {
        auto* h = reinterpret_cast<JniCefBrowserViewDelegate*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(6) < 0) { return cef_runtime_style_t::CEF_RUNTIME_STYLE_DEFAULT; }
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "getBrowserRuntimeStyle", "()Lnet/kurobako/cef4j/gen/CefRuntimeStyle;");
        if (!mid) { env->PopLocalFrame(nullptr); return cef_runtime_style_t::CEF_RUNTIME_STYLE_DEFAULT; }
        auto jResult = (jobject)env->CallObjectMethod(h->javaHandler, mid);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return cef_runtime_style_t::CEF_RUNTIME_STYLE_DEFAULT; }
        cef_runtime_style_t nativeResult = static_cast<cef_runtime_style_t>(jResult ? env->GetLongField(jResult, env->GetFieldID(env->GetObjectClass(jResult), "value", "J")) : 0);
        env->PopLocalFrame(nullptr);
        return nativeResult;
    }

    static int CEF_CALLBACK _allow_move_for_picture_in_picture(cef_browser_view_delegate_t* self, struct _cef_browser_view_t* browser_view) {
        auto* h = reinterpret_cast<JniCefBrowserViewDelegate*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) { return false; }
        cef_browser_view_t* _p_browser_view = browser_view;
        if (_p_browser_view) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser_view); _b->add_ref(_b); }
        auto j_browser_view_cls = FindClassCached(env, "net/kurobako/cef4j/gen/views/CefBrowserView$NativePeer");
        auto j_browser_view_ctor = env->GetMethodID(j_browser_view_cls, "<init>", "(J)V");
        auto j_browser_view = _p_browser_view ? env->NewObject(j_browser_view_cls, j_browser_view_ctor, reinterpret_cast<jlong>(_p_browser_view)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "allowMoveForPictureInPicture", "(Lnet/kurobako/cef4j/gen/views/CefBrowserView;)Z");
        if (!mid) { env->PopLocalFrame(nullptr); return false; }
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, j_browser_view);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return false; }
        env->PopLocalFrame(nullptr);
        return jResult;
    }

    static int CEF_CALLBACK _allow_picture_in_picture_without_user_activation(cef_browser_view_delegate_t* self, struct _cef_browser_view_t* browser_view) {
        auto* h = reinterpret_cast<JniCefBrowserViewDelegate*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) { return false; }
        cef_browser_view_t* _p_browser_view = browser_view;
        if (_p_browser_view) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser_view); _b->add_ref(_b); }
        auto j_browser_view_cls = FindClassCached(env, "net/kurobako/cef4j/gen/views/CefBrowserView$NativePeer");
        auto j_browser_view_ctor = env->GetMethodID(j_browser_view_cls, "<init>", "(J)V");
        auto j_browser_view = _p_browser_view ? env->NewObject(j_browser_view_cls, j_browser_view_ctor, reinterpret_cast<jlong>(_p_browser_view)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "allowPictureInPictureWithoutUserActivation", "(Lnet/kurobako/cef4j/gen/views/CefBrowserView;)Z");
        if (!mid) { env->PopLocalFrame(nullptr); return false; }
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, j_browser_view);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return false; }
        env->PopLocalFrame(nullptr);
        return jResult;
    }
};

extern "C" cef_browser_view_delegate_t* Create_JniCefBrowserViewDelegate(JNIEnv* env, jobject handler) {
    JavaVM* jvm;
    env->GetJavaVM(&jvm);
    auto globalRef = env->NewGlobalRef(handler);
    return reinterpret_cast<cef_browser_view_delegate_t*>(new JniCefBrowserViewDelegate(jvm, globalRef));
}
