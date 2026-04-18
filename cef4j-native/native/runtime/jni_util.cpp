#include "jni_util.h"
#include "include/cef_api_hash.h"

#include <cstring>

static JavaVM* jvm = nullptr;

static struct {
    jobject appClassLoader;
    jclass classLoaderClass;
    jmethodID classLoaderLoadClass;
} loaderCache;

// Cached JNI class and method IDs, initialized once in JNI_OnLoad.
// All jclass entries are global refs so they survive across native calls.
// jmethodID values are stable for the lifetime of the class and need no ref.
static struct {
    // Boxing (CefValue conversion)
    jclass booleanClass;
    jmethodID booleanValueOf;
    jclass integerClass;
    jmethodID integerValueOf;
    jclass doubleClass;
    jmethodID doubleValueOf;

    // Collections (string list/map/multimap + CefValue)
    jclass arrayListClass;
    jmethodID arrayListInitCap;   // ArrayList(int capacity)
    jmethodID arrayListInit;      // ArrayList()
    jmethodID arrayListAdd;

    jclass hashMapClass;
    jmethodID hashMapInitCap;     // HashMap(int capacity)
    jmethodID hashMapInit;        // HashMap()
    jmethodID hashMapGet;
    jmethodID hashMapPut;

    // Interfaces used for Java -> native conversion
    jclass listClass;
    jmethodID listSize;
    jmethodID listGet;

    jclass mapClass;
    jmethodID mapEntrySet;

    jclass setClass;
    jmethodID setIterator;

    jclass iteratorClass;
    jmethodID iteratorHasNext;
    jmethodID iteratorNext;

    jclass mapEntryClass;
    jmethodID mapEntryGetKey;
    jmethodID mapEntryGetValue;
} jc;

static jclass globalRef(JNIEnv* env, const char* name) {
    jclass local = env->FindClass(name);
    jclass global = static_cast<jclass>(env->NewGlobalRef(local));
    env->DeleteLocalRef(local);
    return global;
}

static bool isBootstrapClassName(const char* name) {
    return strncmp(name, "java/", 5) == 0 || strncmp(name, "javax/", 6) == 0 || strncmp(name, "jdk/", 4) == 0
        || strncmp(name, "sun/", 4) == 0 || strncmp(name, "com/sun/", 8) == 0;
}

static void initClassLoaderCache(JNIEnv* env) {
    jclass threadClass = env->FindClass("java/lang/Thread");
    jmethodID currentThread = env->GetStaticMethodID(threadClass, "currentThread", "()Ljava/lang/Thread;");
    jmethodID getContextClassLoader =
        env->GetMethodID(threadClass, "getContextClassLoader", "()Ljava/lang/ClassLoader;");
    jobject thread = env->CallStaticObjectMethod(threadClass, currentThread);
    jobject loader = thread ? env->CallObjectMethod(thread, getContextClassLoader) : nullptr;
    if (CheckJNIException(env)) {
        loader = nullptr;
    }

    loaderCache.classLoaderClass = globalRef(env, "java/lang/ClassLoader");
    loaderCache.classLoaderLoadClass = env->GetMethodID(
        loaderCache.classLoaderClass, "loadClass", "(Ljava/lang/String;)Ljava/lang/Class;");

    if (!loader) {
        jmethodID getSystemClassLoader = env->GetStaticMethodID(
            loaderCache.classLoaderClass, "getSystemClassLoader", "()Ljava/lang/ClassLoader;");
        loader = env->CallStaticObjectMethod(loaderCache.classLoaderClass, getSystemClassLoader);
        if (CheckJNIException(env)) {
            loader = nullptr;
        }
    }

    loaderCache.appClassLoader = loader ? env->NewGlobalRef(loader) : nullptr;

    if (loader) env->DeleteLocalRef(loader);
    if (thread) env->DeleteLocalRef(thread);
    env->DeleteLocalRef(threadClass);
}

