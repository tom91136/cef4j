// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
#include <jni.h>
#include "include/capi/cef_drag_handler_capi.h"
#include "include/capi/cef_browser_capi.h"
#include "include/capi/cef_drag_data_capi.h"
#include "include/capi/cef_frame_capi.h"
#include "jni_util.h"

#include <atomic>
#include "jni_util.h"

struct JniCefDragHandler: public cef_drag_handler_t {
    JavaVM *jvm;
    jobject javaHandler;  // global ref
    std::atomic<int> refCount { 1 };

    JniCefDragHandler(JavaVM *vm, jobject handler) : cef_drag_handler_t { }, jvm(vm) {
        javaHandler = handler;
        InitRefCount<JniCefDragHandler, cef_drag_handler_t>(reinterpret_cast<cef_base_ref_counted_t*>(static_cast<cef_drag_handler_t*>(this)));
        on_drag_enter = &_on_drag_enter;
        on_draggable_regions_changed = &_on_draggable_regions_changed;
    }

    static int CEF_CALLBACK _on_drag_enter(cef_drag_handler_t* self, struct _cef_browser_t* browser, struct _cef_drag_data_t* dragData, cef_drag_operations_mask_t mask) {
        auto* h = reinterpret_cast<JniCefDragHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(14) < 0) {return false;}
        cef_browser_t* _p_browser = browser;
        if (_p_browser) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b);}
        auto j_browser_cls = env->FindClass("net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        cef_drag_data_t* _p_dragData = dragData;
        if (_p_dragData) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_dragData); _b->add_ref(_b);}
        auto j_dragData_cls = env->FindClass("net/kurobako/cef4j/gen/CefDragData$NativePeer");
        auto j_dragData_ctor = env->GetMethodID(j_dragData_cls, "<init>", "(J)V");
        auto j_dragData = _p_dragData ? env->NewObject(j_dragData_cls, j_dragData_ctor, reinterpret_cast<jlong>(_p_dragData)) : nullptr;
        auto j_mask_cls = env->FindClass("net/kurobako/cef4j/gen/CefDragOperationsMask");
        auto j_mask_from = env->GetStaticMethodID(j_mask_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefDragOperationsMask;");
        auto j_mask = env->CallStaticObjectMethod(j_mask_cls, j_mask_from, static_cast<jlong>(mask));
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onDragEnter", "(Lnet/kurobako/cef4j/gen/CefBrowser;Lnet/kurobako/cef4j/gen/CefDragData;Lnet/kurobako/cef4j/gen/CefDragOperationsMask;)Z");
        if (!mid) {env->PopLocalFrame(nullptr); return false;}
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, j_browser, j_dragData, j_mask);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return false;}
        env->PopLocalFrame(nullptr);
        return jResult;
    }

    static void CEF_CALLBACK _on_draggable_regions_changed(cef_drag_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, size_t regionsCount, cef_draggable_region_t const* regions) {
        auto* h = reinterpret_cast<JniCefDragHandler*>(self);
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
        auto j_regions_cls = env->FindClass("net/kurobako/cef4j/gen/NativePointer");
        auto j_regions_ctor = env->GetMethodID(j_regions_cls, "<init>", "(J)V");
        auto j_regions = env->NewObject(j_regions_cls, j_regions_ctor, reinterpret_cast<jlong>(regions));
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onDraggableRegionsChanged", "(Lnet/kurobako/cef4j/gen/CefBrowser;Lnet/kurobako/cef4j/gen/CefFrame;JLnet/kurobako/cef4j/gen/NativePointer;)V");
        if (!mid) {env->PopLocalFrame(nullptr); return;}
        env->CallVoidMethod(h->javaHandler, mid, j_browser, j_frame, static_cast<jlong>(regionsCount), j_regions);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return;}
        env->PopLocalFrame(nullptr);
    }
};

extern "C" cef_drag_handler_t* Create_JniCefDragHandler(JNIEnv *env, jobject handler) {
    JavaVM *jvm;
    env->GetJavaVM(&jvm);
    auto globalRef = env->NewGlobalRef(handler);
    return reinterpret_cast<cef_drag_handler_t*>(new JniCefDragHandler(jvm, globalRef));
}
