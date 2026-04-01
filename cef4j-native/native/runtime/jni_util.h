// Hand-written JNI utilities - stable across CEF versions.
#ifndef CEF4J_JNI_UTIL_H
#define CEF4J_JNI_UTIL_H

#include <jni.h>
#include <atomic>
#include <cstddef>
#include <string>
#include "include/capi/cef_base_capi.h"
#include "include/internal/cef_string.h"

// ---------------------------------------------------------------------------
// ScopedJNIEnv: Ensures a JNIEnv* is available on the current thread.
//
// CEF callbacks arrive on CEF's threads (IO, UI, renderer). The JVM only
// knows about threads that have been attached. ScopedJNIEnv calls
// AttachCurrentThread if necessary and DetachCurrentThread in its destructor
// (only if it was the one that attached).
// ---------------------------------------------------------------------------
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

// ---------------------------------------------------------------------------
// String conversion
// ---------------------------------------------------------------------------

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

// ---------------------------------------------------------------------------
// Reference counting helpers
// ---------------------------------------------------------------------------

// Initialize the cef_base_ref_counted_t of a CEF C struct with a simple
// ref-count implementation. Called by handler wrapper constructors.
//
// The ref count is stored in a size_t at the given offset from the base
// pointer (typically sizeof(cef_base_ref_counted_t) within the wrapper
// struct, but we use a separate counter to keep it simple).
//
// Usage pattern (in generated handler wrappers):
//   struct JniFoo : public cef_foo_t {
//       std::atomic<int> refCount{1};
//       ...
//   };
// Then in the constructor:
//   InitRefCount(&base, [](cef_base_ref_counted_t* b) { ... add_ref ... },
//                       [](cef_base_ref_counted_t* b) { ... release ... },
//                       [](cef_base_ref_counted_t* b) { ... has_one_ref ... });
//
// For simplicity, we provide a macro-free template approach below.

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

// ---------------------------------------------------------------------------
// JNI exception checking
// ---------------------------------------------------------------------------

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

// ---------------------------------------------------------------------------
// String collection conversion (native -> Java)
// ---------------------------------------------------------------------------

#include "include/internal/cef_string_list.h"
#include "include/internal/cef_string_map.h"
#include "include/internal/cef_string_multimap.h"

inline jobject CefStringListToJavaList(JNIEnv* env, cef_string_list_t list) {
    jclass cls = env->FindClass("java/util/ArrayList");
    int count = list ? static_cast<int>(cef_string_list_size(list)) : 0;
    jmethodID init = env->GetMethodID(cls, "<init>", "(I)V");
    jmethodID add = env->GetMethodID(cls, "add", "(Ljava/lang/Object;)Z");
    jobject jList = env->NewObject(cls, init, (jint)count);
    cef_string_t str = {};
    for (int i = 0; i < count; i++) {
        cef_string_list_value(list, i, &str);
        jstring jStr = CefStringToJString(env, &str);
        env->CallBooleanMethod(jList, add, jStr);
        env->DeleteLocalRef(jStr);
    }
    cef_string_clear(&str);
    return jList;
}

inline jobject CefStringMapToJavaMap(JNIEnv* env, cef_string_map_t map) {
    jclass cls = env->FindClass("java/util/HashMap");
    int count = map ? static_cast<int>(cef_string_map_size(map)) : 0;
    jmethodID init = env->GetMethodID(cls, "<init>", "(I)V");
    jmethodID put = env->GetMethodID(cls, "put",
        "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;");
    jobject jMap = env->NewObject(cls, init, (jint)count);
    cef_string_t key = {}, val = {};
    for (int i = 0; i < count; i++) {
        cef_string_map_key(map, i, &key);
        cef_string_map_value(map, i, &val);
        jstring jKey = CefStringToJString(env, &key);
        jstring jVal = CefStringToJString(env, &val);
        env->CallObjectMethod(jMap, put, jKey, jVal);
        env->DeleteLocalRef(jKey);
        env->DeleteLocalRef(jVal);
    }
    cef_string_clear(&key);
    cef_string_clear(&val);
    return jMap;
}

inline jobject CefStringMultimapToJavaMap(JNIEnv* env, cef_string_multimap_t mmap) {
    jclass mapCls = env->FindClass("java/util/HashMap");
    jclass listCls = env->FindClass("java/util/ArrayList");
    jmethodID mapInit = env->GetMethodID(mapCls, "<init>", "()V");
    jmethodID mapGet = env->GetMethodID(mapCls, "get",
        "(Ljava/lang/Object;)Ljava/lang/Object;");
    jmethodID mapPut = env->GetMethodID(mapCls, "put",
        "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;");
    jmethodID listInit = env->GetMethodID(listCls, "<init>", "()V");
    jmethodID listAdd = env->GetMethodID(listCls, "add", "(Ljava/lang/Object;)Z");

    int count = mmap ? static_cast<int>(cef_string_multimap_size(mmap)) : 0;
    jobject jMap = env->NewObject(mapCls, mapInit);
    cef_string_t key = {}, val = {};
    for (int i = 0; i < count; i++) {
        cef_string_multimap_key(mmap, i, &key);
        cef_string_multimap_value(mmap, i, &val);
        jstring jKey = CefStringToJString(env, &key);
        jobject existing = env->CallObjectMethod(jMap, mapGet, jKey);
        if (!existing) {
            existing = env->NewObject(listCls, listInit);
            env->CallObjectMethod(jMap, mapPut, jKey, existing);
        }
        jstring jVal = CefStringToJString(env, &val);
        env->CallBooleanMethod(existing, listAdd, jVal);
        env->DeleteLocalRef(jKey);
        env->DeleteLocalRef(jVal);
        env->DeleteLocalRef(existing);
    }
    cef_string_clear(&key);
    cef_string_clear(&val);
    return jMap;
}

