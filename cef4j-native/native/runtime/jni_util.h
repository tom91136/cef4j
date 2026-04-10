#pragma once

#include <jni.h>
#include <atomic>
#include <cstddef>
#include <string>
#include "include/capi/cef_base_capi.h"
#include "include/internal/cef_string.h"

// JNI export signature macros.
// These encode the mangled JNI symbol so hand-written and generated C++ can
// use a readable form instead of raw Java_net_kurobako_cef4j_... names.
//
// Usage (generated code in net.kurobako.cef4j.gen):
//   CEF4J_JNI_EXPORT(jboolean, CEF4J_PEER(CefBrowser), isValid0)(JNIEnv* env, ...) { }
//   CEF4J_JNI_EXPORT(jint, CefGlobals, initialize0)(JNIEnv* env, jclass clz, ...) { }
//
// Usage (hand-written code in net.kurobako.cef4j):
//   CEF4J_JNI_EXPORT_RT(jlong, CefApp, createMainArgs0)(JNIEnv* env, jclass clz, ...) { }

// Inner class "$NativePeer" mangling helper
#define CEF4J_PEER(cls) cls##_00024NativePeer

// Indirection layer so CEF4J_PEER() expands before token-paste
#define CEF4J_JNI_EXPORT_(ret, cls, name) \
    extern "C" JNIEXPORT ret JNICALL Java_net_kurobako_cef4j_gen_##cls##_##name
#define CEF4J_JNI_EXPORT(ret, cls, name) CEF4J_JNI_EXPORT_(ret, cls, name)

// Variant for hand-written runtime code (net.kurobako.cef4j package, no .gen)
#define CEF4J_JNI_EXPORT_RT_(ret, cls, name) \
    extern "C" JNIEXPORT ret JNICALL Java_net_kurobako_cef4j_##cls##_##name
#define CEF4J_JNI_EXPORT_RT(ret, cls, name) CEF4J_JNI_EXPORT_RT_(ret, cls, name)

// ScopedJNIEnv: Ensures a JNIEnv* is available on the current thread.
//
// CEF callbacks arrive on CEF's threads (IO, UI, renderer). The JVM only
// knows about threads that have been attached. ScopedJNIEnv calls
// AttachCurrentThread if necessary and DetachCurrentThread in its destructor
// (only if it was the one that attached).
class ScopedJNIEnv {
public:
    explicit ScopedJNIEnv(JavaVM* jvm) : jvm_(jvm), env_(nullptr), didAttach_(false) {
        jint rc = jvm_->GetEnv(reinterpret_cast<void**>(&env_), JNI_VERSION_1_8);
        if (rc == JNI_EDETACHED) {
            JavaVMAttachArgs args{JNI_VERSION_1_8, nullptr, nullptr};
            jvm_->AttachCurrentThread(reinterpret_cast<void**>(&env_), &args);
            didAttach_ = true;
        }
    }

    ~ScopedJNIEnv() {
        if (didAttach_) {
            jvm_->DetachCurrentThread();
        }
    }

    ScopedJNIEnv(const ScopedJNIEnv&) = delete;
    ScopedJNIEnv& operator=(const ScopedJNIEnv&) = delete;

    JNIEnv* operator->() const { return env_; }
    JNIEnv* get() const { return env_; }
    operator JNIEnv*() const { return env_; }

private:
    JavaVM* jvm_;
    JNIEnv* env_;
    bool didAttach_;
};

// Resolve a Java class from native-attached threads using the application
// class loader captured during JNI_OnLoad. Raw FindClass only works reliably
// for bootstrap classes on arbitrary native threads.
jclass FindClassCached(JNIEnv* env, const char* name);

// String conversion

// Convert a CEF string (UTF-16) to a Java String.
// Returns nullptr if cefStr is null.
inline jstring CefStringToJString(JNIEnv* env, const cef_string_t* cefStr) {
    if (!cefStr || !cefStr->str) return nullptr;
    return env->NewString(reinterpret_cast<const jchar*>(cefStr->str),
                          static_cast<jsize>(cefStr->length));
}

// Convert a Java String to a cef_string_t (caller-owned).
// Writes into `out`. Safe to call with null jStr (clears out).
inline void JStringToCefString(JNIEnv* env, jstring jStr, cef_string_t* out) {
    if (!jStr) {
        cef_string_clear(out);
        return;
    }
    const jchar* chars = env->GetStringChars(jStr, nullptr);
    jsize len = env->GetStringLength(jStr);
    cef_string_utf16_set(reinterpret_cast<const char16_t*>(chars),
                         static_cast<size_t>(len), out, /*copy=*/1);
    env->ReleaseStringChars(jStr, chars);
}

