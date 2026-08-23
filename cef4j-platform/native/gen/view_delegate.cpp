// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
#include <jni.h>
#include "include/capi/views/cef_view_delegate_capi.h"
#include "include/capi/views/cef_view_capi.h"
#include "jni_util.h"

#include <atomic>

struct JniCefViewDelegate : public cef_view_delegate_t {
    JavaVM* jvm;
    jobject javaHandler;  // global ref
    std::atomic<int> refCount{1};

    JniCefViewDelegate(JavaVM* vm, jobject handler) : cef_view_delegate_t{}, jvm(vm) {
        javaHandler = handler;
        InitRefCount<JniCefViewDelegate, cef_view_delegate_t>(reinterpret_cast<cef_base_ref_counted_t*>(static_cast<cef_view_delegate_t*>(this)));
        get_preferred_size = &_get_preferred_size;
        get_minimum_size = &_get_minimum_size;
        get_maximum_size = &_get_maximum_size;
        get_height_for_width = &_get_height_for_width;
        on_parent_view_changed = &_on_parent_view_changed;
        on_child_view_changed = &_on_child_view_changed;
        on_window_changed = &_on_window_changed;
        on_layout_changed = &_on_layout_changed;
        on_focus = &_on_focus;
        on_blur = &_on_blur;
        on_theme_changed = &_on_theme_changed;
    }

