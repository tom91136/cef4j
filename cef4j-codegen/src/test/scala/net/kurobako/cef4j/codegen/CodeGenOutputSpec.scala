package net.kurobako.cef4j.codegen

class CodeGenOutputSpec extends munit.FunSuite {

  private given Banners = new Banners("test")

  private val codegen        = new JniCppCodeGen(Map.empty)
  private val byValueCodegen = new JniCppCodeGen(
    Map(
      "cef_size_t" -> CefDecl.DataStruct(
        "cef_size_t",
        List(
          Field("width", CType.Int),
          Field("height", CType.Int)
        )
      )
    )
  )

  test("Java native codegen produces correct JNI export macro") {
    val decl: CefDecl.ObjectStruct = CefDecl.ObjectStruct(
      "cef_browser_t",
      List(FnPtr("go_back", CType.Void, Nil))
    )
    val cpp = codegen.emitToString(decl)
    assert(
      cpp.contains("CEF4J_JNI_EXPORT(void, CEF4J_PEER(CefBrowser), goBack0)"),
      s"Expected CEF4J_JNI_EXPORT macro not found in:\n$cpp"
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
      cpp.contains(
        "InitRefCount<JniCefRenderHandler, cef_render_handler_t>(reinterpret_cast<cef_base_ref_counted_t*>(static_cast<cef_render_handler_t*>(this)))"
      ),
      s"Expected InitRefCount call not found in:\n$cpp"
    )
    assert(
      cpp.contains("std::atomic<int> refCount{1}"),
      s"Expected refCount member not found in:\n$cpp"
    )
  }

  test("generated C++ does not introduce extra blank lines at include-body boundaries") {
    val handlerDecl: CefDecl.HandlerStruct = CefDecl.HandlerStruct("cef_client_t", Nil)
    val objectDecl: CefDecl.ObjectStruct   =
      CefDecl.ObjectStruct("cef_browser_t", List(FnPtr("go_back", CType.Void, Nil)))

    val handlerCpp = codegen.emitHandlerToString(handlerDecl)
    val objectCpp  = codegen.emitToString(objectDecl)

    assert(
      !handlerCpp.contains("#include \"jni_util.h\"\n\n\n"),
      s"Unexpected extra blank line after native includes in:\n$handlerCpp"
    )
    assert(
      !handlerCpp.contains("InitRefCount<JniCefClient, cef_client_t>(&base);\n\n    }"),
      s"Unexpected blank line before handler constructor close in:\n$handlerCpp"
    )
    assert(
      !objectCpp.contains("#include \"jni_util.h\"\n\n\n"),
      s"Unexpected extra blank line after JNI includes in:\n$objectCpp"
    )
  }

  test("Java interface codegen generates camelCase methods") {
    assertEquals(Naming.structToJavaName("cef_browser_t"), "CefBrowser")
    assertEquals(Naming.toCamelCase("is_valid"), "isValid")
    assertEquals(Naming.toCamelCase("go_back"), "goBack")
    assertEquals(Naming.toCamelCase("get_url"), "getUrl")
  }

  test("Pascal word splitting preserves V8 as a compound segment") {
    assertEquals(Naming.splitPascalWords("CefV8BackingStore"), List("Cef", "V8", "Backing", "Store"))
  }

