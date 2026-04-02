// Hand-written CefValue <-> Java Object marshaling - stable across CEF versions.
//
// Converts between CEF's CefValue tree and Java object trees using standard
// JDK types (Boolean, Integer, Double, String, byte[], ArrayList, HashMap).

#include "cef_value_util.h"
#include "jni_util.h"
#include <cstring>

// Cached JNI class/method IDs - initialized on first use.
// These are safe to cache because they use global refs.
struct JniClassCache {
    jclass booleanClass = nullptr;
    jmethodID booleanValueOf = nullptr;

    jclass integerClass = nullptr;
    jmethodID integerValueOf = nullptr;

    jclass doubleClass = nullptr;
    jmethodID doubleValueOf = nullptr;

    jclass arrayListClass = nullptr;
    jmethodID arrayListInit = nullptr;
    jmethodID arrayListAdd = nullptr;

    jclass hashMapClass = nullptr;
    jmethodID hashMapInit = nullptr;
    jmethodID hashMapPut = nullptr;

    bool initialized = false;

    void init(JNIEnv* env) {
        if (initialized) return;

        jclass local;

        local = env->FindClass("java/lang/Boolean");
        booleanClass = static_cast<jclass>(env->NewGlobalRef(local));
        booleanValueOf = env->GetStaticMethodID(booleanClass, "valueOf", "(Z)Ljava/lang/Boolean;");
        env->DeleteLocalRef(local);

        local = env->FindClass("java/lang/Integer");
        integerClass = static_cast<jclass>(env->NewGlobalRef(local));
        integerValueOf = env->GetStaticMethodID(integerClass, "valueOf", "(I)Ljava/lang/Integer;");
        env->DeleteLocalRef(local);

        local = env->FindClass("java/lang/Double");
        doubleClass = static_cast<jclass>(env->NewGlobalRef(local));
        doubleValueOf = env->GetStaticMethodID(doubleClass, "valueOf", "(D)Ljava/lang/Double;");
        env->DeleteLocalRef(local);

        local = env->FindClass("java/util/ArrayList");
        arrayListClass = static_cast<jclass>(env->NewGlobalRef(local));
        arrayListInit = env->GetMethodID(arrayListClass, "<init>", "(I)V");
        arrayListAdd = env->GetMethodID(arrayListClass, "add", "(Ljava/lang/Object;)Z");
        env->DeleteLocalRef(local);

        local = env->FindClass("java/util/HashMap");
        hashMapClass = static_cast<jclass>(env->NewGlobalRef(local));
        hashMapInit = env->GetMethodID(hashMapClass, "<init>", "(I)V");
        hashMapPut = env->GetMethodID(hashMapClass, "put",
            "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;");
        env->DeleteLocalRef(local);

        initialized = true;
    }
};

static JniClassCache classCache;

// CefValue -> Java Object

jobject CefValueToJObject(JNIEnv* env, cef_value_t* value) {
    if (!value) return nullptr;
    classCache.init(env);

    cef_value_type_t type = value->get_type(value);
    switch (type) {
        case VTYPE_NULL:
        case VTYPE_INVALID:
            return nullptr;

        case VTYPE_BOOL: {
            int b = value->get_bool(value);
            return env->CallStaticObjectMethod(classCache.booleanClass,
                classCache.booleanValueOf, static_cast<jboolean>(b));
        }

        case VTYPE_INT: {
            int i = value->get_int(value);
            return env->CallStaticObjectMethod(classCache.integerClass,
                classCache.integerValueOf, static_cast<jint>(i));
        }

        case VTYPE_DOUBLE: {
            double d = value->get_double(value);
            return env->CallStaticObjectMethod(classCache.doubleClass,
                classCache.doubleValueOf, static_cast<jdouble>(d));
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
                auto* buf = new char[sz];
                bin->get_data(bin, buf, sz, 0);
                env->SetByteArrayRegion(arr, 0, static_cast<jsize>(sz),
                                        reinterpret_cast<const jbyte*>(buf));
                delete[] buf;
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

// CefDictionaryValue -> HashMap<String, Object>

jobject CefDictValueToJMap(JNIEnv* env, cef_dictionary_value_t* dict) {
    if (!dict) return nullptr;
    classCache.init(env);

    cef_string_list_t keys = cef_string_list_alloc();
    int ok = dict->get_keys(dict, keys);
    if (!ok) {
        cef_string_list_free(keys);
        return nullptr;
    }

    size_t count = cef_string_list_size(keys);
    jobject map = env->NewObject(classCache.hashMapClass, classCache.hashMapInit,
                                 static_cast<jint>(count));

    for (size_t i = 0; i < count; i++) {
        cef_string_t key{};
        cef_string_list_value(keys, i, &key);
        jstring jKey = CefStringToJString(env, &key);

        cef_value_t* val = dict->get_value(dict, &key);
        jobject jVal = CefValueToJObject(env, val);
        if (val) val->base.release(&val->base);

        env->CallObjectMethod(map, classCache.hashMapPut, jKey, jVal);

        env->DeleteLocalRef(jKey);
        if (jVal) env->DeleteLocalRef(jVal);
        cef_string_clear(&key);
    }

    cef_string_list_free(keys);
    return map;
}

// CefListValue -> ArrayList<Object>

jobject CefListValueToJList(JNIEnv* env, cef_list_value_t* list) {
    if (!list) return nullptr;
    classCache.init(env);

    size_t count = list->get_size(list);
    jobject arrayList = env->NewObject(classCache.arrayListClass,
                                       classCache.arrayListInit,
                                       static_cast<jint>(count));

    for (size_t i = 0; i < count; i++) {
        cef_value_t* val = list->get_value(list, i);
        jobject jVal = CefValueToJObject(env, val);
        if (val) val->base.release(&val->base);

        env->CallBooleanMethod(arrayList, classCache.arrayListAdd, jVal);

        if (jVal) env->DeleteLocalRef(jVal);
    }

    return arrayList;
}
