package net.kurobako.cef4j.codegen

// Per-method type info recovered from C++ headers, for example, bool coerced to int in the C API.
case class CppMethodTypeInfo(returnType: String, paramTypes: Map[String, String])
