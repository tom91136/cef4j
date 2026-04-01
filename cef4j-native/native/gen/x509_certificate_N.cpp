// GENERATED - do not edit.
#include <jni.h>
#include "include/capi/cef_x509_certificate_capi.h"
#include "include/capi/cef_values_capi.h"
#include "jni_util.h"

extern "C" JNIEXPORT void JNICALL Java_net_kurobako_cef4j_gen_CefX509Certificate_00024NativePeer_N_1Release(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefX509Certificate_00024NativePeer_N_1GetSubject(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_x509_certificate_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_subject(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefX509CertPrincipal$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefX509Certificate_00024NativePeer_N_1GetIssuer(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_x509_certificate_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_issuer(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefX509CertPrincipal$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefX509Certificate_00024NativePeer_N_1GetSerialNumber(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_x509_certificate_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_serial_number(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefBinaryValue$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefX509Certificate_00024NativePeer_N_1GetValidStart(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_x509_certificate_t*>(self);
    if (!s) return nullptr;
    cef_basetime_t result = s->get_valid_start(s);
    auto cls = env->FindClass("net/kurobako/cef4j/gen/CefBasetime");
    auto ctor = env->GetMethodID(cls, "<init>", "(J)V");
    auto _dsResult = env->NewObject(cls, ctor, static_cast<jlong>((&result)->val));
    return _dsResult;
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefX509Certificate_00024NativePeer_N_1GetValidExpiry(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_x509_certificate_t*>(self);
    if (!s) return nullptr;
    cef_basetime_t result = s->get_valid_expiry(s);
    auto cls = env->FindClass("net/kurobako/cef4j/gen/CefBasetime");
    auto ctor = env->GetMethodID(cls, "<init>", "(J)V");
    auto _dsResult = env->NewObject(cls, ctor, static_cast<jlong>((&result)->val));
    return _dsResult;
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefX509Certificate_00024NativePeer_N_1GetDerEncoded(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_x509_certificate_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_derencoded(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefBinaryValue$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jobject JNICALL Java_net_kurobako_cef4j_gen_CefX509Certificate_00024NativePeer_N_1GetPemEncoded(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_x509_certificate_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_pemencoded(s);
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("net/kurobako/cef4j/gen/CefBinaryValue$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

extern "C" JNIEXPORT jlong JNICALL Java_net_kurobako_cef4j_gen_CefX509Certificate_00024NativePeer_N_1GetIssuerChainSize(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_x509_certificate_t*>(self);
    if (!s) return 0;
    return static_cast<jlong>(s->get_issuer_chain_size(s));
}

extern "C" JNIEXPORT jobjectArray JNICALL Java_net_kurobako_cef4j_gen_CefX509Certificate_00024NativePeer_N_1GetDerEncodedIssuerChain(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_x509_certificate_t*>(self);
    if (!s) return nullptr;
    size_t _count = s->get_issuer_chain_size(s);
    cef_binary_value_t** _arr = _count > 0 ? new cef_binary_value_t*[_count]() : nullptr;
    s->get_derencoded_issuer_chain(s, &_count, _arr);
    auto _elemCls = env->FindClass("net/kurobako/cef4j/gen/CefBinaryValue$NativePeer");
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

extern "C" JNIEXPORT jobjectArray JNICALL Java_net_kurobako_cef4j_gen_CefX509Certificate_00024NativePeer_N_1GetPemEncodedIssuerChain(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_x509_certificate_t*>(self);
    if (!s) return nullptr;
    size_t _count = s->get_issuer_chain_size(s);
    cef_binary_value_t** _arr = _count > 0 ? new cef_binary_value_t*[_count]() : nullptr;
    s->get_pemencoded_issuer_chain(s, &_count, _arr);
    auto _elemCls = env->FindClass("net/kurobako/cef4j/gen/CefBinaryValue$NativePeer");
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
