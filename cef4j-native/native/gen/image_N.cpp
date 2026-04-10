// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
#include <jni.h>
#include "include/capi/cef_image_capi.h"
#include "include/capi/cef_values_capi.h"
#include "jni_util.h"

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefImage), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefImage), isEmpty0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_image_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_empty(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefImage), isSame0)(JNIEnv* env, jobject obj, jlong self, jobject that) {
    auto* s = reinterpret_cast<cef_image_t*>(self);
    if (!s) return JNI_FALSE;
    cef_image_t* _that_ptr = that ? reinterpret_cast<cef_image_t*>(env->GetLongField(that, env->GetFieldID(env->GetObjectClass(that), "nativePtr", "J"))) : nullptr;
    if (_that_ptr) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_that_ptr); _b->add_ref(_b); }
    auto _r = s->is_same(s, _that_ptr);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefImage), addBitmap0)(JNIEnv* env, jobject obj, jlong self, jfloat scale_factor, jint pixel_width, jint pixel_height, jobject color_type, jobject alpha_type, jobject pixel_data) {
    auto* s = reinterpret_cast<cef_image_t*>(self);
    if (!s) return JNI_FALSE;
    if (!color_type) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "colorType must not be null"); return JNI_FALSE; }
    if (!alpha_type) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "alphaType must not be null"); return JNI_FALSE; }
    if (!pixel_data) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "pixelData must not be null"); return JNI_FALSE; }
    const void* _pixel_data_addr = pixel_data ? env->GetDirectBufferAddress(pixel_data) : nullptr;
    if (pixel_data && !_pixel_data_addr) { env->ThrowNew(FindClassCached(env, "java/lang/IllegalArgumentException"), "pixelData must be a direct ByteBuffer; use ByteBuffer.allocateDirect(...)"); return JNI_FALSE; }
    auto _r = s->add_bitmap(s, scale_factor, pixel_width, pixel_height, static_cast<cef_color_type_t>(env->GetLongField(color_type, env->GetFieldID(env->GetObjectClass(color_type), "value", "J"))), static_cast<cef_alpha_type_t>(env->GetLongField(alpha_type, env->GetFieldID(env->GetObjectClass(alpha_type), "value", "J"))), _pixel_data_addr, static_cast<size_t>(env->GetDirectBufferCapacity(pixel_data)));
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefImage), addPng0)(JNIEnv* env, jobject obj, jlong self, jfloat scale_factor, jobject png_data) {
    auto* s = reinterpret_cast<cef_image_t*>(self);
    if (!s) return JNI_FALSE;
    if (!png_data) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "pngData must not be null"); return JNI_FALSE; }
    const void* _png_data_addr = png_data ? env->GetDirectBufferAddress(png_data) : nullptr;
    if (png_data && !_png_data_addr) { env->ThrowNew(FindClassCached(env, "java/lang/IllegalArgumentException"), "pngData must be a direct ByteBuffer; use ByteBuffer.allocateDirect(...)"); return JNI_FALSE; }
    auto _r = s->add_png(s, scale_factor, _png_data_addr, static_cast<size_t>(env->GetDirectBufferCapacity(png_data)));
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefImage), addJpeg0)(JNIEnv* env, jobject obj, jlong self, jfloat scale_factor, jobject jpeg_data) {
    auto* s = reinterpret_cast<cef_image_t*>(self);
    if (!s) return JNI_FALSE;
    if (!jpeg_data) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "jpegData must not be null"); return JNI_FALSE; }
    const void* _jpeg_data_addr = jpeg_data ? env->GetDirectBufferAddress(jpeg_data) : nullptr;
    if (jpeg_data && !_jpeg_data_addr) { env->ThrowNew(FindClassCached(env, "java/lang/IllegalArgumentException"), "jpegData must be a direct ByteBuffer; use ByteBuffer.allocateDirect(...)"); return JNI_FALSE; }
    auto _r = s->add_jpeg(s, scale_factor, _jpeg_data_addr, static_cast<size_t>(env->GetDirectBufferCapacity(jpeg_data)));
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jlong, CEF4J_PEER(CefImage), getWidth0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_image_t*>(self);
    if (!s) return 0;
    return static_cast<jlong>(s->get_width(s));
}

