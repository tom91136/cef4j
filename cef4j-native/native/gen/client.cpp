// GENERATED - do not edit.
#include <jni.h>
#include "include/capi/cef_client_capi.h"
#include "include/capi/cef_audio_handler_capi.h"
#include "include/capi/cef_browser_capi.h"
#include "include/capi/cef_command_handler_capi.h"
#include "include/capi/cef_context_menu_handler_capi.h"
#include "include/capi/cef_dialog_handler_capi.h"
#include "include/capi/cef_display_handler_capi.h"
#include "include/capi/cef_download_handler_capi.h"
#include "include/capi/cef_drag_handler_capi.h"
#include "include/capi/cef_find_handler_capi.h"
#include "include/capi/cef_focus_handler_capi.h"
#include "include/capi/cef_frame_capi.h"
#include "include/capi/cef_frame_handler_capi.h"
#include "include/capi/cef_jsdialog_handler_capi.h"
#include "include/capi/cef_keyboard_handler_capi.h"
#include "include/capi/cef_life_span_handler_capi.h"
#include "include/capi/cef_load_handler_capi.h"
#include "include/capi/cef_permission_handler_capi.h"
#include "include/capi/cef_print_handler_capi.h"
#include "include/capi/cef_process_message_capi.h"
#include "include/capi/cef_render_handler_capi.h"
#include "include/capi/cef_request_handler_capi.h"
#include "jni_util.h"

#include <atomic>
#include "jni_util.h"

extern "C" cef_audio_handler_t* Create_JniCefAudioHandler(JNIEnv *env, jobject handler);
extern "C" cef_command_handler_t* Create_JniCefCommandHandler(JNIEnv *env, jobject handler);
extern "C" cef_context_menu_handler_t* Create_JniCefContextMenuHandler(JNIEnv *env, jobject handler);
extern "C" cef_dialog_handler_t* Create_JniCefDialogHandler(JNIEnv *env, jobject handler);
extern "C" cef_display_handler_t* Create_JniCefDisplayHandler(JNIEnv *env, jobject handler);
extern "C" cef_download_handler_t* Create_JniCefDownloadHandler(JNIEnv *env, jobject handler);
extern "C" cef_drag_handler_t* Create_JniCefDragHandler(JNIEnv *env, jobject handler);
extern "C" cef_find_handler_t* Create_JniCefFindHandler(JNIEnv *env, jobject handler);
extern "C" cef_focus_handler_t* Create_JniCefFocusHandler(JNIEnv *env, jobject handler);
extern "C" cef_frame_handler_t* Create_JniCefFrameHandler(JNIEnv *env, jobject handler);
extern "C" cef_permission_handler_t* Create_JniCefPermissionHandler(JNIEnv *env, jobject handler);
extern "C" cef_jsdialog_handler_t* Create_JniCefJsDialogHandler(JNIEnv *env, jobject handler);
extern "C" cef_keyboard_handler_t* Create_JniCefKeyboardHandler(JNIEnv *env, jobject handler);
extern "C" cef_life_span_handler_t* Create_JniCefLifeSpanHandler(JNIEnv *env, jobject handler);
extern "C" cef_load_handler_t* Create_JniCefLoadHandler(JNIEnv *env, jobject handler);
extern "C" cef_print_handler_t* Create_JniCefPrintHandler(JNIEnv *env, jobject handler);
extern "C" cef_render_handler_t* Create_JniCefRenderHandler(JNIEnv *env, jobject handler);
extern "C" cef_request_handler_t* Create_JniCefRequestHandler(JNIEnv *env, jobject handler);

struct JniCefClient: public cef_client_t {
    JavaVM *jvm;
    jobject javaHandler;  // global ref
    std::atomic<int> refCount { 1 };