jclass FindClassCached(JNIEnv* env, const char* name) {
    if (!name) return nullptr;
    if (isBootstrapClassName(name) || !loaderCache.appClassLoader || !loaderCache.classLoaderLoadClass) {
        return env->FindClass(name);
    }

    std::string binaryName(name);
    for (char& ch : binaryName) {
        if (ch == '/') ch = '.';
    }
    jstring jName = env->NewStringUTF(binaryName.c_str());
    if (!jName) return nullptr;
    auto cls = static_cast<jclass>(env->CallObjectMethod(loaderCache.appClassLoader, loaderCache.classLoaderLoadClass, jName));
    env->DeleteLocalRef(jName);
    if (CheckJNIException(env)) {
        return nullptr;
    }
    return cls;
}

static void initClassCache(JNIEnv* env) {
    jc.booleanClass    = globalRef(env, "java/lang/Boolean");
    jc.booleanValueOf  = env->GetStaticMethodID(jc.booleanClass, "valueOf", "(Z)Ljava/lang/Boolean;");

    jc.integerClass    = globalRef(env, "java/lang/Integer");
    jc.integerValueOf  = env->GetStaticMethodID(jc.integerClass, "valueOf", "(I)Ljava/lang/Integer;");

    jc.doubleClass     = globalRef(env, "java/lang/Double");
    jc.doubleValueOf   = env->GetStaticMethodID(jc.doubleClass, "valueOf", "(D)Ljava/lang/Double;");

    jc.arrayListClass   = globalRef(env, "java/util/ArrayList");
    jc.arrayListInitCap = env->GetMethodID(jc.arrayListClass, "<init>", "(I)V");
    jc.arrayListInit    = env->GetMethodID(jc.arrayListClass, "<init>", "()V");
    jc.arrayListAdd     = env->GetMethodID(jc.arrayListClass, "add", "(Ljava/lang/Object;)Z");

    jc.hashMapClass   = globalRef(env, "java/util/HashMap");
    jc.hashMapInitCap = env->GetMethodID(jc.hashMapClass, "<init>", "(I)V");
    jc.hashMapInit    = env->GetMethodID(jc.hashMapClass, "<init>", "()V");
    jc.hashMapGet     = env->GetMethodID(jc.hashMapClass, "get", "(Ljava/lang/Object;)Ljava/lang/Object;");
    jc.hashMapPut     = env->GetMethodID(jc.hashMapClass, "put",
                            "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;");

    jc.listClass = globalRef(env, "java/util/List");
    jc.listSize  = env->GetMethodID(jc.listClass, "size", "()I");
    jc.listGet   = env->GetMethodID(jc.listClass, "get", "(I)Ljava/lang/Object;");

    jc.mapClass    = globalRef(env, "java/util/Map");
    jc.mapEntrySet = env->GetMethodID(jc.mapClass, "entrySet", "()Ljava/util/Set;");

    jc.setClass    = globalRef(env, "java/util/Set");
    jc.setIterator = env->GetMethodID(jc.setClass, "iterator", "()Ljava/util/Iterator;");

    jc.iteratorClass   = globalRef(env, "java/util/Iterator");
    jc.iteratorHasNext = env->GetMethodID(jc.iteratorClass, "hasNext", "()Z");
    jc.iteratorNext    = env->GetMethodID(jc.iteratorClass, "next", "()Ljava/lang/Object;");

    jc.mapEntryClass    = globalRef(env, "java/util/Map$Entry");
    jc.mapEntryGetKey   = env->GetMethodID(jc.mapEntryClass, "getKey", "()Ljava/lang/Object;");
    jc.mapEntryGetValue = env->GetMethodID(jc.mapEntryClass, "getValue", "()Ljava/lang/Object;");
}

