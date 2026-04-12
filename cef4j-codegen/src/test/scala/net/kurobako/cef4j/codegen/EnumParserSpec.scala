package net.kurobako.cef4j.codegen

class EnumParserSpec extends munit.FunSuite {

  private def findValue(enum_ : CefDecl.Enum, name: String): (Long, String) =
    enum_.values.find(_._1 == name).map(v => (v._2, v._3)).getOrElse(fail(s"$name not found"))

  test("parses all enum values with correct integers") {
    val stub  = """typedef enum {
      RT_MAIN_FRAME = 0,
      RT_SUB_FRAME = 1,
      RT_NAVIGATION_PRELOAD_MAIN_FRAME = 19,
      RT_NAVIGATION_PRELOAD_SUB_FRAME = 20,
    } cef_resource_type_t;"""
    val decls = CHeaderParser.parse(stub, Set.empty)
    val enum_ = decls.head.asInstanceOf[CefDecl.Enum]
    assertEquals(findValue(enum_, "RT_NAVIGATION_PRELOAD_MAIN_FRAME")._1, 19L)
    assertEquals(findValue(enum_, "RT_NAVIGATION_PRELOAD_SUB_FRAME")._1, 20L)
  }

  test("parses hex enum values") {
    val stub  = """typedef enum {
      EVENTFLAG_SHIFT_DOWN = 0x02,
      EVENTFLAG_CONTROL_DOWN = 0x04,
      EVENTFLAG_ALT_DOWN = 0x08,
    } cef_event_flags_t;"""
    val decls = CHeaderParser.parse(stub, Set.empty)
    val enum_ = decls.head.asInstanceOf[CefDecl.Enum]
    assertEquals(findValue(enum_, "EVENTFLAG_SHIFT_DOWN")._1, 0x02L)
    assertEquals(findValue(enum_, "EVENTFLAG_CONTROL_DOWN")._1, 0x04L)
    assertEquals(findValue(enum_, "EVENTFLAG_ALT_DOWN")._1, 0x08L)
  }

  test("parses sequential enum values without explicit assignment") {
    val stub  = """typedef enum {
      STATE_DEFAULT = 0,
      STATE_ENABLED,
      STATE_DISABLED,
    } cef_state_t;"""
    val decls = CHeaderParser.parse(stub, Set.empty)
    val enum_ = decls.head.asInstanceOf[CefDecl.Enum]
    assertEquals(findValue(enum_, "STATE_DEFAULT")._1, 0L)
    assertEquals(findValue(enum_, "STATE_ENABLED")._1, 1L)
    assertEquals(findValue(enum_, "STATE_DISABLED")._1, 2L)
  }

  test("parses bit-shift enum expressions and preserves raw expr") {
    val stub                    = """typedef enum {
      TT_LINK = 1,
      TT_SERVER_REDIRECT_QUALIFIER = 1 << 24,
      TT_CLIENT_REDIRECT_QUALIFIER = 1 << 25,
    } cef_transition_type_t;"""
    val decls                   = CHeaderParser.parse(stub, Set.empty)
    val enum_                   = decls.head.asInstanceOf[CefDecl.Enum]
    val (serverVal, serverExpr) = findValue(enum_, "TT_SERVER_REDIRECT_QUALIFIER")
    assertEquals(serverVal, 1L << 24)
    assertEquals(serverExpr, "1 << 24")
    val (clientVal, clientExpr) = findValue(enum_, "TT_CLIENT_REDIRECT_QUALIFIER")
    assertEquals(clientVal, 1L << 25)
    assertEquals(clientExpr, "1 << 25")
  }

  test("parses OR-combined enum expressions") {
    val stub            = """typedef enum {
      FLAG_A = 0x01,
      FLAG_B = 0x02,
      FLAG_AB = FLAG_A | FLAG_B,
    } cef_flags_t;"""
    val decls           = CHeaderParser.parse(stub, Set.empty)
    val enum_           = decls.head.asInstanceOf[CefDecl.Enum]
    val (abVal, abExpr) = findValue(enum_, "FLAG_AB")
    assertEquals(abVal, 0x03L)
    assertEquals(abExpr, "FLAG_A | FLAG_B")
  }

  test("enum name extracted from closing line") {
    val stub  = """typedef enum {
      RV_CANCEL = 0,
      RV_CONTINUE = 1,
      RV_CONTINUE_ASYNC = 2,
    } cef_return_value_t;"""
    val decls = CHeaderParser.parse(stub, Set.empty)
    val enum_ = decls.head.asInstanceOf[CefDecl.Enum]
    assertEquals(enum_.name, "cef_return_value_t")
  }

  test("parses enum value when expression is split across lines") {
    val stub                  = """typedef enum {
      DRAG_OPERATION_DELETE = 32,
      DRAG_OPERATION_EVERY =
      (0x7fffffff * 2U + 1U)
    } cef_drag_operations_mask_t;"""
    val decls                 = CHeaderParser.parse(stub, Set.empty)
    val enum_                 = decls.head.asInstanceOf[CefDecl.Enum]
    val (everyVal, everyExpr) = findValue(enum_, "DRAG_OPERATION_EVERY")
    assertEquals(everyVal, 4294967295L)
    assertEquals(everyExpr, "(0x7fffffff * 2U + 1U)")
  }
}
