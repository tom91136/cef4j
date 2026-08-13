#pragma once

#include "include/cef_version.h"
#include "include/capi/cef_frame_capi.h"
#include "include/capi/cef_v8_capi.h"

// CEF 133 normalized V8 C API names by inserting word separators. Keep the
// runtime server source and its generated renderer dispatcher portable across
// both naming eras without making the wire protocol version-specific.
#if CEF_VERSION_MAJOR < 133
#define cef_v8_context_t cef_v8context_t
#define cef_v8_exception_t cef_v8exception_t
#define cef_v8_handler_t cef_v8handler_t
#define cef_v8_value_t cef_v8value_t

#define cef_v8_context_get_current_context cef_v8context_get_current_context
#define cef_v8_value_create_array cef_v8value_create_array
#define cef_v8_value_create_bool cef_v8value_create_bool
#define cef_v8_value_create_double cef_v8value_create_double
#define cef_v8_value_create_function cef_v8value_create_function
#define cef_v8_value_create_int cef_v8value_create_int
#define cef_v8_value_create_null cef_v8value_create_null
#define cef_v8_value_create_string cef_v8value_create_string
#define cef_v8_value_create_undefined cef_v8value_create_undefined

#define get_v8_context get_v8context
#endif

inline void cef4j_verify_api_hash() {
#if CEF_VERSION_MAJOR < 133
    (void)cef_api_hash(0);
#else
    (void)cef_api_hash(CEF_API_VERSION, 0);
#endif
}
