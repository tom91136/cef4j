package net.kurobako.cef4j.codegen

case class FnPtr(
    name: String,
    ret: CType,
    params: List[Param],
    isSpecial: Option[SpecialFn] = None,
    metaAttrs: List[(String, String)] = Nil,
    cppName: Option[String] = None
)
