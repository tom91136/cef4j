package net.kurobako.cef4j.codegen.ipc

import net.kurobako.cef4j.codegen.CType
import net.kurobako.cef4j.codegen.CefDecl
import net.kurobako.cef4j.codegen.FnPtr
import net.kurobako.cef4j.codegen.Param

class SpecDeriverSpec extends munit.FunSuite {

  private def handler(name: String, fns: FnPtr*): CefDecl.HandlerStruct =
    CefDecl.HandlerStruct(name, fns.toList)

  private def fn(name: String, params: (String, CType)*): FnPtr =
    FnPtr(name, CType.Void, params.toList.map((n, t) => Param(n, t)))

  test("HandlerStruct method with all-supported param types yields a spec") {
    val decl  = handler("cef_load_handler_t", fn("on_load_end", "browser_id" -> CType.Int, "url" -> CType.JString))
    val specs = SpecDeriver.derive(List(decl), "net.kurobako.cef4j.ipc.protocol.gen")

    assertEquals(specs.size, 1)
    val spec = specs.head
    assertEquals(spec.className, "LoadHandlerOnLoadEndEvent")
    assertEquals(spec.fields, List(FieldSpec("browserId", FieldType.I32), FieldSpec("url", FieldType.Utf8String)))
    assert(spec.messageId >= SpecDeriver.AstIdBase, s"id ${spec.messageId} below AST base")
  }

  test("ObjectPtr params now map to RemoteHandle, expanding eligibility") {
    val decl = handler(
      "cef_request_handler_t",
      fn("on_before_browse", "browser" -> CType.ObjectPtr("cef_browser_t"), "is_redirect" -> CType.Bool)
    )
    val specs = SpecDeriver.derive(List(decl), "test.gen")
    assertEquals(specs.size, 1)
    assertEquals(
      specs.head.fields,
      List(FieldSpec("browser", FieldType.RemoteHandle), FieldSpec("isRedirect", FieldType.Bool))
    )
  }

  test("method with a genuinely unsupported param type is skipped") {
    val decl = handler(
      "cef_imaginary_handler_t",
      fn("on_thing", "weight" -> CType.Float, "count" -> CType.Int)
    )
    val specs = SpecDeriver.derive(List(decl), "test.gen")
    assertEquals(specs, Nil)
  }

  test("BufferSize companion params are filtered before eligibility check") {
    val decl = handler(
      "cef_some_handler_t",
      fn("on_data", "len" -> CType.BufferSize("data"), "count" -> CType.Int)
    )
    val specs = SpecDeriver.derive(List(decl), "test.gen")
    assertEquals(specs.size, 1)
    assertEquals(specs.head.fields, List(FieldSpec("count", FieldType.I32)))
  }

  test("ObjectStruct method emits a Request + (empty void) Response pair sharing one messageId") {
    val voidFn = FnPtr("set_locale", CType.Void, List(Param("locale", CType.JString)))
    val decl   = CefDecl.ObjectStruct("cef_settings_t", List(voidFn))
    val specs  = SpecDeriver.derive(List(decl), "test.gen")

    assertEquals(specs.size, 2)
    val req = specs.find(_.className == "SettingsSetLocaleRequest").getOrElse(fail("no request"))
    val res = specs.find(_.className == "SettingsSetLocaleResponse").getOrElse(fail("no response"))
    assertEquals(req.messageId, res.messageId, "Request + Response must share messageId")
    assertEquals(
      req.fields,
      List(FieldSpec("self", FieldType.RemoteHandle), FieldSpec("locale", FieldType.Utf8String))
    )
    assertEquals(res.fields, Nil, "void return → empty Response")
  }

  test("ObjectStruct method with primitive return emits a typed Response result field") {
    val boolFn = FnPtr("can_go_back", CType.Bool, Nil)
    val decl   = CefDecl.ObjectStruct("cef_browser_t", List(boolFn))
    val specs  = SpecDeriver.derive(List(decl), "test.gen")

    val res = specs.find(_.className == "BrowserCanGoBackResponse").getOrElse(fail("no response"))
    assertEquals(res.fields, List(FieldSpec("result", FieldType.Bool)))
  }