    JniCefClient(JavaVM *vm, jobject handler) : cef_client_t { }, jvm(vm) {
        javaHandler = handler;
        InitRefCount<JniCefClient, cef_client_t> (&base);
        get_audio_handler = &_get_audio_handler;
        get_command_handler = &_get_command_handler;
        get_context_menu_handler = &_get_context_menu_handler;
        get_dialog_handler = &_get_dialog_handler;
        get_display_handler = &_get_display_handler;
        get_download_handler = &_get_download_handler;
        get_drag_handler = &_get_drag_handler;
        get_find_handler = &_get_find_handler;
        get_focus_handler = &_get_focus_handler;
        get_frame_handler = &_get_frame_handler;
        get_permission_handler = &_get_permission_handler;
        get_jsdialog_handler = &_get_jsdialog_handler;
        get_keyboard_handler = &_get_keyboard_handler;
        get_life_span_handler = &_get_life_span_handler;
        get_load_handler = &_get_load_handler;
        get_print_handler = &_get_print_handler;
        get_render_handler = &_get_render_handler;
        get_request_handler = &_get_request_handler;
        on_process_message_received = &_on_process_message_received;
    }

    static cef_audio_handler_t* CEF_CALLBACK _get_audio_handler(cef_client_t* self) {
        auto* h = reinterpret_cast<JniCefClient*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(9) < 0) {return nullptr;}
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "getAudioHandler", "()Ljava/util/Optional;");
        if (!mid) {env->PopLocalFrame(nullptr); return nullptr;}
        auto jResult = (jobject)env->CallObjectMethod(h->javaHandler, mid);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return nullptr;}
        cef_audio_handler_t* nativeResult = nullptr;
        if (jResult) {
            auto _optCls = env->FindClass("java/util/Optional");
            auto _isPresentMid = env->GetMethodID(_optCls, "isPresent", "()Z");
            if (env->CallBooleanMethod(jResult, _isPresentMid) == JNI_TRUE) {
                auto _getMid = env->GetMethodID(_optCls, "get", "()Ljava/lang/Object;");
                auto _handlerObj = env->CallObjectMethod(jResult, _getMid);
                nativeResult = Create_JniCefAudioHandler(env, _handlerObj);
            }
        }
        env->PopLocalFrame(nullptr);
        return nativeResult;
    }

    static cef_command_handler_t* CEF_CALLBACK _get_command_handler(cef_client_t* self) {
        auto* h = reinterpret_cast<JniCefClient*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(9) < 0) {return nullptr;}
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "getCommandHandler", "()Ljava/util/Optional;");
        if (!mid) {env->PopLocalFrame(nullptr); return nullptr;}
        auto jResult = (jobject)env->CallObjectMethod(h->javaHandler, mid);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return nullptr;}
        cef_command_handler_t* nativeResult = nullptr;
        if (jResult) {
            auto _optCls = env->FindClass("java/util/Optional");
            auto _isPresentMid = env->GetMethodID(_optCls, "isPresent", "()Z");
            if (env->CallBooleanMethod(jResult, _isPresentMid) == JNI_TRUE) {
                auto _getMid = env->GetMethodID(_optCls, "get", "()Ljava/lang/Object;");
                auto _handlerObj = env->CallObjectMethod(jResult, _getMid);
                nativeResult = Create_JniCefCommandHandler(env, _handlerObj);
            }
        }
        env->PopLocalFrame(nullptr);
        return nativeResult;
    }

    static cef_context_menu_handler_t* CEF_CALLBACK _get_context_menu_handler(cef_client_t* self) {
        auto* h = reinterpret_cast<JniCefClient*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(9) < 0) {return nullptr;}
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "getContextMenuHandler", "()Ljava/util/Optional;");
        if (!mid) {env->PopLocalFrame(nullptr); return nullptr;}
        auto jResult = (jobject)env->CallObjectMethod(h->javaHandler, mid);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return nullptr;}
        cef_context_menu_handler_t* nativeResult = nullptr;
        if (jResult) {
            auto _optCls = env->FindClass("java/util/Optional");
            auto _isPresentMid = env->GetMethodID(_optCls, "isPresent", "()Z");
            if (env->CallBooleanMethod(jResult, _isPresentMid) == JNI_TRUE) {
                auto _getMid = env->GetMethodID(_optCls, "get", "()Ljava/lang/Object;");
                auto _handlerObj = env->CallObjectMethod(jResult, _getMid);
                nativeResult = Create_JniCefContextMenuHandler(env, _handlerObj);
            }
        }
        env->PopLocalFrame(nullptr);
        return nativeResult;
    }

    static cef_dialog_handler_t* CEF_CALLBACK _get_dialog_handler(cef_client_t* self) {
        auto* h = reinterpret_cast<JniCefClient*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(9) < 0) {return nullptr;}
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "getDialogHandler", "()Ljava/util/Optional;");
        if (!mid) {env->PopLocalFrame(nullptr); return nullptr;}
        auto jResult = (jobject)env->CallObjectMethod(h->javaHandler, mid);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return nullptr;}
        cef_dialog_handler_t* nativeResult = nullptr;
        if (jResult) {
            auto _optCls = env->FindClass("java/util/Optional");
            auto _isPresentMid = env->GetMethodID(_optCls, "isPresent", "()Z");
            if (env->CallBooleanMethod(jResult, _isPresentMid) == JNI_TRUE) {
                auto _getMid = env->GetMethodID(_optCls, "get", "()Ljava/lang/Object;");
                auto _handlerObj = env->CallObjectMethod(jResult, _getMid);
                nativeResult = Create_JniCefDialogHandler(env, _handlerObj);
            }
        }
        env->PopLocalFrame(nullptr);
        return nativeResult;
    }

    static cef_display_handler_t* CEF_CALLBACK _get_display_handler(cef_client_t* self) {
        auto* h = reinterpret_cast<JniCefClient*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(9) < 0) {return nullptr;}
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "getDisplayHandler", "()Ljava/util/Optional;");
        if (!mid) {env->PopLocalFrame(nullptr); return nullptr;}
        auto jResult = (jobject)env->CallObjectMethod(h->javaHandler, mid);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return nullptr;}
        cef_display_handler_t* nativeResult = nullptr;
        if (jResult) {
            auto _optCls = env->FindClass("java/util/Optional");
            auto _isPresentMid = env->GetMethodID(_optCls, "isPresent", "()Z");
            if (env->CallBooleanMethod(jResult, _isPresentMid) == JNI_TRUE) {
                auto _getMid = env->GetMethodID(_optCls, "get", "()Ljava/lang/Object;");
                auto _handlerObj = env->CallObjectMethod(jResult, _getMid);
                nativeResult = Create_JniCefDisplayHandler(env, _handlerObj);
            }
        }
        env->PopLocalFrame(nullptr);
        return nativeResult;
    }

    static cef_download_handler_t* CEF_CALLBACK _get_download_handler(cef_client_t* self) {
        auto* h = reinterpret_cast<JniCefClient*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(9) < 0) {return nullptr;}
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "getDownloadHandler", "()Ljava/util/Optional;");
        if (!mid) {env->PopLocalFrame(nullptr); return nullptr;}
        auto jResult = (jobject)env->CallObjectMethod(h->javaHandler, mid);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return nullptr;}
        cef_download_handler_t* nativeResult = nullptr;
        if (jResult) {
            auto _optCls = env->FindClass("java/util/Optional");
            auto _isPresentMid = env->GetMethodID(_optCls, "isPresent", "()Z");
            if (env->CallBooleanMethod(jResult, _isPresentMid) == JNI_TRUE) {
                auto _getMid = env->GetMethodID(_optCls, "get", "()Ljava/lang/Object;");
                auto _handlerObj = env->CallObjectMethod(jResult, _getMid);
                nativeResult = Create_JniCefDownloadHandler(env, _handlerObj);
            }
        }
        env->PopLocalFrame(nullptr);
        return nativeResult;
    }

    static cef_drag_handler_t* CEF_CALLBACK _get_drag_handler(cef_client_t* self) {
        auto* h = reinterpret_cast<JniCefClient*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(9) < 0) {return nullptr;}
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "getDragHandler", "()Ljava/util/Optional;");
        if (!mid) {env->PopLocalFrame(nullptr); return nullptr;}
        auto jResult = (jobject)env->CallObjectMethod(h->javaHandler, mid);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return nullptr;}
        cef_drag_handler_t* nativeResult = nullptr;
        if (jResult) {
            auto _optCls = env->FindClass("java/util/Optional");
            auto _isPresentMid = env->GetMethodID(_optCls, "isPresent", "()Z");
            if (env->CallBooleanMethod(jResult, _isPresentMid) == JNI_TRUE) {
                auto _getMid = env->GetMethodID(_optCls, "get", "()Ljava/lang/Object;");
                auto _handlerObj = env->CallObjectMethod(jResult, _getMid);
                nativeResult = Create_JniCefDragHandler(env, _handlerObj);
            }
        }
        env->PopLocalFrame(nullptr);
        return nativeResult;
    }

    static cef_find_handler_t* CEF_CALLBACK _get_find_handler(cef_client_t* self) {
        auto* h = reinterpret_cast<JniCefClient*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(9) < 0) {return nullptr;}
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "getFindHandler", "()Ljava/util/Optional;");
        if (!mid) {env->PopLocalFrame(nullptr); return nullptr;}
        auto jResult = (jobject)env->CallObjectMethod(h->javaHandler, mid);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return nullptr;}
        cef_find_handler_t* nativeResult = nullptr;
        if (jResult) {
            auto _optCls = env->FindClass("java/util/Optional");
            auto _isPresentMid = env->GetMethodID(_optCls, "isPresent", "()Z");
            if (env->CallBooleanMethod(jResult, _isPresentMid) == JNI_TRUE) {
                auto _getMid = env->GetMethodID(_optCls, "get", "()Ljava/lang/Object;");
                auto _handlerObj = env->CallObjectMethod(jResult, _getMid);
                nativeResult = Create_JniCefFindHandler(env, _handlerObj);
            }
        }
        env->PopLocalFrame(nullptr);
        return nativeResult;
    }

    static cef_focus_handler_t* CEF_CALLBACK _get_focus_handler(cef_client_t* self) {
        auto* h = reinterpret_cast<JniCefClient*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(9) < 0) {return nullptr;}
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "getFocusHandler", "()Ljava/util/Optional;");
        if (!mid) {env->PopLocalFrame(nullptr); return nullptr;}
        auto jResult = (jobject)env->CallObjectMethod(h->javaHandler, mid);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return nullptr;}
        cef_focus_handler_t* nativeResult = nullptr;
        if (jResult) {
            auto _optCls = env->FindClass("java/util/Optional");
            auto _isPresentMid = env->GetMethodID(_optCls, "isPresent", "()Z");
            if (env->CallBooleanMethod(jResult, _isPresentMid) == JNI_TRUE) {
                auto _getMid = env->GetMethodID(_optCls, "get", "()Ljava/lang/Object;");
                auto _handlerObj = env->CallObjectMethod(jResult, _getMid);
                nativeResult = Create_JniCefFocusHandler(env, _handlerObj);
            }
        }
        env->PopLocalFrame(nullptr);
        return nativeResult;
    }

    static cef_frame_handler_t* CEF_CALLBACK _get_frame_handler(cef_client_t* self) {
        auto* h = reinterpret_cast<JniCefClient*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(9) < 0) {return nullptr;}
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "getFrameHandler", "()Ljava/util/Optional;");
        if (!mid) {env->PopLocalFrame(nullptr); return nullptr;}
        auto jResult = (jobject)env->CallObjectMethod(h->javaHandler, mid);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return nullptr;}
        cef_frame_handler_t* nativeResult = nullptr;
        if (jResult) {
            auto _optCls = env->FindClass("java/util/Optional");
            auto _isPresentMid = env->GetMethodID(_optCls, "isPresent", "()Z");
            if (env->CallBooleanMethod(jResult, _isPresentMid) == JNI_TRUE) {
                auto _getMid = env->GetMethodID(_optCls, "get", "()Ljava/lang/Object;");
                auto _handlerObj = env->CallObjectMethod(jResult, _getMid);
                nativeResult = Create_JniCefFrameHandler(env, _handlerObj);
            }
        }
        env->PopLocalFrame(nullptr);
        return nativeResult;
    }

    static cef_permission_handler_t* CEF_CALLBACK _get_permission_handler(cef_client_t* self) {
        auto* h = reinterpret_cast<JniCefClient*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(9) < 0) {return nullptr;}
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "getPermissionHandler", "()Ljava/util/Optional;");
        if (!mid) {env->PopLocalFrame(nullptr); return nullptr;}
        auto jResult = (jobject)env->CallObjectMethod(h->javaHandler, mid);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return nullptr;}
        cef_permission_handler_t* nativeResult = nullptr;
        if (jResult) {
            auto _optCls = env->FindClass("java/util/Optional");
            auto _isPresentMid = env->GetMethodID(_optCls, "isPresent", "()Z");
            if (env->CallBooleanMethod(jResult, _isPresentMid) == JNI_TRUE) {
                auto _getMid = env->GetMethodID(_optCls, "get", "()Ljava/lang/Object;");
                auto _handlerObj = env->CallObjectMethod(jResult, _getMid);
                nativeResult = Create_JniCefPermissionHandler(env, _handlerObj);
            }
        }
        env->PopLocalFrame(nullptr);
        return nativeResult;
    }

    static cef_jsdialog_handler_t* CEF_CALLBACK _get_jsdialog_handler(cef_client_t* self) {
        auto* h = reinterpret_cast<JniCefClient*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(9) < 0) {return nullptr;}
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "getJsDialogHandler", "()Ljava/util/Optional;");
        if (!mid) {env->PopLocalFrame(nullptr); return nullptr;}
        auto jResult = (jobject)env->CallObjectMethod(h->javaHandler, mid);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return nullptr;}
        cef_jsdialog_handler_t* nativeResult = nullptr;
        if (jResult) {
            auto _optCls = env->FindClass("java/util/Optional");
            auto _isPresentMid = env->GetMethodID(_optCls, "isPresent", "()Z");
            if (env->CallBooleanMethod(jResult, _isPresentMid) == JNI_TRUE) {
                auto _getMid = env->GetMethodID(_optCls, "get", "()Ljava/lang/Object;");
                auto _handlerObj = env->CallObjectMethod(jResult, _getMid);
                nativeResult = Create_JniCefJsDialogHandler(env, _handlerObj);
            }
        }
        env->PopLocalFrame(nullptr);
        return nativeResult;
    }

    static cef_keyboard_handler_t* CEF_CALLBACK _get_keyboard_handler(cef_client_t* self) {
        auto* h = reinterpret_cast<JniCefClient*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(9) < 0) {return nullptr;}
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "getKeyboardHandler", "()Ljava/util/Optional;");
        if (!mid) {env->PopLocalFrame(nullptr); return nullptr;}
        auto jResult = (jobject)env->CallObjectMethod(h->javaHandler, mid);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return nullptr;}
        cef_keyboard_handler_t* nativeResult = nullptr;
        if (jResult) {
            auto _optCls = env->FindClass("java/util/Optional");
            auto _isPresentMid = env->GetMethodID(_optCls, "isPresent", "()Z");
            if (env->CallBooleanMethod(jResult, _isPresentMid) == JNI_TRUE) {
                auto _getMid = env->GetMethodID(_optCls, "get", "()Ljava/lang/Object;");
                auto _handlerObj = env->CallObjectMethod(jResult, _getMid);
                nativeResult = Create_JniCefKeyboardHandler(env, _handlerObj);
            }
        }
        env->PopLocalFrame(nullptr);
        return nativeResult;
    }

    static cef_life_span_handler_t* CEF_CALLBACK _get_life_span_handler(cef_client_t* self) {
        auto* h = reinterpret_cast<JniCefClient*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(9) < 0) {return nullptr;}
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "getLifeSpanHandler", "()Ljava/util/Optional;");
        if (!mid) {env->PopLocalFrame(nullptr); return nullptr;}
        auto jResult = (jobject)env->CallObjectMethod(h->javaHandler, mid);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return nullptr;}
        cef_life_span_handler_t* nativeResult = nullptr;
        if (jResult) {
            auto _optCls = env->FindClass("java/util/Optional");
            auto _isPresentMid = env->GetMethodID(_optCls, "isPresent", "()Z");
            if (env->CallBooleanMethod(jResult, _isPresentMid) == JNI_TRUE) {
                auto _getMid = env->GetMethodID(_optCls, "get", "()Ljava/lang/Object;");
                auto _handlerObj = env->CallObjectMethod(jResult, _getMid);
                nativeResult = Create_JniCefLifeSpanHandler(env, _handlerObj);
            }
        }
        env->PopLocalFrame(nullptr);
        return nativeResult;
    }

    static cef_load_handler_t* CEF_CALLBACK _get_load_handler(cef_client_t* self) {
        auto* h = reinterpret_cast<JniCefClient*>(self);
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

    static cef_print_handler_t* CEF_CALLBACK _get_print_handler(cef_client_t* self) {
        auto* h = reinterpret_cast<JniCefClient*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(9) < 0) {return nullptr;}
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "getPrintHandler", "()Ljava/util/Optional;");
        if (!mid) {env->PopLocalFrame(nullptr); return nullptr;}
        auto jResult = (jobject)env->CallObjectMethod(h->javaHandler, mid);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return nullptr;}
        cef_print_handler_t* nativeResult = nullptr;
        if (jResult) {
            auto _optCls = env->FindClass("java/util/Optional");
            auto _isPresentMid = env->GetMethodID(_optCls, "isPresent", "()Z");
            if (env->CallBooleanMethod(jResult, _isPresentMid) == JNI_TRUE) {
                auto _getMid = env->GetMethodID(_optCls, "get", "()Ljava/lang/Object;");
                auto _handlerObj = env->CallObjectMethod(jResult, _getMid);
                nativeResult = Create_JniCefPrintHandler(env, _handlerObj);
            }
        }
        env->PopLocalFrame(nullptr);
        return nativeResult;
    }

    static cef_render_handler_t* CEF_CALLBACK _get_render_handler(cef_client_t* self) {
        auto* h = reinterpret_cast<JniCefClient*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(9) < 0) {return nullptr;}
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "getRenderHandler", "()Ljava/util/Optional;");
        if (!mid) {env->PopLocalFrame(nullptr); return nullptr;}
        auto jResult = (jobject)env->CallObjectMethod(h->javaHandler, mid);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return nullptr;}
        cef_render_handler_t* nativeResult = nullptr;
        if (jResult) {
            auto _optCls = env->FindClass("java/util/Optional");
            auto _isPresentMid = env->GetMethodID(_optCls, "isPresent", "()Z");
            if (env->CallBooleanMethod(jResult, _isPresentMid) == JNI_TRUE) {
                auto _getMid = env->GetMethodID(_optCls, "get", "()Ljava/lang/Object;");
                auto _handlerObj = env->CallObjectMethod(jResult, _getMid);
                nativeResult = Create_JniCefRenderHandler(env, _handlerObj);
            }
        }
        env->PopLocalFrame(nullptr);
        return nativeResult;
    }

    static cef_request_handler_t* CEF_CALLBACK _get_request_handler(cef_client_t* self) {
        auto* h = reinterpret_cast<JniCefClient*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(9) < 0) {return nullptr;}
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "getRequestHandler", "()Ljava/util/Optional;");
        if (!mid) {env->PopLocalFrame(nullptr); return nullptr;}
        auto jResult = (jobject)env->CallObjectMethod(h->javaHandler, mid);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return nullptr;}
        cef_request_handler_t* nativeResult = nullptr;
        if (jResult) {
            auto _optCls = env->FindClass("java/util/Optional");
            auto _isPresentMid = env->GetMethodID(_optCls, "isPresent", "()Z");
            if (env->CallBooleanMethod(jResult, _isPresentMid) == JNI_TRUE) {
                auto _getMid = env->GetMethodID(_optCls, "get", "()Ljava/lang/Object;");
                auto _handlerObj = env->CallObjectMethod(jResult, _getMid);
                nativeResult = Create_JniCefRequestHandler(env, _handlerObj);
            }
        }
        env->PopLocalFrame(nullptr);
        return nativeResult;
    }

    static int CEF_CALLBACK _on_process_message_received(cef_client_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, cef_process_id_t source_process, struct _cef_process_message_t* message) {
        auto* h = reinterpret_cast<JniCefClient*>(self);
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

extern "C" cef_client_t* Create_JniCefClient(JNIEnv *env, jobject handler) {
    JavaVM *jvm;
    env->GetJavaVM(&jvm);
    auto globalRef = env->NewGlobalRef(handler);
    return reinterpret_cast<cef_client_t*>(new JniCefClient(jvm, globalRef));
}

extern "C" JNIEXPORT jlong JNICALL Java_net_kurobako_cef4j_gen_CefClient_1N_N_1Create(JNIEnv* env, jobject obj) {
    return reinterpret_cast<jlong>(Create_JniCefClient(env, obj));
}
