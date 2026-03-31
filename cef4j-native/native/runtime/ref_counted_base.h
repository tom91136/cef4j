// Hand-written reference counting base - stable across CEF versions.
//
// This header provides the RefCountedHandler mixin that generated handler
// wrapper structs (JniFoo) inherit from. It gives them:
//   - std::atomic<int> refCount
//   - JavaVM* jvm / jobject javaHandler members
//   - Automatic ref-count initialization via InitRefCount<T>
//
// Usage in generated code:
//   struct JniCefFooHandler : public cef_foo_handler_t {
//       JavaVM* jvm;
//       jobject javaHandler;
//       std::atomic<int> refCount{1};
//       ...
//       JniCefFooHandler(JavaVM* vm, jobject handler) : jvm(vm) {
//           javaHandler = handler;
//           InitRefCount<JniCefFooHandler>(&base);
//           ...assign function pointers...
//       }
//   };
//
// The ref-count logic (add_ref, release, has_one_ref, has_at_least_one_ref)
// is provided by InitRefCount<T> in jni_util.h. The release callback
// automatically deletes the Java global ref and the C++ wrapper when the
// ref count reaches zero.
//
// This file exists primarily as documentation - the actual machinery is in
// jni_util.h (InitRefCount). Generated handler structs already embed the
// required members directly.

#ifndef CEF4J_REF_COUNTED_BASE_H
#define CEF4J_REF_COUNTED_BASE_H

#include <atomic>
#include "jni_util.h"

#endif // CEF4J_REF_COUNTED_BASE_H
