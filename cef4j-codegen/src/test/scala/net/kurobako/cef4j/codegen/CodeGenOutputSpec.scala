package net.kurobako.cef4j.codegen

class CodeGenOutputSpec extends munit.FunSuite {

  test("Java native codegen produces correct JNI symbol mangling") {
    val decl: CefDecl.ObjectStruct = CefDecl.ObjectStruct(
      "cef_browser_t",
      List(FnPtr("go_back", CType.Void, Nil))
    )
    val cpp = JniCppCodeGen.emitToString(decl)
    assert(
      cpp.contains("Java_net_kurobako_cef4j_gen_CefBrowser_00024NativePeer_N_1GoBack"),
      s"Expected JNI symbol not found in:\n$cpp"
    )
  }

  test("object codegen produces null-guard on self before function pointer call") {
    val decl: CefDecl.ObjectStruct = CefDecl.ObjectStruct(
      "cef_browser_t",
      List(FnPtr("go_back", CType.Void, Nil))
    )
    val cpp = JniCppCodeGen.emitToString(decl)
    assert(
      cpp.contains("if (!s)") || cpp.contains("if (s == nullptr)"),
      s"Expected null guard not found in:\n$cpp"
    )
  }

  test("handler codegen initialises ref-counting via InitRefCount") {
    val decl: CefDecl.HandlerStruct = CefDecl.HandlerStruct("cef_render_handler_t", Nil)
    val cpp                         = JniCppCodeGen.emitHandlerToString(decl)
    assert(
      cpp.contains("InitRefCount<JniCefRenderHandler, cef_render_handler_t>(&base)"),
      s"Expected InitRefCount call not found in:\n$cpp"
    )
    assert(
      cpp.contains("std::atomic<int> refCount{1}"),
      s"Expected refCount member not found in:\n$cpp"
    )
  }

  test("Java interface codegen generates camelCase methods") {
    assertEquals(Naming.structToJavaName("cef_browser_t"), "CefBrowser")
    assertEquals(Naming.toCamelCase("is_valid"), "isValid")
    assertEquals(Naming.toCamelCase("go_back"), "goBack")
    assertEquals(Naming.toCamelCase("get_url"), "getUrl")
  }

  test("Java NativePeer class delegates to native methods with self pointer") {
    val decl: CefDecl.ObjectStruct = CefDecl.ObjectStruct(
      "cef_browser_t",
      List(FnPtr("is_valid", CType.Bool, Nil))
    )
    val java = JavaNativeCodeGen.renderInnerClass(decl)
    assert(java.contains("static class NativePeer"), s"Missing NativePeer class in:\n$java")
    assert(java.contains("N_IsValid(nativePtr)"), s"Missing delegation in:\n$java")
    assert(java.contains("private native boolean N_IsValid(long self)"), s"Missing native decl in:\n$java")
  }

  test("Java enum codegen produces fromLong lookup") {
    val decl: CefDecl.Enum = CefDecl.Enum(
      "cef_resource_type_t",
      List("RT_MAIN_FRAME" -> 0L, "RT_SUB_FRAME" -> 1L)
    )
    val java = JavaEnumCodeGen.emitToString(decl)
    assert(java.contains("RT_MAIN_FRAME(0L)"), s"Missing enum constant in:\n$java")
    assert(java.contains("fromLong"), s"Missing fromLong in:\n$java")
    assert(java.contains("UNKNOWN(-1L)"), s"Missing UNKNOWN sentinel in:\n$java")
  }

  test("JNI symbol for multi-word function name is correct") {
    val sym = Naming.jniSymbol("cef_browser_host_t", "send_mouse_click_event")
    assertEquals(sym, "Java_net_kurobako_cef4j_gen_CefBrowserHost_00024NativePeer_N_1SendMouseClickEvent")
  }

  test("handler trampoline marshals args and calls Java method") {
    val decl: CefDecl.HandlerStruct = CefDecl.HandlerStruct(
      "cef_display_handler_t",
      List(
        FnPtr(
          "on_title_change",
          CType.Void,
          List(
            Param("browser", CType.Ptr("_cef_browser_t")),
            Param("title", CType.JString)
          )
        )
      )
    )
    val cpp = JniCppCodeGen.emitHandlerToString(decl)
    // Should call GetMethodID with correct name and signature
    assert(cpp.contains("\"onTitleChange\""), s"Missing method name in:\n$cpp")
    assert(cpp.contains("\"(JLjava/lang/String;)V\""), s"Missing JNI sig in:\n$cpp")
    // Should convert string param
    assert(cpp.contains("CefStringToJString(env, title)"), s"Missing string conversion in:\n$cpp")
    // Should call Java void method
    assert(cpp.contains("CallVoidMethod"), s"Missing CallVoidMethod in:\n$cpp")
    // Should use PushLocalFrame/PopLocalFrame
    assert(cpp.contains("PushLocalFrame"), s"Missing PushLocalFrame in:\n$cpp")
    assert(cpp.contains("PopLocalFrame"), s"Missing PopLocalFrame in:\n$cpp")
  }

  test("handler trampoline returns boolean correctly") {
    val decl: CefDecl.HandlerStruct = CefDecl.HandlerStruct(
      "cef_life_span_handler_t",
      List(
        FnPtr(
          "do_close",
          CType.Bool,
          List(
            Param("browser", CType.Ptr("_cef_browser_t"))
          )
        )
      )
    )
    val cpp = JniCppCodeGen.emitHandlerToString(decl)
    assert(cpp.contains("CallBooleanMethod"), s"Missing CallBooleanMethod in:\n$cpp")
    assert(cpp.contains("jResult == JNI_TRUE ? 1 : 0"), s"Missing bool return conversion in:\n$cpp")
    assert(cpp.contains("\"(J)Z\""), s"Missing JNI sig in:\n$cpp")
  }

  test("handler trampoline handles out-params with pre/post conversion") {
    val decl: CefDecl.HandlerStruct = CefDecl.HandlerStruct(
      "cef_render_handler_t",
      List(
        FnPtr(
          "get_screen_point",
          CType.Bool,
          List(
            Param("browser", CType.Ptr("_cef_browser_t")),
            Param("viewX", CType.Int),
            Param("viewY", CType.Int),
            Param("screenX", CType.OutInt),
            Param("screenY", CType.OutInt)
          )
        )
      )
    )
    val cpp = JniCppCodeGen.emitHandlerToString(decl)
    // Pre-call: create arrays and set initial values
    assert(cpp.contains("NewIntArray(1)"), s"Missing NewIntArray in:\n$cpp")
    assert(cpp.contains("SetIntArrayRegion"), s"Missing SetIntArrayRegion in:\n$cpp")
    // Post-call: read back values
    assert(cpp.contains("GetIntArrayRegion"), s"Missing GetIntArrayRegion in:\n$cpp")
  }

  test("handler emits C-linkage factory function") {
    val decl: CefDecl.HandlerStruct = CefDecl.HandlerStruct("cef_client_t", Nil)
    val cpp                         = JniCppCodeGen.emitHandlerToString(decl)
    assert(cpp.contains("Create_JniCefClient"), s"Missing C-linkage factory in:\n$cpp")
    assert(cpp.contains("cef_client_t* Create_JniCefClient"), s"Missing return type in:\n$cpp")
  }
}
