package net.kurobako.cef4j.codegen.ipc

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
