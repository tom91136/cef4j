package net.kurobako.cef4j.codegen.ipc

enum FieldType {
  case I32
  case I64
  case Bool
  case Utf8String
  case Bytes

  /** An int32 count followed by length-prefixed UTF-8 strings. */
  case StringList

  /** An int32 id into a runtime-server handle table. */
  case RemoteHandle

  /** An inline, recursively encoded by-value struct. */
  case DataStruct(cefStructName: String)
}
