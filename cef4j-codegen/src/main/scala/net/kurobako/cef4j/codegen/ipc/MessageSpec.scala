package net.kurobako.cef4j.codegen.ipc

case class MessageSpec(
    className: String,
    packageName: String,
    messageId: Int,
    fields: List[FieldSpec]
) {
  require(messageId >= 0, s"messageId must be >= 0, got $messageId")
}