extern "C" JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM* vm, void* /*reserved*/) {
    jvm = vm;

    // Configure the CEF API version at load time so version mismatches are caught early.
    // On macOS, cef_api_hash() is a function-pointer stub (via libcef_dll_dylib.cc) and
    // cannot be called here because cef_load_library() hasn't been called yet.
    // It is called in loadCefLibrary0() after cef_load_library() initializes the stubs.
#ifndef __APPLE__
    cef4j_verify_api_hash();
#endif

    JNIEnv* env;
    if (vm->GetEnv(reinterpret_cast<void**>(&env), JNI_VERSION_1_8) != JNI_OK) {
        return JNI_ERR;
    }

    initClassCache(env);
    initClassLoaderCache(env);
    return JNI_VERSION_1_8;
}

extern "C" JNIEXPORT void JNICALL JNI_OnUnload(JavaVM* /*vm*/, void* /*reserved*/) {
    if (jvm && loaderCache.appClassLoader) {
        ScopedJNIEnv env(jvm);
        env->DeleteGlobalRef(loaderCache.appClassLoader);
        loaderCache.appClassLoader = nullptr;
    }
    if (jvm && loaderCache.classLoaderClass) {
        ScopedJNIEnv env(jvm);
        env->DeleteGlobalRef(loaderCache.classLoaderClass);
        loaderCache.classLoaderClass = nullptr;
    }
    loaderCache.classLoaderLoadClass = nullptr;
    jvm = nullptr;
}

// String collection conversion (native -> Java)

jobject CefStringListToJavaList(JNIEnv* env, cef_string_list_t list) {
    int count = list ? static_cast<int>(cef_string_list_size(list)) : 0;
    jobject jList = env->NewObject(jc.arrayListClass, jc.arrayListInitCap, static_cast<jint>(count));
    cef_string_t str = {};
    for (int i = 0; i < count; i++) {
        cef_string_list_value(list, i, &str);
        jstring jStr = CefStringToJString(env, &str);
        env->CallBooleanMethod(jList, jc.arrayListAdd, jStr);
        env->DeleteLocalRef(jStr);
    }
    cef_string_clear(&str);
    return jList;
}

jobject CefStringMapToJavaMap(JNIEnv* env, cef_string_map_t map) {
    int count = map ? static_cast<int>(cef_string_map_size(map)) : 0;
    jobject jMap = env->NewObject(jc.hashMapClass, jc.hashMapInitCap, static_cast<jint>(count));
    cef_string_t key = {}, val = {};
    for (int i = 0; i < count; i++) {
        cef_string_map_key(map, i, &key);
        cef_string_map_value(map, i, &val);
        jstring jKey = CefStringToJString(env, &key);
        jstring jVal = CefStringToJString(env, &val);
        env->CallObjectMethod(jMap, jc.hashMapPut, jKey, jVal);
        env->DeleteLocalRef(jKey);
        env->DeleteLocalRef(jVal);
    }
    cef_string_clear(&key);
    cef_string_clear(&val);
    return jMap;
}

jobject CefStringMultimapToJavaMap(JNIEnv* env, cef_string_multimap_t mmap) {
    int count = mmap ? static_cast<int>(cef_string_multimap_size(mmap)) : 0;
    jobject jMap = env->NewObject(jc.hashMapClass, jc.hashMapInit);
    cef_string_t key = {}, val = {};
    for (int i = 0; i < count; i++) {
        cef_string_multimap_key(mmap, i, &key);
        cef_string_multimap_value(mmap, i, &val);
        jstring jKey = CefStringToJString(env, &key);
        jobject existing = env->CallObjectMethod(jMap, jc.hashMapGet, jKey);
        if (!existing) {
            existing = env->NewObject(jc.arrayListClass, jc.arrayListInit);
            env->CallObjectMethod(jMap, jc.hashMapPut, jKey, existing);
        }
        jstring jVal = CefStringToJString(env, &val);
        env->CallBooleanMethod(existing, jc.arrayListAdd, jVal);
        env->DeleteLocalRef(jKey);
        env->DeleteLocalRef(jVal);
        env->DeleteLocalRef(existing);
    }
    cef_string_clear(&key);
    cef_string_clear(&val);
    return jMap;
}

