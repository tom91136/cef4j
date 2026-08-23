#pragma once

#include <cstring>
#include <type_traits>

#include "include/cef_api_hash.h"
#include "include/cef_version.h"
#include "include/capi/cef_v8_capi.h"
#include "include/internal/cef_string.h"

using cef4j_char_t = std::remove_cv_t<std::remove_pointer_t<decltype(((cef_string_t*)nullptr)->str)>>;

#if CEF_VERSION_MAJOR < 133
using cef4j_v8_context_t = cef_v8context_t;
using cef4j_v8_exception_t = cef_v8exception_t;
using cef4j_v8_handler_t = cef_v8handler_t;
using cef4j_v8_value_t = cef_v8value_t;

inline cef4j_v8_value_t* cef4j_v8_create_function(const cef_string_t* name, cef4j_v8_handler_t* handler) {
    return cef_v8value_create_function(name, handler);
}

inline cef4j_v8_value_t* cef4j_v8_create_undefined() {
    return cef_v8value_create_undefined();
}
#else
using cef4j_v8_context_t = cef_v8_context_t;
using cef4j_v8_exception_t = cef_v8_exception_t;
using cef4j_v8_handler_t = cef_v8_handler_t;
using cef4j_v8_value_t = cef_v8_value_t;

inline cef4j_v8_value_t* cef4j_v8_create_function(const cef_string_t* name, cef4j_v8_handler_t* handler) {
    return cef_v8_value_create_function(name, handler);
}

inline cef4j_v8_value_t* cef4j_v8_create_undefined() {
    return cef_v8_value_create_undefined();
}
#endif

inline const char* cef4j_runtime_api_hash() {
#if CEF_VERSION_MAJOR < 133
    return cef_api_hash(0);
#else
    return cef_api_hash(CEF_API_VERSION, 0);
#endif
}

inline bool cef4j_verify_api_hash() {
    const char* actual = cef4j_runtime_api_hash();
    return actual && std::strcmp(actual, CEF_API_HASH_PLATFORM) == 0;
}
