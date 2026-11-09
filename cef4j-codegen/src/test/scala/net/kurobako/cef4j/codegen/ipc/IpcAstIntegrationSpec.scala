package net.kurobako.cef4j.codegen.ipc

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import scala.concurrent.duration.*

import net.kurobako.cef4j.codegen.CHeaderParser
import net.kurobako.cef4j.codegen.CefDecl
import net.kurobako.cef4j.codegen.FileSystem
import net.kurobako.cef4j.codegen.Preprocessor

class IpcAstIntegrationSpec extends munit.FunSuite {

  override val munitTimeout: FiniteDuration = 2.minutes

  private val cefRoot: Path = {
    val prop = Option(System.getProperty("cef.root")).getOrElse(fail("cef.root is not set; run mvn verify"))
    val path = Paths.get(prop)
    assert(Files.isDirectory(path), s"cef.root does not exist: $path")
    path
  }
  private val cefInclude: Path   = cefRoot.resolve("include")
  private val compilerId: String =
    Option(System.getProperty("cef.compiler")).getOrElse("gcc")

  private lazy val decls: List[CefDecl] = {
    val capiDir     = cefInclude.resolve("capi")
    val typesHeader = cefInclude.resolve("internal/cef_types.h")
    val headers     = List(capiDir, capiDir.resolve("views"))
      .filter(Files.isDirectory(_))
      .flatMap(dir =>
        FileSystem.children(dir).filter(p => Files.isRegularFile(p) && p.toString.endsWith("_capi.h"))
      )
      .sorted
    val handlers = CHeaderParser.parseHandlerAnnotations(cefInclude)
    (headers :+ typesHeader)
      .filter(Files.isRegularFile(_))
      .flatMap(h =>
        CHeaderParser.parse(Preprocessor.preprocess(h, List(cefRoot), compilerId), handlers)
      )
  }

  test("SpecDeriver produces a substantial set of message specs from real CEF headers") {
    val specs = SpecDeriver.derive(decls, "net.kurobako.cef4j.ipc.protocol.gen")
    assert(specs.size >= 100, s"expected many derived specs, got ${specs.size}")
  }

  test("every derived spec emits valid C++ and every shared shape valid Java without throwing") {
    val specs  = SpecDeriver.derive(decls, "net.kurobako.cef4j.ipc.protocol.gen")
    val shared = SharedMessages.derive(specs)
    specs.foreach { s =>
      val cpp = CppEmitter.emit(s)
      assert(cpp.contains(s"struct ${s.className}"), s"C++ emit missing struct for ${s.className}")
    }
    shared.messages.foreach { m =>
      val java = JavaEmitter.emitShared(m)
      assert(java.contains(s"class ${m.className}"), s"Java emit missing class for ${m.className}")
    }
  }

  test("derived specs collapse into far fewer shared Java classes") {
    val specs  = SpecDeriver.derive(decls, "net.kurobako.cef4j.ipc.protocol.gen")
    val shared = SharedMessages.derive(specs)
    assert(shared.messages.size * 3 < specs.size, s"${shared.messages.size} shapes for ${specs.size} specs")
    assertEquals(specs.map(_.fields).distinct.size, shared.messages.size)
    assertEquals(shared.ids.size, specs.map(_.className).distinct.size)
  }

  test("a shape keeps the name of its message unless two or more messages share it") {
    val specs      = SpecDeriver.derive(decls, "net.kurobako.cef4j.ipc.protocol.gen")
    val shared     = SharedMessages.derive(specs)
    val usesByType = specs.groupBy(s => shared.refs.typeName(s.className)).view.mapValues(_.size).toMap
    val specNames  = specs.map(_.className).toSet
    val byName     = shared.messages.map(m => m.className -> usesByType.getOrElse(m.className, 0)).toMap
    byName.foreach { case (name, uses) =>
      if (uses == 1) assert(specNames(name), s"$name has one user and is not named after it")
      else assert(name.endsWith("Message") && !specNames(name), s"$name has $uses users")
    }
    assert(byName.values.exists(_ == 1) && byName.values.exists(_ > 1))
  }

  test("ipc codegen Main emits shared messages and no per-RPC message class") {
    val out = Files.createTempDirectory("cef4j-ipc-main-")
    try {
      Main.main(Array(s"--out-java=${out.resolve("java")}", s"--cef-include=$cefInclude", s"--compiler=$compilerId"))
      val pkg = out.resolve("java/net/kurobako/cef4j/ipc/protocol/gen")
      assert(Files.isRegularFile(pkg.resolve("MessageIds.java")))
      assert(Files.isRegularFile(pkg.resolve("SelfHandleMessage.java")))
      assert(Files.isRegularFile(pkg.resolve("OsrPaintEvent.java")), "hand-written messages keep their own class")
      assert(Files.isRegularFile(pkg.resolve("BrowserHostFindRequest.java")), "a single-use shape keeps its RPC name")
      assert(!Files.exists(pkg.resolve("BrowserGoBackRequest.java")), "a shared shape drops the RPC name")
      assert(!Files.exists(pkg.resolve("LifeSpanHandlerOnAfterCreatedEvent.java")))
    } finally FileSystem.deleteTree(out)
  }

  test("derived ids stay above the hand-written reserved range") {
    val specs = SpecDeriver.derive(decls, "p")
    specs.foreach { s =>
      assert(s.messageId >= SpecDeriver.AstIdBase, s"${s.className} id ${s.messageId} below AstIdBase")
    }
  }

