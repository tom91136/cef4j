package net.kurobako.cef4j.codegen

class CHeaderParserSpec extends munit.FunSuite {

  val browserStub: String = """
    typedef struct _cef_browser_t {
      cef_base_ref_counted_t base;
      int (CEF_CALLBACK* is_valid)(struct _cef_browser_t* self);
      void (CEF_CALLBACK* go_back)(struct _cef_browser_t* self);
      cef_string_userfree_t (CEF_CALLBACK* get_url)(
          struct _cef_browser_t* self);
    } cef_browser_t;
  """

  test("parses object struct function pointers") {
    val decls   = CHeaderParser.parse(browserStub, handlerNames = Set.empty)
    val browser = decls.collectFirst { case s: CefDecl.ObjectStruct => s }.get
    assertEquals(browser.name, "cef_browser_t")
    assertEquals(browser.fns.map(_.name), List("is_valid", "go_back", "get_url"))
  }

  test("strips self from parameter list") {
    val decls   = CHeaderParser.parse(browserStub, Set.empty)
    val isValid = decls.head match {
      case s: CefDecl.ObjectStruct => s.fns.head
      case other                   => fail(s"Expected ObjectStruct, got $other")
    }
    assertEquals(isValid.params, Nil)
  }

  test("maps cef_string_userfree_t to CType.JString") {
    val decls  = CHeaderParser.parse(browserStub, Set.empty)
    val getUrl = decls.head match {
      case s: CefDecl.ObjectStruct => s.fns.last
      case other                   => fail(s"Expected ObjectStruct, got $other")
    }
    assertEquals(getUrl.ret, CType.JString)
  }

  test("classifies handler structs from source annotation set") {
    val stub  = """typedef struct _cef_render_handler_t {
      cef_base_ref_counted_t base;
      void (CEF_CALLBACK* on_paint)(struct _cef_render_handler_t* self,
          struct _cef_browser_t* browser,
          cef_paint_element_type_t type,
          size_t dirtyRectsCount, const cef_rect_t* dirtyRects,
          const void* buffer, int width, int height);
    } cef_render_handler_t;"""
    val decls = CHeaderParser.parse(stub, handlerNames = Set("cef_render_handler_t"))
    assert(decls.head.isInstanceOf[CefDecl.HandlerStruct])
  }

  test("marks on_paint as SpecialFn.OnPaint") {
    val stub    = """typedef struct _cef_render_handler_t {
      cef_base_ref_counted_t base;
      void (CEF_CALLBACK* on_paint)(struct _cef_render_handler_t* self,
          struct _cef_browser_t* browser,
          cef_paint_element_type_t type,
          size_t dirtyRectsCount, const cef_rect_t* dirtyRects,
          const void* buffer, int width, int height);
    } cef_render_handler_t;"""
    val decls   = CHeaderParser.parse(stub, handlerNames = Set("cef_render_handler_t"))
    val handler = decls.head match {
      case h: CefDecl.HandlerStruct => h
      case other                    => fail(s"Expected HandlerStruct, got $other")
    }
    assertEquals(handler.fns.head.isSpecial, Some(SpecialFn.OnPaint))
  }

  test("parses data struct without base as DataStruct") {
    val stub  = """typedef struct _cef_rect_t {
      int x;
      int y;
      int width;
      int height;
    } cef_rect_t;"""
    val decls = CHeaderParser.parse(stub, Set.empty)
    val ds    = decls.head match {
      case d: CefDecl.DataStruct => d
      case other                 => fail(s"Expected DataStruct, got $other")
    }
    assertEquals(ds.name, "cef_rect_t")
    assertEquals(ds.fields.map(_.name), List("x", "y", "width", "height"))
  }

  test("recognises transitive base field as ref-counted (e.g. cef_preference_manager_t base)") {
    val stub  = """typedef struct _cef_request_context_t {
      cef_preference_manager_t base;
      int (CEF_CALLBACK* is_same)(struct _cef_request_context_t* self,
          struct _cef_request_context_t* other);
    } cef_request_context_t;"""
    val decls = CHeaderParser.parse(stub, Set.empty)
    val obj   = decls.head match {
      case o: CefDecl.ObjectStruct => o
      case other => fail(s"Expected ObjectStruct for transitive base, got ${other.getClass.getSimpleName}")
    }
    assertEquals(obj.name, "cef_request_context_t")
    assertEquals(obj.fns.map(_.name), List("is_same"))
  }

  test("parses function pointer returning a struct pointer (preprocessed form)") {
    val stub  = """typedef struct _cef_render_handler_t {
      cef_base_ref_counted_t base;
      struct _cef_accessibility_handler_t*(* get_accessibility_handler)(
          struct _cef_render_handler_t* self);
      void(* on_popup_show)(struct _cef_render_handler_t* self,
          struct _cef_browser_t* browser, int show);
    } cef_render_handler_t;"""
    val decls = CHeaderParser.parse(stub, handlerNames = Set("cef_render_handler_t"))
    val fns   = decls.head match {
      case h: CefDecl.HandlerStruct => h.fns
      case other                    => fail(s"Expected HandlerStruct, got $other")
    }
    assertEquals(fns.map(_.name), List("get_accessibility_handler", "on_popup_show"))
    assertEquals(fns.head.ret, CType.Ptr("cef_accessibility_handler_t"))
  }

  test("parses function pointer with multiple non-self params") {
    val stub  = """typedef struct _cef_browser_host_t {
      cef_base_ref_counted_t base;
      void (CEF_CALLBACK* send_key_event)(struct _cef_browser_host_t* self,
          const cef_string_t* key, int modifiers);
    } cef_browser_host_t;"""
    val decls = CHeaderParser.parse(stub, Set.empty)
    val fn    = decls.head match {
      case s: CefDecl.ObjectStruct => s.fns.head
      case other                   => fail(s"Expected ObjectStruct, got $other")
    }
    assertEquals(fn.name, "send_key_event")
    assertEquals(fn.params.length, 2)
    assertEquals(fn.params.head.typ, CType.JString)
    assertEquals(fn.params(1).typ, CType.Int)
  }
}