  test("Java NativePeer class delegates to native methods with self pointer") {
    val decl: CefDecl.ObjectStruct = CefDecl.ObjectStruct(
      "cef_browser_t",
      List(FnPtr("is_valid", CType.Bool, Nil))
    )
    val java = JavaNativeCodeGen.renderInnerClass(decl)
    assert(java.contains("final class NativePeer"), s"Missing NativePeer class in:\n$java")
    assert(java.contains("isValid0(nativePtr)"), s"Missing delegation in:\n$java")
    assert(java.contains("static native boolean isValid0(long self)"), s"Missing native decl in:\n$java")
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

  test("doc comment joining removes ordinary prose line-wrap artifacts") {
    val input =
      "Class used to represent the browser process aspects of a browser. The\nmethods of this class can only be called in the browser process. They may be\ncalled on any thread in that process unless otherwise indicated in the\ncomments."

    val result = DocComments.convertCefDoc(input)

    assert(
      result.contains(
        "Class used to represent the browser process aspects of a browser. The methods of this class can only be called in the browser process. They may be called on any thread in that process unless otherwise indicated in the comments."
      ),
      s"Expected prose lines to be joined into one paragraph in:\n$result"
    )
  }

  test("class doc source refs keep Doxygen links in @see tags") {
    given DocComments.Context = DocComments.baseContext(146, 0)

    val result = JavaCodeGen.renderClassDoc(
      classDoc = "Reads data from a stream.\n@_cefsrc:cef_stream.h:42"
    )

    assert(
      result.contains(
        """@see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__stream_8h.html">cef_stream.h:42</a>"""
      ),
      s"Expected linked @see tag in:\n$result"
    )
  }

  test("method doc source refs keep Doxygen links in @see tags") {
    given namingContext: Naming.Context   = Naming.Context.empty
    given docContext: DocComments.Context = DocComments.baseContext(146, 0)

    val fn     = FnPtr("read", CType.Long, List(Param("buffer", CType.Buffer("size"))))
    val result = DocComments.forMethod(
      fn = fn,
      docs = Map("read" -> "Reads from a stream.\n@_cefsrc:cef_stream.h:42"),
      capiSource = "cef_stream_capi.h",
      cPrototype = "size_t (CEF_CALLBACK* read)(struct _cef_read_handler_t* self, void* ptr, size_t size, size_t n);"
    )

    assert(
      result.contains(
        """@see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__stream_8h.html">cef_stream.h:42</a>"""
      ),
      s"Expected linked method @see tag in:\n$result"
    )
  }

  test("Doxygen URL for subdirectory header strips directory prefix") {
    given DocComments.Context = DocComments.baseContext(146, 0)

    val result = JavaCodeGen.renderClassDoc(
      classDoc = "A scroll view.\n@_cefsrc:views/cef_scroll_view.h:10"
    )

    // Doxygen URLs use only the filename, not the directory path
    assert(
      result.contains("cef__scroll__view_8h.html"),
      s"Expected filename-only Doxygen URL in:\n$result"
    )
    assert(
      !result.contains("views_2"),
      s"Doxygen URL should not contain directory prefix:\n$result"
    )
  }

  test("method doc cross references keep Javadoc links") {
    given namingContext: Naming.Context   = Naming.Context.empty
    given docContext: DocComments.Context = DocComments.Context(
      docsBaseUrl = "https://cef-builds.spotifycdn.com/docs/146.0",
      classNameMap = Map("CefLifeSpanHandler" -> "CefLifeSpanHandler"),
      methodSigMap = Map(
        ("CefLifeSpanHandler", "onBeforeClose") -> List(List("CefBrowser"))
      )
    )

    val fn     = FnPtr("get_main_frame", CType.ObjectPtr("cef_frame_t"), Nil)
    val result = DocComments.forMethod(
      fn = fn,
      docs = Map(
        "get_main_frame" ->
          "Returns the main frame after CefLifeSpanHandler::OnBeforeClose() is called.\n@_cefsrc:cef_browser.h:152"
      ),
      capiSource = "cef_browser_capi.h",
      cPrototype = "cef_frame_t* (CEF_CALLBACK* get_main_frame)(struct _cef_browser_t* self);"
    )

    assert(
      result.contains("{@link CefLifeSpanHandler#onBeforeClose(CefBrowser)}"),
      s"Expected method cross-reference link in:\n$result"
    )
  }

  test("method doc lookup prefers owner-qualified capi_name matches over unqualified aliases") {
    given namingContext: Naming.Context = Naming.Context(
      cppClassNames = Map("cef_before_download_callback_t" -> "CefBeforeDownloadCallback"),
      compoundSegments = Map.empty,
      javaPackage = "net.kurobako.cef4j.gen"
    )
    given docContext: DocComments.Context = DocComments.baseContext(146, 0)

    val fn = FnPtr(
      name = "cont",
      ret = CType.Void,
      params = List(Param("download_path", CType.JString), Param("show_dialog", CType.Int))
    )
    val docs = Map(
      "CefBeforeDownloadCallback::Continue" ->
        "Call to continue the download.\n/*--cef(capi_name=cont,optional_param=download_path)--*/\n@_cefsrc:cef_download_handler.h:51",
      "CefCallback::Continue" ->
        "Callback for asynchronous continuation of Read().\n/*--cef(capi_name=cont)--*/\n@_cefsrc:cef_resource_handler.h:70",
      "cont" ->
        "Callback for asynchronous continuation of Read().\n/*--cef(capi_name=cont)--*/\n@_cefsrc:cef_resource_handler.h:70"
    )

    val result = DocComments.forMethod(
      fn = fn,
      docs = docs,
      capiSource = "cef_download_handler_capi.h",
      cPrototype =
        "void (CEF_CALLBACK* cont)(struct _cef_before_download_callback_t* self, const cef_string_t* download_path, int show_dialog);",
      cefStructName = "cef_before_download_callback_t"
    )

    assert(result.contains("Call to continue the download."), s"Expected owner-qualified download doc in:\n$result")
    assert(
      result.contains("cef_download_handler.h:51"),
      s"Expected owner-qualified source reference in:\n$result"
    )
    assert(!result.contains("continuation of Read()"), s"Unexpected collided cont() doc in:\n$result")
  }

  test("static free function docs prefer owner-qualified create over unrelated create") {
    given namingContext: Naming.Context = Naming.Context(
      cppClassNames = Map("cef_shared_process_message_builder_t" -> "CefSharedProcessMessageBuilder"),
      compoundSegments = Map.empty,
      javaPackage = "net.kurobako.cef4j.gen"
    )
    given docContext: DocComments.Context = DocComments.baseContext(146, 0)

    val tmpDir: java.nio.file.Path              = java.nio.file.Files.createTempDirectory("cef4j-doc-test")
    val objectDecl: CefDecl.ObjectStruct        = CefDecl.ObjectStruct("cef_shared_process_message_builder_t", Nil)
    val freeMethods: List[CefDecl.FreeFunction] = List(
      CefDecl.FreeFunction(
        cName = "cef_shared_process_message_builder_create",
        ret = CType.ObjectPtr("cef_shared_process_message_builder_t"),
        params = List(Param("name", CType.JString), Param("byte_size", CType.SizeT)),
        ownerStruct = "cef_shared_process_message_builder_t",
        javaMethodName = "create",
        sourceHeader = "cef_shared_process_message_builder_capi.h"
      )
    )
    val docs = Map(
      "CefSharedProcessMessageBuilder::Create" ->
        "Creates a new CefSharedProcessMessageBuilder with the specified |name|.\n@_cefsrc:cef_shared_process_message_builder.h:51",
      "CefZipReader::Create" ->
        "Create a new CefZipReader object.\n@_cefsrc:cef_zip_reader.h:51",
      "Create" ->
        "Create a new CefZipReader object.\n@_cefsrc:cef_zip_reader.h:51"
    )

    JavaInterfaceCodeGen.emitObject(objectDecl, tmpDir, docs = docs, freeFunctions = freeMethods)
    val javaCode = java.nio.file.Files.readString(tmpDir.resolve("CefSharedProcessMessageBuilder.java"))

    assert(
      javaCode.contains("Creates a new CefSharedProcessMessageBuilder"),
      s"Expected owner-qualified create() doc in:\n$javaCode"
    )
    assert(
      javaCode.contains("cef_shared_process_message_builder.h:51"),
      s"Expected correct create() source reference in:\n$javaCode"
    )
    assert(
      !javaCode.contains("Create a new CefZipReader object."),
      s"Unexpected collided create() doc in:\n$javaCode"
    )
  }

  test("free function docs prefer owner-qualified create variants over generic create docs") {
    given namingContext: Naming.Context = Naming.Context(
      cppClassNames = Map("cef_scroll_view_t" -> "CefScrollView"),
      compoundSegments = Map.empty,
      javaPackage = "net.kurobako.cef4j.gen"
    )
    given docContext: DocComments.Context = DocComments.baseContext(146, 0)

    val tmpDir: java.nio.file.Path              = java.nio.file.Files.createTempDirectory("cef4j-doc-test")
    val objectDecl: CefDecl.ObjectStruct        = CefDecl.ObjectStruct("cef_scroll_view_t", Nil)
    val freeMethods: List[CefDecl.FreeFunction] = List(
      CefDecl.FreeFunction(
        cName = "cef_scroll_view_create",
        ret = CType.ObjectPtr("cef_scroll_view_t"),
        params = List(Param("delegate", CType.ObjectPtr("cef_view_delegate_t"))),
        ownerStruct = "cef_scroll_view_t",
        javaMethodName = "create",
        sourceHeader = "views/cef_scroll_view_capi.h"
      )
    )
    val docs = Map(
      "CefScrollView::CreateScrollView" ->
        "Create a new ScrollView.\n/*--cef(optional_param=delegate)--*/\n@_cefsrc:views/cef_scroll_view.h:49",
      "CefZipReader::Create" ->
        "Create a new CefZipReader object.\n@_cefsrc:cef_zip_reader.h:51",
      "Create" ->
        "Create a new CefZipReader object.\n@_cefsrc:cef_zip_reader.h:51"
    )

    JavaInterfaceCodeGen.emitObject(objectDecl, tmpDir, docs = docs, freeFunctions = freeMethods)
    val javaCode = java.nio.file.Files.readString(tmpDir.resolve("CefScrollView.java"))

    assert(
      javaCode.contains("Create a new ScrollView."),
      s"Expected owner-qualified ScrollView create() doc in:\n$javaCode"
    )
    assert(
      javaCode.contains("views/cef_scroll_view.h:49"),
      s"Expected correct ScrollView create() source reference in:\n$javaCode"
    )
    assert(
      !javaCode.contains("Create a new CefZipReader object."),
      s"Unexpected collided create() doc in:\n$javaCode"
    )
  }

  test("doc conversion escapes shift expressions inside generated C prototypes") {
    given namingContext: Naming.Context   = Naming.Context.empty
    given docContext: DocComments.Context = DocComments.Context.empty

    val result = DocComments.convertCefDoc(
      "Flags used to customize the behavior of CefURLRequest.",
      capiSource = "cef_types.h",
      cPrototype = "typedef enum {\n  UR_FLAG_SKIP_CACHE = 1 << 0\n} cef_urlrequest_flags_t;"
    )

    assert(result.contains("1 &lt;&lt; 0"), s"Expected escaped shift expression in:\n$result")
  }

  test("doc comment conversion escapes literal self-closing tags in prose") {
    given namingContext: Naming.Context   = Naming.Context.empty
    given docContext: DocComments.Context = DocComments.Context.empty

    val result = DocComments.convertCefDoc(
      """Returns {@code true} if the node represents an empty element. "<a/>" is considered empty."""
    )

    assert(result.contains("&lt;a/&gt;"), s"Expected literal XML tag to be escaped in:\n$result")
  }

  test("doc comment conversion escapes literal closing tags in prose") {
    given namingContext: Naming.Context   = Naming.Context.empty
    given docContext: DocComments.Context = DocComments.Context.empty

    val result = DocComments.convertCefDoc(
      """"<a>" is considered empty while "</a>" is not."""
    )

    assert(result.contains("&lt;/a&gt;"), s"Expected literal closing tag to be escaped in:\n$result")
  }

  test("doc comment conversion escapes literal placeholder tags in prose") {
    given namingContext: Naming.Context   = Naming.Context.empty
    given docContext: DocComments.Context = DocComments.Context.empty

    val result = DocComments.convertCefDoc(
      """Pass the `--devtools-protocol-log-file=<path>` command-line flag."""
    )

    assert(result.contains("&lt;path&gt;"), s"Expected literal placeholder tag to be escaped in:\n$result")
  }

  test("doc comment conversion escapes ampersands in prose") {
    given namingContext: Naming.Context   = Naming.Context.empty
    given docContext: DocComments.Context = DocComments.Context.empty

    val result = DocComments.convertCefDoc(
      "Called during a drag & drop operation."
    )

    assert(result.contains("drag &amp; drop"), s"Expected ampersand to be escaped in:\n$result")
  }

  test("doc comment conversion escapes comparison operators in prose") {
    given namingContext: Naming.Context   = Naming.Context.empty
    given docContext: DocComments.Context = DocComments.Context.empty

    val result = DocComments.convertCefDoc(
      "If delay_ms is <= 0 then the callback should happen soon."
    )

    assert(result.contains("&lt;= 0"), s"Expected <= comparison to be escaped in:\n$result")
  }

  test("JNI symbol for multi-word function name is correct") {
    val fn  = FnPtr("send_mouse_click_event", CType.Void, Nil)
    val sym = Naming.jniSymbol("cef_browser_host_t", fn)
    assertEquals(sym, "Java_net_kurobako_cef4j_gen_CefBrowserHost_00024NativePeer_sendMouseClickEvent0")
  }

  test("generated Java package is configurable through Naming context") {
    given configuredNamingContext: Naming.Context = Naming.Context(Map.empty, Map.empty, "com.example.cef.gen")
    given emptyDocContext: DocComments.Context    = DocComments.Context.empty

    val java = JavaCodeGen.renderJavaFile(
      declaration = "public final class Example",
      body = "    private Example() {}"
    )

    assert(java.contains("package com.example.cef.gen;"), s"Expected configured package in:\n$java")
  }

  test("JNI symbols track configured generated package") {
    given configuredNamingContext: Naming.Context = Naming.Context(Map.empty, Map.empty, "com.example.cef.gen")

    val fn  = FnPtr("go_back", CType.Void, Nil)
    val sym = Naming.jniSymbol("cef_browser_t", fn)

    assertEquals(sym, "Java_com_example_cef_gen_CefBrowser_00024NativePeer_goBack0")
    assertEquals(Naming.nativePointerInternalName, "com/example/cef/gen/NativePointer")
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

  test("handler codegen supports by-value data-struct returns") {
    val decl: CefDecl.HandlerStruct = CefDecl.HandlerStruct(
      "cef_example_handler_t",
      List(
        FnPtr("get_size", CType.DataStruct("cef_size_t"), Nil)
      )
    )

    val cpp = byValueCodegen.emitHandlerToString(decl)

    assert(cpp.contains("cef_size_t nativeResult = ([&]()"), s"Missing by-value return conversion in:\n$cpp")
    assert(
      cpp.contains("""FindClassCached(env, "net/kurobako/cef4j/gen/CefSize")"""),
      s"Missing struct class lookup in:\n$cpp"
    )
    assert(
      cpp.contains("""env->GetIntField(jResult, env->GetFieldID(_c, "width", "I"))"""),
      s"Missing field extraction in:\n$cpp"
    )
  }

  test("jniSig for DataStruct emits fully-qualified class descriptor, not Object") {
    assertEquals(
      Naming.jniSig(CType.DataStruct("cef_size_t")),
      "Lnet/kurobako/cef4j/gen/CefSize;"
    )
  }

  test("handler trampoline uses correct JNI descriptor for DataStruct return") {
    val decl: CefDecl.HandlerStruct = CefDecl.HandlerStruct(
      "cef_example_handler_t",
      List(
        FnPtr("get_size", CType.DataStruct("cef_size_t"), Nil)
      )
    )

    val cpp = byValueCodegen.emitHandlerToString(decl)

    assert(
      cpp.contains("()Lnet/kurobako/cef4j/gen/CefSize;"),
      s"Expected DataStruct return descriptor to use CefSize, not Object, in:\n$cpp"
    )
    assert(
      !cpp.contains("Ljava/lang/Object;"),
      s"DataStruct return descriptor must not fall back to Object in:\n$cpp"
    )
  }

  test("handler emits C-linkage factory function") {
    val decl: CefDecl.HandlerStruct = CefDecl.HandlerStruct("cef_client_t", Nil)
    val cpp                         = codegen.emitHandlerToString(decl)
    assert(cpp.contains("Create_JniCefClient"), s"Missing C-linkage factory in:\n$cpp")
    assert(cpp.contains("cef_client_t* Create_JniCefClient"), s"Missing return type in:\n$cpp")
  }

  test("C int return recovered to bool produces boolean Java method and correct JNI marshalling") {
    // Simulate a handler where C API uses int but C++ uses bool (e.g. getLocalizedString)
    val handlerDecl: CefDecl.HandlerStruct = CefDecl.HandlerStruct(
      "cef_resource_bundle_handler_t",
      List(
        FnPtr(
          "get_localized_string",
          CType.Bool, // recovered from CType.Int via CppMethodTypeInfo
          List(
            Param("string_id", CType.Int),
            Param("string", CType.JString)
          )
        )
      )
    )

    // Verify Java interface emits boolean return type
    given namingContext: Naming.Context   = Naming.Context.empty
    given docContext: DocComments.Context = DocComments.Context.empty
    val tmpDir                            = java.nio.file.Files.createTempDirectory("codegen-test")
    JavaInterfaceCodeGen.emitHandler(handlerDecl, tmpDir)
    val javaCode = java.nio.file.Files.readString(tmpDir.resolve("CefResourceBundleHandler.java"))
    assert(
      javaCode.contains("default boolean getLocalizedString("),
      s"Expected boolean return type in Java interface:\n$javaCode"
    )

    // Verify handler trampoline calls CallBooleanMethod and uses Z signature
    val cpp = codegen.emitHandlerToString(handlerDecl)
    assert(cpp.contains("CallBooleanMethod"), s"Expected CallBooleanMethod in trampoline:\n$cpp")
    assert(
      cpp.contains("\"Z\"") || cpp.contains("Z)") || cpp.contains(")Z\""),
      s"Expected Z (boolean) in JNI signature:\n$cpp"
    )
    // The C callback returns int, jboolean implicitly promotes
    assert(cpp.contains("return jResult;"), s"Expected direct return of jboolean result:\n$cpp")

    // Also verify an ObjectStruct with Bool return works on the NativePeer JNI side
    val objectDecl: CefDecl.ObjectStruct = CefDecl.ObjectStruct(
      "cef_window_t",
      List(FnPtr("is_closed", CType.Bool, Nil))
    )
    val nativeCpp = codegen.emitToString(objectDecl)
    assert(
      nativeCpp.contains("static_cast<jboolean>"),
      s"Expected static_cast<jboolean> in NativePeer for Bool return:\n$nativeCpp"
    )
  }

  test("type recovery promotes C int to Bool when C++ header says bool") {
    val handlerDecl: CefDecl.HandlerStruct = CefDecl.HandlerStruct(
      "cef_resource_bundle_handler_t",
      List(
        FnPtr(
          "get_localized_string",
          CType.Int, // C API type - should be recovered to Bool
          List(Param("string_id", CType.Int), Param("string", CType.JString))
        )
      )
    )
    val cppTypeInfo = Map(
      "CefResourceBundleHandler::GetLocalizedString" -> CppMethodTypeInfo(
        "bool",
        Map("string_id" -> "int", "string" -> "CefString&")
      ),
      "GetLocalizedString" -> CppMethodTypeInfo("bool", Map("string_id" -> "int", "string" -> "CefString&"))
    )
    val parsed = ParsedTree(
      decls = List(handlerDecl),
      structDecls = List(handlerDecl),
      freeFunctions = Nil,
      knownStructNames = Set("cef_resource_bundle_handler_t")
    )
    val namingCtx = Naming.Context.fromCppClassNames(
      Map("cef_resource_bundle_handler_t" -> "CefResourceBundleHandler"),
      Map.empty,
      "net.kurobako.cef4j.gen"
    )
    val parseState = ParseState(
      namingContext = namingCtx,
      docContext = DocComments.Context.empty,
      handlerNames = Set.empty,
      docs = Map.empty,
      cppTypeInfo = cppTypeInfo,
      enumDocs = Map.empty,
      classDocs = Map.empty,
      structHeaderMap = Map.empty,
      structFieldDocs = Map.empty
    )
    val refined     = passes.RefineTree(parsed, parseState)
    val recoveredFn = refined.decls.head match {
      case h: CefDecl.HandlerStruct => h.fns.head
      case _                        => fail("Expected HandlerStruct")
    }
    assertEquals(recoveredFn.ret, CType.Bool, "Return type should be recovered from Int to Bool")
  }

  test("type recovery uses class-qualified lookup to avoid cross-class collisions") {
    // Two classes have SetVisible with different return types
    val menuModelDecl = CefDecl.ObjectStruct(
      "cef_menu_model_t",
      List(FnPtr("set_visible", CType.Int, List(Param("command_id", CType.Int), Param("visible", CType.Int))))
    )
    val viewDecl = CefDecl.ObjectStruct(
      "cef_view_t",
      List(FnPtr("set_visible", CType.Int, List(Param("visible", CType.Int))))
    )
    // CefMenuModel::SetVisible returns bool, CefView::SetVisible returns void
    // Without qualified lookup, last-writer-wins would make one of them wrong
    val cppTypeInfo = Map(
      "CefMenuModel::SetVisible" -> CppMethodTypeInfo("bool", Map("command_id" -> "int", "visible" -> "bool")),
      "CefView::SetVisible"      -> CppMethodTypeInfo("void", Map("visible" -> "bool")),
      "SetVisible"               -> CppMethodTypeInfo("void", Map("visible" -> "bool")) // last-writer-wins = void
    )
    val parsed = ParsedTree(
      decls = List(menuModelDecl, viewDecl),
      structDecls = List(menuModelDecl, viewDecl),
      freeFunctions = Nil,
      knownStructNames = Set("cef_menu_model_t", "cef_view_t")
    )
    val namingCtx = Naming.Context.fromCppClassNames(
      Map("cef_menu_model_t" -> "CefMenuModel", "cef_view_t" -> "CefView"),
      Map.empty,
      "net.kurobako.cef4j.gen"
    )
    val parseState = ParseState(
      namingContext = namingCtx,
      docContext = DocComments.Context.empty,
      handlerNames = Set.empty,
      docs = Map.empty,
      cppTypeInfo = cppTypeInfo,
      enumDocs = Map.empty,
      classDocs = Map.empty,
      structHeaderMap = Map.empty,
      structFieldDocs = Map.empty
    )
    val refined = passes.RefineTree(parsed, parseState)

    // CefMenuModel::setVisible should be Bool (from qualified lookup), not Int (from unqualified void)
    val menuModelFn = refined.decls.head match {
      case o: CefDecl.ObjectStruct => o.fns.head
      case _                       => fail("Expected ObjectStruct for menu model")
    }
    assertEquals(menuModelFn.ret, CType.Bool, "CefMenuModel.setVisible should recover to Bool via qualified lookup")

    // CefView::setVisible should stay Int (void doesn't promote)
    val viewFn = refined.decls(1) match {
      case o: CefDecl.ObjectStruct => o.fns.head
      case _                       => fail("Expected ObjectStruct for view")
    }
    assertEquals(viewFn.ret, CType.Int, "CefView.setVisible should stay Int (C++ returns void, no promotion)")
  }

  test("NativePeer emits @Nullable on non-optional String params (not strict-null-check)") {
    val decl: CefDecl.ObjectStruct = CefDecl.ObjectStruct(
      "cef_jsdialog_callback_t",
      List(FnPtr("cont", CType.Void, List(Param("success", CType.Int), Param("user_input", CType.JString))))
    )
    val java = JavaNativeCodeGen.renderInnerClass(decl)
    assert(java.contains("@Nullable String userInput"), s"Missing @Nullable on String param in:\n$java")
  }

  test("NativePeer emits @Nonnull on strict-null-check params (enums, by-value structs)") {
    val decl: CefDecl.ObjectStruct = CefDecl.ObjectStruct(
      "cef_test_t",
      List(FnPtr("set_mode", CType.Void, List(Param("mode", CType.Enum("cef_mode_t")))))
    )
    val java = JavaNativeCodeGen.renderInnerClass(decl)
    assert(java.contains("@Nonnull"), s"Missing @Nonnull on enum param in:\n$java")
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
