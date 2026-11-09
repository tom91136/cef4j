package net.kurobako.cef4j.codegen.ipc

/** The shared type a data struct travels as, with one member per wire field in the same order. */
case class SharedLayout(javaType: String, members: List[SharedMember])
