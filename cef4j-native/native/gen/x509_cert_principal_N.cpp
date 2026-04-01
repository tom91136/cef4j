// GENERATED - do not edit.
#include <jni.h>
#include "include/capi/cef_x509_certificate_capi.h"
#include "jni_util.h"

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefX509CertPrincipal_00024NativePeer_N_1Release(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefX509CertPrincipal_00024NativePeer_N_1GetDisplayName(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_x509_cert_principal_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_display_name(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefX509CertPrincipal_00024NativePeer_N_1GetCommonName(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_x509_cert_principal_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_common_name(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefX509CertPrincipal_00024NativePeer_N_1GetLocalityName(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_x509_cert_principal_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_locality_name(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefX509CertPrincipal_00024NativePeer_N_1GetStateOrProvinceName(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_x509_cert_principal_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_state_or_province_name(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT jstring JNICALL Java_net_kurobako_cef4j_gen_CefX509CertPrincipal_00024NativePeer_N_1GetCountryName(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_x509_cert_principal_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_country_name(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefX509CertPrincipal_00024NativePeer_N_1GetOrganizationNames(JNIEnv* env, jobject obj, jlong self, jobject names) {
    auto* s = reinterpret_cast<cef_x509_cert_principal_t*>(self);
    if (!s) return;
    if (!names) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "names must not be null"); return;}
    auto _names_csl = JavaListToCefStringList(env, names);
    s->get_organization_names(s, _names_csl);
    CefStringListWriteBack(env, _names_csl, names);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefX509CertPrincipal_00024NativePeer_N_1GetOrganizationUnitNames(JNIEnv* env, jobject obj, jlong self, jobject names) {
    auto* s = reinterpret_cast<cef_x509_cert_principal_t*>(self);
    if (!s) return;
    if (!names) {env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "names must not be null"); return;}
    auto _names_csl = JavaListToCefStringList(env, names);
    s->get_organization_unit_names(s, _names_csl);
    CefStringListWriteBack(env, _names_csl, names);
}
