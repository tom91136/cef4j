// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
#include <jni.h>
#include "include/capi/cef_request_handler_capi.h"
#include "include/capi/cef_x509_certificate_capi.h"
#include "jni_util.h"

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefSelectClientCertificateCallback), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefSelectClientCertificateCallback), select0)(JNIEnv* env, jobject obj, jlong self, jobject cert) {
    auto* s = reinterpret_cast<cef_select_client_certificate_callback_t*>(self);
    if (!s) return;
    cef_x509_certificate_t* _cert_ptr = cert ? reinterpret_cast<cef_x509_certificate_t*>(env->GetLongField(cert, env->GetFieldID(env->GetObjectClass(cert), "nativePtr", "J"))) : nullptr;
    if (_cert_ptr) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_cert_ptr); _b->add_ref(_b); }
    s->select(s, _cert_ptr);
}
