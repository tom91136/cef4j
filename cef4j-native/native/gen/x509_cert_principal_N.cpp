// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
#include <jni.h>
#include "include/capi/cef_x509_certificate_capi.h"
#include "jni_util.h"

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefX509CertPrincipal), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefX509CertPrincipal), getDisplayName0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_x509_cert_principal_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_display_name(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefX509CertPrincipal), getCommonName0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_x509_cert_principal_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_common_name(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefX509CertPrincipal), getLocalityName0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_x509_cert_principal_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_locality_name(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefX509CertPrincipal), getStateOrProvinceName0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_x509_cert_principal_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_state_or_province_name(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefX509CertPrincipal), getCountryName0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_x509_cert_principal_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_country_name(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefX509CertPrincipal), getOrganizationNames0)(JNIEnv* env, jobject obj, jlong self, jobject names) {
    auto* s = reinterpret_cast<cef_x509_cert_principal_t*>(self);
    if (!s) return;
    if (!names) { env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "names must not be null"); return; }
    auto _names_csl = JavaListToCefStringList(env, names);
    s->get_organization_names(s, _names_csl);
    CefStringListWriteBack(env, _names_csl, names);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefX509CertPrincipal), getOrganizationUnitNames0)(JNIEnv* env, jobject obj, jlong self, jobject names) {
    auto* s = reinterpret_cast<cef_x509_cert_principal_t*>(self);
    if (!s) return;
    if (!names) { env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "names must not be null"); return; }
    auto _names_csl = JavaListToCefStringList(env, names);
    s->get_organization_unit_names(s, _names_csl);
    CefStringListWriteBack(env, _names_csl, names);
}