CEF4J_JNI_EXPORT(jlong, CEF4J_PEER(CefImage), getHeight0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_image_t*>(self);
    if (!s) return 0;
    return static_cast<jlong>(s->get_height(s));
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefImage), hasRepresentation0)(JNIEnv* env, jobject obj, jlong self, jfloat scale_factor) {
    auto* s = reinterpret_cast<cef_image_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->has_representation(s, scale_factor);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefImage), removeRepresentation0)(JNIEnv* env, jobject obj, jlong self, jfloat scale_factor) {
    auto* s = reinterpret_cast<cef_image_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->remove_representation(s, scale_factor);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefImage), getRepresentationInfo0)(JNIEnv* env, jobject obj, jlong self, jfloat scale_factor, jfloatArray actual_scale_factor, jintArray pixel_width, jintArray pixel_height) {
    auto* s = reinterpret_cast<cef_image_t*>(self);
    if (!s) return JNI_FALSE;
    float _actual_scale_factor_val = 0;
    if (actual_scale_factor) { jfloat _jv; env->GetFloatArrayRegion(actual_scale_factor, 0, 1, &_jv); _actual_scale_factor_val = static_cast<float>(_jv); }
    int _pixel_width_val = 0;
    if (pixel_width) { jint _jv; env->GetIntArrayRegion(pixel_width, 0, 1, &_jv); _pixel_width_val = _jv; }
    int _pixel_height_val = 0;
    if (pixel_height) { jint _jv; env->GetIntArrayRegion(pixel_height, 0, 1, &_jv); _pixel_height_val = _jv; }
    auto _r = s->get_representation_info(s, scale_factor, &_actual_scale_factor_val, &_pixel_width_val, &_pixel_height_val);
    if (actual_scale_factor) { jfloat _jv = static_cast<jfloat>(_actual_scale_factor_val); env->SetFloatArrayRegion(actual_scale_factor, 0, 1, &_jv); }
    if (pixel_width) { jint _jv = _pixel_width_val; env->SetIntArrayRegion(pixel_width, 0, 1, &_jv); }
    if (pixel_height) { jint _jv = _pixel_height_val; env->SetIntArrayRegion(pixel_height, 0, 1, &_jv); }
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefImage), getAsBitmap0)(JNIEnv* env, jobject obj, jlong self, jfloat scale_factor, jobject color_type, jobject alpha_type, jintArray pixel_width, jintArray pixel_height) {
    auto* s = reinterpret_cast<cef_image_t*>(self);
    if (!s) return nullptr;
    if (!color_type) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "colorType must not be null"); return nullptr; }
    if (!alpha_type) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "alphaType must not be null"); return nullptr; }
    int _pixel_width_val = 0;
    if (pixel_width) { jint _jv; env->GetIntArrayRegion(pixel_width, 0, 1, &_jv); _pixel_width_val = _jv; }
    int _pixel_height_val = 0;
    if (pixel_height) { jint _jv; env->GetIntArrayRegion(pixel_height, 0, 1, &_jv); _pixel_height_val = _jv; }
    auto _r = s->get_as_bitmap(s, scale_factor, static_cast<cef_color_type_t>(env->GetLongField(color_type, env->GetFieldID(env->GetObjectClass(color_type), "value", "J"))), static_cast<cef_alpha_type_t>(env->GetLongField(alpha_type, env->GetFieldID(env->GetObjectClass(alpha_type), "value", "J"))), &_pixel_width_val, &_pixel_height_val);
    if (pixel_width) { jint _jv = _pixel_width_val; env->SetIntArrayRegion(pixel_width, 0, 1, &_jv); }
    if (pixel_height) { jint _jv = _pixel_height_val; env->SetIntArrayRegion(pixel_height, 0, 1, &_jv); }
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefBinaryValue$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefImage), getAsPng0)(JNIEnv* env, jobject obj, jlong self, jfloat scale_factor, jboolean with_transparency, jintArray pixel_width, jintArray pixel_height) {
    auto* s = reinterpret_cast<cef_image_t*>(self);
    if (!s) return nullptr;
    int _pixel_width_val = 0;
    if (pixel_width) { jint _jv; env->GetIntArrayRegion(pixel_width, 0, 1, &_jv); _pixel_width_val = _jv; }
    int _pixel_height_val = 0;
    if (pixel_height) { jint _jv; env->GetIntArrayRegion(pixel_height, 0, 1, &_jv); _pixel_height_val = _jv; }
    auto _r = s->get_as_png(s, scale_factor, static_cast<bool>(with_transparency), &_pixel_width_val, &_pixel_height_val);
    if (pixel_width) { jint _jv = _pixel_width_val; env->SetIntArrayRegion(pixel_width, 0, 1, &_jv); }
    if (pixel_height) { jint _jv = _pixel_height_val; env->SetIntArrayRegion(pixel_height, 0, 1, &_jv); }
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefBinaryValue$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefImage), getAsJpeg0)(JNIEnv* env, jobject obj, jlong self, jfloat scale_factor, jint quality, jintArray pixel_width, jintArray pixel_height) {
    auto* s = reinterpret_cast<cef_image_t*>(self);
    if (!s) return nullptr;
    int _pixel_width_val = 0;
    if (pixel_width) { jint _jv; env->GetIntArrayRegion(pixel_width, 0, 1, &_jv); _pixel_width_val = _jv; }
    int _pixel_height_val = 0;
    if (pixel_height) { jint _jv; env->GetIntArrayRegion(pixel_height, 0, 1, &_jv); _pixel_height_val = _jv; }
    auto _r = s->get_as_jpeg(s, scale_factor, quality, &_pixel_width_val, &_pixel_height_val);
    if (pixel_width) { jint _jv = _pixel_width_val; env->SetIntArrayRegion(pixel_width, 0, 1, &_jv); }
    if (pixel_height) { jint _jv = _pixel_height_val; env->SetIntArrayRegion(pixel_height, 0, 1, &_jv); }
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefBinaryValue$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefImage), create0)(JNIEnv* env, jclass clz) {
    auto _r = cef_image_create();
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefImage$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}
