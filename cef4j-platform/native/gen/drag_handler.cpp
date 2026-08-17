// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
#include <jni.h>
#include "include/capi/cef_drag_handler_capi.h"
#include "include/capi/cef_browser_capi.h"
#include "include/capi/cef_drag_data_capi.h"
#include "include/capi/cef_frame_capi.h"
#include "jni_util.h"

#include <atomic>
#include <vector>
#include "jni_util.h"

struct JniCefDragHandler : public cef_drag_handler_t {
    JavaVM* jvm;
    jobject javaHandler;  // global ref
    std::atomic<int> refCount{1};

    JniCefDragHandler(JavaVM* vm, jobject handler) : cef_drag_handler_t{}, jvm(vm) {
        javaHandler = handler;
        InitRefCount<JniCefDragHandler, cef_drag_handler_t>(reinterpret_cast<cef_base_ref_counted_t*>(static_cast<cef_drag_handler_t*>(this)));
        on_drag_enter = &_on_drag_enter;
        on_draggable_regions_changed = &_on_draggable_regions_changed;
    }

    static int CEF_CALLBACK _on_drag_enter(cef_drag_handler_t* self, struct _cef_browser_t* browser, struct _cef_drag_data_t* dragData, cef_drag_operations_mask_t mask) {
        auto* h = reinterpret_cast<JniCefDragHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(14) < 0) { return false; }
        cef_browser_t* _p_browser = browser;
        if (_p_browser) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b); }
        auto j_browser_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        cef_drag_data_t* _p_dragData = dragData;
        if (_p_dragData) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_dragData); _b->add_ref(_b); }
        auto j_dragData_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefDragData$NativePeer");
        auto j_dragData_ctor = env->GetMethodID(j_dragData_cls, "<init>", "(J)V");
        auto j_dragData = _p_dragData ? env->NewObject(j_dragData_cls, j_dragData_ctor, reinterpret_cast<jlong>(_p_dragData)) : nullptr;
        auto j_mask_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefDragOperationsMask");
        auto j_mask_from = env->GetStaticMethodID(j_mask_cls, "of", "(J)Lnet/kurobako/cef4j/gen/CefDragOperationsMask;");
        auto j_mask = env->CallStaticObjectMethod(j_mask_cls, j_mask_from, static_cast<jlong>(mask));
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onDragEnter", "(Lnet/kurobako/cef4j/gen/CefBrowser;Lnet/kurobako/cef4j/gen/CefDragData;Lnet/kurobako/cef4j/gen/CefDragOperationsMask;)Z");
        if (!mid) { env->PopLocalFrame(nullptr); return false; }
        auto jResult = env->CallBooleanMethod(h->javaHandler, mid, j_browser, j_dragData, j_mask);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return false; }
        env->PopLocalFrame(nullptr);
        return jResult;
    }

    static void CEF_CALLBACK _on_draggable_regions_changed(cef_drag_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, size_t regionsCount, cef_draggable_region_t const* regions) {
        auto* h = reinterpret_cast<JniCefDragHandler*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(25) < 0) { return; }
        cef_browser_t* _p_browser = browser;
        if (_p_browser) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_browser); _b->add_ref(_b); }
        auto j_browser_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefBrowser$NativePeer");
        auto j_browser_ctor = env->GetMethodID(j_browser_cls, "<init>", "(J)V");
        auto j_browser = _p_browser ? env->NewObject(j_browser_cls, j_browser_ctor, reinterpret_cast<jlong>(_p_browser)) : nullptr;
        cef_frame_t* _p_frame = frame;
        if (_p_frame) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_frame); _b->add_ref(_b); }
        auto j_frame_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefFrame$NativePeer");
        auto j_frame_ctor = env->GetMethodID(j_frame_cls, "<init>", "(J)V");
        auto j_frame = _p_frame ? env->NewObject(j_frame_cls, j_frame_ctor, reinterpret_cast<jlong>(_p_frame)) : nullptr;
        auto j_regions_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefDraggableRegion");
        auto j_regions_ctor = env->GetMethodID(j_regions_cls, "<init>", "(Lnet/kurobako/cef4j/gen/CefRect;I)V");
        jint j_regions_len = static_cast<jint>(regionsCount);
        auto j_regions = env->NewObjectArray(j_regions_len, j_regions_cls, nullptr);
        for (jint _i = 0; _i < j_regions_len; _i++) {
            auto _bv___regions__i___bounds_cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefRect");
            auto _bv___regions__i___bounds_ctor = env->GetMethodID(_bv___regions__i___bounds_cls, "<init>", "(IIII)V");
            auto _bv___regions__i___bounds = env->NewObject(_bv___regions__i___bounds_cls, _bv___regions__i___bounds_ctor, static_cast<jint>(((&regions[_i]))->bounds.x), static_cast<jint>(((&regions[_i]))->bounds.y), static_cast<jint>(((&regions[_i]))->bounds.width), static_cast<jint>(((&regions[_i]))->bounds.height));
            auto _elem = env->NewObject(j_regions_cls, j_regions_ctor, _bv___regions__i___bounds, static_cast<jint>(((&regions[_i]))->draggable));
            env->SetObjectArrayElement(j_regions, _i, _elem);
            env->DeleteLocalRef(_elem);
        }
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "onDraggableRegionsChanged", "(Lnet/kurobako/cef4j/gen/CefBrowser;Lnet/kurobako/cef4j/gen/CefFrame;J[Lnet/kurobako/cef4j/gen/CefDraggableRegion;)V");
        if (!mid) { env->PopLocalFrame(nullptr); return; }
        env->CallVoidMethod(h->javaHandler, mid, j_browser, j_frame, static_cast<jlong>(regionsCount), j_regions);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }
        env->PopLocalFrame(nullptr);
    }
};

extern "C" cef_drag_handler_t* Create_JniCefDragHandler(JNIEnv* env, jobject handler) {
    JavaVM* jvm;
    env->GetJavaVM(&jvm);
    auto globalRef = env->NewGlobalRef(handler);
    return reinterpret_cast<cef_drag_handler_t*>(new JniCefDragHandler(jvm, globalRef));
}
