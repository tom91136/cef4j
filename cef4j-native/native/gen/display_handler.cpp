// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
#include <jni.h>
#include "include/capi/cef_display_handler_capi.h"
#include "include/capi/cef_browser_capi.h"
#include "include/capi/cef_frame_capi.h"
#include "jni_util.h"

#include <atomic>
#include "jni_util.h"

struct JniCefDisplayHandler : public cef_display_handler_t {
    JavaVM* jvm;
    jobject javaHandler;  // global ref
    std::atomic<int> refCount{1};

    JniCefDisplayHandler(JavaVM* vm, jobject handler) : cef_display_handler_t{}, jvm(vm) {
        javaHandler = handler;
        InitRefCount<JniCefDisplayHandler, cef_display_handler_t>(reinterpret_cast<cef_base_ref_counted_t*>(static_cast<cef_display_handler_t*>(this)));
        on_address_change = &_on_address_change;
        on_title_change = &_on_title_change;
        on_favicon_urlchange = &_on_favicon_urlchange;
        on_fullscreen_mode_change = &_on_fullscreen_mode_change;
        on_tooltip = &_on_tooltip;
        on_status_message = &_on_status_message;
        on_console_message = &_on_console_message;
        on_auto_resize = &_on_auto_resize;
        on_loading_progress_change = &_on_loading_progress_change;
        on_cursor_change = &_on_cursor_change;
        on_media_access_change = &_on_media_access_change;
        on_contents_bounds_change = &_on_contents_bounds_change;
        get_root_window_screen_rect = &_get_root_window_screen_rect;
    }

