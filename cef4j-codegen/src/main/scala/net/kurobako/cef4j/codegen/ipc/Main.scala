package net.kurobako.cef4j.codegen.ipc

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

import net.kurobako.cef4j.codegen.AtomicFiles
import net.kurobako.cef4j.codegen.CHeaderParser
import net.kurobako.cef4j.codegen.CefDecl
import net.kurobako.cef4j.codegen.DocComments
import net.kurobako.cef4j.codegen.FileSystem
import net.kurobako.cef4j.codegen.HeaderMetadataIndex
import net.kurobako.cef4j.codegen.Naming
import net.kurobako.cef4j.codegen.Preprocessor
import net.kurobako.cef4j.codegen.namedStruct
import net.kurobako.cef4j.codegen.passes.InitialiseDocContext
import net.kurobako.cef4j.codegen.{Config => CodegenConfig}

object Main {

  def main(args: Array[String]): Unit = {
    val cfg         = parseArgs(args.toList)
    val handWritten = Specs.all(cfg.javaPackage)
    val (astSpecs, astFacades, astHandlersAll, clientGetters, astDataStructs, astJvmVisitors)
        : (
            List[MessageSpec],
            List[FacadeSpec],
            List[HandlerSpec],
            Map[String, String],
            List[DataStructSpec],
            List[JvmVisitorSpec]
        ) =
      cfg.cefInclude match {
        case Some(inc) => deriveFromCef(inc, cfg.compilerId, cfg.javaPackage, cfg.cefApiVersionRaw)
        case None      =>
          (
            List.empty[MessageSpec],
            List.empty[FacadeSpec],
            List.empty[HandlerSpec],
            Map.empty[String, String],
            List.empty[DataStructSpec],
            List.empty[JvmVisitorSpec]
          )
      }
    // JVM-owned visitors replace their broadcast handlers.
    val visitorStructs                       = astJvmVisitors.map(_.cefStructName).toSet
    val astHandlers                          = astHandlersAll.filterNot(h => visitorStructs.contains(h.cefStructName))
    val visitorEventSpecs: List[MessageSpec] = astJvmVisitors.map { v =>
      MessageSpec(
        className = v.eventClassName,
        packageName = v.packageName,
        messageId = SpecDeriver.stableId(v.eventClassName),
        fields = FieldSpec("callbackId", FieldType.I32) :: v.params
      )
    }
    // Hand-written protocol shapes override derived ones.
    val handWrittenNames                   = handWritten.map(_.className).toSet
    val visitorEventBlocklist: Set[String] = astJvmVisitors.map { v =>
      val structPrefix = v.cefStructName.stripPrefix("cef_").stripSuffix("_t")
      val opName       = v.cefMethodName
      val sp           = structPrefix.split('_').iterator.filter(_.nonEmpty).map(p => p.head.toUpper +: p.tail).mkString
      val mp           = opName.split('_').iterator.filter(_.nonEmpty).map(p => p.head.toUpper +: p.tail).mkString
      sp + mp + "Event"
    }.toSet
    val astFiltered =
      astSpecs.filterNot(s => handWrittenNames.contains(s.className) || visitorEventBlocklist.contains(s.className))
    val visitorEvents = visitorEventSpecs.filterNot(s => handWrittenNames.contains(s.className))
    val specs         = handWritten ++ astFiltered ++ visitorEvents
    // Facades cannot target overridden wire shapes.
    val facades = astFacades.map { f =>
      val kept = f.methods.filterNot(m =>
        handWrittenNames.contains(m.requestClassName) || handWrittenNames.contains(m.responseClassName)
      )
      f.copy(methods = kept)
    }.filter(_.methods.nonEmpty)
    if (cfg.cefInclude.isDefined) {
      println(
        s"cef4j ipc codegen: hand-written=${handWritten.size}, " +
          s"AST messages=${astSpecs.size} (${astSpecs.size - astFiltered.size} shadowed by hand-written), " +
          s"AST facades=${facades.size}/${astFacades.size}, " +
          s"AST handlers=${astHandlers.size}, " +
          s"AST data structs=${astDataStructs.size}"
      )
    }

    val javaCleared = cfg.outJava.fold(0) { outJava =>
      val pkgDir = outJava.resolve(cfg.javaPackage.replace('.', '/'))
      FileSystem.createDirectories(pkgDir)
      val cleared = clearDir(pkgDir, ".java")
      specs.foreach { spec =>
        write(pkgDir.resolve(s"${spec.className}.java"), JavaEmitter.emit(spec))
      }
      val facadeByCefStruct   = facades.map(f => f.cefStructName -> f.className).toMap
      val affinityByCefStruct = facades.map(f => f.cefStructName -> f.affinity).toMap
      facades.foreach { facade =>
        write(
          pkgDir.resolve(s"${facade.className}.java"),
          JavaFacadeEmitter.emit(facade, facadeByCefStruct, affinityByCefStruct)
        )
      }
      astHandlers.foreach { handler =>
        write(pkgDir.resolve(s"${handler.className}.java"), JavaHandlerEmitter.emit(handler))
      }
      astDataStructs.foreach { ds =>
        write(pkgDir.resolve(s"${ds.className}.java"), JavaDataStructEmitter.emit(ds))
      }
      astJvmVisitors.foreach { v =>
        write(pkgDir.resolve(s"${v.className}.java"), JavaJvmVisitorEmitter.emit(v))
      }
      cleared
    }

    val cppCleared = cfg.outCpp.fold(0) { outCpp =>
      FileSystem.createDirectories(outCpp)
      val cleared = clearDir(outCpp, ".h")
      specs.foreach { spec =>
        write(outCpp.resolve(s"${spec.className}.h"), CppEmitter.emit(spec))
      }
      astDataStructs.foreach { ds =>
        val asMessage = MessageSpec(ds.className, ds.packageName, 0, ds.fields)
        write(outCpp.resolve(s"${ds.className}.h"), CppEmitter.emit(asMessage, exactPayload = false))
      }
      if (facades.nonEmpty) {
        val capiHeaders = cfg.cefInclude.toList.flatMap { inc =>
          val cefRoot = inc.getParent
          val capiDir = inc.resolve("capi")
          List(capiDir, capiDir.resolve("views"))
            .filter(Files.isDirectory(_))
            .flatMap(dir =>
              FileSystem.children(dir).filter(p => Files.isRegularFile(p) && p.toString.endsWith("_capi.h"))
            )
            .map(p => cefRoot.relativize(p).toString.replace('\\', '/'))
        }
        // Renderer release is not associated with a derived facade.
        val manualRendererRelayIds: List[Int] =
          handWritten.collect { case s if s.className == "RendererReleaseHandleRequest" => s.messageId }
        val dispatcher = CppDispatcherEmitter.emit(
          CppDispatcherEmitter.DispatchInputs(
            facades = facades,
            messageSpecs = specs,
            capiHeaders = capiHeaders,
            packageName = cfg.javaPackage,
            dataStructByCef = astDataStructs.map(d => d.cefStructName -> d).toMap,
            jvmVisitorByCef = astJvmVisitors.map(v => v.cefStructName -> v).toMap,
            manualRendererRelayIds = manualRendererRelayIds
          )
        )
        write(outCpp.resolve("Dispatcher.h"), dispatcher)
        if (facades.exists(_.affinity == ProcessAffinity.Renderer)) {
          val renderer = CppRendererDispatcherEmitter.emit(
            CppRendererDispatcherEmitter.RendererInputs(
              facades = facades,
              messageSpecs = specs,
              capiHeaders = capiHeaders,
              packageName = cfg.javaPackage
            )
          )
          write(outCpp.resolve("RendererDispatcher.h"), renderer)
        }
        if (astHandlers.nonEmpty) {
          val forwarders = CppHandlerForwarderEmitter.emit(
            CppHandlerForwarderEmitter.ForwarderInputs(
              handlers = astHandlers,
              facadeStructs = facades.map(_.cefStructName).toSet,
              capiHeaders = capiHeaders,
              packageName = cfg.javaPackage,
              clientGetters = clientGetters
            )
          )
          write(outCpp.resolve("HandlerForwarders.h"), forwarders)
        }
        if (astJvmVisitors.nonEmpty) {
          // CEF groups some visitor typedefs in unrelated C API headers.
          val visitors = CppJvmVisitorEmitter.emit(
            CppJvmVisitorEmitter.Inputs(
              visitors = astJvmVisitors,
              capiHeaders = capiHeaders,
              packageName = cfg.javaPackage
            )
          )
          write(outCpp.resolve("JvmVisitors.h"), visitors)
        }
      }
      cleared
    }

    println(
      s"cef4j ipc codegen: emitted=${specs.size} (java cleared=$javaCleared into ${cfg.outJava}, cpp cleared=$cppCleared into ${cfg.outCpp})"
    )
  }

