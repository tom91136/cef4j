// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
#include <jni.h>
#include "include/capi/views/cef_window_capi.h"
#include "include/capi/cef_image_capi.h"
#include "include/capi/cef_menu_model_capi.h"
#include "include/capi/views/cef_browser_view_capi.h"
#include "include/capi/views/cef_display_capi.h"
#include "include/capi/views/cef_overlay_controller_capi.h"
#include "include/capi/views/cef_view_capi.h"
#include "include/capi/views/cef_window_delegate_capi.h"
#include "jni_util.h"

extern "C" cef_window_delegate_t* Create_JniCefWindowDelegate(JNIEnv* env, jobject handler);
CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefWindow), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefWindow), show0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_window_t*>(self);
    if (!s) return;
    s->show(s);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefWindow), showAsBrowserModalDialog0)(JNIEnv* env, jobject obj, jlong self, jobject browser_view) {
    auto* s = reinterpret_cast<cef_window_t*>(self);
    if (!s) return;
    cef_browser_view_t* _browser_view_ptr = browser_view ? reinterpret_cast<cef_browser_view_t*>(env->GetLongField(browser_view, env->GetFieldID(env->GetObjectClass(browser_view), "nativePtr", "J"))) : nullptr;
    if (_browser_view_ptr) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_browser_view_ptr); _b->add_ref(_b); }
    s->show_as_browser_modal_dialog(s, _browser_view_ptr);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefWindow), hide0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_window_t*>(self);
    if (!s) return;
    s->hide(s);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefWindow), centerWindow0)(JNIEnv* env, jobject obj, jlong self, jobject size) {
    auto* s = reinterpret_cast<cef_window_t*>(self);
    if (!s) return;
    if (!size) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "size must not be null"); return; }
    cef_size_t _size_val = {};
    auto _size_c = FindClassCached(env, "net/kurobako/cef4j/gen/CefSize");
    _size_val.width = static_cast<decltype(_size_val.width)>(env->GetIntField(size, env->GetFieldID(_size_c, "width", "I")));
    _size_val.height = static_cast<decltype(_size_val.height)>(env->GetIntField(size, env->GetFieldID(_size_c, "height", "I")));
    s->center_window(s, &_size_val);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefWindow), cefClose0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_window_t*>(self);
    if (!s) return;
    s->close(s);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(views_CefWindow), isClosed0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_window_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_closed(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefWindow), activate0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_window_t*>(self);
    if (!s) return;
    s->activate(s);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefWindow), deactivate0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_window_t*>(self);
    if (!s) return;
    s->deactivate(s);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(views_CefWindow), isActive0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_window_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_active(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefWindow), bringToTop0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_window_t*>(self);
    if (!s) return;
    s->bring_to_top(s);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefWindow), setAlwaysOnTop0)(JNIEnv* env, jobject obj, jlong self, jboolean on_top) {
    auto* s = reinterpret_cast<cef_window_t*>(self);
    if (!s) return;
    s->set_always_on_top(s, static_cast<bool>(on_top));
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(views_CefWindow), isAlwaysOnTop0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_window_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_always_on_top(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefWindow), maximize0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_window_t*>(self);
    if (!s) return;
    s->maximize(s);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefWindow), minimize0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_window_t*>(self);
    if (!s) return;
    s->minimize(s);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefWindow), restore0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_window_t*>(self);
    if (!s) return;
    s->restore(s);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefWindow), setFullscreen0)(JNIEnv* env, jobject obj, jlong self, jboolean fullscreen) {
    auto* s = reinterpret_cast<cef_window_t*>(self);
    if (!s) return;
    s->set_fullscreen(s, static_cast<bool>(fullscreen));
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(views_CefWindow), isMaximized0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_window_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_maximized(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(views_CefWindow), isMinimized0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_window_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_minimized(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(views_CefWindow), isFullscreen0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_window_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_fullscreen(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(views_CefWindow), getFocusedView0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_window_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_focused_view(s);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/views/CefView$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefWindow), setTitle0)(JNIEnv* env, jobject obj, jlong self, jstring title) {
    auto* s = reinterpret_cast<cef_window_t*>(self);
    if (!s) return;
    auto _title_str = title ? JStringToCefString(env, title) : nullptr;
    s->set_title(s, _title_str);
    if (_title_str) cef_string_userfree_free(_title_str);
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(views_CefWindow), getTitle0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_window_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_title(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefWindow), setWindowIcon0)(JNIEnv* env, jobject obj, jlong self, jobject image) {
    auto* s = reinterpret_cast<cef_window_t*>(self);
    if (!s) return;
    cef_image_t* _image_ptr = image ? reinterpret_cast<cef_image_t*>(env->GetLongField(image, env->GetFieldID(env->GetObjectClass(image), "nativePtr", "J"))) : nullptr;
    if (_image_ptr) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_image_ptr); _b->add_ref(_b); }
    s->set_window_icon(s, _image_ptr);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(views_CefWindow), getWindowIcon0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_window_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_window_icon(s);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefImage$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefWindow), setWindowAppIcon0)(JNIEnv* env, jobject obj, jlong self, jobject image) {
    auto* s = reinterpret_cast<cef_window_t*>(self);
    if (!s) return;
    cef_image_t* _image_ptr = image ? reinterpret_cast<cef_image_t*>(env->GetLongField(image, env->GetFieldID(env->GetObjectClass(image), "nativePtr", "J"))) : nullptr;
    if (_image_ptr) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_image_ptr); _b->add_ref(_b); }
    s->set_window_app_icon(s, _image_ptr);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(views_CefWindow), getWindowAppIcon0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_window_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_window_app_icon(s);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefImage$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(views_CefWindow), addOverlayView0)(JNIEnv* env, jobject obj, jlong self, jobject view, jobject docking_mode, jboolean can_activate) {
    auto* s = reinterpret_cast<cef_window_t*>(self);
    if (!s) return nullptr;
    if (!docking_mode) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "dockingMode must not be null"); return nullptr; }
    cef_view_t* _view_ptr = view ? reinterpret_cast<cef_view_t*>(env->GetLongField(view, env->GetFieldID(env->GetObjectClass(view), "nativePtr", "J"))) : nullptr;
    if (_view_ptr) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_view_ptr); _b->add_ref(_b); }
    auto _r = s->add_overlay_view(s, _view_ptr, static_cast<cef_docking_mode_t>(env->GetLongField(docking_mode, env->GetFieldID(env->GetObjectClass(docking_mode), "value", "J"))), static_cast<bool>(can_activate));
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/views/CefOverlayController$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefWindow), showMenu0)(JNIEnv* env, jobject obj, jlong self, jobject menu_model, jobject screen_point, jobject anchor_position) {
    auto* s = reinterpret_cast<cef_window_t*>(self);
    if (!s) return;
    if (!screen_point) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "screenPoint must not be null"); return; }
    if (!anchor_position) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "anchorPosition must not be null"); return; }
    cef_menu_model_t* _menu_model_ptr = menu_model ? reinterpret_cast<cef_menu_model_t*>(env->GetLongField(menu_model, env->GetFieldID(env->GetObjectClass(menu_model), "nativePtr", "J"))) : nullptr;
    if (_menu_model_ptr) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_menu_model_ptr); _b->add_ref(_b); }
    cef_point_t _screen_point_val = {};
    auto _screen_point_c = FindClassCached(env, "net/kurobako/cef4j/gen/CefPoint");
    _screen_point_val.x = static_cast<decltype(_screen_point_val.x)>(env->GetIntField(screen_point, env->GetFieldID(_screen_point_c, "x", "I")));
    _screen_point_val.y = static_cast<decltype(_screen_point_val.y)>(env->GetIntField(screen_point, env->GetFieldID(_screen_point_c, "y", "I")));
    s->show_menu(s, _menu_model_ptr, &_screen_point_val, static_cast<cef_menu_anchor_position_t>(env->GetLongField(anchor_position, env->GetFieldID(env->GetObjectClass(anchor_position), "value", "J"))));
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefWindow), cancelMenu0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_window_t*>(self);
    if (!s) return;
    s->cancel_menu(s);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(views_CefWindow), getDisplay0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_window_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_display(s);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/views/CefDisplay$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(views_CefWindow), getClientAreaBoundsInScreen0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_window_t*>(self);
    if (!s) return nullptr;
    cef_rect_t result = s->get_client_area_bounds_in_screen(s);
    auto cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefRect");
    auto ctor = env->GetMethodID(cls, "<init>", "(IIII)V");
    auto _dsResult = env->NewObject(cls, ctor, static_cast<jint>(((&result))->x), static_cast<jint>(((&result))->y), static_cast<jint>(((&result))->width), static_cast<jint>(((&result))->height));
    return _dsResult;
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefWindow), setDraggableRegions0)(JNIEnv* env, jobject obj, jlong self, jlong regionsCount, jobjectArray regions) {
    auto* s = reinterpret_cast<cef_window_t*>(self);
    if (!s) return;
    size_t _regions_sz = static_cast<size_t>(regionsCount);
    cef_draggable_region_t* _regions_arr = _regions_sz > 0 ? new cef_draggable_region_t[_regions_sz]() : nullptr;
    { auto _bvac = FindClassCached(env, "net/kurobako/cef4j/gen/CefDraggableRegion");
    for (size_t _i = 0; _i < _regions_sz; _i++) {
        auto _elem = env->GetObjectArrayElement(regions, _i);
        if (_elem) {
            auto _rd_bounds = env->GetObjectField(_elem, env->GetFieldID(_bvac, "bounds", "Lnet/kurobako/cef4j/gen/CefRect;"));
            if (_rd_bounds) {
                auto _rd_boundsc = env->GetObjectClass(_rd_bounds);
                _regions_arr[_i].bounds.x = static_cast<decltype(_regions_arr[_i].bounds.x)>(env->GetIntField(_rd_bounds, env->GetFieldID(_rd_boundsc, "x", "I")));
                _regions_arr[_i].bounds.y = static_cast<decltype(_regions_arr[_i].bounds.y)>(env->GetIntField(_rd_bounds, env->GetFieldID(_rd_boundsc, "y", "I")));
                _regions_arr[_i].bounds.width = static_cast<decltype(_regions_arr[_i].bounds.width)>(env->GetIntField(_rd_bounds, env->GetFieldID(_rd_boundsc, "width", "I")));
                _regions_arr[_i].bounds.height = static_cast<decltype(_regions_arr[_i].bounds.height)>(env->GetIntField(_rd_bounds, env->GetFieldID(_rd_boundsc, "height", "I")));
            }
            _regions_arr[_i].draggable = static_cast<decltype(_regions_arr[_i].draggable)>(env->GetIntField(_elem, env->GetFieldID(_bvac, "draggable", "I")));
        }
    } }
    s->set_draggable_regions(s, regionsCount, _regions_arr);
    delete[] _regions_arr;
}

