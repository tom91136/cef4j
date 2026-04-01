// GENERATED - do not edit.
#include <jni.h>
#include "include/capi/cef_auth_callback_capi.h"
#include "jni_util.h"

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefAuthCallback_00024NativePeer_N_1Release(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefAuthCallback_00024NativePeer_N_1Cont(JNIEnv* env, jobject obj, jlong self, jstring username, jstring password) {
    auto* s = reinterpret_cast<cef_auth_callback_t*>(self);
    if (!s) return;
    if (!username) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "username must not be null"); return;}
    if (!password) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "password must not be null"); return;}
    auto _username_str = JStringToCefString(env, username);
    auto _password_str = JStringToCefString(env, password);
    s->cont(s, _username_str, _password_str);
    if (_username_str) cef_string_userfree_free(_username_str);
    if (_password_str) cef_string_userfree_free(_password_str);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefAuthCallback_00024NativePeer_N_1Cancel(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_auth_callback_t*>(self);
    if (!s) return;
    s->cancel(s);
}
