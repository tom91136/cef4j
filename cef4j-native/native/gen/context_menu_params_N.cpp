// GENERATED - do not edit.
#include <jni.h>
#include "include/capi/cef_context_menu_handler_capi.h"
#include "jni_util.h"

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefContextMenuParams_00024NativePeer_N_1Release(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

extern "C" JNIEXPORT jint JNICALL Java_net_kurobako_cef4j_gen_CefContextMenuParams_00024NativePeer_N_1GetXCoord(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_context_menu_params_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->get_xcoord(s));
}

extern "C" JNIEXPORT jint JNICALL Java_net_kurobako_cef4j_gen_CefContextMenuParams_00024NativePeer_N_1GetYCoord(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_context_menu_params_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->get_ycoord(s));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefContextMenuParams_00024NativePeer_N_1GetTypeFlags(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_context_menu_params_t*>(self);
    if (!s) return 0;
    auto _r = s->get_type_flags(s);
    auto _eCls = env->FindClass("net/kurobako/cef4j/gen/CefContextMenuTypeFlags");
    auto _eOf = env->GetStaticMethodID(_eCls, "of", "(J)Lnet/kurobako/cef4j/gen/CefContextMenuTypeFlags;");
    return env->CallStaticObjectMethod(_eCls, _eOf, static_cast<jlong>(_r));
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefContextMenuParams_00024NativePeer_N_1GetLinkUrl(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_context_menu_params_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_link_url(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefContextMenuParams_00024NativePeer_N_1GetUnfilteredLinkUrl(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_context_menu_params_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_unfiltered_link_url(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefContextMenuParams_00024NativePeer_N_1GetSourceUrl(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_context_menu_params_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_source_url(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefContextMenuParams_00024NativePeer_N_1HasImageContents(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_context_menu_params_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->has_image_contents(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefContextMenuParams_00024NativePeer_N_1GetTitleText(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_context_menu_params_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_title_text(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefContextMenuParams_00024NativePeer_N_1GetPageUrl(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_context_menu_params_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_page_url(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefContextMenuParams_00024NativePeer_N_1GetFrameUrl(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_context_menu_params_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_frame_url(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefContextMenuParams_00024NativePeer_N_1GetFrameCharset(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_context_menu_params_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_frame_charset(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefContextMenuParams_00024NativePeer_N_1GetMediaType(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_context_menu_params_t*>(self);
    if (!s) return 0;
    auto _r = s->get_media_type(s);
    auto _eCls = env->FindClass("net/kurobako/cef4j/gen/CefContextMenuMediaType");
    auto _eOf = env->GetStaticMethodID(_eCls, "of", "(J)Lnet/kurobako/cef4j/gen/CefContextMenuMediaType;");
    return env->CallStaticObjectMethod(_eCls, _eOf, static_cast<jlong>(_r));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefContextMenuParams_00024NativePeer_N_1GetMediaStateFlags(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_context_menu_params_t*>(self);
    if (!s) return 0;
    auto _r = s->get_media_state_flags(s);
    auto _eCls = env->FindClass("net/kurobako/cef4j/gen/CefContextMenuMediaStateFlags");
    auto _eOf = env->GetStaticMethodID(_eCls, "of", "(J)Lnet/kurobako/cef4j/gen/CefContextMenuMediaStateFlags;");
    return env->CallStaticObjectMethod(_eCls, _eOf, static_cast<jlong>(_r));
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefContextMenuParams_00024NativePeer_N_1GetSelectionText(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_context_menu_params_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_selection_text(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefContextMenuParams_00024NativePeer_N_1GetMisspelledWord(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_context_menu_params_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_misspelled_word(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefContextMenuParams_00024NativePeer_N_1GetDictionarySuggestions(JNIEnv* env, jobject obj, jlong self, jobject suggestions) {
    auto* s = reinterpret_cast<cef_context_menu_params_t*>(self);
    if (!s) return JNI_FALSE;
    if (!suggestions) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "suggestions must not be null"); return JNI_FALSE;}
    auto _suggestions_csl = JavaListToCefStringList(env, suggestions);
    auto _r = s->get_dictionary_suggestions(s, _suggestions_csl);
    CefStringListWriteBack(env, _suggestions_csl, suggestions);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefContextMenuParams_00024NativePeer_N_1IsEditable(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_context_menu_params_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_editable(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefContextMenuParams_00024NativePeer_N_1IsSpellCheckEnabled(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_context_menu_params_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_spell_check_enabled(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefContextMenuParams_00024NativePeer_N_1GetEditStateFlags(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_context_menu_params_t*>(self);
    if (!s) return 0;
    auto _r = s->get_edit_state_flags(s);
    auto _eCls = env->FindClass("net/kurobako/cef4j/gen/CefContextMenuEditStateFlags");
    auto _eOf = env->GetStaticMethodID(_eCls, "of", "(J)Lnet/kurobako/cef4j/gen/CefContextMenuEditStateFlags;");
    return env->CallStaticObjectMethod(_eCls, _eOf, static_cast<jlong>(_r));
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefContextMenuParams_00024NativePeer_N_1IsCustomMenu(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_context_menu_params_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_custom_menu(s);
    return static_cast<jboolean>(_r);
}
