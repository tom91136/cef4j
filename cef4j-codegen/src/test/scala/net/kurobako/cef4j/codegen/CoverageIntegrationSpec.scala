package net.kurobako.cef4j.codegen

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import scala.concurrent.duration.*
import scala.jdk.StreamConverters._

class CoverageIntegrationSpec extends munit.FunSuite {

  override val munitTimeout = 2.minutes

  private val cefRoot: Path = {
    val prop = System.getProperty("cef.root")
    assume(prop != null, "cef.root system property not set - run via `mvn verify`")
    val path = Paths.get(prop)
    assume(Files.isDirectory(path), s"cef.root does not exist: $path")
    path
  }

  private val cefInclude: Path = cefRoot.resolve("include")

  // CEF headers use `#include "include/capi/..."` so the -I path must be the CEF root
  private val includeDirs: List[Path] = List(cefRoot)

  private val compilerId: String =
    Option(System.getProperty("cef.compiler")).getOrElse("gcc")

  private lazy val capiHeaders: List[Path] = {
    val capiDir = cefInclude.resolve("capi")
    assume(Files.isDirectory(capiDir), s"capi directory does not exist: $capiDir")
    List(capiDir, capiDir.resolve("views")).filter(Files.isDirectory(_))
      .flatMap(dir =>
        Files.list(dir).toScala(List).filter(p => Files.isRegularFile(p) && p.toString.endsWith("_capi.h"))
      )
      .sorted
  }

  private lazy val typesHeader: Path = {
    val p = cefInclude.resolve("internal/cef_types.h")
    assume(Files.isRegularFile(p), s"cef_types.h not found: $p")
    p
  }

  private lazy val handlerNames: Set[String] =
    CHeaderParser.parseHandlerAnnotations(cefInclude)

  private lazy val allDecls: List[CefDecl] = {
    val preprocessed = (capiHeaders :+ typesHeader).map { h =>
      h -> Preprocessor.preprocess(h, includeDirs, compilerId)
    }
    preprocessed.flatMap { case (_, src) =>
      CHeaderParser.parse(src, handlerNames)
    }
  }

  test("preprocessor succeeds on all capi headers") {
    capiHeaders.foreach { h =>
      val result = Preprocessor.preprocess(h, includeDirs, compilerId)
      assert(result.nonEmpty, s"Empty preprocessor output for $h")
    }
  }

  test("preprocessor succeeds on cef_types.h") {
    val result = Preprocessor.preprocess(typesHeader, includeDirs, compilerId)
    assert(result.nonEmpty, "Empty preprocessor output for cef_types.h")
  }

  test("parser produces non-empty declarations from real headers") {
    assert(allDecls.nonEmpty, "No declarations parsed from real CEF headers")
  }

  test("parser finds object structs") {
    val objects = allDecls.collect { case d: CefDecl.ObjectStruct => d }
    assert(objects.nonEmpty, "No ObjectStruct declarations found")
  }

  test("parser finds handler structs") {
    val handlers = allDecls.collect { case d: CefDecl.HandlerStruct => d }
    assert(handlers.nonEmpty, "No HandlerStruct declarations found")
  }

  test("parser finds enums") {
    val enums = allDecls.collect { case d: CefDecl.Enum => d }
    assert(enums.nonEmpty, "No Enum declarations found")
  }

  test("parser finds data structs") {
    val data = allDecls.collect { case d: CefDecl.DataStruct => d }
    assert(data.nonEmpty, "No DataStruct declarations found")
  }

  test("cef_browser_t is parsed as ObjectStruct with known methods") {
    val browser = allDecls.collectFirst { case d: CefDecl.ObjectStruct if d.name == "cef_browser_t" => d }
    assert(browser.isDefined, "cef_browser_t not found")
    val fns = browser.get.fns.map(_.name)
    assert(fns.contains("is_valid"), s"is_valid not found in cef_browser_t; got: $fns")
    assert(fns.contains("go_back"), s"go_back not found in cef_browser_t; got: $fns")
    assert(fns.contains("go_forward"), s"go_forward not found in cef_browser_t; got: $fns")
    assert(fns.contains("get_host"), s"get_host not found in cef_browser_t; got: $fns")
  }

