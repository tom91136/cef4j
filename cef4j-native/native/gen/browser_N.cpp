// GENERATED - do not edit.
#include <jni.h>
#include "include/capi/cef_browser_capi.h"
#include "include/capi/cef_frame_capi.h"
#include "jni_util.h"

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefBrowser_00024NativePeer_N_1Release(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefBrowser_00024NativePeer_N_1IsValid(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_browser_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_valid(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefBrowser_00024NativePeer_N_1GetHost(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_browser_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_host(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefBrowserHost$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefBrowser_00024NativePeer_N_1CanGoBack(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_browser_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->can_go_back(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefBrowser_00024NativePeer_N_1GoBack(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_browser_t*>(self);
    if (!s) return;
    s->go_back(s);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefBrowser_00024NativePeer_N_1CanGoForward(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_browser_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->can_go_forward(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefBrowser_00024NativePeer_N_1GoForward(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_browser_t*>(self);
    if (!s) return;
    s->go_forward(s);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefBrowser_00024NativePeer_N_1IsLoading(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_browser_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_loading(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefBrowser_00024NativePeer_N_1Reload(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_browser_t*>(self);
    if (!s) return;
    s->reload(s);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefBrowser_00024NativePeer_N_1ReloadIgnoreCache(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_browser_t*>(self);
    if (!s) return;
    s->reload_ignore_cache(s);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefBrowser_00024NativePeer_N_1StopLoad(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_browser_t*>(self);
    if (!s) return;
    s->stop_load(s);
}

extern "C" JNIEXPORT jint JNICALL Java_net_kurobako_cef4j_gen_CefBrowser_00024NativePeer_N_1GetIdentifier(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_browser_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->get_identifier(s));
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefBrowser_00024NativePeer_N_1IsSame(JNIEnv* env, jobject obj, jlong self, jobject that) {
    auto* s = reinterpret_cast<cef_browser_t*>(self);
    if (!s) return JNI_FALSE;
    if (!that) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "that must not be null"); return JNI_FALSE;}
    cef_browser_t* _that_ptr = reinterpret_cast<cef_browser_t*>(env->GetLongField(that, env->GetFieldID(env->GetObjectClass(that), "nativePtr", "J")));
    if (_that_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_that_ptr); _b->add_ref(_b);}
    auto _r = s->is_same(s, _that_ptr);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefBrowser_00024NativePeer_N_1IsPopup(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_browser_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_popup(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefBrowser_00024NativePeer_N_1HasDocument(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_browser_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->has_document(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefBrowser_00024NativePeer_N_1GetMainFrame(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_browser_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_main_frame(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefFrame$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefBrowser_00024NativePeer_N_1GetFocusedFrame(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_browser_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_focused_frame(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefFrame$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefBrowser_00024NativePeer_N_1GetFrameByIdentifier(JNIEnv* env, jobject obj, jlong self, jstring identifier) {
    auto* s = reinterpret_cast<cef_browser_t*>(self);
    if (!s) return nullptr;
    if (!identifier) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "identifier must not be null"); return nullptr;}
    auto _identifier_str = JStringToCefString(env, identifier);
    auto _r = s->get_frame_by_identifier(s, _identifier_str);
    if (_identifier_str) cef_string_userfree_free(_identifier_str);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefFrame$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefBrowser_00024NativePeer_N_1GetFrameByName(JNIEnv* env, jobject obj, jlong self, jstring name) {
    auto* s = reinterpret_cast<cef_browser_t*>(self);
    if (!s) return nullptr;
    auto _name_str = name ? JStringToCefString(env, name) : nullptr;
    auto _r = s->get_frame_by_name(s, _name_str);
    if (_name_str) cef_string_userfree_free(_name_str);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefFrame$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jlong JNICALL Java_net_kurobako_cef4j_gen_CefBrowser_00024NativePeer_N_1GetFrameCount(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_browser_t*>(self);
    if (!s) return 0;
    return static_cast<jlong>(s->get_frame_count(s));
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefBrowser_00024NativePeer_N_1GetFrameIdentifiers(JNIEnv* env, jobject obj, jlong self, jobject identifiers) {
    auto* s = reinterpret_cast<cef_browser_t*>(self);
    if (!s) return;
    if (!identifiers) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "identifiers must not be null"); return;}
    size_t identifiers_count = s->get_frame_count(s);
    auto _identifiers_csl = JavaListToCefStringList(env, identifiers);
    s->get_frame_identifiers(s, _identifiers_csl);
    CefStringListWriteBack(env, _identifiers_csl, identifiers);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefBrowser_00024NativePeer_N_1GetFrameNames(JNIEnv* env, jobject obj, jlong self, jobject names) {
    auto* s = reinterpret_cast<cef_browser_t*>(self);
    if (!s) return;
    if (!names) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "names must not be null"); return;}
    auto _names_csl = JavaListToCefStringList(env, names);
    s->get_frame_names(s, _names_csl);
    CefStringListWriteBack(env, _names_csl, names);
}