// String collection conversion (Java -> native)

cef_string_list_t JavaListToCefStringList(JNIEnv* env, jobject jList) {
    cef_string_list_t list = cef_string_list_alloc();
    if (!jList) return list;
    jint count = env->CallIntMethod(jList, jc.listSize);
    for (jint i = 0; i < count; i++) {
        auto jStr = static_cast<jstring>(env->CallObjectMethod(jList, jc.listGet, i));
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

cef_string_map_t JavaMapToCefStringMap(JNIEnv* env, jobject jMap) {
    cef_string_map_t map = cef_string_map_alloc();
    if (!jMap) return map;
    jobject entrySet = env->CallObjectMethod(jMap, jc.mapEntrySet);
    jobject iter = env->CallObjectMethod(entrySet, jc.setIterator);
    while (env->CallBooleanMethod(iter, jc.iteratorHasNext)) {
        jobject entry = env->CallObjectMethod(iter, jc.iteratorNext);
        auto jKey = static_cast<jstring>(env->CallObjectMethod(entry, jc.mapEntryGetKey));
        auto jVal = static_cast<jstring>(env->CallObjectMethod(entry, jc.mapEntryGetValue));
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

cef_string_multimap_t JavaMapToCefStringMultimap(JNIEnv* env, jobject jMap) {
    cef_string_multimap_t mmap = cef_string_multimap_alloc();
    if (!jMap) return mmap;
    jobject entrySet = env->CallObjectMethod(jMap, jc.mapEntrySet);
    jobject iter = env->CallObjectMethod(entrySet, jc.setIterator);
    while (env->CallBooleanMethod(iter, jc.iteratorHasNext)) {
        jobject entry = env->CallObjectMethod(iter, jc.iteratorNext);
        auto jKey = static_cast<jstring>(env->CallObjectMethod(entry, jc.mapEntryGetKey));
        jobject jValList = env->CallObjectMethod(entry, jc.mapEntryGetValue);
        cef_string_t key = {};
        JStringToCefString(env, jKey, &key);
        jint count = env->CallIntMethod(jValList, jc.listSize);
        for (jint i = 0; i < count; i++) {
            auto jVal = static_cast<jstring>(env->CallObjectMethod(jValList, jc.listGet, i));
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

const char* const* JavaListToConstCStringArray(
    JNIEnv* env,
    jobject jList,
    std::vector<std::string>& storage,
    std::vector<const char*>& ptrs
) {
    storage.clear();
    ptrs.clear();
    if (!jList) return nullptr;

    jint count = env->CallIntMethod(jList, jc.listSize);
    if (CheckJNIException(env)) return nullptr;
    if (count <= 0) return nullptr;

    storage.reserve(static_cast<size_t>(count));
    ptrs.reserve(static_cast<size_t>(count + 1));

    for (jint i = 0; i < count; i++) {
        auto jStr = static_cast<jstring>(env->CallObjectMethod(jList, jc.listGet, i));
        if (CheckJNIException(env)) {
            if (jStr) env->DeleteLocalRef(jStr);
            storage.clear();
            ptrs.clear();
            return nullptr;
        }
        if (!jStr) {
            storage.emplace_back("");
        } else {
            const char* utf = env->GetStringUTFChars(jStr, nullptr);
            if (!utf) {
                env->DeleteLocalRef(jStr);
                storage.clear();
                ptrs.clear();
                return nullptr;
            }
            storage.emplace_back(utf);
            env->ReleaseStringUTFChars(jStr, utf);
            env->DeleteLocalRef(jStr);
        }
        ptrs.push_back(storage.back().c_str());
    }

    ptrs.push_back(nullptr);
    return ptrs.data();
}

char** JavaListToCStringArray(
    JNIEnv* env,
    jobject jList,
    std::vector<std::string>& storage,
    std::vector<char*>& ptrs
) {
    storage.clear();
    ptrs.clear();
    if (!jList) return nullptr;

    jint count = env->CallIntMethod(jList, jc.listSize);
    if (CheckJNIException(env)) return nullptr;
    if (count <= 0) return nullptr;

    storage.reserve(static_cast<size_t>(count));
    ptrs.reserve(static_cast<size_t>(count + 1));

    for (jint i = 0; i < count; i++) {
        auto jStr = static_cast<jstring>(env->CallObjectMethod(jList, jc.listGet, i));
        if (CheckJNIException(env)) {
            if (jStr) env->DeleteLocalRef(jStr);
            storage.clear();
            ptrs.clear();
            return nullptr;
        }
        if (!jStr) {
            storage.emplace_back("");
        } else {
            const char* utf = env->GetStringUTFChars(jStr, nullptr);
            if (!utf) {
                env->DeleteLocalRef(jStr);
                storage.clear();
                ptrs.clear();
                return nullptr;
            }
            storage.emplace_back(utf);
            env->ReleaseStringUTFChars(jStr, utf);
            env->DeleteLocalRef(jStr);
        }
        ptrs.push_back(const_cast<char*>(storage.back().c_str()));
    }

    ptrs.push_back(nullptr);
    return ptrs.data();
}

// Writeback: copy CEF string collection back into existing Java collection

void CefStringListWriteBack(JNIEnv* env, cef_string_list_t list, jobject jList) {
    if (!list || !jList) { if (list) cef_string_list_free(list); return; }
    int count = static_cast<int>(cef_string_list_size(list));
    cef_string_t str = {};
    for (int i = 0; i < count; i++) {
        cef_string_list_value(list, i, &str);
        jstring jStr = CefStringToJString(env, &str);
        env->CallBooleanMethod(jList, jc.arrayListAdd, jStr);
        env->DeleteLocalRef(jStr);
    }
    cef_string_clear(&str);
    cef_string_list_free(list);
}

void CefStringMapWriteBack(JNIEnv* env, cef_string_map_t map, jobject jMap) {
    if (!map || !jMap) { if (map) cef_string_map_free(map); return; }
    int count = static_cast<int>(cef_string_map_size(map));
    cef_string_t key = {}, val = {};
    for (int i = 0; i < count; i++) {
        cef_string_map_key(map, i, &key);
        cef_string_map_value(map, i, &val);
        jstring jKey = CefStringToJString(env, &key);
        jstring jVal = CefStringToJString(env, &val);
        env->CallObjectMethod(jMap, jc.hashMapPut, jKey, jVal);
        env->DeleteLocalRef(jKey);
        env->DeleteLocalRef(jVal);
    }
    cef_string_clear(&key);
    cef_string_clear(&val);
    cef_string_map_free(map);
}

void CefStringMultimapWriteBack(JNIEnv* env, cef_string_multimap_t mmap, jobject jMap) {
    if (!mmap || !jMap) { if (mmap) cef_string_multimap_free(mmap); return; }
    int count = static_cast<int>(cef_string_multimap_size(mmap));
    cef_string_t key = {}, val = {};
    for (int i = 0; i < count; i++) {
        cef_string_multimap_key(mmap, i, &key);
        cef_string_multimap_value(mmap, i, &val);
        jstring jKey = CefStringToJString(env, &key);
        jstring jVal = CefStringToJString(env, &val);
        jobject existing = env->CallObjectMethod(jMap, jc.hashMapGet, jKey);
        if (!existing) {
            existing = env->NewObject(jc.arrayListClass, jc.arrayListInit);
            env->CallObjectMethod(jMap, jc.hashMapPut, jKey, existing);
        }
        env->CallBooleanMethod(existing, jc.arrayListAdd, jVal);
        env->DeleteLocalRef(jKey);
        env->DeleteLocalRef(jVal);
        env->DeleteLocalRef(existing);
    }
    cef_string_clear(&key);
    cef_string_clear(&val);
    cef_string_multimap_free(mmap);
}

// CefValue <-> Java Object conversion

jobject CefValueToJObject(JNIEnv* env, cef_value_t* value) {
    if (!value) return nullptr;

    cef_value_type_t type = value->get_type(value);
    switch (type) {
        case VTYPE_NULL:
        case VTYPE_INVALID:
            return nullptr;

        case VTYPE_BOOL: {
            int b = value->get_bool(value);
            return env->CallStaticObjectMethod(jc.booleanClass,
                jc.booleanValueOf, static_cast<jboolean>(b));
        }

        case VTYPE_INT: {
            int i = value->get_int(value);
            return env->CallStaticObjectMethod(jc.integerClass,
                jc.integerValueOf, static_cast<jint>(i));
        }

        case VTYPE_DOUBLE: {
            double d = value->get_double(value);
            return env->CallStaticObjectMethod(jc.doubleClass,
                jc.doubleValueOf, static_cast<jdouble>(d));
        }

        case VTYPE_STRING: {
            cef_string_userfree_t s = value->get_string(value);
            jstring result = CefStringToJString(env, s);
            cef_string_userfree_utf16_free(s);
            return result;
        }

        case VTYPE_BINARY: {
            cef_binary_value_t* bin = value->get_binary(value);
            if (!bin) return nullptr;
            size_t sz = bin->get_size(bin);
            jbyteArray arr = env->NewByteArray(static_cast<jsize>(sz));
            if (sz > 0) {
                jbyte* buf = env->GetByteArrayElements(arr, nullptr);
                bin->get_data(bin, buf, sz, 0);
                env->ReleaseByteArrayElements(arr, buf, 0);
            }
            bin->base.release(&bin->base);
            return arr;
        }

        case VTYPE_LIST: {
            cef_list_value_t* list = value->get_list(value);
            jobject result = CefListValueToJList(env, list);
            if (list) list->base.release(&list->base);
            return result;
        }

        case VTYPE_DICTIONARY: {
            cef_dictionary_value_t* dict = value->get_dictionary(value);
            jobject result = CefDictValueToJMap(env, dict);
            if (dict) dict->base.release(&dict->base);
            return result;
        }

        default:
            return nullptr;
    }
}

jobject CefDictValueToJMap(JNIEnv* env, cef_dictionary_value_t* dict) {
    if (!dict) return nullptr;

    cef_string_list_t keys = cef_string_list_alloc();
    int ok = dict->get_keys(dict, keys);
    if (!ok) {
        cef_string_list_free(keys);
        return nullptr;
    }

    size_t count = cef_string_list_size(keys);
    jobject map = env->NewObject(jc.hashMapClass, jc.hashMapInitCap,
                                 static_cast<jint>(count));

    for (size_t i = 0; i < count; i++) {
        cef_string_t key{};
        cef_string_list_value(keys, i, &key);
        jstring jKey = CefStringToJString(env, &key);

        cef_value_t* val = dict->get_value(dict, &key);
        jobject jVal = CefValueToJObject(env, val);
        if (val) val->base.release(&val->base);

        env->CallObjectMethod(map, jc.hashMapPut, jKey, jVal);

        env->DeleteLocalRef(jKey);
        if (jVal) env->DeleteLocalRef(jVal);
        cef_string_clear(&key);
    }

    cef_string_list_free(keys);
    return map;
}

jobject CefListValueToJList(JNIEnv* env, cef_list_value_t* list) {
    if (!list) return nullptr;

    size_t count = list->get_size(list);
    jobject arrayList = env->NewObject(jc.arrayListClass,
                                       jc.arrayListInitCap,
                                       static_cast<jint>(count));

    for (size_t i = 0; i < count; i++) {
        cef_value_t* val = list->get_value(list, i);
        jobject jVal = CefValueToJObject(env, val);
        if (val) val->base.release(&val->base);

        env->CallBooleanMethod(arrayList, jc.arrayListAdd, jVal);

        if (jVal) env->DeleteLocalRef(jVal);
    }

    return arrayList;
}
