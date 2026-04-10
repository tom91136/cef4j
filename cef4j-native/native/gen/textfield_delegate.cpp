// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
#include <jni.h>
#include "include/capi/views/cef_textfield_delegate_capi.h"
#include "include/capi/views/cef_textfield_capi.h"
#include "jni_util.h"

#include <atomic>
#include "jni_util.h"

struct JniCefTextfieldDelegate : public cef_textfield_delegate_t {
    JavaVM* jvm;
    jobject javaHandler;  // global ref
    std::atomic<int> refCount{1};

    JniCefTextfieldDelegate(JavaVM* vm, jobject handler) : cef_textfield_delegate_t{}, jvm(vm) {
        javaHandler = handler;
        InitRefCount<JniCefTextfieldDelegate, cef_textfield_delegate_t>(reinterpret_cast<cef_base_ref_counted_t*>(static_cast<cef_textfield_delegate_t*>(this)));
        on_key_event = &_on_key_event;
        on_after_user_action = &_on_after_user_action;
    }

    static int CEF_CALLBACK _on_key_event(cef_textfield_delegate_t* self, struct _cef_textfield_t* textfield, const cef_key_event_t* event) {
        auto* h = reinterpret_cast<JniCefTextfieldDelegate*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(14) < 0) { return false; }
        cef_textfield_t* _p_textfield = textfield;
        if (_p_textfield) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_textfield); _b->add_ref(_b); }
        auto j_textfield_cls = FindClassCached(env, "net/kurobako/cef4j/gen/views/CefTextfield$NativePeer");
        auto j_textfield_ctor = env->GetMethodID(j_textfield_cls, "<init>", "(J)V");
        auto j_textfield = _p_textfield ? env->NewObject(j_textfield_cls, j_textfield_ctor, reinterpret_cast<jlong>(_p_textfield)) : nullptr;
        auto _bv_event_type_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefKeyEventType");
        auto _bv_event_type_of = env->GetStaticMethodID(_bv_event_type_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefKeyEventType;");
        auto _bv_event_type = env->CallStaticObjectMethod(_bv_event_type_cls, _bv_event_type_of, static_cast<jlong>(event->type));
        auto j_event_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefKeyEvent");
        auto j_event_ctor = env->GetMethodID(j_event_cls, "<init>", "(Lnet/kurobako/cef4j/gen/CefKeyEventType;IIIICCI)V");
        auto j_event = event
    ? env->NewObject(j_event_cls, j_event_ctor,
        _bv_event_type,
        static_cast<jint>(event->modifiers),
        static_cast<jint>(event->windows_key_code),
        static_cast<jint>(event->native_key_code),
        static_cast<jint>(event->is_system_key),
        static_cast<jchar>(event->character),
        static_cast<jchar>(event->unmodified_character),
        static_cast<jint>(event->focus_on_editable_field))
    : nullptr;
        if (j_event) env->SetLongField(j_event, env->GetFieldID(j_event_cls, "size", "J"), static_cast<jlong>(event->size));
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onKeyEvent", "(Lnet/kurobako/cef4j/gen/views/CefTextfield;Lnet/kurobako/cef4j/gen/CefKeyEvent;)Z");
        if (!mid) { env->PopLocalFrame(nullptr); return false; }
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, j_textfield, j_event);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return false; }
        env->PopLocalFrame(nullptr);
        return jResult;
    }

    static void CEF_CALLBACK _on_after_user_action(cef_textfield_delegate_t* self, struct _cef_textfield_t* textfield) {
        auto* h = reinterpret_cast<JniCefTextfieldDelegate*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) { return; }
        cef_textfield_t* _p_textfield = textfield;
        if (_p_textfield) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_textfield); _b->add_ref(_b); }
        auto j_textfield_cls = FindClassCached(env, "net/kurobako/cef4j/gen/views/CefTextfield$NativePeer");
        auto j_textfield_ctor = env->GetMethodID(j_textfield_cls, "<init>", "(J)V");
        auto j_textfield = _p_textfield ? env->NewObject(j_textfield_cls, j_textfield_ctor, reinterpret_cast<jlong>(_p_textfield)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onAfterUserAction", "(Lnet/kurobako/cef4j/gen/views/CefTextfield;)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, j_textfield);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        env->PopLocalFrame(nullptr);
    }
};

extern "C" cef_textfield_delegate_t* Create_JniCefTextfieldDelegate(JNIEnv* env, jobject handler) {
    JavaVM* jvm;
    env->GetJavaVM(&jvm);
    auto globalRef = env->NewGlobalRef(handler);
    return reinterpret_cast<cef_textfield_delegate_t*>(new JniCefTextfieldDelegate(jvm, globalRef));
}
