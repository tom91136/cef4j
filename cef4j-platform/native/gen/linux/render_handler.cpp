// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
#include <jni.h>
#include "include/capi/cef_render_handler_capi.h"
#include "include/capi/cef_accessibility_handler_capi.h"
#include "include/capi/cef_browser_capi.h"
#include "include/capi/cef_drag_data_capi.h"
#include "jni_util.h"

#include <atomic>
#include <vector>

extern "C" cef_accessibility_handler_t* Create_JniCefAccessibilityHandler(JNIEnv* env, jobject handler);

struct JniCefRenderHandler : public cef_render_handler_t {
    JavaVM* jvm;
    jobject javaHandler;  // global ref
    std::atomic<int> refCount{1};

    JniCefRenderHandler(JavaVM* vm, jobject handler) : cef_render_handler_t{}, jvm(vm) {
        javaHandler = handler;
        InitRefCount<JniCefRenderHandler, cef_render_handler_t>(reinterpret_cast<cef_base_ref_counted_t*>(static_cast<cef_render_handler_t*>(this)));
        get_accessibility_handler = &_get_accessibility_handler;
        get_root_screen_rect = &_get_root_screen_rect;
        get_view_rect = &_get_view_rect;
        get_screen_point = &_get_screen_point;
        get_screen_info = &_get_screen_info;
        on_popup_show = &_on_popup_show;
        on_popup_size = &_on_popup_size;
        on_paint = &_on_paint;
        on_accelerated_paint = &_on_accelerated_paint;
        get_touch_handle_size = &_get_touch_handle_size;
        on_touch_handle_state_changed = &_on_touch_handle_state_changed;
        start_dragging = &_start_dragging;
        update_drag_cursor = &_update_drag_cursor;
        on_scroll_offset_changed = &_on_scroll_offset_changed;
        on_ime_composition_range_changed = &_on_ime_composition_range_changed;
        on_text_selection_changed = &_on_text_selection_changed;
        on_virtual_keyboard_requested = &_on_virtual_keyboard_requested;
    }

