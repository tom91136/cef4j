// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
#include <jni.h>
#include "include/capi/cef_media_router_capi.h"
#include "jni_util.h"

#include <atomic>

struct JniCefMediaObserver : public cef_media_observer_t {
    JavaVM* jvm;
    jobject javaHandler;  // global ref
    std::atomic<int> refCount{1};

    JniCefMediaObserver(JavaVM* vm, jobject handler) : cef_media_observer_t{}, jvm(vm) {
        javaHandler = handler;
        InitRefCount<JniCefMediaObserver, cef_media_observer_t>(reinterpret_cast<cef_base_ref_counted_t*>(static_cast<cef_media_observer_t*>(this)));
        on_sinks = &_on_sinks;
        on_routes = &_on_routes;
        on_route_state_changed = &_on_route_state_changed;
        on_route_message_received = &_on_route_message_received;
    }

    static void CEF_CALLBACK _on_sinks(cef_media_observer_t* self, size_t sinksCount, struct _cef_media_sink_t* const* sinks) {
        auto* h = reinterpret_cast<JniCefMediaObserver*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(24) < 0) { return; }
        auto j_sinks_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefMediaSink$NativePeer");
        auto j_sinks_ctor = env->GetMethodID(j_sinks_cls, "<init>", "(J)V");
        jsize j_sinks_len = static_cast<jsize>(sinksCount);
        auto j_sinks = env->NewObjectArray(j_sinks_len, j_sinks_cls, nullptr);
        for (jsize _i = 0; _i < j_sinks_len; _i++) {
            cef_media_sink_t* _elem = sinks[_i];
            if (_elem) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_elem); _b->add_ref(_b); }
            auto _jelem = _elem ? env->NewObject(j_sinks_cls, j_sinks_ctor, reinterpret_cast<jlong>(_elem)) : nullptr;
            env->SetObjectArrayElement(j_sinks, _i, _jelem);
        }
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onSinks", "(J[Lnet/kurobako/cef4j/gen/CefMediaSink;)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, static_cast<jlong>(sinksCount), j_sinks);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        env->PopLocalFrame(nullptr);
    }

    static void CEF_CALLBACK _on_routes(cef_media_observer_t* self, size_t routesCount, struct _cef_media_route_t* const* routes) {
        auto* h = reinterpret_cast<JniCefMediaObserver*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(24) < 0) { return; }
        auto j_routes_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefMediaRoute$NativePeer");
        auto j_routes_ctor = env->GetMethodID(j_routes_cls, "<init>", "(J)V");
        jsize j_routes_len = static_cast<jsize>(routesCount);
        auto j_routes = env->NewObjectArray(j_routes_len, j_routes_cls, nullptr);
        for (jsize _i = 0; _i < j_routes_len; _i++) {
            cef_media_route_t* _elem = routes[_i];
            if (_elem) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_elem); _b->add_ref(_b); }
            auto _jelem = _elem ? env->NewObject(j_routes_cls, j_routes_ctor, reinterpret_cast<jlong>(_elem)) : nullptr;
            env->SetObjectArrayElement(j_routes, _i, _jelem);
        }
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onRoutes", "(J[Lnet/kurobako/cef4j/gen/CefMediaRoute;)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, static_cast<jlong>(routesCount), j_routes);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        env->PopLocalFrame(nullptr);
    }

    static void CEF_CALLBACK _on_route_state_changed(cef_media_observer_t* self, struct _cef_media_route_t* route, cef_media_route_connection_state_t state) {
        auto* h = reinterpret_cast<JniCefMediaObserver*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(11) < 0) { return; }
        cef_media_route_t* _p_route = route;
        if (_p_route) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_route); _b->add_ref(_b); }
        auto j_route_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefMediaRoute$NativePeer");
        auto j_route_ctor = env->GetMethodID(j_route_cls, "<init>", "(J)V");
        auto j_route = _p_route ? env->NewObject(j_route_cls, j_route_ctor, reinterpret_cast<jlong>(_p_route)) : nullptr;
        auto j_state_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefMediaRouteConnectionState");
        auto j_state_from = env->GetStaticMethodID(j_state_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefMediaRouteConnectionState;");
        auto j_state = env->CallStaticObjectMethod(j_state_cls, j_state_from, static_cast<jlong>(state));
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onRouteStateChanged", "(Lnet/kurobako/cef4j/gen/CefMediaRoute;Lnet/kurobako/cef4j/gen/CefMediaRouteConnectionState;)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, j_route, j_state);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        env->PopLocalFrame(nullptr);
    }

    static void CEF_CALLBACK _on_route_message_received(cef_media_observer_t* self, struct _cef_media_route_t* route, const void* message, size_t message_size) {
        auto* h = reinterpret_cast<JniCefMediaObserver*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(9) < 0) { return; }
        cef_media_route_t* _p_route = route;
        if (_p_route) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_route); _b->add_ref(_b); }
        auto j_route_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefMediaRoute$NativePeer");
        auto j_route_ctor = env->GetMethodID(j_route_cls, "<init>", "(J)V");
        auto j_route = _p_route ? env->NewObject(j_route_cls, j_route_ctor, reinterpret_cast<jlong>(_p_route)) : nullptr;
        jobject j_message = (message_size > 0 && message) ? env->NewDirectByteBuffer(const_cast<void*>(static_cast<const void*>(message)), static_cast<jlong>(message_size)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onRouteMessageReceived", "(Lnet/kurobako/cef4j/gen/CefMediaRoute;Ljava/nio/ByteBuffer;)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, j_route, j_message);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        env->PopLocalFrame(nullptr);
    }
};

extern "C" cef_media_observer_t* Create_JniCefMediaObserver(JNIEnv* env, jobject handler) {
    JavaVM* jvm;
    env->GetJavaVM(&jvm);
    auto globalRef = env->NewGlobalRef(handler);
    return reinterpret_cast<cef_media_observer_t*>(new JniCefMediaObserver(jvm, globalRef));
}
