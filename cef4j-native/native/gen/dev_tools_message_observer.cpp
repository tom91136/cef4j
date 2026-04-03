// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
#include <jni.h>
#include "include/capi/cef_devtools_message_observer_capi.h"
#include "include/capi/cef_browser_capi.h"
#include "jni_util.h"

#include <atomic>
#include "jni_util.h"

struct JniCefDevToolsMessageObserver: public cef_dev_tools_message_observer_t {
    JavaVM *jvm;
    jobject javaHandler;  // global ref
    std::atomic<int> refCount { 1 };

    JniCefDevToolsMessageObserver(JavaVM *vm, jobject handler) : cef_dev_tools_message_observer_t { }, jvm(vm) {
        javaHandler = handler;
        InitRefCount<JniCefDevToolsMessageObserver, cef_dev_tools_message_observer_t>(
                reinterpret_cast<cef_base_ref_counted_t*>(static_cast<cef_dev_tools_message_observer_t*>(this)));
        on_dev_tools_message = &_on_dev_tools_message;
        on_dev_tools_method_result = &_on_dev_tools_method_result;
        on_dev_tools_event = &_on_dev_tools_event;
        on_dev_tools_agent_attached = &_on_dev_tools_agent_attached;
        on_dev_tools_agent_detached = &_on_dev_tools_agent_detached;
    }

    static int CEF_CALLBACK _on_dev_tools_message(cef_dev_tools_message_observer_t* self, struct _cef_browser_t* browser, const void* message, size_t message_size) {
        auto* h = reinterpret_cast<JniCefDevToolsMessageObserver*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(9) < 0) {return false;}
        cef_browser_t* _p_browser = browser;
        if (_p_browser) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b);}
        auto j_browser_cls = env->FindClass("net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        jobject j_message = (message_size > 0 && message) ? env->NewDirectByteBuffer(const_cast<void*>(static_cast<const void*>(message)), static_cast<jlong>(message_size)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onDevToolsMessage", "(Lnet/kurobako/cef4j/gen/CefBrowser;Ljava/nio/ByteBuffer;)Z");
        if (!mid) {env->PopLocalFrame(nullptr); return false;}
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, j_browser, j_message);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return false;}
        env->PopLocalFrame(nullptr);
        return jResult;
    }

    static void CEF_CALLBACK _on_dev_tools_method_result(cef_dev_tools_message_observer_t* self, struct _cef_browser_t* browser, int message_id, int success, const void* result, size_t result_size) {
        auto* h = reinterpret_cast<JniCefDevToolsMessageObserver*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(9) < 0) {return;}
        cef_browser_t* _p_browser = browser;
        if (_p_browser) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b);}
        auto j_browser_cls = env->FindClass("net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        jobject j_result = (result_size > 0 && result) ? env->NewDirectByteBuffer(const_cast<void*>(static_cast<const void*>(result)), static_cast<jlong>(result_size)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onDevToolsMethodResult", "(Lnet/kurobako/cef4j/gen/CefBrowser;IZLjava/nio/ByteBuffer;)V");
        if (!mid) {env->PopLocalFrame(nullptr); return;}
        env->CallVoidMethod(h->javaHandler, mid, j_browser, static_cast<jint>(message_id), static_cast<jboolean>(success), j_result);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return;}
        env->PopLocalFrame(nullptr);
    }

    static void CEF_CALLBACK _on_dev_tools_event(cef_dev_tools_message_observer_t* self, struct _cef_browser_t* browser, const cef_string_t* method, const void* params, size_t params_size) {
        auto* h = reinterpret_cast<JniCefDevToolsMessageObserver*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(10) < 0) {return;}
        cef_browser_t* _p_browser = browser;
        if (_p_browser) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b);}
        auto j_browser_cls = env->FindClass("net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        auto j_method = CefStringToJString(env, method);
        jobject j_params = (params_size > 0 && params) ? env->NewDirectByteBuffer(const_cast<void*>(static_cast<const void*>(params)), static_cast<jlong>(params_size)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onDevToolsEvent", "(Lnet/kurobako/cef4j/gen/CefBrowser;Ljava/lang/String;Ljava/nio/ByteBuffer;)V");
        if (!mid) {env->PopLocalFrame(nullptr); return;}
        env->CallVoidMethod(h->javaHandler, mid, j_browser, j_method, j_params);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return;}
        env->PopLocalFrame(nullptr);
    }

    static void CEF_CALLBACK _on_dev_tools_agent_attached(cef_dev_tools_message_observer_t* self, struct _cef_browser_t* browser) {
        auto* h = reinterpret_cast<JniCefDevToolsMessageObserver*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) {return;}
        cef_browser_t* _p_browser = browser;
        if (_p_browser) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b);}
        auto j_browser_cls = env->FindClass("net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onDevToolsAgentAttached", "(Lnet/kurobako/cef4j/gen/CefBrowser;)V");
        if (!mid) {env->PopLocalFrame(nullptr); return;}
        env->CallVoidMethod(h->javaHandler, mid, j_browser);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return;}
        env->PopLocalFrame(nullptr);
    }

    static void CEF_CALLBACK _on_dev_tools_agent_detached(cef_dev_tools_message_observer_t* self, struct _cef_browser_t* browser) {
        auto* h = reinterpret_cast<JniCefDevToolsMessageObserver*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) {return;}
        cef_browser_t* _p_browser = browser;
        if (_p_browser) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b);}
        auto j_browser_cls = env->FindClass("net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onDevToolsAgentDetached", "(Lnet/kurobako/cef4j/gen/CefBrowser;)V");
        if (!mid) {env->PopLocalFrame(nullptr); return;}
        env->CallVoidMethod(h->javaHandler, mid, j_browser);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return;}
        env->PopLocalFrame(nullptr);
    }
};

extern "C" cef_dev_tools_message_observer_t* Create_JniCefDevToolsMessageObserver(JNIEnv *env, jobject handler) {
    JavaVM *jvm;
    env->GetJavaVM(&jvm);
    auto globalRef = env->NewGlobalRef(handler);
    return reinterpret_cast<cef_dev_tools_message_observer_t*>(new JniCefDevToolsMessageObserver(jvm, globalRef));
}