    static cef_accessibility_handler_t* CEF_CALLBACK _get_accessibility_handler(cef_render_handler_t* self) {
        auto* h = reinterpret_cast<JniCefRenderHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(9) < 0) { return nullptr; }
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "getAccessibilityHandler", "()Ljava/util/Optional;");
        if (!mid) { env->PopLocalFrame(nullptr); return nullptr; }
        auto jResult = (jobject)env->CallObjectMethod(h->javaHandler, mid);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return nullptr; }
        cef_accessibility_handler_t* nativeResult = nullptr;
        if (jResult) {
            auto _optCls = FindClassCached(env, "java/util/Optional");
            auto _isPresentMid = env->GetMethodID(_optCls, "isPresent", "()Z");
            if (env->CallBooleanMethod(jResult, _isPresentMid) == JNI_TRUE) {
                auto _getMid = env->GetMethodID(_optCls, "get", "()Ljava/lang/Object;");
                auto _handlerObj = env->CallObjectMethod(jResult, _getMid);
                nativeResult = Create_JniCefAccessibilityHandler(env, _handlerObj);
            }
        }
        env->PopLocalFrame(nullptr);
        return nativeResult;
    }

    static int CEF_CALLBACK _get_root_screen_rect(cef_render_handler_t* self, struct _cef_browser_t* browser, cef_rect_t* rect) {
        auto* h = reinterpret_cast<JniCefRenderHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(11) < 0) { return false; }
        cef_browser_t* _p_browser = browser;
        if (_p_browser) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b); }
        auto j_browser_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        auto j_rect_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefRect$Mutable");
        auto j_rect_ctor = env->GetMethodID(j_rect_cls, "<init>", "(IIII)V");
        auto j_rect = rect ? env->NewObject(j_rect_cls, j_rect_ctor, static_cast<jint>((rect)->x), static_cast<jint>((rect)->y), static_cast<jint>((rect)->width), static_cast<jint>((rect)->height)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "getRootScreenRect", "(Lnet/kurobako/cef4j/gen/CefBrowser;Lnet/kurobako/cef4j/gen/CefRect$Mutable;)Z");
        if (!mid) { env->PopLocalFrame(nullptr); return false; }
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, j_browser, j_rect);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return false; }
        if (rect && j_rect) {
            (rect)->x = static_cast<decltype((rect)->x)>(env->GetIntField(j_rect, env->GetFieldID(j_rect_cls, "x", "I")));
            (rect)->y = static_cast<decltype((rect)->y)>(env->GetIntField(j_rect, env->GetFieldID(j_rect_cls, "y", "I")));
            (rect)->width = static_cast<decltype((rect)->width)>(env->GetIntField(j_rect, env->GetFieldID(j_rect_cls, "width", "I")));
            (rect)->height = static_cast<decltype((rect)->height)>(env->GetIntField(j_rect, env->GetFieldID(j_rect_cls, "height", "I")));
        }
        env->PopLocalFrame(nullptr);
        return jResult;
    }

    static void CEF_CALLBACK _get_view_rect(cef_render_handler_t* self, struct _cef_browser_t* browser, cef_rect_t* rect) {
        auto* h = reinterpret_cast<JniCefRenderHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(11) < 0) { return; }
        cef_browser_t* _p_browser = browser;
        if (_p_browser) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b); }
        auto j_browser_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        auto j_rect_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefRect$Mutable");
        auto j_rect_ctor = env->GetMethodID(j_rect_cls, "<init>", "(IIII)V");
        auto j_rect = rect ? env->NewObject(j_rect_cls, j_rect_ctor, static_cast<jint>((rect)->x), static_cast<jint>((rect)->y), static_cast<jint>((rect)->width), static_cast<jint>((rect)->height)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "getViewRect", "(Lnet/kurobako/cef4j/gen/CefBrowser;Lnet/kurobako/cef4j/gen/CefRect$Mutable;)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, j_browser, j_rect);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        if (rect && j_rect) {
            (rect)->x = static_cast<decltype((rect)->x)>(env->GetIntField(j_rect, env->GetFieldID(j_rect_cls, "x", "I")));
            (rect)->y = static_cast<decltype((rect)->y)>(env->GetIntField(j_rect, env->GetFieldID(j_rect_cls, "y", "I")));
            (rect)->width = static_cast<decltype((rect)->width)>(env->GetIntField(j_rect, env->GetFieldID(j_rect_cls, "width", "I")));
            (rect)->height = static_cast<decltype((rect)->height)>(env->GetIntField(j_rect, env->GetFieldID(j_rect_cls, "height", "I")));
        }
        env->PopLocalFrame(nullptr);
    }

    static int CEF_CALLBACK _get_screen_point(cef_render_handler_t* self, struct _cef_browser_t* browser, int viewX, int viewY, int* screenX, int* screenY) {
        auto* h = reinterpret_cast<JniCefRenderHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(10) < 0) { return false; }
        cef_browser_t* _p_browser = browser;
        if (_p_browser) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b); }
        auto j_browser_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        jintArray j_screenX = env->NewIntArray(1);
        if (screenX) { jint _v = *screenX; env->SetIntArrayRegion(j_screenX, 0, 1, &_v); }
        jintArray j_screenY = env->NewIntArray(1);
        if (screenY) { jint _v = *screenY; env->SetIntArrayRegion(j_screenY, 0, 1, &_v); }
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "getScreenPoint", "(Lnet/kurobako/cef4j/gen/CefBrowser;II[I[I)Z");
        if (!mid) { env->PopLocalFrame(nullptr); return false; }
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, j_browser, static_cast<jint>(viewX), static_cast<jint>(viewY), j_screenX, j_screenY);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return false; }
        if (screenX) { jint _v; env->GetIntArrayRegion(j_screenX, 0, 1, &_v); *screenX = _v; }
        if (screenY) { jint _v; env->GetIntArrayRegion(j_screenY, 0, 1, &_v); *screenY = _v; }
        env->PopLocalFrame(nullptr);
        return jResult;
    }

    static int CEF_CALLBACK _get_screen_info(cef_render_handler_t* self, struct _cef_browser_t* browser, cef_screen_info_t* screen_info) {
        auto* h = reinterpret_cast<JniCefRenderHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(21) < 0) { return false; }
        cef_browser_t* _p_browser = browser;
        if (_p_browser) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b); }
        auto j_browser_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        auto _bv_screen_info_rect_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefRect");
        auto _bv_screen_info_rect_ctor = env->GetMethodID(_bv_screen_info_rect_cls, "<init>", "(IIII)V");
        auto _bv_screen_info_rect = env->NewObject(_bv_screen_info_rect_cls, _bv_screen_info_rect_ctor, static_cast<jint>((screen_info)->rect.x), static_cast<jint>((screen_info)->rect.y), static_cast<jint>((screen_info)->rect.width), static_cast<jint>((screen_info)->rect.height));
        auto _bv_screen_info_available_rect_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefRect");
        auto _bv_screen_info_available_rect_ctor = env->GetMethodID(_bv_screen_info_available_rect_cls, "<init>", "(IIII)V");
        auto _bv_screen_info_available_rect = env->NewObject(_bv_screen_info_available_rect_cls, _bv_screen_info_available_rect_ctor, static_cast<jint>((screen_info)->available_rect.x), static_cast<jint>((screen_info)->available_rect.y), static_cast<jint>((screen_info)->available_rect.width), static_cast<jint>((screen_info)->available_rect.height));
        auto j_screen_info_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefScreenInfo$Mutable");
        auto j_screen_info_ctor = env->GetMethodID(j_screen_info_cls, "<init>", "(FIIILnet/kurobako/cef4j/gen/CefRect;Lnet/kurobako/cef4j/gen/CefRect;)V");
        auto j_screen_info = screen_info
    ? env->NewObject(j_screen_info_cls, j_screen_info_ctor,
        static_cast<jfloat>((screen_info)->device_scale_factor),
        static_cast<jint>((screen_info)->depth),
        static_cast<jint>((screen_info)->depth_per_component),
        static_cast<jint>((screen_info)->is_monochrome),
        _bv_screen_info_rect,
        _bv_screen_info_available_rect)
    : nullptr;
        if (j_screen_info) env->SetLongField(j_screen_info, env->GetFieldID(j_screen_info_cls, "size", "J"), static_cast<jlong>(screen_info->size));
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "getScreenInfo", "(Lnet/kurobako/cef4j/gen/CefBrowser;Lnet/kurobako/cef4j/gen/CefScreenInfo$Mutable;)Z");
        if (!mid) { env->PopLocalFrame(nullptr); return false; }
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, j_browser, j_screen_info);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return false; }
        if (screen_info && j_screen_info) {
            (screen_info)->device_scale_factor = static_cast<decltype((screen_info)->device_scale_factor)>(env->GetFloatField(j_screen_info, env->GetFieldID(j_screen_info_cls, "deviceScaleFactor", "F")));
            (screen_info)->depth = static_cast<decltype((screen_info)->depth)>(env->GetIntField(j_screen_info, env->GetFieldID(j_screen_info_cls, "depth", "I")));
            (screen_info)->depth_per_component = static_cast<decltype((screen_info)->depth_per_component)>(env->GetIntField(j_screen_info, env->GetFieldID(j_screen_info_cls, "depthPerComponent", "I")));
            (screen_info)->is_monochrome = static_cast<decltype((screen_info)->is_monochrome)>(env->GetIntField(j_screen_info, env->GetFieldID(j_screen_info_cls, "isMonochrome", "I")));
            auto _wbn_rect = env->GetObjectField(j_screen_info, env->GetFieldID(j_screen_info_cls, "rect", "Lnet/kurobako/cef4j/gen/CefRect;"));
            if (_wbn_rect) {
                auto _wbn_rectc = env->GetObjectClass(_wbn_rect);
                (screen_info)->rect.x = static_cast<decltype((screen_info)->rect.x)>(env->GetIntField(_wbn_rect, env->GetFieldID(_wbn_rectc, "x", "I")));
                (screen_info)->rect.y = static_cast<decltype((screen_info)->rect.y)>(env->GetIntField(_wbn_rect, env->GetFieldID(_wbn_rectc, "y", "I")));
                (screen_info)->rect.width = static_cast<decltype((screen_info)->rect.width)>(env->GetIntField(_wbn_rect, env->GetFieldID(_wbn_rectc, "width", "I")));
                (screen_info)->rect.height = static_cast<decltype((screen_info)->rect.height)>(env->GetIntField(_wbn_rect, env->GetFieldID(_wbn_rectc, "height", "I")));
            }
            auto _wbn_available_rect = env->GetObjectField(j_screen_info, env->GetFieldID(j_screen_info_cls, "availableRect", "Lnet/kurobako/cef4j/gen/CefRect;"));
            if (_wbn_available_rect) {
                auto _wbn_available_rectc = env->GetObjectClass(_wbn_available_rect);
                (screen_info)->available_rect.x = static_cast<decltype((screen_info)->available_rect.x)>(env->GetIntField(_wbn_available_rect, env->GetFieldID(_wbn_available_rectc, "x", "I")));
                (screen_info)->available_rect.y = static_cast<decltype((screen_info)->available_rect.y)>(env->GetIntField(_wbn_available_rect, env->GetFieldID(_wbn_available_rectc, "y", "I")));
                (screen_info)->available_rect.width = static_cast<decltype((screen_info)->available_rect.width)>(env->GetIntField(_wbn_available_rect, env->GetFieldID(_wbn_available_rectc, "width", "I")));
                (screen_info)->available_rect.height = static_cast<decltype((screen_info)->available_rect.height)>(env->GetIntField(_wbn_available_rect, env->GetFieldID(_wbn_available_rectc, "height", "I")));
            }
        }
        env->PopLocalFrame(nullptr);
        return jResult;
    }

    static void CEF_CALLBACK _on_popup_show(cef_render_handler_t* self, struct _cef_browser_t* browser, int show) {
        auto* h = reinterpret_cast<JniCefRenderHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) { return; }
        cef_browser_t* _p_browser = browser;
        if (_p_browser) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b); }
        auto j_browser_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onPopupShow", "(Lnet/kurobako/cef4j/gen/CefBrowser;Z)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, j_browser, static_cast<jboolean>(show));
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        env->PopLocalFrame(nullptr);
    }

    static void CEF_CALLBACK _on_popup_size(cef_render_handler_t* self, struct _cef_browser_t* browser, const cef_rect_t* rect) {
        auto* h = reinterpret_cast<JniCefRenderHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(11) < 0) { return; }
        cef_browser_t* _p_browser = browser;
        if (_p_browser) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b); }
        auto j_browser_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        auto j_rect_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefRect");
        auto j_rect_ctor = env->GetMethodID(j_rect_cls, "<init>", "(IIII)V");
        auto j_rect = rect ? env->NewObject(j_rect_cls, j_rect_ctor, static_cast<jint>((rect)->x), static_cast<jint>((rect)->y), static_cast<jint>((rect)->width), static_cast<jint>((rect)->height)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onPopupSize", "(Lnet/kurobako/cef4j/gen/CefBrowser;Lnet/kurobako/cef4j/gen/CefRect;)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, j_browser, j_rect);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        env->PopLocalFrame(nullptr);
    }

    static void CEF_CALLBACK _on_paint(cef_render_handler_t* self, struct _cef_browser_t* browser, cef_paint_element_type_t type, size_t dirtyRectsCount, cef_rect_t const* dirtyRects, const void* buffer, int width, int height) {
        auto* h = reinterpret_cast<JniCefRenderHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(23) < 0) { return; }
        cef_browser_t* _p_browser = browser;
        if (_p_browser) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b); }
        auto j_browser_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        auto j_type_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefPaintElementType");
        auto j_type_from = env->GetStaticMethodID(j_type_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefPaintElementType;");
        auto j_type = env->CallStaticObjectMethod(j_type_cls, j_type_from, static_cast<jlong>(type));
        auto j_dirtyRects_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefRect");
        auto j_dirtyRects_ctor = env->GetMethodID(j_dirtyRects_cls, "<init>", "(IIII)V");
        jint j_dirtyRects_len = static_cast<jint>(dirtyRectsCount);
        auto j_dirtyRects = env->NewObjectArray(j_dirtyRects_len, j_dirtyRects_cls, nullptr);
        for (jint _i = 0; _i < j_dirtyRects_len; _i++) {
            auto _elem = env->NewObject(j_dirtyRects_cls, j_dirtyRects_ctor, static_cast<jint>(((&dirtyRects[_i]))->x), static_cast<jint>(((&dirtyRects[_i]))->y), static_cast<jint>(((&dirtyRects[_i]))->width), static_cast<jint>(((&dirtyRects[_i]))->height));
            env->SetObjectArrayElement(j_dirtyRects, _i, _elem);
            env->DeleteLocalRef(_elem);
        }
        jlong j_buffer_len = static_cast<jlong>(width) * static_cast<jlong>(height) * 4;
        jobject j_buffer = (buffer && j_buffer_len > 0) ? env->NewDirectByteBuffer(const_cast<void*>(buffer), j_buffer_len) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onPaint", "(Lnet/kurobako/cef4j/gen/CefBrowser;Lnet/kurobako/cef4j/gen/CefPaintElementType;J[Lnet/kurobako/cef4j/gen/CefRect;Ljava/nio/ByteBuffer;II)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, j_browser, j_type, static_cast<jlong>(dirtyRectsCount), j_dirtyRects, j_buffer, static_cast<jint>(width), static_cast<jint>(height));
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        env->PopLocalFrame(nullptr);
    }

    static void CEF_CALLBACK _on_accelerated_paint(cef_render_handler_t* self, struct _cef_browser_t* browser, cef_paint_element_type_t type, size_t dirtyRectsCount, cef_rect_t const* dirtyRects, const cef_accelerated_paint_info_t* info) {
        auto* h = reinterpret_cast<JniCefRenderHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(49) < 0) { return; }
        cef_browser_t* _p_browser = browser;
        if (_p_browser) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b); }
        auto j_browser_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        auto j_type_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefPaintElementType");
        auto j_type_from = env->GetStaticMethodID(j_type_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefPaintElementType;");
        auto j_type = env->CallStaticObjectMethod(j_type_cls, j_type_from, static_cast<jlong>(type));
        auto j_dirtyRects_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefRect");
        auto j_dirtyRects_ctor = env->GetMethodID(j_dirtyRects_cls, "<init>", "(IIII)V");
        jint j_dirtyRects_len = static_cast<jint>(dirtyRectsCount);
        auto j_dirtyRects = env->NewObjectArray(j_dirtyRects_len, j_dirtyRects_cls, nullptr);
        for (jint _i = 0; _i < j_dirtyRects_len; _i++) {
            auto _elem = env->NewObject(j_dirtyRects_cls, j_dirtyRects_ctor, static_cast<jint>(((&dirtyRects[_i]))->x), static_cast<jint>(((&dirtyRects[_i]))->y), static_cast<jint>(((&dirtyRects[_i]))->width), static_cast<jint>(((&dirtyRects[_i]))->height));
            env->SetObjectArrayElement(j_dirtyRects, _i, _elem);
            env->DeleteLocalRef(_elem);
        }
        jobject j_info = nullptr;
        if (info) {
            auto _bv_info_format_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefColorType");
            auto _bv_info_format_of = env->GetStaticMethodID(_bv_info_format_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefColorType;");
            auto _bv_info_format = env->CallStaticObjectMethod(_bv_info_format_cls, _bv_info_format_of, static_cast<jlong>((info)->format));
            auto _bv_info_extra_coded_size_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefSize");
            auto _bv_info_extra_coded_size_ctor = env->GetMethodID(_bv_info_extra_coded_size_cls, "<init>", "(II)V");
            auto _bv_info_extra_coded_size = env->NewObject(_bv_info_extra_coded_size_cls, _bv_info_extra_coded_size_ctor, static_cast<jint>((info)->extra.coded_size.width), static_cast<jint>((info)->extra.coded_size.height));
            auto _bv_info_extra_visible_rect_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefRect");
            auto _bv_info_extra_visible_rect_ctor = env->GetMethodID(_bv_info_extra_visible_rect_cls, "<init>", "(IIII)V");
            auto _bv_info_extra_visible_rect = env->NewObject(_bv_info_extra_visible_rect_cls, _bv_info_extra_visible_rect_ctor, static_cast<jint>((info)->extra.visible_rect.x), static_cast<jint>((info)->extra.visible_rect.y), static_cast<jint>((info)->extra.visible_rect.width), static_cast<jint>((info)->extra.visible_rect.height));
            auto _bv_info_extra_content_rect_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefRect");
            auto _bv_info_extra_content_rect_ctor = env->GetMethodID(_bv_info_extra_content_rect_cls, "<init>", "(IIII)V");
            auto _bv_info_extra_content_rect = env->NewObject(_bv_info_extra_content_rect_cls, _bv_info_extra_content_rect_ctor, static_cast<jint>((info)->extra.content_rect.x), static_cast<jint>((info)->extra.content_rect.y), static_cast<jint>((info)->extra.content_rect.width), static_cast<jint>((info)->extra.content_rect.height));
            auto _bv_info_extra_source_size_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefSize");
            auto _bv_info_extra_source_size_ctor = env->GetMethodID(_bv_info_extra_source_size_cls, "<init>", "(II)V");
            auto _bv_info_extra_source_size = env->NewObject(_bv_info_extra_source_size_cls, _bv_info_extra_source_size_ctor, static_cast<jint>((info)->extra.source_size.width), static_cast<jint>((info)->extra.source_size.height));
            auto _bv_info_extra_capture_update_rect_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefRect");
            auto _bv_info_extra_capture_update_rect_ctor = env->GetMethodID(_bv_info_extra_capture_update_rect_cls, "<init>", "(IIII)V");
            auto _bv_info_extra_capture_update_rect = env->NewObject(_bv_info_extra_capture_update_rect_cls, _bv_info_extra_capture_update_rect_ctor, static_cast<jint>((info)->extra.capture_update_rect.x), static_cast<jint>((info)->extra.capture_update_rect.y), static_cast<jint>((info)->extra.capture_update_rect.width), static_cast<jint>((info)->extra.capture_update_rect.height));
            auto _bv_info_extra_region_capture_rect_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefRect");
            auto _bv_info_extra_region_capture_rect_ctor = env->GetMethodID(_bv_info_extra_region_capture_rect_cls, "<init>", "(IIII)V");
            auto _bv_info_extra_region_capture_rect = env->NewObject(_bv_info_extra_region_capture_rect_cls, _bv_info_extra_region_capture_rect_ctor, static_cast<jint>((info)->extra.region_capture_rect.x), static_cast<jint>((info)->extra.region_capture_rect.y), static_cast<jint>((info)->extra.region_capture_rect.width), static_cast<jint>((info)->extra.region_capture_rect.height));
            auto _bv_info_extra_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefAcceleratedPaintInfoCommon");
            auto _bv_info_extra_ctor = env->GetMethodID(_bv_info_extra_cls, "<init>", "(JLnet/kurobako/cef4j/gen/CefSize;Lnet/kurobako/cef4j/gen/CefRect;Lnet/kurobako/cef4j/gen/CefRect;Lnet/kurobako/cef4j/gen/CefSize;Lnet/kurobako/cef4j/gen/CefRect;Lnet/kurobako/cef4j/gen/CefRect;JIIII)V");
            auto _bv_info_extra = env->NewObject(_bv_info_extra_cls, _bv_info_extra_ctor,
        to_jlong((info)->extra.timestamp),
        _bv_info_extra_coded_size,
        _bv_info_extra_visible_rect,
        _bv_info_extra_content_rect,
        _bv_info_extra_source_size,
        _bv_info_extra_capture_update_rect,
        _bv_info_extra_region_capture_rect,
        to_jlong((info)->extra.capture_counter),
        static_cast<jint>((info)->extra.has_capture_update_rect),
        static_cast<jint>((info)->extra.has_region_capture_rect),
        static_cast<jint>((info)->extra.has_source_size),
        static_cast<jint>((info)->extra.has_capture_counter));
            auto j_info_cls = FindClassCached(env, "net/kurobako/cef4j/gen/linux/CefAcceleratedPaintInfo");
            auto j_info_ctor = env->GetMethodID(j_info_cls, "<init>", "(IJLnet/kurobako/cef4j/gen/CefColorType;Lnet/kurobako/cef4j/gen/CefAcceleratedPaintInfoCommon;)V");
            j_info = env->NewObject(j_info_cls, j_info_ctor, static_cast<jint>((info)->plane_count), to_jlong((info)->modifier), _bv_info_format, _bv_info_extra);
            if (j_info) env->SetLongField(j_info, env->GetFieldID(j_info_cls, "size", "J"), static_cast<jlong>(info->size));
        }
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onAcceleratedPaint", "(Lnet/kurobako/cef4j/gen/CefBrowser;Lnet/kurobako/cef4j/gen/CefPaintElementType;J[Lnet/kurobako/cef4j/gen/CefRect;Lnet/kurobako/cef4j/gen/CefAcceleratedPaintInfo;)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, j_browser, j_type, static_cast<jlong>(dirtyRectsCount), j_dirtyRects, j_info);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        env->PopLocalFrame(nullptr);
    }

    static void CEF_CALLBACK _get_touch_handle_size(cef_render_handler_t* self, struct _cef_browser_t* browser, cef_horizontal_alignment_t orientation, cef_size_t* size) {
        auto* h = reinterpret_cast<JniCefRenderHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(14) < 0) { return; }
        cef_browser_t* _p_browser = browser;
        if (_p_browser) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b); }
        auto j_browser_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        auto j_orientation_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefHorizontalAlignment");
        auto j_orientation_from = env->GetStaticMethodID(j_orientation_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefHorizontalAlignment;");
        auto j_orientation = env->CallStaticObjectMethod(j_orientation_cls, j_orientation_from, static_cast<jlong>(orientation));
        auto j_size_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefSize$Mutable");
        auto j_size_ctor = env->GetMethodID(j_size_cls, "<init>", "(II)V");
        auto j_size = size ? env->NewObject(j_size_cls, j_size_ctor, static_cast<jint>((size)->width), static_cast<jint>((size)->height)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "getTouchHandleSize", "(Lnet/kurobako/cef4j/gen/CefBrowser;Lnet/kurobako/cef4j/gen/CefHorizontalAlignment;Lnet/kurobako/cef4j/gen/CefSize$Mutable;)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, j_browser, j_orientation, j_size);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        if (size && j_size) {
            (size)->width = static_cast<decltype((size)->width)>(env->GetIntField(j_size, env->GetFieldID(j_size_cls, "width", "I")));
            (size)->height = static_cast<decltype((size)->height)>(env->GetIntField(j_size, env->GetFieldID(j_size_cls, "height", "I")));
        }
        env->PopLocalFrame(nullptr);
    }

    static void CEF_CALLBACK _on_touch_handle_state_changed(cef_render_handler_t* self, struct _cef_browser_t* browser, const cef_touch_handle_state_t* state) {
        auto* h = reinterpret_cast<JniCefRenderHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(17) < 0) { return; }
        cef_browser_t* _p_browser = browser;
        if (_p_browser) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b); }
        auto j_browser_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        auto _bv_state_orientation_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefHorizontalAlignment");
        auto _bv_state_orientation_of = env->GetStaticMethodID(_bv_state_orientation_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefHorizontalAlignment;");
        auto _bv_state_orientation = env->CallStaticObjectMethod(_bv_state_orientation_cls, _bv_state_orientation_of, static_cast<jlong>((state)->orientation));
        auto _bv_state_origin_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefPoint");
        auto _bv_state_origin_ctor = env->GetMethodID(_bv_state_origin_cls, "<init>", "(II)V");
        auto _bv_state_origin = env->NewObject(_bv_state_origin_cls, _bv_state_origin_ctor, static_cast<jint>((state)->origin.x), static_cast<jint>((state)->origin.y));
        auto j_state_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefTouchHandleState");
        auto j_state_ctor = env->GetMethodID(j_state_cls, "<init>", "(IIILnet/kurobako/cef4j/gen/CefHorizontalAlignment;IILnet/kurobako/cef4j/gen/CefPoint;F)V");
        auto j_state = state
    ? env->NewObject(j_state_cls, j_state_ctor,
        static_cast<jint>((state)->touch_handle_id),
        static_cast<jint>((state)->flags),
        static_cast<jint>((state)->enabled),
        _bv_state_orientation,
        static_cast<jint>((state)->mirror_vertical),
        static_cast<jint>((state)->mirror_horizontal),
        _bv_state_origin,
        static_cast<jfloat>((state)->alpha))
    : nullptr;
        if (j_state) env->SetLongField(j_state, env->GetFieldID(j_state_cls, "size", "J"), static_cast<jlong>(state->size));
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onTouchHandleStateChanged", "(Lnet/kurobako/cef4j/gen/CefBrowser;Lnet/kurobako/cef4j/gen/CefTouchHandleState;)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, j_browser, j_state);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        env->PopLocalFrame(nullptr);
    }

    static int CEF_CALLBACK _start_dragging(cef_render_handler_t* self, struct _cef_browser_t* browser, struct _cef_drag_data_t* drag_data, cef_drag_operations_mask_t allowed_ops, int x, int y) {
        auto* h = reinterpret_cast<JniCefRenderHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(14) < 0) { return false; }
        cef_browser_t* _p_browser = browser;
        if (_p_browser) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b); }
        auto j_browser_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        cef_drag_data_t* _p_drag_data = drag_data;
        if (_p_drag_data) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_drag_data); _b->add_ref(_b); }
        auto j_drag_data_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefDragData$NativePeer");
        auto j_drag_data_ctor = env->GetMethodID(j_drag_data_cls, "<init>", "(J)V");
        auto j_drag_data = _p_drag_data ? env->NewObject(j_drag_data_cls, j_drag_data_ctor, reinterpret_cast<jlong>(_p_drag_data)) : nullptr;
        auto j_allowed_ops_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefDragOperationsMask");
        auto j_allowed_ops_from = env->GetStaticMethodID(j_allowed_ops_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefDragOperationsMask;");
        auto j_allowed_ops = env->CallStaticObjectMethod(j_allowed_ops_cls, j_allowed_ops_from, static_cast<jlong>(allowed_ops));
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "startDragging", "(Lnet/kurobako/cef4j/gen/CefBrowser;Lnet/kurobako/cef4j/gen/CefDragData;Lnet/kurobako/cef4j/gen/CefDragOperationsMask;II)Z");
        if (!mid) { env->PopLocalFrame(nullptr); return false; }
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, j_browser, j_drag_data, j_allowed_ops, static_cast<jint>(x), static_cast<jint>(y));
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return false; }
        env->PopLocalFrame(nullptr);
        return jResult;
    }

    static void CEF_CALLBACK _update_drag_cursor(cef_render_handler_t* self, struct _cef_browser_t* browser, cef_drag_operations_mask_t operation) {
        auto* h = reinterpret_cast<JniCefRenderHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(11) < 0) { return; }
        cef_browser_t* _p_browser = browser;
        if (_p_browser) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b); }
        auto j_browser_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        auto j_operation_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefDragOperationsMask");
        auto j_operation_from = env->GetStaticMethodID(j_operation_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefDragOperationsMask;");
        auto j_operation = env->CallStaticObjectMethod(j_operation_cls, j_operation_from, static_cast<jlong>(operation));
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "updateDragCursor", "(Lnet/kurobako/cef4j/gen/CefBrowser;Lnet/kurobako/cef4j/gen/CefDragOperationsMask;)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, j_browser, j_operation);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        env->PopLocalFrame(nullptr);
    }

    static void CEF_CALLBACK _on_scroll_offset_changed(cef_render_handler_t* self, struct _cef_browser_t* browser, double x, double y) {
        auto* h = reinterpret_cast<JniCefRenderHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) { return; }
        cef_browser_t* _p_browser = browser;
        if (_p_browser) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b); }
        auto j_browser_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onScrollOffsetChanged", "(Lnet/kurobako/cef4j/gen/CefBrowser;DD)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, j_browser, static_cast<jdouble>(x), static_cast<jdouble>(y));
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        env->PopLocalFrame(nullptr);
    }

    static void CEF_CALLBACK _on_ime_composition_range_changed(cef_render_handler_t* self, struct _cef_browser_t* browser, const cef_range_t* selected_range, size_t character_boundsCount, cef_rect_t const* character_bounds) {
        auto* h = reinterpret_cast<JniCefRenderHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(22) < 0) { return; }
        cef_browser_t* _p_browser = browser;
        if (_p_browser) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b); }
        auto j_browser_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        auto j_selected_range_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefRange");
        auto j_selected_range_ctor = env->GetMethodID(j_selected_range_cls, "<init>", "(II)V");
        auto j_selected_range = selected_range ? env->NewObject(j_selected_range_cls, j_selected_range_ctor, static_cast<jint>((selected_range)->from), static_cast<jint>((selected_range)->to)) : nullptr;
        auto j_character_bounds_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefRect");
        auto j_character_bounds_ctor = env->GetMethodID(j_character_bounds_cls, "<init>", "(IIII)V");
        jint j_character_bounds_len = static_cast<jint>(character_boundsCount);
        auto j_character_bounds = env->NewObjectArray(j_character_bounds_len, j_character_bounds_cls, nullptr);
        for (jint _i = 0; _i < j_character_bounds_len; _i++) {
            auto _elem = env->NewObject(j_character_bounds_cls, j_character_bounds_ctor, static_cast<jint>(((&character_bounds[_i]))->x), static_cast<jint>(((&character_bounds[_i]))->y), static_cast<jint>(((&character_bounds[_i]))->width), static_cast<jint>(((&character_bounds[_i]))->height));
            env->SetObjectArrayElement(j_character_bounds, _i, _elem);
            env->DeleteLocalRef(_elem);
        }
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onImeCompositionRangeChanged", "(Lnet/kurobako/cef4j/gen/CefBrowser;Lnet/kurobako/cef4j/gen/CefRange;J[Lnet/kurobako/cef4j/gen/CefRect;)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, j_browser, j_selected_range, static_cast<jlong>(character_boundsCount), j_character_bounds);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        env->PopLocalFrame(nullptr);
    }

    static void CEF_CALLBACK _on_text_selection_changed(cef_render_handler_t* self, struct _cef_browser_t* browser, const cef_string_t* selected_text, const cef_range_t* selected_range) {
        auto* h = reinterpret_cast<JniCefRenderHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(12) < 0) { return; }
        cef_browser_t* _p_browser = browser;
        if (_p_browser) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b); }
        auto j_browser_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        auto j_selected_text = CefStringToJString(env, selected_text);
        auto j_selected_range_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefRange");
        auto j_selected_range_ctor = env->GetMethodID(j_selected_range_cls, "<init>", "(II)V");
        auto j_selected_range = selected_range ? env->NewObject(j_selected_range_cls, j_selected_range_ctor, static_cast<jint>((selected_range)->from), static_cast<jint>((selected_range)->to)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onTextSelectionChanged", "(Lnet/kurobako/cef4j/gen/CefBrowser;Ljava/lang/String;Lnet/kurobako/cef4j/gen/CefRange;)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, j_browser, j_selected_text, j_selected_range);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        env->PopLocalFrame(nullptr);
    }

    static void CEF_CALLBACK _on_virtual_keyboard_requested(cef_render_handler_t* self, struct _cef_browser_t* browser, cef_text_input_mode_t input_mode) {
        auto* h = reinterpret_cast<JniCefRenderHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(11) < 0) { return; }
        cef_browser_t* _p_browser = browser;
        if (_p_browser) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b); }
        auto j_browser_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        auto j_input_mode_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefTextInputMode");
        auto j_input_mode_from = env->GetStaticMethodID(j_input_mode_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefTextInputMode;");
        auto j_input_mode = env->CallStaticObjectMethod(j_input_mode_cls, j_input_mode_from, static_cast<jlong>(input_mode));
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onVirtualKeyboardRequested", "(Lnet/kurobako/cef4j/gen/CefBrowser;Lnet/kurobako/cef4j/gen/CefTextInputMode;)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, j_browser, j_input_mode);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        env->PopLocalFrame(nullptr);
    }
};

extern "C" cef_render_handler_t* Create_JniCefRenderHandler(JNIEnv* env, jobject handler) {
    JavaVM* jvm;
    env->GetJavaVM(&jvm);
    auto globalRef = env->NewGlobalRef(handler);
    return reinterpret_cast<cef_render_handler_t*>(new JniCefRenderHandler(jvm, globalRef));
}
