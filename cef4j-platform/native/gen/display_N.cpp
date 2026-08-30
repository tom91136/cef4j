// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
#include <jni.h>
#include "include/capi/views/cef_display_capi.h"
#include "jni_util.h"

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefDisplay), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

CEF4J_JNI_EXPORT(jlong, CEF4J_PEER(views_CefDisplay), getId0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_display_t*>(self);
    if (!s) return 0;
    return to_jlong(s->get_id(s));
}

CEF4J_JNI_EXPORT(jfloat, CEF4J_PEER(views_CefDisplay), getDeviceScaleFactor0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_display_t*>(self);
    if (!s) return 0;
    return static_cast<jfloat>(s->get_device_scale_factor(s));
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefDisplay), convertPointToPixels0)(JNIEnv* env, jobject obj, jlong self, jobject point) {
    auto* s = reinterpret_cast<cef_display_t*>(self);
    if (!s) return;
    if (!point) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "point must not be null"); return; }
    cef_point_t _point_val = {};
    auto _point_c = FindClassCached(env, "net/kurobako/cef4j/gen/CefPoint$Mutable");
    _point_val.x = static_cast<decltype(_point_val.x)>(env->GetIntField(point, env->GetFieldID(_point_c, "x", "I")));
    _point_val.y = static_cast<decltype(_point_val.y)>(env->GetIntField(point, env->GetFieldID(_point_c, "y", "I")));
    s->convert_point_to_pixels(s, &_point_val);
    env->SetIntField(point, env->GetFieldID(_point_c, "x", "I"), static_cast<jint>((&_point_val)->x));
    env->SetIntField(point, env->GetFieldID(_point_c, "y", "I"), static_cast<jint>((&_point_val)->y));
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(views_CefDisplay), convertPointFromPixels0)(JNIEnv* env, jobject obj, jlong self, jobject point) {
    auto* s = reinterpret_cast<cef_display_t*>(self);
    if (!s) return;
    if (!point) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "point must not be null"); return; }
    cef_point_t _point_val = {};
    auto _point_c = FindClassCached(env, "net/kurobako/cef4j/gen/CefPoint$Mutable");
    _point_val.x = static_cast<decltype(_point_val.x)>(env->GetIntField(point, env->GetFieldID(_point_c, "x", "I")));
    _point_val.y = static_cast<decltype(_point_val.y)>(env->GetIntField(point, env->GetFieldID(_point_c, "y", "I")));
    s->convert_point_from_pixels(s, &_point_val);
    env->SetIntField(point, env->GetFieldID(_point_c, "x", "I"), static_cast<jint>((&_point_val)->x));
    env->SetIntField(point, env->GetFieldID(_point_c, "y", "I"), static_cast<jint>((&_point_val)->y));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(views_CefDisplay), getBounds0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_display_t*>(self);
    if (!s) return nullptr;
    cef_rect_t result = s->get_bounds(s);
    auto cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefRect");
    auto ctor = env->GetMethodID(cls, "<init>", "(IIII)V");
    auto _dsResult = env->NewObject(cls, ctor, static_cast<jint>(((&result))->x), static_cast<jint>(((&result))->y), static_cast<jint>(((&result))->width), static_cast<jint>(((&result))->height));
    return _dsResult;
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(views_CefDisplay), getWorkArea0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_display_t*>(self);
    if (!s) return nullptr;
    cef_rect_t result = s->get_work_area(s);
    auto cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefRect");
    auto ctor = env->GetMethodID(cls, "<init>", "(IIII)V");
    auto _dsResult = env->NewObject(cls, ctor, static_cast<jint>(((&result))->x), static_cast<jint>(((&result))->y), static_cast<jint>(((&result))->width), static_cast<jint>(((&result))->height));
    return _dsResult;
}

