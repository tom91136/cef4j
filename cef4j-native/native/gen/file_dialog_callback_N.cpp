// GENERATED - do not edit.
#include <jni.h>
#include "include/capi/cef_dialog_handler_capi.h"
#include "jni_util.h"

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefFileDialogCallback_00024NativePeer_N_1Release(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefFileDialogCallback_00024NativePeer_N_1Cont(JNIEnv* env, jobject obj, jlong self, jobject file_paths) {
    auto* s = reinterpret_cast<cef_file_dialog_callback_t*>(self);
    if (!s) return;
    if (!file_paths) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "filePaths must not be null"); return;}
    auto _file_paths_csl = JavaListToCefStringList(env, file_paths);
    s->cont(s, _file_paths_csl);
    cef_string_list_free(_file_paths_csl);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefFileDialogCallback_00024NativePeer_N_1Cancel(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_file_dialog_callback_t*>(self);
    if (!s) return;
    s->cancel(s);
}
