package net.kurobako.cef4j.codegen

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import scala.jdk.StreamConverters._

object Main {

  case class Config(
      cefInclude: Path,
      outCpp: Path,
      outJava: Path,
      compilerId: String
  )

  def main(args: Array[String]): Unit = {
    val startNanos = System.nanoTime()
    val cfg        = parseArgs(args.toList)

    val capiDir     = cfg.cefInclude.resolve("capi")
    val capiHeaders = Files.list(capiDir)
      .toScala(List)
      .filter(_.toString.endsWith("_capi.h"))

    val typesHeader = cfg.cefInclude.resolve("internal/cef_types.h")

    // CEF headers use `#include "include/capi/..."` so -I must be the parent of include/
    val cefRoot = cfg.cefInclude.getParent

    // 1. Preprocess all headers
    val preprocessed = (capiHeaders :+ typesHeader).map { h =>
      h -> Preprocessor.preprocess(h, List(cefRoot), cfg.compilerId)
    }
    val headersScanned = preprocessed.size

    // 2. Parse
    val handlerNames = CHeaderParser.parseHandlerAnnotations(cfg.cefInclude)
    val docs         = CHeaderParser.parseDocComments(cfg.cefInclude)
    val cppTypeInfo  = CHeaderParser.parseCppTypeInfo(cfg.cefInclude)
    val enumDocs     = CHeaderParser.parseEnumDocs(cfg.cefInclude)
    val classDocs    = CHeaderParser.parseClassDocs(cfg.cefInclude)
    // Build struct->header map by scanning raw CAPI headers for typedef struct definitions
    val structHeaderMap = buildStructHeaderMap(capiDir)

    val decls = preprocessed.flatMap { case (_, src) =>
      CHeaderParser.parse(src, handlerNames).map(attachSourceHeader(_, structHeaderMap))
    }

    // 2b. Enrich FnPtrs with metacomment attributes, recovered C++ types, and enum docs
    val enrichedDecls        = decls.map(enrichDecl(_, docs, cppTypeInfo, enumDocs))
    val javaClassesGenerated = enrichedDecls.size
    val cppClassesGenerated  = enrichedDecls.count(_.isCppGenerated)

    // 3. Emit - clean generated dirs first, then write
    cleanDir(cfg.outCpp)
    cleanDir(cfg.outJava)

    // Collect by-value struct names that appear in ByValueOut params (need mutable variants)
    val needsMutableSet = enrichedDecls.flatMap {
      case d: CefDecl.ObjectStruct  => d.fns.flatMap(_.params)
      case d: CefDecl.HandlerStruct => d.fns.flatMap(_.params)
      case _                        => Nil
    }.collect { case p if p.typ.isInstanceOf[CType.ByValueOut] => p.typ.asInstanceOf[CType.ByValueOut].cefName }.toSet

    // Build DataStruct lookup for JNI codegen
    val dataStructMap = enrichedDecls.collect { case d: CefDecl.DataStruct => d.name -> d }.toMap
    JniCppCodeGen.setDataStructs(dataStructMap)

    enrichedDecls.foreach(emitDecl(_, cfg, docs, classDocs, needsMutableSet))

    val elapsedSeconds = (System.nanoTime() - startNanos) / 1_000_000_000.0
    println(
      f"""cef4j codegen complete:
         |  headers scanned: $headersScanned%d
         |  Java classes generated: $javaClassesGenerated%d
         |  C++ classes generated: $cppClassesGenerated%d
         |  declarations processed: ${decls.size}%d
         |  total time: $elapsedSeconds%.2f s""".stripMargin
    )
  }

  private def parseArgs(args: List[String]): Config =
    args.foldLeft(Config(
      cefInclude = Paths.get("."),
      outCpp = Paths.get("."),
      outJava = Paths.get("."),
      compilerId = "gcc"
    )) { (cfg, arg) =>
      arg match {
        case s"--cef-include=$path" =>
          cfg.copy(cefInclude = Paths.get(path))
        case s"--out-cpp=$path" =>
          cfg.copy(outCpp = Paths.get(path))
        case s"--out-java=$path" =>
          cfg.copy(outJava = Paths.get(path))
        case s"--compiler=$id" =>
          cfg.copy(compilerId = id)
        case other =>
          System.err.println(s"Unknown argument: $other")
          cfg
      }
    }

  private def attachSourceHeader(decl: CefDecl, structHeaderMap: Map[String, String]): CefDecl =
    decl.namedStruct
      .map(name => decl.withSourceHeader(structHeaderMap.getOrElse(name, "")))
      .getOrElse(decl)

