// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
#include <jni.h>
#include "include/capi/cef_x509_certificate_capi.h"
#include "include/capi/cef_values_capi.h"
#include "jni_util.h"

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefX509Certificate), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefX509Certificate), getSubject0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_x509_certificate_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_subject(s);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefX509CertPrincipal$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefX509Certificate), getIssuer0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_x509_certificate_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_issuer(s);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefX509CertPrincipal$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefX509Certificate), getSerialNumber0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_x509_certificate_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_serial_number(s);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefBinaryValue$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefX509Certificate), getValidStart0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_x509_certificate_t*>(self);
    if (!s) return nullptr;
    cef_basetime_t result = s->get_valid_start(s);
    auto cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefBaseTime");
    auto ctor = env->GetMethodID(cls, "<init>", "(J)V");
    auto _dsResult = env->NewObject(cls, ctor, to_jlong(((&result))->val));
    return _dsResult;
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefX509Certificate), getValidExpiry0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_x509_certificate_t*>(self);
    if (!s) return nullptr;
    cef_basetime_t result = s->get_valid_expiry(s);
    auto cls = FindClassCached(env, "net/kurobako/cef4j/gen/CefBaseTime");
    auto ctor = env->GetMethodID(cls, "<init>", "(J)V");
    auto _dsResult = env->NewObject(cls, ctor, to_jlong(((&result))->val));
    return _dsResult;
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefX509Certificate), getDerEncoded0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_x509_certificate_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_derencoded(s);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefBinaryValue$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefX509Certificate), getPemEncoded0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_x509_certificate_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_pemencoded(s);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefBinaryValue$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jlong, CEF4J_PEER(CefX509Certificate), getIssuerChainSize0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_x509_certificate_t*>(self);
    if (!s) return 0;
    return static_cast<jlong>(s->get_issuer_chain_size(s));
}

CEF4J_JNI_EXPORT(jobjectArray, CEF4J_PEER(CefX509Certificate), getDerEncodedIssuerChain0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_x509_certificate_t*>(self);
    if (!s) return nullptr;
    size_t _count = s->get_issuer_chain_size(s);
    cef_binary_value_t** _arr = _count > 0 ? new cef_binary_value_t*[_count]() : nullptr;
    s->get_derencoded_issuer_chain(s, &_count, _arr);
    auto _elemCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefBinaryValue$NativePeer");
    auto _elemCtor = env->GetMethodID(_elemCls, "<init>", "(J)V");
    auto _result = env->NewObjectArray(static_cast<jsize>(_count), _elemCls, nullptr);
    for (size_t _i = 0; _i < _count; _i++) {
        if (_arr[_i]) {
            auto _peer = env->NewObject(_elemCls, _elemCtor, reinterpret_cast<jlong>(_arr[_i]));
            env->SetObjectArrayElement(_result, static_cast<jsize>(_i), _peer);
        }
    }
    delete[] _arr;
    return _result;
}

CEF4J_JNI_EXPORT(jobjectArray, CEF4J_PEER(CefX509Certificate), getPemEncodedIssuerChain0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_x509_certificate_t*>(self);
    if (!s) return nullptr;
    size_t _count = s->get_issuer_chain_size(s);
    cef_binary_value_t** _arr = _count > 0 ? new cef_binary_value_t*[_count]() : nullptr;
    s->get_pemencoded_issuer_chain(s, &_count, _arr);
    auto _elemCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefBinaryValue$NativePeer");
    auto _elemCtor = env->GetMethodID(_elemCls, "<init>", "(J)V");
    auto _result = env->NewObjectArray(static_cast<jsize>(_count), _elemCls, nullptr);
    for (size_t _i = 0; _i < _count; _i++) {
        if (_arr[_i]) {
            auto _peer = env->NewObject(_elemCls, _elemCtor, reinterpret_cast<jlong>(_arr[_i]));
            env->SetObjectArrayElement(_result, static_cast<jsize>(_i), _peer);
        }
    }
    delete[] _arr;
    return _result;
}
