// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
#include <jni.h>
#include "include/capi/cef_ssl_status_capi.h"
#include "include/capi/cef_x509_certificate_capi.h"
#include "jni_util.h"

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefSslStatus), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefSslStatus), isSecureConnection0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_sslstatus_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_secure_connection(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefSslStatus), getCertStatus0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_sslstatus_t*>(self);
    if (!s) return 0;
    auto _r = s->get_cert_status(s);
    auto _eCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefCertStatus");
    auto _eOf = env->GetStaticMethodID(_eCls, "of", "(J)Lnet/kurobako/cef4j/gen/CefCertStatus;");
    return env->CallStaticObjectMethod(_eCls, _eOf, static_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefSslStatus), getSslVersion0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_sslstatus_t*>(self);
    if (!s) return 0;
    auto _r = s->get_sslversion(s);
    auto _eCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefSslVersion");
    auto _eOf = env->GetStaticMethodID(_eCls, "of", "(J)Lnet/kurobako/cef4j/gen/CefSslVersion;");
    return env->CallStaticObjectMethod(_eCls, _eOf, static_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefSslStatus), getContentStatus0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_sslstatus_t*>(self);
    if (!s) return 0;
    auto _r = s->get_content_status(s);
    auto _eCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefSslContentStatus");
    auto _eOf = env->GetStaticMethodID(_eCls, "of", "(J)Lnet/kurobako/cef4j/gen/CefSslContentStatus;");
    return env->CallStaticObjectMethod(_eCls, _eOf, static_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefSslStatus), getX509Certificate0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_sslstatus_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_x509_certificate(s);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefX509Certificate$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}
