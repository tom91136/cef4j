// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
#include <jni.h>
#include "include/capi/cef_ssl_info_capi.h"
#include "include/capi/cef_x509_certificate_capi.h"
#include "jni_util.h"

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefSslInfo), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefSslInfo), getCertStatus0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_sslinfo_t*>(self);
    if (!s) return 0;
    auto _r = s->get_cert_status(s);
    auto _eCls = env->FindClass("net/kurobako/cef4j/gen/CefCertStatus");
    auto _eOf = env->GetStaticMethodID(_eCls, "of", "(J)Lnet/kurobako/cef4j/gen/CefCertStatus;");
    return env->CallStaticObjectMethod(_eCls, _eOf, static_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefSslInfo), getX509certificate0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_sslinfo_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_x509_certificate(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefX509Certificate$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}