  private def clearDir(dir: Path, suffix: String): Int =
    if (!Files.exists(dir)) 0
    else {
      val files = FileSystem.children(dir).filter(path => Files.isRegularFile(path) && path.toString.endsWith(suffix))
      files.foreach(Files.delete)
      files.size
    }

  private def write(target: Path, source: String): Unit = AtomicFiles.writeString(target, source)

  private case class Config(
      outJava: Option[Path] = None,
      outCpp: Option[Path] = None,
      javaPackage: String = "net.kurobako.cef4j.ipc.protocol.gen",
      cefInclude: Option[Path] = None,
      compilerId: String = "gcc",
      cefApiVersionRaw: Option[String] = None
  )

  private def parseArgs(args: List[String]): Config =
    args.foldLeft(Config()) { (cfg, arg) =>
      arg match {
        case s"--out-java=$path"       => cfg.copy(outJava = Some(Paths.get(path)))
        case s"--out-cpp=$path"        => cfg.copy(outCpp = Some(Paths.get(path)))
        case s"--java-package=$value"  => cfg.copy(javaPackage = value)
        case s"--cef-include=$path"    => cfg.copy(cefInclude = Some(Paths.get(path)))
        case s"--compiler=$id"         => cfg.copy(compilerId = id)
        case s"--cef-api-version=$raw" => cfg.copy(cefApiVersionRaw = Some(raw))
        case other                     =>
          throw new IllegalArgumentException(s"Unknown argument: $other")
      }
    }

