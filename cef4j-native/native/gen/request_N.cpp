// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
#include <jni.h>
#include "include/capi/cef_request_capi.h"
#include "jni_util.h"

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefRequest), release0)(JNIEnv* env, jclass clz, jlong ptr) {
    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);
}

CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefRequest), isReadOnly0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_request_t*>(self);
    if (!s) return JNI_FALSE;
    auto _r = s->is_read_only(s);
    return static_cast<jboolean>(_r);
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefRequest), getUrl0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_request_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_url(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefRequest), setUrl0)(JNIEnv* env, jobject obj, jlong self, jstring url) {
    auto* s = reinterpret_cast<cef_request_t*>(self);
    if (!s) return;
    auto _url_str = url ? JStringToCefString(env, url) : nullptr;
    s->set_url(s, _url_str);
    if (_url_str) cef_string_userfree_free(_url_str);
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefRequest), getMethod0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_request_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_method(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefRequest), setMethod0)(JNIEnv* env, jobject obj, jlong self, jstring method) {
    auto* s = reinterpret_cast<cef_request_t*>(self);
    if (!s) return;
    auto _method_str = JStringToCefString(env, method);
    s->set_method(s, _method_str);
    if (_method_str) cef_string_userfree_free(_method_str);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefRequest), setReferrer0)(JNIEnv* env, jobject obj, jlong self, jstring referrer_url, jobject policy) {
    auto* s = reinterpret_cast<cef_request_t*>(self);
    if (!s) return;
    if (!policy) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "policy must not be null"); return; }
    auto _referrer_url_str = referrer_url ? JStringToCefString(env, referrer_url) : nullptr;
    s->set_referrer(s, _referrer_url_str, static_cast<cef_referrer_policy_t>(env->GetLongField(policy, env->GetFieldID(env->GetObjectClass(policy), "value", "J"))));
    if (_referrer_url_str) cef_string_userfree_free(_referrer_url_str);
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefRequest), getReferrerUrl0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_request_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_referrer_url(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefRequest), getReferrerPolicy0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_request_t*>(self);
    if (!s) return 0;
    auto _r = s->get_referrer_policy(s);
    auto _eCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefReferrerPolicy");
    auto _eOf = env->GetStaticMethodID(_eCls, "of", "(J)Lnet/kurobako/cef4j/gen/CefReferrerPolicy;");
    return env->CallStaticObjectMethod(_eCls, _eOf, static_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefRequest), getPostData0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_request_t*>(self);
    if (!s) return nullptr;
    auto _r = s->get_post_data(s);
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefPostData$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefRequest), setPostData0)(JNIEnv* env, jobject obj, jlong self, jobject postData) {
    auto* s = reinterpret_cast<cef_request_t*>(self);
    if (!s) return;
    cef_post_data_t* _postData_ptr = postData ? reinterpret_cast<cef_post_data_t*>(env->GetLongField(postData, env->GetFieldID(env->GetObjectClass(postData), "nativePtr", "J"))) : nullptr;
    if (_postData_ptr) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_postData_ptr); _b->add_ref(_b); }
    s->set_post_data(s, _postData_ptr);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefRequest), getHeaderMap0)(JNIEnv* env, jobject obj, jlong self, jobject headerMap) {
    auto* s = reinterpret_cast<cef_request_t*>(self);
    if (!s) return;
    if (!headerMap) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "headerMap must not be null"); return; }
    auto _headerMap_csmm = JavaMapToCefStringMultimap(env, headerMap);
    s->get_header_map(s, _headerMap_csmm);
    CefStringMultimapWriteBack(env, _headerMap_csmm, headerMap);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefRequest), setHeaderMap0)(JNIEnv* env, jobject obj, jlong self, jobject headerMap) {
    auto* s = reinterpret_cast<cef_request_t*>(self);
    if (!s) return;
    if (!headerMap) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "headerMap must not be null"); return; }
    auto _headerMap_csmm = JavaMapToCefStringMultimap(env, headerMap);
    s->set_header_map(s, _headerMap_csmm);
    cef_string_multimap_free(_headerMap_csmm);
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefRequest), getHeaderByName0)(JNIEnv* env, jobject obj, jlong self, jstring name) {
    auto* s = reinterpret_cast<cef_request_t*>(self);
    if (!s) return nullptr;
    auto _name_str = JStringToCefString(env, name);
    auto result = s->get_header_by_name(s, _name_str);
    if (_name_str) cef_string_userfree_free(_name_str);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefRequest), setHeaderByName0)(JNIEnv* env, jobject obj, jlong self, jstring name, jstring value, jboolean overwrite) {
    auto* s = reinterpret_cast<cef_request_t*>(self);
    if (!s) return;
    auto _name_str = JStringToCefString(env, name);
    auto _value_str = value ? JStringToCefString(env, value) : nullptr;
    s->set_header_by_name(s, _name_str, _value_str, static_cast<bool>(overwrite));
    if (_name_str) cef_string_userfree_free(_name_str);
    if (_value_str) cef_string_userfree_free(_value_str);
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefRequest), set0)(JNIEnv* env, jobject obj, jlong self, jstring url, jstring method, jobject postData, jobject headerMap) {
    auto* s = reinterpret_cast<cef_request_t*>(self);
    if (!s) return;
    if (!headerMap) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "headerMap must not be null"); return; }
    auto _url_str = JStringToCefString(env, url);
    auto _method_str = JStringToCefString(env, method);
    cef_post_data_t* _postData_ptr = postData ? reinterpret_cast<cef_post_data_t*>(env->GetLongField(postData, env->GetFieldID(env->GetObjectClass(postData), "nativePtr", "J"))) : nullptr;
    if (_postData_ptr) { auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_postData_ptr); _b->add_ref(_b); }
    auto _headerMap_csmm = JavaMapToCefStringMultimap(env, headerMap);
    s->set(s, _url_str, _method_str, _postData_ptr, _headerMap_csmm);
    if (_url_str) cef_string_userfree_free(_url_str);
    if (_method_str) cef_string_userfree_free(_method_str);
    cef_string_multimap_free(_headerMap_csmm);
}

