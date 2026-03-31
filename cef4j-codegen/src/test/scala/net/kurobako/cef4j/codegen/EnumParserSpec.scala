package net.kurobako.cef4j.codegen

class EnumParserSpec extends munit.FunSuite {

  test("parses all enum values with correct integers") {
    val stub  = """typedef enum {
      RT_MAIN_FRAME = 0,
      RT_SUB_FRAME = 1,
      RT_NAVIGATION_PRELOAD_MAIN_FRAME = 19,
      RT_NAVIGATION_PRELOAD_SUB_FRAME = 20,
    } cef_resource_type_t;"""
    val decls = CHeaderParser.parse(stub, Set.empty)
    val enum_ = decls.head.asInstanceOf[CefDecl.Enum]
    assert(enum_.values.contains("RT_NAVIGATION_PRELOAD_MAIN_FRAME" -> 19L))
    assert(enum_.values.contains("RT_NAVIGATION_PRELOAD_SUB_FRAME" -> 20L))
  }

  test("parses hex enum values") {
    val stub  = """typedef enum {
      EVENTFLAG_SHIFT_DOWN = 0x02,
      EVENTFLAG_CONTROL_DOWN = 0x04,
      EVENTFLAG_ALT_DOWN = 0x08,
    } cef_event_flags_t;"""
    val decls = CHeaderParser.parse(stub, Set.empty)
    val enum_ = decls.head.asInstanceOf[CefDecl.Enum]
    assertEquals(enum_.values.find(_._1 == "EVENTFLAG_SHIFT_DOWN").get._2, 0x02L)
    assertEquals(enum_.values.find(_._1 == "EVENTFLAG_CONTROL_DOWN").get._2, 0x04L)
    assertEquals(enum_.values.find(_._1 == "EVENTFLAG_ALT_DOWN").get._2, 0x08L)
  }

  test("parses sequential enum values without explicit assignment") {
    val stub  = """typedef enum {
      STATE_DEFAULT = 0,
      STATE_ENABLED,
      STATE_DISABLED,
    } cef_state_t;"""
    val decls = CHeaderParser.parse(stub, Set.empty)
    val enum_ = decls.head.asInstanceOf[CefDecl.Enum]
    assertEquals(enum_.values.find(_._1 == "STATE_DEFAULT").get._2, 0L)
    assertEquals(enum_.values.find(_._1 == "STATE_ENABLED").get._2, 1L)
    assertEquals(enum_.values.find(_._1 == "STATE_DISABLED").get._2, 2L)
  }

  test("parses bit-shift enum expressions") {
    val stub  = """typedef enum {
      TT_LINK = 1,
      TT_SERVER_REDIRECT_QUALIFIER = 1 << 24,
      TT_CLIENT_REDIRECT_QUALIFIER = 1 << 25,
    } cef_transition_type_t;"""
    val decls = CHeaderParser.parse(stub, Set.empty)
    val enum_ = decls.head.asInstanceOf[CefDecl.Enum]
    assertEquals(enum_.values.find(_._1 == "TT_SERVER_REDIRECT_QUALIFIER").get._2, 1L << 24)
    assertEquals(enum_.values.find(_._1 == "TT_CLIENT_REDIRECT_QUALIFIER").get._2, 1L << 25)
  }

  test("parses OR-combined enum expressions") {
    val stub  = """typedef enum {
      FLAG_A = 0x01,
      FLAG_B = 0x02,
      FLAG_AB = FLAG_A | FLAG_B,
    } cef_flags_t;"""
    val decls = CHeaderParser.parse(stub, Set.empty)
    val enum_ = decls.head.asInstanceOf[CefDecl.Enum]
    assertEquals(enum_.values.find(_._1 == "FLAG_AB").get._2, 0x03L)
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
}
