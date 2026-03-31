// Hand-written CefValue <-> Java Object marshaling - stable across CEF versions.
#ifndef CEF4J_VALUE_UTIL_H
#define CEF4J_VALUE_UTIL_H

#include <jni.h>
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

#endif // CEF4J_VALUE_UTIL_H