    static cef_size_t CEF_CALLBACK _get_preferred_size(cef_view_delegate_t* self, struct _cef_view_t* view) {
        auto* h = reinterpret_cast<JniCefViewDelegate*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) { return cef_size_t{}; }
        cef_view_t* _p_view = view;
        if (_p_view) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_view); _b->add_ref(_b); }
        auto j_view_cls = FindClassCached(env, "net/kurobako/cef4j/gen/views/CefView$NativePeer");
        auto j_view_ctor = env->GetMethodID(j_view_cls, "<init>", "(J)V");
        auto j_view = _p_view ? env->NewObject(j_view_cls, j_view_ctor, reinterpret_cast<jlong>(_p_view)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "getPreferredSize", "(Lnet/kurobako/cef4j/gen/views/CefView;)Lnet/kurobako/cef4j/gen/CefSize;");
        if (!mid) { env->PopLocalFrame(nullptr); return cef_size_t{}; }
        auto jResult = (jobject)env->CallObjectMethod(h->javaHandler, mid, j_view);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return cef_size_t{}; }
        cef_size_t nativeResult = ([&]() {
        cef_size_t _result = {};
        if (jResult) {
            auto _c = FindClassCached(env, "net/kurobako/cef4j/gen/CefSize");
            _result.width = static_cast<decltype(_result.width)>(env->GetIntField(jResult, env->GetFieldID(_c, "width", "I")));
            _result.height = static_cast<decltype(_result.height)>(env->GetIntField(jResult, env->GetFieldID(_c, "height", "I")));
        }
        return _result;
    })();
        env->PopLocalFrame(nullptr);
        return nativeResult;
    }

    static cef_size_t CEF_CALLBACK _get_minimum_size(cef_view_delegate_t* self, struct _cef_view_t* view) {
        auto* h = reinterpret_cast<JniCefViewDelegate*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) { return cef_size_t{}; }
        cef_view_t* _p_view = view;
        if (_p_view) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_view); _b->add_ref(_b); }
        auto j_view_cls = FindClassCached(env, "net/kurobako/cef4j/gen/views/CefView$NativePeer");
        auto j_view_ctor = env->GetMethodID(j_view_cls, "<init>", "(J)V");
        auto j_view = _p_view ? env->NewObject(j_view_cls, j_view_ctor, reinterpret_cast<jlong>(_p_view)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "getMinimumSize", "(Lnet/kurobako/cef4j/gen/views/CefView;)Lnet/kurobako/cef4j/gen/CefSize;");
        if (!mid) { env->PopLocalFrame(nullptr); return cef_size_t{}; }
        auto jResult = (jobject)env->CallObjectMethod(h->javaHandler, mid, j_view);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return cef_size_t{}; }
        cef_size_t nativeResult = ([&]() {
        cef_size_t _result = {};
        if (jResult) {
            auto _c = FindClassCached(env, "net/kurobako/cef4j/gen/CefSize");
            _result.width = static_cast<decltype(_result.width)>(env->GetIntField(jResult, env->GetFieldID(_c, "width", "I")));
            _result.height = static_cast<decltype(_result.height)>(env->GetIntField(jResult, env->GetFieldID(_c, "height", "I")));
        }
        return _result;
    })();
        env->PopLocalFrame(nullptr);
        return nativeResult;
    }

    static cef_size_t CEF_CALLBACK _get_maximum_size(cef_view_delegate_t* self, struct _cef_view_t* view) {
        auto* h = reinterpret_cast<JniCefViewDelegate*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) { return cef_size_t{}; }
        cef_view_t* _p_view = view;
        if (_p_view) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_view); _b->add_ref(_b); }
        auto j_view_cls = FindClassCached(env, "net/kurobako/cef4j/gen/views/CefView$NativePeer");
        auto j_view_ctor = env->GetMethodID(j_view_cls, "<init>", "(J)V");
        auto j_view = _p_view ? env->NewObject(j_view_cls, j_view_ctor, reinterpret_cast<jlong>(_p_view)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "getMaximumSize", "(Lnet/kurobako/cef4j/gen/views/CefView;)Lnet/kurobako/cef4j/gen/CefSize;");
        if (!mid) { env->PopLocalFrame(nullptr); return cef_size_t{}; }
        auto jResult = (jobject)env->CallObjectMethod(h->javaHandler, mid, j_view);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return cef_size_t{}; }
        cef_size_t nativeResult = ([&]() {
        cef_size_t _result = {};
        if (jResult) {
            auto _c = FindClassCached(env, "net/kurobako/cef4j/gen/CefSize");
            _result.width = static_cast<decltype(_result.width)>(env->GetIntField(jResult, env->GetFieldID(_c, "width", "I")));
            _result.height = static_cast<decltype(_result.height)>(env->GetIntField(jResult, env->GetFieldID(_c, "height", "I")));
        }
        return _result;
    })();
        env->PopLocalFrame(nullptr);
        return nativeResult;
    }

    static int CEF_CALLBACK _get_height_for_width(cef_view_delegate_t* self, struct _cef_view_t* view, int width) {
        auto* h = reinterpret_cast<JniCefViewDelegate*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) { return 0; }
        cef_view_t* _p_view = view;
        if (_p_view) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_view); _b->add_ref(_b); }
        auto j_view_cls = FindClassCached(env, "net/kurobako/cef4j/gen/views/CefView$NativePeer");
        auto j_view_ctor = env->GetMethodID(j_view_cls, "<init>", "(J)V");
        auto j_view = _p_view ? env->NewObject(j_view_cls, j_view_ctor, reinterpret_cast<jlong>(_p_view)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "getHeightForWidth", "(Lnet/kurobako/cef4j/gen/views/CefView;I)I");
        if (!mid) { env->PopLocalFrame(nullptr); return 0; }
        auto jResult = env->CallIntMethod(h->javaHandler, mid, j_view, static_cast<jint>(width));
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return 0; }
        int nativeResult = static_cast<int>(jResult);
        env->PopLocalFrame(nullptr);
        return nativeResult;
    }

    static void CEF_CALLBACK _on_parent_view_changed(cef_view_delegate_t* self, struct _cef_view_t* view, int added, struct _cef_view_t* parent) {
        auto* h = reinterpret_cast<JniCefViewDelegate*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(11) < 0) { return; }
        cef_view_t* _p_view = view;
        if (_p_view) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_view); _b->add_ref(_b); }
        auto j_view_cls = FindClassCached(env, "net/kurobako/cef4j/gen/views/CefView$NativePeer");
        auto j_view_ctor = env->GetMethodID(j_view_cls, "<init>", "(J)V");
        auto j_view = _p_view ? env->NewObject(j_view_cls, j_view_ctor, reinterpret_cast<jlong>(_p_view)) : nullptr;
        cef_view_t* _p_parent = parent;
        if (_p_parent) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_parent); _b->add_ref(_b); }
        auto j_parent_cls = FindClassCached(env, "net/kurobako/cef4j/gen/views/CefView$NativePeer");
        auto j_parent_ctor = env->GetMethodID(j_parent_cls, "<init>", "(J)V");
        auto j_parent = _p_parent ? env->NewObject(j_parent_cls, j_parent_ctor, reinterpret_cast<jlong>(_p_parent)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onParentViewChanged", "(Lnet/kurobako/cef4j/gen/views/CefView;ZLnet/kurobako/cef4j/gen/views/CefView;)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, j_view, static_cast<jboolean>(added), j_parent);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        env->PopLocalFrame(nullptr);
    }

    static void CEF_CALLBACK _on_child_view_changed(cef_view_delegate_t* self, struct _cef_view_t* view, int added, struct _cef_view_t* child) {
        auto* h = reinterpret_cast<JniCefViewDelegate*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(11) < 0) { return; }
        cef_view_t* _p_view = view;
        if (_p_view) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_view); _b->add_ref(_b); }
        auto j_view_cls = FindClassCached(env, "net/kurobako/cef4j/gen/views/CefView$NativePeer");
        auto j_view_ctor = env->GetMethodID(j_view_cls, "<init>", "(J)V");
        auto j_view = _p_view ? env->NewObject(j_view_cls, j_view_ctor, reinterpret_cast<jlong>(_p_view)) : nullptr;
        cef_view_t* _p_child = child;
        if (_p_child) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_child); _b->add_ref(_b); }
        auto j_child_cls = FindClassCached(env, "net/kurobako/cef4j/gen/views/CefView$NativePeer");
        auto j_child_ctor = env->GetMethodID(j_child_cls, "<init>", "(J)V");
        auto j_child = _p_child ? env->NewObject(j_child_cls, j_child_ctor, reinterpret_cast<jlong>(_p_child)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onChildViewChanged", "(Lnet/kurobako/cef4j/gen/views/CefView;ZLnet/kurobako/cef4j/gen/views/CefView;)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, j_view, static_cast<jboolean>(added), j_child);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        env->PopLocalFrame(nullptr);
    }

    static void CEF_CALLBACK _on_window_changed(cef_view_delegate_t* self, struct _cef_view_t* view, int added) {
        auto* h = reinterpret_cast<JniCefViewDelegate*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) { return; }
        cef_view_t* _p_view = view;
        if (_p_view) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_view); _b->add_ref(_b); }
        auto j_view_cls = FindClassCached(env, "net/kurobako/cef4j/gen/views/CefView$NativePeer");
        auto j_view_ctor = env->GetMethodID(j_view_cls, "<init>", "(J)V");
        auto j_view = _p_view ? env->NewObject(j_view_cls, j_view_ctor, reinterpret_cast<jlong>(_p_view)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onWindowChanged", "(Lnet/kurobako/cef4j/gen/views/CefView;Z)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, j_view, static_cast<jboolean>(added));
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        env->PopLocalFrame(nullptr);
    }

    static void CEF_CALLBACK _on_layout_changed(cef_view_delegate_t* self, struct _cef_view_t* view, const cef_rect_t* new_bounds) {
        auto* h = reinterpret_cast<JniCefViewDelegate*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(11) < 0) { return; }
        cef_view_t* _p_view = view;
        if (_p_view) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_view); _b->add_ref(_b); }
        auto j_view_cls = FindClassCached(env, "net/kurobako/cef4j/gen/views/CefView$NativePeer");
        auto j_view_ctor = env->GetMethodID(j_view_cls, "<init>", "(J)V");
        auto j_view = _p_view ? env->NewObject(j_view_cls, j_view_ctor, reinterpret_cast<jlong>(_p_view)) : nullptr;
        auto j_new_bounds_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefRect");
        auto j_new_bounds_ctor = env->GetMethodID(j_new_bounds_cls, "<init>", "(IIII)V");
        auto j_new_bounds = new_bounds ? env->NewObject(j_new_bounds_cls, j_new_bounds_ctor, static_cast<jint>((new_bounds)->x), static_cast<jint>((new_bounds)->y), static_cast<jint>((new_bounds)->width), static_cast<jint>((new_bounds)->height)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onLayoutChanged", "(Lnet/kurobako/cef4j/gen/views/CefView;Lnet/kurobako/cef4j/gen/CefRect;)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, j_view, j_new_bounds);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        env->PopLocalFrame(nullptr);
    }

    static void CEF_CALLBACK _on_focus(cef_view_delegate_t* self, struct _cef_view_t* view) {
        auto* h = reinterpret_cast<JniCefViewDelegate*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) { return; }
        cef_view_t* _p_view = view;
        if (_p_view) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_view); _b->add_ref(_b); }
        auto j_view_cls = FindClassCached(env, "net/kurobako/cef4j/gen/views/CefView$NativePeer");
        auto j_view_ctor = env->GetMethodID(j_view_cls, "<init>", "(J)V");
        auto j_view = _p_view ? env->NewObject(j_view_cls, j_view_ctor, reinterpret_cast<jlong>(_p_view)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onFocus", "(Lnet/kurobako/cef4j/gen/views/CefView;)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, j_view);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        env->PopLocalFrame(nullptr);
    }

    static void CEF_CALLBACK _on_blur(cef_view_delegate_t* self, struct _cef_view_t* view) {
        auto* h = reinterpret_cast<JniCefViewDelegate*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) { return; }
        cef_view_t* _p_view = view;
        if (_p_view) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_view); _b->add_ref(_b); }
        auto j_view_cls = FindClassCached(env, "net/kurobako/cef4j/gen/views/CefView$NativePeer");
        auto j_view_ctor = env->GetMethodID(j_view_cls, "<init>", "(J)V");
        auto j_view = _p_view ? env->NewObject(j_view_cls, j_view_ctor, reinterpret_cast<jlong>(_p_view)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onBlur", "(Lnet/kurobako/cef4j/gen/views/CefView;)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, j_view);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        env->PopLocalFrame(nullptr);
    }

    static void CEF_CALLBACK _on_theme_changed(cef_view_delegate_t* self, struct _cef_view_t* view) {
        auto* h = reinterpret_cast<JniCefViewDelegate*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) { return; }
        cef_view_t* _p_view = view;
        if (_p_view) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_view); _b->add_ref(_b); }
        auto j_view_cls = FindClassCached(env, "net/kurobako/cef4j/gen/views/CefView$NativePeer");
        auto j_view_ctor = env->GetMethodID(j_view_cls, "<init>", "(J)V");
        auto j_view = _p_view ? env->NewObject(j_view_cls, j_view_ctor, reinterpret_cast<jlong>(_p_view)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onThemeChanged", "(Lnet/kurobako/cef4j/gen/views/CefView;)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, j_view);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        env->PopLocalFrame(nullptr);
    }
};

extern "C" cef_view_delegate_t* Create_JniCefViewDelegate(JNIEnv* env, jobject handler) {
    JavaVM* jvm;
    env->GetJavaVM(&jvm);
    auto globalRef = env->NewGlobalRef(handler);
    return reinterpret_cast<cef_view_delegate_t*>(new JniCefViewDelegate(jvm, globalRef));
}