  test("facade close operation is named cefClose") {
    val close = FnPtr("close", CType.Void, Nil)
    val decl  = CefDecl.ObjectStruct("cef_window_t", List(close))

    val facade = SpecDeriver.deriveFacades(List(decl), "test.gen").head

    assertEquals(facade.methods.head.methodName, "cefClose")
    assertEquals(facade.methods.head.cefMethodName, "close")
  }

  test("facade releases its handle through an idempotent AutoCloseable contract") {
    val method = FnPtr("is_valid", CType.Bool, Nil)
    val decl   = CefDecl.ObjectStruct("cef_browser_t", List(method))

    val source = JavaFacadeEmitter.emit(SpecDeriver.deriveFacades(List(decl), "test.gen").head)

    assert(source.contains("public final class Browser implements AutoCloseable"))
    assert(source.contains("public synchronized CompletableFuture<Void> closeAsync()"))
    assert(source.contains("if (closeFuture == null)"))
    assert(source.contains("public CompletableFuture<Void> releaseHandle()"))
    assert(source.contains("return closeAsync();"))
    assert(source.contains("public void close()"))
    assert(source.contains("@SuppressWarnings(\"FutureReturnValueIgnored\")"))
    assert(source.contains("closeAsync();"))
    assert(!source.contains("closeAsync().join();"))
  }

  test("ObjectStruct method with unsupported return type is skipped wholesale") {
    val floatFn = FnPtr("get_zoom", CType.Float, Nil)
    val decl    = CefDecl.ObjectStruct("cef_browser_t", List(floatFn))
    val specs   = SpecDeriver.derive(List(decl), "test.gen")
    assertEquals(specs, Nil, "unsupported return → drop the whole method")
  }

  test("HandlerStruct methods do not get a self field (events route by messageId)") {
    val decl  = handler("cef_load_handler_t", fn("on_load_end", "status" -> CType.Int))
    val specs = SpecDeriver.derive(List(decl), "test.gen")
    assertEquals(specs.size, 1)
    assertEquals(specs.head.fields, List(FieldSpec("status", FieldType.I32)))
  }

  test("ids are deterministic across runs") {
    val decl   = handler("cef_load_handler_t", fn("on_load_end", "status" -> CType.Int))
    val first  = SpecDeriver.derive(List(decl), "p").head.messageId
    val second = SpecDeriver.derive(List(decl), "p").head.messageId
    val byName = SpecDeriver.derive(List(handler("cef_other_t", fn("on_load_end", "status" -> CType.Int))), "p")
      .head
      .messageId
    assertEquals(first, second, "same input → same id")
    assertNotEquals(first, byName, "different class name → different id")
  }

  test("ObjectPtr params now map to RemoteHandle, expanding eligibility (Event flavour)") {
    val decl = handler(
      "cef_request_handler_t",
      fn("on_before_browse", "browser" -> CType.ObjectPtr("cef_browser_t"), "is_redirect" -> CType.Bool)
    )
    val specs = SpecDeriver.derive(List(decl), "test.gen")
    assertEquals(specs.size, 1)
    assertEquals(
      specs.head.fields,
      List(FieldSpec("browser", FieldType.RemoteHandle), FieldSpec("isRedirect", FieldType.Bool))
    )
  }

  test("non-handler/object decls are ignored") {
    val decls = List(
      CefDecl.Enum("cef_log_severity_t", List(("LOGSEVERITY_DEFAULT", 0L, "default"))),
      CefDecl.DataStruct("cef_rect_t", List.empty),
      CefDecl.FreeFunction("cef_initialize", CType.Bool, Nil, "", "initialize")
    )
    assertEquals(SpecDeriver.derive(decls, "p"), Nil)
  }
}
