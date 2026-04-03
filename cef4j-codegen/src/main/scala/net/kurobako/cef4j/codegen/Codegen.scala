package net.kurobako.cef4j.codegen

import java.nio.file.Path

case class Config(
    cefInclude: Path,
    outCpp: Path,
    outJava: Path,
    javaPackage: String,
    compilerId: String,
    extraCppDirs: List[String] = Nil,
    extraCapiDirs: List[String] = Nil
) {
  val outJavaPackageDir: Path = outJava.resolve(javaPackage.replace('.', '/'))
}

case class HeaderInputs(
    capiDir: Path,
    capiHeaders: List[Path],
    typesHeader: Path,
    cefRoot: Path
) {
  val allHeaders: List[Path] = capiHeaders :+ typesHeader
}

case class ParseState(
    namingContext: Naming.Context,
    docContext: DocComments.Context,
    handlerNames: Set[String],
    docs: Map[String, String],
    cppTypeInfo: Map[String, CppMethodTypeInfo],
    enumDocs: Map[String, (String, Map[String, String])],
    classDocs: Map[String, String],
    structHeaderMap: Map[String, String],
    structFieldDocs: Map[String, Map[String, String]]
)

case class ParsedTree(
    decls: List[CefDecl],
    structDecls: List[CefDecl],
    freeFunctions: List[CefDecl.FreeFunction],
    knownStructNames: Set[String]
)

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

case class UntypedPtrWarning(structName: String, fnName: String, innerType: String, position: String)

// Per-method type info recovered from C++ headers, for example, bool coerced to int in the C API.
case class CppMethodTypeInfo(returnType: String, paramTypes: Map[String, String])
