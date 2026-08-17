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

case class FieldSpec(name: String, ty: FieldType)

case class MessageSpec(
    className: String,
    packageName: String,
    messageId: Int,
    fields: List[FieldSpec]
) {
  require(messageId >= 0, s"messageId must be >= 0, got $messageId")
}

/** The process in which a facade's CEF object is valid. */
enum ProcessAffinity {
  case Browser
  case Renderer
}

case class FacadeSpec(
    className: String,
    packageName: String,
    cefStructName: String,
    methods: List[FacadeMethod],
    affinity: ProcessAffinity = ProcessAffinity.Browser
)

case class FacadeMethod(
    methodName: String,
    cefMethodName: String,
    requestClassName: String,
    responseClassName: String,
    explicitParams: List[FieldSpec],
    resultField: Option[FieldSpec],
    handleStructByField: Map[String, String] = Map.empty,
    // Preserves filtered size parameters in the original C signature order.
    cCallArgs: Option[List[CCallArg]] = None,
    javadoc: String = ""
)

sealed trait CCallArg
object CCallArg {
  case class Explicit(fieldName: String)   extends CCallArg
  case class BytesSize(bytesField: String) extends CCallArg
}

case class HandlerSpec(
    className: String,
    packageName: String,
    cefStructName: String,
    methods: List[HandlerMethod]
)

/** A JVM-owned, single-method CEF visitor. */
case class JvmVisitorSpec(
    cefStructName: String,
    className: String,
    eventClassName: String,
    packageName: String,
    methodName: String,
    cefMethodName: String,
    params: List[FieldSpec],
    constStringByField: Map[String, Boolean] = Map.empty,
    javadoc: String = ""
)

case class DataStructSpec(
    className: String,
    packageName: String,
    cefStructName: String,
    fields: List[FieldSpec]
)

case class HandlerMethod(
    methodName: String,
    cefMethodName: String,
    eventClassName: String,
    params: List[FieldSpec],
    handleStructByField: Map[String, String] = Map.empty,
    // C++ function-pointer parameter types preserve string constness.
    constStringByField: Map[String, Boolean] = Map.empty,
    // Non-void callbacks use the synchronous intercept wire.
    returnType: Option[FieldType] = None,
    responseClassName: Option[String] = None,
    javadoc: String = ""
)