// ---------------------------------------------------------------------------
// String collection conversion (Java -> native)
// ---------------------------------------------------------------------------

inline cef_string_list_t JavaListToCefStringList(JNIEnv* env, jobject jList) {
    cef_string_list_t list = cef_string_list_alloc();
    if (!jList) return list;
    jclass listCls = env->FindClass("java/util/List");
    jmethodID sizeMid = env->GetMethodID(listCls, "size", "()I");
    jmethodID getMid = env->GetMethodID(listCls, "get", "(I)Ljava/lang/Object;");
    jint count = env->CallIntMethod(jList, sizeMid);
    for (jint i = 0; i < count; i++) {
        auto jStr = (jstring)env->CallObjectMethod(jList, getMid, i);
        if (jStr) {
            cef_string_t s = {};
            JStringToCefString(env, jStr, &s);
            cef_string_list_append(list, &s);
            cef_string_clear(&s);
            env->DeleteLocalRef(jStr);
        }
    }
    return list;
}

inline cef_string_map_t JavaMapToCefStringMap(JNIEnv* env, jobject jMap) {
    cef_string_map_t map = cef_string_map_alloc();
    if (!jMap) return map;
    jclass mapCls = env->FindClass("java/util/Map");
    jclass setCls = env->FindClass("java/util/Set");
    jclass iterCls = env->FindClass("java/util/Iterator");
    jclass entryCls = env->FindClass("java/util/Map$Entry");
    jmethodID entrySetMid = env->GetMethodID(mapCls, "entrySet", "()Ljava/util/Set;");
    jmethodID iterMid = env->GetMethodID(setCls, "iterator", "()Ljava/util/Iterator;");
    jmethodID hasNextMid = env->GetMethodID(iterCls, "hasNext", "()Z");
    jmethodID nextMid = env->GetMethodID(iterCls, "next", "()Ljava/lang/Object;");
    jmethodID getKeyMid = env->GetMethodID(entryCls, "getKey", "()Ljava/lang/Object;");
    jmethodID getValMid = env->GetMethodID(entryCls, "getValue", "()Ljava/lang/Object;");
    jobject entrySet = env->CallObjectMethod(jMap, entrySetMid);
    jobject iter = env->CallObjectMethod(entrySet, iterMid);
    while (env->CallBooleanMethod(iter, hasNextMid)) {
        jobject entry = env->CallObjectMethod(iter, nextMid);
        auto jKey = (jstring)env->CallObjectMethod(entry, getKeyMid);
        auto jVal = (jstring)env->CallObjectMethod(entry, getValMid);
        cef_string_t key = {}, val = {};
        JStringToCefString(env, jKey, &key);
        JStringToCefString(env, jVal, &val);
        cef_string_map_append(map, &key, &val);
        cef_string_clear(&key);
        cef_string_clear(&val);
        env->DeleteLocalRef(jKey);
        env->DeleteLocalRef(jVal);
        env->DeleteLocalRef(entry);
    }
    env->DeleteLocalRef(iter);
    env->DeleteLocalRef(entrySet);
    return map;
}

inline cef_string_multimap_t JavaMapToCefStringMultimap(JNIEnv* env, jobject jMap) {
    cef_string_multimap_t mmap = cef_string_multimap_alloc();
    if (!jMap) return mmap;
    // Expects Map<String, List<String>>
    jclass mapCls = env->FindClass("java/util/Map");
    jclass setCls = env->FindClass("java/util/Set");
    jclass iterCls = env->FindClass("java/util/Iterator");
    jclass entryCls = env->FindClass("java/util/Map$Entry");
    jclass listCls = env->FindClass("java/util/List");
    jmethodID entrySetMid = env->GetMethodID(mapCls, "entrySet", "()Ljava/util/Set;");
    jmethodID iterMid = env->GetMethodID(setCls, "iterator", "()Ljava/util/Iterator;");
    jmethodID hasNextMid = env->GetMethodID(iterCls, "hasNext", "()Z");
    jmethodID nextMid = env->GetMethodID(iterCls, "next", "()Ljava/lang/Object;");
    jmethodID getKeyMid = env->GetMethodID(entryCls, "getKey", "()Ljava/lang/Object;");
    jmethodID getValMid = env->GetMethodID(entryCls, "getValue", "()Ljava/lang/Object;");
    jmethodID sizeMid = env->GetMethodID(listCls, "size", "()I");
    jmethodID getMid = env->GetMethodID(listCls, "get", "(I)Ljava/lang/Object;");
    jobject entrySet = env->CallObjectMethod(jMap, entrySetMid);
    jobject iter = env->CallObjectMethod(entrySet, iterMid);
    while (env->CallBooleanMethod(iter, hasNextMid)) {
        jobject entry = env->CallObjectMethod(iter, nextMid);
        auto jKey = (jstring)env->CallObjectMethod(entry, getKeyMid);
        jobject jValList = env->CallObjectMethod(entry, getValMid);
        cef_string_t key = {};
        JStringToCefString(env, jKey, &key);
        jint count = env->CallIntMethod(jValList, sizeMid);
        for (jint i = 0; i < count; i++) {
            auto jVal = (jstring)env->CallObjectMethod(jValList, getMid, i);
            cef_string_t val = {};
            JStringToCefString(env, jVal, &val);
            cef_string_multimap_append(mmap, &key, &val);
            cef_string_clear(&val);
            env->DeleteLocalRef(jVal);
        }
        cef_string_clear(&key);
        env->DeleteLocalRef(jKey);
        env->DeleteLocalRef(jValList);
        env->DeleteLocalRef(entry);
    }
    env->DeleteLocalRef(iter);
    env->DeleteLocalRef(entrySet);
    return mmap;
}

