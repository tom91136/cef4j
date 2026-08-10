package net.kurobako.cef4j.codegen.ipc

/** Layout-spec IR for cef4j IPC messages.
  *
  * A message is a flat list of typed fields, encoded sequentially in little-endian. Variable-length fields are
  * length-prefixed (4-byte LE int32). Codegen emits Java encoder + decoder + view from this; later sessions add a C++
  * struct-overlay emitter.
  *
  * The IR is hand-written for now; future work will derive specs from CEF AST nodes (Decision 10, architecture doc).
  */
enum FieldType {
  case I32
  case I64
  case Bool
  case Utf8String
  case Bytes

  /** Wire-encoded as `int32 count` followed by `count` length-prefixed UTF-8 strings. Used for CEF's
    * `cef_string_list_t` (HTTP headers, cookie domains, command-line args). The dispatcher converts a
    * `std::vector<std::string>` into a stack-allocated `cef_string_list_t` for the C call and frees it afterwards. JVM
    * side maps to `String[]` for ergonomics — CEF doesn't allow nulls in these lists.
    */
  case StringList

  /** Wire-encoded as a 4-byte little-endian id. The helper side maintains a `HandleTable` mapping ids back to
    * ref-counted CEF struct pointers; the JVM side wraps the id in a typed `RemoteHandle` value object.
    *
    * Lifetime: this slice doesn't yet emit retain/release messages; handles stay valid until the helper exits.
    * Refcounted lifecycle is a planned follow-up.
    */
  case RemoteHandle

  /** Inline by-value struct field. The wire layout is the data struct's own `encodedSize`/`encodeInto` recursively — no
    * length prefix at the parent level. `cefStructName` is the original C struct name (e.g. `cef_browser_settings_t`)
    * so emitters can resolve the matching DataStructSpec by name.
    */
  case DataStruct(cefStructName: String)
}

case class FieldSpec(name: String, ty: FieldType)

case class MessageSpec(
    className: String,
    packageName: String,
    messageId: Int,
    fields: List[FieldSpec]
) {
  require(messageId >= 0, s"messageId must be >= 0, got $messageId")
}

/** A typed-method facade for a CEF object. Produced by `SpecDeriver` for each `ObjectStruct`; consumed by
  * `JavaFacadeEmitter` to generate ergonomic Java wrappers like `CefBrowser` that dispatch through a `CefSession`.
  *
  * One `FacadeSpec` corresponds to one CEF struct and lists every method that successfully derived a Request/Response
  * pair. Skipped methods (unsupported return types, etc.) are absent. `cefStructName` is the original C type (e.g.
  * `cef_browser_t`); the helper-side dispatcher uses it to pick the right `HandleTable<T>` and to know the receiver
  * pointer type when invoking methods.
  */
/** Which CEF process the methods on this facade run in. CEF restricts most APIs to a single process — `cef_browser_t`
  * is browser-process only, `cef_v8_value_t` is renderer-process only. The dispatcher needs to know so it can route
  * requests correctly: browser-affinity methods are called directly on the helper's UI thread; renderer-affinity
  * methods are relayed via `cef_process_message` to the renderer subprocess and dispatched there.
  */
enum ProcessAffinity {
  case Browser
  case Renderer
}

case class FacadeSpec(
    className: String,
    packageName: String,
    cefStructName: String,
    methods: List[FacadeMethod],
    affinity: ProcessAffinity = ProcessAffinity.Browser
)

/** `cefMethodName` is the original C function-pointer name on the CEF struct (e.g. `go_back`); the dispatcher emits
  * `receiver->go_back(receiver, ...)`. `methodName` is the JVM-facing camelCase name. `handleStructByField` records,
  * per RemoteHandle param/result, the original C struct type (e.g. `cef_frame_t`); the dispatcher uses it to pick the
  * matching `HandleTable<T>` for retain/release/insert. The implicit `self` field is always the receiver type and isn't
  * listed here.
  */
case class FacadeMethod(
    methodName: String,
    cefMethodName: String,
    requestClassName: String,
    responseClassName: String,
    explicitParams: List[FieldSpec],
    resultField: Option[FieldSpec], // None if void return; Some(...) describes the Response.result field
    handleStructByField: Map[String, String] = Map.empty,
    // Ordered list of C-call argument references, in the original CEF function-pointer signature order
    // (excluding self at index 0). Most entries are Explicit fields from explicitParams; BytesSize entries
    // synthesize the size companion that gets filtered from the wire — its value is `bytesField.size()` at
    // the call site. None defaults to "explicit params in declaration order" — what most methods need.
    cCallArgs: Option[List[CCallArg]] = None
)

