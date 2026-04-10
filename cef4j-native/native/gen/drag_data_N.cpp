// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
#include <jni.h>
#include "include/capi/cef_drag_data_capi.h"
#include "include/capi/cef_image_capi.h"
#include "include/capi/cef_stream_capi.h"
#include "jni_util.h"

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefDragData), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefDragData), cefClone0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_drag_data_t*>(self);
    if (!s) return nullptr;
    auto _r = s->clone(s);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefDragData$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefDragData), isReadOnly0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_drag_data_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_read_only(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefDragData), isLink0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_drag_data_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_link(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefDragData), isFragment0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_drag_data_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_fragment(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefDragData), isFile0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_drag_data_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_file(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefDragData), getLinkUrl0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_drag_data_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_link_url(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefDragData), getLinkTitle0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_drag_data_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_link_title(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefDragData), getLinkMetadata0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_drag_data_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_link_metadata(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefDragData), getFragmentText0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_drag_data_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_fragment_text(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefDragData), getFragmentHtml0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_drag_data_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_fragment_html(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefDragData), getFragmentBaseUrl0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_drag_data_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_fragment_base_url(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefDragData), getFileName0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_drag_data_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_file_name(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jlong, CEF4J_PEER(CefDragData), getFileContents0)(JNIEnv* env, jobject obj, jlong self, jobject writer) {
    auto* s = reinterpret_cast<cef_drag_data_t*>(self);
    if (!s) return 0;
    cef_stream_writer_t* _writer_ptr = writer ? reinterpret_cast<cef_stream_writer_t*>(env->GetLongField(writer, env->GetFieldID(env->GetObjectClass(writer), "nativePtr", "J"))) : nullptr;
    if (_writer_ptr) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_writer_ptr); _b->add_ref(_b); }
    return static_cast<jlong>(s->get_file_contents(s, _writer_ptr));
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefDragData), getFileNames0)(JNIEnv* env, jobject obj, jlong self, jobject names) {
    auto* s = reinterpret_cast<cef_drag_data_t*>(self);
    if (!s) return JNI_FALSE;
    if (!names) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "names must not be null"); return JNI_FALSE; }
    auto _names_csl = JavaListToCefStringList(env, names);
    auto _r = s->get_file_names(s, _names_csl);
    CefStringListWriteBack(env, _names_csl, names);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefDragData), getFilePaths0)(JNIEnv* env, jobject obj, jlong self, jobject paths) {
    auto* s = reinterpret_cast<cef_drag_data_t*>(self);
    if (!s) return JNI_FALSE;
    if (!paths) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "paths must not be null"); return JNI_FALSE; }
    auto _paths_csl = JavaListToCefStringList(env, paths);
    auto _r = s->get_file_paths(s, _paths_csl);
    CefStringListWriteBack(env, _paths_csl, paths);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefDragData), setLinkUrl0)(JNIEnv* env, jobject obj, jlong self, jstring url) {
    auto* s = reinterpret_cast<cef_drag_data_t*>(self);
    if (!s) return;
    auto _url_str = url ? JStringToCefString(env, url) : nullptr;
    s->set_link_url(s, _url_str);
    if (_url_str) cef_string_userfree_free(_url_str);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefDragData), setLinkTitle0)(JNIEnv* env, jobject obj, jlong self, jstring title) {
    auto* s = reinterpret_cast<cef_drag_data_t*>(self);
    if (!s) return;
    auto _title_str = title ? JStringToCefString(env, title) : nullptr;
    s->set_link_title(s, _title_str);
    if (_title_str) cef_string_userfree_free(_title_str);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefDragData), setLinkMetadata0)(JNIEnv* env, jobject obj, jlong self, jstring data) {
    auto* s = reinterpret_cast<cef_drag_data_t*>(self);
    if (!s) return;
    auto _data_str = data ? JStringToCefString(env, data) : nullptr;
    s->set_link_metadata(s, _data_str);
    if (_data_str) cef_string_userfree_free(_data_str);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefDragData), setFragmentText0)(JNIEnv* env, jobject obj, jlong self, jstring text) {
    auto* s = reinterpret_cast<cef_drag_data_t*>(self);
    if (!s) return;
    auto _text_str = text ? JStringToCefString(env, text) : nullptr;
    s->set_fragment_text(s, _text_str);
    if (_text_str) cef_string_userfree_free(_text_str);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefDragData), setFragmentHtml0)(JNIEnv* env, jobject obj, jlong self, jstring html) {
    auto* s = reinterpret_cast<cef_drag_data_t*>(self);
    if (!s) return;
    auto _html_str = html ? JStringToCefString(env, html) : nullptr;
    s->set_fragment_html(s, _html_str);
    if (_html_str) cef_string_userfree_free(_html_str);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefDragData), setFragmentBaseUrl0)(JNIEnv* env, jobject obj, jlong self, jstring base_url) {
    auto* s = reinterpret_cast<cef_drag_data_t*>(self);
    if (!s) return;
    auto _base_url_str = base_url ? JStringToCefString(env, base_url) : nullptr;
    s->set_fragment_base_url(s, _base_url_str);
    if (_base_url_str) cef_string_userfree_free(_base_url_str);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefDragData), resetFileContents0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_drag_data_t*>(self);
    if (!s) return;
    s->reset_file_contents(s);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefDragData), addFile0)(JNIEnv* env, jobject obj, jlong self, jstring path, jstring display_name) {
    auto* s = reinterpret_cast<cef_drag_data_t*>(self);
    if (!s) return;
    auto _path_str = JStringToCefString(env, path);
    auto _display_name_str = display_name ? JStringToCefString(env, display_name) : nullptr;
    s->add_file(s, _path_str, _display_name_str);
    if (_path_str) cef_string_userfree_free(_path_str);
    if (_display_name_str) cef_string_userfree_free(_display_name_str);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefDragData), clearFilenames0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_drag_data_t*>(self);
    if (!s) return;
    s->clear_filenames(s);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefDragData), getImage0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_drag_data_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_image(s);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefImage$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefDragData), getImageHotspot0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_drag_data_t*>(self);
    if (!s) return nullptr;
    cef_point_t result = s->get_image_hotspot(s);
    auto cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefPoint");
    auto ctor = env->GetMethodID(cls, "<init>", "(II)V");
    auto _dsResult = env->NewObject(cls, ctor, static_cast<jint>((&result)->x), static_cast<jint>((&result)->y));
    return _dsResult;
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefDragData), hasImage0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_drag_data_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->has_image(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefDragData), create0)(JNIEnv* env, jclass clz) {
    auto _r = cef_drag_data_create();
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefDragData$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}
