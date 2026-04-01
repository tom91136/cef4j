package net.kurobako.cef4j.codegen

class CodeGenOutputSpec extends munit.FunSuite {

  private val codegen = new JniCppCodeGen(Map.empty)

  test("Java native codegen produces correct JNI symbol mangling") {
    val decl: CefDecl.ObjectStruct = CefDecl.ObjectStruct(
      "cef_browser_t",
      List(FnPtr("go_back", CType.Void, Nil))
    )
    val cpp = codegen.emitToString(decl)
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
    val cpp = codegen.emitToString(decl)
    assert(
      cpp.contains("if (!s)") || cpp.contains("if (s == nullptr)"),
      s"Expected null guard not found in:\n$cpp"
    )
  }

  test("handler codegen initialises ref-counting via InitRefCount") {
    val decl: CefDecl.HandlerStruct = CefDecl.HandlerStruct("cef_render_handler_t", Nil)
    val cpp                         = codegen.emitHandlerToString(decl)
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
    assert(java.contains("final class NativePeer"), s"Missing NativePeer class in:\n$java")
    assert(java.contains("N_IsValid(nativePtr)"), s"Missing delegation in:\n$java")
    assert(java.contains("private static native boolean N_IsValid(long self)"), s"Missing native decl in:\n$java")
  }

  test("Java enum codegen produces final class with prefix stripping and expressions") {
    val decl: CefDecl.Enum = CefDecl.Enum(
      "cef_resource_type_t",
      List(("RT_MAIN_FRAME", 0L, "0"), ("RT_SUB_FRAME", 1L, "1"), ("RT_FLAG", 8L, "1 << 3"))
    )
    val tmpDir = java.nio.file.Files.createTempDirectory("cef4j-test")
    JavaEnumCodeGen.emit(decl, tmpDir)
    val javaCode = java.nio.file.Files.readString(tmpDir.resolve("CefResourceType.java"))
    assert(
      javaCode.contains("public final class CefResourceType implements CefEnum<CefResourceType>"),
      s"Missing class decl in:\n$javaCode"
    )
    // Inner Kind enum holds the constants with stripped prefix
    assert(
      javaCode.contains("""MAIN_FRAME(0, "0", "RT_MAIN_FRAME")"""),
      s"Missing stripped constant in Kind enum:\n$javaCode"
    )
    assert(
      javaCode.contains("""FLAG(1 << 3, "1 << 3", "RT_FLAG")"""),
      s"Missing stripped shift expr in Kind enum:\n$javaCode"
    )
    assert(javaCode.contains("public enum Kind"), s"Missing Kind enum in:\n$javaCode")
    assert(javaCode.contains("public static CefResourceType of(long v)"), s"Missing of(long) in:\n$javaCode")
    assert(javaCode.contains("public static CefResourceType of(Kind k)"), s"Missing of(Kind) in:\n$javaCode")
    assert(!javaCode.contains("fromLong"), s"Unexpected fromLong in:\n$javaCode")
    assert(javaCode.contains("public final long value"), s"Missing value field in:\n$javaCode")
    assert(javaCode.contains("Long.hashCode(value)"), s"Missing hashCode in:\n$javaCode")
    assert(javaCode.contains("Possible values:"), s"Missing possible values list in:\n$javaCode")
  }

  test("doc comment joining removes hyphenated line-wrap artifacts") {
    // CEF headers wrap "command-\n/// line" which should become "command-line", not "command- line"
    val input =
      "Specify NULL or 0 to get the recommended\ndefault values. Many settings can be configured using command-\nline switches."
    val result = DocComments.convertCefDoc(input)
    assert(!result.contains("command- line"), s"Hyphenated wrap not joined in:\n$result")
    assert(result.contains("command-line"), s"Expected 'command-line' in:\n$result")
  }

  test("JNI symbol for multi-word function name is correct") {
    val fn  = FnPtr("send_mouse_click_event", CType.Void, Nil)
    val sym = Naming.jniSymbol("cef_browser_host_t", fn)
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
    val cpp = codegen.emitHandlerToString(decl)
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
    val cpp = codegen.emitHandlerToString(decl)
    assert(cpp.contains("CallBooleanMethod"), s"Missing CallBooleanMethod in:\n$cpp")
    assert(cpp.contains("return jResult;"), s"Missing direct bool return in:\n$cpp")
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
    val cpp = codegen.emitHandlerToString(decl)
    // Pre-call: create arrays and set initial values
    assert(cpp.contains("NewIntArray(1)"), s"Missing NewIntArray in:\n$cpp")
    assert(cpp.contains("SetIntArrayRegion"), s"Missing SetIntArrayRegion in:\n$cpp")
    // Post-call: read back values
    assert(cpp.contains("GetIntArrayRegion"), s"Missing GetIntArrayRegion in:\n$cpp")
  }

  test("handler emits C-linkage factory function") {
    val decl: CefDecl.HandlerStruct = CefDecl.HandlerStruct("cef_client_t", Nil)
    val cpp                         = codegen.emitHandlerToString(decl)
    assert(cpp.contains("Create_JniCefClient"), s"Missing C-linkage factory in:\n$cpp")
    assert(cpp.contains("cef_client_t* Create_JniCefClient"), s"Missing return type in:\n$cpp")
  }

  test("NativePeer emits @Nonnull on non-optional reference params") {
    val decl: CefDecl.ObjectStruct = CefDecl.ObjectStruct(
      "cef_jsdialog_callback_t",
      List(FnPtr("cont", CType.Void, List(Param("success", CType.Int), Param("user_input", CType.JString))))
    )
    val java = JavaNativeCodeGen.renderInnerClass(decl)
    assert(java.contains("@Nonnull String userInput"), s"Missing @Nonnull on String param in:\n$java")
  }

  test("NativePeer emits @Nullable on optional reference params") {
    val decl: CefDecl.ObjectStruct = CefDecl.ObjectStruct(
      "cef_test_t",
      List(
        FnPtr(
          "do_thing",
          CType.Void,
          List(Param("name", CType.JString)),
          metaAttrs = List("optional_param" -> "name")
        )
      )
    )
    val java = JavaNativeCodeGen.renderInnerClass(decl)
    assert(java.contains("@Nullable String name"), s"Missing @Nullable on optional String param in:\n$java")
  }

  test("handler ptr return unwraps Optional and calls factory") {
    val handlerCodegen              = new JniCppCodeGen(Map.empty, Set("cef_render_handler_t"))
    val decl: CefDecl.HandlerStruct = CefDecl.HandlerStruct(
      "cef_client_t",
      List(FnPtr("get_render_handler", CType.Ptr("_cef_render_handler_t"), Nil))
    )
    val cpp = handlerCodegen.emitHandlerToString(decl)
    // JNI sig should reference Optional, not J
    assert(cpp.contains("\"()Ljava/util/Optional;\""), s"Missing Optional JNI sig in:\n$cpp")
    // Should unwrap Optional via isPresent/get
    assert(cpp.contains("isPresent"), s"Missing isPresent in:\n$cpp")
    // Should call factory function
    assert(cpp.contains("Create_JniCefRenderHandler(env,"), s"Missing factory call in:\n$cpp")
    // Should forward-declare factory
    assert(
      cpp.contains("extern \"C\" cef_render_handler_t* Create_JniCefRenderHandler"),
      s"Missing factory forward declaration in:\n$cpp"
    )
    // Should NOT add_ref (factory creates with refCount=1)
    assert(!cpp.contains("add_ref"), s"Unexpected add_ref in:\n$cpp")
  }
}
