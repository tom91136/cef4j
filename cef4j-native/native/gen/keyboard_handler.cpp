// GENERATED - do not edit.
#include <jni.h>
#include "include/capi/cef_keyboard_handler_capi.h"
#include "include/capi/cef_browser_capi.h"
#include "jni_util.h"

#include <atomic>
#include "jni_util.h"

struct JniCefKeyboardHandler: public cef_keyboard_handler_t {
    JavaVM *jvm;
    jobject javaHandler;  // global ref
    std::atomic<int> refCount { 1 };

    JniCefKeyboardHandler(JavaVM *vm, jobject handler) : cef_keyboard_handler_t { }, jvm(vm) {
        javaHandler = handler;
        InitRefCount<JniCefKeyboardHandler, cef_keyboard_handler_t> (&base);
        on_pre_key_event = &_on_pre_key_event;
        on_key_event = &_on_key_event;
    }

    static int CEF_CALLBACK _on_pre_key_event(cef_keyboard_handler_t* self, struct _cef_browser_t* browser, const cef_key_event_t* event, cef_event_handle_t os_event, int* is_keyboard_shortcut) {
        auto* h = reinterpret_cast<JniCefKeyboardHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(15) < 0) {return false;}
        cef_browser_t* _p_browser = browser;
        if (_p_browser) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b);}
        auto j_browser_cls = env->FindClass("net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        auto _bv_event_type_cls = env->FindClass("net/kurobako/cef4j/gen/CefKeyEventType");
        auto _bv_event_type_of = env->GetStaticMethodID(_bv_event_type_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefKeyEventType;");
        auto _bv_event_type = env->CallStaticObjectMethod(_bv_event_type_cls, _bv_event_type_of, static_cast<jlong>(event->type));
        auto j_event_cls = env->FindClass("net/kurobako/cef4j/gen/CefKeyEvent");
        auto j_event_ctor = env->GetMethodID(j_event_cls, "<init>", "(Lnet/kurobako/cef4j/gen/CefKeyEventType;IIIICCI)V");
        auto j_event = event ? env->NewObject(j_event_cls, j_event_ctor, _bv_event_type, static_cast<jint>(event->modifiers), static_cast<jint>(event->windows_key_code), static_cast<jint>(event->native_key_code), static_cast<jint>(event->is_system_key), static_cast<jchar>(event->character), static_cast<jchar>(event->unmodified_character), static_cast<jint>(event->focus_on_editable_field)) : nullptr;
        if (j_event) env->SetLongField(j_event, env->GetFieldID(j_event_cls, "size", "J"), static_cast<jlong>(event->size));
        jintArray j_is_keyboard_shortcut = env->NewIntArray(1);
        if (is_keyboard_shortcut) {jint _v = *is_keyboard_shortcut; env->SetIntArrayRegion(j_is_keyboard_shortcut, 0, 1, &_v);}
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onPreKeyEvent", "(Lnet/kurobako/cef4j/gen/CefBrowser;Lnet/kurobako/cef4j/gen/CefKeyEvent;J[I)Z");
        if (!mid) {env->PopLocalFrame(nullptr); return false;}
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, j_browser, j_event, (jlong)(os_event), j_is_keyboard_shortcut);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return false;}
        if (is_keyboard_shortcut) {jint _v; env->GetIntArrayRegion(j_is_keyboard_shortcut, 0, 1, &_v); *is_keyboard_shortcut = _v;}
        env->PopLocalFrame(nullptr);
        return jResult;
    }

    static int CEF_CALLBACK _on_key_event(cef_keyboard_handler_t* self, struct _cef_browser_t* browser, const cef_key_event_t* event, cef_event_handle_t os_event) {
        auto* h = reinterpret_cast<JniCefKeyboardHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(14) < 0) {return false;}
        cef_browser_t* _p_browser = browser;
        if (_p_browser) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b);}
        auto j_browser_cls = env->FindClass("net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        auto _bv_event_type_cls = env->FindClass("net/kurobako/cef4j/gen/CefKeyEventType");
        auto _bv_event_type_of = env->GetStaticMethodID(_bv_event_type_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefKeyEventType;");
        auto _bv_event_type = env->CallStaticObjectMethod(_bv_event_type_cls, _bv_event_type_of, static_cast<jlong>(event->type));
        auto j_event_cls = env->FindClass("net/kurobako/cef4j/gen/CefKeyEvent");
        auto j_event_ctor = env->GetMethodID(j_event_cls, "<init>", "(Lnet/kurobako/cef4j/gen/CefKeyEventType;IIIICCI)V");
        auto j_event = event ? env->NewObject(j_event_cls, j_event_ctor, _bv_event_type, static_cast<jint>(event->modifiers), static_cast<jint>(event->windows_key_code), static_cast<jint>(event->native_key_code), static_cast<jint>(event->is_system_key), static_cast<jchar>(event->character), static_cast<jchar>(event->unmodified_character), static_cast<jint>(event->focus_on_editable_field)) : nullptr;
        if (j_event) env->SetLongField(j_event, env->GetFieldID(j_event_cls, "size", "J"), static_cast<jlong>(event->size));
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onKeyEvent", "(Lnet/kurobako/cef4j/gen/CefBrowser;Lnet/kurobako/cef4j/gen/CefKeyEvent;J)Z");
        if (!mid) {env->PopLocalFrame(nullptr); return false;}
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, j_browser, j_event, (jlong)(os_event));
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return false;}
        env->PopLocalFrame(nullptr);
        return jResult;
    }
};

extern "C" cef_keyboard_handler_t* Create_JniCefKeyboardHandler(JNIEnv *env, jobject handler) {
    JavaVM *jvm;
    env->GetJavaVM(&jvm);
    auto globalRef = env->NewGlobalRef(handler);
    return reinterpret_cast<cef_keyboard_handler_t*>(new JniCefKeyboardHandler(jvm, globalRef));
}

extern "C" JNIEXPORT jlong JNICALL Java_net_kurobako_cef4j_gen_CefKeyboardHandler_1N_N_1Create(JNIEnv* env, jobject obj) {
    return reinterpret_cast<jlong>(Create_JniCefKeyboardHandler(env, obj));
}
