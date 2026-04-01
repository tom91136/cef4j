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
    // Read CEF version for Doxygen doc links
    val versionHeader  = cfg.cefInclude.resolve("cef_version.h")
    val versionContent = if (Files.exists(versionHeader)) Files.readString(versionHeader) else ""
    val cefMajor = """CEF_VERSION_MAJOR\s+(\d+)""".r.findFirstMatchIn(versionContent).map(_.group(1).toInt).getOrElse(0)
    val cefMinor = """CEF_VERSION_MINOR\s+(\d+)""".r.findFirstMatchIn(versionContent).map(_.group(1).toInt).getOrElse(0)
    DocComments.initDocsBaseUrl(cefMajor, cefMinor)

    val handlerNames     = CHeaderParser.parseHandlerAnnotations(cfg.cefInclude)
    val docs             = CHeaderParser.parseDocComments(cfg.cefInclude)
    val cppTypeInfo      = CHeaderParser.parseCppTypeInfo(cfg.cefInclude)
    val cppClassNames    = CHeaderParser.parseCppClassNames(cfg.cefInclude)
    val compoundSegments = CHeaderParser.deriveCompoundSegments(cfg.cefInclude, cppClassNames)
    Naming.initCppClassNames(cppClassNames, compoundSegments)
    val enumDocs  = CHeaderParser.parseEnumDocs(cfg.cefInclude)
    val classDocs = CHeaderParser.parseClassDocs(cfg.cefInclude)
    // Build struct->header map by scanning raw CAPI headers for typedef struct definitions
    val structHeaderMap = buildStructHeaderMap(capiDir)

    val rawDecls = preprocessed.flatMap { case (_, src) =>
      CHeaderParser.parse(src, handlerNames).map(attachSourceHeader(_, structHeaderMap))
    }

    // 2a. Parse free functions from raw CAPI headers
    val knownStructNames = rawDecls.flatMap(_.namedStruct).toSet
    val dataStructNames  = rawDecls.collect { case d: CefDecl.DataStruct => d.name }.toSet
    val rawFreeFunctions = CHeaderParser.parseFreeExports(capiDir, knownStructNames, dataStructNames)

    // 2b. Reclassify Ptr types now that all struct names are known (structs + free functions)
    val allRawDecls   = rawDecls ++ rawFreeFunctions
    val reclassified  = CHeaderParser.reclassifyPointers(allRawDecls, handlerNames)
    val arrayPromoted = CHeaderParser.promoteArrayParams(reclassified)
    val decls         = CHeaderParser.promoteBufferParams(arrayPromoted)

    // Separate struct decls from free functions after type resolution
    val structDecls = decls.collect {
      case d: CefDecl.ObjectStruct  => d
      case d: CefDecl.HandlerStruct => d
      case d: CefDecl.DataStruct    => d
      case d: CefDecl.Enum          => d
    }
    val freeFunctions = decls.collect { case d: CefDecl.FreeFunction => d }

    // 2c. Enrich FnPtrs with metacomment attributes, recovered C++ types, and enum docs
    val enrichedDecls0       = structDecls.map(enrichDecl(_, docs, cppTypeInfo, enumDocs))
    val enrichedDecls        = promoteCountFuncArrays(enrichedDecls0)
    val enrichedFreeFuncs    = enrichFreeFunctions(freeFunctions, docs, cppTypeInfo)
    val javaClassesGenerated = enrichedDecls.size
    val cppClassesGenerated  = enrichedDecls.count(_.isCppGenerated)

    // Group free functions by owner struct
    val freeFuncsByOwner = enrichedFreeFuncs.groupBy(_.ownerStruct)
    val orphanFreeFuncs  = freeFuncsByOwner.getOrElse("", Nil)

    // 2d. Initialize doc comment conversion maps
    val allEnums = enrichedDecls.collect { case d: CefDecl.Enum => d }
    DocComments.initEnumConstants(allEnums)
    DocComments.initClassNames(knownStructNames)
    DocComments.initMethodSignatures(enrichedDecls)

    // 3. Emit - clean generated dirs first, then write
    cleanDir(cfg.outCpp)
    cleanDir(cfg.outJava)

    // Collect by-value struct names that appear in ByValueOut params (need mutable variants)
    val structByValueOuts = enrichedDecls.flatMap {
      case d: CefDecl.ObjectStruct  => d.fns.flatMap(_.params)
      case d: CefDecl.HandlerStruct => d.fns.flatMap(_.params)
      case _                        => Nil
    }.collect { case p if p.typ.isInstanceOf[CType.ByValueOut] => p.typ.asInstanceOf[CType.ByValueOut].cefName }
    val ffByValueOuts = enrichedFreeFuncs.flatMap(_.params)
      .collect { case p if p.typ.isInstanceOf[CType.ByValueOut] => p.typ.asInstanceOf[CType.ByValueOut].cefName }
    val needsMutableSet = (structByValueOuts ++ ffByValueOuts).toSet

    val dataStructMap = enrichedDecls.collect { case d: CefDecl.DataStruct => d.name -> d }.toMap
    val scopedNames   = enrichedDecls.collect { case d: CefDecl.ObjectStruct if d.scoped => d.name }.toSet
    val jniCodeGen    = new JniCppCodeGen(dataStructMap, handlerNames, scopedNames, structHeaderMap)

    // Error on any remaining untyped CType.Ptr — every pointer must be classified
    val untypedPtrWarnings = (collectUntypedPtrWarnings(enrichedDecls, handlerNames) ++
      collectFreeFuncUntypedPtrWarnings(enrichedFreeFuncs, handlerNames)).distinct
    if (untypedPtrWarnings.nonEmpty) {
      System.err.println(s"\n====================================")
      System.err.println(
        s"ERROR: ${untypedPtrWarnings.size} unresolved pointer parameter(s)/return(s) — all pointers must be typed:"
      )
      val byInner = untypedPtrWarnings.groupBy(_.innerType).toList.sortBy(-_._2.size)
      byInner.foreach { case (inner, warnings) =>
        System.err.println(s"\n  $inner* (${warnings.size} occurrences):")
        warnings.sortBy(w => (w.structName, w.fnName)).foreach { w =>
          System.err.println(s"    ${w.structName} :: ${w.fnName} ${w.position}")
        }
      }
      System.err.println(s"====================================\n")
      throw new RuntimeException(s"${untypedPtrWarnings.size} unresolved CType.Ptr remaining — codegen cannot continue")
    }

    emitNativePointer(cfg.outJava)
    emitMarkerInterfaces(cfg.outJava)
    enrichedDecls.foreach(emitDecl(
      _,
      cfg,
      docs,
      classDocs,
      needsMutableSet,
      jniCodeGen,
      handlerNames,
      freeFuncsByOwner
    ))

    // Emit CefGlobals for orphan free functions
    if (orphanFreeFuncs.nonEmpty) {
      JavaInterfaceCodeGen.emitGlobals(orphanFreeFuncs, cfg.outJava, docs)
      jniCodeGen.emitGlobals(orphanFreeFuncs, cfg.outCpp)
    }

    val elapsedSeconds = (System.nanoTime() - startNanos) / 1_000_000_000.0
    println(
      f"""cef4j codegen complete:
         |  headers scanned: $headersScanned%d
         |  Java classes generated: $javaClassesGenerated%d
         |  C++ classes generated: $cppClassesGenerated%d
         |  declarations processed: ${decls.size}%d
         |  free functions parsed: ${enrichedFreeFuncs.size}%d (${orphanFreeFuncs.size}%d orphans)
         |  Untyped ptr warnings: ${untypedPtrWarnings.size}%d
         |  total time: $elapsedSeconds%.2f s""".stripMargin
    )
  }

  /** Post-enrichment pass: when a function has a count_func metacomment, flatten the two-pass count+array out-param
    * pattern into a single CountFuncArray return type.
    *
    * count_func format: "arrayParam:CountMethodCppName" (e.g., "chain:GetIssuerChainSize"). The count param is named
    * "${arrayParam}Count" with type OutPrimitivePtr(SizeT). The array param is named "arrayParam" with whatever type it
    * was resolved to.
    */
  private def promoteCountFuncArrays(decls: List[CefDecl]): List[CefDecl] = {

    def parseCountFunc(spec: String): Option[(String, String)] =
      spec match {
        case s"$param:$func" if param.nonEmpty && func.nonEmpty => Some((param, func))
        case _                                                  => None
      }

    /** Convert a PascalCase C++ method name to the snake_case C function-pointer name. */
    def toSnakeCase(pascal: String): String =
      pascal.replaceAll("([a-z0-9])([A-Z])", "$1_$2").toLowerCase

    /** Determine the element type from the array param's current CType. */
    def elementType(ct: CType): Option[CType] = ct match {
      case CType.ObjectPtrArray(name)   => Some(CType.ObjectPtr(name))
      case CType.OutObjectPtr(name)     => Some(CType.ObjectPtr(name))
      case CType.ObjectPtr(name)        => Some(CType.ObjectPtr(name))
      case CType.ByValueOut(name)       => Some(CType.ByValueIn(name))
      case CType.ByValueArray(name)     => Some(CType.ByValueIn(name))
      case CType.OutPrimitivePtr(inner) => Some(inner)
      case CType.OpaquePtr              => None // can't determine element type
      case _                            => None
    }

    def promoteFn(fn: FnPtr): FnPtr =
      fn.metaAttrs.collectFirst { case ("count_func", spec) => spec }
        .flatMap(parseCountFunc) match {
        case None                                   => fn
        case Some((arrayParamName, countMethodCpp)) =>
          val countParamName = s"${arrayParamName}Count"
          val countParam     = fn.params.find(_.name == countParamName)
          val arrayParam     = fn.params.find(_.name == arrayParamName)

          (countParam, arrayParam) match {
            case (Some(_), Some(ap)) =>
              elementType(ap.typ) match {
                case Some(elemTy) =>
                  val countFuncC = toSnakeCase(countMethodCpp)
                  val cfaType    = CType.CountFuncArray(elemTy, countFuncC, countParamName, arrayParamName)
                  // Remove both params, change return type to the array
                  val newParams = fn.params.filterNot(p => p.name == countParamName || p.name == arrayParamName)
                  // If original return was void, the new return is the array.
                  // If original return was non-void (e.g., bool for GetTaskIdsList), keep it — the array
                  // becomes an extra out-param-turned-return. For now we handle void only cleanly;
                  // for bool returns we still flatten but the bool is lost (acceptable: the bool just
                  // indicates whether the task manager is available, not whether the array is valid).
                  fn.copy(ret = cfaType, params = newParams)
                case None =>
                  System.err.println(
                    s"  WARN: count_func on ${fn.name}: cannot determine element type for ${ap.name} (${ap.typ})"
                  )
                  fn
              }
            case _ =>
              System.err.println(
                s"  WARN: count_func on ${fn.name}: expected params $countParamName + $arrayParamName but not found"
              )
              fn
          }
      }

    decls.map {
      case d: CefDecl.ObjectStruct  => d.copy(fns = d.fns.map(promoteFn))
      case d: CefDecl.HandlerStruct => d.copy(fns = d.fns.map(promoteFn))
      case other                    => other
    }
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
    val enriched = recoverCppTypes(enrichWithMetaAttrs(decl, docs, cppTypeInfo.keySet), cppTypeInfo)
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
      needsMutableSet: Set[String] = Set.empty,
      jniCodeGen: JniCppCodeGen = null,
      handlerNames: Set[String] = Set.empty,
      freeFuncsByOwner: Map[String, List[CefDecl.FreeFunction]] = Map.empty
  ): Unit =
    decl match {
      case d: CefDecl.ObjectStruct =>
        val associatedFFs = freeFuncsByOwner.getOrElse(d.name, Nil)
        jniCodeGen.emit(d, cfg.outCpp, associatedFFs)
        JavaInterfaceCodeGen.emitObject(
          d,
          cfg.outJava,
          docs,
          classDocs.getOrElse(d.name, ""),
          handlerNames = handlerNames,
          freeFunctions = associatedFFs
        )
      case d: CefDecl.HandlerStruct =>
        jniCodeGen.emitHandler(d, cfg.outCpp)
        JavaInterfaceCodeGen.emitHandler(
          d,
          cfg.outJava,
          docs,
          classDocs.getOrElse(d.name, ""),
          handlerNames = handlerNames
        )
      case d: CefDecl.DataStruct =>
        JavaRecordCodeGen.emit(
          d,
          cfg.outJava,
          classDocs.getOrElse(d.name, ""),
          needsMutable = needsMutableSet.contains(d.name)
        )
      case d: CefDecl.Enum =>
        JavaEnumCodeGen.emit(d, cfg.outJava, sourceHeader = "cef_types.h")
      case _: CefDecl.FreeFunction => // handled separately
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

      // Find the matching C++ key — this gives us the authoritative PascalCase name
      val matchedKey = cppTypeInfo.get(pascal).map(_ => pascal)
        .orElse(cppTypeInfo.get(fn.name).map(_ => fn.name))
        .orElse(capiName.filter(cppTypeInfo.contains))
        .orElse(cppTypeInfo.keys.find(_.equalsIgnoreCase(pascal)))

      val info = matchedKey.flatMap(cppTypeInfo.get)

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
          fn.copy(ret = recoveredRet, params = recoveredParams, cppName = matchedKey)
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
  private def enrichWithMetaAttrs(
      decl: CefDecl,
      docs: Map[String, String],
      cppMethodNames: Set[String]
  ): CefDecl = {
    def enrich(fn: FnPtr): FnPtr = {
      val pascal = Naming.toPascalCase(fn.name)
      // Try cppName first (authoritative), then camel/pascal fallbacks, then case-insensitive.
      val docText = fn.cppName.flatMap(docs.get)
        .orElse(docs.get(Naming.toCamelCase(fn.name))).orElse(docs.get(pascal)).orElse(docs.get(fn.name))
        .orElse(cppMethodNames.find(_.equalsIgnoreCase(pascal)).flatMap(docs.get))
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

  private def emitNativePointer(outJava: Path): Unit = {
    val content =
      s"""// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Opaque native pointer wrapper. Prevents accidental misuse of raw pointer values. */
public final class NativePointer {

    public final long address;

    public NativePointer(long address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof NativePointer)) return false;
        return this.address == ((NativePointer) obj).address;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(address);
    }

    @Override
    public String toString() {
        return "NativePointer{0x" + Long.toHexString(address) + "}";
    }
}
"""
    Files.writeString(outJava.resolve("NativePointer.java"), content)
  }

  private def emitMarkerInterfaces(outJava: Path): Unit = {
    val banner = "// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native"
    Files.writeString(
      outJava.resolve("CefLibraryObject.java"),
      s"""$banner
package net.kurobako.cef4j.gen;

/**
 * Marker interface for CEF structs allocated on the library (DLL) side.
 *
 * <p>Instances are created by the CEF library and returned to client code
 * as {@code NativePeer} wrappers. Client code calls methods on them but
 * never instantiates them directly.
 *
 * @see CefClientHandler
 */
public interface CefLibraryObject extends AutoCloseable {
    @Override void close();
}
"""
    )
    Files.writeString(
      outJava.resolve("CefClientHandler.java"),
      s"""$banner
package net.kurobako.cef4j.gen;

/**
 * Marker interface for CEF structs allocated on the client side.
 *
 * <p>Client code implements these interfaces and passes instances to the
 * CEF library (e.g., handler callbacks). The library calls back into the
 * client implementation.
 *
 * @see CefLibraryObject
 */
public interface CefClientHandler {}
"""
    )
    Files.writeString(
      outJava.resolve("CefEnum.java"),
      s"""$banner
package net.kurobako.cef4j.gen;

/**
 * Type-safe wrapper for CEF C enum values.
 *
 * <p>Known constants are pre-allocated as {@code static final} fields.
 * Unknown or composite values can be created via the {@code of(long)} factory
 * without data loss.
 *
 * @param <T> the concrete enum type (self-referential bound for type safety)
 */
public interface CefEnum<T extends CefEnum<T>> {

    /** The underlying C enum numeric value. */
    long value();

    /** The original C expression (e.g., {@code "1 << 3"}), or the numeric string for unknown values. */
    String expr();

    /** The constant name (e.g., {@code "CEF_FOO"}), or a descriptive name for unknown values. */
    String name();
}
"""
    )
  }

  case class UntypedPtrWarning(structName: String, fnName: String, innerType: String, position: String)

  private def collectUntypedPtrWarnings(
      decls: List[CefDecl],
      handlerNames: Set[String]
  ): List[UntypedPtrWarning] = {
    def isUntypedPtr(ct: CType): Option[String] = ct match {
      case CType.Ptr(inner) =>
        val stripped = inner.stripPrefix("const ").stripPrefix("struct ").trim
        // Skip void*, handler ptrs, and self ptrs (already handled)
        if (stripped == "void" || stripped.isEmpty) None
        else if (handlerNames.contains(stripped.stripPrefix("_"))) None
        else Some(inner)
      case _ => None
    }

    def collectFromFns(structName: String, fns: List[FnPtr]): List[UntypedPtrWarning] =
      fns.flatMap { fn =>
        val retWarning = isUntypedPtr(fn.ret).map(inner =>
          UntypedPtrWarning(structName, fn.name, inner, "(return)")
        ).toList
        val paramWarnings = fn.params.flatMap { p =>
          isUntypedPtr(p.typ).map(inner =>
            UntypedPtrWarning(structName, fn.name, inner, s"(param: ${p.name})")
          )
        }
        retWarning ++ paramWarnings
      }

    decls.flatMap {
      case d: CefDecl.ObjectStruct  => collectFromFns(d.name, d.fns)
      case d: CefDecl.HandlerStruct => collectFromFns(d.name, d.fns)
      case _                        => Nil
    }
  }

  /** Enrich free functions with metacomment attributes and C++ type recovery. */
  private def enrichFreeFunctions(
      ffs: List[CefDecl.FreeFunction],
      docs: Map[String, String],
      cppTypeInfo: Map[String, CHeaderParser.CppMethodTypeInfo]
  ): List[CefDecl.FreeFunction] = ffs.map { ff =>
    // Look up docs by Java method name or Pascal case
    val pascal  = Naming.toPascalCase(ff.javaMethodName)
    val docText = docs.get(ff.javaMethodName).orElse(docs.get(pascal)).orElse(docs.get(ff.cName))
    val attrs   = docText.map(DocComments.extractAttrsList).getOrElse(Nil)

    // Recover C++ types (bool -> int)
    val info = cppTypeInfo.get(pascal)
      .orElse(cppTypeInfo.get(ff.javaMethodName))
    val (recoveredRet, recoveredParams) = info match {
      case Some(ti) =>
        val ret    = recoverType(ff.ret, ti.returnType)
        val params = ff.params.map { p =>
          val cppType = ti.paramTypes.get(p.name)
            .orElse(ti.paramTypes.get(Naming.toCamelCase(p.name)))
          cppType match {
            case Some(ct) => p.copy(typ = recoverType(p.typ, ct))
            case None     => p
          }
        }
        (ret, params)
      case None => (ff.ret, ff.params)
    }

    ff.copy(ret = recoveredRet, params = recoveredParams, metaAttrs = attrs)
  }

  /** Collect untyped pointer warnings from free functions. */
  private def collectFreeFuncUntypedPtrWarnings(
      ffs: List[CefDecl.FreeFunction],
      handlerNames: Set[String]
  ): List[UntypedPtrWarning] = {
    def isUntypedPtr(ct: CType): Option[String] = ct match {
      case CType.Ptr(inner) =>
        val stripped = inner.stripPrefix("const ").stripPrefix("struct ").trim
        if (stripped == "void" || stripped.isEmpty) None
        else if (handlerNames.contains(stripped.stripPrefix("_"))) None
        else Some(inner)
      case _ => None
    }

    ffs.flatMap { ff =>
      val retWarning = isUntypedPtr(ff.ret).map(inner =>
        UntypedPtrWarning(s"FREE:${ff.cName}", ff.cName, inner, "(return)")
      ).toList
      val paramWarnings = ff.params.flatMap { p =>
        isUntypedPtr(p.typ).map(inner =>
          UntypedPtrWarning(s"FREE:${ff.cName}", ff.cName, inner, s"(param: ${p.name})")
        )
      }
      retWarning ++ paramWarnings
    }
  }
}