  test("class names are unique (no duplicate Java type collisions)") {
    val specs      = SpecDeriver.derive(decls, "p")
    val byName     = specs.groupBy(_.className)
    val collisions = byName.filter(_._2.size > 1).keys.toList.sorted
    if (collisions.nonEmpty) {
      fail(s"Duplicate generated class names (first 10): ${collisions.take(10).mkString(", ")}")
    }
  }

  test("SpecDeriver.deriveFacades produces a non-trivial set of facades from real CEF headers") {
    val facades = SpecDeriver.deriveFacades(decls, "net.kurobako.cef4j.ipc.protocol.gen")
    assert(facades.size >= 30, s"expected many derived facades, got ${facades.size}")
    val knownFacade = facades.find(_.className == "Browser")
    assert(
      knownFacade.isDefined,
      s"expected a Browser facade among derived facades; got ${facades.map(_.className).take(20)}"
    )
  }

  test("every derived facade emits valid Java source without throwing") {
    val facades = SpecDeriver.deriveFacades(decls, "net.kurobako.cef4j.ipc.protocol.gen")
    facades.foreach { f =>
      val java = JavaFacadeEmitter.emit(f)
      assert(java.contains(s"class ${f.className}"), s"Java facade emit missing class for ${f.className}")
      assert(java.contains(" extends "), s"Java facade ${f.className} does not extend a facade base")
      assert(java.contains("CefSession"), s"Java facade ${f.className} missing CefSession reference")
      assert(
        java.contains("CefFutures.map") || java.contains("CefFutures.discard"),
        s"Java facade ${f.className} does not propagate cancellation"
      )
      assert(!java.contains(".thenApply"), s"Java facade ${f.className} bypasses cancellation propagation")
      assert(java.contains("RemoteHandle"), s"Java facade ${f.className} missing RemoteHandle reference")
      assert(!java.contains("closeLock"))
      f.methods.foreach { m =>
        assert(
          java.contains(s" ${m.methodName}("),
          s"Java facade ${f.className} missing method ${m.methodName}"
        )
      }
    }
  }

  test("facade method names match request/response classes that actually exist as messages") {
    val pkg     = "net.kurobako.cef4j.ipc.protocol.gen"
    val specs   = SpecDeriver.derive(decls, pkg).map(_.className).toSet
    val facades = SpecDeriver.deriveFacades(decls, pkg)
    val missing = facades.flatMap { f =>
      f.methods.flatMap { m =>
        val req = if (specs.contains(m.requestClassName)) None
        else Some(s"${f.className}.${m.methodName}: req=${m.requestClassName}")
        val resp = if (specs.contains(m.responseClassName)) None
        else Some(s"${f.className}.${m.methodName}: resp=${m.responseClassName}")
        req.toList ++ resp.toList
      }
    }
    if (missing.nonEmpty) fail(s"Facades reference unknown message types (first 5): ${missing.take(5).mkString(", ")}")
  }

  test("SpecDeriver.deriveHandlers produces a non-trivial set of handler interfaces") {
    val handlers = SpecDeriver.deriveHandlers(decls, "net.kurobako.cef4j.ipc.protocol.gen")
    assert(handlers.size >= 20, s"expected many handler specs from CEF, got ${handlers.size}")
    val knownLoad = handlers.find(_.className == "CefLoadHandler")
    assert(knownLoad.isDefined, s"expected a CefLoadHandler among handlers; got ${handlers.map(_.className).take(20)}")
  }

  test("every derived handler emits valid Java source without throwing") {
    val handlers = SpecDeriver.deriveHandlers(decls, "net.kurobako.cef4j.ipc.protocol.gen")
    handlers.foreach { h =>
      val java = JavaHandlerEmitter.emit(h)
      assert(java.contains(s"interface ${h.className}"), s"Java handler emit missing interface for ${h.className}")
      assert(java.contains("CefSession"), s"Java handler ${h.className} missing CefSession reference")
      assert(java.contains("static CefSession.HandlerRegistration register("))
      assert(java.contains("return CefSession.HandlerRegistration.combine("))
      h.methods.foreach { m =>
        val returnType = m.returnType match {
          case None                 => "void"
          case Some(FieldType.Bool) => "java.util.Optional<Boolean>"
          case Some(FieldType.I32)  => "java.util.OptionalInt"
          case Some(_)              => "void"
        }
        assert(
          java.contains(s"default $returnType ${m.methodName}("),
          s"Java handler ${h.className} missing $returnType method ${m.methodName}"
        )
      }
    }
  }

  test("handler register() calls only reference event classes that exist as messages") {
    val pkg      = "net.kurobako.cef4j.ipc.protocol.gen"
    val specs    = SpecDeriver.derive(decls, pkg).map(_.className).toSet
    val handlers = SpecDeriver.deriveHandlers(decls, pkg)
    val missing  = handlers.flatMap { h =>
      h.methods.collect {
        case m if !specs.contains(m.eventClassName) => s"${h.className}.${m.methodName}: ev=${m.eventClassName}"
      }
    }
    if (missing.nonEmpty) fail(s"Handlers reference unknown event types (first 5): ${missing.take(5).mkString(", ")}")
  }

  test("every JVM visitor route exposes its closeable registration") {
    val visitors = SpecDeriver.deriveJvmVisitors(decls, "net.kurobako.cef4j.ipc.protocol.gen")
    assert(visitors.nonEmpty)
    visitors.foreach { visitor =>
      val java = JavaJvmVisitorEmitter.emit(visitor)
      assert(java.contains("static CefSession.HandlerRegistration route("))
      assert(java.contains("return session.on("))
    }
  }
}
