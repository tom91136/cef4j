// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
#include <jni.h>
#include "include/capi/views/cef_view_capi.h"
#include "include/capi/views/cef_browser_view_capi.h"
#include "include/capi/views/cef_button_capi.h"
#include "include/capi/views/cef_panel_capi.h"
#include "include/capi/views/cef_scroll_view_capi.h"
#include "include/capi/views/cef_textfield_capi.h"
#include "include/capi/views/cef_view_delegate_capi.h"
#include "include/capi/views/cef_window_capi.h"
#include "jni_util.h"

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefView), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(views_CefView), asBrowserView0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_view_t*>(self);
    if (!s) return nullptr;
    auto _r = s->as_browser_view(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/views/CefBrowserView$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(views_CefView), asButton0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_view_t*>(self);
    if (!s) return nullptr;
    auto _r = s->as_button(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/views/CefButton$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(views_CefView), asPanel0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_view_t*>(self);
    if (!s) return nullptr;
    auto _r = s->as_panel(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/views/CefPanel$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(views_CefView), asScrollView0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_view_t*>(self);
    if (!s) return nullptr;
    auto _r = s->as_scroll_view(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/views/CefScrollView$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(views_CefView), asTextfield0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_view_t*>(self);
    if (!s) return nullptr;
    auto _r = s->as_textfield(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/views/CefTextfield$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(views_CefView), getTypeString0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_view_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_type_string(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(views_CefView), cefToString0)(JNIEnv* env, jobject obj, jlong self, jboolean include_children) {
    auto* s = reinterpret_cast<cef_view_t*>(self);
    if (!s) return nullptr;
    auto result = s->to_string(s, static_cast<bool>(include_children));
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(views_CefView), isValid0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_view_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_valid(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(views_CefView), isAttached0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_view_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_attached(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(views_CefView), isSame0)(JNIEnv* env, jobject obj, jlong self, jobject that) {
    auto* s = reinterpret_cast<cef_view_t*>(self);
    if (!s) return JNI_FALSE;
    cef_view_t* _that_ptr = that ? reinterpret_cast<cef_view_t*>(env->GetLongField(that, env->GetFieldID(env->GetObjectClass(that), "nativePtr", "J"))) : nullptr;
    if (_that_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_that_ptr); _b->add_ref(_b);}
    auto _r = s->is_same(s, _that_ptr);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(views_CefView), getDelegate0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_view_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_delegate(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/views/CefViewDelegate$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(views_CefView), getWindow0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_view_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_window(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/views/CefWindow$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jint, CEF4J_PEER(views_CefView), getId0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_view_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->get_id(s));
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefView), setId0)(JNIEnv* env, jobject obj, jlong self, jint id) {
    auto* s = reinterpret_cast<cef_view_t*>(self);
    if (!s) return;
    s->set_id(s, id);
}

CEF4J_JNI_EXPORT(jint, CEF4J_PEER(views_CefView), getGroupId0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_view_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->get_group_id(s));
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefView), setGroupId0)(JNIEnv* env, jobject obj, jlong self, jint group_id) {
    auto* s = reinterpret_cast<cef_view_t*>(self);
    if (!s) return;
    s->set_group_id(s, group_id);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(views_CefView), getParentView0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_view_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_parent_view(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/views/CefView$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(views_CefView), getViewForId0)(JNIEnv* env, jobject obj, jlong self, jint id) {
    auto* s = reinterpret_cast<cef_view_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_view_for_id(s, id);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/views/CefView$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefView), setBounds0)(JNIEnv* env, jobject obj, jlong self, jobject bounds) {
    auto* s = reinterpret_cast<cef_view_t*>(self);
    if (!s) return;
    if (!bounds) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "bounds must not be null"); return;}
    cef_rect_t _bounds_val = {};
    if (bounds) {
        auto _c = env->FindClass("net/kurobako/cef4j/gen/CefRect");
        _bounds_val.x = static_cast<decltype(_bounds_val.x)>(env->GetIntField(bounds, env->GetFieldID(_c, "x", "I")));
        _bounds_val.y = static_cast<decltype(_bounds_val.y)>(env->GetIntField(bounds, env->GetFieldID(_c, "y", "I")));
        _bounds_val.width = static_cast<decltype(_bounds_val.width)>(env->GetIntField(bounds, env->GetFieldID(_c, "width", "I")));
        _bounds_val.height = static_cast<decltype(_bounds_val.height)>(env->GetIntField(bounds, env->GetFieldID(_c, "height", "I")));
    }
    s->set_bounds(s, &_bounds_val);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(views_CefView), getBounds0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_view_t*>(self);
    if (!s) return nullptr;
    cef_rect_t result = s->get_bounds(s);
    auto cls = env->FindClass("net/kurobako/cef4j/gen/CefRect");
    auto ctor = env->GetMethodID(cls, "<init>", "(IIII)V");
    auto _dsResult = env->NewObject(cls, ctor, static_cast<jint>((&result)->x), static_cast<jint>((&result)->y), static_cast<jint>((&result)->width), static_cast<jint>((&result)->height));
    return _dsResult;
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(views_CefView), getBoundsInScreen0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_view_t*>(self);
    if (!s) return nullptr;
    cef_rect_t result = s->get_bounds_in_screen(s);
    auto cls = env->FindClass("net/kurobako/cef4j/gen/CefRect");
    auto ctor = env->GetMethodID(cls, "<init>", "(IIII)V");
    auto _dsResult = env->NewObject(cls, ctor, static_cast<jint>((&result)->x), static_cast<jint>((&result)->y), static_cast<jint>((&result)->width), static_cast<jint>((&result)->height));
    return _dsResult;
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefView), setSize0)(JNIEnv* env, jobject obj, jlong self, jobject size) {
    auto* s = reinterpret_cast<cef_view_t*>(self);
    if (!s) return;
    if (!size) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "size must not be null"); return;}
    cef_size_t _size_val = {};
    if (size) {
        auto _c = env->FindClass("net/kurobako/cef4j/gen/CefSize");
        _size_val.width = static_cast<decltype(_size_val.width)>(env->GetIntField(size, env->GetFieldID(_c, "width", "I")));
        _size_val.height = static_cast<decltype(_size_val.height)>(env->GetIntField(size, env->GetFieldID(_c, "height", "I")));
    }
    s->set_size(s, &_size_val);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(views_CefView), getSize0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_view_t*>(self);
    if (!s) return nullptr;
    cef_size_t result = s->get_size(s);
    auto cls = env->FindClass("net/kurobako/cef4j/gen/CefSize");
    auto ctor = env->GetMethodID(cls, "<init>", "(II)V");
    auto _dsResult = env->NewObject(cls, ctor, static_cast<jint>((&result)->width), static_cast<jint>((&result)->height));
    return _dsResult;
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefView), setPosition0)(JNIEnv* env, jobject obj, jlong self, jobject position) {
    auto* s = reinterpret_cast<cef_view_t*>(self);
    if (!s) return;
    if (!position) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "position must not be null"); return;}
    cef_point_t _position_val = {};
    if (position) {
        auto _c = env->FindClass("net/kurobako/cef4j/gen/CefPoint");
        _position_val.x = static_cast<decltype(_position_val.x)>(env->GetIntField(position, env->GetFieldID(_c, "x", "I")));
        _position_val.y = static_cast<decltype(_position_val.y)>(env->GetIntField(position, env->GetFieldID(_c, "y", "I")));
    }
    s->set_position(s, &_position_val);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(views_CefView), getPosition0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_view_t*>(self);
    if (!s) return nullptr;
    cef_point_t result = s->get_position(s);
    auto cls = env->FindClass("net/kurobako/cef4j/gen/CefPoint");
    auto ctor = env->GetMethodID(cls, "<init>", "(II)V");
    auto _dsResult = env->NewObject(cls, ctor, static_cast<jint>((&result)->x), static_cast<jint>((&result)->y));
    return _dsResult;
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefView), setInsets0)(JNIEnv* env, jobject obj, jlong self, jobject insets) {
    auto* s = reinterpret_cast<cef_view_t*>(self);
    if (!s) return;
    if (!insets) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "insets must not be null"); return;}
    cef_insets_t _insets_val = {};
    if (insets) {
        auto _c = env->FindClass("net/kurobako/cef4j/gen/CefInsets");
        _insets_val.top = static_cast<decltype(_insets_val.top)>(env->GetIntField(insets, env->GetFieldID(_c, "top", "I")));
        _insets_val.left = static_cast<decltype(_insets_val.left)>(env->GetIntField(insets, env->GetFieldID(_c, "left", "I")));
        _insets_val.bottom = static_cast<decltype(_insets_val.bottom)>(env->GetIntField(insets, env->GetFieldID(_c, "bottom", "I")));
        _insets_val.right = static_cast<decltype(_insets_val.right)>(env->GetIntField(insets, env->GetFieldID(_c, "right", "I")));
    }
    s->set_insets(s, &_insets_val);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(views_CefView), getInsets0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_view_t*>(self);
    if (!s) return nullptr;
    cef_insets_t result = s->get_insets(s);
    auto cls = env->FindClass("net/kurobako/cef4j/gen/CefInsets");
    auto ctor = env->GetMethodID(cls, "<init>", "(IIII)V");
    auto _dsResult = env->NewObject(cls, ctor, static_cast<jint>((&result)->top), static_cast<jint>((&result)->left), static_cast<jint>((&result)->bottom), static_cast<jint>((&result)->right));
    return _dsResult;
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(views_CefView), getPreferredSize0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_view_t*>(self);
    if (!s) return nullptr;
    cef_size_t result = s->get_preferred_size(s);
    auto cls = env->FindClass("net/kurobako/cef4j/gen/CefSize");
    auto ctor = env->GetMethodID(cls, "<init>", "(II)V");
    auto _dsResult = env->NewObject(cls, ctor, static_cast<jint>((&result)->width), static_cast<jint>((&result)->height));
    return _dsResult;
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefView), sizeToPreferredSize0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_view_t*>(self);
    if (!s) return;
    s->size_to_preferred_size(s);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(views_CefView), getMinimumSize0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_view_t*>(self);
    if (!s) return nullptr;
    cef_size_t result = s->get_minimum_size(s);
    auto cls = env->FindClass("net/kurobako/cef4j/gen/CefSize");
    auto ctor = env->GetMethodID(cls, "<init>", "(II)V");
    auto _dsResult = env->NewObject(cls, ctor, static_cast<jint>((&result)->width), static_cast<jint>((&result)->height));
    return _dsResult;
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(views_CefView), getMaximumSize0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_view_t*>(self);
    if (!s) return nullptr;
    cef_size_t result = s->get_maximum_size(s);
    auto cls = env->FindClass("net/kurobako/cef4j/gen/CefSize");
    auto ctor = env->GetMethodID(cls, "<init>", "(II)V");
    auto _dsResult = env->NewObject(cls, ctor, static_cast<jint>((&result)->width), static_cast<jint>((&result)->height));
    return _dsResult;
}

