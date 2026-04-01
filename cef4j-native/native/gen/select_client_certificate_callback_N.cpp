// GENERATED - do not edit.
#include <jni.h>
#include "include/capi/cef_request_handler_capi.h"
#include "include/capi/cef_x509_certificate_capi.h"
#include "jni_util.h"

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefSelectClientCertificateCallback_00024NativePeer_N_1Release(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefSelectClientCertificateCallback_00024NativePeer_N_1Select(JNIEnv* env, jobject obj, jlong self, jobject cert) {
    auto* s = reinterpret_cast<cef_select_client_certificate_callback_t*>(self);
    if (!s) return;
    cef_x509_certificate_t* _cert_ptr = cert ? reinterpret_cast<cef_x509_certificate_t*>(env->GetLongField(cert, env->GetFieldID(env->GetObjectClass(cert), "nativePtr", "J"))) : nullptr;
    if (_cert_ptr) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_cert_ptr); _b->add_ref(_b);}
    s->select(s, _cert_ptr);
}