/** One argument in the C-call signature. Used by the dispatcher emitter to build the actual `receiver->fn(...)` call
  * when a method's C signature has filtered BufferSize companions interleaved with explicit params.
  */
sealed trait CCallArg
object CCallArg {

  /** Reference to one of the FacadeMethod's explicit fields by name. */
  case class Explicit(fieldName: String) extends CCallArg

  /** Synthesized size_t for a Bytes field (the BufferSize companion that was filtered from the wire). The dispatcher
    * emits `static_cast<size_t>(${bytesField}.size())`.
    */
  case class BytesSize(bytesField: String) extends CCallArg
}

/** A typed Java interface for a CEF `HandlerStruct` callback set (e.g. `cef_load_handler_t` → `CefLoadHandler`). Each
  * method corresponds to one event class the helper can emit; the generated `register(...)` static wires
  * `session.on(messageId, decoder, ev -> handler.method(ev.fields()))` for every method.
  *
  * Helper-side callback wiring (i.e. setting these handlers on a CefClient and forwarding to IPC events) is a separate
  * concern. These interfaces are useful even before that lands: they document the API surface and let future helper
  * code register typed callbacks immediately.
  */
case class HandlerSpec(
    className: String,
    packageName: String,
    cefStructName: String,
    methods: List[HandlerMethod]
)

/** A "JVM-owned visitor" — a HandlerStruct whose ownership lives on the JVM side and which CEF invokes. The helper
  * synthesises a real `cef_X_t` whose single callback ships an event back to the JVM carrying a callbackId; the JVM
  * dispatches by id to the registered Java visitor. Inverse of {@link HandlerSpec}, which is helper-owned and
  * broadcasts events.
  *
  * Detected from HandlerStructs that meet all of:
  *   - Used as an `ObjectPtr` method param in some facade (i.e. they cross the wire as method args).
  *   - Have a single callback method (the visit method) — multi-method visitors aren't supported yet.
  *   - Method return is `void` (single-shot fire-and-forget — non-void visitor returns are tracked separately).
  *   - Method params are simple (primitives + strings, no nested handles or by-value structs).
  */
case class JvmVisitorSpec(
    cefStructName: String,  // e.g. "cef_string_visitor_t"
    className: String,      // e.g. "CefStringVisitor"
    eventClassName: String, // e.g. "StringVisitorCallbackEvent"
    packageName: String,
    methodName: String,      // e.g. "visit"
    cefMethodName: String,   // e.g. "visit" (snake_case)
    params: List[FieldSpec], // visit args (excluding self), as wire fields
    constStringByField: Map[String, Boolean] = Map.empty
)

/** A CEF by-value data struct (e.g. `cef_browser_settings_t`, `cef_window_info_t`). Unlike the ObjectStruct facades,
  * these are pure data — no methods, no helper-side handle. They cross the wire as a flat list of typed fields encoded
  * in the same little-endian layout as message bodies.
  *
  * Initial codegen scope: only primitives (`I32` covers Int/UInt/Enum, `I64` covers Long, `Bool`) and `Utf8String`.
  * Structs that have any other field type are skipped from emission so we don't ship partial shims that misrepresent
  * the native layout.
  */
case class DataStructSpec(
    className: String,
    packageName: String,
    cefStructName: String,
    fields: List[FieldSpec]
)

case class HandlerMethod(
    methodName: String,
    cefMethodName: String,
    eventClassName: String,
    params: List[FieldSpec],
    handleStructByField: Map[String, String] = Map.empty,
    // CEF callback signatures alternate between `const cef_string_t*` and `cef_string_t*` for string params; the
    // forwarder lambda must match exactly because function-pointer types are invariant in C++. Captured per
    // string param so the emitter picks the right qualifier.
    constStringByField: Map[String, Boolean] = Map.empty,
    // None for void callbacks (delivered as Kind::Event, fire-and-forget). Some(FieldType) for callbacks the
    // helper needs a JVM-supplied return value from (DoClose returns int, OnBeforePopup returns int, etc.).
    // Non-void variants ride the Kind::Intercept wire: helper sends the request, blocks on
    // InterceptRegistry::awaitResponse, decodes the JVM-supplied response, returns to CEF.
    returnType: Option[FieldType] = None,
    // Class name for the response message when returnType is Some — symmetric to FacadeMethod's
    // requestClassName/responseClassName. The Request shape lives in eventClassName.
    responseClassName: Option[String] = None
)
