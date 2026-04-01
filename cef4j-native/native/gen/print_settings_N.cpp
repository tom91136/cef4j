// GENERATED - do not edit.
#include <jni.h>
#include "include/capi/cef_print_settings_capi.h"
#include "jni_util.h"

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefPrintSettings_00024NativePeer_N_1Release(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefPrintSettings_00024NativePeer_N_1IsValid(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_print_settings_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_valid(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefPrintSettings_00024NativePeer_N_1IsReadOnly(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_print_settings_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_read_only(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefPrintSettings_00024NativePeer_N_1SetOrientation(JNIEnv* env, jobject obj, jlong self, jboolean landscape) {
    auto* s = reinterpret_cast<cef_print_settings_t*>(self);
    if (!s) return;
    s->set_orientation(s, static_cast<bool>(landscape));
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefPrintSettings_00024NativePeer_N_1IsLandscape(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_print_settings_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_landscape(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefPrintSettings_00024NativePeer_N_1SetPrinterPrintableArea(JNIEnv* env, jobject obj, jlong self, jobject physical_size_device_units, jobject printable_area_device_units, jboolean landscape_needs_flip) {
    auto* s = reinterpret_cast<cef_print_settings_t*>(self);
    if (!s) return;
    if (!physical_size_device_units) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "physicalSizeDeviceUnits must not be null"); return;}
    if (!printable_area_device_units) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "printableAreaDeviceUnits must not be null"); return;}
    cef_size_t _physical_size_device_units_val = {};
    if (physical_size_device_units) {auto _c = env->FindClass("net/kurobako/cef4j/gen/CefSize"); _physical_size_device_units_val.width = static_cast<decltype(_physical_size_device_units_val.width)>(env->GetIntField(physical_size_device_units, env->GetFieldID(_c, "width", "I"))); _physical_size_device_units_val.height = static_cast<decltype(_physical_size_device_units_val.height)>(env->GetIntField(physical_size_device_units, env->GetFieldID(_c, "height", "I")));}
    cef_rect_t _printable_area_device_units_val = {};
    if (printable_area_device_units) {auto _c = env->FindClass("net/kurobako/cef4j/gen/CefRect"); _printable_area_device_units_val.x = static_cast<decltype(_printable_area_device_units_val.x)>(env->GetIntField(printable_area_device_units, env->GetFieldID(_c, "x", "I"))); _printable_area_device_units_val.y = static_cast<decltype(_printable_area_device_units_val.y)>(env->GetIntField(printable_area_device_units, env->GetFieldID(_c, "y", "I"))); _printable_area_device_units_val.width = static_cast<decltype(_printable_area_device_units_val.width)>(env->GetIntField(printable_area_device_units, env->GetFieldID(_c, "width", "I"))); _printable_area_device_units_val.height = static_cast<decltype(_printable_area_device_units_val.height)>(env->GetIntField(printable_area_device_units, env->GetFieldID(_c, "height", "I")));}
    s->set_printer_printable_area(s, &_physical_size_device_units_val, &_printable_area_device_units_val, static_cast<bool>(landscape_needs_flip));
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefPrintSettings_00024NativePeer_N_1SetDeviceName(JNIEnv* env, jobject obj, jlong self, jstring name) {
    auto* s = reinterpret_cast<cef_print_settings_t*>(self);
    if (!s) return;
    auto _name_str = name ? JStringToCefString(env, name) : nullptr;
    s->set_device_name(s, _name_str);
    if (_name_str) cef_string_userfree_free(_name_str);
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefPrintSettings_00024NativePeer_N_1GetDeviceName(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_print_settings_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_device_name(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefPrintSettings_00024NativePeer_N_1SetDpi(JNIEnv* env, jobject obj, jlong self, jint dpi) {
    auto* s = reinterpret_cast<cef_print_settings_t*>(self);
    if (!s) return;
    s->set_dpi(s, dpi);
}

extern "C" JNIEXPORT jint JNICALL Java_net_kurobako_cef4j_gen_CefPrintSettings_00024NativePeer_N_1GetDpi(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_print_settings_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->get_dpi(s));
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefPrintSettings_00024NativePeer_N_1SetPageRanges(JNIEnv* env, jobject obj, jlong self, jlong rangesCount, jobjectArray ranges) {
    auto* s = reinterpret_cast<cef_print_settings_t*>(self);
    if (!s) return;
    if (!ranges) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "ranges must not be null"); return;}
    size_t _ranges_sz = static_cast<size_t>(rangesCount);
    cef_range_t* _ranges_arr = _ranges_sz > 0 ? new cef_range_t[_ranges_sz]() : nullptr;
    {   auto _bvac = env->FindClass("net/kurobako/cef4j/gen/CefRange");
        for (size_t _i = 0; _i < _ranges_sz; _i++) {
            auto _elem = env->GetObjectArrayElement(ranges, _i);
            if (_elem) {_ranges_arr[_i].from = static_cast<decltype(_ranges_arr[_i].from)>(env->GetIntField(_elem, env->GetFieldID(_bvac, "from", "I"))); _ranges_arr[_i].to = static_cast<decltype(_ranges_arr[_i].to)>(env->GetIntField(_elem, env->GetFieldID(_bvac, "to", "I")));}
        }}
    s->set_page_ranges(s, rangesCount, _ranges_arr);
    delete[] _ranges_arr;
}

extern "C" JNIEXPORT jlong JNICALL Java_net_kurobako_cef4j_gen_CefPrintSettings_00024NativePeer_N_1GetPageRangesCount(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_print_settings_t*>(self);
    if (!s) return 0;
    return static_cast<jlong>(s->get_page_ranges_count(s));
}

extern "C" JNIEXPORT jobjectArray JNICALL Java_net_kurobako_cef4j_gen_CefPrintSettings_00024NativePeer_N_1GetPageRanges(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_print_settings_t*>(self);
    if (!s) return nullptr;
    size_t _count = s->get_page_ranges_count(s);
    cef_range_t* _arr = _count > 0 ? new cef_range_t[_count]() : nullptr;
    s->get_page_ranges(s, &_count, _arr);
    auto _elemCls = env->FindClass("net/kurobako/cef4j/gen/CefRange");
    auto _elemCtor = env->GetMethodID(_elemCls, "<init>", "(II)V");
    auto _result = env->NewObjectArray(static_cast<jsize>(_count), _elemCls, nullptr);
    for (size_t _i = 0; _i < _count; _i++) {
        auto _elem = env->NewObject(_elemCls, _elemCtor, static_cast<jint>(_arr[_i].from), static_cast<jint>(_arr[_i].to));
        env->SetObjectArrayElement(_result, static_cast<jsize>(_i), _elem);
    }
    delete[] _arr;
    return _result;
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefPrintSettings_00024NativePeer_N_1SetSelectionOnly(JNIEnv* env, jobject obj, jlong self, jboolean selection_only) {
    auto* s = reinterpret_cast<cef_print_settings_t*>(self);
    if (!s) return;
    s->set_selection_only(s, static_cast<bool>(selection_only));
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefPrintSettings_00024NativePeer_N_1IsSelectionOnly(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_print_settings_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_selection_only(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefPrintSettings_00024NativePeer_N_1SetCollate(JNIEnv* env, jobject obj, jlong self, jboolean collate) {
    auto* s = reinterpret_cast<cef_print_settings_t*>(self);
    if (!s) return;
    s->set_collate(s, static_cast<bool>(collate));
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefPrintSettings_00024NativePeer_N_1WillCollate(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_print_settings_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->will_collate(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefPrintSettings_00024NativePeer_N_1SetColorModel(JNIEnv* env, jobject obj, jlong self, jobject model) {
    auto* s = reinterpret_cast<cef_print_settings_t*>(self);
    if (!s) return;
    if (!model) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "model must not be null"); return;}
    s->set_color_model(s, static_cast<cef_color_model_t>(env->GetLongField(model, env->GetFieldID(env->GetObjectClass(model), "value", "J"))));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefPrintSettings_00024NativePeer_N_1GetColorModel(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_print_settings_t*>(self);
    if (!s) return 0;
    auto _r = s->get_color_model(s);
    auto _eCls = env->FindClass("net/kurobako/cef4j/gen/CefColorModel");
    auto _eOf = env->GetStaticMethodID(_eCls, "of", "(J)Lnet/kurobako/cef4j/gen/CefColorModel;");
    return env->CallStaticObjectMethod(_eCls, _eOf, static_cast<jlong>(_r));
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefPrintSettings_00024NativePeer_N_1SetCopies(JNIEnv* env, jobject obj, jlong self, jint copies) {
    auto* s = reinterpret_cast<cef_print_settings_t*>(self);
    if (!s) return;
    s->set_copies(s, copies);
}

extern "C" JNIEXPORT jint JNICALL Java_net_kurobako_cef4j_gen_CefPrintSettings_00024NativePeer_N_1GetCopies(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_print_settings_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->get_copies(s));
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefPrintSettings_00024NativePeer_N_1SetDuplexMode(JNIEnv* env, jobject obj, jlong self, jobject mode) {
    auto* s = reinterpret_cast<cef_print_settings_t*>(self);
    if (!s) return;
    if (!mode) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "mode must not be null"); return;}
    s->set_duplex_mode(s, static_cast<cef_duplex_mode_t>(env->GetLongField(mode, env->GetFieldID(env->GetObjectClass(mode), "value", "J"))));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefPrintSettings_00024NativePeer_N_1GetDuplexMode(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_print_settings_t*>(self);
    if (!s) return 0;
    auto _r = s->get_duplex_mode(s);
    auto _eCls = env->FindClass("net/kurobako/cef4j/gen/CefDuplexMode");
    auto _eOf = env->GetStaticMethodID(_eCls, "of", "(J)Lnet/kurobako/cef4j/gen/CefDuplexMode;");
    return env->CallStaticObjectMethod(_eCls, _eOf, static_cast<jlong>(_r));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefPrintSettings_00024NativePeer_N_1Create(JNIEnv* env, jclass clz) {
    auto _r = cef_print_settings_create();
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefPrintSettings$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}
