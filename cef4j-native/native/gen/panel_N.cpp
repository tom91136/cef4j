// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
#include <jni.h>
#include "include/capi/views/cef_panel_capi.h"
#include "include/capi/views/cef_box_layout_capi.h"
#include "include/capi/views/cef_fill_layout_capi.h"
#include "include/capi/views/cef_layout_capi.h"
#include "include/capi/views/cef_panel_delegate_capi.h"
#include "include/capi/views/cef_view_capi.h"
#include "include/capi/views/cef_window_capi.h"
#include "jni_util.h"

extern "C" cef_panel_delegate_t* Create_JniCefPanelDelegate(JNIEnv* env, jobject handler);
CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefPanel), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(views_CefPanel), asWindow0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_panel_t*>(self);
    if (!s) return nullptr;
    auto _r = s->as_window(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/views/CefWindow$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(views_CefPanel), setToFillLayout0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_panel_t*>(self);
    if (!s) return nullptr;
    auto _r = s->set_to_fill_layout(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/views/CefFillLayout$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(views_CefPanel), setToBoxLayout0)(JNIEnv* env, jobject obj, jlong self, jobject settings) {
    auto* s = reinterpret_cast<cef_panel_t*>(self);
    if (!s) return nullptr;
    if (!settings) { env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "settings must not be null"); return nullptr; }
    cef_box_layout_settings_t _settings_val = {};
    if (settings) {
        auto _c = env->FindClass("net/kurobako/cef4j/gen/CefBoxLayoutSettings");
        _settings_val.horizontal = static_cast<decltype(_settings_val.horizontal)>(env->GetIntField(settings, env->GetFieldID(_c, "horizontal", "I")));
        _settings_val.inside_border_horizontal_spacing = static_cast<decltype(_settings_val.inside_border_horizontal_spacing)>(env->GetIntField(settings, env->GetFieldID(_c, "insideBorderHorizontalSpacing", "I")));
        _settings_val.inside_border_vertical_spacing = static_cast<decltype(_settings_val.inside_border_vertical_spacing)>(env->GetIntField(settings, env->GetFieldID(_c, "insideBorderVerticalSpacing", "I")));
        auto _rd_inside_border_insets = env->GetObjectField(settings, env->GetFieldID(_c, "insideBorderInsets", "Lnet/kurobako/cef4j/gen/CefInsets;"));
        if (_rd_inside_border_insets) {
            auto _rd_inside_border_insetsc = env->GetObjectClass(_rd_inside_border_insets);
            _settings_val.inside_border_insets.top = static_cast<decltype(_settings_val.inside_border_insets.top)>(env->GetIntField(_rd_inside_border_insets, env->GetFieldID(_rd_inside_border_insetsc, "top", "I")));
            _settings_val.inside_border_insets.left = static_cast<decltype(_settings_val.inside_border_insets.left)>(env->GetIntField(_rd_inside_border_insets, env->GetFieldID(_rd_inside_border_insetsc, "left", "I")));
            _settings_val.inside_border_insets.bottom = static_cast<decltype(_settings_val.inside_border_insets.bottom)>(env->GetIntField(_rd_inside_border_insets, env->GetFieldID(_rd_inside_border_insetsc, "bottom", "I")));
            _settings_val.inside_border_insets.right = static_cast<decltype(_settings_val.inside_border_insets.right)>(env->GetIntField(_rd_inside_border_insets, env->GetFieldID(_rd_inside_border_insetsc, "right", "I")));
        }
        _settings_val.between_child_spacing = static_cast<decltype(_settings_val.between_child_spacing)>(env->GetIntField(settings, env->GetFieldID(_c, "betweenChildSpacing", "I")));
        auto _rd_main_axis_alignment = env->GetObjectField(settings, env->GetFieldID(_c, "mainAxisAlignment", "Lnet/kurobako/cef4j/gen/CefAxisAlignment;"));
        if (_rd_main_axis_alignment) {
            _settings_val.main_axis_alignment = static_cast<decltype(_settings_val.main_axis_alignment)>(env->GetLongField(_rd_main_axis_alignment, env->GetFieldID(env->GetObjectClass(_rd_main_axis_alignment), "value", "J")));
        }
        auto _rd_cross_axis_alignment = env->GetObjectField(settings, env->GetFieldID(_c, "crossAxisAlignment", "Lnet/kurobako/cef4j/gen/CefAxisAlignment;"));
        if (_rd_cross_axis_alignment) {
            _settings_val.cross_axis_alignment = static_cast<decltype(_settings_val.cross_axis_alignment)>(env->GetLongField(_rd_cross_axis_alignment, env->GetFieldID(env->GetObjectClass(_rd_cross_axis_alignment), "value", "J")));
        }
        _settings_val.minimum_cross_axis_size = static_cast<decltype(_settings_val.minimum_cross_axis_size)>(env->GetIntField(settings, env->GetFieldID(_c, "minimumCrossAxisSize", "I")));
        _settings_val.default_flex = static_cast<decltype(_settings_val.default_flex)>(env->GetIntField(settings, env->GetFieldID(_c, "defaultFlex", "I")));
        _settings_val.size = sizeof(cef_box_layout_settings_t);
    }
    auto _r = s->set_to_box_layout(s, &_settings_val);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/views/CefBoxLayout$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(views_CefPanel), getLayout0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_panel_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_layout(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/views/CefLayout$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefPanel), layout0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_panel_t*>(self);
    if (!s) return;
    s->layout(s);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefPanel), addChildView0)(JNIEnv* env, jobject obj, jlong self, jobject view) {
    auto* s = reinterpret_cast<cef_panel_t*>(self);
    if (!s) return;
    cef_view_t* _view_ptr = view ? reinterpret_cast<cef_view_t*>(env->GetLongField(view, env->GetFieldID(env->GetObjectClass(view), "nativePtr", "J"))) : nullptr;
    if (_view_ptr) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_view_ptr); _b->add_ref(_b); }
    s->add_child_view(s, _view_ptr);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefPanel), addChildViewAt0)(JNIEnv* env, jobject obj, jlong self, jobject view, jint index) {
    auto* s = reinterpret_cast<cef_panel_t*>(self);
    if (!s) return;
    cef_view_t* _view_ptr = view ? reinterpret_cast<cef_view_t*>(env->GetLongField(view, env->GetFieldID(env->GetObjectClass(view), "nativePtr", "J"))) : nullptr;
    if (_view_ptr) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_view_ptr); _b->add_ref(_b); }
    s->add_child_view_at(s, _view_ptr, index);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefPanel), reorderChildView0)(JNIEnv* env, jobject obj, jlong self, jobject view, jint index) {
    auto* s = reinterpret_cast<cef_panel_t*>(self);
    if (!s) return;
    cef_view_t* _view_ptr = view ? reinterpret_cast<cef_view_t*>(env->GetLongField(view, env->GetFieldID(env->GetObjectClass(view), "nativePtr", "J"))) : nullptr;
    if (_view_ptr) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_view_ptr); _b->add_ref(_b); }
    s->reorder_child_view(s, _view_ptr, index);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefPanel), removeChildView0)(JNIEnv* env, jobject obj, jlong self, jobject view) {
    auto* s = reinterpret_cast<cef_panel_t*>(self);
    if (!s) return;
    cef_view_t* _view_ptr = view ? reinterpret_cast<cef_view_t*>(env->GetLongField(view, env->GetFieldID(env->GetObjectClass(view), "nativePtr", "J"))) : nullptr;
    if (_view_ptr) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_view_ptr); _b->add_ref(_b); }
    s->remove_child_view(s, _view_ptr);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefPanel), removeAllChildViews0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_panel_t*>(self);
    if (!s) return;
    s->remove_all_child_views(s);
}

CEF4J_JNI_EXPORT(jlong, CEF4J_PEER(views_CefPanel), getChildViewCount0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_panel_t*>(self);
    if (!s) return 0;
    return static_cast<jlong>(s->get_child_view_count(s));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(views_CefPanel), create0)(JNIEnv* env, jclass clz, jobject delegate) {
    cef_panel_delegate_t* _delegate_ptr = delegate ? Create_JniCefPanelDelegate(env, delegate) : nullptr;
    auto _r = cef_panel_create(_delegate_ptr);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/views/CefPanel$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}