  test("cef_browser_host_t is parsed as ObjectStruct") {
    val host = allDecls.collectFirst { case d: CefDecl.ObjectStruct if d.name == "cef_browser_host_t" => d }
    assert(host.isDefined, "cef_browser_host_t not found")
    assert(host.get.fns.nonEmpty, "cef_browser_host_t has no function pointers")
  }

  test("handler annotation parsing finds known handlers") {
    assert(handlerNames.contains("cef_render_handler_t"), s"cef_render_handler_t not in handler set: $handlerNames")
    assert(handlerNames.contains("cef_load_handler_t"), s"cef_load_handler_t not in handler set: $handlerNames")
    assert(handlerNames.contains("cef_display_handler_t"), s"cef_display_handler_t not in handler set: $handlerNames")
    assert(
      handlerNames.contains("cef_life_span_handler_t"),
      s"cef_life_span_handler_t not in handler set: $handlerNames"
    )
  }

  test("render handler has on_paint marked as SpecialFn.OnPaint") {
    val renderHandler = allDecls.collectFirst {
      case d: CefDecl.HandlerStruct if d.name == "cef_render_handler_t" => d
    }
    assert(renderHandler.isDefined, "cef_render_handler_t not found")
    val onPaint = renderHandler.get.fns.find(_.name == "on_paint")
    assert(onPaint.isDefined, "on_paint not found in render handler")
    assertEquals(onPaint.get.isSpecial, Some(SpecialFn.OnPaint))
  }

  test("all object struct function pointers have self stripped") {
    val objects = allDecls.collect { case d: CefDecl.ObjectStruct => d }
    objects.foreach { obj =>
      obj.fns.foreach { fn =>
        val selfParam = fn.params.find(p => p.name == "self" || p.name.contains("self"))
        assert(
          selfParam.isEmpty,
          s"${obj.name}.${fn.name} still has self param: ${fn.params.map(p => s"${p.name}: ${p.typ}")}"
        )
      }
    }
  }

  test("enums from cef_types.h include known values") {
    val enums     = allDecls.collect { case d: CefDecl.Enum => d }
    val enumNames = enums.map(_.name).toSet

    val expected = Set(
      "cef_log_severity_t",
      "cef_state_t",
      "cef_return_value_t",
      "cef_paint_element_type_t"
    )
    expected.foreach { name =>
      assert(enumNames.contains(name), s"Expected enum $name not found; got: ${enumNames.toList.sorted.mkString(", ")}")
    }
  }

  test("every capi header produces at least one declaration") {
    capiHeaders.foreach { header =>
      val src   = Preprocessor.preprocess(header, includeDirs, compilerId)
      val decls = CHeaderParser.parse(src, handlerNames)
      assert(
        decls.nonEmpty,
        s"No declarations parsed from ${header.getFileName}"
      )
    }
  }

  test("no function pointer has CType.Void for all params (degenerate parse)") {
    val allFns = allDecls.flatMap {
      case d: CefDecl.ObjectStruct  => d.fns
      case d: CefDecl.HandlerStruct => d.fns
      case _                        => Nil
    }
    val suspicious = allFns.filter { fn =>
      fn.params.nonEmpty && fn.params.forall(_.typ == CType.Void)
    }
    assert(
      suspicious.isEmpty,
      s"Functions with all-Void params (likely parse failure): ${suspicious.map(_.name).mkString(", ")}"
    )
  }

  test("doc comments extracted from C++ headers") {
    val docs = CHeaderParser.parseDocComments(cefInclude)
    assert(docs.nonEmpty, "No doc comments extracted from C++ headers")
  }

  test("summary: declaration counts") {
    val objects  = allDecls.collect { case d: CefDecl.ObjectStruct => d }
    val handlers = allDecls.collect { case d: CefDecl.HandlerStruct => d }
    val data     = allDecls.collect { case d: CefDecl.DataStruct => d }
    val enums    = allDecls.collect { case d: CefDecl.Enum => d }
    val totalFns = (objects.flatMap(_.fns) ++ handlers.flatMap(_.fns)).size

    println(s"""
      |=== CEF Codegen Coverage Summary ===
      |  Object structs:  ${objects.size}
      |  Handler structs: ${handlers.size}
      |  Data structs:    ${data.size}
      |  Enums:           ${enums.size}
      |  Total FnPtrs:    $totalFns
      |  Handler names:   ${handlerNames.size}
      |====================================""".stripMargin)
  }
}
