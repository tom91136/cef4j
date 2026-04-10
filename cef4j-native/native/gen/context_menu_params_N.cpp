// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
#include <jni.h>
#include "include/capi/cef_context_menu_handler_capi.h"
#include "jni_util.h"

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefContextMenuParams), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

CEF4J_JNI_EXPORT(jint, CEF4J_PEER(CefContextMenuParams), getXCoord0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_context_menu_params_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->get_xcoord(s));
}

CEF4J_JNI_EXPORT(jint, CEF4J_PEER(CefContextMenuParams), getYCoord0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_context_menu_params_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->get_ycoord(s));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefContextMenuParams), getTypeFlags0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_context_menu_params_t*>(self);
    if (!s) return 0;
    auto _r = s->get_type_flags(s);
    auto _eCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefContextMenuTypeFlags");
    auto _eOf = env->GetStaticMethodID(_eCls, "of", "(J)Lnet/kurobako/cef4j/gen/CefContextMenuTypeFlags;");
    return env->CallStaticObjectMethod(_eCls, _eOf, static_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefContextMenuParams), getLinkUrl0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_context_menu_params_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_link_url(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefContextMenuParams), getUnfilteredLinkUrl0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_context_menu_params_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_unfiltered_link_url(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefContextMenuParams), getSourceUrl0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_context_menu_params_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_source_url(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefContextMenuParams), hasImageContents0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_context_menu_params_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->has_image_contents(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefContextMenuParams), getTitleText0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_context_menu_params_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_title_text(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefContextMenuParams), getPageUrl0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_context_menu_params_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_page_url(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefContextMenuParams), getFrameUrl0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_context_menu_params_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_frame_url(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefContextMenuParams), getFrameCharset0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_context_menu_params_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_frame_charset(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefContextMenuParams), getMediaType0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_context_menu_params_t*>(self);
    if (!s) return 0;
    auto _r = s->get_media_type(s);
    auto _eCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefContextMenuMediaType");
    auto _eOf = env->GetStaticMethodID(_eCls, "of", "(J)Lnet/kurobako/cef4j/gen/CefContextMenuMediaType;");
    return env->CallStaticObjectMethod(_eCls, _eOf, static_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefContextMenuParams), getMediaStateFlags0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_context_menu_params_t*>(self);
    if (!s) return 0;
    auto _r = s->get_media_state_flags(s);
    auto _eCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefContextMenuMediaStateFlags");
    auto _eOf = env->GetStaticMethodID(_eCls, "of", "(J)Lnet/kurobako/cef4j/gen/CefContextMenuMediaStateFlags;");
    return env->CallStaticObjectMethod(_eCls, _eOf, static_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefContextMenuParams), getSelectionText0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_context_menu_params_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_selection_text(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefContextMenuParams), getMisspelledWord0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_context_menu_params_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_misspelled_word(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefContextMenuParams), getDictionarySuggestions0)(JNIEnv* env, jobject obj, jlong self, jobject suggestions) {
    auto* s = reinterpret_cast<cef_context_menu_params_t*>(self);
    if (!s) return JNI_FALSE;
    if (!suggestions) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "suggestions must not be null"); return JNI_FALSE; }
    auto _suggestions_csl = JavaListToCefStringList(env, suggestions);
    auto _r = s->get_dictionary_suggestions(s, _suggestions_csl);
    CefStringListWriteBack(env, _suggestions_csl, suggestions);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefContextMenuParams), isEditable0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_context_menu_params_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_editable(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefContextMenuParams), isSpellCheckEnabled0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_context_menu_params_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_spell_check_enabled(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefContextMenuParams), getEditStateFlags0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_context_menu_params_t*>(self);
    if (!s) return 0;
    auto _r = s->get_edit_state_flags(s);
    auto _eCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefContextMenuEditStateFlags");
    auto _eOf = env->GetStaticMethodID(_eCls, "of", "(J)Lnet/kurobako/cef4j/gen/CefContextMenuEditStateFlags;");
    return env->CallStaticObjectMethod(_eCls, _eOf, static_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefContextMenuParams), isCustomMenu0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_context_menu_params_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_custom_menu(s);
    return static_cast<jboolean>(_r);
}