    static void CEF_CALLBACK _on_address_change(cef_display_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, const cef_string_t* url) {
        auto* h = reinterpret_cast<JniCefDisplayHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(12) < 0) { return; }
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
        auto j_url = CefStringToJString(env, url);
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onAddressChange", "(Lnet/kurobako/cef4j/gen/CefBrowser;Lnet/kurobako/cef4j/gen/CefFrame;Ljava/lang/String;)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, j_browser, j_frame, j_url);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        env->PopLocalFrame(nullptr);
    }

    static void CEF_CALLBACK _on_title_change(cef_display_handler_t* self, struct _cef_browser_t* browser, const cef_string_t* title) {
        auto* h = reinterpret_cast<JniCefDisplayHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(9) < 0) { return; }
        cef_browser_t* _p_browser = browser;
        if (_p_browser) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b); }
        auto j_browser_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        auto j_title = CefStringToJString(env, title);
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onTitleChange", "(Lnet/kurobako/cef4j/gen/CefBrowser;Ljava/lang/String;)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, j_browser, j_title);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        env->PopLocalFrame(nullptr);
    }

    static void CEF_CALLBACK _on_favicon_urlchange(cef_display_handler_t* self, struct _cef_browser_t* browser, cef_string_list_t icon_urls) {
        auto* h = reinterpret_cast<JniCefDisplayHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(9) < 0) { return; }
        cef_browser_t* _p_browser = browser;
        if (_p_browser) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b); }
        auto j_browser_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        auto j_icon_urls = CefStringListToJavaList(env, icon_urls);
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onFaviconUrlChange", "(Lnet/kurobako/cef4j/gen/CefBrowser;Ljava/util/List;)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, j_browser, j_icon_urls);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        env->PopLocalFrame(nullptr);
    }

    static void CEF_CALLBACK _on_fullscreen_mode_change(cef_display_handler_t* self, struct _cef_browser_t* browser, int fullscreen) {
        auto* h = reinterpret_cast<JniCefDisplayHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) { return; }
        cef_browser_t* _p_browser = browser;
        if (_p_browser) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b); }
        auto j_browser_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onFullscreenModeChange", "(Lnet/kurobako/cef4j/gen/CefBrowser;Z)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, j_browser, static_cast<jboolean>(fullscreen));
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        env->PopLocalFrame(nullptr);
    }

    static int CEF_CALLBACK _on_tooltip(cef_display_handler_t* self, struct _cef_browser_t* browser, cef_string_t* text) {
        auto* h = reinterpret_cast<JniCefDisplayHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(9) < 0) { return false; }
        cef_browser_t* _p_browser = browser;
        if (_p_browser) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b); }
        auto j_browser_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        auto j_text = CefStringToJString(env, text);
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onTooltip", "(Lnet/kurobako/cef4j/gen/CefBrowser;Ljava/lang/String;)Z");
        if (!mid) { env->PopLocalFrame(nullptr); return false; }
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, j_browser, j_text);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return false; }
        env->PopLocalFrame(nullptr);
        return jResult;
    }

    static void CEF_CALLBACK _on_status_message(cef_display_handler_t* self, struct _cef_browser_t* browser, const cef_string_t* value) {
        auto* h = reinterpret_cast<JniCefDisplayHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(9) < 0) { return; }
        cef_browser_t* _p_browser = browser;
        if (_p_browser) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b); }
        auto j_browser_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        auto j_value = CefStringToJString(env, value);
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onStatusMessage", "(Lnet/kurobako/cef4j/gen/CefBrowser;Ljava/lang/String;)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, j_browser, j_value);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        env->PopLocalFrame(nullptr);
    }

    static int CEF_CALLBACK _on_console_message(cef_display_handler_t* self, struct _cef_browser_t* browser, cef_log_severity_t level, const cef_string_t* message, const cef_string_t* source, int line) {
        auto* h = reinterpret_cast<JniCefDisplayHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(13) < 0) { return false; }
        cef_browser_t* _p_browser = browser;
        if (_p_browser) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b); }
        auto j_browser_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        auto j_level_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefLogSeverity");
        auto j_level_from = env->GetStaticMethodID(j_level_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefLogSeverity;");
        auto j_level = env->CallStaticObjectMethod(j_level_cls, j_level_from, static_cast<jlong>(level));
        auto j_message = CefStringToJString(env, message);
        auto j_source = CefStringToJString(env, source);
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onConsoleMessage", "(Lnet/kurobako/cef4j/gen/CefBrowser;Lnet/kurobako/cef4j/gen/CefLogSeverity;Ljava/lang/String;Ljava/lang/String;I)Z");
        if (!mid) { env->PopLocalFrame(nullptr); return false; }
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, j_browser, j_level, j_message, j_source, static_cast<jint>(line));
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return false; }
        env->PopLocalFrame(nullptr);
        return jResult;
    }

    static int CEF_CALLBACK _on_auto_resize(cef_display_handler_t* self, struct _cef_browser_t* browser, const cef_size_t* new_size) {
        auto* h = reinterpret_cast<JniCefDisplayHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(11) < 0) { return false; }
        cef_browser_t* _p_browser = browser;
        if (_p_browser) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b); }
        auto j_browser_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        auto j_new_size_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefSize");
        auto j_new_size_ctor = env->GetMethodID(j_new_size_cls, "<init>", "(II)V");
        auto j_new_size = new_size ? env->NewObject(j_new_size_cls, j_new_size_ctor, static_cast<jint>(new_size->width), static_cast<jint>(new_size->height)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onAutoResize", "(Lnet/kurobako/cef4j/gen/CefBrowser;Lnet/kurobako/cef4j/gen/CefSize;)Z");
        if (!mid) { env->PopLocalFrame(nullptr); return false; }
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, j_browser, j_new_size);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return false; }
        env->PopLocalFrame(nullptr);
        return jResult;
    }

    static void CEF_CALLBACK _on_loading_progress_change(cef_display_handler_t* self, struct _cef_browser_t* browser, double progress) {
        auto* h = reinterpret_cast<JniCefDisplayHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) { return; }
        cef_browser_t* _p_browser = browser;
        if (_p_browser) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b); }
        auto j_browser_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onLoadingProgressChange", "(Lnet/kurobako/cef4j/gen/CefBrowser;D)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, j_browser, static_cast<jdouble>(progress));
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        env->PopLocalFrame(nullptr);
    }

    static int CEF_CALLBACK _on_cursor_change(cef_display_handler_t* self, struct _cef_browser_t* browser, cef_cursor_handle_t cursor, cef_cursor_type_t type, const cef_cursor_info_t* custom_cursor_info) {
        auto* h = reinterpret_cast<JniCefDisplayHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(14) < 0) { return false; }
        cef_browser_t* _p_browser = browser;
        if (_p_browser) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b); }
        auto j_browser_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        auto j_type_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefCursorType");
        auto j_type_from = env->GetStaticMethodID(j_type_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefCursorType;");
        auto j_type = env->CallStaticObjectMethod(j_type_cls, j_type_from, static_cast<jlong>(type));
        auto j_custom_cursor_info_cls = FindClassCached(env, "net/kurobako/cef4j/gen/NativePointer");
        auto j_custom_cursor_info_ctor = env->GetMethodID(j_custom_cursor_info_cls, "<init>", "(J)V");
        auto j_custom_cursor_info = env->NewObject(j_custom_cursor_info_cls, j_custom_cursor_info_ctor, reinterpret_cast<jlong>(custom_cursor_info));
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onCursorChange", "(Lnet/kurobako/cef4j/gen/CefBrowser;JLnet/kurobako/cef4j/gen/CefCursorType;Lnet/kurobako/cef4j/gen/NativePointer;)Z");
        if (!mid) { env->PopLocalFrame(nullptr); return false; }
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, j_browser, (jlong)(cursor), j_type, j_custom_cursor_info);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return false; }
        env->PopLocalFrame(nullptr);
        return jResult;
    }

    static void CEF_CALLBACK _on_media_access_change(cef_display_handler_t* self, struct _cef_browser_t* browser, int has_video_access, int has_audio_access) {
        auto* h = reinterpret_cast<JniCefDisplayHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) { return; }
        cef_browser_t* _p_browser = browser;
        if (_p_browser) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b); }
        auto j_browser_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onMediaAccessChange", "(Lnet/kurobako/cef4j/gen/CefBrowser;ZZ)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, j_browser, static_cast<jboolean>(has_video_access), static_cast<jboolean>(has_audio_access));
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        env->PopLocalFrame(nullptr);
    }

    static int CEF_CALLBACK _on_contents_bounds_change(cef_display_handler_t* self, struct _cef_browser_t* browser, const cef_rect_t* new_bounds) {
        auto* h = reinterpret_cast<JniCefDisplayHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(11) < 0) { return false; }
        cef_browser_t* _p_browser = browser;
        if (_p_browser) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b); }
        auto j_browser_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        auto j_new_bounds_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefRect");
        auto j_new_bounds_ctor = env->GetMethodID(j_new_bounds_cls, "<init>", "(IIII)V");
        auto j_new_bounds = new_bounds ? env->NewObject(j_new_bounds_cls, j_new_bounds_ctor, static_cast<jint>(new_bounds->x), static_cast<jint>(new_bounds->y), static_cast<jint>(new_bounds->width), static_cast<jint>(new_bounds->height)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onContentsBoundsChange", "(Lnet/kurobako/cef4j/gen/CefBrowser;Lnet/kurobako/cef4j/gen/CefRect;)Z");
        if (!mid) { env->PopLocalFrame(nullptr); return false; }
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, j_browser, j_new_bounds);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return false; }
        env->PopLocalFrame(nullptr);
        return jResult;
    }

    static int CEF_CALLBACK _get_root_window_screen_rect(cef_display_handler_t* self, struct _cef_browser_t* browser, cef_rect_t* rect) {
        auto* h = reinterpret_cast<JniCefDisplayHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(11) < 0) { return false; }
        cef_browser_t* _p_browser = browser;
        if (_p_browser) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b); }
        auto j_browser_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        auto j_rect_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefRect$Mutable");
        auto j_rect_ctor = env->GetMethodID(j_rect_cls, "<init>", "(IIII)V");
        auto j_rect = rect ? env->NewObject(j_rect_cls, j_rect_ctor, static_cast<jint>(rect->x), static_cast<jint>(rect->y), static_cast<jint>(rect->width), static_cast<jint>(rect->height)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "getRootWindowScreenRect", "(Lnet/kurobako/cef4j/gen/CefBrowser;Lnet/kurobako/cef4j/gen/CefRect$Mutable;)Z");
        if (!mid) { env->PopLocalFrame(nullptr); return false; }
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, j_browser, j_rect);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return false; }
        if (rect && j_rect) {
            rect->x = static_cast<decltype(rect->x)>(env->GetIntField(j_rect, env->GetFieldID(j_rect_cls, "x", "I")));
            rect->y = static_cast<decltype(rect->y)>(env->GetIntField(j_rect, env->GetFieldID(j_rect_cls, "y", "I")));
            rect->width = static_cast<decltype(rect->width)>(env->GetIntField(j_rect, env->GetFieldID(j_rect_cls, "width", "I")));
            rect->height = static_cast<decltype(rect->height)>(env->GetIntField(j_rect, env->GetFieldID(j_rect_cls, "height", "I")));
        }
        env->PopLocalFrame(nullptr);
        return jResult;
    }
};

extern "C" cef_display_handler_t* Create_JniCefDisplayHandler(JNIEnv* env, jobject handler) {
    JavaVM* jvm;
    env->GetJavaVM(&jvm);
    auto globalRef = env->NewGlobalRef(handler);
    return reinterpret_cast<cef_display_handler_t*>(new JniCefDisplayHandler(jvm, globalRef));
}
