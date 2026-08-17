// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
#include <jni.h>
#include "include/capi/views/cef_overlay_controller_capi.h"
#include "include/capi/views/cef_view_capi.h"
#include "include/capi/views/cef_window_capi.h"
#include "jni_util.h"

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefOverlayController), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(views_CefOverlayController), isValid0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_overlay_controller_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_valid(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(views_CefOverlayController), isSame0)(JNIEnv* env, jobject obj, jlong self, jobject that) {
    auto* s = reinterpret_cast<cef_overlay_controller_t*>(self);
    if (!s) return JNI_FALSE;
    cef_overlay_controller_t* _that_ptr = that ? reinterpret_cast<cef_overlay_controller_t*>(env->GetLongField(that, env->GetFieldID(env->GetObjectClass(that), "nativePtr", "J"))) : nullptr;
    if (_that_ptr) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_that_ptr); _b->add_ref(_b); }
    auto _r = s->is_same(s, _that_ptr);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(views_CefOverlayController), getContentsView0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_overlay_controller_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_contents_view(s);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/views/CefView$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(views_CefOverlayController), getWindow0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_overlay_controller_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_window(s);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/views/CefWindow$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(views_CefOverlayController), getDockingMode0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_overlay_controller_t*>(self);
    if (!s) return 0;
    auto _r = s->get_docking_mode(s);
    auto _eCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefDockingMode");
    auto _eOf = env->GetStaticMethodID(_eCls, "of", "(J)Lnet/kurobako/cef4j/gen/CefDockingMode;");
    return env->CallStaticObjectMethod(_eCls, _eOf, static_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefOverlayController), destroy0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_overlay_controller_t*>(self);
    if (!s) return;
    s->destroy(s);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefOverlayController), setBounds0)(JNIEnv* env, jobject obj, jlong self, jobject bounds) {
    auto* s = reinterpret_cast<cef_overlay_controller_t*>(self);
    if (!s) return;
    if (!bounds) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "bounds must not be null"); return; }
    cef_rect_t _bounds_val = {};
    auto _bounds_c = FindClassCached(env, "net/kurobako/cef4j/gen/CefRect");
    _bounds_val.x = static_cast<decltype(_bounds_val.x)>(env->GetIntField(bounds, env->GetFieldID(_bounds_c, "x", "I")));
    _bounds_val.y = static_cast<decltype(_bounds_val.y)>(env->GetIntField(bounds, env->GetFieldID(_bounds_c, "y", "I")));
    _bounds_val.width = static_cast<decltype(_bounds_val.width)>(env->GetIntField(bounds, env->GetFieldID(_bounds_c, "width", "I")));
    _bounds_val.height = static_cast<decltype(_bounds_val.height)>(env->GetIntField(bounds, env->GetFieldID(_bounds_c, "height", "I")));
    s->set_bounds(s, &_bounds_val);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(views_CefOverlayController), getBounds0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_overlay_controller_t*>(self);
    if (!s) return nullptr;
    cef_rect_t result = s->get_bounds(s);
    auto cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefRect");
    auto ctor = env->GetMethodID(cls, "<init>", "(IIII)V");
    auto _dsResult = env->NewObject(cls, ctor, static_cast<jint>(((&result))->x), static_cast<jint>(((&result))->y), static_cast<jint>(((&result))->width), static_cast<jint>(((&result))->height));
    return _dsResult;
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(views_CefOverlayController), getBoundsInScreen0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_overlay_controller_t*>(self);
    if (!s) return nullptr;
    cef_rect_t result = s->get_bounds_in_screen(s);
    auto cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefRect");
    auto ctor = env->GetMethodID(cls, "<init>", "(IIII)V");
    auto _dsResult = env->NewObject(cls, ctor, static_cast<jint>(((&result))->x), static_cast<jint>(((&result))->y), static_cast<jint>(((&result))->width), static_cast<jint>(((&result))->height));
    return _dsResult;
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefOverlayController), setSize0)(JNIEnv* env, jobject obj, jlong self, jobject size) {
    auto* s = reinterpret_cast<cef_overlay_controller_t*>(self);
    if (!s) return;
    if (!size) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "size must not be null"); return; }
    cef_size_t _size_val = {};
    auto _size_c = FindClassCached(env, "net/kurobako/cef4j/gen/CefSize");
    _size_val.width = static_cast<decltype(_size_val.width)>(env->GetIntField(size, env->GetFieldID(_size_c, "width", "I")));
    _size_val.height = static_cast<decltype(_size_val.height)>(env->GetIntField(size, env->GetFieldID(_size_c, "height", "I")));
    s->set_size(s, &_size_val);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(views_CefOverlayController), getSize0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_overlay_controller_t*>(self);
    if (!s) return nullptr;
    cef_size_t result = s->get_size(s);
    auto cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefSize");
    auto ctor = env->GetMethodID(cls, "<init>", "(II)V");
    auto _dsResult = env->NewObject(cls, ctor, static_cast<jint>(((&result))->width), static_cast<jint>(((&result))->height));
    return _dsResult;
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefOverlayController), setPosition0)(JNIEnv* env, jobject obj, jlong self, jobject position) {
    auto* s = reinterpret_cast<cef_overlay_controller_t*>(self);
    if (!s) return;
    if (!position) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "position must not be null"); return; }
    cef_point_t _position_val = {};
    auto _position_c = FindClassCached(env, "net/kurobako/cef4j/gen/CefPoint");
    _position_val.x = static_cast<decltype(_position_val.x)>(env->GetIntField(position, env->GetFieldID(_position_c, "x", "I")));
    _position_val.y = static_cast<decltype(_position_val.y)>(env->GetIntField(position, env->GetFieldID(_position_c, "y", "I")));
    s->set_position(s, &_position_val);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(views_CefOverlayController), getPosition0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_overlay_controller_t*>(self);
    if (!s) return nullptr;
    cef_point_t result = s->get_position(s);
    auto cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefPoint");
    auto ctor = env->GetMethodID(cls, "<init>", "(II)V");
    auto _dsResult = env->NewObject(cls, ctor, static_cast<jint>(((&result))->x), static_cast<jint>(((&result))->y));
    return _dsResult;
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefOverlayController), setInsets0)(JNIEnv* env, jobject obj, jlong self, jobject insets) {
    auto* s = reinterpret_cast<cef_overlay_controller_t*>(self);
    if (!s) return;
    if (!insets) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "insets must not be null"); return; }
    cef_insets_t _insets_val = {};
    auto _insets_c = FindClassCached(env, "net/kurobako/cef4j/gen/CefInsets");
    _insets_val.top = static_cast<decltype(_insets_val.top)>(env->GetIntField(insets, env->GetFieldID(_insets_c, "top", "I")));
    _insets_val.left = static_cast<decltype(_insets_val.left)>(env->GetIntField(insets, env->GetFieldID(_insets_c, "left", "I")));
    _insets_val.bottom = static_cast<decltype(_insets_val.bottom)>(env->GetIntField(insets, env->GetFieldID(_insets_c, "bottom", "I")));
    _insets_val.right = static_cast<decltype(_insets_val.right)>(env->GetIntField(insets, env->GetFieldID(_insets_c, "right", "I")));
    s->set_insets(s, &_insets_val);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(views_CefOverlayController), getInsets0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_overlay_controller_t*>(self);
    if (!s) return nullptr;
    cef_insets_t result = s->get_insets(s);
    auto cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefInsets");
    auto ctor = env->GetMethodID(cls, "<init>", "(IIII)V");
    auto _dsResult = env->NewObject(cls, ctor, static_cast<jint>(((&result))->top), static_cast<jint>(((&result))->left), static_cast<jint>(((&result))->bottom), static_cast<jint>(((&result))->right));
    return _dsResult;
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefOverlayController), sizeToPreferredSize0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_overlay_controller_t*>(self);
    if (!s) return;
    s->size_to_preferred_size(s);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefOverlayController), setVisible0)(JNIEnv* env, jobject obj, jlong self, jboolean visible) {
    auto* s = reinterpret_cast<cef_overlay_controller_t*>(self);
    if (!s) return;
    s->set_visible(s, static_cast<bool>(visible));
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(views_CefOverlayController), isVisible0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_overlay_controller_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_visible(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(views_CefOverlayController), isDrawn0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_overlay_controller_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_drawn(s);
    return static_cast<jboolean>(_r);
}
