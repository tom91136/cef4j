// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
#include <jni.h>
#include "include/capi/views/cef_panel_delegate_capi.h"
#include "jni_util.h"

#include <atomic>
#include "jni_util.h"

struct JniCefPanelDelegate : public cef_panel_delegate_t {
    JavaVM* jvm;
    jobject javaHandler;  // global ref
    std::atomic<int> refCount{1};

    JniCefPanelDelegate(JavaVM* vm, jobject handler) : cef_panel_delegate_t{}, jvm(vm) {
        javaHandler = handler;
        InitRefCount<JniCefPanelDelegate, cef_panel_delegate_t>(reinterpret_cast<cef_base_ref_counted_t*>(static_cast<cef_panel_delegate_t*>(this)));
    }


};

extern "C" cef_panel_delegate_t* Create_JniCefPanelDelegate(JNIEnv* env, jobject handler) {
    JavaVM* jvm;
    env->GetJavaVM(&jvm);
    auto globalRef = env->NewGlobalRef(handler);
    return reinterpret_cast<cef_panel_delegate_t*>(new JniCefPanelDelegate(jvm, globalRef));
}
