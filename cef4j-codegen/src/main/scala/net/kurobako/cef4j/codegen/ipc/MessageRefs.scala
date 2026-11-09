package net.kurobako.cef4j.codegen.ipc

object MessageRefs {
  val Named: MessageRefs = MessageRefs(Map.empty)
}

/** Resolves a per-RPC message name to the Java expressions that build, decode and tag it. */
case class MessageRefs(shared: Map[String, String]) {

  def typeName(messageName: String): String = shared.getOrElse(messageName, messageName)

  def idExpr(messageName: String): String =
    s"${MessageId.ClassName}.${MessageId.constant(messageName)}"

  def newExpr(messageName: String, args: String): String = s"new ${typeName(messageName)}($args)"

  def decoderExpr(messageName: String): String = s"${typeName(messageName)}.DECODER"
}