// Overload returning a cef_string_userfree_t for generated object stubs
// that pass string args directly to CEF function pointers.
inline cef_string_userfree_t JStringToCefString(JNIEnv* env, jstring jStr) {
    cef_string_userfree_t s = cef_string_userfree_utf16_alloc();
    JStringToCefString(env, jStr, s);
    return s;
}

// Reference counting helpers

// Standard ref-count callbacks for handler wrappers that embed
// std::atomic<int> refCount as their first member after the CEF struct.
// T must be the JniFoo wrapper struct type.
// CefStruct is the CEF C API struct type (e.g., cef_client_t).
// base->size must equal sizeof(CefStruct) because CEF validates it
// against the expected struct size at the DLL boundary.
template<typename T, typename CefStruct>
void InitRefCount(cef_base_ref_counted_t* base) {
    base->size = sizeof(CefStruct);
    base->add_ref = [](cef_base_ref_counted_t* self) {
        auto* t = reinterpret_cast<T*>(self);
        t->refCount.fetch_add(1, std::memory_order_relaxed);
    };
    base->release = [](cef_base_ref_counted_t* self) -> int {
        auto* t = reinterpret_cast<T*>(self);
        if (t->refCount.fetch_sub(1, std::memory_order_acq_rel) == 1) {
            // Release the Java global ref before deleting
            if (t->jvm && t->javaHandler) {
                ScopedJNIEnv env(t->jvm);
                env->DeleteGlobalRef(t->javaHandler);
            }
            delete t;
            return 1;
        }
        return 0;
    };
    base->has_one_ref = [](cef_base_ref_counted_t* self) -> int {
        auto* t = reinterpret_cast<T*>(self);
        return t->refCount.load(std::memory_order_acquire) == 1 ? 1 : 0;
    };
    base->has_at_least_one_ref = [](cef_base_ref_counted_t* self) -> int {
        auto* t = reinterpret_cast<T*>(self);
        return t->refCount.load(std::memory_order_acquire) >= 1 ? 1 : 0;
    };
}

// JNI exception checking

// Check for pending Java exception after a JNI call. Returns true if an
// exception is pending (and clears it so native code can continue safely).
inline bool CheckJNIException(JNIEnv* env) {
    if (env->ExceptionCheck()) {
        env->ExceptionDescribe();
        env->ExceptionClear();
        return true;
    }
    return false;
}

// String collection conversion - declarations only (implementations in jni_util.cpp)

#include "include/internal/cef_string_list.h"
#include "include/internal/cef_string_map.h"
#include "include/internal/cef_string_multimap.h"

// Native -> Java
jobject CefStringListToJavaList(JNIEnv* env, cef_string_list_t list);
jobject CefStringMapToJavaMap(JNIEnv* env, cef_string_map_t map);
jobject CefStringMultimapToJavaMap(JNIEnv* env, cef_string_multimap_t mmap);

// Java -> Native
cef_string_list_t JavaListToCefStringList(JNIEnv* env, jobject jList);
cef_string_map_t JavaMapToCefStringMap(JNIEnv* env, jobject jMap);
cef_string_multimap_t JavaMapToCefStringMultimap(JNIEnv* env, jobject jMap);

// Writeback: copy CEF string collection back into existing Java collection, then free CEF collection
void CefStringListWriteBack(JNIEnv* env, cef_string_list_t list, jobject jList);
void CefStringMapWriteBack(JNIEnv* env, cef_string_map_t map, jobject jMap);
void CefStringMultimapWriteBack(JNIEnv* env, cef_string_multimap_t mmap, jobject jMap);

// CefValue <-> Java Object conversion (recursive)

#include "include/capi/cef_values_capi.h"

// Convert a cef_value_t* to a Java Object (recursively).
// Returns:
//   null         -> null
//   bool         -> java.lang.Boolean
//   int          -> java.lang.Integer
//   double       -> java.lang.Double
//   string       -> java.lang.String
//   binary       -> byte[]
//   list         -> java.util.ArrayList<Object>
//   dictionary   -> java.util.HashMap<String, Object>
jobject CefValueToJObject(JNIEnv* env, cef_value_t* value);

// Convert a cef_dictionary_value_t* to a java.util.HashMap<String, Object>.
jobject CefDictValueToJMap(JNIEnv* env, cef_dictionary_value_t* dict);

// Convert a cef_list_value_t* to a java.util.ArrayList<Object>.
jobject CefListValueToJList(JNIEnv* env, cef_list_value_t* list);
