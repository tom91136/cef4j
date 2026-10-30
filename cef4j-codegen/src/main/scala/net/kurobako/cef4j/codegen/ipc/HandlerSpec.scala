package net.kurobako.cef4j.codegen.ipc

case class HandlerSpec(
    className: String,
    packageName: String,
    cefStructName: String,
    methods: List[HandlerMethod]
)