CEF4J_JNI_EXPORT(jint, CEF4J_PEER(CefRequest), getFlags0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_request_t*>(self);
    if (!s) return 0;
    return static_cast<jint>(s->get_flags(s));
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefRequest), setFlags0)(JNIEnv* env, jobject obj, jlong self, jint flags) {
    auto* s = reinterpret_cast<cef_request_t*>(self);
    if (!s) return;
    s->set_flags(s, flags);
}

CEF4J_JNI_EXPORT(jstring, CEF4J_PEER(CefRequest), getFirstPartyForCookies0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_request_t*>(self);
    if (!s) return nullptr;
    auto result = s->get_first_party_for_cookies(s);
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;
}

CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefRequest), setFirstPartyForCookies0)(JNIEnv* env, jobject obj, jlong self, jstring url) {
    auto* s = reinterpret_cast<cef_request_t*>(self);
    if (!s) return;
    auto _url_str = url ? JStringToCefString(env, url) : nullptr;
    s->set_first_party_for_cookies(s, _url_str);
    if (_url_str) cef_string_userfree_free(_url_str);
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefRequest), getResourceType0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_request_t*>(self);
    if (!s) return 0;
    auto _r = s->get_resource_type(s);
    auto _eCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefResourceType");
    auto _eOf = env->GetStaticMethodID(_eCls, "of", "(J)Lnet/kurobako/cef4j/gen/CefResourceType;");
    return env->CallStaticObjectMethod(_eCls, _eOf, static_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefRequest), getTransitionType0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_request_t*>(self);
    if (!s) return 0;
    auto _r = s->get_transition_type(s);
    auto _eCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefTransitionType");
    auto _eOf = env->GetStaticMethodID(_eCls, "of", "(J)Lnet/kurobako/cef4j/gen/CefTransitionType;");
    return env->CallStaticObjectMethod(_eCls, _eOf, static_cast<jlong>(_r));
}

CEF4J_JNI_EXPORT(jlong, CEF4J_PEER(CefRequest), getIdentifier0)(JNIEnv* env, jobject obj, jlong self) {
    auto* s = reinterpret_cast<cef_request_t*>(self);
    if (!s) return 0;
    return static_cast<jlong>(s->get_identifier(s));
}

CEF4J_JNI_EXPORT(jobject, CEF4J_PEER(CefRequest), create0)(JNIEnv* env, jclass clz) {
    auto _r = cef_request_create();
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "net/kurobako/cef4j/gen/CefRequest$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));
}
