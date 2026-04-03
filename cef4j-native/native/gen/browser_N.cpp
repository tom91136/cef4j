// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
#include <jni.h>
#include "include/capi/cef_browser_capi.h"
#include "include/capi/cef_frame_capi.h"
#include "jni_util.h"

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefBrowser), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefBrowser), isValid0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_browser_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_valid(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefBrowser), getHost0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_browser_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_host(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefBrowserHost$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefBrowser), canGoBack0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_browser_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->can_go_back(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefBrowser), goBack0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_browser_t*>(self);
    if (!s) return;
    s->go_back(s);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefBrowser), canGoForward0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_browser_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->can_go_forward(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefBrowser), goForward0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_browser_t*>(self);
    if (!s) return;
    s->go_forward(s);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefBrowser), isLoading0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_browser_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_loading(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefBrowser), reload0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_browser_t*>(self);
    if (!s) return;
    s->reload(s);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefBrowser), reloadIgnoreCache0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_browser_t*>(self);
    if (!s) return;
    s->reload_ignore_cache(s);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefBrowser), stopLoad0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_browser_t*>(self);
    if (!s) return;
    s->stop_load(s);
}

CEF4J_JNI_EXPORT(jint, CEF4J_PEER(CefBrowser), getIdentifier0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_browser_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->get_identifier(s));
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefBrowser), isSame0)(JNIEnv* env, jobject obj, jlong self, jobject that) {
    auto* s = reinterpret_cast<cef_browser_t*>(self);
    if (!s) return JNI_FALSE;
    cef_browser_t* _that_ptr = that ? reinterpret_cast<cef_browser_t*>(env->GetLongField(that, env->GetFieldID(env->GetObjectClass(that), "nativePtr", "J"))) : nullptr;
    if (_that_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_that_ptr); _b->add_ref(_b);}
    auto _r = s->is_same(s, _that_ptr);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefBrowser), isPopup0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_browser_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_popup(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefBrowser), hasDocument0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_browser_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->has_document(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefBrowser), getMainFrame0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_browser_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_main_frame(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefFrame$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefBrowser), getFocusedFrame0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_browser_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_focused_frame(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefFrame$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefBrowser), getFrameByIdentifier0)(JNIEnv* env, jobject obj, jlong self, jstring identifier) {
    auto* s = reinterpret_cast<cef_browser_t*>(self);
    if (!s) return nullptr;
    auto _identifier_str = JStringToCefString(env, identifier);
    auto _r = s->get_frame_by_identifier(s, _identifier_str);
    if (_identifier_str) cef_string_userfree_free(_identifier_str);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefFrame$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefBrowser), getFrameByName0)(JNIEnv* env, jobject obj, jlong self, jstring name) {
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

CEF4J_JNI_EXPORT(jlong, CEF4J_PEER(CefBrowser), getFrameCount0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_browser_t*>(self);
    if (!s) return 0;
    return static_cast<jlong>(s->get_frame_count(s));
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefBrowser), getFrameIdentifiers0)(JNIEnv* env, jobject obj, jlong self, jobject identifiers) {
    auto* s = reinterpret_cast<cef_browser_t*>(self);
    if (!s) return;
    if (!identifiers) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "identifiers must not be null"); return;}
    auto _identifiers_csl = JavaListToCefStringList(env, identifiers);
    s->get_frame_identifiers(s, _identifiers_csl);
    CefStringListWriteBack(env, _identifiers_csl, identifiers);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefBrowser), getFrameNames0)(JNIEnv* env, jobject obj, jlong self, jobject names) {
    auto* s = reinterpret_cast<cef_browser_t*>(self);
    if (!s) return;
    if (!names) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "names must not be null"); return;}
    auto _names_csl = JavaListToCefStringList(env, names);
    s->get_frame_names(s, _names_csl);
    CefStringListWriteBack(env, _names_csl, names);
}
