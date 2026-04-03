// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
#include <jni.h>
#include "include/capi/cef_dom_capi.h"
#include "jni_util.h"

#include <atomic>
#include "jni_util.h"

struct JniCefDomVisitor: public cef_domvisitor_t {
    JavaVM *jvm;
    jobject javaHandler;  // global ref
    std::atomic<int> refCount { 1 };

    JniCefDomVisitor(JavaVM *vm, jobject handler) : cef_domvisitor_t { }, jvm(vm) {
        javaHandler = handler;
        InitRefCount<JniCefDomVisitor, cef_domvisitor_t> (&base);
        visit = &_visit;
    }

    static void CEF_CALLBACK _visit(cef_domvisitor_t* self, struct _cef_domdocument_t* document) {
        auto* h = reinterpret_cast<JniCefDomVisitor*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(8) < 0) {return;}
        cef_domdocument_t* _p_document = document;
        if (_p_document) {auto* _b = reinterpret_cast<cef_base_ref_counted_t*>(_p_document); _b->add_ref(_b);}
        auto j_document_cls = env->FindClass("net/kurobako/cef4j/gen/CefDomDocument$NativePeer");
        auto j_document_ctor = env->GetMethodID(j_document_cls, "<init>", "(J)V");
        auto j_document = _p_document ? env->NewObject(j_document_cls, j_document_ctor, reinterpret_cast<jlong>(_p_document)) : nullptr;
        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "visit", "(Lnet/kurobako/cef4j/gen/CefDomDocument;)V");
        if (!mid) {env->PopLocalFrame(nullptr); return;}
        env->CallVoidMethod(h->javaHandler, mid, j_document);
        if (CheckJNIException(env)) {env->PopLocalFrame(nullptr); return;}
        env->PopLocalFrame(nullptr);
    }
};

extern "C" cef_domvisitor_t* Create_JniCefDomVisitor(JNIEnv *env, jobject handler) {
    JavaVM *jvm;
    env->GetJavaVM(&jvm);
    auto globalRef = env->NewGlobalRef(handler);
    return reinterpret_cast<cef_domvisitor_t*>(new JniCefDomVisitor(jvm, globalRef));
}
