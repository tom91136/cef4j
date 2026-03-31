// Hand-written JNI factory forwarders for handler types used by CefClient.
//
// CefClient's get_xxx_handler() methods return native handler struct pointers.
// The Java CefClient creates these wrappers eagerly via addXxxHandler() and
// stores the native pointer. These JNI methods forward to the generated
// Create_JniCefXxx factory functions.

#include <jni.h>
#include "include/capi/cef_client_capi.h"

// Forward-declare handler factory functions from generated code
extern "C" {
    _cef_audio_handler_t* Create_JniCefAudioHandler(JNIEnv*, jobject);
    _cef_command_handler_t* Create_JniCefCommandHandler(JNIEnv*, jobject);
    _cef_context_menu_handler_t* Create_JniCefContextMenuHandler(JNIEnv*, jobject);
    _cef_dialog_handler_t* Create_JniCefDialogHandler(JNIEnv*, jobject);
    _cef_display_handler_t* Create_JniCefDisplayHandler(JNIEnv*, jobject);
    _cef_download_handler_t* Create_JniCefDownloadHandler(JNIEnv*, jobject);
    _cef_drag_handler_t* Create_JniCefDragHandler(JNIEnv*, jobject);
    _cef_find_handler_t* Create_JniCefFindHandler(JNIEnv*, jobject);
    _cef_focus_handler_t* Create_JniCefFocusHandler(JNIEnv*, jobject);
    _cef_frame_handler_t* Create_JniCefFrameHandler(JNIEnv*, jobject);
    _cef_jsdialog_handler_t* Create_JniCefJsdialogHandler(JNIEnv*, jobject);
    _cef_keyboard_handler_t* Create_JniCefKeyboardHandler(JNIEnv*, jobject);
    _cef_life_span_handler_t* Create_JniCefLifeSpanHandler(JNIEnv*, jobject);
    _cef_load_handler_t* Create_JniCefLoadHandler(JNIEnv*, jobject);
    _cef_permission_handler_t* Create_JniCefPermissionHandler(JNIEnv*, jobject);
    _cef_print_handler_t* Create_JniCefPrintHandler(JNIEnv*, jobject);
    _cef_render_handler_t* Create_JniCefRenderHandler(JNIEnv*, jobject);
    _cef_request_handler_t* Create_JniCefRequestHandler(JNIEnv*, jobject);
}

// Macro to generate a JNI factory method for each handler type.
// Maps Java_net_kurobako_cef4j_CefClient_N_1CreateXxx(handler) -> Create_JniCefXxx(env, handler)
#define HANDLER_FACTORY(Name) \
    extern "C" JNIEXPORT jlong JNICALL \
    Java_net_kurobako_cef4j_CefClient_N_1Create##Name( \
        JNIEnv* env, jclass, jobject handler) { \
        if (!handler) return 0; \
        return reinterpret_cast<jlong>(Create_JniCef##Name(env, handler)); \
    }

HANDLER_FACTORY(AudioHandler)
HANDLER_FACTORY(CommandHandler)
HANDLER_FACTORY(ContextMenuHandler)
HANDLER_FACTORY(DialogHandler)
HANDLER_FACTORY(DisplayHandler)
HANDLER_FACTORY(DownloadHandler)
HANDLER_FACTORY(DragHandler)
HANDLER_FACTORY(FindHandler)
HANDLER_FACTORY(FocusHandler)
HANDLER_FACTORY(FrameHandler)
HANDLER_FACTORY(JsdialogHandler)
HANDLER_FACTORY(KeyboardHandler)
HANDLER_FACTORY(LifeSpanHandler)
HANDLER_FACTORY(LoadHandler)
HANDLER_FACTORY(PermissionHandler)
HANDLER_FACTORY(PrintHandler)
HANDLER_FACTORY(RenderHandler)
HANDLER_FACTORY(RequestHandler)