CEF4J_JNI_EXPORT(jint, CEF4J_PEER(views_CefDisplay), getRotation0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_display_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->get_rotation(s));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(views_CefDisplay), getPrimary0)(JNIEnv* env, jclass clz) {
    auto _r = cef_display_get_primary();
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/views/CefDisplay$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(views_CefDisplay), getNearestPoint0)(JNIEnv* env, jclass clz, jobject point, jint input_pixel_coords) {
    if (!point) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "point must not be null"); return nullptr; }
    cef_point_t _point_val = {};
    auto _point_c = FindClassCached(env, "net/kurobako/cef4j/gen/CefPoint");
    _point_val.x = static_cast<decltype(_point_val.x)>(env->GetIntField(point, env->GetFieldID(_point_c, "x", "I")));
    _point_val.y = static_cast<decltype(_point_val.y)>(env->GetIntField(point, env->GetFieldID(_point_c, "y", "I")));
    auto _r = cef_display_get_nearest_point(&_point_val, input_pixel_coords);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/views/CefDisplay$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(views_CefDisplay), getMatchingBounds0)(JNIEnv* env, jclass clz, jobject bounds, jint input_pixel_coords) {
    if (!bounds) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "bounds must not be null"); return nullptr; }
    cef_rect_t _bounds_val = {};
    auto _bounds_c = FindClassCached(env, "net/kurobako/cef4j/gen/CefRect");
    _bounds_val.x = static_cast<decltype(_bounds_val.x)>(env->GetIntField(bounds, env->GetFieldID(_bounds_c, "x", "I")));
    _bounds_val.y = static_cast<decltype(_bounds_val.y)>(env->GetIntField(bounds, env->GetFieldID(_bounds_c, "y", "I")));
    _bounds_val.width = static_cast<decltype(_bounds_val.width)>(env->GetIntField(bounds, env->GetFieldID(_bounds_c, "width", "I")));
    _bounds_val.height = static_cast<decltype(_bounds_val.height)>(env->GetIntField(bounds, env->GetFieldID(_bounds_c, "height", "I")));
    auto _r = cef_display_get_matching_bounds(&_bounds_val, input_pixel_coords);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/views/CefDisplay$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jlong, CEF4J_PEER(views_CefDisplay), getCount0)(JNIEnv* env, jclass clz) {
    return static_cast<jlong>(cef_display_get_count());
}

