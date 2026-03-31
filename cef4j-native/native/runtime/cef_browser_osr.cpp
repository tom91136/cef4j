// Hand-written CefBrowserOsr JNI bindings - stable across CEF versions.
//
// Implements the native methods for CefBrowserOsr.java. This is hand-written
// because browser creation, input dispatch, and host access involve
// platform-specific structs (cef_window_info_t, cef_mouse_event_t,
// cef_key_event_t) that are not amenable to codegen.

#include <jni.h>
#include <cstring>
#include "jni_util.h"
#include "include/capi/cef_browser_capi.h"
#include "include/capi/cef_client_capi.h"

// Forward: GetJVM from jni_util.cpp
extern JavaVM* GetJVM();

// Forward: factory from generated client.cpp
extern "C" cef_client_t* Create_JniCefClient(JNIEnv* env, jobject handler);

// ---------------------------------------------------------------------------
// Browser creation
// ---------------------------------------------------------------------------

extern "C" JNIEXPORT jlong JNICALL
Java_net_kurobako_cef4j_CefBrowserOsr_N_1CreateBrowserSync(
    JNIEnv* env, jclass /*cls*/, jobject client, jstring url, jint frameRate) {

    // Window info for OSR
    cef_window_info_t windowInfo{};
    memset(&windowInfo, 0, sizeof(windowInfo));
    windowInfo.size = sizeof(cef_window_info_t);
    windowInfo.windowless_rendering_enabled = 1;

    // Browser settings - defaults
    cef_browser_settings_t settings{};
    settings.size = sizeof(cef_browser_settings_t);
    settings.windowless_frame_rate = frameRate > 0 ? frameRate : 60;

    // Convert URL
    cef_string_t cefUrl{};
    if (url) {
        JStringToCefString(env, url, &cefUrl);
    }

    // Marshal the Java CefClient to a native cef_client_t*
    cef_client_t* nativeClient = client ? Create_JniCefClient(env, client) : nullptr;
    cef_browser_t* browser = cef_browser_host_create_browser_sync(
        &windowInfo, nativeClient, &cefUrl, &settings, nullptr, nullptr);

    cef_string_clear(&cefUrl);

    return reinterpret_cast<jlong>(browser);
}

// ---------------------------------------------------------------------------
// Browser methods
// ---------------------------------------------------------------------------

extern "C" JNIEXPORT jint JNICALL
Java_net_kurobako_cef4j_CefBrowserOsr_N_1GetIdentifier(
    JNIEnv* /*env*/, jclass /*cls*/, jlong browser) {
    auto* b = reinterpret_cast<cef_browser_t*>(browser);
    if (!b) return -1;
    return b->get_identifier(b);
}

extern "C" JNIEXPORT void JNICALL
Java_net_kurobako_cef4j_CefBrowserOsr_N_1LoadURL(
    JNIEnv* env, jclass /*cls*/, jlong browser, jstring url) {
    auto* b = reinterpret_cast<cef_browser_t*>(browser);
    if (!b) return;
    cef_frame_t* frame = b->get_main_frame(b);
    if (!frame) return;
    cef_string_t cefUrl{};
    JStringToCefString(env, url, &cefUrl);
    frame->load_url(frame, &cefUrl);
    cef_string_clear(&cefUrl);
    frame->base.release(&frame->base);
}

extern "C" JNIEXPORT void JNICALL
Java_net_kurobako_cef4j_CefBrowserOsr_N_1ExecuteJavaScript(
    JNIEnv* env, jclass /*cls*/, jlong browser,
    jstring code, jstring url, jint line) {
    auto* b = reinterpret_cast<cef_browser_t*>(browser);
    if (!b) return;
    cef_frame_t* frame = b->get_main_frame(b);
    if (!frame) return;
    cef_string_t cefCode{}, cefUrl{};
    JStringToCefString(env, code, &cefCode);
    JStringToCefString(env, url, &cefUrl);
    frame->execute_java_script(frame, &cefCode, &cefUrl, line);
    cef_string_clear(&cefCode);
    cef_string_clear(&cefUrl);
    frame->base.release(&frame->base);
}

extern "C" JNIEXPORT jint JNICALL
Java_net_kurobako_cef4j_CefBrowserOsr_N_1CanGoBack(
    JNIEnv* /*env*/, jclass /*cls*/, jlong browser) {
    auto* b = reinterpret_cast<cef_browser_t*>(browser);
    return b ? b->can_go_back(b) : 0;
}

extern "C" JNIEXPORT void JNICALL
Java_net_kurobako_cef4j_CefBrowserOsr_N_1GoBack(
    JNIEnv* /*env*/, jclass /*cls*/, jlong browser) {
    auto* b = reinterpret_cast<cef_browser_t*>(browser);
    if (b) b->go_back(b);
}

extern "C" JNIEXPORT jint JNICALL
Java_net_kurobako_cef4j_CefBrowserOsr_N_1CanGoForward(
    JNIEnv* /*env*/, jclass /*cls*/, jlong browser) {
    auto* b = reinterpret_cast<cef_browser_t*>(browser);
    return b ? b->can_go_forward(b) : 0;
}

extern "C" JNIEXPORT void JNICALL
Java_net_kurobako_cef4j_CefBrowserOsr_N_1GoForward(
    JNIEnv* /*env*/, jclass /*cls*/, jlong browser) {
    auto* b = reinterpret_cast<cef_browser_t*>(browser);
    if (b) b->go_forward(b);
}

