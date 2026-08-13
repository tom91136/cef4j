package net.kurobako.cef4j.codegen.ipc

/** Hand-written message specs. Future work (later sessions): derive specs from CEF AST nodes (Decision 10). */
object Specs {

  def all(packageName: String): List[MessageSpec] = List(
    // ----- Refcount lifecycle: JVM-side facade.close() releases the matching runtime-server-side handle. The
    //       dispatcher's generated `dispatchRelease(kind, id)` switches on `kind` (the CEF struct name, e.g.
    //       "cef_browser_t") to pick the right HandleTable. Single shared message keeps the wire surface tight.
    MessageSpec(
      className = "ReleaseHandleRequest",
      packageName = packageName,
      messageId = 6,
      fields = List(FieldSpec("handle", FieldType.RemoteHandle), FieldSpec("kind", FieldType.Utf8String))
    ),
    MessageSpec(
      className = "ReleaseHandleResponse",
      packageName = packageName,
      messageId = 6,
      fields = Nil
    ),

    // ----- Renderer-affinity counterpart of ReleaseHandleRequest. JVM facades for cef_v8_*/cef_dom* hold
    //       handles into the renderer subprocess's `tables::X` (the runtime server binary holds independent table
    //       state per process). Plain ReleaseHandleRequest dispatches in the browser process and would
    //       silently no-op for these. RendererReleaseHandleRequest carries a frame so the browser-side
    //       relay can ship it to the right renderer; the renderer-side handler then runs dispatchRelease
    //       in its own table state. messageId is part of the renderer-relay set (the runtime server main.cpp
    //       intercepts this id before genrender::dispatch and routes it to gendisp::dispatchRelease).
    MessageSpec(
      className = "RendererReleaseHandleRequest",
      packageName = packageName,
      messageId = 24,
      fields = List(
        FieldSpec("frame", FieldType.RemoteHandle),
        FieldSpec("handle", FieldType.RemoteHandle),
        FieldSpec("kind", FieldType.Utf8String)
      )
    ),
    MessageSpec(
      className = "RendererReleaseHandleResponse",
      packageName = packageName,
      messageId = 24,
      fields = Nil
    ),

    // ----- OSR viewport resize. JFX layout drives a per-browser size; the runtime server updates its render
    //       handler's view rect and calls cef_browser_host_t::was_resized so CEF re-queries dimensions and
    //       triggers a fresh paint at the new size. Codegen-generated BrowserHost.wasResized covers the
    //       second half (signalling the resize) but doesn't ship dimensions; this Request feeds the runtime server
    //       the JFX-side size so the runtime-server-side renderer knows what to ask for. shm capacity is sized for
    //       4K (sufficient for any practical resize); we don't reallocate today, just paint into a larger
    //       buffer.
    MessageSpec(
      className = "SetViewportSizeRequest",
      packageName = packageName,
      messageId = 25,
      fields = List(
        FieldSpec("browser", FieldType.RemoteHandle),
        FieldSpec("width", FieldType.I32),
        FieldSpec("height", FieldType.I32)
      )
    ),
    MessageSpec(
      className = "SetViewportSizeResponse",
      packageName = packageName,
      messageId = 25,
      fields = Nil
    ),

    // ----- Browser-creation factory. Carries a `BrowserSettings` data struct over the wire (the new
    //       FieldType.DataStruct path); the runtime server decodes it and copies field-by-field into a native
    //       cef_browser_settings_t before calling cef_browser_host_create_browser. The new browser's handle
    //       arrives via LifeSpanHandlerOnAfterCreatedEvent (forwarder + tables::browser.insert).
    //       window_info_t is still runtime-server-side defaults (windowless 800x600); adding it as a second field is
    //       the natural follow-up once we have a WindowInfo data struct (currently filtered because it has
    //       a platform handle field).
    MessageSpec(
      className = "CreateBrowserRequest",
      packageName = packageName,
      messageId = 7,
      fields = List(
        FieldSpec("url", FieldType.Utf8String),
        FieldSpec("settings", FieldType.DataStruct("cef_browser_settings_t"))
      )
    ),
    MessageSpec(
      className = "CreateBrowserResponse",
      packageName = packageName,
      messageId = 7,
      fields = Nil
    ),

    // ----- V8 evaluate javascript: JVM evaluates JS in a specific frame's V8 context and gets the result.
    //       Wire path: JVM → browser-process runtime server (Kind::Request with corrId) → relay to renderer via
    //       cef_process_message("v8_eval_req") with [corrId, code] → renderer looks up frame, evaluates in
    //       the frame's V8 context, packs result into cef_process_message("v8_eval_resp") with [corrId,
    //       valueKind, value] → browser-process on_process_message_received decodes and replies as
    //       Kind::Response with EvaluateJavascriptResponse.
    //       valueKind: 0=null/undefined, 1=bool, 2=int, 3=double, 4=string, 5=error (errorMessage carries
    //       the exception text). For complex values (objects, arrays), the result is the JSON stringification.
    MessageSpec(
      className = "EvaluateJavascriptRequest",
      packageName = packageName,
      messageId = 12,
      fields = List(
        FieldSpec("frame", FieldType.RemoteHandle),
        FieldSpec("code", FieldType.Utf8String),
        // When true, complex values (objects/arrays/functions) are retained in the renderer's V8 handle
        // table and the response carries a non-zero valueHandle. JVM calls V8 methods through that handle
        // until V8ReleaseHandleRequest. When false, complex values are JSON.stringified as before — the
        // pre-handle eval shape, kept for callers that just want a value-or-string.
        FieldSpec("retainHandle", FieldType.Bool)
      )
    ),
    MessageSpec(
      className = "EvaluateJavascriptResponse",
      packageName = packageName,
      messageId = 12,
      fields = List(
        FieldSpec("valueKind", FieldType.I32),
        FieldSpec("boolValue", FieldType.Bool),
        FieldSpec("intValue", FieldType.I32),
        FieldSpec("doubleValue", FieldType.I64), // bit-cast double for wire portability
        FieldSpec("stringValue", FieldType.Utf8String),
        FieldSpec("errorMessage", FieldType.Utf8String),
        // Non-zero only when retainHandle was true and the result was a complex V8 value (object/array/
        // function). Points into the renderer-side cef_v8_value_t HandleTable. JVM uses it via
        // V8Value*Request messages until V8ReleaseHandleRequest.
        FieldSpec("valueHandle", FieldType.I32)
      )
    ),

    // ----- V8 value method dispatch: JVM holds a renderer-side V8 handle id (from EvaluateJavascript with
    //       retainHandle=true) and calls methods on it. Same wire path as EvaluateJavascript: JVM →
    //       browser-process server (Kind::Request) → cef_process_message to renderer → V8 op → response process
    //       message → Kind::Response. Renderer-side dispatcher routes by message name.
    MessageSpec(
      className = "V8GetStringValueRequest",
      packageName = packageName,
      messageId = 13,
      fields = List(
        FieldSpec("frame", FieldType.RemoteHandle),
        FieldSpec("v8Handle", FieldType.I32)
      )
    ),
    MessageSpec(
      className = "V8GetStringValueResponse",
      packageName = packageName,
      messageId = 13,
      fields = List(
        FieldSpec("ok", FieldType.Bool),
        FieldSpec("stringValue", FieldType.Utf8String)
      )
    ),
    MessageSpec(
      className = "V8GetPropertyRequest",
      packageName = packageName,
      messageId = 14,
      fields = List(
        FieldSpec("frame", FieldType.RemoteHandle),
        FieldSpec("v8Handle", FieldType.I32),
        FieldSpec("propertyName", FieldType.Utf8String)
      )
    ),
    // Property reads return a fresh JsResult-shaped response — primitives inline, complex values get a
    // child v8Handle into the same renderer-side table (so caller can drill recursively).
    MessageSpec(
      className = "V8GetPropertyResponse",
      packageName = packageName,
      messageId = 14,
      fields = List(
        FieldSpec("valueKind", FieldType.I32),
        FieldSpec("boolValue", FieldType.Bool),
        FieldSpec("intValue", FieldType.I32),
        FieldSpec("doubleValue", FieldType.I64),
        FieldSpec("stringValue", FieldType.Utf8String),
        FieldSpec("errorMessage", FieldType.Utf8String),
        FieldSpec("valueHandle", FieldType.I32)
      )
    ),
    // Execute a JS function held by handle, with args supplied as a JSON-encoded array (so we don't have
    // to encode every primitive shape separately). Renderer JSON.parses the args inside the V8 context,
    // invokes the function via execute_function, packs the return value through the same JsResult layout
    // used by V8GetPropertyResponse — primitive inline, complex via valueHandle.
    MessageSpec(
      className = "V8ExecuteFunctionRequest",
      packageName = packageName,
      messageId = 18,
      fields = List(
        FieldSpec("frame", FieldType.RemoteHandle),
        FieldSpec("v8Handle", FieldType.I32),
        FieldSpec("argsJson", FieldType.Utf8String)
      )
    ),
    MessageSpec(
      className = "V8ExecuteFunctionResponse",
      packageName = packageName,
      messageId = 18,
      fields = List(
        FieldSpec("valueKind", FieldType.I32),
        FieldSpec("boolValue", FieldType.Bool),
        FieldSpec("intValue", FieldType.I32),
        FieldSpec("doubleValue", FieldType.I64),
        FieldSpec("stringValue", FieldType.Utf8String),
        FieldSpec("errorMessage", FieldType.Utf8String),
        FieldSpec("valueHandle", FieldType.I32)
      )
    ),
    MessageSpec(
      className = "V8ReleaseHandleRequest",
      packageName = packageName,
      messageId = 15,
      fields = List(
        FieldSpec("frame", FieldType.RemoteHandle),
        FieldSpec("v8Handle", FieldType.I32)
      )
    ),
    MessageSpec(
      className = "V8ReleaseHandleResponse",
      packageName = packageName,
      messageId = 15,
      fields = Nil
    ),

    // ----- More V8 method dispatchers, all on the same getProperty/executeFunction wire pattern. The
    //       value-encoding fields (valueKind/boolValue/intValue/doubleValue/stringValue/valueHandle) appear
    //       in setProperty too — they're a reusable JsValue payload, going JVM→renderer instead of back.
    MessageSpec(
      className = "V8SetPropertyRequest",
      packageName = packageName,
      messageId = 19,
      fields = List(
        FieldSpec("frame", FieldType.RemoteHandle),
        FieldSpec("v8Handle", FieldType.I32),
        FieldSpec("propertyName", FieldType.Utf8String),
        // JsValue payload: caller fills in whichever slot matches valueKind. valueHandle lets you assign
        // an existing V8 handle (e.g. an object retained from a prior eval) as a property of another.
        FieldSpec("valueKind", FieldType.I32),
        FieldSpec("boolValue", FieldType.Bool),
        FieldSpec("intValue", FieldType.I32),
        FieldSpec("doubleValue", FieldType.I64),
        FieldSpec("stringValue", FieldType.Utf8String),
        FieldSpec("valueHandle", FieldType.I32)
      )
    ),
    MessageSpec(
      className = "V8SetPropertyResponse",
      packageName = packageName,
      messageId = 19,
      fields = List(
        FieldSpec("ok", FieldType.Bool),
        FieldSpec("errorMessage", FieldType.Utf8String)
      )
    ),
    MessageSpec(
      className = "V8HasPropertyRequest",
      packageName = packageName,
      messageId = 20,
      fields = List(
        FieldSpec("frame", FieldType.RemoteHandle),
        FieldSpec("v8Handle", FieldType.I32),
        FieldSpec("propertyName", FieldType.Utf8String)
      )
    ),
    MessageSpec(
      className = "V8HasPropertyResponse",
      packageName = packageName,
      messageId = 20,
      fields = List(FieldSpec("has", FieldType.Bool))
    ),
    MessageSpec(
      className = "V8GetKeysRequest",
      packageName = packageName,
      messageId = 21,
      fields = List(
        FieldSpec("frame", FieldType.RemoteHandle),
        FieldSpec("v8Handle", FieldType.I32)
      )
    ),
    MessageSpec(
      className = "V8GetKeysResponse",
      packageName = packageName,
      messageId = 21,
      fields = List(
        FieldSpec("ok", FieldType.Bool),
        FieldSpec("keys", FieldType.StringList)
      )
    ),
    MessageSpec(
      className = "V8GetArrayLengthRequest",
      packageName = packageName,
      messageId = 22,
      fields = List(
        FieldSpec("frame", FieldType.RemoteHandle),
        FieldSpec("v8Handle", FieldType.I32)
      )
    ),
    MessageSpec(
      className = "V8GetArrayLengthResponse",
      packageName = packageName,
      messageId = 22,
      fields = List(
        FieldSpec("ok", FieldType.Bool),
        FieldSpec("length", FieldType.I32)
      )
    ),
    MessageSpec(
      className = "V8GetValueByIndexRequest",
      packageName = packageName,
      messageId = 23,
      fields = List(
        FieldSpec("frame", FieldType.RemoteHandle),
        FieldSpec("v8Handle", FieldType.I32),
        FieldSpec("index", FieldType.I32)
      )
    ),
    // Same JsResult shape as getProperty — primitives inline, complex via valueHandle.
    MessageSpec(
      className = "V8GetValueByIndexResponse",
      packageName = packageName,
      messageId = 23,
      fields = List(
        FieldSpec("valueKind", FieldType.I32),
        FieldSpec("boolValue", FieldType.Bool),
        FieldSpec("intValue", FieldType.I32),
        FieldSpec("doubleValue", FieldType.I64),
        FieldSpec("stringValue", FieldType.Utf8String),
        FieldSpec("errorMessage", FieldType.Utf8String),
        FieldSpec("valueHandle", FieldType.I32)
      )
    ),

    // ----- JVM-implemented JS function: JVM registers a global function name in the renderer's V8 context.
    //       When JS calls window.<name>(...args), the renderer's V8 handler JSON-serialises the args and
    //       fires JsFunctionCallEvent to the JVM. v1 is fire-and-forget — JS sees undefined return.
    //       Future: sync-return via renderer-blocked intercept (the V8 handler waits for a JVM response,
    //       sets retval, returns to JS).
    //       callbackId is the JVM's identifier (assigned at register time) so the JVM can route inbound
    //       JsFunctionCallEvents back to the right Java handler — same JvmCallbackTable pattern as visitors.
    MessageSpec(
      className = "RegisterJsFunctionRequest",
      packageName = packageName,
      messageId = 16,
      fields = List(
        FieldSpec("frame", FieldType.RemoteHandle),
        FieldSpec("name", FieldType.Utf8String),
        FieldSpec("callbackId", FieldType.I32)
      )
    ),
    MessageSpec(
      className = "RegisterJsFunctionResponse",
      packageName = packageName,
      messageId = 16,
      fields = Nil
    ),
    // Sent from renderer (relayed by browser) when JS invokes a registered global function.
    MessageSpec(
      className = "JsFunctionCallEvent",
      packageName = packageName,
      messageId = 17,
      fields = List(
        FieldSpec("callbackId", FieldType.I32),
        FieldSpec("argsJson", FieldType.Utf8String)
      )
    ),

    // ----- V8 context created event: fired when the renderer subprocess creates a V8 execution context for a
    //       frame (typically once per top-level navigation, plus per-iframe). Renderer-process side encodes the
    //       event into a CEF process message that travels over the existing browser↔renderer pipe; the runtime server's
    //       on_process_message_received in the browser process re-encodes it as an IPC event for the JVM. This
    //       is the renderer-process IPC-channel foundation: with it the JVM can observe renderer state, and a
    //       follow-up adds JVM→renderer dispatch (V8 method calls) by the inverse path.
    MessageSpec(
      className = "V8ContextCreatedEvent",
      packageName = packageName,
      messageId = 10,
      fields = List(
        FieldSpec("browser", FieldType.RemoteHandle),
        FieldSpec("frameUrl", FieldType.Utf8String)
      )
    ),

    // ----- OSR paint event: runtime server publishes browser pixel buffers via portable file-backed shared mappings
    //       and sends a small envelope describing which path to read. Java 11 opens it with FileChannel.map on every
    //       supported platform. byteCount is the populated tail of the buffer (most-recent
    //       paint), width/height describe the full bitmap. Sent as Kind::Event.
    MessageSpec(
      className = "OsrPaintEvent",
      packageName = packageName,
      messageId = 9,
      fields = List(
        FieldSpec("browser", FieldType.RemoteHandle),
        FieldSpec("shmName", FieldType.Utf8String),
        // Even sequence published in the shm header after the pixel copy completes. The JVM accepts a
        // snapshot only when the header equals this value both before and after its copy, so a producer
        // wraparound can drop a stale event but can never expose a torn frame.
        FieldSpec("frameSequence", FieldType.I64),
        FieldSpec("width", FieldType.I32),
        FieldSpec("height", FieldType.I32),
        FieldSpec("byteCount", FieldType.I32),
        FieldSpec("paintType", FieldType.I32),
        FieldSpec("dirtyX", FieldType.I32),
        FieldSpec("dirtyY", FieldType.I32),
        FieldSpec("dirtyWidth", FieldType.I32),
        FieldSpec("dirtyHeight", FieldType.I32)
      )
    ),

    // ----- Inline OSR paint event: transports that cannot share the runtime server's file-backed mapping publish a complete
    //       BGRA snapshot inside the IPC event. The WebSocket runtime server coalesces pending events by browser so
    //       slow consumers receive the newest frame rather than an ever-growing stale-frame backlog. This is
    //       intentionally uncompressed; compression/video is a separate frame-transport concern.
    MessageSpec(
      className = "InlinePaintEvent",
      packageName = packageName,
      messageId = 26,
      fields = List(
        FieldSpec("browser", FieldType.RemoteHandle),
        FieldSpec("frameSequence", FieldType.I64),
        FieldSpec("width", FieldType.I32),
        FieldSpec("height", FieldType.I32),
        FieldSpec("paintType", FieldType.I32),
        FieldSpec("dirtyX", FieldType.I32),
        FieldSpec("dirtyY", FieldType.I32),
        FieldSpec("dirtyWidth", FieldType.I32),
        FieldSpec("dirtyHeight", FieldType.I32),
        FieldSpec("pixels", FieldType.Bytes)
      )
    ),

    // ----- Raw DevTools Protocol bridge. CEF exposes complete UTF-8 JSON messages, which we preserve on
    //       the wire so higher layers can correlate CDP results and events without depending on a concrete
    //       control transport. Registration is runtime-server-owned because the generated callback facade cannot
    //       represent CEF's callback-scoped void* buffers safely.
    MessageSpec(
      className = "DevToolsAttachRequest",
      packageName = packageName,
      messageId = 27,
      fields = List(FieldSpec("browser", FieldType.RemoteHandle))
    ),
    MessageSpec(
      className = "DevToolsAttachResponse",
      packageName = packageName,
      messageId = 27,
      fields = Nil
    ),
    MessageSpec(
      className = "DevToolsMessageEvent",
      packageName = packageName,
      messageId = 28,
      fields = List(
        FieldSpec("browser", FieldType.RemoteHandle),
        FieldSpec("message", FieldType.Bytes)
      )
    ),
    MessageSpec(
      className = "DevToolsAgentDetachedEvent",
      packageName = packageName,
      messageId = 29,
      fields = List(FieldSpec("browser", FieldType.RemoteHandle))
    ),
    MessageSpec(
      className = "DevToolsDetachRequest",
      packageName = packageName,
      messageId = 30,
      fields = List(FieldSpec("browser", FieldType.RemoteHandle))
    ),
    MessageSpec(
      className = "DevToolsDetachResponse",
      packageName = packageName,
      messageId = 30,
      fields = Nil
    ),

    // ----- Test-only intercept echo: validates the synchronous runtime server→JVM callback wire end-to-end without
    //       needing a real CEF callback. JVM sends TriggerInterceptRequest{echoMessageId, echoPayload}; runtime server
    //       allocates a corrId, fires Kind::Intercept(echoMessageId, echoPayload), blocks waiting for a
    //       Kind::InterceptResponse with the same corrId. JVM-side test registers an intercept handler that
    //       returns a payload. Runtime server acks the original Request with TriggerInterceptResponse{returnedPayload}.
    MessageSpec(
      className = "TriggerInterceptRequest",
      packageName = packageName,
      messageId = 8,
      fields = List(
        FieldSpec("echoMessageId", FieldType.I32),
        FieldSpec("echoPayload", FieldType.Bytes)
      )
    ),
    MessageSpec(
      className = "TriggerInterceptResponse",
      packageName = packageName,
      messageId = 8,
      fields = List(FieldSpec("returnedPayload", FieldType.Bytes))
    ),

    // ----- Codegen-native demonstration messages (richer field mix, IDs 100+). -----
    MessageSpec(
      className = "NavigateRequest",
      packageName = packageName,
      messageId = 100,
      fields = List(
        FieldSpec("url", FieldType.Utf8String),
        FieldSpec("referrer", FieldType.Utf8String),
        FieldSpec("transitionType", FieldType.I32),
        FieldSpec("preserveSession", FieldType.Bool)
      )
    ),
    MessageSpec(
      className = "NavigateResult",
      packageName = packageName,
      messageId = 101,
      fields = List(
        FieldSpec("browserId", FieldType.I32),
        FieldSpec("httpStatus", FieldType.I32),
        FieldSpec("finalUrl", FieldType.Utf8String),
        FieldSpec("bytesLoaded", FieldType.I64),
        FieldSpec("ok", FieldType.Bool)
      )
    )
  )
}