  // Preprocessing must use the runtime's CEF_API_VERSION to preserve struct layout.
  private def deriveFromCef(
      cefInclude: Path,
      compilerId: String,
      packageName: String,
      cefApiVersionRaw: Option[String]
  ): (
      List[MessageSpec],
      List[FacadeSpec],
      List[HandlerSpec],
      Map[String, String],
      List[DataStructSpec],
      List[JvmVisitorSpec]
  ) = {
    val cefRoot     = cefInclude.getParent
    val capiDir     = cefInclude.resolve("capi")
    val typesHeader = cefInclude.resolve("internal/cef_types.h")
    val capiHeaders = List(capiDir, capiDir.resolve("views"))
      .filter(Files.isDirectory(_))
      .flatMap(dir =>
        FileSystem.children(dir).filter(p => Files.isRegularFile(p) && p.toString.endsWith("_capi.h"))
      )
      .sorted

    val defines = cefApiVersionRaw.toList.map(raw => s"CEF_API_VERSION=${CodegenConfig.normaliseCefApiVersion(raw)}")

    val handlerNames            = CHeaderParser.parseHandlerAnnotations(cefInclude)
    val rawDecls: List[CefDecl] =
      (capiHeaders :+ typesHeader)
        .filter(Files.isRegularFile(_))
        .flatMap { h =>
          val src = Preprocessor.preprocess(h, List(cefRoot), compilerId, defines)
          CHeaderParser.parse(src, handlerNames)
        }
    val decls: List[CefDecl] = CHeaderParser.promoteBufferParams(rawDecls)

    val metadata                           = HeaderMetadataIndex(cefInclude)
    given ipcNamingContext: Naming.Context = Naming.Context.fromCppClassNames(
      metadata.cppClassNames,
      metadata.deriveCompoundSegments(cefInclude),
      packageName
    )
    val structNames                          = decls.flatMap(_.namedStruct).toSet
    val baseDocs                             = DocComments.withClassNames(InitialiseDocContext(cefInclude), structNames)
    given ipcDocContext: DocComments.Context = DocComments.withMethodSignatures(baseDocs, decls)
    val methodDocs: Map[(String, String), String] = decls.flatMap {
      case d: CefDecl.ObjectStruct =>
        d.fns.map(fn =>
          (d.name, fn.name) -> remoteSafeDoc(DocComments.forMethod(fn, metadata.docs, d.sourceHeader, "", d.name))
        )
      case d: CefDecl.HandlerStruct =>
        d.fns.map(fn =>
          (d.name, fn.name) -> remoteSafeDoc(DocComments.forMethod(fn, metadata.docs, d.sourceHeader, "", d.name))
        )
      case _ => Nil
    }.filter(_._2.nonEmpty).toMap

    val dataStructs      = SpecDeriver.deriveDataStructs(decls, packageName)
    val knownDataStructs = dataStructs.map(_.cefStructName).toSet
    (
      SpecDeriver.derive(decls, packageName, knownDataStructs),
      SpecDeriver.deriveFacades(decls, packageName, knownDataStructs, methodDocs),
      SpecDeriver.deriveHandlers(decls, packageName, knownDataStructs, methodDocs),
      SpecDeriver.deriveClientGetters(decls),
      dataStructs,
      SpecDeriver.deriveJvmVisitors(decls, packageName, methodDocs)
    )
  }

  // JNI-projection links do not resolve against remote wire signatures.
  private def remoteSafeDoc(doc: String): String = {
    val Link = """\{@link\s+([^\s}]+)(?:\s+[^}]*)?\}""".r
    Link.replaceAllIn(
      doc,
      matched => {
        val target       = matched.group(1)
        val hash         = target.indexOf('#')
        val rawClassName = if (hash >= 0) target.substring(0, hash) else target
        val className    = rawClassName.substring(rawClassName.lastIndexOf('.') + 1)
        val member       =
          if (hash < 0) ""
          else {
            val raw = target.substring(hash + 1)
            "." + raw.takeWhile(_ != '(') + (if (raw.contains('(')) "()" else "")
          }
        s"{@code $className$member}"
      }
    )
  }
}
