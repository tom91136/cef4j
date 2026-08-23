// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
#include <jni.h>
#include "include/capi/cef_dialog_handler_capi.h"
#include "include/capi/cef_browser_capi.h"
#include "jni_util.h"

#include <atomic>

struct JniCefDialogHandler : public cef_dialog_handler_t {
    JavaVM* jvm;
    jobject javaHandler;  // global ref
    std::atomic<int> refCount{1};

    JniCefDialogHandler(JavaVM* vm, jobject handler) : cef_dialog_handler_t{}, jvm(vm) {
        javaHandler = handler;
        InitRefCount<JniCefDialogHandler, cef_dialog_handler_t>(reinterpret_cast<cef_base_ref_counted_t*>(static_cast<cef_dialog_handler_t*>(this)));
        on_file_dialog = &_on_file_dialog;
    }

    static int CEF_CALLBACK _on_file_dialog(cef_dialog_handler_t* self, struct _cef_browser_t* browser, cef_file_dialog_mode_t mode, const cef_string_t* title, const cef_string_t* default_file_path, cef_string_list_t accept_filters, cef_string_list_t accept_extensions, cef_string_list_t accept_descriptions, struct _cef_file_dialog_callback_t* callback) {
        auto* h = reinterpret_cast<JniCefDialogHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(19) < 0) { return false; }
        cef_browser_t* _p_browser = browser;
        if (_p_browser) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b); }
        auto j_browser_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        auto j_mode_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefFileDialogMode");
        auto j_mode_from = env->GetStaticMethodID(j_mode_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefFileDialogMode;");
        auto j_mode = env->CallStaticObjectMethod(j_mode_cls, j_mode_from, static_cast<jlong>(mode));
        auto j_title = CefStringToJString(env, title);
        auto j_default_file_path = CefStringToJString(env, default_file_path);
        auto j_accept_filters = CefStringListToJavaList(env, accept_filters);
        auto j_accept_extensions = CefStringListToJavaList(env, accept_extensions);
        auto j_accept_descriptions = CefStringListToJavaList(env, accept_descriptions);
        cef_file_dialog_callback_t* _p_callback = callback;
        if (_p_callback) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_callback); _b->add_ref(_b); }
        auto j_callback_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefFileDialogCallback$NativePeer");
        auto j_callback_ctor = env->GetMethodID(j_callback_cls, "<init>", "(J)V");
        auto j_callback = _p_callback ? env->NewObject(j_callback_cls, j_callback_ctor, reinterpret_cast<jlong>(_p_callback)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onFileDialog", "(Lnet/kurobako/cef4j/gen/CefBrowser;Lnet/kurobako/cef4j/gen/CefFileDialogMode;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/List;Lnet/kurobako/cef4j/gen/CefFileDialogCallback;)Z");
        if (!mid) { env->PopLocalFrame(nullptr); return false; }
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, j_browser, j_mode, j_title, j_default_file_path, j_accept_filters, j_accept_extensions, j_accept_descriptions, j_callback);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return false; }
        env->PopLocalFrame(nullptr);
        return jResult;
    }
};

extern "C" cef_dialog_handler_t* Create_JniCefDialogHandler(JNIEnv* env, jobject handler) {
    JavaVM* jvm;
    env->GetJavaVM(&jvm);
    auto globalRef = env->NewGlobalRef(handler);
    return reinterpret_cast<cef_dialog_handler_t*>(new JniCefDialogHandler(jvm, globalRef));
}