  private def enrichDecl(
      decl: CefDecl,
      docs: Map[String, String],
      cppTypeInfo: Map[String, CHeaderParser.CppMethodTypeInfo],
      enumDocs: Map[String, (String, Map[String, String])]
  ): CefDecl = {
    val enriched = recoverCppTypes(enrichWithMetaAttrs(decl, docs), cppTypeInfo)
    enriched match {
      case e: CefDecl.Enum =>
        enumDocs.get(e.name) match {
          case Some((doc, valDocs)) => e.copy(doc = doc, valueDocs = valDocs)
          case None                 => e
        }
      case other => other
    }
  }

  private def emitDecl(
      decl: CefDecl,
      cfg: Config,
      docs: Map[String, String],
      classDocs: Map[String, String],
      needsMutableSet: Set[String] = Set.empty
  ): Unit =
    decl match {
      case d: CefDecl.ObjectStruct =>
        JniCppCodeGen.emit(d, cfg.outCpp)
        JavaInterfaceCodeGen.emitObject(
          d,
          cfg.outJava,
          docs,
          classDocs.getOrElse(d.name, ""),
          nativePeerBody = JavaNativeCodeGen.renderInnerClass(d)
        )
      case d: CefDecl.HandlerStruct =>
        JniCppCodeGen.emitHandler(d, cfg.outCpp)
        JavaInterfaceCodeGen.emitHandler(d, cfg.outJava, docs, classDocs.getOrElse(d.name, ""))
      case d: CefDecl.DataStruct =>
        JavaRecordCodeGen.emit(
          d,
          cfg.outJava,
          classDocs.getOrElse(d.name, ""),
          needsMutable = needsMutableSet.contains(d.name)
        )
      case d: CefDecl.Enum =>
        JavaEnumCodeGen.emit(d, cfg.outJava)
    }

  /** Recover types lost in C->C++ API translation. Cross-references C++ headers to upgrade C API types:
    *   - bool (C++) -> int (C API) -> boolean (Java)
    *   - CefRefPtr<T> preserves concrete inner type info
    * Uses C++ header type info and capi_name mapping.
    */
  private def recoverCppTypes(
      decl: CefDecl,
      cppTypeInfo: Map[String, CHeaderParser.CppMethodTypeInfo]
  ): CefDecl = {
    def recover(fn: FnPtr): FnPtr = {
      // Look up C++ type info: try PascalCase name, then capi_name reverse lookup
      val pascal   = Naming.toPascalCase(fn.name)
      val capiName = fn.metaAttrs.collectFirst { case ("capi_name", n) => n }
      val info     = cppTypeInfo.get(pascal)
        .orElse(cppTypeInfo.get(fn.name))
        .orElse(capiName.flatMap(cppTypeInfo.get))

      info match {
        case Some(ti) =>
          val recoveredRet    = recoverType(fn.ret, ti.returnType)
          val recoveredParams = fn.params.map { p =>
            val cppParamType = ti.paramTypes.get(p.name)
              .orElse(ti.paramTypes.get(Naming.toCamelCase(p.name)))
            cppParamType match {
              case Some(cppType) => p.copy(typ = recoverType(p.typ, cppType))
              case None          => p
            }
          }
          fn.copy(ret = recoveredRet, params = recoveredParams)
        case None => fn
      }
    }

    decl.mapFns(recover)
  }

  /** Recover a single type from C API to the more precise C++ type. */
  private def recoverType(capiType: CType, cppTypeStr: String): CType = (capiType, cppTypeStr) match {
    case (CType.Int, "bool") => CType.Bool // bool coerced to int in C API
    case _                   => capiType
  }

  /** Enrich FnPtrs in a CefDecl with metacomment attributes looked up from the C++ docs map. */
  private def enrichWithMetaAttrs(decl: CefDecl, docs: Map[String, String]): CefDecl = {
    def enrich(fn: FnPtr): FnPtr = {
      val camel   = Naming.toCamelCase(fn.name)
      val pascal  = Naming.toPascalCase(fn.name)
      val docText = docs.get(camel).orElse(docs.get(pascal)).orElse(docs.get(fn.name))
      docText match {
        case Some(text) => fn.copy(metaAttrs = DocComments.extractAttrsList(text))
        case None       => fn
      }
    }
    decl.mapFns(enrich)
  }

  private val StructDefPattern = """typedef\s+struct\s+_?(cef_\w+_t)\s*\{""".r

  private def buildStructHeaderMap(capiDir: Path): Map[String, String] = {
    val headers = Files.list(capiDir).toScala(List).filter(_.toString.endsWith(".h"))
    headers.flatMap { h =>
      val headerName = h.getFileName.toString
      val content    = Files.readString(h)
      StructDefPattern.findAllMatchIn(content).map(m => m.group(1) -> headerName)
    }.toMap
  }

  private def cleanDir(dir: Path): Unit = {
    if (Files.exists(dir)) {
      Files.walk(dir)
        .toScala(List)
        .sortBy(_.toString)(using Ordering[String].reverse) // files before dirs
        .foreach(Files.deleteIfExists)
    }
    Files.createDirectories(dir)
  }
}
