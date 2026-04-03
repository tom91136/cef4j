// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
#include <jni.h>
#include "include/capi/cef_render_process_handler_capi.h"
#include "include/capi/cef_browser_capi.h"
#include "include/capi/cef_dom_capi.h"
#include "include/capi/cef_frame_capi.h"
#include "include/capi/cef_load_handler_capi.h"
#include "include/capi/cef_process_message_capi.h"
#include "include/capi/cef_v8_capi.h"
#include "include/capi/cef_values_capi.h"
#include "jni_util.h"

#include <atomic>
#include "jni_util.h"

extern "C" cef_load_handler_t* Create_JniCefLoadHandler(JNIEnv *env, jobject handler);

struct JniCefRenderProcessHandler: public cef_render_process_handler_t {
    JavaVM *jvm;
    jobject javaHandler;  // global ref
    std::atomic<int> refCount { 1 };

    JniCefRenderProcessHandler(JavaVM *vm, jobject handler) : cef_render_process_handler_t { }, jvm(vm) {
        javaHandler = handler;
        InitRefCount<JniCefRenderProcessHandler, cef_render_process_handler_t> (&base);
        on_web_kit_initialized = &_on_web_kit_initialized;
        on_browser_created = &_on_browser_created;
        on_browser_destroyed = &_on_browser_destroyed;
        get_load_handler = &_get_load_handler;
        on_context_created = &_on_context_created;
        on_context_released = &_on_context_released;
        on_uncaught_exception = &_on_uncaught_exception;
        on_focused_node_changed = &_on_focused_node_changed;
        on_process_message_received = &_on_process_message_received;
    }

