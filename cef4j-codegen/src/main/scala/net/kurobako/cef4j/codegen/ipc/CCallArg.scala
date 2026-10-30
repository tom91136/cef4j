package net.kurobako.cef4j.codegen.ipc

/** An argument of the C function behind a facade method: a value the Java parameters carry, or the size of one. */
enum CCallArg {
  case Explicit(fieldName: String)
  case BytesSize(bytesField: String)
}
