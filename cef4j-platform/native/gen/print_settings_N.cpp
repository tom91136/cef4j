// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
#include <jni.h>
#include "include/capi/cef_print_settings_capi.h"
#include "jni_util.h"

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefPrintSettings), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefPrintSettings), isValid0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_print_settings_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_valid(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefPrintSettings), isReadOnly0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_print_settings_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_read_only(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefPrintSettings), setOrientation0)(JNIEnv* env, jobject obj, jlong self, jboolean landscape) {
    auto* s = reinterpret_cast<cef_print_settings_t*>(self);
    if (!s) return;
    s->set_orientation(s, static_cast<bool>(landscape));
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefPrintSettings), isLandscape0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_print_settings_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_landscape(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefPrintSettings), setPrinterPrintableArea0)(JNIEnv* env, jobject obj, jlong self, jobject physical_size_device_units, jobject printable_area_device_units, jboolean landscape_needs_flip) {
    auto* s = reinterpret_cast<cef_print_settings_t*>(self);
    if (!s) return;
    if (!physical_size_device_units) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "physicalSizeDeviceUnits must not be null"); return; }
    if (!printable_area_device_units) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "printableAreaDeviceUnits must not be null"); return; }
    cef_size_t _physical_size_device_units_val = {};
    auto _physical_size_device_units_c = FindClassCached(env, "net/kurobako/cef4j/gen/CefSize");
    _physical_size_device_units_val.width = static_cast<decltype(_physical_size_device_units_val.width)>(env->GetIntField(physical_size_device_units, env->GetFieldID(_physical_size_device_units_c, "width", "I")));
    _physical_size_device_units_val.height = static_cast<decltype(_physical_size_device_units_val.height)>(env->GetIntField(physical_size_device_units, env->GetFieldID(_physical_size_device_units_c, "height", "I")));
    cef_rect_t _printable_area_device_units_val = {};
    auto _printable_area_device_units_c = FindClassCached(env, "net/kurobako/cef4j/gen/CefRect");
    _printable_area_device_units_val.x = static_cast<decltype(_printable_area_device_units_val.x)>(env->GetIntField(printable_area_device_units, env->GetFieldID(_printable_area_device_units_c, "x", "I")));
    _printable_area_device_units_val.y = static_cast<decltype(_printable_area_device_units_val.y)>(env->GetIntField(printable_area_device_units, env->GetFieldID(_printable_area_device_units_c, "y", "I")));
    _printable_area_device_units_val.width = static_cast<decltype(_printable_area_device_units_val.width)>(env->GetIntField(printable_area_device_units, env->GetFieldID(_printable_area_device_units_c, "width", "I")));
    _printable_area_device_units_val.height = static_cast<decltype(_printable_area_device_units_val.height)>(env->GetIntField(printable_area_device_units, env->GetFieldID(_printable_area_device_units_c, "height", "I")));
    s->set_printer_printable_area(s, &_physical_size_device_units_val, &_printable_area_device_units_val, static_cast<bool>(landscape_needs_flip));
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefPrintSettings), setDeviceName0)(JNIEnv* env, jobject obj, jlong self, jstring name) {
    auto* s = reinterpret_cast<cef_print_settings_t*>(self);
    if (!s) return;
    auto _name_str = name ? JStringToCefString(env, name) : nullptr;
    s->set_device_name(s, _name_str);
    if (_name_str) cef_string_userfree_free(_name_str);
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefPrintSettings), getDeviceName0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_print_settings_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_device_name(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefPrintSettings), setDpi0)(JNIEnv* env, jobject obj, jlong self, jint dpi) {
    auto* s = reinterpret_cast<cef_print_settings_t*>(self);
    if (!s) return;
    s->set_dpi(s, dpi);
}

CEF4J_JNI_EXPORT(jint, CEF4J_PEER(CefPrintSettings), getDpi0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_print_settings_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->get_dpi(s));
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefPrintSettings), setPageRanges0)(JNIEnv* env, jobject obj, jlong self, jlong rangesCount, jobjectArray ranges) {
    auto* s = reinterpret_cast<cef_print_settings_t*>(self);
    if (!s) return;
    if (!ranges) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "ranges must not be null"); return; }
    jsize _ranges_len = ranges ? env->GetArrayLength(ranges) : 0;
    if (static_cast<unsigned long long>(rangesCount) > static_cast<unsigned long long>(_ranges_len)) { env->ThrowNew(FindClassCached(env, "java/lang/IllegalArgumentException"), "ranges count exceeds array length"); return; }
    size_t _ranges_sz = static_cast<size_t>(rangesCount);
    cef_range_t* _ranges_arr = _ranges_sz > 0 ? new cef_range_t[_ranges_sz]() : nullptr;
    { auto _bvac = FindClassCached(env, "net/kurobako/cef4j/gen/CefRange");
    for (size_t _i = 0; _i < _ranges_sz; _i++) {
        auto _elem = env->GetObjectArrayElement(ranges, static_cast<jsize>(_i));
        if (_elem) {
            _ranges_arr[_i].from = static_cast<decltype(_ranges_arr[_i].from)>(env->GetIntField(_elem, env->GetFieldID(_bvac, "from", "I")));
            _ranges_arr[_i].to = static_cast<decltype(_ranges_arr[_i].to)>(env->GetIntField(_elem, env->GetFieldID(_bvac, "to", "I")));
        }
    } }
    s->set_page_ranges(s, rangesCount, _ranges_arr);
    delete[] _ranges_arr;
}

