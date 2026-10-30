package net.kurobako.cef4j.codegen

case class RefinedTree(
    decls: List[CefDecl],
    freeFunctions: List[CefDecl.FreeFunction]
) {
  val allEnums: List[CefDecl.Enum]                   = decls.collect { case d: CefDecl.Enum => d }
  val dataStructMap: Map[String, CefDecl.DataStruct] =
    decls.collect { case d: CefDecl.DataStruct => d.name -> d }.toMap
  val scopedNames: Set[String] =
    decls.collect { case d: CefDecl.ObjectStruct if d.scoped => d.name }.toSet
  val freeFunctionsByOwner: Map[String, List[CefDecl.FreeFunction]] =
    freeFunctions.groupBy(_.ownerStruct)
  val orphanFreeFunctions: List[CefDecl.FreeFunction] =
    freeFunctionsByOwner.getOrElse("", Nil)
  val javaClassesGenerated: Int = decls.size
  val cppClassesGenerated: Int  = decls.count(_.isCppGenerated)
}
