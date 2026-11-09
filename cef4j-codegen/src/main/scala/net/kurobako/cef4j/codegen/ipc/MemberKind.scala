package net.kurobako.cef4j.codegen.ipc

enum MemberKind {
  case I32, I64, Bool, Str, Char, WireSize
  case EnumOf(javaType: String)
}
