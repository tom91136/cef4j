// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
#include <jni.h>
#include "include/capi/cef_dialog_handler_capi.h"
#include "jni_util.h"

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefFileDialogCallback), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefFileDialogCallback), cont0)(JNIEnv* env, jobject obj, jlong self, jobject file_paths) {
    auto* s = reinterpret_cast<cef_file_dialog_callback_t*>(self);
    if (!s) return;
    if (!file_paths) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "filePaths must not be null"); return;}
    auto _file_paths_csl = JavaListToCefStringList(env, file_paths);
    s->cont(s, _file_paths_csl);
    cef_string_list_free(_file_paths_csl);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefFileDialogCallback), cancel0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_file_dialog_callback_t*>(self);
    if (!s) return;
    s->cancel(s);
}
