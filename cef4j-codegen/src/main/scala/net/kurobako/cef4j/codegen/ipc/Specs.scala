package net.kurobako.cef4j.codegen.ipc

object Specs {

  def all(packageName: String): List[MessageSpec] = List(
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

    // Renderer-owned handles must be released in the renderer process.
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
    MessageSpec(
      className = "EvaluateJavascriptRequest",
      packageName = packageName,
      messageId = 12,
      fields = List(
        FieldSpec("frame", FieldType.RemoteHandle),
        FieldSpec("code", FieldType.Utf8String),
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
        FieldSpec("doubleValue", FieldType.I64),
        FieldSpec("stringValue", FieldType.Utf8String),
        FieldSpec("errorMessage", FieldType.Utf8String),
        FieldSpec("valueHandle", FieldType.I32)
      )
    ),
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
    MessageSpec(
      className = "V8SetPropertyRequest",
      packageName = packageName,
      messageId = 19,
      fields = List(
        FieldSpec("frame", FieldType.RemoteHandle),
        FieldSpec("v8Handle", FieldType.I32),
        FieldSpec("propertyName", FieldType.Utf8String),
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
    MessageSpec(
      className = "JsFunctionCallEvent",
      packageName = packageName,
      messageId = 17,
      fields = List(
        FieldSpec("callbackId", FieldType.I32),
        FieldSpec("argsJson", FieldType.Utf8String)
      )
    ),
    MessageSpec(
      className = "V8ContextCreatedEvent",
      packageName = packageName,
      messageId = 10,
      fields = List(
        FieldSpec("browser", FieldType.RemoteHandle),
        FieldSpec("frameUrl", FieldType.Utf8String)
      )
    ),
    MessageSpec(
      className = "OsrPaintEvent",
      packageName = packageName,
      messageId = 9,
      fields = List(
        FieldSpec("browser", FieldType.RemoteHandle),
        FieldSpec("shmName", FieldType.Utf8String),
        // Stable even sequence guards against torn shared-memory frames.
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

    // Reserved test protocol for end-to-end intercept verification.
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
