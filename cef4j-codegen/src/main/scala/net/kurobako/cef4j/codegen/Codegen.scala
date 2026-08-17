package net.kurobako.cef4j.codegen

import java.nio.file.Path

enum CodegenPlatform {
  case Linux
  case Mac
  case Windows

  def id: String =
    this match {
      case Linux   => "linux"
      case Mac     => "mac"
      case Windows => "win"
    }

  def cefTypesHeader: String =
    this match {
      case Linux   => "cef_types_linux.h"
      case Mac     => "cef_types_mac.h"
      case Windows => "cef_types_win.h"
    }

  def osMacro: String =
    this match {
      case Linux   => "OS_LINUX"
      case Mac     => "OS_MAC"
      case Windows => "OS_WIN"
    }

  def preprocessorDefines: List[String] =
    this match {
      case Linux =>
        List("OS_LINUX=1")
      case Mac =>
        List("OS_MAC=1")
      case Windows =>
        List("OS_WIN=1")
    }
}

object CodegenPlatform {
  def parse(value: String): Option[CodegenPlatform] =
    value.toLowerCase match {
      case "linux"            => Some(CodegenPlatform.Linux)
      case "linux64"          => Some(CodegenPlatform.Linux)
      case "linuxarm64"       => Some(CodegenPlatform.Linux)
      case "mac" | "macos"    => Some(CodegenPlatform.Mac)
      case "macosx64"         => Some(CodegenPlatform.Mac)
      case "macosarm64"       => Some(CodegenPlatform.Mac)
      case "win" | "windows"  => Some(CodegenPlatform.Windows)
      case "windows64"        => Some(CodegenPlatform.Windows)
      case "windowsarm64"     => Some(CodegenPlatform.Windows)
      case "auto" | "current" => Some(detectCurrent)
      case _                  => None
    }

  def detectCurrent: CodegenPlatform = {
    val os = System.getProperty("os.name", "").toLowerCase
    if (os.contains("win")) CodegenPlatform.Windows
    else if (os.contains("mac")) CodegenPlatform.Mac
    else CodegenPlatform.Linux
  }
}

case class Config(
    cefInclude: Path,
    outCpp: Path,
    outJava: Path,
    javaPackage: String,
    compilerId: String,
    cefApiVersionRaw: Option[String] = None,
    extraCppDirs: List[String] = Nil,
    extraCapiDirs: List[String] = Nil,
    targetPlatform: CodegenPlatform = CodegenPlatform.detectCurrent,
    emitJava: Boolean = true,
    emitCommonCpp: Boolean = true,
    emitJavaPlatformOnly: Boolean = false,
    javaPlatformSubPackage: String = ""
) {
  val outJavaPackageDir: Path         = outJava.resolve(javaPackage.replace('.', '/'))
  val outJavaPlatformPackageDir: Path =
    if (javaPlatformSubPackage.nonEmpty) outJavaPackageDir.resolve(javaPlatformSubPackage.replace('.', '/'))
    else outJavaPackageDir
  val outCppPlatformDir: Path           = outCpp.resolve(targetPlatform.id)
  val preprocessorDefines: List[String] =
    targetPlatform.preprocessorDefines ++ cefApiVersionRaw.toList.map { raw =>
      s"CEF_API_VERSION=${Config.normaliseCefApiVersion(raw)}"
    }
}

object Config {

  /** Extract and normalise a CEF API version.
    *
    * Accepts either a bare major version number ("150") or a full CEF version string
    * ("150.0.18+gdb11278+chromium-150.0.7871.213"). In both cases, the major version is extracted and multiplied by 100
    * (e.g. 150 → 15000) to match CEF's internal `CEF_API_VERSION_NNNNN` naming convention.
    */
  def normaliseCefApiVersion(raw: String): String = {
    val trimmed  = raw.trim
    val majorStr = if (trimmed.contains(".")) trimmed.takeWhile(_.isDigit) else trimmed
    require(majorStr.matches("^[0-9]+$"), s"CEF API version must be numeric or a version string: $raw")
    val value = BigInt(majorStr)
    if (value >= 133 && value < 1000) (value * 100).toString else majorStr
  }
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
    structFieldDocs: Map[String, Map[String, String]],
    platformSpecificTypes: Set[String] = Set.empty
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