extern "C" JNIEXPORT void JNICALL
Java_net_kurobako_cef4j_CefBrowserOsr_N_1Reload(
    JNIEnv* /*env*/, jclass /*cls*/, jlong browser) {
    auto* b = reinterpret_cast<cef_browser_t*>(browser);
    if (b) b->reload(b);
}

extern "C" JNIEXPORT void JNICALL
Java_net_kurobako_cef4j_CefBrowserOsr_N_1StopLoad(
    JNIEnv* /*env*/, jclass /*cls*/, jlong browser) {
    auto* b = reinterpret_cast<cef_browser_t*>(browser);
    if (b) b->stop_load(b);
}

extern "C" JNIEXPORT jlong JNICALL
Java_net_kurobako_cef4j_CefBrowserOsr_N_1GetHost(
    JNIEnv* /*env*/, jclass /*cls*/, jlong browser) {
    auto* b = reinterpret_cast<cef_browser_t*>(browser);
    if (!b) return 0;
    return reinterpret_cast<jlong>(b->get_host(b));
}

// ---------------------------------------------------------------------------
// Host methods (input dispatch, OSR notifications)
// ---------------------------------------------------------------------------

extern "C" JNIEXPORT void JNICALL
Java_net_kurobako_cef4j_CefBrowserOsr_N_1WasResized(
    JNIEnv* /*env*/, jclass /*cls*/, jlong host) {
    auto* h = reinterpret_cast<cef_browser_host_t*>(host);
    if (h) h->was_resized(h);
}

extern "C" JNIEXPORT void JNICALL
Java_net_kurobako_cef4j_CefBrowserOsr_N_1Invalidate(
    JNIEnv* /*env*/, jclass /*cls*/, jlong host) {
    auto* h = reinterpret_cast<cef_browser_host_t*>(host);
    if (h) h->invalidate(h, PET_VIEW);
}

extern "C" JNIEXPORT void JNICALL
Java_net_kurobako_cef4j_CefBrowserOsr_N_1SendMouseClickEvent(
    JNIEnv* /*env*/, jclass /*cls*/, jlong host,
    jint x, jint y, jint modifiers,
    jint buttonType, jint mouseUp, jint clickCount) {
    auto* h = reinterpret_cast<cef_browser_host_t*>(host);
    if (!h) return;
    cef_mouse_event_t event{};
    event.x = x;
    event.y = y;
    event.modifiers = static_cast<uint32_t>(modifiers);
    h->send_mouse_click_event(h, &event,
        static_cast<cef_mouse_button_type_t>(buttonType),
        mouseUp, clickCount);
}

extern "C" JNIEXPORT void JNICALL
Java_net_kurobako_cef4j_CefBrowserOsr_N_1SendMouseMoveEvent(
    JNIEnv* /*env*/, jclass /*cls*/, jlong host,
    jint x, jint y, jint modifiers, jint mouseLeave) {
    auto* h = reinterpret_cast<cef_browser_host_t*>(host);
    if (!h) return;
    cef_mouse_event_t event{};
    event.x = x;
    event.y = y;
    event.modifiers = static_cast<uint32_t>(modifiers);
    h->send_mouse_move_event(h, &event, mouseLeave);
}

extern "C" JNIEXPORT void JNICALL
Java_net_kurobako_cef4j_CefBrowserOsr_N_1SendMouseWheelEvent(
    JNIEnv* /*env*/, jclass /*cls*/, jlong host,
    jint x, jint y, jint modifiers, jint deltaX, jint deltaY) {
    auto* h = reinterpret_cast<cef_browser_host_t*>(host);
    if (!h) return;
    cef_mouse_event_t event{};
    event.x = x;
    event.y = y;
    event.modifiers = static_cast<uint32_t>(modifiers);
    h->send_mouse_wheel_event(h, &event, deltaX, deltaY);
}

extern "C" JNIEXPORT void JNICALL
Java_net_kurobako_cef4j_CefBrowserOsr_N_1SendKeyEvent(
    JNIEnv* /*env*/, jclass /*cls*/, jlong host,
    jint type, jint modifiers, jint windowsKeyCode, jint nativeKeyCode,
    jchar character, jchar unmodifiedCharacter, jint isSystemKey) {
    auto* h = reinterpret_cast<cef_browser_host_t*>(host);
    if (!h) return;
    cef_key_event_t event{};
    event.type = static_cast<cef_key_event_type_t>(type);
    event.modifiers = static_cast<uint32_t>(modifiers);
    event.windows_key_code = windowsKeyCode;
    event.native_key_code = nativeKeyCode;
    event.character = character;
    event.unmodified_character = unmodifiedCharacter;
    event.is_system_key = isSystemKey;
    h->send_key_event(h, &event);
}

extern "C" JNIEXPORT void JNICALL
Java_net_kurobako_cef4j_CefBrowserOsr_N_1CloseBrowser(
    JNIEnv* /*env*/, jclass /*cls*/, jlong host, jint forceClose) {
    auto* h = reinterpret_cast<cef_browser_host_t*>(host);
    if (h) h->close_browser(h, forceClose);
}

extern "C" JNIEXPORT void JNICALL
Java_net_kurobako_cef4j_CefBrowserOsr_N_1SetFocus(
    JNIEnv* /*env*/, jclass /*cls*/, jlong host, jint focus) {
    auto* h = reinterpret_cast<cef_browser_host_t*>(host);
    if (h) h->set_focus(h, focus);
}
