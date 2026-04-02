// GENERATED - do not edit.
#include <jni.h>
#include "include/capi/cef_drag_data_capi.h"
#include "include/capi/cef_image_capi.h"
#include "include/capi/cef_stream_capi.h"
#include "jni_util.h"

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefDragData_00024NativePeer_N_1Release(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefDragData_00024NativePeer_N_1Clone(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_drag_data_t*>(self);
    if (!s) return nullptr;
    auto _r = s->clone(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefDragData$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefDragData_00024NativePeer_N_1IsReadOnly(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_drag_data_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_read_only(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefDragData_00024NativePeer_N_1IsLink(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_drag_data_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_link(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefDragData_00024NativePeer_N_1IsFragment(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_drag_data_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_fragment(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefDragData_00024NativePeer_N_1IsFile(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_drag_data_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_file(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefDragData_00024NativePeer_N_1GetLinkUrl(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_drag_data_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_link_url(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefDragData_00024NativePeer_N_1GetLinkTitle(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_drag_data_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_link_title(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefDragData_00024NativePeer_N_1GetLinkMetadata(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_drag_data_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_link_metadata(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefDragData_00024NativePeer_N_1GetFragmentText(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_drag_data_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_fragment_text(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefDragData_00024NativePeer_N_1GetFragmentHtml(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_drag_data_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_fragment_html(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefDragData_00024NativePeer_N_1GetFragmentBaseUrl(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_drag_data_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_fragment_base_url(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefDragData_00024NativePeer_N_1GetFileName(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_drag_data_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_file_name(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jlong JNICALL Java_net_kurobako_cef4j_gen_CefDragData_00024NativePeer_N_1GetFileContents(JNIEnv* env, jobject obj, jlong self, jobject writer) {
    auto* s = reinterpret_cast<cef_drag_data_t*>(self);
    if (!s) return 0;
    cef_stream_writer_t* _writer_ptr = writer ? reinterpret_cast<cef_stream_writer_t*>(env->GetLongField(writer, env->GetFieldID(env->GetObjectClass(writer), "nativePtr", "J"))) : nullptr;
    if (_writer_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_writer_ptr); _b->add_ref(_b);}
    return static_cast<jlong>(s->get_file_contents(s, _writer_ptr));
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefDragData_00024NativePeer_N_1GetFileNames(JNIEnv* env, jobject obj, jlong self, jobject names) {
    auto* s = reinterpret_cast<cef_drag_data_t*>(self);
    if (!s) return JNI_FALSE;
    if (!names) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "names must not be null"); return JNI_FALSE;}
    auto _names_csl = JavaListToCefStringList(env, names);
    auto _r = s->get_file_names(s, _names_csl);
    CefStringListWriteBack(env, _names_csl, names);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefDragData_00024NativePeer_N_1GetFilePaths(JNIEnv* env, jobject obj, jlong self, jobject paths) {
    auto* s = reinterpret_cast<cef_drag_data_t*>(self);
    if (!s) return JNI_FALSE;
    if (!paths) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "paths must not be null"); return JNI_FALSE;}
    auto _paths_csl = JavaListToCefStringList(env, paths);
    auto _r = s->get_file_paths(s, _paths_csl);
    CefStringListWriteBack(env, _paths_csl, paths);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefDragData_00024NativePeer_N_1SetLinkUrl(JNIEnv* env, jobject obj, jlong self, jstring url) {
    auto* s = reinterpret_cast<cef_drag_data_t*>(self);
    if (!s) return;
    auto _url_str = url ? JStringToCefString(env, url) : nullptr;
    s->set_link_url(s, _url_str);
    if (_url_str) cef_string_userfree_free(_url_str);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefDragData_00024NativePeer_N_1SetLinkTitle(JNIEnv* env, jobject obj, jlong self, jstring title) {
    auto* s = reinterpret_cast<cef_drag_data_t*>(self);
    if (!s) return;
    auto _title_str = title ? JStringToCefString(env, title) : nullptr;
    s->set_link_title(s, _title_str);
    if (_title_str) cef_string_userfree_free(_title_str);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefDragData_00024NativePeer_N_1SetLinkMetadata(JNIEnv* env, jobject obj, jlong self, jstring data) {
    auto* s = reinterpret_cast<cef_drag_data_t*>(self);
    if (!s) return;
    auto _data_str = data ? JStringToCefString(env, data) : nullptr;
    s->set_link_metadata(s, _data_str);
    if (_data_str) cef_string_userfree_free(_data_str);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefDragData_00024NativePeer_N_1SetFragmentText(JNIEnv* env, jobject obj, jlong self, jstring text) {
    auto* s = reinterpret_cast<cef_drag_data_t*>(self);
    if (!s) return;
    auto _text_str = text ? JStringToCefString(env, text) : nullptr;
    s->set_fragment_text(s, _text_str);
    if (_text_str) cef_string_userfree_free(_text_str);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefDragData_00024NativePeer_N_1SetFragmentHtml(JNIEnv* env, jobject obj, jlong self, jstring html) {
    auto* s = reinterpret_cast<cef_drag_data_t*>(self);
    if (!s) return;
    auto _html_str = html ? JStringToCefString(env, html) : nullptr;
    s->set_fragment_html(s, _html_str);
    if (_html_str) cef_string_userfree_free(_html_str);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefDragData_00024NativePeer_N_1SetFragmentBaseUrl(JNIEnv* env, jobject obj, jlong self, jstring base_url) {
    auto* s = reinterpret_cast<cef_drag_data_t*>(self);
    if (!s) return;
    auto _base_url_str = base_url ? JStringToCefString(env, base_url) : nullptr;
    s->set_fragment_base_url(s, _base_url_str);
    if (_base_url_str) cef_string_userfree_free(_base_url_str);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefDragData_00024NativePeer_N_1ResetFileContents(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_drag_data_t*>(self);
    if (!s) return;
    s->reset_file_contents(s);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefDragData_00024NativePeer_N_1AddFile(JNIEnv* env, jobject obj, jlong self, jstring path, jstring display_name) {
    auto* s = reinterpret_cast<cef_drag_data_t*>(self);
    if (!s) return;
    auto _path_str = JStringToCefString(env, path);
    auto _display_name_str = display_name ? JStringToCefString(env, display_name) : nullptr;
    s->add_file(s, _path_str, _display_name_str);
    if (_path_str) cef_string_userfree_free(_path_str);
    if (_display_name_str) cef_string_userfree_free(_display_name_str);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefDragData_00024NativePeer_N_1ClearFilenames(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_drag_data_t*>(self);
    if (!s) return;
    s->clear_filenames(s);
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefDragData_00024NativePeer_N_1GetImage(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_drag_data_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_image(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefImage$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefDragData_00024NativePeer_N_1GetImageHotspot(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_drag_data_t*>(self);
    if (!s) return nullptr;
    cef_point_t result = s->get_image_hotspot(s);
    auto cls = env->FindClass("net/kurobako/cef4j/gen/CefPoint");
    auto ctor = env->GetMethodID(cls, "<init>", "(II)V");
    auto _dsResult = env->NewObject(cls, ctor, static_cast<jint>((&result)->x), static_cast<jint>((&result)->y));
    return _dsResult;
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefDragData_00024NativePeer_N_1HasImage(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_drag_data_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->has_image(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefDragData_00024NativePeer_N_1Create(JNIEnv* env, jclass clz) {
    auto _r = cef_drag_data_create();
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefDragData$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}