CEF4J_JNI_EXPORT(jlong, CEF4J_PEER(views_CefWindow), getWindowHandle0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_window_t*>(self);
    if (!s) return 0;
    return to_jlong(s->get_window_handle(s));
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefWindow), sendKeyPress0)(JNIEnv* env, jobject obj, jlong self, jint key_code, jint event_flags) {
    auto* s = reinterpret_cast<cef_window_t*>(self);
    if (!s) return;
    s->send_key_press(s, key_code, event_flags);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefWindow), sendMouseMove0)(JNIEnv* env, jobject obj, jlong self, jint screen_x, jint screen_y) {
    auto* s = reinterpret_cast<cef_window_t*>(self);
    if (!s) return;
    s->send_mouse_move(s, screen_x, screen_y);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefWindow), sendMouseEvents0)(JNIEnv* env, jobject obj, jlong self, jobject button, jboolean mouse_down, jboolean mouse_up) {
    auto* s = reinterpret_cast<cef_window_t*>(self);
    if (!s) return;
    if (!button) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "button must not be null"); return; }
    s->send_mouse_events(s, static_cast<cef_mouse_button_type_t>(env->GetLongField(button, env->GetFieldID(env->GetObjectClass(button), "value", "J"))), static_cast<bool>(mouse_down), static_cast<bool>(mouse_up));
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefWindow), setAccelerator0)(JNIEnv* env, jobject obj, jlong self, jint command_id, jint key_code, jboolean shift_pressed, jboolean ctrl_pressed, jboolean alt_pressed, jboolean high_priority) {
    auto* s = reinterpret_cast<cef_window_t*>(self);
    if (!s) return;
    s->set_accelerator(s, command_id, key_code, static_cast<bool>(shift_pressed), static_cast<bool>(ctrl_pressed), static_cast<bool>(alt_pressed), static_cast<bool>(high_priority));
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefWindow), removeAccelerator0)(JNIEnv* env, jobject obj, jlong self, jint command_id) {
    auto* s = reinterpret_cast<cef_window_t*>(self);
    if (!s) return;
    s->remove_accelerator(s, command_id);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefWindow), removeAllAccelerators0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_window_t*>(self);
    if (!s) return;
    s->remove_all_accelerators(s);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefWindow), setThemeColor0)(JNIEnv* env, jobject obj, jlong self, jint color_id, jint color) {
    auto* s = reinterpret_cast<cef_window_t*>(self);
    if (!s) return;
    s->set_theme_color(s, color_id, color);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefWindow), themeChanged0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_window_t*>(self);
    if (!s) return;
    s->theme_changed(s);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(views_CefWindow), getRuntimeStyle0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_window_t*>(self);
    if (!s) return 0;
    auto _r = s->get_runtime_style(s);
    auto _eCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefRuntimeStyle");
    auto _eOf = env->GetStaticMethodID(_eCls, "of", "(J)Lnet/kurobako/cef4j/gen/CefRuntimeStyle;");
    return env->CallStaticObjectMethod(_eCls, _eOf, static_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(views_CefWindow), createTopLevel0)(JNIEnv* env, jclass clz, jobject delegate) {
    cef_window_delegate_t* _delegate_ptr = delegate ? Create_JniCefWindowDelegate(env, delegate) : nullptr;
    auto _r = cef_window_create_top_level(_delegate_ptr);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/views/CefWindow$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}
