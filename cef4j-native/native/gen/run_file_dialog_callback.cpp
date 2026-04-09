// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
#include <jni.h>
#include "include/capi/cef_browser_capi.h"
#include "jni_util.h"

#include <atomic>
#include "jni_util.h"

struct JniCefRunFileDialogCallback : public cef_run_file_dialog_callback_t {
    JavaVM* jvm;
    jobject javaHandler;  // global ref
    std::atomic<int> refCount{1};

    JniCefRunFileDialogCallback(JavaVM* vm, jobject handler) : cef_run_file_dialog_callback_t{}, jvm(vm) {
        javaHandler = handler;
        InitRefCount<JniCefRunFileDialogCallback, cef_run_file_dialog_callback_t>(reinterpret_cast<cef_base_ref_counted_t*>(static_cast<cef_run_file_dialog_callback_t*>(this)));
        on_file_dialog_dismissed = &_on_file_dialog_dismissed;
    }

    static void CEF_CALLBACK _on_file_dialog_dismissed(cef_run_file_dialog_callback_t* self, cef_string_list_t file_paths) {
        auto* h = reinterpret_cast<JniCefRunFileDialogCallback*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(6) < 0) { return; }
        auto j_file_paths = CefStringListToJavaList(env, file_paths);
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onFileDialogDismissed", "(Ljava/util/List;)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, j_file_paths);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        env->PopLocalFrame(nullptr);
    }
};

extern "C" cef_run_file_dialog_callback_t* Create_JniCefRunFileDialogCallback(JNIEnv* env, jobject handler) {
    JavaVM* jvm;
    env->GetJavaVM(&jvm);
    auto globalRef = env->NewGlobalRef(handler);
    return reinterpret_cast<cef_run_file_dialog_callback_t*>(new JniCefRunFileDialogCallback(jvm, globalRef));
}