CEF4J_JNI_EXPORT(jobjectArray, CEF4J_PEER(views_CefDisplay), getAlls0)(JNIEnv* env, jclass clz) {
    size_t _count = cef_display_get_count();
    cef_display_t** _arr = _count > 0 ? new cef_display_t*[_count]() : nullptr;
    cef_display_get_alls(&_count, _arr);
    auto _result = env->NewObjectArray(static_cast<jsize>(_count), FindClassCached(env, "net/kurobako/cef4j/gen/views/CefDisplay$NativePeer"), nullptr);
    auto _peerCls = FindClassCached(env, "net/kurobako/cef4j/gen/views/CefDisplay$NativePeer");
    auto _peerCtor = env->GetMethodID(_peerCls, "<init>", "(J)V");
    for (size_t _i = 0; _i < _count; _i++) {
        auto _elem = _arr[_i] ? env->NewObject(_peerCls, _peerCtor, reinterpret_cast<jlong>(_arr[_i])) : nullptr;
        env->SetObjectArrayElement(_result, static_cast<jsize>(_i), _elem);
    }
    delete[] _arr;
    return _result;
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(views_CefDisplay), convertScreenPointToPixels0)(JNIEnv* env, jclass clz, jobject point) {
    if (!point) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "point must not be null"); return nullptr; }
    cef_point_t _point_val = {};
    auto _point_c = FindClassCached(env, "net/kurobako/cef4j/gen/CefPoint");
    _point_val.x = static_cast<decltype(_point_val.x)>(env->GetIntField(point, env->GetFieldID(_point_c, "x", "I")));
    _point_val.y = static_cast<decltype(_point_val.y)>(env->GetIntField(point, env->GetFieldID(_point_c, "y", "I")));
    cef_point_t result = cef_display_convert_screen_point_to_pixels(&_point_val);
    auto cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefPoint");
    auto ctor = env->GetMethodID(cls, "<init>", "(II)V");
    auto _dsResult = env->NewObject(cls, ctor, static_cast<jint>(((&result))->x), static_cast<jint>(((&result))->y));
    return _dsResult;
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(views_CefDisplay), convertScreenPointFromPixels0)(JNIEnv* env, jclass clz, jobject point) {
    if (!point) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "point must not be null"); return nullptr; }
    cef_point_t _point_val = {};
    auto _point_c = FindClassCached(env, "net/kurobako/cef4j/gen/CefPoint");
    _point_val.x = static_cast<decltype(_point_val.x)>(env->GetIntField(point, env->GetFieldID(_point_c, "x", "I")));
    _point_val.y = static_cast<decltype(_point_val.y)>(env->GetIntField(point, env->GetFieldID(_point_c, "y", "I")));
    cef_point_t result = cef_display_convert_screen_point_from_pixels(&_point_val);
    auto cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefPoint");
    auto ctor = env->GetMethodID(cls, "<init>", "(II)V");
    auto _dsResult = env->NewObject(cls, ctor, static_cast<jint>(((&result))->x), static_cast<jint>(((&result))->y));
    return _dsResult;
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(views_CefDisplay), convertScreenRectToPixels0)(JNIEnv* env, jclass clz, jobject rect) {
    if (!rect) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "rect must not be null"); return nullptr; }
    cef_rect_t _rect_val = {};
    auto _rect_c = FindClassCached(env, "net/kurobako/cef4j/gen/CefRect");
    _rect_val.x = static_cast<decltype(_rect_val.x)>(env->GetIntField(rect, env->GetFieldID(_rect_c, "x", "I")));
    _rect_val.y = static_cast<decltype(_rect_val.y)>(env->GetIntField(rect, env->GetFieldID(_rect_c, "y", "I")));
    _rect_val.width = static_cast<decltype(_rect_val.width)>(env->GetIntField(rect, env->GetFieldID(_rect_c, "width", "I")));
    _rect_val.height = static_cast<decltype(_rect_val.height)>(env->GetIntField(rect, env->GetFieldID(_rect_c, "height", "I")));
    cef_rect_t result = cef_display_convert_screen_rect_to_pixels(&_rect_val);
    auto cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefRect");
    auto ctor = env->GetMethodID(cls, "<init>", "(IIII)V");
    auto _dsResult = env->NewObject(cls, ctor, static_cast<jint>(((&result))->x), static_cast<jint>(((&result))->y), static_cast<jint>(((&result))->width), static_cast<jint>(((&result))->height));
    return _dsResult;
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(views_CefDisplay), convertScreenRectFromPixels0)(JNIEnv* env, jclass clz, jobject rect) {
    if (!rect) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "rect must not be null"); return nullptr; }
    cef_rect_t _rect_val = {};
    auto _rect_c = FindClassCached(env, "net/kurobako/cef4j/gen/CefRect");
    _rect_val.x = static_cast<decltype(_rect_val.x)>(env->GetIntField(rect, env->GetFieldID(_rect_c, "x", "I")));
    _rect_val.y = static_cast<decltype(_rect_val.y)>(env->GetIntField(rect, env->GetFieldID(_rect_c, "y", "I")));
    _rect_val.width = static_cast<decltype(_rect_val.width)>(env->GetIntField(rect, env->GetFieldID(_rect_c, "width", "I")));
    _rect_val.height = static_cast<decltype(_rect_val.height)>(env->GetIntField(rect, env->GetFieldID(_rect_c, "height", "I")));
    cef_rect_t result = cef_display_convert_screen_rect_from_pixels(&_rect_val);
    auto cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefRect");
    auto ctor = env->GetMethodID(cls, "<init>", "(IIII)V");
    auto _dsResult = env->NewObject(cls, ctor, static_cast<jint>(((&result))->x), static_cast<jint>(((&result))->y), static_cast<jint>(((&result))->width), static_cast<jint>(((&result))->height));
    return _dsResult;
}
