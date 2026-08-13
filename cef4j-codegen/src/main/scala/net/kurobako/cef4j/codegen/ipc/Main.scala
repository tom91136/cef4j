package net.kurobako.cef4j.codegen.ipc

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import scala.jdk.StreamConverters.*

import net.kurobako.cef4j.codegen.AtomicFiles
import net.kurobako.cef4j.codegen.CHeaderParser
import net.kurobako.cef4j.codegen.CefDecl
import net.kurobako.cef4j.codegen.DocComments
import net.kurobako.cef4j.codegen.HeaderMetadataIndex
import net.kurobako.cef4j.codegen.Naming
import net.kurobako.cef4j.codegen.Preprocessor
import net.kurobako.cef4j.codegen.namedStruct
import net.kurobako.cef4j.codegen.passes.InitialiseDocContext
import net.kurobako.cef4j.codegen.{Config => CodegenConfig}

/** IPC codegen entry point. Hand-written specs for now; later sessions add a CEF AST-driven derivation.
  *
  * Java output: one {@code .java} file per spec into {@code <out-java>/<javaPackage path>/}. C++ output: one {@code .h}
  * file per spec into {@code <out-cpp>/}. Either may be omitted.
  */
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
    // Visitor structs are JVM-owned — strip them from the runtime-server-owned handler set so we don't double-emit a
    // dead "broadcast" forwarder + a JVM-visitor synthetic for the same struct.
    val visitorStructs = astJvmVisitors.map(_.cefStructName).toSet
    val astHandlers    = astHandlersAll.filterNot(h => visitorStructs.contains(h.cefStructName))
    // Visitor events carry a callbackId for routing within the JVM. Synthesise one MessageSpec per visitor.
    val visitorEventSpecs: List[MessageSpec] = astJvmVisitors.map { v =>
      MessageSpec(
        className = v.eventClassName,
        packageName = v.packageName,
        messageId = SpecDeriver.stableId(v.eventClassName),
        fields = FieldSpec("callbackId", FieldType.I32) :: v.params
      )
    }
    // Hand-written specs take precedence: their class names (e.g. BrowserCanGoBackResponse) intentionally shadow
    // any AST-derived spec with the same name so callers see the more ergonomic field shape from Specs.
    val handWrittenNames = handWritten.map(_.className).toSet
    // Drop the auto-generated VisitEvent classes for visitor structs — they're broadcast-shape and unused; the
    // visitor-specific *CallbackEvent above replaces them.
    val visitorEventBlocklist: Set[String] = astJvmVisitors.map { v =>
      val structPrefix = v.cefStructName.stripPrefix("cef_").stripSuffix("_t")
      val opName       = v.cefMethodName
      // Hand-rolled join matching the auto-generated naming; e.g. cef_string_visitor_t::visit -> "StringVisitorVisitEvent"
      val sp = structPrefix.split('_').iterator.filter(_.nonEmpty).map(p => p.head.toUpper +: p.tail).mkString
      val mp = opName.split('_').iterator.filter(_.nonEmpty).map(p => p.head.toUpper +: p.tail).mkString
      sp + mp + "Event"
    }.toSet
    val astFiltered =
      astSpecs.filterNot(s => handWrittenNames.contains(s.className) || visitorEventBlocklist.contains(s.className))
    val visitorEvents = visitorEventSpecs.filterNot(s => handWrittenNames.contains(s.className))
    val specs         = handWritten ++ astFiltered ++ visitorEvents
    // Drop facade methods that point at a Request/Response shadowed by a hand-written spec — the field shape
    // probably differs (e.g. `canGoBack` instead of `result`), so the generic facade getter wouldn't compile.
    // Hand-written code can dispatch directly through `session.request` for those.
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
      Files.createDirectories(pkgDir)
      val cleared = clearDir(pkgDir, ".java")
      specs.foreach { spec =>
        write(pkgDir.resolve(s"${spec.className}.java"), JavaEmitter.emit(spec))
      }
      // Facades land alongside the message types in the same package, with the same `gen` namespace. Pass
      // the cef-struct → facade-class map so methods returning a RemoteHandle to a known facade can be
      // typed as the wrapper (e.g. `Browser.getMainFrame() -> CompletableFuture<Frame>`).
      val facadeByCefStruct   = facades.map(f => f.cefStructName -> f.className).toMap
      val affinityByCefStruct = facades.map(f => f.cefStructName -> f.affinity).toMap
      facades.foreach { facade =>
        write(
          pkgDir.resolve(s"${facade.className}.java"),
          JavaFacadeEmitter.emit(facade, facadeByCefStruct, affinityByCefStruct)
        )
      }
      // Handler interfaces (CefLoadHandler, CefRenderHandler, etc.) land in the same package.
      astHandlers.foreach { handler =>
        write(pkgDir.resolve(s"${handler.className}.java"), JavaHandlerEmitter.emit(handler))
      }
      // By-value data structs (BrowserSettings, WindowInfo, etc.) — POJOs with constructor + encode/decode.
      astDataStructs.foreach { ds =>
        write(pkgDir.resolve(s"${ds.className}.java"), JavaDataStructEmitter.emit(ds))
      }
      // JVM-owned visitor interfaces (CefStringVisitor, etc.) — typed callbacks the JVM provides; the
      // generated route() static helper subscribes routing of the matching CallbackEvent.
      astJvmVisitors.foreach { v =>
        write(pkgDir.resolve(s"${v.className}.java"), JavaJvmVisitorEmitter.emit(v))
      }
      cleared
    }

    val cppCleared = cfg.outCpp.fold(0) { outCpp =>
      Files.createDirectories(outCpp)
      val cleared = clearDir(outCpp, ".h")
      specs.foreach { spec =>
        write(outCpp.resolve(s"${spec.className}.h"), CppEmitter.emit(spec))
      }
      // Data structs share the wire layout with messages; re-using `CppEmitter` keeps the codecs in lock-step.
      // The synthetic messageId=0 is harmless dead code on the C++ side (no one dispatches by it).
      astDataStructs.foreach { ds =>
        val asMessage = MessageSpec(ds.className, ds.packageName, 0, ds.fields)
        write(outCpp.resolve(s"${ds.className}.h"), CppEmitter.emit(asMessage))
      }
      // Dispatcher header is only meaningful when AST facades are available (i.e. --cef-include was passed); a
      // runtime server running with hand-written specs only doesn't need a generated dispatcher. Emit relative paths
      // (e.g. `include/capi/cef_browser_capi.h`, `include/capi/views/cef_box_layout_capi.h`) so the runtime server's
      // `-I${CEF_ROOT}` lands on them correctly.
      if (facades.nonEmpty) {
        val capiHeaders = cfg.cefInclude.toList.flatMap { inc =>
          val cefRoot = inc.getParent
          val capiDir = inc.resolve("capi")
          List(capiDir, capiDir.resolve("views"))
            .filter(Files.isDirectory(_))
            .flatMap(dir =>
              Files.list(dir).toScala(List).filter(p => Files.isRegularFile(p) && p.toString.endsWith("_capi.h"))
            )
            .map(p => cefRoot.relativize(p).toString.replace('\\', '/'))
        }
        // The renderer relay also needs to forward `RendererReleaseHandleRequest` (hand-written, msgId=24).
        // It's not tied to a facade so the per-facade auto-collection misses it; pass it through the
        // manualRendererRelayIds escape hatch. Resolve the messageId from the spec list to stay symbolic.
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
        // Renderer-side dispatcher mirrors the browser's, scoped to renderer-affinity facades. The browser
        // dispatcher emits relay case labels for renderer messageIds; this header turns the relayed payloads
        // into real C-API calls inside the renderer's V8 context.
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
          // CEF doesn't strictly map cef_<struct>_t → cef_<struct>_capi.h (e.g. cef_completion_callback_t lives
          // in cef_callback_capi.h). Reuse the dispatcher's full capi-header list — the per-spec lookup is
          // brittle when CEF groups multiple typedefs in one header.
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

  private def clearDir(dir: Path, suffix: String): Int = {
    if (!Files.exists(dir)) return 0
    var n = 0
    Files.list(dir).forEach { p =>
      if (Files.isRegularFile(p) && p.toString.endsWith(suffix)) {
        Files.delete(p)
        n += 1
      }
    }
    n
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

  /** Run the existing parse pipeline against {@code cefInclude} and feed the resulting decls through
    * {@link SpecDeriver}. Skips the cefRoot check; expects a CEF dist {@code include/} directory layout.
    *
    * When {@code cefApiVersionRaw} is provided, it gets normalised (146 → 14600 etc.) and forwarded to the preprocessor
    * as a `CEF_API_VERSION=NNNNN` define so that version-gated `#if CEF_API_ADDED(...)` blocks match the
    * CEF_API_VERSION the runtime server compiles against. Without this, the codegen sees experimental methods that
    * aren't actually present in the runtime server's struct layout, leading to "no member named X" compile failures.
    */
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
        Files.list(dir).toScala(List).filter(p => Files.isRegularFile(p) && p.toString.endsWith("_capi.h"))
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
    // Promote void* + size pairs into CType.Buffer/BufferSize so deriveFacadeMethod can map Buffer params to
    // FieldType.Bytes. Without this, methods like cef_post_data_element_t::set_to_bytes silently get skipped
    // because the void* stays as OpaquePtr (not a wire-supported type).
    val decls: List[CefDecl] = CHeaderParser.promoteBufferParams(rawDecls)

    // Reuse the same metadata index and normalization pipeline as the in-process bindings. The remote facade and
    // handler APIs are another projection of the same CEF headers, so throwing away those comments here made the two
    // public surfaces needlessly different and lost source links, thread notes and cef_meta semantics.
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

    // Resolve data structs first so we can pass the known set to the message/facade derivers. They use it
    // to skip methods whose params reference a struct the data-struct deriver couldn't emit (cef_cookie_t,
    // cef_key_event_t, etc. — they have nested data structs / char16 fields the overlay deriver doesn't
    // support yet). Without this filter the dispatcher would emit `#include "Cookie.h"` for an overlay we
    // never wrote, breaking the runtime server build.
    val dataStructs      = SpecDeriver.deriveDataStructs(decls, packageName)
    val knownDataStructs = dataStructs.map(_.cefStructName).toSet
    (
      SpecDeriver.derive(decls, packageName, knownDataStructs),
      SpecDeriver.deriveFacades(decls, packageName, knownDataStructs, methodDocs),
      SpecDeriver.deriveHandlers(decls, packageName, knownDataStructs, methodDocs),
      SpecDeriver.deriveClientGetters(decls),
      dataStructs,
      SpecDeriver.deriveJvmVisitors(decls, packageName, knownDataStructs, methodDocs)
    )
  }

  /** Inline links emitted for the JNI projection carry JNI parameter types. The remote projection deliberately uses
    * wire types instead, and some facade names omit the Cef prefix. Preserve the reference as code without publishing a
    * link to a signature that does not exist in this projection; external CEF source links remain untouched.
    */
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
