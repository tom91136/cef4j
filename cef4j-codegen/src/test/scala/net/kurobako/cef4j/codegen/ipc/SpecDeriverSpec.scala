package net.kurobako.cef4j.codegen.ipc

import net.kurobako.cef4j.codegen.CType
import net.kurobako.cef4j.codegen.CefDecl
import net.kurobako.cef4j.codegen.Field
import net.kurobako.cef4j.codegen.FnPtr
import net.kurobako.cef4j.codegen.Naming
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

  test("handler defaults answer through the shared unhandled constants") {
    val decl   = handler("cef_key_handler_t", FnPtr("on_key_event", CType.Int, List(Param("key", CType.Int))))
    val source = JavaHandlerEmitter.emit(SpecDeriver.deriveHandlers(List(decl), "test.gen").head)

    assert(
      source.contains("default java.util.Optional<Boolean> onKeyEvent(int key) { return Unhandled.bool(); }"),
      source
    )
    assert(source.contains("import net.kurobako.cef4j.ipc.session.Unhandled;"))
    assert(!source.contains(".empty()"))
  }

  test("handler intercepts reply under the id of their response message") {
    val decl     = handler("cef_key_handler_t", FnPtr("on_key_event", CType.Int, List(Param("key", CType.Int))))
    val messages = SpecDeriver.derive(List(decl), "test.gen")
    val refs     = SharedMessages.derive(messages).refs
    val source   = JavaHandlerEmitter.emit(SpecDeriver.deriveHandlers(List(decl), "test.gen").head, refs)

    assert(source.contains("new CefSession.Reply(MessageIds.KEY_HANDLER_ON_KEY_EVENT_RESPONSE, "), source)
    assert(source.contains("session.intercept(MessageIds.KEY_HANDLER_ON_KEY_EVENT_EVENT, "), source)
  }

  test("remote names use the shared CEF acronym dictionary") {
    given Naming.Context = Naming.Context(
      cppClassNames = Map.empty,
      compoundSegments = Map("urlrequest" -> List("Url", "Request")),
      javaPackage = "test.gen"
    )
    val method = FnPtr("get_url", CType.JString, Nil)
    val decl   = CefDecl.ObjectStruct("cef_urlrequest_t", List(method))

    val messages = SpecDeriver.derive(List(decl), "test.gen")
    val facade   = SpecDeriver.deriveFacades(List(decl), "test.gen").head

    assertEquals(messages.map(_.className), List("UrlRequestGetUrlRequest", "UrlRequestGetUrlResponse"))
    assertEquals(facade.className, "UrlRequest")
    assertEquals(facade.methods.head.methodName, "getUrl")
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

  test("facade and handler address derived messages by their shared shape or their own name") {
    val facadeDecl = CefDecl.ObjectStruct(
      "cef_browser_t",
      List(
        FnPtr("is_valid", CType.Bool, Nil),
        FnPtr("is_loading", CType.Bool, Nil),
        FnPtr("can_go_back", CType.Int, Nil)
      )
    )
    val handlerDecl = handler("cef_load_handler_t", fn("on_load_end", "browser_id" -> CType.Int))
    val messages    = SpecDeriver.derive(List(facadeDecl, handlerDecl), "test.gen")
    val refs        = SharedMessages.derive(messages).refs

    val facade = JavaFacadeEmitter.emit(SpecDeriver.deriveFacades(List(facadeDecl), "test.gen").head, refs = refs)
    assert(
      facade.contains(
        "session.request(MessageIds.BROWSER_IS_VALID_REQUEST, new SelfHandleMessage(handle), ResultBoolMessage.DECODER)"
      ),
      facade
    )
    assert(facade.contains("ResultBoolMessage::result"), facade)
    assert(facade.contains("BrowserCanGoBackResponse.DECODER"), facade)

    val events = JavaHandlerEmitter.emit(SpecDeriver.deriveHandlers(List(handlerDecl), "test.gen").head, refs)
    assert(events.contains("session.on(MessageIds.LOAD_HANDLER_ON_LOAD_END_EVENT, "), events)
    assert(events.contains("LoadHandlerOnLoadEndEvent.DECODER"), events)
    assert(!events.contains("decoder("), events)
  }

  test("facade inherits its lifetime from the shared browser base") {
    val method = FnPtr("is_valid", CType.Bool, Nil)
    val decl   = CefDecl.ObjectStruct("cef_browser_t", List(method))

    val source = JavaFacadeEmitter.emit(SpecDeriver.deriveFacades(List(decl), "test.gen").head)

    assert(source.contains("public final class Browser extends BrowserFacade"))
    assert(source.contains("super(session, handle, \"cef_browser_t\");"))
    for (member <- List("closeLock", "closeFuture", "closeAsync", "releaseHandle", "public void close()", "handle()"))
      assert(!source.contains(member), s"Unexpected $member in:\n$source")
  }

  test("renderer facade inherits its lifetime from the shared renderer base") {
    val source = JavaFacadeEmitter.emit(
      FacadeSpec("RendererThing", "test.gen", "cef_renderer_object_t", Nil, ProcessAffinity.Renderer)
    )

    assert(source.contains("public final class RendererThing extends RendererFacade"))
    assert(source.contains("super(session, frame, handle, \"cef_renderer_object_t\");"))
    assert(!source.contains("closeLock"))
    assert(!source.contains("frame()"))
  }

  test("browser facade base releases its handle through the browser release message") {
    val refs   = SharedMessages.derive(Specs.all("test.gen")).refs
    val source = JavaFacadeEmitter.emitBase(ProcessAffinity.Browser, "test.gen", refs)

    assert(source.contains("package test.gen;"))
    assert(source.contains("public abstract class BrowserFacade extends RemoteFacade"))
    assert(
      source.contains(
        s"session.request(${refs.idExpr("ReleaseHandleRequest")}, " +
          s"${refs.newExpr("ReleaseHandleRequest", "handle, cefStructName")}, " +
          s"${refs.decoderExpr("ReleaseHandleResponse")})"
      ),
      source
    )
    assertEquals(refs.idExpr("ReleaseHandleRequest"), "MessageIds.RELEASE_HANDLE_REQUEST")
    assert(!source.contains("frame"))
  }

  test("renderer facade base releases its frame-bound handle") {
    val refs   = SharedMessages.derive(Specs.all("test.gen")).refs
    val source = JavaFacadeEmitter.emitBase(ProcessAffinity.Renderer, "test.gen", refs)

    assert(source.contains("public abstract class RendererFacade extends RemoteFacade"))
    assert(
      source.contains(
        s"session.request(${refs.idExpr("RendererReleaseHandleRequest")}, " +
          s"${refs.newExpr("RendererReleaseHandleRequest", "frame, handle, cefStructName")}, " +
          s"${refs.decoderExpr("RendererReleaseHandleResponse")})"
      ),
      source
    )
    assert(source.contains("public final RemoteHandle frame()"))
    assert(source.contains("protected final RemoteHandle frame;"))
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

  test("wire structs link a gen type unless platform-specific") {
    given Naming.Context = Naming.Context.empty.copy(typesPackage = "t.gen")
    val point            = CefDecl.DataStruct("cef_point_t", List(Field("x", CType.Int), Field("y", CType.Int)))
    val plane            = CefDecl.DataStruct("cef_plane_t", List(Field("fd", CType.Int)))
    val specs            = SpecDeriver.deriveDataStructs(List(point, plane), "p", Set("cef_plane_t"))
    assertEquals(
      specs.map(s => s.cefStructName -> s.shared.map(_.javaType)),
      List("cef_point_t" -> Some("t.gen.CefPoint"), "cef_plane_t" -> None)
    )
  }
}