CEF4J_JNI_EXPORT(jint, CEF4J_PEER(views_CefView), getHeightForWidth0)(JNIEnv* env, jobject obj, jlong self, jint width) {
    auto* s = reinterpret_cast<cef_view_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->get_height_for_width(s, width));
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefView), invalidateLayout0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_view_t*>(self);
    if (!s) return;
    s->invalidate_layout(s);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefView), setVisible0)(JNIEnv* env, jobject obj, jlong self, jboolean visible) {
    auto* s = reinterpret_cast<cef_view_t*>(self);
    if (!s) return;
    s->set_visible(s, static_cast<bool>(visible));
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(views_CefView), isVisible0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_view_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_visible(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(views_CefView), isDrawn0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_view_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_drawn(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefView), setEnabled0)(JNIEnv* env, jobject obj, jlong self, jboolean enabled) {
    auto* s = reinterpret_cast<cef_view_t*>(self);
    if (!s) return;
    s->set_enabled(s, static_cast<bool>(enabled));
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(views_CefView), isEnabled0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_view_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_enabled(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefView), setFocusable0)(JNIEnv* env, jobject obj, jlong self, jboolean focusable) {
    auto* s = reinterpret_cast<cef_view_t*>(self);
    if (!s) return;
    s->set_focusable(s, static_cast<bool>(focusable));
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(views_CefView), isFocusable0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_view_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_focusable(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(views_CefView), isAccessibilityFocusable0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_view_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_accessibility_focusable(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(views_CefView), hasFocus0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_view_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->has_focus(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefView), requestFocus0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_view_t*>(self);
    if (!s) return;
    s->request_focus(s);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefView), setBackgroundColor0)(JNIEnv* env, jobject obj, jlong self, jint color) {
    auto* s = reinterpret_cast<cef_view_t*>(self);
    if (!s) return;
    s->set_background_color(s, color);
}

CEF4J_JNI_EXPORT(jint, CEF4J_PEER(views_CefView), getBackgroundColor0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_view_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->get_background_color(s));
}

CEF4J_JNI_EXPORT(jint, CEF4J_PEER(views_CefView), getThemeColor0)(JNIEnv* env, jobject obj, jlong self, jint color_id) {
    auto* s = reinterpret_cast<cef_view_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->get_theme_color(s, color_id));
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(views_CefView), convertPointToScreen0)(JNIEnv* env, jobject obj, jlong self, jobject point) {
    auto* s = reinterpret_cast<cef_view_t*>(self);
    if (!s) return JNI_FALSE;
    if (!point) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "point must not be null"); return JNI_FALSE;}
    cef_point_t _point_val = {};
    if (point) {
        auto _c = env->FindClass("net/kurobako/cef4j/gen/CefPoint$Mutable");
        _point_val.x = static_cast<decltype(_point_val.x)>(env->GetIntField(point, env->GetFieldID(_c, "x", "I")));
        _point_val.y = static_cast<decltype(_point_val.y)>(env->GetIntField(point, env->GetFieldID(_c, "y", "I")));
    }
    auto _r = s->convert_point_to_screen(s, &_point_val);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(views_CefView), convertPointFromScreen0)(JNIEnv* env, jobject obj, jlong self, jobject point) {
    auto* s = reinterpret_cast<cef_view_t*>(self);
    if (!s) return JNI_FALSE;
    if (!point) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "point must not be null"); return JNI_FALSE;}
    cef_point_t _point_val = {};
    if (point) {
        auto _c = env->FindClass("net/kurobako/cef4j/gen/CefPoint$Mutable");
        _point_val.x = static_cast<decltype(_point_val.x)>(env->GetIntField(point, env->GetFieldID(_c, "x", "I")));
        _point_val.y = static_cast<decltype(_point_val.y)>(env->GetIntField(point, env->GetFieldID(_c, "y", "I")));
    }
    auto _r = s->convert_point_from_screen(s, &_point_val);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(views_CefView), convertPointToWindow0)(JNIEnv* env, jobject obj, jlong self, jobject point) {
    auto* s = reinterpret_cast<cef_view_t*>(self);
    if (!s) return JNI_FALSE;
    if (!point) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "point must not be null"); return JNI_FALSE;}
    cef_point_t _point_val = {};
    if (point) {
        auto _c = env->FindClass("net/kurobako/cef4j/gen/CefPoint$Mutable");
        _point_val.x = static_cast<decltype(_point_val.x)>(env->GetIntField(point, env->GetFieldID(_c, "x", "I")));
        _point_val.y = static_cast<decltype(_point_val.y)>(env->GetIntField(point, env->GetFieldID(_c, "y", "I")));
    }
    auto _r = s->convert_point_to_window(s, &_point_val);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(views_CefView), convertPointFromWindow0)(JNIEnv* env, jobject obj, jlong self, jobject point) {
    auto* s = reinterpret_cast<cef_view_t*>(self);
    if (!s) return JNI_FALSE;
    if (!point) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "point must not be null"); return JNI_FALSE;}
    cef_point_t _point_val = {};
    if (point) {
        auto _c = env->FindClass("net/kurobako/cef4j/gen/CefPoint$Mutable");
        _point_val.x = static_cast<decltype(_point_val.x)>(env->GetIntField(point, env->GetFieldID(_c, "x", "I")));
        _point_val.y = static_cast<decltype(_point_val.y)>(env->GetIntField(point, env->GetFieldID(_c, "y", "I")));
    }
    auto _r = s->convert_point_from_window(s, &_point_val);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(views_CefView), convertPointToView0)(JNIEnv* env, jobject obj, jlong self, jobject view, jobject point) {
    auto* s = reinterpret_cast<cef_view_t*>(self);
    if (!s) return JNI_FALSE;
    if (!point) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "point must not be null"); return JNI_FALSE;}
    cef_view_t* _view_ptr = view ? reinterpret_cast<cef_view_t*>(env->GetLongField(view, env->GetFieldID(env->GetObjectClass(view), "nativePtr", "J"))) : nullptr;
    if (_view_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_view_ptr); _b->add_ref(_b);}
    cef_point_t _point_val = {};
    if (point) {
        auto _c = env->FindClass("net/kurobako/cef4j/gen/CefPoint$Mutable");
        _point_val.x = static_cast<decltype(_point_val.x)>(env->GetIntField(point, env->GetFieldID(_c, "x", "I")));
        _point_val.y = static_cast<decltype(_point_val.y)>(env->GetIntField(point, env->GetFieldID(_c, "y", "I")));
    }
    auto _r = s->convert_point_to_view(s, _view_ptr, &_point_val);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(views_CefView), convertPointFromView0)(JNIEnv* env, jobject obj, jlong self, jobject view, jobject point) {
    auto* s = reinterpret_cast<cef_view_t*>(self);
    if (!s) return JNI_FALSE;
    if (!point) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "point must not be null"); return JNI_FALSE;}
    cef_view_t* _view_ptr = view ? reinterpret_cast<cef_view_t*>(env->GetLongField(view, env->GetFieldID(env->GetObjectClass(view), "nativePtr", "J"))) : nullptr;
    if (_view_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_view_ptr); _b->add_ref(_b);}
    cef_point_t _point_val = {};
    if (point) {
        auto _c = env->FindClass("net/kurobako/cef4j/gen/CefPoint$Mutable");
        _point_val.x = static_cast<decltype(_point_val.x)>(env->GetIntField(point, env->GetFieldID(_c, "x", "I")));
        _point_val.y = static_cast<decltype(_point_val.y)>(env->GetIntField(point, env->GetFieldID(_c, "y", "I")));
    }
    auto _r = s->convert_point_from_view(s, _view_ptr, &_point_val);
    return static_cast<jboolean>(_r);
}
