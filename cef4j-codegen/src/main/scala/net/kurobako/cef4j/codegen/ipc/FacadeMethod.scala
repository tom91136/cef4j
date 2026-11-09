package net.kurobako.cef4j.codegen.ipc

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
) {
  def fieldsSatisfy(ok: (String, FieldType) => Boolean): Boolean =
    explicitParams.forall(p => ok(p.name, p.ty)) && resultField.forall(f => ok("result", f.ty))
}