    static void CEF_CALLBACK _on_web_kit_initialized(cef_render_process_handler_t* self) {
        auto* h = reinterpret_cast<JniCefRenderProcessHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(5) < 0) {return;}
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onWebKitInitialized", "()V");
        if (!mid) {env->PopLocalFrame(nullptr); return;}
        env->CallVoidMethod(h->javaHandler, mid);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return;}
        env->PopLocalFrame(nullptr);
    }

    static void CEF_CALLBACK _on_browser_created(cef_render_process_handler_t* self, struct _cef_browser_t* browser, struct _cef_dictionary_value_t* extra_info) {
        auto* h = reinterpret_cast<JniCefRenderProcessHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(11) < 0) {return;}
        cef_browser_t* _p_browser = browser;
        if (_p_browser) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b);}
        auto j_browser_cls = env->FindClass("net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        cef_dictionary_value_t* _p_extra_info = extra_info;
        if (_p_extra_info) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_extra_info); _b->add_ref(_b);}
        auto j_extra_info_cls = env->FindClass("net/kurobako/cef4j/gen/CefDictionaryValue$NativePeer");
        auto j_extra_info_ctor = env->GetMethodID(j_extra_info_cls, "<init>", "(J)V");
        auto j_extra_info = _p_extra_info ? env->NewObject(j_extra_info_cls, j_extra_info_ctor, reinterpret_cast<jlong>(_p_extra_info)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onBrowserCreated", "(Lnet/kurobako/cef4j/gen/CefBrowser;Lnet/kurobako/cef4j/gen/CefDictionaryValue;)V");
        if (!mid) {env->PopLocalFrame(nullptr); return;}
        env->CallVoidMethod(h->javaHandler, mid, j_browser, j_extra_info);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return;}
        env->PopLocalFrame(nullptr);
    }

    static void CEF_CALLBACK _on_browser_destroyed(cef_render_process_handler_t* self, struct _cef_browser_t* browser) {
        auto* h = reinterpret_cast<JniCefRenderProcessHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) {return;}
        cef_browser_t* _p_browser = browser;
        if (_p_browser) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b);}
        auto j_browser_cls = env->FindClass("net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onBrowserDestroyed", "(Lnet/kurobako/cef4j/gen/CefBrowser;)V");
        if (!mid) {env->PopLocalFrame(nullptr); return;}
        env->CallVoidMethod(h->javaHandler, mid, j_browser);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return;}
        env->PopLocalFrame(nullptr);
    }

    static cef_load_handler_t* CEF_CALLBACK _get_load_handler(cef_render_process_handler_t* self) {
        auto* h = reinterpret_cast<JniCefRenderProcessHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(9) < 0) {return nullptr;}
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "getLoadHandler", "()Ljava/util/Optional;");
        if (!mid) {env->PopLocalFrame(nullptr); return nullptr;}
        auto jResult = (jobject)env->CallObjectMethod(h->javaHandler, mid);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return nullptr;}
        cef_load_handler_t* nativeResult = nullptr;
        if (jResult) {
            auto _optCls = env->FindClass("java/util/Optional");
            auto _isPresentMid = env->GetMethodID(_optCls, "isPresent", "()Z");
            if (env->CallBooleanMethod(jResult, _isPresentMid) == JNI_TRUE) {
                auto _getMid = env->GetMethodID(_optCls, "get", "()Ljava/lang/Object;");
                auto _handlerObj = env->CallObjectMethod(jResult, _getMid);
                nativeResult = Create_JniCefLoadHandler(env, _handlerObj);
            }
        }
        env->PopLocalFrame(nullptr);
        return nativeResult;
    }

    static void CEF_CALLBACK _on_context_created(cef_render_process_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, struct _cef_v8_context_t* context) {
        auto* h = reinterpret_cast<JniCefRenderProcessHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(14) < 0) {return;}
        cef_browser_t* _p_browser = browser;
        if (_p_browser) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b);}
        auto j_browser_cls = env->FindClass("net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        cef_frame_t* _p_frame = frame;
        if (_p_frame) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_frame); _b->add_ref(_b);}
        auto j_frame_cls = env->FindClass("net/kurobako/cef4j/gen/CefFrame$NativePeer");
        auto j_frame_ctor = env->GetMethodID(j_frame_cls, "<init>", "(J)V");
        auto j_frame = _p_frame ? env->NewObject(j_frame_cls, j_frame_ctor, reinterpret_cast<jlong>(_p_frame)) : nullptr;
        cef_v8_context_t* _p_context = context;
        if (_p_context) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_context); _b->add_ref(_b);}
        auto j_context_cls = env->FindClass("net/kurobako/cef4j/gen/CefV8Context$NativePeer");
        auto j_context_ctor = env->GetMethodID(j_context_cls, "<init>", "(J)V");
        auto j_context = _p_context ? env->NewObject(j_context_cls, j_context_ctor, reinterpret_cast<jlong>(_p_context)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onContextCreated", "(Lnet/kurobako/cef4j/gen/CefBrowser;Lnet/kurobako/cef4j/gen/CefFrame;Lnet/kurobako/cef4j/gen/CefV8Context;)V");
        if (!mid) {env->PopLocalFrame(nullptr); return;}
        env->CallVoidMethod(h->javaHandler, mid, j_browser, j_frame, j_context);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return;}
        env->PopLocalFrame(nullptr);
    }

    static void CEF_CALLBACK _on_context_released(cef_render_process_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, struct _cef_v8_context_t* context) {
        auto* h = reinterpret_cast<JniCefRenderProcessHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(14) < 0) {return;}
        cef_browser_t* _p_browser = browser;
        if (_p_browser) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b);}
        auto j_browser_cls = env->FindClass("net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        cef_frame_t* _p_frame = frame;
        if (_p_frame) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_frame); _b->add_ref(_b);}
        auto j_frame_cls = env->FindClass("net/kurobako/cef4j/gen/CefFrame$NativePeer");
        auto j_frame_ctor = env->GetMethodID(j_frame_cls, "<init>", "(J)V");
        auto j_frame = _p_frame ? env->NewObject(j_frame_cls, j_frame_ctor, reinterpret_cast<jlong>(_p_frame)) : nullptr;
        cef_v8_context_t* _p_context = context;
        if (_p_context) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_context); _b->add_ref(_b);}
        auto j_context_cls = env->FindClass("net/kurobako/cef4j/gen/CefV8Context$NativePeer");
        auto j_context_ctor = env->GetMethodID(j_context_cls, "<init>", "(J)V");
        auto j_context = _p_context ? env->NewObject(j_context_cls, j_context_ctor, reinterpret_cast<jlong>(_p_context)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onContextReleased", "(Lnet/kurobako/cef4j/gen/CefBrowser;Lnet/kurobako/cef4j/gen/CefFrame;Lnet/kurobako/cef4j/gen/CefV8Context;)V");
        if (!mid) {env->PopLocalFrame(nullptr); return;}
        env->CallVoidMethod(h->javaHandler, mid, j_browser, j_frame, j_context);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return;}
        env->PopLocalFrame(nullptr);
    }

    static void CEF_CALLBACK _on_uncaught_exception(cef_render_process_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, struct _cef_v8_context_t* context, struct _cef_v8_exception_t* exception, struct _cef_v8_stack_trace_t* stackTrace) {
        auto* h = reinterpret_cast<JniCefRenderProcessHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(20) < 0) {return;}
        cef_browser_t* _p_browser = browser;
        if (_p_browser) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b);}
        auto j_browser_cls = env->FindClass("net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        cef_frame_t* _p_frame = frame;
        if (_p_frame) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_frame); _b->add_ref(_b);}
        auto j_frame_cls = env->FindClass("net/kurobako/cef4j/gen/CefFrame$NativePeer");
        auto j_frame_ctor = env->GetMethodID(j_frame_cls, "<init>", "(J)V");
        auto j_frame = _p_frame ? env->NewObject(j_frame_cls, j_frame_ctor, reinterpret_cast<jlong>(_p_frame)) : nullptr;
        cef_v8_context_t* _p_context = context;
        if (_p_context) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_context); _b->add_ref(_b);}
        auto j_context_cls = env->FindClass("net/kurobako/cef4j/gen/CefV8Context$NativePeer");
        auto j_context_ctor = env->GetMethodID(j_context_cls, "<init>", "(J)V");
        auto j_context = _p_context ? env->NewObject(j_context_cls, j_context_ctor, reinterpret_cast<jlong>(_p_context)) : nullptr;
        cef_v8_exception_t* _p_exception = exception;
        if (_p_exception) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_exception); _b->add_ref(_b);}
        auto j_exception_cls = env->FindClass("net/kurobako/cef4j/gen/CefV8Exception$NativePeer");
        auto j_exception_ctor = env->GetMethodID(j_exception_cls, "<init>", "(J)V");
        auto j_exception = _p_exception ? env->NewObject(j_exception_cls, j_exception_ctor, reinterpret_cast<jlong>(_p_exception)) : nullptr;
        cef_v8_stack_trace_t* _p_stackTrace = stackTrace;
        if (_p_stackTrace) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_stackTrace); _b->add_ref(_b);}
        auto j_stackTrace_cls = env->FindClass("net/kurobako/cef4j/gen/CefV8StackTrace$NativePeer");
        auto j_stackTrace_ctor = env->GetMethodID(j_stackTrace_cls, "<init>", "(J)V");
        auto j_stackTrace = _p_stackTrace ? env->NewObject(j_stackTrace_cls, j_stackTrace_ctor, reinterpret_cast<jlong>(_p_stackTrace)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onUncaughtException", "(Lnet/kurobako/cef4j/gen/CefBrowser;Lnet/kurobako/cef4j/gen/CefFrame;Lnet/kurobako/cef4j/gen/CefV8Context;Lnet/kurobako/cef4j/gen/CefV8Exception;Lnet/kurobako/cef4j/gen/CefV8StackTrace;)V");
        if (!mid) {env->PopLocalFrame(nullptr); return;}
        env->CallVoidMethod(h->javaHandler, mid, j_browser, j_frame, j_context, j_exception, j_stackTrace);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return;}
        env->PopLocalFrame(nullptr);
    }

    static void CEF_CALLBACK _on_focused_node_changed(cef_render_process_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, struct _cef_domnode_t* node) {
        auto* h = reinterpret_cast<JniCefRenderProcessHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(14) < 0) {return;}
        cef_browser_t* _p_browser = browser;
        if (_p_browser) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b);}
        auto j_browser_cls = env->FindClass("net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        cef_frame_t* _p_frame = frame;
        if (_p_frame) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_frame); _b->add_ref(_b);}
        auto j_frame_cls = env->FindClass("net/kurobako/cef4j/gen/CefFrame$NativePeer");
        auto j_frame_ctor = env->GetMethodID(j_frame_cls, "<init>", "(J)V");
        auto j_frame = _p_frame ? env->NewObject(j_frame_cls, j_frame_ctor, reinterpret_cast<jlong>(_p_frame)) : nullptr;
        cef_domnode_t* _p_node = node;
        if (_p_node) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_node); _b->add_ref(_b);}
        auto j_node_cls = env->FindClass("net/kurobako/cef4j/gen/CefDomNode$NativePeer");
        auto j_node_ctor = env->GetMethodID(j_node_cls, "<init>", "(J)V");
        auto j_node = _p_node ? env->NewObject(j_node_cls, j_node_ctor, reinterpret_cast<jlong>(_p_node)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onFocusedNodeChanged", "(Lnet/kurobako/cef4j/gen/CefBrowser;Lnet/kurobako/cef4j/gen/CefFrame;Lnet/kurobako/cef4j/gen/CefDomNode;)V");
        if (!mid) {env->PopLocalFrame(nullptr); return;}
        env->CallVoidMethod(h->javaHandler, mid, j_browser, j_frame, j_node);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return;}
        env->PopLocalFrame(nullptr);
    }

    static int CEF_CALLBACK _on_process_message_received(cef_render_process_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, cef_process_id_t source_process, struct _cef_process_message_t* message) {
        auto* h = reinterpret_cast<JniCefRenderProcessHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(17) < 0) {return false;}
        cef_browser_t* _p_browser = browser;
        if (_p_browser) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b);}
        auto j_browser_cls = env->FindClass("net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        cef_frame_t* _p_frame = frame;
        if (_p_frame) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_frame); _b->add_ref(_b);}
        auto j_frame_cls = env->FindClass("net/kurobako/cef4j/gen/CefFrame$NativePeer");
        auto j_frame_ctor = env->GetMethodID(j_frame_cls, "<init>", "(J)V");
        auto j_frame = _p_frame ? env->NewObject(j_frame_cls, j_frame_ctor, reinterpret_cast<jlong>(_p_frame)) : nullptr;
        auto j_source_process_cls = env->FindClass("net/kurobako/cef4j/gen/CefProcessId");
        auto j_source_process_from = env->GetStaticMethodID(j_source_process_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefProcessId;");
        auto j_source_process = env->CallStaticObjectMethod(j_source_process_cls, j_source_process_from, static_cast<jlong>(source_process));
        cef_process_message_t* _p_message = message;
        if (_p_message) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_message); _b->add_ref(_b);}
        auto j_message_cls = env->FindClass("net/kurobako/cef4j/gen/CefProcessMessage$NativePeer");
        auto j_message_ctor = env->GetMethodID(j_message_cls, "<init>", "(J)V");
        auto j_message = _p_message ? env->NewObject(j_message_cls, j_message_ctor, reinterpret_cast<jlong>(_p_message)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onProcessMessageReceived", "(Lnet/kurobako/cef4j/gen/CefBrowser;Lnet/kurobako/cef4j/gen/CefFrame;Lnet/kurobako/cef4j/gen/CefProcessId;Lnet/kurobako/cef4j/gen/CefProcessMessage;)Z");
        if (!mid) {env->PopLocalFrame(nullptr); return false;}
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, j_browser, j_frame, j_source_process, j_message);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return false;}
        env->PopLocalFrame(nullptr);
        return jResult;
    }
};

extern "C" cef_render_process_handler_t* Create_JniCefRenderProcessHandler(JNIEnv *env, jobject handler) {
    JavaVM *jvm;
    env->GetJavaVM(&jvm);
    auto globalRef = env->NewGlobalRef(handler);
    return reinterpret_cast<cef_render_process_handler_t*>(new JniCefRenderProcessHandler(jvm, globalRef));
}
