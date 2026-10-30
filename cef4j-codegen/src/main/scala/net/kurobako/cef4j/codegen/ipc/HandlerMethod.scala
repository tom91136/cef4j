package net.kurobako.cef4j.codegen.ipc

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
