package net.kurobako.cef4j.codegen.ipc

/** One Java class per distinct field layout. A layout used by one message keeps that message's name. */
case class SharedMessage(className: String, packageName: String, fields: List[FieldSpec])