CEF4J_JNI_EXPORT(jlong, CEF4J_PEER(CefPrintSettings), getPageRangesCount0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_print_settings_t*>(self);
    if (!s) return 0;
    return static_cast<jlong>(s->get_page_ranges_count(s));
}

CEF4J_JNI_EXPORT(jobjectArray, CEF4J_PEER(CefPrintSettings), getPageRanges0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_print_settings_t*>(self);
    if (!s) return nullptr;
    size_t _count = s->get_page_ranges_count(s);
    cef_range_t* _arr = _count > 0 ? new cef_range_t[_count]() : nullptr;
    s->get_page_ranges(s, &_count, _arr);
    auto _elemCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefRange");
    auto _elemCtor = env->GetMethodID(_elemCls, "<init>", "(II)V");
    auto _result = env->NewObjectArray(static_cast<jsize>(_count), _elemCls, nullptr);
    for (size_t _i = 0; _i < _count; _i++) {
        auto _elem = env->NewObject(_elemCls, _elemCtor, static_cast<jint>(_arr[_i].from), static_cast<jint>(_arr[_i].to));
        env->SetObjectArrayElement(_result, static_cast<jsize>(_i), _elem);
    }
    delete[] _arr;
    return _result;
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefPrintSettings), setSelectionOnly0)(JNIEnv* env, jobject obj, jlong self, jboolean selection_only) {
    auto* s = reinterpret_cast<cef_print_settings_t*>(self);
    if (!s) return;
    s->set_selection_only(s, static_cast<bool>(selection_only));
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefPrintSettings), isSelectionOnly0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_print_settings_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_selection_only(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefPrintSettings), setCollate0)(JNIEnv* env, jobject obj, jlong self, jboolean collate) {
    auto* s = reinterpret_cast<cef_print_settings_t*>(self);
    if (!s) return;
    s->set_collate(s, static_cast<bool>(collate));
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefPrintSettings), willCollate0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_print_settings_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->will_collate(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefPrintSettings), setColorModel0)(JNIEnv* env, jobject obj, jlong self, jobject model) {
    auto* s = reinterpret_cast<cef_print_settings_t*>(self);
    if (!s) return;
    if (!model) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "model must not be null"); return; }
    s->set_color_model(s, static_cast<cef_color_model_t>(env->GetLongField(model, env->GetFieldID(env->GetObjectClass(model), "value", "J"))));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefPrintSettings), getColorModel0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_print_settings_t*>(self);
    if (!s) return 0;
    auto _r = s->get_color_model(s);
    auto _eCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefColorModel");
    auto _eOf = env->GetStaticMethodID(_eCls, "of", "(J)Lnet/kurobako/cef4j/gen/CefColorModel;");
    return env->CallStaticObjectMethod(_eCls, _eOf, static_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefPrintSettings), setCopies0)(JNIEnv* env, jobject obj, jlong self, jint copies) {
    auto* s = reinterpret_cast<cef_print_settings_t*>(self);
    if (!s) return;
    s->set_copies(s, copies);
}

CEF4J_JNI_EXPORT(jint, CEF4J_PEER(CefPrintSettings), getCopies0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_print_settings_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->get_copies(s));
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefPrintSettings), setDuplexMode0)(JNIEnv* env, jobject obj, jlong self, jobject mode) {
    auto* s = reinterpret_cast<cef_print_settings_t*>(self);
    if (!s) return;
    if (!mode) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "mode must not be null"); return; }
    s->set_duplex_mode(s, static_cast<cef_duplex_mode_t>(env->GetLongField(mode, env->GetFieldID(env->GetObjectClass(mode), "value", "J"))));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefPrintSettings), getDuplexMode0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_print_settings_t*>(self);
    if (!s) return 0;
    auto _r = s->get_duplex_mode(s);
    auto _eCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefDuplexMode");
    auto _eOf = env->GetStaticMethodID(_eCls, "of", "(J)Lnet/kurobako/cef4j/gen/CefDuplexMode;");
    return env->CallStaticObjectMethod(_eCls, _eOf, static_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefPrintSettings), create0)(JNIEnv* env, jclass clz) {
    auto _r = cef_print_settings_create();
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefPrintSettings$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}