// ---------------------------------------------------------------------------
// Writeback: copy CEF string collection back into existing Java collection
// ---------------------------------------------------------------------------

/** Append all strings from a CEF string list into an existing Java List<String>, then free the CEF list. */
inline void CefStringListWriteBack(JNIEnv* env, cef_string_list_t list, jobject jList) {
    if (!list || !jList) { if (list) cef_string_list_free(list); return; }
    jclass cls = env->GetObjectClass(jList);
    jmethodID add = env->GetMethodID(cls, "add", "(Ljava/lang/Object;)Z");
    int count = static_cast<int>(cef_string_list_size(list));
    cef_string_t str = {};
    for (int i = 0; i < count; i++) {
        cef_string_list_value(list, i, &str);
        jstring jStr = CefStringToJString(env, &str);
        env->CallBooleanMethod(jList, add, jStr);
        env->DeleteLocalRef(jStr);
    }
    cef_string_clear(&str);
    cef_string_list_free(list);
}

/** Put all entries from a CEF string map into an existing Java Map<String,String>, then free the CEF map. */
inline void CefStringMapWriteBack(JNIEnv* env, cef_string_map_t map, jobject jMap) {
    if (!map || !jMap) { if (map) cef_string_map_free(map); return; }
    jclass cls = env->GetObjectClass(jMap);
    jmethodID put = env->GetMethodID(cls, "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;");
    int count = static_cast<int>(cef_string_map_size(map));
    cef_string_t key = {}, val = {};
    for (int i = 0; i < count; i++) {
        cef_string_map_key(map, i, &key);
        cef_string_map_value(map, i, &val);
        jstring jKey = CefStringToJString(env, &key);
        jstring jVal = CefStringToJString(env, &val);
        env->CallObjectMethod(jMap, put, jKey, jVal);
        env->DeleteLocalRef(jKey);
        env->DeleteLocalRef(jVal);
    }
    cef_string_clear(&key);
    cef_string_clear(&val);
    cef_string_map_free(map);
}

/** Put all entries from a CEF string multimap into an existing Java Map<String,List<String>>, then free the CEF multimap. */
inline void CefStringMultimapWriteBack(JNIEnv* env, cef_string_multimap_t mmap, jobject jMap) {
    if (!mmap || !jMap) { if (mmap) cef_string_multimap_free(mmap); return; }
    jclass mapCls = env->GetObjectClass(jMap);
    jclass listCls = env->FindClass("java/util/ArrayList");
    jmethodID getMid = env->GetMethodID(mapCls, "get", "(Ljava/lang/Object;)Ljava/lang/Object;");
    jmethodID putMid = env->GetMethodID(mapCls, "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;");
    jmethodID listInit = env->GetMethodID(listCls, "<init>", "()V");
    jmethodID listAdd = env->GetMethodID(listCls, "add", "(Ljava/lang/Object;)Z");
    int count = static_cast<int>(cef_string_multimap_size(mmap));
    cef_string_t key = {}, val = {};
    for (int i = 0; i < count; i++) {
        cef_string_multimap_key(mmap, i, &key);
        cef_string_multimap_value(mmap, i, &val);
        jstring jKey = CefStringToJString(env, &key);
        jstring jVal = CefStringToJString(env, &val);
        jobject existing = env->CallObjectMethod(jMap, getMid, jKey);
        if (!existing) {
            existing = env->NewObject(listCls, listInit);
            env->CallObjectMethod(jMap, putMid, jKey, existing);
        }
        env->CallBooleanMethod(existing, listAdd, jVal);
        env->DeleteLocalRef(jKey);
        env->DeleteLocalRef(jVal);
        env->DeleteLocalRef(existing);
    }
    cef_string_clear(&key);
    cef_string_clear(&val);
    cef_string_multimap_free(mmap);
}

#endif // CEF4J_JNI_UTIL_H
