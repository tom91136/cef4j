// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
#include <jni.h>
#include "include/capi/cef_audio_handler_capi.h"
#include "include/capi/cef_browser_capi.h"
#include "jni_util.h"

#include <atomic>
#include "jni_util.h"

struct JniCefAudioHandler: public cef_audio_handler_t {
    JavaVM *jvm;
    jobject javaHandler;  // global ref
    std::atomic<int> refCount { 1 };

    JniCefAudioHandler(JavaVM *vm, jobject handler) : cef_audio_handler_t { }, jvm(vm) {
        javaHandler = handler;
        InitRefCount<JniCefAudioHandler, cef_audio_handler_t>(reinterpret_cast<cef_base_ref_counted_t*>(static_cast<cef_audio_handler_t*>(this)));
        get_audio_parameters = &_get_audio_parameters;
        on_audio_stream_started = &_on_audio_stream_started;
        on_audio_stream_packet = &_on_audio_stream_packet;
        on_audio_stream_stopped = &_on_audio_stream_stopped;
        on_audio_stream_error = &_on_audio_stream_error;
    }

    static int CEF_CALLBACK _get_audio_parameters(cef_audio_handler_t* self, struct _cef_browser_t* browser, cef_audio_parameters_t* params) {
        auto* h = reinterpret_cast<JniCefAudioHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(16) < 0) {return false;}
        cef_browser_t* _p_browser = browser;
        if (_p_browser) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b);}
        auto j_browser_cls = env->FindClass("net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        auto _bv_params_channel_layout_cls = env->FindClass("net/kurobako/cef4j/gen/CefChannelLayout");
        auto _bv_params_channel_layout_of = env->GetStaticMethodID(_bv_params_channel_layout_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefChannelLayout;");
        auto _bv_params_channel_layout = env->CallStaticObjectMethod(_bv_params_channel_layout_cls, _bv_params_channel_layout_of, static_cast<jlong>(params->channel_layout));
        auto j_params_cls = env->FindClass("net/kurobako/cef4j/gen/CefAudioParameters$Mutable");
        auto j_params_ctor = env->GetMethodID(j_params_cls, "<init>", "(Lnet/kurobako/cef4j/gen/CefChannelLayout;II)V");
        auto j_params = params ? env->NewObject(j_params_cls, j_params_ctor, _bv_params_channel_layout, static_cast<jint>(params->sample_rate), static_cast<jint>(params->frames_per_buffer)) : nullptr;
        if (j_params) env->SetLongField(j_params, env->GetFieldID(j_params_cls, "size", "J"), static_cast<jlong>(params->size));
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "getAudioParameters", "(Lnet/kurobako/cef4j/gen/CefBrowser;Lnet/kurobako/cef4j/gen/CefAudioParameters$Mutable;)Z");
        if (!mid) {env->PopLocalFrame(nullptr); return false;}
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, j_browser, j_params);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return false;}
        if (params && j_params) {
            auto _wb_channel_layout = env->GetObjectField(j_params, env->GetFieldID(j_params_cls, "channelLayout", "Lnet/kurobako/cef4j/gen/CefChannelLayout;"));
            if (_wb_channel_layout) {
                params->channel_layout = static_cast<decltype(params->channel_layout)>(env->GetLongField(_wb_channel_layout, env->GetFieldID(env->GetObjectClass(_wb_channel_layout), "value", "J")));
            }
            params->sample_rate = static_cast<decltype(params->sample_rate)>(env->GetIntField(j_params, env->GetFieldID(j_params_cls, "sampleRate", "I")));
            params->frames_per_buffer = static_cast<decltype(params->frames_per_buffer)>(env->GetIntField(j_params, env->GetFieldID(j_params_cls, "framesPerBuffer", "I")));
        }
        env->PopLocalFrame(nullptr);
        return jResult;
    }

    static void CEF_CALLBACK _on_audio_stream_started(cef_audio_handler_t* self, struct _cef_browser_t* browser, const cef_audio_parameters_t* params, int channels) {
        auto* h = reinterpret_cast<JniCefAudioHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(14) < 0) {return;}
        cef_browser_t* _p_browser = browser;
        if (_p_browser) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b);}
        auto j_browser_cls = env->FindClass("net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        auto _bv_params_channel_layout_cls = env->FindClass("net/kurobako/cef4j/gen/CefChannelLayout");
        auto _bv_params_channel_layout_of = env->GetStaticMethodID(_bv_params_channel_layout_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefChannelLayout;");
        auto _bv_params_channel_layout = env->CallStaticObjectMethod(_bv_params_channel_layout_cls, _bv_params_channel_layout_of, static_cast<jlong>(params->channel_layout));
        auto j_params_cls = env->FindClass("net/kurobako/cef4j/gen/CefAudioParameters");
        auto j_params_ctor = env->GetMethodID(j_params_cls, "<init>", "(Lnet/kurobako/cef4j/gen/CefChannelLayout;II)V");
        auto j_params = params ? env->NewObject(j_params_cls, j_params_ctor, _bv_params_channel_layout, static_cast<jint>(params->sample_rate), static_cast<jint>(params->frames_per_buffer)) : nullptr;
        if (j_params) env->SetLongField(j_params, env->GetFieldID(j_params_cls, "size", "J"), static_cast<jlong>(params->size));
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onAudioStreamStarted", "(Lnet/kurobako/cef4j/gen/CefBrowser;Lnet/kurobako/cef4j/gen/CefAudioParameters;I)V");
        if (!mid) {env->PopLocalFrame(nullptr); return;}
        env->CallVoidMethod(h->javaHandler, mid, j_browser, j_params, static_cast<jint>(channels));
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return;}
        env->PopLocalFrame(nullptr);
    }

    static void CEF_CALLBACK _on_audio_stream_packet(cef_audio_handler_t* self, struct _cef_browser_t* browser, const float** data, int frames, int64_t pts) {
        auto* h = reinterpret_cast<JniCefAudioHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(11) < 0) {return;}
        cef_browser_t* _p_browser = browser;
        if (_p_browser) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b);}
        auto j_browser_cls = env->FindClass("net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        auto j_data_cls = env->FindClass("net/kurobako/cef4j/gen/NativePointer");
        auto j_data_ctor = env->GetMethodID(j_data_cls, "<init>", "(J)V");
        auto j_data = env->NewObject(j_data_cls, j_data_ctor, reinterpret_cast<jlong>(data));
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onAudioStreamPacket", "(Lnet/kurobako/cef4j/gen/CefBrowser;Lnet/kurobako/cef4j/gen/NativePointer;IJ)V");
        if (!mid) {env->PopLocalFrame(nullptr); return;}
        env->CallVoidMethod(h->javaHandler, mid, j_browser, j_data, static_cast<jint>(frames), static_cast<jlong>(pts));
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return;}
        env->PopLocalFrame(nullptr);
    }

    static void CEF_CALLBACK _on_audio_stream_stopped(cef_audio_handler_t* self, struct _cef_browser_t* browser) {
        auto* h = reinterpret_cast<JniCefAudioHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) {return;}
        cef_browser_t* _p_browser = browser;
        if (_p_browser) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b);}
        auto j_browser_cls = env->FindClass("net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onAudioStreamStopped", "(Lnet/kurobako/cef4j/gen/CefBrowser;)V");
        if (!mid) {env->PopLocalFrame(nullptr); return;}
        env->CallVoidMethod(h->javaHandler, mid, j_browser);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return;}
        env->PopLocalFrame(nullptr);
    }

    static void CEF_CALLBACK _on_audio_stream_error(cef_audio_handler_t* self, struct _cef_browser_t* browser, const cef_string_t* message) {
        auto* h = reinterpret_cast<JniCefAudioHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(9) < 0) {return;}
        cef_browser_t* _p_browser = browser;
        if (_p_browser) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b);}
        auto j_browser_cls = env->FindClass("net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        auto j_message = CefStringToJString(env, message);
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onAudioStreamError", "(Lnet/kurobako/cef4j/gen/CefBrowser;Ljava/lang/String;)V");
        if (!mid) {env->PopLocalFrame(nullptr); return;}
        env->CallVoidMethod(h->javaHandler, mid, j_browser, j_message);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return;}
        env->PopLocalFrame(nullptr);
    }
};

extern "C" cef_audio_handler_t* Create_JniCefAudioHandler(JNIEnv *env, jobject handler) {
    JavaVM *jvm;
    env->GetJavaVM(&jvm);
    auto globalRef = env->NewGlobalRef(handler);
    return reinterpret_cast<cef_audio_handler_t*>(new JniCefAudioHandler(jvm, globalRef));
}
