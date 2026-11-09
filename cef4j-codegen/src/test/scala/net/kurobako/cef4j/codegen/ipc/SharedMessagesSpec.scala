package net.kurobako.cef4j.codegen.ipc

import net.kurobako.cef4j.codegen.Banners
import net.kurobako.cef4j.codegen.Naming

class SharedMessagesSpec extends munit.FunSuite {

  private val handle = FieldType.RemoteHandle

  private def spec(name: String, id: Int, fields: FieldSpec*): MessageSpec =
    MessageSpec(name, "test.gen", id, fields.toList)

  test("messages with identical field names and types share one class named after the shape") {
    val shared = SharedMessages.derive(List(
      spec("BrowserIsSameRequest", 10001, FieldSpec("self", handle), FieldSpec("that", handle)),
      spec("ImageIsSameRequest", 10002, FieldSpec("self", handle), FieldSpec("that", handle))
    ))

    assertEquals(shared.messages.map(_.className), List("SelfHandleThatHandleMessage"))
    assertEquals(shared.refs.typeName("BrowserIsSameRequest"), "SelfHandleThatHandleMessage")
    assertEquals(shared.refs.typeName("ImageIsSameRequest"), "SelfHandleThatHandleMessage")
    assertEquals(
      shared.ids,
      List(MessageId("BROWSER_IS_SAME_REQUEST", 10001), MessageId("IMAGE_IS_SAME_REQUEST", 10002))
    )
  }

  test("a different field name or type is a different shape") {
    val shared = SharedMessages.derive(List(
      spec("AFooRequest", 10001, FieldSpec("self", handle), FieldSpec("that", handle)),
      spec("BFooRequest", 10002, FieldSpec("self", handle), FieldSpec("other", handle)),
      spec("CFooRequest", 10003, FieldSpec("self", handle), FieldSpec("that", FieldType.I32)),
      spec("DFooRequest", 10004, FieldSpec("self", handle), FieldSpec("that", handle))
    ))

    assertEquals(
      shared.messages.map(_.className).sorted,
      List("BFooRequest", "CFooRequest", "SelfHandleThatHandleMessage")
    )
  }

  test("a shape used by one message keeps the name of that message") {
    val shared = SharedMessages.derive(List(
      spec("BrowserHostFindRequest", 10001, FieldSpec("self", handle), FieldSpec("term", FieldType.Utf8String)),
      spec("BrowserGoBackRequest", 10002, FieldSpec("self", handle)),
      spec("BrowserGoForwardRequest", 10003, FieldSpec("self", handle))
    ))

    assertEquals(shared.messages.map(_.className).sorted, List("BrowserHostFindRequest", "SelfHandleMessage"))
    assertEquals(shared.refs.typeName("BrowserHostFindRequest"), "BrowserHostFindRequest")
    assertEquals(shared.refs.typeName("BrowserGoBackRequest"), "SelfHandleMessage")
    assertEquals(shared.refs.typeName("BrowserGoForwardRequest"), "SelfHandleMessage")
  }

  test("a message without fields maps to the empty shape") {
    val shared = SharedMessages.derive(List(spec("AResponse", 10001), spec("BResponse", 10002)))

    assertEquals(shared.messages.map(_.className), List("EmptyMessage"))
  }

  test("long shapes keep a bounded, distinct name") {
    def wide(last: String) = (1 to 12).map(i => FieldSpec(s"parameter$i", FieldType.Utf8String)) :+
      FieldSpec(last, FieldType.I32)
    val shared = SharedMessages.derive(List(
      spec("AWideRequest", 10001, wide("tailA")*),
      spec("BWideRequest", 10002, wide("tailB")*),
      spec("CWideRequest", 10003, wide("tailA")*),
      spec("DWideRequest", 10004, wide("tailB")*)
    ))

    val names = shared.messages.map(_.className)
    assertEquals(names.distinct.size, 2)
    names.foreach(n => assert(n.length <= 112, n))
  }

  test("references name the shared class for derived messages and the message itself otherwise") {
    val shared = SharedMessages.derive(List(
      spec("BrowserGoBackRequest", 10001, FieldSpec("self", handle)),
      spec("BrowserGoForwardRequest", 10002, FieldSpec("self", handle))
    ))
    val refs = shared.refs

    assertEquals(refs.idExpr("BrowserGoBackRequest"), "MessageIds.BROWSER_GO_BACK_REQUEST")
    assertEquals(
      refs.newExpr("BrowserGoBackRequest", "handle"),
      "new SelfHandleMessage(handle)"
    )
    assertEquals(
      refs.decoderExpr("BrowserGoBackRequest"),
      "SelfHandleMessage.DECODER"
    )
    assertEquals(refs.idExpr("SetViewportSizeRequest"), "MessageIds.SET_VIEWPORT_SIZE_REQUEST")
    assertEquals(refs.newExpr("SetViewportSizeRequest", "a, b"), "new SetViewportSizeRequest(a, b)")
    assertEquals(refs.decoderExpr("SetViewportSizeRequest"), "SetViewportSizeRequest.DECODER")
  }

  test("hand-written messages share classes with derived ones and keep their fixed ids") {
    val shared = SharedMessages.derive(List(
      spec("ReleaseHandleRequest", 6, FieldSpec("self", handle)),
      spec("BrowserGoBackRequest", 10001, FieldSpec("self", handle)),
      spec("SetViewportSizeRequest", 12, FieldSpec("width", FieldType.I32))
    ))
    val refs = shared.refs

    assertEquals(shared.messages.map(_.className).sorted, List("SelfHandleMessage", "SetViewportSizeRequest"))
    assertEquals(refs.typeName("ReleaseHandleRequest"), "SelfHandleMessage")
    assertEquals(refs.idExpr("ReleaseHandleRequest"), "MessageIds.RELEASE_HANDLE_REQUEST")
    assertEquals(refs.newExpr("SetViewportSizeRequest", "w"), "new SetViewportSizeRequest(w)")
    assertEquals(refs.decoderExpr("SetViewportSizeRequest"), "SetViewportSizeRequest.DECODER")
    assert(shared.ids.contains(MessageId("RELEASE_HANDLE_REQUEST", 6)))
    assert(shared.ids.contains(MessageId("SET_VIEWPORT_SIZE_REQUEST", 12)))
  }

  test("an empty argument list builds the message without arguments") {
    val refs =
      SharedMessages.derive(List(spec("BrowserGoBackResponse", 10001), spec("BrowserGoForwardResponse", 10002))).refs

    assertEquals(
      refs.newExpr("BrowserGoBackResponse", ""),
      "new EmptyMessage()"
    )
  }

  test("message ids class lists one constant per derived message") {
    given Banners = Banners.forIpc
    val source    = JavaMessageIdsEmitter.emit(
      List(MessageId("A_REQUEST", 10001), MessageId("B_REQUEST", 10002)),
      "test.gen"
    )

    assert(source.contains("package test.gen;"))
    assert(source.contains("public final class MessageIds"))
    assert(source.contains("public static final int A_REQUEST = 10001;"))
    assert(source.contains("public static final int B_REQUEST = 10002;"))
  }
}
