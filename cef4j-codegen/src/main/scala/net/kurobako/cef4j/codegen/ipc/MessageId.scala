package net.kurobako.cef4j.codegen.ipc

object MessageId {
  val ClassName: String = "MessageIds"

  def constant(messageName: String): String = SpecDeriver.camelToSnake(messageName).toUpperCase
}

case class MessageId(constant: String, value: Int)
