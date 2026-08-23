// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
#include <jni.h>
#include "include/capi/cef_media_router_capi.h"
#include "jni_util.h"

#include <atomic>

struct JniCefMediaRouteCreateCallback : public cef_media_route_create_callback_t {
    JavaVM* jvm;
    jobject javaHandler;  // global ref
    std::atomic<int> refCount{1};

    JniCefMediaRouteCreateCallback(JavaVM* vm, jobject handler) : cef_media_route_create_callback_t{}, jvm(vm) {
        javaHandler = handler;
        InitRefCount<JniCefMediaRouteCreateCallback, cef_media_route_create_callback_t>(reinterpret_cast<cef_base_ref_counted_t*>(static_cast<cef_media_route_create_callback_t*>(this)));
        on_media_route_create_finished = &_on_media_route_create_finished;
    }

    static void CEF_CALLBACK _on_media_route_create_finished(cef_media_route_create_callback_t* self, cef_media_route_create_result_t result, const cef_string_t* error, struct _cef_media_route_t* route) {
        auto* h = reinterpret_cast<JniCefMediaRouteCreateCallback*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(12) < 0) { return; }
        auto j_result_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefMediaRouteCreateResult");
        auto j_result_from = env->GetStaticMethodID(j_result_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefMediaRouteCreateResult;");
        auto j_result = env->CallStaticObjectMethod(j_result_cls, j_result_from, static_cast<jlong>(result));
        auto j_error = CefStringToJString(env, error);
        cef_media_route_t* _p_route = route;
        if (_p_route) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_route); _b->add_ref(_b); }
        auto j_route_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefMediaRoute$NativePeer");
        auto j_route_ctor = env->GetMethodID(j_route_cls, "<init>", "(J)V");
        auto j_route = _p_route ? env->NewObject(j_route_cls, j_route_ctor, reinterpret_cast<jlong>(_p_route)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onMediaRouteCreateFinished", "(Lnet/kurobako/cef4j/gen/CefMediaRouteCreateResult;Ljava/lang/String;Lnet/kurobako/cef4j/gen/CefMediaRoute;)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, j_result, j_error, j_route);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        env->PopLocalFrame(nullptr);
    }
};

extern "C" cef_media_route_create_callback_t* Create_JniCefMediaRouteCreateCallback(JNIEnv* env, jobject handler) {
    JavaVM* jvm;
    env->GetJavaVM(&jvm);
    auto globalRef = env->NewGlobalRef(handler);
    return reinterpret_cast<cef_media_route_create_callback_t*>(new JniCefMediaRouteCreateCallback(jvm, globalRef));
}
