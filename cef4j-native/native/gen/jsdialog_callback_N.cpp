// GENERATED - do not edit.
#include <jni.h>
#include "include/capi/cef_jsdialog_handler_capi.h"
#include "jni_util.h"

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefJsDialogCallback_00024NativePeer_N_1Release(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefJsDialogCallback_00024NativePeer_N_1Cont(JNIEnv* env, jobject obj, jlong self, jint success, jstring user_input) {
    auto* s = reinterpret_cast<cef_jsdialog_callback_t*>(self);
    if (!s) return;
    auto _user_input_str = JStringToCefString(env, user_input);
    s->cont(s, success, _user_input_str);
    if (_user_input_str) cef_string_userfree_free(_user_input_str);
}
