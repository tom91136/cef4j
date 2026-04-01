// GENERATED - do not edit.
#include <jni.h>
#include "include/capi/cef_ssl_status_capi.h"
#include "include/capi/cef_x509_certificate_capi.h"
#include "jni_util.h"

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefSslStatus_00024NativePeer_N_1Release(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

extern "C" JNIEXPORT jboolean JNICALL Java_net_kurobako_cef4j_gen_CefSslStatus_00024NativePeer_N_1IsSecureConnection(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_sslstatus_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_secure_connection(s);
    return static_cast<jboolean>(_r);
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefSslStatus_00024NativePeer_N_1GetCertStatus(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_sslstatus_t*>(self);
    if (!s) return 0;
    auto _r = s->get_cert_status(s);
    auto _eCls = env->FindClass("net/kurobako/cef4j/gen/CefCertStatus");
    auto _eOf = env->GetStaticMethodID(_eCls, "of", "(J)Lnet/kurobako/cef4j/gen/CefCertStatus;");
    return env->CallStaticObjectMethod(_eCls, _eOf, static_cast<jlong>(_r));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefSslStatus_00024NativePeer_N_1GetSslVersion(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_sslstatus_t*>(self);
    if (!s) return 0;
    auto _r = s->get_sslversion(s);
    auto _eCls = env->FindClass("net/kurobako/cef4j/gen/CefSslVersion");
    auto _eOf = env->GetStaticMethodID(_eCls, "of", "(J)Lnet/kurobako/cef4j/gen/CefSslVersion;");
    return env->CallStaticObjectMethod(_eCls, _eOf, static_cast<jlong>(_r));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefSslStatus_00024NativePeer_N_1GetContentStatus(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_sslstatus_t*>(self);
    if (!s) return 0;
    auto _r = s->get_content_status(s);
    auto _eCls = env->FindClass("net/kurobako/cef4j/gen/CefSslContentStatus");
    auto _eOf = env->GetStaticMethodID(_eCls, "of", "(J)Lnet/kurobako/cef4j/gen/CefSslContentStatus;");
    return env->CallStaticObjectMethod(_eCls, _eOf, static_cast<jlong>(_r));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefSslStatus_00024NativePeer_N_1GetX509certificate(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_sslstatus_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_x509_certificate(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefX509Certificate$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}
